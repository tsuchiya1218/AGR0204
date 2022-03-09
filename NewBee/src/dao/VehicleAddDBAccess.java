package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;

public class VehicleAddDBAccess {
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

	public String vehicleUpdate(String[] data) {

		Connection con = createConnection();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		int rs = -1;

		try {
			if (con != null) {

				String sql = "UPDATE item SET price = ? WHERE itemid = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, data[4]);
				pstmt.setString(2, data[5]);
				rs = pstmt.executeUpdate();


				String sql2 = "UPDATE transportation SET dstation = ? , "
						+ "astation = ?, dtime = ?, atime = ?"
						+ " WHERE itemid = ?";
				pstmt1 = con.prepareStatement(sql2);
				pstmt1.setString(1, data[0]);
				pstmt1.setString(2, data[1]);
				pstmt1.setString(3, data[2]);
				pstmt1.setString(4, data[3]);
				pstmt1.setString(5, data[5]);
				rs = pstmt1.executeUpdate();
				if (rs == 0) {
					result = "既に存在しています。" + "\n" + "ご確認ください。";
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

	public String vehicleAdd(String[] data) {

		Connection con = createConnection();
		int itemId = (int)System.currentTimeMillis()/1000;
		PreparedStatement pstmt = null;
		int rs = -1;
		try {
			if (con != null) {

				PreparedStatement pstmt2 = null;
				String itemSql = "INSERT INTO Item VALUES(?,?,?)";
				pstmt2 = con.prepareStatement(itemSql);
				pstmt2.setInt(1,itemId);
				pstmt2.setString(2, "4");
				pstmt2.setString(3, data[5]);
				rs = pstmt2.executeUpdate();



				String sql = "INSERT INTO Transportation(itemid,name,dstation,astation,dtime,atime,ttypeid)"
						+ " VALUES(?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, itemId);
				pstmt.setString(2, data[0]);
				pstmt.setString(3, data[1]);
				pstmt.setString(4, data[2]);
				pstmt.setString(5, data[3]);
				pstmt.setString(6, data[4]);
				pstmt.setString(7, data[6]);
				rs = pstmt.executeUpdate();
				if (rs == 0) {
					result = "既に存在しています。" + "\n" + "ご確認ください。";
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
	public String[][] vehicleSearch(String data) {

		String[][] tableData = new String[20][8];
		int i = 0;

		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if (con != null) {
				String sql = "SELECT name,dstation,astation,dtime,atime,ttypeid,item.price, Transportation.itemid FROM Transportation INNER JOIN item ON item.itemid = transportation.itemid"
						+ " WHERE name LIKE ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+data+"%");
				rs = pstmt.executeQuery();
			}
			while (rs.next()) {
				tableData[i][0] = rs.getString("name");
				tableData[i][1] = rs.getString("dstation");
				tableData[i][2] = rs.getString("astation");
				tableData[i][3] = rs.getString("dtime");
				tableData[i][4] = rs.getString("atime");
				tableData[i][5] = rs.getString("ttypeid");
				tableData[i][6] = rs.getString("price");
				tableData[i][7] = rs.getString("itemid");
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
