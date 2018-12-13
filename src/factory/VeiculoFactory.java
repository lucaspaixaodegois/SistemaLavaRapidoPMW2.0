package factory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import model.Veiculo;
import controller.EditarVeiculoController;

public class VeiculoFactory extends Controller<Veiculo> implements Initializable {

	public static EditarVeiculoController getInstance() throws IOException {

		FXMLLoader loader = new FXMLLoader(ClienteFactory.class.getClass().getResource("/view/EditarVeiculo.fxml"));

		Parent root = loader.load();

		EditarVeiculoController listagem = loader.getController();
		listagem.setParent(root);

		return listagem;

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
