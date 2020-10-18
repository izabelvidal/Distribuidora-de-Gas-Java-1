package gui.controllers.cadastro;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private void cadastraProdutoBtnHandler(ActionEvent event){
        Pane cadastroProduto;
        try{
            cadastroProduto = FXMLLoader.load(getClass().getResource("../../views/cadastro/TelaCadastroProduto.fxml"));
            painelCadastro.getChildren().setAll(cadastroProduto);
        }catch (IOException ex){
            System.out.println(ex);
        }
    }

    private void cadastrarClienteBtnHandler(ActionEvent event){
        Pane cadastroCliente;
        try{
            cadastroCliente = FXMLLoader.load(getClass().getResource("../../views/cadastro/TelaCadastroCliente.fxml"));
            painelCadastro.getChildren().setAll(cadastroCliente);
        } catch (IOException ex){
            System.out.println(ex);
        }
    }

    private void cadastrarGerenteBtnHandler(ActionEvent event){
        Pane cadastroGerente;
        try{
            cadastroGerente = FXMLLoader.load(getClass().getResource("../../views/cadastro/TelaCadastroGerente.fxml"));
            painelCadastro.getChildren().setAll(cadastroGerente);
        }catch (IOException ex){
            System.out.println(ex);
        }
    }
}
