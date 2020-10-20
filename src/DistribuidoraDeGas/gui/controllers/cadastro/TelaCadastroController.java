package DistribuidoraDeGas.gui.controllers.cadastro;

import DistribuidoraDeGas.gui.controllers.MenuInicialController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;
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
public class TelaCadastroController implements Initializable {
    @FXML
    private Pane painelCadastro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
    }

    @FXML
    public void cadastraGerenteBtnHandler(javafx.event.ActionEvent event) {
        Pane cadastroGerente;
        try{
            cadastroGerente = FXMLLoader.load(getClass().getResource("../../views/cadastro/TelaCadastroGerente.fxml"));
            painelCadastro.getChildren().setAll(cadastroGerente);
        }catch (IOException ex){
            System.out.println(ex);
        }
    }

    @FXML
    public void cadastraProdutoBtnHandler(javafx.event.ActionEvent event) {
        Pane cadastroProduto;
        try{
            cadastroProduto = FXMLLoader.load(getClass().getResource("../../views/cadastro/TelaCadastroProduto.fxml"));
            painelCadastro.getChildren().setAll(cadastroProduto);
        }catch (IOException ex){
            System.out.println(ex);
        }
    }

    @FXML
    public void voltarBtnHandler(javafx.event.ActionEvent event) {
        Pane menuInicial;
        try {
            menuInicial = FXMLLoader.load(getClass().getResource("../../views/MenuInicial.fxml"));
            painelCadastro.getChildren().setAll(menuInicial);

        } catch (IOException ex) {
            Logger.getLogger(MenuInicialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void cadastrarClienteBtnHandler(javafx.event.ActionEvent event) {
        Pane cadastroCliente;
        try{
            cadastroCliente = FXMLLoader.load(getClass().getResource("../../views/cadastro/TelaCadastroCliente.fxml"));
            painelCadastro.getChildren().setAll(cadastroCliente);
        } catch (IOException ex){
            System.out.println(ex);
        }
    }
}
