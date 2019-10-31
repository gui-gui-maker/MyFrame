package com.scts.maintenance.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.scts.maintenance.bean.Maintenance;
import com.scts.maintenance.service.MaintenanceService;

/**
 * 系统更新维护日志（系统建设台账）管理控制器
 * 
 * @ClassName MaintenanceAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-09-08 下午01:45:00
 */
@Controller
@RequestMapping("maintenance")
public class MaintenanceAction extends
		SpringSupportAction<Maintenance, MaintenanceService> {
	@Autowired
	private MaintenanceService maintenanceService;
	

	// 批量无附件保存
	@RequestMapping(value = "saveBasic")
	@ResponseBody
	public HashMap<String, Object> saveBasic(HttpServletRequest request,
			@RequestBody
			Maintenance maintenance) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			Maintenance info = maintenanceService.saveBasic(
					maintenance, request);
			wrapper.put("success", true);
			wrapper.put("obj", info);
		} catch (Exception e) {
			log.debug(e.toString());
			wrapper.put("success", false);
			wrapper.put("msg", "保存失败！");
			e.printStackTrace();
		}
		return wrapper;
	}
	

	// 获取维护日志信息
	@RequestMapping(value = "getDetail")
	@ResponseBody
	public HashMap<String, Object> getDetail(HttpServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		try {
			return maintenanceService.getDetail(id);
		} catch (Exception e) {
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("读取报送打印信息失败！");
		}
	}
	

	// 删除检验
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(HttpServletRequest request, String ids)
			throws Exception {
		String[] idArr = ids.split(",");
		for (int i = 0; i < idArr.length; i++) {
			maintenanceService.del(request, idArr[i]);
		}
		return JsonWrapper.successWrapper(ids);
	}
}
