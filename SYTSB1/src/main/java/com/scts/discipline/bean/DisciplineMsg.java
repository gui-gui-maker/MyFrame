package com.scts.discipline.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;
@Entity
@Table(name="TJY2_DISCIPLINE_MSG")
public class DisciplineMsg implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;			//	
	private String business_id;	//	报告id
	private String report_sn;		//	报告编号
	private String fk_com_id;		//	单位id
	private String com_name;	//	单位名称
	private String contact_man;		//	联系人
	private String phone_number;		//	电话号码
	private String content;			//	短信内容
	private String create_op;		//	创建人
	private String create_op_id;		//	创建人id
	private Date create_date;		//创建时间
	private String data_status;		//	99 删除
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	
	public String getReport_sn() {
		return report_sn;
	}

	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}

	public String getFk_com_id() {
		return fk_com_id;
	}

	public void setFk_com_id(String fk_com_id) {
		this.fk_com_id = fk_com_id;
	}

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public String getContact_man() {
		return contact_man;
	}

	public void setContact_man(String contact_man) {
		this.contact_man = contact_man;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	
	public String getCreate_op() {
		return create_op;
	}

	public void setCreate_op(String create_op) {
		this.create_op = create_op;
	}

	public String getCreate_op_id() {
		return create_op_id;
	}

	public void setCreate_op_id(String create_op_id) {
		this.create_op_id = create_op_id;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	
	public String getData_status() {
		return data_status;
	}

	public void setData_status(String data_status) {
		this.data_status = data_status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}


}
