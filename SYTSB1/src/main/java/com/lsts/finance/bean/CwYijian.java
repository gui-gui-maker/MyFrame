package com.lsts.finance.bean;

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
@Table(name = "TJY2_CW_YIJIAN")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CwYijian implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//意见id

    private String fileId;//审核表id

    private String auditOpinion;//审核意见

    private String auditMan;//审核人

    private java.util.Date auditTime;//审核时间

    private String auditManId;//审核人id

    private String auditResult;//审核结果

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
    public void setAuditResult(String value){
        this.auditResult = value;
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
    @Column(name ="AUDIT_RESULT",unique=false,nullable=true,insertable=true,updatable=true,length=500)
    public String getAuditResult(){
        return this.auditResult;
    }
    
    
} 
