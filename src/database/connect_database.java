package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class connect_database {
	public static Connection connect;
	public Connection connect_database() {
		Connection connect1 = null;
		try {
			DriverManager.registerDriver(new SQLServerDriver());
			String url = "jdbc:sqlserver://localhost:1433;databaseName=quan_ly_nha_tro;user=sa;password=12345;trustServerCertificate = true;";
			connect1 = DriverManager.getConnection(url);
			System.out.println("connect thanh cong");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect1;
	}
	public connect_database() {
		this.connect = connect_database();
	}
}
