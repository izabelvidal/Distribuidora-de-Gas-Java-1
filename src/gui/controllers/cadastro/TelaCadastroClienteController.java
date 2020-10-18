package gui.controllers.cadastro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TelaCadastroClienteController implements Initializable {
    private Alert spam;
    private ObservableList<String> choicesList;

    @FXML
    private Pane painelCadastroCliente;
    @FXML
    private TextField inputNome;
    @FXML
    private TextField inputTelefone;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;
    @FXML
    private DatePicker dataNascimento;
    @FXML
    private TextField inputCpf;
    @FXML
    private ChoiceBox choiceTipo;
    @FXML
    private TextField inputRua;
    @FXML
    private TextField inputBairro;
    @FXML
    private TextField inputCidade;
    @FXML
    private TextField inputEstado;
    @FXML
    private TextField inputNumeroDaCasa;

    public TelaCadastroClienteController(){
        spam = new Alert(Alert.AlertType.NONE);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> choicesList = FXCollections.observableArrayList("Comum", "Revendedor");
        choiceTipo.setItems(choicesList);
        LocalDate maxDate = LocalDate.now();
        dataNascimento.setDayCellFactory(d ->
                new DateCell(){
                @Override public void updateItem(LocalDate item, boolean empty){
                    super.updateItem(item,empty);
                    setDisable(item.isAfter(maxDate));
                }
                });
        //TODO
    }

    @FXML
    private void confimarBtnHandler(ActionEvent event){
        String nome = inputNome.getText();
        String cpf = inputCpf.getText();
        String tel = inputTelefone.getText();
        String tipo = (String) choiceTipo.getValue();
        LocalDate data = dataNascimento.getValue();
        String rua = inputRua.getText();
        String bairro = inputBairro.getText();
        String cidade = inputCidade.getText();
        String estado = inputEstado.getText();
        String numero = inputNumeroDaCasa.getText();

        if((nome.length()>0) && (cpf.length()>0) && (tel.length()>0)
        && (choiceTipo.getValue() != null) && (rua.length()>0) && (bairro.length()>0)
        && (cidade.length()>0) && (estado.length()>0) && (numero.length()>0)
        && (dataNascimento.getValue() != null));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataNasc = data.format(formatter);





    }
}
