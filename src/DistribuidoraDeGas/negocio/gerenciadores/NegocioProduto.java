package DistribuidoraDeGas.negocio.gerenciadores;

import DistribuidoraDeGas.dados.RepositorioProdutosVendidos;
import DistribuidoraDeGas.dados.contratos.*;
import DistribuidoraDeGas.negocio.entidades.Cliente;
import DistribuidoraDeGas.negocio.entidades.Produto;
import DistribuidoraDeGas.negocio.entidades.Venda;
import DistribuidoraDeGas.negocio.excecoes.*;

import java.util.*;

public class NegocioProduto {
    private iRepositorioEstoqueProdutos repositorioProdutos;
    private iRepositorioPessoa repositorioPessoa;
    private RepositorioProdutosVendidos repositorioProdutosVendidos;

    public NegocioProduto(iRepositorioEstoqueProdutos repositorioProd,RepositorioProdutosVendidos repositorioProdutosVendidos, iRepositorioPessoa repositorioPessoa) {
        this.repositorioProdutos = repositorioProd;
        this.repositorioPessoa = repositorioPessoa;
        this.repositorioProdutosVendidos = repositorioProdutosVendidos;
    }

    public void adicionarProduto(Produto produto) throws ProdutoJaCadastradoException {
        boolean existe = this.repositorioProdutos.verificarProduto(produto.getId());

        if (existe) {
            throw new ProdutoJaCadastradoException(produto.getId());
        } else {
            this.repositorioProdutos.adicionarProduto(produto);
        }
    }

    public void removerProduto(String id) throws ProdutoInexistenteException {
        this.repositorioProdutos.removerProduto(id);
    }

    public void decrementarQntd(String id, int qntd, String cpf) throws QuantidadeInvalidaException, ProdutoInexistenteException, PessoaInexistenteException {
        Produto produto = this.consultarProduto(id);
        Cliente cliente = (Cliente) repositorioPessoa.getPessoa(cpf);

        if (produto.getQuantidade() < qntd) {
            throw new QuantidadeInvalidaException(qntd);
        } else {
            produto.setQuantidade(produto.getQuantidade() - qntd);
        }

        Venda venda2 = new Venda(qntd, new Produto(produto.getNome(), produto.getMarca(), produto.getId(), produto.getQuantidade(), produto.getPeso(),
                produto.getPreco() * qntd), new Cliente(cliente.getNome(), cliente.getCpf(), cliente.getDataNascimento(), cliente.getTelefone(),
                cliente.getEndereco(), cliente.getTipo()));

        registarVenda(venda2);
    }

    private void registarVenda(Venda venda){
        this.repositorioProdutosVendidos.adicionarVenda(venda);
    }

    public Produto consultarProduto(String id) throws ProdutoInexistenteException {
        return this.repositorioProdutos.getProduto(id);
    }

   /* public Cliente consultarCliente(String cpf) throws PessoaInexistenteException {
        return (Cliente) this.repositorioPessoa.getPessoa(cpf);
    }*/

    public void alterarPreco(Produto produto, double preco) throws ProdutoInexistenteException {
        boolean existe = this.repositorioProdutos.verificarProduto(produto.getId());

        if (existe) {
            produto.setPreco(preco);
            this.repositorioProdutos.atualizarProduto(produto);
        } else {
            throw new ProdutoInexistenteException(produto.getId());
        }
    }

    public void alterarQuantidade(Produto produto, int qntd) throws ProdutoInexistenteException, QuantidadeInvalidaException {
        boolean existe = this.repositorioProdutos.verificarProduto(produto.getId());

        if (existe) {
            if (qntd > 0) {
                produto.setQuantidade(qntd);
                this.repositorioProdutos.atualizarProduto(produto);
            } else {
                throw new QuantidadeInvalidaException(qntd);
            }
        } else {
            throw new ProdutoInexistenteException(produto.getId());
        }
    }

    public ArrayList<Produto> consultarProdutosEstoque() {
        return this.repositorioProdutos.listarProdutos();
    }

   /* public ArrayList<Venda> consultarVendaProdutosPorData(String data){
        return this.repositorioProdutosVendidos.consultarVendasPorData(data);
    }*/

    public ArrayList<Venda> consultarVendaProdutos() {
        return this.repositorioProdutosVendidos.consultarVendas();
    }
}


