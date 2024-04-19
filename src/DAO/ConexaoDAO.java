package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoDAO {
    static String stringConexao = "jdbc:postgresql://localhost:5432/javaONG";
    static String usuario = "postgres";
    static String senha = "postgres";

    public static Connection getConnection() {
        Connection conexao = null;
        try {
            return DriverManager.getConnection(stringConexao, usuario, senha);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}