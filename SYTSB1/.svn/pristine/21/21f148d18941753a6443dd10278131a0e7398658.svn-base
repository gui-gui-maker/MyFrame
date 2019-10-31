package com.scts.payment.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 报检信息收费详细信息表
 * InspectionPayDetail entity. 
 * @author GaoYa
 * @date 2014-05-04 16:10:00
 */
@Entity
@Table(name = "TZSB_INSPECTION_PAY_DETAIL")
public class InspectionPayDetail implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	//流水号
	private String fk_pay_info_id;	// 收费信息表ID
	private String cash_pay;	// 实际收款额
	private String invoice_no;	// 发票号
	private String check_no;	// 支票号
	private Date receive_date;	// 缴费日期
	private String pay_type;	// 缴费方式 1 现金缴费 2 银行转账 3 免收费
	private String fk_company_id;	// 缴费单位ID
	private String company_name;	// 缴费单位名称
	private String pay_man_name;	// 缴费人姓名
	private String receive_man_name;	// 收费人姓名
	private String remark;	// 备注




	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	
	@Column(name="CASH_PAY")
	public String getCash_pay() {
		return cash_pay;
	}

	public void setCash_pay(String cash_pay) {
		this.cash_pay = cash_pay;
	}
	
	@Column(name="INVOICE_NO")
	public String getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}

	@Column(name="COMPANY_NAME")
	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	@Column(name="PAY_MAN_NAME")
	public String getPay_man_name() {
		return pay_man_name;
	}

	public void setPay_man_name(String pay_man_name) {
		this.pay_man_name = pay_man_name;
	}

	@Column(name="RECEIVE_MAN_NAME")
	public String getReceive_man_name() {
		return receive_man_name;
	}

	public void setReceive_man_name(String receive_man_name) {
		this.receive_man_name = receive_man_name;
	}

	@Column(name="FK_COMPANY_ID")
	public String getFk_company_id() {
		return fk_company_id;
	}

	public void setFk_company_id(String fk_company_id) {
		this.fk_company_id = fk_company_id;
	}

	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name="FK_PAY_INFO_ID")
	public String getFk_pay_info_id() {
		return fk_pay_info_id;
	}

	public void setFk_pay_info_id(String fk_pay_info_id) {
		this.fk_pay_info_id = fk_pay_info_id;
	}

	@Column(name="CHECK_NO")
	public String getCheck_no() {
		return check_no;
	}

	public void setCheck_no(String check_no) {
		this.check_no = check_no;
	}

	@Column(name="RECEIVE_DATE")
	public Date getReceive_date() {
		return receive_date;
	}

	public void setReceive_date(Date receive_date) {
		this.receive_date = receive_date;
	}

	@Column(name="PAY_TYPE")
	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	
	
}
