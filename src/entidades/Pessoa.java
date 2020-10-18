package entidades;

public class Pessoa {
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private Endereco endereco;

    public Pessoa(String nome, String email, String senha, String cpf, Endereco endereco){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    //getters

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getSenha() {
        return senha;
    }

    //setters

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "\nEmail: " + this.email + "\nCPF: " + this.cpf +
                "\nEndereço: " + this.endereco;
    }
}
