package model;

import java.io.Serializable;

public class BookingCheck implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String custName;
	private String type;
	private String tel;
	private String orderCode;
	private String num;
	private String orderTime;


	public BookingCheck(String tel, String custName, String orderCode, 
			String type, String orderTime, String num) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.tel = tel;
		this.custName = custName;
		this.orderCode = orderCode;
		this.type = type;
		this.orderTime = orderTime;
		this.num = num;
	}
	
	public String getCustName() {
		return custName;
	}


	public void setCustName(String custName) {
		this.custName = custName;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
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


	public String getNum() {
		return num;
	}


	public void setNum(String num) {
		this.num = num;
	}


	public String getOrderTime() {
		return orderTime;
	}


	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}




}
