package show_alert;

import java.net.URL; 
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.database_home;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class show_alert_with_fxml implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		event_for_button();
	}
	@FXML
	private TextField name;
	@FXML 
	private TextField money;
	@FXML
	private Button button;
	public void event_for_button() {
		button.setOnAction(event->
			{
				String name_value = this.name.getText();
				String money_value = this.money.getText();
				try {
					if (database_home.check_room_exist(name_value) == false) {
						show_alert.show("Add room failed!", "Add room failed!", "This room is existed");
						return;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					database_home.insert_room(name_value, money_value);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					database_home.insert_host_room(name_value);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				show_alert.show("Add room completely", "Add room completely", "");
			}
		);
	}
}
