package service_home;

import java.sql.SQLException;

import database.database_home;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import show_alert.show_alert;

public class service_for_pr_post {
	public static void set_data_for_pr_post(BorderPane pr_post, TextField link1, TextField link2, TextField link3, TextField link4, TextArea post) {
		pr_post.addEventFilter(MouseEvent.MOUSE_CLICKED, event->
			{
				try {
					database_home.get_link_text();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();	
				}
				link1.setText(user.user.link_text.get(0));
				link2.setText(user.user.link_text.get(1));
				link3.setText(user.user.link_text.get(2));
				link4.setText(user.user.link_text.get(3));
				post.setText(user.user.link_text.get(4));
			}
		);
	}
	public static void event_for_confirm_pr_post(Button confirm, TextField link1, TextField link2, TextField link3, TextField link4, TextArea post) {
		confirm.setOnAction(event-> 
			{
				String link1_value = link1.getText();
				String link2_value = link2.getText();
				String link3_value = link3.getText();
				String link4_value = link4.getText();
				String post_value = post.getText();
				
				try {
					database_home.update_link_text(link1_value, link2_value, link3_value, link4_value, post_value);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				show_alert.show("Confirm completely!", "Confirm completely!", "");
			}
		);
	}
}
