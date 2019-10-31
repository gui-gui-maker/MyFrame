package com.neo.model.ext;

import java.io.Serializable;

public class YxdmOnly implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String yxdh;
	String yxdm;
	String yxmc;
	
	
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "院校代码："+this.yxdm +" 院校名称："+ this.yxmc;
	}
	
	

}
