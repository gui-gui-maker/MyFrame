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
@Table(name = "TJY2_ARCHIVES_PRINT")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArchivesPrint implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String identifier;//编号

    private String applyUnit;//申请部门

    private String applyUnitId;//申请部门id

    private String proposer;//申请人

    private String proposerId;//申请人id

    private java.util.Date applyTime;//申请时间

    private String status;//状态

    private String registrant;//登记人

    private java.util.Date registrantTime;//登记时间

    private String registrantId;//登记人id

    private String documentName;//文档名称

    private String documentId;//文档编号
    private String documentIdid;//文档编号id

    private String useUnitName;//使用单位名称
    
    private String applyReason;//申请原因
    
    private String sqbmfzr;//申请部门负责人意见
    private String fwbmfzr;//服务部门负责人意见
    private String fwbmjbr;//服务部门经办人
    private String sqbmfzrName;//申请部门负责人签字
    private java.util.Date sqbmfzrTime;//申请部门负责人时间
    private String fwbmfzrName;//服务部门负责人签字
    private java.util.Date fwbmfzrTime;//服务部门负责人时间
    private String fwbmjbrName;//服务部门经办人签字
    private java.util.Date fwbmjbrTime;//服务部门经办人时间
    private String sqbmfzrId;//申请部门负责人意见id
    private String fwbmfzrId;//服务部门负责人意见id
    private String fwbmjbrId;//服务部门经办人id

	
	
	public void setDocumentIdid(String documentIdid) {
		this.documentIdid = documentIdid;
	}
	public void setSqbmfzrId(String sqbmfzrId) {
		this.sqbmfzrId = sqbmfzrId;
	}
	public void setFwbmfzrId(String fwbmfzrId) {
		this.fwbmfzrId = fwbmfzrId;
	}
	public void setFwbmjbrId(String fwbmjbrId) {
		this.fwbmjbrId = fwbmjbrId;
	}
	public void setSqbmfzrName(String sqbmfzrName) {
		this.sqbmfzrName = sqbmfzrName;
	}
	public void setSqbmfzrTime(java.util.Date sqbmfzrTime) {
		this.sqbmfzrTime = sqbmfzrTime;
	}
	public void setFwbmfzrName(String fwbmfzrName) {
		this.fwbmfzrName = fwbmfzrName;
	}
	public void setFwbmfzrTime(java.util.Date fwbmfzrTime) {
		this.fwbmfzrTime = fwbmfzrTime;
	}
	public void setFwbmjbrName(String fwbmjbrName) {
		this.fwbmjbrName = fwbmjbrName;
	}
	public void setFwbmjbrTime(java.util.Date fwbmjbrTime) {
		this.fwbmjbrTime = fwbmjbrTime;
	}
	
	public void setSqbmfzr(String sqbmfzr) {
		this.sqbmfzr = sqbmfzr;
	}
	public void setFwbmfzr(String fwbmfzr) {
		this.fwbmfzr = fwbmfzr;
	}
	public void setFwbmjbr(String fwbmjbr) {
		this.fwbmjbr = fwbmjbr;
	}
	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
	public void setUseUnitName(String useUnitName) {
		this.useUnitName = useUnitName;
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
    public void setProposer(String value){
        this.proposer = value;
    }
    public void setProposerId(String value){
        this.proposerId = value;
    }
    public void setApplyTime(java.util.Date value){
        this.applyTime = value;
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
    public void setRegistrantId(String value){
        this.registrantId = value;
    }
    public void setDocumentName(String value){
        this.documentName = value;
    }
    public void setDocumentId(String value){
        this.documentId = value;
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
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=100)
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
    @Column(name ="REGISTRANT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getRegistrantId(){
        return this.registrantId;
    }
    @Column(name ="DOCUMENT_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getDocumentName(){
        return this.documentName;
    }
    @Column(name ="DOCUMENT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=4000)
    public String getDocumentId(){
        return this.documentId;
    }
    @Column(name ="USE_UNIT_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getUseUnitName() {
		return useUnitName;
	}
    @Column(name ="APPLY_REASON",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getApplyReason() {
		return applyReason;
	}
    @Column(name ="SQBMFZR",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getSqbmfzr() {
		return sqbmfzr;
	}
    @Column(name ="FWBMFZR",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
	public String getFwbmfzr() {
		return fwbmfzr;
	}
    @Column(name ="FWBMJBR",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
	public String getFwbmjbr() {
		return fwbmjbr;
	}
    @Column(name ="SQBMFZR_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getSqbmfzrName() {
		return sqbmfzrName;
	}
    @Column(name ="SQBMFZR_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getSqbmfzrTime() {
		return sqbmfzrTime;
	}
    @Column(name ="FWBMFZR_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
	public String getFwbmfzrName() {
		return fwbmfzrName;
	}
    @Column(name ="FWBMFZR_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getFwbmfzrTime() {
		return fwbmfzrTime;
	}
    @Column(name ="FWBMJBR_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
	public String getFwbmjbrName() {
		return fwbmjbrName;
	}
    @Column(name ="FWBMJBR_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getFwbmjbrTime() {
		return fwbmjbrTime;
	}
    @Column(name ="SQBMFZR_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getSqbmfzrId() {
		return sqbmfzrId;
	}
    @Column(name ="FWBMFZR_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	public String getFwbmfzrId() {
		return fwbmfzrId;
	}
    @Column(name ="FWBMJBR_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	public String getFwbmjbrId() {
		return fwbmjbrId;
	}
    @Column(name ="DOCUMENT_IDID",unique=false,nullable=true,insertable=true,updatable=true,length=4000)
	public String getDocumentIdid() {
		return documentIdid;
	}
} 
