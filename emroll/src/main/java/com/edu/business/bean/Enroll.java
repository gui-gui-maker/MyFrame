package com.edu.business.bean;


import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ZYDH")
public class Enroll {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String bbjhid;
    private String nf        ;
    private String yxdm      ;
    private String yxmc      ;
    private String yxdh      ;
    private String yxdhmc    ;
    private String zszydm    ;
    private String zszymc    ;
    private String zylbdm    ;
    private String zylbmc    ;
    //包含专业
    private String bhzy      ;
    //包含专业2
    private String bhzygs    ;
    private String ccdm      ;
    private String ccmc      ;
    private String sbzydh    ;
    private String xbzydh    ;
    private String zkfx      ;
    private String xzdm      ;
    private String xzmc      ;
    private String sfbz      ;
    private String bxdd      ;
    private String bxddssmc  ;
    private String bxdddjsmc ;
    private String bxddqxmc  ;
    private String bxddxxdz  ;
    private String bxddbb    ;
    private String sfks      ;
    private String wyyz      ;
    private String yxbmdm    ;
    private String yxbmmc    ;
    private String kldm      ;
    private String klmc      ;
    private String jhxzdm    ;
    private String jhxzmc    ;
    private String jhlbdm    ;
    private String jhlbmc    ;
    private String syssdm    ;
    private String syssmc    ;
    private String pcdm      ;
    private String pcmc     ;
    private String zklxdm    ;
    private String zklxmc    ;
    private String kskmyq    ;
    private String kskmyqzw  ;
    private String xkkmbhzy  ;
    private String skyq      ;
    private String zybz      ;
    private String dz1       ;
    private String dz2       ;
    private String dz3       ;
    private String dz4       ;
    private String dz5       ;
    private String dz6       ;
    private String dz7       ;
    private String dz8       ;
    private String dz9       ;
    private String dz10      ;
    private String zsjhs     ;
    private String xfzy      ;
    private String zyxztj    ;
    private String sfdy      ;
    private String dybz      ;
    private String dyml      ;
    private String zjhs      ;
    private String yxbz      ;
    private String yxdz      ;
    private String sddm      ;
    private String zgdm      ;
    private String zgmc      ;
    private String yxjblxmc  ;
    private String ytlb      ;
    private String ytlbmc    ;
    private String xbyq      ;
    private String hxlb      ;
    private String err       ;
    //是否被删除
    private String isDel ;
    private Date lastUpdateTime ;
    private String lastUpdateBy ;

    public String getId() {
        return id;
    }

    public String getBbjhid() {
        return bbjhid;
    }

    public String getNf() {
        return nf;
    }

    public String getYxdm() {
        return yxdm;
    }

    public String getYxmc() {
        return yxmc;
    }

    public String getYxdh() {
        return yxdh;
    }

    public String getYxdhmc() {
        return yxdhmc;
    }

    public String getZszydm() {
        return zszydm;
    }

    public String getZszymc() {
        return zszymc;
    }

    public String getZylbdm() {
        return zylbdm;
    }

    public String getZylbmc() {
        return zylbmc;
    }

    public String getBhzy() {
        return bhzy;
    }

    public String getBhzygs() {
        return bhzygs;
    }

    public String getCcdm() {
        return ccdm;
    }

    public String getCcmc() {
        return ccmc;
    }

    public String getSbzydh() {
        return sbzydh;
    }

    public String getXbzydh() {
        return xbzydh;
    }

    public String getZkfx() {
        return zkfx;
    }

    public String getXzdm() {
        return xzdm;
    }

    public String getXzmc() {
        return xzmc;
    }

    public String getSfbz() {
        return sfbz;
    }

    public String getBxdd() {
        return bxdd;
    }

    public String getBxddssmc() {
        return bxddssmc;
    }

    public String getBxdddjsmc() {
        return bxdddjsmc;
    }

    public String getBxddqxmc() {
        return bxddqxmc;
    }

    public String getBxddxxdz() {
        return bxddxxdz;
    }

    public String getBxddbb() {
        return bxddbb;
    }

    public String getSfks() {
        return sfks;
    }

    public String getWyyz() {
        return wyyz;
    }

    public String getYxbmdm() {
        return yxbmdm;
    }

    public String getYxbmmc() {
        return yxbmmc;
    }

    public String getKldm() {
        return kldm;
    }

    public String getKlmc() {
        return klmc;
    }

    public String getJhxzdm() {
        return jhxzdm;
    }

    public String getJhxzmc() {
        return jhxzmc;
    }

    public String getJhlbdm() {
        return jhlbdm;
    }

    public String getJhlbmc() {
        return jhlbmc;
    }

    public String getSyssdm() {
        return syssdm;
    }

    public String getSyssmc() {
        return syssmc;
    }

    public String getPcdm() {
        return pcdm;
    }

    public String getPcmc() {
        return pcmc;
    }

    public String getZklxdm() {
        return zklxdm;
    }

    public String getZklxmc() {
        return zklxmc;
    }

    public String getKskmyq() {
        return kskmyq;
    }

    public String getKskmyqzw() {
        return kskmyqzw;
    }

    public String getXkkmbhzy() {
        return xkkmbhzy;
    }

    public String getSkyq() {
        return skyq;
    }

    public String getZybz() {
        return zybz;
    }

    public String getDz1() {
        return dz1;
    }

    public String getDz2() {
        return dz2;
    }

    public String getDz3() {
        return dz3;
    }

    public String getDz4() {
        return dz4;
    }

    public String getDz5() {
        return dz5;
    }

    public String getDz6() {
        return dz6;
    }

    public String getDz7() {
        return dz7;
    }

    public String getDz8() {
        return dz8;
    }

    public String getDz9() {
        return dz9;
    }

    public String getDz10() {
        return dz10;
    }

    public String getZsjhs() {
        return zsjhs;
    }

    public String getXfzy() {
        return xfzy;
    }

    public String getZyxztj() {
        return zyxztj;
    }

    public String getSfdy() {
        return sfdy;
    }

    public String getDybz() {
        return dybz;
    }

    public String getDyml() {
        return dyml;
    }

    public String getZjhs() {
        return zjhs;
    }

    public String getYxbz() {
        return yxbz;
    }

    public String getYxdz() {
        return yxdz;
    }

    public String getSddm() {
        return sddm;
    }

    public String getZgdm() {
        return zgdm;
    }

    public String getZgmc() {
        return zgmc;
    }

    public String getYxjblxmc() {
        return yxjblxmc;
    }

    public String getYtlb() {
        return ytlb;
    }

    public String getYtlbmc() {
        return ytlbmc;
    }

    public String getXbyq() {
        return xbyq;
    }

    public String getHxlb() {
        return hxlb;
    }

    public String getErr() {
        return err;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBbjhid(String bbjhid) {
        this.bbjhid = bbjhid;
    }

    public void setNf(String nf) {
        this.nf = nf;
    }

    public void setYxdm(String yxdm) {
        this.yxdm = yxdm;
    }

    public void setYxmc(String yxmc) {
        this.yxmc = yxmc;
    }

    public void setYxdh(String yxdh) {
        this.yxdh = yxdh;
    }

    public void setYxdhmc(String yxdhmc) {
        this.yxdhmc = yxdhmc;
    }

    public void setZszydm(String zszydm) {
        this.zszydm = zszydm;
    }

    public void setZszymc(String zszymc) {
        this.zszymc = zszymc;
    }

    public void setZylbdm(String zylbdm) {
        this.zylbdm = zylbdm;
    }

    public void setZylbmc(String zylbmc) {
        this.zylbmc = zylbmc;
    }

    public void setBhzy(String bhzy) {
        this.bhzy = bhzy;
    }

    public void setBhzygs(String bhzygs) {
        this.bhzygs = bhzygs;
    }

    public void setCcdm(String ccdm) {
        this.ccdm = ccdm;
    }

    public void setCcmc(String ccmc) {
        this.ccmc = ccmc;
    }

    public void setSbzydh(String sbzydh) {
        this.sbzydh = sbzydh;
    }

    public void setXbzydh(String xbzydh) {
        this.xbzydh = xbzydh;
    }

    public void setZkfx(String zkfx) {
        this.zkfx = zkfx;
    }

    public void setXzdm(String xzdm) {
        this.xzdm = xzdm;
    }

    public void setXzmc(String xzmc) {
        this.xzmc = xzmc;
    }

    public void setSfbz(String sfbz) {
        this.sfbz = sfbz;
    }

    public void setBxdd(String bxdd) {
        this.bxdd = bxdd;
    }

    public void setBxddssmc(String bxddssmc) {
        this.bxddssmc = bxddssmc;
    }

    public void setBxdddjsmc(String bxdddjsmc) {
        this.bxdddjsmc = bxdddjsmc;
    }

    public void setBxddqxmc(String bxddqxmc) {
        this.bxddqxmc = bxddqxmc;
    }

    public void setBxddxxdz(String bxddxxdz) {
        this.bxddxxdz = bxddxxdz;
    }

    public void setBxddbb(String bxddbb) {
        this.bxddbb = bxddbb;
    }

    public void setSfks(String sfks) {
        this.sfks = sfks;
    }

    public void setWyyz(String wyyz) {
        this.wyyz = wyyz;
    }

    public void setYxbmdm(String yxbmdm) {
        this.yxbmdm = yxbmdm;
    }

    public void setYxbmmc(String yxbmmc) {
        this.yxbmmc = yxbmmc;
    }

    public void setKldm(String kldm) {
        this.kldm = kldm;
    }

    public void setKlmc(String klmc) {
        this.klmc = klmc;
    }

    public void setJhxzdm(String jhxzdm) {
        this.jhxzdm = jhxzdm;
    }

    public void setJhxzmc(String jhxzmc) {
        this.jhxzmc = jhxzmc;
    }

    public void setJhlbdm(String jhlbdm) {
        this.jhlbdm = jhlbdm;
    }

    public void setJhlbmc(String jhlbmc) {
        this.jhlbmc = jhlbmc;
    }

    public void setSyssdm(String syssdm) {
        this.syssdm = syssdm;
    }

    public void setSyssmc(String syssmc) {
        this.syssmc = syssmc;
    }

    public void setPcdm(String pcdm) {
        this.pcdm = pcdm;
    }

    public void setPcmc(String pcmc) {
        this.pcmc = pcmc;
    }

    public void setZklxdm(String zklxdm) {
        this.zklxdm = zklxdm;
    }

    public void setZklxmc(String zklxmc) {
        this.zklxmc = zklxmc;
    }

    public void setKskmyq(String kskmyq) {
        this.kskmyq = kskmyq;
    }

    public void setKskmyqzw(String kskmyqzw) {
        this.kskmyqzw = kskmyqzw;
    }

    public void setXkkmbhzy(String xkkmbhzy) {
        this.xkkmbhzy = xkkmbhzy;
    }

    public void setSkyq(String skyq) {
        this.skyq = skyq;
    }

    public void setZybz(String zybz) {
        this.zybz = zybz;
    }

    public void setDz1(String dz1) {
        this.dz1 = dz1;
    }

    public void setDz2(String dz2) {
        this.dz2 = dz2;
    }

    public void setDz3(String dz3) {
        this.dz3 = dz3;
    }

    public void setDz4(String dz4) {
        this.dz4 = dz4;
    }

    public void setDz5(String dz5) {
        this.dz5 = dz5;
    }

    public void setDz6(String dz6) {
        this.dz6 = dz6;
    }

    public void setDz7(String dz7) {
        this.dz7 = dz7;
    }

    public void setDz8(String dz8) {
        this.dz8 = dz8;
    }

    public void setDz9(String dz9) {
        this.dz9 = dz9;
    }

    public void setDz10(String dz10) {
        this.dz10 = dz10;
    }

    public void setZsjhs(String zsjhs) {
        this.zsjhs = zsjhs;
    }

    public void setXfzy(String xfzy) {
        this.xfzy = xfzy;
    }

    public void setZyxztj(String zyxztj) {
        this.zyxztj = zyxztj;
    }

    public void setSfdy(String sfdy) {
        this.sfdy = sfdy;
    }

    public void setDybz(String dybz) {
        this.dybz = dybz;
    }

    public void setDyml(String dyml) {
        this.dyml = dyml;
    }

    public void setZjhs(String zjhs) {
        this.zjhs = zjhs;
    }

    public void setYxbz(String yxbz) {
        this.yxbz = yxbz;
    }

    public void setYxdz(String yxdz) {
        this.yxdz = yxdz;
    }

    public void setSddm(String sddm) {
        this.sddm = sddm;
    }

    public void setZgdm(String zgdm) {
        this.zgdm = zgdm;
    }

    public void setZgmc(String zgmc) {
        this.zgmc = zgmc;
    }

    public void setYxjblxmc(String yxjblxmc) {
        this.yxjblxmc = yxjblxmc;
    }

    public void setYtlb(String ytlb) {
        this.ytlb = ytlb;
    }

    public void setYtlbmc(String ytlbmc) {
        this.ytlbmc = ytlbmc;
    }

    public void setXbyq(String xbyq) {
        this.xbyq = xbyq;
    }

    public void setHxlb(String hxlb) {
        this.hxlb = hxlb;
    }

    public void setErr(String err) {
        this.err = err;
    }

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}
    
}
