package DistribuidoraDeGas.gui.controllers.alterar;

import DistribuidoraDeGas.Main;
import DistribuidoraDeGas.gui.controllers.MenuInicialController;
import DistribuidoraDeGas.negocio.entidades.Cliente;
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
public class TelaAlterarClienteController implements Initializable {
    private Alert spam;
    private Cliente cliente;

    @FXML
    private Button btnTrocarEstado;
    @FXML
    private Button btnTrocarCidade;
    @FXML
    private TextField inputAlteraEstado;
    @FXML
    private TextField inputAlterarCidade;
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

    public TelaAlterarClienteController(){
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
                cliente = Main.distribudora.consultarCliente(inputCpf.getText());
                btnTrocarTell.setDisable(false);
                btnTrocarBairro.setDisable(false);
                btnTrocarNumero.setDisable(false);
                btnTrocarRua.setDisable(false);
                btnTrocarCidade.setDisable(false);
                btnTrocarEstado.setDisable(false);
                btnTrocarCidade.setDisable(false);
                inputAlteraBairro.setText(cliente.getEndereco().getBairro());
                inputAlteraRua.setText(cliente.getEndereco().getRua());
                inputAlteraNumero.setText(String.valueOf(cliente.getEndereco().getNumero()));
                inputAlteraTell.setText(cliente.getTelefone());
                inputAlteraEstado.setText(cliente.getEndereco().getEstado());
                inputAlterarCidade.setText(cliente.getEndereco().getCidade());
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
                inputAlteraTell.setText("");
                inputAlteraNumero.setText("");
                inputAlteraRua.setText("");
                inputAlteraBairro.setText("");
                inputAlterarCidade.setText("");
                inputAlteraEstado.setText("");
            }
        }else {
            btnTrocarRua.setDisable(true);
            btnTrocarNumero.setDisable(true);
            btnTrocarBairro.setDisable(true);
            btnTrocarTell.setDisable(true);
            btnTrocarEstado.setDisable(true);
            btnTrocarCidade.setDisable(true);
            inputAlteraTell.setText("");
            inputAlteraNumero.setText("");
            inputAlteraRua.setText("");
            inputAlteraBairro.setText("");
            inputAlteraEstado.setText("");
            inputAlterarCidade.setText("");
        }
    }

    @FXML
    public void trocarTellBtnHandler(ActionEvent event) {
        String telefone = inputAlteraTell.getText();
        if(inputAlteraTell.getLength()>0){
            if(!telefone.equals(cliente.getTelefone())){
                Main.distribudora.atualizarTelefoneCliente(cliente,telefone);
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
            if(!bairro.equals(cliente.getEndereco().getBairro())){
                Main.distribudora.atualizarBairro(bairro,cliente,cliente.getEndereco());
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
            if(!rua.equals(cliente.getEndereco().getRua())){
                Main.distribudora.atualizarRua(cliente, cliente.getEndereco(), rua);
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
            if(numero != cliente.getEndereco().getNumero()){
                Main.distribudora.atualizarNumero(numero,cliente,cliente.getEndereco());
                spam.setAlertType(Alert.AlertType.INFORMATION);
                spam.setContentText("Rua alterada com sucesso");
                spam.show();
            }
        }
    }

    @FXML
    public void trocarCidadeBtnHandler(ActionEvent event) {
        String cidade = inputAlterarCidade.getText();
        if(inputAlterarCidade.getLength()>0){
            if(!cidade.equals(cliente.getEndereco().getCidade())){
                Main.distribudora.atualizarCidade(cidade, cliente, cliente.getEndereco());
                spam.setAlertType(Alert.AlertType.INFORMATION);
                spam.setContentText("Rua alterada com sucesso");
                spam.show();
            }
        }
    }

    @FXML
    public void trocarEstadoBtnHandler(ActionEvent event) {
        String estado = inputAlteraEstado.getText();
        if(inputAlteraEstado.getLength()>0){
            if(!estado.equals(cliente.getEndereco().getEstado())){
                Main.distribudora.atualizarEstado(estado,cliente,cliente.getEndereco());
                spam.setAlertType(Alert.AlertType.INFORMATION);
                spam.setContentText("Rua alterada com sucesso");
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
}
