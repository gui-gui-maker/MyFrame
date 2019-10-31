package com.edu.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true,value = {"hibernateLazyInitializer", "handler"})
public class Resource implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id; 
	@Transient
	private String pid;
	private String  code ;
	private String  name;
	private String  url;
	private Integer rank;
	@Transient
	private boolean hasAuth = false;
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)  
	@JoinColumn(name="pid") 
	private Resource parent;
	
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.REMOVE},mappedBy="parent") 
	private Set<Resource> children = new HashSet<Resource>();
	
	@JsonBackReference
	@JoinTable(name="ROLE_RESOURCE",
			joinColumns={@JoinColumn(name="RESOURCE_ID", referencedColumnName="ID")},
			inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
	@ManyToMany(fetch=FetchType.LAZY)
	private Set<Role> roles = new HashSet<Role>();
	
	@JsonBackReference
	@JoinTable(name="AUTH_RESOURCE",
			joinColumns={@JoinColumn(name="RESOURCE_ID", referencedColumnName="ID")},
			inverseJoinColumns={@JoinColumn(name="AUTH_ID", referencedColumnName="ID")})
	@ManyToMany(fetch=FetchType.LAZY)
	private Set<Auth> auths = new HashSet<Auth>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	
	public Resource getParent() {
		return parent;
	}
	public void setParent(Resource parent) {
		this.parent = parent;
	}
	
	public Set<Resource> getChildren() {
		return children;
	}
	
	public void setChildren(Set<Resource> children) {
		this.children = children;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public boolean isHasAuth() {
		return hasAuth;
	}
	public void setHasAuth(boolean hasAuth) {
		this.hasAuth = hasAuth;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Set<Auth> getAuths() {
		return auths;
	}
	public void setAuths(Set<Auth> auths) {
		this.auths = auths;
	} 
	
	
	  
}
