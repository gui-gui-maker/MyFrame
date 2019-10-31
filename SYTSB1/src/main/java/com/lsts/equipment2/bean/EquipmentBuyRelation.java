package com.lsts.equipment2.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 设备申请关联表
 * EquipmentBuyRelation entity. 
 * @author GaoYa
 * @date 2014-02-18 09:29:00
 */
@Entity
@Table(name = "BASE_EQUIPMENT2_APPLY_RELA")
public class EquipmentBuyRelation implements BaseEntity{

	private static final long serialVersionUID = 1L;
	private String id;	// ID
	//private String fk_equipment_id;	// 关联办公器具ID
	//private String fk_apply_id;	// 关联申请ID
	private String apply_count;	// 申请数量
	private String need_return;	// 是否需要归还（入库），码表：BASE_EQ_NEED
	private String return_count;// 归还（入库）数量
	private String repair_com;	// 维修单位
	private Double repair_price;// 维修费用
	private String repair_requirement;// 维修要求
	private Date conduct_date;	// 维修日期、停用日期、报废日期、借用日期
	private Date create_date;	// 创建时间
	private String create_by;	// 创建人
	private Date last_modify_date;	// 最后更新时间
	private String last_modify_by;	// 最后更新人
	private String equipment_id;	// 设备ID
	private String apply_id;	// 申请ID

	public void setId(String id) {
		this.id = id;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	public void setLast_modify_by(String last_modify_by) {
		this.last_modify_by = last_modify_by;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public void setLast_modify_date(Date last_modify_date) {
		this.last_modify_date = last_modify_date;
	}
	public void setApply_count(String apply_count) {
		this.apply_count = apply_count;
	}
	public void setNeed_return(String need_return) {
		this.need_return = need_return;
	}
	public void setReturn_count(String return_count) {
		this.return_count = return_count;
	}
	public void setConduct_date(Date conduct_date) {
		this.conduct_date = conduct_date;
	}
	public void setRepair_com(String repair_com) {
		this.repair_com = repair_com;
	}
	public void setRepair_price(Double repair_price) {
		this.repair_price = repair_price;
	}
	public void setRepair_requirement(String repair_requirement) {
		this.repair_requirement = repair_requirement;
	}
	public void setEquipment_id(String equipment_id) {
		this.equipment_id = equipment_id;
	}
	public void setApply_id(String apply_id) {
		this.apply_id = apply_id;
	}
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
	public String getId() {
		return id;
	}
	@Column(name ="CREATE_BY",unique=true,nullable=false,insertable=true,updatable=true,length=32)
	public String getCreate_by() {
		return create_by;
	}
	@Column(name ="LAST_MODIFY_BY",unique=true,nullable=false,insertable=true,updatable=true,length=32)
	public String getLast_modify_by() {
		return last_modify_by;
	}
	@Column(name ="CREATE_DATE",unique=true,nullable=false,insertable=true,updatable=true,length=32)
	public Date getCreate_date() {
		return create_date;
	}
	@Column(name ="LAST_MODIFY_DATE",unique=true,nullable=false,insertable=true,updatable=true,length=32)
	public Date getLast_modify_date() {
		return last_modify_date;
	}
	
	/*public String getFk_equipment_id() {
		return fk_equipment_id;
	}
	public void setFk_equipment_id(String fk_equipment_id) {
		this.fk_equipment_id = fk_equipment_id;
	}
	public String getFk_apply_id() {
		return fk_apply_id;
	}
	public void setFk_apply_id(String fk_apply_id) {
		this.fk_apply_id = fk_apply_id;
	}*/
	@Column(name ="APPLY_COUNT",unique=true,nullable=false,insertable=true,updatable=true,length=32)
	public String getApply_count() {
		return apply_count;
	}
	@Column(name ="NEED_RETURN",unique=true,nullable=false,insertable=true,updatable=true,length=32)
	public String getNeed_return() {
		return need_return;
	}
	@Column(name ="RETURN_COUNT",unique=true,nullable=false,insertable=true,updatable=true,length=32)
	public String getReturn_count() {
		return return_count;
	}
	@Column(name ="CONDUCT_DATE",unique=true,nullable=false,insertable=true,updatable=true,length=32)	
	public Date getConduct_date() {
		return conduct_date;
	}
	@Column(name ="REPAIR_COM",unique=true,nullable=false,insertable=true,updatable=true,length=32)
	public String getRepair_com() {
		return repair_com;
	}
	@Column(name ="REPAIR_PRICE",unique=true,nullable=false,insertable=true,updatable=true,length=32)
	public Double getRepair_price() {
		return repair_price;
	}
	@Column(name ="REPAIR_REQUIREMENT",unique=true,nullable=false,insertable=true,updatable=true,length=32)
	public String getRepair_requirement() {
		return repair_requirement;
	}
	@Column(name ="EQUIPMENT_ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
	public String getEquipment_id() {
		return equipment_id;
	}
	@Column(name ="APPLY_ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
	public String getApply_id() {
		return apply_id;
	}
	
}
