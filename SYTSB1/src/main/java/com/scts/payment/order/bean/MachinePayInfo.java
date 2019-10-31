package com.scts.payment.order.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 智能一体机缴费支付单明细
 * MachinePayInfo entity. 
 * @author GaoYa
 * @date 2018-06-11 16:09:00
 */
@Entity
@Table(name = "machine_pay_info")
@JsonIgnoreProperties(ignoreUnknown = true, value = { "machinePayOrder"})
public class MachinePayInfo implements BaseEntity{

	private static final long serialVersionUID = 1L;
	private String id;	// 流水号
	private String info_id;		// 业务编号
	private String report_sn;		// 支付报告编号
	private String pay_device_type;	// 支付设备类型（3：电梯...）
	private String phone_num;		// 支付人联系电话
	private String com_name;		// 支付人单位名称（开票单位）
	private Double pay_money;		// 支付金额
	private String pay_type;		// 支付方式（0：支付宝 1：微信）
	private Date   pay_time;		// 支付时间
	private String pay_status;		// 支付状态（unpay：未支付 payed：已支付）
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
	
	public MachinePayOrder machinePayOrder;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_machine_pay_order_id")
	public MachinePayOrder getMachinePayOrder() {
		return machinePayOrder;
	}

	public void setMachinePayOrder(MachinePayOrder machinePayOrder) {
		this.machinePayOrder = machinePayOrder;
	}
	
	public String getOp_ip() {
		return op_ip;
	}
	public void setOp_ip(String op_ip) {
		this.op_ip = op_ip;
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
	public String getMachine_num() {
		return machine_num;
	}
	public void setMachine_num(String machine_num) {
		this.machine_num = machine_num;
	}
	public Double getPay_money() {
		return pay_money;
	}
	public void setPay_money(Double pay_money) {
		this.pay_money = pay_money;
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
	public String getPay_status() {
		return pay_status;
	}
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}

	public String getData_status() {
		return data_status;
	}
	public void setData_status(String data_status) {
		this.data_status = data_status;
	}

	public String getReport_sn() {
		return report_sn;
	}
	public void setReport_sn(String report_sn) {
		this.report_sn = report_sn;
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
	public String getPay_device_type() {
		return pay_device_type;
	}
	public void setPay_device_type(String pay_device_type) {
		this.pay_device_type = pay_device_type;
	}
	
}
