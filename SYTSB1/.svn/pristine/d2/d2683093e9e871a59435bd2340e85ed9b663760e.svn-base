package com.khnt.rtbox.config.bean;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;


@Entity
@Table(name = "RT_PERSON_DIR")
@JsonIgnoreProperties(value = { "versions" })
public class RtPersonDir implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;
	//业务数据ID
	private String fkBusinessId;
	//目录数据
	private String rtDirJson;
	//报表代码
	private String rtCode;
	//创建人
	private String fkCreateUserId;
	//创建时间
	private Date createTime;
	//版本号
	private Integer version;

	private Set<RtPersonDirVersion> versions;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rtPersonDir")
	public Set<RtPersonDirVersion> getVersions() {
		return versions;
	}

	public void setVersions(Set<RtPersonDirVersion> versions) {
		this.versions = versions;
	}


	public String toString() {
		return "RT_PERSON_DIR:ID=" + id;

	}
}
