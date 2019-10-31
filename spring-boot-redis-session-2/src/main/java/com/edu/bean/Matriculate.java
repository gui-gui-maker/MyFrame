package com.edu.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 被录取者
 * @author Guido
 *
 */

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Matriculate")
public class Matriculate {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String 	id        	;
	private String 	ksh        	;
	private String 	mzdq       	;
	private String 	yxdh       	;
	private String 	yxdm       	;
	private String 	yxmc       	;
	private String 	zydm       	;
	private String 	zydh       	;
	private String 	zymc       	;
	private String 	pcdm       	;
	private String 	kldm       	;
	private String 	lqzyh      	;
	private String 	zyjb       	;
	private String 	jhxz       	;
	private String 	lqsj       	;
	private String 	xz         	;
	private String 	sfsf       	;
	private String 	tdmbh      	;
	private String 	lqfs       	;
	private Integer cj         	;
	private Integer tzcj       	;
	private Integer tdcj       	;
	private Integer bh         	;
	private String 	tddw       	;
	private String 	cc         	;
	private Integer printed_mc 	;
	private Integer printed_tj 	;
	private Integer printed_dd 	;
	private String nf;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKsh() {
		return ksh;
	}
	public void setKsh(String ksh) {
		this.ksh = ksh;
	}
	public String getMzdq() {
		return mzdq;
	}
	public void setMzdq(String mzdq) {
		this.mzdq = mzdq;
	}
	public String getYxdh() {
		return yxdh;
	}
	public void setYxdh(String yxdh) {
		this.yxdh = yxdh;
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
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getZydh() {
		return zydh;
	}
	public void setZydh(String zydh) {
		this.zydh = zydh;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getPcdm() {
		return pcdm;
	}
	public void setPcdm(String pcdm) {
		this.pcdm = pcdm;
	}
	public String getKldm() {
		return kldm;
	}
	public void setKldm(String kldm) {
		this.kldm = kldm;
	}
	public String getLqzyh() {
		return lqzyh;
	}
	public void setLqzyh(String lqzyh) {
		this.lqzyh = lqzyh;
	}
	public String getZyjb() {
		return zyjb;
	}
	public void setZyjb(String zyjb) {
		this.zyjb = zyjb;
	}
	public String getJhxz() {
		return jhxz;
	}
	public void setJhxz(String jhxz) {
		this.jhxz = jhxz;
	}
	public String getLqsj() {
		return lqsj;
	}
	public void setLqsj(String lqsj) {
		this.lqsj = lqsj;
	}
	public String getXz() {
		return xz;
	}
	public void setXz(String xz) {
		this.xz = xz;
	}
	public String getSfsf() {
		return sfsf;
	}
	public void setSfsf(String sfsf) {
		this.sfsf = sfsf;
	}
	public String getTdmbh() {
		return tdmbh;
	}
	public void setTdmbh(String tdmbh) {
		this.tdmbh = tdmbh;
	}
	public String getLqfs() {
		return lqfs;
	}
	public void setLqfs(String lqfs) {
		this.lqfs = lqfs;
	}
	public Integer getCj() {
		return cj;
	}
	public void setCj(Integer cj) {
		this.cj = cj;
	}
	public Integer getTzcj() {
		return tzcj;
	}
	public void setTzcj(Integer tzcj) {
		this.tzcj = tzcj;
	}
	public Integer getTdcj() {
		return tdcj;
	}
	public void setTdcj(Integer tdcj) {
		this.tdcj = tdcj;
	}
	public Integer getBh() {
		return bh;
	}
	public void setBh(Integer bh) {
		this.bh = bh;
	}
	public String getTddw() {
		return tddw;
	}
	public void setTddw(String tddw) {
		this.tddw = tddw;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public Integer getPrinted_mc() {
		return printed_mc;
	}
	public void setPrinted_mc(Integer printed_mc) {
		this.printed_mc = printed_mc;
	}
	public Integer getPrinted_tj() {
		return printed_tj;
	}
	public void setPrinted_tj(Integer printed_tj) {
		this.printed_tj = printed_tj;
	}
	public Integer getPrinted_dd() {
		return printed_dd;
	}
	public void setPrinted_dd(Integer printed_dd) {
		this.printed_dd = printed_dd;
	}
	
	public String getNf() {
		return nf;
	}
	public void setNf(String nf) {
		this.nf = nf;
	}
	
	
	
}
