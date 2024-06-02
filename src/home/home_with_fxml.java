package home;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import service_home.service_for_all_room;
import service_home.service_for_control;
import service_home.service_for_controll_all_boarder;

public class home_with_fxml implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		all_event();
		citizen_id_text.setText("Citizen ID: " + user.user.citizen_id);
	}
	// Vấn đề 1: chuyển page.
// đối tượng trong dashboar
	@FXML
	private Text citizen_id_text;
// các nút chuyển page.
	@FXML
	private BorderPane all_boarder_button;
	@FXML
	private BorderPane add_boarder_button;
	@FXML
	private BorderPane pr_post_button;
	@FXML
	private BorderPane add_room_button;
	@FXML
	private BorderPane log_out;
	@FXML
	private BorderPane account_setting_button;
// các anchorpane trong content
	@FXML
	private AnchorPane all_boarder;
	@FXML
	private AnchorPane add_boarder;
	@FXML
	private AnchorPane pr_post;
	@FXML
	private AnchorPane account_setting;
	@FXML
	private AnchorPane detail_boarder;
	@FXML 
	private AnchorPane all_room;
// các đối tượng trong add boarder.
	@FXML
	private TextField name;
	@FXML
	private TextField email;
	@FXML
	private TextField phone;
	@FXML
	private TextField citizen_id;
	@FXML
	private TextField province;
	@FXML
	private TextField register_room;
	@FXML
	private Button confirm_add_boarder;
	@FXML
	private Button reset_add_boarder;
// các đối tượng trong all boarder.
	@FXML
	private VBox vbox;
	@FXML
	private Button reload_button;
	@FXML
	private ComboBox<String> cb;
// các đối tượng trong detail boarder.
	@FXML
	private TextField email_detail;
	@FXML
	private TextField register_room_detail;
	@FXML
	private TextField phone_detail;
	@FXML
	private TextField day_of_boarding_detail;
	@FXML
	private TextField province_detail;
	@FXML
	private TextField citizen_id_detail;
	@FXML
	private TextField debt_money_detail;
	@FXML
	private CheckBox paid;
	@FXML
	private TextField name_detail;
	@FXML
	private Button confirm_update_detail;
	@FXML
	private Button remove_boarder_detail;
	@FXML
	Text edit_in_detail;
// các đối tượng có trong pr post
	@FXML
	private TextField input_link1;
	@FXML
	private TextField input_link2;
	@FXML
	private TextField input_link3;
	@FXML
	private TextField input_link4;
	@FXML
	private TextArea post;
	@FXML
	private Button confirm;
// các đối tượng có trong account setting.
	@FXML
	private TextField input_location;
	@FXML
	private TextField input_phone;
	@FXML
	private TextField input_email;
	@FXML
	private Button confirm_account_setting;
// các đối tượng có trong all room 
	@FXML 
	VBox vbox_in_all_room;
	@FXML
	Button reload_all_room;
	@FXML
	Button save_all_room;
	
	
	
	// sự kiện cho các nút chuyển page.
	public void event_for_all_button_change_page() {
		service_home.service_for_control.turn_on_1_page(all_boarder_button, all_boarder, all_boarder, add_boarder, pr_post, account_setting, detail_boarder, all_boarder_button, all_boarder_button, add_boarder_button, pr_post_button, account_setting_button, log_out, all_room);
		service_home.service_for_control.turn_on_1_page(add_boarder_button, add_boarder, all_boarder, add_boarder, pr_post, account_setting, detail_boarder, all_boarder_button, all_boarder_button, add_boarder_button, pr_post_button, account_setting_button, log_out, all_room);
		service_home.service_for_control.turn_on_1_page(pr_post_button, pr_post, all_boarder, add_boarder, pr_post, account_setting, detail_boarder, all_boarder_button, all_boarder_button, add_boarder_button, pr_post_button, account_setting_button, log_out, all_room);
		service_home.service_for_control.turn_on_1_page(account_setting_button, account_setting, all_boarder, add_boarder, pr_post, account_setting, detail_boarder, all_boarder_button, all_boarder_button, add_boarder_button, pr_post_button, account_setting_button, log_out, all_room);
	}
	// xử lí sự kiện nhấn add_room
	public void event_for_add_room_button() {
		add_room_button.addEventFilter(MouseEvent.MOUSE_CLICKED, event->
			{
				service_for_control.show_add_room();
			}
		);
	}
	// sự kiện nhấn confirm trong add boarder
	public void event_for_confirm_add_boarder(){
		confirm_add_boarder.setOnAction(event->
			{
				service_home.service_for_control.event_for_confirm_add_boarder(name, email, phone, citizen_id, province, register_room);
			}
		);
	}
	public void event_for_reset_add_boarder(){
		reset_add_boarder.setOnAction(event->
			{
				service_home.service_for_control.event_for_reset_add_boarder(name, email, phone, citizen_id, province, register_room);
			}
		);
	}
	// sự kiện nhấn một người ở trọ trong all boarder
	public void event_add_anchorpane_in_all_boarder() {
		service_home.service_for_controll_all_boarder.add_anchorpane_into_vbox(vbox, all_boarder, add_boarder, pr_post, account_setting, detail_boarder, email_detail, register_room_detail, phone_detail, day_of_boarding_detail, province_detail, citizen_id_detail, debt_money_detail, paid, name_detail, all_room);
	}
	// xử lí sự kiện update boarder.
	public void event_update_boarder() {
		service_home.service_for_controll_all_boarder.event_for_update_boarder(vbox , confirm_update_detail, paid, email_detail, register_room_detail, register_room_detail, phone_detail, province_detail, citizen_id_detail, name_detail);
	}
	
	// xử lí sự kiện reload all_boarder
	public void event_for_reload_boarder() {
		service_home.service_for_controll_all_boarder.event_for_reload(reload_button , vbox, all_boarder, add_boarder, pr_post, account_setting, detail_boarder, email_detail, register_room_detail, phone_detail, day_of_boarding_detail, province_detail, citizen_id_detail, debt_money_detail, paid, name_detail, all_room, cb);
	
	}
	// xử lí sự kiện cho combobox
	public void event_and_data_for_combobox() {
		service_home.service_for_control.add_data_for_filter(cb);
		service_home.service_for_controll_all_boarder.event_for_combobox(cb, vbox, all_boarder, add_boarder, pr_post, account_setting, detail_boarder, email_detail, register_room_detail, phone_detail, day_of_boarding_detail, province_detail, citizen_id_detail, debt_money_detail, paid, name_detail, all_room);
	}
	// sự kiện khi chuyển sang trang pr-post
	public void event_for_change_pr_post() {
		//service_home.service_for_pr_post.set_data_for_pr_post(pr_post_button, input_link1, input_link2, input_link3, input_link4, post);
		service_for_all_room.turn_on_all_room(pr_post_button, all_boarder, add_boarder, pr_post, account_setting, detail_boarder, all_room, vbox_in_all_room);
	}
	// xử lí sự kiện nhấn nút confirm pr-post
	public void event_for_confirm_pr_post() {
		service_home.service_for_pr_post.event_for_confirm_pr_post(confirm, input_link1, input_link2, input_link3, input_link4, post);
	}
	// sự kiện nhấn account_setting.
	public void event_for_account_setting() {
		service_home.service_for_account_setting.event_for_button_account_setting(account_setting_button, input_email, input_location, input_phone);
	}
	// sự kiện confirm update in account_setting.
	public void event_for_confirm_account_setting() {
		service_home.service_for_account_setting.event_for_update_account_setting(confirm_account_setting, input_location, input_phone);
	}
	// sự kiện logout.
	public void event_for_logout() {
		service_home.service_for_logout.event_for_logout(log_out, getClass());
	}
	
	// event for reload in all room
	public void event_for_reload_in_all_room() {
		service_for_all_room.event_for_reload_in_all_room(vbox_in_all_room, reload_all_room);
	}
	// event fro save in all room
	public void event_for_save_in_all_room() {
		service_for_all_room.event_for_save_in_all_room(save_all_room);
	}
	// event for edit in detail
	public void event_for_edit_in_detail() {
		service_for_controll_all_boarder.event_for_edit_detail(edit_in_detail, email_detail, register_room_detail, phone_detail, day_of_boarding_detail, province_detail, citizen_id_detail, debt_money_detail, paid, name_detail);;
	}
	// event for delete boarder 
	public void event_for_delete_boarder() {
		service_for_controll_all_boarder.remove_boarder(remove_boarder_detail);
	}
	
	// tất cả sự kiện.
	public void all_event() {
		event_for_all_button_change_page();
		event_for_add_room_button();
		event_for_confirm_add_boarder();
		event_for_reset_add_boarder();
		event_add_anchorpane_in_all_boarder();
		event_update_boarder();
		event_for_reload_boarder();
		event_and_data_for_combobox();
		event_for_change_pr_post();
		event_for_confirm_pr_post();
		event_for_account_setting();
		event_for_confirm_account_setting();
		event_for_logout();
		event_for_reload_in_all_room();
		event_for_save_in_all_room();
		event_for_edit_in_detail();
		event_for_delete_boarder();
	}
	

}
