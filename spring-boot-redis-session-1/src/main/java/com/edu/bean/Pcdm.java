package com.edu.bean;

import java.io.Serializable;

public class Pcdm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pcdm;
	private String pcmc;
	private String gbpcdm;
	private String gbccdm;
	private int nf;
	
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
	public int getNf() {
		return nf;
	}
	public void setNf(int nf) {
		this.nf = nf;
	}
	
	
}
