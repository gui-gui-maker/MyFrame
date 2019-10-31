package com.scts.cspace.path.bean;

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
 * @author XCB
 * @date 2016-10-24
 * @summary 资源分类路径
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tjypt_resource_path")
public class TjyptResourcePath implements BaseEntity {
	private static final long serialVersionUID = 1L;
	private String id;
	private String fk_resource_space_id; // 所属资源空间
	private String parent_path_id; // 上级资源分类
	private String path_name; // 资源分类名称
	private String path_remark; // 资源分类备注
	private String path_is_hidden; // 是否隐藏 1.隐藏
	private String path_is_recyclebin; // 是否在回收站 1.在回收站
	private String path_important_flag; // 重要等级
	private String path_order_no; // 自定义排序序号
	private String path_accsess_password; // 资源访问密码
	private Date path_last_update_date; // 最后修改日期
	private String path_share_user_id; // 资源分析者
	private String path_share_user; // 资源分析者
	private String path_create_user_id;//文件夹创建人
	private Date path_create_date;//文件夹创建时间

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFk_resource_space_id() {
		return fk_resource_space_id;
	}

	public void setFk_resource_space_id(String fk_resource_space_id) {
		this.fk_resource_space_id = fk_resource_space_id;
	}

	public String getParent_path_id() {
		return parent_path_id;
	}

	public void setParent_path_id(String parent_path_id) {
		this.parent_path_id = parent_path_id;
	}

	public String getPath_name() {
		return path_name;
	}

	public void setPath_name(String path_name) {
		this.path_name = path_name;
	}

	public String getPath_remark() {
		return path_remark;
	}

	public void setPath_remark(String path_remark) {
		this.path_remark = path_remark;
	}

	public String getPath_is_hidden() {
		return path_is_hidden;
	}

	public void setPath_is_hidden(String path_is_hidden) {
		this.path_is_hidden = path_is_hidden;
	}

	public String getPath_is_recyclebin() {
		return path_is_recyclebin;
	}

	public void setPath_is_recyclebin(String path_is_recyclebin) {
		this.path_is_recyclebin = path_is_recyclebin;
	}

	public String getPath_important_flag() {
		return path_important_flag;
	}

	public void setPath_important_flag(String path_important_flag) {
		this.path_important_flag = path_important_flag;
	}

	public String getPath_order_no() {
		return path_order_no;
	}

	public void setPath_order_no(String path_order_no) {
		this.path_order_no = path_order_no;
	}

	public String getPath_accsess_password() {
		return path_accsess_password;
	}

	public void setPath_accsess_password(String path_accsess_password) {
		this.path_accsess_password = path_accsess_password;
	}

	public Date getPath_last_update_date() {
		return path_last_update_date;
	}

	public void setPath_last_update_date(Date path_last_update_date) {
		this.path_last_update_date = path_last_update_date;
	}

	public String getPath_share_user_id() {
		return path_share_user_id;
	}

	public void setPath_share_user_id(String path_share_user_id) {
		this.path_share_user_id = path_share_user_id;
	}

	public String getPath_share_user() {
		return path_share_user;
	}

	public void setPath_share_user(String path_share_user) {
		this.path_share_user = path_share_user;
	}

	public String getPath_create_user_id() {
		return path_create_user_id;
	}

	public void setPath_create_user_id(String path_create_user_id) {
		this.path_create_user_id = path_create_user_id;
	}

	public Date getPath_create_date() {
		return path_create_date;
	}

	public void setPath_create_date(Date path_create_date) {
		this.path_create_date = path_create_date;
	}



}
