package com.lsts.device.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * 罐车參數
 * 
 * @author 肖慈边 2015-11-27
 * 
 */

@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = { "deviceDocument" })
@Table(name = "BASE_DEVICE_BOILER")
public class TankerPara implements BaseEntity{

	
	private static final long serialVersionUID = 1L;
	
    private String id;//id
 
    private String clpzh;  //车辆牌照号
    private String cpys;//车牌颜色
    private String cx;//车型
    private String rllx;//燃料类型
    private String yhmc;//业户名称	
    private String clcp;//车辆厂牌
    private String cpxh;//厂牌型号
    private String clkw;//车辆客位
    private String cldw;//车辆吨位
    private Date ccrq;//出厂日期
    private String yy_dlyszh;//道路运输证号
    private String fdjh;//发动机号
    private String dph;//底盘号
    private String csys;//车身颜色
    private String yy_jyfw;//经营范围



    
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClpzh() {
		return clpzh;
	}

	public void setClpzh(String clpzh) {
		this.clpzh = clpzh;
	}

	public String getCpys() {
		return cpys;
	}

	public void setCpys(String cpys) {
		this.cpys = cpys;
	}

	public String getCx() {
		return cx;
	}

	public void setCx(String cx) {
		this.cx = cx;
	}

	public String getRllx() {
		return rllx;
	}

	public void setRllx(String rllx) {
		this.rllx = rllx;
	}

	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getClcp() {
		return clcp;
	}

	public void setClcp(String clcp) {
		this.clcp = clcp;
	}

	public String getCpxh() {
		return cpxh;
	}

	public void setCpxh(String cpxh) {
		this.cpxh = cpxh;
	}

	public String getClkw() {
		return clkw;
	}

	public void setClkw(String clkw) {
		this.clkw = clkw;
	}

	public String getCldw() {
		return cldw;
	}

	public void setCldw(String cldw) {
		this.cldw = cldw;
	}

	public Date getCcrq() {
		return ccrq;
	}

	public void setCcrq(Date ccrq) {
		this.ccrq = ccrq;
	}

	public String getYy_dlyszh() {
		return yy_dlyszh;
	}

	public void setYy_dlyszh(String yy_dlyszh) {
		this.yy_dlyszh = yy_dlyszh;
	}

	public String getFdjh() {
		return fdjh;
	}

	public void setFdjh(String fdjh) {
		this.fdjh = fdjh;
	}

	public String getDph() {
		return dph;
	}

	public void setDph(String dph) {
		this.dph = dph;
	}

	public String getCsys() {
		return csys;
	}

	public void setCsys(String csys) {
		this.csys = csys;
	}

	public String getYy_jyfw() {
		return yy_jyfw;
	}

	public void setYy_jyfw(String yy_jyfw) {
		this.yy_jyfw = yy_jyfw;
	}

	
	
   

 
   


} 
