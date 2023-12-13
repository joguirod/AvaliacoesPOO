package Avaliacao01;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbFunctions {
    public Connection connectToDb(String dbname, String user, String password){
        Connection conn = null;

        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname);
            if(conn != null){
                System.out.println("Connection Established.");
            } else{
                System.out.println("Connection Failed.");
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return conn;
    }

    public void createTable(Connection conn, String table_name){

    }
}
