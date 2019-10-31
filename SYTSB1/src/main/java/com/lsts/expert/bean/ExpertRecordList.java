package com.lsts.expert.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name="TJY2_EXPERT_RECODE_LIST")
public class ExpertRecordList implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8708890823866879578L;
	private String id;
	private String fk_tjy2_expert_record_id;
	private String expert_id;//专家id
	
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFk_tjy2_expert_record_id() {
		return fk_tjy2_expert_record_id;
	}
	public void setFk_tjy2_expert_record_id(String fk_tjy2_expert_record_id) {
		this.fk_tjy2_expert_record_id = fk_tjy2_expert_record_id;
	}
	public String getExpert_id() {
		return expert_id;
	}
	public void setExpert_id(String expert_id) {
		this.expert_id = expert_id;
	}

	
	
}
