package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cliente;
import model.FormaPagamento;
import model.Servico;
import model.TipoServico;
import model.Veiculo;

public class EditarServicoController extends Controller<Servico> implements Initializable {

	private Servico servico;
	private Stage stage;
	private Parent parent;

	@FXML
	private ComboBox<Cliente> cbCliente;

	@FXML
	private ComboBox<Cliente> cbPlacaCliente;

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
	private ComboBox<TipoServico> cbTipoServico;

	@FXML
	private DatePicker dpServico;

	@FXML
	private ComboBox<FormaPagamento> cbFormaPagamento;

	public void abrir(Servico servico) {

		setServico(servico);

		stage = new Stage();
		Scene scene = new Scene(parent, 800, 800);
		stage.setScene(scene);
		stage.setResizable(false);
		// stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.WINDOW_MODAL);

		// tfPlaca.setText(servico.getPlaca());
		tfValorTotal.setText(servico.getValorTotal());
		taArea.setText(servico.getTaArea());
		cbTipoServico.setValue(servico.getTiposervico());
		cbFormaPagamento.setValue(servico.getFormapagamento());
		cbCliente.setValue(servico.getCliente());

		dpServico.setValue(servico.getDataServico());

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
		// tfPlaca.setText("");
		taArea.setText("");
		dpServico.setValue(null);
		cbCliente.setValue(null);
		cbFormaPagamento.setValue(null);
		cbTipoServico.setValue(null);
		servico = null;
		tfValorTotal.setText("");
		atualizarBotoes();
	}

	@FXML
	void handleIncluir(ActionEvent event) {

		getServico().setCliente(cbCliente.getValue());
		// getServico().setPlaca(tfPlaca.getText());
		getServico().setTiposervico(cbTipoServico.getValue());
		getServico().setDataServico(dpServico.getValue());
		getServico().setValorTotal(tfValorTotal.getText());
		getServico().setFormapagamento(cbFormaPagamento.getValue());
		getServico().setTaArea(taArea.getText());

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
	void handleAlterar(ActionEvent event) {
		getServico().setCliente(cbCliente.getValue());
		// getServico().setPlaca(tfPlaca.getText());
		getServico().setTiposervico(cbTipoServico.getValue());
		getServico().setDataServico(dpServico.getValue());
		getServico().setValorTotal(tfValorTotal.getText());
		getServico().setFormapagamento(cbFormaPagamento.getValue());
		getServico().setTaArea(taArea.getText());

		super.save(getServico());
		handleLimpar(event);
	}

	public Servico getServico() {
		if (servico == null)
			servico = new Servico();
		return servico;
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
//		// seobrescreendo o método que mostra o conteudo selecionado
//		cbTipoServico.setButtonCell(new ListCell<TipoServico>() {
//			@Override
//			protected void updateItem(TipoServico item, boolean empty) {
//				super.updateItem(item, empty);
//
//				if (item == null || empty)
//					setText(null);
//				else
//					setText(item.getLabel());
//			}
//		});

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

		// TODO Auto-generated method stub
		// adicionando o conteudo do combobox
//				cbFormaPagamento.getItems().addAll(FormaPagamento.values());
//				// sobreescrevendo o método que mostra o conteudo do combobox
//				cbFormaPagamento.setCellFactory(c -> new ListCell<FormaPagamento>() {
//					@Override
//					protected void updateItem(FormaPagamento item, boolean empty) {
//						super.updateItem(item, empty);
//
//						if (item == null || empty)
//							setText(null);
//						else
//							setText(item.getLabel());
//					}
//				});
//				// seobrescreendo o método que mostra o conteudo selecionado
//				cbFormaPagamento.setButtonCell(new ListCell<FormaPagamento>() {
//					@Override
//					protected void updateItem(FormaPagamento item, boolean empty) {
//						super.updateItem(item, empty);
//
//						if (item == null || empty)
//							setText(null);
//						else
//							setText(item.getLabel());
//					}
//				});

		// final ToggleGroup group = new ToggleGroup();

//				rbEmEspera.setToggleGroup(group);
//				rbEmEspera.setSelected(true);
//				rbLavando.setToggleGroup(group);
//				rbConcluido.setToggleGroup(group);
//				

		/*
		 * group = new ToggleGroup();
		 * 
		 * rbEmEspera.setToggleGroup(group); rbLavando.setToggleGroup(group);
		 * rbConcluido.setToggleGroup(group);
		 * 
		 * String output = "";
		 * 
		 * if (getServico().getStatus().equals("Em espera")) {
		 * group.selectToggle(rbEmEspera);
		 * 
		 * 
		 * 
		 * }
		 * 
		 * else if (getServico().getStatus().equals("Lavando")) {
		 * group.selectToggle(rbLavando); }
		 * 
		 * else if(getServico().getStatus().equals("Concluido")) {
		 * group.selectToggle(rbConcluido); }
		 */

	}

	public void setParent(Parent parent) {
		this.parent = parent;

	}
}
