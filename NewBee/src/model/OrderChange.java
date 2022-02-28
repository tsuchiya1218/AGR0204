package model;

import java.io.Serializable;

public class OrderChange implements Serializable {
	private static final long serialVersionUID = 1L;
	private String startTime;
	private String custName;
	private String price;
	private String tel;
	private String orderCode;
	private String endTime;
	private String itemId;

	public OrderChange() {

	}

	public OrderChange(String tel, String custName, String orderCode, 
			 String startTime, String endTime,String price, String itemId) {
		this.tel = tel;
		this.custName = custName;
		this.orderCode = orderCode;
		this.price = price;
		this.startTime = startTime;
		this.endTime = endTime;
		this.setItemId(itemId);
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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



	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}



}