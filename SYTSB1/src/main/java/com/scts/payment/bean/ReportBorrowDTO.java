package com.scts.payment.bean;



/**
 * 借报告、借发票数据传输对象 
 * InspectionInfoDTO
 * 
 * @author GaoYa
 * @date 2014-07-22 上午10:09:00
 */
public class ReportBorrowDTO{
	
	private static final long serialVersionUID = 1L;
	private String borrow_date;		// 外借日期
	private String invoice_no;		// 发票号
	private String com_name	;		// 报告使用单位
	private String borrow_com_name	;	// 借票单位
	private String report_count;	// 报告份数
	private String unpay_amount;	// 欠款金额
	private String check_dep_id;	// 检验部门ID
	private String check_department;	// 检验部门
	private String maintain_unit_name;	// 维保单位
	private String borrow_name;		// 欠款人
	private String contack_number;	// 联系电话
	private String issue_name;		// 签发人姓名
	private String sign_leader_id;	// 签字主任ID
	private String borrow_start_date;	// 外借开始日期
	private String borrow_end_date;		// 外借结束日期
	private String repay_date;		// 外借结束日期
	
	public String getCom_name() {
		return com_name;
	}


	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}


	public String getBorrow_date() {
		return borrow_date;
	}


	public void setBorrow_date(String borrow_date) {
		this.borrow_date = borrow_date;
	}


	public String getReport_count() {
		return report_count;
	}


	public void setReport_count(String report_count) {
		this.report_count = report_count;
	}


	public String getUnpay_amount() {
		return unpay_amount;
	}


	public void setUnpay_amount(String unpay_amount) {
		this.unpay_amount = unpay_amount;
	}


	public String getInvoice_no() {
		return invoice_no;
	}


	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}


	public String getBorrow_com_name() {
		return borrow_com_name;
	}


	public void setBorrow_com_name(String borrow_com_name) {
		this.borrow_com_name = borrow_com_name;
	}


	public String getCheck_department() {
		return check_department;
	}


	public void setCheck_department(String check_department) {
		this.check_department = check_department;
	}


	public String getMaintain_unit_name() {
		return maintain_unit_name;
	}


	public void setMaintain_unit_name(String maintain_unit_name) {
		this.maintain_unit_name = maintain_unit_name;
	}


	public String getBorrow_name() {
		return borrow_name;
	}


	public void setBorrow_name(String borrow_name) {
		this.borrow_name = borrow_name;
	}


	public String getContack_number() {
		return contack_number;
	}


	public void setContack_number(String contack_number) {
		this.contack_number = contack_number;
	}


	public String getIssue_name() {
		return issue_name;
	}


	public void setIssue_name(String issue_name) {
		this.issue_name = issue_name;
	}


	public String getCheck_dep_id() {
		return check_dep_id;
	}


	public void setCheck_dep_id(String check_dep_id) {
		this.check_dep_id = check_dep_id;
	}


	public String getSign_leader_id() {
		return sign_leader_id;
	}


	public void setSign_leader_id(String sign_leader_id) {
		this.sign_leader_id = sign_leader_id;
	}


	public String getBorrow_start_date() {
		return borrow_start_date;
	}


	public void setBorrow_start_date(String borrow_start_date) {
		this.borrow_start_date = borrow_start_date;
	}


	public String getBorrow_end_date() {
		return borrow_end_date;
	}


	public void setBorrow_end_date(String borrow_end_date) {
		this.borrow_end_date = borrow_end_date;
	}
	
	public String getRepay_date() {
		return repay_date;
	}


	public void setRepay_date(String repay_date) {
		this.repay_date = repay_date;
	}
}
