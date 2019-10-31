package com.lsts.mobileapp.login.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "sys_user_online")
public class Online implements BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String clientId;
	
	private String userId;
	
	private String orgId;
	
	private String isOnline;
	
	private String isPush;
	
	private String isClientReceived;
	
	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="client_id")
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name="is_online")
	public String getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}
	
	@Column(name="is_push")
	public String getIsPush() {
		return isPush;
	}
	public void setIsPush(String isPush) {
		this.isPush = isPush;
	}
	@Column(name="is_client_received")
	public String getIsClientReceived() {
		return isClientReceived;
	}
	public void setIsClientReceived(String isClientReceived) {
		this.isClientReceived = isClientReceived;
	}
	@Column(name="org_id")
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	
}
