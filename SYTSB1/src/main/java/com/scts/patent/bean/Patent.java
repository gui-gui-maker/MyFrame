package com.scts.patent.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 专利管理
 * patent entity. 
 * @author XCB
 * @date 2019-02-28 
 */
@Entity
@Table(name = "TJY2_PATENT_MANAGE")
public class Patent implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;		// ID
	private String patent_num;//专利编号
	private String patent_name;//专利名称
	private String patent_brief;//专利简介
	private String patent_type;//专利类型
	private String patent_org;//发表部门
	private String invent_man;//第一作者
	private String invent_man_id;//第一作者
	private Date invent_date;//发表日期
	private String create_man;//创建人
	private String create_man_id;//创建人id
	private Date create_date;//创建日期
	private String invent_scend;//专利第二作者
	private String invent_scend_id;//专利第二作者
	private String invent_third;//专利第三作者
	private String invent_third_id;//专利第三作者
	private String other_man;//其他作者
	private String other_man_id;//其他作者
	private String data_status;//状态
	private String remark;//备注
	private Date invent_end;
	private String sure_man;//确认人
	private Date sure_date;//确认时间
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public String getPatent_num() {
		return patent_num;
	}

	public void setPatent_num(String patent_num) {
		this.patent_num = patent_num;
	}

	public String getPatent_name() {
		return patent_name;
	}

	public void setPatent_name(String patent_name) {
		this.patent_name = patent_name;
	}

	public String getPatent_brief() {
		return patent_brief;
	}

	public void setPatent_brief(String patent_brief) {
		this.patent_brief = patent_brief;
	}

	public String getPatent_type() {
		return patent_type;
	}

	public void setPatent_type(String patent_type) {
		this.patent_type = patent_type;
	}

	public String getPatent_org() {
		return patent_org;
	}

	public void setPatent_org(String patent_org) {
		this.patent_org = patent_org;
	}

	public String getInvent_man() {
		return invent_man;
	}

	public void setInvent_man(String invent_man) {
		this.invent_man = invent_man;
	}

	public String getInvent_man_id() {
		return invent_man_id;
	}

	public void setInvent_man_id(String invent_man_id) {
		this.invent_man_id = invent_man_id;
	}

	public Date getInvent_date() {
		return invent_date;
	}

	public void setInvent_date(Date invent_date) {
		this.invent_date = invent_date;
	}

	public String getCreate_man() {
		return create_man;
	}

	public void setCreate_man(String create_man) {
		this.create_man = create_man;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getInvent_scend() {
		return invent_scend;
	}

	public void setInvent_scend(String invent_scend) {
		this.invent_scend = invent_scend;
	}

	public String getInvent_scend_id() {
		return invent_scend_id;
	}

	public void setInvent_scend_id(String invent_scend_id) {
		this.invent_scend_id = invent_scend_id;
	}

	public String getInvent_third() {
		return invent_third;
	}

	public void setInvent_third(String invent_third) {
		this.invent_third = invent_third;
	}

	public String getInvent_third_id() {
		return invent_third_id;
	}

	public void setInvent_third_id(String invent_third_id) {
		this.invent_third_id = invent_third_id;
	}

	public String getOther_man() {
		return other_man;
	}

	public void setOther_man(String other_man) {
		this.other_man = other_man;
	}

	public String getOther_man_id() {
		return other_man_id;
	}

	public void setOther_man_id(String other_man_id) {
		this.other_man_id = other_man_id;
	}

	public String getData_status() {
		return data_status;
	}

	public void setData_status(String data_status) {
		this.data_status = data_status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getInvent_end() {
		return invent_end;
	}

	public void setInvent_end(Date invent_end) {
		this.invent_end = invent_end;
	}

	public String getSure_man() {
		return sure_man;
	}

	public Date getSure_date() {
		return sure_date;
	}

	public void setSure_man(String sure_man) {
		this.sure_man = sure_man;
	}

	public void setSure_date(Date sure_date) {
		this.sure_date = sure_date;
	}

	public String getCreate_man_id() {
		return create_man_id;
	}

	public void setCreate_man_id(String create_man_id) {
		this.create_man_id = create_man_id;
	}


}
