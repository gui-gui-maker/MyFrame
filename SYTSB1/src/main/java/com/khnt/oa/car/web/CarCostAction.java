package com.khnt.oa.car.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.oa.car.bean.CarCost;
import com.khnt.oa.car.service.CarCostManager;
import com.khnt.utils.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
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
@Controller
@RequestMapping("oa/car/cost/")
public class CarCostAction extends
		SpringSupportAction<CarCost, CarCostManager> {

	@Autowired
	private CarCostManager carCostManager;

	@Override
	public HashMap<String, Object> save(HttpServletRequest request, CarCost carCost) throws Exception {
		if(StringUtil.isEmpty(carCost.getId())){
			carCost.setWriteTime(new Date());
		}
		return super.save(request, carCost);
	}
	
}
