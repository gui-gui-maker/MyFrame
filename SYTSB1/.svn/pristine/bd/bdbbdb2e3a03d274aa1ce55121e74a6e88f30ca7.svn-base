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
@Table(name = "base_devie_pact")

public class PactInfo implements BaseEntity{
	
	
	private static final long serialVersionUID = 1L;
	private String	id	;	//	ID
	private String	pact_num	;//合同编号
	private String	pact_com_id	;//合同单位ID
	private String	pact_unit_id	;//合同单位ID
	private String	pact_name	;//合同名称
	private String	pact_com	;//合同单位	
	private Double	pact_money	;//合同金额	
	private String	pact_unit	;//合同部门	
	private String	create_name	;//创建人员	
	private Date	create_date	;//创建日期	
	private Date	pact_sign_date	;//合同签订日期	
	private Date	pact_end_date	;//合同终止日期	
	private String document_id;//合同文档ID
	private String document_name;//合同文档名称
    private byte document_doc[];//合同文档内容

    @Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getPact_num() {
		return pact_num;
	}

	public void setPact_num(String pact_num) {
		this.pact_num = pact_num;
	}

	public String getPact_name() {
		return pact_name;
	}

	public void setPact_name(String pact_name) {
		this.pact_name = pact_name;
	}

	public String getPact_com() {
		return pact_com;
	}

	public void setPact_com(String pact_com) {
		this.pact_com = pact_com;
	}

	

	public String getPact_unit() {
		return pact_unit;
	}

	public void setPact_unit(String pact_unit) {
		this.pact_unit = pact_unit;
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

	public String getPact_com_id() {
		return pact_com_id;
	}

	public void setPact_com_id(String pact_com_id) {
		this.pact_com_id = pact_com_id;
	}

	public Double getPact_money() {
		return pact_money;
	}

	public void setPact_money(Double pact_money) {
		this.pact_money = pact_money;
	}

	public String getPact_unit_id() {
		return pact_unit_id;
	}

	public void setPact_unit_id(String pact_unit_id) {
		this.pact_unit_id = pact_unit_id;
	}

	public Date getPact_sign_date() {
		return pact_sign_date;
	}

	public void setPact_sign_date(Date pact_sign_date) {
		this.pact_sign_date = pact_sign_date;
	}

	public Date getPact_end_date() {
		return pact_end_date;
	}

	public void setPact_end_date(Date pact_end_date) {
		this.pact_end_date = pact_end_date;
	}
	public String getDocument_id() {
		return document_id;
	}
	public void setDocument_id(String document_id) {
		this.document_id = document_id;
	}
	public String getDocument_name() {
		return document_name;
	}
	public void setDocument_name(String document_name) {
		this.document_name = document_name;
	}
	public byte[] getDocument_doc() {
		return document_doc;
	}
	public void setDocument_doc(byte[] document_doc) {
		this.document_doc = document_doc;
	}



}
