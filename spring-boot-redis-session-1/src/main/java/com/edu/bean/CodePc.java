package com.edu.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "all_pc")
public class CodePc implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pcdm")
	private String pcdm;//批次代码
	private String pcmc;//批次名称
	private String gbpcdm;//国编批次代码
	private String gbccdm;//层次代码
	private String ccmc;//层次名称
	private String pcsm;//批次说明
	private int nf;//年份
	
	
	public String getPcdm() {
		return pcdm;
	}
	public void setPcdm(String pcdm) {
		this.pcdm = pcdm;
	}
	public String getPcmc() {
		return pcmc;
	}
	public void setPcmc(String pcmc) {
		this.pcmc = pcmc;
	}
	public String getGbpcdm() {
		return gbpcdm;
	}
	public void setGbpcdm(String gbpcdm) {
		this.gbpcdm = gbpcdm;
	}
	public String getGbccdm() {
		return gbccdm;
	}
	public void setGbccdm(String gbccdm) {
		this.gbccdm = gbccdm;
	}
	public String getCcmc() {
		return ccmc;
	}
	public void setCcmc(String ccmc) {
		this.ccmc = ccmc;
	}
	public String getPcsm() {
		return pcsm;
	}
	public void setPcsm(String pcsm) {
		this.pcsm = pcsm;
	}
	public int getNf() {
		return nf;
	}
	public void setNf(int nf) {
		this.nf = nf;
	}
	
}
