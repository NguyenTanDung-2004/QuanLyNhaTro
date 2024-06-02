package service_login;

import java.sql.SQLException;

import database.database_home;
import database.database_login;
import hash_data.hash;
import javafx.scene.control.TextField;
import user.user;
import database.database_login;

public class service_login {
	public static boolean check_email_pass_null(TextField email, TextField password) { // kiểm tra xem có cái gì null không.
		String email_text = email.getText();
		String password_text = password.getText();
		if (email_text == null || password_text == null) {
			return false;
		}
		return true;
	}
	public static boolean check_password_email(String email, String password) { // kiểm tra password và email có trùng với nhau hay không.
		try {
			return database_login.login_check_password_email(email, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public static boolean check_all(TextField email, TextField password) { // kiểm tra và cho phép vào bên trong home.
		String email_text = email.getText();
		String password_text = password.getText();
		if (check_email_pass_null(email, password) == true && check_password_email(email_text, password_text) == true) {
			try {
				database_login.setup_data_for_user(email_text);
				database_home.create_all_data_all_boarder();
				database_home.get_link_text();
				user.display(user.link_text);
				user.email = email_text;
				database_home.get_data();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
}
