package Avaliacao01.Repositorios;

import Avaliacao01.Entidades.Perfil;
import Avaliacao01.Entidades.Postagem;
import Avaliacao01.Entidades.PostagemAvancada;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDePostagens {
    private List<Postagem> postagens = new ArrayList<Postagem>();

    public void incluir(Postagem postagem){
        postagens.add(postagem);
    }

    public Postagem consultarPostagem(int id){
        for (Postagem postagem:
             postagens) {
            if(postagem.getId() == id){
                return postagem;
            }
        }
        return null;
    }

    public List<Postagem> consultarPostagemPorPerfil(Perfil perfil){
        List<Postagem> postagensDesejadas = new ArrayList<Postagem>();
        for (Postagem postagem:
             postagens) {
            if(postagem.getPerfil() == perfil){
                postagensDesejadas.add(postagem);
            }
        }
        return postagensDesejadas;
    }

    public List<PostagemAvancada> consultarPostagemPorHashtag(String hashtag){
        List<PostagemAvancada> postagensDesejadas = new ArrayList<PostagemAvancada>();
        for (Postagem postagem:
             postagens) {
            if(postagem instanceof PostagemAvancada){
                if(((PostagemAvancada) postagem).existeHashtag(hashtag)){
                    postagensDesejadas.add((PostagemAvancada) postagem);
                }
            }
        }
        return postagensDesejadas;
    }

    public List<Postagem> consultarPostagemPorTexto(String texto){
        List<Postagem> postagensDesejadas = new ArrayList<Postagem>();
        for (Postagem postagem:
             postagens) {
            String textoPost = postagem.getTexto();
            if(textoPost.contains(texto)){
                postagensDesejadas.add(postagem);
            }
        }
        return postagensDesejadas;
    }

    public List<Postagem>  consultarPostagemCompleto(int id, String texto, Perfil perfil){
        List<Postagem> postagensDesejadas = new ArrayList<Postagem>();
        for(Postagem postagem : postagens){
            if(postagem.getId() == id && postagem.getTexto().contains(texto) && postagem.getPerfil() == perfil){
                postagensDesejadas.add(postagem);
            }
        }
        return postagensDesejadas;
    }

    public List<PostagemAvancada>  consultarPostagemAvancadaCompleto(int id, String texto, String hashtag, Perfil perfil){
        List<PostagemAvancada> postagensDesejadas = new ArrayList<PostagemAvancada>();
        for (Postagem postagem:
             postagens) {
            if(postagem instanceof PostagemAvancada){
                if(postagem.getId() == id
                        && postagem.getTexto().contains(texto)
                        && ((PostagemAvancada) postagem).getHashtags().contains(hashtag)
                        && postagem.getPerfil() == perfil){
                    postagensDesejadas.add((PostagemAvancada) postagem);
                }
            }
        }
        return postagensDesejadas;
    }
}
