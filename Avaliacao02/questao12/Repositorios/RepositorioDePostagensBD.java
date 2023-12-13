package Avaliacao02.questao12.Repositorios;

import Avaliacao02.questao12.Entidades.Perfil;
import Avaliacao02.questao12.Entidades.Postagem;
import Avaliacao02.questao12.Entidades.PostagemAvancada;
import Avaliacao02.questao12.Interfaces.IRepositorioPostagem;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDePostagensBD implements IRepositorioPostagem {
    private String url;
    private String usuario;
    private String senha;
    private Connection con;

    public RepositorioDePostagensBD() {
        url = "jdbc:postgresql://localhost:5432/redesocial";
        usuario = "postgres";
        senha = "admin";

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, usuario, senha);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void incluir(Postagem postagem) {
        try {
            String query;
            if (postagem instanceof PostagemAvancada) {
                query = "INSERT INTO postagem (id_postagem, id_perfil, texto, curtidas, descurtidas, data, tipo_postagem, hashtags, visualizacoes_restantes) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            } else {
                query = "INSERT INTO postagem (id_postagem, id_perfil, texto, curtidas, descurtidas, data, tipo_postagem) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";
            }

            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setInt(1, postagem.getId());
                preparedStatement.setInt(2, postagem.getPerfil().getId());
                preparedStatement.setString(3, postagem.getTexto());
                preparedStatement.setInt(4, postagem.getCurtidas());
                preparedStatement.setInt(5, postagem.getDescurtidas());
                preparedStatement.setDate(6, Date.valueOf(postagem.getData()));

                if (postagem instanceof PostagemAvancada) {
                    preparedStatement.setInt(7, 1); // Tipo de postagem avançada
                    preparedStatement.setString(8, String.join(",", ((PostagemAvancada) postagem).getHashtags()));
                    preparedStatement.setInt(9, ((PostagemAvancada) postagem).getVisualizacoesRestantes());
                } else {
                    preparedStatement.setInt(7, 0); // Tipo de postagem comum
                }

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Postagem consultarPostagem(int idPostagem) {
        String query = "SELECT * FROM postagem INNER JOIN perfil ON perfil.id_perfil = postagem.id_perfil WHERE id_postagem = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, idPostagem);
            try (ResultSet res = preparedStatement.executeQuery()) {
                if (res.next()) {
                    int idPerfil = res.getInt("id_perfil");
                    String nomePerfil = res.getString("nome");
                    String email = res.getString("email");

                    Perfil perfil = new Perfil(idPerfil, nomePerfil, email);

                    String texto = res.getString("texto");
                    int curtidas = res.getInt("curtidas");
                    int descurtidas = res.getInt("descurtidas");
                    LocalDate data = res.getDate("data").toLocalDate();
                    int tipoPostagem = res.getInt("tipo_postagem");

                    if (tipoPostagem == 0) {
                        return new Postagem(idPostagem, texto, perfil, curtidas, descurtidas, data);
                    } else {
                        List<String> hashtags = new ArrayList<>(List.of(res.getString("hashtags").split(",")));
                        int visualizacoesRestantes = res.getInt("visualizacoes_restantes");
                        return new PostagemAvancada(idPostagem, texto, perfil, curtidas, descurtidas, data,
                                hashtags, visualizacoesRestantes);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Postagem> consultarPostagemPorPerfil(Perfil perfil) {
        Statement statement;
        ResultSet res = null;
        List<Postagem> postagensDesejadas = new ArrayList<Postagem>();
        Postagem postagem = null;
        try{
            String query = "select * from postagem where id_perfil = " + perfil.getId();
            statement = con.createStatement();
            res = statement.executeQuery(query);
            while(res.next()){
                int idPostagem = res.getInt("id_postagem");
                String texto = res.getString("texto");
                int curtidas = res.getInt("curtidas");
                int descurtidas = res.getInt("descurtidas");
                LocalDate data = res.getDate("data").toLocalDate();
                int tipoPostagem = res.getInt("tipo_postagem");

                if(tipoPostagem == 0){
                    postagem = new Postagem(idPostagem, texto, perfil, curtidas, descurtidas, data);
                } else{
                    List<String> hashtags = new ArrayList<String>();
                    String hashtagsString = res.getString("hashtags");
                    hashtags = List.of(hashtagsString.split(","));
                    int visualizacoesRestantes = res.getInt("visualizacoes_restantes");
                    postagem = new PostagemAvancada(idPostagem, texto, perfil, curtidas, descurtidas, data,
                            hashtags, visualizacoesRestantes);
                }
                postagensDesejadas.add(postagem);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return postagensDesejadas;
    }

    public List<Postagem> consultarPostagemPorHashtag(String hashtag) {
        Statement statement;
        ResultSet res = null;
        List<Postagem> postagensDesejadas = new ArrayList<Postagem>();
        Postagem postagem = null;
        try{
            String query = String.format("SELECT postagem.*, perfil.* FROM postagem " +
                    "INNER JOIN perfil ON postagem.id_perfil = perfil.id_perfil " +
                    "WHERE postagem.hashtags ILIKE '%%,%s,%%' OR postagem.hashtags ILIKE '%%s,%%' OR postagem.hashtags " +
                    "ILIKE '%%,%s%%'", hashtag, hashtag, hashtag);
            statement = con.createStatement();
            res = statement.executeQuery(query);
            while(res.next()){
                int idPostagem = res.getInt("id_postagem");

                int idPerfil = res.getInt("id_perfil");
                String nome = res.getString("nome");
                String email = res.getString("email");
                Perfil perfil = new Perfil(idPerfil, nome, email);

                String texto = res.getString("texto");
                int curtidas = res.getInt("curtidas");
                int descurtidas = res.getInt("descurtidas");
                LocalDate data = res.getDate("data").toLocalDate();
                int tipoPostagem = res.getInt("tipo_postagem");

                if(tipoPostagem == 0){
                    postagem = new Postagem(idPostagem, texto, perfil, curtidas, descurtidas, data);
                } else{
                    List<String> hashtags = new ArrayList<String>();
                    String hashtagsString = res.getString("hashtags");
                    hashtags = List.of(hashtagsString.split(","));
                    int visualizacoesRestantes = res.getInt("visualizacoes_restantes");
                    postagem = new PostagemAvancada(idPostagem, texto, perfil, curtidas, descurtidas, data,
                            hashtags, visualizacoesRestantes);
                }
                postagensDesejadas.add(postagem);
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return postagensDesejadas;
    }

    public List<Postagem> consultarPostagemPorTexto(String texto) {
        return null;
    }

    public List<Postagem> consultarPostagemCompleto(int id, String texto, Perfil perfil) {
        return null;
    }

    public List<Postagem> consultarPostagemAvancadaCompleto(int id, String texto, String hashtag, Perfil perfil) {
        return null;
    }

    public void atualizarPostagemNoBD(Postagem postagem, String coluna, String valorAtualizado) {
        String query = String.format("UPDATE postagem SET %s = ? WHERE id_postagem = ?", coluna);
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, valorAtualizado);
            preparedStatement.setInt(2, postagem.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void atualizarPostagemNoBD(Postagem postagem, String coluna, int valorAtualizado) {
        String query = String.format("UPDATE postagem SET %s = ? WHERE id_postagem = ?", coluna);
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, valorAtualizado);
            preparedStatement.setInt(2, postagem.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Postagem> getPostagens(){
        Statement statement;
        ResultSet res = null;
        List<Postagem> postagens = new ArrayList<Postagem>();
        Postagem postagem = null;
        try{
            String query = "SELECT postagem.*, perfil.* FROM postagem " +
                    "INNER JOIN perfil ON postagem.id_perfil = perfil.id_perfil ";
            statement = con.createStatement();
            res = statement.executeQuery(query);
            while(res.next()){
                int idPostagem = res.getInt("id_postagem");

                int idPerfil = res.getInt("id_perfil");
                String nome = res.getString("nome");
                String email = res.getString("email");
                Perfil perfil = new Perfil(idPerfil, nome, email);

                String texto = res.getString("texto");
                int curtidas = res.getInt("curtidas");
                int descurtidas = res.getInt("descurtidas");
                LocalDate data = res.getDate("data").toLocalDate();
                int tipoPostagem = res.getInt("tipo_postagem");

                if(tipoPostagem == 0){
                    postagem = new Postagem(idPostagem, texto, perfil, curtidas, descurtidas, data);
                } else{
                    List<String> hashtags = new ArrayList<String>();
                    String hashtagsString = res.getString("hashtags");
                    hashtags = List.of(hashtagsString.split(","));
                    int visualizacoesRestantes = res.getInt("visualizacoes_restantes");
                    postagem = new PostagemAvancada(idPostagem, texto, perfil, curtidas, descurtidas, data,
                            hashtags, visualizacoesRestantes);
                }
                postagens.add(postagem);
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return postagens;
    }
}
