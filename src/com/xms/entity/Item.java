package com.xms.entity;

import java.sql.Timestamp;

public class Item {
	private Integer id;
	private Integer c_id;
	private String c_name;
	private String c_picture_url;
	private Double c_price;
	private Timestamp add_time;
	private Timestamp remove_time;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getC_id() {
		return c_id;
	}
	public void setC_id(Integer c_id) {
		this.c_id = c_id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_picture_url() {
		return c_picture_url;
	}
	public void setC_picture_url(String c_picture_url) {
		this.c_picture_url = c_picture_url;
	}
	public Double getC_price() {
		return c_price;
	}
	public void setC_price(Double c_price) {
		this.c_price = c_price;
	}
	public Timestamp getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Timestamp add_time) {
		this.add_time = add_time;
	}
	public Timestamp getRemove_time() {
		return remove_time;
	}
	public void setRemove_time(Timestamp remove_time) {
		this.remove_time = remove_time;
	}
	
}
