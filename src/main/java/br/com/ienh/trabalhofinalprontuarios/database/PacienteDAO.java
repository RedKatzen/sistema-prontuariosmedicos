package br.com.ienh.trabalhofinalprontuarios.database;

import br.com.ienh.trabalhofinalprontuarios.database.entidades.Paciente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class PacienteDAO {

    private Date converterLocalDate(LocalDate data) {
        return java.sql.Date.valueOf(data);
    }

    private LocalDate converterDate(Date data) {
        return data.toLocalDate();
    }

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

    public static Paciente obterPorId(int idBusca) throws Exception {
        Paciente paciente = null;
        Connection conn = DatabaseConn.getDatabaseConnection().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM pacientes WHERE id = "+idBusca);
        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String dataNascimento = rs.getString("data_nascimento");
            String sexo = rs.getString("sexo");
            String cpf = rs.getString("cpf");
            paciente = new Paciente(id, nome, dataNascimento, sexo, cpf);
        }
        return paciente;
    }

    public static int insere(Paciente paciente) throws Exception {
        Connection conn = DatabaseConn.getDatabaseConnection().getConnection();
        Statement stmt = conn.createStatement();
        int linhasAfetadas = stmt.executeUpdate("INSERT INTO pacientes (nome, data_nascimento, sexo, cpf) " +
                "VALUES ('"+paciente.getNome()+"', '"+paciente.getData_nascimento()+"', '"+paciente.getSexo()+"', '"+paciente.getCpf()+"')");
        return linhasAfetadas;
    }

    public static int atualiza(Paciente paciente) throws Exception {
        Connection conn = DatabaseConn.getDatabaseConnection().getConnection();
        Statement stmt = conn.createStatement();
        int linhasAfetadas = stmt.executeUpdate("UPDATE pacientes " +
                "SET nome = '" + paciente.getNome() + "', data_nascimento = '" + paciente.getData_nascimento() + "', sexo = '"+ paciente.getSexo() +"', cpf = '"+ paciente.getCpf() +"' " +
                "WHERE id = " + paciente.getId());
        return linhasAfetadas;
    }

    public static int exclui(Paciente paciente)  throws Exception {
        Connection conn = DatabaseConn.getDatabaseConnection().getConnection();
        Statement stmt = conn.createStatement();
        int linhasAfetadas = stmt.executeUpdate("DELETE FROM pacientes WHERE id = "+paciente.getId());
        return linhasAfetadas;
    }
}
