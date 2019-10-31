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
import com.scts.car.bean.CarMpg;
import com.scts.car.service.CarMpgManager;

@Controller
@RequestMapping("carMpgAction")
public class CarMpgAction extends SpringSupportAction<CarMpg, CarMpgManager> {

	@Autowired
	private CarMpgManager carMpgManager;

	/**
	 * 获取油耗详情
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
			return carMpgManager.getDetail(id);
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}

	/**
	 * 保存油耗信息
	 * 
	 * @param request
	 * @param carCm
	 * @return
	 */
	@RequestMapping(value = "saveInfo")
	@ResponseBody
	public HashMap<String, Object> saveInfo(HttpServletRequest request, CarMpg carMpg) {
		try {
			return carMpgManager.saveInfo(request, carMpg);
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
			carMpgManager.carMpgUpdate();
			return JsonWrapper.successWrapper("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("操作失败！");
		}
	}

	/**
	 * 根据车辆id和年月份查询油耗记录
	 * 
	 * @param fkCarId
	 * @param yearMonth
	 * @return
	 */
	@RequestMapping(value = "getCarMpg")
	@ResponseBody
	public HashMap<String, Object> getCarMpg(String fkCarId, Date yearMonth) {
		return JsonWrapper.successWrapper(carMpgManager.getCarMpg(fkCarId, yearMonth));
	}

}