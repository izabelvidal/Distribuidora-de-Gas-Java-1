package DistribuidoraDeGas;

import DistribuidoraDeGas.dados.RepositorioCliente;
import DistribuidoraDeGas.negocio.entidades.Cliente;
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
        Parent root = FXMLLoader.load(getClass().getResource("gui/views/cadastro/TelaCadastroGerente.fxml"));
        stageMain = stage;
        Scene scene = new Scene(root);

        stageMain.setScene(scene);
        stageMain.setResizable(false);
        stageMain.show();
    }

    public static void main(String[] args) {
        distribudora = new DistribuidoraDeGasFachada();
        launch(args);
    }
}
