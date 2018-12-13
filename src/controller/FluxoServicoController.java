package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import factory.JPAFactory;
import factory.ServicoFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Cliente;
import model.Servico;
import model.Telefone;
import model.TipoServico;
import model.Veiculo;
import repository.ServicoRepository;

public class FluxoServicoController extends Controller<Cliente> implements Initializable {

	private Servico servico;

	@FXML
	private TextField tfPlaca;

	@FXML
	private TableView<Servico> tvServico;

	@FXML
	private TableColumn<Servico, String> tcIdServico;

	@FXML
	private TableColumn<Servico, String> tcNomeCliente;

	@FXML
	private TableColumn<Servico, String> tcDddServico;

	@FXML
	private TableColumn<Telefone, String> tcNumeroServico;

	@FXML
	private TableColumn<Veiculo, String> tcPlacaServico;

	@FXML
	private TableColumn<Veiculo, String> tcMarcaServico;

	@FXML
	private TableColumn<Veiculo, String> tcModeloServico;

	@FXML
	private TableColumn<Servico, String> tcEntradaServico;

	@FXML
	private TableColumn<Servico, String> tcSaidaServico;

	@FXML
	private TableColumn<Servico, String> tcServico;

	@FXML
	private TableColumn<Servico, String> tcValorServico;

	@FXML
	private TableColumn<Servico, String> tcStatusServico;

	@FXML
	private DatePicker dpDataInicio;

	@FXML
	private DatePicker dpDataFinal;

	@FXML
	private ComboBox<TipoServico> cbTipoServico;

	@FXML
	private MenuButton mbServico;

	@FXML
	void handleBuscar(ActionEvent event) {

		ServicoRepository repository = new ServicoRepository(JPAFactory.getEntityManager());
		List<Servico> lista = repository.getServico(tfPlaca.getText());

		if (lista.isEmpty()) {
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Informação");
			alerta.setHeaderText(null);
			alerta.setContentText("A consulta não retornou dados.");
			alerta.show();
		}
		tvServico.setItems(FXCollections.observableList(lista));
	}

	@FXML
	void handleServico(ActionEvent event) {

	}

	@FXML
	void handleMouseClicked(MouseEvent event) throws IOException {
		// verificando se eh o botao principal
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			// verificando a quantidade de cliques
			if (event.getClickCount() == 2) {

				EditarServicoController listagem = ServicoFactory.getInstance();
				servico = tvServico.getSelectionModel().getSelectedItem();
				listagem.abrir(servico);
			}
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		tcIdServico.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcNomeCliente.setCellValueFactory(new PropertyValueFactory<>("cliente.nome"));
		tcDddServico.setCellValueFactory(new PropertyValueFactory<>("ddd"));
		tcNumeroServico.setCellValueFactory(new PropertyValueFactory<>("numero"));
		tcPlacaServico.setCellValueFactory(new PropertyValueFactory<>("placa"));
		tcMarcaServico.setCellValueFactory(new PropertyValueFactory<>("marca"));
		tcModeloServico.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		tcEntradaServico.setCellValueFactory(new PropertyValueFactory<>("entrada"));
		tcSaidaServico.setCellValueFactory(new PropertyValueFactory<>("saida"));
		tcServico.setCellValueFactory(new PropertyValueFactory<>("tiposervico"));
		tcStatusServico.setCellValueFactory(new PropertyValueFactory<>("status"));
		tcValorServico.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
		
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
