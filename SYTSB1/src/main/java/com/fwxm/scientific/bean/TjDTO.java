package com.fwxm.scientific.bean;

import java.math.BigDecimal;



/**
 * 持证、科研项目统计数据集
 * 
 * @author dxf
 *
 * @date 2015-12-29
 */
public class TjDTO {

	private String userName;	// 人员姓名
	private String userId;	// 人员id
	private String depName;	// 所属部门
	private String depId;	// 所属部门id
	private String xmmc;//项目名称
	private Double fybx;//费用报销
	private Double clf;//差旅费
	private Double money;//金额
	private Double lwts;//论文条数
	private Double zlgs;//专利个数
	private String zsxm;//证书项目
	private String zsxz;//证书性质
	private Double number;//数量
    
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getLwts() {
		return lwts;
	}
	public void setLwts(Double lwts) {
		this.lwts = lwts;
	}
	public Double getZlgs() {
		return zlgs;
	}
	public void setZlgs(Double zlgs) {
		this.zlgs = zlgs;
	}
	public String getZsxm() {
		return zsxm;
	}
	public void setZsxm(String zsxm) {
		this.zsxm = zsxm;
	}
	public String getZsxz() {
		return zsxz;
	}
	public void setZsxz(String zsxz) {
		this.zsxz = zsxz;
	}
	public Double getNumber() {
		return number;
	}
	public void setNumber(Double number) {
		this.number = number;
	}
	public Double getFybx() {
		return fybx;
	}
	public void setFybx(Double fybx) {
		this.fybx = fybx;
	}
	public Double getClf() {
		return clf;
	}
	public void setClf(Double clf) {
		this.clf = clf;
	}
	
	
	
	
}
