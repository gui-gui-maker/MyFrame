package com.lsts.finance.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;


/**
 * 发票入库及领取子表
 * @author lenovo
 *
 */
@Entity
@Table(name = "TJY2_CW_INVOICE_F")
@JsonIgnoreProperties(ignoreUnknown=true,value={"cwInvoiceP"})
public class CwInvoiceF implements BaseEntity {
	
	private String id; //主键
	
	private BigDecimal invoiceMoney; //发票金额
	
	private String invoiceCode ; //发票号
	
	private String invoiceType; //发票类型（数据字典：TJY2_CW_FP_TYPE）
	
	private String status = "WSY"; //状态
	
	private String invoiceUnit; //开票单位
	
	private Date invoiceDate; //开票时间
	
	private String checkoutDep; //检验部门
	
	private String checkoutUnit; //受检单位
	
	private String moneyType; //交易类型（数据字典：PAY_TYPE）
	
	private String invoice_id ; //开票人ID

	private String invoice_name; //开票人

	private String lead_id; // 领用人ID
	
	private String lead_name; //领用人
	
	private Date lead_date;//领用时间
	
	private String pk_lead_id; 
	
	private String remark; 
	
	private Date invoiceDate1; //销票时间
	

	private CwInvoiceP cwInvoiceP;//父表实体类

	public void setId(String value){
		this.id = value;
	}
	
	public void setInvoiceMoney(BigDecimal value) {
		this.invoiceMoney = value;
	}
	
	public void setStatus(String value) {
		this.status = value;
	}
	
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public void setInvoiceType(String value) {
		this.invoiceType = value;
	}
	
	public void setCwInvoiceP(CwInvoiceP cwInvoiceP) {
		this.cwInvoiceP = cwInvoiceP;
	}
	
	public void setInvoiceUnit(String value) {
		this.invoiceUnit = value;
	}
	
	public void setInvoiceDate(Date value) {
		this.invoiceDate = value;
	}
	
	public void setInvoiceDate1(Date value) {
		this.invoiceDate1 = value;
	}
	
	public void setCheckoutDep(String value) {
		this.checkoutDep = value;
	}
	
	public void setCheckoutUnit(String value) {
		this.checkoutUnit = value;
	}
	
	public void setMoneyType(String value) {
		this.moneyType = value;
	}
	
	public void setInvoiceCode(String value) {
		this.invoiceCode = value;
	}
	
	

	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
	public String getId() {
		return id;
	}
	
	@Column(name ="INVOICE_MONEY")
	public BigDecimal getInvoiceMoney() {
		return invoiceMoney;
	}
	
	@Column(name ="STATUS")
	public String getStatus() {
		return status;
	}
	
	@Column(name ="REMARK")
	public String getRemark() {
		return remark;
	}
	
	@Column(name ="INVOICE_TYPE")
	public String getInvoiceType() {
		return invoiceType;
	}
	
	@Column(name ="INVOICE_UNIT")
	public String getInvoiceUnit() {
		return invoiceUnit;
	}

	
	@Column(name ="INVOICE_DATE")
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	
	@Column(name ="INVOICE_DATE1")
	public Date getInvoiceDate1() {
		return invoiceDate1;
	}

	
	@Column(name ="CHECKOUT_DEP")
	public String getCheckoutDep() {
		return checkoutDep;
	}

	
	@Column(name ="CHECKOUT_UNIT")
	public String getCheckoutUnit() {
		return checkoutUnit;
	}

	
	@Column(name ="MONEY_TYPE")
	public String getMoneyType() {
		return moneyType;
	}

	@Column(name ="INVOICE_CODE")
	public String getInvoiceCode() {
		return invoiceCode;
	}

	

	@ManyToOne
	@JoinColumn(name="PK_INVOICE_ID")
	public CwInvoiceP getCwInvoiceP() {
		return cwInvoiceP;
	}

	@Column(name ="PK_LEAD_ID")
	public String getPk_lead_id() {
		return pk_lead_id;
	}

	public void setPk_lead_id(String value) {
		this.pk_lead_id = value;
	}

	@Column(name = "INVOICE_ID")
	public String getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}
	@Column(name = "INVOICE_NAME")
	public String getInvoice_name() {
		return invoice_name;
	}

	public void setInvoice_name(String invoice_name) {
		this.invoice_name = invoice_name;
	}
	@Column(name = "LEAD_ID")
	public String getLead_id() {
		return lead_id;
	}

	public void setLead_id(String lead_id) {
		this.lead_id = lead_id;
	}
	@Column(name = "LEAD_NAME")
	public String getLead_name() {
		return lead_name;
	}

	public void setLead_name(String lead_name) {
		this.lead_name = lead_name;
	}
	@Column(name = "LEAD_DATE")
	public Date getLead_date() {
		return lead_date;
	}

	public void setLead_date(Date lead_date) {
		this.lead_date = lead_date;
	}

	

	







}
