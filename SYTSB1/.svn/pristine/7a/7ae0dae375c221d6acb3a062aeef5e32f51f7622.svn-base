package com.fwxm.dining.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fwxm.dining.bean.FoodCard;
import com.fwxm.dining.service.EmployeefService;
import com.fwxm.dining.service.FoodCardService;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.bean.User;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;

@Controller
@RequestMapping("dining/foodCard")
public class FoodCardAction extends SpringSupportAction<FoodCard, FoodCardService>{
	@Autowired
	private FoodCardService foodCardService;
	@Autowired
	private EmployeefService employeefService;
	
	/**
	 * 绑定新卡
	 * @param request
	 * @param foodCard
	 * @param action
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveCard")
	@ResponseBody
	public HashMap<String, Object> saveCard(HttpServletRequest request,
			FoodCard foodCard) throws Exception {
		HashMap<String, Object> map = new HashMap<String,Object>();
		
		try {			
				Employee e = employeefService.get(foodCard.getUserId());
				foodCard.setTel(e.getMobileTel());
				foodCard.setCardStatus(FoodCard.NORMAL);
				//根据输入信息查出员工信息，并件员工信息存入卡中
				foodCardService.saveCard(foodCard);
				map.put("success", true);
				map.put("data", foodCard);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	

	@RequestMapping(value = "detailCard")
	@ResponseBody
	public HashMap<String, Object> detailCard(HttpServletRequest request, String id)
			throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try{
			FoodCard foodCard = foodCardService.get(id);
			wrapper.put("success", true);
			wrapper.put("data", foodCard);
		}catch(Exception e){
			e.printStackTrace();
			return JsonWrapper.failureWrapper(11);
		}
		return wrapper;
	}
	@RequestMapping(value = "detailCardByNo")
	@ResponseBody
	public HashMap<String, Object> detailCardByNo(HttpServletRequest request, String card_no)
			throws Exception {
		FoodCard foodCard = foodCardService.getCardByCardNo(card_no);
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", true);
		wrapper.put("data", foodCard);
		return wrapper;
	}
	@RequestMapping(value = "deleteCards")
	@ResponseBody
	public HashMap<String, Object> deleteCards(String ids) throws Exception {
		foodCardService.deleteCards(ids);
		return JsonWrapper.successWrapper(ids);
	}
	
	@RequestMapping(value = "recharge")
	@ResponseBody
	public HashMap<String, Object> recharge(String id,Integer addCount) throws Exception {
		CurrentSessionUser csu = SecurityUtil.getSecurityUser();
		User user = csu.getSysUser();
		FoodCard card = foodCardService.recharge(id,addCount,user.getAccount());
		return JsonWrapper.successWrapper(card);
	}
}
