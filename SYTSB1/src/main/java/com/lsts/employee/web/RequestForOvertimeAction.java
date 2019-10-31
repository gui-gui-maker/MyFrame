
package com.lsts.employee.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.lsts.employee.bean.RequestForOvertime;
import com.lsts.employee.service.RequestForOvertimeManager;


/**********************************************************
 * @author WSL
 * @date 创建时间：2017年12月19日 上午10:27:27
 * @version 1.0
 ***********************************************************/

@Controller
@RequestMapping("requestForOvertimeAction")
public class RequestForOvertimeAction extends SpringSupportAction<RequestForOvertime, RequestForOvertimeManager> {

	@Autowired
	private RequestForOvertimeManager requestForOvertimeManager;
	
	@ResponseBody
	@RequestMapping("savaOvertime")
	public HashMap<String, Object> savaOvertime(HttpServletRequest request, RequestForOvertime entity) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			requestForOvertimeManager.save(entity);
			map.put("success", true);
			map.put("data", entity);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "保存失败！请重试！");
			//return JsonWrapper.failureWrapperMsg("保存失败！请重试！");
		}
		return map;
	}
	
	/**
	 * 删除方法，逻辑删除
	 * @param request
	 * @param ids
	 * @return
	 */
	@RequestMapping("deleteOvertime")
	@ResponseBody
	public HashMap<String, Object> delInspect(HttpServletRequest request, String ids) {
		HashMap<String, Object>  map = new HashMap<String, Object>();
		try {
			requestForOvertimeManager.deleteOvertime(request, ids);
			map.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				return JsonWrapper.failureWrapperMsg("删除失败!请重试！");
		}
		return map;
	}
	
	/**
	 * 提交流程
	 * @param request
	 * @param ids
	 * @param step
	 * @return
	 */
	@RequestMapping(value = "subOvertime")
	@ResponseBody
	public HashMap<String, Object> subOvertime(HttpServletRequest request,String ids,String step) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			requestForOvertimeManager.subOvertime(request, ids,step);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "提交失败！");
		}
		return map;
	}
}
