package action;

import java.util.ArrayList;

import dao.CustomerSearchDBAccess;
import dao.ReviewsSearchDBAccess;
import model.Customer;
import model.Reviews;
import model.ControlUtility;

public class ReviewsCheckAction {
	ReviewsSearchDBAccess reviewsDBA = new ReviewsSearchDBAccess();
	public String[][] execute() {
		ArrayList<Reviews> list = new ArrayList<Reviews>();
		list = reviewsDBA.reviewsSearch();
		if (list != null) {
			return ControlUtility.reviewsToArray(list);
		} else {
			return null;
		}
	}
	public String execute(String[] data) {
		return reviewsDBA.reviewsCancel(data);
}
	public String executeOk(String[] data) {
		return reviewsDBA.reviewsOk(data);
}
}
