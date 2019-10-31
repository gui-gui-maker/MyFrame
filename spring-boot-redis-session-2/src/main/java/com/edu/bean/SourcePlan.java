package com.edu.bean;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 来源计划
 * @author Guido
 *
 */
@Entity
@Table(name = "base_source_plan")
public class SourcePlan {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String 	jhid	;
	private String 	nf	;
	private String 	yxdm	;
	private String 	yxmc	;
	private String 	yxdh	;
	private String 	yxdhmc	;
	private String 	zszydm	;
	private String 	zszymc	;
	private String 	zylbdm	;
	private String 	zylbmc	;
	private String 	bhzy	;
	private String 	bhzygs	;
	private String 	ccdm	;
	private String 	ccmc	;
	private String 	sbzydh	;
	private String 	xbzydh	;
	private String 	zkfx	;
	private String 	xzdm	;
	private String 	xzmc	;
	private String 	sfbz	;
	private String 	bxdd	;
	private String 	bxddssmc	;
	private String 	bxdddjsmc	;
	private String 	bxddqxmc	;
	private String 	bxddxxdz	;
	private String 	bxddbb	;
	private String 	sfks	;
	private String 	wyyz	;
	private String 	yxbmdm	;
	private String 	yxbmmc	;
	private String 	kldm	;
	private String 	klmc	;
	private String 	jhxzdm	;
	private String 	jhxzmc	;
	private String 	jhlbdm	;
	private String 	jhlbmc	;
	private String 	syssdm	;
	private String 	syssmc	;
	private String 	pcdm	;
	private String 	pcmc	;
	private String 	zklxdm	;
	private String 	zklxmc	;
	private String 	kskmyq	;
	private String 	kskmyqzw	;
	private String 	xkkmbhzy	;
	private String 	skyq	;
	private String 	zybz	;
	private String 	dz1	;
	private String 	dz2	;
	private String 	dz3	;
	private String 	dz4	;
	private String 	dz5	;
	private String 	dz6	;
	private String 	dz7	;
	private String 	dz8	;
	private String 	dz9	;
	private String 	dz10	;
	private Integer 	zsjhs	;
	private String 	xfzy	;
	private String 	zyxztj	;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJhid() {
		return jhid;
	}
	public void setJhid(String jhid) {
		this.jhid = jhid;
	}
	public String getNf() {
		return nf;
	}
	public void setNf(String nf) {
		this.nf = nf;
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
	public String getZszydm() {
		return zszydm;
	}
	public void setZszydm(String zszydm) {
		this.zszydm = zszydm;
	}
	public String getZszymc() {
		return zszymc;
	}
	public void setZszymc(String zszymc) {
		this.zszymc = zszymc;
	}
	public String getZylbdm() {
		return zylbdm;
	}
	public void setZylbdm(String zylbdm) {
		this.zylbdm = zylbdm;
	}
	public String getZylbmc() {
		return zylbmc;
	}
	public void setZylbmc(String zylbmc) {
		this.zylbmc = zylbmc;
	}
	public String getBhzy() {
		return bhzy;
	}
	public void setBhzy(String bhzy) {
		this.bhzy = bhzy;
	}
	public String getBhzygs() {
		return bhzygs;
	}
	public void setBhzygs(String bhzygs) {
		this.bhzygs = bhzygs;
	}
	public String getCcdm() {
		return ccdm;
	}
	public void setCcdm(String ccdm) {
		this.ccdm = ccdm;
	}
	public String getCcmc() {
		return ccmc;
	}
	public void setCcmc(String ccmc) {
		this.ccmc = ccmc;
	}
	public String getSbzydh() {
		return sbzydh;
	}
	public void setSbzydh(String sbzydh) {
		this.sbzydh = sbzydh;
	}
	public String getXbzydh() {
		return xbzydh;
	}
	public void setXbzydh(String xbzydh) {
		this.xbzydh = xbzydh;
	}
	public String getZkfx() {
		return zkfx;
	}
	public void setZkfx(String zkfx) {
		this.zkfx = zkfx;
	}
	public String getXzdm() {
		return xzdm;
	}
	public void setXzdm(String xzdm) {
		this.xzdm = xzdm;
	}
	public String getXzmc() {
		return xzmc;
	}
	public void setXzmc(String xzmc) {
		this.xzmc = xzmc;
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
	public String getYxbmdm() {
		return yxbmdm;
	}
	public void setYxbmdm(String yxbmdm) {
		this.yxbmdm = yxbmdm;
	}
	public String getYxbmmc() {
		return yxbmmc;
	}
	public void setYxbmmc(String yxbmmc) {
		this.yxbmmc = yxbmmc;
	}
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
	public String getSyssdm() {
		return syssdm;
	}
	public void setSyssdm(String syssdm) {
		this.syssdm = syssdm;
	}
	public String getSyssmc() {
		return syssmc;
	}
	public void setSyssmc(String syssmc) {
		this.syssmc = syssmc;
	}
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
	public String getSkyq() {
		return skyq;
	}
	public void setSkyq(String skyq) {
		this.skyq = skyq;
	}
	public String getZybz() {
		return zybz;
	}
	public void setZybz(String zybz) {
		this.zybz = zybz;
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
	public String getDz7() {
		return dz7;
	}
	public void setDz7(String dz7) {
		this.dz7 = dz7;
	}
	public String getDz8() {
		return dz8;
	}
	public void setDz8(String dz8) {
		this.dz8 = dz8;
	}
	public String getDz9() {
		return dz9;
	}
	public void setDz9(String dz9) {
		this.dz9 = dz9;
	}
	public String getDz10() {
		return dz10;
	}
	public void setDz10(String dz10) {
		this.dz10 = dz10;
	}
	public Integer getZsjhs() {
		return zsjhs;
	}
	public void setZsjhs(Integer zsjhs) {
		this.zsjhs = zsjhs;
	}
	public String getXfzy() {
		return xfzy;
	}
	public void setXfzy(String xfzy) {
		this.xfzy = xfzy;
	}
	public String getZyxztj() {
		return zyxztj;
	}
	public void setZyxztj(String zyxztj) {
		this.zyxztj = zyxztj;
	}
	
			
	
	
}
