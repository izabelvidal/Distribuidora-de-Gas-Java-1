package DistribuidoraDeGas.gui.controllers.consultas;

import DistribuidoraDeGas.gui.controllers.MenuInicialController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaListarController implements Initializable {
    @FXML
    private Pane painelListar;
    @FXML
    private Button btnListarEstoqueProdutos;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnListaVendaProdutos;
    @FXML
    private Button btnListaClientes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
    }

    @FXML
    public void listarEstoqueProdutosBtnHandler(ActionEvent event) {
        Pane listar;
        try {
            listar = FXMLLoader.load(getClass().getResource("../../views/consultas/TelaListarEstoqueProdutos.fxml"));
            painelListar.getChildren().setAll(listar);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void voltarBtnHandler(ActionEvent event) {
        Pane menuInicial;
        try {
            menuInicial = FXMLLoader.load(getClass().getResource("../../views/MenuInicial.fxml"));
            painelListar.getChildren().setAll(menuInicial);
        }catch (IOException ex){
            Logger.getLogger(MenuInicialController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    public void listaVendaProdutosBtnHandler(ActionEvent event) {
        Pane historicoDeVendas;
        try{
            historicoDeVendas = FXMLLoader.load(getClass().getResource("../../views/consultas/TelaListarVendaDeProdutos.fxml"));
            painelListar.getChildren().setAll(historicoDeVendas);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void listaClientesBtnHandler(ActionEvent event) {
        Pane historicoDeVendas;
        try{
            historicoDeVendas = FXMLLoader.load(getClass().getResource("../../views/consultas/TelaListarCliente.fxml"));
            painelListar.getChildren().setAll(historicoDeVendas);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
