package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;
import model.Reviews;

public class BookingDBAccess {
	// DBとの接続を確立する
	String result = null;

	// DBとの接続を確立する
	private Connection createConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// &useSSL=false URLにつけると例外を消せる
			con = DriverManager.getConnection("jdbc:mysql://10.42.129.142/20gr24?characterEncoding=UTF-8&useSSL=false", "20gr24",
					"20gr24");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバが見つかりません");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DBに接続時にエラーが発生しました。");
			e.printStackTrace();
			result = "DB接続時にエラーが発生しました。(Room)" + "\n" + "ご確認ください。";
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

	public String[][] bookingAll() {

		String[][] tableData = new String[10][6];
		int i = 0;

		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if (con != null) {
				String sql = "SELECT customer.tel ,customer.name , order_code.orderid ,order_code.orderdate ,item.price ,customer.customerid ,order_detail.itemid FROM order_code INNER JOIN customer ON order_code.customerid = customer.customerid INNER JOIN Order_detail ON Order_detail.orderid = order_code.orderid INNER JOIN item ON item.itemid = order_detail.itemid WHERE order_detail.flag = 0";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
			}
			while (rs.next()) {
				tableData[i][0] = rs.getString("tel");
				tableData[i][1] = rs.getString("name");
				tableData[i][2] = rs.getString("orderid");
				tableData[i][3] = rs.getString("orderdate");
				tableData[i][4] = rs.getString("price");
				tableData[i][5] = rs.getString("itemid");
				i++;
			}
		} catch (SQLException e) {
			System.out.println("DB接続時にエラーが発生しました。(order)");
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
				String sql ="DELETE FROM Order_detail where itemid = ? AND orderid = ?";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, data[0]);
				pstmt.setString(2, data[1]);
				rs = pstmt.executeUpdate();
			}
			if (rs == 1) {
				result = "取消しました。";
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
				String sql = "UPDATE Order_detail SET flag = 1 where itemid = ? AND orderid = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, data[0] );
				pstmt.setString(2, data[1]);
				rs = pstmt.executeUpdate();
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
