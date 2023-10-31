package Avaliacao01.Entidades;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Perfil {
    private int id;
    private String nome;
    private String email;

    private List<Postagem> postagens = new ArrayList<Postagem>();
    public Perfil(String nome, String email, int id) {
        this.nome = nome;
        this.email = email;
        this.id = id;
    }

    public void adicionarPostagem(Postagem postagem){
        postagens.add(postagem);
    }

    public int getId() {
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
