package model;

import java.io.Serializable;

public class Reviews implements Serializable{
	private static final long serialVersionUID = 1L;
	private String customerId;
	private String customerName;
	private String orderId;
	private String review;
	private String date;

	public Reviews(String customerId, String customerName,
			String orderId, String review, String date) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.orderId = orderId;
		this.review = review;
		this.date = date;
	}


	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}





}
