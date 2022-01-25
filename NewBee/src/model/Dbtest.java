package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Dbtest {
	Connection con = null;
	PreparedStatement pstme;

	@SuppressWarnings("unused")
	private void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/20jy0238?characterEncoding=UTF-8&useSSL=false", "", "");
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
