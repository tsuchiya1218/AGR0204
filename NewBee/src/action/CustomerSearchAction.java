package action;

import java.util.ArrayList;

import dao.CustomerSearchDBAccess;
import model.Customer;
import model.ControlUtility;

public class CustomerSearchAction {
	public String[][] execute(String[] data){
		ArrayList<Customer> list = new ArrayList<Customer>();
		CustomerSearchDBAccess customerDBA = new CustomerSearchDBAccess();
		//data[0]＝電話番号　 data[1]=カナ　　
		if(data[0]!= "" && data[1] == "") {
			list=customerDBA.searchCustomerByTel(data[0]);
		}else if(data[0] == "" && data[1]!="") {
			list=customerDBA.searchCustomerByKana(data[1]);
		}else {
			list=customerDBA.searchCustomerByCustomer(data[0], data[1]);
		}
		if(list!=null && list.size()!=0) {
			return ControlUtility.customerToArray(list);
		}else {
			return null;
		}
	}
}

