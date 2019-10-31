package com.scts.car.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.scts.car.bean.CarCmDetail;
import com.scts.car.bean.CarInsDetail;
import com.scts.car.service.CarCmDetailManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("carCmDetailAction")
public class CarCmDetailAction extends SpringSupportAction<CarCmDetail, CarCmDetailManager> {

	@Autowired
	private CarCmDetailManager carCmDetailManager;

	/**
	 * 保存保养提醒信息
	 * 
	 * @param request
	 * @param carCm
	 * @return
	 */
	@RequestMapping(value = "saveInfo")
	@ResponseBody
	public HashMap<String, Object> saveInfo(HttpServletRequest request, @RequestBody CarCmDetail carCmDetail) {
		try {
			return carCmDetailManager.saveInfo(request, carCmDetail);
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}

	/**
	 * 删除保养提醒信息
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteInfo")
	@ResponseBody
	public HashMap<String, Object> deleteInfo(HttpServletRequest request, String id) throws Exception {
		return carCmDetailManager.deleteInfo(request, id);
	}
	/**
	 * 保存历史保养提醒信息
	 * 
	 * @param request
	 * @param carInsDetail
	 * @return
	 */
	@RequestMapping(value = "saveHistoryInfo")
	@ResponseBody
	public HashMap<String, Object> saveHistoryInfo(HttpServletRequest request, @RequestBody CarCmDetail carCmDetail) {
		try {
			return carCmDetailManager.saveHistoryInfo(request, carCmDetail);
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}
	/**
	 * 获取保养提醒信息详情
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
			return carCmDetailManager.getDetail(id);
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}
}