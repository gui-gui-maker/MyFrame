package com.scts.payment.wxpay.controller;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
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
import com.scts.payment.wxpay.bean.WeixinOrderRequest;
import com.scts.payment.wxpay.bean.WeixinOrderRespose;
import com.scts.payment.wxpay.config.WeixinPayConfig;
import com.scts.payment.wxpay.util.AmountUtil;
import com.scts.payment.wxpay.util.PayToolUtil;
import com.scts.payment.wxpay.util.StringUtils;
import com.scts.payment.wxpay.util.XMLUtil4jdom;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 智能一体机微信业务控制器
 *
 * @author GaoYa
 * @ClassName MachineWeixinPayAction
 * @JDK 1.7
 * @date 2018-08-27 上午10:03:00
 */
@Controller
@RequestMapping("machine/wxpay")
public class MachineWeixinPayAction extends SpringSupportAction<MachinePayOrder, MachinePayOrderService> {

    @Autowired
    private MachinePayOrderService machinePayOrderService;
    @Autowired
    private MachinePayTradeService machinePayTradeService;
    @Autowired
    private InspectionPayInfoService inspectionPayInfoService;
    @Autowired
    private MachinePayRequestService machinePayRequestService;

    @Autowired
    private ImageTool imageTool;

    /**
     * 统一下单（生成支付二维码）
     *
     * @param payInfoId -- 系统中订单ID
     * @return
     * @author GaoYa
     * @date 2018-08-27 上午10:08:00
     */
    @RequestMapping("openWxPay")
    @ResponseBody
    public HashMap<String, Object> openWxPay(String payInfoId, HttpServletRequest request) {
        // 获取商户订单对象
        InspectionPayInfo payInfo = inspectionPayInfoService.get(payInfoId);
        if (payInfo != null) {
            // 生成微信订单
            String ip = getRemoteHost(request);
            //TODO lan 这里后期要把时间戳移除掉用发票号作为订单号处理，而且微信接口要大调整以实现订单号不变的支付方式
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHMMss");
            String orderId = sf.format(new Date()).trim() + payInfo.getInvoice_no();
            log.debug(this.getClass().getName() + " method openWxPay start, order_no:" + orderId + " 下单");
            String orderInfo = createOrderInfo(payInfo, ip, orderId);
            // 设置统一下单API地址
            String url;
            if (WeixinPayConfig.USE_SANDBOX) {
                url = WeixinPayConfig.SANDBOX_UNIFIEDORDER_API;
            } else {
                url = WeixinPayConfig.UNIFIEDORDER_API;
            }
            // 调用统一下单API
            HashMap<String, Object> map = httpOrder(orderInfo, url);
            if (Boolean.parseBoolean(map.get("success").toString())) {
                String body = "检验报告共" + payInfo.getFk_inspection_info_id().split(",") + "份";
                try {
                    List<Map<String, Object>> list1 = machinePayOrderService.queryMachinePayRequest(payInfo.getInvoice_no());
                    MachinePayRequest pay_req = null;
                    if (list1.size() > 0) {
                        String id = list1.get(0).get("ID").toString();
                        pay_req = machinePayRequestService.get(id);
                        pay_req.setFormat(WeixinPayConfig.FORMAT);
                        pay_req.setCharset_str(WeixinPayConfig.CHARSET);
                        pay_req.setTotal_amount(Double.parseDouble(payInfo.getWechat_mobile()));
                        pay_req.setOrder_body(body);
                        pay_req.setOver_time(Constant.PAY_OVERTIME_30M);
                        pay_req.setReq_result(String.valueOf(map.get("success")));
                        pay_req.setQr_code(String.valueOf(map.get("code_url")));
                        pay_req.setResp_body("");
                        machinePayRequestService.save(pay_req);
                    } else {
                        // 保存在线支付请求记录
                        pay_req = new MachinePayRequest();
                        pay_req.setPay_type(Constant.PAY_TYPE_WEIXIN_PB);
                        pay_req.setRequest_time(new Date());
                        pay_req.setFormat(AlipayConfig.FORMAT);
                        pay_req.setCharset_str(AlipayConfig.CHARSET);
                        pay_req.setOrder_no(orderId);
                        pay_req.setSubject(WeixinPayConfig.BODY);
                        pay_req.setTotal_amount(Double.parseDouble(payInfo.getWechat_mobile()));
                        pay_req.setOrder_body(body);
                        pay_req.setOver_time(Constant.PAY_OVERTIME_30M);
                        pay_req.setReq_result(String.valueOf(map.get("success")));
                        pay_req.setQr_code(String.valueOf(map.get("code_url")));
                        pay_req.setResp_body("");
                        machinePayRequestService.save(pay_req);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    KhntException kh = new KhntException(e);
                    log.error(kh.getMessage());
                }
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("weixin_qr_code", map.get("code_url"));
                resultMap.put("orderId", orderId);
                resultMap.put("actrually_pay", payInfo.getWechat_mobile());
                log.debug(this.getClass().getName() + " method openWxPay end, order_no:" + orderId + " 下单");
                return JsonWrapper.successWrapper(resultMap);
            } else {
                log.debug(this.getClass().getName() + " method openWxPay end, order_no:" + orderId + " 下单");
                return JsonWrapper.failureWrapper("msg", map.get("msg"));
            }
        } else {
            return JsonWrapper.failureWrapper("msg", "获取支付信息失败，请稍后再试");
        }

    }

    /**
     * 查询订单（获取微信支付结果）
     *
     * @param payInfoId -- 系统中订单ID
     * @return
     * @author GaoYa
     * @date 2018-08-28 下午15:08:00
     */
    @RequestMapping("wxpayTradeQuery")
    @ResponseBody
    public Map<String, Object> wxpayTradeQuery(String payInfoId, String orderId) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String trade_no = ""; // 微信交易号
        InspectionPayInfo payInfo = inspectionPayInfoService.get(payInfoId);
        log.debug(this.getClass().getName() + " method wxpayTradeQuery start, order_no:" + orderId + "");
        try {
            if (payInfo == null) {
                map.put("success", false);
                map.put("trade_no", trade_no);
                return map;
            }

            // 生成微信查询订单
            String orderInfo = queryOrderInfo(payInfo, orderId);

            // 设置订单查询API地址
            String url;
            if (WeixinPayConfig.USE_SANDBOX) {
                url = WeixinPayConfig.SANDBOX_PAY_QUERY_API;
            } else {
                url = WeixinPayConfig.PAY_QUERY_API;
            }

            // 调用微信查询订单API
            HashMap<String, Object> resp_map = httpOrder(orderInfo, url);
            if (Boolean.valueOf(String.valueOf(resp_map.get("success")))) {
                String trade_status = String.valueOf(resp_map.get("trade_state"));
                if (resp_map.get("trade_no") != null && resp_map.get("openid") != null) {
                    trade_no = String.valueOf(resp_map.get("trade_no"));
                    if (Constant.WEIXIN_ORDER_SUCCESS.equals(trade_status)) {
                        map.put("success", true);
                        map.put("trade_no", trade_no);
                        return map;
                    } else {
                        // 支付未完成时，返回微信错误消息
                        map.put("success", false);
                        map.put("trade_status", trade_status);
                        map.put("msg", "等待支付中...");
                        return map;
                    }
                } else {
                    // 支付未完成时，返回微信错误消息
                    map.put("success", false);
                    map.put("trade_status", trade_status);
                    map.put("msg", "等待支付中...");
                    return map;
                }
            } else {
                // 支付未完成时，返回微信错误消息
                map.put("success", false);
                map.put("msg", resp_map.get("msg"));
                return map;
            }
        } catch (Exception e) {
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
            map.put("success", false);
            map.put("msg", "服务器繁忙，请稍后再试！");
            return map;
        } finally {
            log.debug(this.getClass().getName() + " method wxpayTradeQuery end, order_no:" + orderId + "");
        }
    }

    /**
     * 生成微信统一下单请求对象
     *
     * @param order -- 商户订单对象
     * @return
     * @author GaoYa
     * @date 2018-08-27 上午11:16:00
     */
    private String createOrderInfo(InspectionPayInfo order, String ip, String orderId) {
        // 生成微信订单对象
        WeixinOrderRequest unifiedOrderRequest = new WeixinOrderRequest();

        // 公众账号ID，必填
        unifiedOrderRequest.setAppid(WeixinPayConfig.APP_ID);

        // 商户号，必填
        unifiedOrderRequest.setMch_id(WeixinPayConfig.MCH_ID);

        // 随机字符串：微信对随机字符串的要求是不超过32位，这里使用时间戳，必填

        unifiedOrderRequest.setNonce_str(StringUtils.makeUUID());

        // 商品描述，必填
        unifiedOrderRequest.setBody(WeixinPayConfig.BODY);

        // 商户订单号，必填
        // 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一
        // 重新发起一笔支付要使用原订单号，避免重复支付，已支付过或已调用关单、撤销的订单号不能重新发起支付。
        unifiedOrderRequest.setOut_trade_no(orderId);

        // 订单总金额，单位为分，需要扩大100倍：1代表支付时是0.01，必填
        // 交易金额默认为人民币交易，接口中参数支付金额单位为【分】，参数值不能带小数。
        // 对账单中的交易金额单位为【元】
        //TODO lan 需要将0.01 替换为： String.valueOf(order.getPay_received()))
        //将金额转换成微信所用单位（微信支付时的最小数值是1）
        //float payREceiv=Float.parseFloat(order.getPay_received())*100;
        float payREceiv = Float.parseFloat(order.getWechat_mobile()) * 100;
        int num = (int) payREceiv;
        String factMoney = String.valueOf(num);
        unifiedOrderRequest.setTotal_fee(factMoney); //这里填写的是业务中的实收总金额
        //unifiedOrderRequest.setTotal_fee(AmountUtil.changeY2F("0.01"));

        // 终端IP，必填
        // APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
        unifiedOrderRequest.setSpbill_create_ip(ip);

        // 通知地址，必填
        // 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
        unifiedOrderRequest.setNotify_url(WeixinPayConfig.NOTIFY_URL);

        // 交易类型，必填
        // JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
        unifiedOrderRequest.setTrade_type("NATIVE");

        // 商品ID：trade_type=NATIVE时（即扫码支付），此参数必传。
        // 此参数为二维码中包含的商品ID，要求32个字符内，商户自行定义。
        // unifiedOrderRequest.setProduct_id(order.getId());

		/*// 交易起始时间，即订单生成时间，选填
        unifiedOrderRequest.setTime_start(DateUtil.format(new Date(), Constant.hmsDatePattern));
		*/
        // 交易结束时间，即订单失效时间，选填
/*        String pay_times = Constant.PAY_OVERTIME_30M; // 获取支付时长
        if (pay_times.contains("m")) {
            int minute = Integer.parseInt(pay_times.substring(0, pay_times.length() - 1));
            Calendar nowTime = Calendar.getInstance();
            nowTime.add(Calendar.MINUTE, minute);
            unifiedOrderRequest.setTime_expire(DateUtil.format(nowTime.getTime(), Constant.hmsDatePattern));
        }*/

        // 签名，必填
        unifiedOrderRequest.setSign(PayToolUtil.createSign(unifiedOrderRequest));

        // 将订单对象转为xml格式
        // 使用Xstream时，由于微信定义的变量名大部分使用了“_”,但是在Xstream中它是关键字，所以会自动变为“__”，引起报错
        XStream xStream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
        // 根元素名需要是xml
        xStream.alias("xml", WeixinOrderRequest.class);
        return xStream.toXML(unifiedOrderRequest);
    }

    /**
     * 生成微信查询订单请求对象
     *
     * @return
     * @author GaoYa
     * @date 2018-08-28 下午16:41:00
     */
    private String queryOrderInfo(InspectionPayInfo payInfo, String orderId) {
        // 生成微信订单对象
        WeixinOrderRequest unifiedOrderRequest = new WeixinOrderRequest();
        // 公众账号ID，必填
        unifiedOrderRequest.setAppid(WeixinPayConfig.APP_ID);
        // 商户号，必填
        unifiedOrderRequest.setMch_id(WeixinPayConfig.MCH_ID);
        // 随机字符串：微信对随机字符串的要求是不超过32位，这里使用时间戳，必填
        unifiedOrderRequest.setNonce_str(StringUtils.makeUUID());
        // 商户订单号，必填
        // 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一
        // 重新发起一笔支付要使用原订单号，避免重复支付，已支付过或已调用关单、撤销的订单号不能重新发起支付。
        unifiedOrderRequest.setOut_trade_no(orderId);
        // 签名，必填
        unifiedOrderRequest.setSign(PayToolUtil.createSign(unifiedOrderRequest));
        // 将订单对象转为xml格式
        // 使用Xstream时，由于微信定义的变量名大部分使用了“_”,但是在Xstream中它是关键字，所以会自动变为“__”，引起报错
        XStream xStream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
        // 根元素名需要是xml
        xStream.alias("xml", WeixinOrderRequest.class);
        return xStream.toXML(unifiedOrderRequest);
    }

    /**
     * 调用微信API
     *
     * @param orderInfo -- 微信下单/查询请求参数
     * @param url       -- 微信API地址
     * @return
     * @author GaoYa
     * @date 2018-08-27 下午15:39:00
     */
    private HashMap<String, Object> httpOrder(String orderInfo, String url) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            // 加入数据
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedOutputStream buffOutStr = new BufferedOutputStream(conn.getOutputStream());
            buffOutStr.write(orderInfo.getBytes("UTF-8"));
            buffOutStr.flush();
            buffOutStr.close();

            // 获取输入流
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            // 使用Xstream时，由于微信定义的变量名大部分使用了“_”,但是在Xstream中它是关键字，所以会自动变为“__”，引起报错
            XStream xStream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
            // 将请求返回的内容通过xStream转换为UnifiedOrderRespose对象
            xStream.ignoreUnknownElements();
            log.debug("已忽略xml中额外属性，例如鼓励金");
            xStream.alias("xml", WeixinOrderRespose.class);
            WeixinOrderRespose unifiedOrderRespose = (WeixinOrderRespose) xStream.fromXML(sb.toString());

            if (url.contains("unifiedorder")) {
                log.debug(this.getClass().getName() + " method openWxPay, order_no:" + unifiedOrderRespose.getOut_trade_no() + " 下单接口");
                // 调用微信统一下单API，获得微信预支付交易链接（code_url），对应链接格式：weixin：//wxpay/bizpayurl?sr=XXXXX
                // 获取二维码链接时，根据微信API，只有在return_code和result_code都为SUCCESS时，才有返回code_url
                // return_code：SUCCESS/FAIL，此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
                // result_code：SUCCESS/FAIL，此字段是交易标识，即业务结果
                if (null != unifiedOrderRespose
                        && Constant.WEIXIN_ORDER_SUCCESS.equals(unifiedOrderRespose.getReturn_code())
                        && Constant.WEIXIN_ORDER_SUCCESS.equals(unifiedOrderRespose.getResult_code())) {
                    map.put("success", true);
                    map.put("code_url", unifiedOrderRespose.getCode_url());
                    log.debug(this.getClass().getName() + " method openWxPay, order_no:" + unifiedOrderRespose.getOut_trade_no() + " 下单接口：下单成功");
                    return map;
                } else {
                    if (Constant.WEIXIN_ORDER_FAIL.equals(unifiedOrderRespose.getReturn_code())) {
                        map.put("success", false);
                        map.put("msg", unifiedOrderRespose.getReturn_msg());
                        map.put("unifiedOrderRespose", unifiedOrderRespose);
                        log.debug(this.getClass().getName() + " method openWxPay, order_no:" + unifiedOrderRespose.getOut_trade_no() + " 下单接口：通讯失败");
                        return map;
                    }
                    if (Constant.WEIXIN_ORDER_FAIL.equals(unifiedOrderRespose.getResult_code())) {
                        map.put("success", false);
                        map.put("msg", unifiedOrderRespose.getErr_code() + "：" + unifiedOrderRespose.getErr_code_des());
                        log.debug(this.getClass().getName() + " method openWxPay, order_no:" + unifiedOrderRespose.getOut_trade_no() + " 下单接口：业务结果失败,返回码：" + unifiedOrderRespose.getErr_code() + "：" + unifiedOrderRespose.getErr_code_des());
                        map.put("unifiedOrderRespose", unifiedOrderRespose);
                        return map;
                    }
                }
            } else if (url.contains("orderquery")) {
                log.debug(this.getClass().getName() + " method wxpayTradeQuery, order_no:" + unifiedOrderRespose.getOut_trade_no() + " 查询订单");
                // 调用微信查询订单API
                // 获取微信支付结果时，根据微信API，只有在return_code、result_code、trade_state都为SUCCESS时，才有返回transaction_id等微信交易信息
                // return_code：SUCCESS/FAIL，此字段是通信标识，非交易标识，交易是否成功需要查看result_code和trade_state来判断
                // result_code：SUCCESS/FAIL，此字段是业务结果
                // trade_state：此字段是交易状态，SUCCESS—支付成功 REFUND—转入退款 NOTPAY—未支付 CLOSED—已关闭 REVOKED—已撤销（刷卡支付） USERPAYING--用户支付中 PAYERROR--支付失败(其他原因，如银行返回失败)
                if (null != unifiedOrderRespose
                        && Constant.WEIXIN_ORDER_SUCCESS.equals(unifiedOrderRespose.getReturn_code())
                        && Constant.WEIXIN_ORDER_SUCCESS.equals(unifiedOrderRespose.getResult_code())
                        && Constant.WEIXIN_ORDER_SUCCESS.equals(unifiedOrderRespose.getTrade_state())) {
                    map.put("success", true);
                    map.put("trade_no", unifiedOrderRespose.getTransaction_id());
                    map.put("total_fee", unifiedOrderRespose.getTotal_fee());
                    map.put("openid", unifiedOrderRespose.getOpenid());
                    map.put("trade_state", unifiedOrderRespose.getTrade_state());
                    log.debug(this.getClass().getName() + " method wxpayTradeQuery, order_no:" + unifiedOrderRespose.getOut_trade_no() + " 查询订单：支付成功");
                    return map;
                } else {
                    if (Constant.WEIXIN_ORDER_FAIL.equals(unifiedOrderRespose.getReturn_code())) {
                        map.put("success", false);
                        map.put("msg", unifiedOrderRespose.getReturn_msg());
                        map.put("unifiedOrderRespose", unifiedOrderRespose);
                        log.debug(this.getClass().getName() + " method wxpayTradeQuery, order_no:" + unifiedOrderRespose.getOut_trade_no() + " 查询订单：通讯失败，msg：" + unifiedOrderRespose.getReturn_msg());
                        return map;
                    }
                    if (Constant.WEIXIN_ORDER_FAIL.equals(unifiedOrderRespose.getResult_code())) {
                        map.put("success", false);
                        map.put("msg", unifiedOrderRespose.getErr_code() + "：" + unifiedOrderRespose.getErr_code_des());
                        map.put("unifiedOrderRespose", unifiedOrderRespose);
                        log.debug(this.getClass().getName() + " method wxpayTradeQuery, order_no:" + unifiedOrderRespose.getOut_trade_no() + " 查询订单：业务结果失败,返回码：" + unifiedOrderRespose.getErr_code() + "：" + unifiedOrderRespose.getErr_code_des());
                        return map;
                    }
                    if (!Constant.WEIXIN_ORDER_SUCCESS.equals(unifiedOrderRespose.getTrade_state())) {
                        map.put("success", false);
                        map.put("msg", unifiedOrderRespose.getTrade_state() + "：" + unifiedOrderRespose.getTrade_state_desc());
                        map.put("unifiedOrderRespose", unifiedOrderRespose);
                        log.debug(this.getClass().getName() + " method wxpayTradeQuery, order_no:" + unifiedOrderRespose.getOut_trade_no() + " 查询订单：业务结果成功，交易状态 " + unifiedOrderRespose.getTrade_state());
                        return map;
                    }
                }
            }
        } catch (Exception e) {
            log.debug(this.getClass().getName() + " method openWxPay/wxpayTradeQuery, 出现被捕获异常：url【" + url + "】,orderInfo【" + orderInfo + "】");
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 回调函数
     * <p>
     * 微信平台发起的回调方法，将扫码支付的处理结果告知商户系统
     *
     * @return
     * @throws JDOMException
     * @throws Exception
     * @author GaoYa
     * @date 2018-08-28 下午14:18:00
     */
    @RequestMapping("weixinNotify")
    public void weixinNotify(HttpServletRequest request, HttpServletResponse response) throws JDOMException, Exception {
        System.out.println("=================微信平台发起的回调=====================");
        // 读取参数
        InputStream inputStream;
        StringBuffer sb = new StringBuffer();
        inputStream = request.getInputStream();
        String s;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        in.close();
        inputStream.close();

        // 解析xml成map
        Map<String, String> m = new HashMap<String, String>();
        m = XMLUtil4jdom.doXMLParse(sb.toString());

        // 过滤空 设置 TreeMap
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        Iterator it = m.keySet().iterator();
        while (it.hasNext()) {
            String parameter = (String) it.next();
            String parameterValue = m.get(parameter);

            String v = "";
            if (null != parameterValue) {
                v = parameterValue.trim();
            }
            packageParams.put(parameter, v);
        }

        // 微信key
        String key = WeixinPayConfig.API_KEY; // key

        // 判断签名是否正确
        if (PayToolUtil.isTenpaySign("UTF-8", packageParams, key)) {
            // ------------------------------
            // 处理业务开始
            // ------------------------------
            String resXml = "";
            if (Constant.WEIXIN_ORDER_SUCCESS.equals((String) packageParams.get("return_code"))
                    && Constant.WEIXIN_ORDER_SUCCESS.equals((String) packageParams.get("result_code"))) {
                System.out.println("=================微信平台发起的回调， 这里是支付成功=====================");
                // 解析各种数据
                String openid = (String) packageParams.get("openid");// 用户标识
                String out_trade_no = (String) packageParams.get("out_trade_no");// 获取商户订单号
                String total_fee = (String) packageParams.get("total_fee");// 获取订单金额
                String transaction_id = (String) packageParams.get("transaction_id");// 微信支付订单号
                // 验证支付单
                MachinePayOrder order = machinePayOrderService.queryOrderByOrderNo(out_trade_no);
                // 支付单已支付
                if (Constant.PAY_STATUS_PAYED.equals(order.getPay_status())) {
                    // 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
                    resXml = XMLUtil4jdom.setXml(Constant.WEIXIN_ORDER_SUCCESS, "OK");
                } else {
                    String total_fee_ = AmountUtil.changeF2Y(String.valueOf(packageParams.get("total_fee")));
                    // 支付单未支付，验证支付金额是否和支付单一致
                    if (null != order && Double.parseDouble(total_fee_) == order.getPay_total()
                            && Constant.PAY_STATUS_UNPAY.equals(order.getPay_status())) {
                        // 更新支付单
                        order.setPay_status(Constant.PAY_STATUS_PAYED);
                        order.setPay_no(transaction_id);
                        order.setPay_type(Constant.PAY_TYPE_WEIXIN);
                        order.setPay_account(openid);
                        order.setPayed_total(Double.parseDouble(total_fee_
                        ));
                        machinePayOrderService.updateOrder(order);

                        // 保存交易记录
                        machinePayTradeService.saveWxpayTrade(order, packageParams);

                        // 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
                        resXml = XMLUtil4jdom.setXml(Constant.WEIXIN_ORDER_SUCCESS, "OK");
                    } else {
                        resXml = XMLUtil4jdom.setXml(Constant.WEIXIN_ORDER_FAIL, "需要支付的金额为：" + order.getPay_total()
                                + "元，已支付：" + Double.parseDouble(total_fee) + "元，支付未完成！");
                    }
                }
            } else {
                System.out.println("=================微信平台发起的回调， 这里是支付未完成=====================");
                resXml = XMLUtil4jdom.setXml(Constant.WEIXIN_ORDER_FAIL, "报文为空");
            }

            // 向微信服务器发送确认信息，若不发送，微信服务器会间隔不同的时间调用回调方法
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        } else {
            System.out.println("=================微信平台发起回调，通知签名验证失败=====================");
        }
    }

    public String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }
}
