package action;


import dao.SpotSearchDBAccess;


public class SpotAddAction {

	public String execute(String[] date) {
		SpotSearchDBAccess spot = new SpotSearchDBAccess();
		return spot.spotAdd(date);
	}
}
