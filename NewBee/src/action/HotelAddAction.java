package action;

import java.util.ArrayList;

import dao.CustomerSearchDBAccess;
import model.Customer;
import model.OrderControlUtility;

public class HotelAddAction {
	public String[][] execute(String[] data) {
		ArrayList<Customer> list = new ArrayList<Customer>();
		CustomerSearchDBAccess customerDBA = new CustomerSearchDBAccess();
		// data[0]＝電話番号 data[1]=カナ
		if (!data[0].equals("") && data[1].equals("")) {
			list = customerDBA.searchCustomerByTel(data[0]);
		}
		if (data[0].equals("") && !data[1].equals("")) {
			list = customerDBA.searchCustomerByKana(data[1]);
		}
		if (!data[0].equals("") && !data[1].equals("")) {
			list = customerDBA.searchCustomerByCustomer(data[0], data[1]);
		}
		if (list != null && list.size() != 0) {
			return OrderControlUtility.customerToArray(list);
		} else {
			return null;
		}
	}
}
