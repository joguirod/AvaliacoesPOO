package Avaliacao01;

import Avaliacao01.Entidades.Perfil;
import Avaliacao01.Entidades.Postagem;
import Avaliacao01.Entidades.PostagemAvancada;
import Avaliacao01.Repositorios.RepositorioDePerfis;
import Avaliacao01.Repositorios.RepositorioDePostagens;

import java.util.List;
import java.util.ArrayList;
public class RedeSocial {
    private RepositorioDePerfis repositorioDePerfis;
    private RepositorioDePostagens repositorioDePostagens;

    public void incluirPerfil(Perfil perfil){
        if(repositorioDePerfis.consultarPerfil(perfil.getId(), perfil.getNome(), perfil.getEmail()) == null){
            repositorioDePerfis.incluir(perfil);
        }
    }
    public Perfil consultarPerfil(int id){
        return repositorioDePerfis.consultarPerfil(id);
    }

    public List<Perfil> perfisCadastrados(){
        return repositorioDePerfis.getPerfis();
    }

    public void incluirPostagem(Postagem postagem){
        if(repositorioDePostagens.consultarPostagem(postagem.getId()) == null){
            repositorioDePostagens.incluir(postagem);
        }
    }

    public List<Postagem> consultarPostagens(int id, String texto, Perfil perfil){
        return repositorioDePostagens.consultarPostagemCompleto(id, texto, perfil);
    }
    public List<PostagemAvancada> consultarPostagens(int id, String texto, Perfil perfil, String hashtag){
        return repositorioDePostagens.consultarPostagemAvancadaCompleto(id, texto, hashtag, perfil);
    }

    public void curtir(int idPostagem){
        Postagem postagem = repositorioDePostagens.consultarPostagem(idPostagem);
        postagem.curtir();
    }

    public void descurtir(int idPostagem){
        Postagem postagem = repositorioDePostagens.consultarPostagem(idPostagem);
        postagem.descurtir();
    }

    public void decrementarVisualizacoes(PostagemAvancada postagem){
        if(postagem.getVisualizacoesRestantes() - 1 > 0){
            postagem.decrementarVisualizacoes();
        }
    }

    public List<Postagem> exibirPostagensPorPerfil(int idPerfil){
        Perfil perfil = repositorioDePerfis.consultarPerfil(idPerfil);
        List<Postagem> postagensDoPerfil = repositorioDePostagens.consultarPostagemPorPerfil(perfil);
        List<Postagem> postagensFiltradas = new ArrayList<Postagem>();
        for (Postagem postagem:
             postagensDoPerfil) {
            if(postagem instanceof PostagemAvancada){
                ((PostagemAvancada) postagem).decrementarVisualizacoes();
            }
            if(((PostagemAvancada)postagem).podeExibir()){
                postagensFiltradas.add(postagem);
            }
        }
        return postagensFiltradas;
    }

    public List<PostagemAvancada> exibirPostagensPorHashtags(String hashtag){
        List<PostagemAvancada> postagensDoPerfil = repositorioDePostagens.consultarPostagemPorHashtag(hashtag);
        List<PostagemAvancada> postagensFiltradas = new ArrayList<PostagemAvancada>();
        for (Postagem postagem:
                postagensDoPerfil) {
            if(postagem instanceof PostagemAvancada){
                ((PostagemAvancada) postagem).decrementarVisualizacoes();
            }
            if(((PostagemAvancada)postagem).podeExibir()){
                postagensFiltradas.add((PostagemAvancada)postagem);
            }
        }
        return postagensFiltradas;
    }
}
