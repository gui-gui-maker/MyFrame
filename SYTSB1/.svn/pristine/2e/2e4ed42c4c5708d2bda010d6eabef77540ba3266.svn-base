package com.lsts.inspection.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 报告检验项目
 * ReportItemValue entity. 
 * @author GaoYa
 * @date 2014-02-26 09:50:00
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tzsb_report_item_value")
public class ReportItemValue implements BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String id ;
	private String fk_report_id;	// 报告类型ID
	private String item_name;	// 项目指标名称
	private String item_value;	// 项目指标值
	private String item_type;	// 项目指标类型
	private String fk_inspection_info_id;	// 报检信息ID
	private String page_no;
	
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
	public String getItem_type() {
		return item_type;
	}
	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}
	public String getFk_inspection_info_id() {
		return fk_inspection_info_id;
	}
	public void setFk_inspection_info_id(String fk_inspection_info_id) {
		this.fk_inspection_info_id = fk_inspection_info_id;
	}
	public String getPage_no() {
		return page_no;
	}
	public void setPage_no(String page_no) {
		this.page_no = page_no;
	}
	
}
