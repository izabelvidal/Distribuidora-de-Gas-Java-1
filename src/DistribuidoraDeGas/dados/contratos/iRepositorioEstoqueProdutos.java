package DistribuidoraDeGas.dados.contratos;

import DistribuidoraDeGas.negocio.entidades.Produto;
import DistribuidoraDeGas.negocio.excecoes.ProdutoInexistenteException;

import java.util.ArrayList;

/**
 * Classe de interface para o repositório de produtos
 *
 * @author Letícia Araújo
 */

public interface iRepositorioEstoqueProdutos {
    void adicionarProduto(Produto produto);
    void removerProduto(String id) throws ProdutoInexistenteException;
    Produto getProduto(String id) throws ProdutoInexistenteException;
    void atualizarProduto(Produto produto);
    boolean verificarProduto(String id);
    ArrayList<Produto> listarProdutos();
}
