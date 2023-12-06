package br.com.ienh.trabalhofinalprontuarios.database;

import br.com.ienh.trabalhofinalprontuarios.database.entidades.Medico;
import br.com.ienh.trabalhofinalprontuarios.database.entidades.Prontuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProntuarioDAO {
    public static List<Prontuario> obterTodosProntuarios() throws Exception {
        List<Prontuario> prontuarios = new ArrayList<>();
        Connection conn = DatabaseConn.getDatabaseConnection().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM prontuarios");
        while(rs.next()) {
            int id = rs.getInt("id");
            int paciente_id = rs.getInt("paciente_id");
            String data_atendimento = rs.getString("data_atendimento");
            String data_alta = rs.getString("data_alta");
            String alergia = rs.getString("alergia");
            int medico_id = rs.getInt("medico_id");
            Prontuario prontuario = new Prontuario(id, paciente_id, data_atendimento, data_alta, alergia, medico_id);
            prontuarios.add(prontuario);
        }
        return prontuarios;
    }

    public static Prontuario obterPorId(int idBusca) throws Exception {
        Prontuario prontuario = null;
        Connection conn = DatabaseConn.getDatabaseConnection().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM prontuarios WHERE id = "+idBusca);
        while (rs.next()){
            int id = rs.getInt("id");
            int paciente_id = rs.getInt("paciente_id");
            String data_atendimento = rs.getString("data_atendimento");
            String data_alta = rs.getString("data_alta");
            String alergia = rs.getString("alergia");
            int medico_id = rs.getInt("medico_id");
            prontuario = new Prontuario(id, paciente_id, data_atendimento, data_alta, alergia, medico_id);
        }
        return prontuario;
    }

    public static int insere(Prontuario prontuario) throws Exception {
        Connection conn = DatabaseConn.getDatabaseConnection().getConnection();
        Statement stmt = conn.createStatement();
        int linhasAfetadas = stmt.executeUpdate("INSERT INTO prontuarios (paciente_id, data_atendimento, data_alta, alergia, medico_id)" +
                "VALUES ('"+prontuario.getPaciente_id()+"', '"+prontuario.getData_atendimento()+"', '"+prontuario.getData_alta()+"', '"+prontuario.getAlergia()+"', '"+prontuario.getMedico_id()+"')");
        return linhasAfetadas;
    }

    public static int atualiza(Prontuario prontuario) throws Exception {
        Connection conn = DatabaseConn.getDatabaseConnection().getConnection();
        Statement stmt = conn.createStatement();
        int linhasAfetadas = stmt.executeUpdate("UPDATE prontuarios " +
                "SET paciente_id = '" + prontuario.getPaciente_id() + "', data_atendimento = '"+prontuario.getData_atendimento()+"', data_alta = '"+prontuario.getData_alta()+"', alergia = '"+prontuario.getAlergia()+"', medico_id = '"+prontuario.getMedico_id()+"' " +
                "WHERE id = " + prontuario.getId());
        return linhasAfetadas;
    }

    public static int exclui(Prontuario prontuario)  throws Exception {
        Connection conn = DatabaseConn.getDatabaseConnection().getConnection();
        Statement stmt = conn.createStatement();
        int linhasAfetadas = stmt.executeUpdate("DELETE FROM prontuarios WHERE id = "+prontuario.getId());
        return linhasAfetadas;
    }
}
