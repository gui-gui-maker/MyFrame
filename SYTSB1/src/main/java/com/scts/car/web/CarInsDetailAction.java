package com.scts.car.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.scts.car.bean.CarInsDetail;
import com.scts.car.service.CarInsDetailManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("carInsDetailAction")
public class CarInsDetailAction extends SpringSupportAction<CarInsDetail, CarInsDetailManager> {

	@Autowired
	private CarInsDetailManager carInsDetailManager;

	/**
	 * 保存保险信息
	 * 
	 * @param request
	 * @param carInsDetail
	 * @return
	 */
	@RequestMapping(value = "saveInfo")
	@ResponseBody
	public HashMap<String, Object> saveInfo(HttpServletRequest request, @RequestBody CarInsDetail carInsDetail) {
		try {
			return carInsDetailManager.saveInfo(request, carInsDetail);
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}

	/**
	 * 删除保险信息
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteInfo")
	@ResponseBody
	public HashMap<String, Object> deleteInfo(HttpServletRequest request, String id) throws Exception {
		return carInsDetailManager.deleteInfo(request, id);
	}

	/**
	 * 保存历史保险信息
	 * 
	 * @param request
	 * @param carInsDetail
	 * @return
	 */
	@RequestMapping(value = "saveHistoryInfo")
	@ResponseBody
	public HashMap<String, Object> saveHistoryInfo(HttpServletRequest request, @RequestBody CarInsDetail carInsDetail) {
		try {
			return carInsDetailManager.saveHistoryInfo(request, carInsDetail);
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}
}