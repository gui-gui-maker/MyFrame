package com.scts.payment.service;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.scts.payment.bean.MachinePayTrade;
import com.scts.payment.dao.MachinePayTradeDao;
import com.scts.payment.order.bean.MachinePayOrder;
import com.scts.payment.order.constant.Constant;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.SortedMap;

/**
 * 智能一体机在线支付交易记录（支付宝和微信）业务逻辑对象
 *
 * @author GaoYa
 * @ClassName MachinePayTradeService
 * @JDK 1.7
 * @date 2018-07-06 下午02:37:00
 */
@Service("machinePayTradeService")
public class MachinePayTradeService extends EntityManageImpl<MachinePayTrade, MachinePayTradeDao> {
    @Autowired
    private MachinePayTradeDao machinePayTradeDao;

    // 保存交易记录
    public void saveTrade(MachinePayOrder order, String trade_resp_body) {
        try {
            if (StringUtil.isNotEmpty(trade_resp_body)) {
                com.scts.payment.bean.MachinePayTrade machinePayTrade = machinePayTradeDao.queryTrade(order.getOrder_no(), order.getPay_no());
                if (machinePayTrade == null) {
                    machinePayTrade = new MachinePayTrade();
                }

                // 创建json解析器
                JsonParser parse = new JsonParser();
                JsonObject json = (JsonObject) parse.parse(trade_resp_body);
                JsonObject result = json.get("alipay_trade_query_response").getAsJsonObject();
                String trade_code = result.get("code").getAsString(); // 支付状态码
                String buyer_user_id = result.get("buyer_user_id").getAsString(); // 支付账户ID
                String send_pay_date = result.get("send_pay_date").getAsString(); // 支付时间
                String trade_status = result.get("trade_status").getAsString(); // 支付状态

                machinePayTrade.setOrder_no(order.getOrder_no());
                machinePayTrade.setInfo_ids(order.getInfo_ids());
                machinePayTrade.setPay_type(order.getPay_type());
                machinePayTrade.setTrade_code(trade_code);
                machinePayTrade.setCom_name(order.getCom_name());
                machinePayTrade.setBuyer_logon_id(order.getPay_account());
                machinePayTrade.setBuyer_user_id(buyer_user_id);
                machinePayTrade.setBuyer_pay_amount(order.getPayed_total());
                machinePayTrade.setSend_pay_date(
                        send_pay_date != null ? DateUtil.convertStringToDate(Constant.ymdhmsDatePattern, send_pay_date)
                                : null);
                machinePayTrade.setTrade_no(order.getPay_no());
                machinePayTrade.setTrade_status(trade_status);
                machinePayTrade.setTrade_resp_body(trade_resp_body);
                machinePayTradeDao.save(machinePayTrade);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("交易记录（" + order.getOrder_no() + "）保存失败：" + e.getMessage());
        }
    }

    public void saveWxpayTrade(MachinePayOrder order, SortedMap<Object, Object> packageParams) {
        try {
            if (packageParams != null) {
                MachinePayTrade machinePayTrade = machinePayTradeDao.queryTrade(order.getOrder_no(), order.getPay_no());
                if (machinePayTrade == null) {
                    machinePayTrade = new MachinePayTrade();
                }

                // 解析各种数据
                /*
				 * String appid = (String) packageParams.get("appid"); // 应用ID String attach =
				 * (String) packageParams.get("attach");// 商家数据包 String bank_type = (String)
				 * packageParams.get("bank_type");// 付款银行 String cash_fee = (String)
				 * packageParams.get("cash_fee");// 现金支付金额 String fee_type = (String)
				 * packageParams.get("fee_type");// 货币种类 String is_subscribe = (String)
				 * packageParams.get("is_subscribe");// 是否关注公众账号 String mch_id = (String)
				 * packageParams.get("mch_id");// 商户号 String nonce_str = (String)
				 * packageParams.get("nonce_str");// 随机字符串 String out_trade_no = (String)
				 * packageParams.get("out_trade_no");// 获取商户订单号 String sign = (String)
				 * packageParams.get("sign");// 获取签名 String total_fee = (String)
				 * packageParams.get("total_fee");// 获取订单金额 String trade_type = (String)
				 * packageParams.get("trade_type");// 交易类型
				 */
                String openid = (String) packageParams.get("openid");// 用户标识
                String result_code = (String) packageParams.get("result_code");// 业务结果 SUCCESS/FAIL
                String time_end = (String) packageParams.get("time_end");// 支付完成时间
                String transaction_id = (String) packageParams.get("transaction_id");// 微信支付订单号

                machinePayTrade.setOrder_no(order.getOrder_no());
                machinePayTrade.setInfo_ids(order.getInfo_ids());
                machinePayTrade.setPay_type(order.getPay_type());
                machinePayTrade.setTrade_code(result_code);
                machinePayTrade.setCom_name(order.getCom_name());
                machinePayTrade.setBuyer_logon_id(order.getPay_account());
                machinePayTrade.setBuyer_user_id(openid);
                machinePayTrade.setBuyer_pay_amount(order.getPayed_total());

                Date pay_date = DateUtil.convertStringToDate(Constant.hmsDatePattern, time_end);
                String send_pay_date = DateUtil.format(pay_date, Constant.ymdhmsDatePattern);
                machinePayTrade.setSend_pay_date(
                        time_end != null ? DateUtil.convertStringToDate(Constant.ymdhmsDatePattern, send_pay_date)
                                : null);
                machinePayTrade
                        .setTrade_no(StringUtil.isNotEmpty(order.getPay_no()) ? order.getPay_no() : transaction_id);
                machinePayTrade.setTrade_status(result_code);

                JSONObject jsonObject = JSONObject.fromObject(packageParams);
                machinePayTrade.setTrade_resp_body(jsonObject.toString());

                machinePayTradeDao.save(machinePayTrade);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("微信交易记录（" + order.getOrder_no() + "）保存失败：" + e.getMessage());
        }
    }


}
