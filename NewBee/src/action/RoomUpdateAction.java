package action;


import dao.RoomAddDBAccess;


public class RoomUpdateAction {
	RoomAddDBAccess room = new RoomAddDBAccess();
	
	public String execute(String[] data) {
		return  room.roomAdd(data);
	}
	
	public String[][] execute(String data) {
		return  room.roomSearch(data);
	}

	public String executeUptade(String[] data) {
		// TODO 自動生成されたメソッド・スタブ
		return  room.roomUpdate(data);
	}
}
