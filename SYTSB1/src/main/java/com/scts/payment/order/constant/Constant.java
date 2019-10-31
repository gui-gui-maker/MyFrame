package com.scts.payment.order.constant;

import java.text.DecimalFormat;

/**
 * 系统常量
 *
 * @author GaoYa
 * @date 2018-06-28 16:50:00
 */
public class Constant {

    public static final String SYS_MACHINE_NUM = "号智能一体机";
    public static final DecimalFormat defaultDecimalFormat = new DecimalFormat("0.00");// 格式化数字

    /**
     * 智能一体机系统操作动作
     */
    public static final String MACHINE_ACTION_DRAW = "报告领取";
    public static final String MACHINE_ACTION_DRAW_DESC = "保存领取单";
    public static final String MACHINE_ACTION_PRINT_REPORT = "打印报告";
    public static final String MACHINE_ACTION_PRINT_TAG = "打印合格证";
    public static final String MACHINE_ACTION_PAY_REG = "缴费登记";

    /**
     * 系统日期格式
     */
    public static final String defaultDatePattern = "yyyy-MM-dd";
    public static final String ymDatePattern = "yyyy-MM";
    public static final String ymdDatePattern = "yyyyMMdd";
    public static final String hmsDatePattern = "yyyyMMddHHmmss";
    public static final String hmDatePattern = "yyyyMMddHHmmssSSS";
    public static final String ymdhmsDatePattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * 支付信息
     */
    public static final String PAY_TITLE = "四川省特种设备检验研究院-特种设备检验费";    // 支付标题（支付描述）
    public static final String PAY_TYPE_ALIPAY = "alipay";    // 支付宝支付
    public static final String PAY_TYPE_WEIXIN = "weixin";    // 微信支付
    public static final String PAY_TYPE_ALIPAY_PB = "alipaypb"; //支付宝平板支付
    public static final String PAY_TYPE_WEIXIN_PB = "weixinpb"; //微信平板支付
    public static final String PAY_STATUS_PAYED = "payed";    // 支付状态（已支付）
    public static final String PAY_STATUS_UNPAY = "unpay";    // 支付状态（未支付）
    public static final String PAY_OVERTIME_30M = "30m";    // 支付超时时长

    // 支付宝支付
    public static final String ALIPAY_TRADE_PARTNER = "2088131485788775";        // PID（合作身份者ID，签约账号）
    public static final String ALIPAY_TRADE_CODE_40004 = "40004";                // 交易状态（交易不存在）
    public static final String ALIPAY_TRADE_CODE_10000 = "10000";                // 交易状态（交易存在）
    public static final String ALIPAY_TRADE_SUCCESS = "TRADE_SUCCESS";            // 支付成功
    public static final String ALIPAY_TRADE_FINISHED = "TRADE_FINISHED";        // 支付完成
    public static final String ALIPAY_TRADE_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";    // 等待支付
    public static final String ALIPAY_TRADE_TRADE_CLOSED = "TRADE_CLOSED";        // 交易关闭

    // 微信支付
    public static final String WEIXIN_ORDER_SUCCESS = "SUCCESS";    // 微信通信/业务交易状态：成功
    public static final String WEIXIN_ORDER_FAIL = "FAIL";            // 微信通信/业务交易状态：失败
    public static final String WEIXIN_TRADE_SUCCESS = "SUCCESS";    // 支付成功
    public static final String WEIXIN_TRADE_NOTPAY = "NOTPAY";        // 未支付
    public static final String WEIXIN_TRADE_USERPAYING = "USERPAYING";    // 等待支付
    public static final String WEIXIN_TRADE_CLOSED = "CLOSED";            // 交易关闭
}
