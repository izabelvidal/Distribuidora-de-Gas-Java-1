package repositorios;

import entidades.Produto;
import excecoes.ProdutoInexistenteException;

import java.util.ArrayList;

public interface iRepositorioEstoqueProdutos {
    void adicionarProduto(Produto produto);
    void removerProduto(String id) throws ProdutoInexistenteException;
    Produto getProduto(String id) throws ProdutoInexistenteException;
    void atualizarProduto(Produto produto);
    boolean verificarProduto(String id);
    ArrayList<Produto> listarProdutos();
}
