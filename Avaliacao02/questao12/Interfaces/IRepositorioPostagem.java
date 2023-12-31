package Avaliacao02.questao12.Interfaces;
import Avaliacao02.questao12.Entidades.Perfil;
import Avaliacao02.questao12.Entidades.Postagem;

import java.util.List;

public interface IRepositorioPostagem {
    public void incluir(Postagem postagem);
    public Postagem consultarPostagem(int id);
    public List<Postagem> consultarPostagemPorPerfil(Perfil perfil);
    public List<Postagem> consultarPostagemPorHashtag(String hashtag);
    public List<Postagem> consultarPostagemPorTexto(String texto);
    public List<Postagem> consultarPostagemCompleto(int id, String texto, Perfil perfil);
    public List<Postagem> consultarPostagemAvancadaCompleto(int id, String texto, String hashtag, Perfil perfil);


}
