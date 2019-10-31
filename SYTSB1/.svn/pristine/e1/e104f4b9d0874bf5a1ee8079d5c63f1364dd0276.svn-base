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
 * 告知书现场管理、专业、作业人员情况
 * 
 * @author liming 2014-5-9
 * 
 */

@Entity
@Table(name = "TZSB_APP_WORKERS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TzsbAppWorks implements BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2084955335603844571L;
	private String id;
	private String fk_tzsb_application_id;
	private String work_item;
	private String name;
	private String id_card;
	private String work_type;
	private String work_item_dis;

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

	public String getWork_item() {
		return work_item;
	}

	public void setWork_item(String work_item) {
		this.work_item = work_item;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public String getWork_type() {
		return work_type;
	}

	public void setWork_type(String work_type) {
		this.work_type = work_type;
	}

	public String getWork_item_dis() {
		return work_item_dis;
	}

	public void setWork_item_dis(String work_item_dis) {
		this.work_item_dis = work_item_dis;
	}

}
