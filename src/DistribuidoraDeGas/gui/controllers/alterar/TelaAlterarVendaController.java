package DistribuidoraDeGas.gui.controllers.alterar;

import DistribuidoraDeGas.Main;
import DistribuidoraDeGas.negocio.entidades.Cliente;
import DistribuidoraDeGas.negocio.entidades.Produto;
import DistribuidoraDeGas.negocio.entidades.Venda;
import DistribuidoraDeGas.negocio.excecoes.ProdutoInexistenteException;
import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 * Essa classe faz a conexão entre a interface gráfica e a fachada.
 * @author Letícia Araújo, Izabel Vidal
 */
public class TelaAlterarVendaController implements Initializable {
    private Alert spam;

    @FXML
    private Pane painelAlterarVenda;
    @FXML
    private DatePicker dataAlterar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnRemoverVenda;
    @FXML
    private TextField inputId;
    @FXML
    private Button btnBuscar;
    @FXML
    private TableView<Venda> tbVendaview;
    @FXML
    private TableColumn<Venda, String> tbNome;
    @FXML
    private TableColumn<Venda, String> tbId;
    @FXML
    private TableColumn<Venda, String> tbData;
    @FXML
    private TableColumn<Venda, String> tbHora;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnMarcarConcluido;
    @FXML
    private ChoiceBox<String> choiceHorario;

    public TelaAlterarVendaController(){
        spam = new Alert(Alert.AlertType.NONE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
        String dataAtual = sdfData.format(new Date());

        LocalDate minDate = LocalDate.now();

        dataAlterar.setDayCellFactory(d->
                new DateCell(){
                    @Override public void updateItem(LocalDate item, boolean empty){
                        super.updateItem(item, empty);
                        setDisable(item.isBefore(minDate));
                    }});
        tbNome.setCellValueFactory(prod->new SimpleStringProperty(prod.getValue().getProduto().getNome()));
        tbId.setCellValueFactory(prod->new SimpleStringProperty(prod.getValue().getProduto().getId()));
        tbData.setCellValueFactory(new PropertyValueFactory<>("Data"));
        tbHora.setCellValueFactory(new PropertyValueFactory<>("Hora"));

        dataAlterar.setValue(minDate);
        preencherListaHoras(dataAtual);
    }

    private void preencherListaHoras(String data){
        ArrayList<String> lista = Main.distribudora.consultarListaHorariosLivres(data);
        ObservableList<String> choiceList = FXCollections.observableArrayList(lista);
        choiceHorario.setItems(choiceList);
    }

    @FXML
    public void marcarConcluidoBtnHandler(ActionEvent event) {
        Venda venda = tbVendaview.getSelectionModel().getSelectedItem();
        if(venda!=null){
            tbVendaview.getItems().remove(venda);
            Main.distribudora.marcarVendaConcluida(venda);
            spam.setAlertType(Alert.AlertType.INFORMATION);
            spam.setContentText("Entrega concluída com sucesso!");
            spam.show();
        }else {
            spam.setAlertType(Alert.AlertType.ERROR);
            spam.setContentText("Selecione uma venda");
            spam.show();
        }
    }

    @FXML
    public void cancelarBtnHandler(ActionEvent event) {
        Pane alterar;
        try {
            alterar = FXMLLoader.load(getClass().getResource("../../views/alterar/TelaAlterar.fxml"));
            painelAlterarVenda.getChildren().setAll(alterar);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void buscarBtnHandler(ActionEvent event) {
        tbVendaview.getItems().clear();
        if(inputId.getLength()>0){
            try {
                Produto produto = Main.distribudora.consultarProduto(inputId.getText());
                ArrayList<Venda> vendaNaoConcluida = Main.distribudora.consultarVendasNaoConcluida(produto.getId());
                for(Venda v: vendaNaoConcluida){
                    tbVendaview.getItems().add(v);
                }
                if(vendaNaoConcluida.isEmpty()){
                    spam.setAlertType(Alert.AlertType.INFORMATION);
                    spam.setContentText("Nenhuma entrega não concluída encontrada!");
                    spam.show();
                }else {
                    btnAlterar.setDisable(false);
                }
            }catch (ProdutoInexistenteException e){
                spam.setAlertType(Alert.AlertType.ERROR);
                spam.setContentText(e.getMessage());
                spam.show();
            }
        }else {
            btnAlterar.setDisable(true);
        }
    }

    @FXML
    public void removerVendaBtnHandler(ActionEvent event) {
        Venda venda = tbVendaview.getSelectionModel().getSelectedItem();
        if(venda!=null){
            tbVendaview.getItems().remove(venda);
            Main.distribudora.desmarcarVenda(venda);
            spam.setAlertType(Alert.AlertType.INFORMATION);
            spam.setContentText("Venda removida com sucesso!");
            spam.show();
        }else {
            spam.setAlertType(Alert.AlertType.ERROR);
            spam.setContentText("Selecione uma venda!");
            spam.show();
        }
    }

    @FXML
    public void alterarBtnHandler(ActionEvent event) {
        Venda venda = tbVendaview.getSelectionModel().getSelectedItem();
        if(choiceHorario.getValue()!=null && venda!=null){
            String data = dataAlterar.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Main.distribudora.alterarVenda(venda,data,choiceHorario.getValue());
            tbVendaview.getItems().clear();
            tbVendaview.getItems().add(venda);
        }else {
            spam.setAlertType(Alert.AlertType.ERROR);
            spam.setContentText("Selecione um horário e um serviço!");
            spam.show();
        }
    }

    @FXML
    public void changeDataHandler(ActionEvent event) {
        if(dataAlterar.getValue()!=null){
            String data = dataAlterar.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            preencherListaHoras(data);
        }
    }
}
