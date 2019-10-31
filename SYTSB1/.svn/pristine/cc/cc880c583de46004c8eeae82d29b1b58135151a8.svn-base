package com.lsts.humanresources.bean;

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
@Table(name = "TJY2_RL_UPLOAD")
public class Upload implements BaseEntity{

    private String id;//${columnb.remarks}

    private String uploadType;//上传类型

    private String uploadName;//文件名称

    private String uploadPath;//存储名称

    private String fkEmployeeId;//基础信息id
    
    private String uploadId;//上传文件id

    @Id
   	@GeneratedValue(generator = "system-uuid")
   	@GenericGenerator(name = "system-uuid", strategy = "uuid")
   	public String getId() {
   		return id;
   	}
   	public void setId(String id) {
   		this.id = id;
   	}
    public void setUploadType(String value){
        this.uploadType = value;
    }
    public void setUploadName(String value){
        this.uploadName = value;
    }
    public void setUploadPath(String value){
        this.uploadPath = value;
    }
    public void setFkEmployeeId(String value){
        this.fkEmployeeId = value;
    }

    @Column(name ="UPLOAD_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getUploadType(){
        return this.uploadType;
    }
    @Column(name ="UPLOAD_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getUploadName(){
        return this.uploadName;
    }
    @Column(name ="UPLOAD_PATH",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getUploadPath(){
        return this.uploadPath;
    }
    @Column(name ="FK_EMPLOYEE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkEmployeeId(){
        return this.fkEmployeeId;
    }
    @Column(name ="UPLOAD_ID")
	public String getUploadId() {
		return uploadId;
	}
	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}


} 
