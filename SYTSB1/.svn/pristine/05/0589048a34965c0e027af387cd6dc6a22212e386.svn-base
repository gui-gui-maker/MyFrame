package com.scts.payment.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.scts.payment.bean.InspectionPayInfo;
import com.scts.payment.bean.PayInfoUnit;
import com.scts.payment.service.PayInfoUnitService;

@Controller
@RequestMapping("pay/info/unit")
public class PayInfoUnitAction extends SpringSupportAction<PayInfoUnit, PayInfoUnitService> {

	@Autowired
	private PayInfoUnitService payInfoUnitService;
	
	// 保存收费信息科室分配
	@RequestMapping(value = "savePayUnit")
	@ResponseBody
	public HashMap<String, Object> savePayUnit(HttpServletRequest request) {

		String data = request.getParameter("data");
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			
			payInfoUnitService.savePayUnit(data);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "保存失败！");
		}
	
		return map;
	}
	
	@RequestMapping(value = "getDetailByPayId")
	@ResponseBody
	public HashMap<String, Object> getDetailByPayId(HttpServletRequest request,String payId) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			
			InspectionPayInfo inspectionPayInfo = payInfoUnitService.getDetailByPayId(payId);
			map.put("data", inspectionPayInfo);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "保存失败！");
		}
	
		return map;
	}
}
