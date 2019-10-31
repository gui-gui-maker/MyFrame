package com.khnt.rtbox.config.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name="BASE_NAME_CONFIG")
public class BaseConfig implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String deviceType;
	private String reportType;
	
	private String name;//基础信息名字
	private String code;//字段名（输入框名字）
	private String type;//类型（text，date,select）
	private String bindType;//绑定类型（code,sql,string）
	private String dataCode;//数据字典
	private String dataSql;//通过sql获取数据
	private String dataString;//通过自定义字符串
	private String defaultValue;//输入框默认值
	private String remark;//备注(用于码表或者sql)
	private String platform;//系统平台
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	@Column(name="DEVICE_TYPE")
	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	@Column(name="REPORT_TYPE")
	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	@Column(name="data_code")
	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}
	@Column(name="data_sql")
	public String getDataSql() {
		return dataSql;
	}

	public void setDataSql(String dataSql) {
		this.dataSql = dataSql;
	}
	@Column(name="data_string")
	public String getDataString() {
		return dataString;
	}

	public void setDataString(String dataString) {
		this.dataString = dataString;
	}
	@Column(name="bind_type")
	public String getBindType() {
		return bindType;
	}

	public void setBindType(String bindType) {
		this.bindType = bindType;
	}
	@Column(name="default_value")
	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	
	
}
