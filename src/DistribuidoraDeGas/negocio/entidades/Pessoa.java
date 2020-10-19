package DistribuidoraDeGas.negocio.entidades;

public class Pessoa {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String telefone;
    private Endereco endereco;

    public Pessoa(String nome, String cpf, String dataNascimento, String telefone, Endereco endereco){
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    //getters

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }


    //setters

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    @Override
    public String toString() {
        return "Nome: " + this.nome + "\nCPF: " + this.cpf +
                "\nEndereço: " + this.endereco;
    }
}
