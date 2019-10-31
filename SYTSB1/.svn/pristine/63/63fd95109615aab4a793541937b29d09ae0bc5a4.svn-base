package com.lsts.report.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * 报告项目数据
 * 
 * @author 肖慈边 2014-1-24
 * 
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "base_reports_itme")
public class ReportItem implements BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String	id	;	//	ID
	private String	fk_reports_id	;	// 报告ID
	private String	cert_code;			// 证书编号
	private String	is_audit_man	;	// 是否单独审核人（1：是 2：否）
	private String	is_inspect_man	;	// 是否单独检验员（1：是 2：否）
	private String	is_eval_pic_man	;	// 是否单独评片人（1：是 2：否）
	
	private String is_electronic_seal_man ; //是否电子签章 （1：是 2：否）
	private String xzhou; //X轴
	private String yzhou; //Y轴
	
	
	
	private String	is_must	;			// 是否为必选项
	private String	page_index	;		// 对应页索引
	private String	itme_name	;		// 项目名称
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getFk_reports_id() {
		return fk_reports_id;
	}
	public void setFk_reports_id(String fk_reports_id) {
		this.fk_reports_id = fk_reports_id;
	}
	public String getCert_code() {
		return cert_code;
	}
	public void setCert_code(String cert_code) {
		this.cert_code = cert_code;
	}
	public String getIs_audit_man() {
		return is_audit_man;
	}
	public void setIs_audit_man(String is_audit_man) {
		this.is_audit_man = is_audit_man;
	}
	public String getIs_inspect_man() {
		return is_inspect_man;
	}
	public void setIs_inspect_man(String is_inspect_man) {
		this.is_inspect_man = is_inspect_man;
	}
	public String getIs_must() {
		return is_must;
	}
	public void setIs_must(String is_must) {
		this.is_must = is_must;
	}
	public String getPage_index() {
		return page_index;
	}
	public void setPage_index(String page_index) {
		this.page_index = page_index;
	}
	public String getItme_name() {
		return itme_name;
	}
	public void setItme_name(String itme_name) {
		this.itme_name = itme_name;
	}
	public String getIs_eval_pic_man() {
		return is_eval_pic_man;
	}
	public void setIs_eval_pic_man(String is_eval_pic_man) {
		this.is_eval_pic_man = is_eval_pic_man;
	}
	
	//set
	public String getIs_electronic_seal_man() {
		return is_electronic_seal_man;
	}
	public void setIs_electronic_seal_man(String is_electronic_seal_man) {
		this.is_electronic_seal_man = is_electronic_seal_man;
	}

	public String getXzhou() {
		return xzhou;
	}
	public void setXzhou(String xzhou) {
		this.xzhou = xzhou;
	}
	public String getYzhou() {
		return yzhou;
	}
	public void setYzhou(String yzhou) {
		this.yzhou = yzhou;
	}
}
