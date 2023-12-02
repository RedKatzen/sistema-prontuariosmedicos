package br.com.ienh.trabalhofinalprontuarios.database.entidades;

public class Prontuario {
    private int id;
    private int paciente_id;
    private String data_atendimento;
    private String data_alta;
    private String alergia;

    public Prontuario(int paciente_id, String data_atendimento, String data_alta, String alergia) {
        this.paciente_id = paciente_id;
        this.data_atendimento = data_atendimento;
        this.data_alta = data_alta;
        this.alergia = alergia;
    }
    public Prontuario(int id, int paciente_id, String data_atendimento, String data_alta, String alergia) {
        this.id = id;
        this.paciente_id = paciente_id;
        this.data_atendimento = data_atendimento;
        this.data_alta = data_alta;
        this.alergia = alergia;
    }
    public Prontuario() {}

    public int getId() {
        return id;
    }

    public int getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(int paciente_id) {
        this.paciente_id = paciente_id;
    }

    public String getData_atendimento() {
        return data_atendimento;
    }

    public void setData_atendimento(String data_atendimento) {
        this.data_atendimento = data_atendimento;
    }

    public String getData_alta() {
        return data_alta;
    }

    public void setData_alta(String data_alta) {
        this.data_alta = data_alta;
    }

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }
}
