package com.lsts.qualitymanage.bean;

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
@Table(name = "TJY2_QUALITY_FILES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class QualityFiles implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String fileId;//文件id

    private String fileName;//文件名字

    private String tjyFile;//（判断是那个体系的文件）

    private String department;//部门

    private String authority;//权限
    
    private String status;//此文件的状态
    private java.util.Date implementDate;//实施日期

    
    
  
	
	public void setImplementDate(java.util.Date implementDate) {
		this.implementDate = implementDate;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setId(String value){
        this.id = value;
    }
    public void setFileId(String value){
        this.fileId = value;
    }
    public void setFileName(String value){
        this.fileName = value;
    }
    public void setTjyFile(String value){
        this.tjyFile = value;
    }
    public void setDepartment(String value){
        this.department = value;
    }
    public void setAuthority(String value){
        this.authority = value;
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
    @Column(name ="FILE_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getFileName(){
        return this.fileName;
    }
    @Column(name ="TJY_FILE",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getTjyFile(){
        return this.tjyFile;
    }
    @Column(name ="DEPARTMENT",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getDepartment(){
        return this.department;
    }
    @Column(name ="AUTHORITY",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getAuthority(){
        return this.authority;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getStatus() {
  		return status;
  	}
    @Column(name ="IMPLEMENT_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getImplementDate() {
		return implementDate;
	}
} 
