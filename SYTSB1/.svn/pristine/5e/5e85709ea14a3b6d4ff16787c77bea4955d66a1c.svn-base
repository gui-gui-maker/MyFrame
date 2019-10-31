package com.lsts.inspection.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 移动端原始记录检验项目解析表
 * ReportRecordParse entity. 
 * @author GaoYa
 * @date 2016-06-27 10:05:00
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tzsb_report_record_parse")
public class ReportRecordParse implements BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String id ;//ID
	private String fk_report_id;	// 报告类型ID
	private String report_name;		// 报告名称
	private String item_name;		// 检验项目代码（检验结果key）
	private String item_value;		// 检验项目默认值（检验结果value）
	private String is_check;		// 是否单独解析（0：否 1：是）
	private String is_checkbox;		// 是否多选框（0：否 1：是）
	private String check_key;		// 解析判断依据（检验结论key）
	private String check_type;		// 解析判断运算符
	private String check_value;		// 解析判断标准值
	private String remarks;			// 备注
	private String last_mdy_uid;	// 最后修改用户ID
	private String last_mdy_uname;  // 最后修改用户姓名
	private Date   last_mdy_date;	// 最后修改时间
	private String data_status;		// 数据状态，默认0（0：启用 99：停用）
	
	//private String fkReportId;	//报告书ID
	//private String reportName;	//报告名称
	private String recordItemName;	//原始记录项目
	private String reportItemName;	//报告书项目
	private String formule;		//转化公式
	private String formuleType;	//转化类型 1-计算 2 判断 3-预定义规则  4-反转规则 
	private String defaultValue;//转换默认值 如果没有特殊情况，直接用次默认值
	private String recordPageNo;//原始记录所在页码
	private String reportPageNo;//报告所在页码
	private String last_upd_by; // 最后更新人
	private String last_upd_name;	// 最后更新人
	private Date last_upd_date; 	// 最后更新时间
	//private String data_status;		// 数据状态（0：正常 98：潜在错误对应项 99：已作废 ）
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFk_report_id() {
		return fk_report_id;
	}
	public void setFk_report_id(String fk_report_id) {
		this.fk_report_id = fk_report_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_value() {
		return item_value;
	}
	public void setItem_value(String item_value) {
		this.item_value = item_value;
	}
	public String getLast_mdy_uid() {
		return last_mdy_uid;
	}
	public void setLast_mdy_uid(String last_mdy_uid) {
		this.last_mdy_uid = last_mdy_uid;
	}
	public String getLast_mdy_uname() {
		return last_mdy_uname;
	}
	public void setLast_mdy_uname(String last_mdy_uname) {
		this.last_mdy_uname = last_mdy_uname;
	}
	public String getData_status() {
		return data_status;
	}
	public void setData_status(String data_status) {
		this.data_status = data_status;
	}
	public String getIs_check() {
		return is_check;
	}
	public void setIs_check(String is_check) {
		this.is_check = is_check;
	}
	public String getCheck_value() {
		return check_value;
	}
	public void setCheck_value(String check_value) {
		this.check_value = check_value;
	}
	public Date getLast_mdy_date() {
		return last_mdy_date;
	}
	public void setLast_mdy_date(Date last_mdy_date) {
		this.last_mdy_date = last_mdy_date;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getReport_name() {
		return report_name;
	}
	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}
	public String getCheck_type() {
		return check_type;
	}
	public void setCheck_type(String check_type) {
		this.check_type = check_type;
	}
	public String getIs_checkbox() {
		return is_checkbox;
	}
	public void setIs_checkbox(String is_checkbox) {
		this.is_checkbox = is_checkbox;
	}
	public String getCheck_key() {
		return check_key;
	}
	public void setCheck_key(String check_key) {
		this.check_key = check_key;
	}
	
	//-----------新版app-----------------------
	@Column(name="RECORD_ITEM_NAME")
	public String getRecordItemName() {
		return recordItemName;
	}
	public void setRecordItemName(String recordItemName) {
		this.recordItemName = recordItemName;
	}
	@Column(name="REPORT_ITEM_NAME")
	public String getReportItemName() {
		return reportItemName;
	}
	public void setReportItemName(String reportItemName) {
		this.reportItemName = reportItemName;
	}
	@Column(name="FORMULE")
	public String getFormule() {
		return formule;
	}
	public void setFormule(String formule) {
		this.formule = formule;
	}
	@Column(name="FORMULE_TYPE")
	public String getFormuleType() {
		return formuleType;
	}
	public void setFormuleType(String formuleType) {
		this.formuleType = formuleType;
	}
	@Column(name="DEFAULT_VALUE")
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	@Column(name="RECORD_PAGE_NO")
	public String getRecordPageNo() {
		return recordPageNo;
	}
	public void setRecordPageNo(String recordPageNo) {
		this.recordPageNo = recordPageNo;
	}
	@Column(name="REPORT_PAGE_NO")
	public String getReportPageNo() {
		return reportPageNo;
	}
	public void setReportPageNo(String reportPageNo) {
		this.reportPageNo = reportPageNo;
	}
	
	public String getLast_upd_by() {
		return last_upd_by;
	}
	public void setLast_upd_by(String last_upd_by) {
		this.last_upd_by = last_upd_by;
	}
	public String getLast_upd_name() {
		return last_upd_name;
	}
	public void setLast_upd_name(String last_upd_name) {
		this.last_upd_name = last_upd_name;
	}
	public Date getLast_upd_date() {
		return last_upd_date;
	}
	public void setLast_upd_date(Date last_upd_date) {
		this.last_upd_date = last_upd_date;
	}
	//新版app------------------------------------------------
}
