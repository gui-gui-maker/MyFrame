package com.scts.weixin.app.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 微信应用基础信息
 * WeixinAppInfo entity. 
 * @author GaoYa
 * @date 2018-08-20 上午09:37:00
 */
@Entity
@Table(name = "WEIXIN_APP_INFO")
public class WeixinAppInfo implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;					// ID
	private String app_sn;				// 微信应用序号
	private String app_code;			// 微信应用编号
	private String app_name;			// 微信应用名称
	private String app_index_req;		// 首页请求地址
	private String app_index_url;		// 首页响应地址
	private String app_deal_url;		// 业务处理响应地址
	private String func_name;			// 功能模块名称
	private String remarks;				// 备注

	private String create_user_id;		// 创建用户ID
	private String create_user_name;	// 创建用户姓名
	private Date   create_date;			// 创建日期
	private String mdy_user_id;			// 最后修改人ID
	private String mdy_user_name;		// 最后修改人姓名
	private Date   mdy_date;			// 最后修改日期
		
	private String data_status;			// 数据状态（0：启用 98：停用 99：已作废）



	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public String getMdy_user_id() {
		return mdy_user_id;
	}

	public void setMdy_user_id(String mdy_user_id) {
		this.mdy_user_id = mdy_user_id;
	}

	public String getMdy_user_name() {
		return mdy_user_name;
	}

	public void setMdy_user_name(String mdy_user_name) {
		this.mdy_user_name = mdy_user_name;
	}

	public Date getMdy_date() {
		return mdy_date;
	}

	public void setMdy_date(Date mdy_date) {
		this.mdy_date = mdy_date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getData_status() {
		return data_status;
	}

	public void setData_status(String data_status) {
		this.data_status = data_status;
	}

	public String getApp_sn() {
		return app_sn;
	}

	public void setApp_sn(String app_sn) {
		this.app_sn = app_sn;
	}

	public String getApp_code() {
		return app_code;
	}

	public void setApp_code(String app_code) {
		this.app_code = app_code;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public String getApp_index_req() {
		return app_index_req;
	}

	public void setApp_index_req(String app_index_req) {
		this.app_index_req = app_index_req;
	}

	public String getApp_index_url() {
		return app_index_url;
	}

	public void setApp_index_url(String app_index_url) {
		this.app_index_url = app_index_url;
	}

	public String getFunc_name() {
		return func_name;
	}

	public void setFunc_name(String func_name) {
		this.func_name = func_name;
	}

	public String getCreate_user_id() {
		return create_user_id;
	}

	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}

	public String getCreate_user_name() {
		return create_user_name;
	}

	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getApp_deal_url() {
		return app_deal_url;
	}

	public void setApp_deal_url(String app_deal_url) {
		this.app_deal_url = app_deal_url;
	}
	
}
