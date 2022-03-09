package model;

import java.io.Serializable;
import java.util.Arrays;

import java.util.List;



public class Live implements Serializable {
	private static final long serialVersionUID = 1L;
	private String itemid;
	private String areaName;
	private String spotName;
	private String starttime;
	private String comment;
	private String price;
	private String liveName;
	private String img;

	public Live() {

	}

	public Live(String itemid, String areaName, String spotName, String starttime ,String comment,
			String price,String liveName,String img) {
		this.itemid = itemid;
		this.areaName = areaName;
		this.spotName = spotName;
		this.starttime = starttime;
		this.comment = comment;
		this.price = price;
		this.liveName = liveName;
		this.img = img;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getSpotName() {
		return spotName;
	}

	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getLiveName() {
		return liveName;
	}

	public void setLiveName(String liveName) {
		this.liveName = liveName;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}







}



