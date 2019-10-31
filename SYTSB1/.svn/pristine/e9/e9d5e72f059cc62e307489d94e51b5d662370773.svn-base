package com.scts.cspace.resource.bean;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

public class QueryResourceInfo {

	private String	id	;
	private String	infoType	;//文件类型（1是文件夹2是资源）
	private String	infoName	;//文件名称
	private String	infoIsHidden	;//是否隐藏 1隐藏
	private String	infoRemark	;//备注说明
	private String	infoSize;//文件大小
	private Date	last_update_date	;//最后更新时间
	private String	resourceType	;//文件类型（1 office文件，2普通文件...）
	private String	level;
	private String	shareUser;//分享人
	private String downTimes;//下载次数
	private String openTimes;//预览次数
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInfoType() {
		return infoType;
	}
	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
	public String getInfoName() {
		return infoName;
	}
	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}
	public String getInfoIsHidden() {
		return infoIsHidden;
	}
	public void setInfoIsHidden(String infoIsHidden) {
		this.infoIsHidden = infoIsHidden;
	}
	public String getInfoRemark() {
		return infoRemark;
	}
	public void setInfoRemark(String infoRemark) {
		this.infoRemark = infoRemark;
	}
	public String getInfoSize() {
		return infoSize;
	}
	public void setInfoSize(String infoSize) {
		this.infoSize = infoSize;
	}
	public Date getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(Date last_update_date) {
		this.last_update_date = last_update_date;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getShareUser() {
		return shareUser;
	}
	public void setShareUser(String shareUser) {
		this.shareUser = shareUser;
	}
	public JSONObject to_Json(){
		JSONObject json = new JSONObject();
		json.put("id",id );
		json.put("infoName",infoName );
		json.put("last_update_date", last_update_date);
		json.put("infoSize",infoSize );
		json.put("infoType", infoType);
		json.put("shareUser", shareUser);
		json.put("resourceType",resourceType );
		json.put("downTimes",downTimes );
		json.put("openTimes",openTimes );
		
		return json;
	}
	public String getDownTimes() {
		return downTimes;
	}
	public void setDownTimes(String downTimes) {
		this.downTimes = downTimes;
	}
	public String getOpenTimes() {
		return openTimes;
	}
	public void setOpenTimes(String openTimes) {
		this.openTimes = openTimes;
	}

	
}
