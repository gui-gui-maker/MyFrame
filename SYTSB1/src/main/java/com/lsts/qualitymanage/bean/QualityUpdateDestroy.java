package com.lsts.qualitymanage.bean;

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
@Table(name = "TJY2_QUALITY_UPDATE_DESTROY")
public class QualityUpdateDestroy implements BaseEntity{
	private static final long serialVersionUID = 1L;

	private String id;//id

    private String fileName;//文件名称

    private String fileId;//文件编号

    private String registrant;//登记人

    private java.util.Date registrantTime;//登记时间

    private String destroyReason;//销毁原因

    private String status;//状态
    
    private String qualityXybzFileId;//质量体系文件的id
   
    private String registrantId;//登记人
    
    private String fgyld;//登记人
    private java.util.Date fgyldTime;//登记时间
    private String fgyldyj;//销毁原因
    private String dwfzr;//登记人
    private java.util.Date dwfzrTime;//登记时间
    private String dwfzryj;//销毁原因
    
    
	public void setFgyld(String fgyld) {
		this.fgyld = fgyld;
	}
	public void setFgyldTime(java.util.Date fgyldTime) {
		this.fgyldTime = fgyldTime;
	}
	public void setFgyldyj(String fgyldyj) {
		this.fgyldyj = fgyldyj;
	}
	public void setDwfzr(String dwfzr) {
		this.dwfzr = dwfzr;
	}
	public void setDwfzrTime(java.util.Date dwfzrTime) {
		this.dwfzrTime = dwfzrTime;
	}
	public void setDwfzryj(String dwfzryj) {
		this.dwfzryj = dwfzryj;
	}
	public void setRegistrantId(String registrantId) {
		this.registrantId = registrantId;
	}
    public void setQualityXybzFileId(String value){
        this.qualityXybzFileId = value;
    }
    public void setId(String value){
        this.id = value;
    }
    public void setFileName(String value){
        this.fileName = value;
    }
    public void setFileId(String value){
        this.fileId = value;
    }
    public void setRegistrant(String value){
        this.registrant = value;
    }
    public void setRegistrantTime(java.util.Date value){
        this.registrantTime = value;
    }
    public void setDestroyReason(String value){
        this.destroyReason = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="FILE_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getFileName(){
        return this.fileName;
    }
    @Column(name ="FILE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFileId(){
        return this.fileId;
    }
    @Column(name ="REGISTRANT",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getRegistrant(){
        return this.registrant;
    }
    @Column(name ="REGISTRANT_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getRegistrantTime(){
        return this.registrantTime;
    }
    @Column(name ="DESTROY_REASON",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
    public String getDestroyReason(){
        return this.destroyReason;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=100)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="QUALITY_XYBZ_FILE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getQualityXybzFileId(){
        return this.qualityXybzFileId;
    }
    @Column(name ="REGISTRANT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getRegistrantId() {
		return registrantId;
	}
    @Column(name ="FGYLD",unique=false,nullable=true,insertable=true,updatable=true,length=200)
	public String getFgyld() {
		return fgyld;
	}
    @Column(name ="FGYLD_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getFgyldTime() {
		return fgyldTime;
	}
    @Column(name ="FGYLDYJ",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
	public String getFgyldyj() {
		return fgyldyj;
	}
    @Column(name ="DWFZR",unique=false,nullable=true,insertable=true,updatable=true,length=200)
	public String getDwfzr() {
		return dwfzr;
	}
    @Column(name ="DWFZR_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public java.util.Date getDwfzrTime() {
		return dwfzrTime;
	}
    @Column(name ="DWFZRYJ",unique=false,nullable=true,insertable=true,updatable=true,length=2000)
	public String getDwfzryj() {
		return dwfzryj;
	}
} 
