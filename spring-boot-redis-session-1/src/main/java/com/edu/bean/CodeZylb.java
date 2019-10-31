package com.edu.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "all_zylb")
public class CodeZylb {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String zylbdm;
	private String zylbmc;
	private int nf;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getZylbdm() {
		return zylbdm;
	}
	public void setZylbdm(String zylbdm) {
		this.zylbdm = zylbdm;
	}
	public String getZylbmc() {
		return zylbmc;
	}
	public void setZylbmc(String zylbmc) {
		this.zylbmc = zylbmc;
	}
	public int getNf() {
		return nf;
	}
	public void setNf(int nf) {
		this.nf = nf;
	}
	
	
	
}
