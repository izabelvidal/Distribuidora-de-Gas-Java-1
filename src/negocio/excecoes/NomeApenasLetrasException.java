package negocio.excecoes;

public class NomeApenasLetrasException extends Exception {
    String nome;
    public NomeApenasLetrasException(String nome){
        super("Nome deve conter apenas letras!\n" + nome);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
