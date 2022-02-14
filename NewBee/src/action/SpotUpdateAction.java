package action;

import java.util.ArrayList;

import dao.SpotAddDBAccess;
import dao.SpotSearchDBAccess;
import model.ControlUtility;
import model.Spot;

public class SpotUpdateAction {

	private ArrayList<Spot> list;

	public String[][] execute(String[] date) {
		SpotSearchDBAccess spot = new SpotSearchDBAccess();
		list = spot.searchSpotByCustomer(date);
		if (list != null && list.size() != 0) {
			return ControlUtility.spotToArray(list);
		} else {
			return null;
		}
	}
}
