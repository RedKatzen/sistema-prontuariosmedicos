package br.com.ienh.trabalhofinalprontuarios.database.entidades;

public class Paciente {
    private int id;
    private String nome;
    private String data_nascimento;
    private String sexo;
    private String cpf;

    public Paciente(int id, String nome, String dataNascimento, String sexo, String cpf) {
        this.id = id;
        this.nome = nome;
        this.data_nascimento = dataNascimento;
        this.sexo = sexo;
        this.cpf = cpf;
    }
    public Paciente(String nome, String dataNascimento, String sexo, String cpf) {
        this.nome = nome;
        this.data_nascimento = dataNascimento;
        this.sexo = sexo;
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_nascimento()  {
        return this.data_nascimento;
    }

    public void setData_nascimento(String dataNascimento) {
        this.data_nascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
