package action;


import java.util.ArrayList;

import dao.SpotSearchDBAccess;
import model.ControlUtility;
import model.Spot;


public class SpotUpdateAction {
	ArrayList<Spot> list = new ArrayList<Spot>();
	SpotSearchDBAccess spot = new SpotSearchDBAccess();
	public String[][] execute(String date) {
		list = spot.spotSearch(date);
		if (list != null && list.size() != 0) {
			return ControlUtility.spotToArray(list);
		} else {
			return null;
		}
	}

	
	public String executeUpdate(String[] data) {
		return spot.spotUpdate(data);
}
}
