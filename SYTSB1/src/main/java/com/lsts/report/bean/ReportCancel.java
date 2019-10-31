package com.lsts.report.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 报告纠正记录
 * ReportCancel entity. 
 * @author GaoYa
 * @date 2014-07-10 11:45:00
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "TZSB_REPORT_CANCEL")
public class ReportCancel implements BaseEntity{

	
	private static final long serialVersionUID = 1L;
	
	private String id;	//	ID
	private String sn;	//  纠正编号
	private String report_sn;		// 报告书编号
	private String report_status;	// 报告状态，数据字典（REPORT_STATUS）
	private String apply_user_id;	// 责任人ID
	private String apply_user_name;	// 责任人姓名
	private String apply_dep_id;	// 责任部门ID
	private String apply_dep_name;	// 责任部门名称
	private String apply_reason;	// 申请纠正原因，数据字典（REPORT_ERROR_APPLY_REASON）
	private Date   apply_date;		// 申请纠正日期
	private String error_from;		// 不符合（或潜在不符合）来源，数据字典（REPORT_ERROE_FROM）
	private String error_type;		// 不符合类型，数据字典（REPORT_ERROE_TYPE）
	private String error_desc;		// 不符合（或潜在不符合）描述
	private String error_reason;	// 不符合（或潜在不符合）原因及影响分析
	private String deal_plan;		// 纠正措施计划，数据字典（REPORT_PLAN_TIPS）
	private String pre_plan;		// 预防措施计划，数据字典（REPORT_PRE_PLAN_TIPS）
	private Date   plan_end_date;	// 计划完成时间
	private String apply_dep_head_id;	// 责任部门负责人ID
	private String apply_dep_head_name;	// 责任部门负责人姓名
	private Date   apply_head_check_date;	// 责任部门负责人审核时间
	private String apply_head_check_result;	// 责任部门负责人审核结果（0：审核通过 1：审核未通过）
	private String qua_dep_check_id;	// 质保工程师ID
	private String qua_dep_check_name;	// 质保工程师姓名
	private String qua_dep_check_result;// 质保工程师审核结果（0：审核通过 1：审核未通过）
	private Date   qua_dep_check_date;	// 质保工程师审核时间
	private String new_report_sn;		// 新报告编号
	private String deal_remark;			// 责任人备注
	private String deal_user_id;		// 责任人ID
	private String deal_user_name;		// 责任人姓名
	private Date   deal_date;			// 纠正时间
	private String confirm_result;		// 业务服务部确认结果，数据字典（REPORT_ERROR_CONFIRM_RESULT）
	private String confirm_user_id;		// 业务服务部经办人ID
	private String confirm_user_name;	// 业务服务部经办人姓名
	private Date confirm_date;			// 业务服务部确认时间
	private String dep_head_id;			// 部门负责人ID（纠正措施完成情况）
	private String dep_head_name;		// 部门负责人姓名（纠正措施完成情况）
	private Date   dep_head_check_date;	// 部门负责人审核时间（纠正措施完成情况）
	private String dep_head_remark;		// 部门负责人备注（纠正措施完成情况）
	
	private String qua_end_result;		// 质量部纠正结果
	private String qua_head_id;			// 质量部负责人ID
	private String qua_head_name;		// 质量部负责人姓名
	private Date   qua_head_check_date;	// 质量部负责人审核时间
	private String data_status;			// 数据状态，数据字典（REPORT_CANCEL_STATUS）


	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getApply_user_id() {
		return apply_user_id;
	}
	public void setApply_user_id(String apply_user_id) {
		this.apply_user_id = apply_user_id;
	}
	public String getApply_user_name() {
		return apply_user_name;
	}
	public void setApply_user_name(String apply_user_name) {
		this.apply_user_name = apply_user_name;
	}
	public String getApply_dep_id() {
		return apply_dep_id;
	}
	public void setApply_dep_id(String apply_dep_id) {
		this.apply_dep_id = apply_dep_id;
	}
	public String getApply_dep_name() {
		return apply_dep_name;
	}
	public void setApply_dep_name(String apply_dep_name) {
		this.apply_dep_name = apply_dep_name;
	}
	public String getApply_reason() {
		return apply_reason;
	}
	public void setApply_reason(String apply_reason) {
		this.apply_reason = apply_reason;
	}
	public Date getApply_date() {
		return apply_date;
	}
	public void setApply_date(Date apply_date) {
		this.apply_date = apply_date;
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
	public String getError_reason() {
		return error_reason;
	}
	public void setError_reason(String error_reason) {
		this.error_reason = error_reason;
	}
	public String getDeal_plan() {
		return deal_plan;
	}
	public void setDeal_plan(String deal_plan) {
		this.deal_plan = deal_plan;
	}
	public Date getPlan_end_date() {
		return plan_end_date;
	}
	public void setPlan_end_date(Date plan_end_date) {
		this.plan_end_date = plan_end_date;
	}
	public String getApply_dep_head_id() {
		return apply_dep_head_id;
	}
	public void setApply_dep_head_id(String apply_dep_head_id) {
		this.apply_dep_head_id = apply_dep_head_id;
	}
	public String getApply_dep_head_name() {
		return apply_dep_head_name;
	}
	public void setApply_dep_head_name(String apply_dep_head_name) {
		this.apply_dep_head_name = apply_dep_head_name;
	}
	public Date getApply_head_check_date() {
		return apply_head_check_date;
	}
	public void setApply_head_check_date(Date apply_head_check_date) {
		this.apply_head_check_date = apply_head_check_date;
	}
	public String getQua_dep_check_id() {
		return qua_dep_check_id;
	}
	public void setQua_dep_check_id(String qua_dep_check_id) {
		this.qua_dep_check_id = qua_dep_check_id;
	}
	public String getQua_dep_check_name() {
		return qua_dep_check_name;
	}
	public void setQua_dep_check_name(String qua_dep_check_name) {
		this.qua_dep_check_name = qua_dep_check_name;
	}
	public Date getQua_dep_check_date() {
		return qua_dep_check_date;
	}
	public void setQua_dep_check_date(Date qua_dep_check_date) {
		this.qua_dep_check_date = qua_dep_check_date;
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
	public String getQua_end_result() {
		return qua_end_result;
	}
	public void setQua_end_result(String qua_end_result) {
		this.qua_end_result = qua_end_result;
	}
	public String getQua_head_id() {
		return qua_head_id;
	}
	public void setQua_head_id(String qua_head_id) {
		this.qua_head_id = qua_head_id;
	}
	public String getQua_head_name() {
		return qua_head_name;
	}
	public void setQua_head_name(String qua_head_name) {
		this.qua_head_name = qua_head_name;
	}
	public Date getQua_head_check_date() {
		return qua_head_check_date;
	}
	public void setQua_head_check_date(Date qua_head_check_date) {
		this.qua_head_check_date = qua_head_check_date;
	}
	public String getQua_dep_check_result() {
		return qua_dep_check_result;
	}
	public void setQua_dep_check_result(String qua_dep_check_result) {
		this.qua_dep_check_result = qua_dep_check_result;
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
	public String getApply_head_check_result() {
		return apply_head_check_result;
	}
	public void setApply_head_check_result(String apply_head_check_result) {
		this.apply_head_check_result = apply_head_check_result;
	}
	public String getPre_plan() {
		return pre_plan;
	}
	public void setPre_plan(String pre_plan) {
		this.pre_plan = pre_plan;
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
	
	
} 
