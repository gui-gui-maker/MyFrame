package com.khnt.rbac.impl.bean;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 系统用户信息
 * 
 * @author 邹洪平 2012-9-10
 * @version 1.0
 * 
 */
@Entity
@Table(name = "sys_user")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "fieldHandler", "roles" })
public class User implements BaseEntity, com.khnt.rbac.bean.User {
	private static final long serialVersionUID = 1L;

	private String id;// 标识ID

	private String account;// 登录用户名

	private String name;// 姓名

	private String password;// 用户密码

	private String status;// 是否启用

	private String sort;// 排序

	private Set<Role> roles; // 角色集合

	private Org org;// 组织机构

	private Employee employee;

	private Date lastPwdDate;// 最后修改密码时间

	private String extConfig;// 扩展信息，json格式

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "ACCOUNT")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "SORT")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@ManyToMany(targetEntity = Role.class, cascade = { javax.persistence.CascadeType.MERGE,
			javax.persistence.CascadeType.PERSIST })
	@JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@ManyToOne
	@JoinColumn(name = "ORG_ID")
	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof User) {
			if (((User) obj).getId().equals(this.getId()))
				return true;
			if (obj.hashCode() == this.hashCode())
				return true;
		}
		return false;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "EMPLOYEE_ID", nullable = true)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Column(name = "LAST_PWD_DATE")
	public Date getLastPwdDate() {
		return lastPwdDate;
	}

	public void setLastPwdDate(Date lastPwdDate) {
		this.lastPwdDate = lastPwdDate;
	}

	public String toString() {
		return "系统用户:" + getName() + "(ID=" + getId() + ")";
	}

	@Column(name = "ext_config")
	public String getExtConfig() {
		return this.extConfig;
	}

	public void setExtConfig(String extConfig) {
		this.extConfig = extConfig;
	}
}