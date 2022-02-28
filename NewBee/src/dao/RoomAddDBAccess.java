package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomAddDBAccess {
	private String result;

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

	public String roomAdd(String[] data) {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		int itemid =  (int)System.currentTimeMillis()/1000;
		int rs = -1;
		try {
			if (con != null) {
				
				String sql = "INSERT INTO item(itemid,itypeid,price) VALUES(?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, itemid);
				pstmt.setString(2,"2");
				pstmt.setString(3, data[5]);
				rs = pstmt.executeUpdate();
				
				
				
				String sql2 = "INSERT INTO room(itemid,type,img,count,remaind,comment,hotelid) VALUES(?,?,?,?,?,?,?)";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1, itemid);
				pstmt2.setString(2, data[0]);
				pstmt2.setString(3, data[1]);
				pstmt2.setString(4, data[2]);
				pstmt2.setString(5, data[2]);
				pstmt2.setString(6, data[3]);
				pstmt2.setString(7, data[4]);
				rs = pstmt2.executeUpdate();
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
				String sql = "UPDATE room SET count = ? , remaind = ? , comment = ?"
						+ " WHERE hotelid = ? AND  itemid = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, data[0]);
				pstmt.setString(2, data[1]);
				pstmt.setString(3, data[2]);
				pstmt.setString(4, data[3]);
				pstmt.setString(5, data[4]);
				rs = pstmt.executeUpdate();
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
	
	
	@SuppressWarnings("resource")
	public String[][] roomSearch(String data) {

		String[][] tableData = new String[10][7];
		int i = 0;

		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if (con != null) {
				String sql2 = "SELECT hotel.hotelid, hotel.name, room.itemid, room.count, room.type, "
						+ " room.remaind , room.comment FROM room INNER JOIN hotel ON room.hotelid = hotel.hotelid"
						+ " WHERE hotel.name LIKE ?";
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, "%"+data+"%");
				rs = pstmt.executeQuery();
			}
			while (rs.next()) {
				tableData[i][0] = rs.getString("hotelid");
				tableData[i][1] = rs.getString("name");
				tableData[i][2] = rs.getString("itemid");
				tableData[i][3] = rs.getString("count");
				tableData[i][4] = rs.getString("type");
				tableData[i][5] = rs.getString("comment");
				tableData[i][6] = rs.getString("remaind");
				i++;
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
