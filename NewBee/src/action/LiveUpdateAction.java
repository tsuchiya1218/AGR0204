package action;

import dao.LiveAddDBAccess;

public class LiveUpdateAction {
	LiveAddDBAccess liveDBA = new LiveAddDBAccess();
	public String execute(String[] data) {
			return liveDBA.liveAdd(data);
	}
	
	public String[][] execute(String data) {
			return liveDBA.liveUpdata(data);
	}
}
