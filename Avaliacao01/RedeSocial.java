package Avaliacao01;

import Avaliacao01.Entidades.Perfil;
import Avaliacao01.Entidades.Postagem;
import Avaliacao01.Entidades.PostagemAvancada;
import Avaliacao01.Repositorios.RepositorioDePerfis;
import Avaliacao01.Repositorios.RepositorioDePostagens;

import java.util.List;
import java.util.ArrayList;
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
            Perfil perfil = postagem.getPerfil();
            perfil.adicionarPostagem(postagem);
            repositorioDePostagens.incluir(postagem);
            return true;
        }
        return false;
    }

    public Postagem consultarPostagem(int id){
        return repositorioDePostagens.consultarPostagem(id);
    }
    public List<Postagem> consultarPostagens(int id, String texto, Perfil perfil){
        return repositorioDePostagens.consultarPostagemCompleto(id, texto, perfil);
    }
    public List<PostagemAvancada> consultarPostagens(int id, String texto, Perfil perfil, String hashtag){
        return repositorioDePostagens.consultarPostagemAvancadaCompleto(id, texto, hashtag, perfil);
    }

    public boolean curtir(int idPostagem){
        Postagem postagem = repositorioDePostagens.consultarPostagem(idPostagem);
        if(postagem != null){
            postagem.curtir();
            return true;
        }
        return false;
    }

    public boolean descurtir(int idPostagem){
        Postagem postagem = repositorioDePostagens.consultarPostagem(idPostagem);
        if (postagem != null){
            postagem.descurtir();
            return true;
        }
        return false;
    }

    public boolean decrementarVisualizacoes(PostagemAvancada postagem){
        if(postagem.getVisualizacoesRestantes() - 1 > 0){
            postagem.decrementarVisualizacoes();
            return true;
        }
        return false;
    }

    public List<Postagem> exibirPostagensPorPerfil(int idPerfil){
        Perfil perfil = repositorioDePerfis.consultarPerfil(idPerfil);
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
}
