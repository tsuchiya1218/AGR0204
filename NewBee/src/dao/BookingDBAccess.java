package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;

public class BookingDBAccess {
	// DBとの接続を確立する
	String result;
	private Connection createConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// &useSSL=false URLにつけると例外を消せる
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/20jy0238?characterEncoding=UTF-8&useSSL=false", "root",
					"aini3895");
		} catch (ClassNotFoundException e) {
			result = ":管理者まで連絡してください。";
			System.out.println("JDBCドライバが見つかりません");
			e.printStackTrace();
		} catch (SQLException e) {
			result = ":管理者まで連絡してください。";
			System.out.println("DBに接続時にエラーが発生しました。");
			e.printStackTrace();
		}
		return con;
	}

	// DBとの接続を閉じる
	private void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("DB切断時にエラーが発生しました。");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("null")
	public String[][] bookingAll() {

		String[][] tableData = null;
		int i = 0;

		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if (con != null) {
				String sql = "SELECT * FROM booking";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery(sql);
			}
			while (rs.next()) {
				tableData[i][0] = rs.getString("tel");
				tableData[i][1] = rs.getString("name");
				tableData[i][2] = rs.getString("orderCode");
				tableData[i][3] = rs.getString("type");
				tableData[i][4] = rs.getString("time");
				tableData[i][5] = rs.getString("num");
			}
		} catch (SQLException e) {
			System.out.println("DB接続時にエラーが発生しました。(Customer)");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		closeConnection(con);
		return tableData;
	}

	public String bookingCancel(String[] data) {


		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int rs = -1;
		try {
			if (con != null) {
				String sql ="DELETE Order where orderCode = ? AND tel = ?";
						
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, data[0]);
				pstmt.setString(1, data[1]);
				rs = pstmt.executeUpdate(sql);
			}
			if (rs == 1) {
				result = "キャンセルしました。";
			} else {
				result = ":管理者まで連絡してください。";
			}
		} catch (SQLException e) {
			System.out.println("DB接続時にエラーが発生しました。(Booking)");
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		closeConnection(con);
		return result;
	}
	
	public String executeRegister(String[] data) {


		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int rs = -1;
		try {
			if (con != null) {
				String sql = "UPDATE Order SET flag = 1 where orderCode = ? AND tel = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, data[0] );
				pstmt.setString(2, data[1]);
				rs = pstmt.executeUpdate(sql);
			}
			if (rs == 1) {
				result = "注文登録しました。";
			} else if(result == null) {
				result = ":管理者まで連絡してください。";
			}
		} catch (SQLException e) {
			System.out.println("DB接続時にエラーが発生しました。(Booking)");
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		closeConnection(con);
		return result;
	}
	
}
