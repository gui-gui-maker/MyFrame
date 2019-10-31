package com.lsts.advicenote.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 短信、微信发送历史记录
 *  entity. 
 * @author GaoYa
 * @date 2015-12-18 16:15:00
 */
@Entity
@Table(name = "MESSAGE_INFO")
public class MessageHistory implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;			// 流水号
	private String business_id;	// 业务id
	private String mobile;		// 手机号码	
	private String content;		// 内容
	private String type;		// 消息类型（1：文本消息）
	private String msg_type;	// 文本消息类型（0：短信 1：微信）
	private String status;		// 发送状态（0，发送成功；-10、用户认证失败；-11、ip或域名错误；-12、余额不足；-14、提交手机号超量；-15、短信内容含屏蔽关键字；-22、发送为空；）
	private String message;		// 发送错误或成功提示
	private String user_id;		// 发送操作用户id
	private String user_name;	// 发送操作用户姓名
	private Date   send_time;	// 发送时间
	private Date   create_date;	// 创建时间
	private String business_ids;	// 业务id
	private String wx_corpid;    //微信账号
	private String wx_pwd;        //微信密码
	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSend_time() {
		return send_time;
	}

	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}

	public String getBusiness_ids() {
		return business_ids;
	}

	public void setBusiness_ids(String business_ids) {
		this.business_ids = business_ids;
	}

	public String getWx_corpid() {
		return wx_corpid;
	}

	public void setWx_corpid(String wx_corpid) {
		this.wx_corpid = wx_corpid;
	}

	public String getWx_pwd() {
		return wx_pwd;
	}

	public void setWx_pwd(String wx_pwd) {
		this.wx_pwd = wx_pwd;
	}
	
}
