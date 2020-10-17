package excecoes;

public class QuantidadeInvalidaException extends Exception{
    private int quantidade;

    public QuantidadeInvalidaException(int quantidade){
        super("Quantidade " + quantidade + " inválida");
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return this.quantidade;
    }
}
