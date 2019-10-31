package com.edu.user.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "permission")
public class Permission implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String name;
	private String auth;
	private String resourceUrl;
	private String illustration;
	
	@ManyToMany
	@JoinTable(name="user_permission",	//用来指定中间表的名称
				//用于指定本表在中间表的字段名称，以及中间表依赖的是本表的哪个字段
			   joinColumns= {@JoinColumn(name="permission_id",referencedColumnName="id")},
				//用于指定对方表在中间表的字段名称，以及中间表依赖的是它的哪个字段
			   inverseJoinColumns= {@JoinColumn(name="user_id",referencedColumnName="id")}
			)
	private Set<User> users = new HashSet<User>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public String getIllustration() {
		return illustration;
	}
	public void setIllustration(String illustration) {
		this.illustration = illustration;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}
