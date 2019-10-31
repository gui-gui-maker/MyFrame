package com.lsts.device.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * 告知书工程情况
 * 
 * @author liming 2014-5-9
 * 
 */

@Entity
@Table(name = "TZSB_APP_ENGINEER_SITUATIONS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TzsbAppEngineerSituations implements BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6603731298906917169L;
	private String id;
	private String fk_tzsb_application_id;
	private String engineering_name;
	private String fk_construct_units_id;
	private String engineering_principal;
	private String principal_tel;
	private String engineering_devise_units;
	private String pact_num;
	private Date pact_date;
	private String construct_item;
	private Integer device_num;
	private Date plan_begin_date;
	private Date plan_end_date;
	private String construction_total_budget;
	private String device_total_budget;
	private String engineering_address;
	private String engineering_district;

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

	public String getEngineering_name() {
		return engineering_name;
	}

	public void setEngineering_name(String engineering_name) {
		this.engineering_name = engineering_name;
	}

	public String getFk_construct_units_id() {
		return fk_construct_units_id;
	}

	public void setFk_construct_units_id(String fk_construct_units_id) {
		this.fk_construct_units_id = fk_construct_units_id;
	}

	public String getEngineering_principal() {
		return engineering_principal;
	}

	public void setEngineering_principal(String engineering_principal) {
		this.engineering_principal = engineering_principal;
	}

	public String getPrincipal_tel() {
		return principal_tel;
	}

	public void setPrincipal_tel(String principal_tel) {
		this.principal_tel = principal_tel;
	}

	public String getEngineering_devise_units() {
		return engineering_devise_units;
	}

	public void setEngineering_devise_units(String engineering_devise_units) {
		this.engineering_devise_units = engineering_devise_units;
	}

	public String getPact_num() {
		return pact_num;
	}

	public void setPact_num(String pact_num) {
		this.pact_num = pact_num;
	}

	public Date getPact_date() {
		return pact_date;
	}

	public void setPact_date(Date pact_date) {
		this.pact_date = pact_date;
	}

	public String getConstruct_item() {
		return construct_item;
	}

	public void setConstruct_item(String construct_item) {
		this.construct_item = construct_item;
	}

	public Integer getDevice_num() {
		return device_num;
	}

	public void setDevice_num(Integer device_num) {
		this.device_num = device_num;
	}

	public Date getPlan_begin_date() {
		return plan_begin_date;
	}

	public void setPlan_begin_date(Date plan_begin_date) {
		this.plan_begin_date = plan_begin_date;
	}

	public Date getPlan_end_date() {
		return plan_end_date;
	}

	public void setPlan_end_date(Date plan_end_date) {
		this.plan_end_date = plan_end_date;
	}

	public String getConstruction_total_budget() {
		return construction_total_budget;
	}

	public void setConstruction_total_budget(String construction_total_budget) {
		this.construction_total_budget = construction_total_budget;
	}

	public String getDevice_total_budget() {
		return device_total_budget;
	}

	public void setDevice_total_budget(String device_total_budget) {
		this.device_total_budget = device_total_budget;
	}

	public String getEngineering_address() {
		return engineering_address;
	}

	public void setEngineering_address(String engineering_address) {
		this.engineering_address = engineering_address;
	}

	public String getEngineering_district() {
		return engineering_district;
	}

	public void setEngineering_district(String engineering_district) {
		this.engineering_district = engineering_district;
	}

}
