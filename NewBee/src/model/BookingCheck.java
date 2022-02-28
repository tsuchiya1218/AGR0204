package model;

import java.io.Serializable;

public class BookingCheck implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String custName;
	private String date;
	private String tel;
	private String orderCode;
	private String price;
	private String itemid;


	public BookingCheck(String tel, String custName, String orderCode, 
			String date, String price ,String itemid) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.tel = tel;
		this.custName = custName;
		this.orderCode = orderCode;
		this.setDate(date);
		this.setPrice(price);
		this.setItemid(itemid);
	}
	
	public String getCustName() {
		return custName;
	}


	public void setCustName(String custName) {
		this.custName = custName;
	}




	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getOrderCode() {
		return orderCode;
	}


	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}


	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}




}
