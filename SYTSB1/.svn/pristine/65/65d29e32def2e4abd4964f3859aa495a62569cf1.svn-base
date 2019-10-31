package com.scts.payment.bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 金额修改审批流程主表
 * InspectionChangeMoney entity. 
 * @author GaoYa
 * @date 2016-11-16 14:45:00
 */
@Entity
@Table(name = "TZSB_INSPECTION_CHANGE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InspectionChange implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;				// ID
	private String com_name;		// 使用单位
	private int    report_count;	// 报告份数
	private String remarks;			// 备注
	private String create_emp_id;	// 申请人ID
	private String create_emp_name;	// 申请人姓名
	private Date   create_date;		// 申请时间
	private String mdy_emp_id;		// 最后修改人ID
	private String mdy_emp_name;	// 最后修改人姓名
	private Date   mdy_date;		// 最后修改时间
	private String check_emp_id;	// 审核人ID
	private String check_emp_name;	// 审核人姓名
	private Date   check_date;		// 审核时间	
	private String data_status;		// 数据状态（0：未审核 1：审核通过 2：审核不通过 99：已作废）
	private String check_remark;	// 审核结果说明

	private List<InspectionChangeMoneyDTO>  changeMoneyDTOList;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public Collection<InspectionChangeMoney> changeMoneyInfo;
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "inspectionChange", orphanRemoval = true)
	public Collection<InspectionChangeMoney> getChangeMoneyInfo() {
		return changeMoneyInfo;
	}

	public void setChangeMoneyInfo(Collection<InspectionChangeMoney> changeMoneyInfo) {
		this.changeMoneyInfo = changeMoneyInfo;
	}


	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public int getReport_count() {
		return report_count;
	}

	public void setReport_count(int report_count) {
		this.report_count = report_count;
	}

	public String getCreate_emp_id() {
		return create_emp_id;
	}

	public void setCreate_emp_id(String create_emp_id) {
		this.create_emp_id = create_emp_id;
	}

	public String getCreate_emp_name() {
		return create_emp_name;
	}

	public void setCreate_emp_name(String create_emp_name) {
		this.create_emp_name = create_emp_name;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getMdy_emp_id() {
		return mdy_emp_id;
	}

	public void setMdy_emp_id(String mdy_emp_id) {
		this.mdy_emp_id = mdy_emp_id;
	}

	public String getMdy_emp_name() {
		return mdy_emp_name;
	}

	public void setMdy_emp_name(String mdy_emp_name) {
		this.mdy_emp_name = mdy_emp_name;
	}

	public Date getMdy_date() {
		return mdy_date;
	}

	public void setMdy_date(Date mdy_date) {
		this.mdy_date = mdy_date;
	}

	public String getCheck_emp_id() {
		return check_emp_id;
	}

	public void setCheck_emp_id(String check_emp_id) {
		this.check_emp_id = check_emp_id;
	}

	public String getCheck_emp_name() {
		return check_emp_name;
	}

	public void setCheck_emp_name(String check_emp_name) {
		this.check_emp_name = check_emp_name;
	}

	public Date getCheck_date() {
		return check_date;
	}

	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getData_status() {
		return data_status;
	}

	public void setData_status(String data_status) {
		this.data_status = data_status;
	}

	public String getCheck_remark() {
		return check_remark;
	}

	public void setCheck_remark(String check_remark) {
		this.check_remark = check_remark;
	}

	@Transient
	public List<InspectionChangeMoneyDTO> getChangeMoneyDTOList() {
		return changeMoneyDTOList;
	}

	public void setChangeMoneyDTOList(List<InspectionChangeMoneyDTO> changeMoneyDTOList) {
		this.changeMoneyDTOList = changeMoneyDTOList;
	}

}
