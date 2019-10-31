package com.edu.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "all_university")
public class CodeUniversity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String yxdh;
	private String yxdm;
	private String yxmc;
	private String yxdz;
	private String jhsxdm;
	private String zgdm;
	private String zgmc;
	private String yxlxdm;
	private String yxlxmc;
	private String yxjblxdm;
	private String yxjblxmc;
	private String ssdm;
	private String ssmc;
	private String jffs;
	private String jffsyt;
	private String sf985;
	private String sf211;
	private Integer nf;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYxdm() {
		return yxdm;
	}

	public void setYxdm(String yxdm) {
		this.yxdm = yxdm;
	}

	public String getYxmc() {
		return yxmc;
	}

	public void setYxmc(String yxmc) {
		this.yxmc = yxmc;
	}

	public String getYxdh() {
		return yxdh;
	}

	public void setYxdh(String yxdh) {
		this.yxdh = yxdh;
	}

	public String getYxdz() {
		return yxdz;
	}

	public void setYxdz(String yxdz) {
		this.yxdz = yxdz;
	}

	public String getJhsxdm() {
		return jhsxdm;
	}

	public void setJhsxdm(String jhsxdm) {
		this.jhsxdm = jhsxdm;
	}

	public String getZgdm() {
		return zgdm;
	}

	public void setZgdm(String zgdm) {
		this.zgdm = zgdm;
	}

	public String getZgmc() {
		return zgmc;
	}

	public void setZgmc(String zgmc) {
		this.zgmc = zgmc;
	}

	public String getYxlxdm() {
		return yxlxdm;
	}

	public void setYxlxdm(String yxlxdm) {
		this.yxlxdm = yxlxdm;
	}

	public String getYxlxmc() {
		return yxlxmc;
	}

	public void setYxlxmc(String yxlxmc) {
		this.yxlxmc = yxlxmc;
	}

	public String getYxjblxdm() {
		return yxjblxdm;
	}

	public void setYxjblxdm(String yxjblxdm) {
		this.yxjblxdm = yxjblxdm;
	}

	public String getYxjblxmc() {
		return yxjblxmc;
	}

	public void setYxjblxmc(String yxjblxmc) {
		this.yxjblxmc = yxjblxmc;
	}

	public String getSsdm() {
		return ssdm;
	}

	public void setSsdm(String ssdm) {
		this.ssdm = ssdm;
	}

	public String getSsmc() {
		return ssmc;
	}

	public void setSsmc(String ssmc) {
		this.ssmc = ssmc;
	}

	public String getJffs() {
		return jffs;
	}

	public void setJffs(String jffs) {
		this.jffs = jffs;
	}

	public String getJffsyt() {
		return jffsyt;
	}

	public void setJffsyt(String jffsyt) {
		this.jffsyt = jffsyt;
	}

	public String getSf985() {
		return sf985;
	}

	public void setSf985(String sf985) {
		this.sf985 = sf985;
	}

	public String getSf211() {
		return sf211;
	}

	public void setSf211(String sf211) {
		this.sf211 = sf211;
	}

	public Integer getNf() {
		return nf;
	}

	public void setNf(Integer nf) {
		this.nf = nf;
	}

	
}
