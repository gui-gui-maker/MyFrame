package com.lsts.archives.bean;

import java.util.Date;
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
@Table(name = "TJY2_ARCHIVES_UPLOAD")
public class Uploads implements BaseEntity{

	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}

    private String uploadType;//上传类型

    private String uploadName;//文件名称

    private String uploadmanName;//上传人名称

    private String fileId;//报告id

    private Date uploadTime;//上传时间
    
    private String uploadSize;//上传大小
    private String uploadPath;//文件路径
    private byte uploadDoc[];//文件内容
    
    private String uploadId;//文件id（attachment_id）
    
    private String parentId;//父节点
    private String type;//状态
    
    @Column(name="UPLOAD_ID")
	public String getUploadId() {
		return uploadId;
	}

	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	
	public void setUploadSize(String uploadSize) {
		this.uploadSize = uploadSize;
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
    public void setUploadType(String value){
        this.uploadType = value;
    }
    public void setUploadName(String value){
        this.uploadName = value;
    }
    public void setUploadmanName(String value){
        this.uploadmanName = value;
    }
    public void setFileId(String value){
        this.fileId = value;
    }

    @Column(name ="UPLOAD_TYPE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getUploadType(){
        return this.uploadType;
    }
    @Column(name ="UPLOAD_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getUploadName(){
        return this.uploadName;
    }
    @Column(name ="UPLOADMAN_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getUploadmanName(){
        return this.uploadmanName;
    }
    @Column(name ="FILE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFileId(){
        return this.fileId;
    }
    @Column(name ="UPLOAD_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public Date getUploadTime() {
		return uploadTime;
	}
    @Column(name ="UPLOAD_SIZE",unique=false,nullable=true,insertable=true,updatable=true,length=200)
    public String getUploadSize() {
		return uploadSize;
	}
    @Column(name="UPLOAD_PATH")
	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	@Column(name ="UPLOAD_DOC")
	public byte[] getUploadDoc() {
		return uploadDoc;
	}

	public void setUploadDoc(byte[] uploadDoc) {
		this.uploadDoc = uploadDoc;
	}
	@Column(name ="PARENT_ID")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Column(name ="TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


    
} 
