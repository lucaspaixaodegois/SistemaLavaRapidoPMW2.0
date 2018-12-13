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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cliente;
import model.Marca;
import model.Modelo;
import model.Tamanho;
import model.Veiculo;

public class EditarVeiculoController extends Controller<Cliente> implements Initializable {

	private Veiculo veiculo;
	private Stage stage;
	private Parent parent;

	@FXML
	private TextField tfPlaca;

	@FXML
	private ComboBox<Marca> cbMarca;
	
	@FXML
	private ComboBox<Marca> cbPlacaCliente;

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

	}

	@FXML
	void handleLimpar(ActionEvent event) {

	}

	@FXML
	void handleModelo(MouseEvent event) {

	}

	public void abrir(Veiculo veiculo) {

		setVeiculo(veiculo);
		stage = new Stage();
		Scene scene = new Scene(parent, 600, 600);
		stage.setScene(scene);
		stage.setResizable(false);
		// stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.WINDOW_MODAL);

		tfCor.setText(veiculo.getCor());
		tfPlaca.setText(veiculo.getPlaca());
		stage.show();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public void setParent(Parent parent) {
		// TODO Auto-generated method stub

	}

}
