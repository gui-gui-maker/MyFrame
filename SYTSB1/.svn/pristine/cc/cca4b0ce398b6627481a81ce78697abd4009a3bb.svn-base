package com.lsts.approve.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;
/**
 * 记录操作人员的对数据产生影响的操作
 * @author Guido
 *
 */
@Entity
@Table(name = "sys_record")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysRecord implements BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String account;//操作人
	private String ip_address;//IP地址
	private Date op_time;//操作时间
	private String action;//动作类型（add,update,delete）
	private String bean_name;//java类名
	private String table_name;//表名称
	private String business_id;//表记录id
	private String old_record;//旧表记录（json字符串）
	private String new_record;//新表记录（json字符串）
	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
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
	public Date getOp_time() {
		return op_time;
	}
	public void setOp_time(Date op_time) {
		this.op_time = op_time;
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
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	

}
