package dao;

import java.io.Serializable;


public class Hotel implements Serializable {
	private String hotelid;
	private String name;
	private String address;
	private String comment;
	private String access;
	private String checkin;
	private String checkout;

	public String getHotelid() {
		return hotelid;
	}

	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public Hotel(String hotelid, String name, String address, String comment,
			String access, String checkin,String checkout) {
		this.hotelid = hotelid;
		this.name = name;
		this.address = address;
		this.comment = comment;
		this.access = access;
		this.checkin = checkin;
		this.checkout = checkout;
	}


}
