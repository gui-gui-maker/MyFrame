package com.edu.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true,value = {"hibernateLazyInitializer", "handler"})
@Entity
public class Profile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String yxdh;
	private String yxdm;
	private String yxmc;
	private String yxdz;
	private String contact;
	private String department;
	private String duty;
	private String mobile;
	private String tel;
	private String email;
	private String qq;
	private String tax;
	private String status;
	
	//映射单向n-1的关联关系
	//使用@ManyToOne 來映射多对一的关联关系
	//使用@JoinColumn來映射外键
	//可以用@ManyToOne 的fetch属性來设置延迟加载
	@JsonBackReference
	@JoinColumn(name="user_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getYxdm() {
		return yxdm;
	}
	public void setYxdm(String yxdm) {
		this.yxdm = yxdm;
	}
	public String getYxmc() {
		return yxmc;
	}
	public void setYxmc(String yxmc) {
		this.yxmc = yxmc;
	}
	public String getYxdh() {
		return yxdh;
	}
	public void setYxdh(String yxdh) {
		this.yxdh = yxdh;
	}
	public String getYxdz() {
		return yxdz;
	}
	public void setYxdz(String yxdz) {
		this.yxdz = yxdz;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
