package login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import database.connect_database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import service_login.function_for_control;
import service_login.service_signup;

public class login_with_fxml implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		event_for_al_controls();
	}
	
	// lấy đối tượng từ fxml.
// form login - các form
	@FXML
	private BorderPane form_login;
	@FXML
	private BorderPane form_signup;
	@FXML
	private BorderPane form_forgot;
// form login - login.
	@FXML
	private Button signin_button_in_login;
	@FXML
	private Text text_forgot_password;
	@FXML
	private Button login_in_login;
	@FXML
	private TextField email_in_login;
	@FXML
	private TextField password_in_login;
// form login - forgot
	@FXML
	private TextField email_in_forgot;
	@FXML
	private TextField password_in_forgot;
	@FXML
	private TextField confirm_in_forgot;
	@FXML
	private TextField code_in_forgot;
	@FXML
	private Button send_code_button_in_forgot;
	@FXML
	private Button confirm_new_password_button_in_forgot;
	@FXML
	private Text back_to_login_text_in_forgot;
// form login - Signup
	@FXML 
	private TextField email_in_signup;
	@FXML
	private TextField password_in_signup;
	@FXML
	private TextField confirm_in_signup;
	@FXML
	private TextField id_in_signup;
	@FXML
	private Button signup_button_in_signup;
	@FXML
	private Text have_account_text_in_signup;
	
	// function event cho tất cả các đối tượng.
	public void event_for_al_controls() {
		event_for_signin_button_in_login(); // hiển thị form signin
		event_for_text_forgot_password(); // hiển thị form forgot
		event_for_have_account_text_in_signup();
		event_for_back_to_login_text_in_forgot();
		event_for_login_in_login();
		event_for_signup_button_in_signup();
		event_for_confirm_new_password_button_in_forgot();
		event_for_send_code_button_in_forgot();
	}
	// function event cho từng đối tượng.
	public void event_for_text_forgot_password() {
		text_forgot_password.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event-> 
			{
				function_for_control.turn_off_all_form(form_signup, form_login, form_forgot);
				function_for_control.turn_on_1_form(form_forgot);
			}
		);
	}
	public void event_for_signin_button_in_login() {
		signin_button_in_login.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event ->
			{
				function_for_control.turn_off_all_form(form_signup, form_login, form_forgot);
				function_for_control.turn_on_1_form(form_signup);
			}
		);
		
	}
	public void event_for_have_account_text_in_signup() {
		have_account_text_in_signup.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event->
			{
				function_for_control.turn_off_all_form(form_signup, form_login, form_forgot);
				function_for_control.turn_on_1_form(form_login);
			}
		);
	}
	public void event_for_back_to_login_text_in_forgot() {
		back_to_login_text_in_forgot.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event->
		{
			function_for_control.turn_off_all_form(form_signup, form_login, form_forgot);
			function_for_control.turn_on_1_form(form_login);
		}
	);
	}
	public void event_for_login_in_login() {
		login_in_login.addEventFilter(MouseEvent.MOUSE_CLICKED, event-> 
			{
				function_for_control obj = new function_for_control();
				try {
					obj.event_for_login_in_login(login_in_login ,email_in_login, password_in_login);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		);
	}
	public void event_for_signup_button_in_signup() {
		function_for_control.event_for_signin_in_signup(signup_button_in_signup, email_in_signup, password_in_signup, confirm_in_signup, id_in_signup);
	}
	// nhấn confirm in forgot.
	public void event_for_confirm_new_password_button_in_forgot() {
		function_for_control.event_for_confirm_password_in_forgot(confirm_new_password_button_in_forgot, email_in_forgot, password_in_forgot, confirm_in_forgot, code_in_forgot);
	}
	// nhấn send code in forgot.
	public void event_for_send_code_button_in_forgot() {
		function_for_control.event_for_send_code_in_forgot(send_code_button_in_forgot, email_in_forgot);
	}
}
