package com.khnt.rtdroc.files.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * <p>
 * 
 * </p>
 * 
 * @ClassName RtdFile
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2018-09-01 09:51:04
 */
@Entity
@Table(name = "RTD_FILE")
public class RtdFile implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;//
	private String pid;// 父ID
	private String fileType;// 文件类型(file:文件,folder:文件夹)
	private String fileName;// 文件名
	private String fkAttIdDraw;// 绘制ID
	private String fkAttIdImg;// 图片ID
	private String remark;// 备注

	private String handleUserName;// 操作人
	private String fkUserIdHandle;// 操作人ID
	private String handleOrgName;// 操作部门
	private String fkOrgIdHandle;// 操作部门ID
	private Date handleTime;// 操作时间
	private String isDel;// 是否删除(是：1、否：0)
	private String fkUserIdLast;// 最后修改人
	private Date lastHandleTime;// 最后修改时间(可计算耗时)
	private String status;// 状态（是否共、分享等）

	// 页面传值
	private String drawData;// 绘制数据
	private String imgData;// 图片数据

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "PID")
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Column(name = "FILE_TYPE")
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Column(name = "FILE_NAME")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "FK_ATT_ID_DRAW")
	public String getFkAttIdDraw() {
		return fkAttIdDraw;
	}

	public void setFkAttIdDraw(String fkAttIdDraw) {
		this.fkAttIdDraw = fkAttIdDraw;
	}

	@Column(name = "FK_ATT_ID_IMG")
	public String getFkAttIdImg() {
		return fkAttIdImg;
	}

	public void setFkAttIdImg(String fkAttIdImg) {
		this.fkAttIdImg = fkAttIdImg;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "HANDLE_USER_NAME")
	public String getHandleUserName() {
		return handleUserName;
	}

	public void setHandleUserName(String handleUserName) {
		this.handleUserName = handleUserName;
	}

	@Column(name = "FK_USER_ID_HANDLE")
	public String getFkUserIdHandle() {
		return fkUserIdHandle;
	}

	public void setFkUserIdHandle(String fkUserIdHandle) {
		this.fkUserIdHandle = fkUserIdHandle;
	}

	@Column(name = "HANDLE_ORG_NAME")
	public String getHandleOrgName() {
		return handleOrgName;
	}

	public void setHandleOrgName(String handleOrgName) {
		this.handleOrgName = handleOrgName;
	}

	@Column(name = "FK_ORG_ID_HANDLE")
	public String getFkOrgIdHandle() {
		return fkOrgIdHandle;
	}

	public void setFkOrgIdHandle(String fkOrgIdHandle) {
		this.fkOrgIdHandle = fkOrgIdHandle;
	}

	@Column(name = "HANDLE_TIME")
	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	@Column(name = "IS_DEL")
	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	@Column(name = "LAST_HANDLE_TIME")
	public Date getLastHandleTime() {
		return lastHandleTime;
	}

	public void setLastHandleTime(Date lastHandleTime) {
		this.lastHandleTime = lastHandleTime;
	}

	@Column(name = "FK_USER_ID_LAST")
	public String getFkUserIdLast() {
		return fkUserIdLast;
	}

	public void setFkUserIdLast(String fkUserIdLast) {
		this.fkUserIdLast = fkUserIdLast;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Transient
	public String getDrawData() {
		return drawData;
	}

	public void setDrawData(String drawData) {
		this.drawData = drawData;
	}

	@Transient
	public String getImgData() {
		return imgData;
	}

	public void setImgData(String imgData) {
		this.imgData = imgData;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "RTD_COMPONENTS:ID=" + id;

	}
}
