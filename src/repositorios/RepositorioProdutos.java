package repositorios;

import entidades.Produto;
import repositorios.iRepositorioEstoqueProdutos;

import java.util.ArrayList;

public class RepositorioProdutos implements iRepositorioEstoqueProdutos {

   private ArrayList<Produto> produtos;

   public RepositorioProdutos(){
       this.produtos = new ArrayList<>();
   }

    @Override
    public void adicionarProduto(Produto produto) {
       this.produtos.add(produto);
    }

    @Override
    public void removerProduto(String id) {

    }

    @Override
    public Produto getProduto(String id) {
        return null;
    }

    @Override
    public void atualizarProduto(Produto produto) {

    }

    @Override
    public boolean verificarProduto(String id) {
        return false;
    }

    @Override
    public ArrayList<Produto> listarProdutos() {
        return null;
    }
}
