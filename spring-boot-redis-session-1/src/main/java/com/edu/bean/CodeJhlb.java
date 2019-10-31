package com.edu.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "all_jhlb")
public class CodeJhlb {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jhlbdm")
	private String jhlbdm;
	private String jhlbmc;
	private String fslb;
	private String fslbmc;
	private String syfw;
	private String xysyfw;
	private Integer nf;
	
	public String getJhlbdm() {
		return jhlbdm;
	}
	public void setJhlbdm(String jhlbdm) {
		this.jhlbdm = jhlbdm;
	}
	public String getJhlbmc() {
		return jhlbmc;
	}
	public void setJhlbmc(String jhlbmc) {
		this.jhlbmc = jhlbmc;
	}
	public String getFslb() {
		return fslb;
	}
	public void setFslb(String fslb) {
		this.fslb = fslb;
	}
	public String getFslbmc() {
		return fslbmc;
	}
	public void setFslbmc(String fslbmc) {
		this.fslbmc = fslbmc;
	}
	public String getSyfw() {
		return syfw;
	}
	public void setSyfw(String syfw) {
		this.syfw = syfw;
	}
	public String getXysyfw() {
		return xysyfw;
	}
	public void setXysyfw(String xysyfw) {
		this.xysyfw = xysyfw;
	}
	public Integer getNf() {
		return nf;
	}
	public void setNf(Integer nf) {
		this.nf = nf;
	}
	
	
}
