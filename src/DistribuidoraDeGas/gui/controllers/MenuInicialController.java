package DistribuidoraDeGas.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

/**
 * FXML Controller class
 * Essa classe faz a conexão entre a interface gráfica e a fachada.
 * @author Letícia Araújo, Izabel Vidal
 */
public class MenuInicialController implements Initializable {
    @FXML
    private Pane painel;
    /**
     * Initializes the controller class
     */
    public MenuInicialController(){

    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //TODO
    }

    @FXML
    private void vendaBtnHandler(ActionEvent event){
        Pane telaVenda;
        try{
            telaVenda = FXMLLoader.load(getClass().getResource("../views/venda/TelaAgendamentoEntrega.fxml"));
            painel.getChildren().setAll(telaVenda);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void cadastroBtnHandler(ActionEvent event){
        Pane telaCadastro;
        try{
            telaCadastro = FXMLLoader.load(getClass().getResource("../views/cadastro/TelaCadastro.fxml"));
            painel.getChildren().setAll(telaCadastro);
        }catch (IOException ex){
            Logger.getLogger(MenuInicialController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    private void alterarBtnHandler(ActionEvent event){
        Pane telaAlterar;
        try{
            telaAlterar = FXMLLoader.load(getClass().getResource("../views/alterar/TelaAlterar.fxml"));
            painel.getChildren().setAll(telaAlterar);
        }catch (IOException ex){
            Logger.getLogger(MenuInicialController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    private void listarBtnHandler(ActionEvent event){
        Pane telaListar;
        try{
            telaListar = FXMLLoader.load(getClass().getResource("../views/consultas/TelaListar.fxml"));
            painel.getChildren().setAll(telaListar);
        }catch (IOException ex){
            Logger.getLogger(MenuInicialController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    private void sairBtnHandler(ActionEvent event){
        Pane cadastro;
        try{
            cadastro = FXMLLoader.load(getClass().getResource("../../views/cadastro/TelaCadastroGerente.fxml"));
            painel.getChildren().setAll(cadastro);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
