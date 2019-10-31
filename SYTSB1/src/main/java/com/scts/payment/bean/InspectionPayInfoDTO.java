package com.scts.payment.bean;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Transient;


/**
 * 已收费业务数据传输对象 
 * InspectionInfoDTO
 * 
 * @author GaoYa
 * @date 2014-06-25 上午10:26:00
 */
public class InspectionPayInfoDTO{
	
	private static final long serialVersionUID = 1L;
	private String	id;			//	ID
	private String pay_com_name;	// 缴费单位名称
	private String pay_received;	// 实收总金额
	private String cash_pay;		// 实际收款额（现金缴费金额，2014-11-07修改）
	private String remark;			// 备注（转账缴费金额，2014-11-07修改）
	private String pos;				// POS机刷卡金额（2015-02-09修改）
	private String hand_in;			// 上缴财政金额（2016-05-04周静要求增加）
	private String draft;			// 承兑汇票金额（2017-03-30周静要求增加）
	private String pay_type;		// 缴费方式 1 现金缴费 2 银行转账 3 免收费
	private String invoice_no;		// 发票号
	private String invoice_type;	// 发票类型
	private String pay_date;		// 缴费日期	
	private String com_name	;		// 使用单位名称
	private String check_department;	// 检验部门
	private Date inspection_date;		// 检验日期
	private String isDiscount;			// 是否打折（1：是 0：否）（2015-08-06修改）
	
	public Collection<InspectionPayInfo> inspectionPayInfo;
	
	public Date getInspection_date() {
		return inspection_date;
	}


	public void setInspection_date(Date inspection_date) {
		this.inspection_date = inspection_date;
	}


	public String getIsDiscount() {
		return isDiscount;
	}


	public void setIsDiscount(String isDiscount) {
		this.isDiscount = isDiscount;
	}


	public String getPay_received() {
		return pay_received;
	}


	public void setPay_received(String pay_received) {
		this.pay_received = pay_received;
	}


	public String getPay_type() {
		return pay_type;
	}


	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}


	public String getInvoice_no() {
		return invoice_no;
	}


	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}


	public String getPay_date() {
		return pay_date;
	}


	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}

	public String getCheck_department() {
		return check_department;
	}


	public void setCheck_department(String check_department) {
		this.check_department = check_department;
	}


	public String getPay_com_name() {
		return pay_com_name;
	}


	public void setPay_com_name(String pay_com_name) {
		this.pay_com_name = pay_com_name;
	}


	public String getCom_name() {
		return com_name;
	}


	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}


	public String getCash_pay() {
		return cash_pay;
	}


	public void setCash_pay(String cash_pay) {
		this.cash_pay = cash_pay;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getPos() {
		return pos;
	}


	public void setPos(String pos) {
		this.pos = pos;
	}


	public String getInvoice_type() {
		return invoice_type;
	}


	public void setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
	}


	public String getHand_in() {
		return hand_in;
	}


	public void setHand_in(String hand_in) {
		this.hand_in = hand_in;
	}
	
	

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getDraft() {
		return draft;
	}


	public void setDraft(String draft) {
		this.draft = draft;
	}


	@Transient
	public Collection<InspectionPayInfo> getInspectionPayInfo() {
		return inspectionPayInfo;
	}


	public void setInspectionPayInfo(Collection<InspectionPayInfo> inspectionPayInfo) {
		this.inspectionPayInfo = inspectionPayInfo;
	}
}
