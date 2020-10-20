package DistribuidoraDeGas.negocio.excecoes;

public class PessoaInexistenteException extends Exception {
    private String cpf;

    public PessoaInexistenteException(String cpf){
        super("Nenhuma pessoa cadastrada com o CPF: " + cpf);
        this.cpf = cpf;
    }

    public String getCpf(){
        return this.cpf;
    }
}
