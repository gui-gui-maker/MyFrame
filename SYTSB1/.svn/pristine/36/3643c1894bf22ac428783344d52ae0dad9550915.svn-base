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
 * 告知书土建工程监理或验收单位
 * 
 * @author liming 2014-5-9
 * 
 */

@Entity
@Table(name = "TZSB_APP_SUPERVISION_UNIT")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TzsbAppSupervisionUnit implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8504693234735729548L;
	private String id;
	private String fk_tzsb_application_id;
	private String supervision_itme;
	private String fk_supervision_unit_id;
	private String supervision_unit_name;
	private String supervision_unit_code;

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

	public String getSupervision_itme() {
		return supervision_itme;
	}

	public void setSupervision_itme(String supervision_itme) {
		this.supervision_itme = supervision_itme;
	}

	public String getFk_supervision_unit_id() {
		return fk_supervision_unit_id;
	}

	public void setFk_supervision_unit_id(String fk_supervision_unit_id) {
		this.fk_supervision_unit_id = fk_supervision_unit_id;
	}

	public String getSupervision_unit_name() {
		return supervision_unit_name;
	}

	public void setSupervision_unit_name(String supervision_unit_name) {
		this.supervision_unit_name = supervision_unit_name;
	}

	public String getSupervision_unit_code() {
		return supervision_unit_code;
	}

	public void setSupervision_unit_code(String supervision_unit_code) {
		this.supervision_unit_code = supervision_unit_code;
	}

}
