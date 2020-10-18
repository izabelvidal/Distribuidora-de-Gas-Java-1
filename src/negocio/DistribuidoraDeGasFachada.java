package negocio;

import dados.RepositorioCliente;
import dados.RepositorioProdutos;
import dados.RepositorioProdutosVendidos;
import negocio.entidades.Produto;
import negocio.entidades.Venda;
import negocio.excecoes.PessoaInexistenteException;
import negocio.excecoes.ProdutoInexistenteException;
import negocio.excecoes.ProdutoJaCadastradoException;
import negocio.excecoes.QuantidadeInvalidaException;
import negocio.gerenciadores.NegocioProduto;
import negocio.gerenciadores.NegocioVenda;

import java.util.ArrayList;

public class DistribuidoraDeGasFachada {
    private NegocioProduto negocioProduto;
    private NegocioVenda negocioVenda;

    public DistribuidoraDeGasFachada(){
        this.negocioProduto = new NegocioProduto(new RepositorioProdutos(), new RepositorioCliente());
        this.negocioVenda = new NegocioVenda(new RepositorioProdutosVendidos());
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
}
