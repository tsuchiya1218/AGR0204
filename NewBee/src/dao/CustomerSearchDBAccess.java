package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;

public class CustomerSearchDBAccess {
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

	public String customerUpdate(String data) {

		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int rs = -1;
		try {
			if (con != null) {
				String sql = "update Customer set flag = 1 where customerid = ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, data);
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

	public ArrayList<Customer> searchCustomerByCustomer(String data) {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Customer> list = new ArrayList<Customer>();
		try {
			if (con != null) {
				String sql = "SELECT customerid,name,email,address,tel FROM Customer WHERE name LIKE ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + data + "%");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					String customerid = rs.getString("customerid");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String address = rs.getString("address");
					String tel = rs.getString("tel");
					Customer customer = new Customer(customerid,name, email,address,tel);
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
