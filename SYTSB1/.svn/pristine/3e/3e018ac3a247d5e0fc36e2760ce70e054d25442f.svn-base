package com.scts.payment.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.utils.StringUtil;
import com.scts.payment.bean.InspectionChange;
import com.scts.payment.bean.InspectionChangeMoneyDTO;
import com.scts.payment.service.InspectionChangeService;

/**
 * 金额修改审批流程主表管理控制器
 * 
 * @ClassName InspectionChangeAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2016-11-16 下午03:45:00
 */
@Controller
@RequestMapping("inspectionChange")
public class InspectionChangeAction extends
		SpringSupportAction<InspectionChange, InspectionChangeService> {
	@Autowired
	private InspectionChangeService inspectionChangeService;
	

	// 批量保存
	@RequestMapping(value = "saveBasic")
	@ResponseBody
	public HashMap<String, Object> saveBasic(HttpServletRequest request,
			@RequestBody
			InspectionChange inspectionChange) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			inspectionChangeService.saveBasic(
					inspectionChange, request);
			wrapper.put("success", true);
			wrapper.put("msg", "保存成功！");
		} catch (Exception e) {
			log.debug(e.toString());
			wrapper.put("success", false);
			wrapper.put("msg", "保存失败！");
			e.printStackTrace();
		}
		return wrapper;
	}
	

	// 获取报告明细
	@RequestMapping(value = "getReportInfos")
	@ResponseBody
	public HashMap<String, Object> getReportInfos(HttpServletRequest request)
			throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String info_ids = request.getParameter("info_ids");
		try {
			InspectionChange inspectionChange = null;
			if(StringUtil.isNotEmpty(id)){
				inspectionChange = inspectionChangeService.get(id);
			}else{
				inspectionChange = new InspectionChange();
			}
			List<InspectionChangeMoneyDTO> infoDTOList = inspectionChangeService.getReportInfos(info_ids);
			inspectionChange.setChangeMoneyDTOList(infoDTOList);
			wrapper.put("success", true);
			wrapper.put("data", inspectionChange);
			return wrapper;
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			return JsonWrapper.failureWrapperMsg("读取金额修改审批流程失败！");
		}
	}
}
