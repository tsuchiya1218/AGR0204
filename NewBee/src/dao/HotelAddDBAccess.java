package dao;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelAddDBAccess {
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

	public String hotelAdd(String[] data) {

		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int hotelItemId = (int)System.currentTimeMillis()/1000;
		int rs = -1;
		try {
			if (con != null) {

				String sql = "INSERT INTO Hotel(hotelid,name,address,comment,access,checkin,checkout,img,spotid)"
						+ " VALUES(?,?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, hotelItemId);
				pstmt.setString(2, data[0]);
				pstmt.setString(3, data[1]);
				pstmt.setString(4, data[2]);
				pstmt.setString(5, data[3]);
				pstmt.setString(6, data[4]);
				pstmt.setString(7, data[5]);
				pstmt.setString(8, data[6]);
				pstmt.setString(9, data[7]);
				rs = pstmt.executeUpdate();
				if (rs == 0) {
					result = "観光地は既に存在しています。。" + "\n" + "ご確認ください。";
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
	public String hotelUpdata(String[] data) {

		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int rs = -1;
		try {
			if (con != null) {
				//SQL修正する必要がある
				String sql = "UPDATE hotel SET address = ? , comment = ?,access = ? , checkin = ?, checkout = ? WHERE hotelid = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, data[1]);
				pstmt.setString(1, data[2]);
				pstmt.setString(1, data[3]);
				pstmt.setString(1, data[4]);
				pstmt.setString(1, data[5]);
				pstmt.setString(1, data[0]);
				rs = pstmt.executeUpdate();
				if (rs == 0) {
					result =  "更新失敗しました。" + "\n" + "ご確認ください。";
				} else if (rs == 1) {
					result = "更新完了しました。";
				}
			}
		} catch (SQLException e) {
			System.out.println("DB接続時にエラーが発生しました。(Live)");
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

	public String[][] hotelSearch(String data) {

		String[][] tableData = new String[10][7];
		int i = 0;

		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if (con != null) {
				String sql = "SELECT * FROM hotel WHERE name LIKE ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+data+"%");
				rs = pstmt.executeQuery(sql);
			}
			while (rs.next()) {
				tableData[i][0] = rs.getString("hotelid");
				tableData[i][1] = rs.getString("name");
				tableData[i][2] = rs.getString("address");
				tableData[i][3] = rs.getString("comment");
				tableData[i][4] = rs.getString("access");
				tableData[i][5] = rs.getString("checkin");
				tableData[i][6] = rs.getString("checkout");

			}
		} catch (SQLException e) {
			System.out.println("DB接続時にエラーが発生しました。(Hotel)");
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
