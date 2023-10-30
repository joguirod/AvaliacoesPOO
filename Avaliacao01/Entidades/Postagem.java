package Avaliacao01.Entidades;

public class Postagem {
    static private int id;
    private String texto;
    private int curtidas = 0;
    private int descurtidas = 0;
    private String data; // Formato yyyy-MM-dd
    private Perfil perfil;

    public Postagem(String texto, Perfil perfil, String data) {
        this.texto = texto;
        this.perfil = perfil;
        this.data = data;
    }

    public Postagem(String texto, int curtidas, int descurtidas, String data, Perfil perfil) {
        this.texto = texto;
        this.curtidas = curtidas;
        this.descurtidas = descurtidas;
        this.data = data;
        this.perfil = perfil;
        id++;
    }

    public void curtir(){
        curtidas++;
    }

    public void descurtir(){
        descurtidas++;
    }

    public boolean ehPopular(){
        return curtidas > 1.5 * descurtidas;
    }

    public static int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public int getCurtidas() {
        return curtidas;
    }

    public int getDescurtidas() {
        return descurtidas;
    }

    public String getData() {
        return data;
    }

    public Perfil getPerfil() {
        return perfil;
    }
}
