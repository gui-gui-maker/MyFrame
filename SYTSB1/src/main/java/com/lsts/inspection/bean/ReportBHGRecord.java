package com.lsts.inspection.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 报告检验项目不合格问题来源记录表
 * ReportItemBHGRecord entity. 
 * @author GaoYa
 * @date 2016-01-06 11:16:00
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tzsb_report_bhg_record")
public class ReportBHGRecord implements BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String id ;
	private String bhg_name;		// 问题来源key（代码）
	private String bhg_value;		// 问题来源值
	private String item_type;		// 数据类型
	private String fk_inspection_info_id;	// 检验业务信息ID
	private String last_mdy_uid;	// 最后修改用户ID
	private String last_mdy_uname;  // 最后修改用户姓名
	private String last_mdy_time;   // 最后修改时间 
	private String data_status;		// 数据状态，默认0（0：新建 1：修改 99：删除）
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getLast_mdy_uid() {
		return last_mdy_uid;
	}
	public void setLast_mdy_uid(String last_mdy_uid) {
		this.last_mdy_uid = last_mdy_uid;
	}
	public String getLast_mdy_uname() {
		return last_mdy_uname;
	}
	public void setLast_mdy_uname(String last_mdy_uname) {
		this.last_mdy_uname = last_mdy_uname;
	}
	public String getLast_mdy_time() {
		return last_mdy_time;
	}
	public void setLast_mdy_time(String last_mdy_time) {
		this.last_mdy_time = last_mdy_time;
	}
	public String getData_status() {
		return data_status;
	}
	public void setData_status(String data_status) {
		this.data_status = data_status;
	}
	public String getBhg_name() {
		return bhg_name;
	}
	public void setBhg_name(String bhg_name) {
		this.bhg_name = bhg_name;
	}
	public String getBhg_value() {
		return bhg_value;
	}
	public void setBhg_value(String bhg_value) {
		this.bhg_value = bhg_value;
	}
	
}
