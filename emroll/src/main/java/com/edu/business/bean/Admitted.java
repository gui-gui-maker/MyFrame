package com.edu.business.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "base_admitted")
public class Admitted {
	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String ksh;
	private String mzdq;
	private String yxdh;
	private String yxdm;
	private String yxmc;
	private String zydm;
	private String zydh;
	private String zymc;
	private String pcdm;
	private String kldm;
	private String lqzyh;
	private String zyjb;
	private String jhxz;
	private String lqsj;
	private String xz;
	private String sfsf;
	private String tdmbh;
	private String lqfs;
	private String cj;
	private String tzcj;
	private String tdcj;
	private String bh;
	private String tddw;
	private String cc;
	private String printed_mc;
	private String printed_tj;
	private String printed_dd;
	private String version;//年份
	
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
	public String getCj() {
		return cj;
	}
	public void setCj(String cj) {
		this.cj = cj;
	}
	public String getTzcj() {
		return tzcj;
	}
	public void setTzcj(String tzcj) {
		this.tzcj = tzcj;
	}
	public String getTdcj() {
		return tdcj;
	}
	public void setTdcj(String tdcj) {
		this.tdcj = tdcj;
	}
	public String getBh() {
		return bh;
	}
	public void setBh(String bh) {
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
	public String getPrinted_mc() {
		return printed_mc;
	}
	public void setPrinted_mc(String printed_mc) {
		this.printed_mc = printed_mc;
	}
	public String getPrinted_tj() {
		return printed_tj;
	}
	public void setPrinted_tj(String printed_tj) {
		this.printed_tj = printed_tj;
	}
	public String getPrinted_dd() {
		return printed_dd;
	}
	public void setPrinted_dd(String printed_dd) {
		this.printed_dd = printed_dd;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
}
