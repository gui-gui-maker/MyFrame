package com.lsts.mobileapp.rcparse.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.service.InspectionInfoService;
import com.lsts.mobileapp.rcparse.bean.R3;
import com.lsts.mobileapp.rcparse.bean.R4;
import com.lsts.mobileapp.rcparse.service.R3Manager;

/** 
 * @author 作者 廖增伟
 * @JDK 1.8
 * @version 创建时间：2018年6月10日 上午22:16:15  
 * 类说明
 */
@Controller
@RequestMapping("r3Action")
public class R3Action extends SpringSupportAction<R3, R3Manager> {
	
	@Autowired
	private R3Manager r3Manager;
	@Autowired
	private InspectionInfoService infoService;
	
	/**
	 * 原始记录转换生成报告
	 * 
	 * @param request
	 * 
	 * @return 
	 * @throws 
	 * @author GaoYa
	 * @since 2018-08-06 17:19:00
	 */
	@ResponseBody
	@RequestMapping("beginParse")
	public HashMap<String, Object> beginParse(HttpServletRequest request) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		try {

			// 获取请求参数
			Map<String, String> reqParamsMap = r3Manager.getReqParams(request);

			// 获取报告业务ID
			String fkInfoId = reqParamsMap.get("INFO_ID");

			// 获取报告业务信息
			InspectionInfo info = infoService.get(fkInfoId);

			// 原始记录转换成报告
			r3Manager.dealRecordToReport(request, info);
			// 更新原始记录转换报告状态
			r3Manager.updateRecordStatus(info);

			// 设置返回参数	
			returnMap.put("success", true);
			returnMap.put("infoId", fkInfoId);
			
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", "原始记录转换报告失败.");
		}
		return returnMap;
	}
	
	/**
	 * 启动流程并提交审核
	 * 
	 * @param request
	 * 
	 * @return 
	 * @throws 
	 * @author GaoYa
	 * @since 2018-08-13 09:05:00
	 */
	@ResponseBody
	@RequestMapping("startFlow")
	public HashMap<String, Object> startFlow(HttpServletRequest request) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		try {
			
			// 获取请求参数
			Map<String, String> reqParamsMap = r3Manager.getReqParams(request);

			// 获取报告业务ID
			String fkInfoId = reqParamsMap.get("INFO_ID");

			// 获取报告业务信息
			InspectionInfo info = infoService.get(fkInfoId);
			
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			info.setRecordConfirmId(((User)user.getSysUser()).getEmployee().getId());
			info.setRecordConfirmOp(((User)user.getSysUser()).getEmployee().getName());
			infoService.save(info);
			// 启动流程
			HashMap<String, Object> startFlowResultMap = r3Manager.startFlow(request, info);

			// 自动提交审核，提交流程
			boolean start_result = Boolean.valueOf(String.valueOf(startFlowResultMap.get("success")));
			// 流程启动成功时，自动提交审核
			if (start_result) {
				HashMap<String, Object> subFlowResultMap = infoService.subFlow(request, reqParamsMap, info);
				boolean sub_result = Boolean.valueOf(String.valueOf(subFlowResultMap.get("success")));
				if (sub_result) {
					info.setRecordFlow("2");
					// 更新原始记录校核状态 有误。。。
					//r3Manager.updateRecordFlow(info);
					// 记录操作日志
					r3Manager.addRecordLog(request, info);
				}
				return subFlowResultMap;
			} else {
				return startFlowResultMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", "提交失败！");
		}
		return returnMap;
	}
	
	// 启用对应关系
    @RequestMapping(value = "enable")
    @ResponseBody
    public HashMap<String, Object> enable(HttpServletRequest request) throws Exception {	
        return r3Manager.enable(request);
    }
    
    // 停用对应关系
    @RequestMapping(value = "disable")
    @ResponseBody
    public HashMap<String, Object> disable(HttpServletRequest request) throws Exception {	
        return r3Manager.disable(request);
    }
    
    // 对比对应关系
    @RequestMapping(value = "compare")
    @ResponseBody
    public HashMap<String, Object> compare(HttpServletRequest request) throws Exception {	
        return r3Manager.compare(request);
    }
    
    // 取消对应关系错误标记
    @RequestMapping(value = "cancelTags")
    @ResponseBody
    public HashMap<String, Object> cancelTags(HttpServletRequest request) throws Exception {	
        return r3Manager.cancelTags(request);
    }
 // 保存对应关系信息
 	@RequestMapping(value = "saveBasic")
 	@ResponseBody
 	public HashMap<String, Object> saveBasic(HttpServletRequest request,@RequestBody R4 r4) throws Exception {
 		HashMap<String, Object> wrapper = new HashMap<String, Object>();
 		try {
 			r3Manager.saveBasic(r4, request);
 			wrapper.put("success", true);
 		} catch (Exception e) {
 			log.debug(e.toString());
 			wrapper.put("success", false);
 			wrapper.put("msg", "保存失败！");
 			e.printStackTrace();
 		}
 		return wrapper;
 	}

 	// 根据报告id获取报告和原始记录对应关系信息
 	@RequestMapping(value = "getRelations")
 	@ResponseBody
 	public HashMap<String, Object> getRelations(HttpServletRequest request) throws Exception {
 		String report_id = request.getParameter("report_id");
 		try {
 			return r3Manager.getRelations(report_id);
 		} catch (Exception e) {
 			log.debug(e.toString());
 			return JsonWrapper.failureWrapperMsg("读取对应关系信息失败！");
 		}
 	}
 	
 	// 删除对应关系信息
 	@RequestMapping(value = "del")
 	@ResponseBody
 	public HashMap<String, Object> del(HttpServletRequest request, String id) throws Exception {
 		r3Manager.del(request, id);
 		return JsonWrapper.successWrapper(id);
 	}
 		
 	// 根据对应关系记录ids获取对应关系记录
 	@RequestMapping(value = "getRelationsByIds")
 	@ResponseBody
 	public HashMap<String, Object> getRelationsByIds(HttpServletRequest request) throws Exception {
 		String ids = request.getParameter("ids");
 		try {
 			return r3Manager.getRelationsByIds(ids);
 		} catch (Exception e) {
 			log.debug(e.toString());
 			return JsonWrapper.failureWrapperMsg("读取对应关系信息失败！");
 		}
 	}

 	// 批量修改对应关系信息
 	@RequestMapping(value = "saveBasic2")
 	@ResponseBody
 	public HashMap<String, Object> saveBasic2(HttpServletRequest request, @RequestBody R4 r4)
 			throws Exception {
 		HashMap<String, Object> wrapper = new HashMap<String, Object>();
 		try {
 			r3Manager.saveBasic2(r4, request);
 			wrapper.put("success", true);
 		} catch (Exception e) {
 			log.debug(e.toString());
 			wrapper.put("success", false);
 			wrapper.put("msg", "保存失败！");
 			e.printStackTrace();
 		}
 		return wrapper;
 	}
 	// 查漏
 	@RequestMapping(value = "checkItems")
 	@ResponseBody
 	public HashMap<String, Object> checkItems(String report_id, String itemNames) throws Exception {
 		HashMap<String, Object> map = new HashMap<String, Object>();
 		try {
 			String return_items = "";
 			String[] items = itemNames.split(",");
 			for (int i = 0; i < items.length; i++) {
 				List<R3> parse = r3Manager.getRelationInfos(report_id, items[i]);
 				if(parse == null) {
 					if(StringUtil.isNotEmpty(return_items)) {
 						return_items += ",";
 					}
 					return_items += items[i];
 				}
 			}
 			map.put("success", true);
 			map.put("return_items", return_items);
 		} catch (Exception e) {
 			log.debug(e.toString());
 			map.put("success", false);
 			map.put("msg", "查漏请求超时，请稍后再试！");
 		}
 		return map;
 	}
}
