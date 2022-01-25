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

	public ArrayList<Customer> searchCustomerByTel(String tel) {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Customer> list = new ArrayList<Customer>();
		try {
			if (con != null) {
				String sql = "SELECT CUSTID,CUSTNAME,KANA,ADDRESS FROM customer WHERE TEL=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, tel);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int custId = rs.getInt("CUSTID");
					String custName = rs.getString("CUSTNAME");
					String kana = rs.getString("KANA");
					String address = rs.getString("ADDRESS");
					Customer customer = new Customer(custId, custName, kana, tel, address);
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

	public ArrayList<Customer> searchCustomerByKana(String kana) {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Customer> list = new ArrayList<Customer>();
		try {
			if (con != null) {
				String sql = "SELECT CUSTID,CUSTNAME,KANA,TEL,ADDRESS FROM customer WHERE KANA LIKE ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + kana + "%");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int custId = rs.getInt("CUSTID");
					String custName = rs.getString("CUSTNAME");
					String tel = rs.getString("tel");
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
