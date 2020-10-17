package repositorios;

import entidades.Venda;

import java.util.ArrayList;

public interface iRepositorioProdutosVendidos {
    void adicionarVenda(Venda produtoVendido);
    ArrayList<Venda> listarProdutosVendidos();
    ArrayList<Venda> consultarVendaPorData(String data);
    ArrayList<Venda> consultarVendaPorStatus(String status);
    void atualizarVenda(Venda produtoVendido);
    ArrayList<Venda> consultarVendaPeloCliente(String cpf);
}
