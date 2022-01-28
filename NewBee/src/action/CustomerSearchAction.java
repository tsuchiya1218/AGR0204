package action;

import java.util.ArrayList;

import dao.CustomerSearchDBAccess;
import model.Customer;
import model.OrderControlUtility;

public class CustomerSearchAction {
	public String[][] execute(String[] data) {
		ArrayList<Customer> list = new ArrayList<Customer>();
		CustomerSearchDBAccess customerDBA = new CustomerSearchDBAccess();
		// data
			list = customerDBA.searchCustomerByCustomer(data[0], data[1]);

		if (list != null && list.size() != 0) {
			return OrderControlUtility.customerToArray(list);
		} else {
			return null;
		}
	}
}
