package com.khnt.mobile.version.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * @ClassName: SdyAppUpdate
 * @Description:app更新信息
 * @author Jhon
 * @date 2016年4月26日 下午5:33:13
 * 
 */
@Entity
@Table(name = "SYS_APP_UPDATE")
public class AppUpdate implements BaseEntity {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String id;//

	private String type; //IOS`IOS`ANDROID`ANDROID

	private String appVersion;//应用版本号
	
	private String version;// 版本号:资源版本号

	private String description;// 升级描述信息

	private java.util.Date cdate;//发布时间

	private String cname;//发布人
	
	private String url;//android安装包对应url，ios对应企业应用plist的url或应用商店对应app的url
	
	private String pubType;//1.wgt升级包 2，整包方式 3，大升级
	
	private String packageName;//包名
	
	private String needUpdate;//是否强制更新
	

	public void setId(String value) {
		this.id = value;
	}

	public void setVersion(String value) {
		this.version = value;
	}

	public void setDescription(String value) {
		this.description = value;
	}

	public void setCdate(java.util.Date value) {
		this.cdate = value;
	}

	public void setCname(String value) {
		this.cname = value;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getId() {
		return this.id;
	}

	@Column(name = "VERSION", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getVersion() {
		return this.version;
	}

	@Column(name = "DESCRIPTION", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getDescription() {
		return this.description;
	}

	@Column(name = "CDATE", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getCdate() {
		return this.cdate;
	}

	@Column(name = "CNAME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getCname() {
		return this.cname;
	}

	
	@Column(name="url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	@Column(name="pub_type")
	public String getPubType() {
		return pubType;
	}

	public void setPubType(String pubType) {
		this.pubType = pubType;
	}

	
	@Column(name="PACKAGE_NAME")
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	
	@Column(name="TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name="NEED_UPDATE")
	public String getNeedUpdate() {
		return needUpdate;
	}

	public void setNeedUpdate(String needUpdate) {
		this.needUpdate = needUpdate;
	}

	
	@Column(name="app_version")
	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

}
