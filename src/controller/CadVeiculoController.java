package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import factory.JPAFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Marca;
import model.Modelo;
import model.Tamanho;
import model.Veiculo;
import repository.MarcaRepository;
import repository.VeiculoRepository;

public class CadVeiculoController extends Controller<Veiculo> implements Initializable {

	private Veiculo veiculo;

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
	private Button btIncluir;

	@FXML
	private Button btAlterar;

	@FXML
	private Button btExcluir;

	@FXML
	private Button btLimpar;

	@FXML
	void handleAlterar(ActionEvent event) {

	}

	@FXML
	void handleExcluir(ActionEvent event) {

	}

	@FXML
	void handleIncluir(ActionEvent event) {

		getVeiculo().setPlaca(tfPlaca.getText());
		getVeiculo().setCor(tfCor.getText());
//		getVeiculo().setMarca(cbMarca.getValue());
		getVeiculo().setModelo(cbModelo.getValue());
		getVeiculo().setTamanho(cbTamanho.getValue());

		super.save(getVeiculo());

		handleLimpar(event);
	}

	@FXML
	void handleLimpar(ActionEvent event) {

	}

	private void atualizarBotoes() {
		btIncluir.setDisable(getVeiculo().getId() != null);
		btAlterar.setDisable(getVeiculo().getId() == null);
		btExcluir.setDisable(getVeiculo().getId() == null);
	}

	public Veiculo getVeiculo() {
		if (veiculo == null)
			veiculo = new Veiculo();
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

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
}
