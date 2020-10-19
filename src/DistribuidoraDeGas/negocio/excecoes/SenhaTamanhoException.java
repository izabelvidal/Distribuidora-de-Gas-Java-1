package DistribuidoraDeGas.negocio.excecoes;

public class SenhaTamanhoException extends Exception {
    String senha;
    public SenhaTamanhoException(String senha){
        super("A senha deve conter ao menos 8 caracteres!");
    }

    public String getSenha() {
        return senha;
    }
}
