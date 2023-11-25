package br.com.ienh.trabalhofinalprontuarios.database;

import br.com.ienh.trabalhofinalprontuarios.database.DatabaseConn;

import java.sql.Connection;

public class TesteConn {
    public static void test() {
        try {
            Connection conn = DatabaseConn.getDatabaseConnection().getConnection();
            boolean acessivel = conn.isValid(10);
            if(acessivel) { System.out.println("Conectado!"); } else { System.out.println("Não Conectado!"); }
        } catch (Exception e) {
            System.out.println("Não conectou!: "+e.getMessage());
        }
    }
}