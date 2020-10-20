package DistribuidoraDeGas.negocio.excecoes;

/**
 * Classe de exceção que é lançada quando não é encontrado produto com esse id
 * Foi necessária a criação dessa classe de exceção pois quando é feita a busca do id de um produto,
 * ele pode vir a não existir.
 *
 * @author Letícia Araújo
 */
public class ProdutoInexistenteException extends Exception{
    private String id;

    public ProdutoInexistenteException(String id){
        super("Nenhum produto cadastrado no sistema com ID: " + id);
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
}
