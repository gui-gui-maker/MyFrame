package com.scts.car.bean;

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
@Table(name = "TJY2_CAR_CM_DETAIL")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarCmDetail implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;// 主键id

	private String fkCarId;// 车辆id

	private String cmKm;// 保养公里数

	private java.util.Date cmDate;// 保养日期

	private String nextCmKm;// 下次保养公里数

	private java.util.Date nextCmDate;// 下次保养日期

	private String remark;// 备注

	private String createUserId;// 创建人id

	private String createUserName;// 创建人

	private java.util.Date createDate;// 创建时间

	private String lastModifyUserId;// 最后修改人id

	private String lastModifyUserName;// 最后修改人

	private java.util.Date lastModifyDate;// 最后修改时间

	private String dataStatus;// 数据状态

	public void setId(String value) {
		this.id = value;
	}

	public void setFkCarId(String value) {
		this.fkCarId = value;
	}

	public void setCmKm(String value) {
		this.cmKm = value;
	}

	public void setCmDate(java.util.Date value) {
		this.cmDate = value;
	}

	public void setNextCmKm(String value) {
		this.nextCmKm = value;
	}

	public void setNextCmDate(java.util.Date value) {
		this.nextCmDate = value;
	}

	public void setRemark(String value) {
		this.remark = value;
	}

	public void setCreateUserId(String value) {
		this.createUserId = value;
	}

	public void setCreateUserName(String value) {
		this.createUserName = value;
	}

	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}

	public void setLastModifyUserId(String value) {
		this.lastModifyUserId = value;
	}

	public void setLastModifyUserName(String value) {
		this.lastModifyUserName = value;
	}

	public void setLastModifyDate(java.util.Date value) {
		this.lastModifyDate = value;
	}

	public void setDataStatus(String value) {
		this.dataStatus = value;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getId() {
		return this.id;
	}

	@Column(name = "FK_CAR_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getFkCarId() {
		return this.fkCarId;
	}

	@Column(name = "CM_KM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getCmKm() {
		return this.cmKm;
	}

	@Column(name = "CM_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public java.util.Date getCmDate() {
		return this.cmDate;
	}

	@Column(name = "NEXT_CM_KM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getNextCmKm() {
		return this.nextCmKm;
	}

	@Column(name = "NEXT_CM_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public java.util.Date getNextCmDate() {
		return this.nextCmDate;
	}

	@Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	public String getRemark() {
		return this.remark;
	}

	@Column(name = "CREATE_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getCreateUserId() {
		return this.createUserId;
	}

	@Column(name = "CREATE_USER_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getCreateUserName() {
		return this.createUserName;
	}

	@Column(name = "CREATE_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	@Column(name = "LAST_MODIFY_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getLastModifyUserId() {
		return this.lastModifyUserId;
	}

	@Column(name = "LAST_MODIFY_USER_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getLastModifyUserName() {
		return this.lastModifyUserName;
	}

	@Column(name = "LAST_MODIFY_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public java.util.Date getLastModifyDate() {
		return this.lastModifyDate;
	}

	@Column(name = "DATA_STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getDataStatus() {
		return this.dataStatus;
	}

}
