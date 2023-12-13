package Avaliacao02.questao12.Interfaces;
import Avaliacao02.questao12.Entidades.Perfil;

import java.util.List;

public interface IRepositorioPerfil {
    public void incluir(Perfil perfil);

    public Perfil consultarPerfil(int id);

    public Perfil consultarPerfil(String nome, String email);

    public Perfil obterUltimoPerfil();

    public List<Perfil> getPerfis();
}
