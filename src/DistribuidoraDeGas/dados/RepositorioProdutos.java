package DistribuidoraDeGas.dados;

import DistribuidoraDeGas.negocio.entidades.Produto;
import DistribuidoraDeGas.negocio.excecoes.ProdutoInexistenteException;
import DistribuidoraDeGas.dados.contratos.iRepositorioEstoqueProdutos;

import java.util.ArrayList;

/**
 * Essa classe é responsável por armazenar e gerenciar os Produtos
 *
 * @author Letícia Araújo
 */

public class RepositorioProdutos implements iRepositorioEstoqueProdutos {

   private ArrayList<Produto> produtos;

   public RepositorioProdutos(){
       this.produtos = new ArrayList<>();
   }

    @Override
    public void adicionarProduto(Produto produto){
       this.produtos.add(produto);
    }

    @Override
    public void removerProduto(String id) throws ProdutoInexistenteException {
        try{
            Produto produto = this.getProduto(id);
            this.produtos.remove(produto);
        }catch (ProdutoInexistenteException e){
            throw new ProdutoInexistenteException(id);
        }
    }

    @Override
    public Produto getProduto(String id) throws ProdutoInexistenteException{
        int index;

        for(Produto p: this.produtos){
            if(p.getId().equals(id)){
                index = this.produtos.indexOf(p);
                return this.produtos.get(index);
            }
        }
        throw new ProdutoInexistenteException(id);
    }

    @Override
    public void atualizarProduto(Produto produto) {
        int index = this.produtos.indexOf(produto);
        this.produtos.set(index,produto);
    }

    @Override
    public boolean verificarProduto(String id) {
       for(Produto p: this.produtos){
           if(id.equals(p.getId())){
               return true;
           }
       }
        return false;
    }

    @Override
    public ArrayList<Produto> listarProdutos() {
        return this.produtos;
    }
}
