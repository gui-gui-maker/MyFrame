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
 * 短信信箱
 *  entity. 
 * @author GaoYa
 * @date 2014-03-24 15:20:00
 */
@Entity
@Table(name = "MESSAGE_STEP")
public class MessageInfo implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	//流水号
	private String step_no;	// 通知书编号
	public String getStep_no() {
		return step_no;
	}

	public void setStep_no(String step_no) {
		this.step_no = step_no;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getBy_op() {
		return by_op;
	}

	public void setBy_op(String by_op) {
		this.by_op = by_op;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	private String info;	// 通知书名称
	private String flag;	// 通知书类型
	private String by_op;	// 通知书正文（doc文档，保存的是附件id）
	private Date time;	// 录入人姓名
	

	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}


	
	
	
}
