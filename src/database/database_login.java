package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import hash_data.hash;

public class database_login {
	public static boolean login_check_email_exist(String email) throws SQLException { // kiểm tra xem email có tồn tại trong database hay không. True là không, False là có.
		Statement statement = connect_database.connect.createStatement();
		String query = "select host.email from host \r\n"
				+ "where host.email = '" + email + "'";
        ResultSet resultSet = statement.executeQuery(query);
        int flag = 0;
        while (resultSet.next()) {
            flag++;
            break;
        }
        if (flag == 0) {
        	return true;
        }
        return false;
	}
	public static boolean login_check_password_email(String email, String password) throws SQLException { // kiểm tra mật khẩu và email có trùng với nhau hay không.
		Statement statement = connect_database.connect.createStatement();
		String query = "select host.password from host \r\n"
				+ "where host.email = '" + email + "'";
        ResultSet resultSet = statement.executeQuery(query);
        String s = null;
        // Hiển thị dữ liệu trong console
        while (resultSet.next()) {
        	s = resultSet.getString("password");
        }
        if (s == null) {
        	return false;
        }
        String s1 = hash.encryptToSHA1(password);
        if (s.equals(s1)) {
        	return true;
        }
        return false;
	}
	public static void forgot_update_password(String email, String password) { // update password trong forgot password.
		String encry = hash.encryptToSHA1(password);
		String query = "update host\r\n"
				+ "set host.password = '" + encry + "'" + "\r\n"
				+ "where host.email = '" + email + "'";
		try (PreparedStatement preparedStatement = connect_database.connect.prepareStatement(query)) {
		      // Thiết lập giá trị cho các tham số
		      // Thực hiện truy vấn UPDATE
		      int rowsAffected = preparedStatement.executeUpdate();
		      if (rowsAffected > 0) {
		          System.out.println("update thành công");
		      } else {
		          System.out.println("update thất bại");
		      }
		  } catch(Exception e) {
		      System.out.println(e);
		  }
	}
	public static void signup_insert_data(String email, String password, String citizen_id) throws SQLException { // insert data khi signup.
		String query = "insert into host (host_id, email, citizen_id, password) values (?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connect_database.connect.prepareStatement(query)) {
            // Thiết lập giá trị cho các tham số
            preparedStatement.setInt(1, count_host_id());
            preparedStatement.setNString(2, email);
            preparedStatement.setNString(3, citizen_id);
            preparedStatement.setNString(4, hash.encryptToSHA1(password));
            // Thực hiện truy vấn INSERT
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Dữ liệu đã được thêm thành công!");
            } else {
                System.out.println("Kh ông có dữ liệu nào được thêm.");
            }
		}
	}
	public static int count_host_id() throws SQLException { // đếm số lượng email trong host.
		Statement statement = connect_database.connect.createStatement();
		String query = "select count(*) as sl from host \r\n"
				+ "";
        ResultSet resultSet = statement.executeQuery(query);
        String s = null;
        // Hiển thị dữ liệu trong console
        while (resultSet.next()) {
        	s = resultSet.getString("sl");
        }
        int result = Integer.parseInt(s);
		return result + 1;
	}
	public static void setup_data_for_user(String email) throws SQLException{
		Statement statement = connect_database.connect.createStatement();	
		String query = "select host_id from host\r\n"
				+ "where email = '" + email + "'";
		ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
           user.user.user_id = resultSet.getInt(1);
        }
	}
	
}
