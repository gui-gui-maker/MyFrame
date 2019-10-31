package com.scts.cspace.file.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * @author XCB
 * @date 2016-12-20
 * @summary 缓存数据
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "file_cache")
public class FileCache implements BaseEntity {
	private static final long serialVersionUID = 1L;
	private String id;
	private String infoName;// 文件名称
	private String file_path;// 文件路径
	private Date last_update_date;// 更新时间
	private String infoSize;// 文件大小
	private String infoType;//文件类型3是文件 4是文件夹
	private String parent_id;// 父ID
	private String  resourceType; //资源类型
	private String downTimes;//下载次数
	private String openTimes;//预览次数
	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}


	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	
	@Column(name="resource_type")
	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	@Column(name="file_type")
	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
	@Column(name="file_name")
	public String getInfoName() {
		return infoName;
	}

	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}
	@Column(name="file_update")
	public Date getLast_update_date() {
		return last_update_date;
	}

	public void setLast_update_date(Date last_update_date) {
		this.last_update_date = last_update_date;
	}
	@Column(name="file_size")
	public String getInfoSize() {
		return infoSize;
	}

	public void setInfoSize(String infoSize) {
		this.infoSize = infoSize;
	}

	public JSONObject to_Json(){
		JSONObject json = new JSONObject();
		json.put("id",id );
		json.put("infoName",infoName );
		//json.put("file_path", file_path);
		json.put("last_update_date", last_update_date);
		json.put("infoSize",infoSize );
		json.put("infoType", infoType);
		//json.put("parent_id", parent_id);
		json.put("resourceType",resourceType );
		json.put("downTimes",downTimes );
		json.put("openTimes",openTimes );
		return json;
	}

	@Transient
	public String getDownTimes() {
		return downTimes;
	}

	public void setDownTimes(String downTimes) {
		this.downTimes = downTimes;
	}

	@Transient
	public String getOpenTimes() {
		return openTimes;
	}

	public void setOpenTimes(String openTimes) {
		this.openTimes = openTimes;
	}

}
