package com.edu.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 校核计划
 * @author Guido
 *
 */
@Entity
@Table(name = "t_zydh")
public class Plan {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	
	private String 	id      ;
	private String jhid;
	private String 	yxdm	;
	private String 	yxmc	;
	private String 	yxdh	;
	private String 	yxdhmc	;
	private String 	sbzydh	;
	private String 	zydm	;
	private String 	zszymc	;
	private String 	zkfx	;
	private String 	bhzygs	;
	private String 	zylbdm	;
	private String 	ccdm	;
	private String 	xzdm	;
	private String 	sfbz	;
	private String 	bxdd	;
	private String 	sfks	;
	private String 	wyyz	;
	private String 	kldm	;
	private String 	jhxzdm	;
	private String 	pcdm	;
	private String 	jhlbdm	;
	private String 	zklxdm	;
	private String 	zklxmc	;
	private String 	kskmyq	;
	private String 	kskmyqzw	;
	private String 	xkkmbhzy	;
	private String 	xbyq	;
	private String 	zsjhs	;
	private String 	zjhs	;
	private String 	zybz	;
	private String 	ytlb	;
	private String 	yxbz	;
	private String 	dz1	;
	private String 	dz2	;
	private String 	dz3	;
	private String 	dz4	;
	private String 	dz5	;
	private String 	dz6	;
	private String 	bbjhid	;
	private String 	sfdy	;
	private int	dybz	;
	private String 	dyml	;
	private String 	cc	;
	private String 	pc	;
	private String 	yxdz	;
	private String 	xbzydh	;
	private String 	bhzy	;
	private String 	bxddssmc	;
	private String 	bxdddjsmc	;
	private String 	bxddqxmc	;
	private String 	bxddxxdz	;
	private String 	bxddbb	;
	private int 	xgbj = 0;
	private String 	dz9	;
	private String 	dz8	;
	private String 	sddm	;
	private int applies = 0;//申请未处理条数
	private String status;//0未修改，1院校修改，2，修改人员修改，3考试院确认（院校不能再修改，只能确认，或申请），4院校申请，5院校确认
	private String lastUpdateBy;
	private Date lastUpdateTime;
	
	public String getJhid() {
		return jhid;
	}
	public void setJhid(String jhid) {
		this.jhid = jhid;
	}
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
	public String getYxdhmc() {
		return yxdhmc;
	}
	public void setYxdhmc(String yxdhmc) {
		this.yxdhmc = yxdhmc;
	}
	public String getSbzydh() {
		return sbzydh;
	}
	public void setSbzydh(String sbzydh) {
		this.sbzydh = sbzydh;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getZszymc() {
		return zszymc;
	}
	public void setZszymc(String zszymc) {
		this.zszymc = zszymc;
	}
	public String getZkfx() {
		return zkfx;
	}
	public void setZkfx(String zkfx) {
		this.zkfx = zkfx;
	}
	public String getBhzygs() {
		return bhzygs;
	}
	public void setBhzygs(String bhzygs) {
		this.bhzygs = bhzygs;
	}
	public String getZylbdm() {
		return zylbdm;
	}
	public void setZylbdm(String zylbdm) {
		this.zylbdm = zylbdm;
	}
	public String getCcdm() {
		return ccdm;
	}
	public void setCcdm(String ccdm) {
		this.ccdm = ccdm;
	}
	public String getXzdm() {
		return xzdm;
	}
	public void setXzdm(String xzdm) {
		this.xzdm = xzdm;
	}
	public String getSfbz() {
		return sfbz;
	}
	public void setSfbz(String sfbz) {
		this.sfbz = sfbz;
	}
	public String getBxdd() {
		return bxdd;
	}
	public void setBxdd(String bxdd) {
		this.bxdd = bxdd;
	}
	public String getSfks() {
		return sfks;
	}
	public void setSfks(String sfks) {
		this.sfks = sfks;
	}
	public String getWyyz() {
		return wyyz;
	}
	public void setWyyz(String wyyz) {
		this.wyyz = wyyz;
	}
	public String getKldm() {
		return kldm;
	}
	public void setKldm(String kldm) {
		this.kldm = kldm;
	}
	public String getJhxzdm() {
		return jhxzdm;
	}
	public void setJhxzdm(String jhxzdm) {
		this.jhxzdm = jhxzdm;
	}
	public String getPcdm() {
		return pcdm;
	}
	public void setPcdm(String pcdm) {
		this.pcdm = pcdm;
	}
	public String getJhlbdm() {
		return jhlbdm;
	}
	public void setJhlbdm(String jhlbdm) {
		this.jhlbdm = jhlbdm;
	}
	public String getZklxdm() {
		return zklxdm;
	}
	public void setZklxdm(String zklxdm) {
		this.zklxdm = zklxdm;
	}
	public String getZklxmc() {
		return zklxmc;
	}
	public void setZklxmc(String zklxmc) {
		this.zklxmc = zklxmc;
	}
	public String getKskmyq() {
		return kskmyq;
	}
	public void setKskmyq(String kskmyq) {
		this.kskmyq = kskmyq;
	}
	public String getKskmyqzw() {
		return kskmyqzw;
	}
	public void setKskmyqzw(String kskmyqzw) {
		this.kskmyqzw = kskmyqzw;
	}
	public String getXkkmbhzy() {
		return xkkmbhzy;
	}
	public void setXkkmbhzy(String xkkmbhzy) {
		this.xkkmbhzy = xkkmbhzy;
	}
	public String getXbyq() {
		return xbyq;
	}
	public void setXbyq(String xbyq) {
		this.xbyq = xbyq;
	}
	public String getZsjhs() {
		return zsjhs;
	}
	public void setZsjhs(String zsjhs) {
		this.zsjhs = zsjhs;
	}
	public String getZjhs() {
		return zjhs;
	}
	public void setZjhs(String zjhs) {
		this.zjhs = zjhs;
	}
	public String getZybz() {
		return zybz;
	}
	public void setZybz(String zybz) {
		this.zybz = zybz;
	}
	public String getYtlb() {
		return ytlb;
	}
	public void setYtlb(String ytlb) {
		this.ytlb = ytlb;
	}
	public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
	public String getDz1() {
		return dz1;
	}
	public void setDz1(String dz1) {
		this.dz1 = dz1;
	}
	public String getDz2() {
		return dz2;
	}
	public void setDz2(String dz2) {
		this.dz2 = dz2;
	}
	public String getDz3() {
		return dz3;
	}
	public void setDz3(String dz3) {
		this.dz3 = dz3;
	}
	public String getDz4() {
		return dz4;
	}
	public void setDz4(String dz4) {
		this.dz4 = dz4;
	}
	public String getDz5() {
		return dz5;
	}
	public void setDz5(String dz5) {
		this.dz5 = dz5;
	}
	public String getDz6() {
		return dz6;
	}
	public void setDz6(String dz6) {
		this.dz6 = dz6;
	}
	public String getBbjhid() {
		return bbjhid;
	}
	public void setBbjhid(String bbjhid) {
		this.bbjhid = bbjhid;
	}
	public String getSfdy() {
		return sfdy;
	}
	public void setSfdy(String sfdy) {
		this.sfdy = sfdy;
	}
	public int getDybz() {
		return dybz;
	}
	public void setDybz(int dybz) {
		this.dybz = dybz;
	}
	public String getDyml() {
		return dyml;
	}
	public void setDyml(String dyml) {
		this.dyml = dyml;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getPc() {
		return pc;
	}
	public void setPc(String pc) {
		this.pc = pc;
	}
	public String getYxdz() {
		return yxdz;
	}
	public void setYxdz(String yxdz) {
		this.yxdz = yxdz;
	}
	public String getXbzydh() {
		return xbzydh;
	}
	public void setXbzydh(String xbzydh) {
		this.xbzydh = xbzydh;
	}
	public String getBhzy() {
		return bhzy;
	}
	public void setBhzy(String bhzy) {
		this.bhzy = bhzy;
	}
	public String getBxddssmc() {
		return bxddssmc;
	}
	public void setBxddssmc(String bxddssmc) {
		this.bxddssmc = bxddssmc;
	}
	public String getBxdddjsmc() {
		return bxdddjsmc;
	}
	public void setBxdddjsmc(String bxdddjsmc) {
		this.bxdddjsmc = bxdddjsmc;
	}
	public String getBxddqxmc() {
		return bxddqxmc;
	}
	public void setBxddqxmc(String bxddqxmc) {
		this.bxddqxmc = bxddqxmc;
	}
	public String getBxddxxdz() {
		return bxddxxdz;
	}
	public void setBxddxxdz(String bxddxxdz) {
		this.bxddxxdz = bxddxxdz;
	}
	public String getBxddbb() {
		return bxddbb;
	}
	public void setBxddbb(String bxddbb) {
		this.bxddbb = bxddbb;
	}
	public int getXgbj() {
		return xgbj;
	}
	public void setXgbj(int xgbj) {
		this.xgbj = xgbj;
	}
	public String getDz9() {
		return dz9;
	}
	public void setDz9(String dz9) {
		this.dz9 = dz9;
	}
	public String getDz8() {
		return dz8;
	}
	public void setDz8(String dz8) {
		this.dz8 = dz8;
	}
	public String getSddm() {
		return sddm;
	}
	public void setSddm(String sddm) {
		this.sddm = sddm;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLastUpdateBy() {
		return lastUpdateBy;
	}
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public int getApplies() {
		return applies;
	}
	public void setApplies(int applies) {
		this.applies = applies;
	}
	
	

}