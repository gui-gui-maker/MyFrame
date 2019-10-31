package com.edu.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "all_jhxz")
public class CodeJhxz {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jhxzdm")
	private String jhxzdm;
	private String jhxzmc;
	private Integer nf;
	
	public String getJhxzdm() {
		return jhxzdm;
	}
	public void setJhxzdm(String jhxzdm) {
		this.jhxzdm = jhxzdm;
	}
	public String getJhxzmc() {
		return jhxzmc;
	}
	public void setJhxzmc(String jhxzmc) {
		this.jhxzmc = jhxzmc;
	}
	public Integer getNf() {
		return nf;
	}
	public void setNf(Integer nf) {
		this.nf = nf;
	}
	
	
	
}
