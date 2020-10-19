package DistribuidoraDeGas.dados.contratos;

import DistribuidoraDeGas.negocio.entidades.Venda;
import java.util.ArrayList;

/**
 * Classe de interface para o repositório de produtos vendidos.
 *
 * @author Letícia Araújo
 */

public interface iRepositorioProdutosVendidos {
    void adicionarVenda(Venda produtoVendido);
    ArrayList<Venda> consultarVendaPorData(String data);
    ArrayList<Venda> consultarVendaNaoConcluida(String data);
    void atualizarVenda(Venda produtoVendido);
    ArrayList<Venda> consultarVendaPeloCliente(String cpf);
    ArrayList<Venda> consultarVendas();
}
