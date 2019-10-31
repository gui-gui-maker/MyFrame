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
@Table(name = "TJY2_ARCHIVES_RZ")
public class ArchivesRz implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String identifier;//编号

    private String applyUnit;//申请部门

    private String archivesType;//档案类型

    private String proposer;//申请人

    private String proposerId;//申请人id

    private java.util.Date applyTime;//申请时间

    private String reportNumber;//报告号

    private String applyUnitId;//申请部门id

    private java.util.Date recordTime;//记录时间
    
    private String applicationId;//申请表id
    private String procedures;//当前操作环节
   
	public void setProcedures(String procedures) {
		this.procedures = procedures;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public void setRecordTime(java.util.Date recordTime) {
		this.recordTime = recordTime;
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
    public void setArchivesType(String value){
        this.archivesType = value;
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
    public void setReportNumber(String value){
        this.reportNumber = value;
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
    @Column(name ="ARCHIVES_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getArchivesType(){
        return this.archivesType;
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
    @Column(name ="REPORT_NUMBER",unique=false,nullable=true,insertable=true,updatable=true,length=4000)
    public String getReportNumber(){
        return this.reportNumber;
    }
    @Column(name ="APPLY_UNIT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getApplyUnitId() {
		return applyUnitId;
	}
    @Column(name ="RECORD_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getRecordTime() {
		return recordTime;
	}
    @Column(name ="APPLICATION_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getApplicationId() {
		return applicationId;
	}
    @Column(name ="PROCEDURES",unique=false,nullable=true,insertable=true,updatable=true,length=1000)
    public String getProcedures() {
		return procedures;
	}
} 
