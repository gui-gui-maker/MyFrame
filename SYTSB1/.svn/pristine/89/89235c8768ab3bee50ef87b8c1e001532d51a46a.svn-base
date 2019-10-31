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
 * 预警处理情况记录
 * 
 * @author liming	 2014-5-9
 * 
 */

@Entity
@Table(name = "tzsb_warning_deal")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceWarningDeal implements BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6955463704424484876L;
    private String id;
	private  DeviceDocument deviceDocument;
	private  DeviceWarningStatus warningStatus;
    private String deal_status;
    private Date deal_time;
    private String deal_man;
    private String deal_receive_man;
    private String deal_remark;
    private String deal_unit;
    private String deal_man_id;
    private String deal_unit_id;
    private Character flag;
    private String op_man;
    private Date op_date;
    private Character is_or;
    private String send_status;
    private String fk_base_device_document_id; 
    private String fk_warning_status_id; 
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	//@Override
	public void setId(String id) {
		this.id=id;
		
	}
	@Transient
	public DeviceDocument getDeviceDocument() {
		return deviceDocument;
	}

	public void setDeviceDocument(DeviceDocument deviceDocument) {
		this.deviceDocument = deviceDocument;
	}
	@Transient
	public DeviceWarningStatus getWarningStatus() {
		return warningStatus;
	}

	public void setWarningStatus(DeviceWarningStatus warningStatus) {
		this.warningStatus = warningStatus;
	}

	public String getDeal_status() {
		return deal_status;
	}

	public void setDeal_status(String deal_status) {
		this.deal_status = deal_status;
	}


	public String getDeal_man() {
		return deal_man;
	}

	public void setDeal_man(String deal_man) {
		this.deal_man = deal_man;
	}


	public String getDeal_receive_man() {
		return deal_receive_man;
	}

	public void setDeal_receive_man(String deal_receive_man) {
		this.deal_receive_man = deal_receive_man;
	}

	public String getDeal_remark() {
		return deal_remark;
	}

	public void setDeal_remark(String deal_remark) {
		this.deal_remark = deal_remark;
	}

	public String getDeal_unit() {
		return deal_unit;
	}

	public void setDeal_unit(String deal_unit) {
		this.deal_unit = deal_unit;
	}

	public String getDeal_man_id() {
		return deal_man_id;
	}

	public void setDeal_man_id(String deal_man_id) {
		this.deal_man_id = deal_man_id;
	}

	public String getDeal_unit_id() {
		return deal_unit_id;
	}

	public void setDeal_unit_id(String deal_unit_id) {
		this.deal_unit_id = deal_unit_id;
	}


	public String getOp_man() {
		return op_man;
	}

	public void setOp_man(String op_man) {
		this.op_man = op_man;
	}

	public Date getDeal_time() {
		return deal_time;
	}

	public void setDeal_time(Date deal_time) {
		this.deal_time = deal_time;
	}

	public Character getFlag() {
		return flag;
	}

	public void setFlag(Character flag) {
		this.flag = flag;
	}

	public Date getOp_date() {
		return op_date;
	}

	public void setOp_date(Date op_date) {
		this.op_date = op_date;
	}

	public Character getIs_or() {
		return is_or;
	}

	public void setIs_or(Character is_or) {
		this.is_or = is_or;
	}

	public String getFk_base_device_document_id() {
		return fk_base_device_document_id;
	}

	public void setFk_base_device_document_id(String fk_base_device_document_id) {
		this.fk_base_device_document_id = fk_base_device_document_id;
	}

	public String getFk_warning_status_id() {
		return fk_warning_status_id;
	}

	public void setFk_warning_status_id(String fk_warning_status_id) {
		this.fk_warning_status_id = fk_warning_status_id;
	}

	public String getSend_status() {
		return send_status;
	}

	public void setSend_status(String send_status) {
		this.send_status = send_status;
	}


}
