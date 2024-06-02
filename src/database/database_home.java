package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hash_data.hash;
import show_alert.show_alert;
import user.user;
import util.date;

public class database_home {
	public static int count_room_id;
	public static int count_boarder_id;
	public static int count_room_id() throws SQLException { // đếm số lượng email trong host.
		Statement statement = connect_database.connect.createStatement();
		String query = "select count(*) as sl from room \r\n"
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
	public static void insert_room(String room_name, String money) throws SQLException {
		String query = "insert into room (room_id, room_host_id, money) values (?, ?, ?)";
		count_room_id = count_room_id();
		try (PreparedStatement preparedStatement = connect_database.connect.prepareStatement(query)) {
            // Thiết lập giá trị cho các tham số
            preparedStatement.setInt(1, count_room_id);
            preparedStatement.setNString(2, room_name);
            preparedStatement.setNString(3, money);
            // Thực hiện truy vấn INSERT
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Dữ liệu đã được thêm thành công!");
            } else {
                System.out.println("Kh ông có dữ liệu nào được thêm.");
            }
		}
	}
	public static void insert_host_room(String room_name) throws SQLException {
		String query = "insert into host_room (host_id, room_id, room_host_id) values (?, ?, ?)";
		try (PreparedStatement preparedStatement = connect_database.connect.prepareStatement(query)) {
            // Thiết lập giá trị cho các tham số
            preparedStatement.setInt(1, user.user_id);
            preparedStatement.setInt(2, count_room_id);
            preparedStatement.setNString(3, room_name);
            // Thực hiện truy vấn INSERT
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Dữ liệu đã được thêm thành công!");
            } else {
                System.out.println("Kh ông có dữ liệu nào được thêm.");
            }
		}
	}
	public static boolean check_room_exist(String room_host_id) throws SQLException {
		Statement statement = connect_database.connect.createStatement();
		String query = "select * from host_room \r\n"
				+ "where host_room.host_id = '" + user.user_id + "' and host_room.room_host_id = '" + room_host_id + "'";
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
	public static int count_boarder_id() throws SQLException {
		Statement statement = connect_database.connect.createStatement();
		String query = "select count(*) as sl from boarder \r\n"
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
	public static void insert_boarder(String email, String citizen_id, String name, String province, String phone) throws SQLException {
		String query = "insert into boarder (boarder_id, email, citizen_id, name, province, phone) values (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connect_database.connect.prepareStatement(query)) {
            // Thiết lập giá trị cho các tham số
			count_boarder_id = count_boarder_id();
            preparedStatement.setInt(1, count_boarder_id);
            preparedStatement.setNString(2, email);
            preparedStatement.setNString(3, citizen_id);
            preparedStatement.setNString(4, name);
            preparedStatement.setNString(5, province);
            preparedStatement.setNString(6, phone);
            // Thực hiện truy vấn INSERT
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Dữ liệu đã được thêm thành công!");
            } else {
                System.out.println("Kh ông có dữ liệu nào được thêm.");
            }
		}
	}
	public static void insert_rent(String room_host_id) throws SQLException {
		String query = "insert into rent (boarder_id, host_id, room_id, owned, day_of_boarding) values (?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connect_database.connect.prepareStatement(query)) {
            // Thiết lập giá trị cho các tham số
            preparedStatement.setInt(1, count_boarder_id);
            preparedStatement.setInt(2, user.user_id);
            preparedStatement.setInt(3, get_room_id(room_host_id));
            preparedStatement.setInt(4, 0);
            preparedStatement.setNString(5, date.get_date());
            // Thực hiện truy vấn INSERT
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Dữ liệu đã được thêm thành công!");
            } else {
                System.out.println("Kh ông có dữ liệu nào được thêm.");
            }
		}
	}
	public static int get_room_id(String room_host_id) throws SQLException {
		Statement statement = connect_database.connect.createStatement();
		String query = "select room.room_id from room inner join host_room on host_room.room_id = room.room_id \r\n"
				+ "where host_room.host_id = '" + user.user_id + "' and host_room.room_host_id = '" + room_host_id + "'";
		ResultSet resultSet = statement.executeQuery(query);
        int a = 0;
        // Hiển thị dữ liệu trong console
        while (resultSet.next()) {
        	a = resultSet.getInt("room_id");
        }
        return a;
	}
	public static void create_all_data_all_boarder() throws SQLException {
		Statement statement = connect_database.connect.createStatement();
		String query = "select name, owned, day_of_boarding, email, province, room_host_id, phone, citizen_id, rent.room_id, rent.boarder_id \r\n"
				+ "from boarder inner join rent on boarder.boarder_id = rent.boarder_id inner join room on rent.room_id = room.room_id\r\n"
				+ "where host_id = '" + user.user_id + "'";
		ResultSet resultSet = statement.executeQuery(query);
		user.all_data_all_boarder = new ArrayList<>();
        // Hiển thị dữ liệu trong console
        while (resultSet.next()) {
        	ArrayList<String> list = new ArrayList<>();
        	list.add(resultSet.getString(1));
        	list.add(resultSet.getString(2));
        	list.add(resultSet.getString(3));
        	list.add(resultSet.getString(4));
        	list.add(resultSet.getString(5));
        	list.add(resultSet.getString(6));
        	list.add(resultSet.getString(7));
        	list.add(resultSet.getString(8));
        	list.add(resultSet.getString(9));
        	list.add(resultSet.getString(10));
        	user.all_data_all_boarder.add(list);
        }
        
	}
	public static void create_data_filter(String name, String value) throws SQLException {
		Statement statement = connect_database.connect.createStatement();
		String query = "select name, owned, day_of_boarding, email, province, room_host_id, phone, citizen_id \r\n"
				+ "from boarder inner join rent on boarder.boarder_id = rent.boarder_id inner join room on rent.room_id = room.room_id\r\n"
				+ "where host_id = '" + user.user_id + "' and " + name + " = '" + value + "'" ;
		ResultSet resultSet = statement.executeQuery(query);
		user.data_filter = new ArrayList<>();
        // Hiển thị dữ liệu trong console
        while (resultSet.next()) {
        	ArrayList<String> list = new ArrayList<>();
        	list.add(resultSet.getString(1));
        	list.add(resultSet.getString(2));
        	list.add(resultSet.getString(3));
        	list.add(resultSet.getString(4));
        	list.add(resultSet.getString(5));
        	list.add(resultSet.getString(6));
        	list.add(resultSet.getString(7));
        	list.add(resultSet.getString(8));
        	user.data_filter.add(list);
        }
        
	}
	public static int get_boarder_id (String email) throws SQLException {
		Statement statement = connect_database.connect.createStatement();
		String query = "select boarder_id from boarder \r\n"
				+ "where boarder.email = '" + email + "'";
		ResultSet resultSet = statement.executeQuery(query);
        int a = 0;
        // Hiển thị dữ liệu trong console
        while (resultSet.next()) {
        	a = resultSet.getInt("boarder_id");
        }
        return a;
	}
	public static void update_own(int boarder_id, int a) throws SQLException {
	    String query = "UPDATE rent "
	            + "SET rent.owned = ? "
	            + "WHERE rent.boarder_id = ? AND rent.host_id = ?";
	    
	    try (PreparedStatement preparedStatement = connect_database.connect.prepareStatement(query)) {
	        preparedStatement.setInt(1, a);
	        preparedStatement.setInt(2, boarder_id);
	        preparedStatement.setInt(3, user.user_id);
	        
	        int rowsAffected = preparedStatement.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("update thành công");
	        } else {
	            System.out.println("update thất bại");
	        }
	    } catch(SQLException e) {
	        System.out.println("Lỗi khi thực hiện truy vấn: " + e.getMessage());
	        throw e; // Re-throw SQLException for handling at higher level if needed
	    }
	}

	public static void get_link_text() throws SQLException {
		Statement statement = connect_database.connect.createStatement();
		String query = "select host.img1, host.img2, host.img3, host.img4, host.text_pr \r\n"
				+ "from host\r\n"
				+ "where host.host_id = " + user.user_id;
        ResultSet resultSet = statement.executeQuery(query);
        // Hiển thị dữ liệu trong console
        user.link_text = new ArrayList<>();
        while (resultSet.next()) {
        	user.link_text.add(resultSet.getString(1));
        	user.link_text.add(resultSet.getString(2));
        	user.link_text.add(resultSet.getString(3));
        	user.link_text.add(resultSet.getString(4));
        	user.link_text.add(resultSet.getString(5));
        }
	}
	
	public static void update_link_text(String link1, String link2, String link3, String link4, String pr) throws SQLException {
		String query = "update host \r\n"
				+ "set host.img1 = ?, host.img2 = ?, host.img3 = ?, host.img4 = ?, host.text_pr = ?\r\n"
				+ "where host.host_id = ?";
		try (PreparedStatement preparedStatement = connect_database.connect.prepareStatement(query)) {
            // Thiết lập giá trị cho các tham số
			preparedStatement.setNString(1, link1);
			preparedStatement.setNString(2, link2);
			preparedStatement.setNString(3, link3);
			preparedStatement.setNString(4, link4);
			preparedStatement.setNString(5, pr);
			preparedStatement.setInt(6, user.user_id);
            // Thực hiện truy vấn INSERT
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Dữ liệu đã được thêm thành công!");
            } else {
                System.out.println("Kh ông có dữ liệu nào được thêm.");
            }
		}
	}
	
	public static void get_data() throws SQLException {
		Statement statement = connect_database.connect.createStatement();
		String query = "select location, phone, citizen_id from host\r\n"
				+ "where host.host_id = " + user.user_id;
        ResultSet resultSet = statement.executeQuery(query);
        String s = null;
        // Hiển thị dữ liệu trong console
        while (resultSet.next()) {
        	user.location = resultSet.getString(1);
        	user.phone = resultSet.getString(2);
        	user.citizen_id = resultSet.getString(3);
        }
	}

	public static void update_data_account_setting(String location, String phone) throws SQLException {
		String query = "update host \r\n"
				+ "set host.location = ?, host.phone = ?\r\n"
				+ "where host.host_id = ?";
		try (PreparedStatement preparedStatement = connect_database.connect.prepareStatement(query)) {
            // Thiết lập giá trị cho các tham số
			preparedStatement.setNString(1, location);
			preparedStatement.setNString(2, phone);
			preparedStatement.setInt(3, user.user_id);
            // Thực hiện truy vấn INSERT
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Dữ liệu đã được thêm thành công!");
            } else {
                System.out.println("Kh ông có dữ liệu nào được thêm.");
            }
		}
	}
	
	public static List<List<Object>> get_room_of_user(int user_id){
		List<List<Object>> obj1 = new ArrayList<>();
		try {
			Statement statement = connect_database.connect.createStatement();
			String query = "select room.room_host_id, money, count(boarder_id), room.room_id from host_room inner join room on host_room.room_id = room.room_id \r\n"
					+ "	left join rent on rent.room_id = room.room_id\r\n"
					+ "where host_room.host_id = " + user_id + "\r\n"
					+ "group by room.room_host_id, money, room.room_id";
	        ResultSet resultSet = statement.executeQuery(query);
	        String s = null;
	        // Hiển thị dữ liệu trong console
	        while (resultSet.next()) {
	        	List<Object> obj = new ArrayList<Object>();
	        	obj.add(resultSet.getString(1));
	        	obj.add(resultSet.getString(2));
	        	obj.add(resultSet.getInt(3));
	        	obj.add(resultSet.getInt(4));
	        	obj1.add(obj);
	        }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
        return obj1;
	}
	public static List<String> get_room_for_combobox(int user_id){
		List<String> obj1 = new ArrayList<>();
		try {
			Statement statement = connect_database.connect.createStatement();
			String query = "select room.room_host_id, money, count(boarder_id), room.room_id from host_room inner join room on host_room.room_id = room.room_id \r\n"
					+ "	left join rent on rent.room_id = room.room_id\r\n"
					+ "where host_room.host_id = " + user_id + "\r\n"
					+ "group by room.room_host_id, money, room.room_id";
	        ResultSet resultSet = statement.executeQuery(query);
	        String s = null;
	        // Hiển thị dữ liệu trong console
	        while (resultSet.next()) {
	        	obj1.add(resultSet.getString(1));
	        }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
        return obj1;
	}
	public static void update_room_money(int room_id, String money) {
		String query = "update room\r\n"
				+ "set room.money = '" + money + "' "
				+ "where room.room_id = " + room_id;
		try (PreparedStatement preparedStatement = connect_database.connect.prepareStatement(query)) {
            // Thực hiện truy vấn INSERT
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Dữ liệu đã được thêm thành công!");
            } else {
                System.out.println("Kh ông có dữ liệu nào được thêm.");
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		connect_database obj = new connect_database();
		connect_database.connect = obj.connect_database();
		database_home.update_room_money(11, "200");
	}
	public static String get_money_from_room(int room_id) {
		String money = "";
		try {
			Statement statement = connect_database.connect.createStatement();
			String query = "select money from room where room.room_id = " + room_id;
	        ResultSet resultSet = statement.executeQuery(query);
	        String s = null;
	        // Hiển thị dữ liệu trong console
	        while (resultSet.next()) {
	        	money = resultSet.getString(1);
	        }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
        return money;
	}
	public static void update_boarder(int boarder_id, String email, String name, String phone, String province, String citizen_id) {
		String query = "update boarder\r\n"
				+ "set boarder.citizen_id = ?,\r\n"
				+ "boarder.email = ?,\r\n"
				+ "boarder.name = ?,\r\n"
				+ "boarder.phone = ?,\r\n"
				+ "boarder.province = ?\r\n"
				+ "where boarder.boarder_id = " + boarder_id;
		try (PreparedStatement preparedStatement = connect_database.connect.prepareStatement(query)) {
            // Thiết lập giá trị cho các tham số
			preparedStatement.setNString(1, citizen_id);
			preparedStatement.setNString(2, email);
			preparedStatement.setNString(3, name);
			preparedStatement.setNString(4, phone);
			preparedStatement.setNString(5, province);
            // Thực hiện truy vấn INSERT
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Dữ liệu đã được thêm thành công!");
            } else {
                System.out.println("Kh ông có dữ liệu nào được thêm.");
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static int get_room_id_using_name(String room_name) {
		int result = 0;
		try {
			Statement statement = connect_database.connect.createStatement();
			String query = "select room.room_id from host_room inner join room on host_room.room_id = room.room_id \r\n"
					+ "where room.room_host_id = '" + room_name + "' and host_room.host_id = " + user.user_id;
	        ResultSet resultSet = statement.executeQuery(query);
	        String s = null;
	        // Hiển thị dữ liệu trong console
	        while (resultSet.next()) {
	        	result = resultSet.getInt(1);
	        }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static void update_rent(String room_name, int boarder_id) {
		int room_id = get_room_id_using_name(room_name);
		String query = "update rent \r\n"
				+ "set rent.room_id = " + room_id
				+ " where rent.host_id = " + user.user_id + " and rent.boarder_id = " + boarder_id;
		try (PreparedStatement preparedStatement = connect_database.connect.prepareStatement(query)) {
            // Thực hiện truy vấn INSERT
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Dữ liệu đã được thêm thành công!");
            } else {
                System.out.println("Kh ông có dữ liệu nào được thêm.");
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void delete_rent() {
		String query = "delete from rent \r\n"
				+ "where rent.host_id = ? and rent.boarder_id = ?";
		try (PreparedStatement preparedStatement = connect_database.connect.prepareStatement(query)) {
            // Thực hiện truy vấn INSERT
            
            preparedStatement.setInt(1, user.user_id);
            preparedStatement.setInt(2, user.current_boarder_id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dữ liệu đã được thêm thành công!");
            } else {
                System.out.println("Kh ông có dữ liệu nào được thêm.");
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void delete_boarder() {
		String query = "delete from boarder where boarder.boarder_id = ?";
		try (PreparedStatement preparedStatement = connect_database.connect.prepareStatement(query)) {
            // Thực hiện truy vấn INSERT
           
            preparedStatement.setInt(1, user.current_boarder_id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dữ liệu đã được thêm thành công!");
            } else {
                System.out.println("Kh ông có dữ liệu nào được thêm.");
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
