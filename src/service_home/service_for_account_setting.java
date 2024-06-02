package service_home;

import java.sql.SQLException;

import database.database_home;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import show_alert.show_alert;

public class service_for_account_setting {
	public static void event_for_button_account_setting(BorderPane account_setting, TextField email, TextField location, TextField phone) {
		account_setting.addEventFilter(MouseEvent.MOUSE_CLICKED, event->
			{
				try {
					database_home.get_data();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				email.setText(user.user.email);
				location.setText(user.user.location);
				phone.setText(user.user.phone);
			}
		);
	}
	public static void event_for_update_account_setting(Button confirm_update_account_setting, TextField location, TextField phone) {
		confirm_update_account_setting.addEventFilter(MouseEvent.MOUSE_CLICKED, event->
			{
				try {
					database_home.update_data_account_setting(location.getText(), phone.getText());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				show_alert.show("Update Completely!", "Update Completely", null);
			}
			
		);
	}
}
