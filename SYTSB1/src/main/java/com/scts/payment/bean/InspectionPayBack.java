package com.scts.payment.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 报检业务退款信息表
 * InspectionPayBack entity. 
 * @author GaoYa
 * @date 2014-05-04 16:02:00
 */
@Entity
@Table(name = "TZSB_INSPECTION_PAY_BACK")
public class InspectionPayBack implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	//流水号
	private String fk_inspection_info_id;	// 报检信息ID
	private String cash_back;	// 退款金额
	private Date back_date;		// 退款时间
	private String receive_man;	// 接受退款人
	private String fk_company_id;	// 单位ID
	private String com_name;		// 接受退款单位
	private String remark;			// 备注

	private List<InspectionInfoDTO>  inspectionInfoDTOList;
	private List<InspectionZZJDPayInfoDTO>  inspectionZZJDPayInfoDTOList;
	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	@Column(name="FK_INSPECTION_INFO_ID")
	public String getFk_inspection_info_id() {
		return fk_inspection_info_id;
	}

	public void setFk_inspection_info_id(String fk_inspection_info_id) {
		this.fk_inspection_info_id = fk_inspection_info_id;
	}

	@Column(name="CASH_BACK")
	public String getCash_back() {
		return cash_back;
	}

	public void setCash_back(String cash_back) {
		this.cash_back = cash_back;
	}

	@Column(name="BACK_DATE")
	public Date getBack_date() {
		return back_date;
	}

	public void setBack_date(Date back_date) {
		this.back_date = back_date;
	}

	@Column(name="RECEIVE_MAN")
	public String getReceive_man() {
		return receive_man;
	}

	public void setReceive_man(String receive_man) {
		this.receive_man = receive_man;
	}

	@Column(name="FK_COMPANY_ID")
	public String getFk_company_id() {
		return fk_company_id;
	}

	public void setFk_company_id(String fk_company_id) {
		this.fk_company_id = fk_company_id;
	}

	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Transient
	public List<InspectionInfoDTO> getInspectionInfoDTOList() {
		return inspectionInfoDTOList;
	}

	public void setInspectionInfoDTOList(
			List<InspectionInfoDTO> inspectionInfoDTOList) {
		this.inspectionInfoDTOList = inspectionInfoDTOList;
	}

	@Transient
	public List<InspectionZZJDPayInfoDTO> getInspectionZZJDPayInfoDTOList() {
		return inspectionZZJDPayInfoDTOList;
	}

	public void setInspectionZZJDPayInfoDTOList(
			List<InspectionZZJDPayInfoDTO> inspectionZZJDPayInfoDTOList) {
		this.inspectionZZJDPayInfoDTOList = inspectionZZJDPayInfoDTOList;
	}

	@Column(name="COM_NAME")
	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	
	
}
