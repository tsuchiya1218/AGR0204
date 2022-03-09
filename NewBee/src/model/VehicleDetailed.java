package model;

import java.io.Serializable;

public class VehicleDetailed implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String dstation;
	private String astation;
	private String dtime;
	private String atime;
	private String ttypeid;
	private String price;
	private String imteid;

	public VehicleDetailed(String name, String dstation, String astation,
			String dtime, String atime,
			String ttypeid,String price,String imteid ) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.name = name;
		this.dstation = dstation;
		this.astation = astation;
		this.dtime = dtime;
		this.atime = atime;
		this.ttypeid = ttypeid;
		this.setPrice(price);
		this.setImteid(imteid);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDstation() {
		return dstation;
	}

	public void setDstation(String dstation) {
		this.dstation = dstation;
	}

	public String getAstation() {
		return astation;
	}

	public void setAstation(String astation) {
		this.astation = astation;
	}

	public String getDtime() {
		return dtime;
	}

	public void setDtime(String dtime) {
		this.dtime = dtime;
	}

	public String getAtime() {
		return atime;
	}

	public void setAtime(String atime) {
		this.atime = atime;
	}

	public String getTtypeid() {
		return ttypeid;
	}

	public void setTtypeid(String ttypeid) {
		this.ttypeid = ttypeid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImteid() {
		return imteid;
	}

	public void setImteid(String imteid) {
		this.imteid = imteid;
	}


}
