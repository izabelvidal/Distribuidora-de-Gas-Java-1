package DistribuidoraDeGas.negocio.excecoes;

/**
 * Classe de exceção que é lançada quando o usuário tenta realizar alguma ação com um produto utilizando uma quantidade
 * inválida
 * Foi necessária a criação dessa classe pois uma quantidade inválida pode ser inserida.
 * @author Letícia Araújo
 */
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
