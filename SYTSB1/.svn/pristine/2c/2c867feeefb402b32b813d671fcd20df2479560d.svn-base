package com.lsts.finance.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;
/**
 * 发票领取
 * @author lenovo
 *
 */

@Entity
@Table(name = "TJY2_CW_INVOICE_LY")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CwInvoiceLead implements BaseEntity {
	
	private String id; //主键
	
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
	
	private String registrantId;//登记人ID
	
	private String lead_dep_id; //领用部门
	
	private String invoiceType; //发票类型 (数据字典:TJY2_CW_FP_TYPE)
	
	private String invoice_start; //发票起始号
	
	private String invoice_end; //发票结束号
	
	
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
	
	
	@Column(name="REGISTRANT_NAME")
	public String getRegistrant_name() {
		return registrant_name;
	}

	public void setRegistrant_name(String registrant_name) {
		this.registrant_name = registrant_name;
	}
	
	
	@Column(name="REGISTRANT_DATE")
	public Date getRegistrant_date() {
		return registrant_date;
	}

	public void setRegistrant_date(Date registrant_date) {
		this.registrant_date = registrant_date;
	}

	
	@Column(name="LEAD_NAME")
	public String getLead_name() {
		return lead_name;
	}

	public void setLead_name(String lead_name) {
		this.lead_name = lead_name;
	}

	
	@Column(name="LEAD_DATE")
	public Date getLead_date() {
		return lead_date;
	}

	public void setLead_date(Date lead_date) {
		this.lead_date = lead_date;
	}

	
	@Column(name="LEAD_CODE")
	public String getLead_code() {
		return lead_code;
	}

	public void setLead_code(String lead_code) {
		this.lead_code = lead_code;
	}

	@Column(name="LEAD_DEP")
	public String getLead_dep() {
		return lead_dep;
	}

	public void setLead_dep(String lead_dep) {
		this.lead_dep = lead_dep;
	}

	@Column(name="LEAD_USE")
	public String getLead_use() {
		return lead_use;
	}

	public void setLead_use(String lead_use) {
		this.lead_use = lead_use;
	}

	@Column(name="LEAD_NUM")
	public int getLead_num() {
		return lead_num;
	}

	public void setLead_num(int lead_num) {
		this.lead_num = lead_num;
	}

	@Column(name="LEAD_LOG")
	public String getLead_log() {
		return lead_log;
	}

	public void setLead_log(String lead_log) {
		this.lead_log = lead_log;
	}
	
	@Column(name = "LEAD_ID")
	public String getLead_id() {
		return lead_id;
	}

	public void setLead_id(String lead_id) {
		this.lead_id = lead_id;
	}
	@Column(name="LEAD_DEP_ID")
	public String getLead_dep_id() {
		return lead_dep_id;
	}

	public void setLead_dep_id(String lead_dep_id) {
		this.lead_dep_id = lead_dep_id;
	}

	@Column(name="REGISTRANT_ID")
	public String getRegistrantId() {
		return registrantId;
	}

	public void setRegistrantId(String registrantId) {
		this.registrantId = registrantId;
	}


	@Column(name = "INVOICE_TYPE")
	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	@Column(name = "INVOICE_START")
	public String getInvoice_start() {
		return invoice_start;
	}

	public void setInvoice_start(String invoice_start) {
		this.invoice_start = invoice_start;
	}
	@Column(name = "INVOICE_END")
	public String getInvoice_end() {
		return invoice_end;
	}

	public void setInvoice_end(String invoice_end) {
		this.invoice_end = invoice_end;
	}
	
	
	
	
	
	
	
	
	
	
	
}
