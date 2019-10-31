package com.lsts.finance.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;


@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = { "cwBill" })
@Table(name="TJY2_CW_BILL_PARA")
public class CwBillPara implements BaseEntity{
	
	private String id;//
	private String invoice_id;//发片编号id
	private String registrant_name; //登记人	
	private Date registrant_date; //登记时间	
	private String lead_name; //领用人	
	private Date lead_date; //领用时间	
	private String lead_code; // 领用发票编号	
	private String lead_dep; //领用部门	
	private String lead_use; //领用用途	
	private int lead_num; //领用总数	
	private String lead_log; //领用记录
	private String lead_id;//领用人ID	
	private String registrant_id;//登记人ID	
	private String lead_dep_id; //领用部门	
	private String invoice_type; //发票类型 (数据字典:TJY2_CW_FP_TYPE)
	private String invoice_type_code;//
	private String cw_invoice_lead_id;//票据领用id
	private String data_type;//0申请1退回
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private CwBill cwBill;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cw_bill_id")
	public CwBill getCwBill() {
		return cwBill;
	}

	public void setCwBill(CwBill cwBill) {
		this.cwBill = cwBill;
	}
	
	
	public String getInvoice_id() {
		return invoice_id;
	}
	
	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}

	public String getRegistrant_name() {
		return registrant_name;
	}

	public void setRegistrant_name(String registrant_name) {
		this.registrant_name = registrant_name;
	}

	public Date getRegistrant_date() {
		return registrant_date;
	}

	public void setRegistrant_date(Date registrant_date) {
		this.registrant_date = registrant_date;
	}

	public String getLead_name() {
		return lead_name;
	}

	public void setLead_name(String lead_name) {
		this.lead_name = lead_name;
	}

	public Date getLead_date() {
		return lead_date;
	}

	public void setLead_date(Date lead_date) {
		this.lead_date = lead_date;
	}

	public String getLead_code() {
		return lead_code;
	}

	public void setLead_code(String lead_code) {
		this.lead_code = lead_code;
	}

	public String getLead_dep() {
		return lead_dep;
	}

	public void setLead_dep(String lead_dep) {
		this.lead_dep = lead_dep;
	}

	public String getLead_use() {
		return lead_use;
	}

	public void setLead_use(String lead_use) {
		this.lead_use = lead_use;
	}

	public int getLead_num() {
		return lead_num;
	}

	public void setLead_num(int lead_num) {
		this.lead_num = lead_num;
	}

	public String getLead_log() {
		return lead_log;
	}

	public void setLead_log(String lead_log) {
		this.lead_log = lead_log;
	}

	public String getLead_id() {
		return lead_id;
	}

	public void setLead_id(String lead_id) {
		this.lead_id = lead_id;
	}

	public String getLead_dep_id() {
		return lead_dep_id;
	}

	public void setLead_dep_id(String lead_dep_id) {
		this.lead_dep_id = lead_dep_id;
	}

	public String getRegistrant_id() {
		return registrant_id;
	}

	public void setRegistrant_id(String registrant_id) {
		this.registrant_id = registrant_id;
	}

	public String getInvoice_type() {
		return invoice_type;
	}

	public void setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
	}

	/**
	 * @return the invoice_type_code
	 */
	public String getInvoice_type_code() {
		return invoice_type_code;
	}

	/**
	 * @param invoice_type_code the invoice_type_code to set
	 */
	public void setInvoice_type_code(String invoice_type_code) {
		this.invoice_type_code = invoice_type_code;
	}

	/**
	 * @return the cw_invoice_lead_id
	 */
	public String getCw_invoice_lead_id() {
		return cw_invoice_lead_id;
	}

	/**
	 * @param cw_invoice_lead_id the cw_invoice_lead_id to set
	 */
	public void setCw_invoice_lead_id(String cw_invoice_lead_id) {
		this.cw_invoice_lead_id = cw_invoice_lead_id;
	}

	/**
	 * @return the data_type
	 */
	public String getData_type() {
		return data_type;
	}

	/**
	 * @param data_type the data_type to set
	 */
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}

}
