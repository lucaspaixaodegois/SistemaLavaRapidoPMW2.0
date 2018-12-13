package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import factory.JPAFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Servico;
import model.Tamanho;
import model.TipoServico;
import model.Veiculo;
import repository.ServicoRepository;

public class FluxoCaixaController extends Controller<Servico> implements Initializable {

	@FXML
	private ComboBox<TipoServico> cbTipoServico;
	@FXML
	private ComboBox<Tamanho> cbTamanho;
	@FXML
	private DatePicker dpDataInicio;

	@FXML
	private DatePicker dpDataFinal;

	@FXML
	private MenuButton mbServico;

	@FXML
	private MenuButton mbTamanho;

	@FXML
	private TableView<Servico> tvFluxoCaixa;

	@FXML
	private TableColumn<Servico, String> tcIdServico;

	@FXML
	private TableColumn<Servico, String> tcDataServico;

	@FXML
	private TableColumn<Servico, String> tcServico;

	@FXML
	private TableColumn<Veiculo, String> tcMarcaServico;

	@FXML
	private TableColumn<Veiculo, String> tcModeloServico;

	@FXML
	private TableColumn<Veiculo, String> tcTamanhoVeiculo;

	@FXML
	private TableColumn<Veiculo, String> tcPlacaServico;

	@FXML
	private TableColumn<Servico, String> tcValorServico;

	@FXML
	private TextField tfFaturamento;

	@FXML
	void handleFiltrar(ActionEvent event) {
		ServicoRepository repository = new ServicoRepository(JPAFactory.getEntityManager());
		List<Servico> lista = repository.getServico(cbTipoServico.getId());

		if (lista.isEmpty()) {
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Informação");
			alerta.setHeaderText(null);
			alerta.setContentText("A consulta não retornou dados.");
			alerta.show();
		}
		tvFluxoCaixa.setItems(FXCollections.observableList(lista));
	}

	public void handleTotalServicos() {
		ServicoRepository repository = new ServicoRepository(JPAFactory.getEntityManager());
		List<Servico> lista  = repository.getTotalServico();
		
		Double t = 0.0;
		
		for (Servico servico : lista) {
			t = t + servico.getValorTotal();
		}
		
		tfFaturamento.setText(t.toString());
	}

	@FXML
	void handleServico(ActionEvent event) {

	}

	@FXML
	void handleTamanho(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		tcIdServico.setCellValueFactory(new PropertyValueFactory<>("Id"));
		tcDataServico.setCellValueFactory(new PropertyValueFactory<>("Data"));
		tcServico.setCellValueFactory(new PropertyValueFactory<>("Servico"));
		tcMarcaServico.setCellValueFactory(new PropertyValueFactory<>("Marca"));
		tcModeloServico.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
		tcTamanhoVeiculo.setCellValueFactory(new PropertyValueFactory<>("Tamanho"));
		tcPlacaServico.setCellValueFactory(new PropertyValueFactory<>("Placa"));
		tcValorServico.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
		
		handleTotalServicos();
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
	}

}