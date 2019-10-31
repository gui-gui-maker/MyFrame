package com.lsts.archives.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_ARCHIVES_PRINT_DYRZ")
public class ArchivesPrintDyrz implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String documentId;//报告编号

    private String identifier;//申请表id

    private String printMan;//打印人

    private String printManId;//打印人id

    private java.util.Date printTime;//打印时间

    private String applyReason;//申请原因
    
    private String applyUnit;//申请部门

    private String applyUnitId;//申请部门id

    private String proposer;//申请人

    private String proposerId;//申请人id

    private java.util.Date applyTime;//申请时间
    
    private String applicationId;//申请表id

    public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
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
    public void setId(String value){
        this.id = value;
    }
    public void setDocumentId(String value){
        this.documentId = value;
    }
    public void setIdentifier(String value){
        this.identifier = value;
    }
    public void setPrintMan(String value){
        this.printMan = value;
    }
    public void setPrintManId(String value){
        this.printManId = value;
    }
    public void setPrintTime(java.util.Date value){
        this.printTime = value;
    }
    public void setApplyReason(String value){
        this.applyReason = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="DOCUMENT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getDocumentId(){
        return this.documentId;
    }
    @Column(name ="IDENTIFIER",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getIdentifier(){
        return this.identifier;
    }
    @Column(name ="PRINT_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getPrintMan(){
        return this.printMan;
    }
    @Column(name ="PRINT_MAN_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getPrintManId(){
        return this.printManId;
    }
    @Column(name ="PRINT_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getPrintTime(){
        return this.printTime;
    }
    @Column(name ="APPLY_REASON",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getApplyReason(){
        return this.applyReason;
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
    @Column(name ="APPLICATION_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getApplicationId() {
		return applicationId;
	}
} 
