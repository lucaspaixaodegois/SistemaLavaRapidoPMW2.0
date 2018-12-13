package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.TextFieldFormatter;
import factory.JPAFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Cliente;
import model.Marca;
import model.Modelo;
import model.Tamanho;
import model.Telefone;
import model.Veiculo;
import repository.MarcaRepository;
import repository.VeiculoRepository;

public class CadClienteController extends Controller<Cliente> implements Initializable {

	private Cliente cliente;

	@FXML
	private Button btNovoCliente;

	@FXML
	private Button btNovoVeiculo;

	@FXML
	private Button btNovoServico;

	@FXML
	private Button btBuscaCliente;

	@FXML
	private Button btBuscaVeiculo;

	@FXML
	private Button btFluxoServico;

	@FXML
	private Button btFluxoCaixa;

	@FXML
	private TextField tfCpfCnpj;

	@FXML
	private TextField tfNomeCliente;

	@FXML
	private TextField tfEndereco;

	@FXML
	private TextField tfEmail;

	@FXML
	private Button btLimpar;

	@FXML
	private Button btExcluir;

	@FXML
	private Button btAlterar;

	@FXML
	private Button btIncluir;

	@FXML
	private DatePicker dpAniversario;

	@FXML
	private TextField tfCodigoArea;

	@FXML
	private TextField tfNumeroCliente;

	@FXML
	private TextField tfPlaca;

	@FXML
	private ComboBox<Marca> cbMarca;

	@FXML
	private ComboBox<Modelo> cbModelo;

	@FXML
	private TextField tfCor;

	@FXML
	private ComboBox<Tamanho> cbTamanho;

	@FXML
	private TableView<Veiculo> tvVeiculo;
	@FXML
	private TableView<Telefone> tvTelefone;

	@FXML
	private TableView<Cliente> tvCliente;

	@FXML
	private TableColumn<Telefone, String> tcCodigoArea;

	@FXML
	private TableColumn<Telefone, String> tcNumero;
	@FXML
	private TableColumn<Veiculo, String> tcPlaca;
	@FXML
	private TableColumn<Veiculo, String> tcMarca;
	@FXML
	private TableColumn<Veiculo, String> tcModelo;
	@FXML
	private TableColumn<Veiculo, String> tcCor;
	@FXML
	private TableColumn<Veiculo, String> tcTamanho;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Carregar combobox com as Marcas
		carregarComboBoxMarcas();
		// carregarComboBoxModelo();

		// adicionando o conteudo do combobox
		cbTamanho.getItems().addAll(Tamanho.values());
		// sobreescrevendo o m�todo que mostra o conteudo do combobox
		cbTamanho.setCellFactory(c -> new ListCell<Tamanho>() {
			@Override
			protected void updateItem(Tamanho item, boolean empty) {
				super.updateItem(item, empty);

				if (item == null || empty)
					setText(null);
				else
					setText(item.getLabel());
			}
		});

		// seobrescreendo o m�todo que mostra o conteudo selecionado
		cbTamanho.setButtonCell(new ListCell<Tamanho>() {
			@Override
			protected void updateItem(Tamanho item, boolean empty) {
				super.updateItem(item, empty);

				if (item == null || empty)
					setText(null);
				else
					setText(item.getLabel());
			}
		});

		// atualizando os botoes
		atualizarBotoes();

		atualizarBotoes();
		tcCodigoArea.setCellValueFactory(new PropertyValueFactory<>("codigoArea"));
		tcNumero.setCellValueFactory(new PropertyValueFactory<>("numeroCliente"));
		tcPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
		tcMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		tcModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		tcCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
		tcTamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
	}

	@FXML
	void handleAdicionarTelefone(ActionEvent event) {
		Telefone tel = new Telefone();
		tel.setCodigoArea(tfCodigoArea.getText());
		tel.setNumeroCliente(tfNumeroCliente.getText());
		tel.setCliente(cliente);

		if (getCliente().getListaTelefone() == null)
			getCliente().setListaTelefone(new ArrayList<Telefone>());

		getCliente().getListaTelefone().add(tel);

		// atualizando a interface
		tvTelefone.setItems(FXCollections.observableList(getCliente().getListaTelefone()));
		// limpando os campos
		tfCodigoArea.clear();
		tfNumeroCliente.clear();
		tfCodigoArea.requestFocus();
	}

	@FXML
	private void tfTelefoneReleased() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("#####-####");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(tfNumeroCliente);
		tff.formatter();
	}

	@FXML
	private void tfDDDReleased() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("##");
		tff.setCaracteresValidos("123456789");
		tff.setTf(tfCodigoArea);
		tff.formatter();
	}

	@FXML
	private void tfPlacaReleased() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("###-####");
		tff.setCaracteresValidos("");
		tff.setTf(tfPlaca);
		tff.formatter();
	}

	@FXML
	void handleAdicionarVeiculo(ActionEvent event) {
		Veiculo car = new Veiculo();
		car.setPlaca(tfPlaca.getText());
		car.setCor(tfCor.getText());
		car.setMarca(cbMarca.getValue());	
//		getVeiculo().setMarca(cbMarca.getValue());
		car.setModelo(cbModelo.getValue());
		car.setTamanho(cbTamanho.getValue());
		car.setCliente(cliente);

		if (getCliente().getListaVeiculo() == null)
			getCliente().setListaVeiculo(new ArrayList<Veiculo>());

		getCliente().getListaVeiculo().add(car);

		// atualizando a interface
		tvVeiculo.setItems(FXCollections.observableList(getCliente().getListaVeiculo()));
		// limpando os campos
		tfPlaca.clear();
		tfCor.clear();
	}

	@FXML
	void handleAlterar(ActionEvent event) {
		getCliente().setCpfCnpj(tfCpfCnpj.getText());
		getCliente().setNome(tfNomeCliente.getText());
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
		Alert alerta = new Alert(AlertType.INFORMATION);
		alerta.setTitle("Informação");
		alerta.setHeaderText(null);
		alerta.setContentText("Cliente excluido com sucessos.");
		alerta.show();
	}

	@FXML
	void handleIncluir(ActionEvent event) {
		getCliente().setCpfCnpj(tfCpfCnpj.getText());
		getCliente().setNome(tfNomeCliente.getText());
		getCliente().setEndereco(tfEndereco.getText());
		getCliente().setEmail(tfEmail.getText());
		getCliente().setDataAniversario(dpAniversario.getValue());

		getCliente().setPlaca(tfPlaca.getText());
		getCliente().setCor(tfCor.getText());
		//getVeiculo().setMarca(cbMarca.getValue());
		getCliente().setModelo(cbModelo.getValue());
		getCliente().setTamanho(cbTamanho.getValue());

		super.save(getCliente());
		Alert alerta = new Alert(AlertType.INFORMATION);
		alerta.setTitle("Informação");
		alerta.setHeaderText(null);
		alerta.setContentText("Cliente incluido com sucessos.");
		alerta.show();
		handleLimpar(event);
	}

	@FXML
	void handleLimpar(ActionEvent event) {
		tfCpfCnpj.setText("");
		tfNomeCliente.setText("");
		tfEndereco.setText("");
		tfEmail.setText("");
		dpAniversario.setValue(null);
		// limpando as informacoes do cliente
		cliente = null;
		// setando o focus no cpf
		tfCpfCnpj.requestFocus();

		// limpando as tables
//		tvCliente.getItems().clear();
		tvTelefone.getItems().clear();

		atualizarBotoes();
	}

	private void atualizarBotoes() {
		btIncluir.setDisable(getCliente().getId() != null);
		btAlterar.setDisable(getCliente().getId() == null);
		btExcluir.setDisable(getCliente().getId() == null);
	}

	public void carregarComboBoxMarcas() {
		MarcaRepository repository = new MarcaRepository(JPAFactory.getEntityManager());
		List<Marca> lista = repository.getListMarcas();
		cbMarca.setItems(FXCollections.observableList(lista));
	}

	@FXML
	void handleModelo(MouseEvent event) {
		carregarComboBoxModelo(cbMarca.getValue());
	}

	public void carregarComboBoxModelo(Marca marca) {
		VeiculoRepository repository = new VeiculoRepository(JPAFactory.getEntityManager());
		List<Modelo> lista = repository.getModeloVeiculo(marca);
		cbModelo.setItems(FXCollections.observableList(lista));
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
