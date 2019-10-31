package com.lsts.inspection.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name="TZSB_INSPECTION_VERSION")
public class InspectionVersion implements BaseEntity {

	private String id;	// 流水号
	private String businessId;	// 业务编号
	private String opUserId;	// 操作用户编号
	private String opUserName;	// 操作用户姓名
	private Date   opTime;	// 操作时间
	private String opRemark;	// 操作描述
	private String oldRecordVersion;//原始记录版本信息
	private String newRecordVersion;//
	
	private String oldReportVersion;//报告版本信息
	private String newReportVersion;//
	
	private String recordAtt;//
	private String reportAtt;//
	
	private Integer version;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4917548714806160806L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	@Column(name="BUSINESS_ID")
	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	
	@Column(name="OP_USER_ID")
	public String getOpUserId() {
		return opUserId;
	}

	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}

	@Column(name="OP_USER_NAME")
	public String getOpUserName() {
		return opUserName;
	}

	public void setOpUserName(String opUserName) {
		this.opUserName = opUserName;
	}

	@Column(name="OP_TIME")
	public Date getOpTime() {
		return opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	@Column(name="OP_REMARK")
	public String getOpRemark() {
		return opRemark;
	}

	public void setOpRemark(String opRemark) {
		this.opRemark = opRemark;
	}

	@Column(name="OLD_RECORD_VERSION")
	public String getOldRecordVersion() {
		return oldRecordVersion;
	}

	public void setOldRecordVersion(String oldRecordVersion) {
		this.oldRecordVersion = oldRecordVersion;
	}

	@Column(name="NEW_RECORD_VERSION")
	public String getNewRecordVersion() {
		return newRecordVersion;
	}

	public void setNewRecordVersion(String newRecordVersion) {
		this.newRecordVersion = newRecordVersion;
	}

	@Column(name="OLD_REPORT_VERSION")
	public String getOldReportVersion() {
		return oldReportVersion;
	}

	public void setOldReportVersion(String oldReportVersion) {
		this.oldReportVersion = oldReportVersion;
	}

	@Column(name="NEW_REPORT_VERSION")
	public String getNewReportVersion() {
		return newReportVersion;
	}

	public void setNewReportVersion(String newReportVersion) {
		this.newReportVersion = newReportVersion;
	}

	@Column(name="RECORD_ATT")
	public String getRecordAtt() {
		return recordAtt;
	}

	public void setRecordAtt(String recordAtt) {
		this.recordAtt = recordAtt;
	}

	@Column(name="REPORT_ATT")
	public String getReportAtt() {
		return reportAtt;
	}

	public void setReportAtt(String reportAtt) {
		this.reportAtt = reportAtt;
	}

	@Column(name="VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	
	
}
