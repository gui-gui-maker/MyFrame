package com.lsts.inspection.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 系统自动随机分配报告签发日志
 * SysAutoIssueLog entity. 
 * @author GaoYa
 * @date 2017-06-26 10:35:00
 */
@Entity
@Table(name = "sys_auto_issue_log")
public class SysAutoIssueLog implements BaseEntity{

	private static final long serialVersionUID = 1L;
	private String id;			// 流水号	
	private String business_id;	// 报告业务ID
	private String report_sn;	// 报告编号
	private String report_com_name;	// 报告使用单位
	private String check_unit_id;	// 报告检验部门ID
	private String device_type;	// 设备类别
	private String op_user_id;	// 提交用户ID
	private String op_user_name;// 提交用户姓名
	private Date   op_time;		// 操作时间
	private String op_action;	// 操作动作
	private String op_remark;	// 操作描述
	private String to_user_id;	// 目标用户ID
	private String to_user_name;// 目标用户姓名
	private String issue_type;	// 分配规则（0：相同使用单位优先分配 1、量少优先分配）
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusiness_id() {
		return business_id;
	}
	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
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
	public Date getOp_time() {
		return op_time;
	}
	public void setOp_time(Date op_time) {
		this.op_time = op_time;
	}
	public String getOp_action() {
		return op_action;
	}
	public void setOp_action(String op_action) {
		this.op_action = op_action;
	}
	public String getOp_remark() {
		return op_remark;
	}
	public void setOp_remark(String op_remark) {
		this.op_remark = op_remark;
	}
	public String getTo_user_id() {
		return to_user_id;
	}
	public void setTo_user_id(String to_user_id) {
		this.to_user_id = to_user_id;
	}
	public String getTo_user_name() {
		return to_user_name;
	}
	public void setTo_user_name(String to_user_name) {
		this.to_user_name = to_user_name;
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
	public String getCheck_unit_id() {
		return check_unit_id;
	}
	public void setCheck_unit_id(String check_unit_id) {
		this.check_unit_id = check_unit_id;
	}
	public String getIssue_type() {
		return issue_type;
	}
	public void setIssue_type(String issue_type) {
		this.issue_type = issue_type;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
}
