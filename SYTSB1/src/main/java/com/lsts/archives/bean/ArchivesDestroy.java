package com.lsts.archives.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_ARCHIVES_DESTROY")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArchivesDestroy implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String identifier;//编号

    private String applyUnit;//申请部门

    private String applyUnitId;//申请部门id

    private String applyOpinion;//理由、意见

    private String proposer;//申请人

    private String proposerId;//申请人id

    private java.util.Date applyTime;//申请时间

    private String registrant;//登记人

    private java.util.Date registrantTime;//登记时间

    private String registrantId;//登记人id

    private String status;//状态

    private String archivesNumber;//档案编号

    private String archivesName;//档案名称
    
    private String reportNumber;//报告号
    private String reportNumberId;//报告号
    private String shares;//共多少份报告

    private String sqbm;//申请部门意见
    private java.util.Date sqbmTime;//申请部门时间
    private String kgb;//科管部意见
    private java.util.Date kgbTime;//科管部时间
    private String zgb;//质管部意见
    private java.util.Date zgbTime;//质管部时间
    private String fgyld;//分管院领导意见
    private java.util.Date fgyldTime;//分管院领导时间
    private String jsfzr;//技术负责人意见
    private java.util.Date jsfzrTime;//技术负责人时间
    private String yz;//院长意见
    private java.util.Date yzTime;//院长时间
    

    public void setShares(String shares) {
		this.shares = shares;
	}
    public void setReportNumberId(String reportNumberId) {
		this.reportNumberId = reportNumberId;
	}
    public void setReportNumber(String value){
        this.reportNumber = value;
    }
	public void setSqbm(String sqbm) {
		this.sqbm = sqbm;
	}
	public void setSqbmTime(java.util.Date sqbmTime) {
		this.sqbmTime = sqbmTime;
	}
	public void setKgb(String kgb) {
		this.kgb = kgb;
	}
	public void setKgbTime(java.util.Date kgbTime) {
		this.kgbTime = kgbTime;
	}
	public void setZgb(String zgb) {
		this.zgb = zgb;
	}
	public void setZgbTime(java.util.Date zgbTime) {
		this.zgbTime = zgbTime;
	}
	public void setFgyld(String fgyld) {
		this.fgyld = fgyld;
	}
	public void setFgyldTime(java.util.Date fgyldTime) {
		this.fgyldTime = fgyldTime;
	}
	public void setJsfzr(String jsfzr) {
		this.jsfzr = jsfzr;
	}
	public void setJsfzrTime(java.util.Date jsfzrTime) {
		this.jsfzrTime = jsfzrTime;
	}
	public void setYz(String yz) {
		this.yz = yz;
	}
	public void setYzTime(java.util.Date yzTime) {
		this.yzTime = yzTime;
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
    public void setApplyUnitId(String value){
        this.applyUnitId = value;
    }
    public void setApplyOpinion(String value){
        this.applyOpinion = value;
    }
    public void setProposer(String value){
        this.proposer = value;
    }
    public void setProposerId(String value){
        this.proposerId = value;
    }
    public void setApplyTime(java.util.Date value){
        this.applyTime = value;
    }
    public void setRegistrant(String value){
        this.registrant = value;
    }
    public void setRegistrantTime(java.util.Date value){
        this.registrantTime = value;
    }
    public void setRegistrantId(String value){
        this.registrantId = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setArchivesNumber(String value){
        this.archivesNumber = value;
    }
    public void setArchivesName(String value){
        this.archivesName = value;
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
    @Column(name ="APPLY_UNIT",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getApplyUnit(){
        return this.applyUnit;
    }
    @Column(name ="APPLY_UNIT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getApplyUnitId(){
        return this.applyUnitId;
    }
    @Column(name ="APPLY_OPINION",unique=false,nullable=true,insertable=true,updatable=true,length=4000)
    public String getApplyOpinion(){
        return this.applyOpinion;
    }
    @Column(name ="PROPOSER",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getProposer(){
        return this.proposer;
    }
    @Column(name ="PROPOSER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getProposerId(){
        return this.proposerId;
    }
    @Column(name ="APPLY_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getApplyTime(){
        return this.applyTime;
    }
    @Column(name ="REGISTRANT",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getRegistrant(){
        return this.registrant;
    }
    @Column(name ="REGISTRANT_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getRegistrantTime(){
        return this.registrantTime;
    }
    @Column(name ="REGISTRANT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getRegistrantId(){
        return this.registrantId;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="ARCHIVES_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getArchivesNumber(){
        return this.archivesNumber;
    }
    @Column(name ="ARCHIVES_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getArchivesName(){
        return this.archivesName;
    }
    @Column(name ="REPORT_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=4000)
    public String getReportNumber(){
        return this.reportNumber;
    }
    @Column(name ="REPORT_NUMBER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=4000)
    public String getReportNumberId() {
		return reportNumberId;
	}
    @Column(name ="SHARES",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getShares() {
		return shares;
	}
    
    @Column(name ="SQBM",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getSqbm() {
		return sqbm;
	}
    @Column(name ="SQBM_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getSqbmTime() {
    	return sqbmTime;
    }
    @Column(name ="KGB",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getKgb() {
    	return kgb;
    }
    @Column(name ="KGB_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getKgbTime() {
    	return kgbTime;
    }
    @Column(name ="ZGB",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getZgb() {
    	return zgb;
    }
    @Column(name ="ZGB_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getZgbTime() {
    	return zgbTime;
    }
    @Column(name ="FGYLD",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getFgyld() {
    	return fgyld;
    }
    @Column(name ="FGYLD_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getFgyldTime() {
    	return fgyldTime;
    }
    @Column(name ="JSFZR",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getJsfzr() {
		return jsfzr;
	}
    @Column(name ="JSFZR_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getJsfzrTime() {
		return jsfzrTime;
	}
    @Column(name ="YZ",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
	public String getYz() {
		return yz;
	}
    @Column(name ="YZ_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getYzTime() {
		return yzTime;
	}

} 
