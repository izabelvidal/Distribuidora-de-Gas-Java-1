package repositorios;

import entidades.Cliente;
import entidades.Produto;
import entidades.Venda;
import excecoes.PessoaInexistenteException;
import excecoes.ProdutoInexistenteException;
import excecoes.QuantidadeInvalidaException;

import java.util.ArrayList;

/**
 * Essa classe é responsável por armazenar e gerenciar os Produtos vendidos
 *
 * @author Letícia Araújo
 */

public class RepositorioProdutosVendidos implements iRepositorioProdutosVendidos{

    private ArrayList<Venda> produtosVendidos;
    private RepositorioProdutos repositorioProdutos;
    private RepositorioCliente repositorioCliente;

    public RepositorioProdutosVendidos(RepositorioProdutos repositorioProdutos, RepositorioCliente repositorioCliente){
        this.produtosVendidos = new ArrayList<>();
        this.repositorioProdutos = repositorioProdutos;
        this.repositorioCliente = repositorioCliente;
    }

    @Override
    public void adicionarVenda(Venda produtoVendido) {
        this.produtosVendidos.add(produtoVendido);
    }

    public void decrementarQntd(String id, int qntd, String cpf) throws QuantidadeInvalidaException, ProdutoInexistenteException, PessoaInexistenteException {
        Produto produto = this.consultarProduto(id);
        Cliente cliente = (Cliente) this.repositorioCliente.getPessoa(cpf);

        if(produto.getQuantidade() < qntd){
            throw new QuantidadeInvalidaException(qntd);
        }else{
            produto.setQuantidade(produto.getQuantidade() - qntd);

            Venda venda = new Venda(qntd, new Produto(produto.getNome(),produto.getMarca(),produto.getId(),produto.getQuantidade(), produto.getPeso(), produto.getPreco() * qntd), new Cliente(cliente.getNome(), cliente.getEmail(), ));
        }
    }

    public Produto consultarProduto(String id) throws ProdutoInexistenteException{
        return this.repositorioProdutos.getProduto(id);
    }
    @Override
    public ArrayList<Venda> listarProdutosVendidos() {
        return this.produtosVendidos;
    }

    @Override
    public ArrayList<Venda> consultarVendaPorData(String data) {
        ArrayList<Venda> produtosV = new ArrayList<>();

        for(Venda v: this.produtosVendidos){
            if(v.getData().equals(data)){
                produtosV.add(v);
            }
        }
        return produtosV;
    }

    @Override
    public ArrayList<Venda> consultarVendaPorStatus(String status) {
        ArrayList<Venda> produtosV = new ArrayList<>();

        for(Venda v: this.produtosVendidos){
            if(v.getStatus().equals(status)){
                produtosV.add(v);
            }
        }
        return produtosV;
    }

    @Override
    public void atualizarVenda(Venda produtoVendido) {
        int index = this.produtosVendidos.indexOf(produtoVendido);
        this.produtosVendidos.set(index,produtoVendido);
    }

    @Override
    public ArrayList<Venda> consultarVendaPeloCliente(String cpf) {
        ArrayList<Venda> vendaDoCliente = new ArrayList<>();

        for(Venda v: this.produtosVendidos){
            if(v.getCliente().getCpf().equals(cpf)){
                vendaDoCliente.add(v);
            }
        }
        return vendaDoCliente;
    }
}
