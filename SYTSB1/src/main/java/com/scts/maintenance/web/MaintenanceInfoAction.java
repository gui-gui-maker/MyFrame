package com.scts.maintenance.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.ant.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.utils.BeanUtils;
import com.khnt.utils.FileUtil;
import com.khnt.utils.StringUtil;
import com.lsts.common.service.CodeTablesService;
import com.scts.maintenance.bean.MaintenanceInfo;
import com.scts.maintenance.bean.MaintenanceInfoDTO;
import com.scts.maintenance.service.MaintenanceInfoService;
import com.scts.task.bean.FunctionTaskInfo;
import com.scts.task.service.FunctionTaskInfoService;

/**
 * 系统更新维护日志（系统建设台账）明细管理控制器
 * 
 * @ClassName MaintenanceAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-09-18 下午04:45:00
 */
@Controller
@RequestMapping("maintenance/info")
public class MaintenanceInfoAction extends
		SpringSupportAction<MaintenanceInfo, MaintenanceInfoService> {
	@Autowired
	private MaintenanceInfoService maintenanceInfoService;
	@Autowired
	private AttachmentManager attachmentManager;
	@Autowired
	private CodeTablesService codeTablesService;
	@Autowired
	private FunctionTaskInfoService functionTaskInfoService;
	
	/**
	 * 编辑
	 * @param request
	 * @param maintenanceInfo
	 * @return
	 * @throws Exception
	 */
	 @RequestMapping(value = "maintenanceEdit")
	 @ResponseBody
	 public HashMap<String, Object> maintenanceEdit(HttpServletRequest request,
				MaintenanceInfo maintenanceInfo) throws Exception {

			String uploadFiles = request.getParameter("uploadFiles");
			String type=request.getParameter("type");
			String send_msg_type=request.getParameter("send_msg_type");
			HashMap<String, Object>  map=maintenanceInfoService.maintenanceEdit(request,maintenanceInfo,uploadFiles,type,send_msg_type);
		 return map;
	 }
	 
	
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
			MaintenanceInfo maintenanceInfo) throws Exception {
		String uploadFiles = request.getParameter("uploadFiles");
		try {
			maintenanceInfoService.saveInfo(request, maintenanceInfo, uploadFiles); // 保存台账信息（含附件）
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败，请重试！");
		}
		return JsonWrapper.successWrapper(maintenanceInfo);
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
		MaintenanceInfo maintenanceInfo = maintenanceInfoService.get(id);
		List<Attachment> list = attachmentManager.getBusAttachment(id);
		
		List<Attachment> task_attachs = new ArrayList<Attachment>();
		List<Attachment> update_attachs = new ArrayList<Attachment>();
		for(Attachment attachment : list){
			if("task".equals(attachment.getTz_category())){
				task_attachs.add(attachment);
			}else if("update".equals(attachment.getTz_category())){
				update_attachs.add(attachment);
			}
		}
		
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", true);
		wrapper.put("data", maintenanceInfo);
		wrapper.put("task_attachs", task_attachs);
		wrapper.put("update_attachs", update_attachs);
		return wrapper;
	}
	
	/**
	 * 论证时，获取运维日志来源任务书详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getTaskDetail")
	@ResponseBody
	public HashMap<String, Object> getTaskDetail(HttpServletRequest request, String id)
			throws Exception {
		MaintenanceInfo maintenanceInfo = maintenanceInfoService.get(id);
		String expect_finish_date = "";
		if(StringUtil.isNotEmpty(maintenanceInfo.getFk_func_task_id())){
			FunctionTaskInfo task = functionTaskInfoService.get(maintenanceInfo.getFk_func_task_id());
			if(task != null){
				if(task.getExpect_finish_date()!=null){
					expect_finish_date = DateUtils.format(task.getExpect_finish_date(), "yyyy-MM-dd");
				}
			}
		}
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", true);
		wrapper.put("data", maintenanceInfo);
		wrapper.put("expect_finish_date", expect_finish_date);
		return wrapper;
	}
	
	// 删除
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(HttpServletRequest request, String ids)
			throws Exception {
		String[] idArr = ids.split(",");
		for (int i = 0; i < idArr.length; i++) {
			maintenanceInfoService.del(request, idArr[i]);
		}
		return JsonWrapper.successWrapper(ids);
	}
	
	// 受理需求
    @RequestMapping(value = "receives")
    @ResponseBody
    public HashMap<String, Object> receives(@RequestBody MaintenanceInfoDTO entity,HttpServletRequest request) throws Exception {	
        return maintenanceInfoService.receives(request, entity);
    }
    
    // 标记开发信息
    @RequestMapping(value = "develops")
    @ResponseBody
    public HashMap<String, Object> develops(@RequestBody MaintenanceInfoDTO entity,HttpServletRequest request) throws Exception {	
        return maintenanceInfoService.develops(request,entity);
    }
    
    // 标记论证信息
    @RequestMapping(value = "prove")
    @ResponseBody
    public HashMap<String, Object> prove(@RequestBody MaintenanceInfoDTO entity, HttpServletRequest request) throws Exception {	
    	String send_msg_type = request.getParameter("send_msg_type");	// 发送信息类型（1：微信 2：短信 3：微信和短信）
		if(StringUtil.isEmpty(send_msg_type)){
			send_msg_type = "1";	// 默认发送微信
		}
        return maintenanceInfoService.prove(request, entity);
    }
    
    // 标记完成，并发送提醒信息
    @RequestMapping(value = "finishs")
    @ResponseBody
    public HashMap<String, Object> finishs(@RequestBody MaintenanceInfoDTO entity,HttpServletRequest request) throws Exception {	
    	String send_msg_type = request.getParameter("send_msg_type");	// 发送信息类型（1：微信 2：短信 3：微信和短信）
		if(StringUtil.isEmpty(send_msg_type)){
			send_msg_type = "1";	// 默认发送微信
		}
        return maintenanceInfoService.finishs(request, entity, send_msg_type);
    }
    
    // 未标记开发直接标记完成，并发送提醒信息
    @RequestMapping(value = "finishs2")
    @ResponseBody
    public HashMap<String, Object> finishs2(MaintenanceInfoDTO entity, HttpServletRequest request) throws Exception {	
    	String uploadFiles = request.getParameter("uploadFiles");
    	/*String send_msg_type = request.getParameter("send_msg_type");	// 发送信息类型（1：微信 2：短信 3：微信和短信）
		if(StringUtil.isEmpty(send_msg_type)){
			send_msg_type = "1";	// 默认发送微信
		}*/
        return maintenanceInfoService.finishs2(request, entity, uploadFiles);
    }
    
    // 发布（已发布信息，可公开显示于系统首页）
    @RequestMapping(value = "publishs")
    @ResponseBody
    public HashMap<String, Object> publishs(HttpServletRequest request) throws Exception {	
        return maintenanceInfoService.publishs(request);
    }
    
    // 确认
    @RequestMapping(value = "confirms")
    @ResponseBody
    public HashMap<String, Object> confirms(HttpServletRequest request) throws Exception {	
        return maintenanceInfoService.confirms(request);
    }
    
    // 查询流程步骤信息
	@RequestMapping(value = "getFlowStep")
	@ResponseBody
	public ModelAndView getFlowStep(HttpServletRequest request)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map = maintenanceInfoService
				.getFlowStep(request.getParameter("id"));
		ModelAndView mav = new ModelAndView("app/maintenance/flow_card", map);
		return mav;
	}
	
	// 发送临时信息
    @RequestMapping(value = "sendMsg")
    @ResponseBody
    public HashMap<String, Object> sendMsg(@RequestBody MaintenanceInfoDTO entity,HttpServletRequest request) throws Exception {	
    	String send_msg_type = request.getParameter("send_msg_type");	// 发送信息类型（1：微信 2：短信 3：微信和短信）
		if(StringUtil.isEmpty(send_msg_type)){
			send_msg_type = "1";	// 默认发送微信
		}
        return maintenanceInfoService.sendMsg(request, entity, send_msg_type);
    }
    
    /**
	 * 获取运维日志打印详情（LODOP）
	 * 
	 * @param request
	 * @param info_id --
	 *            运维日志业务ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getPrintData")
	@ResponseBody
	private HashMap<String, Object> getPrintData(HttpServletRequest request, String info_id) throws Exception {
		HashMap<String, Object> return_map = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

		String sn = ""; 		// 业务编号
		String func_name = ""; 	// 功能模块名称
		String info_type = ""; 	// 类型（1：新功能 2：升级功能 3：数据维护 4：数据统计 5：优化功能）
		String org_name = ""; 	// 来源部门
		String advance_user_name = ""; 	// 报告人姓名
		String advance_date = ""; 		// 报告日期
		String receive_user_name = ""; 	// 受理人姓名
		String receive_date = ""; 		// 受理日期
		String pro_desc = ""; 			// 功能说明
		String prove_user_name = ""; 	// 论证人姓名（多个论证人以逗号分隔）
		String prove_type = ""; 		// 论证结论（0：处理 1：延期）
		String prove_remark = ""; 		// 论证备注（延期时填写）
		String develop_user_name = ""; 	// 开发员姓名
		String develop_end_date = ""; 	// 完成开发日期
		String test_user_name = ""; 	// 测试员姓名
		String test_date = ""; 			// 测试日期
		String develop_desc = ""; 		// 完成/更新情况
		String task_attach_id = "";		// 功能说明附件ID
		String update_attach_id = "";	// 更新说明附件ID

		MaintenanceInfo maintenanceInfo = maintenanceInfoService.get(info_id);
		List<Attachment> list = attachmentManager.getBusAttachment(info_id);
		//List<Attachment> task_attachs = new ArrayList<Attachment>();
		//List<Attachment> update_attachs = new ArrayList<Attachment>();
		for (Attachment attachment : list) {
			if ("task".equals(attachment.getTz_category())) {
				task_attach_id = attachment.getId();
				//task_attachs.add(attachment);
			} else if ("update".equals(attachment.getTz_category())) {
				update_attach_id = attachment.getId();
				//update_attachs.add(attachment);
			}
		}

		if (StringUtil.isNotEmpty(maintenanceInfo.getSn())) {
			sn = maintenanceInfo.getSn();
		}
		if (StringUtil.isNotEmpty(maintenanceInfo.getFunc_name())) {
			func_name = maintenanceInfo.getFunc_name();
		}
		if (StringUtil.isNotEmpty(maintenanceInfo.getInfo_type())) {
			info_type = codeTablesService.getValueByCode("MAINTENANCE_TYPE", maintenanceInfo.getInfo_type());
		}
		if (StringUtil.isNotEmpty(maintenanceInfo.getOrg_name())) {
			org_name = maintenanceInfo.getOrg_name();
		}
		if (StringUtil.isNotEmpty(maintenanceInfo.getAdvance_user_name())) {
			advance_user_name = maintenanceInfo.getAdvance_user_name();
		}
		if (maintenanceInfo.getAdvance_date() != null) {
			advance_date = sdf.format(maintenanceInfo.getAdvance_date());
		}
		if (StringUtil.isNotEmpty(maintenanceInfo.getReceive_user_name())) {
			receive_user_name = maintenanceInfo.getReceive_user_name();
		}
		if (maintenanceInfo.getReceive_date() != null) {
			receive_date = sdf.format(maintenanceInfo.getReceive_date());
		}
		if (StringUtil.isNotEmpty(maintenanceInfo.getPro_desc())) {
			pro_desc = maintenanceInfo.getPro_desc();
		}
		if (StringUtil.isNotEmpty(maintenanceInfo.getProve_user_name())) {
			prove_user_name = maintenanceInfo.getProve_user_name();
		}
		if (StringUtil.isNotEmpty(maintenanceInfo.getProve_type())) {
			if ("0".equals(maintenanceInfo.getProve_type())) {
				prove_type = "处理";
			} else {
				prove_type = "延期";
			}
		}
		if (StringUtil.isNotEmpty(maintenanceInfo.getProve_remark())) {
			prove_remark = maintenanceInfo.getProve_remark();
		}
		if (StringUtil.isNotEmpty(maintenanceInfo.getDevelop_user_name())) {
			develop_user_name = maintenanceInfo.getDevelop_user_name();
		}
		if (maintenanceInfo.getDevelop_end_date() != null) {
			develop_end_date = sdf.format(maintenanceInfo.getDevelop_end_date());
		}
		if (StringUtil.isNotEmpty(maintenanceInfo.getTest_user_name())) {
			test_user_name = maintenanceInfo.getTest_user_name();
		}
		if (maintenanceInfo.getTest_date() != null) {
			test_date = sdf.format(maintenanceInfo.getTest_date());
		}
		if (StringUtil.isNotEmpty(maintenanceInfo.getDevelop_desc())) {
			develop_desc = maintenanceInfo.getDevelop_desc();
		}

		return_map.put("sn", sn); 				// 业务编号
		return_map.put("func_name", func_name); // 功能模块名称
		return_map.put("info_type", info_type); // 业务类型
		return_map.put("org_name", org_name); 	// 来源部门
		return_map.put("advance_user_name", advance_user_name); // 报告人
		return_map.put("advance_date", advance_date); 			// 报告日期
		return_map.put("receive_user_name", receive_user_name); // 受理人
		return_map.put("receive_date", receive_date); 			// 受理日期
		return_map.put("pro_desc", pro_desc); 					// 功能说明
		return_map.put("prove_user_name", prove_user_name); 	// 论证人
		return_map.put("prove_type", prove_type); 				// 论证结论
		return_map.put("prove_remark", prove_remark); 			// 论证备注
		return_map.put("develop_user_name", develop_user_name); // 开发人
		return_map.put("develop_end_date", develop_end_date); 	// 完成开发日期
		return_map.put("test_user_name", test_user_name); 		// 测试人
		return_map.put("test_date", test_date); 				// 测试日期
		return_map.put("develop_desc", develop_desc); 			// 完成/更新情况
		return_map.put("task_attach_id", task_attach_id); 		// 功能说明附件ID
		return_map.put("update_attach_id", update_attach_id); 	// 更新说明附件ID
		return_map.put("success", true);
		return return_map;
	}
	
	// 系统运行维护日志打印
	@RequestMapping(value = "doPrint")
	public ModelAndView doPrint(HttpServletRequest request, String info_id) {
		try {
			MaintenanceInfoDTO maintenanceInfoDTO = new MaintenanceInfoDTO();
			MaintenanceInfo maintenanceInfo = maintenanceInfoService.get(info_id);
			BeanUtils.copyProperties(maintenanceInfoDTO, maintenanceInfo);
			if (StringUtil.isNotEmpty(maintenanceInfo.getProve_type())) {
				if ("0".equals(maintenanceInfo.getProve_type())) {
					maintenanceInfoDTO.setProve_type("处理");
				} else {
					maintenanceInfoDTO.setProve_type("延期");
				}
			}
			if (StringUtil.isNotEmpty(maintenanceInfo.getInfo_type())) {
				maintenanceInfoDTO.setInfo_type(
						codeTablesService.getValueByCode("MAINTENANCE_TYPE", maintenanceInfo.getInfo_type()));
			}
			List<Attachment> list = attachmentManager.getBusAttachment(info_id);
			String task_attach_id = "";
			String task_attach_name = "";
			String update_attach_id = "";
			String update_attach_name = "";
			for (Attachment attachment : list) {
				if ("task".equals(attachment.getTz_category())) {
					if (StringUtil.isNotEmpty(task_attach_id)) {
						task_attach_id += "、";
						task_attach_name += "、";
					}
					task_attach_id += attachment.getId();
					task_attach_name += attachment.getFileName();

				} else if ("update".equals(attachment.getTz_category())) {
					if (StringUtil.isNotEmpty(update_attach_id)) {
						update_attach_id += "、";
						update_attach_name += "、";
					}
					update_attach_id += attachment.getId();
					update_attach_name += attachment.getFileName();
				}
			}
			maintenanceInfoDTO.setTask_attach_id(task_attach_id);
			maintenanceInfoDTO.setTask_attach_name(task_attach_name);
			maintenanceInfoDTO.setUpdate_attach_id(update_attach_id);
			maintenanceInfoDTO.setUpdate_attach_name(update_attach_name);
			request.getSession().setAttribute("maintenanceInfoDTO", maintenanceInfoDTO);
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
		}
		ModelAndView mav = new ModelAndView("app/maintenance/maintenance_print2");
		return mav;
	}
	
	// 系统运行维护日志打印(带图片打印)
	@RequestMapping(value = "doPrint2")
	public ModelAndView doPrint2(HttpServletRequest request, String info_id) {
		try {
			MaintenanceInfoDTO maintenanceInfoDTO = new MaintenanceInfoDTO();
			MaintenanceInfo maintenanceInfo = maintenanceInfoService.get(info_id);
			BeanUtils.copyProperties(maintenanceInfoDTO, maintenanceInfo);
			if (StringUtil.isNotEmpty(maintenanceInfo.getProve_type())) {
				if ("0".equals(maintenanceInfo.getProve_type())) {
					maintenanceInfoDTO.setProve_type("处理");
				} else {
					maintenanceInfoDTO.setProve_type("延期");
				}
			}
			if (StringUtil.isNotEmpty(maintenanceInfo.getInfo_type())) {
				maintenanceInfoDTO.setInfo_type(
						codeTablesService.getValueByCode("MAINTENANCE_TYPE", maintenanceInfo.getInfo_type()));
			}
			List<Attachment> list = attachmentManager.getBusAttachment(info_id);
			String task_attach_id = "";
			String task_attach_name = "";
			String update_attach_id = "";
			String update_attach_name = "";
			for (Attachment attachment : list) {
				if ("task".equals(attachment.getTz_category())) {
					if (StringUtil.isNotEmpty(task_attach_id)) {
						task_attach_id += "、";
						task_attach_name += "、";
					}
					task_attach_id += attachment.getId();
					task_attach_name += attachment.getFileName();

				} else if ("update".equals(attachment.getTz_category())) {
					if (StringUtil.isNotEmpty(update_attach_id)) {
						update_attach_id += "、";
						update_attach_name += "、";
					}
					update_attach_id += attachment.getId();
					update_attach_name += attachment.getFileName();
				}
			}
			maintenanceInfoDTO.setTask_attach_id(task_attach_id);
			maintenanceInfoDTO.setTask_attach_name(task_attach_name);
			maintenanceInfoDTO.setUpdate_attach_id(update_attach_id);
			maintenanceInfoDTO.setUpdate_attach_name(update_attach_name);
			maintenanceInfoDTO.setAttachment_list(list);
			request.getSession().setAttribute("maintenanceInfoDTO", maintenanceInfoDTO);
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
		}
		ModelAndView mav = new ModelAndView("app/maintenance/maintenance_print3");
		return mav;
	}
	
	@RequestMapping(value = "getAttachment")
	public void getAttachment(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String filePath = null;
			String id = request.getParameter("id");
			Attachment attachment = attachmentManager.get(id);
			String imagepath = request.getSession().getServletContext().getRealPath("upload");
			filePath = imagepath + File.separator + attachment.getFilePath();
			FileUtil.download(response, filePath, "", "application/octet-stream");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 系统运行维护日志打印（9月5日新增）
	@RequestMapping(value = "doPrint1")
	public ModelAndView doPrint1(HttpServletRequest request, String info_id) {
		try {
			MaintenanceInfoDTO maintenanceInfoDTO = new MaintenanceInfoDTO();
			MaintenanceInfo maintenanceInfo = maintenanceInfoService.get(info_id);
			BeanUtils.copyProperties(maintenanceInfoDTO, maintenanceInfo);
			if (StringUtil.isNotEmpty(maintenanceInfo.getProve_type())) {
				if ("0".equals(maintenanceInfo.getProve_type())) {
					maintenanceInfoDTO.setProve_type("处理");
				} else {
					maintenanceInfoDTO.setProve_type("延期");
				}
			}
			if (StringUtil.isNotEmpty(maintenanceInfo.getInfo_type())) {
				maintenanceInfoDTO.setInfo_type(
						codeTablesService.getValueByCode("MAINTENANCE_TYPE", maintenanceInfo.getInfo_type()));
			}
			List<Attachment> list = attachmentManager.getBusAttachment(info_id);
			String task_attach_id = "";
			String task_attach_name = "";
			String update_attach_id = "";
			String update_attach_name = "";
			for (Attachment attachment : list) {
				if ("task".equals(attachment.getTz_category())) {
					if (StringUtil.isNotEmpty(task_attach_id)) {
						task_attach_id += "、";
						task_attach_name += "、";
					}
					task_attach_id += attachment.getId();
					task_attach_name += attachment.getFileName();

				} else if ("update".equals(attachment.getTz_category())) {
					if (StringUtil.isNotEmpty(update_attach_id)) {
						update_attach_id += "、";
						update_attach_name += "、";
					}
					update_attach_id += attachment.getId();
					update_attach_name += attachment.getFileName();
				}
			}
			maintenanceInfoDTO.setTask_attach_id(task_attach_id);
			maintenanceInfoDTO.setTask_attach_name(task_attach_name);
			maintenanceInfoDTO.setUpdate_attach_id(update_attach_id);
			maintenanceInfoDTO.setUpdate_attach_name(update_attach_name);
			request.getSession().setAttribute("maintenanceInfoDTO", maintenanceInfoDTO);
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
		}
		ModelAndView mav = new ModelAndView("app/maintenance/maintenance_printNew");
		return mav;
	}
}
