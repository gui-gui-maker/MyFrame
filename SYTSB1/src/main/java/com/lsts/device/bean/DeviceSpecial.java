package com.lsts.device.bean;

import java.util.Date;

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

/**
 * 特种设备注册登记
 * 
 * @author liming
 * 
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = { "deviceDocument" })
@Table(name = "TZSB_DEVICE_SPECIAL")
public class DeviceSpecial implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8534330710970740503L;
	private String id;
	private String fk_property_right_unit_id;
	private String manager_tel;
	private String equipment_use_place;
	private String fk_tzsb_operators_info_id;
	private String equipment_name;
	private String equipment_model;
	private String fk_design_units_id;
	private String suitable_place;
	private String project_charge_man;
	private String project_charge_tel;
	private String build_unit;
	private Date start_date;
	private String accept_unit;
	private String acceptance_unit;
	private String acceptance_code;
	private String maintenance_time;
	private String overhaul_time;
	private String fk_maintenance_unit_id;
	private String maintenance_man;
	private String maintenance_tel;
	private String factory_vehicle_license;
	private String maintenance_unit_name;
	private String property_right_unit_name;
	private String property_right_unit_address;
	private String property_right_unit_zip_code;
	private String property_right_unit_legal_rep;
	private String property_right_unit_manager;
	private String design_units_name;
	private String design_units_code;
	private String send_status;
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
	@JoinColumn(name = "FK_TZSB_DEVICE_DOCUMENT_ID")
	public DeviceDocument getDeviceDocument() {
		return deviceDocument;
	}

	public void setDeviceDocument(DeviceDocument deviceDocument) {
		this.deviceDocument = deviceDocument;
	}

	public String getFk_property_right_unit_id() {
		return fk_property_right_unit_id;
	}

	public void setFk_property_right_unit_id(String fk_property_right_unit_id) {
		this.fk_property_right_unit_id = fk_property_right_unit_id;
	}

	public String getManager_tel() {
		return manager_tel;
	}

	public void setManager_tel(String manager_tel) {
		this.manager_tel = manager_tel;
	}

	public String getEquipment_use_place() {
		return equipment_use_place;
	}

	public void setEquipment_use_place(String equipment_use_place) {
		this.equipment_use_place = equipment_use_place;
	}

	public String getFk_tzsb_operators_info_id() {
		return fk_tzsb_operators_info_id;
	}

	public void setFk_tzsb_operators_info_id(String fk_tzsb_operators_info_id) {
		this.fk_tzsb_operators_info_id = fk_tzsb_operators_info_id;
	}

	public String getEquipment_name() {
		return equipment_name;
	}

	public void setEquipment_name(String equipment_name) {
		this.equipment_name = equipment_name;
	}

	public String getEquipment_model() {
		return equipment_model;
	}

	public void setEquipment_model(String equipment_model) {
		this.equipment_model = equipment_model;
	}

	public String getFk_design_units_id() {
		return fk_design_units_id;
	}

	public void setFk_design_units_id(String fk_design_units_id) {
		this.fk_design_units_id = fk_design_units_id;
	}

	public String getSuitable_place() {
		return suitable_place;
	}

	public void setSuitable_place(String suitable_place) {
		this.suitable_place = suitable_place;
	}

	public String getProject_charge_man() {
		return project_charge_man;
	}

	public void setProject_charge_man(String project_charge_man) {
		this.project_charge_man = project_charge_man;
	}

	public String getProject_charge_tel() {
		return project_charge_tel;
	}

	public void setProject_charge_tel(String project_charge_tel) {
		this.project_charge_tel = project_charge_tel;
	}

	public String getBuild_unit() {
		return build_unit;
	}

	public void setBuild_unit(String build_unit) {
		this.build_unit = build_unit;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public String getAccept_unit() {
		return accept_unit;
	}

	public void setAccept_unit(String accept_unit) {
		this.accept_unit = accept_unit;
	}

	public String getAcceptance_unit() {
		return acceptance_unit;
	}

	public void setAcceptance_unit(String acceptance_unit) {
		this.acceptance_unit = acceptance_unit;
	}

	public String getAcceptance_code() {
		return acceptance_code;
	}

	public void setAcceptance_code(String acceptance_code) {
		this.acceptance_code = acceptance_code;
	}

	public String getMaintenance_time() {
		return maintenance_time;
	}

	public void setMaintenance_time(String maintenance_time) {
		this.maintenance_time = maintenance_time;
	}

	public String getOverhaul_time() {
		return overhaul_time;
	}

	public void setOverhaul_time(String overhaul_time) {
		this.overhaul_time = overhaul_time;
	}

	public String getFk_maintenance_unit_id() {
		return fk_maintenance_unit_id;
	}

	public void setFk_maintenance_unit_id(String fk_maintenance_unit_id) {
		this.fk_maintenance_unit_id = fk_maintenance_unit_id;
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

	public String getFactory_vehicle_license() {
		return factory_vehicle_license;
	}

	public void setFactory_vehicle_license(String factory_vehicle_license) {
		this.factory_vehicle_license = factory_vehicle_license;
	}

	public String getMaintenance_unit_name() {
		return maintenance_unit_name;
	}

	public void setMaintenance_unit_name(String maintenance_unit_name) {
		this.maintenance_unit_name = maintenance_unit_name;
	}

	public String getProperty_right_unit_name() {
		return property_right_unit_name;
	}

	public void setProperty_right_unit_name(String property_right_unit_name) {
		this.property_right_unit_name = property_right_unit_name;
	}

	public String getProperty_right_unit_address() {
		return property_right_unit_address;
	}

	public void setProperty_right_unit_address(String property_right_unit_address) {
		this.property_right_unit_address = property_right_unit_address;
	}

	public String getProperty_right_unit_zip_code() {
		return property_right_unit_zip_code;
	}

	public void setProperty_right_unit_zip_code(String property_right_unit_zip_code) {
		this.property_right_unit_zip_code = property_right_unit_zip_code;
	}

	public String getProperty_right_unit_legal_rep() {
		return property_right_unit_legal_rep;
	}

	public void setProperty_right_unit_legal_rep(
			String property_right_unit_legal_rep) {
		this.property_right_unit_legal_rep = property_right_unit_legal_rep;
	}

	public String getProperty_right_unit_manager() {
		return property_right_unit_manager;
	}

	public void setProperty_right_unit_manager(String property_right_unit_manager) {
		this.property_right_unit_manager = property_right_unit_manager;
	}

	public String getDesign_units_name() {
		return design_units_name;
	}

	public void setDesign_units_name(String design_units_name) {
		this.design_units_name = design_units_name;
	}

	public String getDesign_units_code() {
		return design_units_code;
	}

	public void setDesign_units_code(String design_units_code) {
		this.design_units_code = design_units_code;
	}

	public String getSend_status() {
		return send_status;
	}

	public void setSend_status(String send_status) {
		this.send_status = send_status;
	}

}
