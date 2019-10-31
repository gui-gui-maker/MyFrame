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
 * 告知书施工分包
 * 
 * @author liming 2014-5-9
 * 
 */

@Entity
@Table(name = "TZSB_APP_OUTSOUR")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TzsbAppOutsour implements BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5902024748715135158L;
	private String id;
	private String fk_tzsb_application_id;
	private String engineering_item;
	private String contractor_unit;
	private String contractor_unit_code;

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

	public String getEngineering_item() {
		return engineering_item;
	}

	public void setEngineering_item(String engineering_item) {
		this.engineering_item = engineering_item;
	}

	public String getContractor_unit() {
		return contractor_unit;
	}

	public void setContractor_unit(String contractor_unit) {
		this.contractor_unit = contractor_unit;
	}

	public String getContractor_unit_code() {
		return contractor_unit_code;
	}

	public void setContractor_unit_code(String contractor_unit_code) {
		this.contractor_unit_code = contractor_unit_code;
	}

}
