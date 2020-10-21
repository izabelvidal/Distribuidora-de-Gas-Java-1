package DistribuidoraDeGas.gui.controllers.alterar;

import DistribuidoraDeGas.Main;
import DistribuidoraDeGas.gui.controllers.MenuInicialController;
import DistribuidoraDeGas.negocio.entidades.Gerente;
import DistribuidoraDeGas.negocio.excecoes.PessoaInexistenteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 * Essa classe faz a conexão entre a interface gráfica e a fachada.
 * @author Letícia Araújo, Izabel Vidal
 */
public class TelaAlterarGerenteController implements Initializable {
    private Alert spam;
    private Gerente gerente;

    @FXML
    public Button btnTrocarSenha;
    @FXML
    public Button btnTrocarEmail;
    @FXML
    public TextField inputAlteraSenha;
    @FXML
    public TextField inputAlteraEmail;
    @FXML
    private Pane painelAlterar;
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField inputCpf;
    @FXML
    private TextField inputAlteraTell;
    @FXML
    private Label alterarInformacoes;
    @FXML
    private Button btnTrocarTell;
    @FXML
    private Button btnVoltar;
    @FXML
    private TextField inputAlteraRua;
    @FXML
    private Button btnTrocarRua;
    @FXML
    private TextField inputAlteraBairro;
    @FXML
    private Button btnTrocarBairro;
    @FXML
    private TextField inputAlteraNumero;
    @FXML
    private Button btnTrocarNumero;
    @FXML
    private TextField inputAlterarCidade;
    @FXML
    private TextField inputAlteraEstado;
    @FXML
    private Button btnTrocarCidade;
    @FXML
    private Button btnTrocarEstado;

    public TelaAlterarGerenteController(){
        spam = new Alert(Alert.AlertType.NONE);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
    }

    @FXML
    public void buscarCpfBtnHandler(ActionEvent event) {
        if(inputCpf.getLength()>0){
            try {
                gerente = Main.distribudora.consultarGerente(inputCpf.getText());
                btnTrocarTell.setDisable(false);
                btnTrocarBairro.setDisable(false);
                btnTrocarNumero.setDisable(false);
                btnTrocarRua.setDisable(false);
                btnTrocarCidade.setDisable(false);
                btnTrocarEstado.setDisable(false);
                btnTrocarCidade.setDisable(false);
                btnTrocarEmail.setDisable(false);
                btnTrocarSenha.setDisable(false);
                inputAlteraBairro.setText(gerente.getEndereco().getBairro());
                inputAlteraRua.setText(gerente.getEndereco().getRua());
                inputAlteraNumero.setText(String.valueOf(gerente.getEndereco().getNumero()));
                inputAlteraTell.setText(gerente.getTelefone());
                inputAlteraEstado.setText(gerente.getEndereco().getEstado());
                inputAlterarCidade.setText(gerente.getEndereco().getCidade());
                inputAlteraEmail.setText(gerente.getEmail());
                inputAlteraSenha.setText(gerente.getSenha());
            }catch (PessoaInexistenteException e){
                spam.setAlertType(Alert.AlertType.ERROR);
                spam.setContentText(e.getMessage());
                spam.show();

                btnTrocarRua.setDisable(true);
                btnTrocarNumero.setDisable(true);
                btnTrocarBairro.setDisable(true);
                btnTrocarTell.setDisable(true);
                btnTrocarCidade.setDisable(true);
                btnTrocarEstado.setDisable(true);
                btnTrocarSenha.setDisable(true);
                btnTrocarEmail.setDisable(true);
                inputAlteraTell.setText("");
                inputAlteraNumero.setText("");
                inputAlteraRua.setText("");
                inputAlteraBairro.setText("");
                inputAlterarCidade.setText("");
                inputAlteraEstado.setText("");
                inputAlteraSenha.setText("");
                inputAlteraEmail.setText("");
            }
        }else {
            btnTrocarRua.setDisable(true);
            btnTrocarNumero.setDisable(true);
            btnTrocarBairro.setDisable(true);
            btnTrocarTell.setDisable(true);
            btnTrocarEstado.setDisable(true);
            btnTrocarCidade.setDisable(true);
            btnTrocarEmail.setDisable(true);
            btnTrocarSenha.setDisable(true);
            inputAlteraTell.setText("");
            inputAlteraNumero.setText("");
            inputAlteraRua.setText("");
            inputAlteraBairro.setText("");
            inputAlteraEstado.setText("");
            inputAlterarCidade.setText("");
            inputAlteraEmail.setText("");
            inputAlteraSenha.setText("");
        }
    }

    @FXML
    public void trocarTellBtnHandler(ActionEvent event) {
        String telefone = inputAlteraTell.getText();
        if(inputAlteraTell.getLength()>0){
            if(!telefone.equals(gerente.getTelefone())){
                Main.distribudora.atualizarTelefoneGerente(gerente,telefone);
                spam.setAlertType(Alert.AlertType.INFORMATION);
                spam.setContentText("Telefone alterado com sucesso");
                spam.show();
            }
        }
    }

    @FXML
    public void trocarBairroBtnHandler(ActionEvent event) {
        String bairro = inputAlteraBairro.getText();
        if(inputAlteraBairro.getLength()>0){
            if(!bairro.equals(gerente.getEndereco().getBairro())){
                Main.distribudora.atualizarBairroGerente(bairro,gerente,gerente.getEndereco());
                spam.setAlertType(Alert.AlertType.INFORMATION);
                spam.setContentText("Bairro alterado com sucesso");
                spam.show();
            }
        }
    }

    @FXML
    public void trocarRuaBtnHandler(ActionEvent event) {
        String rua = inputAlteraRua.getText();
        if(inputAlteraRua.getLength()>0){
            if(!rua.equals(gerente.getEndereco().getRua())){
                Main.distribudora.atualizarRuaGerente(gerente, gerente.getEndereco(), rua);
                spam.setAlertType(Alert.AlertType.INFORMATION);
                spam.setContentText("Rua alterada com sucesso");
                spam.show();
            }
        }
    }

    @FXML
    public void trocarNumeroBtnHandler(ActionEvent event) {
        int numero = Integer.parseInt(inputAlteraNumero.getText());
        if(inputAlteraNumero.getLength()>0){
            if(numero != gerente.getEndereco().getNumero()){
                Main.distribudora.atualizarNumeroGerenete(numero,gerente,gerente.getEndereco());
                spam.setAlertType(Alert.AlertType.INFORMATION);
                spam.setContentText("Número alterada com sucesso");
                spam.show();
            }
        }
    }

    @FXML
    public void trocarCidadeBtnHandler(ActionEvent event) {
        String cidade = inputAlterarCidade.getText();
        if(inputAlterarCidade.getLength()>0){
            if(!cidade.equals(gerente.getEndereco().getCidade())){
                Main.distribudora.atualizarCidadeGerente(cidade, gerente, gerente.getEndereco());
                spam.setAlertType(Alert.AlertType.INFORMATION);
                spam.setContentText("Cidade alterada com sucesso");
                spam.show();
            }
        }
    }

    @FXML
    public void trocarEstadoBtnHandler(ActionEvent event) {
        String estado = inputAlteraEstado.getText();
        if(inputAlteraEstado.getLength()>0){
            if(!estado.equals(gerente.getEndereco().getEstado())){
                Main.distribudora.atualizarEstadoGerente(estado,gerente,gerente.getEndereco());
                spam.setAlertType(Alert.AlertType.INFORMATION);
                spam.setContentText("Estado alterada com sucesso");
                spam.show();
            }
        }
    }

    @FXML
    public void voltarBtnHandler(ActionEvent event) {
        Pane telaCadastro;
        try{
            telaCadastro = FXMLLoader.load(getClass().getResource("../../views/alterar/TelaAlterar.fxml"));
            painelAlterar.getChildren().setAll(telaCadastro);
        }catch (IOException ex){
            Logger.getLogger(MenuInicialController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    public void trocarEmailBtnHandler(ActionEvent event) {
        String email = inputAlteraEmail.getText();
        if(inputAlteraEmail.getLength()>0){
            if(!email.equals(gerente.getEmail())){
                Main.distribudora.atualizarEmail(email,gerente);
                spam.setAlertType(Alert.AlertType.INFORMATION);
                spam.setContentText("Email alterada com sucesso");
                spam.show();
            }
        }
    }

    public void trocarSenhaBtnHandler(ActionEvent event) {
        String senha = inputAlteraSenha.getText();
        if(inputAlteraSenha.getLength()>0){
            if(!senha.equals(gerente.getSenha())){
                Main.distribudora.atualizarSenha(senha,gerente);
                spam.setAlertType(Alert.AlertType.INFORMATION);
                spam.setContentText("Senha alterada com sucesso");
                spam.show();
            }
        }
    }
}
