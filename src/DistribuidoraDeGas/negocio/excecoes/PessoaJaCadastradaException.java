package DistribuidoraDeGas.negocio.excecoes;

public class PessoaJaCadastradaException extends Exception {
    String cpf; 
    public PessoaJaCadastradaException(String cpf){
        super("Pessoa já cadastrada com CPF: " + cpf);
    }

    public String getCpf(){
        return this.cpf;
    }
}
