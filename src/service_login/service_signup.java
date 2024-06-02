package service_login;

import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import database.database_login;
import javafx.scene.control.TextField;

public class service_signup {
	public static synchronized boolean check_email_exist_by_sending_email(String email) {
        final String username = "tandungnguyen918@gmail.com";
        final String password = "oese kloq vtgv mxii";

        // Thông tin tài khoản email đích (email B)
        String to = email;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Thay đổi nếu sử dụng email provider khác
        props.put("mail.smtp.port", "587"); // Thay đổi nếu sử dụng cổng khác
        props.put("mail.smtp.ssl.trust", "*");

        // Tạo đối tượng Session với thông tin xác thực
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Tạo đối tượng MimeMessage
            Message message = new MimeMessage(session);

            // Đặt thông tin người gửi (email A)
            message.setFrom(new InternetAddress(username));

            // Đặt thông tin người nhận (email B)
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Đặt chủ đề của email
            message.setSubject("Check account Security.");

            // Đặt nội dung của email
            message.setText("Your account is used to sign up my app.");

            // Gửi email
            Transport.send(message);

            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Sending email failed!");
            return false;
        }
	}
	
	public static synchronized boolean check_email_exist(String email) { // true là email chưa được đăng kí, false là email đã được đăng kí.
		try {
			return database_login.login_check_email_exist(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public static synchronized boolean check_null(TextField email, TextField password, TextField confirm, TextField citizen_id) { //false là có thứ gì đó bằng null.
		String emai_text = email.getText();
		String password_text = password.getText();
		String confirm_text = confirm.getText();
		String citizen_id_text = citizen_id.getText();
		if (emai_text == null || password_text == null || confirm_text == null || citizen_id_text == null) {
			return false;
		}
		return true;
	}
	public static synchronized boolean check_pass_confirm(TextField password, TextField confirm) { // kiểm tra pass và confirm có bằng nhau hay không.
		String password_text = password.getText();
		String confirm_text = confirm.getText(); 
		if (password_text.equals(confirm_text)) {
			return true;
		}
		return false;
	}
	public static synchronized boolean check_all(TextField email, TextField password, TextField confirm, TextField citizen_id) {
		String emai_text = email.getText();
		String password_text = password.getText();
		String confirm_text = confirm.getText();
		String citizen_id_text = citizen_id.getText();
		if (check_pass_confirm(password, confirm) == true && check_email_exist_by_sending_email(emai_text) == true && check_email_exist(emai_text) == true && check_null(email, password, confirm, citizen_id) == true) {
			try {
				database_login.signup_insert_data(emai_text, password_text, citizen_id_text);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		else {
			return false;
		}
	}
}
