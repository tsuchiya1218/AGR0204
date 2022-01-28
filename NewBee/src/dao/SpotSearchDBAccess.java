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

	public ArrayList<Spot> searchSpotByCustomer(String[] tel) {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Spot> list = new ArrayList<Spot>();

		Spot customer = new Spot(11111, "草津温泉", "群馬県吾妻郡草津町",
				" JR東日本吾妻線：長野原草津口駅", "9:00-17:00","1.jpg");
		list.add(customer);
//		try {
//			if (con != null) {
//				String sql = "SELECT CUSTID,CUSTNAME,KANA,ADDRESS FROM customer WHERE TEL=? AND KANA LIKE ?";
//				pstmt = con.prepareStatement(sql);
//				pstmt.setString(1, tel);
//				pstmt.setString(2, "%" + kana + "%");
//				rs = pstmt.executeQuery();
//				while (rs.next()) {
//					int custId = rs.getInt("CUSTID");
//					String custName = rs.getString("CUSTNAME");
//					String address = rs.getString("ADDRESS");
//					String custKana = rs.getString("KANA");
//					Customer customer = new Customer(custId, custName, custKana, tel, address);
//					list.add(customer);
//				}
//			}
//		} catch (SQLException e) {
//			System.out.println("DB接続時にエラーが発生しました。(Customer)");
//			e.printStackTrace();
//		} finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//			} catch (SQLException e) {
//				System.out.println("DB切断時にエラーが発生しました。");
//				e.printStackTrace();
//			}
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//			} catch (SQLException e) {
//				System.out.println("DB切断時にエラーが発生しました。");
//				e.printStackTrace();
//			}
//		}
//		closeConnection(con);
		return list;
	}

	public ArrayList<Customer> searchCustomerByCustomer(String tel, String kana) {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Customer> list = new ArrayList<Customer>();
		try {
			if (con != null) {
				String sql = "SELECT CUSTID,CUSTNAME,KANA,ADDRESS FROM customer WHERE TEL=? AND KANA LIKE ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, tel);
				pstmt.setString(2, "%" + kana + "%");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int custId = rs.getInt("CUSTID");
					String custName = rs.getString("CUSTNAME");
					String address = rs.getString("ADDRESS");
					String custKana = rs.getString("KANA");
					Customer customer = new Customer(custId, custName, custKana, tel, address);
					list.add(customer);
				}
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
		return list;
	}
}
