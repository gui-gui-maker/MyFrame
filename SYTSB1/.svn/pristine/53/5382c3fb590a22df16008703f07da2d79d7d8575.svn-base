package com.lsts.device.bean;

import java.util.List;

import javax.persistence.Transient;

import java.util.Date;


/**
 * 设备预警数据传输对象 DeviceDTO
 * 
 * @author GaoYa
 * @date 2014-04-17 下午01:36:00
 */
public class DeviceDTO {

	// Fields
	private String id;
	private String device_name;
	private String com_name;
	private String com_addr;
	private String com_code; 		// 使用单位代码
	private String device_type;
	private String internal_num; 	// 单位内部编号
	private String device_qr_code;	// 设备二维码编号
	private String device_model;	// 设备型号
	private String make_date; 		// 制造日期
	private String factory_code; 	// 出厂编号	
	private String device_area_code; // 设备所在区域编码
	private String device_area_name; // 设备所在区域名称
	private String device_street_code; // 设备所在街道编码
	private String device_street_name; // 设备所在街道名称

	private String check_unit_id; // 检验部门ID
	private String check_unit_name; // 检验部门名称
	private int warn_days;
	private String fk_company_info_use_id; // 使用单位ID
	private String company_name; // 使用单位
	private String device_use_place; // 设备使用地点
	private String fk_maintain_unit_id; // 维保单位ID
	private String maintain_unit_name; // 维保单位名称
	private String maintenance_man; // 维保单位联系人
	private String maintenance_tel; // 维保单位联系人电话
	private String construction_units_name; // 安装单位名称（现场施工单位名称）
	private String construction_user_name; 	// 安装（施工）单位负责人
	private String construction_licence_no; // 安装单位许可证号
	
	
	private String security_op; // 安全管理人员
	private String security_tel; // 安全管理联系电话
	private String check_date; // 检验日期

	// 允许区县局修改字段
	private String registration_num; // 使用登记证号
	private String registration_agencies; // 注册登记机构
	private String registration_op; // 注册登记人员
	private String registration_date; // 注册登记日期

	// 移动检验业务任务接收信息
	private String receive_user_id; // 接收人ID
	private String receive_user_name; // 接收人姓名
	private Date receive_time; // 接收时间
	
	private String count;		// 复制台数
	private String note; 		// 备注
	
	private String device_sort_code; // 设备类别代码
	private String device_sort_name; // 设备品种
	private String make_units_name; // 制造单位名称
	
	
	
	public List<DeviceDocument> device_registration_codes;

	// Constructors

	/** default constructor */
	public DeviceDTO() {
	}

	/** full constructor */
	public DeviceDTO(String id, String device_name, String com_name,
			String device_type, String device_area_code,
			String device_area_name, String device_street_code,
			String device_street_name, int warn_days) {
		this.id = id;
		this.device_name = device_name;
		this.com_name = com_name;
		this.device_type = device_type;
		this.device_area_code = device_area_code;
		this.device_area_name = device_area_name;
		this.device_street_code = device_street_code;
		this.device_street_name = device_street_name;
		this.warn_days = warn_days;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public int getWarn_days() {
		return warn_days;
	}

	public void setWarn_days(int warn_days) {
		this.warn_days = warn_days;
	}

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public String getDevice_area_name() {
		return device_area_name;
	}

	public void setDevice_area_name(String device_area_name) {
		this.device_area_name = device_area_name;
	}

	public String getFk_company_info_use_id() {
		return fk_company_info_use_id;
	}

	public void setFk_company_info_use_id(String fk_company_info_use_id) {
		this.fk_company_info_use_id = fk_company_info_use_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getFk_maintain_unit_id() {
		return fk_maintain_unit_id;
	}

	public void setFk_maintain_unit_id(String fk_maintain_unit_id) {
		this.fk_maintain_unit_id = fk_maintain_unit_id;
	}

	public String getMaintain_unit_name() {
		return maintain_unit_name;
	}

	public void setMaintain_unit_name(String maintain_unit_name) {
		this.maintain_unit_name = maintain_unit_name;
	}

	public String getMaintenance_man() {
		return maintenance_man;
	}

	public void setMaintenance_man(String maintenance_man) {
		this.maintenance_man = maintenance_man;
	}

	public String getMaintenance_tel() {
		return maintenance_tel;
	}

	public void setMaintenance_tel(String maintenance_tel) {
		this.maintenance_tel = maintenance_tel;
	}

	public String getSecurity_op() {
		return security_op;
	}

	public void setSecurity_op(String security_op) {
		this.security_op = security_op;
	}

	public String getSecurity_tel() {
		return security_tel;
	}

	public void setSecurity_tel(String security_tel) {
		this.security_tel = security_tel;
	}

	public String getDevice_use_place() {
		return device_use_place;
	}

	public void setDevice_use_place(String device_use_place) {
		this.device_use_place = device_use_place;
	}

	public String getDevice_area_code() {
		return device_area_code;
	}

	public void setDevice_area_code(String device_area_code) {
		this.device_area_code = device_area_code;
	}

	public String getCheck_unit_id() {
		return check_unit_id;
	}

	public void setCheck_unit_id(String check_unit_id) {
		this.check_unit_id = check_unit_id;
	}

	public String getCheck_unit_name() {
		return check_unit_name;
	}

	public void setCheck_unit_name(String check_unit_name) {
		this.check_unit_name = check_unit_name;
	}

	public String getCheck_date() {
		return check_date;
	}

	public void setCheck_date(String check_date) {
		this.check_date = check_date;
	}

	public String getDevice_street_code() {
		return device_street_code;
	}

	public void setDevice_street_code(String device_street_code) {
		this.device_street_code = device_street_code;
	}

	public String getDevice_street_name() {
		return device_street_name;
	}

	public void setDevice_street_name(String device_street_name) {
		this.device_street_name = device_street_name;
	}

	public String getRegistration_num() {
		return registration_num;
	}

	public void setRegistration_num(String registration_num) {
		this.registration_num = registration_num;
	}

	public String getRegistration_agencies() {
		return registration_agencies;
	}

	public void setRegistration_agencies(String registration_agencies) {
		this.registration_agencies = registration_agencies;
	}

	public String getRegistration_op() {
		return registration_op;
	}

	public void setRegistration_op(String registration_op) {
		this.registration_op = registration_op;
	}

	public String getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}

	public Date getReceive_time() {
		return receive_time;
	}

	public void setReceive_time(Date receive_time) {
		this.receive_time = receive_time;
	}

	public String getReceive_user_id() {
		return receive_user_id;
	}

	public void setReceive_user_id(String receive_user_id) {
		this.receive_user_id = receive_user_id;
	}

	public String getReceive_user_name() {
		return receive_user_name;
	}

	public void setReceive_user_name(String receive_user_name) {
		this.receive_user_name = receive_user_name;
	}
	
	

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@Transient
	public List<DeviceDocument> getDevice_registration_codes() {
		return device_registration_codes;
	}

	public void setDevice_registration_codes(List<DeviceDocument> device_registration_codes) {
		this.device_registration_codes = device_registration_codes;
	}

	public String getConstruction_units_name() {
		return construction_units_name;
	}

	public void setConstruction_units_name(String construction_units_name) {
		this.construction_units_name = construction_units_name;
	}

	public String getConstruction_user_name() {
		return construction_user_name;
	}

	public void setConstruction_user_name(String construction_user_name) {
		this.construction_user_name = construction_user_name;
	}

	public String getConstruction_licence_no() {
		return construction_licence_no;
	}

	public void setConstruction_licence_no(String construction_licence_no) {
		this.construction_licence_no = construction_licence_no;
	}

	public String getCom_addr() {
		return com_addr;
	}

	public void setCom_addr(String com_addr) {
		this.com_addr = com_addr;
	}

	public String getCom_code() {
		return com_code;
	}

	public void setCom_code(String com_code) {
		this.com_code = com_code;
	}

	public String getInternal_num() {
		return internal_num;
	}

	public void setInternal_num(String internal_num) {
		this.internal_num = internal_num;
	}

	public String getDevice_qr_code() {
		return device_qr_code;
	}

	public void setDevice_qr_code(String device_qr_code) {
		this.device_qr_code = device_qr_code;
	}

	public String getDevice_model() {
		return device_model;
	}

	public void setDevice_model(String device_model) {
		this.device_model = device_model;
	}

	public String getMake_date() {
		return make_date;
	}

	public void setMake_date(String make_date) {
		this.make_date = make_date;
	}

	public String getFactory_code() {
		return factory_code;
	}

	public void setFactory_code(String factory_code) {
		this.factory_code = factory_code;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public String getDevice_sort_code() {
		return device_sort_code;
	}
	public void setDevice_sort_code(String device_sort_code) {
		this.device_sort_code = device_sort_code;
	}

	public String getDevice_sort_name() {
		return device_sort_name;
	}
	public void setDevice_sort_name(String device_sort_name) {
		this.device_sort_name = device_sort_name;
	}
	public String getMake_units_name() {
		return make_units_name;
	}
	public void setMake_units_name(String make_units_name) {
		this.make_units_name = make_units_name;
	}
}