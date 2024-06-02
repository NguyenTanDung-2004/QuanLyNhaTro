package service_home;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.database_home;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import show_alert.show_alert;
import user.user;

public class service_for_all_room {
	public static AnchorPane create_row_in_(String name, String money, int boarders, int id) {
		AnchorPane anchorpane = new AnchorPane();
		anchorpane.setMaxHeight(45);
		anchorpane.setMinHeight(45);
		Label room = new Label();
		room.setText(name);
		TextField money_label = new TextField();
		money_label.setMaxWidth(83);
		money_label.setMinWidth(83);
		money_label.setText(money);
		Label vnd = new Label();
		vnd.setText("VND");
		Label boarders_label = new Label();
		boarders_label.setText("Current Boarders: " + boarders);
		anchorpane.getChildren().add(boarders_label);
		anchorpane.getChildren().add(room);
		anchorpane.getChildren().add(money_label);
		anchorpane.getChildren().add(vnd);
		// set top
		anchorpane.setTopAnchor(boarders_label, 14.0);
		anchorpane.setTopAnchor(room, 14.0);
		anchorpane.setTopAnchor(money_label, 10.0);
		anchorpane.setTopAnchor(vnd, 15.0);
		//set left
		anchorpane.setLeftAnchor(room, 70.0);
		anchorpane.setLeftAnchor(money_label, 360.0);
		anchorpane.setLeftAnchor(vnd, 440.0);
		anchorpane.setLeftAnchor(boarders_label, 694.8571428571428);
		//set style 
		money_label.setStyle("-fx-background-color: #caf0f8; -fx-font-size: 15px");
		room.setStyle("-fx-font-weight: 600; -fx-font-size: 15;");
		vnd.setStyle("-fx-font-weight: 600; -fx-font-size: 15");
		boarders_label.setStyle("-fx-font-weight: 600; -fx-font-size: 15");
		
		anchorpane.setOnMouseEntered(e -> 
		{
			anchorpane.setStyle("-fx-background-color: #0077b6; -fx-cursor: hand;");
			money_label.setStyle("-fx-background-color: #0077b6; -fx-font-size: 15px");
		}
		);
		anchorpane.setOnMouseExited(e -> 
		{
			anchorpane.setStyle("-fx-background-color: #caf0f8; -fx-scale-x: 1; -fx-scale-y: 1");
			money_label.setStyle("-fx-background-color: #caf0f8; -fx-font-size: 15px");
		}
		);		
		List<Object> list = new ArrayList<Object>();
		list.add(id);
		list.add(money_label);
		user.id_money_room.add(list);
		return anchorpane;
	}
	public static void set_all_data_for_room(VBox vbox) {
		user.data_room_of_user = database_home.get_room_of_user(user.user_id);
		user.id_money_room = new ArrayList<List<Object>>();
		for (int i = 0; i < user.data_room_of_user.size(); i++) {
			vbox.getChildren().add(create_row_in_((String)user.data_room_of_user.get(i).get(0), (String)user.data_room_of_user.get(i).get(1), (int)user.data_room_of_user.get(i).get(2), (int)user.data_room_of_user.get(i).get(3)));
		}
	}
	public static void turn_on_all_room(BorderPane all_room_button, AnchorPane all_boarder, AnchorPane add_boarder, AnchorPane pr_post, AnchorPane account_setting, AnchorPane detail_boarder, AnchorPane all_room, VBox vbox) {
		all_room_button.setOnMouseClicked(e -> 
		{
			service_for_control.turn_off_all_page(all_boarder, add_boarder, pr_post, account_setting, detail_boarder, all_room);
			vbox.getChildren().clear();
			set_all_data_for_room(vbox);
			all_room.setVisible(true);
		}
		);
	}
	public static void event_for_reload_in_all_room(VBox vbox, Button reload) {
		reload.setOnMouseClicked(e -> 
		{
			vbox.getChildren().clear();
			set_all_data_for_room(vbox);
		}
		);
	}
	public static void event_for_save_in_all_room(Button save) {
		save.setOnMouseClicked(e -> 
		{
			for (int i = 0; i < user.id_money_room.size(); i++) {
				database_home.update_room_money((Integer)user.id_money_room.get(i).get(0), (((TextField) user.id_money_room.get(i).get(1)).getText()));
			}
			show_alert.show("Update successfully!", "Update Room Successfully!", "Congratulation, all room was updated succesfully!");
		}
		);
	}
}
