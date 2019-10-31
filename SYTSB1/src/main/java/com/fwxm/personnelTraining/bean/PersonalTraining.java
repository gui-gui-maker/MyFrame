package com.fwxm.personnelTraining.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;



@Entity
@Table(name = "ZL_PERSONAL_TRAINING")
public class PersonalTraining implements BaseEntity{
	
	private String id;//
	private String apply_op;//申请人
	private String apply_op_id;//申请人ID
	private String sn;//编号
	private String apply_org;//申请部门
	private String apply_org_id;//部门id
	private String create_op_id;//创建人id
	private String create_op;//创建人 
	private String apply_content;//培训类容
	private Date apply_date;//申请培训时间
	private String apply_reason;//申请理由
	private String remark;//备注
	private Date create_date;//填报时间
	private String org_audit_op;//部门审核人
	private String org_audit_op_id;//部门审核人ID
	private String org_audit_opinion;//部门审核意见
	private Date org_audit_date;//部门审核时间
	private String audit_op;//审核人
	private String audit_op_id;//审核人ID
	private String audit_opinion;//审核意见
	private Date audit_date;//审核时间
	private String sign_op;//审批人
	private String sign_op_id;//审批人ID
	private String sign_opinion;//审批意见
	private Date sign_date;//审批时间
	private String status;//
	
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApply_op() {
		return apply_op;
	}
	public void setApply_op(String apply_op) {
		this.apply_op = apply_op;
	}
	public String getApply_op_id() {
		return apply_op_id;
	}
	public void setApply_op_id(String apply_op_id) {
		this.apply_op_id = apply_op_id;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getApply_org() {
		return apply_org;
	}
	public void setApply_org(String apply_org) {
		this.apply_org = apply_org;
	}
	public String getApply_org_id() {
		return apply_org_id;
	}
	public void setApply_org_id(String apply_org_id) {
		this.apply_org_id = apply_org_id;
	}
	public String getCreate_op_id() {
		return create_op_id;
	}
	public void setCreate_op_id(String create_op_id) {
		this.create_op_id = create_op_id;
	}
	public String getCreate_op() {
		return create_op;
	}
	public void setCreate_op(String create_op) {
		this.create_op = create_op;
	}
	public String getApply_content() {
		return apply_content;
	}
	public void setApply_content(String apply_content) {
		this.apply_content = apply_content;
	}
	public Date getApply_date() {
		return apply_date;
	}
	public void setApply_date(Date apply_date) {
		this.apply_date = apply_date;
	}
	public String getApply_reason() {
		return apply_reason;
	}
	public void setApply_reason(String apply_reason) {
		this.apply_reason = apply_reason;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getOrg_audit_op() {
		return org_audit_op;
	}
	public void setOrg_audit_op(String org_audit_op) {
		this.org_audit_op = org_audit_op;
	}
	public String getOrg_audit_op_id() {
		return org_audit_op_id;
	}
	public void setOrg_audit_op_id(String org_audit_op_id) {
		this.org_audit_op_id = org_audit_op_id;
	}
	public String getOrg_audit_opinion() {
		return org_audit_opinion;
	}
	public void setOrg_audit_opinion(String org_audit_opinion) {
		this.org_audit_opinion = org_audit_opinion;
	}
	public Date getOrg_audit_date() {
		return org_audit_date;
	}
	public void setOrg_audit_date(Date org_audit_date) {
		this.org_audit_date = org_audit_date;
	}
	public String getAudit_op() {
		return audit_op;
	}
	public void setAudit_op(String audit_op) {
		this.audit_op = audit_op;
	}
	public String getAudit_op_id() {
		return audit_op_id;
	}
	public void setAudit_op_id(String audit_op_id) {
		this.audit_op_id = audit_op_id;
	}
	public Date getAudit_date() {
		return audit_date;
	}
	public void setAudit_date(Date audit_date) {
		this.audit_date = audit_date;
	}
	public String getSign_op() {
		return sign_op;
	}
	public void setSign_op(String sign_op) {
		this.sign_op = sign_op;
	}
	public String getSign_op_id() {
		return sign_op_id;
	}
	public void setSign_op_id(String sign_op_id) {
		this.sign_op_id = sign_op_id;
	}
	public String getSign_opinion() {
		return sign_opinion;
	}
	public void setSign_opinion(String sign_opinion) {
		this.sign_opinion = sign_opinion;
	}
	public Date getSign_date() {
		return sign_date;
	}
	public void setSign_date(Date sign_date) {
		this.sign_date = sign_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAudit_opinion() {
		return audit_opinion;
	}
	public void setAudit_opinion(String audit_opinion) {
		this.audit_opinion = audit_opinion;
	}
	

}
