package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;

public class LiveAddDBAccess {
	// DBとの接続を確立する
	String result = null;

	// DBとの接続を確立する
	private Connection createConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// &useSSL=false URLにつけると例外を消せる
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/20jy0238?characterEncoding=UTF-8&useSSL=false", "root",
					"aini389125");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			result = "DB接続時にエラーが発生しました。(Spot)" + "\n" + "ご確認ください。";
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

	public String liveAdd(String[] data) {

		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int rs = -1;
		try {
			if (con != null) {
				String sql = "INSERT INTO Live(name,startTime,comment,img)"
						+ " VALUES(?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, data[0]);
				pstmt.setString(2, data[1]);
				pstmt.setString(3, data[2]);
				pstmt.setString(4, data[3]);
				rs = pstmt.executeUpdate(sql);
				if (rs == 0) {
					result = "ライブ観光地は既に存在しています。。" + "\n" + "ご確認ください。";
				} else if (rs == 1) {
					result = "新規完了しました。";
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
	public String[][] liveUpdata(String data) {

		String[][] tableData = null;
		int i = 0;

		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if (con != null) {
				String sql = "SELECT * FROM Live WHERE hotelName LIKE ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+data+"%");
				rs = pstmt.executeQuery(sql);
			}
			while (rs.next()) {
				tableData[i][0] = rs.getString("liveId");
				tableData[i][1] = rs.getString("liveTheme");
				tableData[i][2] = rs.getString("startTime");
				tableData[i][3] = rs.getString("comment");
				tableData[i][4] = rs.getString("img");
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

}
