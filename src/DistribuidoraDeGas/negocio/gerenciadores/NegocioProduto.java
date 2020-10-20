package DistribuidoraDeGas.negocio.gerenciadores;

import DistribuidoraDeGas.dados.RepositorioProdutosVendidos;
import DistribuidoraDeGas.dados.contratos.*;
import DistribuidoraDeGas.negocio.entidades.Cliente;
import DistribuidoraDeGas.negocio.entidades.Produto;
import DistribuidoraDeGas.negocio.entidades.Venda;
import DistribuidoraDeGas.negocio.excecoes.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Essa classe aplica as regras de negócio e o gerenciamento do Produto no sistema
 *
 * @author Letícia Araújo
 */
public class NegocioProduto {
    private iRepositorioEstoqueProdutos repositorioProdutos;
    private iRepositorioPessoa repositorioPessoa;
    private RepositorioProdutosVendidos repositorioProdutosVendidos;

    public NegocioProduto(iRepositorioEstoqueProdutos repositorioProd, RepositorioProdutosVendidos repositorioProdutosVendidos, iRepositorioPessoa repositorioPessoa) {
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

    public void decrementarQntd(String id, int qntd, String cpf) throws QuantidadeInvalidaException, ProdutoInexistenteException, PessoaInexistenteException/*, PessoaInexistenteException*/ {
        Produto produto = this.consultarProduto(id);
        Cliente cliente = (Cliente) repositorioPessoa.getPessoa(cpf);

        if (produto.getQuantidade() < qntd) {
            throw new QuantidadeInvalidaException(qntd);
        } else {
            produto.setQuantidade(produto.getQuantidade() - qntd);
        }

        Venda venda = new Venda(qntd, new Produto(produto.getNome(), produto.getMarca(), produto.getId(), produto.getQuantidade(), produto.getPeso(),
                produto.getPreco() * qntd), new Cliente(cliente.getNome(), cliente.getCpf(), cliente.getDataNascimento(), cliente.getTelefone(),
                cliente.getEndereco(), cliente.getTipo()));

        this.registarVenda(venda);
    }

    public void registarVenda(Venda venda){
        this.repositorioProdutosVendidos.adicionarVenda(venda);
    }

    public Produto consultarProduto(String id) throws ProdutoInexistenteException {
        return this.repositorioProdutos.getProduto(id);
    }

    /*public Cliente consultarCliente(String cpf) throws PessoaInexistenteException {
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

    public void marcarVendaConcluida(Venda v){
        v.concluirStatus();
        this.repositorioProdutosVendidos.atualizarVenda(v);
    }

    public ArrayList<Venda> consultarVendaPorData(String data){
        return this.repositorioProdutosVendidos.consultarVendaPorData(data);
    }

    public ArrayList<Venda> consultarVendaProdutos(){
        return this.repositorioProdutosVendidos.consultarVendas();
    }

    public void alterarVenda(Venda venda, String data, String hora){
        venda.setData(data);
        venda.setHora(hora);
        this.repositorioProdutosVendidos.atualizarVenda(venda);
    }

    public ArrayList<String> consultarHorariosDisponiveisPorData(String data){
        SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfDataComp = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        GregorianCalendar gc = new GregorianCalendar();

        gc.setTime(new Date());
        gc.add(Calendar.HOUR,-1);
        String dataAtual = sdfData.format(gc.getTime());

        ArrayList<String> listaHoras = new ArrayList<String>(Arrays.asList("08:00", "09:00", "10:00", "11:00", "12:00"));

        GregorianCalendar horaTemp = new GregorianCalendar();
        ArrayList<String> horasRemovidasTemp = new ArrayList<>();

        if(data.equals(dataAtual)){
            for(String hora: listaHoras){
                try{
                    horaTemp.setTime(sdfDataComp.parse(dataAtual + " " + hora));
                    if(horaTemp.getTime().before(gc.getTime())){
                        horasRemovidasTemp.add(hora);
                    }
                }catch (ParseException e){
                    e.printStackTrace();
                }
            }for(String hora: horasRemovidasTemp){
                listaHoras.remove(hora);
            }
        }
        return listaHoras;
    }
}

