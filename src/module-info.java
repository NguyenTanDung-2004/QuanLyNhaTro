module Quan_Ly_Nha_Tro {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires com.microsoft.sqlserver.jdbc;
	requires java.mail;
	
	opens login to javafx.graphics, javafx.fxml;
	opens home to javafx.graphics, javafx.fxml;
	opens show_alert to javafx.graphics, javafx.fxml;
}
