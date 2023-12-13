package Avaliacao02.Entidades;

import java.util.ArrayList;
import java.util.List;

public class Perfil {
    private int id;
    private String nome;
    private String email;
    private List<Postagem> postagens = new ArrayList<Postagem>();

    public Perfil(int id, String nome, String email) {
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
                + getEmail() + ", "
                + "Postagens associadas: " + getPostagens().size();
    }
}
