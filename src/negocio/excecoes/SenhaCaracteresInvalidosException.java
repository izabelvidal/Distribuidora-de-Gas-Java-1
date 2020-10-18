package negocio.excecoes;

public class SenhaCaracteresInvalidosException extends Exception {
    String senha;
    public SenhaCaracteresInvalidosException(String senha){
        super("A senha deve contar caracteres [a-zA-Z_0-9]!\n");
    }

    public String getSenha() {
        return senha;
    }
}
