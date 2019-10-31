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
 * 告知书现场施工组织
 * 
 * @author liming 2014-5-9
 * 
 */

@Entity
@Table(name = "TZSB_APP_CONSTRUCTION_ORG")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TzsbAppConstrucationOrg implements BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2677523497032613795L;
	private String id;
	private String fk_tzsb_application_id;
	private String fk_construction_units_id;
	private String construction_principal;
	private String construction_principal_mobil;
	private String technical_director;
	private String technical_director_mobil;
	private String construction_units_name;
	
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
	public String getFk_construction_units_id() {
		return fk_construction_units_id;
	}
	public void setFk_construction_units_id(String fk_construction_units_id) {
		this.fk_construction_units_id = fk_construction_units_id;
	}
	public String getConstruction_principal() {
		return construction_principal;
	}
	public void setConstruction_principal(String construction_principal) {
		this.construction_principal = construction_principal;
	}
	public String getConstruction_principal_mobil() {
		return construction_principal_mobil;
	}
	public void setConstruction_principal_mobil(String construction_principal_mobil) {
		this.construction_principal_mobil = construction_principal_mobil;
	}
	public String getTechnical_director() {
		return technical_director;
	}
	public void setTechnical_director(String technical_director) {
		this.technical_director = technical_director;
	}
	public String getTechnical_director_mobil() {
		return technical_director_mobil;
	}
	public void setTechnical_director_mobil(String technical_director_mobil) {
		this.technical_director_mobil = technical_director_mobil;
	}
	public String getConstruction_units_name() {
		return construction_units_name;
	}
	public void setConstruction_units_name(String construction_units_name) {
		this.construction_units_name = construction_units_name;
	}


}
