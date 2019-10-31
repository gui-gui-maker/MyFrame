package com.neo.model;

import java.io.Serializable;

/**
 * 院校代号
 * @author Guido
 *
 */
public class Yxdh implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String yxdh;
	private String yxdm;
	private String yxmc;
	private String yxdhmc;
	private String bz;
	
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
	public String getYxdhmc() {
		return yxdhmc;
	}
	public void setYxdhmc(String yxdhmc) {
		this.yxdhmc = yxdhmc;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	
}
