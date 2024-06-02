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

public class service_forgot {
    public static String code;
    
    public static boolean send_code_to_email(String email) { // gửi email.
    	service_forgot.code = null;
    	Random random = new Random();
    	int x1 = random.nextInt(9 - 1 + 1) + 1;	
    	int x2 = random.nextInt(9 - 0 + 1) + 0;	
    	int x3 = random.nextInt(9 - 0 + 1) + 0;	
    	int x4 = random.nextInt(9 - 0 + 1) + 0;	
    	int x5 = random.nextInt(9 - 0 + 1) + 0;	
    	code = "" + x1 + x2 + x3 + x4 + x5;
        final String username = "tandungnguyen918@gmail.com";
        final String password = "oese kloq vtgv mxii";

        // Thông tin tài khoản email đích (email B)
        String to = email;

        // Cấu hình properties cho session
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
            message.setSubject("This is code to confirm reset your password.");

            // Đặt nội dung của email
            message.setText("Your code: " + x1 + x2 + x3 + x4 + x5);

            // Gửi email
            Transport.send(message);

            return true;
        } catch (MessagingException e) {
            return false;
        }

    }
    
    public static boolean check_null(TextField email, TextField new_pass, TextField confirm_pass, TextField code) { // kiểm tra có giá trị nào bằng null hay không.
    	String email_text = email.getText();
    	String new_pass_text = new_pass.getText();
    	String confirm_pass_text = confirm_pass.getText();
    	String code_text = code.getText();
    	if (email_text == null || new_pass_text == null || confirm_pass_text == null || code_text == null) {
    		return false;
    	}
    	return true;
    }
    
    public static boolean check_confirm_pass_new_pass(TextField confirm_pass, TextField new_pass) { // true là bằng nhau, false là không bằng nhau.
    	String new_pass_text = new_pass.getText();
    	String confirm_pass_text = confirm_pass.getText();
    	if (new_pass_text.equals(confirm_pass_text)) {
    		return true;
    	}
    	return false;
    }

    public static boolean check_code(String code) { // true là bằng, false là không bằng.
    	if (code.equals(service_forgot.code)) {
    		return true;
    	}
    	return false;
    }
    
    public static boolean check_email(String email) { // kiểm tra liệu rằng email có tồn tại hay không.
    	try {
			return database_login.login_check_email_exist(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return true;
    }
    
    public static boolean check_all(TextField email, TextField new_pass, TextField confirm_pass, TextField code) {
    	String code_text = code.getText();
    	if (check_code(code_text) == true && check_confirm_pass_new_pass(confirm_pass, new_pass) == true && check_null(email, new_pass, confirm_pass, code) == true) {
    		database_login.forgot_update_password(email.getText(), new_pass.getText());
    		return true;
    	}
    	return false;
    }
}
