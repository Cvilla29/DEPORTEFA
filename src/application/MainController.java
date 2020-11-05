package application;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

import com.sun.glass.ui.MenuItem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class MainController {
	
	@FXML
	private Label lblStatus;
	@FXML
	private TextField txtUser;
	@FXML
	private TextField txtPass;
	@FXML
	private TextArea txtArea;
	@FXML
	private Pane pNoticias;
	@FXML
	private Pane pEquipos;
	@FXML
	private Pane pClasificacio;
	
	public void accederLogin(ActionEvent event) throws IOException {
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		Scene scene = new Scene(root,370,280);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void login (ActionEvent event) {
		if (txtUser.getText().equals("user") && txtPass.getText().equals("pass")) {
			lblStatus.setText("Acceso Permitido");
		} else {
			lblStatus.setText("Acceso Denegado");
		}
	}
	
	public void noticias(ActionEvent ev) {
		if (pEquipos.isVisible()==true || pClasificacion.isVisible()==true) {
			pEquipos.setVisible(false);
			pClasificacion.setVisible(false);
			pNoticias.setVisible(true);
		}
	}
	
	public void equipos(ActionEvent ev) {
		if (pNoticias.isVisible()==true || pClasificacion.isVisible()==true) {
			pNoticias.setVisible(false);
			pClasificacion.setVisible(false);
			pEquipos.setVisible(true);
		}
	}
	
	public void clasificacion(ActionEvent ev) {
		if (pNoticias.isVisible()==true || pEquipos.isVisible()==true) {
			pNoticias.setVisible(false);
			pEquipos.setVisible(false);
			pClasificacion.setVisible(true);
		}
	}
	
	public void adir(ActionEvent event) {
		txtArea.setText("Increible Prueba");
	}
}
