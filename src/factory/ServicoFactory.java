package factory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.Controller;
import controller.EditarServicoController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import model.Servico;

public class ServicoFactory extends Controller<Servico> implements Initializable {

	public static EditarServicoController getInstance() throws IOException {

		FXMLLoader loader = new FXMLLoader(ServicoFactory.class.getClass().getResource("/view/EditarServico.fxml"));

		Parent root = loader.load();

		EditarServicoController listagem = loader.getController();
		listagem.setParent(root);

		return listagem;

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
