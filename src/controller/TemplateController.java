package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Perfil;

public class TemplateController implements Initializable {
	
	public static Parent novoClienteView;
	public static Parent novoVeiculoView;
	public static Parent novoServicoView;
	public static Parent consultaClienteView;
	public static Parent consultaVeiculoView;
	public static Parent fluxoCaixaView;
	public static Parent fluxoServicoView;
	
	@FXML
    private ScrollPane scrollPane;
	
	 @FXML
	private Label labelUsuario;
	
	 @FXML
	 private Button btNovoCliente;

	    @FXML
	    private Button btNovoVeiculo;

	    @FXML
	    private Button btNovoServico;

	    @FXML
	    private Button btBuscaCliente;

	    @FXML
	    private Button btBuscaVeiculo;

	    @FXML
	    private Button btFluxoServico;

	    @FXML
	    private Button btFluxoCaixa;
	    
	 
	@FXML
	void handleNovoCliente(ActionEvent event) throws IOException {
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.getChildren().add(novoClienteView);
		scrollPane.setContent(vbox);
	}

	@FXML
	void handleNovoVeiculo(ActionEvent event) throws IOException {

		Main.trocaTela(2);
	}

	@FXML
	void handleNovoServico(ActionEvent event) throws IOException {

		Main.trocaTela(3);
	}

	@FXML
	void handleBuscaCliente(ActionEvent event) throws IOException {

		Main.trocaTela(4);
	}

	@FXML
	void handleBuscaVeiculo(ActionEvent event) throws IOException {

		Main.trocaTela(5);
	}

	@FXML
	void handleFluxoServico(ActionEvent event) throws IOException {

		Main.trocaTela(6);
	}

	@FXML
	void handleFluxoCaixa(ActionEvent event) throws IOException {

		Main.trocaTela(7);
	}
	
	 @FXML
	    void handleBloquear(ActionEvent event) {
		 abrirTelaLogin();
	    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		abrirTelaLogin();
		
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
		try {
			
			novoClienteView = FXMLLoader.load(Main.class.getResource("/view/CadCliente.fxml"));
			novoVeiculoView = FXMLLoader.load(Main.class.getResource("/view/CadVeiculo.fxml"));
			novoServicoView = FXMLLoader.load(Main.class.getResource("/view/CadServico.fxml"));
			consultaClienteView = FXMLLoader.load(Main.class.getResource("/view/ConsultaCliente.fxml"));
			consultaVeiculoView = FXMLLoader.load(Main.class.getResource("/view/ConsultaVeiculo.fxml"));
			fluxoCaixaView = FXMLLoader.load(Main.class.getResource("/view/FluxoCaixa.fxml"));
			fluxoServicoView = FXMLLoader.load(Main.class.getResource("/view/FluxoServico.fxml"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void abrirTelaLogin() {
		try {
	    	Stage stage = new Stage(StageStyle.TRANSPARENT);
	    	Parent parent = FXMLLoader.load(Main.class.getResource("/view/Login.fxml"));
	    	Scene scene = new Scene(parent, 400, 400);
	    	stage.setTitle("Login");
	    	stage.setScene(scene);
	    	stage.initModality(Modality.APPLICATION_MODAL);
	    	stage.showAndWait();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// atualizando a interface com o usuario logado
		labelUsuario.setText(Controller.getUsuarioLogado().getNome());
			
		// bloqueando botoes de conforme o perfil
		if (Controller.getUsuarioLogado().getPerfil().equals(Perfil.ADMINISTRADOR)) {
			
			btNovoCliente.setDisable(false);
			btNovoVeiculo.setDisable(false);
		} else if (Controller.getUsuarioLogado().getPerfil().equals(Perfil.CADASTRO)) {
			btFluxoCaixa.setDisable(true);
		}
		
					
	}
		
}
