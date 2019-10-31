package com.lsts.log.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 系统电子印章日志
 * SysLog entity. 
 * @author GaoYa
 * @date 2018-03-26 14:01:00
 */
@Entity
@Table(name = "sys_dzyz_log")
public class SysDzyzLog implements BaseEntity{

	private static final long serialVersionUID = 1L;
	private String id;	// 流水号
	private String business_id;	// 业务编号
	private String op_user_id;	// 操作用户编号
	private String op_user_name;	// 操作用户姓名
	private Date   op_time;	// 操作时间
	private String op_action;	// 操作动作
	private String op_remark;	// 操作描述
	private String op_ip;	// 操作IP地址
	
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

	
}
