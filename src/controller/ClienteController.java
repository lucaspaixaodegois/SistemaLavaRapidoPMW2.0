package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import factory.JPAFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Cliente;
import model.Telefone;
import repository.ClienteRepository;

public class ClienteController extends Controller<Cliente> implements Initializable {

	private Cliente cliente;

	@FXML
	private TextField tfCpf, tfNome, tfEndereco, tfEmail;

	@FXML
	private DatePicker dpAniversario;

	@FXML
	private Button btLimpar, btIncluir;
	@FXML
	private Button btExcluir;

	@FXML
	private Button btAlterar;

	@FXML
	private TableView<Cliente> tvClientes;

	@FXML
	private TableColumn<Cliente, Integer> tcIdClientes;

	@FXML
	private TableColumn<Cliente, String> tcCpfClientes;

	@FXML
	private TableColumn<Cliente, String> tcNomeClientes;

	@FXML
	private TableColumn<Cliente, String> tcEnderecoClientes;

	@FXML
	private TableColumn<Cliente, String> tcEmailClientes;

	// atributos do telefone
	@FXML
	private TextField tfCodigoArea;

	@FXML
	private TextField tfNumero;

	@FXML
	private TableView<Telefone> tbTelefone;

	@FXML
	private TableColumn<Telefone, String> tcCodigoArea;

	@FXML
	private TableColumn<Telefone, String> tcNumero;
	// fim dos atributos do telefone

	@FXML
	private TextField tfPesquisar;

	@FXML
	private TabPane tpAbas;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// setando o focus no text field cpf
		tfCpf.requestFocus();

		// configurando as colunas das tabelas conforme os atributos da classe Cliente
		tcIdClientes.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcCpfClientes.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tcNomeClientes.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcEnderecoClientes.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		tcEmailClientes.setCellValueFactory(new PropertyValueFactory<>("email"));
		dpAniversario.setValue(getCliente().getDataAniversario());
		// configurando as colunas das tabelas conforme os atributos da classe Telefone
		tcCodigoArea.setCellValueFactory(new PropertyValueFactory<>("codigoArea"));
		tcNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));

		// atualizando osbotoes
		atualizarBotoes();

	}

	@FXML
	void handleAdicionarTelefone(ActionEvent event) {
		Telefone tel = new Telefone();
		tel.setCodigoArea(tfCodigoArea.getText());
		tel.setNumeroCliente(tfNumero.getText());
		tel.setCliente(cliente);

		if (getCliente().getListaTelefone() == null)
			getCliente().setListaTelefone(new ArrayList<Telefone>());

		getCliente().getListaTelefone().add(tel);

		// atualizando a interface
		tbTelefone.setItems(FXCollections.observableList(getCliente().getListaTelefone()));

		// limpando os campos
		tfCodigoArea.clear();
		tfNumero.clear();
		tfCodigoArea.requestFocus();

	}

	@FXML
	void handlePesquisar(ActionEvent event) {
		ClienteRepository repository = new ClienteRepository(JPAFactory.getEntityManager());
		List<Cliente> lista = repository.getClientes(tfPesquisar.getText());

		if (lista.isEmpty()) {
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Informa��o");
			alerta.setHeaderText(null);
			alerta.setContentText("A consulta n�o retornou dados.");
			alerta.show();
		}
		tvClientes.setItems(FXCollections.observableList(lista));
	}

	@FXML
	void handleMouseClicked(MouseEvent event) {
		// verificando se eh o botao principal
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			// verificando a quantidade de cliques
			if (event.getClickCount() == 2) {
				cliente = tvClientes.getSelectionModel().getSelectedItem();
				tfCpf.setText(getCliente().getCpfCnpj());
				tfNome.setText(getCliente().getNome());
				tfEndereco.setText(getCliente().getEndereco());
				tfEmail.setText(getCliente().getEmail());
				dpAniversario.setValue(getCliente().getDataAniversario());

				// preenchendo os telefone
				tbTelefone.setItems(FXCollections.observableList(getCliente().getListaTelefone()));

				// selecionando a primeira aba
				tpAbas.getSelectionModel().select(0);

				// setando o focus no cpf
				tfCpf.requestFocus();
				atualizarBotoes();
			}
		}

	}

	@FXML
	void hadleIncluir(ActionEvent event) {
		getCliente().setCpfCnpj(tfCpf.getText());
		getCliente().setNome(tfNome.getText());
		getCliente().setEndereco(tfEndereco.getText());
		getCliente().setEmail(tfEmail.getText());
		getCliente().setDataAniversario(dpAniversario.getValue());

		super.save(getCliente());

		handleLimpar(event);
	}

	@FXML
	void handleAlterar(ActionEvent event) {
		getCliente().setCpfCnpj(tfCpf.getText());
		getCliente().setNome(tfNome.getText());
		getCliente().setEndereco(tfEndereco.getText());
		getCliente().setEmail(tfEmail.getText());
		getCliente().setDataAniversario(dpAniversario.getValue());
		
		save(getCliente());

		handleLimpar(event);
	}

	@FXML
	void handleExcluir(ActionEvent event) {
		super.remove(getCliente());
		handleLimpar(event);
	}

	@FXML
	void handleLimpar(ActionEvent event) {
		tfCpf.setText("");
		tfNome.setText("");
		tfEndereco.setText("");
		tfEmail.setText("");
		dpAniversario.setValue(null);
		// limpando as informacoes do cliente
		cliente = null;
		// setando o focus no cpf
		tfCpf.requestFocus();

		// limpando as tables
		tvClientes.getItems().clear();
		tbTelefone.getItems().clear();

		atualizarBotoes();
	}

	private void atualizarBotoes() {
		btIncluir.setDisable(getCliente().getId() != null);
		btAlterar.setDisable(getCliente().getId() == null);
		btExcluir.setDisable(getCliente().getId() == null);
	}

	public Cliente getCliente() {
		if (cliente == null)
			cliente = new Cliente();
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
