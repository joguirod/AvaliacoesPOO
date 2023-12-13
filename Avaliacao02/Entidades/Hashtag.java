package Avaliacao02.Entidades;

public class Hashtag {
    private int idHashtag;
    private String nomeHashtag;
    private int qtdUtilizada;

    public Hashtag(int idHashtag, String nomeHashtag){
        this.idHashtag = idHashtag;
        this.nomeHashtag = nomeHashtag;
        qtdUtilizada = 1;
    }



    public void incrementarQtd(){
        qtdUtilizada++;
    }
}
