package Avaliacao01;

import Avaliacao01.Entidades.Perfil;
import Avaliacao01.Entidades.Postagem;
import Avaliacao01.Entidades.PostagemAvancada;
import Avaliacao01.Repositorios.RepositorioDePerfis;
import Avaliacao01.Repositorios.RepositorioDePostagens;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class RedeSocial {
    private RepositorioDePerfis repositorioDePerfis = new RepositorioDePerfis();
    private RepositorioDePostagens repositorioDePostagens = new RepositorioDePostagens();


    public boolean incluirPerfil(Perfil perfil){
        if(repositorioDePerfis.consultarPerfil(perfil.getNome(), perfil.getEmail()) == null){
            repositorioDePerfis.incluir(perfil);
            return true;
        }
        return false;
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

    public boolean incluirPostagem(Postagem postagem){
        if(repositorioDePostagens.consultarPostagem(postagem.getId()) == null){
            repositorioDePostagens.incluir(postagem);
            postagem.getPerfil().adicionarPostagem(postagem);
            return true;
        }
        return false;
    }

    public Postagem consultarPostagem(int id){
        Postagem postagem = repositorioDePostagens.consultarPostagem(id);
        if(((PostagemAvancada) postagem).podeExibir()){
            ((PostagemAvancada) postagem).decrementarVisualizacoes();
        }
        return postagem;
    }
    public List<Postagem> consultarPostagens(int id, String texto, Perfil perfil){
        return repositorioDePostagens.consultarPostagemCompleto(id, texto, perfil);
    }
    public List<PostagemAvancada> consultarPostagens(int id, String texto, Perfil perfil, String hashtag){
        return repositorioDePostagens.consultarPostagemAvancadaCompleto(id, texto, hashtag, perfil);
    }

    public boolean curtir(int idPostagem){
        Postagem postagem = repositorioDePostagens.consultarPostagem(idPostagem);
        if(postagem != null) {
            if (postagem instanceof PostagemAvancada) {
                if (((PostagemAvancada) postagem).podeExibir()) {
                    postagem.curtir();
                    return true;
                }
            } else {
                postagem.curtir();
                return true;
            }
        }
        return false;
    }

    public boolean descurtir(int idPostagem){
        Postagem postagem = repositorioDePostagens.consultarPostagem(idPostagem);
        if(postagem != null) {
            if (postagem instanceof PostagemAvancada) {
                if (((PostagemAvancada) postagem).podeExibir()) {
                    postagem.descurtir();
                    return true;
                }
            } else {
                postagem.descurtir();
                return true;
            }
        }
        return false;
    }

    public boolean decrementrarVisualizacoesRestantes(int idPostagem){
       Postagem postagem = consultarPostagem(idPostagem);
       if(postagem != null){
           if(postagem instanceof PostagemAvancada && ((PostagemAvancada) postagem).podeExibir()) {
               ((PostagemAvancada) postagem).decrementarVisualizacoes();
               return true;
           }
       }
       return false;
    }

    public List<Postagem> exibirPostagensPorPerfil(int idPerfil){
        Perfil perfil = repositorioDePerfis.consultarPerfil(idPerfil);
        if(perfil != null){
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
        } else {
            return null;
        }
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
