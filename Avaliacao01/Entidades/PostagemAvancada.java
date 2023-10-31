package Avaliacao01.Entidades;

import java.util.ArrayList;
import java.util.List;

public class PostagemAvancada extends Postagem{
    private List<String> hashtags = new ArrayList<String>();
    private int visualizacoesRestantes;

    public PostagemAvancada(String texto, int curtidas, int descurtidas, String data, Perfil perfil
            , List<String> hashtags, int visualizacoesRestantes) {
        super(texto, curtidas, descurtidas, data, perfil);
        this.hashtags = hashtags;
        this.visualizacoesRestantes = visualizacoesRestantes;
    }

    public PostagemAvancada(String texto, Perfil perfil, String data, int id, List<String> hashtags, int visualizacoesRestantes) {
        super(texto, perfil, data, id);
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
        return super.toString()
                + "\nHashtags: " + getHashtags().toString()
                + "\nVisualizações restantes: " + getVisualizacoesRestantes();
    }
}
