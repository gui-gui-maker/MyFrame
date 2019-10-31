package com.lsts.device.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * 告知书设备情况
 * 
 * @author liming 2014-5-9
 * 
 */

@Entity
@Table(name = "TZSB_APP_DEVICE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TzsbAppDevice implements BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5667159257106052414L;
	private String id;
	private String fk_tzsb_application_id;
	private String seq_num;
	private String device_sort;
	private String device_dis;
	private String device_variety;
	private String device_name;
	private String device_type;
	private String device_code;
	private String internal_num;
	private String device_use_address;
	private String factory_code;
	private String fk_company_info_make_id;
	private String make_units_name;
	private String make_units_code;
	private String make_date;
	private String device_sort_code;
	private String pipe_level;
	private String pipe_material;
	private String pipe_length;
	private String pipe_size;
	private TzsbApplication tzsbApplication;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFk_tzsb_application_id() {
		return fk_tzsb_application_id;
	}
	public void setFk_tzsb_application_id(String fk_tzsb_application_id) {
		this.fk_tzsb_application_id = fk_tzsb_application_id;
	}
	public String getSeq_num() {
		return seq_num;
	}
	public void setSeq_num(String seq_num) {
		this.seq_num = seq_num;
	}
	public String getDevice_sort() {
		return device_sort;
	}
	public void setDevice_sort(String device_sort) {
		this.device_sort = device_sort;
	}
	public String getDevice_dis() {
		return device_dis;
	}
	public void setDevice_dis(String device_dis) {
		this.device_dis = device_dis;
	}
	public String getDevice_variety() {
		return device_variety;
	}
	public void setDevice_variety(String device_variety) {
		this.device_variety = device_variety;
	}
	public String getDevice_name() {
		return device_name;
	}
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	public String getDevice_code() {
		return device_code;
	}
	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}
	public String getInternal_num() {
		return internal_num;
	}
	public void setInternal_num(String internal_num) {
		this.internal_num = internal_num;
	}
	public String getDevice_use_address() {
		return device_use_address;
	}
	public void setDevice_use_address(String device_use_address) {
		this.device_use_address = device_use_address;
	}
	public String getFactory_code() {
		return factory_code;
	}
	public void setFactory_code(String factory_code) {
		this.factory_code = factory_code;
	}
	public String getFk_company_info_make_id() {
		return fk_company_info_make_id;
	}
	public void setFk_company_info_make_id(String fk_company_info_make_id) {
		this.fk_company_info_make_id = fk_company_info_make_id;
	}
	public String getMake_units_name() {
		return make_units_name;
	}
	public void setMake_units_name(String make_units_name) {
		this.make_units_name = make_units_name;
	}
	public String getMake_units_code() {
		return make_units_code;
	}
	public void setMake_units_code(String make_units_code) {
		this.make_units_code = make_units_code;
	}
	public String getDevice_sort_code() {
		return device_sort_code;
	}
	public void setDevice_sort_code(String device_sort_code) {
		this.device_sort_code = device_sort_code;
	}
	public String getPipe_level() {
		return pipe_level;
	}
	public void setPipe_level(String pipe_level) {
		this.pipe_level = pipe_level;
	}
	public String getPipe_material() {
		return pipe_material;
	}
	public void setPipe_material(String pipe_material) {
		this.pipe_material = pipe_material;
	}
	public String getPipe_length() {
		return pipe_length;
	}
	public void setPipe_length(String pipe_length) {
		this.pipe_length = pipe_length;
	}
	public String getPipe_size() {
		return pipe_size;
	}
	public void setPipe_size(String pipe_size) {
		this.pipe_size = pipe_size;
	}
	@Transient
	public TzsbApplication getTzsbApplication() {
		return tzsbApplication;
	}
	public void setTzsbApplication(TzsbApplication tzsbApplication) {
		this.tzsbApplication = tzsbApplication;
	}
	public String getMake_date() {
		return make_date;
	}
	public void setMake_date(String make_date) {
		this.make_date = make_date;
	}
	
	
}
