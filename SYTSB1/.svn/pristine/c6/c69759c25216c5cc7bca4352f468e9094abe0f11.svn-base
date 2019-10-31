package com.lsts.finance.bean;


import java.util.Date;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_CW_INVOICE_P")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CwInvoiceP implements BaseEntity {
	
	private String id; //主键
	
	private String buyName; //购买人
	
	private Date buyDate; //购买时间
	
	private String registrantName; //登记人
	
	private String registrantId;//登记人ID
	
	private Date registrantDate; //登记时间
	
	private String remark; //备注
	
	private String status ="WSY"; //状态
	
	private int invoiceNum; //发票数量
	
	private String invoiceStart ; //发票起始号
	
	private String invoiceEnd;  //发票结束号
	
	private String buyNameId; //购买人ID
	
	private String invoiceType; //发票类型 (数据字典:TJY2_CW_FP_TYPE)
	
	private Set<CwInvoiceF> cwInvoiceF; //子表实体类

	public void setId(String id) {
		this.id = id;
	}
	
	public void setBuyName(String value) {
		this.buyName = value;
	}
	
	public void setBuyDate(Date value) {
		this.buyDate = value;
	}

	public void setRegistrantName(String value) {
		this.registrantName = value;
	}
	
	public void setRegistrantDate(Date value) {
		this.registrantDate = value;
	}
	
	public void setStatus(String value) {
		this.status = value;
	}
	
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public void setCwInvoiceF(Set<CwInvoiceF> cwInvoiceF) {
		this.cwInvoiceF = cwInvoiceF;
	}
	
	public void setInvoiceNum(int value) {
		this.invoiceNum = value;
	}
	
	public void setInvoiceEnd(String value) {
		this.invoiceEnd = value;
	}
	
	public void setInvoiceStart(String value) {
		this.invoiceStart = value;
	}
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
	public String getId() {
		return id;
	}

	@Column(name ="BUY_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
	public String getBuyName() {
		return buyName;
	}

	@Column(name ="BUY_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public Date getBuyDate() {
		return buyDate;
	}

	@Column(name ="REGISTRANT_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=50)
	public String getRegistrantName() {
		return registrantName;
	}

	@Column(name ="REGISTRANT_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public Date getRegistrantDate() {
		return registrantDate;
	}

	@Column(name ="INVOICE_NUM",unique=false,nullable=true,insertable=true,updatable=true,length=100)
	public int getInvoiceNum() {
		return invoiceNum;
	}
	@Column(name ="INVOICE_START",unique=false,nullable=true,insertable=true,updatable=true,length=100)
	public String getInvoiceStart() {
		return invoiceStart;
	}

	@Column(name ="INVOICE_END",unique=false,nullable=true,insertable=true,updatable=true,length=100)
	public String getInvoiceEnd() {
		return invoiceEnd;
	} 

	@Column(name ="REMARK",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
	public String getRemark() {
		return remark;
	}
	
	
	@Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=10)
	public String getStatus() {
		return status;
	}
	
	
	@Column(name="BUY_NAME_ID")
	public String getBuyNameId() {
		return buyNameId;
	}

	public void setBuyNameId(String buyNameId) {
		this.buyNameId = buyNameId;
	}
	@Column(name = "REGISTRANT_ID")
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

	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.LAZY,mappedBy="cwInvoiceP")
	@OrderBy
	public Set<CwInvoiceF> getCwInvoiceF() {
		return cwInvoiceF;
	}

	
	

	
	
	
	
	
	
}
