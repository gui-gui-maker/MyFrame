package com.scts.payment.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.security.CurrentSessionUser;
import com.scts.payment.bean.InspectionPayDetail;
import com.scts.payment.service.InspectionPayDetailService;

/**
 * 报检收费详细信息控制器
 * 
 * @ClassName InspectionPayDetailAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-05-04 下午04:49:00
 */
@Controller
@RequestMapping("payment/payDetail")
public class InspectionPayDetailAction extends
		SpringSupportAction<InspectionPayDetail, InspectionPayDetailService> {
	@Autowired
	private InspectionPayDetailService inspectionPayDetailService;


	// 保存
	@RequestMapping(value = "savePayDetail")
	@ResponseBody
	public HashMap<String, Object> savePayDetail(
			InspectionPayDetail inspectionPayDetail) {
		try {
			CurrentSessionUser user = super.getCurrentUser();
			
			inspectionPayDetailService.save(inspectionPayDetail);
			return JsonWrapper.successWrapper(inspectionPayDetail);
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			return JsonWrapper.failureWrapper("保存失败！");
		}
	}

	// 删除
	@RequestMapping(value = "deletePayDetail")
	@ResponseBody
	public HashMap<String, Object> delete(
			HttpServletRequest request, String ids) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			String[] idArr = ids.split(",");


			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			wrapper.put("error", true);
			return JsonWrapper.failureWrapper("报检收费详细信息删除失败！");
		}
		return wrapper;
	}

	// 详情
	@RequestMapping(value = "detail")
	@ResponseBody
	public HashMap<String, Object> detail(HttpServletRequest request, String id) {
		try {
			return super.detail(request, id);
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			return JsonWrapper.failureWrapper("报检收费详细信息查询失败！");
		}
	}
	
}
