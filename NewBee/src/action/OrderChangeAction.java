package action;


import dao.OrderUpdataDBAccess;


public class OrderChangeAction {
	OrderUpdataDBAccess orderDBA = new OrderUpdataDBAccess();
	public String execute(String[] data) {
			return orderDBA.OrderChange(data);
	}
	
	public String[][] execute(String data) {
			return orderDBA.orderSearch(data);
	}
}
