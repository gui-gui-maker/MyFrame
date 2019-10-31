package com.lsts.mobileapp.input.bean;

import com.khnt.core.crud.bean.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="TZSB_RECORD_LOG")
public class TzsbRecordLog implements BaseEntity {

	private static final long serialVersionUID = 1L;
	private String id;	// 流水号
	private String business_id;	// 业务编号
	private String op_user_id;	// 操作用户编号
	private String op_user_name;	// 操作用户姓名
	private Date   op_time;	// 操作时间
	private String op_action;	// 操作动作
	private String op_remark;	// 操作描述
	private String op_ip;	// 操作IP地址
	private String op_status;	// 操作状态
	private String next_id;	// 下一操作人id
	private String next_name;	//下一操作人
	private String target_id;//被复制对象
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusiness_id() {
		return business_id;
	}
	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}
	public String getOp_user_id() {
		return op_user_id;
	}
	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}
	public String getOp_user_name() {
		return op_user_name;
	}
	public void setOp_user_name(String op_user_name) {
		this.op_user_name = op_user_name;
	}
	public Date getOp_time() {
		return op_time;
	}
	public void setOp_time(Date op_time) {
		this.op_time = op_time;
	}
	public String getOp_action() {
		return op_action;
	}
	public void setOp_action(String op_action) {
		this.op_action = op_action;
	}
	public String getOp_remark() {
		return op_remark;
	}
	public void setOp_remark(String op_remark) {
		this.op_remark = op_remark;
	}
	public String getOp_ip() {
		return op_ip;
	}
	public void setOp_ip(String op_ip) {
		this.op_ip = op_ip;
	}
	public String getOp_status() {
		return op_status;
	}
	public void setOp_status(String op_status) {
		this.op_status = op_status;
	}
	public String getNext_id() {
		return next_id;
	}
	public void setNext_id(String next_id) {
		this.next_id = next_id;
	}
	public String getNext_name() {
		return next_name;
	}
	public void setNext_name(String next_name) {
		this.next_name = next_name;
	}
	public String getTarget_id() {
		return target_id;
	}
	public void setTarget_id(String target_id) {
		this.target_id = target_id;
	}

	
	
}
