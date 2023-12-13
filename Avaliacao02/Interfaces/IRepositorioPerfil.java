package Avaliacao02.Interfaces;
import Avaliacao02.Entidades.Perfil;

import java.util.List;

public interface IRepositorioPerfil {
    public void incluir(Perfil perfil);

    public Perfil consultarPerfil(int id);

    public Perfil consultarPerfil(String nome, String email);

    public Perfil obterUltimoPerfil();

    public List<Perfil> getPerfis();
}
