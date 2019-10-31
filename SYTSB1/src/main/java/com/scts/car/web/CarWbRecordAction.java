package com.scts.car.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.scts.car.bean.CarWbRecord;
import com.scts.car.service.CarWbRecordManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("carWbRecordAction")
public class CarWbRecordAction extends SpringSupportAction<CarWbRecord, CarWbRecordManager> {

	@Autowired
	private CarWbRecordManager carWbRecordManager;

	/**
	 * 保存维保记录
	 * 
	 * @param request
	 * @param carWbRecord
	 * @return
	 */
	@RequestMapping(value = "saveInfo")
	@ResponseBody
	public HashMap<String, Object> saveInfo(HttpServletRequest request, @RequestBody CarWbRecord carWbRecord) {
		try {
			return carWbRecordManager.saveInfo(request, carWbRecord);
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}

	/**
	 * 获取维保记录
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
			return carWbRecordManager.getDetail(id);
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败！");
		}
	}

	/**
	 * 删除维保记录
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteInfo")
	@ResponseBody
	public HashMap<String, Object> deleteInfo(HttpServletRequest request, String id) throws Exception {
		return carWbRecordManager.deleteInfo(request, id);
	}

	/**
	 * 根据维保申请id获取维保记录
	 * 
	 * @param request
	 * @param fkWbId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getWbRecordsByFkWbId")
	@ResponseBody
	public HashMap<String, Object> getWbRecordsByFkWbId(HttpServletRequest request, String fkWbId) throws Exception {
		return carWbRecordManager.getWbRecordsByFkWbId(request, fkWbId);
	}

	/**
	 * 根据车辆id获取维保记录
	 * 
	 * @param request
	 * @param fkCarId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getWbRecordsByFkCarId")
	@ResponseBody
	public HashMap<String, Object> getWbRecordsByFkCarId(HttpServletRequest request, String fkCarId) throws Exception {
		return carWbRecordManager.getWbRecordsByFkCarId(request, fkCarId);
	}
}