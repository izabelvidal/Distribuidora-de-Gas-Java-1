package DistribuidoraDeGas.gui.controllers.cadastro;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import DistribuidoraDeGas.Main;
import DistribuidoraDeGas.negocio.entidades.Endereco;
import DistribuidoraDeGas.negocio.excecoes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 * Essa classe faz a conexão entre a interface gráfica e a fachada.
 * @author Letícia Araújo, Izabel Vidal
 */
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
        choicesList = FXCollections.observableArrayList("Comum", "Revendedor");
        spam = new Alert(Alert.AlertType.NONE);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    private void limparCampos(){
        inputNome.setText("");
        inputBairro.setText("");
        inputCidade.setText("");
        inputCpf.setText("");
        inputEstado.setText("");
        inputNumeroDaCasa.setText("");
        inputTelefone.setText("");
        inputRua.setText("");
        choiceTipo.setValue(null);
        dataNascimento.setValue(null);
    }

    public void confirmarBtnHandler(ActionEvent event) {
        String nome = inputNome.getText();
        String cpf = inputCpf.getText();
        String tel = inputTelefone.getText();
        LocalDate data = dataNascimento.getValue();
        String rua = inputRua.getText();
        String tipo = (String) choiceTipo.getValue();
        String bairro = inputBairro.getText();
        String cidade = inputCidade.getText();
        String estado = inputEstado.getText();
        String numero = inputNumeroDaCasa.getText();
        int numeroDaCasa = Integer.parseInt(numero);

        if ((nome.length() > 0) && (cpf.length() > 0) && (tel.length() > 0)
                && (rua.length() > 0) && (bairro.length() > 0) && (cidade.length() > 0) && (estado.length() > 0) && (numero.length() > 0)
                && (dataNascimento.getValue() != null)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataNasc = data.format(formatter);


            try {
                Endereco endereco = new Endereco(rua, numeroDaCasa, bairro, cidade, estado);
                Main.distribudora.cadastrarCliente(nome, cpf, dataNasc, tel, endereco, tipo);
                spam.setAlertType(Alert.AlertType.INFORMATION);
                spam.setContentText("Cliente cadastrado com sucesso!");
                spam.show();
                limparCampos();
                btnConfirmar.setDisable(true);
            } catch (PessoaJaCadastradaException | NomeTamanhoException | NomeApenasLetrasException | CpfApenasNumerosException | CpfTamanhoException e) {
                spam.setAlertType(Alert.AlertType.ERROR);
                spam.setContentText(e.getMessage());
                spam.show();
            }
        }else{
            spam.setAlertType(Alert.AlertType.ERROR);
            spam.setContentText("Preencha todas as informações do cliente");
            spam.show();
        }
    }

    @FXML
    public void cancelarBtnHandler(ActionEvent event) {
        Pane cadastro;
        try{
            cadastro = FXMLLoader.load(getClass().getResource("../../views/cadastro/TelaCadastro.fxml"));
            painelCadastroCliente.getChildren().setAll(cadastro);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}

