package com.scts.task.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.scts.task.bean.ContractTaskInfo;
import com.scts.task.service.ContractTaskInfoService;

/**
 * 合同检验任务单管理控制器
 * 
 * @ClassName ContractTaskInfoAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2018-04-02 下午04:13:00
 */
@Controller
@RequestMapping("contractTaskInfo")
public class ContractTaskInfoAction extends
		SpringSupportAction<ContractTaskInfo, ContractTaskInfoService> {
	@Autowired
	private ContractTaskInfoService contractTaskInfoService;
	@Autowired
	private AttachmentManager attachmentManager;

	/**
	 * 保存
	 * 
	 * @param request
	 * @param functionTaskInfo
	 * @throws Exception
	 * @author GaoYa
	 * @date 2018-04-02 下午04:15:00
	 */
	@RequestMapping(value = "saveBasic")
	@ResponseBody
	public HashMap<String, Object> saveBasic(HttpServletRequest request,
			ContractTaskInfo cunctionTaskInfo) throws Exception {
		try {
			String uploadFiles = request.getParameter("uploadFiles");
			// 保存
			contractTaskInfoService.saveInfo(request, cunctionTaskInfo,uploadFiles); 	
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败，请重试！");
		}
		return JsonWrapper.successWrapper(cunctionTaskInfo);
	}
	
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
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		ContractTaskInfo cunctionTaskInfo = contractTaskInfoService.get(id);
		List<Attachment> list = attachmentManager.getBusAttachment(id);
		wrapper.put("success", true);
		wrapper.put("data", cunctionTaskInfo);
		wrapper.put("attachs", list);
		return wrapper;
	}
	
	// 删除
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(HttpServletRequest request, String ids)
			throws Exception {
		String[] idArr = ids.split(",");
		for (int i = 0; i < idArr.length; i++) {
			contractTaskInfoService.del(request, idArr[i]);
		}
		return JsonWrapper.successWrapper(ids);
	}
	
	
	// 查询检验任务单日志信息
	@RequestMapping(value = "getFlowStep")
	@ResponseBody
	public ModelAndView getFlowStep(HttpServletRequest request) throws Exception {
		Map<String, Object> map = contractTaskInfoService.getFlowStep(request.getParameter("id"));
		ModelAndView mav = new ModelAndView("app/contract_task/flow_card", map);
		return mav;
	}
	
	@RequestMapping(value = "getContractTasks")
	@ResponseBody
	public HashMap<String, Object> getContractTasks(HttpServletRequest request)
			throws Exception {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
//			String json = contractTaskInfoService.getContractTasks();
			String check_type=request.getParameter("check_type");
			String sblx=request.getParameter("sblx");
			String json = contractTaskInfoService.getContractTasks(check_type,sblx);
			map.put("data", json);
			map.put("success", true);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("访问失败！");
		}
	}
	
	@RequestMapping(value = "getCjsb")
	@ResponseBody
	public HashMap<String, Object> getCjsb(HttpServletRequest request){
		try {
			String ids=request.getParameter("ids");
			return JsonWrapper.successWrapper(contractTaskInfoService.getCjsb(ids));
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("参检设备失败！");
		}
		
	}
	/**
	 * 查询报告信息
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getbgxx")
	@ResponseBody
	public HashMap<String, Object> getbgxx(HttpServletRequest request,String id){
		try {
			return JsonWrapper.successWrapper(contractTaskInfoService.getbgxx(id));
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("报告信息查询出错！");
		}
	}
}
