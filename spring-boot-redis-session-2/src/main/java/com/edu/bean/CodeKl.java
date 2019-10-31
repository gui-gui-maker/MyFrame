package com.edu.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "all_kl")
public class CodeKl {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "kldm")
	private String kldm;
	private String klmc;
	private Integer nf;
	
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
	public Integer getNf() {
		return nf;
	}
	public void setNf(Integer nf) {
		this.nf = nf;
	}
	
	
}
