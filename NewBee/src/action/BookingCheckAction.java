package action;

import dao.BookingDBAccess;

public class BookingCheckAction {
	
	BookingDBAccess bookingDBA = new BookingDBAccess();
	
	public String executeCancel(String[] data) {
		String result = null;
			result = bookingDBA.bookingCancel(data);
			return result;
		}
	
	public String executeRegister(String[] data) {
		String result = null;
			result = bookingDBA.executeRegister(data);
			return result;
		}
	
	public String[][] execute() {
			String[][] result = null;
			result = bookingDBA.bookingAll();
		return result;
	}
}
