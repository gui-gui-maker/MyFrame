package com.lsts.report.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.lsts.report.bean.ReportErrorInfo;
import com.lsts.report.service.ReportErrorInfoService;

/**
 * 不符合报告明细表管理控制器
 * 
 * @ClassName ReportErrorInfoAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-11-04 下午04:45:00
 */
@Controller
@RequestMapping("report/error/info")
public class ReportErrorInfoAction extends
		SpringSupportAction<ReportErrorInfo, ReportErrorInfoService> {
	@Autowired
	private ReportErrorInfoService reportErrorInfoService;
	
	/**
	 * 详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getDetail")
	@ResponseBody
	public HashMap<String, Object> getDetail(HttpServletRequest request, String id)
			throws Exception {
		ReportErrorInfo reportErrorInfo = reportErrorInfoService.get(id);
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", true);
		wrapper.put("data", reportErrorInfo);
		return wrapper;
	}
	
	// 删除
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(HttpServletRequest request, String ids)
			throws Exception {
		String[] idArr = ids.split(",");
		for (int i = 0; i < idArr.length; i++) {
			reportErrorInfoService.del(request, idArr[i]);
		}
		return JsonWrapper.successWrapper(ids);
	}
	
}
