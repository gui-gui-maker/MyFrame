package com.lsts.pact.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;


/*******************************************************************************
 * 
 * 合同数据
 * @author 肖慈边 2016-1-19
 * 
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "base_rule_system")

public class RuleInfo implements BaseEntity{
	
	
	private static final long serialVersionUID = 1L;
	private String	id	;	//	ID
	private String	file_num	;//合同编号
	private String	file_name	;//合同单位ID
	private String	file_cont	;//合同单位ID
	private String	create_name	;//合同单位ID
	private Date	create_date	;//合同单位ID


    @Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getFile_num() {
		return file_num;
	}
	public void setFile_num(String file_num) {
		this.file_num = file_num;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_cont() {
		return file_cont;
	}
	public void setFile_cont(String file_cont) {
		this.file_cont = file_cont;
	}
	public String getCreate_name() {
		return create_name;
	}
	public void setCreate_name(String create_name) {
		this.create_name = create_name;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	



}
