package dados;

import negocio.entidades.Cliente;
import negocio.entidades.Produto;
import negocio.entidades.Venda;
import negocio.excecoes.PessoaInexistenteException;
import negocio.excecoes.ProdutoInexistenteException;
import negocio.excecoes.QuantidadeInvalidaException;
import dados.contratos.iRepositorioProdutosVendidos;

import java.util.ArrayList;

/**
 * Essa classe é responsável por armazenar e gerenciar os Produtos vendidos
 *
 * @author Letícia Araújo
 */

public class RepositorioProdutosVendidos implements iRepositorioProdutosVendidos {

    private ArrayList<Venda> produtosVendidos;

    public RepositorioProdutosVendidos(){
        this.produtosVendidos = new ArrayList<>();
    }

    @Override
    public void adicionarVenda(Venda produtoVendido) {
        this.produtosVendidos.add(produtoVendido);
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
