package model;

import java.io.Serializable;

public class OrderChange implements Serializable {
	private static final long serialVersionUID = 1L;
	private String startTime;
	private String custName;
	private String course;
	private String tel;
	private String orderCode;
	private String num;
	private String endTime;

	public OrderChange() {

	}

	public OrderChange(String tel, String custName, String orderCode, 
			String course, String startTime, String endTime, String num) {
		this.tel = tel;
		this.custName = custName;
		this.orderCode = orderCode;
		this.course = course;
		this.startTime = startTime;
		this.endTime = endTime;
		this.num = num;
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

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
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

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}