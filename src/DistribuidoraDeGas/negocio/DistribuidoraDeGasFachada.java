package DistribuidoraDeGas.negocio;

import DistribuidoraDeGas.dados.RepositorioCliente;
import DistribuidoraDeGas.dados.RepositorioGerente;
import DistribuidoraDeGas.dados.RepositorioProdutos;
import DistribuidoraDeGas.dados.RepositorioProdutosVendidos;
import DistribuidoraDeGas.negocio.entidades.*;
import DistribuidoraDeGas.negocio.excecoes.*;
import DistribuidoraDeGas.negocio.gerenciadores.*;

import java.util.ArrayList;

/**
 * Classe que se conecta com a interface gráfica
 *
 * @author Letícia Araújo e Izabel Vidal
 */

public class DistribuidoraDeGasFachada {
    private NegocioProduto negocioProduto;
    private NegocioVenda negocioVenda;
    private NegocioGerente negocioGerente;
    private NegocioCliente negocioCliente;
    private NegocioPessoa negocioPessoa;

    public DistribuidoraDeGasFachada(){
        this.negocioProduto = new NegocioProduto(new RepositorioProdutos(), new RepositorioCliente());
        this.negocioVenda = new NegocioVenda(new RepositorioProdutosVendidos());
        this.negocioGerente = new NegocioGerente(new RepositorioGerente());
        this.negocioCliente = new NegocioCliente(new RepositorioCliente());
        this.negocioPessoa = new NegocioPessoa(new RepositorioCliente(), new RepositorioGerente());
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
    //Fim métodos venda


    //gerente
    public void cadastrarGerente(String nome, String cpf, String dataNascimento, String telefone, Endereco endereco, String email, String senha, String cnpj) throws PessoaJaCadastradaException, NomeTamanhoException, NomeApenasLetrasException, CpfApenasNumerosException, CpfTamanhoException, CnpjApenasNumerosException, CnpjTamanhoException, SenhaTamanhoException, SenhaCaracteresInvalidosException {
        Gerente gerente = new Gerente(nome, cpf,dataNascimento,telefone, endereco, email, senha, cnpj);
        this.validarNome(gerente);
        this.validarCpf(gerente);
        this.validarCnpj(gerente);
        this.validarSenha(gerente);
        this.negocioGerente.adicionarGerente(gerente);
    }


    public void atualizarGerente(String cpf, String telefone, Endereco endereco, String rua, int numero, String bairro, String cidade, String estado, String email, String senha) throws PessoaInexistenteException {
        Gerente gerente = (Gerente) negocioGerente.consultarGerente(cpf);
        this.negocioGerente.alterarTelefone(gerente, telefone);
        this.negocioGerente.alterarEmail(gerente, email);
        this.negocioGerente.alterarSenha(gerente, senha);
        this.negocioGerente.atualizarEndereco(endereco, gerente, rua, numero, bairro, cidade, estado);
    }

    //cliente
    public void cadastrarCliente(String nome, String cpf, String dataNascimento, String telefone, Endereco endereco, String tipo) throws PessoaJaCadastradaException, NomeTamanhoException, NomeApenasLetrasException, CpfApenasNumerosException, CpfTamanhoException{
        Cliente cliente = new Cliente(nome, cpf,dataNascimento,telefone, endereco, tipo);
        this.validarNome(cliente);
        this.validarCpf(cliente);
        this.negocioGerente.adicionarGerente(cliente);
    }

    public void atualizarCliente(String cpf, String telefone, Endereco endereco, String rua, int numero, String bairro, String cidade, String estado, String tipo) throws PessoaInexistenteException {
        Cliente cliente = negocioCliente.consultarCliente(cpf);
        this.negocioCliente.alterarTipoCliente(cliente, tipo);
        this.negocioCliente.alterarTelefone(cliente, telefone);
        this.negocioCliente.atualizarEndereco(endereco, cliente, rua, numero, bairro, cidade, estado);
    }

    public Cliente consultarCliente(String cpf) throws PessoaInexistenteException {
        return this.negocioCliente.consultarCliente(cpf);
    }

    //validações
    public void validarCpf(Pessoa pessoa) throws CpfTamanhoException, CpfApenasNumerosException{
        this.negocioPessoa.validarCpf(pessoa);
    }

    public void validarNome(Pessoa pessoa) throws NomeTamanhoException, NomeApenasLetrasException{
        this.negocioPessoa.validarNome(pessoa);
    }

    public void validarCnpj(Gerente gerente) throws CnpjTamanhoException, CnpjApenasNumerosException{
        this.negocioGerente.validarCnpj(gerente);
    }

    public void validarSenha(Gerente gerente) throws SenhaTamanhoException, SenhaCaracteresInvalidosException {
        this.negocioGerente.validarSenha(gerente);
    }
}
