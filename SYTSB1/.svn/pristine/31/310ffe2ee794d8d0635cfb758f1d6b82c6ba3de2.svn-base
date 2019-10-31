package com.scts.payment.order.web;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.utils.StringUtil;
import com.lsts.ImageTool;
import com.scts.payment.bean.InspectionPayInfo;
import com.scts.payment.order.bean.MachinePayOrder;
import com.scts.payment.order.bean.MachinePayRequest;
import com.scts.payment.order.config.AlipayConfig;
import com.scts.payment.order.constant.Constant;
import com.scts.payment.order.service.MachinePayOrderService;
import com.scts.payment.order.service.MachinePayRequestService;
import com.scts.payment.service.InspectionPayInfoService;
import com.scts.payment.service.MachinePayTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 智能一体机支付宝当面付业务控制器
 *
 * @author GaoYa
 * @ClassName AliPayFaceAction
 * @JDK 1.7
 * @date 2018-07-04 下午15:55:00
 */
@Controller
@RequestMapping("machine/alipay/face")
public class AliPayFaceAction extends
        SpringSupportAction<MachinePayOrder, MachinePayOrderService> {

    @Autowired
    private MachinePayOrderService machinePayOrderService;
    @Autowired
    private MachinePayTradeService machinePayTradeService;
    @Autowired
    private MachinePayRequestService machinePayRequestService;
    @Autowired
    private ImageTool imageTool;
    @Autowired
    private InspectionPayInfoService inspectionPayInfoService;

	/*
     * 初始化参数，不然signVerified会验证失败
	 * 支付宝官方demo采用的通过配置文件进行请求参数初始化配置
	 * static {
		Configs.init("zfbinfo.properties");
	}*/

    /**
     * 支付宝预下单请求（生成支付二维码）
     *
     * @param out_trade_no -- 缴费支付单号/订单号
     * @param totalAmount  -- 付款金额
     * @return
     * @throws Exception
     * @author GaoYa
     * @date 2018-07-05 下午15:55:00
     */
    @RequestMapping(value = "openAliPay")
    @ResponseBody
    public HashMap<String, Object> openAliPay(String out_trade_no, String totalAmount, String pay_count, String payInfoId, HttpServletRequest request) {
        try {
            // 获得初始化的AlipayClient
            log.debug(this.getClass().getName() + " method openAliPay start, order_no:" + out_trade_no + " 下单");
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.GATEWAY_URL, AlipayConfig.APP_ID,
                    AlipayConfig.MERCHANT_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
                    AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);

            // 设置请求参数
            AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();

            AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();

            // 封装请求支付信息
            // 缴费支付单号/订单号，必须且唯一，测试时，每测试一次必须修改新的订单号
            model.setOutTradeNo(out_trade_no);

            // 订单名称/支付类目，必须
            // 根据省院实际业务情况，此处固定为统一类目：检验费
            String subject = AlipayConfig.SUBJECT;
            model.setSubject(subject);

            // 付款金额，必须
            //add by latiflan 付款金额从后台获取，不从前台传入
            InspectionPayInfo payInfo = inspectionPayInfoService.get(payInfoId);
            model.setTotalAmount(payInfo.getAlipay_mobile());

            // 订单描述，可空
            String body = "检验报告共" + pay_count + "份";
            model.setBody(body);

            // 设置支付超时时长
            model.setTimeoutExpress(AlipayConfig.ALIPAY_OVERTIME_30M);

            alipayRequest.setBizModel(model);

            AlipayTradePrecreateResponse response = alipayClient.execute(alipayRequest);

            try {
                List<Map<String, Object>> list1 = machinePayOrderService.queryMachinePayRequest(out_trade_no);
                if (list1.size() > 0) {
                    String id = list1.get(0).get("ID").toString();
                    MachinePayRequest pay_req = machinePayRequestService.get(id);
                    pay_req.setFormat(AlipayConfig.FORMAT);
                    pay_req.setCharset_str(AlipayConfig.CHARSET);
                    pay_req.setTotal_amount(Double.parseDouble(payInfo.getAlipay_mobile()));
                    pay_req.setOrder_body(body);
                    pay_req.setOver_time(AlipayConfig.ALIPAY_OVERTIME_30M);
                    pay_req.setReq_result(String.valueOf(response.isSuccess()));
                    pay_req.setQr_code(response.getQrCode());
                    pay_req.setResp_body(response.getBody());
                    machinePayRequestService.save(pay_req);
                } else {
                    // 保存在线支付请求记录
                    MachinePayRequest pay_req = new MachinePayRequest();
                    pay_req.setPay_type(Constant.PAY_TYPE_ALIPAY_PB);
                    pay_req.setRequest_time(new Date());
//					pay_req.setServer_url(AlipayConfig.GATEWAY_URL);
//					pay_req.setP_id(AlipayConfig.P_ID);
//					pay_req.setApp_id(AlipayConfig.APP_ID);
//					pay_req.setPrivate_key(AlipayConfig.MERCHANT_PRIVATE_KEY);
                    pay_req.setFormat(AlipayConfig.FORMAT);
                    pay_req.setCharset_str(AlipayConfig.CHARSET);
//					pay_req.setAlipay_public_key(AlipayConfig.ALIPAY_PUBLIC_KEY);
//					pay_req.setSign_type(AlipayConfig.SIGN_TYPE);
                    pay_req.setOrder_no(out_trade_no);
                    pay_req.setSubject(subject);
                    pay_req.setTotal_amount(Double.parseDouble(payInfo.getAlipay_mobile()));
                    pay_req.setOrder_body(body);
                    pay_req.setOver_time(AlipayConfig.ALIPAY_OVERTIME_30M);
                    pay_req.setReq_result(String.valueOf(response.isSuccess()));
                    pay_req.setQr_code(response.getQrCode());
                    pay_req.setResp_body(response.getBody());
                    machinePayRequestService.save(pay_req);
                }
            } catch (Exception e) {
                e.printStackTrace();
                KhntException kh = new KhntException(e);
                log.error(kh.getMessage());
            }

            if (response.isSuccess()) {
                System.out.println("支付宝预下单请求-请求调用成功！");
                // 条码支付详细信息
                // System.out.println("条码支付详细信息："+response.getBody());
                // qr_Code当前预下单请求生成的二维码码串，可以用二维码生成工具根据该码串值生成对应的二维码
                System.out.println("支付宝预下单请求二维码码串：" + response.getQrCode());
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("alipay_qr_code", response.getQrCode());
                resultMap.put("actrually_pay", payInfo.getAlipay_mobile());
                return JsonWrapper.successWrapper(resultMap);
            } else {
                System.out.println("支付宝预下单请求-请求调用失败！");
                return JsonWrapper.failureWrapper("msg", "支付宝预下单请求-请求调用失败！");
            }
        } catch (Exception e) {
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
            return JsonWrapper.failureWrapper("msg", "服务器繁忙，请稍后再试！");
        } finally {
            log.debug(this.getClass().getName() + " method openAliPay end, order_no:" + out_trade_no + " 下单");
        }
    }

    /**
     * 生成支付二维码
     *
     * @param response
     * @throws Exception
     * @author GaoYa
     * @date 2018-07-05 下午16:35:00
     */
    @RequestMapping(value = "showQrCode")
    public void showQrCode(String alipay_qr_code, HttpServletResponse response) throws Exception {
        try {
            imageTool.createAlipayQrCode(alipay_qr_code, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 支付宝统一收单线下交易查询
     *
     * @param out_trade_no -- 缴费支付单号/订单号
     * @return
     * @throws Exception
     * @author GaoYa
     * @date 2018-07-05 下午17:18:00
     */
    @RequestMapping(value = "alipayTradeQuery")
    @ResponseBody
    public HashMap<String, Object> alipayTradeQuery(String out_trade_no) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String trade_no = "";    // 交易单号
        log.debug(this.getClass().getName() + " method alipayTradeQuery start, order_no:" + out_trade_no);
        try {
            if (StringUtil.isNotEmpty(out_trade_no)) {
                AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.GATEWAY_URL, AlipayConfig.APP_ID,
                        AlipayConfig.MERCHANT_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
                        AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);
                AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();

                // out_trade_no（订单号）和trade_no（支付宝交易号)二选一用 即可查询，此处选择根据订单号进行查询
                AlipayTradeQueryModel model = new AlipayTradeQueryModel();
                model.setOutTradeNo(out_trade_no); // 订单号
                // model.setTradeNo("2016113021001004950297103440"); // 支付宝交易号
                request.setBizModel(model);

                AlipayTradeQueryResponse response = alipayClient.execute(request);
                System.out.println("支付宝统一收单线下交易查询-查询结果：" + response.getBody());

                // 创建json解析器
                JsonParser parse = new JsonParser();
                JsonObject json = (JsonObject) parse.parse(response.getBody());
                JsonObject result = json.get("alipay_trade_query_response").getAsJsonObject();

                String code = result.get("code").getAsString();    // 支付状态码
                System.out.println("支付状态码：" + code);
                String trade_order_no = result.get("out_trade_no").getAsString();    // 支付订单号

                if (Constant.ALIPAY_TRADE_CODE_40004.equals(code)) {
                    map.put("success", false);
                    map.put("msg", code);
                    log.debug(this.getClass().getName() + " method alipayTradeQuery, order_no:" + out_trade_no + " 交易不存在");
                    return map;
                } else if (Constant.ALIPAY_TRADE_CODE_10000.equals(code)) {
                    String buyer_logon_id = result.get("buyer_logon_id").getAsString();    // 支付用户账号
                    String buyer_pay_amount = result.get("buyer_pay_amount").getAsString();    // 支付金额
                    trade_no = result.get("trade_no").getAsString();    // 交易单号
                    String trade_status = result.get("trade_status").getAsString();            // 支付状态
                    // 支付宝支付状态为成功或完成，处理支付业务
                    if (Constant.ALIPAY_TRADE_SUCCESS.equals(trade_status)
                            || Constant.ALIPAY_TRADE_FINISHED.equals(trade_status)) {
                        map.put("success", true);
                        map.put("trade_no", trade_no);
                        log.debug(this.getClass().getName() + " method alipayTradeQuery, order_no:" + out_trade_no + " 交易成功");
                        return map;
                        // 验证支付宝返回的订单号与请求订单号一致
                        /*if (out_trade_no.equals(trade_order_no)) {
							// 验证支付单
							MachinePayOrder order = machinePayOrderService.queryOrderByOrderNo(out_trade_no);
							// 支付单已支付
							if (Constant.PAY_STATUS_PAYED.equals(order.getPay_status())) {
								map.put("success", true);
								map.put("trade_no", trade_no);
								return map;
							} else {
								// 支付单未支付，验证支付金额是否和支付单一致
								if (null != order && Double.parseDouble(buyer_pay_amount) == order.getPay_total()
										&& Constant.PAY_STATUS_UNPAY.equals(order.getPay_status())) {
									// 更新支付单
									order.setPay_status(Constant.PAY_STATUS_PAYED);
									order.setPay_no(trade_no);
									order.setPay_type(Constant.PAY_TYPE_ALIPAY);
									order.setPay_account(buyer_logon_id);
									order.setPayed_total(Double.parseDouble(buyer_pay_amount));
									machinePayOrderService.updateOrder(order);
									
									// 保存交易记录
									machinePayTradeService.saveTrade(order, response.getBody());

									map.put("success", true);
									map.put("trade_no", trade_no);
									return map;
								} else {
									map.put("success", false);
									map.put("trade_status", trade_status);
									map.put("msg", "需要支付的金额为：" + order.getPay_total() + "元，已支付："
											+ Double.parseDouble(buyer_pay_amount) + "元，支付未完成！");
									return map;
								}
							}
						}*/
                    } else if (Constant.ALIPAY_TRADE_WAIT_BUYER_PAY.equals(trade_status)) {
                        // 如果状态是正在等待用户付款
                        map.put("success", false);
                        map.put("trade_status", trade_status);
                        map.put("msg", "等待支付中...");
                        log.debug(this.getClass().getName() + " method alipayTradeQuery, order_no:" + out_trade_no + " 等待支付");
                        return map;
                    } else if (Constant.ALIPAY_TRADE_TRADE_CLOSED.equals(trade_status)) {
                        // 如果状态是未付款交易超时关闭，或支付完成后全额退款
                        map.put("success", false);
                        map.put("trade_status", trade_status);
                        map.put("msg", "未付款交易超时关闭，支付未完成！");
                        log.debug(this.getClass().getName() + " method alipayTradeQuery, order_no:" + out_trade_no + " 未付款交易超时关闭，或支付完成后全额退款");
                        return map;
                    }
                }
            }
        } catch (Exception e) {
            KhntException kh = new KhntException(e);
            log.debug(this.getClass().getName() + " method alipayTradeQuery error, order_no:" + out_trade_no + " 支付出错");
            log.error(kh.getMessage());
            map.put("success", false);
            map.put("msg", "服务器繁忙，请稍后再试！");
            return map;
        }
        log.debug(this.getClass().getName() + " method alipayTradeQuery end, order_no:" + out_trade_no + "");
        map.put("success", true);
        map.put("trade_no", trade_no);
        return map;
    }

    /**
     * pc端同步通知
     *
     * @param
     * @return String
     * @throws Exception
     * @author GaoYa
     * @date 2018-07-07 下午13:55:00
     */
    @RequestMapping(value = "/returnUrl")
    public String returnUrl(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("------------------------------------pc同步通知-------------------------------------------");
        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE);

        //——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
            response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
            log.info("-----------------out_trade_no:" + out_trade_no + "trade_no:" + trade_no + "total_amount:" + total_amount + "---------------------");
            map.put("alipayResult", "支付宝支付成功");
        } else {
            response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
            map.put("alipayResult", "支付宝支付失败");
        }
        //——请在这里编写您的程序（以上代码仅作参考）——
        return "alipayResult";
    }

    /**
     * pc端异步通知
     *
     * @param
     * @return String
     * @throws Exception
     * @author GaoYa
     * @date 2018-07-07 下午14:28:00
     */
    @RequestMapping(value = "/notifyUrl", method = RequestMethod.POST)
    public void notifyUrl(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            log.info("------------------------------------pc异步通知-------------------------------------------");
            //获取支付宝POST过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化。
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
                params.put(name, valueStr);
            }
            //logger.info("---------------------------------------------------------------params=========="+params);
            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
            //商户订单号

            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号

            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
            //计算得出通知验证结果
            //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
            boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");
            log.info("------------------------------------------支付宝异步通知页面验证成功：trade_finished------------------------------verify_result=" + verify_result);
            if (verify_result) {//验证成功
                //////////////////////////////////////////////////////////////////////////////////////////
                //请在这里加上商户的业务逻辑程序代码

                //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

                if (trade_status.equals("TRADE_FINISHED")) {
                    //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                    //如果有做过处理，不执行商户的业务程序
                    log.info("------------------------------------------支付宝异步通知页面验证成功：trade_finished------------------------------");
                    //注意：
                    //如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                    //如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
                } else if (trade_status.equals("TRADE_SUCCESS")) {
                    //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                    //如果有做过处理，不执行商户的业务程序
                    log.info("------------------------------------------支付宝异步通知页面验证成功：trade_success------------------------------");
                    //注意：
                    //如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
                }
                //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
                response.getWriter().println("success");
                response.getWriter().close();
                //////////////////////////////////////////////////////////////////////////////////////////
            } else {//验证失败
                response.getWriter().println("fail");
                response.getWriter().close();
                log.info("-------------------------------fail-------------------------------");
            }
        } catch (Exception e) {
            response.getWriter().println("fail");
            response.getWriter().close();
            log.info("跳转到pc网站支付宝支付-同步不通知-页面-error");
            e.printStackTrace();
        }
    }

//    public HashMap<String, Object> invo
}
