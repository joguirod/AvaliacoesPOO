package Avaliacao02.Repositorios;

import Avaliacao02.Entidades.Perfil;
import Avaliacao02.Interfaces.IRepositorioPerfil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDePerfisBD implements IRepositorioPerfil {

    private String url;
    private String usuario;
    private String senha;
    private Connection con;
    private PreparedStatement preparedStatement;

    public RepositorioDePerfisBD() {
        url = "jdbc:postgresql://localhost:5432/redesocial";
        usuario = "postgres";
        senha = "admin";

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, usuario, senha);
            // Prepare the statement for reuse
            preparedStatement = con.prepareStatement("INSERT INTO perfil (id_perfil, nome, email) VALUES (?, ?, ?)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void incluir(Perfil perfil) {
        try {
            // Set values for the prepared statement
            preparedStatement.setInt(1, perfil.getId());
            preparedStatement.setString(2, perfil.getNome());
            preparedStatement.setString(3, perfil.getEmail());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // procura no banco de dados na coluna id_perfil, se não encontrar retorna null.
    public Perfil consultarPerfil(int id) {
        String query = "select * from perfil where id_perfil = " + id;
        ResultSet res = null;
        try {
            Statement statement = con.createStatement();
            res = statement.executeQuery(query);

            // Verifica se há resultados antes de acessar os dados
            if (res.next()) {
                String nome = res.getString("nome");
                String email = res.getString("email");
                Perfil perfil = new Perfil(id, nome, email);
                return perfil;
            } else {
                // Se não houver resultados, retorne null ou faça algo apropriado
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Perfil consultarPerfil(String nome, String email) {
        String query = "SELECT * FROM perfil WHERE nome = ? OR email = ?";
        ResultSet res = null;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, email);

            res = preparedStatement.executeQuery();

            if (res.next()) {
                int id = res.getInt("id_perfil");
                Perfil perfil = new Perfil(id, nome, email);
                return perfil;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Perfil obterUltimoPerfil() {
        String query = "SELECT * FROM perfil WHERE id_perfil = (SELECT MAX(id_perfil) FROM perfil)";
        ResultSet res = null;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            res = preparedStatement.executeQuery();

            if (res.next()) {
                int id = res.getInt("id_perfil");
                String nome = res.getString("nome");
                String email = res.getString("email");
                Perfil perfil = new Perfil(id, nome, email);
                return perfil;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Perfil> getPerfis(){
        String query = "select * from perfil";
        ResultSet res = null;
        List<Perfil> perfis = new ArrayList<Perfil>();
        try{
            Statement statement = con.createStatement();
            res = statement.executeQuery(query);
            while(res.next()){
                int id = res.getInt("id_perfil");
                String nome = res.getString("nome");
                String email = res.getString("email");
                Perfil perfil = new Perfil(id, nome, email);
                perfis.add(perfil);
            }
        } catch(Exception e){

        }
        return perfis;
    }
}
