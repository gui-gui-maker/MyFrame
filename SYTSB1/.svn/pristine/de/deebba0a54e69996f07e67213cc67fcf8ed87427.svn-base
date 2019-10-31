package com.scts.payment.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.utils.BeanUtils;
import com.lsts.constant.Constant;
import com.lsts.finance.bean.CwBankDTO;
import com.lsts.finance.bean.CwBankDetail;
import com.scts.payment.bean.MachinePayTrade;
import com.scts.payment.service.MachinePayTradeService;

import util.TS_Util;

/**
 * 智能一体机在线支付交易记录（支付宝和微信）业务控制器
 * 
 * @ClassName MachinePayTradeAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2018-07-10 下午16:36:00
 */
@Controller
@RequestMapping("pay/trade")
public class MachinePayTradeAction extends SpringSupportAction<MachinePayTrade, MachinePayTradeService> {

	@Autowired
	private MachinePayTradeService machinePayTradeService;

	/**
	 * 获取在线支付交易记录
	 * @param id -- 在线支付交易ID
	 * 
	 * @return 
	 * @author GaoYa
	 * @date 2018-07-10 下午16:45:00
	 */
	@RequestMapping(value = "queryTradeInfos")
	@ResponseBody
	public HashMap<String, Object> queryTradeInfos(HttpServletRequest request, String id)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<MachinePayTrade> list = new ArrayList<MachinePayTrade>(); 
			MachinePayTrade machinePayTrade = machinePayTradeService.get(id);
			if (machinePayTrade != null) {
				if(Constant.MACHINE_PAY_TYPE_ALIPAY_CODE.equals(machinePayTrade.getPay_type())) {
					machinePayTrade.setPay_type(Constant.MACHINE_PAY_TYPE_ALIPAY_TEXT);
				}else if(Constant.MACHINE_PAY_TYPE_WEIXIN_CODE.equals(machinePayTrade.getPay_type())) {
					machinePayTrade.setPay_type(Constant.MACHINE_PAY_TYPE_WEIXIN_TEXT);
				}
				list.add(machinePayTrade);
			}
			map.put("list", list);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("获在线支付交易记录失败！");
		}
		return map;
	}
}
