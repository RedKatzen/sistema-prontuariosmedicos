package br.com.ienh.trabalhofinalprontuarios.database.entidades;

public class Medico {
    private int id;
    private String nome;
    private String especialidade;
    private String cpf;
    private String data_admissao;
    private String email;

    public Medico(String nome, String especialidade, String cpf, String data_admissao, String email) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.cpf = cpf;
        this.data_admissao = data_admissao;
        this.email = email;
    }
    public Medico(int id, String nome, String especialidade, String cpf, String data_admissao, String email) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.cpf = cpf;
        this.data_admissao = data_admissao;
        this.email = email;
    }
    public Medico() {}

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getData_admissao() {
        return data_admissao;
    }

    public void setData_admissao(String data_admissao) {
        this.data_admissao = data_admissao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
