package negocio.entidades;

public class Gerente extends Pessoa{
    private String cnpj;

    public Gerente(String nome, String email, String senha, String cpf, Endereco endereco, String cnpj) {
        super(nome, email, senha, cpf, endereco);
        this.cnpj = cnpj;
    }

    //getters

    public String getCnpj() {
        return cnpj;
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
