package com.scts.cspace.space.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;
@Entity
@Table(name="TJYPT_SPACE_EXPAND")
public class TjyptSpaceExpand implements BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//
	private String audit_user_id;//审核人id
	private Date audit_date;//审核时间
	private String audit_conclusion;//审核结论（0不通过 1通过）
	private String apply_expand;//希望申请的容积,单位G
	private String apply_user_id;//申请人id
	private String audit_desc;//审核说明
	private Date apply_date;//申请时间
	private Float audit_expand;//审核人允许的容积,单位G
	private String audit_user_name;//审核人
	private String apply_user_name;//申请人
	private String apply_desc;//申请说明
	private String step;//步骤

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAudit_user_id() {
		return audit_user_id;
	}

	public void setAudit_user_id(String audit_user_id) {
		this.audit_user_id = audit_user_id;
	}

	public Date getAudit_date() {
		return audit_date;
	}

	public void setAudit_date(Date audit_date) {
		this.audit_date = audit_date;
	}

	public String getAudit_conclusion() {
		return audit_conclusion;
	}

	public void setAudit_conclusion(String audit_conclusion) {
		this.audit_conclusion = audit_conclusion;
	}

	public String getApply_expand() {
		return apply_expand;
	}

	public void setApply_expand(String apply_expand) {
		this.apply_expand = apply_expand;
	}

	public String getApply_user_id() {
		return apply_user_id;
	}

	public void setApply_user_id(String apply_user_id) {
		this.apply_user_id = apply_user_id;
	}

	public String getAudit_desc() {
		return audit_desc;
	}

	public void setAudit_desc(String audit_desc) {
		this.audit_desc = audit_desc;
	}

	public Date getApply_date() {
		return apply_date;
	}

	public void setApply_date(Date apply_date) {
		this.apply_date = apply_date;
	}

	public Float getAudit_expand() {
		return audit_expand;
	}

	public void setAudit_expand(Float audit_expand) {
		this.audit_expand = audit_expand;
	}

	public String getAudit_user_name() {
		return audit_user_name;
	}

	public void setAudit_user_name(String audit_user_name) {
		this.audit_user_name = audit_user_name;
	}

	public String getApply_user_name() {
		return apply_user_name;
	}

	public void setApply_user_name(String apply_user_name) {
		this.apply_user_name = apply_user_name;
	}

	public String getApply_desc() {
		return apply_desc;
	}

	public void setApply_desc(String apply_desc) {
		this.apply_desc = apply_desc;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	
	
}
