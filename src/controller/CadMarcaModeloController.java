package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Marca;
import model.Modelo;
import model.Veiculo;

public class CadMarcaModeloController extends Controller<Veiculo> implements Initializable {
	private Marca marca;
	private Modelo modelo;
	private Veiculo veiculo;
	private Stage stage;
	private Parent parent;
	
	@FXML
	private Button btLimpar;

	@FXML
	private Button btExcluir;

	@FXML
	private Button btAlterar;

	@FXML
	private Button btIncluir;

	@FXML
	private TextField tfModelo;

	@FXML
	private TextField tfMarca;
	@FXML
    private TableView<Veiculo> tvMarcaModelo;
	@FXML
	private TableColumn<Veiculo, String> tcMarca;
	@FXML
	private TableColumn<Veiculo, String> tcModelo;

	
	@FXML
	void handleAdicionarMarcaModelo(ActionEvent event) {
//		Veiculo NovaMarcaModelo = new Veiculo();
//		NovaMarcaModelo.setMarca(tfMarca.getText());
//		NovaMarcaModelo.setModelo(tfModelo.getText());
//		
//		if (getCliente().getListaVeiculo() == null)
//			getCliente().setListaVeiculo(new ArrayList<Veiculo>());
//
//		getCliente().getListaVeiculo().add(car);
//
//		// atualizando a interface
//		tvVeiculo.setItems(FXCollections.observableList(getCliente().getListaVeiculo()));
//		
//		// limpando os campos
//		tfModelo.clear();
//		tfMarca.clear();
//		
//		//inicia o curso no campo marca
//		tfMarca.requestFocus();
	}
	@FXML
	void handleAlterar(ActionEvent event) {

	}

	@FXML
	void handleExcluir(ActionEvent event) {

	}

	@FXML
	void handleIncluir(ActionEvent event) {

	}

	@FXML
	void handleLimpar(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	}
	public Parent getParent() {
		return parent;

	}

	public void setParent(Parent parent) {
		this.parent = parent;

	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public Veiculo getVeiculo() {
		if (veiculo == null)
			veiculo = new Veiculo();
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

}