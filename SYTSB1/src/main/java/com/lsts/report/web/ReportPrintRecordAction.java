package com.lsts.report.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.lsts.report.bean.ReportPrintRecord;
import com.lsts.report.bean.ReportPrintRecordDTO;
import com.lsts.report.service.ReportPrintRecordService;

/**
 * 检验资料报送打印签收明细控制器
 * 
 * @ClassName ReportPrintRecordAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-09-06 下午03:20:00
 */
@Controller
@RequestMapping("report/print/record")
public class ReportPrintRecordAction extends
		SpringSupportAction<ReportPrintRecord, ReportPrintRecordService> {

	@Autowired
	private ReportPrintRecordService reportPrintRecordService;

	// 删除报检业务明细数据
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(HttpServletRequest request, String ids) throws Exception {
		reportPrintRecordService.del(request, ids);
		return JsonWrapper.successWrapper(ids);
	}
	
	// 获取制造监检明细列表
	@RequestMapping(value = "getInfos")
	@ResponseBody
	public HashMap<String, Object> getInfos(HttpServletRequest request)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		try {
			map.put("list", reportPrintRecordService.queryInfos(ids));
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("读取制造监督检验明细信息失败！");
		}
		return map;
	}
	
	// 批量签收报送打印
	@RequestMapping(value = "receives")
	@ResponseBody
	public HashMap<String, Object> receives(HttpServletRequest request, String ids, String report_print_id)
			throws Exception {
		HashMap<String, Object> wrapper = JsonWrapper.successWrapper();
		boolean isSuccess = reportPrintRecordService.receives(ids, report_print_id, request);
		wrapper.put("success", isSuccess);
		return wrapper;
	}

	// 签收报送打印信息
	@RequestMapping(value = "receive")
	@ResponseBody
	public HashMap<String, Object> receive(HttpServletRequest request, @RequestBody ReportPrintRecordDTO entity,
			String ids, String report_print_id) throws Exception {
		return reportPrintRecordService.receive(request, ids, report_print_id, entity);
	}
	
	// 批量签收报送打印信息
	@RequestMapping(value = "batch_receive")
	@ResponseBody
	public HashMap<String, Object> batch_receive(HttpServletRequest request, @RequestBody ReportPrintRecordDTO entity,
			String ids) throws Exception {
		return reportPrintRecordService.batch_receive(request, ids, entity);
	}
}
