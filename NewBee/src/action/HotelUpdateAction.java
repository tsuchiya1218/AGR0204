package action;

import dao.HotelAddDBAccess;


public class HotelUpdateAction {
	HotelAddDBAccess hotelDBA = new HotelAddDBAccess();
	public String execute(String[] data) {
			return hotelDBA.hotelAdd(data);
	}

	public String[][] executeSearch(String data) {
			return hotelDBA.hotelSearch(data);
	}
	public String executeUpdate(String[] data) {
		return hotelDBA.hotelUpdata(data);
}
}
