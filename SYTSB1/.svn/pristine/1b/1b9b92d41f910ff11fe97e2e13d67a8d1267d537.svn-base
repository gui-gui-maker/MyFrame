package com.lsts.equipment2.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.transaction.annotation.Transactional;

import com.khnt.core.crud.bean.BaseEntity;



@Entity
@Table(name = "TJY2_EQUIPMENT_LOAN_CENTRE ")
@JsonIgnoreProperties(ignoreUnknown = true,value={"equipmentLoan"})
public class EquipmentCentre implements BaseEntity{

	private String id; //注键ID
	private String fk_equipment_id;//设备ID
	private String fk_box_id;//设备箱ID
	
	private EquipmentLoan equipmentLoan;
	
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name ="ID",unique=false,nullable=false,insertable=true,updatable=true,length=32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name ="FK_EQUIPMENT_ID",unique=true,nullable=true,insertable=true,updatable=true,length=32)
	public String getFk_equipment_id() {
		return fk_equipment_id;
	}
	public void setFk_equipment_id(String fk_equipment_id) {
		this.fk_equipment_id = fk_equipment_id;
	}
	@Column(name ="FK_BOX_ID",unique=true,nullable=true,insertable=true,updatable=true,length=32)
	public String getFk_box_id() {
		return fk_box_id;
	}
	public void setFk_box_id(String fk_box_id) {
		this.fk_box_id = fk_box_id;
	}
	
	@ManyToOne
	@JoinColumn(name="FK_RECORD_ID")
	public EquipmentLoan getEquipmentLoan() {
		return equipmentLoan;
	}
	public void setEquipmentLoan(EquipmentLoan equipmentLoan) {
		this.equipmentLoan = equipmentLoan;
	}

	
	
	
	
	
	
	
}
