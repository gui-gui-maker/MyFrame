package com.fwxm.contract.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name="CONTRACT_INFO")
public class ContractInfo implements BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//
	private Float amount;//金额（万）
	private String contract_no;//合同编号
	private String contract_property;//合同性质（1 公开合同，0 非公开合同）
	private String contract_type;//合同类型（1 普通合同，2 分包合同）
	private String custom_com_name;//签订合同单位
	private String dev_op;//发展部合同经办人
	private String dev_op_id;//发展部合同经办人id
	private String file_status;//合同存档情况
	private String fk_contract_id;//总合同id
	private String fk_contract_no;//总合同编号
	private String fk_customer_id;//客户信息id

	private String inspect_depart;//检验部门
	private String inspect_depart_id;//检验部门id
	private String inspect_op;//检验部门经办人
	private String inspect_op_id;//检验部门经办人id
	private String project_name;//项目名称
	private String remark;//备注
	private String sign_op;//合同鉴定人
	private String sign_op_id;//合同鉴定人id
	private Date sign_time;//合同签订时间
	private String data_status;//数据状态
	private String doc_ids;//附件id
	private String audit_step;//审核所在步骤（0 起草 1 审批中 2 审批完成 3 已归档）
	private Date effect_date;//到期日期
	private Date negotiate_date;//谈判时间
	private String negotiate_place;//谈判地点
	private String negotiate_recorder;//谈判记录人
	private String negotiate_parter;//谈判参加人员
	private String negotiate_contain;//谈判内容
	private String negotiate_recorder_id;
	private String negotiate_parter_id;//谈判参加人员
	private String pay_sure;//收费情况确认
	private Date file_date;//归档日期
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	public String getContract_property() {
		return contract_property;
	}

	public void setContract_property(String contract_property) {
		this.contract_property = contract_property;
	}

	public String getContract_type() {
		return contract_type;
	}

	public void setContract_type(String contract_type) {
		this.contract_type = contract_type;
	}

	public String getCustom_com_name() {
		return custom_com_name;
	}

	public void setCustom_com_name(String custom_com_name) {
		this.custom_com_name = custom_com_name;
	}

	public String getDev_op() {
		return dev_op;
	}

	public void setDev_op(String dev_op) {
		this.dev_op = dev_op;
	}

	public String getDev_op_id() {
		return dev_op_id;
	}

	public void setDev_op_id(String dev_op_id) {
		this.dev_op_id = dev_op_id;
	}

	public String getFile_status() {
		return file_status;
	}

	public void setFile_status(String file_status) {
		this.file_status = file_status;
	}

	public String getFk_contract_id() {
		return fk_contract_id;
	}

	public void setFk_contract_id(String fk_contract_id) {
		this.fk_contract_id = fk_contract_id;
	}

	public String getFk_customer_id() {
		return fk_customer_id;
	}

	public void setFk_customer_id(String fk_customer_id) {
		this.fk_customer_id = fk_customer_id;
	}

	public String getInspect_depart() {
		return inspect_depart;
	}

	public void setInspect_depart(String inspect_depart) {
		this.inspect_depart = inspect_depart;
	}

	public String getInspect_depart_id() {
		return inspect_depart_id;
	}

	public void setInspect_depart_id(String inspect_depart_id) {
		this.inspect_depart_id = inspect_depart_id;
	}

	public String getInspect_op() {
		return inspect_op;
	}

	public void setInspect_op(String inspect_op) {
		this.inspect_op = inspect_op;
	}

	public String getInspect_op_id() {
		return inspect_op_id;
	}

	public void setInspect_op_id(String inspect_op_id) {
		this.inspect_op_id = inspect_op_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Date getSign_time() {
		return sign_time;
	}

	public void setSign_time(Date sign_time) {
		this.sign_time = sign_time;
	}

	public String getData_status() {
		return data_status;
	}

	public void setData_status(String data_status) {
		this.data_status = data_status;
	}

	public String getDoc_ids() {
		return doc_ids;
	}

	public void setDoc_ids(String doc_ids) {
		this.doc_ids = doc_ids;
	}

	public String getAudit_step() {
		return audit_step;
	}

	public void setAudit_step(String audit_step) {
		this.audit_step = audit_step;
	}

	public String getFk_contract_no() {
		return fk_contract_no;
	}

	public void setFk_contract_no(String fk_contract_no) {
		this.fk_contract_no = fk_contract_no;
	}

	public Date getEffect_date() {
		return effect_date;
	}

	public void setEffect_date(Date effect_date) {
		this.effect_date = effect_date;
	}

	public Date getNegotiate_date() {
		return negotiate_date;
	}

	public void setNegotiate_date(Date negotiate_date) {
		this.negotiate_date = negotiate_date;
	}

	public String getNegotiate_place() {
		return negotiate_place;
	}

	public void setNegotiate_place(String negotiate_place) {
		this.negotiate_place = negotiate_place;
	}

	public String getNegotiate_recorder() {
		return negotiate_recorder;
	}

	public void setNegotiate_recorder(String negotiate_recorder) {
		this.negotiate_recorder = negotiate_recorder;
	}

	public String getNegotiate_parter() {
		return negotiate_parter;
	}

	public void setNegotiate_parter(String negotiate_parter) {
		this.negotiate_parter = negotiate_parter;
	}

	public String getNegotiate_contain() {
		return negotiate_contain;
	}

	public void setNegotiate_contain(String negotiate_contain) {
		this.negotiate_contain = negotiate_contain;
	}

	public String getNegotiate_recorder_id() {
		return negotiate_recorder_id;
	}

	public void setNegotiate_recorder_id(String negotiate_recorder_id) {
		this.negotiate_recorder_id = negotiate_recorder_id;
	}

	public String getNegotiate_parter_id() {
		return negotiate_parter_id;
	}

	public void setNegotiate_parter_id(String negotiate_parter_id) {
		this.negotiate_parter_id = negotiate_parter_id;
	}

	public String getPay_sure() {
		return pay_sure;
	}

	public void setPay_sure(String pay_sure) {
		this.pay_sure = pay_sure;
	}

	public Date getFile_date() {
		return file_date;
	}

	public void setFile_date(Date file_date) {
		this.file_date = file_date;
	}

	
	
}
