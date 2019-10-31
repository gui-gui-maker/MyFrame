package com.lsts.report.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * 报表数据
 * 
 * @author 肖慈边 2014-1-23
 * 
 */

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "base_reports")
public class Report implements BaseEntity{

	
	private static final long serialVersionUID = 1L;
	
	private String	id	;			// ID
	private String	report_name	;	// 报告名称
	private String	report_file	;	// 报告报表文件
	private String	ysjl_name	;	// 原始记录名称
	private String	ysjl_file	;	// 原始记录报表文件
	private String  ysjl_pages;		// 原始记录目录页码（以逗号分隔）
	private String	mrdataset;		// 数据集名称
	private String	rootpath;		// 报表路径
	private String	report_sn;		// 报告书编号格式
	private String	is_print_tags;	// 是否打印合格标签
	private String	report_file_tags;	// 标签名称
	private String	mrdataset_tags;	// 标签数据集名称
	private Integer	printcopies;	// 报告打印份数
	private Integer print_ysjl_copies;	// 原始记录打印份数
	private String	printername;	// 打印机名称
	private Integer	interval;		// 两次检验间隔时间(天)
	private String	printername_tags;	// 标签打印机名称
	private String	old_check_type;		// 老系统所属检验类别
	private String	sort_order;			// 排序字段
	private String	flag;				// 1 在用 0 停用
	private String  und_date; 			// 报告超期天数
	private String  info_tmp_name;		// 信息表模板名称
	private String  enter_type;			// 是否支持批量录入（excel导入），数据字典ba_sf  0：单份录入（否） 1：批量录入（支持excel导入）（是）
	private String  report_code;		// 报告编号
	private String  is_upload;			// 是否支持附件上传，数据字典ba_sf  0：否（不支持） 1：是（支持）
	private String  is_print_double;	// 是否双面打印 （0：否 1：是）
	private String  check_flow;			// 报告审批流程（0：两级审核 1：一级审核）
	private String  is_mdy_editor;		// 是否支持修改编制人，数据字典ba_sf  0：否（不支持） 1：是（支持）
	
	private String is_electronic_seal_man; 	// 是否电子签章 （1：是 2：否）
	private String is_xsq; 					// 是否带限速器（0：否 1：是）
	private String is_issue;				// 是否是签发指定报告类型（0：否 1：是）
	private String issue_msg_type;			// 签发后是否发送消息提醒（1：微信 2：短信 3：微信和短信 0：不发送 ）
	private String report_version;		// 报告版本（0：新版本 1：老版本）
	private String mdy_user_id;			// 最后修改人ID
	private String mdy_user_name;		// 最后修改人姓名
	private Date   mdy_date;			// 最后修改日期
	private String remark;				// 备注
	
	private String reportType;//报告类型 1- office模版 空或者0  明宇模版  2 js模板
	private String rtboxId;//js模板id
	private String rtboxCode;//js模板code
	private String recordModelId;//原始记录模板id
	private String recordModelCode;//原始记录模板code
	
	private String rtDefaultDirJson;//报告默认目录数据
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
//	// 关联证书对象
//	
//	public Collection<ReportCert> reportCert;
//
//	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "report", orphanRemoval = true)
//	public Collection<ReportCert> getReportCert() {
//		return reportCert;
//	}
//
//	public void setReportCert(Collection<ReportCert> reportCert) {
//		this.reportCert = reportCert;
//	}
//	
	public String getReport_name() {
		return report_name;
	}
	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}
	public String getReport_file() {
		return report_file;
	}
	public void setReport_file(String report_file) {
		this.report_file = report_file;
	}
	public String getMrdataset() {
		return mrdataset;
	}
	public void setMrdataset(String mrdataset) {
		this.mrdataset = mrdataset;
	}
	public String getRootpath() {
		return rootpath;
	}
	public void setRootpath(String rootpath) {
		this.rootpath = rootpath;
	}
	public String getReport_sn() {
		return report_sn;
	}
	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}
	public String getIs_print_tags() {
		return is_print_tags;
	}
	public void setIs_print_tags(String is_print_tags) {
		this.is_print_tags = is_print_tags;
	}
	public String getReport_file_tags() {
		return report_file_tags;
	}
	public void setReport_file_tags(String report_file_tags) {
		this.report_file_tags = report_file_tags;
	}
	public String getMrdataset_tags() {
		return mrdataset_tags;
	}
	public void setMrdataset_tags(String mrdataset_tags) {
		this.mrdataset_tags = mrdataset_tags;
	}
	public Integer getPrintcopies() {
		return printcopies;
	}
	public void setPrintcopies(Integer printcopies) {
		this.printcopies = printcopies;
	}
	public String getPrintername() {
		return printername;
	}
	public void setPrintername(String printername) {
		this.printername = printername;
	}
	public Integer getInterval() {
		return interval;
	}
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	public String getPrintername_tags() {
		return printername_tags;
	}
	public void setPrintername_tags(String printername_tags) {
		this.printername_tags = printername_tags;
	}
	public String getOld_check_type() {
		return old_check_type;
	}
	public void setOld_check_type(String old_check_type) {
		this.old_check_type = old_check_type;
	}
	public String getSort_order() {
		return sort_order;
	}
	public void setSort_order(String sort_order) {
		this.sort_order = sort_order;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getInfo_tmp_name() {
		return info_tmp_name;
	}
	public void setInfo_tmp_name(String info_tmp_name) {
		this.info_tmp_name = info_tmp_name;
	}
	public String getEnter_type() {
		return enter_type;
	}
	public void setEnter_type(String enter_type) {
		this.enter_type = enter_type;
	}
	public String getReport_code() {
		return report_code;
	}
	public void setReport_code(String report_code) {
		this.report_code = report_code;
	}
	public String getIs_upload() {
		return is_upload;
	}
	public void setIs_upload(String is_upload) {
		this.is_upload = is_upload;
	}
	/**
	 * @return the is_print_double
	 */
	public String getIs_print_double() {
		return is_print_double;
	}
	/**
	 * @param is_print_double the is_print_double to set
	 */
	public void setIs_print_double(String is_print_double) {
		this.is_print_double = is_print_double;
	}
	
	public String getYsjl_file() {
		return ysjl_file;
	}
	public void setYsjl_file(String ysjl_file) {
		this.ysjl_file = ysjl_file;
	}
	public String getCheck_flow() {
		return check_flow;
	}

	public void setCheck_flow(String check_flow) {
		this.check_flow = check_flow;
	}

	// 是否電子簽章get
	public String getIs_electronic_seal_man() {
		return is_electronic_seal_man;
	}

	public void setIs_electronic_seal_man(String is_electronic_seal_man) {
		this.is_electronic_seal_man = is_electronic_seal_man;
	}

	public String getUnd_date() {
		return und_date;
	}

	public void setUnd_date(String und_date) {
		this.und_date = und_date;
	}
	public String getYsjl_name() {
		return ysjl_name;
	}
	public void setYsjl_name(String ysjl_name) {
		this.ysjl_name = ysjl_name;
	}
	public String getYsjl_pages() {
		return ysjl_pages;
	}
	public void setYsjl_pages(String ysjl_pages) {
		this.ysjl_pages = ysjl_pages;
	}
	public Integer getPrint_ysjl_copies() {
		return print_ysjl_copies;
	}
	public void setPrint_ysjl_copies(Integer print_ysjl_copies) {
		this.print_ysjl_copies = print_ysjl_copies;
	}
	public String getIs_mdy_editor() {
		return is_mdy_editor;
	}
	public void setIs_mdy_editor(String is_mdy_editor) {
		this.is_mdy_editor = is_mdy_editor;
	}
	public String getIs_xsq() {
		return is_xsq;
	}
	public void setIs_xsq(String is_xsq) {
		this.is_xsq = is_xsq;
	}
	public String getIs_issue() {
		return is_issue;
	}
	public void setIs_issue(String is_issue) {
		this.is_issue = is_issue;
	}
	public String getIssue_msg_type() {
		return issue_msg_type;
	}
	public void setIssue_msg_type(String issue_msg_type) {
		this.issue_msg_type = issue_msg_type;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getReport_version() {
		return report_version;
	}
	public void setReport_version(String report_version) {
		this.report_version = report_version;
	}
	
	@Column(name="REPORT_TYPE")
	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	@Column(name="RECORD_MODEL_ID")
	public String getRecordModelId() {
		return recordModelId;
	}

	public void setRecordModelId(String recordModelId) {
		this.recordModelId = recordModelId;
	}

	@Column(name="RECORD_MODEL_CODE")
	public String getRecordModelCode() {
		return recordModelCode;
	}

	public void setRecordModelCode(String recordModelCode) {
		this.recordModelCode = recordModelCode;
	}
	
	@Column(name = "RTBOX_ID")
	public String getRtboxId() {
		return rtboxId;
	}

	public void setRtboxId(String rtboxId) {
		this.rtboxId = rtboxId;
	}

	@Column(name = "RTBOX_CODE")
	public String getRtboxCode() {
		return rtboxCode;
	}

	public void setRtboxCode(String rtboxCode) {
		this.rtboxCode = rtboxCode;
	}
	
	@Column(name="RT_DEFAULT_DIR_JSON")
	public String getRtDefaultDirJson(){
		return rtDefaultDirJson;
	}
		
	public void setRtDefaultDirJson(String rtDefaultDirJson){
	this.rtDefaultDirJson=rtDefaultDirJson;
	}
	
} 