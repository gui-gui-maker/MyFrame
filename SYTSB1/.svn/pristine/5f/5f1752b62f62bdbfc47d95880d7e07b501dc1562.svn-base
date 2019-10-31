package com.lsts.report.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.QueryCondition;
import com.lsts.report.bean.ReportItem;
import com.lsts.report.service.ReportItemService;

/**
 * 报告项目管理 web controller
 * 
 * @author 肖慈边 2014-1-24
 */

@Controller
@RequestMapping("report/item")
public class ReportItemAction extends
		SpringSupportAction<ReportItem, ReportItemService> {
	
	@Autowired
	private ReportItemService reportItemService;

	/**
	 * 查询报告项目信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getItem")
	@ResponseBody
	public HashMap<String, Object> getCert(HttpServletRequest request) {
		try {
			String report_id = request.getParameter("id");
			QueryCondition qc = new QueryCondition(ReportItem.class);
			qc.addCondition("fk_reports_id", "=", report_id);
			qc.setOrderBy("page_index", "asc");
			List<ReportItem>reportCertList = reportItemService.query(qc);
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			wrapper.put("success", true);
			wrapper.put("datalist", reportCertList);
			return wrapper;
		} catch (Exception e) {
			e.printStackTrace();
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			wrapper.put("error", true);
			return wrapper;
		}
	}
}
