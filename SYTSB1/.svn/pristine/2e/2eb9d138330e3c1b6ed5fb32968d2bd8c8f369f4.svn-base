package com.lsts.log.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 系统数据修改日志
 * SysDataMdyLog entity. 
 * @author GaoYa
 * @date 2015-10-20 17:26:00
 */
@Entity
@Table(name = "sys_data_mdy_log")
public class SysDataMdyLog implements BaseEntity{

	private static final long serialVersionUID = 1L;
	private String id;	// ID
	private String business_id;	// 业务ID
	private String table_code;	// 修改表名（code）
	private String table_name;	// 修改表名（text）
	private String column_name;	// 修改列明
	private String old_value;	// 原值
	private String new_value;	// 新值
	private String op_user_id;	// 操作用户ID
	private String op_user_name;// 操作用户姓名
	private Date   op_time;		// 操作时间
	private String op_action;	// 操作动作
	private String op_remark;	// 操作描述
	private String op_ip;		// 操作IP地址
	
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
	public String getTable_code() {
		return table_code;
	}
	public void setTable_code(String table_code) {
		this.table_code = table_code;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	public String getOld_value() {
		return old_value;
	}
	public void setOld_value(String old_value) {
		this.old_value = old_value;
	}
	public String getNew_value() {
		return new_value;
	}
	public void setNew_value(String new_value) {
		this.new_value = new_value;
	}
}
