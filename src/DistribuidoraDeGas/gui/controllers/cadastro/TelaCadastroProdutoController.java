package DistribuidoraDeGas.gui.controllers.cadastro;

import DistribuidoraDeGas.Main;
import DistribuidoraDeGas.negocio.excecoes.ProdutoJaCadastradoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 * Essa classe faz a conexão entre a interface gráfica e a fachada.
 * @author Letícia Araújo, Izabel Vidal
 */
public class TelaCadastroProdutoController implements Initializable {
    @FXML
    private Pane painelCadastroProdutos;
    @FXML
    private TextField inputDigiteNome;
    @FXML
    private TextField inputDigiteMarca;
    @FXML
    private TextField inputDigiteQtd;
    @FXML
    private TextField inputDigitePreco;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField inputID;
    @FXML
    private TextField inputDigitePeso;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
    }

    public void confirmarBtnHandler(ActionEvent event) {
        verificarCampos();
    }

    private void verificarCampos(){
        String nome = inputDigiteNome.getText();
        String marca = inputDigiteMarca.getText();
        String id = inputID.getText();
        String peso = inputDigitePeso.getText();
        String preco = inputDigitePreco.getText();
        String qtd = inputDigiteQtd.getText();
        boolean validados;

        if((nome.length()>0) && (marca.length()>0) && (id.length()>0)
        && (peso.length()>0) && (preco.length()>0) && (qtd.length()>0)){
            validados = validarDados();

            if(validados){
                preco = inputDigitePreco.getText();
                qtd = inputDigiteQtd.getText();
                peso = inputDigitePeso.getText();
                try {
                    Main.distribudora.cadastrarProduto(nome,marca,id,Double.parseDouble(peso),Double.parseDouble(preco),Integer.parseInt(qtd));
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.INFORMATION);
                    a.setContentText("Produto cadastrado com sucesso!");
                    a.show();
                    limparCampos();
                }catch (ProdutoJaCadastradoException e){
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText(e.getMessage());
                    a.show();
                }
            }else{
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Preencha todos os campos!");
                a.show();
            }
        }
    }

    private boolean validarDados(){
        String preco = inputDigitePreco.getText();
        String qtd = inputDigiteQtd.getText();
        String peso = inputDigitePeso.getText();
        boolean erro = false;

        try {
            if(preco.contains(",")){
                inputDigitePreco.setText(preco.replace(",", "."));
                Double.parseDouble(inputDigitePreco.getText());
            }else{
                Double.parseDouble(preco);
            }
        }catch (Exception e){
            erro = true;
            inputDigitePreco.setText("");
        }

        try {
            if(peso.contains(",")){
                inputDigitePeso.setText(peso.replace(",", "."));
                Double.parseDouble(inputDigitePeso.getText());
            }else {
                Double.parseDouble(preco);
            }
        }catch (Exception e){
            erro = true;
            inputDigitePeso.setText("");
        }

        try {
            Integer.parseInt(inputDigiteQtd.getText());
        }catch (Exception e){
            erro = true;
            inputDigiteQtd.setText("");
        }

        if(erro){
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Campos inseridos inconrretamente!");
            a.show();
            return false;
        }else {
            return true;
        }
    }

    private void limparCampos(){
        inputDigiteQtd.setText("");
        inputDigitePeso.setText("");
        inputDigitePreco.setText("");
        inputDigiteMarca.setText("");
        inputDigiteNome.setText("");
        inputID.setText("");
    }

    @FXML
    public void cancelarBtnHandler(ActionEvent event) {
        Pane cadastro;
        try {
            cadastro = FXMLLoader.load(getClass().getResource("../../views/cadastro/TelaCadastro.fxml"));
            painelCadastroProdutos.getChildren().setAll(cadastro);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
