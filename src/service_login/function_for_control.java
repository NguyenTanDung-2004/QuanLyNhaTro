package service_login;

import java.io.IOException;  

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import login.login;

public class function_for_control{
	public static void turn_off_all_form(BorderPane obj1, BorderPane obj2, BorderPane obj3) { // tắt toàn bộ các form trong login.
		obj1.setVisible(false);
		obj2.setVisible(false);
		obj3.setVisible(false);
	}
	public static void turn_on_1_form(BorderPane obj){
		obj.setVisible(true);
	}
	public void event_for_login_in_login(Button button , TextField email, TextField password) throws IOException {
		button.setOnAction(event -> {
            button.setText("Processing...");
            // Chạy mã trong một luồng mới
            new Thread(() -> {
                if (service_login.check_all(email, password) == true) {
                    Platform.runLater(() -> {
                    	button.setText("Processing...");
                    	try {
							login.root = FXMLLoader.load(getClass().getResource("/home/home.fxml"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
            		    login.scene = new Scene(login.root);
            		    login.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
            		    login.primaryStage.setScene(login.scene);
            		    login.primaryStage.setResizable(false);

            		    // Lấy kích thước màn hình
            		    javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            		    double screenWidth = screenBounds.getWidth();
            		    double screenHeight = screenBounds.getHeight();

            		    // Lấy kích thước cửa sổ
            		    double windowWidth = login.primaryStage.getWidth();
            		    double windowHeight = login.primaryStage.getHeight();

            		    // Tính toán tọa độ mới để đặt primaryStage ở giữa màn hình
            		    double x = (screenWidth - windowWidth) / 2;
            		    double y = (screenHeight - windowHeight) / 2;

            		    // Đặt tọa độ cho primaryStage
            		    login.primaryStage.setX(x);
            		    login.primaryStage.setY(y);

            		    login.primaryStage.show();
                    });
                } else {
                    Platform.runLater(() -> {
            			show_alert("Login Failed!", "Login Failed!", "Please check your information again.");
            			button.setText("Login Now");
                    });
                }
            }).start();
        });
	}
	public static synchronized void event_for_signin_in_signup(Button button, TextField email, TextField password, TextField confirm, TextField citizen_id) {
		button.setOnAction(event -> {
            button.setText("Processing...");

            // Chạy mã trong một luồng mới
            new Thread(() -> {
                if (service_signup.check_all(email, password, confirm, citizen_id)) {
                    Platform.runLater(() -> {
                        function_for_control.show_alert("Signup completely!", "Signup completetly", "Now you can login into this app.");
                        button.setText("Sign Up");
                    });
                } else {
                    Platform.runLater(() -> {
                        function_for_control.show_alert("Signup Failed!", "Signup Failed!","Please check your infomation: email is not exist, password is not same confirm,...");
                        button.setText("Sign Up");
                    });
                }
            }).start();
        });
	}
	public static void event_for_confirm_password_in_forgot(Button button, TextField email, TextField new_pass, TextField confirm_pass, TextField code) {
		button.setOnAction(event -> {
            button.setText("Processing...");

            // Chạy mã trong một luồng mới
            new Thread(() -> {
                if (service_forgot.check_all(email, new_pass, confirm_pass, code)) {
                    Platform.runLater(() -> {
                        function_for_control.show_alert("Reset Completely!", "Reset Completely", "Now you can use new password to login into this app.");
                        button.setText("Confirm new password");
                    });
                } else {
                    Platform.runLater(() -> {
                        function_for_control.show_alert("Reset Failed!", "Reset Failed!","Please check your infomation: email is not exist, password is not same confirm,...");
                        button.setText("Confirm new password");
                    });
                }
            }).start();
        });
	}
	public static void event_for_send_code_in_forgot(Button button, TextField email) {
		button.setOnAction(event -> {
            button.setText("Sending...");

            // Chạy mã trong một luồng mới
            new Thread(() -> {
                if (service_forgot.send_code_to_email(email.getText()) == true) {
                	Platform.runLater(() -> {
                		function_for_control.show_alert("Send code to email completely!", "Send code to email completely!", "Please use this code to reset your password");
                		button.setText("Send code");
                	});
                }
                else {
                	Platform.runLater(() -> {
                    	function_for_control.show_alert("Send code to email failed!", "Send code to email failed!", "Please check your information again");
                    	button.setText("Send code");
                	});
                }
            }).start();
        });
	}
	public static void show_alert(String title, String header, String content) {
        show_alert.show_alert.show(title, header, content);
	}
}
