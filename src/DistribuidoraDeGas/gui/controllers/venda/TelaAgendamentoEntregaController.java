package DistribuidoraDeGas.gui.controllers.venda;

import DistribuidoraDeGas.Main;
import DistribuidoraDeGas.gui.controllers.MenuInicialController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaAgendamentoEntregaController implements Initializable {
    private String dataAtual;
    private DateTimeFormatter formatter;

    @FXML
    private Pane painelAgendamento;
    @FXML
    private Button btnAgendar;
    @FXML
    private Button btnVoltar;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ChoiceBox choiceAgendamentos;

    public TelaAgendamentoEntregaController(){
        this.dataAtual = LocalDate.now().toString();
        SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(new Date());
        gc.add(Calendar.HOUR,-1);
        this.dataAtual = sdfData.format(gc.getTime());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
        //Nao pode selecionar ontem
        LocalDate minDate = LocalDate.now();
        datePicker.setDayCellFactory(d ->
                new DateCell(){
            @Override public void updateItem(LocalDate item, boolean empty){
                super.updateItem(item, empty);
                setDisable(item.isBefore(minDate));
            }});

        datePicker.setValue(LocalDate.now());
        preencherListaHoras(this.dataAtual);
    }

    private void preencherListaHoras(String data){
        ArrayList<String> lista = Main.distribudora.consultarListaHorariosLivres(data);

        ObservableList<String> choicesList = FXCollections.observableArrayList(lista);
        choiceAgendamentos.setItems(choicesList);
    }

    @FXML
    public void voltarBtnHandler(ActionEvent event) {
        Pane menuInicial;
        try{
            menuInicial = FXMLLoader.load(getClass().getResource("../../views/MenuInicial.fxml"));
            painelAgendamento.getChildren().setAll(menuInicial);
        }catch (IOException ex){
            Logger.getLogger(MenuInicialController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    public void agendarBtnHandler(ActionEvent event) {
        if(choiceAgendamentos.getValue()!=null){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/venda/TelaVendaProdutos.fxml"));
                Parent root = loader.load();
                TelaVendaProdutosController telaVendaProdutosController = loader.getController();

                String data = datePicker.getValue().format(formatter);
                String hora = (String) choiceAgendamentos.getValue();
                telaVendaProdutosController.transferirInfo(data,hora);

                Main.stageMain.setScene(new Scene(root));
                Main.stageMain.show();
            }catch (IOException ex){
                Logger.getLogger(MenuInicialController.class.getName()).log(Level.SEVERE,null, ex);
            }
        }else{
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Selecione um horário!");
            a.show();
        }
    }

    @FXML
    public void inputDataHandler(ActionEvent event) {
        String data = datePicker.getValue().format(formatter);

        preencherListaHoras(data);
    }
}
