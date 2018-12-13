package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Util;
import factory.JPAFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Perfil;
import model.Usuario;
import repository.UsuarioRepository;

public class LoginController extends Controller<Usuario> implements Initializable {

	@FXML
	private TextField tfUsuario;

	@FXML
	private PasswordField tfSenha;

	@FXML
	private Button btEntrar;

	@FXML
	private Button btCancelar;

	@FXML
	private Button btSobre;

	@FXML
	private Label lbSobre;


	@FXML
	void handleCancelar(ActionEvent event) {
		System.exit(-1);
	}

	@FXML
	void handleEntrar(ActionEvent event) {
		Usuario usuario = new Usuario();
		usuario.setNome("Olá:Gaspar");
		usuario.setPerfil(Perfil.ADMINISTRADOR);

		Controller.setUsuarioLogado(usuario);

		Button button = (Button) event.getSource();
		Stage stage = (Stage) button.getScene().getWindow();
		stage.close();

	}

	@FXML
	void handleSobre(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sobre");
		alert.setHeaderText("Sistema Lava Rápido v1.0");
		alert.setContentText("Para mais informações sobre esta aplicação... clique Mostrar detalhes");
		Label label = new Label("Informações sobre aplicação\r\n" + "Version 1.0.0\r\n" + "Date: 2018-11-15\r\n"
				+ "Desenvolvido por: Carlesandro Gaspar e Lucas Paixão\r\n" + "LTP II - Prof. Jânio Junior\r\n"
				+ "Sob a Orientação do Professor Jânio Junior");
		alert.getDialogPane().setExpandableContent(label);
		alert.show();
	}

	int tentativa = 3;

	@FXML
	public void acessarSistema(ActionEvent event) throws IOException, InterruptedException {

		UsuarioRepository repository = new UsuarioRepository(JPAFactory.getEntityManager());

		System.out.println("Senha encriptada: " + Util.encrypt(tfSenha.getText()));
		
		List<Usuario> usuario = repository.getLogin(tfUsuario.getText(), Util.encrypt(tfSenha.getText()));

		if (!usuario.isEmpty()) {

			for (Usuario lista : usuario) {
				Usuario usuarioteste = lista;
				Controller.setUsuarioLogado(usuarioteste);

			}
			Button button = (Button) event.getSource();
			Stage stage = (Stage) button.getScene().getWindow();
			stage.close();

		} else {
			tentativa--;

			if (tentativa != 0)
				System.out.println("Você tem mais " + tentativa + " tentativa");
			else if (tentativa == 0)
				System.exit(-1);

		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
