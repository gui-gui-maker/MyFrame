package com.scts.payment.bean;

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

/**
 * 金额修改审批流程明细表
 * InspectionChangeMoney entity. 
 * @author GaoYa
 * @date 2016-11-16 11:05:00
 */
@Entity
@Table(name = "TZSB_INSPECTION_CHANGE_MONEY")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InspectionChangeMoney implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	//流水号
	private String fk_inspection_info_id;	// 报检信息ID
	private String report_com_name;			// 使用单位
	private String report_sn;				// 报告书编号
	private Double advance_fees;			// 应收金额
	private Double change_money;			// 修改金额（实收金额）
	private String remarks;					// 备注（修改金额说明）
	private String create_emp_id;			// 申请人ID
	private String create_emp_name;			// 申请人姓名
	private Date   create_date;				// 申请时间
	private String mdy_emp_id;				// 最后修改人ID
	private String mdy_emp_name;			// 最后修改人姓名
	private Date   mdy_date;				// 最后修改时间
	private String check_emp_id;			// 审核人ID
	private String check_emp_name;			// 审核人姓名
	private Date   check_date;				// 审核时间
	private String data_status;				// 数据状态（0：未审核 1：审核通过 2：审核不通过 99：已作废）
	private String check_remark;			// 审核结果说明


	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}
	
	public InspectionChange inspectionChange;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FK_CHANGE_MONEY_ID")
	public InspectionChange getInspectionChange() {
		return inspectionChange;
	}

	public void setInspectionChange(InspectionChange inspectionChange) {
		this.inspectionChange = inspectionChange;
	}

	@Column(name="FK_INSPECTION_INFO_ID")
	public String getFk_inspection_info_id() {
		return fk_inspection_info_id;
	}

	public void setFk_inspection_info_id(String fk_inspection_info_id) {
		this.fk_inspection_info_id = fk_inspection_info_id;
	}

	public String getReport_sn() {
		return report_sn;
	}

	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}

	public Double getAdvance_fees() {
		return advance_fees;
	}

	public void setAdvance_fees(Double advance_fees) {
		this.advance_fees = advance_fees;
	}

	public Double getChange_money() {
		return change_money;
	}

	public void setChange_money(Double change_money) {
		this.change_money = change_money;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreate_emp_id() {
		return create_emp_id;
	}

	public void setCreate_emp_id(String create_emp_id) {
		this.create_emp_id = create_emp_id;
	}

	public String getCreate_emp_name() {
		return create_emp_name;
	}

	public void setCreate_emp_name(String create_emp_name) {
		this.create_emp_name = create_emp_name;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getMdy_emp_id() {
		return mdy_emp_id;
	}

	public void setMdy_emp_id(String mdy_emp_id) {
		this.mdy_emp_id = mdy_emp_id;
	}

	public String getMdy_emp_name() {
		return mdy_emp_name;
	}

	public void setMdy_emp_name(String mdy_emp_name) {
		this.mdy_emp_name = mdy_emp_name;
	}

	public Date getMdy_date() {
		return mdy_date;
	}

	public void setMdy_date(Date mdy_date) {
		this.mdy_date = mdy_date;
	}

	public String getCheck_emp_id() {
		return check_emp_id;
	}

	public void setCheck_emp_id(String check_emp_id) {
		this.check_emp_id = check_emp_id;
	}

	public String getCheck_emp_name() {
		return check_emp_name;
	}

	public void setCheck_emp_name(String check_emp_name) {
		this.check_emp_name = check_emp_name;
	}

	public Date getCheck_date() {
		return check_date;
	}

	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}

	public String getData_status() {
		return data_status;
	}

	public void setData_status(String data_status) {
		this.data_status = data_status;
	}

	public String getCheck_remark() {
		return check_remark;
	}

	public void setCheck_remark(String check_remark) {
		this.check_remark = check_remark;
	}

	public String getReport_com_name() {
		return report_com_name;
	}

	public void setReport_com_name(String report_com_name) {
		this.report_com_name = report_com_name;
	}
	
}
