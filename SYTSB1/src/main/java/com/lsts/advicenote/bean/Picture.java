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
@Table(name = "TSJY_PICTURE")
public class Picture implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	//流水号
	private String pic_title;	// 通知书编号

	private String flag;	// 通知书类型
	private String pic_status;	// 通知书正文（doc文档，保存的是附件id）
	private String created_by;	// 录入人姓名
	private Date created_date;	// 审核时间
	private String pic_id;	// 审核时间

	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public String getPic_title() {
		return pic_title;
	}

	public void setPic_title(String pic_title) {
		this.pic_title = pic_title;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPic_status() {
		return pic_status;
	}

	public void setPic_status(String pic_status) {
		this.pic_status = pic_status;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public String getPic_id() {
		return pic_id;
	}

	public void setPic_id(String pic_id) {
		this.pic_id = pic_id;
	}



	
	
	
}
