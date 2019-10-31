package com.lsts.archives.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_ARCHIVES_BORROW")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArchivesBorrow implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//申请id

    private String identifier;//编号

    private String applyUnit;//申请部门

    private String applyType;//申请类型

    private String proposer;//申请人

    private java.util.Date applyTime;//申请时间

    private String proposerId;//申请人id

    private Date restoreTime;//计划返还时间

    private String reportNumber;//报告号
    
    private String reportNumberId;//报告id

    private String applyReason;//申请理由

    private String status;//状态

    private String registrant;//登记人
    
    private String fatalism;//天数

    private java.util.Date registrantTime;//登记时间
    
    private String applyUnitId;//申请部门

    private String reportBiaoId;//报告表id
   
    private String bmfzryj;//部门负责人意见
    
    private String bmfzr;//部门负责人
    
    private java.util.Date bmfzrTime;//部门负责人时间

    private String yffwbyj;//业务服务部意见
    
    private String yffwb;//业务服务部
    
    private java.util.Date yffwbTime;//业务服务部意见
    
    private String jfbgr;//交付报告人
    
    private String jfbgrId;//交付报告人id
    
	private java.util.Date jfbgsj;//交付报告时间
	
	private String fhbgr;//返还报告人
	
	private String fhbgrId;//返还报告人id
	
    private java.util.Date fhbgsj;//返还报告时间

    private String fkMsg;//验证码share
    
    private String shares;//共多少份报告
    
    private String reportReceiptor;//领取报告人
    
    private String reportReceiptorId;//领取报告人id
    
	private java.util.Date reportReceiptDate;//领取报告时间
	
	private String isSg;//是否是借阅手工报告（0：否，1：是）
    
    private List<ArchivesBorrowSub> archivesBorrowSubs;
    
	public void setBmfzr(String bmfzr) {
		this.bmfzr = bmfzr;
	}
	public void setBmfzrTime(java.util.Date bmfzrTime) {
		this.bmfzrTime = bmfzrTime;
	}
	public void setYffwb(String yffwb) {
		this.yffwb = yffwb;
	}
	public void setYffwbTime(java.util.Date yffwbTime) {
		this.yffwbTime = yffwbTime;
	}
	public void setJfbgr(String jfbgr) {
		this.jfbgr = jfbgr;
	}
	public void setJfbgrId(String jfbgrId) {
		this.jfbgrId = jfbgrId;
	}
	public void setFhbgr(String fhbgr) {
		this.fhbgr = fhbgr;
	}
	public void setFhbgrId(String fhbgrId) {
		this.fhbgrId = fhbgrId;
	}
	public void setShares(String shares) {
		this.shares = shares;
	}
	public void setFkMsg(String value){
        this.fkMsg = value;
    }
	public void setReportNumberId(String reportNumberId) {
		this.reportNumberId = reportNumberId;
	}
	public void setJfbgsj(java.util.Date jfbgsj) {
		this.jfbgsj = jfbgsj;
	}
	public void setFhbgsj(java.util.Date fhbgsj) {
		this.fhbgsj = fhbgsj;
	}
	public void setBmfzryj(String bmfzryj) {
		this.bmfzryj = bmfzryj;
	}
	public void setYffwbyj(String yffwbyj) {
		this.yffwbyj = yffwbyj;
	}

	
	public void setReportBiaoId(String reportBiaoId) {
		this.reportBiaoId = reportBiaoId;
	}
	public void setFatalism(String fatalism) {
		this.fatalism = fatalism;
	}
	public void setApplyUnitId(String applyUnitId) {
		this.applyUnitId = applyUnitId;
	}
	public void setId(String value){
        this.id = value;
    }
    public void setIdentifier(String value){
        this.identifier = value;
    }
    public void setApplyUnit(String value){
        this.applyUnit = value;
    }
    public void setApplyType(String value){
        this.applyType = value;
    }
    public void setProposer(String value){
        this.proposer = value;
    }
    public void setApplyTime(java.util.Date value){
        this.applyTime = value;
    }
    public void setProposerId(String value){
        this.proposerId = value;
    }
    public void setRestoreTime(Date value){
        this.restoreTime = value;
    }
    public void setReportNumber(String value){
        this.reportNumber = value;
    }
    public void setApplyReason(String value){
        this.applyReason = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setRegistrant(String value){
        this.registrant = value;
    }
    public void setRegistrantTime(java.util.Date value){
        this.registrantTime = value;
    }
    public void setReportReceiptor(String value) {
		this.reportReceiptor = value;
	}
	public void setReportReceiptorId(String value) {
		this.reportReceiptorId = value;
	}
	public void setReportReceiptDate(java.util.Date value){
        this.reportReceiptDate = value;
    }
	public void setIsSg(String value){
        this.isSg = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="IDENTIFIER",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getIdentifier(){
        return this.identifier;
    }
    @Column(name ="APPLY_UNIT",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getApplyUnit(){
        return this.applyUnit;
    }
    @Column(name ="APPLY_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getApplyType(){
        return this.applyType;
    }
    @Column(name ="PROPOSER",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getProposer(){
        return this.proposer;
    }
    @Column(name ="APPLY_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getApplyTime(){
        return this.applyTime;
    }
    @Column(name ="PROPOSER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getProposerId(){
        return this.proposerId;
    }
    @Column(name ="RESTORE_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public Date getRestoreTime(){
        return this.restoreTime;
    }
    @Column(name ="REPORT_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=4000)
    public String getReportNumber(){
        return this.reportNumber;
    }
    @Column(name ="APPLY_REASON",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getApplyReason(){
        return this.applyReason;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="REGISTRANT",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getRegistrant(){
        return this.registrant;
    }
    @Column(name ="REGISTRANT_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getRegistrantTime(){
        return this.registrantTime;
    }
    @Column(name ="APPLY_UNIT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getApplyUnitId() {
		return applyUnitId;
	}
    @Column(name ="FATALISM",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getFatalism() {
		return fatalism;
	}
    @Column(name ="REPORT_BIAO_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getReportBiaoId() {
		return reportBiaoId;
	}
    @Column(name ="BMFZRYJ",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getBmfzryj() {
		return bmfzryj;
	}
    @Column(name ="YFFWBYJ",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getYffwbyj() {
    	return yffwbyj;
    }
    @Column(name ="JFBGSJ",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getJfbgsj() {
		return jfbgsj;
	}
    @Column(name ="FHBGSJ",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getFhbgsj() {
		return fhbgsj;
	}
    @Column(name ="REPORT_NUMBER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=4000)
    public String getReportNumberId() {
		return reportNumberId;
	}
    @Column(name ="FK_MSG",unique=false,nullable=true,insertable=true,updatable=true,length=20)
    public String getFkMsg(){
        return this.fkMsg;
    }
    @Column(name ="SHARES",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getShares() {
		return shares;
	}
    @Column(name ="JFBGR",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getJfbgr() {
		return jfbgr;
	}
    @Column(name ="JFBGR_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getJfbgrId() {
		return jfbgrId;
	}
    @Column(name ="FHBGR",unique=false,nullable=true,insertable=true,updatable=true,length=100)
	public String getFhbgr() {
		return fhbgr;
	}
    @Column(name ="FHBGR_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	public String getFhbgrId() {
		return fhbgrId;
	}
    @Column(name ="BMFZR",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getBmfzr() {
		return bmfzr;
	}
    @Column(name ="BMFZR_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getBmfzrTime() {
    	return bmfzrTime;
    }
    @Column(name ="YFFWB",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getYffwb() {
    	return yffwb;
    }
    @Column(name ="YFFWB_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getYffwbTime() {
    	return yffwbTime;
    }
    @Column(name ="REPORT_RECEIPTOR",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getReportReceiptor() {
		return reportReceiptor;
	}
    @Column(name ="REPORT_RECEIPTOR_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getReportReceiptorId() {
		return reportReceiptorId;
	}
    @Column(name ="REPORT_RECEIPT_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getReportReceiptDate() {
    	return reportReceiptDate;
    }
    @Column(name ="IS_SG",unique=false,nullable=true,insertable=true,updatable=true,length=2)
    public String getIsSg(){
        return this.isSg;
    }
    
    @Transient
	public List<ArchivesBorrowSub> getArchivesBorrowSubs() {
		return archivesBorrowSubs;
	}
	
	public void setArchivesBorrowSubs(List<ArchivesBorrowSub> value) {
		this.archivesBorrowSubs = value;
	}
} 
