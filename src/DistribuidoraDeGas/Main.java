package DistribuidoraDeGas;

import DistribuidoraDeGas.negocio.entidades.Endereco;
import DistribuidoraDeGas.negocio.entidades.Produto;
import DistribuidoraDeGas.negocio.excecoes.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import DistribuidoraDeGas.negocio.DistribuidoraDeGasFachada;

public class Main extends Application {
    public static DistribuidoraDeGasFachada distribudora;
    public static Stage stageMain;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui/views/MenuInicial.fxml"));
        stageMain = stage;
        Scene scene = new Scene(root);

        stageMain.setScene(scene);
        stageMain.setResizable(false);
        stageMain.show();
    }

    public static void main(String[] args) throws ProdutoJaCadastradoException, NomeApenasLetrasException, PessoaJaCadastradaException, CpfTamanhoException, CpfApenasNumerosException, NomeTamanhoException, QuantidadeInvalidaException, PessoaInexistenteException, ProdutoInexistenteException {
        distribudora = new DistribuidoraDeGasFachada();
        //launch(args);
        distribudora.cadastrarProduto("pirulito", "agsgasgag", "01", 32.2, 21, 1);
        Endereco endereco = new Endereco("akjsjksjkd", 20, "hdhashuuhas", "djkdjkdsjdsjk", "mdsmkdmdm");
        distribudora.cadastrarCliente("aohushudshu", "12345678912", "21/10/2001", "52142214452", endereco, "comum");
        distribudora.venderProduto("01", 1, "12345678912");

    }
}
