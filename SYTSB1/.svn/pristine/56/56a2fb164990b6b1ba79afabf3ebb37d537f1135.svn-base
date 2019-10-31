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
 * 不符合报告明细表
 * ReportErrorInfo entity. 
 * @author GaoYa
 * @date 2015-11-02 10:39:00
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = { "reportError"})
@Table(name = "TZSB_REPORT_ERROR_INFO2")
public class ReportErrorInfo implements BaseEntity{

	
	private static final long serialVersionUID = 1L;
	
	private String id;	//	ID	
	private String report_sn;		// 报告书编号（纠错报告）
	private String info_id;			// 纠错报告业务信息ID
	private String report_id;		// 纠错报告类型ID
	private String error_type;		// 不符合类型，数据字典（REPORT_ERROE_TYPE1：一般、严重）
	private String error_desc;		// 不符合事实陈述	
	private String error_category;	// 不符合项目（0：报告 1：其他）其中报告需要填写不符合纠正流转表，其他不需要
	private String create_user_id;	// 创建人ID
	private String create_user_name;// 创建人姓名
	private Date   create_date;		// 创建日期
	private String mdy_user_id;		// 最后修改人ID
	private String mdy_user_name;	// 最后修改人姓名
	private Date   mdy_date;		// 最后修改日期
	private String data_status;		// 数据状态（0：正常 99：已删除）
	private String error_status;    //不符合的种类
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public ReportError reportError;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_report_error_id")
	public ReportError getReportError() {
		return reportError;
	}

	public void setReportError(ReportError reportError) {
		this.reportError = reportError;
	}
	
	public String getReport_sn() {
		return report_sn;
	}
	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
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
	public String getMdy_user_id() {
		return mdy_user_id;
	}
	public void setMdy_user_id(String mdy_user_id) {
		this.mdy_user_id = mdy_user_id;
	}
	public String getMdy_user_name() {
		return mdy_user_name;
	}
	public void setMdy_user_name(String mdy_user_name) {
		this.mdy_user_name = mdy_user_name;
	}
	public Date getMdy_date() {
		return mdy_date;
	}
	public void setMdy_date(Date mdy_date) {
		this.mdy_date = mdy_date;
	}
	public String getData_status() {
		return data_status;
	}
	public void setData_status(String data_status) {
		this.data_status = data_status;
	}
	public String getInfo_id() {
		return info_id;
	}
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}
	public String getReport_id() {
		return report_id;
	}
	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
	public String getError_category() {
		return error_category;
	}
	public void setError_category(String error_category) {
		this.error_category = error_category;
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
	public String getError_status() {
		return error_status;
	}
	public void setError_status(String error_status) {
		this.error_status = error_status;
	}
	
	
} 
