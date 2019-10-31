package com.lsts.advicenote.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.rbac.bean.Employee;
import com.lsts.advicenote.bean.MessageHistory;
import com.lsts.advicenote.service.MessgeHistoryService;
import com.lsts.employee.bean.EmployeePrinter;

/**
 * 短信、微信发送历史控制器
 * 
 * @ClassName MessageAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2016-01-22 上午11:22:00
 */
@Controller
@RequestMapping("messageHistory")
public class MessageHistoryAction extends
		SpringSupportAction<MessageHistory,MessgeHistoryService> {

	@Autowired
	private MessgeHistoryService messageHistoryService;
	
	/**
	 * 详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getDetail")
	@ResponseBody
	public HashMap<String, Object> getDetail(HttpServletRequest request, String id)
			throws Exception {
		MessageHistory messageHistory = messageHistoryService.get(id);

		HashMap<String, Object> wrapper = new HashMap<String, Object>();;
		wrapper.put("success", true);
		wrapper.put("data", messageHistory);
		return wrapper;
	}
	/**获取统计数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getMessageLog")
	@ResponseBody
	public HashMap<String, Object> getMessageLog(HttpServletRequest request) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String org_id = request.getParameter("org_id");
		String name = request.getParameter("name");
		try {
			wrapper.put("success", true);
			wrapper.put("data", messageHistoryService.getMessageLog(name,org_id,startDate,endDate));
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("获取财务报账数据失败，请重试！");
		}
		return wrapper;
	}
}
