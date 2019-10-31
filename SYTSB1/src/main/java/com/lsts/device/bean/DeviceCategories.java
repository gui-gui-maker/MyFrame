package com.lsts.device.bean;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * 设备种类
 * 
 * @author liming	 2014-5-9
 * 
 */

@Entity
@Table(name = "BASE_DEVICE_CATEGORIES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceCategories implements BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8080426241592276199L;
	private String id;
	private String parent_id;
	private String device_code;
	private String device_name;
	private String main_code;
	private String main_name;
	
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getDevice_code() {
		return device_code;
	}
	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}
	public String getDevice_name() {
		return device_name;
	}
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}
	public String getMain_code() {
		return main_code;
	}
	public void setMain_code(String main_code) {
		this.main_code = main_code;
	}
	public String getMain_name() {
		return main_name;
	}
	public void setMain_name(String main_name) {
		this.main_name = main_name;
	}
	


}
