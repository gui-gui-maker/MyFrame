package com.lsts.inspection.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name="TZSB_DRAW_LOG")
public class Drawlog implements BaseEntity {

	private String id; // 流水号
	private String business_id; // 业务编号
	private String op_user_id; // 操作用户编号
	private String op_user_name; // 操作用户姓名
	private Date op_time; // 操作时间
	private String error_msg; // 错误信息

	private String op_ip; // 操作IP地址
	private String op_status; // 操作状态1正常10退回11撤回99作废
	private String activity_id; // 流程id
	private String activity_name; // 流程name

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

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
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

	public String getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(String activity_id) {
		this.activity_id = activity_id;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

}
