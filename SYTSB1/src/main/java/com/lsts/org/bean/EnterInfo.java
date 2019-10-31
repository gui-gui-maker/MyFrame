package com.lsts.org.bean;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;
import com.lsts.device.bean.DeviceDTO;
import com.lsts.device.bean.DeviceDocument;

/*******************************************************************************
 * 
 * 单位信息
 * 
 * @author 肖慈边 2014-1-21
 * 
 */

@Entity
@Table(name = "base_company_info")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnterInfo implements BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private String	id	;	//	id
	private String	com_code	;	//	组织机构代码
	private String	com_name	;	//	单位名称
	private String	com_type	;	//	单位类型

	private String	com_legal_rep	;	//	法定代表人
	private String	com_area_code	;	//	地区编码
	private String	com_address	;	//	单位地址
	private String	com_zip_code	;	//	邮政编码
	private String	com_tel	;	//	单位电话
	private String	com_contact_man	;	//	联系人
	private String	com_fax	;	//	传真
	private String	com_email	;	//	电子信箱
	private String	com_web_site	;	//	网址
	private String	security_depart	;	//	安全管理部门
	private String	security	;	//	安全管理人员
	private String	qualification_certificate	;	//	资格证书
	private String	qualification_certificate_no	;	//	资格证书号
	private Date	com_fund_date	;	//	成立日期
	private String	approve_depart	;	//	批准成立机关
	private String	licence_depart	;	//	营业执照登记机构
	private String	com_unit_type	;	//	经济类型
	private String	employee_count	;	//	单位总人数(人)
	private String	registered_funds	;	//	注册资金(万元)
	private String	permanent_assets	;	//	固定资产(万元)
	private String	manager_deputy	;	//	管理者代表
	private String	manager_deputy_pos	;	//	管理者代表职位
	private String	quality_principal	;	//	质量保证负责人
	private String	charge_depart	;	//	主管部门
	private Date	com_begin_date	;	//	开始工作日期
	private String	com_status	;	//	状态
	private String	authorize_dapart	;	//	授权维护单位
	private String	if_province_manage	;	//	是否省级管理
	private String	remark	;	//	备注
	private String	created_by	;	//	创建人
	private Date	created_date	;	//	创建时间
	private String	last_upd_by	;	//	最后更新人
	private Date	last_upd_date	;	//	最后更新时间
	private String	licence_reg_no	;	//	营业执照注册号
	private String	import_id	;	//	导入数据是原数据库ID
	private String	fk_parent_id	;	//	上级机构ID
	private String	send_status	;	//	上级机构ID
	
	private List<DeviceDocument>  deviceList;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
//	
//	// 关联中间表对象
//	public Collection<EnterRelation> enterRelation;
//
//
//	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "enterInfo", orphanRemoval = true)
//	public Collection<EnterRelation> getEnterRelation() {
//		return enterRelation;
//	}
//
//	public void setEnterRelation(Collection<EnterRelation> enterRelation) {
//		this.enterRelation = enterRelation;
//	}
	
	
	public String getCom_code() {
		return com_code;
	}
	public void setCom_code(String com_code) {
		this.com_code = com_code;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getCom_legal_rep() {
		return com_legal_rep;
	}
	public void setCom_legal_rep(String com_legal_rep) {
		this.com_legal_rep = com_legal_rep;
	}
	public String getCom_area_code() {
		return com_area_code;
	}
	public void setCom_area_code(String com_area_code) {
		this.com_area_code = com_area_code;
	}
	public String getCom_address() {
		return com_address;
	}
	public void setCom_address(String com_address) {
		this.com_address = com_address;
	}
	public String getCom_zip_code() {
		return com_zip_code;
	}
	public void setCom_zip_code(String com_zip_code) {
		this.com_zip_code = com_zip_code;
	}
	public String getCom_tel() {
		return com_tel;
	}
	public void setCom_tel(String com_tel) {
		this.com_tel = com_tel;
	}
	public String getCom_contact_man() {
		return com_contact_man;
	}
	public void setCom_contact_man(String com_contact_man) {
		this.com_contact_man = com_contact_man;
	}
	public String getCom_fax() {
		return com_fax;
	}
	public void setCom_fax(String com_fax) {
		this.com_fax = com_fax;
	}
	public String getCom_email() {
		return com_email;
	}
	public void setCom_email(String com_email) {
		this.com_email = com_email;
	}
	public String getCom_web_site() {
		return com_web_site;
	}
	public void setCom_web_site(String com_web_site) {
		this.com_web_site = com_web_site;
	}
	public String getSecurity_depart() {
		return security_depart;
	}
	public void setSecurity_depart(String security_depart) {
		this.security_depart = security_depart;
	}
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}
	public String getQualification_certificate() {
		return qualification_certificate;
	}
	public void setQualification_certificate(String qualification_certificate) {
		this.qualification_certificate = qualification_certificate;
	}
	public String getQualification_certificate_no() {
		return qualification_certificate_no;
	}
	public void setQualification_certificate_no(String qualification_certificate_no) {
		this.qualification_certificate_no = qualification_certificate_no;
	}
	public Date getCom_fund_date() {
		return com_fund_date;
	}
	public void setCom_fund_date(Date com_fund_date) {
		this.com_fund_date = com_fund_date;
	}
	public String getApprove_depart() {
		return approve_depart;
	}
	public void setApprove_depart(String approve_depart) {
		this.approve_depart = approve_depart;
	}
	public String getLicence_depart() {
		return licence_depart;
	}
	public void setLicence_depart(String licence_depart) {
		this.licence_depart = licence_depart;
	}
	public String getCom_unit_type() {
		return com_unit_type;
	}
	public void setCom_unit_type(String com_unit_type) {
		this.com_unit_type = com_unit_type;
	}
	public String getEmployee_count() {
		return employee_count;
	}
	public void setEmployee_count(String employee_count) {
		this.employee_count = employee_count;
	}
	public String getRegistered_funds() {
		return registered_funds;
	}
	public void setRegistered_funds(String registered_funds) {
		this.registered_funds = registered_funds;
	}
	public String getPermanent_assets() {
		return permanent_assets;
	}
	public void setPermanent_assets(String permanent_assets) {
		this.permanent_assets = permanent_assets;
	}
	public String getManager_deputy() {
		return manager_deputy;
	}
	public void setManager_deputy(String manager_deputy) {
		this.manager_deputy = manager_deputy;
	}
	public String getManager_deputy_pos() {
		return manager_deputy_pos;
	}
	public void setManager_deputy_pos(String manager_deputy_pos) {
		this.manager_deputy_pos = manager_deputy_pos;
	}
	public String getQuality_principal() {
		return quality_principal;
	}
	public void setQuality_principal(String quality_principal) {
		this.quality_principal = quality_principal;
	}
	public String getCharge_depart() {
		return charge_depart;
	}
	public void setCharge_depart(String charge_depart) {
		this.charge_depart = charge_depart;
	}
	public Date getCom_begin_date() {
		return com_begin_date;
	}
	public void setCom_begin_date(Date com_begin_date) {
		this.com_begin_date = com_begin_date;
	}
	public String getCom_status() {
		return com_status;
	}
	public void setCom_status(String com_status) {
		this.com_status = com_status;
	}
	public String getAuthorize_dapart() {
		return authorize_dapart;
	}
	public void setAuthorize_dapart(String authorize_dapart) {
		this.authorize_dapart = authorize_dapart;
	}
	public String getIf_province_manage() {
		return if_province_manage;
	}
	public void setIf_province_manage(String if_province_manage) {
		this.if_province_manage = if_province_manage;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getLast_upd_by() {
		return last_upd_by;
	}
	public void setLast_upd_by(String last_upd_by) {
		this.last_upd_by = last_upd_by;
	}
	public Date getLast_upd_date() {
		return last_upd_date;
	}
	public void setLast_upd_date(Date last_upd_date) {
		this.last_upd_date = last_upd_date;
	}
	public String getLicence_reg_no() {
		return licence_reg_no;
	}
	public void setLicence_reg_no(String licence_reg_no) {
		this.licence_reg_no = licence_reg_no;
	}
	public String getImport_id() {
		return import_id;
	}
	public void setImport_id(String import_id) {
		this.import_id = import_id;
	}
	public String getFk_parent_id() {
		return fk_parent_id;
	}
	public void setFk_parent_id(String fk_parent_id) {
		this.fk_parent_id = fk_parent_id;
	}
	public String getCom_type() {
		return com_type;
	}
	public void setCom_type(String com_type) {
		this.com_type = com_type;
	}
	
	@Transient
	public List<DeviceDocument> getDeviceList() {
		return deviceList;
	}
	public void setDeviceList(List<DeviceDocument> deviceList) {
		this.deviceList = deviceList;
	}
	public String getSend_status() {
		return send_status;
	}
	public void setSend_status(String send_status) {
		this.send_status = send_status;
	}
}
