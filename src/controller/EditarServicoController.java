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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cliente;
import model.FormaPagamento;
import model.Servico;
import model.Status;
import model.TipoServico;
import model.Veiculo;
import repository.ServicoRepository;
import repository.VeiculoRepository;

public class EditarServicoController extends Controller<Servico> implements Initializable {

	private Servico servico;
	private Stage stage;
	private Parent parent;

	@FXML
	private ComboBox<Cliente> cbCliente;

	@FXML
	private ComboBox<Veiculo> cbPlacaCliente;

	@FXML
	private TextField tfValorTotal;

	@FXML
	private TextArea taArea;

	@FXML
	private Button btLimpar;

	@FXML
	private Button btExcluir;

	@FXML
	private Button btAlterar;

	@FXML
	private Button btIncluir;

	@FXML
	private ComboBox<Status> cbStatus;
	@FXML
	private ComboBox<TipoServico> cbTipoServico;

	@FXML
	private DatePicker dpServico;

	@FXML
	private TextField tfEntrada;
	@FXML
	private TextField tfSaida;
	@FXML
	private ComboBox<FormaPagamento> cbFormaPagamento;

	public void abrir(Servico servico) {

		setServico(servico);
		stage = new Stage();
		Scene scene = new Scene(parent, 800, 500);
		stage.setScene(scene);
		stage.setResizable(false);
		// stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.WINDOW_MODAL);

		tfValorTotal.setText(servico.getValorTotal());
		taArea.setText(servico.getTaArea());
		cbTipoServico.setValue(servico.getTiposervico());
		cbFormaPagamento.setValue(servico.getFormapagamento());
		cbCliente.setValue(servico.getCliente());

		dpServico.setValue(servico.getDataServico());
		tfSaida.setText(servico.getSaida());
		tfEntrada.setText(servico.getEntrada());
		atualizarBotoes();
		stage.show();
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	private void atualizarBotoes() {
		btIncluir.setDisable(getServico().getId() != null);
		btAlterar.setDisable(getServico().getId() == null);
		btExcluir.setDisable(getServico().getId() == null);
	}

	@FXML
	void handleLimpar(ActionEvent event) {
		tfSaida.setText("");
		tfEntrada.setText("");
		taArea.setText("");
		dpServico.setValue(null);
		cbCliente.setValue(null);
		cbFormaPagamento.setValue(null);
		cbTipoServico.setValue(null);
		servico = null;
		tfValorTotal.setText("");
		cbStatus.setValue(null);
		atualizarBotoes();
	}

	@FXML
	void handleIncluir(ActionEvent event) {

		getServico().setCliente(cbCliente.getValue());
		getServico().setTiposervico(cbTipoServico.getValue());
		getServico().setDataServico(dpServico.getValue());
		getServico().setValorTotal(tfValorTotal.getText());
		getServico().setFormapagamento(cbFormaPagamento.getValue());
		getServico().setTaArea(taArea.getText());
		getServico().setEntrada(tfEntrada.getText());
		getServico().setSaida(tfSaida.getText());
		getServico().setStatus(cbStatus.getValue());

		System.out.println("teste");
		super.save(getServico());

		handleLimpar(event);
	}

	@FXML
	void handleExcluir(ActionEvent event) {
		super.remove(getServico());
		handleLimpar(event);
	}

	@FXML
	void handlePlaca(MouseEvent event) {
		carregarComboBoxPlaca(cbCliente.getValue());
	}

	public void carregarComboBoxPlaca(Cliente cliente) {
		VeiculoRepository repository = new VeiculoRepository(JPAFactory.getEntityManager());
		List<Veiculo> lista = repository.getPlacas(cliente);
		cbPlacaCliente.setItems(FXCollections.observableList(lista));
	}

	@FXML
	void handleAlterar(ActionEvent event) {

		getServico().setCliente(cbCliente.getValue());
		getServico().setTiposervico(cbTipoServico.getValue());
		getServico().setDataServico(dpServico.getValue());
		getServico().setValorTotal(tfValorTotal.getText());
		getServico().setFormapagamento(cbFormaPagamento.getValue());
		getServico().setTaArea(taArea.getText());
		getServico().setEntrada(tfEntrada.getText());
		getServico().setSaida(tfSaida.getText());
		getServico().setStatus(cbStatus.getValue());

		ServicoRepository repository = new ServicoRepository(JPAFactory.getEntityManager());

		super.save(getServico());
		handleLimpar(event);
	}

	@FXML
	private void tfValorTotalReleased() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("##.##");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(tfValorTotal);
		tff.formatter();
	}

	@FXML
	private void tfHoraEntradaReleased() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("##:##");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(tfEntrada);
		tff.formatter();
	}

	@FXML
	private void tfHoraSaidaReleased() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("##:##");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(tfSaida);
		tff.formatter();
	}

	public Servico getServico() {
		if (servico == null)
			servico = new Servico();
		return servico;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		atualizarBotoes();

		//cbCliente.setDisable(true);

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

//adicionando o conteudo do combobox
		cbStatus.getItems().addAll(Status.values());
//sobreescrevendo o método que mostra o conteudo do combobox
		cbStatus.setCellFactory(c -> new ListCell<Status>() {
			@Override
			protected void updateItem(Status item, boolean empty) {
				super.updateItem(item, empty);

				if (item == null || empty)
					setText(null);
				else
					setText(item.getLabel());
			}
		});
//seobrescreendo o método que mostra o conteudo selecionado
		cbStatus.setButtonCell(new ListCell<Status>() {
			@Override
			protected void updateItem(Status item, boolean empty) {
				super.updateItem(item, empty);

				if (item == null || empty)
					setText(null);
				else
					setText(item.getLabel());
			}
		});

	}

//	// atualizando a interface com o nome do cliente
//			nomeCliente.setText(Controller.getUsuarioLogado().getNome());

	public void setParent(Parent parent) {
		this.parent = parent;

	}
}
