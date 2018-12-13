package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Perfil;
import model.Usuario;;

public class UsuarioController extends Controller<Usuario> implements Initializable {

	Usuario usuario;

	@FXML
	private TextField tfLogin;

	@FXML
	private ComboBox<Perfil> cbPerfil;

	@FXML
	private PasswordField tfPassword;

	@FXML
	private Button btIncluir;

	@FXML
	private Button btAlterar;

	@FXML
	private Button btExcluir;

	@FXML
	private Button btLimpar;

	@FXML
	private TextField tfNome;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		atualizarBotoes();
	}

	@FXML
	void handleIncluir(ActionEvent event) {
		getUsuario().setNome(tfNome.getText());
		getUsuario().setLogin(tfLogin.getText());
		getUsuario().setPassword(tfPassword.getText());

		super.save(getUsuario());
		handleLimpar(event);
	}

	@FXML
	void handleAlterar(ActionEvent event) {
		getUsuario().setNome(tfNome.getText());
		getUsuario().setLogin(tfLogin.getText());
		getUsuario().setPassword(tfPassword.getText());

		super.save(getUsuario());
		handleLimpar(event);
	}

	@FXML
	void handleExcluir(ActionEvent event) {
		super.remove(getUsuario());
		handleLimpar(event);
	}

	@FXML
	void handleLimpar(ActionEvent event) {
		usuario = null;
		tfNome.clear();
		tfLogin.clear();
		tfPassword.clear();
		atualizarBotoes();
	}

	private void atualizarBotoes() {
		btIncluir.setDisable(getUsuario().getId() != null);
		btAlterar.setDisable(getUsuario().getId() == null);
		btExcluir.setDisable(getUsuario().getId() == null);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
