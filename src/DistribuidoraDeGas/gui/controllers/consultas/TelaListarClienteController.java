package DistribuidoraDeGas.gui.controllers.consultas;

import DistribuidoraDeGas.Main;
import DistribuidoraDeGas.gui.controllers.MenuInicialController;
import DistribuidoraDeGas.negocio.entidades.Cliente;
import DistribuidoraDeGas.negocio.entidades.Endereco;
import DistribuidoraDeGas.negocio.entidades.Gerente;
import DistribuidoraDeGas.negocio.excecoes.PessoaInexistenteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 * Essa classe faz a conexão entre a interface gráfica e a fachada.
 * @author Letícia Araújo, Izabel Vidal
 */
public class TelaListarClienteController implements Initializable {
    private Alert spam;
    @FXML
    private Pane painelListarClientes;
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField inputBuscar;
    @FXML
    private TableView<Cliente> tbCliente;
    @FXML
    private TableColumn<?, ?> tbNome;
    @FXML
    private TableColumn<?, ?> tbCpf;
    @FXML
    private TableColumn<?, ?> tbDataNasc;
    @FXML
    private TableColumn<?, ?> tbTel;
    @FXML
    private TableColumn<?, ?> tbTipo;
    @FXML
    private TableView<Endereco> tbEndereco;
    @FXML
    private TableColumn<?, ?> tbRua;
    @FXML
    private TableColumn<?, ?> tbNumero;
    @FXML
    private TableColumn<?, ?> tbBairro;
    @FXML
    private TableColumn<?, ?> tbCidade;
    @FXML
    private TableColumn<?, ?> tbEstado;
    @FXML
    private Button btnVoltar;

    public TelaListarClienteController(){
        spam = new Alert(Alert.AlertType.NONE);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
        tbNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tbCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tbDataNasc.setCellValueFactory(new PropertyValueFactory<>("Data de Nascimento"));
        tbTel.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
        tbTipo.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
        tbRua.setCellValueFactory(new PropertyValueFactory<>("Rua"));
        tbNumero.setCellValueFactory(new PropertyValueFactory<>("Numero"));
        tbBairro.setCellValueFactory(new PropertyValueFactory<>("Bairro"));
        tbCidade.setCellValueFactory(new PropertyValueFactory<>("Cidade"));
        tbEstado.setCellValueFactory(new PropertyValueFactory<>("Estado"));
    }

    @FXML
    public void buscarBtnHandler(ActionEvent event) {
        tbCliente.getItems().clear();
        tbEndereco.getItems().clear();
        if(inputBuscar.getLength()>0){
            try {
                Cliente cliente = Main.distribudora.consultarCliente(inputBuscar.getText());
                Endereco endereco = cliente.getEndereco();
                tbCliente.getItems().clear();
                tbEndereco.getItems().clear();

                tbCliente.getItems().add(cliente);
                tbEndereco.getItems().add(endereco);
            }catch (PessoaInexistenteException e){
                spam.setAlertType(Alert.AlertType.ERROR);
                spam.setContentText(e.getMessage());
                spam.show();
                tbCliente.getItems().clear();
                tbEndereco.getItems().clear();
            }
        }
    }

    @FXML
    private void voltarBtnHandler(ActionEvent event) {

        Pane listarClientes;
        try {
            listarClientes = FXMLLoader.load(getClass().getResource("../../views/consultas/TelaListar.fxml"));
            painelListarClientes.getChildren().setAll(listarClientes);

        } catch (IOException ex) {
            Logger.getLogger(MenuInicialController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
