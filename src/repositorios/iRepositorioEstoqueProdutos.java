package repositorios;

import entidades.Produto;

import java.util.ArrayList;

public interface iRepositorioEstoqueProdutos {
    void adicionarProduto(Produto produto);
    void removerProduto(String id);
    Produto getProduto(String id);
    void atualizarProduto(Produto produto);
    boolean verificarProduto(String id);
    ArrayList<Produto> listarProdutos();
}
