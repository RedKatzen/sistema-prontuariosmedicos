package br.com.ienh.trabalhofinalprontuarios.database;

import br.com.ienh.trabalhofinalprontuarios.database.DatabaseConn;
import br.com.ienh.trabalhofinalprontuarios.database.entidades.Medico;
import br.com.ienh.trabalhofinalprontuarios.database.entidades.Paciente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {
    public static List<Medico> obterTodosMedicos() throws Exception {
        List<Medico> medicos = new ArrayList<>();
        Connection conn = DatabaseConn.getDatabaseConnection().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM medicos");
        while(rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String especialidade = rs.getString("especialidade");
            String cpf = rs.getString("cpf");
            String data_admissao = rs.getString("data_admissao");
            String email = rs.getString("email");
            Medico medico = new Medico(id, nome, especialidade, cpf, data_admissao, email);
            medicos.add(medico);
        }
        return medicos;
    }

    public static Medico obterPorId(int idBusca) throws Exception {
        Medico medico = null;
        Connection conn = DatabaseConn.getDatabaseConnection().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM medicos WHERE id = "+idBusca);
        while (rs.next()){
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String especialidade = rs.getString("especialidade");
            String cpf = rs.getString("cpf");
            String data_admissao = rs.getString("data_admissao");
            String email = rs.getString("email");
            medico = new Medico(id, nome, especialidade, cpf, data_admissao, email);
        }
        return medico;
    }

    public static int insere(Medico medico) throws Exception {
        Connection conn = DatabaseConn.getDatabaseConnection().getConnection();
        Statement stmt = conn.createStatement();
        int linhasAfetadas = stmt.executeUpdate("INSERT INTO medicos (nome, especialidade, cpf, data_admissao, email)" +
                "VALUES ('"+medico.getNome()+"', '"+medico.getEspecialidade()+"', '"+medico.getCpf()+"', '"+medico.getData_admissao()+"', '"+medico.getEmail()+"')");
        return linhasAfetadas;
    }

    public static int atualiza(Medico medico) throws Exception {
        Connection conn = DatabaseConn.getDatabaseConnection().getConnection();
        Statement stmt = conn.createStatement();
        int linhasAfetadas = stmt.executeUpdate("UPDATE medicos " +
                "SET nome = '" + medico.getNome() + "', especialidade = '"+medico.getEspecialidade()+"', cpf = '"+medico.getCpf()+"', data_admissao '"+medico.getData_admissao()+"', email = '"+medico.getEmail()+"' " +
                "WHERE id = " + medico.getId());
        return linhasAfetadas;
    }

    public static int exclui(Medico medico)  throws Exception {
        Connection conn = DatabaseConn.getDatabaseConnection().getConnection();
        Statement stmt = conn.createStatement();
        int linhasAfetadas = stmt.executeUpdate("DELETE FROM medicos WHERE id = "+medico.getId());
        return linhasAfetadas;
    }
}
