package dados.contratos;

import negocio.entidades.Venda;
import java.util.ArrayList;

/**
 * Classe de interface para o repositório de produtos vendidos.
 *
 * @author Letícia Araújo
 */

public interface iRepositorioProdutosVendidos {
    void adicionarVenda(Venda produtoVendido);
    ArrayList<Venda> listarProdutosVendidos();
    ArrayList<Venda> consultarVendaPorData(String data);
    ArrayList<Venda> consultarVendaPorStatus(String status);
    void atualizarVenda(Venda produtoVendido);
    ArrayList<Venda> consultarVendaPeloCliente(String cpf);
}
