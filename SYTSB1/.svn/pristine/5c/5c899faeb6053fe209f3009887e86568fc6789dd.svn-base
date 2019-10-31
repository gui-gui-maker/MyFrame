package com.lsts.inspection.web;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.bean.Employee;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.employee.bean.EmployeePrinter;
import com.lsts.inspection.bean.InspectionInfoPay;
import com.lsts.inspection.service.InspectionInfoPayService;

/**
 * 检验检测收费标准配置控制器
 * 
 * @ClassName InspectionInfoPayAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-12-30 上午09:47:00
 */
@Controller
@RequestMapping("inspectionInfo/pay")
public class InspectionInfoPayAction extends
		SpringSupportAction<InspectionInfoPay, InspectionInfoPayService> {

	@Autowired
	private InspectionInfoPayService inspectionInfoPayService;
	
	/**
	 * 保存
	 * 
	 * @param request
	 * @param employee
	 * @throws Exception
	 */
	@RequestMapping(value = "saveBasic")
	@ResponseBody
	public HashMap<String, Object> saveBasic(HttpServletRequest request,
			InspectionInfoPay inspectionInfoPay) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		try {
			inspectionInfoPay.setData_status("0");	// 状态（0：启用 99：停用）
			inspectionInfoPay.setLast_mdy_userid(user.getId());
			inspectionInfoPay.setLast_mdy_username(user.getName());
			inspectionInfoPay.setLast_mdy_date(new Date());
			inspectionInfoPayService.save(inspectionInfoPay);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败，请重试！");
		}
		return JsonWrapper.successWrapper(inspectionInfoPay);
	}

	// 删除检验检测收费标准配置信息
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(HttpServletRequest request, String ids) throws Exception {
		inspectionInfoPayService.del(request, ids);
		return JsonWrapper.successWrapper(ids);
	}
	
}
