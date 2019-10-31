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
 * 通知公告
 *  entity. 
 * @author GaoYa
 * @date 2014-03-24 15:20:00
 */
@Entity
@Table(name = "TSJY_AFFICHE")
public class Affiche implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	//流水号
	private String affiche_title;	// 通知书编号
	private String affiche_content;	// 通知书名称
	private String flag;	// 通知书类型
	private String affiche_status;	// 通知书正文（doc文档，保存的是附件id）
	private String created_by;	// 录入人姓名
	private Date userful_time;	// 录入时间
	private Date created_date;	// 审核时间

	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public String getAffiche_title() {
		return affiche_title;
	}

	public void setAffiche_title(String affiche_title) {
		this.affiche_title = affiche_title;
	}

	public String getAffiche_content() {
		return affiche_content;
	}

	public void setAffiche_content(String affiche_content) {
		this.affiche_content = affiche_content;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getAffiche_status() {
		return affiche_status;
	}

	public void setAffiche_status(String affiche_status) {
		this.affiche_status = affiche_status;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getUserful_time() {
		return userful_time;
	}

	public void setUserful_time(Date userful_time) {
		this.userful_time = userful_time;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	
	
	
}
