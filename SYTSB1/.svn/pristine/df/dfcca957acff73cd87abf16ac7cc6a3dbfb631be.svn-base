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
 * 检验资料报送打印签收主表
 * 
 * @ClassName ReportPrint
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-09-06 下午01:00:00
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "TZSB_REPORT_PRINT")
public class ReportPrint implements BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private String	id	;	//	ID
	private String  sn;				// 业务流水号
	private String  org_id;      	// 部门ID
	private String  org_name;		// 部门名称
	private String	enter_op_id;	// 录入人ID
	private String	enter_op_name;	// 录入人姓名
	private Date	enter_date;		// 录入时间
	private String  commit_user_id;     // 报送人ID
	private String  commit_user_name;	// 报送人姓名
	private Date	commit_date;		// 报送时间
	private String  receive_user_id;	// 签收人ID
	private String  receive_user_name;	// 签收人姓名
	private Date    receive_time;		// 签收时间
	private String  com_name; 			// 使用/制造/安装单位名称
	private String  report_sn; 			// 报告书编号（多个以逗号分隔）
	private String  data_status;		// 数据状态（0：创建 1：已提交 2：已签收 99：已删除）

	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Collection<ReportPrintRecord> reportPrintRecord;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "reportPrint", orphanRemoval = true)
	public Collection<ReportPrintRecord> getReportPrintRecord() {
		return reportPrintRecord;
	}

	public void setReportPrintRecord(Collection<ReportPrintRecord> reportPrintRecord) {
		this.reportPrintRecord = reportPrintRecord;
	}
	
	public String getEnter_op_id() {
		return enter_op_id;
	}
	public void setEnter_op_id(String enter_op_id) {
		this.enter_op_id = enter_op_id;
	}
	public String getEnter_op_name() {
		return enter_op_name;
	}
	public void setEnter_op_name(String enter_op_name) {
		this.enter_op_name = enter_op_name;
	}
	public Date getEnter_date() {
		return enter_date;
	}
	public void setEnter_date(Date enter_date) {
		this.enter_date = enter_date;
	}
	public Date getCommit_date() {
		return commit_date;
	}
	public void setCommit_date(Date commit_date) {
		this.commit_date = commit_date;
	}
	public String getData_status() {
		return data_status;
	}
	public void setData_status(String data_status) {
		this.data_status = data_status;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getCommit_user_id() {
		return commit_user_id;
	}
	public void setCommit_user_id(String commit_user_id) {
		this.commit_user_id = commit_user_id;
	}
	public String getCommit_user_name() {
		return commit_user_name;
	}
	public void setCommit_user_name(String commit_user_name) {
		this.commit_user_name = commit_user_name;
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
	public Date getReceive_time() {
		return receive_time;
	}
	public void setReceive_time(Date receive_time) {
		this.receive_time = receive_time;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getReport_sn() {
		return report_sn;
	}
	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}
}
