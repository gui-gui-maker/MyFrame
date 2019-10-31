package com.lsts.equipment2.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 供货商信息
 * EquipmentSupplier entity. 
 * @author GaoYa
 * @date 2014-02-17 16:58:00
 */
@Entity
@Table(name = "TJY2_EQUIP_SUPPLIER")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EquipmentSupplier implements BaseEntity{

	private static final long serialVersionUID = 1L;
	private String id;	// ID
	private String supplier_name;		// 供应商名称
	private String supplier_tel;		// 供货商电话
	private String supplier_contacts;	// 供应商联系人
	private String supplier_contacts_tel;	// 供应商联系人电话
	private Date cooperation_begin;	// 开始合作时间
	private String supplier_judge_level;	// 供货商评价等级 码表：BASE_EQ_SUPPLIER_LEVEL
	private String supplier_judge_desc;	// 供货商评价描述
	private Date create_date;	// 创建时间
	private String create_by;	// 创建人
	private Date last_modify_date;	// 最后更新时间
	private String last_modify_by;	// 最后更新人

	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name ="SUPPLIER_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	public String getSupplier_name() {
		return supplier_name;
	}
	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	@Column(name ="SUPPLIER_TEL",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	public String getSupplier_tel() {
		return supplier_tel;
	}
	public void setSupplier_tel(String supplier_tel) {
		this.supplier_tel = supplier_tel;
	}
	@Column(name ="SUPPLIER_CONTACTS",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	public String getSupplier_contacts() {
		return supplier_contacts;
	}
	public void setSupplier_contacts(String supplier_contacts) {
		this.supplier_contacts = supplier_contacts;
	}
	@Column(name = "SUPPLIER_CONTACTS_TEL")
	public String getSupplier_contacts_tel() {
		return supplier_contacts_tel;
	}
	public void setSupplier_contacts_tel(String supplier_contacts_tel) {
		this.supplier_contacts_tel = supplier_contacts_tel;
	}
	@Column(name ="SUPPLIER_JUDGE_LEVEL",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	public String getSupplier_judge_level() {
		return supplier_judge_level;
	}
	public void setSupplier_judge_level(String supplier_judge_level) {
		this.supplier_judge_level = supplier_judge_level;
	}
	@Column(name ="SUPPLIER_JUDGE_DESC",unique=false,nullable=true,insertable=true,updatable=true,length=2048)
	public String getSupplier_judge_desc() {
		return supplier_judge_desc;
	}
	public void setSupplier_judge_desc(String supplier_judge_desc) {
		this.supplier_judge_desc = supplier_judge_desc;
	}
	@Column(name ="CREATE_BY",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	public String getCreate_by() {
		return create_by;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	@Column(name ="LAST_MODIFY_BY",unique=false,nullable=true,insertable=true,updatable=true,length=32)
	public String getLast_modify_by() {
		return last_modify_by;
	}
	public void setLast_modify_by(String last_modify_by) {
		this.last_modify_by = last_modify_by;
	}
	@Column(name ="COOPERATION_BEGIN",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public Date getCooperation_begin() {
		return cooperation_begin;
	}
	public void setCooperation_begin(Date cooperation_begin) {
		this.cooperation_begin = cooperation_begin;
	}
	@Column(name ="CREATE_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	@Column(name ="LAST_MODIFY_DATE",unique=false,nullable=true,insertable=true,updatable=true,length=7)
	public Date getLast_modify_date() {
		return last_modify_date;
	}
	public void setLast_modify_date(Date last_modify_date) {
		this.last_modify_date = last_modify_date;
	}
	
}
