package action;

import dao.SpotAddDBAccess;

public class SpotAddAction {

	public String execute(String[] date) {
		SpotAddDBAccess tourist = new SpotAddDBAccess();
		return tourist.addTourist(date);
	}
}
