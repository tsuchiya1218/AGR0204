package action;

import dao.BookingDBAccess;

public class BookingCheckAction {
	public String execute(String data) {
		String result;
		BookingDBAccess customerDBA = new BookingDBAccess();
		result = customerDBA.bookingByOrderId(data);
		return result;
	}
}
