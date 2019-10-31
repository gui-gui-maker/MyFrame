package com.lsts.hall.bean;


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

/*******************************************************************************
 * @author Mr.Dawn
 * @date 2014-12-22
 * @summary 大厅报检业
 */

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tzsb_inspection_hall")
public class ReportHall implements BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String	id	;	//	
	private String	hall_no	;	//	报检单号
	private String	com_name	;	//	报检公司名称
	private String	inspection_type	;	//	报检类型
	private String	fee_detail	;	//	费用情况
	private String	remark	;	//	备注
	private String	reg_op	;	//	登记人员
	private Date	reg_date;	//	登记日期
	private String	flow_status	;	//	流转状态
	private String	data_status	;	//	流转状态
	private String  back_reason;	//  退回原因
	
	private String	com_op	;	//	受理方式
	private String	area_code	;	//	所属区域
	private String	dep_address	;	//	单位地址
	private String	contant_person	;	//	联系人
	private String	contant_phone	;	//	联系人手机
	
	private List<ReportHallPara>  paraList;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	// 关联流转对象
	
	public Collection<ReportHallPara> hallPara;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "reportHall", orphanRemoval = true)
	public Collection<ReportHallPara> getHallPara() {
		return hallPara;
	}

	public void setHallPara(Collection<ReportHallPara> hallPara) {
		this.hallPara = hallPara;
	}
	public String getHall_no() {
		return hall_no;
	}
	public void setHall_no(String hall_no) {
		this.hall_no = hall_no;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getInspection_type() {
		return inspection_type;
	}
	public void setInspection_type(String inspection_type) {
		this.inspection_type = inspection_type;
	}
	public String getFee_detail() {
		return fee_detail;
	}
	public void setFee_detail(String fee_detail) {
		this.fee_detail = fee_detail;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getReg_op() {
		return reg_op;
	}
	public void setReg_op(String reg_op) {
		this.reg_op = reg_op;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		
		this.reg_date = reg_date;
	}
	public String getFlow_status() {
		return flow_status;
	}
	public void setFlow_status(String flow_status) {
		this.flow_status = flow_status;
	}
	public String getData_status() {
		return data_status;
	}
	public void setData_status(String data_status) {
		this.data_status = data_status;
	}
	
	public String getCom_op() {
		return com_op;
	}
	public void setCom_op(String com_op) {
		this.com_op = com_op;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public String getDep_address() {
		return dep_address;
	}
	public void setDep_address(String dep_address) {
		this.dep_address = dep_address;
	}
	public String getContant_person() {
		return contant_person;
	}
	public void setContant_person(String contant_person) {
		this.contant_person = contant_person;
	}
	public String getContant_phone() {
		return contant_phone;
	}
	public void setContant_phone(String contant_phone) {
		this.contant_phone = contant_phone;
	}
	@Transient
	public List<ReportHallPara> getParaList() {
		return paraList;
	}
	public void setParaList(List<ReportHallPara> paraList) {
		this.paraList = paraList;
	}
	public String getBack_reason() {
		return back_reason;
	}
	public void setBack_reason(String back_reason) {
		this.back_reason = back_reason;
	}

}
