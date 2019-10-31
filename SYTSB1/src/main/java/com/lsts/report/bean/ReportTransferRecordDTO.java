package com.lsts.report.bean;

import java.util.Date;



/**
 * 检验资料报送打印签收明细数据传输对象
 * ReportPrintRecordDTO 
 * @author GaoYa
 * @date 2015-09-08 09:05:00
 */
public class ReportTransferRecordDTO{	
	private String id;
	private String sn;				// 业务流水号
	private String org_id;      	// 部门ID
	private String org_name;		// 部门名称
	private String report_sn;	    // 报告书编号
	private String info_id;			// 报告业务信息ID
	private String device_id;		// 设备ID
	private String report_id;		// 报告类型ID 
	private Double	advance_fees;	// 报告金额
	private Integer report_count;	// 报告份数
	private String made_unit_name;	// 制造单位名称
	private String com_name; 		// 使用单位名称
	private String construction_units_name; // 安装单位名称（现场施工单位名称）
	private String commit_user_id;     // 报送人ID
	private String commit_user_name;	// 报送人姓名
	private Date   commit_time;		// 报送时间
	private String commit_time_str;		// 报送时间
	private String receive_user_id;	// 接收人ID
	private String receive_user_name;	// 接收人姓名
	private Date   receive_time;		// 接收时间
	private String receive_time_str;	// 接收时间
	private String remark;			// 备注
	private String data_status;		// 状态（0：创建 1：已提交 2：已签收 99：已删除）
	
	
	
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
	public String getReport_sn() {
		return report_sn;
	}
	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}
	public Integer getReport_count() {
		return report_count;
	}
	public void setReport_count(Integer report_count) {
		this.report_count = report_count;
	}
	public String getMade_unit_name() {
		return made_unit_name;
	}
	public void setMade_unit_name(String made_unit_name) {
		this.made_unit_name = made_unit_name;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getConstruction_units_name() {
		return construction_units_name;
	}
	public void setConstruction_units_name(String construction_units_name) {
		this.construction_units_name = construction_units_name;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getData_status() {
		return data_status;
	}
	public void setData_status(String data_status) {
		this.data_status = data_status;
	}
	public Date getCommit_time() {
		return commit_time;
	}
	public void setCommit_time(Date commit_time) {
		this.commit_time = commit_time;
	}
	public Date getReceive_time() {
		return receive_time;
	}
	public void setReceive_time(Date receive_time) {
		this.receive_time = receive_time;
	}
	public String getCommit_time_str() {
		return commit_time_str;
	}
	public void setCommit_time_str(String commit_time_str) {
		this.commit_time_str = commit_time_str;
	}
	public String getReceive_time_str() {
		return receive_time_str;
	}
	public void setReceive_time_str(String receive_time_str) {
		this.receive_time_str = receive_time_str;
	}
	public String getInfo_id() {
		return info_id;
	}
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getReport_id() {
		return report_id;
	}
	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
	public Double getAdvance_fees() {
		return advance_fees;
	}
	public void setAdvance_fees(Double advance_fees) {
		this.advance_fees = advance_fees;
	}
	
}