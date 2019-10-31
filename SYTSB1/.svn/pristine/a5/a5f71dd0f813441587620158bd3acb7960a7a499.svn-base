package com.lsts.inspection.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * 报告配置数据
 * 
 * @author 肖慈边 2014-2-21
 * 
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "base_reports_per_audit")
public class ReportPerAudit implements BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String	id	;	//	ID
	
	private String	fk_report_id	;	//	报告ID
	
	private String	fk_inspection_info_id;	//	报检信息ID
	private String	item_name	;	//项目名称
	private String	item_value	;	//项目值
	private String	item_value_id	;	//项目值ID

	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFk_report_id() {
		return fk_report_id;
	}
	public void setFk_report_id(String fk_report_id) {
		this.fk_report_id = fk_report_id;
	}
	public String getFk_inspection_info_id() {
		return fk_inspection_info_id;
	}
	public void setFk_inspection_info_id(String fk_inspection_info_id) {
		this.fk_inspection_info_id = fk_inspection_info_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_value() {
		return item_value;
	}
	public void setItem_value(String item_value) {
		this.item_value = item_value;
	}
	public String getItem_value_id() {
		return item_value_id;
	}
	public void setItem_value_id(String item_value_id) {
		this.item_value_id = item_value_id;
	}
	
	
	
	

}
