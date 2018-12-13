package factory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.Controller;
import controller.EditarClienteController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import model.Cliente;

public class ClienteFactory extends Controller<Cliente> implements Initializable {

	public static EditarClienteController getInstance() throws IOException {

		FXMLLoader loader = new FXMLLoader(ClienteFactory.class.getClass().getResource("/view/EditarCliente.fxml"));

		Parent root = loader.load();

		EditarClienteController listagem = loader.getController();
		listagem.setParent(root);

		return listagem;

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
