package DistribuidoraDeGas.negocio.excecoes;

public class NomeTamanhoException extends Exception{
    String nome;
    public NomeTamanhoException(String nome){
        super("Nome deve ter entre 5 e 50 caracteres!\n" + nome);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
