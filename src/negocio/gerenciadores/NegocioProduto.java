package negocio.gerenciadores;

import dados.RepositorioCliente;
import dados.contratos.*;
import negocio.entidades.Cliente;
import negocio.entidades.Produto;
import negocio.entidades.Venda;
import negocio.excecoes.*;

public class NegocioProduto {
    private iRepositorioEstoqueProdutos repositorioProdutos;
    private iRepositorioProdutosVendidos repositorioProdutosVendidos;
    private RepositorioCliente repositorioPessoa;

    public NegocioProduto(iRepositorioProdutosVendidos repositorioProdVendidos, iRepositorioEstoqueProdutos repositorioProd, RepositorioCliente repositorioPessoa){
        this.repositorioProdutos = repositorioProd;
        this.repositorioProdutosVendidos = repositorioProdVendidos;
        this.repositorioPessoa = repositorioPessoa;
    }

    public void adicionarProduto(Produto produto) throws ProdutoJaCadastradoException{
        boolean existe = this.repositorioProdutos.verificarProduto(produto.getId());

        if(existe){
            throw new ProdutoJaCadastradoException(produto.getId());
        }else{
            this.repositorioProdutos.adicionarProduto(produto);
        }
    }

    public void removerProduto(String id) throws ProdutoInexistenteException{
        this.repositorioProdutos.removerProduto(id);
    }

   /* public void decrementarQntd(String id, int qntd, String cpf, String status) throws QuantidadeInvalidaException, ProdutoInexistenteException, PessoaInexistenteException {
        Produto produto = this.consultarProduto(id);
        Cliente cliente = this.consultarVendaCliente(cpf);
        Venda venda = this.consultarVenda(status);

        if(produto.getQuantidade() < qntd){
            throw new QuantidadeInvalidaException(qntd);
        }else{
            produto.setQuantidade(produto.getQuantidade() - qntd);
        }

        Venda venda2 = new Venda(qntd, new Produto(produto.getNome(), produto.getMarca(), produto.getId(), produto.getQuantidade(), produto.getPeso(),
                produto.getPreco() * qntd), new Cliente(cliente.getNome(), cliente.getEmail(), cliente.getSenha(), cliente.getCpf(),
                cliente.getEndereco(), cliente.getTipo()), venda.getStatus());

        this.registarVenda(venda2);
    }*/

    private void registarVenda(Venda venda){
        this.repositorioProdutosVendidos.adicionarVenda(venda);
    }

    public Produto consultarProduto(String id) throws ProdutoInexistenteException{
        return this.repositorioProdutos.getProduto(id);
    }

   /* public Cliente consultarVendaCliente(String cpf) throws PessoaInexistenteException {
        return (Cliente) this.repositorioPessoa.getPessoa(cpf);
    }

    public Venda consultarVenda(String status){
        return this.repositorioProdutosVendidos.consultarVendaPorStatus(status);
    }*/
}