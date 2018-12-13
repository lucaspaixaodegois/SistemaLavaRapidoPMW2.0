package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Servico;
import model.TipoServico;

public class FluxoCaixaController extends Controller<Servico> implements Initializable {

	@FXML
	private ComboBox<TipoServico> cbTipoServico;

	@FXML
	private DatePicker dpDataInicio;

	@FXML
	private DatePicker dpDataFinal;

	@FXML
	private MenuButton mbServico;

	@FXML
	private MenuButton mbTamanho;

	@FXML
	private TableView<?> tvFluxoCaixa;

	@FXML
	private TableColumn<?, ?> tcFluxoCaixa;

	@FXML
	private TableColumn<?, ?> tcDataFluxoCaixa;

	@FXML
	private TableColumn<?, ?> tcServicoFluxoCaixa;

	@FXML
	private TableColumn<?, ?> tcMarcaFluxoCaixa;

	@FXML
	private TableColumn<?, ?> tcModeloFluxoCaixa;

	@FXML
	private TableColumn<?, ?> tcTamanhoFluxoCaixa;

	@FXML
	private TableColumn<?, ?> tcPlacaFluxoCaixa;

	@FXML
	private TableColumn<?, ?> tcValorFluxoCaixa;

	@FXML
	private TextField tfFaturamento;

	@FXML
	void handleFiltrar(ActionEvent event) {

	}

	@FXML
	void handleServico(ActionEvent event) {

	}

	@FXML
	void handleTamanho(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
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