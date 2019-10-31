package com.lsts.humanresources.bean;

import java.util.Date;

/**
 * 微信全院概况数据传输对象 
 * RsSummaryDTO
 * @author Quinc
 * @date 2016-09-06 11:12:58
 */
public class RsSummaryDTO {

	// Fields
	private String total;
	private String man;
	private String woman;
	private String regular;
	private String phd;
	private String postgraduate;
	private String undergraduate;
	private String member_CPC;
	private String member_CYL;
	private String senior_engineer;
	private String p_senior_engineer;
	private String enginer;
	private String examiner;
	private String pressure_examiner;
	private String ele_examiner;
	private String non_destructive;
	private String second_class;

	// Constructors

	/** default constructor */
	public RsSummaryDTO() {
	}

	/** full constructor */
	public RsSummaryDTO(String total, String man, String woman, String regular, String phd, String postgraduate,
			String undergraduate, String member_CPC, String member_CYL, String senior_engineer,
			String p_senior_engineer, String enginer, String examiner, String pressure_examiner, String ele_examiner,
			String non_destructive, String second_class) {
		super();
		this.total = total;
		this.man = man;
		this.woman = woman;
		this.regular = regular;
		this.phd = phd;
		this.postgraduate = postgraduate;
		this.undergraduate = undergraduate;
		this.member_CPC = member_CPC;
		this.member_CYL = member_CYL;
		this.senior_engineer = senior_engineer;
		this.p_senior_engineer = p_senior_engineer;
		this.enginer = enginer;
		this.examiner = examiner;
		this.pressure_examiner = pressure_examiner;
		this.ele_examiner = ele_examiner;
		this.non_destructive = non_destructive;
		this.second_class = second_class;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getMan() {
		return man;
	}

	public void setMan(String man) {
		this.man = man;
	}

	public String getWoman() {
		return woman;
	}

	public void setWoman(String woman) {
		this.woman = woman;
	}

	public String getRegular() {
		return regular;
	}

	public void setRegular(String regular) {
		this.regular = regular;
	}

	public String getPhd() {
		return phd;
	}

	public void setPhd(String phd) {
		this.phd = phd;
	}

	public String getPostgraduate() {
		return postgraduate;
	}

	public void setPostgraduate(String postgraduate) {
		this.postgraduate = postgraduate;
	}

	public String getUndergraduate() {
		return undergraduate;
	}

	public void setUndergraduate(String undergraduate) {
		this.undergraduate = undergraduate;
	}

	public String getMember_CPC() {
		return member_CPC;
	}

	public void setMember_CPC(String member_CPC) {
		this.member_CPC = member_CPC;
	}

	public String getMember_CYL() {
		return member_CYL;
	}

	public void setMember_CYL(String member_CYL) {
		this.member_CYL = member_CYL;
	}

	public String getSenior_engineer() {
		return senior_engineer;
	}

	public void setSenior_engineer(String senior_engineer) {
		this.senior_engineer = senior_engineer;
	}

	public String getP_senior_engineer() {
		return p_senior_engineer;
	}

	public void setP_senior_engineer(String p_senior_engineer) {
		this.p_senior_engineer = p_senior_engineer;
	}

	public String getEnginer() {
		return enginer;
	}

	public void setEnginer(String enginer) {
		this.enginer = enginer;
	}

	public String getExaminer() {
		return examiner;
	}

	public void setExaminer(String examiner) {
		this.examiner = examiner;
	}

	public String getPressure_examiner() {
		return pressure_examiner;
	}

	public void setPressure_examiner(String pressure_examiner) {
		this.pressure_examiner = pressure_examiner;
	}

	public String getEle_examiner() {
		return ele_examiner;
	}

	public void setEle_examiner(String ele_examiner) {
		this.ele_examiner = ele_examiner;
	}

	public String getNon_destructive() {
		return non_destructive;
	}

	public void setNon_destructive(String non_destructive) {
		this.non_destructive = non_destructive;
	}

	public String getSecond_class() {
		return second_class;
	}

	public void setSecond_class(String second_class) {
		this.second_class = second_class;
	}
	
	

}