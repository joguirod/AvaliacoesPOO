package Avaliacao01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conexao {
    private String url;
    private String usuario;
    private String senha;
    private Connection con;

    public Conexao() {
        url = "jdbc:postgresql://localhost:5432/postres";
        usuario = "postgres";
        senha = "admin";

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão com banco de dados finalizada com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int executaSql(String sql) {
        try {
            Statement stm = con.createStatement();
            int res = stm.executeUpdate(sql);
            con.close();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void inserirPerfil(String tabela, int idPerfil, String nome, String email) {
        Statement statement;
        try {
            String query = String.format("insert into %s(id_perfil, nome, email) values (%d, %s, %s)", tabela,
                    idPerfil, nome, email);
            statement = con.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}


