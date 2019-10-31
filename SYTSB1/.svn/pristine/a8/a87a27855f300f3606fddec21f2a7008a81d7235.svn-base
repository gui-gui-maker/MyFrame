package com.fwxm.dining.web;


import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fwxm.dining.bean.Food;
import com.fwxm.dining.bean.SeatOrder;
import com.fwxm.dining.service.FoodService;
import com.fwxm.dining.service.SeatOrderService;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;

@Controller
@RequestMapping("dining/seatOrder")
public class SeatOrderAction extends SpringSupportAction<SeatOrder, SeatOrderService>{
	@Autowired
	private SeatOrderService seatOrderService;
	@Autowired
	private FoodService foodService;
	
	@RequestMapping("saveSeatOrder")
	@ResponseBody
	public HashMap<String,Object> saveSeatOrder(HttpServletRequest request,SeatOrder entity)throws Exception{
		
		try{
			if(entity.getSostatus()!=0){
				return JsonWrapper.failureWrapperMsg("订单已生效，保存订单失败！");
			}
			if(entity.getEmp_id()==null){
				CurrentSessionUser user = SecurityUtil.getSecurityUser();
				User u = (User) user.getSysUser();
				Employee e = u.getEmployee();
				entity.setEmp_id(e.getId());
				entity.setOrder_man(e.getName());
			}
			entity.setSostatus(0);
			entity.setOrder_time(new Date());
			String[] ids = entity.getFood_ids().split(",");
			StringBuffer fns = new StringBuffer(); 
			for(String id:ids){
				Food f = foodService.get(id);
				fns.append(f.getName()+",");
			}
			String foodNames = fns.toString().substring(0,fns.toString().length()-1);
			entity.setFood_names(foodNames);
			seatOrderService.save(entity);
		}catch(Exception e){
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存订单失败！");
		}
		return JsonWrapper.successWrapper(entity);
	}
	@RequestMapping("getSeatOrder")
	@ResponseBody
	public HashMap<String,Object> getSeatOrder(HttpServletRequest request,String id)throws Exception{
		SeatOrder so =null;
		try{
			so = seatOrderService.get(id);
		}catch(Exception e){
			e.printStackTrace();
			return JsonWrapper.failureWrapper("获取信息失败！");
		}
		return JsonWrapper.successWrapper(so);
	}
	
	@RequestMapping(value = "deleteSeatOrders")
	@ResponseBody
	public HashMap<String, Object> deleteSeatOrders(String ids) throws Exception {
		seatOrderService.deleteSeatOrders(ids);
		return JsonWrapper.successWrapper(ids);
	}
}
