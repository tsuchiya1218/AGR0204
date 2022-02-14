package model;

import java.io.Serializable;

public class Reviews implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userId;
	private String custName;
	private String itemCode;
	private String course;
	private String Comment;
	private String time;

	public Reviews(String userId, String custName, String itemCode, 
			String course, String Comment, String time) {
		this.userId = userId;
		this.custName = custName;
		this.itemCode = itemCode;
		this.course = course;
		this.setComment(Comment);
		this.time = time;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String orderCode) {
		this.itemCode = orderCode;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}


	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

}
