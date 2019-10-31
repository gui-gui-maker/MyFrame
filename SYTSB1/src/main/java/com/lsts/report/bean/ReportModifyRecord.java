package com.lsts.report.bean;

import java.util.Date;

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
 * 设备和报告修改数据记录明细表
 * ReportModifyRecord entity. 
 * @author GaoYa
 * @date 2017-11-21 16:38:00
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = { "reportModifyInfo"})
@Table(name = "tzsb_report_modify_record")
public class ReportModifyRecord implements BaseEntity{
	private static final long serialVersionUID = 1L;
	private String id;			// 流水号	
	private String info_id;		// 报告业务ID
	private String report_sn;	// 报告编号
	private String device_registration_code_old;	// 设备注册代码（原）
	private String device_registration_code;// 设备注册代码（新）
	private String registration_num;		// 使用登记证号（新）
	private String device_qr_code;			// 二维码编号（新）
	private String remark;					// 备注
	private String modify_type;				// 修改类型（1：设备 2：报告 3：设备和报告）
	private String op_user_id;	// 修改人ID
	private String op_user_name;// 修改人姓名
	private Date   op_date;		// 修改时间
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public ReportModifyInfo reportModifyInfo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_report_modify_id")
	public ReportModifyInfo getReportModifyInfo() {
		return reportModifyInfo;
	}

	public void setReportModifyInfo(ReportModifyInfo reportModifyInfo) {
		this.reportModifyInfo = reportModifyInfo;
	}
	
	public String getInfo_id() {
		return info_id;
	}
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}
	public String getReport_sn() {
		return report_sn;
	}
	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}
	public String getDevice_registration_code() {
		return device_registration_code;
	}
	public void setDevice_registration_code(String device_registration_code) {
		this.device_registration_code = device_registration_code;
	}
	public String getRegistration_num() {
		return registration_num;
	}
	public void setRegistration_num(String registration_num) {
		this.registration_num = registration_num;
	}
	public String getDevice_qr_code() {
		return device_qr_code;
	}
	public void setDevice_qr_code(String device_qr_code) {
		this.device_qr_code = device_qr_code;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getModify_type() {
		return modify_type;
	}
	public void setModify_type(String modify_type) {
		this.modify_type = modify_type;
	}
	public String getDevice_registration_code_old() {
		return device_registration_code_old;
	}
	public void setDevice_registration_code_old(String device_registration_code_old) {
		this.device_registration_code_old = device_registration_code_old;
	}
	public String getOp_user_id() {
		return op_user_id;
	}
	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}
	public String getOp_user_name() {
		return op_user_name;
	}
	public void setOp_user_name(String op_user_name) {
		this.op_user_name = op_user_name;
	}
	public Date getOp_date() {
		return op_date;
	}
	public void setOp_date(Date op_date) {
		this.op_date = op_date;
	}
	
}
