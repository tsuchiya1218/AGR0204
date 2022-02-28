package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;
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
	
	public String reviewsCancel(String[] data) {

		Connection con = createConnection();
		PreparedStatement pstmt = null;
		
		int rs = -1;
		try {
			if (con != null) {
				String sql = "DELETE FROM review WHERE customerid  = ? AND orderid = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, data[0]);
				pstmt.setString(2, data[1]);
				rs = pstmt.executeUpdate();
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
				String sql = "UPDATE Review SET flag = 1 WHERE customerid = ? AND orderid = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, data[0]);
				pstmt.setString(2, data[1]);
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
	
	@SuppressWarnings("null")
	public ArrayList<Reviews> reviewsSearch() {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Reviews> list = new ArrayList<Reviews>();
		try {
			if (con != null) {
				//SQL文法は変更する必要がある
				String sql = "SELECT review.customerid, customer.name, order_detail.orderid, review.reviews, review.date, "
						+ "order_detail.itemid FROM review INNER JOIN customer ON customer.customerid = review.customerid"
						+ " INNER JOIN order_detail ON review.orderid = order_detail.orderid";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					
					String customerId = rs.getString("customerid");
					String customerName = rs.getString("name");
					String orderId = rs.getString("orderid");
					String review = rs.getString("reviews");
					String date = rs.getString("date");
					Reviews reviews = new Reviews(customerId, customerName, orderId,review,date);
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
