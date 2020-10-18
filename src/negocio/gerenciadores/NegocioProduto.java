package negocio.gerenciadores;

import dados.RepositorioCliente;
import dados.contratos.*;
import negocio.entidades.Cliente;
import negocio.entidades.Produto;
import negocio.entidades.Venda;
import negocio.excecoes.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class NegocioProduto {
    private iRepositorioEstoqueProdutos repositorioProdutos;
    private iRepositorioPessoa repositorioPessoa;

    public NegocioProduto(iRepositorioEstoqueProdutos repositorioProd, iRepositorioPessoa repositorioPessoa) {
        this.repositorioProdutos = repositorioProd;
        this.repositorioPessoa = repositorioPessoa;
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
        Cliente cliente = this.consultarCliente(cpf);

        if (produto.getQuantidade() < qntd) {
            throw new QuantidadeInvalidaException(qntd);
        } else {
            produto.setQuantidade(produto.getQuantidade() - qntd);
        }

        Venda venda2 = new Venda(qntd, new Produto(produto.getNome(), produto.getMarca(), produto.getId(), produto.getQuantidade(), produto.getPeso(),
                produto.getPreco() * qntd), new Cliente(cliente.getNome(), cliente.getCpf(), cliente.getDataNascimento(), cliente.getTelefone(),
                cliente.getEndereco(), cliente.getTipo()));

        NegocioVenda.registarVenda(venda2);
    }

    public Produto consultarProduto(String id) throws ProdutoInexistenteException {
        return this.repositorioProdutos.getProduto(id);
    }

    public Cliente consultarCliente(String cpf) throws PessoaInexistenteException {
        return (Cliente) this.repositorioPessoa.getPessoa(cpf);
    }

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
}

