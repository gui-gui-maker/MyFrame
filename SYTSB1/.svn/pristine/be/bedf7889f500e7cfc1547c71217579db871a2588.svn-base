package com.scts.cspace.resource.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * @author BenQ
 * @date 2016-10-21
 * @summary 资源文件
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tjypt_resource_info")
public class TjyptResourceInfo implements BaseEntity {
	private static final long serialVersionUID = 1L;
	private String id; //
	private String fk_file_id; // 资源ID
	private String resource_name; // 资源名称
	private String resource_path; // 资源路径
	private String resource_owner_id; // 资源拥有者
	private String resource_share_user_id; // 资源分析者
	private String resource_share_user; // 资源分析者
	private String resource_share_status; // 资源分享状态
	private String resource_size; // 资源大小
	private String resource_type; // 资源类型
	private String resource_remark; // 资源备注
	private String resource_is_hidden; // 资源隐藏

	private String resource_important_flag; // 资源重要等级
	private String resource_order_no; // 资源排序标识
	private String resource_access_password; // 资源发访问密码
	private Date resource_last_open_date; // 资源最后访问时间
	private String resource_last_open_ip; // 资源最后访问IP
	private Date resource_download_date; // 资源最后下载时间
	private String resource_download_ip; // 资源最后下载IP
	private String resource_is_recyclebin; // 是否在回收站
	private String resource_status; // 资源状态
	private Date resource_last_update_date; // 资源状态
	private Date event_date;//事件发生时间
	private String event_man;//事件参与人
	private String event;//事件
	private String event_describe;//描述
	private String event_name;//名称
	private String if_down;//是否下载中心文件
	private String org_id;//下载中心文件所属部门
	
	private String business_id;//业务id

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFk_file_id() {
		return fk_file_id;
	}

	public void setFk_file_id(String fk_file_id) {
		this.fk_file_id = fk_file_id;
	}

	public String getResource_name() {
		return resource_name;
	}

	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}

	public String getResource_path() {
		return resource_path;
	}

	public void setResource_path(String resource_path) {
		this.resource_path = resource_path;
	}

	public String getResource_owner_id() {
		return resource_owner_id;
	}

	public void setResource_owner_id(String resource_owner_id) {
		this.resource_owner_id = resource_owner_id;
	}

	public String getResource_share_user_id() {
		return resource_share_user_id;
	}

	public void setResource_share_user_id(String resource_share_user_id) {
		this.resource_share_user_id = resource_share_user_id;
	}

	public String getResource_share_status() {
		return resource_share_status;
	}

	public void setResource_share_status(String resource_share_status) {
		this.resource_share_status = resource_share_status;
	}

	public String getResource_size() {
		return resource_size;
	}

	public void setResource_size(String resource_size) {
		this.resource_size = resource_size;
	}

	public String getResource_type() {
		return resource_type;
	}

	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}

	public String getResource_remark() {
		return resource_remark;
	}

	public void setResource_remark(String resource_remark) {
		this.resource_remark = resource_remark;
	}

	public String getResource_is_hidden() {
		return resource_is_hidden;
	}

	public void setResource_is_hidden(String resource_is_hidden) {
		this.resource_is_hidden = resource_is_hidden;
	}

	public String getResource_important_flag() {
		return resource_important_flag;
	}

	public void setResource_important_flag(String resource_important_flag) {
		this.resource_important_flag = resource_important_flag;
	}

	public String getResource_order_no() {
		return resource_order_no;
	}

	public void setResource_order_no(String resource_order_no) {
		this.resource_order_no = resource_order_no;
	}

	public String getResource_access_password() {
		return resource_access_password;
	}

	public void setResource_access_password(String resource_access_password) {
		this.resource_access_password = resource_access_password;
	}

	public Date getResource_last_open_date() {
		return resource_last_open_date;
	}

	public void setResource_last_open_date(Date resource_last_open_date) {
		this.resource_last_open_date = resource_last_open_date;
	}

	public String getResource_last_open_ip() {
		return resource_last_open_ip;
	}

	public void setResource_last_open_ip(String resource_last_open_ip) {
		this.resource_last_open_ip = resource_last_open_ip;
	}

	public Date getResource_download_date() {
		return resource_download_date;
	}

	public void setResource_download_date(Date resource_download_date) {
		this.resource_download_date = resource_download_date;
	}

	public String getResource_download_ip() {
		return resource_download_ip;
	}

	public void setResource_download_ip(String resource_download_ip) {
		this.resource_download_ip = resource_download_ip;
	}

	public String getResource_is_recyclebin() {
		return resource_is_recyclebin;
	}

	public void setResource_is_recyclebin(String resource_is_recyclebin) {
		this.resource_is_recyclebin = resource_is_recyclebin;
	}

	public String getResource_status() {
		return resource_status;
	}

	public void setResource_status(String resource_status) {
		this.resource_status = resource_status;
	}

	public Date getResource_last_update_date() {
		return resource_last_update_date;
	}

	public void setResource_last_update_date(Date resource_last_update_date) {
		this.resource_last_update_date = resource_last_update_date;
	}

	public String getResource_share_user() {
		return resource_share_user;
	}

	public void setResource_share_user(String resource_share_user) {
		this.resource_share_user = resource_share_user;
	}

	public Date getEvent_date() {
		return event_date;
	}

	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}

	public String getEvent_man() {
		return event_man;
	}

	public void setEvent_man(String event_man) {
		this.event_man = event_man;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEvent_describe() {
		return event_describe;
	}

	public void setEvent_describe(String event_describe) {
		this.event_describe = event_describe;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getIf_down() {
		return if_down;
	}

	public void setIf_down(String if_down) {
		this.if_down = if_down;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}

	

	

}
