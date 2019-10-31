package com.edu.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "code_university")
public class University0  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String 	yxdm	;
	private String 	yxmc	;
	private String 	yxywmc	;
	private String 	yxdz	;
	private String 	yzbm	;
	private String 	sddm	;
	private String 	ssmc	;
	private String 	zgdm	;
	private String 	zgmc	;
	private String 	yxlxdm	;
	private String 	yxlxmc	;
	private String 	yxjblxdm	;
	private String 	yxjblxmc	;
	private String 	sf211	;
	private String 	sf985	;
	private String 	gzbz	;
	private String 	txdz	;
	private String 	kszxdh	;
	private String 	zszcwz	;
	private String 	yxbz	;

	/*
	 * private String yxdh; private String jhsxdm; private String ssdm; private
	 * String jffs; private String jffsyt;private Integer nf;
	 */

	
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)//级联保存、更新、删除、刷新;延迟加载
	private Set<CodeUniversity> colleges = new HashSet<CodeUniversity>();
	
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
	public String getYxywmc() {
		return yxywmc;
	}
	public void setYxywmc(String yxywmc) {
		this.yxywmc = yxywmc;
	}
	public String getYxdz() {
		return yxdz;
	}
	public void setYxdz(String yxdz) {
		this.yxdz = yxdz;
	}
	public String getYzbm() {
		return yzbm;
	}
	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	public String getSddm() {
		return sddm;
	}
	public void setSddm(String sddm) {
		this.sddm = sddm;
	}
	public String getSsmc() {
		return ssmc;
	}
	public void setSsmc(String ssmc) {
		this.ssmc = ssmc;
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
	public String getSf211() {
		return sf211;
	}
	public void setSf211(String sf211) {
		this.sf211 = sf211;
	}
	public String getSf985() {
		return sf985;
	}
	public void setSf985(String sf985) {
		this.sf985 = sf985;
	}
	public String getGzbz() {
		return gzbz;
	}
	public void setGzbz(String gzbz) {
		this.gzbz = gzbz;
	}
	public String getTxdz() {
		return txdz;
	}
	public void setTxdz(String txdz) {
		this.txdz = txdz;
	}
	public String getKszxdh() {
		return kszxdh;
	}
	public void setKszxdh(String kszxdh) {
		this.kszxdh = kszxdh;
	}
	public String getZszcwz() {
		return zszcwz;
	}
	public void setZszcwz(String zszcwz) {
		this.zszcwz = zszcwz;
	}
	public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
	public Set<CodeUniversity> getColleges() {
		return colleges;
	}
	public void setColleges(Set<CodeUniversity> colleges) {
		this.colleges = colleges;
	}
	
	

}
