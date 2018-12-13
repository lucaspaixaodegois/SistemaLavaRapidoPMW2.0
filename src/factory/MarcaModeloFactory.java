package factory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.Controller;
import controller.EditarVeiculoController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import model.Modelo;

public class MarcaModeloFactory extends Controller<Modelo> implements Initializable {

	public static <CadMarcaModelo> EditarVeiculoController getInstance() throws IOException {

		FXMLLoader loader = new FXMLLoader(MarcaModeloFactory.class.getClass().getResource("/view/CadMarcaModelo.fxml"));

		Parent root = loader.load();

		CadMarcaModelo listagem = loader.getController();
		((EditarVeiculoController) listagem).setParent(root);

		return (EditarVeiculoController) listagem;

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
