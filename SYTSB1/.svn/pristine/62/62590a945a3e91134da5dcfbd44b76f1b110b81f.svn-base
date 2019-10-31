package com.scts.payment.order.bean;

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
 * 智能一体机缴费支付订单
 * MachinePayOrder entity. 
 * @author GaoYa
 * @date 2018-06-11 16:18:00
 */
@Entity
@Table(name = "machine_pay_order")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MachinePayOrder implements BaseEntity{

	
	private static final long serialVersionUID = 1L;
	
	private String id;				// ID
	private String order_no;		// 缴费单号（订单编号）
	private String info_ids;		// 报告业务ID
	private String pay_title;		// 支付标题（支付描述）
	private Double pay_total;		// 订单金额
	private String pay_type;		// 支付方式（alipay：支付宝 weixin：微信）
	private Date   pay_start_time;	// 支付开始时间（缴费支付单创建时间）
	private Date   pay_end_time;	// 支付结束时间（支付超时期限）
	private Date   pay_time;		// 支付时间
	private String report_sn;		// 支付报告编号（多个以逗号分隔）
	private Integer pay_count;		// 支付报告份数
	private String pay_device_type;	// 支付设备类型（3：电梯...）
	private String phone_num;		// 支付人联系电话
	private String com_name;		// 支付人单位名称（开票单位）
	private String sign_file;		// 支付人签名文件（path）
	private String pay_status;		// 支付状态（unpay：未支付 payed：已支付）
	private String pay_no;			// 支付单号（交易单号）
	private String pay_account;		// 支付帐号
	private Double payed_total;		// 支付金额
	private String data_status;		// 数据状态（0：正常  98：用户取消  99：支付超时已作废）	
	private String op_ip;			// 支付终端IP地址
	private String machine_num;		// 支付终端编号
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
	
	public Collection<MachinePayInfo> machinePayInfo;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "machinePayOrder", orphanRemoval = true)
	public Collection<MachinePayInfo> getMachinePayInfo() {
		return machinePayInfo;
	}

	public void setMachinePayInfo(Collection<MachinePayInfo> machinePayInfo) {
		this.machinePayInfo = machinePayInfo;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getReport_sn() {
		return report_sn;
	}
	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
	}
	public String getData_status() {
		return data_status;
	}
	public void setData_status(String data_status) {
		this.data_status = data_status;
	}
	
	public String getInfo_ids() {
		return info_ids;
	}
	public void setInfo_ids(String info_ids) {
		this.info_ids = info_ids;
	}
	
	public String getSign_file() {
		return sign_file;
	}
	public void setSign_file(String sign_file) {
		this.sign_file = sign_file;
	}
	
	public Double getPay_total() {
		return pay_total;
	}
	public void setPay_total(Double pay_total) {
		this.pay_total = pay_total;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public Date getPay_time() {
		return pay_time;
	}
	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}
	public Integer getPay_count() {
		return pay_count;
	}
	public void setPay_count(Integer pay_count) {
		this.pay_count = pay_count;
	}
	public String getPay_device_type() {
		return pay_device_type;
	}
	public void setPay_device_type(String pay_device_type) {
		this.pay_device_type = pay_device_type;
	}
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getPay_status() {
		return pay_status;
	}
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}
	public Date getPay_start_time() {
		return pay_start_time;
	}
	public void setPay_start_time(Date pay_start_time) {
		this.pay_start_time = pay_start_time;
	}
	public Date getPay_end_time() {
		return pay_end_time;
	}
	public void setPay_end_time(Date pay_end_time) {
		this.pay_end_time = pay_end_time;
	}
	public String getOp_ip() {
		return op_ip;
	}
	public void setOp_ip(String op_ip) {
		this.op_ip = op_ip;
	}
	public String getMachine_num() {
		return machine_num;
	}
	public void setMachine_num(String machine_num) {
		this.machine_num = machine_num;
	}
	public String getPay_title() {
		return pay_title;
	}
	public void setPay_title(String pay_title) {
		this.pay_title = pay_title;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getPay_no() {
		return pay_no;
	}
	public void setPay_no(String pay_no) {
		this.pay_no = pay_no;
	}
	public String getPay_account() {
		return pay_account;
	}
	public void setPay_account(String pay_account) {
		this.pay_account = pay_account;
	}
	public Double getPayed_total() {
		return payed_total;
	}
	public void setPayed_total(Double payed_total) {
		this.payed_total = payed_total;
	}
	
} 
