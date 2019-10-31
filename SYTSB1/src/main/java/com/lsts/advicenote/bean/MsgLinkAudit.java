package com.lsts.advicenote.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name="TJY2_MSG_LINK_AUDIT")
public class MsgLinkAudit implements BaseEntity {

	private String id;//
	private String business_id;//业务id
	private String business_table;//业务表
	private String sql;//sql语句  备用
	private String datas;//展示数据 用于详情页面
	private String action;//处理调用方法
	private String handle_id;//持有人
	private String handle_op;//持有人
	private String create_op_id;//创建人id
	private String create_op;//创建人
	private String next_op_sql;//提交选择人员sql语句
	private String status;//0 没有处理 1已处理
	private Date create_date;//创建时间
	private Date op_date;//处理时间
	private String title;//模块标题
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public String getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getDatas() {
		return datas;
	}

	public void setDatas(String datas) {
		this.datas = datas;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getHandle_id() {
		return handle_id;
	}

	public void setHandle_id(String handle_id) {
		this.handle_id = handle_id;
	}

	public String getHandle_op() {
		return handle_op;
	}

	public void setHandle_op(String handle_op) {
		this.handle_op = handle_op;
	}

	public String getCreate_op_id() {
		return create_op_id;
	}

	public void setCreate_op_id(String create_op_id) {
		this.create_op_id = create_op_id;
	}

	public String getCreate_op() {
		return create_op;
	}

	public void setCreate_op(String create_op) {
		this.create_op = create_op;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getOp_date() {
		return op_date;
	}

	public void setOp_date(Date op_date) {
		this.op_date = op_date;
	}

	public String getBusiness_table() {
		return business_table;
	}

	public void setBusiness_table(String business_table) {
		this.business_table = business_table;
	}

	public String getNext_op_sql() {
		return next_op_sql;
	}

	public void setNext_op_sql(String next_op_sql) {
		this.next_op_sql = next_op_sql;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
}
