package com.scts.machine.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 智能一体机系统操作日志
 * MachineLog entity. 
 * @author GaoYa
 * @date 2018-05-18 09:01:00
 */
@Entity
@Table(name = "machine_log")
public class MachineLog implements BaseEntity{

	private static final long serialVersionUID = 1L;
	private String id;	// 流水号
	private String business_id;	// 业务编号
	private String op_user_id;		// 操作用户编号
	private String op_user_name;	// 操作用户姓名
	private Date   op_time;			// 操作时间
	private String phone_num;		// 操作人联系电话
	private String com_name;		// 操作人单位名称
	private String op_action;		// 操作动作
	private String op_remark;		// 操作描述
	private String op_ip;			// 操作IP地址
	private String machine_num;		// 操作终端编号
	private String flow_status;		// 业务流程提交状态（0：待提交 1：已提交）
	private Date   flow_sub_time;	// 业务流程提交时间
	
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
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getMachine_num() {
		return machine_num;
	}
	public void setMachine_num(String machine_num) {
		this.machine_num = machine_num;
	}
	public String getFlow_status() {
		return flow_status;
	}
	public void setFlow_status(String flow_status) {
		this.flow_status = flow_status;
	}
	public Date getFlow_sub_time() {
		return flow_sub_time;
	}
	public void setFlow_sub_time(Date flow_sub_time) {
		this.flow_sub_time = flow_sub_time;
	}
}
