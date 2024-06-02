package service_home;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import login.login;

public class service_for_logout {
    public static void event_for_logout(BorderPane logout, Class<?> clazz) {
        logout.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                login.root = FXMLLoader.load(clazz.getResource("/login/login.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            login.scene = new Scene(login.root);
            login.primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            login.primaryStage.setScene(login.scene);
            login.primaryStage.setResizable(false);

            // Tính toán và đặt tọa độ cho primaryStage
            javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double screenWidth = screenBounds.getWidth();
            double screenHeight = screenBounds.getHeight();
            double windowWidth = login.primaryStage.getWidth();
            double windowHeight = login.primaryStage.getHeight();
            double x = (screenWidth - windowWidth) / 2;
            double y = (screenHeight - windowHeight) / 2;
            login.primaryStage.setX(x);
            login.primaryStage.setY(y);

            login.primaryStage.show();
        });
    }
}
