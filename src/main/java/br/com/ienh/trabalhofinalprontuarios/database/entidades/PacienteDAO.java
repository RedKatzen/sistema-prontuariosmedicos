package br.com.ienh.trabalhofinalprontuarios.database.entidades;

import br.com.ienh.trabalhofinalprontuarios.database.DatabaseConn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {
    public static List<Paciente> obterTodosPacientes() throws Exception {
        List<Paciente> pacientes = new ArrayList<>();
        Connection conn = DatabaseConn.getDatabaseConnection().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM pacientes");
        while(rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String dataNascimento = rs.getString("data_nascimento");
            String sexo = rs.getString("sexo");
            String cpf = rs.getString("cpf");
            Paciente paciente = new Paciente(id, nome, dataNascimento, sexo, cpf);
            pacientes.add(paciente);
        }
        return pacientes;
    }
}
