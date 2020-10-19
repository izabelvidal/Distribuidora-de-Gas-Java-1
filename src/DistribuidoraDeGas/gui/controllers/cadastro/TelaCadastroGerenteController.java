package DistribuidoraDeGas.gui.controllers.cadastro;

import DistribuidoraDeGas.Main;
import DistribuidoraDeGas.negocio.entidades.Endereco;
import DistribuidoraDeGas.negocio.excecoes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TelaCadastroGerenteController implements Initializable {
    private Alert spam;

    @FXML
    private Pane painelCadastroGerente;
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
    private TextField inputRua;
    @FXML
    private TextField inputBairro;
    @FXML
    private TextField inputCidade;
    @FXML
    private TextField inputEstado;
    @FXML
    private TextField inputNumeroDaCasa;
    @FXML
    private TextField inputEmail;
    @FXML
    private TextField inputCnpj;
    @FXML
    private TextField inputSenha;

    public TelaCadastroGerenteController(){
        spam = new Alert(Alert.AlertType.NONE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    public void confirmarBtnHandler(ActionEvent event) {
        String nome = inputNome.getText();
        String cpf = inputCpf.getText();
        String tel = inputTelefone.getText();
        LocalDate data = dataNascimento.getValue();
        String rua = inputRua.getText();
        String email = inputEmail.getText();
        String senha = inputEmail.getText();
        String bairro = inputBairro.getText();
        String cidade = inputCidade.getText();
        String estado = inputEstado.getText();
        String numero = inputNumeroDaCasa.getText();
        String cnpj = inputCnpj.getText();
        int numeroDaCasa = Integer.parseInt(numero);

        if ((nome.length() > 0) && (cpf.length() > 0) && (tel.length() > 0)
                && (rua.length() > 0) && (bairro.length() > 0) && (cidade.length() > 0) && (estado.length() > 0) && (numero.length() > 0)
                && (dataNascimento.getValue() != null) && (email.length()>0) && (senha.length()>0) && (cnpj.length()>0)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataNasc = data.format(formatter);


            try {
                Endereco endereco = new Endereco(rua, numeroDaCasa, bairro, cidade, estado);
                Main.distribudora.cadastrarGerente(nome, cpf, dataNasc, tel, endereco,email,senha,cnpj);
                spam.setAlertType(Alert.AlertType.INFORMATION);
                spam.setContentText("Gerente cadastrado com sucesso!");
                spam.show();
                limparCampos();
                btnConfirmar.setDisable(true);
            } catch (PessoaJaCadastradaException | NomeTamanhoException | NomeApenasLetrasException | CpfApenasNumerosException | CpfTamanhoException | CnpjApenasNumerosException | CnpjTamanhoException | SenhaTamanhoException | SenhaCaracteresInvalidosException e) {
                spam.setAlertType(Alert.AlertType.ERROR);
                spam.setContentText(e.getMessage());
                spam.show();
            }
        }else{
            spam.setAlertType(Alert.AlertType.ERROR);
            spam.setContentText("Preencha todas as informações do gerente");
            spam.show();
        }
    }

    @FXML
    public void cancelarBtnHandler(ActionEvent event) {
        Pane cadastro;
        try{
            cadastro = FXMLLoader.load(getClass().getResource("../../views/cadastro/TelaCadastro.fxml"));
            painelCadastroGerente.getChildren().setAll(cadastro);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
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
        inputCnpj.setText("");
        inputEmail.setText("");
        inputSenha.setText("");
        dataNascimento.setValue(null);
    }


}
