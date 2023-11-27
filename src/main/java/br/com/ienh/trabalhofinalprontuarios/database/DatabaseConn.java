package br.com.ienh.trabalhofinalprontuarios.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConn {
    private Connection conn;
    private static DatabaseConn db;

    private DatabaseConn() throws Exception {
        String url = "jdbc:mysql://localhost:3306/sisienh?serverTimezone=UTC";
        String usuario = "root";
        String senha = "root";
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, usuario, senha);
    }

    public static DatabaseConn getDatabaseConnection() throws Exception {
        if(db == null) db = new DatabaseConn();
        return db;
    }

    public Connection getConnection() {
        return conn;
    }
}
