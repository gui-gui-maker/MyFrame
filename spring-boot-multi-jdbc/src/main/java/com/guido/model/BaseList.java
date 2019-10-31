package com.guido.model;

public class BaseList {
	
    private String    code;
	private String 	  name   	;
	private String 	  sflb  	;
	private String 	  msql   	;
	private String 	  cc     	;
	private String 	  pc     	;
	private String 	  pcode  	;
	private String 	  llevel 	;
	private String 	  kl     	;
	private String 	  jhxz   	;
	private String 	  jhlb   	;
	private String 	  zylb   	;
	private String 	  zklx  	;
	private String 	  fkey  	;
	
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSflb() {
		return sflb;
	}
	public void setSflb(String sflb) {
		this.sflb = sflb;
	}
	public String getMsql() {
		return msql;
	}
	public void setMsql(String msql) {
		this.msql = msql;
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
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getLlevel() {
		return llevel;
	}
	public void setLlevel(String llevel) {
		this.llevel = llevel;
	}
	public String getKl() {
		return kl;
	}
	public void setKl(String kl) {
		this.kl = kl;
	}
	public String getJhxz() {
		return jhxz;
	}
	public void setJhxz(String jhxz) {
		this.jhxz = jhxz;
	}
	public String getJhlb() {
		return jhlb;
	}
	public void setJhlb(String jhlb) {
		this.jhlb = jhlb;
	}
	public String getZylb() {
		return zylb;
	}
	public void setZylb(String zylb) {
		this.zylb = zylb;
	}
	public String getZklx() {
		return zklx;
	}
	public void setZklx(String zklx) {
		this.zklx = zklx;
	}
	
	public String getFkey() {
		return fkey;
	}
	public void setFkey(String fkey) {
		this.fkey = fkey;
	}
	@Override
	public String toString() {
		return "BaseList [code=" + code + ", name=" + name + ", sflb=" + sflb + ", msql=" + msql + ", cc=" + cc
				+ ", pc=" + pc + ", pcode=" + pcode + ", llevel=" + llevel + ", kl=" + kl + ", jhxz=" + jhxz + ", jhlb="
				+ jhlb + ", zylb=" + zylb + ", zklx=" + zklx + "]";
	}
	
	

}
