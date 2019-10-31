package com.edu.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "all_zklx")
public class CodeZklx {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String zklxdm;
	private String zklxmc;	
	private String kldm;
	private String klmc;
	private String zklxsm;	
	private String xkkm;
	private Integer nf;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getKldm() {
		return kldm;
	}
	public void setKldm(String kldm) {
		this.kldm = kldm;
	}
	public String getKlmc() {
		return klmc;
	}
	public void setKlmc(String klmc) {
		this.klmc = klmc;
	}
	public String getZklxdm() {
		return zklxdm;
	}
	public void setZklxdm(String zklxdm) {
		this.zklxdm = zklxdm;
	}
	public String getZklxmc() {
		return zklxmc;
	}
	public void setZklxmc(String zklxmc) {
		this.zklxmc = zklxmc;
	}
	public String getZklxsm() {
		return zklxsm;
	}
	public void setZklxsm(String zklxsm) {
		this.zklxsm = zklxsm;
	}
	public String getXkkm() {
		return xkkm;
	}
	public void setXkkm(String xkkm) {
		this.xkkm = xkkm;
	}
	public Integer getNf() {
		return nf;
	}
	public void setNf(Integer nf) {
		this.nf = nf;
	}
	
	
	

}
