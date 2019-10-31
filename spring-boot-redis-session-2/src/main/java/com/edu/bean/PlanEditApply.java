package com.edu.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "plan_edit_apply")
public class PlanEditApply {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String 	id      ;
	private String 	fid	;
	private String 	field	;
	private String oValue;
	private String nValue;
	private String  otherApply;//申请内容
	private String  applyBy;//时间
	private Date  applyTime;//时间
	private String checkBy;
	private Date checkTime;
	private String  status;//0待审核，1已审核
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getoValue() {
		return oValue;
	}
	public void setoValue(String oValue) {
		this.oValue = oValue;
	}
	public String getnValue() {
		return nValue;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOtherApply() {
		return otherApply;
	}
	public void setOtherApply(String otherApply) {
		this.otherApply = otherApply;
	}
	public String getApplyBy() {
		return applyBy;
	}
	public void setApplyBy(String applyBy) {
		this.applyBy = applyBy;
	}
	public String getCheckBy() {
		return checkBy;
	}
	public void setCheckBy(String checkBy) {
		this.checkBy = checkBy;
	}
	public String getStatus() {
		return status;
	}
	public void setnValue(String nValue) {
		this.nValue = nValue;
	}
	
	
	
}
