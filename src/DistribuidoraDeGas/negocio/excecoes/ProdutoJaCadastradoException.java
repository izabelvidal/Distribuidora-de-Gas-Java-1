package DistribuidoraDeGas.negocio.excecoes;

/**
 * Classe de exceção que lança o erro se o produto for cadastrado
 * Foi necessária a criação dessa classe de exceção pois quando é cadastrado um produto,
 * ele pode ter sido cadastrado anteriormente.
 *
 * @author Letícia Araújo
 */

public class ProdutoJaCadastradoException extends Exception{
    private String id;

    public ProdutoJaCadastradoException(String id){
        super("Produto já cadastrado com ID: " + id);
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
}
