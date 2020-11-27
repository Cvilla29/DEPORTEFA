package application;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

public class MainController{
	
	@FXML
	private Label lblStatus;
	@FXML
	private TextField txtUser;
	@FXML
	private TextField txtPass;
	@FXML
	private TextArea txtArea;
	@FXML
	private Pane pPrincipal;
	@FXML
	private Pane pNoticias;
	@FXML
	private Pane pEquipos;
	@FXML
	private Pane pClasificacion;
	@FXML
	private Pane pVerNoticias;
	@FXML
	private Label lblTexto;
	
	Main menu = new Main();
	
	
	public void accederLogin(MouseEvent ev) throws IOException {
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		Scene scene = new Scene(root,370,280);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
	}
	
	public void login (MouseEvent ev) {
		if (txtUser.getText().equals("user") && txtPass.getText().equals("pass")) {
			lblStatus.setText("Acceso Permitido");
		} else {
			lblStatus.setText("Acceso Denegado");
		}
	}
	
	public void cerrarLogin (MouseEvent ev) {
		((Node)ev.getSource()).getScene().getWindow().hide();
	}
	
	public void noticias(MouseEvent ev) {
		if (pEquipos.isVisible()==true || pClasificacion.isVisible()==true || pVerNoticias.isVisible()==true) {
			pEquipos.setVisible(false);
			pClasificacion.setVisible(false);
			pVerNoticias.setVisible(false);
			pNoticias.setVisible(true);
		}
	}
	
	public void verNoticias(MouseEvent ev) {
		if (pEquipos.isVisible()==true || pClasificacion.isVisible()==true || pNoticias.isVisible()==true) {
			pEquipos.setVisible(false);
			pClasificacion.setVisible(false);
			pNoticias.setVisible(false);
			pVerNoticias.setVisible(true);
			
			lblTexto.setText(menu.texto);
		}
	}
	
	public void equipos(MouseEvent ev) {
		if (pNoticias.isVisible()==true || pClasificacion.isVisible()==true || pVerNoticias.isVisible()==true) {
			pNoticias.setVisible(false);
			pClasificacion.setVisible(false);
			pVerNoticias.setVisible(false);
			pEquipos.setVisible(true);
			
		}
	}
	
	public void clasificacion(MouseEvent ev) {
		if (pNoticias.isVisible()==true || pEquipos.isVisible()==true || pVerNoticias.isVisible()==true) {
			pNoticias.setVisible(false);
			pEquipos.setVisible(false);
			pVerNoticias.setVisible(false);
			pClasificacion.setVisible(true);
		}
	}


	
}
