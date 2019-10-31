package com.scts.cspace.space.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * @author XCB
 * @date 2016-10-24
 * @summary 资源空间属性
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tjypt_resource_space")
public class TjyptResourceSpace implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;// 资源空间属性ID
	private String space_access_password;// 空间访问验证密码
	private Date space_create_date;// 资源空间创建时间
	private Date space_last_upload_date;// 最后上传资源的时间
	private String space_max_size;// 资源空间总大小
	private String space_owner;// 源空间拥有者
	private String space_type;// 源空间类型
	private String space_use_size;// 空间实际使用大小
	private String space_hidden_type;//空间隐藏模式 0 - 普通模式  1-隐藏模式
	private String space_hidden_password;//空间隐藏模式密码


	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSpace_access_password() {
		return space_access_password;
	}

	public void setSpace_access_password(String space_access_password) {
		this.space_access_password = space_access_password;
	}

	public Date getSpace_create_date() {
		return space_create_date;
	}

	public void setSpace_create_date(Date space_create_date) {
		this.space_create_date = space_create_date;
	}

	public Date getSpace_last_upload_date() {
		return space_last_upload_date;
	}

	public void setSpace_last_upload_date(Date space_last_upload_date) {
		this.space_last_upload_date = space_last_upload_date;
	}

	public String getSpace_max_size() {
		return space_max_size;
	}

	public void setSpace_max_size(String space_max_size) {
		this.space_max_size = space_max_size;
	}

	public String getSpace_owner() {
		return space_owner;
	}

	public void setSpace_owner(String space_owner) {
		this.space_owner = space_owner;
	}

	public String getSpace_type() {
		return space_type;
	}

	public void setSpace_type(String space_type) {
		this.space_type = space_type;
	}

	public String getSpace_use_size() {
		return space_use_size;
	}

	public void setSpace_use_size(String space_use_size) {
		this.space_use_size = space_use_size;
	}

	public String getSpace_hidden_type() {
		return space_hidden_type;
	}

	public void setSpace_hidden_type(String space_hidden_type) {
		this.space_hidden_type = space_hidden_type;
	}

	public String getSpace_hidden_password() {
		return space_hidden_password;
	}

	public void setSpace_hidden_password(String space_hidden_password) {
		this.space_hidden_password = space_hidden_password;
	}

	
}
