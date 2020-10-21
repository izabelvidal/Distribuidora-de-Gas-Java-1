package DistribuidoraDeGas.gui.controllers.alterar;

import DistribuidoraDeGas.gui.controllers.MenuInicialController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 * Essa classe faz a conexão entre a interface gráfica e a fachada.
 * @author Letícia Araújo, Izabel Vidal
 */
public class TelaAlterarController implements Initializable {

    @FXML
    private Pane painelAlterar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
    }

    @FXML
    public void alterarClienteBtnHandler(ActionEvent event) {
        Pane alterarCliente;
        try {
            alterarCliente = FXMLLoader.load(getClass().getResource("../../views/alterar/TelaAlterarCliente.fxml"));
            painelAlterar.getChildren().setAll(alterarCliente);
        }catch (IOException ex){
            System.out.println(ex);
        }
    }

    @FXML
    public void alterarProdutoBtnHandler(ActionEvent event) {
        Pane alterarProduto;
        try {
            alterarProduto = FXMLLoader.load(getClass().getResource("../../views/alterar/TelaAlterarProduto.fxml"));
            painelAlterar.getChildren().setAll(alterarProduto);
        }catch (IOException ex){
            Logger.getLogger(MenuInicialController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    public void alterarGerenteBtnHandler(ActionEvent event) {
        Pane alterarGerente;
        try {
            alterarGerente = FXMLLoader.load(getClass().getResource("../../views/alterar/TelaAlterarGerente.fxml"));
            painelAlterar.getChildren().setAll(alterarGerente);
        }catch (IOException ex){
            Logger.getLogger(MenuInicialController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    public void voltarBtnHandler(ActionEvent event) {
        Pane menuInicial;
        try {
            menuInicial = FXMLLoader.load(getClass().getResource("../../views/MenuInicial.fxml"));
            painelAlterar.getChildren().setAll(menuInicial);
        }catch (IOException ex){
            Logger.getLogger(MenuInicialController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    public void alterarVendaBtnHandler(ActionEvent event) {
        Pane alterarVenda;
        try {
            alterarVenda = FXMLLoader.load(getClass().getResource("../../views/alterar/TelaAlterarVenda.fxml"));
            painelAlterar.getChildren().setAll(alterarVenda);
        }catch (IOException ex){
            Logger.getLogger(MenuInicialController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
