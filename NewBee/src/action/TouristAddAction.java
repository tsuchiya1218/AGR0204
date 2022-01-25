package action;

import dao.TouristAddDBAccess;

public class TouristAddAction {

	public String execute(String[] date) {
		TouristAddDBAccess tourist = new TouristAddDBAccess();
		return tourist.addTourist(date);
	}
}
