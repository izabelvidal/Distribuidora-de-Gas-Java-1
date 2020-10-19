package DistribuidoraDeGas;

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

    public static void main(String[] args){
        distribudora = new DistribuidoraDeGasFachada();
        launch(args);
    }
}
