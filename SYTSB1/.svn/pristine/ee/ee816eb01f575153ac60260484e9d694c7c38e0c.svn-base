package com.scts.paper.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 论文管理
 * Paper entity. 
 * @author XCB
 * @date 2019-02-27 
 */
@Entity
@Table(name = "TJY2_PAPER_MANAGE")
public class Paper implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;		// ID
	private String paper_num;//论文编号
	private String paper_name;//论文名称
	private String paper_brief;//论文简介
	private String paper_type;//论文类型
	private String paper_org;//发表部门
	private String paper_man;//第一作者
	private String paper_man_id;//第一作者
	private Date paper_date;//发表日期
	private String create_man;//创建人
	private String create_man_id;//创建人ID
	private Date create_date;//创建日期
	private String paper_scend;//论文第二作者
	private String paper_scend_id;//论文第二作者
	private String paper_third;//论文第三作者
	private String paper_third_id;//论文第三作者
	private String other_man;//其他作者
	private String other_man_id;//其他作者
	private String data_status;//状态
	private String remark;//备注
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

	public String getPaper_num() {
		return paper_num;
	}

	public void setPaper_num(String paper_num) {
		this.paper_num = paper_num;
	}

	public String getPaper_name() {
		return paper_name;
	}

	public void setPaper_name(String paper_name) {
		this.paper_name = paper_name;
	}

	public String getPaper_brief() {
		return paper_brief;
	}

	public void setPaper_brief(String paper_brief) {
		this.paper_brief = paper_brief;
	}

	public String getPaper_type() {
		return paper_type;
	}

	public void setPaper_type(String paper_type) {
		this.paper_type = paper_type;
	}

	public String getPaper_org() {
		return paper_org;
	}

	public void setPaper_org(String paper_org) {
		this.paper_org = paper_org;
	}

	public String getPaper_man() {
		return paper_man;
	}

	public void setPaper_man(String paper_man) {
		this.paper_man = paper_man;
	}

	public Date getPaper_date() {
		return paper_date;
	}

	public void setPaper_date(Date paper_date) {
		this.paper_date = paper_date;
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

	public String getPaper_scend() {
		return paper_scend;
	}

	public void setPaper_scend(String paper_scend) {
		this.paper_scend = paper_scend;
	}

	public String getPaper_third() {
		return paper_third;
	}

	public void setPaper_third(String paper_third) {
		this.paper_third = paper_third;
	}

	public String getOther_man() {
		return other_man;
	}

	public void setOther_man(String other_man) {
		this.other_man = other_man;
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

	public String getPaper_man_id() {
		return paper_man_id;
	}

	public void setPaper_man_id(String paper_man_id) {
		this.paper_man_id = paper_man_id;
	}

	public String getPaper_scend_id() {
		return paper_scend_id;
	}

	public void setPaper_scend_id(String paper_scend_id) {
		this.paper_scend_id = paper_scend_id;
	}

	public String getPaper_third_id() {
		return paper_third_id;
	}

	public void setPaper_third_id(String paper_third_id) {
		this.paper_third_id = paper_third_id;
	}

	public String getOther_man_id() {
		return other_man_id;
	}

	public void setOther_man_id(String other_man_id) {
		this.other_man_id = other_man_id;
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
