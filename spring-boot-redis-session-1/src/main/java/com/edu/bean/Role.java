package com.edu.bean;

import java.util.HashSet;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true,value = {"hibernateLazyInitializer", "handler"})
@Entity
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String role;//角色名
	private String depict;//描述
	private boolean available;//是否可用

	
	@ManyToMany(mappedBy="roles",fetch=FetchType.EAGER)
	private Set<Auth> auths = new HashSet<>();
	
	@JsonBackReference
	@ManyToMany(mappedBy="roles",fetch=FetchType.LAZY)
	private Set<User> users = new HashSet<User>();
	
	@ManyToMany(mappedBy="roles",fetch=FetchType.EAGER)
	private Set<Resource> resources = new HashSet<Resource>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public String getDepict() {
		return depict;
	}
	public void setDepict(String depict) {
		this.depict = depict;
	}
	
	public Set<Auth> getAuths() {
		return auths;
	}
	public void setAuths(Set<Auth> auths) {
		this.auths = auths;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Set<Resource> getResources() {
		return resources;
	}
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
	
	
}
