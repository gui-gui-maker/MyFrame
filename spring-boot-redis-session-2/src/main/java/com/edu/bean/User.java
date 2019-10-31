package com.edu.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "sys_user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4873169939186589893L;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
	@Column(name="USERNAME")
	private String userName;
	private String password;
	private String salt;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)//级联保存、更新、删除、刷新;延迟加载
	private Set<Contact> contacts = new HashSet<Contact>();
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)//级联保存、更新、删除、刷新;延迟加载
	private Set<Role> roles = new HashSet<Role>();
	
	private String yxdm;
	

    public User() {
    	
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public String getYxdm() {
		return yxdm;
	}

	public void setYxdm(String yxdm) {
		this.yxdm = yxdm;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", salt=" + salt + "]"+(getRoles().iterator().hasNext()?getRoles().iterator().next().toString():"no role");
	}
	
	

}
