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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Veiculo;
import repository.VeiculoRepository;

public class ConsultaVeiculoController extends Controller<Veiculo> implements Initializable {


	@FXML
	private TextField tfPlaca;

	@FXML
	private TextField tfNomeCliente;

	@FXML
	private TableView<Veiculo> tvVeiculo;

	@FXML
	private TableColumn<Veiculo, String> tcIdVeiculo;

	@FXML
	private TableColumn<Veiculo, String> tcNomeCliente;

	@FXML
	private TableColumn<Veiculo, String> tcPlacaVeiculo;

	@FXML
	private TableColumn<Veiculo, String> tcMarcaVeiculo;

	@FXML
	private TableColumn<Veiculo, String> tcModeloVeiculo;

	@FXML
	private TableColumn<Veiculo, String> tcCorVeiculo;

	@FXML
	private TableColumn<Veiculo, String> tcTamanhoVeiculo;

	
	@FXML
	void handleMouseClicked(ActionEvent event) {

	}
	@FXML
	void handleBuscaCliente(ActionEvent event) {

	}

	@FXML
	void handleBuscaPlaca(ActionEvent event) {
		VeiculoRepository repository = new VeiculoRepository(JPAFactory.getEntityManager());
		List<Veiculo> lista = repository.getVeiculo(tfPlaca.getText());

		if (lista.isEmpty()) {
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Informação");
			alerta.setHeaderText(null);
			alerta.setContentText("A consulta não retornou dados.");
			alerta.show();
		}
		tvVeiculo.setItems(FXCollections.observableList(lista));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// configurando as colunas das tabelas conforme os atributos da classe Cliente
		tcIdVeiculo.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcPlacaVeiculo.setCellValueFactory(new PropertyValueFactory<>("placa"));
		tcMarcaVeiculo.setCellValueFactory(new PropertyValueFactory<>("marca"));
		tcModeloVeiculo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		tcTamanhoVeiculo.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
		tcCorVeiculo.setCellValueFactory(new PropertyValueFactory<>("cor"));
		tcNomeCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));

	}

}