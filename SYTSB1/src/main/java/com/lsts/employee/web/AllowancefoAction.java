package com.lsts.employee.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.lsts.employee.bean.Allowancefo;
import com.lsts.employee.service.AllowancefoService;


@Controller
@RequestMapping("allowancefoAction")
public class AllowancefoAction extends SpringSupportAction<Allowancefo, AllowancefoService>{
	@Autowired
	private AllowancefoService allowancefoService;

	@RequestMapping(value = "getDetailByOp")
	@ResponseBody
	public HashMap<String, Object> getDetailByOp(HttpServletRequest request,String id,String opId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			Allowancefo allowancefo= allowancefoService.getDetailByOp(request, id,opId);
			map.put("data", allowancefo);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "查询失败！");
		}
		return map;
	}
}
