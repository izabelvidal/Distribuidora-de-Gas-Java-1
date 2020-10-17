package repositorios;

import entidades.Venda;

import java.util.ArrayList;

public class RepositorioProdutosVendidos implements iRepositorioProdutosVendidos{

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
