package com.scts.weixin.com.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 扫码登录日志
 * @author pingZhou
 *
 */
@Entity
@Table(name="WEIXIN_LOGIN_LOG")
public class WeixinLoginLog implements BaseEntity{

	private static final long serialVersionUID = 1L;
	private String id;	// 流水号
	private String mobile;	// 手机号
	private String userId;	// 微信用户id
	private Date   opTime;	// 操作时间
	private String opAction;	// 操作动作
	private String opRemark;	// 操作描述
	private String opStatus;	// 操作状态
	private String deviceId;	// 企业微信安装设备id
	private String accessToken;	// 微信授权accessToken
	private String code;	// 授权code
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name="USER_ID")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name="OP_TIME")
	public Date getOpTime() {
		return opTime;
	}
	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}
	@Column(name="OP_ACTION")
	public String getOpAction() {
		return opAction;
	}
	public void setOpAction(String opAction) {
		this.opAction = opAction;
	}
	@Column(name="OP_REMARK")
	public String getOpRemark() {
		return opRemark;
	}
	public void setOpRemark(String opRemark) {
		this.opRemark = opRemark;
	}
	@Column(name="OP_STATUS")
	public String getOpStatus() {
		return opStatus;
	}
	public void setOpStatus(String opStatus) {
		this.opStatus = opStatus;
	}
	@Column(name="DEVICE_ID")
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	@Column(name="ACCESS_TOKEN")
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	@Column(name="CODE")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
