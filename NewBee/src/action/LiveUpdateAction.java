package action;

import dao.LiveAddDBAccess;

public class LiveUpdateAction {
	LiveAddDBAccess liveDBA = new LiveAddDBAccess();
	public String executeAdd(String[] data) {
			return liveDBA.liveAdd(data);
	}

	
	public String executeUpdate(String[] data) {
			return liveDBA.liveUpdate(data);
	}
	public String[][] executeSearch(String data) {
		return liveDBA.liveSearch(data);
}
}
