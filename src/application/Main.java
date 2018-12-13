package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

	private static BorderPane root;

	public static void main(String[] args) {
		launch(args);
	}
	
	public static void trocaTela(int numero) throws IOException {
		Parent clienteView = null;
		if (numero == 1) {
			clienteView = FXMLLoader.load(Main.class.getResource("/view/CadCliente.fxml"));
			System.out.println("chamando cliente");
		} else if (numero == 2) {
			clienteView = FXMLLoader.load(Main.class.getResource("/view/CadMarcaModelo.fxml"));
			System.out.println("chamando veiculo");
		} else if (numero == 3) {
			System.out.println("chamando servico");
			clienteView = FXMLLoader.load(Main.class.getResource("/view/CadServico.fxml"));
		} else if (numero == 4) {
			clienteView = FXMLLoader.load(Main.class.getResource("/view/ConsultaCliente.fxml"));
			System.out.println("chamando Busca Cliente");
		} else if (numero == 5) {
			clienteView = FXMLLoader.load(Main.class.getResource("/view/ConsultaVeiculo.fxml"));
			System.out.println("chamando Busca Veiculo");
		} else if (numero == 6) {
			clienteView = FXMLLoader.load(Main.class.getResource("/view/FluxoServico.fxml"));
			System.out.println("chamando Fluxo Servico");
		} else if (numero == 7) {
			clienteView = FXMLLoader.load(Main.class.getResource("/view/FluxoCaixa.fxml"));
			System.out.println("chamando Fluxo Caixa");
		}

		// adicionando a tela inicial no template (parte central)
		ScrollPane scroll = (ScrollPane) root.getChildren().get(3);
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.getChildren().add(clienteView);
		scroll.setContent(vbox);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		root = FXMLLoader.load(getClass().getResource("/view/Template.fxml"));

		Parent clienteView = FXMLLoader.load(getClass().getResource("/view/CadCliente.fxml"));

		// adicionando a tela inicial no template (parte central)
		ScrollPane scroll = (ScrollPane) root.getChildren().get(3);
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.getChildren().add(clienteView);
		scroll.setContent(vbox);

		Scene scene = new Scene(root, 700, 600);

		primaryStage.setTitle("SISTEMA LAVA RÁPIDO PMW");
		primaryStage.setScene(scene);

		// Full Screen
//		primaryStage.setFullScreen(true);
		primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
		primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());

		primaryStage.show();
	}

}
