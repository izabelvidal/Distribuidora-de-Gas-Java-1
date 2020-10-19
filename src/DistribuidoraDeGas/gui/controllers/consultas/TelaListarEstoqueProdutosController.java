package DistribuidoraDeGas.gui.controllers.consultas;

import DistribuidoraDeGas.Main;
import DistribuidoraDeGas.negocio.entidades.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TelaListarEstoqueProdutosController implements Initializable {
    @FXML
    private AnchorPane painelEstoqueProdutos;
    @FXML
    private TableView<Produto> tbView;
    @FXML
    private TableColumn<?, ?> tbId;
    @FXML
    private TableColumn<?, ?> tbNome;
    @FXML
    private TableColumn<?, ?> tbMarca;
    @FXML
    private TableColumn<?, ?> tbQtd;
    @FXML
    private TableColumn<?, ?> tbPeso;
    @FXML
    private TableColumn<?, ?> tbPreco;
    @FXML
    private Button btnVoltar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
        tbId.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tbNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tbMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        tbQtd.setCellValueFactory(new PropertyValueFactory<>("Quantidade"));
        tbPeso.setCellValueFactory(new PropertyValueFactory<>("Peso"));
        tbPreco.setCellValueFactory(new PropertyValueFactory<>("Preco"));
        ArrayList<Produto> produtos = Main.distribudora.consultarProdutosEstoque();
        for(Produto produto: produtos){
            tbView.getItems().add(produto);
        }
    }

    @FXML
    public void voltarBtnHandler(ActionEvent event) {
        Pane listar;
        try {
            listar = FXMLLoader.load(getClass().getResource("../../views/consultas/TelaListar.fxml"));
            painelEstoqueProdutos.getChildren().setAll(listar);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
