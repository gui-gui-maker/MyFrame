package com.khnt.oa.car.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.oa.car.bean.CarRent;
import com.khnt.oa.car.service.CarRentManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;

/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName OrgAction
 * @JDK 1.5
 * @author
 * @date
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("oa/car/rent/")
public class CarRentAction extends
		SpringSupportAction<CarRent, CarRentManager> {

	@Autowired
	private CarRentManager carRentManager;
	
	
	
	
	@Override
	public HashMap<String, Object> save(HttpServletRequest request,
			CarRent entity) throws Exception {
		try{
			entity.setAgentId(this.getCurrentUser().getId());
			entity.setAgentName(this.getCurrentUser().getName());
			entity.setStatus("0");
			carRentManager.save(entity);
			return super.save(request, entity);
		}
		catch(Exception e){
			return JsonWrapper.failureWrapperMsg("保存租车信息失败："+e.getMessage());
		}
	}

	@RequestMapping(value = "backRentCar")
	@ResponseBody
	public HashMap<String, Object> backRentCar(String id, CarRent carRent) throws Exception {
		if (carRentManager.backRentCar(id, carRent)) {
			return JsonWrapper.successWrapper();
		} else {
			return JsonWrapper.failureWrapper();
		}
	}
}
