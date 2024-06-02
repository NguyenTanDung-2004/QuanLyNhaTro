package service_home;

import java.sql.SQLException;
import java.util.List;

import database.database_home;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import show_alert.show_alert;

public class service_for_control {
	static BorderPane borderpane_clicking;
	public static void turn_off_all_page(AnchorPane all_boarder, AnchorPane add_boarder, AnchorPane pr_post, AnchorPane account_setting, AnchorPane detail_boarder, AnchorPane all_room) {
		all_boarder.setVisible(false);
		add_boarder.setVisible(false);
		pr_post.setVisible(false);
		account_setting.setVisible(false);
		detail_boarder.setVisible(false);
		all_room.setVisible(false);
	}
	public static void reset_style_all_tag(BorderPane all_boarder, BorderPane add_boarder, BorderPane pr_post, BorderPane account_setting, BorderPane log_out, BorderPane add_room) {
		all_boarder.setStyle("-fx-background-color: #0077b6; -fx-scale-x: 1; -fx-scale-y: 1");
		add_boarder.setStyle("-fx-background-color: #0077b6; -fx-scale-x: 1; -fx-scale-y: 1");
		pr_post.setStyle("-fx-background-color: #0077b6; -fx-scale-x: 1; -fx-scale-y: 1");
		add_room.setStyle("-fx-background-color: #0077b6; -fx-scale-x: 1; -fx-scale-y: 1");
		log_out.setStyle("-fx-background-color: #0077b6; -fx-scale-x: 1; -fx-scale-y: 1");
		account_setting.setStyle("-fx-background-color: #0077b6; -fx-scale-x: 1; -fx-scale-y: 1");
		hover_for_button(add_room);
		hover_for_button(log_out);
		hover_for_button(account_setting);
		hover_for_button(pr_post);
		hover_for_button(add_boarder);
		hover_for_button(all_boarder);
	}
	public static void hover_for_button(BorderPane button) {
		button.setOnMouseEntered(e -> {
			button.setStyle("-fx-background-color: #0096c7; -fx-scale-x: 1.1; -fx-scale-y: 1.1");
        });

        // Xử lý sự kiện khi con chuột di chuyển ra khỏi nút
        button.setOnMouseExited(e -> {
        	button.setStyle("-fx-background-color: #0077b6; -fx-scale-x: 1; -fx-scale-y: 1");
        });
	}
	public static void turn_on_1_page(BorderPane button, AnchorPane page, AnchorPane all_boarder, AnchorPane add_boarder, AnchorPane pr_post, AnchorPane account_setting, AnchorPane detail_boarder, BorderPane all_boarder1, BorderPane add_boarder1, BorderPane pr_post1, BorderPane account_setting1, BorderPane log_out1, BorderPane add_room1, AnchorPane all_room) {
		button.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->
			{
				borderpane_clicking = button;
				reset_style_all_tag(all_boarder1, add_boarder1, pr_post1, account_setting1, log_out1, add_room1);
				button.setStyle("-fx-background-color: #0096c7; -fx-scale-x: 1.1; -fx-scale-y: 1.1");
				turn_off_all_page(all_boarder, add_boarder, pr_post, account_setting, detail_boarder, all_room);
				page.setVisible(true);
			}
		);
	}
	public static void show_add_room() {
		show_alert.show();
	}
	public static void event_for_confirm_add_boarder(TextField name, TextField email, TextField phone, TextField citizen_id, TextField province, TextField register_room){
		String nam_value = name.getText();
		String email_value = email.getText();
		String phone_value = phone.getText();
		String citizen_id_value = citizen_id.getText();
		String province_value = province.getText();
		String register_room_value = register_room.getText();
		try {
			if (database_home.check_room_exist(register_room_value)) {
				show_alert.show("Add Boarder Failed!", "Add Boarder Failed!", "This room is not exits.");
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			database_home.insert_boarder(email_value, citizen_id_value, nam_value, province_value, phone_value);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			database_home.insert_rent(register_room_value);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		show_alert.show("Add boarder completely!", "Add boarder completely!", "");
	}
	public static void event_for_reset_add_boarder(TextField name, TextField email, TextField phone, TextField citizen_id, TextField province, TextField register_room) {
		name.setText(null);
		email.setText(null);
		phone.setText(null);
		citizen_id.setText(null);
		province.setText(null);
		register_room.setText(null);
	}
	public static void add_data_for_filter(ComboBox<String> cb) {
		List<String> list = database_home.get_room_for_combobox(user.user.user_id); 
		cb.getItems().addAll("Owe", "All data");
		for (int i = 0; i < list.size(); i++) {
			cb.getItems().add(list.get(i));
		}
	}
}
