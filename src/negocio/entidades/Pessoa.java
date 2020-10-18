package negocio.entidades;

public class Pessoa {
    private String nome;
    private String cpf;
    private Endereco endereco;

    public Pessoa(String nome, String cpf, Endereco endereco){
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    //getters

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
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

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    @Override
    public String toString() {
        return "Nome: " + this.nome + "\nCPF: " + this.cpf +
                "\nEndereço: " + this.endereco;
    }
}
