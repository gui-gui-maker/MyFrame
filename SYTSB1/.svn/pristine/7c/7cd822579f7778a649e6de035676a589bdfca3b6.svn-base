package com.scts.payment.order.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 智能一体机在线支付请求记录
 * MachinePayTradeRequest entity. 
 * @author GaoYa
 * @date 2018-07-12 15:18:00
 */
@Entity
@Table(name = "machine_pay_request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MachinePayRequest implements BaseEntity{

	
	private static final long serialVersionUID = 1L;
	
	private String id;				// ID	
	private String pay_type;		// 支付方式（alipay：支付宝 weixin：微信）
	private Date   request_time;	// 请求时间
	
	/*请求支付宝接口所需参数*/
	/*必须*/
	private String server_url;		// 支付宝服务器地址
	private String p_id; 			// 支付宝合作身份者ID，签约账号
	private String app_id; 			// 支付宝APPID
	private String private_key; 	// 商户私钥
	private String format; 			// 数据交换格式
	private String charset_str;		// 字符编码格式
	private String alipay_public_key; 	// 支付宝公钥
	private String sign_type; 		// 签名方式
	private String order_no;		// 商户系统订单号
	private String subject;			// 商户系统订单名称/支付类目
	private Double total_amount;	// 商户系统付款金额
	/*可选*/
	private String order_body;		// 商户系统订单描述
	private String over_time;		// 商户系统支付超时时长（单位为分钟，例如：30m）	
	/*请求支付宝接口所需参数*/
	
	/*请求支付宝接口返回参数*/
	private String req_result;		// 请求结果（true：请求成功 false：请求失败）
	private String qr_code;			// 支付宝二维码码串
	private String resp_body;		// 请求结果字符串
	/*请求支付宝接口返回参数*/

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
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public Date getRequest_time() {
		return request_time;
	}
	public void setRequest_time(Date request_time) {
		this.request_time = request_time;
	}
	public String getServer_url() {
		return server_url;
	}
	public void setServer_url(String server_url) {
		this.server_url = server_url;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getPrivate_key() {
		return private_key;
	}
	public void setPrivate_key(String private_key) {
		this.private_key = private_key;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getCharset_str() {
		return charset_str;
	}
	public void setCharset_str(String charset_str) {
		this.charset_str = charset_str;
	}
	public String getAlipay_public_key() {
		return alipay_public_key;
	}
	public void setAlipay_public_key(String alipay_public_key) {
		this.alipay_public_key = alipay_public_key;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(Double total_amount) {
		this.total_amount = total_amount;
	}
	public String getOver_time() {
		return over_time;
	}
	public void setOver_time(String over_time) {
		this.over_time = over_time;
	}
	public String getQr_code() {
		return qr_code;
	}
	public void setQr_code(String qr_code) {
		this.qr_code = qr_code;
	}
	public String getResp_body() {
		return resp_body;
	}
	public void setResp_body(String resp_body) {
		this.resp_body = resp_body;
	}
	public String getOrder_body() {
		return order_body;
	}
	public void setOrder_body(String order_body) {
		this.order_body = order_body;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getReq_result() {
		return req_result;
	}
	public void setReq_result(String req_result) {
		this.req_result = req_result;
	}

} 
