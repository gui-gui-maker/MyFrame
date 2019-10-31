package com.lsts.inspection.bean;


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
 * 
 * 大厅报检数据
 * 
 * @author 肖慈边 2014-2-13
 * 
 */

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tzsb_inspection_hall")
public class InspectionHall implements BaseEntity {

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
	
	private List<InspectionHallPara>  paraList;

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
	
	public Collection<InspectionHallPara> hallPara;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "inspectionHall", orphanRemoval = true)
	public Collection<InspectionHallPara> getHallPara() {
		return hallPara;
	}

	public void setHallPara(Collection<InspectionHallPara> hallPara) {
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
	@Transient
	public List<InspectionHallPara> getParaList() {
		return paraList;
	}
	public void setParaList(List<InspectionHallPara> paraList) {
		this.paraList = paraList;
	}

}
