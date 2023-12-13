package Avaliacao02.Entidades;

import java.time.LocalDate;

public class Postagem {
    private int id;
    private String texto;
    private int curtidas = 0;
    private int descurtidas = 0;
    private LocalDate data; // Formato yyyy-MM-dd
    private Perfil perfil;

    public Postagem(int id, String texto, Perfil perfil) {
        this.texto = texto;
        this.perfil = perfil;
        this.data = LocalDate.now();
        this.id = id;
    }
    public Postagem(String texto, Perfil perfil, int curtidas, int descurtidas) {
        this.texto = texto;
        this.perfil = perfil;
        this.data = LocalDate.now();
        this.curtidas = curtidas;
        this.id = id;
    }

    public Postagem(int id, String texto, Perfil perfil, int curtidas, int descurtidas, LocalDate data) {
        this.texto = texto;
        this.curtidas = curtidas;
        this.descurtidas = descurtidas;
        this.data = data;
        this.perfil = perfil;
        this.id = id;
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

    public int getId() {
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

    public LocalDate getData() {
        return data;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    @Override
    public String toString() {
        return "ID Perfil: " + getPerfil().getId()
                + "\nID Postagem: " + getId()
                + "\nTexto: " + getTexto()
                + "\nCurtidas: " + getCurtidas()
                + "\nDescurtidas: " + getDescurtidas()
                +  "\nData: " + getData();
    }
}
