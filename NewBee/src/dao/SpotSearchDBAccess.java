package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;
import model.Spot;

public class SpotSearchDBAccess {
	String result;
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

	public String spotAdd(String[] data) {


		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int rs = -1;
		try {
			if (con != null) {
				String sql = "insert into spot values(?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,(int)System.currentTimeMillis()/1000);
				pstmt.setString(2,data[0]);
				pstmt.setString(3,data[1]);
				pstmt.setString(4,data[2]);
				pstmt.setString(5,data[3]);
				pstmt.setString(6,data[4]);
				pstmt.setString(7,data[5]);
				pstmt.setInt(8,Integer.parseInt(data[6]));
				rs = pstmt.executeUpdate();
				if (rs == 0) {
					result = "error" + "\n" + "ご確認ください。";
				} else if (rs == 1) {
					result = "処理完了しました。";
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


	public ArrayList<Spot> spotSearch(String data) {

		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Spot> list = new ArrayList<Spot>();
		try {
			if (con != null) {
				String sql = "SELECT * FROM spot WHERE name LIKE ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+data+"%");
				rs = pstmt.executeQuery();
			}
			while (rs.next()) {
				String spotId = rs.getString("spotid");
				String name = rs.getString("name");
				String img = rs.getString("img");
				String address = rs.getString("address");
				String access = rs.getString("access");
				String time = rs.getString("time");
				String comment = rs.getString("comment");
				Spot spot = new Spot( spotId, name, address, access, time, comment, img);
				list.add(spot);
			}
		} catch (SQLException e) {
			System.out.println("DB接続時にエラーが発生しました。(spot)");
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
		return list;
	}


	public String spotUpdate(String[] data) {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int rs = -1;
		try {
			if (con != null) {
				//SQL修正する必要がある
				String sql = "UPDATE spot SET name = ? , address = ? , access = ? , time = ? , comment = ? WHERE spotid = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, data[0]);
				pstmt.setString(2, data[1]);
				pstmt.setString(3, data[2]);
				pstmt.setString(4, data[3]);
				pstmt.setString(5, data[4]);
				pstmt.setInt(6, Integer.parseInt(data[5]));
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
}
