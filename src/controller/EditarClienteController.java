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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cliente;
import model.Marca;
import model.Modelo;
import model.Tamanho;
import model.Telefone;
import model.Veiculo;
import repository.MarcaRepository;
import repository.VeiculoRepository;

public class EditarClienteController extends Controller<Cliente> implements Initializable {

	private Cliente cliente;
	private Stage stage;
	private Parent parent;

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
	private TableView<Telefone> tvTelefone;
	@FXML
	private TableView<Veiculo> tvVeiculo;
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

	public void abrir(Cliente cliente) {

		setCliente(cliente);

		stage = new Stage();
		Scene scene = new Scene(parent, 800, 650);
		stage.setScene(scene);
		stage.setResizable(false);
		// stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.WINDOW_MODAL);

		tfNomeCliente.setText(cliente.getNome());
		tfCpfCnpj.setText(cliente.getCpfCnpj());
		tfEndereco.setText(cliente.getEndereco());
		tfEmail.setText(cliente.getEmail());
		dpAniversario.setValue(cliente.getDataAniversario());
		
		tvTelefone.setItems(FXCollections.observableList(getCliente().getListaTelefone()));
		
		tvVeiculo.setItems(FXCollections.observableList(getCliente().getListaVeiculo()));
		
		atualizarBotoes();
		stage.show();

	}

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
		tcCodigoArea.setCellValueFactory(new PropertyValueFactory<>("codigoArea"));
		tcNumero.setCellValueFactory(new PropertyValueFactory<>("numeroCliente"));
		tcPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
		tcMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		tcCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
		tcTamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
		tcModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));



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
	void handleAdicionarVeiculo(ActionEvent event) {
		Veiculo car = new Veiculo();
		car.setPlaca(tfPlaca.getText());
		car.setCor(tfCor.getText());
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
		getCliente().setPlaca(tfPlaca.getText());
		getCliente().setCor(tfCor.getText());
//		getVeiculo().setMarca(cbMarca.getValue());
		getCliente().setModelo(cbModelo.getValue());
		getCliente().setTamanho(cbTamanho.getValue());
		save(getCliente());
		handleLimpar(event);
	}

	@FXML
	void handleExcluir(ActionEvent event) {
		super.remove(getCliente());
		handleLimpar(event);
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
//		getVeiculo().setMarca(cbMarca.getValue());
		getCliente().setModelo(cbModelo.getValue());
		getCliente().setTamanho(cbTamanho.getValue());

		super.save(getCliente());

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
		// limpando as tables
//		tvCliente.getItems().clear();
		tvTelefone.getItems().clear();
		tvVeiculo.getItems().clear();
		tfCor.setText("");
		tfPlaca.setText("");
		cbMarca.setValue(null);
		cbModelo.setValue(null);
		cbTamanho.setValue(null);

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
	
	public Parent getParent() {
		return parent;

	}

	public void setParent(Parent parent) {
		this.parent = parent;

	}


}
