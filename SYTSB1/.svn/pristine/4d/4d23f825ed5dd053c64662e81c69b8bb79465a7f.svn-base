package com.fwxm.scientific.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

public class KYTaskBook  implements BaseEntity{
	private String id;
	private String xlId;//立项编号
	private String xmmc;//项目名称
	private String zylb;//专业类别
	private String xmfzrqz;//项目负责人签字
	private String lxjf;//立项经费
	private Date xm_start_date;//项目开始时间
	private Date xm_end_date;//项目结束时间
	
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="XL_ID")
	public String getXlId() {
		return xlId;
	}
	public void setXlId(String xlId) {
		this.xlId = xlId;
	}
	@Column(name="XMMC")
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	@Column(name="ZYLB")
	public String getZylb() {
		return zylb;
	}
	public void setZylb(String zylb) {
		this.zylb = zylb;
	}
	@Column(name="XMFZRQZ")
	public String getXmfzrqz() {
		return xmfzrqz;
	}
	public void setXmfzrqz(String xmfzrqz) {
		this.xmfzrqz = xmfzrqz;
	}
	@Column(name="LXJF")
	public String getLxjf() {
		return lxjf;
	}
	public void setLxjf(String lxjf) {
		this.lxjf = lxjf;
	}
	@Column(name="XM_START_DATE")
	public Date getXm_start_date() {
		return xm_start_date;
	}
	public void setXm_start_date(Date xm_start_date) {
		this.xm_start_date = xm_start_date;
	}
	@Column(name="XM_END_DATE")
	public Date getXm_end_date() {
		return xm_end_date;
	}
	public void setXm_end_date(Date xm_end_date) {
		this.xm_end_date = xm_end_date;
	}
	
	
	
}
