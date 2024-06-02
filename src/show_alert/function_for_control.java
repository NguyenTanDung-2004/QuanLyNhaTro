package show_alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.connect_database;
import javafx.scene.control.TextField;

public class function_for_control {
	public static boolean check_null(TextField name, TextField money) { 
		String name_value = name.getText();
		String money_value = money.getText();
		if (name_value == null || money_value == null) {
			return false;
		}
		return true;
	}
	public static boolean check_room_exist(TextField name) throws SQLException {
		Statement statement = connect_database.connect.createStatement();
		String name_room = name.getText();
		String query = "select * from host_room \r\n"
				+ "where host_room.host_id = '" + host.host.host_id + "' and host_room.room_host_id = '" + name_room + "'";
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
}
