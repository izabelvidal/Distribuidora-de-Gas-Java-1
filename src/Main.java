import negocio.DistribuidoraDeGasFachada;
import negocio.excecoes.PessoaInexistenteException;
import negocio.excecoes.ProdutoInexistenteException;
import negocio.excecoes.ProdutoJaCadastradoException;
import negocio.excecoes.QuantidadeInvalidaException;

public class Main {
    public static void main(String[] args) throws ProdutoJaCadastradoException, QuantidadeInvalidaException, PessoaInexistenteException, ProdutoInexistenteException {
        DistribuidoraDeGasFachada fachada = new DistribuidoraDeGasFachada();

        fachada.cadastrarProduto("gás", "fugaz", "1", 32.2, 54, 20);
        fachada.venderProduto("1", 21, "70356714470");

    }
}
