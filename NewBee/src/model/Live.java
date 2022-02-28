package model;

import java.io.Serializable;
import java.util.Arrays;

import java.util.List;



public class Live implements Serializable {
	private static final long serialVersionUID = 1L;
	private String liveId;
	private String time;
	private String price;
	private String lName;
	private String comment;
	private String area;
	private String name;

	public Live() {

	}

	public Live(String liveId, String area, String lName, String name ,String time,String comment,String price) {
		this.setLiveId(liveId);
		this.setlName(lName);
		this.time = time;
		this.comment = comment;
		this.setPrice(price);
		this.area = area;
		this.setName(name);
	}





	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getLiveId() {
		return liveId;
	}

	public void setLiveId(String liveId) {
		this.liveId = liveId;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}



