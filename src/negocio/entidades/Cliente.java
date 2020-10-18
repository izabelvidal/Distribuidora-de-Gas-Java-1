package negocio.entidades;

public class Cliente extends Pessoa{
    private String tipo;
    public Cliente(String nome, String cpf, String dataNascimento, String telefone, Endereco endereco, String tipo){
        super(nome, cpf, dataNascimento, telefone, endereco);
        this.tipo = tipo;
    }

    //getters
    public String getTipo() {
        return tipo;
    }

    //setters

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Nome: " + this.getNome() + "\nCPF: " + this.getCpf() +
                "\nEndereço: " + this.getEndereco() + "\nTipo: " + this.tipo;
    }
}
