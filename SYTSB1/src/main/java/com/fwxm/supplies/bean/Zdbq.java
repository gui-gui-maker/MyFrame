package com.fwxm.supplies.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;
/**
 * 自动补全
 * @author MYP
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "TJY2_CH_ZDBQ")
public class Zdbq implements BaseEntity {
	private static final long serialVersionUID = 1L;
	private String id;
	private String text;
	private String type;//1：产品名称，2：规格及型号
	private String createUserName;
	private String createUserId;
	private String createOrgId;
	private String createOrgName;
	private String createunitName;
	private String createunitId;
	private Date createTime;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	@Column(name="TEXT")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Column(name="TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="CREATE_USER_NAME")
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	@Column(name="CREATE_USER_ID")
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	@Column(name="CREATE_ORG_ID")
	public String getCreateOrgId() {
		return createOrgId;
	}
	public void setCreateOrgId(String createOrgId) {
		this.createOrgId = createOrgId;
	}
	@Column(name="CREATE_ORG_NAME")
	public String getCreateOrgName() {
		return createOrgName;
	}
	public void setCreateOrgName(String createOrgName) {
		this.createOrgName = createOrgName;
	}
	@Column(name="CREATE_UNIT_NAME")
	public String getCreateunitName() {
		return createunitName;
	}
	public void setCreateunitName(String createunitName) {
		this.createunitName = createunitName;
	}
	@Column(name="CREATE_UNIT_ID")
	public String getCreateunitId() {
		return createunitId;
	}
	public void setCreateunitId(String createunitId) {
		this.createunitId = createunitId;
	}
	@Column(name="CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
