package negocio.entidades;

public class Gerente extends Pessoa{
    private String email;
    private String senha;
    private String cnpj;

    public Gerente(String nome, String cpf, Endereco endereco, String email, String senha, String cnpj) {
        super(nome, cpf, endereco);
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
    }

    //getters

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getCnpj() {
        return cnpj;
    }

    //setters

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "Nome: " + this.getNome() + "\nEmail: " + this.getEmail() + "\nCPF: " + this.getCpf() +
                "\nEndereço: " + this.getEndereco() + "\nCNPJ: " + this.cnpj;
    }
}
