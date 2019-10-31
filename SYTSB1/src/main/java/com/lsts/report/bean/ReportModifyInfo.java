package com.lsts.report.bean;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 设备和报告修改数据记录主表
 * ReportModifyInfo entity. 
 * @author GaoYa
 * @date 2017-11-21 16:31:00
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tzsb_report_modify_info")
public class ReportModifyInfo implements BaseEntity{
	private static final long serialVersionUID = 1L;
	private String id;			// 流水号	
	private String info_ids;	// 报告业务ID（多个以逗号分隔）
	private String modify_type;	// 修改类型（1：设备 2：报告 3：设备和报告）
	private String op_user_id;	// 修改人ID
	private String op_user_name;// 修改人姓名
	private Date   op_date;		// 修改时间
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Collection<ReportModifyRecord> reportModifyRecord;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "reportModifyInfo", orphanRemoval = true)
	public Collection<ReportModifyRecord> getReportModifyRecord() {
		return reportModifyRecord;
	}

	public void setReportModifyRecord(Collection<ReportModifyRecord> reportModifyRecord) {
		this.reportModifyRecord = reportModifyRecord;
	}
	
	public String getModify_type() {
		return modify_type;
	}
	public void setModify_type(String modify_type) {
		this.modify_type = modify_type;
	}
	public String getInfo_ids() {
		return info_ids;
	}
	public void setInfo_ids(String info_ids) {
		this.info_ids = info_ids;
	}
	public String getOp_user_id() {
		return op_user_id;
	}
	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}
	public String getOp_user_name() {
		return op_user_name;
	}
	public void setOp_user_name(String op_user_name) {
		this.op_user_name = op_user_name;
	}
	public Date getOp_date() {
		return op_date;
	}
	public void setOp_date(Date op_date) {
		this.op_date = op_date;
	}
	
}
