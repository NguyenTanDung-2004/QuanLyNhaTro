package service_home;


import java.sql.SQLException;
import java.util.List;

import database.database_home;
import home.home_with_fxml;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import show_alert.show_alert;
import user.user;

public class service_for_controll_all_boarder {
	public static int height = 500;
	public static AnchorPane create_1_row(String name, String province, String flag, String date) {
		AnchorPane obj = new AnchorPane();
		obj.getStyleClass().add("row");
		obj.setMinHeight(40);
		obj.setMaxHeight(40);
		Text t1 = new Text();
		t1.setText(name);
		Text t2 = new Text(province);
		Text t3 = new Text();
		if (flag.equals("0")) {
			t3.setText("Completely");
			t3.setFill(Color.GREEN);
		}
		else {
			t3.setText("Owned");
			t3.setFill(Color.RED);
		}
		Text t4 = new Text(date);
		obj.getChildren().addAll(t1, t2, t3, t4);
		obj.setTopAnchor(t1, 10.857142857142858);
		obj.setTopAnchor(t2, 10.857142857142858);
		obj.setTopAnchor(t3, 10.857142857142858);
		obj.setTopAnchor(t4, 10.857142857142858);
		obj.setLeftAnchor(t1, 70.0);
		obj.setLeftAnchor(t2, 280.0);
		obj.setLeftAnchor(t3, 480.0);
		obj.setLeftAnchor(t4, 680.0);
		t1.getStyleClass().add("data_in_1_row");
        t2.getStyleClass().add("data_in_1_row");
        t3.getStyleClass().add("data_in_1_row");
        t4.getStyleClass().add("data_in_1_row");
		return obj;
	}
	public static void event_for_all_boarder(BorderPane all_boarder_dashboard , VBox vbox, AnchorPane all_boarder, AnchorPane add_boarder, AnchorPane pr_post, AnchorPane account_setting, AnchorPane detail_boarder, TextField email, TextField register_room, TextField phone, TextField day_of_boarding, TextField province, TextField citizen_id, TextField debt_money, CheckBox paid, TextField name, AnchorPane all_room) {
		vbox.setPrefHeight((double)500);
		for (int i = 0; i < user.all_data_all_boarder.size(); i++) {
			AnchorPane obj = create_1_row(user.all_data_all_boarder.get(i).get(0), user.all_data_all_boarder.get(i).get(4), user.all_data_all_boarder.get(i).get(1), user.all_data_all_boarder.get(i).get(2));
			vbox.getChildren().add(obj);
			final int index = i;
			obj.addEventFilter(MouseEvent.MOUSE_CLICKED, event->
				{
					service_for_controll_all_boarder.set_data_for_detail_boarder(index, email, register_room, phone, day_of_boarding, province, citizen_id, debt_money, paid, name);
					service_for_controll_all_boarder.event_for_1_row(index, all_boarder, add_boarder, pr_post, account_setting, detail_boarder, all_room);
				}
			);
			
			vbox.setPrefHeight((double)height + 40);
		}
	}
	public static void remove_anchorpane(VBox vbox) {
		vbox.getChildren().clear();
	}
	public static void add_anchorpane_into_vbox(VBox vbox, AnchorPane all_boarder, AnchorPane add_boarder, AnchorPane pr_post, AnchorPane account_setting, AnchorPane detail_boarder, TextField email, TextField register_room, TextField phone, TextField day_of_boarding, TextField province, TextField citizen_id, TextField debt_money, CheckBox paid, TextField name, AnchorPane all_room) {
		vbox.setPrefHeight((double)500);
		for (int i = 0; i < user.all_data_all_boarder.size(); i++) {
			AnchorPane obj = create_1_row(user.all_data_all_boarder.get(i).get(0), user.all_data_all_boarder.get(i).get(4), user.all_data_all_boarder.get(i).get(1), user.all_data_all_boarder.get(i).get(2));
			vbox.getChildren().add(obj);
			final int index = i;
			obj.addEventFilter(MouseEvent.MOUSE_CLICKED, event->
				{
					service_for_controll_all_boarder.set_data_for_detail_boarder(index, email, register_room, phone, day_of_boarding, province, citizen_id, debt_money, paid, name);
					service_for_controll_all_boarder.event_for_1_row(index, all_boarder, add_boarder, pr_post, account_setting, detail_boarder, all_room);
				}
			);
			
			vbox.setPrefHeight((double)height + 40);
		}
	}
	public static void add_anchorpane_into_vbox_filter(VBox vbox, AnchorPane all_boarder, AnchorPane add_boarder, AnchorPane pr_post, AnchorPane account_setting, AnchorPane detail_boarder, TextField email, TextField register_room, TextField phone, TextField day_of_boarding, TextField province, TextField citizen_id, TextField debt_money, CheckBox paid, TextField name, AnchorPane all_room) {
		vbox.setPrefHeight((double)500);
		for (int i = 0; i < user.all_data_all_boarder.size(); i++) {
			AnchorPane obj = create_1_row(user.data_filter.get(i).get(0), user.data_filter.get(i).get(4), user.data_filter.get(i).get(1), user.data_filter.get(i).get(2));
			vbox.getChildren().add(obj);
			final int index = i;
			obj.addEventFilter(MouseEvent.MOUSE_CLICKED, event->
				{
					service_for_controll_all_boarder.set_data_for_detail_boarder_filter(index, email, register_room, phone, day_of_boarding, province, citizen_id, debt_money, paid, name);
					service_for_controll_all_boarder.event_for_1_row(index, all_boarder, add_boarder, pr_post, account_setting, detail_boarder, all_room);
				}
			);
			
			vbox.setPrefHeight((double)height + 40);
		}
	}
	public static void event_for_1_row(int i, AnchorPane all_boarder, AnchorPane add_boarder, AnchorPane pr_post, AnchorPane account_setting, AnchorPane detail_boarder, AnchorPane all_room) {
		turn_on_detail_boarder(all_boarder, add_boarder, pr_post, account_setting, detail_boarder, all_room);
	}
	public static void turn_on_detail_boarder(AnchorPane all_boarder, AnchorPane add_boarder, AnchorPane pr_post, AnchorPane account_setting, AnchorPane detail_boarder, AnchorPane all_room) {
		service_for_control.turn_off_all_page(all_boarder, add_boarder, pr_post, account_setting, detail_boarder, all_room);
		detail_boarder.setVisible(true);
	}
	public static void set_data_for_detail_boarder(int i, TextField email, TextField register_room, TextField phone, TextField day_of_boarding, TextField province, TextField citizen_id, TextField debt_money, CheckBox paid, TextField name) {
		email.setText(user.all_data_all_boarder.get(i).get(3));
		register_room.setText(user.all_data_all_boarder.get(i).get(5));
		phone.setText(user.all_data_all_boarder.get(i).get(6));
		province.setText(user.all_data_all_boarder.get(i).get(4));
		citizen_id.setText(user.all_data_all_boarder.get(i).get(7));
		day_of_boarding.setText(user.all_data_all_boarder.get(i).get(2));
		debt_money.setText(database_home.get_money_from_room(Integer.parseInt(user.all_data_all_boarder.get(i).get(8))) + " VND");
		if (user.all_data_all_boarder.get(i).get(1).equals("0")) {
			paid.setSelected(true);
		}
		else {
			paid.setSelected(false);
		}
		name.setText(user.all_data_all_boarder.get(i).get(0));
		user.current_boarder_id = Integer.parseInt(user.all_data_all_boarder.get(i).get(9));
		System.out.println(user.current_boarder_id);
	}
	public static void set_data_for_detail_boarder_filter(int i, TextField email, TextField register_room, TextField phone, TextField day_of_boarding, TextField province, TextField citizen_id, TextField debt_money, CheckBox paid, TextField name) {
		email.setText(user.data_filter.get(i).get(3));
		register_room.setText(user.data_filter.get(i).get(5));
		phone.setText(user.data_filter.get(i).get(6));
		province.setText(user.data_filter.get(i).get(4));
		citizen_id.setText(user.data_filter.get(i).get(7));
		day_of_boarding.setText(user.data_filter.get(i).get(2));
		if (user.data_filter.get(i).get(1).equals("0")) {
			paid.setSelected(true);
		}
		else {
			paid.setSelected(false);
		}
		name.setText(user.data_filter.get(i).get(0));
	}
	public static void event_for_update_boarder(VBox vbox , Button confirm_update, CheckBox paid, TextField email, TextField room_host_id, TextField register_room, TextField phone, TextField province, TextField citizen, TextField name) {
		confirm_update.addEventFilter(MouseEvent.MOUSE_CLICKED, event->
			{
				if (paid.isSelected() == true) {
					try {
						database_home.update_own(user.current_boarder_id, 0);
						show_alert.show("Update completely!", "Update completely!", "Check in all boarders.");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					try {
						database_home.update_own(user.current_boarder_id, 1);
						
						show_alert.show("Update completely!", "Update completely!", "Check in all boarders.");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				database_home.update_boarder(user.current_boarder_id, email.getText(), name.getText(), phone.getText(), province.getText(), citizen.getText());
				database_home.update_rent(register_room.getText(), user.current_boarder_id);
				email.setEditable(false);
				register_room.setEditable(false);
				phone.setEditable(false);
				province.setEditable(false);
				citizen.setEditable(false);
				name.setEditable(false);
			}
		);
	}
	// event for edit detail
	public static void event_for_edit_detail(Text edit_detail, TextField email, TextField register_room, TextField phone, TextField day_of_boarding, TextField province, TextField citizen_id, TextField debt_money, CheckBox paid, TextField name) {
		edit_detail.setOnMouseClicked(e -> 
		{
			email.setEditable(true);
			register_room.setEditable(true);
			phone.setEditable(true);
			day_of_boarding.setVisible(true);
			province.setEditable(true);
			citizen_id.setEditable(true);
			name.setEditable(true);
		}
		);
		
	}
	
	
	public static void event_for_reload(Button reload, VBox vbox, AnchorPane all_boarder, AnchorPane add_boarder, AnchorPane pr_post, AnchorPane account_setting, AnchorPane detail_boarder, TextField email, TextField register_room, TextField phone, TextField day_of_boarding, TextField province, TextField citizen_id, TextField debt_money, CheckBox paid, TextField name, AnchorPane all_room, ComboBox<String> cb) {
		reload.addEventFilter(MouseEvent.MOUSE_CLICKED, event-> 
			{
				try {
					database_home.create_all_data_all_boarder();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				remove_anchorpane(vbox);
				add_anchorpane_into_vbox(vbox, all_boarder, add_boarder, pr_post, account_setting, detail_boarder, email, register_room, phone, day_of_boarding, province, citizen_id, debt_money, paid, name, all_room);
				user.list_room_of_user = database_home.get_room_for_combobox(user.user_id);
				List<String> list = database_home.get_room_for_combobox(user.user_id); 
				cb.getItems().clear();
				cb.getItems().addAll("Owe", "All data");
				for (int i = 0; i < list.size(); i++) {
					cb.getItems().add(list.get(i));
				}
			}
		);
	}
	public static void event_for_combobox(ComboBox<String> cb, VBox vbox, AnchorPane all_boarder, AnchorPane add_boarder, AnchorPane pr_post, AnchorPane account_setting, AnchorPane detail_boarder, TextField email, TextField register_room, TextField phone, TextField day_of_boarding, TextField province, TextField citizen_id, TextField debt_money, CheckBox paid, TextField name, AnchorPane all_room) {
		user.list_room_of_user = database_home.get_room_for_combobox(user.user_id); 
		cb.setOnAction(event->
			{
				String selectedItem = cb.getSelectionModel().getSelectedItem();
				if (selectedItem.equals("Owe")) {
					try {
						database_home.create_data_filter("owned", "1");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					remove_anchorpane(vbox);
					add_anchorpane_into_vbox_filter(vbox, all_boarder, add_boarder, pr_post, account_setting, detail_boarder, email, register_room, phone, day_of_boarding, province, citizen_id, debt_money, paid, name, all_room);
				}
				if (selectedItem.equals("All data")) {
					remove_anchorpane(vbox);
					add_anchorpane_into_vbox(vbox, all_boarder, add_boarder, pr_post, account_setting, detail_boarder, email, register_room, phone, day_of_boarding, province, citizen_id, debt_money, paid, name, all_room);
				}
				for (int i = 0; i < user.list_room_of_user.size(); i++) {
					if (selectedItem.equals(user.list_room_of_user.get(i))) {
						remove_anchorpane(vbox);
						add_anchorpane_into_vbox1(vbox, all_boarder, add_boarder, pr_post, account_setting, detail_boarder, email, register_room, phone, day_of_boarding, province, citizen_id, debt_money, paid, name, all_room, user.list_room_of_user.get(i));
					}
				}
			}
		);
	}
	public static void event_for_button_search(ComboBox<String> cb, TextField tf, Button search) {
		
	}
	public static void add_anchorpane_into_vbox1(VBox vbox, AnchorPane all_boarder, AnchorPane add_boarder, AnchorPane pr_post, AnchorPane account_setting, AnchorPane detail_boarder, TextField email, TextField register_room, TextField phone, TextField day_of_boarding, TextField province, TextField citizen_id, TextField debt_money, CheckBox paid, TextField name, AnchorPane all_room, String room) {
		vbox.setPrefHeight((double)500);
		for (int i = 0; i < user.all_data_all_boarder.size(); i++) {
			if (user.all_data_all_boarder.get(i).get(5).equals(room)){
				AnchorPane obj = create_1_row(user.all_data_all_boarder.get(i).get(0), user.all_data_all_boarder.get(i).get(4), user.all_data_all_boarder.get(i).get(1), user.all_data_all_boarder.get(i).get(2));
				vbox.getChildren().add(obj);
				final int index = i;
				obj.addEventFilter(MouseEvent.MOUSE_CLICKED, event->
					{
						service_for_controll_all_boarder.set_data_for_detail_boarder(index, email, register_room, phone, day_of_boarding, province, citizen_id, debt_money, paid, name);
						service_for_controll_all_boarder.event_for_1_row(index, all_boarder, add_boarder, pr_post, account_setting, detail_boarder, all_room);
					}
				);
				
				vbox.setPrefHeight((double)height + 40);
			}
		}
		
	}
	
	public static void remove_boarder(Button remove) {
		remove.setOnMouseClicked(e -> 
		{
			database_home.delete_rent();
			database_home.delete_boarder();
			show_alert.show("Delete completely!", "Delete Boarder Completely!", "");
		}
		);
		
	}
}
