package com.scts.payment.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 外借记录表
 * ReportBorrow entity. 
 * @author GaoYa
 * @date 2014-05-12 17:20:00
 */
@Entity
@Table(name = "TZSB_REPORT_BORROW")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportBorrow implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	//流水号
	private String fk_inspection_info_id;	// 报检信息ID
	private String fk_company_id;	// 单位ID
	private String com_name;		// 外借单位名称
	private String borrow_type;		// 外借类型，数据字典：TZSB_BORROW_TYPE（0：外借报告，1：外借发票，2：外借报告和发票）
	private String unpay_amount;	// 欠款金额
	private String invoice_no;		// 发票号
	private String borrow_name;		// 欠款人
	private String contack_number;	// 联系电话
	private Date borrow_date;		// 外借日期
	private String borrow_status;	// 状态，数据字典：TZSB_BORROW_STATUS（0：申请中，1：已批准，2：已借出 3：已取消外借）
	private String operator_user_id;// 操作人ID
	private String operator_name;	// 操作人
	private Date operator_date;		// 操作时间
	private String remark;			// 备注
	private String check_dep_id;	// 检验部门ID
	private String check_dep_name;	// 检验部门名称
	private String device_type;		// 设备类型（机电：JD 承压：CY）
	private String pay_no;			// 编号/合同号
	private String pact_id;			// 合同ID
	private String sign_leader_id;	// 签字主任ID
	private String sign_leader_name;// 签字主任姓名
	private String report_sn;		// 报告书编号
	private Date   repay_date;		// 还款日期
	
	private String unpay_amount_uppercase;	// 欠款金额大写
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

	@Column(name="BORROW_TYPE")
	public String getBorrow_type() {
		return borrow_type;
	}

	public void setBorrow_type(String borrow_type) {
		this.borrow_type = borrow_type;
	}

	@Column(name="UNPAY_AMOUNT")
	public String getUnpay_amount() {
		return unpay_amount;
	}

	public void setUnpay_amount(String unpay_amount) {
		this.unpay_amount = unpay_amount;
	}

	@Column(name="INVOICE_NO")
	public String getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}

	@Column(name="BORROW_NAME")
	public String getBorrow_name() {
		return borrow_name;
	}

	public void setBorrow_name(String borrow_name) {
		this.borrow_name = borrow_name;
	}

	@Column(name="CONTACK_NUMBER")
	public String getContack_number() {
		return contack_number;
	}

	public void setContack_number(String contack_number) {
		this.contack_number = contack_number;
	}

	@Column(name="BORROW_DATE")
	public Date getBorrow_date() {
		return borrow_date;
	}

	public void setBorrow_date(Date borrow_date) {
		this.borrow_date = borrow_date;
	}

	@Column(name="BORROW_STATUS")
	public String getBorrow_status() {
		return borrow_status;
	}

	public void setBorrow_status(String borrow_status) {
		this.borrow_status = borrow_status;
	}
	
	@Column(name="OPERATOR_NAME")
	public String getOperator_name() {
		return operator_name;
	}

	public void setOperator_name(String operator_name) {
		this.operator_name = operator_name;
	}

	@Column(name="OPERATOR_DATE")
	public Date getOperator_date() {
		return operator_date;
	}

	public void setOperator_date(Date operator_date) {
		this.operator_date = operator_date;
	}
	
	
	
	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	@Transient
	public String getUnpay_amount_uppercase() {
		return unpay_amount_uppercase;
	}

	public void setUnpay_amount_uppercase(String unpay_amount_uppercase) {
		this.unpay_amount_uppercase = unpay_amount_uppercase;
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

	public String getOperator_user_id() {
		return operator_user_id;
	}

	public void setOperator_user_id(String operator_user_id) {
		this.operator_user_id = operator_user_id;
	}

	public String getCheck_dep_id() {
		return check_dep_id;
	}

	public void setCheck_dep_id(String check_dep_id) {
		this.check_dep_id = check_dep_id;
	}

	public String getCheck_dep_name() {
		return check_dep_name;
	}

	public void setCheck_dep_name(String check_dep_name) {
		this.check_dep_name = check_dep_name;
	}

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public String getPay_no() {
		return pay_no;
	}

	public void setPay_no(String pay_no) {
		this.pay_no = pay_no;
	}

	public String getSign_leader_id() {
		return sign_leader_id;
	}

	public void setSign_leader_id(String sign_leader_id) {
		this.sign_leader_id = sign_leader_id;
	}

	public String getSign_leader_name() {
		return sign_leader_name;
	}

	public void setSign_leader_name(String sign_leader_name) {
		this.sign_leader_name = sign_leader_name;
	}

	public String getReport_sn() {
		return report_sn;
	}

	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}

	public String getPact_id() {
		return pact_id;
	}

	public void setPact_id(String pact_id) {
		this.pact_id = pact_id;
	}

	public Date getRepay_date() {
		return repay_date;
	}

	public void setRepay_date(Date repay_date) {
		this.repay_date = repay_date;
	}
	
}
