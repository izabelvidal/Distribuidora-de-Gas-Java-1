package DistribuidoraDeGas.gui.controllers.consultas;

import DistribuidoraDeGas.Main;
import DistribuidoraDeGas.negocio.entidades.Venda;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 * Essa classe faz a conexão entre a interface gráfica e a fachada.
 * @author Letícia Araújo, Izabel Vidal
 */
public class TelaListarVendaDeProdutosController implements Initializable {
    private Alert spam;

    @FXML
    private Pane painelListaVenda;
    @FXML
    private TextField inputCpf;
    @FXML
    private DatePicker dateSearch;
    @FXML
    private Button btnBuscar;
    @FXML
    private TableView<Venda> tbView;
    @FXML
    private TableColumn<Venda, String> tbId;
    @FXML
    private TableColumn<?, ?> tbStatus;
    @FXML
    private TableColumn<Venda, String> tbQnt;
    @FXML
    private TableColumn<Venda, String> tbPeso;
    @FXML
    private TableColumn<Venda, String> tbPreco;
    @FXML
    private TableColumn<Venda, String> tbDataHora;
    @FXML
    private Button btnVoltar;

    public TelaListarVendaDeProdutosController(){
        spam = new Alert(Alert.AlertType.NONE);
        spam.setAlertType(Alert.AlertType.ERROR);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
        tbId.setCellValueFactory(venda->new SimpleStringProperty(venda.getValue().getProduto().getId()));
        tbStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        tbQnt.setCellValueFactory(prod->new SimpleStringProperty(String.valueOf(prod.getValue().getProduto().getQuantidade())));
        tbPeso.setCellValueFactory(prod->new SimpleStringProperty(String.valueOf(prod.getValue().getProduto().getPeso())));
        tbPreco.setCellValueFactory(venda->new SimpleStringProperty(String.valueOf(venda.getValue().getProduto().getPreco())));
        tbDataHora.setCellValueFactory(venda->new SimpleStringProperty(venda.getValue().getData() + "-" + venda.getValue().getHora()));

        preencherHistorico();
    }

    private void preencherHistorico(){
        ArrayList<Venda> venda = Main.distribudora.consultarVendaProdutos();
        tbView.getItems().clear();
        for(Venda v: venda){
            tbView.getItems().add(v);
        }
    }

    @FXML
    public void buscarBtnHandler(ActionEvent event) {
        LocalDate date = dateSearch.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        tbView.getItems().clear();
        if (dateSearch.getValue() != null) {
            String data = date.format(formatter);
            ArrayList<Venda> venda = Main.distribudora.consultarVendaPorData(data);
            for (Venda v : venda) {
                tbView.getItems().add(v);
            }
        }else {
            preencherHistorico();
        }
    }

    @FXML
    public void voltarBtnHandler(ActionEvent event) {
        Pane listar;
        try {
            listar = FXMLLoader.load(getClass().getResource("../../views/consultas/TelaListar.fxml"));
            painelListaVenda.getChildren().setAll(listar);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
