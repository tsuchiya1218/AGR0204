package dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderUpdataDBAccess {
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

	public String OrderChange(String[] data) {

		Connection con = createConnection();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		int rs = -1;
		try {
			if (con != null) {
				String sql = "UPDATE item SET price = ? WHERE itemid = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, data[2]);
				pstmt.setString(2, data[3]);
				rs = pstmt.executeUpdate();


				String sql2 = "UPDATE Order_detail SET starttime = ?, endtime = ? WHERE itemid = ? AND orderid = ?";
				pstmt1 = con.prepareStatement(sql2);
				pstmt1.setString(1, data[0]);
				pstmt1.setString(2, data[1]);
				pstmt1.setString(3, data[3]);
				pstmt1.setString(4, data[4]);
				rs = pstmt1.executeUpdate();
				if (rs == 0) {
					result = "変更失敗しました。" + "\n" + "ご確認ください。";
				} else if (rs == 1) {
					result = "変更完了しました。";
				}
			}

		} catch (SQLException e) {
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

	@SuppressWarnings("null")
	public String[][] orderSearch(String data) {

		String[][] tableData = new String[20][7];
		int i = 0;

		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if (con != null) {
				String sql = "SELECT customer.tel ,customer.name , order_code.orderid ,order_detail.starttime ,order_detail.endtime , item.price ,order_detail.itemid FROM order_code INNER JOIN customer ON order_code.customerid = customer.customerid INNER JOIN Order_detail ON Order_detail.orderid = order_code.orderid INNER JOIN item ON item.itemid = order_detail.itemid WHERE customer.tel = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, data);
				rs = pstmt.executeQuery();
			}
			while (rs.next()) {
				tableData[i][0] = rs.getString("tel");
				tableData[i][1] = rs.getString("name");
				tableData[i][2] = rs.getString("orderid");
				tableData[i][3] = rs.getString("starttime");
				tableData[i][4] = rs.getString("endtime");
				tableData[i][5] = rs.getString("price");
				tableData[i][6] = rs.getString("itemid");
			}
		} catch (SQLException e) {
			System.out.println("DB接続時にエラーが発生しました。(Order)");
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

}
