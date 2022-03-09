package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


public class Spot implements Serializable {
	private static final long serialVersionUID = 1L;

	private String address;
	private String time;
	private String imgPath;
	private String access;
	private String sName;
	private String spotId;
	private String comment;

	public Spot() {

	}

	public Spot(String spotId, String sName, String address, String access,String time,String comment,String imgPath) {
		this.setSpotId(spotId);
		this.sName = sName;
		this.address = address;
		this.access = access;
		this.time = time;
		this.comment = comment;
		this.setImgPath(imgPath);
	}


	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getSpotId() {
		return spotId;
	}

	public void setSpotId(String spotId) {
		this.spotId = spotId;
	}

}



