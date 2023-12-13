package Avaliacao02.questao12;

import Avaliacao02.questao12.Entidades.Perfil;
import Avaliacao02.questao12.Entidades.Postagem;
import Avaliacao02.questao12.Entidades.PostagemAvancada;
import Avaliacao02.questao12.Exceptions.*;
import Avaliacao02.questao12.Exceptions.*;
import Avaliacao02.questao12.Interfaces.IRedeSocial;
import Avaliacao02.questao12.Repositorios.RepositorioDePerfisArrayList;
import Avaliacao02.questao12.Repositorios.RepositorioDePostagensArrayList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedeSocialArray implements IRedeSocial {
    private RepositorioDePerfisArrayList repositorioDePerfis = new RepositorioDePerfisArrayList();
    private RepositorioDePostagensArrayList repositorioDePostagens = new RepositorioDePostagensArrayList();


    public void incluirPerfil(Perfil perfil) throws ContaJaExiste {
        if(repositorioDePerfis.consultarPerfil(perfil.getId()) != null){
            throw new ContaJaExiste("Tentativa de criação de conta falhou. Uma conta com um dos atributos já existe.");
        }
        repositorioDePerfis.incluir(perfil);
    }
    public Perfil consultarPerfil(int id){
        return repositorioDePerfis.consultarPerfil(id);
    }

    public List<Perfil> perfisCadastrados(){
        return repositorioDePerfis.getPerfis();
    }
    public List<Postagem> postagensCadastradas(){
        return repositorioDePostagens.getPostagens();
    }

    public void incluirPostagem(Postagem postagem) throws PostagemExistente {
        if(repositorioDePostagens.consultarPostagem(postagem.getId()) != null){
            throw new PostagemExistente("Postagem não adicionada. Uma postagem com mesmo id já existe.");
        }
        repositorioDePostagens.incluir(postagem);
    }

    public Postagem consultarPostagem(int id) throws PostagemNaoExiste, PostagemNaoPodeExibir {
        Postagem postagem = repositorioDePostagens.consultarPostagem(id);

        // Adicação de lançamento de exceção
        if(postagem == null){
            throw new PostagemNaoExiste("A postagem a ser consultada não existe.");
        }

       if(postagem instanceof PostagemAvancada){
          if(!((PostagemAvancada) postagem).podeExibir()){
              throw new PostagemNaoPodeExibir("A postagem não pode mais ser exibida.");
          }
          else{
              ((PostagemAvancada) postagem).decrementarVisualizacoes();
          }
       }
        return postagem;
    }
    public List<Postagem> consultarPostagens(int id, String texto, Perfil perfil){
        return repositorioDePostagens.consultarPostagemCompleto(id, texto, perfil);
    }
    public List<PostagemAvancada> consultarPostagens(int id, String texto, Perfil perfil, String hashtag){
        return repositorioDePostagens.consultarPostagemAvancadaCompleto(id, texto, hashtag, perfil);
    }

    public void curtir(int idPostagem) throws PostagemNaoExiste {
        Postagem postagem = repositorioDePostagens.consultarPostagem(idPostagem);

        // Adição de lançamento de erros.
        if(postagem == null){
            throw new PostagemNaoExiste("A postagem a ser curtida não existe!");
        }
        postagem.curtir();
    }

    public void descurtir(int idPostagem) throws PostagemNaoExiste {
        Postagem postagem = repositorioDePostagens.consultarPostagem(idPostagem);

        // Adição de lançamento de erros.
        if(postagem == null){
            throw new PostagemNaoExiste("A postagem a ser descurtida não existe!");
        }
        postagem.descurtir();
    }

    public void decrementrarVisualizacoesRestantes(int idPostagem) throws PostagemNaoExiste, PostagemNaoPodeExibir {
        Postagem postagem = repositorioDePostagens.consultarPostagem(idPostagem);

        // Adição de lançamento de erros.
        if(postagem == null){
            throw new PostagemNaoExiste("A postagem a ter as vizualizações decrementadas não existe!");
        }
        if(((PostagemAvancada)postagem).getVisualizacoesRestantes() == 0){
            throw new PostagemNaoPodeExibir("A postagem não possui mais visualizações restantes para decrementar.");
        }
        ((PostagemAvancada) postagem).decrementarVisualizacoes();
    }

    public List<Postagem> exibirPostagensPorPerfil(int idPerfil) throws ContaNaoEncontrada {
        Perfil perfil = repositorioDePerfis.consultarPerfil(idPerfil);

        // Adição de lançamento de exceção
        if(perfil == null){
            throw new ContaNaoEncontrada("Perfil informado não encontrado.");
        }
        List<Postagem> postagensDoPerfil = repositorioDePostagens.consultarPostagemPorPerfil(perfil);
        List<Postagem> postagensFiltradas = new ArrayList<Postagem>();
        for (Postagem postagem:
             postagensDoPerfil) {
            if(postagem instanceof PostagemAvancada && ((PostagemAvancada)postagem).podeExibir()){
                postagensFiltradas.add(postagem);
                ((PostagemAvancada) postagem).decrementarVisualizacoes();
            }
            else if (!(postagem instanceof PostagemAvancada)) {
                postagensFiltradas.add(postagem);
            }
        }
        return postagensFiltradas;
    }

    public List<PostagemAvancada> exibirPostagensPorHashtags(String hashtag){
        List<PostagemAvancada> postagensDoPerfil = repositorioDePostagens.consultarPostagemPorHashtag(hashtag);
        List<PostagemAvancada> postagensFiltradas = new ArrayList<PostagemAvancada>();
        for (PostagemAvancada postagem:
                postagensDoPerfil) {
            if(postagem.podeExibir()){
                postagensFiltradas.add(postagem);
                postagem.decrementarVisualizacoes();
            }
        }
        return postagensFiltradas;
    }

    public List<String> obterHashtagsMaisPopulares() {
        List<Postagem> postagens = repositorioDePostagens.getPostagens();
        List<String> todasHashtags = new ArrayList<>();

        // Adicionar todas as hashtags em uma lista
        for (Postagem postagem : postagens) {
            if (postagem instanceof PostagemAvancada) {
                todasHashtags.addAll(((PostagemAvancada) postagem).getHashtags());
            }
        }

        Map<String, Integer> contagemHashtags = new HashMap<>();

        for (String hashtag : todasHashtags) {
            contagemHashtags.put(hashtag, contagemHashtags.getOrDefault(hashtag, 0) + 1);
        }

        List<Map.Entry<String, Integer>> sortedHashtags = new ArrayList<>(contagemHashtags.entrySet());
        sortedHashtags.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        List<String> hashtagsMaisPopulares = new ArrayList<>();

        int limite = Math.min(3, sortedHashtags.size());
        for (int i = 0; i < limite; i++) {
            hashtagsMaisPopulares.add(sortedHashtags.get(i).getKey());
        }

        return hashtagsMaisPopulares;
    }
    public List<Postagem> exibirPostagensPopulares(){
        List<Postagem> postagensFiltradas = new ArrayList<Postagem>();
        for(Postagem postagem : repositorioDePostagens.getPostagens()){
            if(postagem instanceof PostagemAvancada){
                if(postagem.ehPopular() && ((PostagemAvancada) postagem).podeExibir()){
                    postagensFiltradas.add(postagem);
                    ((PostagemAvancada) postagem).decrementarVisualizacoes();
                }
            } else if (postagem.ehPopular()){
                postagensFiltradas.add(postagem);
            }
        }
        return postagensFiltradas;
    }

    public Postagem exibirPostagemMaisCurtida() {
        Postagem postagemMaisCurtida = null;
        int maxCurtidas = -1;
        for (Postagem postagem :
                repositorioDePostagens.getPostagens()) {
            if (postagem.getCurtidas() > maxCurtidas && postagem.getCurtidas() > 0) {
                if (!(postagem instanceof PostagemAvancada)) {
                    postagemMaisCurtida = postagem;
                    maxCurtidas = postagem.getCurtidas();
                } else {
                    if (((PostagemAvancada) postagem).podeExibir()) {
                        postagemMaisCurtida = postagem;
                    }
                }
            }
        }
        if (postagemMaisCurtida instanceof PostagemAvancada) {
            ((PostagemAvancada) postagemMaisCurtida).decrementarVisualizacoes();
            return postagemMaisCurtida;
        }
        return postagemMaisCurtida;
    }

    public Postagem obterPostagemMaisDescurtida() {
        Postagem postagemMaisDescurtida = null;
        int maxDescurtidas = -1;

        for (Postagem postagem : repositorioDePostagens.getPostagens()) {
            if (postagem.getDescurtidas() > maxDescurtidas && postagem.getDescurtidas() > 0) {
                if (!(postagem instanceof PostagemAvancada)) {
                    postagemMaisDescurtida = postagem;
                    maxDescurtidas = postagem.getDescurtidas();
                } else {
                    if (((PostagemAvancada) postagem).podeExibir()) {
                        postagemMaisDescurtida = postagem;
                        maxDescurtidas = postagem.getDescurtidas();
                    }
                }
            }
        }

        if (postagemMaisDescurtida instanceof PostagemAvancada) {
            ((PostagemAvancada) postagemMaisDescurtida).decrementarVisualizacoes();
        }

        return postagemMaisDescurtida;
    }

    public Postagem obterPostagemMaisVisualizacoesRestantes() {
        Postagem postagemMaisVisualizacoesRestantes = null;
        int maxVisualizacoesRestantes = -1;

        for (Postagem postagem : repositorioDePostagens.getPostagens()) {
            if (postagem instanceof PostagemAvancada && ((PostagemAvancada) postagem).podeExibir() && ((PostagemAvancada)
                    postagem).getVisualizacoesRestantes() > maxVisualizacoesRestantes) {
                postagemMaisVisualizacoesRestantes = postagem;
                maxVisualizacoesRestantes = ((PostagemAvancada) postagem).getVisualizacoesRestantes();
            }
        }

        if (postagemMaisVisualizacoesRestantes instanceof PostagemAvancada) {
            ((PostagemAvancada) postagemMaisVisualizacoesRestantes).decrementarVisualizacoes();
        }
        return postagemMaisVisualizacoesRestantes;
    }
}
