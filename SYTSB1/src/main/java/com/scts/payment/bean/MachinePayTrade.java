package com.scts.payment.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 智能一体机在线支付交易记录（支付宝和微信）
 * MachinePayTrade entity. 
 * @author GaoYa
 * @date 2018-07-06 13:54:00
 */
@Entity
@Table(name = "machine_pay_trade")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MachinePayTrade implements BaseEntity{

	
	private static final long serialVersionUID = 1L;
	
	private String id;				// ID
	private String order_no;		// 订单号
	private String info_ids;		// 支付（报告）业务ID（多个以逗号分隔）
	private String pay_type;		// 支付方式（alipay：支付宝 weixin：微信）
	private String trade_code;		// 交易状态码（10000：成功 40004：交易不存在）
	private String com_name;		// 单位名称
	private String buyer_logon_id;		// 支付账号
	private String buyer_user_id;		// 支付账户id
	private Double buyer_pay_amount;	// 支付金额	
	private Date   send_pay_date;		// 支付时间
	private String trade_no;			// 交易号
	private String trade_status;		// 交易状态（TRADE_SUCCESS：支付成功 TRADE_FINISHED：支付完成 WAIT_BUYER_PAY：等待支付 TRADE_CLOSED：交易关闭）
	private String trade_resp_body;		// 交易字符串

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getInfo_ids() {
		return info_ids;
	}
	public void setInfo_ids(String info_ids) {
		this.info_ids = info_ids;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getTrade_code() {
		return trade_code;
	}
	public void setTrade_code(String trade_code) {
		this.trade_code = trade_code;
	}
	public String getBuyer_logon_id() {
		return buyer_logon_id;
	}
	public void setBuyer_logon_id(String buyer_logon_id) {
		this.buyer_logon_id = buyer_logon_id;
	}
	public String getBuyer_user_id() {
		return buyer_user_id;
	}
	public void setBuyer_user_id(String buyer_user_id) {
		this.buyer_user_id = buyer_user_id;
	}
	public Double getBuyer_pay_amount() {
		return buyer_pay_amount;
	}
	public void setBuyer_pay_amount(Double buyer_pay_amount) {
		this.buyer_pay_amount = buyer_pay_amount;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getTrade_status() {
		return trade_status;
	}
	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}
	public Date getSend_pay_date() {
		return send_pay_date;
	}
	public void setSend_pay_date(Date send_pay_date) {
		this.send_pay_date = send_pay_date;
	}
	public String getTrade_resp_body() {
		return trade_resp_body;
	}
	public void setTrade_resp_body(String trade_resp_body) {
		this.trade_resp_body = trade_resp_body;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
} 
