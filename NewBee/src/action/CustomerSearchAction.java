package action;

import java.util.ArrayList;

import dao.CustomerSearchDBAccess;
import model.ControlUtility;
import model.Customer;
import model.ControlUtility;

public class CustomerSearchAction {
	CustomerSearchDBAccess customerDBA = new CustomerSearchDBAccess();
	public String[][] execute(String data) {
		ArrayList<Customer> list = new ArrayList<Customer>();
			list = customerDBA.searchCustomerByCustomer(data);
		if (list != null && list.size() != 0) {
			return ControlUtility.customerToArray(list);
		} else {
			return null;
		}
	}

	public String executeUpdate(String data) {
		return customerDBA.customerUpdate(data);
	}
}
