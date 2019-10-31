package com.lsts.approve.bean;


import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;
/**
 * 人员和报告中间表
 * @author Guido
 *
 */
@Entity
@Table(name = "BASE_CERTIFICATE_BY")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CertificateBy implements BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String user_id;
	private String user_account;
	private String user_name;
	private String deptId;//部门
	private String dept;//部门
	private String deviceTypeId;//设备类型
	private String deviceType;//设备类型
	private String is_substitute_person;//是否是主签人员（1：是 0：否）
	private String is_ministerofaduit_person;//是否签本部门部长（1：是 0：否）
	private String mayCertDeptId;//可以审部门
	private String mayCertDept;//可以审部门
	private Float percentage;//份额
	private String report_id;//具体某种报告
	private String report_name;//报告名称
	private String status;//状态(0:停用，1:启用)
	private String is_vacation;//是否在岗（0:在岗 1:请假）
	private String fk_rule;//外键，规则id

	
	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name ="dept")
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	@Column(name ="percentage")
	public Float getPercentage() {
		return percentage;
	}
	public void setPercentage(Float percentage) {
		this.percentage = percentage;
	}
	@Column(name ="Dept_Id")
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	@Column(name ="May_Cert_Dept_Id")
	public String getMayCertDeptId() {
		return mayCertDeptId;
	}
	public void setMayCertDeptId(String mayCertDeptId) {
		this.mayCertDeptId = mayCertDeptId;
	}
	@Column(name ="May_Cert_Dept")
	public String getMayCertDept() {
		return mayCertDept;
	}
	public void setMayCertDept(String mayCertDept) {
		this.mayCertDept = mayCertDept;
	}

	@Column(name ="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name ="Device_Type_Id")
	public String getDeviceTypeId() {
		return deviceTypeId;
	}
	public void setDeviceTypeId(String deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}
	@Column(name ="Device_Type")
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getReport_id() {
		return report_id;
	}
	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
	public String getReport_name() {
		return report_name;
	}
	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}
	public String getIs_vacation() {
		return is_vacation;
	}
	public void setIs_vacation(String is_vacation) {
		this.is_vacation = is_vacation;
	}
	public String getIs_substitute_person() {
		return is_substitute_person;
	}
	public void setIs_substitute_person(String is_substitute_person) {
		this.is_substitute_person = is_substitute_person;
	}
	public String getIs_ministerofaduit_person() {
		return is_ministerofaduit_person;
	}
	public void setIs_ministerofaduit_person(String is_ministerofaduit_person) {
		this.is_ministerofaduit_person = is_ministerofaduit_person;
	}
	public String getFk_rule() {
		return fk_rule;
	}
	public void setFk_rule(String fk_rule) {
		this.fk_rule = fk_rule;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
}
