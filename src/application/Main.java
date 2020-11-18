package application;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.fxml.FXMLLoader;


public class Main extends Application {	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Sample.fxml"));
			Scene scene = new Scene(root,1067,872);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//primaryStage.initStyle(StageStyle.UTILITY);
			primaryStage.setTitle("DEPORTEFA");
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		Main main = new Main();
		
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/noticia?serverTimezone=UTC",
					"root", "root");
			connection.setAutoCommit(false);
			main.selectStatement(connection);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void disconnect(Connection connection) throws SQLException {
		try {
			if (null != connection) {
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	

	public void selectStatement(Connection connection) throws SQLException {
		String sentenciaSql = "SELECT TITULO FROM NOTICIA";
		
		Statement sentencia = null;
		ResultSet resultSet=null;
		try {
			sentencia = connection.createStatement();
			resultSet = sentencia.executeQuery(sentenciaSql);
			while(resultSet.next()) {
				MainController controller = new MainController();
				resultSet.getString("TITULO");
				System.out.println("hola");
				
				}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
			if (sentencia != null) {
				try {
					sentencia.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
		}
	}
	
	
}
