package com.lsts.device.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * 安全阀参数
 * 
 * @author 肖慈边 2014-3-21
 * 
 */

@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = { "deviceDocument" })
@Table(name = "BASE_DEVICE_ACCESSORY")
public class Accessory implements BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private String	id	;	//	id

//	private String	fk_tsjc_device_document_id	;//	设备基本信息ID
	private String	accessory_name	;//	名称
	private String	accessory_type	;//	型号
	private String	accessory_spec	;//	规格
	private String	accessory_number	;//	数量
	private String	make_units	;//	制造单位

	private String work_medium	;//工作介质
	private String work_type	;//检验方式
	private String check_medium;//	校验介质
	private String installation_site;//	安装位置
	private String executive_standard;//	执行标准

	
	
	
	 
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public DeviceDocument deviceDocument;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_tsjc_device_document_id")
	public DeviceDocument getDeviceDocument() {
		return deviceDocument;
	}

	public void setDeviceDocument(DeviceDocument deviceDocument) {
		this.deviceDocument = deviceDocument;
	}
	public String getAccessory_name() {
		return accessory_name;
	}
	public void setAccessory_name(String accessory_name) {
		this.accessory_name = accessory_name;
	}
	public String getAccessory_type() {
		return accessory_type;
	}
	public void setAccessory_type(String accessory_type) {
		this.accessory_type = accessory_type;
	}
	public String getAccessory_spec() {
		return accessory_spec;
	}
	public void setAccessory_spec(String accessory_spec) {
		this.accessory_spec = accessory_spec;
	}
	public String getAccessory_number() {
		return accessory_number;
	}
	public void setAccessory_number(String accessory_number) {
		this.accessory_number = accessory_number;
	}
	public String getMake_units() {
		return make_units;
	}
	public void setMake_units(String make_units) {
		this.make_units = make_units;
	}
	public String getWork_medium() {
		return work_medium;
	}
	public void setWork_medium(String work_medium) {
		this.work_medium = work_medium;
	}
	public String getWork_type() {
		return work_type;
	}
	public void setWork_type(String work_type) {
		this.work_type = work_type;
	}
	public String getCheck_medium() {
		return check_medium;
	}
	public void setCheck_medium(String check_medium) {
		this.check_medium = check_medium;
	}
	public String getInstallation_site() {
		return installation_site;
	}
	public void setInstallation_site(String installation_site) {
		this.installation_site = installation_site;
	}
	public String getExecutive_standard() {
		return executive_standard;
	}
	public void setExecutive_standard(String executive_standard) {
		this.executive_standard = executive_standard;
	}
	


}
