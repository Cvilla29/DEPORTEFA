package application;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import persistencia.Noticia;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {	
	
	// Consultas
	private final String SELECT_NOTICIA = "SELECT TITULO, TEXTO, IMAGEN FROM NOTICIA";
	
	String titulo;
	String texto;
	String imagen;
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Sample.fxml"));
			Scene scene = new Scene(root,1399,920);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//primaryStage.initStyle(StageStyle.UTILITY);
			primaryStage.setTitle("BaloncestoApp");
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		launch(args);
			
	}
	
	
	//**** BASE DE DATOS ****//
	
	
	/*
	 * Método que realiza la conexión con la BBDD
	 * */
	public Connection createConnection() throws ClassNotFoundException, SQLException {
		
		Connection connection = null;
		
		try {
			//Realizamos el registro del driver y obtenemos la conexión
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/noticia?serverTimezone=UTC", "root",
					"root");
			// Nos aseguramos setAutoCommit a false que realizaremos commit solo cuando lo indiquemos
			connection.setAutoCommit(false);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw e;
		}

		return connection;
	}
	
	
	/*
	 * Método que realiza la desconexión a la conexión dada por parámetro
	 * */
	public void disconnect(Connection connection) throws SQLException {

		if (null != connection) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}
	
	
	/*
	 * Método que realiza la consulta a la tabla noticas
	 * */
	
	public List<Noticia> selectNoticia() throws ClassNotFoundException, SQLException {
		
		//datos a devolver
		List<Noticia> listaEmpleados = new ArrayList<Noticia>();
				
		//resources
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			//creamos la conexión a la base de datos
			connection = createConnection();
					
			//ejecutamos la consulta y recuperamos el resultado
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SELECT_NOTICIA);
			Noticia noticia;
			while (resultSet.next()) {
				noticia = new Noticia();
				System.out.println("---------1---------");
				titulo = noticia.setTitulo(resultSet.getString("TITULO"));
				texto = noticia.setTexto(resultSet.getString("TEXTO"));
				imagen = noticia.setImg(resultSet.getString("IMAGEN"));
				
				//añadimos cada dato a la lista final
				listaEmpleados.add(noticia);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			//cerramos todos los resources
			if (null != resultSet) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (null != statement) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				disconnect(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listaEmpleados;
		
	}
	
	
}
