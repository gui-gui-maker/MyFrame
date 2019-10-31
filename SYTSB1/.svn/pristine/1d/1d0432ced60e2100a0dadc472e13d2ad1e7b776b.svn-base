package com.scts.payment.wxpay.config;

/**
 * 微信支付业务参数配置
 * 
 * 为保证交易安全性，采用HTTPS传输；采用POST方法提交；提交和返回数据都为XML格式，根节点名为xml
 * 统一采用UTF-8字符编码；签名算法采用MD5；请求和接收数据均需要校验签名；调用申请退款、撤销订单接口需要商户证书
 * 先判断协议字段返回，再判断业务返回，最后判断交易状态
 * 
 * @ClassName WeixinPayConfig
 * @JDK 1.7
 * @author GaoYa
 * @date 2018-08-24 上午10:07:00
 */
public class WeixinPayConfig {
	
	// 微信开发平台应用ID(公众号ID)（改为自己实际的）
	public static final String APP_ID = "wx7a3c02acc4a17569";		
	
	// 商户号(商户号ID)（改为自己实际的）
	public static final String MCH_ID = "1487423972";		
	
	// API key（设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置）
	public static final String API_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASC";
	
	// 发起支付的ip（改为自己实际的）
	public static final String CREATE_IP = "";	
	
	// 回调地址，微信支付回调接口，就是微信那边收到（改为自己实际的）
//	public static final String NOTIFY_URL = "http://jx.scsei.org.cn/machine/wxpay/weixinNotify.do";	
	public static final String NOTIFY_URL = "http://pt.scsei.org.cn/machine/wxpay/weixinNotify.do";	
	
	
	// 以下是微信几个API的路径：

    //1）扫码支付API
	// 正式环境下单请求
    public static String UNIFIEDORDER_API = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    // 沙箱环境下单请求
    public static String SANDBOX_UNIFIEDORDER_API = "https://api.mch.weixin.qq.com/sandboxnew/pay/unifiedorder";
    
    //2）刷卡支付 
    // 正式环境
    public static String PAY_API = "https://api.mch.weixin.qq.com/pay/micropay";
    // 沙箱环境
    public static String SANDBOX_PAY_API = "https://api.mch.weixin.qq.com/sandboxnew/pay/micropay";
    
    //3）获取验签秘钥API
    // 沙箱环境
    public static String SANDBOX_SIGN_KEY_API = "https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey";

    //4）扫码支付查询API
    // 正式环境查询
    public static String PAY_QUERY_API = "https://api.mch.weixin.qq.com/pay/orderquery";
    // 沙箱环境查询
    public static String SANDBOX_PAY_QUERY_API = "https://api.mch.weixin.qq.com/sandboxnew/pay/orderquery";

    //5）退款API
    public static String REFUND_API = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    //6）退款查询API
    public static String REFUND_QUERY_API = "https://api.mch.weixin.qq.com/pay/refundquery";

    //7）撤销API
    public static String REVERSE_API = "https://api.mch.weixin.qq.com/secapi/pay/reverse";

    //8）下载对账单API
    public static String DOWNLOAD_BILL_API = "https://api.mch.weixin.qq.com/pay/downloadbill";

    //9) 统计上报API
    public static String REPORT_API = "https://api.mch.weixin.qq.com/payitil/report";
    
    //10) 企业向个人账号付款的URL
	public final static String SEND_EED_PACK_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
	
	
	// 应用对应的凭证(在公众号里面)
	public static final String APP_SECRET = "";
	
	// 商品描述
	public static String BODY = "检验费";
	
	// 字符编码格式
	public static String CHARSET = "utf-8";
	
	// 数据交换格式
	public static String FORMAT = "xml";
	
	// 签名类型
	public static final String SIGN_TYPE = "MD5";
	
	// 是否使用沙箱测试环境
	public static boolean USE_SANDBOX = false;


}

