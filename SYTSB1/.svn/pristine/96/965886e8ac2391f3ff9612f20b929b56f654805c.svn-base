package com.lsts.expert.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name="TJY2_EXPERT_PRE")
public class ExpertPre implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String person_type;//机选专家组
	private String person_type_name;//机选专家组名字
	private String person_id;//人员id
	private String person_name;//人员姓名
	private Date create_date;//创建时间
	private Date del_date;//删除时间
	private String data_status;//1 正常 99 删除

	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPerson_type() {
		return person_type;
	}
	public void setPerson_type(String person_type) {
		this.person_type = person_type;
	}
	public String getPerson_type_name() {
		return person_type_name;
	}
	public void setPerson_type_name(String person_type_name) {
		this.person_type_name = person_type_name;
	}
	public String getPerson_id() {
		return person_id;
	}
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}
	public String getPerson_name() {
		return person_name;
	}
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getDel_date() {
		return del_date;
	}
	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}
	public String getData_status() {
		return data_status;
	}
	public void setData_status(String data_status) {
		this.data_status = data_status;
	}

	
	
}
