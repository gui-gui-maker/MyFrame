package com.fwxm.dining.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fwxm.dining.bean.FoodCard;
import com.fwxm.dining.bean.FoodEvaluation;
import com.fwxm.dining.bean.FoodOrder;
import com.fwxm.dining.service.FoodCardService;
import com.fwxm.dining.service.FoodEvaluationService;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

@Controller
@RequestMapping("dining/eval")
public class FoodEvaluationlAction extends SpringSupportAction<FoodEvaluation, FoodEvaluationService>{
	@Autowired
	private FoodEvaluationService foodEvaluationService;
	@Autowired
	private FoodCardService foodCardService;
	
	@RequestMapping(value = "saveEval")
	@ResponseBody
	public HashMap<String, Object> saveEval(HttpServletRequest request,
			FoodEvaluation foodEvaluation) throws Exception {
		try {
			FoodCard fd = null;
				//根据用户id查处一卡通的信息，并将一卡通的id存入订单
				CurrentSessionUser user = SecurityUtil.getSecurityUser();
				User u = (User) user.getSysUser();
				Employee e = u.getEmployee();
				foodEvaluation.setEmp_id(e.getId());
				foodEvaluation.setEval_man(e.getName());
				foodEvaluation.setEval_time(new Date());
				foodEvaluationService.saveEval(foodEvaluation); 
	
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存订单详情信息失败，请重试！");
		}
		return JsonWrapper.successWrapper(foodEvaluation);
	}
	
	@RequestMapping(value = "getList")
	@ResponseBody
	public HashMap<String, Object> getList(HttpServletRequest request,String id) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		List<Map> datalist = new ArrayList<Map>();
		try {
			if (StringUtil.isNotEmpty(id)) {
				List<FoodEvaluation> list = foodEvaluationService.queryEvalByPubmId(id);
				if(!list.isEmpty()){
					for (FoodEvaluation eval : list) {
						Map map = eval.to_Map();
						datalist.add(map);
					}
				}
				wrapper.put("success", true);
				wrapper.put("datalist", datalist);
			}
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("error", true);
		}
		return wrapper;
	}
	@RequestMapping(value = "detailEval")
	@ResponseBody
	public HashMap<String, Object> detail(HttpServletRequest request, String id)
			throws Exception {
		FoodEvaluation eval = foodEvaluationService.get(id);
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", true);
		wrapper.put("data", eval);
		return wrapper;
	}
	@RequestMapping(value = "deleteEvals")
	@ResponseBody
	public HashMap<String, Object> deleteEvals(String ids) throws Exception {
		foodEvaluationService.deleteEvals(ids);
		return JsonWrapper.successWrapper(ids);
	}
}
