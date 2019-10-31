package com.khnt.rtbox.config.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * <p>
 * 
 * </p>
 * 
 * @ClassName PersonDirVersion
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2016-06-15 10:14:23
 */
@Entity
@Table(name = "RT_PERSON_DIR_VERSION")
public class RtPersonDirVersion implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;//
	private RtPersonDir rtPersonDir;// 个人报表目录ID
	private String fkBusinessId;// 业务数据ID
	private String rtDirJson;// 目录数据
	private String rtCode;// 报表代码
	private String fkCreateUserId;// 创建人
	private Date createTime;// 创建时间
	private Integer version;// 当前版本号

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FK_PERSON_DIR_ID")
	public RtPersonDir getRtPersonDir() {
		return rtPersonDir;
	}

	public void setRtPersonDir(RtPersonDir rtPersonDir) {
		this.rtPersonDir = rtPersonDir;
	}

	@Column(name = "FK_BUSINESS_ID")
	public String getFkBusinessId() {
		return fkBusinessId;
	}

	public void setFkBusinessId(String fkBusinessId) {
		this.fkBusinessId = fkBusinessId;
	}

	@Column(name = "RT_DIR_JSON")
	public String getRtDirJson() {
		return rtDirJson;
	}

	public void setRtDirJson(String rtDirJson) {
		this.rtDirJson = rtDirJson;
	}

	@Column(name = "RT_CODE")
	public String getRtCode() {
		return rtCode;
	}

	public void setRtCode(String rtCode) {
		this.rtCode = rtCode;
	}

	@Column(name = "FK_CREATE_USER_ID")
	public String getFkCreateUserId() {
		return fkCreateUserId;
	}

	public void setFkCreateUserId(String fkCreateUserId) {
		this.fkCreateUserId = fkCreateUserId;
	}

	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "RT_PERSON_DIR_VERSION:ID=" + id;

	}
}
