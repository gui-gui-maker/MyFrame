package com.lsts.advicenote.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 通知书管理
 * AdviceNote entity. 
 * @author GaoYa
 * @date 2014-03-24 15:20:00
 */
@Entity
@Table(name = "TZSB_ADVICENOTE")
public class AdviceNote implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	//流水号
	private String advicenote_sn;	// 通知书编号
	private String advicenote_name;	// 通知书名称
	private String advicenote_type;	// 通知书类型
	private String advicenote_content;	// 通知书正文（doc文档，保存的是附件id）
	private String enter_user_id;	// 录入人ID
	private String enter_user_name;	// 录入人姓名
	private Date enter_date;	// 录入时间
	private String need_check;	// 是否需要审核（0：不需要 1：需要）
	private String check_user_id;	// 审核人ID
	private String check_user_name;	// 审核人姓名
	private String check_result;	// 审核结果（0：未审核 1：通过 2：未通过）
	private String check_content;	// 审核意见
	private Date check_date;	// 审核时间

	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}
	
	@Column(name="ADVICENOTE_sn")
	public String getAdvicenote_sn() {
		return advicenote_sn;
	}

	public void setAdvicenote_sn(String advicenote_sn) {
		this.advicenote_sn = advicenote_sn;
	}

	@Column(name="ADVICENOTE_NAME")
	public String getAdvicenote_name() {
		return advicenote_name;
	}

	public void setAdvicenote_name(String advicenote_name) {
		this.advicenote_name = advicenote_name;
	}

	@Column(name="ADVICENOTE_TYPE")
	public String getAdvicenote_type() {
		return advicenote_type;
	}

	public void setAdvicenote_type(String advicenote_type) {
		this.advicenote_type = advicenote_type;
	}

	@Column(name="ADVICENOTE_CONTENT")
	public String getAdvicenote_content() {
		return advicenote_content;
	}

	public void setAdvicenote_content(String advicenote_content) {
		this.advicenote_content = advicenote_content;
	}

	@Column(name="ENTER_USER_ID")
	public String getEnter_user_id() {
		return enter_user_id;
	}

	public void setEnter_user_id(String enter_user_id) {
		this.enter_user_id = enter_user_id;
	}

	@Column(name="ENTER_USER_NAME")
	public String getEnter_user_name() {
		return enter_user_name;
	}

	public void setEnter_user_name(String enter_user_name) {
		this.enter_user_name = enter_user_name;
	}

	@Column(name="ENTER_DATE")
	public Date getEnter_date() {
		return enter_date;
	}

	public void setEnter_date(Date enter_date) {
		this.enter_date = enter_date;
	}

	@Column(name="NEED_CHECK")
	public String getNeed_check() {
		return need_check;
	}

	public void setNeed_check(String need_check) {
		this.need_check = need_check;
	}

	@Column(name="CHECK_USER_ID")
	public String getCheck_user_id() {
		return check_user_id;
	}

	public void setCheck_user_id(String check_user_id) {
		this.check_user_id = check_user_id;
	}

	@Column(name="CHECK_USER_NAME")
	public String getCheck_user_name() {
		return check_user_name;
	}

	public void setCheck_user_name(String check_user_name) {
		this.check_user_name = check_user_name;
	}

	@Column(name="CHECK_RESULT")
	public String getCheck_result() {
		return check_result;
	}

	public void setCheck_result(String check_result) {
		this.check_result = check_result;
	}

	@Column(name="CHECK_DATE")
	public Date getCheck_date() {
		return check_date;
	}

	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}

	@Column(name="CHECK_CONTENT")
	public String getCheck_content() {
		return check_content;
	}

	public void setCheck_content(String check_content) {
		this.check_content = check_content;
	}
}
