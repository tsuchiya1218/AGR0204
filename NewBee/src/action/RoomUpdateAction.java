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
}
