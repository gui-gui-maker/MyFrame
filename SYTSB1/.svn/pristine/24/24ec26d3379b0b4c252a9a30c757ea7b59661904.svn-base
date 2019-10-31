package com.scts.task.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.StringUtil;
import com.lsts.log.service.SysLogService;
import com.scts.task.bean.FunctionTaskInfo;
import com.scts.task.bean.FunctionTaskInfoDTO;
import com.scts.task.service.FunctionTaskInfoService;

/**
 * 系检验和质量管理软件功能开发（修改）任务书管理控制器
 * 
 * @ClassName FunctionTaskInfoAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2016-11-22 下午04:00:00
 */
@Controller
@RequestMapping("functionTaskInfo")
public class FunctionTaskInfoAction extends
		SpringSupportAction<FunctionTaskInfo, FunctionTaskInfoService> {
	@Autowired
	private FunctionTaskInfoService functionTaskInfoService;
	@Autowired
	private AttachmentManager attachmentManager;
	@Autowired
	private SysLogService logService;
	
	/**
	 * 带附件保存
	 * 
	 * @param request
	 * @param employee
	 * @throws Exception
	 * @author GaoYa
	 * @date 2016-01-10 下午04:45:00
	 */
	@RequestMapping(value = "saveBasic")
	@ResponseBody
	public HashMap<String, Object> saveBasic(HttpServletRequest request,
			FunctionTaskInfo functionTaskInfo) throws Exception {
		try {
			String uploadFiles = request.getParameter("uploadFiles");
			String status = request.getParameter("status");
			// 带附件保存
			functionTaskInfoService.saveInfo(request, functionTaskInfo, uploadFiles, status); 	
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败，请重试！");
		}
		return JsonWrapper.successWrapper(functionTaskInfo);
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

		FunctionTaskInfo functionTaskInfo = functionTaskInfoService.get(id);
		List<Attachment> list = attachmentManager.getBusAttachment(id);

		wrapper.put("success", true);
		wrapper.put("data", functionTaskInfo);
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
			functionTaskInfoService.del(request, idArr[i]);
		}
		return JsonWrapper.successWrapper(ids);
	}
	
	// 提交（下达任务）
    @RequestMapping(value = "commits")
    @ResponseBody
    public HashMap<String, Object> commits(HttpServletRequest request) throws Exception {	
        return functionTaskInfoService.commits(request);
    }

	// 签发
    @RequestMapping(value = "issues")
    @ResponseBody
    public HashMap<String, Object> issues(HttpServletRequest request) throws Exception {	
        return functionTaskInfoService.issues(request);
    }
    
    // 接收
    @RequestMapping(value = "receives")
    @ResponseBody
    public HashMap<String, Object> receives(HttpServletRequest request) throws Exception {	
        return functionTaskInfoService.receives(request);
    }
    
    // 退回
    @RequestMapping(value = "backs")
    @ResponseBody
    public HashMap<String, Object> backs(HttpServletRequest request, String dataStr) throws Exception {	
        return functionTaskInfoService.backs(request, dataStr);
    }
    
    // 确认完成
    @RequestMapping(value = "finishs")
    @ResponseBody
    public HashMap<String, Object> finishs(@RequestBody FunctionTaskInfoDTO entity,HttpServletRequest request) throws Exception {	
        return functionTaskInfoService.finishs(request, entity);
    }
    
    /**
	 * 一测情况
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "test1")
	@ResponseBody
	public HashMap<String, Object> test1(@RequestBody FunctionTaskInfoDTO entity, HttpServletRequest request)
			throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
		try {
			String id = request.getParameter("ids");
			String test_result1 = entity.getTest_result1(); // 一测结果
			String test_remark1 = entity.getTest_remark1(); // 结果备注（存在问题或建议时填写）

			String test_result_txt = "";
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				FunctionTaskInfo functionTaskInfo = functionTaskInfoService.get(ids[i]);
				functionTaskInfo.setTest_result1(test_result1);
				functionTaskInfo.setTest_remark1(test_remark1);
				if (StringUtil.isEmpty(entity.getTest_user_name1())) {
					functionTaskInfo.setTest_user_id1(curUser.getId()); 	// 测试人ID
					functionTaskInfo.setTest_user_name1(curUser.getName()); // 测试人姓名
				}else{
					functionTaskInfo.setTest_user_name1(entity.getTest_user_name1()); // 测试人姓名
				}
				functionTaskInfo.setTest_date1(entity.getTest_date1()); // 一测日期
				if ("1".equals(test_result1)) {
					test_result_txt = "满意";
					functionTaskInfo.setData_status("6"); // 6：任务测试完成
				} else {
					test_result_txt = "存在问题或建议";
					functionTaskInfo.setData_status("5"); // 6：任务测试中
				}
				functionTaskInfoService.save(functionTaskInfo);

				// 2、写入日志
				logService.setLogs(ids[i], "一测任务书",
						StringUtil.isEmpty(test_remark1) ? test_result_txt : test_result_txt + "：" + test_remark1,
						curUser.getId(), curUser.getName(), new Date(), request.getRemoteAddr());
			}
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", "一测情况保存失败，请重试！");
		}
		return wrapper;
	}
	
	/**
	 * 二测情况
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "test2")
	@ResponseBody
	public HashMap<String, Object> test2(@RequestBody FunctionTaskInfoDTO entity, HttpServletRequest request)
			throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
		try {
			String id = request.getParameter("ids");
			String test_result2 = entity.getTest_result2(); // 一测结果
			String test_remark2 = entity.getTest_remark2(); // 结果备注（存在问题或建议时填写）

			String test_result_txt = "";
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				FunctionTaskInfo functionTaskInfo = functionTaskInfoService.get(ids[i]);
				functionTaskInfo.setTest_result2(test_result2);
				functionTaskInfo.setTest_remark2(test_remark2);
				if (StringUtil.isEmpty(entity.getTest_user_name2())) {
					functionTaskInfo.setTest_user_id2(curUser.getId()); // 测试人ID
					functionTaskInfo.setTest_user_name2(curUser.getName()); // 测试人姓名
				}else{
					functionTaskInfo.setTest_user_name2(entity.getTest_user_name2()); // 测试人姓名
				}
				functionTaskInfo.setTest_date2(entity.getTest_date2()); // 一测日期
				if ("1".equals(test_result2)) {
					test_result_txt = "满意";
					functionTaskInfo.setData_status("6"); // 6：任务测试完成
				} else {
					test_result_txt = "存在问题或建议";
					functionTaskInfo.setData_status("5"); // 6：任务测试中
				}
				functionTaskInfoService.save(functionTaskInfo);

				// 2、写入日志
				logService.setLogs(ids[i], "二测任务书",
						StringUtil.isEmpty(test_remark2) ? test_result_txt : test_result_txt + "：" + test_remark2,
						curUser.getId(), curUser.getName(), new Date(), request.getRemoteAddr());
			}
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", "二测情况保存失败，请重试！");
		}
		return wrapper;
	}
	
	/**
	 * 三测情况
	 * 
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "test3")
	@ResponseBody
	public HashMap<String, Object> test3(@RequestBody FunctionTaskInfoDTO entity, HttpServletRequest request)
			throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
		try {
			String id = request.getParameter("ids");
			String test_result3 = entity.getTest_result3(); // 一测结果
			String test_remark3 = entity.getTest_remark3(); // 结果备注（存在问题或建议时填写）

			String test_result_txt = "";
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				FunctionTaskInfo functionTaskInfo = functionTaskInfoService.get(ids[i]);
				functionTaskInfo.setTest_result3(test_result3);
				functionTaskInfo.setTest_remark3(test_remark3);
				if (StringUtil.isEmpty(entity.getTest_user_name3())) {
					functionTaskInfo.setTest_user_id3(curUser.getId()); // 测试人ID
					functionTaskInfo.setTest_user_name3(curUser.getName()); // 测试人姓名
				}else{
					functionTaskInfo.setTest_user_name3(entity.getTest_user_name3()); // 测试人姓名
				}
				functionTaskInfo.setTest_date3(entity.getTest_date3()); // 一测日期
				if ("1".equals(test_result3)) {
					test_result_txt = "满意";
					functionTaskInfo.setData_status("6"); // 6：任务测试完成
				} else {
					test_result_txt = "存在问题或建议";
					functionTaskInfo.setData_status("5"); // 6：任务测试中
				}
				functionTaskInfoService.save(functionTaskInfo);

				// 2、写入日志
				logService.setLogs(ids[i], "三测任务书",
						StringUtil.isEmpty(test_remark3) ? test_result_txt : test_result_txt + "：" + test_remark3,
						curUser.getId(), curUser.getName(), new Date(), request.getRemoteAddr());
			}
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", "三测情况保存失败，请重试！");
		}
		return wrapper;
	}
	
	// 查询流程步骤信息
	@RequestMapping(value = "getFlowStep")
	@ResponseBody
	public ModelAndView getFlowStep(HttpServletRequest request) throws Exception {
		Map<String, Object> map = functionTaskInfoService.getFlowStep(request.getParameter("id"));
		ModelAndView mav = new ModelAndView("app/func_task/flow_card", map);
		return mav;
	}
}
