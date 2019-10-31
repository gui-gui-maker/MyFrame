package com.lsts.inspection.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 报告页单独审核信息表
 * ReportPageCheckInfo entity. 
 * @author GaoYa
 * @date 2015-10-17 08:50:00
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tzsb_report_page_check")
public class ReportPageCheckInfo implements BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String id ;
	private String fk_inspection_info_id;	// 业务信息ID
	private String fk_report_id;			// 报告类型ID
	private String fk_report_item_id;		// 报告索引ID（报告页对象ID）
	
	private String page_index;				// 报告页索引
	private String page_name;				// 报告页名称

	private String audit_user_id;			// 单独审核人ID（用于报告，要使用电子签名，故此处使用Employee ID）
	private String audit_user_name;			// 单独审核人姓名
	
	private String create_user_id;			// 创建人ID
	private String create_user_name;		// 创建人姓名
	private Date   create_date;				// 创建日期
	
	private String check_user_id;			// 审核人ID（用于报告，要使用电子签名，故此处使用Employee ID）
	private String check_user_name;			// 审核人姓名
	private String check_desc;				// 审核备注
	private Date   check_date;				// 审核日期
	
	private String mdy_user_id;				// 最后修改人ID
	private String mdy_user_name;			// 最后修改人姓名
	private Date   mdy_date;				// 最后修改日期
	
	private String data_status;				// 数据状态（数据字典REPORT_PAGE_STATUS，0：待审核 1：审核通过 2：审核不通过 99：删除）
	
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
	public String getFk_report_item_id() {
		return fk_report_item_id;
	}
	public void setFk_report_item_id(String fk_report_item_id) {
		this.fk_report_item_id = fk_report_item_id;
	}
	public String getPage_index() {
		return page_index;
	}
	public void setPage_index(String page_index) {
		this.page_index = page_index;
	}
	public String getPage_name() {
		return page_name;
	}
	public void setPage_name(String page_name) {
		this.page_name = page_name;
	}
	public String getAudit_user_id() {
		return audit_user_id;
	}
	public void setAudit_user_id(String audit_user_id) {
		this.audit_user_id = audit_user_id;
	}
	public String getAudit_user_name() {
		return audit_user_name;
	}
	public void setAudit_user_name(String audit_user_name) {
		this.audit_user_name = audit_user_name;
	}
	public String getCheck_user_id() {
		return check_user_id;
	}
	public void setCheck_user_id(String check_user_id) {
		this.check_user_id = check_user_id;
	}
	public String getCheck_user_name() {
		return check_user_name;
	}
	public void setCheck_user_name(String check_user_name) {
		this.check_user_name = check_user_name;
	}
	public Date getCheck_date() {
		return check_date;
	}
	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}
	public String getCreate_user_id() {
		return create_user_id;
	}
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}
	public String getCreate_user_name() {
		return create_user_name;
	}
	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getMdy_user_id() {
		return mdy_user_id;
	}
	public void setMdy_user_id(String mdy_user_id) {
		this.mdy_user_id = mdy_user_id;
	}
	public String getMdy_user_name() {
		return mdy_user_name;
	}
	public void setMdy_user_name(String mdy_user_name) {
		this.mdy_user_name = mdy_user_name;
	}
	public Date getMdy_date() {
		return mdy_date;
	}
	public void setMdy_date(Date mdy_date) {
		this.mdy_date = mdy_date;
	}
	public String getData_status() {
		return data_status;
	}
	public void setData_status(String data_status) {
		this.data_status = data_status;
	}
	public String getCheck_desc() {
		return check_desc;
	}
	public void setCheck_desc(String check_desc) {
		this.check_desc = check_desc;
	}
	
	
}
