package dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Reviews;

public class ReviewsSearchDBAccess {
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
			result = "DB接続時にエラーが発生しました。(Reviews)" + "\n" + "ご確認ください。";
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
	
	public String reviewsCancel(String[] data) {

		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int rs = -1;
		try {
			if (con != null) {
				String sql = "DELETE FROM Reviews WHERE userID = ? AND itemId = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, data[0]);
				pstmt.setString(2, data[1]);
				rs = pstmt.executeUpdate(sql);
				if (rs == 0) {
					result = "error" + "\n" + "ご確認ください。";
				} else if (rs == 1) {
					result = "削除完了しました。";
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

	public String reviewsOk(String[] data) {

		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int rs = -1;
		try {
			if (con != null) {
				String sql = "UPDATE Reviews SET flag = 1 WHERE userID = ? AND itemId = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, data[0]);
				pstmt.setString(2, data[1]);
				rs = pstmt.executeUpdate(sql);
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
	
	@SuppressWarnings("null")
	public ArrayList<Reviews> reviewsSearch() {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Reviews> list = new ArrayList<Reviews>();
		try {
			if (con != null) {
				//SQL文法は変更する必要がある
				String sql = "SELECT * FROM Reviews";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					String userId = rs.getString("userId");
					String custName = rs.getString("CUSTNAME");
					String itemCode = rs.getString("itemCode");
					String course = rs.getString("course");
					String Comment = rs.getString("Comment");
					String time = rs.getString("time");
					Reviews reviews = new Reviews(userId, custName, itemCode, course, Comment,time);
					list.add(reviews);
				}
			}
		} catch (SQLException e) {
			System.out.println("DB接続時にエラーが発生しました。(Reviews)");
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
}
