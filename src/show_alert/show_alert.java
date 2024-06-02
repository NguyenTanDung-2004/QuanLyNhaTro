package show_alert;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service_login.function_for_control;

public class show_alert extends Application{
	public static void show(String title, String header, String content) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Image image = new Image(show_alert.class.getResourceAsStream("/img/img_user/logo.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50); // Đặt chiều rộng của hình ảnh
        imageView.setFitHeight(50); // Đặt chiều cao của hình ảnh

        // Đặt hình ảnh cho cảnh báo
        alert.setGraphic(imageView);
        if (alert.showAndWait().get() == ButtonType.CLOSE) {
        } else {
        }
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/show_alert/alert_add_room.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void show() {
        Stage primaryStage = new Stage();
        try {
            Parent root = FXMLLoader.load(show_alert.class.getResource("/show_alert/alert_add_room.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
	
}
