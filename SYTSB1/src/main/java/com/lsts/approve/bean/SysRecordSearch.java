package com.lsts.approve.bean;

/**
 * 记录操作人员的对数据产生影响的操作
 * @author Guido
 *
 */

public class SysRecordSearch {
	private String id;
	private String account;//操作人
	private String op_time1;//操作时间
	private String op_time2;//操作时间
	private String action;//动作类型（add,update,delete）
	private String bean_name;//java类名
	private String table_name;//表名称
	private String business_id;//表记录id
	private String old_record;//旧表记录（json字符串）
	private String new_record;//新表记录（json字符串）
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getBean_name() {
		return bean_name;
	}
	public void setBean_name(String bean_name) {
		this.bean_name = bean_name;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getBusiness_id() {
		return business_id;
	}
	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getOld_record() {
		return old_record;
	}
	public void setOld_record(String old_record) {
		this.old_record = old_record;
	}
	public String getNew_record() {
		return new_record;
	}
	public void setNew_record(String new_record) {
		this.new_record = new_record;
	}
	public String getOp_time1() {
		return op_time1;
	}
	public void setOp_time1(String op_time1) {
		this.op_time1 = op_time1;
	}
	public String getOp_time2() {
		return op_time2;
	}
	public void setOp_time2(String op_time2) {
		this.op_time2 = op_time2;
	}
	

	

}
