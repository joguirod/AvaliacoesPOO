package Avaliacao01.Entidades;

import java.util.List;
import java.util.ArrayList;
public class Perfil {
    static private int id;
    private String nome;
    private String email;

    private List<Postagem> postagens = new ArrayList<Postagem>();
    public Perfil(String nome, String email) {
        this.nome = nome;
        this.email = email;
        id++;
    }

    public void adicionarPostagem(Postagem postagem){
        postagens.add(postagem);
    }

    public static int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public List<Postagem> getPostagens() {
        return postagens;
    }

    @Override
    public String toString() {
        return getId() + ", "
                + getNome() + ", "
                + getEmail();
    }
}
