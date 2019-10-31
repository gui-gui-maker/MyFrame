package com.lsts.inspection.bean;


import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;


/*******************************************************************************
 * 
 * 
 * 
 * @author zpl
 * 
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "ts_report_validation")
public class ReportValidation implements BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private String	id	;	//	ID
	private String  validation_status	;	//	验证状态 0手动通过 1 未通过 3定时通过
	private String  validation_man	;	//	验证人
	private Date  validation_date	;	//	验证时间
	private String  report_sn	;	//	报告编号
	private String  check_type	;	//	检验类型
	private String  fk_inspection_info	;	//	检验业务表id
	private String  validation_type;//验证方式 0手动 1定时任务
	private String  validation_results;//验证返回结果
	private String  data_status;//标志是否通过定时任务 0否 1是
	private String  cost_date;//验证花费时间(毫秒)
	private String  audit_id;//审核人id
	private String  audit_name;//审核人
	private String  flow_num;//审核参数
	private String  acc_id;//审核参数
	private String  check_flow;//审核参数
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValidation_status() {
		return validation_status;
	}
	public void setValidation_status(String validation_status) {
		this.validation_status = validation_status;
	}
	public String getValidation_man() {
		return validation_man;
	}
	public void setValidation_man(String validation_man) {
		this.validation_man = validation_man;
	}
	public Date getValidation_date() {
		return validation_date;
	}
	public void setValidation_date(Date validation_date) {
		this.validation_date = validation_date;
	}
	public String getReport_sn() {
		return report_sn;
	}
	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}
	public String getCheck_type() {
		return check_type;
	}
	public void setCheck_type(String check_type) {
		this.check_type = check_type;
	}
	public String getFk_inspection_info() {
		return fk_inspection_info;
	}
	public void setFk_inspection_info(String fk_inspection_info) {
		this.fk_inspection_info = fk_inspection_info;
	}
	public String getValidation_type() {
		return validation_type;
	}
	public void setValidation_type(String validation_type) {
		this.validation_type = validation_type;
	}
	public String getValidation_results() {
		return validation_results;
	}
	public void setValidation_results(String validation_results) {
		this.validation_results = validation_results;
	}
	public String getData_status() {
		return data_status;
	}
	public void setData_status(String data_status) {
		this.data_status = data_status;
	}
	public String getCost_date() {
		return cost_date;
	}
	public void setCost_date(String cost_date) {
		this.cost_date = cost_date;
	}
	public String getAudit_id() {
		return audit_id;
	}
	public void setAudit_id(String audit_id) {
		this.audit_id = audit_id;
	}
	public String getAudit_name() {
		return audit_name;
	}
	public void setAudit_name(String audit_name) {
		this.audit_name = audit_name;
	}
	public String getFlow_num() {
		return flow_num;
	}
	public void setFlow_num(String flow_num) {
		this.flow_num = flow_num;
	}
	public String getAcc_id() {
		return acc_id;
	}
	public void setAcc_id(String acc_id) {
		this.acc_id = acc_id;
	}
	public String getCheck_flow() {
		return check_flow;
	}
	public void setCheck_flow(String check_flow) {
		this.check_flow = check_flow;
	}
	
	
}
