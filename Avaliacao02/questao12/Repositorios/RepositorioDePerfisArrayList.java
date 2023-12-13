package Avaliacao02.questao12.Repositorios;

import Avaliacao02.questao12.Entidades.Perfil;
import Avaliacao02.questao12.Interfaces.IRepositorioPerfil;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDePerfisArrayList implements IRepositorioPerfil {
    private List<Perfil> perfis = new ArrayList<Perfil>();

    public void incluir(Perfil perfil){
        perfis.add(perfil);
    }

    public Perfil consultarPerfil(int id){
        for (Perfil perfil:
             perfis) {
            if(perfil.getId() == id){
                return perfil;
            }
        }
        return null;
    }

    public Perfil consultarPerfil(String nome, String email){
        for (Perfil perfil:
             perfis) {
            if(perfil.getNome().equals(nome) || perfil.getEmail().equals(email)){
                return perfil;
            }
        }
        return null;
    }

    public Perfil obterUltimoPerfil(){
        int tamanhoRepositorio = getPerfis().size();
        return perfis.get(tamanhoRepositorio - 1);
    }

    public List<Perfil> getPerfis(){
        return perfis;
    }
}
