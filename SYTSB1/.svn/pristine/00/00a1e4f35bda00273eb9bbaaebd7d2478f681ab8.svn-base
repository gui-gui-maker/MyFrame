package com.lsts.report.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.lsts.report.bean.ReportErrorRecordInfo;
import com.lsts.report.service.ReportErrorRecordInfoService;

/**
 * 检验报告/证书不符合纠正流转明细表管理控制器
 * 
 * @ClassName ReportErrorRecordInfoAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-09-18 下午04:45:00
 */
@Controller
@RequestMapping("report/error/record/info")
public class ReportErrorRecordInfoAction extends
		SpringSupportAction<ReportErrorRecordInfo, ReportErrorRecordInfoService> {
	@Autowired
	private ReportErrorRecordInfoService reportErrorRecordInfoService;
	
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
		ReportErrorRecordInfo reportErrorRecordInfo = reportErrorRecordInfoService.get(id);
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", true);
		wrapper.put("data", reportErrorRecordInfo);
		return wrapper;
	}
	
	// 删除
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(HttpServletRequest request, String ids)
			throws Exception {
		String[] idArr = ids.split(",");
		for (int i = 0; i < idArr.length; i++) {
			reportErrorRecordInfoService.del(request, idArr[i]);
		}
		return JsonWrapper.successWrapper(ids);
	}
	
}
