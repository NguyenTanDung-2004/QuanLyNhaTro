package login;
	
import database.connect_database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class login extends Application {
	public static String args[];
	public static Parent root;
	public static Scene scene;
	public static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			root = FXMLLoader.load(getClass().getResource("/login/login.fxml"));
			scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch();
	}
	@Override
	public void init() throws Exception {
		connect_database obj = new connect_database();
	}
}
