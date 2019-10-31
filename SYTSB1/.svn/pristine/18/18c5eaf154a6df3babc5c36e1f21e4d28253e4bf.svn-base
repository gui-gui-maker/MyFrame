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
@Table(name = "TJY2_ARCHIVES_YIJINA")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArchivesYijina implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//意见id

    private String fileId;//修改表id

    private String auditOpinion;//审核意见

    private String auditMan;//审核人

    private java.util.Date auditTime;//审核时间

    private String auditManId;//审核人id

    private String auditStep;//审核步骤

    private String auditResult;//审核结果
    
    private String ass;//签字id

    private String seal;//盖章id
    
    private String returnName;//退回环节名称

    private String status;//处理后状态

    private String businessName;//业务名称

    public void setAss(String value){
        this.ass = value;
    }
    public void setSeal(String value){
        this.seal = value;
    }
	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}
	public void setId(String value){
        this.id = value;
    }
    public void setFileId(String value){
        this.fileId = value;
    }
    public void setAuditOpinion(String value){
        this.auditOpinion = value;
    }
    public void setAuditMan(String value){
        this.auditMan = value;
    }
    public void setAuditTime(java.util.Date value){
        this.auditTime = value;
    }
    public void setAuditManId(String value){
        this.auditManId = value;
    }
    public void setAuditStep(String value){
        this.auditStep = value;
    }
    public void setReturnName(String value){
        this.returnName = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setBusinessName(String value){
        this.businessName = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="FILE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFileId(){
        return this.fileId;
    }
    @Column(name ="AUDIT_OPINION",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getAuditOpinion(){
        return this.auditOpinion;
    }
    @Column(name ="AUDIT_MAN",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getAuditMan(){
        return this.auditMan;
    }
    @Column(name ="AUDIT_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getAuditTime(){
        return this.auditTime;
    }
    @Column(name ="AUDIT_MAN_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getAuditManId(){
        return this.auditManId;
    }
    @Column(name ="AUDIT_STEP",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getAuditStep(){
        return this.auditStep;
    }
    @Column(name ="AUDIT_RESULT",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getAuditResult() {
		return auditResult;
	}
    @Column(name ="ASS",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getAss(){
        return this.ass;
    }
    @Column(name ="SEAL",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getSeal(){
        return this.seal;
    }
    @Column(name ="RETURN_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getReturnName(){
        return this.returnName;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=10)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="BUSINESS_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getBusinessName(){
        return this.businessName;
    }
} 
