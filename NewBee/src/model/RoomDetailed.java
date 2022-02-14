package model;

import java.io.Serializable;

public class RoomDetailed implements Serializable{
	private static final long serialVersionUID = 1L;
	private String hotelId;
	private String hotelName;
	private String roomId;
	private String roomNum;
	private String roomType;
	private String num;
	private String comment;

	public RoomDetailed(String hotelId, String hotelName, String roomId, 
			String roomNum, String roomType, String comment,String num) {
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.roomId = roomId;
		this.roomNum = roomNum;
		this.roomType = roomType;
		this.num = num;
		this.setComment(comment);
	}
	
	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}




}
