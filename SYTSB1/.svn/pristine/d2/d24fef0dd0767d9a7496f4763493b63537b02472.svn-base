package com.lsts.finance.bean;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;


@Entity
@Table(name="TJY2_CW_BILL")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CwBill implements BaseEntity{
	
	private String id;//
	private String create_op_id;//创建人ID
	private String create_op;//创建人
	private Date create_time;//创建时间
	private String apply_op_id;//申请人ID
	private String apply_op;//申请人姓名
	private Date apply_time;//申请时间
	private String num;//份数
	private String audit_op_id;//审核人ID
	private String audit_op;//审核人姓名
	private Date audit_time;//审核时间
	private String audit_opinion;//审核意见
	private String audit_remark;//审核备注
	private String remark;//申请备注
	private String bill_num;//申请取得票据编号
	private String return_bill_num;//退回票据号段
	private String return_op_id;//退回接受人ID
	private String return_op;//退回接受人姓名
	private String return_op_remark;//接受人备注
	private Date return_time;//退回时间
	private Date return_received_time;//退回接受时间
	private String return_op_opinion;//接受人意见
	private String return_remark;//退回备注
	private String data_status;//数据状态
	private String apply_org_id;//申请部门
	private String apply_org;//申请部门id
	
	
	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Collection<CwBillPara> billPara;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "cwBill", orphanRemoval = true)
	public Collection<CwBillPara> getBillPara() {
		return billPara;
	}
	public void setBillPara(Collection<CwBillPara> billPara) {
		this.billPara = billPara;
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
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getApply_op_id() {
		return apply_op_id;
	}
	public void setApply_op_id(String apply_op_id) {
		this.apply_op_id = apply_op_id;
	}
	public String getApply_op() {
		return apply_op;
	}
	public void setApply_op(String apply_op) {
		this.apply_op = apply_op;
	}
	public Date getApply_time() {
		return apply_time;
	}
	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getAudit_op_id() {
		return audit_op_id;
	}
	public void setAudit_op_id(String audit_op_id) {
		this.audit_op_id = audit_op_id;
	}
	public String getAudit_op() {
		return audit_op;
	}
	public void setAudit_op(String audit_op) {
		this.audit_op = audit_op;
	}
	public Date getAudit_time() {
		return audit_time;
	}
	public void setAudit_time(Date audit_time) {
		this.audit_time = audit_time;
	}
	public String getAudit_opinion() {
		return audit_opinion;
	}
	public void setAudit_opinion(String audit_opinion) {
		this.audit_opinion = audit_opinion;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBill_num() {
		return bill_num;
	}
	public void setBill_num(String bill_num) {
		this.bill_num = bill_num;
	}
	public String getReturn_bill_num() {
		return return_bill_num;
	}
	public void setReturn_bill_num(String return_bill_num) {
		this.return_bill_num = return_bill_num;
	}
	public String getReturn_op_id() {
		return return_op_id;
	}
	public void setReturn_op_id(String return_op_id) {
		this.return_op_id = return_op_id;
	}
	public String getReturn_op() {
		return return_op;
	}
	public void setReturn_op(String return_op) {
		this.return_op = return_op;
	}
	public Date getReturn_time() {
		return return_time;
	}
	public void setReturn_time(Date return_time) {
		this.return_time = return_time;
	}
	public Date getReturn_received_time() {
		return return_received_time;
	}
	public void setReturn_received_time(Date return_received_time) {
		this.return_received_time = return_received_time;
	}
	/**
	 * @return the return_remark
	 */
	public String getReturn_remark() {
		return return_remark;
	}
	/**
	 * @param return_remark the return_remark to set
	 */
	public void setReturn_remark(String return_remark) {
		this.return_remark = return_remark;
	}
	/**
	 * @return the audit_remark
	 */
	public String getAudit_remark() {
		return audit_remark;
	}
	/**
	 * @param audit_remark the audit_remark to set
	 */
	public void setAudit_remark(String audit_remark) {
		this.audit_remark = audit_remark;
	}
	/**
	 * @return the data_status
	 */
	public String getData_status() {
		return data_status;
	}
	/**
	 * @param data_status the data_status to set
	 */
	public void setData_status(String data_status) {
		this.data_status = data_status;
	}
	public String getApply_org_id() {
		return apply_org_id;
	}
	public void setApply_org_id(String apply_org_id) {
		this.apply_org_id = apply_org_id;
	}
	public String getApply_org() {
		return apply_org;
	}
	public void setApply_org(String apply_org) {
		this.apply_org = apply_org;
	}
	/**
	 * @return the return_op_remark
	 */
	public String getReturn_op_remark() {
		return return_op_remark;
	}
	/**
	 * @param return_op_remark the return_op_remark to set
	 */
	public void setReturn_op_remark(String return_op_remark) {
		this.return_op_remark = return_op_remark;
	}
	/**
	 * @return the return_op_opinion
	 */
	public String getReturn_op_opinion() {
		return return_op_opinion;
	}
	/**
	 * @param return_op_opinion the return_op_opinion to set
	 */
	public void setReturn_op_opinion(String return_op_opinion) {
		this.return_op_opinion = return_op_opinion;
	}	
	
}
