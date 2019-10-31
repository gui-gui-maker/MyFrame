package com.scts.car.web;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.scts.car.bean.CarFuelCard;
import com.scts.car.service.CarFuelCardManager;

@Controller
@RequestMapping("carFuelCardAction")
public class CarFuelCardAction extends SpringSupportAction<CarFuelCard, CarFuelCardManager> {

	@Autowired
	private CarFuelCardManager carFuelCardManager;

	/**
	 * 获取油卡详情
	 * 
	 * @param request
	 * @param carWbRecord
	 * @return
	 */
	@RequestMapping(value = "getDetail")
	@ResponseBody
	public HashMap<String, Object> getDetail(HttpServletRequest request) {
		String id = request.getParameter("id");
		try {
			return carFuelCardManager.getDetail(id);
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}

	/**
	 * 保存油卡信息
	 * 
	 * @param request
	 * @param carCm
	 * @return
	 */
	@RequestMapping(value = "saveInfo")
	@ResponseBody
	public HashMap<String, Object> saveInfo(HttpServletRequest request, CarFuelCard carFuelCard) {
		try {
			return carFuelCardManager.saveInfo(request, carFuelCard);
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}

	/**
	 * 初始化本月数据
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "initialize")
	@ResponseBody
	public HashMap<String, Object> initialize() {
		try {
			carFuelCardManager.carFuelCardUpdate();
			;
			return JsonWrapper.failureWrapperMsg("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("操作失败！");
		}
	}

	/**
	 * 根据年月份、车辆id、油卡号查询油卡记录（若车辆id不存在则依据油卡号查询条件）
	 * 
	 * @param fkCarId
	 * @param yearMonth
	 * @return
	 */
	@RequestMapping(value = "getCarFuelCard")
	@ResponseBody
	public HashMap<String, Object> getCarFuelCard(String fkCarId, Date yearMonth, String cardNum) {
		return JsonWrapper.successWrapper(carFuelCardManager.getCarFuelCard(fkCarId, yearMonth, cardNum));
	}
}