package com.lsts.org.bean;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * 单位类型中间表
 * 
 * @author 肖慈边 2014-1-21
 * 
 */
@Entity
@Table(name = "base_com_type_relation")
@JsonIgnoreProperties(ignoreUnknown = true, value = { "enterInfo" })
public class EnterRelation implements BaseEntity  {
	
	private static final long serialVersionUID = 1L;
	
//	private String	com_id	;	//	公司ID
	private String	com_type	;	//	单位区分类型
	
	private String	id	;	//	id
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public EnterInfo enterInfo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "com_id")
	public EnterInfo getEnterInfo() {
		return enterInfo;
	}

	public void setEnterInfo(EnterInfo enterInfo) {
		this.enterInfo = enterInfo;
	}
//	public String getCom_id() {
//		return com_id;
//	}
//	public void setCom_id(String com_id) {
//		this.com_id = com_id;
//	}
	public String getCom_type() {
		return com_type;
	}
	public void setCom_type(String com_type) {
		this.com_type = com_type;
	}
}
