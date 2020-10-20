package DistribuidoraDeGas.gui.controllers.consultas;

import DistribuidoraDeGas.Main;
import DistribuidoraDeGas.negocio.entidades.Cliente;
import DistribuidoraDeGas.negocio.entidades.Produto;
import DistribuidoraDeGas.negocio.entidades.Venda;
import DistribuidoraDeGas.negocio.excecoes.PessoaInexistenteException;
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
    private TableView<Venda> tbProduto;
    @FXML
    private TableColumn<Venda, String> tbId;
    @FXML
    private TableColumn<Venda, String> tbNomeProduto;
    @FXML
    private TableColumn<Venda, String> tbMarca;
    @FXML
    private TableColumn<Venda, String> tbQnt;
    @FXML
    private TableColumn<Venda, String> tbPeso;
    @FXML
    private TableColumn<Venda, String> tbPreco;
    @FXML
    private Button btnVoltar;
    @FXML
    private TableView<Venda> tbVenda;
    @FXML
    private TableColumn<Venda, String> tbCpf;
    @FXML
    private TableColumn<Venda, String> tbRua;
    @FXML
    private TableColumn<Venda, String> tbBairro;
    @FXML
    private TableColumn<Venda, String> tbNumero;
    @FXML
    private TableColumn<Venda, String> tbData;
    @FXML
    private TableColumn<Venda, String> tbHora;
    @FXML
    private TableColumn<?, ?> tbStatus;
    @FXML
    private TableColumn<Venda, String> tbTipo;

    public TelaListarVendaDeProdutosController(){
        spam = new Alert(Alert.AlertType.NONE);
        spam.setAlertType(Alert.AlertType.NONE);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
        LocalDate maxDate = LocalDate.now();
        dateSearch.setDayCellFactory(d ->
                new DateCell(){
            @Override public void updateItem(LocalDate item, boolean empty){
                super.updateItem(item, empty);
                setDisable(item.isAfter(maxDate));
            }});
        tbId.setCellValueFactory(prod -> new SimpleStringProperty(prod.getValue().getProduto().getId()));
        tbMarca.setCellValueFactory(prod -> new SimpleStringProperty(prod.getValue().getProduto().getMarca()));
        tbNomeProduto.setCellValueFactory(prod -> new SimpleStringProperty(prod.getValue().getProduto().getNome()));
        tbQnt.setCellValueFactory(prod -> new SimpleStringProperty(String.valueOf(prod.getValue().getProduto().getQuantidade())));
        tbData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tbHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        tbPreco.setCellValueFactory(prod -> new SimpleStringProperty(String.valueOf(prod.getValue().getProduto().getPreco())));
        tbPeso.setCellValueFactory(prod -> new SimpleStringProperty(String.valueOf(prod.getValue().getProduto().getPeso())));
      /*  tbCpf.setCellValueFactory(cliente -> new SimpleStringProperty(cliente.getValue().getCliente().getCpf()));
        tbRua.setCellValueFactory(cliente -> new SimpleStringProperty(cliente.getValue().getCliente().getEndereco().getRua()));
        tbBairro.setCellValueFactory(cliente -> new SimpleStringProperty(cliente.getValue().getCliente().getEndereco().getBairro()));
        tbNumero.setCellValueFactory(cliente -> new SimpleStringProperty(String.valueOf(cliente.getValue().getCliente().getEndereco().getNumero())));
        tbStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        tbTipo.setCellValueFactory(cliente -> new SimpleStringProperty(cliente.getValue().getCliente().getTipo()));

        preencherHistorico();*/
    }

   /* private void preencherHistorico(){
        ArrayList<Venda> vendas = Main.distribudora.consultarVendaProdutos();
        tbProduto.getItems().clear();
        tbVenda.getItems().clear();
        for(Venda v: vendas){
            tbProduto.getItems().add(v);
            tbVenda.getItems().add(v);
        }
    }


    public void buscarBtnHandler(ActionEvent event) {
        LocalDate date = dateSearch.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        tbProduto.getItems().clear();
        tbVenda.getItems().clear();

        if(inputCpf.getLength()>0){
            try {
                Cliente cliente = Main.distribudora.consultarCliente(inputCpf.getText());
            }catch (PessoaInexistenteException e){
                spam.setContentText(e.getMessage());
                spam.show();
                tbProduto.getItems().clear();
                tbVenda.getItems().clear();
                return;
            }
           /* if(dateSearch.getValue() != null){
                String data = date.format(formatter);
                ArrayList<Venda> vendaProduto = Main.distribudora.consultarVendaClientePorData(data, inputCpf.getText());
                for (Venda v: vendaProduto){
                    tbVenda.getItems().add(v);
                    tbProduto.getItems().add(v);
                }
            }else{
                ArrayList<Venda> vendas = Main.distribudora.consultarVendaPeloCliente(inputCpf.getText());
                for (Venda v: vendas){
                    tbProduto.getItems().add(v);
                    tbVenda.getItems().add(v);
                }
            }
        }else if(dateSearch.getValue() != null){
            String data = date.format(formatter);
            ArrayList<Venda> venda = Main.distribudora.consultarVendaPorData(data);

            for(Venda v: venda){
                tbVenda.getItems().add(v);
                tbProduto.getItems().add(v);
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
    }*/
}
