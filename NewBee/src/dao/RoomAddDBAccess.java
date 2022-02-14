package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;
import model.Spot;

public class RoomAddDBAccess {
	private String result;

	// DBとの接続を確立する
	private Connection createConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// &useSSL=false URLにつけると例外を消せる
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/20jy0238?characterEncoding=UTF-8&useSSL=false", "root",
					"aini389125");
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

	public String roomAdd(String[] data) {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int rs = -1;
		try {
			if (con != null) {
				String sql = "INSERT INTO Room(name,num,comment,type,path) VALUES(?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, data[0]);
				pstmt.setString(1, data[1]);
				pstmt.setString(1, data[2]);
				pstmt.setString(1, data[3]);
				pstmt.setString(1, data[4]);
				rs = pstmt.executeUpdate(sql);
				if (rs == 0) {
					result =  "部屋タイプは既に存在しています" + "\n" + "ご確認ください。";
				} else if (rs == 1) {
					result = "新規完了しました。";
				}
			}
		} catch (SQLException e) {
			System.out.println("DB接続時にエラーが発生しました。(Customer)");
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
	
	public String roomUpdate(String[] data) {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int rs = -1;
		try {
			if (con != null) {
				//SQL修正する必要がある
				String sql = "UPDATE Room SET count = ? , remaind = ? WHERE type = ? AND hotelid = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, data[0]);
				pstmt.setString(1, data[1]);
				pstmt.setString(1, data[2]);
				pstmt.setString(1, data[3]);
				rs = pstmt.executeUpdate(sql);
				if (rs == 0) {
					result =  "更新失敗しました。" + "\n" + "ご確認ください。";
				} else if (rs == 1) {
					result = "更新完了しました。";
				}
			}
		} catch (SQLException e) {
			System.out.println("DB接続時にエラーが発生しました。(Customer)");
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
	
	
	public String[][] roomSearch(String data) {

		String[][] tableData = null;
		int i = 0;

		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if (con != null) {
				String sql = "SELECT * FROM Room WHERE hotelName LIKE ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+data+"%");
				rs = pstmt.executeQuery(sql);
			}
			while (rs.next()) {
				tableData[i][0] = rs.getString("hotelId");
				tableData[i][1] = rs.getString("hotelName");
				tableData[i][2] = rs.getString("address");
				tableData[i][3] = rs.getString("access");
				tableData[i][4] = rs.getString("checkIn");
				tableData[i][5] = rs.getString("checkOut");
				tableData[i][6] = rs.getString("comment");
				tableData[i][7] = rs.getString("img");
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
