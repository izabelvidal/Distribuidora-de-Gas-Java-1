package DistribuidoraDeGas.gui.controllers.alterar;

import DistribuidoraDeGas.Main;
import DistribuidoraDeGas.negocio.entidades.Produto;
import DistribuidoraDeGas.negocio.excecoes.ProdutoInexistenteException;
import DistribuidoraDeGas.negocio.excecoes.QuantidadeInvalidaException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 * Essa classe faz a conexão entre a interface gráfica e a fachada.
 * @author Letícia Araújo, Izabel Vidal
 */
public class TelaAlterarProdutosController implements Initializable {
    private Produto ultimoProdutoPesquisado;
    private Alert spam;
    
    @FXML
    private Pane painelAlterarProdutos;
    @FXML
    private TextField inputId;
    @FXML
    private Button btnBuscar;
    @FXML
    private TableView<Produto> tbView;
    @FXML
    private TableColumn<?, ?> tbNome;
    @FXML
    private TableColumn<?, ?> tbMarca;
    @FXML
    private TableColumn<?, ?> tbId;
    @FXML
    private TableColumn<?, ?> tbQntd;
    @FXML
    private TableColumn<?, ?> tbPeso;
    @FXML
    private TableColumn<?, ?> tbPreco;
    @FXML
    private Button btnRemoverProduto;
    @FXML
    private TextField inputQntd;
    @FXML
    private TextField inputPreco;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;
    
    public TelaAlterarProdutosController(){
        spam = new Alert(Alert.AlertType.NONE);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
        tbNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tbMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        tbId.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tbQntd.setCellValueFactory(new PropertyValueFactory<>("Quantidade"));
        tbPeso.setCellValueFactory(new PropertyValueFactory<>("Peso"));
        tbPreco.setCellValueFactory(new PropertyValueFactory<>("Preço"));
    }
    
    @FXML
    public void buscarBtnHandler(ActionEvent event) {
        if(inputId.getText().length()>0){
            try {
                ultimoProdutoPesquisado = Main.distribudora.consultarProduto(inputId.getText());
                tbView.getItems().clear();
                tbView.getItems().add(ultimoProdutoPesquisado); //Insere produto na tabela de visualizacao
                btnRemoverProduto.setDisable(false);
                btnConfirmar.setDisable(false);
            }catch (ProdutoInexistenteException e){
                spam.setAlertType(Alert.AlertType.ERROR);
                spam.setContentText(e.getMessage());
                spam.show();
                
                inputId.setText("");
                tbView.getItems().clear();
                btnRemoverProduto.setDisable(true);
                btnConfirmar.setDisable(true);
            }
        }else {
            inputId.setText("");
            tbView.getItems().clear();
            btnRemoverProduto.setDisable(true);
            btnConfirmar.setDisable(true);
        }
    }

    @FXML
    public void removerProdutoBtnHandler(ActionEvent event) {
        tbView.getItems().clear();
        try {
            Main.distribudora.removerProduto(ultimoProdutoPesquisado.getId());
            spam.setAlertType(Alert.AlertType.INFORMATION);
            spam.setContentText("Produto removido com sucesso!");
            spam.show();
        }catch (ProdutoInexistenteException e){
            spam.setAlertType(Alert.AlertType.INFORMATION);
            spam.setContentText(e.getMessage());
            spam.show();
        }
        
        inputId.setText("");
        btnRemoverProduto.setDisable(true);
        btnConfirmar.setDisable(true);
    }

    @FXML
    public void confirmarBtnHandler(ActionEvent event) {
        boolean erro = false;
        if(inputPreco.getLength() == 0 && inputQntd.getLength() == 0){
            spam.setAlertType(Alert.AlertType.ERROR);
            spam.setContentText("Insira pelo menos uma alteração");
            spam.show();
        }else if(inputPreco.getLength() > 0 && inputQntd.getLength() > 0){
            boolean precoValidado = validarPreco();
            boolean qntdValidada = validarQntd();
            if(precoValidado && qntdValidada){
                int qntd = Integer.parseInt(inputQntd.getText());
                double preco = Double.parseDouble(inputPreco.getText());

                alterarProduto(qntd,preco);
            }else {
                erro = true;
            }
        }else if(inputPreco.getLength() > 0){
            boolean precoValidado = validarPreco();
            if(precoValidado){
                int qntd = ultimoProdutoPesquisado.getQuantidade();
                double preco = Double.parseDouble(inputPreco.getText());
                alterarProduto(qntd,preco);
            }else {
                erro = true;
            }
        }else{
            boolean qntdValidada = validarQntd();
            if(qntdValidada){
                int qntd = Integer.parseInt(inputQntd.getText());
                double preco = Double.parseDouble(inputPreco.getText());
                alterarProduto(qntd,preco);
            }else {
                erro = true;
            }
        }
        if (erro){
            spam.setAlertType(Alert.AlertType.ERROR);
            spam.setContentText("Um ou mais campos inseridos incorretamente!");
            spam.show();
        }
    }

    @FXML
    public void cancelarBtnHandler(ActionEvent event) {
        Pane alterar;
        try {
            alterar = FXMLLoader.load(getClass().getResource("../../views/alterar/TelaAlterar.fxml"));
            painelAlterarProdutos.getChildren().setAll(alterar);
        }catch (IOException ex){
            System.out.println(ex);
        }
    }

    private boolean validarQntd(){
        try {
            int quantidade = Integer.parseInt(inputQntd.getText());
            return true;
        }catch (Exception ex){
            inputQntd.setText("");
            return false;
        }
    }

    private boolean validarPreco(){
        String preco = inputPreco.getText();

        try {
            if (preco.contains(",")){
                inputPreco.setText(preco.replace(",", "."));
                Double.parseDouble(inputPreco.getText());
            }else {
                Double.parseDouble(preco);
            }return true;
        }catch (Exception e){
            inputPreco.setText("");
            return false;
        }
    }

    private void alterarProduto(int qntd, double preco){
        String id = ultimoProdutoPesquisado.getId();
        try {
            Main.distribudora.atualizarProduto(id, qntd,preco);
        }catch (QuantidadeInvalidaException | ProdutoInexistenteException e){
            spam.setAlertType(Alert.AlertType.ERROR);
            spam.setContentText(e.getMessage());
            spam.show();
        }

        spam.setAlertType(Alert.AlertType.INFORMATION);
        spam.setContentText("Produto alterado com sucesso!");
        spam.show();

        tbView.getItems().clear();
        inputQntd.setText("");
        inputPreco.setText("");
        tbView.getItems().add(ultimoProdutoPesquisado);
    }
}
