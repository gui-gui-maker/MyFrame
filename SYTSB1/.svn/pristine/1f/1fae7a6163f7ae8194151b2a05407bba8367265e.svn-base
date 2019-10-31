package com.lsts.advicenote.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;


@Entity
@Table(name="TJY2_MESSAGE_CONTENT_CONFIG")
public class MessageContentCon implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5360724879228786608L;
	private String id;	
	private String fk_module_id;//模块id
	private String module_code;//编号（不能重复，不能修改）
	private String module_name;//模块名称
	private String content;//发送内容
	private String send_type;//发送方式 微信、短信
	private String send_time;//发送时限 即时、延迟、定时
	private Date preview_time;//定时预约时间
    private Date late_time;//延迟时间
    private String data_status;//0正常 1禁用 99删除
    private String create_op_id;//创建人id 
    private String create_op;//创建人
    private Date create_time;//创建时间
    private String last_update_op_id;//最后修改人id 
    private String last_update_op;//最后修改人
    private Date last_update_time;//最后修改时间
    private String preview_type;//定时类型
    private String wx_module;//微信模块
	
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public String getFk_module_id() {
		return fk_module_id;
	}

	public void setFk_module_id(String fk_module_id) {
		this.fk_module_id = fk_module_id;
	}

	public String getModule_code() {
		return module_code;
	}

	public void setModule_code(String module_code) {
		this.module_code = module_code;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSend_type() {
		return send_type;
	}

	public void setSend_type(String send_type) {
		this.send_type = send_type;
	}

	public String getSend_time() {
		return send_time;
	}

	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}

	public Date getPreview_time() {
		return preview_time;
	}

	public void setPreview_time(Date preview_time) {
		this.preview_time = preview_time;
	}

	public Date getLate_time() {
		return late_time;
	}

	public void setLate_time(Date late_time) {
		this.late_time = late_time;
	}

	public String getData_status() {
		return data_status;
	}

	public void setData_status(String data_status) {
		this.data_status = data_status;
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

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getLast_update_op_id() {
		return last_update_op_id;
	}

	public void setLast_update_op_id(String last_update_op_id) {
		this.last_update_op_id = last_update_op_id;
	}

	public String getLast_update_op() {
		return last_update_op;
	}

	public void setLast_update_op(String last_update_op) {
		this.last_update_op = last_update_op;
	}

	public Date getLast_update_time() {
		return last_update_time;
	}

	public void setLast_update_time(Date last_update_time) {
		this.last_update_time = last_update_time;
	}

	public String getPreview_type() {
		return preview_type;
	}

	public void setPreview_type(String preview_type) {
		this.preview_type = preview_type;
	}

	public String getWx_module() {
		return wx_module;
	}

	public void setWx_module(String wx_module) {
		this.wx_module = wx_module;
	}

	
	
}
