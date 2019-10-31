package com.scts.payment.wxpay.bean;

/**
 * 微信统一下单返回参数
 * UnifiedOrderRespose entity. 
 * @author GaoYa
 * @date 2018-06-13 16:55:00
 */
public class WeixinOrderRespose {
	
	private String return_code;             //返回状态码  
    private String return_msg;              //返回信息  
    private String appid;                   //公众账号ID  
    private String mch_id;                  //商户号  
    private String nonce_str;               //随机字符串  
    private String sign;                    //签名  
    private String result_code;             //业务结果  
    private String err_code;                //错误代码  
    private String err_code_des;            //错误代码描述  
    private String prepay_id;               //预支付交易会话标识  
    private String code_url;                //二维码链接
    
    private String device_info;             //设备号  
    private String openid;          		//用户标识，用户在商户appid下的唯一标识
    private String is_subscribe;			//用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
    private String trade_type;              //交易类型  
    private String trade_state;				//交易状态(SUCCESS—支付成功REFUND—转入退款NOTPAY—未支付CLOSED—已关闭REVOKED—已撤销（刷卡支付）USERPAYING--用户支付中PAYERROR--支付失败(其他原因，如银行返回失败)
    private String bank_type;				//付款银行(银行类型，采用字符串类型的银行标识)
    private int    total_fee;          		//订单总金额，单位为分
    private int    settlement_total_fee;	//应结订单金额(当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额) 
    private String fee_type;				//标价币种(货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY)
    private int    cash_fee;				//现金支付金额
    private String cash_fee_type;			//现金支付币种(货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY)
    private int    coupon_fee;				//代金券金额(“代金券”金额<=订单金额，订单金额-“代金券”金额=现金支付金额)
    private int    coupon_count;			//代金券使用数量
    private String transaction_id;          //微信支付订单号（交易号）
    private String out_trade_no;			//商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
    private String attach;					//附加数据
    private String time_end;				//支付完成时间(订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010)
    private String trade_state_desc;		//交易状态描述(对当前查询订单状态的描述和下一步操作的指引)
    
	
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getPrepay_id() {
		return prepay_id;
	}
	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}
	public String getCode_url() {
		return code_url;
	}
	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getIs_subscribe() {
		return is_subscribe;
	}
	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}
	public String getTrade_state() {
		return trade_state;
	}
	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}
	public String getTrade_state_desc() {
		return trade_state_desc;
	}
	public void setTrade_state_desc(String trade_state_desc) {
		this.trade_state_desc = trade_state_desc;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getTime_end() {
		return time_end;
	}
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
	public int getSettlement_total_fee() {
		return settlement_total_fee;
	}
	public void setSettlement_total_fee(int settlement_total_fee) {
		this.settlement_total_fee = settlement_total_fee;
	}
	public int getCash_fee() {
		return cash_fee;
	}
	public void setCash_fee(int cash_fee) {
		this.cash_fee = cash_fee;
	}
	public String getCash_fee_type() {
		return cash_fee_type;
	}
	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}
	public int getCoupon_fee() {
		return coupon_fee;
	}
	public void setCoupon_fee(int coupon_fee) {
		this.coupon_fee = coupon_fee;
	}
	public int getCoupon_count() {
		return coupon_count;
	}
	public void setCoupon_count(int coupon_count) {
		this.coupon_count = coupon_count;
	}
	
} 
