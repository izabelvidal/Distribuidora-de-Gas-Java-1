package DistribuidoraDeGas.gui.controllers.venda;

import DistribuidoraDeGas.Main;
import DistribuidoraDeGas.negocio.entidades.Cliente;
import DistribuidoraDeGas.negocio.entidades.Produto;
import DistribuidoraDeGas.negocio.excecoes.*;
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
public class TelaVendaProdutosController implements Initializable {
    private double valorT;
    private Produto ultimoProdutoPesquisado;
    private Alert spam;
    private String hora;
    private String data;
    private Cliente cliente;
    @FXML
    private Pane painelVendaProdutos;
    @FXML
    private TextField inputId;
    @FXML
    private TextField inputqtd;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnVender;
    @FXML
    private TableView<Produto> tbViewCarrinho;
    @FXML
    private TableColumn<?, ?> tbProdutoCarrinho;
    @FXML
    private TableColumn<?, ?> tbMarcaCarrinho;
    @FXML
    private TableColumn<?, ?> tbQtdCarrinho;
    @FXML
    private TableColumn<?, ?> tbPrecoCarrinho;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lblValorTotal;
    @FXML
    private TableView<Produto> tbView;
    @FXML
    private TableColumn<?, ?> tbProduto;
    @FXML
    private TableColumn<?, ?> tbMarca;
    @FXML
    private TableColumn<?, ?> tbQtd;
    @FXML
    private TableColumn<?, ?> tbPreco;
    @FXML
    private TableColumn<?, ?> tbPeso;
    @FXML
    private Label lblHorario;
    @FXML
    private TextField inputCpf;

    public TelaVendaProdutosController(){
        this.valorT = 0.0;
        spam = new Alert(Alert.AlertType.NONE);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
        tbProduto.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tbMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        tbQtd.setCellValueFactory(new PropertyValueFactory<>("Quantidade"));
        tbPeso.setCellValueFactory(new PropertyValueFactory<>("Peso"));
        tbPreco.setCellValueFactory(new PropertyValueFactory<>("Preço"));

        tbProdutoCarrinho.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tbMarcaCarrinho.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        tbQtdCarrinho.setCellValueFactory(new PropertyValueFactory<>("Quantidade"));
        tbPrecoCarrinho.setCellValueFactory(new PropertyValueFactory<>("Preço"));
    }

    @FXML
    public void buscarBtnHandler(ActionEvent event) {
        tbView.getItems().removeAll(ultimoProdutoPesquisado);
        if(inputId.getText().length()>0 && inputCpf.getText().length()>0){
            try {
                ultimoProdutoPesquisado = Main.distribudora.consultarProduto(inputId.getText());
                tbView.getItems().add(ultimoProdutoPesquisado); //Insere produto na tabela de visualizacao
                btnVender.setDisable(false);
            }catch (ProdutoInexistenteException e){
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText(e.getMessage());
                a.show();
                spam.setAlertType(Alert.AlertType.ERROR);
                spam.setContentText(e.getMessage());
                spam.show();
                btnVender.setDisable(true);
            }
        }
    }

    @FXML
    public void btnVenderHandler(ActionEvent event) {
        boolean validado = validarQntd(inputqtd.getText());

        if(validado){
            int qnt = Integer.parseInt(inputqtd.getText());
            try{
                Main.distribudora.venderProduto(inputId.getText(),qnt,inputCpf.getText());
                this.valorT += ultimoProdutoPesquisado.getPreco() * qnt;
            }catch (QuantidadeInvalidaException | ProdutoInexistenteException | PessoaInexistenteException e){
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText(e.getMessage());
                a.show();
                return;
            }

            lblValorTotal.setText(String.format("Valor Totlal R$ %.2f", this.valorT));
            tbView.getItems().removeAll(ultimoProdutoPesquisado);

            tbViewCarrinho.getItems().add((new Produto(ultimoProdutoPesquisado.getNome(),ultimoProdutoPesquisado.getMarca(),
                    ultimoProdutoPesquisado.getId(), qnt, ultimoProdutoPesquisado.getPeso(), ultimoProdutoPesquisado.getPreco() * qnt)));

            btnVender.setDisable(true);
            inputId.setText("");
            inputCpf.setText("");
            inputqtd.setText("1");
        }
    }

    private boolean validarQntd(String qntd){
        try{
            int qnt = Integer.parseInt(qntd);
            if(qnt <= 0){
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Quantidade inválida");
                a.show();

                inputqtd.setText("1");
                return false;
            }
            return true;
        }catch (Exception e){
            inputqtd.setText("");
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Quantidade inválida");
            return false;
        }
    }

    void transferirInfo(String dataAgendada, String horaAgendada){
        hora = horaAgendada;
        data = dataAgendada;

        lblHorario.setText("Entrega pro dia "+data+" às "+hora+"h");
    }

    @FXML
    public void voltarBtnHandler(ActionEvent event) {
        Pane venda;
        try {
            if(valorT > 0){
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("Venda no valor total de R$"+valorT + " efetuada");
                a.show();
            }
            venda = FXMLLoader.load(getClass().getResource("../../views/venda/TelaAgendamentoEntrega.fxml"));
            painelVendaProdutos.getChildren().setAll(venda);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void buscarClienteBtnHandler(ActionEvent event) {
        if(inputCpf.getLength()>0){
            try{
                Main.distribudora.consultarCliente(inputCpf.getText());
            }catch (PessoaInexistenteException e){
                spam.setAlertType(Alert.AlertType.ERROR);
                spam.setContentText(e.getMessage());
                spam.show();
                tbView.getItems().clear();
            }
        }else {
            tbView.getItems().clear();
        }
    }
}

