package com.scts.payment.bean;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 报检业务数据传输对象 
 * InspectionInfoDTO
 * 
 * @author GaoYa
 * @date 2014-05-06 上午11:35:00
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InspectionInfoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String	id;	// ID
	private String	check_type;	// 检验类别
	private String	sn;	// 业务流水号
	private String  device_registration_code; // 设备注册代码
	private String	device_sort_code; // 设备类别
	private String	device_name; // 设备名称
	private String	com_id;		// 受检单位（报检单位）
	private String	com_name;	// 受检单位（报检单位）
	private double	advance_fees = 0;	// 预收金额
	private String	advance_remark;		//	预收金额备注
	private double	receivable;			//	实收金额
	private String 	report_sn;	// 报告书编号
	private String 	report_com_name	;	// 报告书使用单位名称
	private String  construction_units_name; // 安装单位名称（现场施工单位名称）
	private String  maintain_unit_name;	// 维保单位名称
	private String  device_area_name; // 设备所在地
	private Date	advance_time;	//	预检日期
	private String  check_date;		// 检验日期
	private String	fee_status;		//	收费状态(0 默认 1 待收费 2 已收费 3 借报告 4 借发票 5 借报告和发票)
	private String	advance_type;	//	收费类型 0 正常收费 1 协议收费 2 免收费
	private String	check_op;	//	检验联系人
	private String	check_tel;	//	联系人电话
	private String  check_department;	// 检验部门
	private String	item_op_name;	//	项目负责人名字
	private String	check_op_name;	//	参检人员名字	
	private String	enter_op_name;	//	录入人员姓名
	private Date	enter_time;		//	录入时间
	private Date    cur_date;		//  当前时间
	private int     draft_count;	//  起草数量
	private int     issue_count;	//  签发数量
	
	
	
	public InspectionInfoDTO() {
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCheck_type() {
		return check_type;
	}
	public void setCheck_type(String check_type) {
		this.check_type = check_type;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getDevice_sort_code() {
		return device_sort_code;
	}
	public void setDevice_sort_code(String device_sort_code) {
		this.device_sort_code = device_sort_code;
	}
	public String getDevice_name() {
		return device_name;
	}
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}
	public String getReport_sn() {
		return report_sn;
	}
	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}
	public String getReport_com_name() {
		return report_com_name;
	}
	public void setReport_com_name(String report_com_name) {
		this.report_com_name = report_com_name;
	}

	public double getAdvance_fees() {
		return advance_fees;
	}

	public void setAdvance_fees(double advance_fees) {
		this.advance_fees = advance_fees;
	}

	public Date getAdvance_time() {
		return advance_time;
	}

	public void setAdvance_time(Date advance_time) {
		this.advance_time = advance_time;
	}

	public String getAdvance_type() {
		return advance_type;
	}

	public void setAdvance_type(String advance_type) {
		this.advance_type = advance_type;
	}

	public String getCheck_op() {
		return check_op;
	}

	public void setCheck_op(String check_op) {
		this.check_op = check_op;
	}

	public String getCheck_tel() {
		return check_tel;
	}

	public void setCheck_tel(String check_tel) {
		this.check_tel = check_tel;
	}

	public String getItem_op_name() {
		return item_op_name;
	}

	public void setItem_op_name(String item_op_name) {
		this.item_op_name = item_op_name;
	}

	public String getCheck_op_name() {
		return check_op_name;
	}

	public void setCheck_op_name(String check_op_name) {
		this.check_op_name = check_op_name;
	}

	public String getEnter_op_name() {
		return enter_op_name;
	}

	public void setEnter_op_name(String enter_op_name) {
		this.enter_op_name = enter_op_name;
	}

	public Date getEnter_time() {
		return enter_time;
	}

	public void setEnter_time(Date enter_time) {
		this.enter_time = enter_time;
	}

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public String getCheck_department() {
		return check_department;
	}

	public void setCheck_department(String check_department) {
		this.check_department = check_department;
	}

	public String getAdvance_remark() {
		return advance_remark;
	}

	public void setAdvance_remark(String advance_remark) {
		this.advance_remark = advance_remark;
	}

	public String getMaintain_unit_name() {
		return maintain_unit_name;
	}

	public void setMaintain_unit_name(String maintain_unit_name) {
		this.maintain_unit_name = maintain_unit_name;
	}

	public String getDevice_area_name() {
		return device_area_name;
	}

	public void setDevice_area_name(String device_area_name) {
		this.device_area_name = device_area_name;
	}

	public String getCheck_date() {
		return check_date;
	}

	public void setCheck_date(String check_date) {
		this.check_date = check_date;
	}

	public String getFee_status() {
		return fee_status;
	}

	public void setFee_status(String fee_status) {
		this.fee_status = fee_status;
	}

	public double getReceivable() {
		return receivable;
	}

	public void setReceivable(double receivable) {
		this.receivable = receivable;
	}
	
	public Date getCur_date() {
		return cur_date;
	}

	public void setCur_date(Date cur_date) {
		this.cur_date = cur_date;
	}

	public int getDraft_count() {
		return draft_count;
	}

	public void setDraft_count(int draft_count) {
		this.draft_count = draft_count;
	}

	public int getIssue_count() {
		return issue_count;
	}

	public void setIssue_count(int issue_count) {
		this.issue_count = issue_count;
	}

	public String getCom_id() {
		return com_id;
	}

	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}

	public String getDevice_registration_code() {
		return device_registration_code;
	}

	public void setDevice_registration_code(String device_registration_code) {
		this.device_registration_code = device_registration_code;
	}

	public String getConstruction_units_name() {
		return construction_units_name;
	}

	public void setConstruction_units_name(String construction_units_name) {
		this.construction_units_name = construction_units_name;
	}

}
