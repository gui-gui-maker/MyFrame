package com.lsts.report.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.lsts.report.bean.ReportTransferRecord;
import com.lsts.report.service.ReportTransferRecordService;

/**
 * 业务服务部前后台报告交接明细控制器
 * 
 * @ClassName ReportTransferRecordAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2016-06-03 下午02:15:00
 */
@Controller
@RequestMapping("report/transfer/record")
public class ReportTransferRecordAction extends
		SpringSupportAction<ReportTransferRecord, ReportTransferRecordService> {

	@Autowired
	private ReportTransferRecordService reportTransferRecordService;

	// 删除业务服务部前后台报告交接明细数据
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(HttpServletRequest request, String ids) throws Exception {
		reportTransferRecordService.del(request, ids);
		return JsonWrapper.successWrapper(ids);
	}
	
	// 获取业务服务部前后台报告交接明细信息列表
	@RequestMapping(value = "getInfos")
	@ResponseBody
	public HashMap<String, Object> getInfos(HttpServletRequest request)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		try {
			map.put("list", reportTransferRecordService.queryInfos(ids));
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("读取业务服务部前后台报告交接明细信息失败！");
		}
		return map;
	}
	
}
