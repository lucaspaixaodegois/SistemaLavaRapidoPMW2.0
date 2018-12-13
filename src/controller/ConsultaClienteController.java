package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import factory.ClienteFactory;
import factory.JPAFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Cliente;
import model.Telefone;
import repository.ClienteRepository;

public class ConsultaClienteController extends Controller<Cliente> implements Initializable {

	private Cliente cliente;

	@FXML
	private TextField tfPlaca;

	@FXML
	private TextField tfNomeCliente;

	@FXML
	private TableView<Cliente> tvCliente;

	@FXML
	private TableView<Telefone> tvTelefone;
	@FXML
	private TableColumn<Cliente, Integer> tcIdCliente;

	@FXML
	private TableColumn<Cliente, String> tcNomeCliente;

	@FXML
	private TableColumn<Cliente, String> tcCpfCnpjCliente;

	@FXML
	private TableColumn<Cliente, String> tcEnderecoCliente;



	@FXML
	private TableColumn<Cliente, String> tcEmailCliente;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// configurando as colunas das tabelas conforme os atributos da classe Cliente
		tcIdCliente.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcCpfCnpjCliente.setCellValueFactory(new PropertyValueFactory<>("cpfCnpj"));
		tcEnderecoCliente.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		tcEmailCliente.setCellValueFactory(new PropertyValueFactory<>("email"));
		
	}

	@FXML
	void BuscaCliente(ActionEvent event) {
		ClienteRepository repository = new ClienteRepository(JPAFactory.getEntityManager());
		List<Cliente> lista = repository.getClientes(tfNomeCliente.getText());

		if (lista.isEmpty()) {
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Informação");
			alerta.setHeaderText(null);
			alerta.setContentText("A consulta não retornou dados.");
			alerta.show();
		}
		tvCliente.setItems(FXCollections.observableList(lista));
	}

	void handleBuscaPlaca(ActionEvent event) {
		ClienteRepository repository = new ClienteRepository(JPAFactory.getEntityManager());
		List<Cliente> lista = repository.getClientes(tfNomeCliente.getText());

		if (lista.isEmpty()) {
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Informação");
			alerta.setHeaderText(null);
			alerta.setContentText("A consulta não retornou dados.");
			alerta.show();

		}
		tvCliente.setItems(FXCollections.observableList(lista));
	}

	@FXML
	void handleMouseClicked(MouseEvent event) throws IOException {
		// verificando se eh o botao principal
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			// verificando a quantidade de cliques
			if (event.getClickCount() == 2) {

				EditarClienteController listagem = ClienteFactory.getInstance();
				cliente = tvCliente.getSelectionModel().getSelectedItem();
				listagem.abrir(cliente);

			}
		}

	}

	public Cliente getCliente() {
		if (cliente == null)
			cliente = new Cliente();
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getClienteSelecionado() {
		return cliente;
	}

}
