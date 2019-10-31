package com.lsts.humanresources.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_RL_WORK_EXPERIENCE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkExperience implements BaseEntity{

    private String id;//id

    private java.util.Date workStartDate;//工作开始日期

    private java.util.Date workStopDate;//工作停止日期

    private String workUnit;//工作单位

    private String workPosition;//工作职位

    private String workOn;//从事工作

    private String fkRlEmplpyeeId;
    
    
    
    
    @Column(name="FK_RL_EMPLPYEE_ID")
	public String getFkRlEmplpyeeId() {
		return fkRlEmplpyeeId;
	}
	public void setFkRlEmplpyeeId(String fkRlEmplpyeeId) {
		this.fkRlEmplpyeeId = fkRlEmplpyeeId;
	}
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    public void setWorkStartDate(java.util.Date value){
        this.workStartDate = value;
    }
    public void setWorkStopDate(java.util.Date value){
        this.workStopDate = value;
    }
    public void setWorkUnit(String value){
        this.workUnit = value;
    }
    public void setWorkPosition(String value){
        this.workPosition = value;
    }
    public void setWorkOn(String value){
        this.workOn = value;
    }

    @Column(name ="WORK_START_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getWorkStartDate(){
        return this.workStartDate;
    }
    @Column(name ="WORK_STOP_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getWorkStopDate(){
        return this.workStopDate;
    }
    @Column(name ="WORK_UNIT",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getWorkUnit(){
        return this.workUnit;
    }
    @Column(name ="WORK_POSITION",unique=false,nullable=true,insertable=true,updatable=true,length=50)
    public String getWorkPosition(){
        return this.workPosition;
    }
    @Column(name ="WORK_ON",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getWorkOn(){
        return this.workOn;
    }



} 
