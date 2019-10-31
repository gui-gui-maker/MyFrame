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
import com.lsts.finance.bean.CwBankDTO;

/**
 * 检验业务收费信息表
 * InspectionPayInfo entity. 
 * @author GaoYa
 * @date 2014-05-04 16:05:00
 */
@Entity
@Table(name = "TZSB_INSPECTION_PAY_INFO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InspectionPayInfo implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	//流水号
	private String fk_inspection_info_id;	// 报检信息ID
	private String pay_no;		// 编号/合同号
	private String pay_receive;	// 应收总金额
	private String pay_received;	// 实收总金额
	private String cash_pay;	// 实际收款额（现金缴费金额，2014-11-07修改）
	
	// 缴费方式（数据字典：PAY_TYPE） 1 现金缴费 2 银行转账 3 免收费（判废免收）
	private String pay_type;	// 4 现金及转账 5 pos机刷卡 6 现金及pos机刷卡 7 转账及pos机刷卡 8 上缴财政 9 承兑汇票
	
	private String invoice_no;	// 发票号
	private Date pay_date;		// 缴费日期
	private Date pay_date1;		// 销票日期
	private String payment_status;	// 缴费状态 0 默认 1 待收费 2 已收费 99 取消收费
	private String fk_company_id;	// 缴费（开票）单位ID
	private String company_name;	// 缴费（开票）单位名称
	private String pay_man_name;	// 缴费人姓名
	private String receive_man_name;	// 收费人姓名
	private Date created_date;	// 创建日期
	private String created_by;	// 创建人
	private String remark;		// 备注（转账缴费金额，2014-11-07修改）
	private String fk_cw_bank_id;	// 转账记录id
	private String pos;				// POS机刷卡金额
	private String hand_in;			// 上缴财政金额（2016-05-04周静要求增加）
	private String draft;			// 承兑汇票金额（2017-03-30周静要求增加）
	private Integer report_count;	// 报告数量
	private String report_com_id;	// 受检单位id
	private String report_com_name;	// 受检单位名称
	private String check_dep_id;		// 检验部门ID
	private String check_dep_name;	// 检验部门名称
	private String device_type;  	// 设备类型（机电：JD 承压：CY）
	private String old_info_id;		// 原检验业务id
	private String pact_id;			// 合同ID
	private String pact_name;		// 合同名称	
	private String report_sn;		// 报告书编号
	private String pay_desc;		// 情况说明（2017-12-05刘思娟要求增加，用于外借收费等情况说明）
	
	private String tel;				//经办人电话号码
	private String alipay_mobile;	//平板（支付宝支付）
	private String wechat_mobile;	//平板（微信支付）
	
	/*智能一体机支付业务*/
	private String fk_alipay_trade_id;	// 支付宝交易记录id
	private String alipay_money;		// 支付宝交易金额
	private String fk_weixin_trade_id;	// 微信交易记录id
	private String weixin_money;		// 微信交易金额
	
	private String batch; 
	
	
	private List<InspectionInfoDTO>  inspectionInfoDTOList;
	private List<InspectionZZJDPayInfoDTO>  inspectionZZJDPayInfoDTOList;
	private List<CwBankDTO>  cwBankDTOList;
	private List<MachinePayTrade>  machinePayTradeList;
	private List<PayInfoUnit>  payInfoUnits;

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
	
	@Column(name="CASH_PAY")
	public String getCash_pay() {
		return cash_pay;
	}

	public void setCash_pay(String cash_pay) {
		this.cash_pay = cash_pay;
	}

	@Column(name="PAY_RECEIVE")
	public String getPay_receive() {
		return pay_receive;
	}

	public void setPay_receive(String pay_receive) {
		this.pay_receive = pay_receive;
	}

	@Column(name="PAY_RECEIVED")
	public String getPay_received() {
		return pay_received;
	}

	public void setPay_received(String pay_received) {
		this.pay_received = pay_received;
	}

	@Column(name="INVOICE_NO")
	public String getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}

	@Column(name="PAY_DATE")
	public Date getPay_date() {
		return pay_date;
	}

	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}
	
	@Column(name="PAY_DATE1")
	public Date getPay_date1() {
		return pay_date1;
	}

	public void setPay_date1(Date pay_date1) {
		this.pay_date1 = pay_date1;
	}

	@Column(name="PAYMENT_STATUS")
	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	@Column(name="COMPANY_NAME")
	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	@Column(name="PAY_MAN_NAME")
	public String getPay_man_name() {
		return pay_man_name;
	}

	public void setPay_man_name(String pay_man_name) {
		this.pay_man_name = pay_man_name;
	}

	@Column(name="RECEIVE_MAN_NAME")
	public String getReceive_man_name() {
		return receive_man_name;
	}

	public void setReceive_man_name(String receive_man_name) {
		this.receive_man_name = receive_man_name;
	}

	@Column(name="CREATED_DATE")
	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	@Column(name="CREATED_BY")
	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
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

	@Column(name="PAY_TYPE")
	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	@Column(name="PAY_NO")
	public String getPay_no() {
		return pay_no;
	}

	public void setPay_no(String pay_no) {
		this.pay_no = pay_no;
	}
	
	@Column(name="POS")
	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
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
	
	@Transient
	public List<CwBankDTO> getCwBankDTOList() {
		return cwBankDTOList;
	}

	public void setCwBankDTOList(List<CwBankDTO> cwBankDTOList) {
		this.cwBankDTOList = cwBankDTOList;
	}

	@Column(name="FK_CW_BANK_ID")
	public String getFk_cw_bank_id() {
		return fk_cw_bank_id;
	}

	public void setFk_cw_bank_id(String fk_cw_bank_id) {
		this.fk_cw_bank_id = fk_cw_bank_id;
	}

	@Column(name="REPORT_COM_ID")
	public String getReport_com_id() {
		return report_com_id;
	}

	public void setReport_com_id(String report_com_id) {
		this.report_com_id = report_com_id;
	}

	@Column(name="REPORT_COM_NAME")
	public String getReport_com_name() {
		return report_com_name;
	}

	public void setReport_com_name(String report_com_name) {
		this.report_com_name = report_com_name;
	}

	@Column(name="CHECK_DEP_ID")
	public String getCheck_dep_id() {
		return check_dep_id;
	}

	public void setCheck_dep_id(String check_dep_id) {
		this.check_dep_id = check_dep_id;
	}

	@Column(name="CHECK_DEP_NAME")
	public String getCheck_dep_name() {
		return check_dep_name;
	}

	public void setCheck_dep_name(String check_dep_name) {
		this.check_dep_name = check_dep_name;
	}

	@Column(name="DEVICE_TYPE")
	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	@Column(name="REPORT_COUNT")
	public Integer getReport_count() {
		return report_count;
	}

	public void setReport_count(Integer report_count) {
		this.report_count = report_count;
	}


	@Column(name="OLD_INFO_ID")
	public String getOld_info_id() {
		return old_info_id;
	}

	public void setOld_info_id(String old_info_id) {
		this.old_info_id = old_info_id;
	}
	
	@Column(name="PACT_ID")
	public String getPact_id() {
		return pact_id;
	}

	public void setPact_id(String pact_id) {
		this.pact_id = pact_id;
	}

	@Column(name="PACT_NAME")
	public String getPact_name() {
		return pact_name;
	}

	public void setPact_name(String pact_name) {
		this.pact_name = pact_name;
	}
	
	@Column(name="HAND_IN")
	public String getHand_in() {
		return hand_in;
	}

	public void setHand_in(String hand_in) {
		this.hand_in = hand_in;
	}

	public String getReport_sn() {
		return report_sn;
	}

	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}

	public String getDraft() {
		return draft;
	}

	public void setDraft(String draft) {
		this.draft = draft;
	}

	public String getPay_desc() {
		return pay_desc;
	}

	public void setPay_desc(String pay_desc) {
		this.pay_desc = pay_desc;
	}

	@Transient
	public List<PayInfoUnit> getPayInfoUnits() {
		return payInfoUnits;
	}

	public void setPayInfoUnits(List<PayInfoUnit> payInfoUnits) {
		this.payInfoUnits = payInfoUnits;
	}

	public String getFk_alipay_trade_id() {
		return fk_alipay_trade_id;
	}

	public void setFk_alipay_trade_id(String fk_alipay_trade_id) {
		this.fk_alipay_trade_id = fk_alipay_trade_id;
	}

	public String getFk_weixin_trade_id() {
		return fk_weixin_trade_id;
	}

	public void setFk_weixin_trade_id(String fk_weixin_trade_id) {
		this.fk_weixin_trade_id = fk_weixin_trade_id;
	}

	@Transient
	public List<MachinePayTrade> getMachinePayTradeList() {
		return machinePayTradeList;
	}

	public void setMachinePayTradeList(List<MachinePayTrade> machinePayTradeList) {
		this.machinePayTradeList = machinePayTradeList;
	}

	public String getAlipay_money() {
		return alipay_money;
	}

	public void setAlipay_money(String alipay_money) {
		this.alipay_money = alipay_money;
	}

	public String getWeixin_money() {
		return weixin_money;
	}

	public void setWeixin_money(String weixin_money) {
		this.weixin_money = weixin_money;
	}

	@Column(name="TEL")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name="ALIPAY_MOBILE")
	public String getAlipay_mobile() {
		return alipay_mobile;
	}

	public void setAlipay_mobile(String alipay_mobile) {
		this.alipay_mobile = alipay_mobile;
	}

	@Column(name="WECHAT_MOBILE")
	public String getWechat_mobile() {
		return wechat_mobile;
	}

	public void setWechat_mobile(String wechat_mobile) {
		this.wechat_mobile = wechat_mobile;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}
	
	
}
