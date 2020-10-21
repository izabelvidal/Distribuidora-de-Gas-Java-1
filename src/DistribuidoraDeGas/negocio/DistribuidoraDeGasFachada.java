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
 * Classe fachada. Representa todas as funcionalidades possíveis do sistema
 *
 * @author Letícia Araújo
 */
public class DistribuidoraDeGasFachada {
    private NegocioProduto negocioProduto;
    private NegocioGerente negocioGerente;
    private NegocioCliente negocioCliente;
    private NegocioPessoa negocioPessoa;

    public DistribuidoraDeGasFachada(){
        this.negocioProduto = new NegocioProduto(new RepositorioProdutos(),new RepositorioProdutosVendidos(), new RepositorioCliente(), new RepositorioCliente());
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
        return this.negocioProduto.consultarVendaProdutos();
    }

   public ArrayList<Venda> consultarVendaPorData(String data){
        return this.negocioProduto.consultarVendaPorData(data);
    }

    public ArrayList<Venda> consultarVendaClientePorData(String data, String cpf){
        return this.negocioProduto.consultarVendaClientePorData(cpf,data);
    }

    public void marcarVendaConcluida(Venda v){
        this.negocioProduto.marcarVendaConcluida(v);
    }

    public void alterarVenda(Venda v, String data, String hora){
        this.negocioProduto.alterarVenda(v,data,hora);
    }

   public ArrayList<String> consultarListaHorariosLivres(String data){
        return this.negocioProduto.consultarHorariosDisponiveisPorData(data);
    }

   public ArrayList<Venda> consultarVendasNaoConcluida(String id){
        return this.negocioProduto.consultarVendasNaoConcluida(id);
    }

    public void desmarcarVenda(Venda v){
        this.negocioProduto.removerVenda(v);
    }
    //Fim métodos venda


    //gerente
    public void cadastrarGerente(String nome, String cpf, String dataNascimento, String telefone, Endereco endereco, String email, String senha, String cnpj) throws PessoaJaCadastradaException, NomeTamanhoException, NomeApenasLetrasException, CpfApenasNumerosException, CpfTamanhoException, CnpjApenasNumerosException, CnpjTamanhoException, SenhaTamanhoException, SenhaCaracteresInvalidosException {
        Gerente gerente = new Gerente(nome, cpf,dataNascimento,telefone, endereco, email, senha, cnpj);
        this.validarNome(gerente);
        this.validarCpf(gerente);
        this.validarCnpj(gerente);
        this.negocioGerente.adicionarGerente(gerente);
    }

    public void atualizarTelefoneGerente(Gerente gerente, String telefone){
        this.negocioGerente.alterarTelefone(gerente,telefone);
    }

    public void atualizarRuaGerente(Gerente gerente,Endereco endereco, String rua){
        this.negocioGerente.atualizarRua(rua,gerente,endereco);
    }

    public void atualizarBairroGerente(String bairro, Gerente gerente, Endereco endereco){
        this.negocioGerente.atualizarBairro(bairro,gerente,endereco);
    }

    public void atualizarNumeroGerenete(int numero, Gerente gerente, Endereco endereco){
        this.negocioGerente.atualizarNumero(numero,gerente,endereco);
    }

    public void atualizarCidadeGerente(String cidade, Gerente gerente, Endereco endereco){
        this.negocioGerente.atualizarCidade(cidade,gerente,endereco);
    }

    public void atualizarEstadoGerente(String estado, Gerente gerente, Endereco endereco){
        this.negocioGerente.atualizarEstado(estado,gerente,endereco);
    }

    public void atualizarEmail(String email, Gerente gerente){
        this.negocioGerente.alterarEmail(gerente,email);
    }

    public void atualizarSenha(String senha, Gerente gerente){
        this.negocioGerente.alterarSenha(gerente,senha);
    }

    public Gerente consultarGerente(String cpf) throws PessoaInexistenteException {
        return this.negocioGerente.consultarGerente(cpf);
    }

    //cliente
    public void cadastrarCliente(String nome, String cpf, String dataNascimento, String telefone, Endereco endereco, String tipo) throws PessoaJaCadastradaException, NomeTamanhoException, NomeApenasLetrasException, CpfApenasNumerosException, CpfTamanhoException{
        Cliente cliente = new Cliente(nome, cpf,dataNascimento,telefone, endereco, tipo);
        this.validarNome(cliente);
        this.validarCpf(cliente);
        this.negocioCliente.adicionarCliente(cliente);
    }

    public void atualizarTelefoneCliente(Cliente cliente, String telefone){
        this.negocioCliente.alterarTelefone(cliente,telefone);
    }

    public void atualizarRua(Cliente cliente,Endereco endereco, String rua){
        this.negocioCliente.atualizarRua(rua,cliente,endereco);
    }
    public void atualizarBairro(String bairro, Cliente cliente, Endereco endereco){
        this.negocioCliente.atualizarBairro(bairro,cliente,endereco);
    }
    public void atualizarNumero(int numero, Cliente cliente, Endereco endereco){
        this.negocioCliente.atualizarNumero(numero,cliente,endereco);
    }
    public void atualizarCidade(String cidade, Cliente cliente, Endereco endereco){
        this.negocioCliente.atualizarCidade(cidade,cliente,endereco);
    }
    public void atualizarEstado(String estado, Cliente cliente, Endereco endereco){
        this.negocioCliente.atualizarEstado(estado,cliente,endereco);
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
}
