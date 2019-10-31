package com.lsts.qualitymanage.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_QUALITY_ATTACHMENT")
public class QualityAttachment implements BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;

	private String fileName;// 文件名

	private Long fileSize;// 文件大小，用字节表示

	private String fileType;// 文件类型

	private String businessId;// 业务ID

	private Date uploadTime;// 上传时间

	private String uploader;// 上传人编码

	private String uploaderName;// 上传人姓名

	private String saveType;// 附件存储类别: disk:文件系统,db:数据库

	private String filePath;// 保存在硬盘上的路径

	private String workItem;// 附件自定义标识，当一个业务有多个子业务附件时，用此字段来区分每个附件的具体业务

	private byte[] fileBody;

	private String busUniqueName;// 业务指定唯一附件标识
	
	private String jy_pic_category;	// 当文件类型为图片时，检验图片类别（现场图片：nomal_pic 不合格图片：bhg_pic 终止检验图片：zzjy_pic）

	private String status;//文件状态
	/**
	 * 默认构造方法
	 */
	public QualityAttachment() {
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

	@Column(name = "FILE_NAME")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "FILE_PATH")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "FILE_SIZE")
	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	@Column(name = "FILE_TYPE")
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Column(name = "BUSINESS_ID")
	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	@Column(name = "UPLOADER")
	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	@Column(name = "UPLOADER_NAME")
	public String getUploaderName() {
		return uploaderName;
	}

	public void setUploaderName(String uploaderName) {
		this.uploaderName = uploaderName;
	}

	@Column(name = "WORK_ITEM")
	public String getWorkItem() {
		return workItem;
	}

	public void setWorkItem(String workItem) {
		this.workItem = workItem;
	}

	@Column(name = "SAVE_TYPE")
	public String getSaveType() {
		return saveType;
	}

	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}

	@Column(name = "UPLOAD_TIME")
	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Transient
	public byte[] getFileBody() {
		return fileBody;
	}

	public void setFileBody(byte[] fileBody) {
		this.fileBody = fileBody;
	}

	@Column(name = "BUS_UNIQUE_NAME")
	public String getBusUniqueName() {
		return busUniqueName;
	}

	public void setBusUniqueName(String busUniqueName) {
		this.busUniqueName = busUniqueName;
	}
	
	@Column(name = "JY_PIC_CATEGORY")
	public String getJy_pic_category() {
		return jy_pic_category;
	}

	public void setJy_pic_category(String jy_pic_category) {
		this.jy_pic_category = jy_pic_category;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name ="STATUS")
    public String getStatus() {
  		return status;
  	}
	public String toString() {
		return "附件[ID:" + this.getId() + ",NAME:" + this.fileName + "]";
	}
}