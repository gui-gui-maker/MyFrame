package com.lsts.report.bean;

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

/**
 * 检验报告/证书不符合纠正流转主表
 * ReportErrorRecord entity. 
 * @author GaoYa
 * @date 2015-09-21 10:23:00
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "TZSB_REPORT_ERROR_RECORD")
public class ReportErrorRecord implements BaseEntity{

	
	private static final long serialVersionUID = 1L;
	
	private String id;	//	ID
	private String fk_report_error_id;	// 不符合报告ID
	private String sn;	//  纠正编号（编号规则：1、质量部 2015-001 2、检验部门 JD1-2015-001）
	private String error_dep_id;	// 责任部门ID
	private String error_dep_name;	// 责任部门名称
	private String error_user_id;	// 责任人ID
	private String error_user_name;	// 责任人姓名
	private String find_user_id;	// 不符合审核员ID
	private String find_user_name;  // 不符合审核员姓名
	private Date   find_date;		// 不符合审核日期
	private String error_from;		// 不符合来源，数据字典（REPORT_ERROE_FROM）
	private String report_error_sn;	// 不符合编号（前缀默认CTJC-019-B08-XXXX，XXXX：不符合编号）
	private String error_type;		// 不符合类型，数据字典（REPORT_ERROE_TYPE1）
	private String error_desc;		// 不符合事实陈述	
	private String report_sn;		// 报告书编号（多个报告编号，以逗号分隔）
	private String report_status;	// 报告状态，数据字典（ERROR_REPORT_STATUS）
	
	private String new_report_sn;		// 新报告编号（多个报告编号，以逗号分隔）
	private String deal_remark;			// 检验员备注处理结果
	private String deal_user_id;		// 检验员ID
	private String deal_user_name;		// 检验员姓名
	private Date   deal_date;			// 检验员纠正时间
	
	/*业务服务部确认后，系统自动将报告作废*/
	private String confirm_result;		// 业务服务部确认纠正措施，数据字典（REPORT_ERROR_CONFIRM_RESULT）
	private String confirm_user_id;		// 业务服务部经办人ID
	private String confirm_user_name;	// 业务服务部经办人姓名
	private Date   confirm_date;		// 业务服务部确认时间
	private String confirm_flag;		// 业务服务部确认纠正措施版本标志（0：老版本 1：新版本）
	
	private String dep_head_id;			// 部门负责人ID（纠正措施完成情况）
	private String dep_head_name;		// 部门负责人姓名（纠正措施完成情况）
	private Date   dep_head_check_date;	// 部门负责人审核时间（纠正措施完成情况）
	private String dep_head_remark;		// 部门负责人备注（纠正措施完成情况）
	
	/*业务服务部外借报告*/
	private String is_borrow_report;	// 是否借报告（数据字典ba_sf 0：否 1：是）		
	private String borrow_user_id;		// 外借人员ID
	private String borrow_user_name;	// 外借人员姓名
	private Date   borrow_date;			// 外借日期	
	private String borrow_op_id;		// 外借经办人员ID
	private String borrow_op_name;		// 外借经办人员姓名
	private String borrow_desc;			// 外借备注

	
	private String data_status;			// 数据状态，数据字典（REPORT_ERROR_STATUS）
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Collection<ReportErrorRecordInfo> reportErrorRecordInfo;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "reportErrorRecord", orphanRemoval = true)
	public Collection<ReportErrorRecordInfo> getReportErrorRecordInfo() {
		return reportErrorRecordInfo;
	}

	public void setReportErrorRecordInfo(Collection<ReportErrorRecordInfo> reportErrorRecordInfo) {
		this.reportErrorRecordInfo = reportErrorRecordInfo;
	}
	
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getReport_sn() {
		return report_sn;
	}
	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}
	public String getReport_status() {
		return report_status;
	}
	public void setReport_status(String report_status) {
		this.report_status = report_status;
	}
	
	public String getError_from() {
		return error_from;
	}
	public void setError_from(String error_from) {
		this.error_from = error_from;
	}
	public String getError_type() {
		return error_type;
	}
	public void setError_type(String error_type) {
		this.error_type = error_type;
	}
	public String getError_desc() {
		return error_desc;
	}
	public void setError_desc(String error_desc) {
		this.error_desc = error_desc;
	}
	
	public String getNew_report_sn() {
		return new_report_sn;
	}
	public void setNew_report_sn(String new_report_sn) {
		this.new_report_sn = new_report_sn;
	}
	public String getDep_head_id() {
		return dep_head_id;
	}
	public void setDep_head_id(String dep_head_id) {
		this.dep_head_id = dep_head_id;
	}
	public String getDep_head_name() {
		return dep_head_name;
	}
	public void setDep_head_name(String dep_head_name) {
		this.dep_head_name = dep_head_name;
	}
	public Date getDep_head_check_date() {
		return dep_head_check_date;
	}
	public void setDep_head_check_date(Date dep_head_check_date) {
		this.dep_head_check_date = dep_head_check_date;
	}
	public String getConfirm_result() {
		return confirm_result;
	}
	public void setConfirm_result(String confirm_result) {
		this.confirm_result = confirm_result;
	}
	public String getConfirm_user_id() {
		return confirm_user_id;
	}
	public void setConfirm_user_id(String confirm_user_id) {
		this.confirm_user_id = confirm_user_id;
	}
	public String getConfirm_user_name() {
		return confirm_user_name;
	}
	public void setConfirm_user_name(String confirm_user_name) {
		this.confirm_user_name = confirm_user_name;
	}
	
	public String getData_status() {
		return data_status;
	}
	public void setData_status(String data_status) {
		this.data_status = data_status;
	}
	public Date getConfirm_date() {
		return confirm_date;
	}
	public void setConfirm_date(Date confirm_date) {
		this.confirm_date = confirm_date;
	}
	
	public String getDeal_remark() {
		return deal_remark;
	}
	public void setDeal_remark(String deal_remark) {
		this.deal_remark = deal_remark;
	}
	public String getDeal_user_id() {
		return deal_user_id;
	}
	public void setDeal_user_id(String deal_user_id) {
		this.deal_user_id = deal_user_id;
	}
	public String getDeal_user_name() {
		return deal_user_name;
	}
	public void setDeal_user_name(String deal_user_name) {
		this.deal_user_name = deal_user_name;
	}
	public Date getDeal_date() {
		return deal_date;
	}
	public void setDeal_date(Date deal_date) {
		this.deal_date = deal_date;
	}
	public String getDep_head_remark() {
		return dep_head_remark;
	}
	public void setDep_head_remark(String dep_head_remark) {
		this.dep_head_remark = dep_head_remark;
	}
	public String getError_dep_id() {
		return error_dep_id;
	}
	public void setError_dep_id(String error_dep_id) {
		this.error_dep_id = error_dep_id;
	}
	public String getError_dep_name() {
		return error_dep_name;
	}
	public void setError_dep_name(String error_dep_name) {
		this.error_dep_name = error_dep_name;
	}
	public String getFind_user_id() {
		return find_user_id;
	}
	public void setFind_user_id(String find_user_id) {
		this.find_user_id = find_user_id;
	}
	public String getFind_user_name() {
		return find_user_name;
	}
	public void setFind_user_name(String find_user_name) {
		this.find_user_name = find_user_name;
	}
	public Date getFind_date() {
		return find_date;
	}
	public void setFind_date(Date find_date) {
		this.find_date = find_date;
	}
	public String getReport_error_sn() {
		return report_error_sn;
	}
	public void setReport_error_sn(String report_error_sn) {
		this.report_error_sn = report_error_sn;
	}
	public String getError_user_id() {
		return error_user_id;
	}
	public void setError_user_id(String error_user_id) {
		this.error_user_id = error_user_id;
	}
	public String getError_user_name() {
		return error_user_name;
	}
	public void setError_user_name(String error_user_name) {
		this.error_user_name = error_user_name;
	}
	public String getIs_borrow_report() {
		return is_borrow_report;
	}
	public void setIs_borrow_report(String is_borrow_report) {
		this.is_borrow_report = is_borrow_report;
	}
	public String getBorrow_user_id() {
		return borrow_user_id;
	}
	public void setBorrow_user_id(String borrow_user_id) {
		this.borrow_user_id = borrow_user_id;
	}
	public String getBorrow_user_name() {
		return borrow_user_name;
	}
	public void setBorrow_user_name(String borrow_user_name) {
		this.borrow_user_name = borrow_user_name;
	}
	public Date getBorrow_date() {
		return borrow_date;
	}
	public void setBorrow_date(Date borrow_date) {
		this.borrow_date = borrow_date;
	}
	public String getBorrow_op_id() {
		return borrow_op_id;
	}
	public void setBorrow_op_id(String borrow_op_id) {
		this.borrow_op_id = borrow_op_id;
	}
	public String getBorrow_op_name() {
		return borrow_op_name;
	}
	public void setBorrow_op_name(String borrow_op_name) {
		this.borrow_op_name = borrow_op_name;
	}
	public String getBorrow_desc() {
		return borrow_desc;
	}
	public void setBorrow_desc(String borrow_desc) {
		this.borrow_desc = borrow_desc;
	}
	public String getFk_report_error_id() {
		return fk_report_error_id;
	}
	public void setFk_report_error_id(String fk_report_error_id) {
		this.fk_report_error_id = fk_report_error_id;
	}
	public String getConfirm_flag() {
		return confirm_flag;
	}
	public void setConfirm_flag(String confirm_flag) {
		this.confirm_flag = confirm_flag;
	}
	
} 
