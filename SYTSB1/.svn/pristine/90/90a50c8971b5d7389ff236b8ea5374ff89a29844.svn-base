package com.lsts.device.bean;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * 预警处理状态
 * 
 * @author 黎明 2014-5-09
 * 
 */

@Entity
@Table(name = "tzsb_warning_status")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceWarningStatus implements BaseEntity {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 556237959504513920L;
	
	private String id;
	private  DeviceDocument deviceDocument;
    private String deal_status;
    private Date deal_time;
    private char active_flag;
    private String remark;
    private String send_status;
    private Date inspection_date;
	private String fk_base_device_document_id;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String arg0) {
		this.id = arg0;
	}

	@Transient
	public DeviceDocument getDeviceDocument() {
		return deviceDocument;
	}

	public void setDeviceDocument(DeviceDocument deviceDocument) {
		this.deviceDocument = deviceDocument;
	}

	public String getDeal_status() {
		return deal_status;
	}

	public void setDeal_status(String deal_status) {
		this.deal_status = deal_status;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getDeal_time() {
		return deal_time;
	}

	public void setDeal_time(Date deal_time) {
		this.deal_time = deal_time;
	}

	public Date getInspection_date() {
		return inspection_date;
	}

	public void setInspection_date(Date inspection_date) {
		this.inspection_date = inspection_date;
	}

	public String getFk_base_device_document_id() {
		return fk_base_device_document_id;
	}

	public void setFk_base_device_document_id(String fk_base_device_document_id) {
		this.fk_base_device_document_id = fk_base_device_document_id;
	}

	public char getActive_flag() {
		return active_flag;
	}

	public void setActive_flag(char active_flag) {
		this.active_flag = active_flag;
	}

	public String getSend_status() {
		return send_status;
	}

	public void setSend_status(String send_status) {
		this.send_status = send_status;
	}



}
