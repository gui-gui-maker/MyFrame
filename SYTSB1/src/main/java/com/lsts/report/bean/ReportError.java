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
 * 不符合报告
 * ReportError entity. 
 * @author GaoYa
 * @date 2015-11-02 10:23:00
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "TZSB_REPORT_ERROR")
public class ReportError implements BaseEntity{

	
	private static final long serialVersionUID = 1L;
	
	private String id;	//	ID
	private String sn;	//  不符合编号（编号规则：1、质量部 2015-001 2、检验部门 JD1-2015-001）
	private String error_dep_id;	// 责任部门ID
	private String error_dep_name;	// 责任部门名称
	private String error_user_id;	// 责任人ID
	private String error_user_name;	// 责任人姓名
	private String find_user_id;	// 不符合审核员ID
	private String find_user_name;  // 不符合审核员姓名
	private Date   find_date;		// 不符合审核日期
	private String error_from;		// 不符合来源，数据字典（REPORT_ERROE_FROM）
	private String report_error_sn;	// 不符合来源之外部输入
	private String error_category;	// 不符合项目（0：报告 1：其他）其中报告需要填写不符合纠正流转表，其他不需要
	private String error_type;		// 不符合类型，数据字典（REPORT_ERROE_TYPE）
	private String error_desc;		// 不符合事实陈述	
	private String report_sn;		// 报告书编号（多个报告编号，以逗号分隔）
	private String report_status;	// 报告状态，数据字典（REPORT_STATUS）
	private String new_report_sn;	// 新报告书编号（多个报告编号，以逗号分隔）

	private Date   solve_end_date;	// 整改期限（请于XXXX年XX月XX日前完成整改）
	private String create_user_id;	// 记录人ID
	private String create_user_name;// 记录人姓名
	private Date   create_date;		// 记录日期
	private String check_user_id;	// 审核人ID
	private String check_user_name;	// 审核人姓名
	private String check_result;	// 审核结果（1：审核通过 2：审核不通过）
	private String check_remark;	// 审核备注（审核不通过时填写）
	private Date   check_date;		// 审核日期
	private String send_msg_status;	// 短信发送状态（0，发送成功；-10、用户认证失败；-11、ip或域名错误；-12、余额不足；-14、提交手机号超量；-15、短信内容含屏蔽关键字；-22、发送为空；）
	private String send_wx_status;	// 微信发送状态
	private String receive_user_id;		// 接收人ID
	private String receive_user_name;	// 接收人姓名
	private Date   receive_date;		// 接收日期

	private String deal_remark;			// 检验员备注处理结果（检验员完成不符合纠正后自动返写不符合纠正编号）
	private String deal_user_id;		// 检验员ID
	private String deal_user_name;		// 检验员姓名
	private Date   deal_date;			// 检验员纠正时间
	
	private String dep_head_id;			// 部门负责人ID（纠正措施完成情况）
	private String dep_head_name;		// 部门负责人姓名（纠正措施完成情况）
	private Date   dep_head_check_date;	// 部门负责人审核时间（纠正措施完成情况）
	private String dep_head_remark;		// 部门负责人备注（纠正措施完成情况）

	private String qua_end_result;		// 质量部确认纠正结果
	private String qua_head_id;			// 质量部负责人ID
	private String qua_head_name;		// 质量部负责人姓名
	private Date   qua_head_check_date;	// 质量部负责人确认日期

	private String data_status;			// 数据状态，数据字典（REPORT_ERROR_STATUS2）
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Collection<ReportErrorInfo> reportErrorInfo;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "reportError", orphanRemoval = true)
	public Collection<ReportErrorInfo> getReportErrorInfo() {
		return reportErrorInfo;
	}

	public void setReportErrorInfo(Collection<ReportErrorInfo> reportErrorInfo) {
		this.reportErrorInfo = reportErrorInfo;
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
	
	public String getData_status() {
		return data_status;
	}
	public void setData_status(String data_status) {
		this.data_status = data_status;
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
	public Date getSolve_end_date() {
		return solve_end_date;
	}
	public void setSolve_end_date(Date solve_end_date) {
		this.solve_end_date = solve_end_date;
	}
	public String getCreate_user_id() {
		return create_user_id;
	}
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}
	public String getCreate_user_name() {
		return create_user_name;
	}
	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getCheck_user_id() {
		return check_user_id;
	}
	public void setCheck_user_id(String check_user_id) {
		this.check_user_id = check_user_id;
	}
	public String getCheck_user_name() {
		return check_user_name;
	}
	public void setCheck_user_name(String check_user_name) {
		this.check_user_name = check_user_name;
	}
	public Date getCheck_date() {
		return check_date;
	}
	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}
	public String getReceive_user_id() {
		return receive_user_id;
	}
	public void setReceive_user_id(String receive_user_id) {
		this.receive_user_id = receive_user_id;
	}
	public String getReceive_user_name() {
		return receive_user_name;
	}
	public void setReceive_user_name(String receive_user_name) {
		this.receive_user_name = receive_user_name;
	}
	public Date getReceive_date() {
		return receive_date;
	}
	public void setReceive_date(Date receive_date) {
		this.receive_date = receive_date;
	}
	public String getNew_report_sn() {
		return new_report_sn;
	}
	public void setNew_report_sn(String new_report_sn) {
		this.new_report_sn = new_report_sn;
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
	public String getCheck_result() {
		return check_result;
	}
	public void setCheck_result(String check_result) {
		this.check_result = check_result;
	}
	public String getSend_msg_status() {
		return send_msg_status;
	}
	public void setSend_msg_status(String send_msg_status) {
		this.send_msg_status = send_msg_status;
	}
	public String getSend_wx_status() {
		return send_wx_status;
	}
	public void setSend_wx_status(String send_wx_status) {
		this.send_wx_status = send_wx_status;
	}
	public String getCheck_remark() {
		return check_remark;
	}
	public void setCheck_remark(String check_remark) {
		this.check_remark = check_remark;
	}
	public String getError_category() {
		return error_category;
	}
	public void setError_category(String error_category) {
		this.error_category = error_category;
	}
	
} 
