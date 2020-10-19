package DistribuidoraDeGas.dados;

import DistribuidoraDeGas.negocio.entidades.Venda;
import DistribuidoraDeGas.dados.contratos.iRepositorioProdutosVendidos;

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
    public ArrayList<Venda> consultarVendaNaoConcluida(String data) {
        ArrayList<Venda> vendaNaoConcluida = new ArrayList<>();

        for(Venda v: this.produtosVendidos){
            if(!v.getStatus().equals("Concluída") && v.getData().equals(data)){
                vendaNaoConcluida.add(v);
            }
        }
        return vendaNaoConcluida;
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

    @Override
    public ArrayList<Venda> consultarVendas() {
        return this.produtosVendidos;
    }
}
