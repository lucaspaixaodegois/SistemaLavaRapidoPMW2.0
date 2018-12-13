package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.TextFieldFormatter;
import factory.JPAFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Cliente;
import model.FormaPagamento;
import model.Servico;
import model.TipoServico;
import model.Veiculo;
import repository.ClienteRepository;
import repository.VeiculoRepository;

public class CadServicoController extends Controller<Servico> implements Initializable {

	private Servico servico;

	@FXML
	private ComboBox<Cliente> cbCliente;

	@FXML
	private ComboBox<Veiculo> cbPlacaCliente;

	@FXML
	private TextField tfValorTotal;

	@FXML
	private Button btLimpar;

	@FXML
	private Button btExcluir;

	@FXML
	private Button btAlterar;

	@FXML
	private Button btIncluir;

	@FXML
	private ComboBox<TipoServico> cbTipoServico;

	@FXML
	private DatePicker dpServico;

	@FXML
	private ComboBox<FormaPagamento> cbFormaPagamento;

	@FXML
	private TextArea taArea;

	@FXML
	private void tfValorTotalReleased() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("##.##");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(tfValorTotal);
		tff.formatter();
	}

	@FXML
	void handleAlterar(ActionEvent event) {

	}

	@FXML
	void handleExcluir(ActionEvent event) {

	}

	@FXML
	void handleIncluir(ActionEvent event) {

		getServico().setCliente(cbCliente.getValue());
		getServico().setTiposervico(cbTipoServico.getValue());
		getServico().setDataServico(dpServico.getValue());
		getServico().setValorTotal(Double.parseDouble(tfValorTotal.getText()));
		getServico().setFormapagamento(cbFormaPagamento.getValue());
		getServico().setTaArea(taArea.getText());
		super.save(getServico());

		handleLimpar(event);
	}

	@FXML
	void handleLimpar(ActionEvent event) {
		cbPlacaCliente.setValue(null);
		taArea.setText("");
		dpServico.setValue(null);
		cbCliente.setValue(null);
		cbFormaPagamento.setValue(null);
		cbTipoServico.setValue(null);
		servico = null;
		tfValorTotal.setText("");
		atualizarBotoes();
	}

	private void atualizarBotoes() {
		btIncluir.setDisable(getServico().getId() != null);
		btAlterar.setDisable(getServico().getId() == null);
		btExcluir.setDisable(getServico().getId() == null);
	}

	public Servico getServico() {
		if (servico == null)
			servico = new Servico();
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public void carregarComboBoxClientes() {
		ClienteRepository repository = new ClienteRepository(JPAFactory.getEntityManager());
		List<Cliente> lista = repository.getClientes("");

		cbCliente.setItems(FXCollections.observableList(lista));
	}

	@FXML
	void handlePlaca(MouseEvent event) {
		carregarComboBoxPlaca(cbCliente.getValue());
	}

	public void carregarComboBoxPlaca(Cliente cliente) {
		VeiculoRepository repository = new VeiculoRepository(JPAFactory.getEntityManager());
		List<Veiculo> listaPlaca = repository.getPlacas(cliente);
		cbPlacaCliente.setItems(FXCollections.observableList(listaPlaca));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		atualizarBotoes();
		// adicionando o conteudo do combobox
		cbTipoServico.getItems().addAll(TipoServico.values());
		// sobreescrevendo o método que mostra o conteudo do combobox
		cbTipoServico.setCellFactory(c -> new ListCell<TipoServico>() {
			@Override
			protected void updateItem(TipoServico item, boolean empty) {
				super.updateItem(item, empty);

				if (item == null || empty)
					setText(null);
				else
					setText(item.getLabel());
			}
		});
		// seobrescreendo o método que mostra o conteudo selecionado
		cbTipoServico.setButtonCell(new ListCell<TipoServico>() {
			@Override
			protected void updateItem(TipoServico item, boolean empty) {
				super.updateItem(item, empty);

				if (item == null || empty)
					setText(null);
				else
					setText(item.getLabel());
			}
		});

		// adicionando o conteudo do combobox
		cbFormaPagamento.getItems().addAll(FormaPagamento.values());
		// sobreescrevendo o método que mostra o conteudo do combobox
		cbFormaPagamento.setCellFactory(c -> new ListCell<FormaPagamento>() {
			@Override
			protected void updateItem(FormaPagamento item, boolean empty) {
				super.updateItem(item, empty);

				if (item == null || empty)
					setText(null);
				else
					setText(item.getLabel());
			}
		});
		// seobrescreendo o método que mostra o conteudo selecionado
		cbFormaPagamento.setButtonCell(new ListCell<FormaPagamento>() {
			@Override
			protected void updateItem(FormaPagamento item, boolean empty) {
				super.updateItem(item, empty);

				if (item == null || empty)
					setText(null);
				else
					setText(item.getLabel());
			}
		});

		carregarComboBoxClientes();
	}
}
