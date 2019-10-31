package com.lsts.device.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * 土建工程施工单位
 * 
 * @author liming 2014-5-9
 * 
 */

@Entity
@Table(name = "TZSB_APP_CONST_UNIT")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TzsbAppConsUnit implements BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4694468098175220093L;
	private String id;
	private String fk_tzsb_application_id;
	private String item_name;
	private String fk_construction_unit_id;
	private String construction_unit_name;
	private String construction_unit_code;
	
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
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getFk_construction_unit_id() {
		return fk_construction_unit_id;
	}
	public void setFk_construction_unit_id(String fk_construction_unit_id) {
		this.fk_construction_unit_id = fk_construction_unit_id;
	}
	public String getConstruction_unit_name() {
		return construction_unit_name;
	}
	public void setConstruction_unit_name(String construction_unit_name) {
		this.construction_unit_name = construction_unit_name;
	}
	public String getConstruction_unit_code() {
		return construction_unit_code;
	}
	public void setConstruction_unit_code(String construction_unit_code) {
		this.construction_unit_code = construction_unit_code;
	}

}
