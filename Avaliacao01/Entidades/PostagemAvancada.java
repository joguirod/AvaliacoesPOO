package Avaliacao01.Entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PostagemAvancada extends Postagem{
    private List<String> hashtags = new ArrayList<String>();
    private int visualizacoesRestantes;

    public PostagemAvancada(String texto, Perfil perfil, int curtidas, int descurtidas, LocalDate data
            , List<String> hashtags, int visualizacoesRestantes) {
        super(texto, perfil, curtidas, descurtidas, data);
        this.hashtags = hashtags;
        this.visualizacoesRestantes = visualizacoesRestantes;
    }

    public PostagemAvancada(String texto, Perfil perfil, int curtidas, int descurtidas, List<String> hashtags, int visualizacoesRestantes) {
        super(texto, perfil, curtidas, descurtidas);
        this.hashtags = hashtags;
        this.visualizacoesRestantes = visualizacoesRestantes;
    }

    public PostagemAvancada(String texto, Perfil perfil, List<String> hashtags, int visualizacoesRestantes) {
        super(texto, perfil);
        this.hashtags = hashtags;
        this.visualizacoesRestantes = visualizacoesRestantes;
    }

    public void adicionarHashtag(String hashtag){
        hashtags.add(hashtag);
    }

    public boolean existeHashtag(String hashtag){
        return hashtags.contains(hashtag);
    }

    public void decrementarVisualizacoes(){
        visualizacoesRestantes--;
    }

    public boolean podeExibir(){
        return visualizacoesRestantes > 0;
    }
    public List<String> getHashtags() {
        return hashtags;
    }

    public int getVisualizacoesRestantes() {
        return visualizacoesRestantes;
    }

    @Override
    public String toString() {
        String hashtagsString = "";
        int count = 0;
        for (String hashtag:
             getHashtags()) {
            if(getHashtags().size()-1 == count){
                hashtagsString += "#"+hashtag;
            } else{
                hashtagsString += "#"+hashtag + ", ";
            }
            count++;
        }
        return super.toString()
                + "\nHashtags: " + hashtagsString
                + "\nVisualizações restantes: " + getVisualizacoesRestantes();
    }
}
