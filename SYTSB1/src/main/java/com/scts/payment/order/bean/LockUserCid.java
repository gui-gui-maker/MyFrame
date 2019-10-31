package com.scts.payment.order.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "LOCK_USER_CID")
public class LockUserCid implements BaseEntity{
	private static final long serialVersionUID = 1L;
	private String id;//流水号
	private String fkUserId;//用户id
	private String name;//用户姓名
	private String cid;//设备id
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="FK_USER_ID")
	public String getFkUserId() {
		return fkUserId;
	}
	public void setFkUserId(String fkUserId) {
		this.fkUserId = fkUserId;
	}
	
	@Column(name="CID")
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "LockUserCid [id=" + id + ", fkUserId=" + fkUserId + ", name=" + name + ", cid=" + cid + "]";
	}
	
	
}
