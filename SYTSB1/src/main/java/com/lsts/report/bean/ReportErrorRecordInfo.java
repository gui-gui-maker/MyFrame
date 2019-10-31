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
 * 检验报告/证书不符合纠正流转明细表
 * ReportErrorRecordInfo entity. 
 * @author GaoYa
 * @date 2015-09-22 16:23:00
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = { "reportErrorRecord"})
@Table(name = "TZSB_REPORT_ERROR_INFO")
public class ReportErrorRecordInfo implements BaseEntity{

	
	private static final long serialVersionUID = 1L;
	
	private String id;	//	ID	
	private String report_sn;		// 报告书编号（纠错报告）
	private String info_id;			// 纠错报告业务信息ID
	private String report_id;		// 纠错报告类型ID
	private String error_type;		// 不符合类型，数据字典（REPORT_ERROE_TYPE1）
	private String error_desc;		// 不符合事实陈述	
	private String report_status;	// 报告状态，数据字典（REPORT_STATUS：1 已存档、2 已打印）
	private String new_report_sn;	// 新报告编号
	private String new_info_id;		// 新报告业务信息ID
	private String new_report_id;	// 新报告类型ID
	private String deal_remark;		// 检验员处理结果
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
	
	public ReportErrorRecord reportErrorRecord;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_report_error_record_id")
	public ReportErrorRecord getReportErrorRecord() {
		return reportErrorRecord;
	}

	public void setReportErrorRecord(ReportErrorRecord reportErrorRecord) {
		this.reportErrorRecord = reportErrorRecord;
	}
	
	public String getReport_sn() {
		return report_sn;
	}
	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}
	
	public String getNew_report_sn() {
		return new_report_sn;
	}
	public void setNew_report_sn(String new_report_sn) {
		this.new_report_sn = new_report_sn;
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
	public String getReport_status() {
		return report_status;
	}
	public void setReport_status(String report_status) {
		this.report_status = report_status;
	}
	public String getDeal_remark() {
		return deal_remark;
	}
	public void setDeal_remark(String deal_remark) {
		this.deal_remark = deal_remark;
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
	public String getNew_info_id() {
		return new_info_id;
	}
	public void setNew_info_id(String new_info_id) {
		this.new_info_id = new_info_id;
	}
	public String getNew_report_id() {
		return new_report_id;
	}
	public void setNew_report_id(String new_report_id) {
		this.new_report_id = new_report_id;
	}
	public String getError_status() {
		return error_status;
	}
	public void setError_status(String error_status) {
		this.error_status = error_status;
	}

} 
