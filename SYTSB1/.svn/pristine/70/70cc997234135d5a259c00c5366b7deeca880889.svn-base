package com.lsts.report.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 报告打印记录表
 * ReportPrintLog entity. 
 * @author GaoYa
 * @date 2017-08-28 17:05:00
 */
@Entity
@Table(name = "TZSB_REPORT_PRINT_LOG")
public class ReportPrintLog implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String info_id;	    	// 报告业务ID
	private String report_sn;	    // 报告书编号
	private Integer print_count;	// 打印份数
	private String com_name; 		// 使用单位名称
	private String org_id;      	// 检验部门ID
	private String org_name;		// 检验部门名称
	private String print_type;		// 报告打印类型（0：报告打印 1：补办打印 2：纠错打印 3：换证打印 4：其他打印 ）
	private String print_type_name;	// 报告打印类型名称（0：报告打印 1：补办打印 2：纠错打印 3：换证打印 4：其他打印 ）
	private String print_user_id;	// 打印人ID
	private String print_user_name;	// 打印人姓名
	private Date   print_time;		// 打印时间
	private String remark;			// 备注
	
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getReport_sn() {
		return report_sn;
	}
	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getInfo_id() {
		return info_id;
	}
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}
	public Integer getPrint_count() {
		return print_count;
	}
	public void setPrint_count(Integer print_count) {
		this.print_count = print_count;
	}
	public String getPrint_type() {
		return print_type;
	}
	public void setPrint_type(String print_type) {
		this.print_type = print_type;
	}
	public String getPrint_user_id() {
		return print_user_id;
	}
	public void setPrint_user_id(String print_user_id) {
		this.print_user_id = print_user_id;
	}
	public String getPrint_user_name() {
		return print_user_name;
	}
	public void setPrint_user_name(String print_user_name) {
		this.print_user_name = print_user_name;
	}
	public Date getPrint_time() {
		return print_time;
	}
	public void setPrint_time(Date print_time) {
		this.print_time = print_time;
	}
	public String getPrint_type_name() {
		return print_type_name;
	}
	public void setPrint_type_name(String print_type_name) {
		this.print_type_name = print_type_name;
	}
	
}
