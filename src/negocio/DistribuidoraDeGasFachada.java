package negocio;

import dados.RepositorioCliente;
import dados.RepositorioGerente;
import dados.RepositorioProdutos;
import dados.RepositorioProdutosVendidos;
import negocio.entidades.*;
import negocio.excecoes.PessoaInexistenteException;
import negocio.excecoes.ProdutoInexistenteException;
import negocio.excecoes.ProdutoJaCadastradoException;
import negocio.excecoes.QuantidadeInvalidaException;
import negocio.gerenciadores.*;

import java.util.ArrayList;

public class DistribuidoraDeGasFachada {
    private NegocioProduto negocioProduto;
    private NegocioVenda negocioVenda;
    private NegocioGerente negocioGerente;
    private NegocioCliente negocioCliente;

    public DistribuidoraDeGasFachada(){
        this.negocioProduto = new NegocioProduto(new RepositorioProdutos(), new RepositorioCliente());
        this.negocioVenda = new NegocioVenda(new RepositorioProdutosVendidos());
        this.negocioGerente = new NegocioGerente(new RepositorioGerente());
        this.negocioCliente = new NegocioCliente(new RepositorioCliente());
    }

    //iniciar métodos Produto
    public void cadastrarProduto(String nome, String marca, String id, double peso, double preco, int quantidade) throws ProdutoJaCadastradoException {
        Produto produto = new Produto(nome,marca,id,quantidade,peso,preco);
        this.negocioProduto.adicionarProduto(produto);
    }

    public void venderProduto(String id, int qntd, String cpf) throws QuantidadeInvalidaException, ProdutoInexistenteException, PessoaInexistenteException {
        this.negocioProduto.decrementarQntd(id,qntd,cpf);
    }

    public Produto consultarProduto(String id) throws ProdutoInexistenteException{
        return this.negocioProduto.consultarProduto(id);
    }

    public void removerProduto(String id) throws ProdutoInexistenteException{
        this.negocioProduto.removerProduto(id);
    }

    public void atualizarProduto(String id, int qntd, double preco) throws ProdutoInexistenteException,QuantidadeInvalidaException{
        Produto produto = negocioProduto.consultarProduto(id);
        negocioProduto.alterarPreco(produto, preco);
        negocioProduto.alterarQuantidade(produto, qntd);
    }

    public ArrayList<Produto> consultarProdutosEstoque(){
        return this.negocioProduto.consultarProdutosEstoque();
    }
    //Fim métodos Produto

    //Inicio métodos Venda
    public ArrayList<Venda> consultarVendaProdutos(){
        return this.negocioVenda.consultarVendaProdutos();
    }

    public ArrayList<Venda> consultarVendaPorData(String data){
        return this.negocioVenda.consultarVendaPorData(data);
    }

    public ArrayList<Venda> consultarVendaPeloCliente(String cpf){
        return this.negocioVenda.consultarVendaCliente(cpf);
    }

    public ArrayList<Venda> consultarVendaClientePorData(String data, String cpf){
        return this.negocioVenda.consultarVendaClientePorData(cpf,data);
    }

    public void marcarVendaConcluida(Venda v){
        this.negocioVenda.marcarVendaConcluida(v);
    }

    public void alterarVenda(Venda v, String data, String hora){
        this.negocioVenda.alterarVenda(v,data,hora);
    }

    public ArrayList<String> consultarListaHorariosLivres(String data){
        return this.negocioVenda.consultarHorariosDisponiveisPorData(data);
    }

    public ArrayList<Venda> consultarVendasClienteNaoConcluida(String cpf){
        return this.negocioVenda.consultarVendasCLienteNaoConcluida(cpf);
    }

    //endereco
    public void cadastrarEnderecoCliente(Endereco endereco, Cliente cliente, String rua, int numero, String bairro, String cidade, String estado){
        this.negocioCliente.atualizarEndereco(endereco, cliente, rua, numero, bairro, cidade, estado);
    }

    public void cadastrarEnderecoGerente(Endereco endereco, Gerente gerente, String rua, int numero, String bairro, String cidade, String estado){
        this.negocioGerente.atualizarEndereco(endereco, gerente, rua, numero, bairro, cidade, estado);
    }
    //gerente
    public void cadastrarGerente throws PessoaJaCadastradaException(String nome, String cpf, String dataNascimento, Endereco endereco, String email, String senha, String cnpj){
        Gerente gerente = new Gerente(nome, cpf,dataNascimento, endereco,email, senha, cnpj);
        this.negocioGerente.adicionarGerente(gerente);
    }
}
