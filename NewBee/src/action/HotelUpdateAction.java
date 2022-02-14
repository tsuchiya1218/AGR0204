package action;

import dao.HotelAddDBAccess;


public class HotelUpdateAction {
	HotelAddDBAccess hotelDBA = new HotelAddDBAccess();
	public String execute(String[] data) {
			return hotelDBA.hotelAdd(data);
	}
	
	public String[][] execute(String data) {
			return hotelDBA.hotelUpdata(data);
	}
}
