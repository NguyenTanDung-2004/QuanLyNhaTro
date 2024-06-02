package user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.connect_database;

public class user {
	public static int user_id;
	public static String phone;
	public static String location;
	public static String citizen_id;
	public static String email;
	// name, flag, date, email, province, room, phone, cccd, room_money, boarder_id
	public static ArrayList<ArrayList<String>> all_data_all_boarder;
	public static ArrayList<String> get_data_1_row(int i){
		return all_data_all_boarder.get(i);
	}
	public static void display(ArrayList<String> arraylist) {
		for (int i = 0 ; i < arraylist.size(); i++) {
			System.out.println(arraylist.get(i));
		}
	}
	public static ArrayList<ArrayList<String>> data_filter;
	public static ArrayList<String> link_text;
	public static List<String> list_room_of_user;
	public static List<List<Object>> data_room_of_user;
	public static List<List<Object>> id_money_room;
	public static int current_boarder_id;
	//name, province, owe, date
	public static void main(String args[]) {
	}
}
