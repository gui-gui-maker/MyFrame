package com.khnt.oa.car.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.oa.car.bean.CarrepairNote;
import com.khnt.oa.car.service.CarrepairNoteManager;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.archives.service.ArchivesBorrowManager;
import com.lsts.equipment2.bean.EquipMaintain;
import com.lsts.equipment2.bean.Equipment;
import com.lsts.humanresources.bean.BgLeave;
import com.lsts.humanresources.bean.InspectionDeclare;
import com.lsts.humanresources.service.BgLeaveManager;
import com.lsts.inspection.bean.FlowInfoDTO;
import com.lsts.qualitymanage.bean.TxwjspSealreg;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("CarrepairNoteAction")
public class CarrepairNoteAction extends SpringSupportAction<CarrepairNote, CarrepairNoteManager> {

    @Autowired
    private CarrepairNoteManager  carrepairNoteManager;
    @Override
	public HashMap<String, Object> save(HttpServletRequest request, CarrepairNote carrepairNote) throws Exception {
		CurrentSessionUser curUser = this.getCurrentUser(); //获取当前用户登录信息
		String pageStatus = request.getParameter("pageStatus");//获取状态
		try {
			if (pageStatus.equals("add")) {
				carrepairNote.setCreateDate(new Date());
				carrepairNote.setCreateId(curUser.getId());
				carrepairNote.setCreateBy(curUser.getName());
			}else if(pageStatus.equals("modify")) {
				carrepairNote.setLastModifyDate(new Date());
				carrepairNote.setLastModifyId(curUser.getId());
				carrepairNote.setLastModifyBy(curUser.getName());
			}
			carrepairNoteManager.save(carrepairNote);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存维修单失败，请重试！");
		}
		return JsonWrapper.successWrapper(carrepairNote);
	}
    /**提交流程并改变状态
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "subFolw")
	@ResponseBody
	public HashMap<String, Object> subFolw(ServletRequest request,String id, String flowId,
	   		String typeCode, String status,String activityId) throws Exception {
	    	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	   		Map<String, Object> map = new HashMap<String, Object>();
	   		//流程传参
	   		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
	   		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
	   		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "车辆维修单审核 -"+user.getName());
	   		map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
	   		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
	   		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
	   		if (StringUtil.isEmpty(id)) {
	   			return JsonWrapper.failureWrapper("参数错误！");
	   		} else {
	   			// 启动流程
	   			if (StringUtil.isNotEmpty(flowId)) {
	   				carrepairNoteManager.doStartPress(map);
	   				//改变状态
	   				CarrepairNote carrepairNote=carrepairNoteManager.get(id);
	   				carrepairNote.setStatus(carrepairNoteManager.CARREPAIE_FLOW_BMFZRDSH);
	   				carrepairNoteManager.save(carrepairNote);
	   			} else {
	   				return JsonWrapper.failureWrapper("流程ID为空！");
	   			}

	   			return JsonWrapper.successWrapper(id);
	   		}

	   	}
	/**
  	 * 审核通过
  	 * @param id
	 * @param typeCode
	 * @param activityId
	 * @param areaFlag
	 * @return
	 * @throws Exception
  	 * */
    @RequestMapping(value = "shtg")
 	@ResponseBody
 	public HashMap<String, Object> shtg(ServletRequest request,String areaFlag,String id, String flowId,
			String activityId,String typeCode) throws Exception {
    	CurrentSessionUser user = SecurityUtil.getSecurityUser();
 		Map<String, Object> map = new HashMap<String, Object>();
 		CarrepairNote carrepairNote2=carrepairNoteManager.get(id);
 		String carrepairNote=request.getParameter("carrepairNote").toString();
 		CarrepairNote carrepairNote1=JSON.parseObject(carrepairNote, CarrepairNote.class);
 		//流程传参
 		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
 		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
 		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "车辆维修单审核 -"+carrepairNote2.getSenrepairName());
 		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
 		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
 		if (StringUtil.isEmpty(id)) {
 			return JsonWrapper.failureWrapper("参数错误！");
 		} else {
 			// 启动流程
 			if (StringUtil.isNotEmpty(activityId)) {
 				carrepairNoteManager.doProcess(map);
 				//改变状态
 				if(areaFlag.equals("2")){
 					carrepairNote2.setUseDepartmentManagerOpinion(carrepairNote1.getUseDepartmentManagerOpinion());
 					carrepairNote2.setUseDepartmentManagerId(user.getId());
 					carrepairNote2.setUseDepartmentManager(user.getName());
 					carrepairNote2.setUseDepartmentManagerDate(new Date());
 					carrepairNote2.setStatus(carrepairNoteManager.CARREPAIE_FLOW_CDFZRDSH);
 				}else if(areaFlag.equals("3")){
 					carrepairNote2.setFleetManagerOpinion(carrepairNote1.getUseDepartmentManagerOpinion());
 					carrepairNote2.setFleetManagerId(user.getId());
 					carrepairNote2.setFleetManager(user.getName());
 					carrepairNote2.setFleetManagerDate(new Date());
 					carrepairNote2.setStatus(carrepairNoteManager.CARREPAIE_FLOW_GLBMDSH);
 				}else if(areaFlag.equals("4")){
 					carrepairNote2.setDepartmentManagerOpinion(carrepairNote1.getUseDepartmentManagerOpinion());
 					carrepairNote2.setDepartmentManagerId(user.getId());
 					carrepairNote2.setDepartmentManager(user.getName());
 					carrepairNote2.setDepartmentManagerDate(new Date());
 					carrepairNote2.setStatus(carrepairNoteManager.CARREPAIE_FLOW_SHTG);
 				}
 				carrepairNoteManager.save(carrepairNote2);
 			} else {
 				return JsonWrapper.failureWrapper("流程ID为空！");
 			}
 			return JsonWrapper.successWrapper(id);
 		}
 	}
    /**
  	 * 审核不通过
  	 **/
  	  @RequestMapping(value = "shbtg")
  	  @ResponseBody
  	  public HashMap<String, Object> shbtg(ServletRequest request,String areaFlag,String id, String flowId,
  			String activityId,String typeCode,String processId)throws Exception {
	  		CurrentSessionUser user = SecurityUtil.getSecurityUser();
	  		CarrepairNote carrepairNote2=carrepairNoteManager.get(id);
	 		Map<String, Object> map = new HashMap<String, Object>();
	 		Map<String, Object> map1 = new HashMap<String, Object>();
	 		String carrepairNote=request.getParameter("carrepairNote").toString();
	 		CarrepairNote carrepairNote1=JSON.parseObject(carrepairNote, CarrepairNote.class);
	 		//流程传参
	 		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
	 		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
	 		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "车辆维修单审核 -"+carrepairNote2.getSenrepairName());
	 		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
	 		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
     		
     		map1.put(FlowExtWorktaskParam.PROCESS_ID, processId);
  			map1.put(FlowExtWorktaskParam.FINISH_TYPE,FinishFlowInf.FINISH_TYPE_TERMINATE);
     		if (StringUtil.isEmpty(id)) {
     			return JsonWrapper.failureWrapper("参数错误！");
     		} else {
     			// 结束流程
     			if (StringUtil.isNotEmpty(activityId)) {
     				carrepairNoteManager.stop(map1);
     				//改变状态
     				if(areaFlag.equals("2")){
     					carrepairNote2.setUseDepartmentManagerOpinion(carrepairNote1.getUseDepartmentManagerOpinion());
     					carrepairNote2.setUseDepartmentManagerId(user.getId());
     					carrepairNote2.setUseDepartmentManager(user.getName());
     					carrepairNote2.setUseDepartmentManagerDate(new Date());
     				}else if(areaFlag.equals("3")){
     					carrepairNote2.setFleetManagerOpinion(carrepairNote1.getUseDepartmentManagerOpinion());
     					carrepairNote2.setFleetManagerId(user.getId());
     					carrepairNote2.setFleetManager(user.getName());
     					carrepairNote2.setFleetManagerDate(new Date());
     				}else if(areaFlag.equals("4")){
     					carrepairNote2.setDepartmentManagerOpinion(carrepairNote1.getUseDepartmentManagerOpinion());
     					carrepairNote2.setDepartmentManagerId(user.getId());
     					carrepairNote2.setDepartmentManager(user.getName());
     					carrepairNote2.setDepartmentManagerDate(new Date());
     				}
     				carrepairNote2.setStatus(carrepairNoteManager.CARREPAIE_FLOW_SHBTG);
     				carrepairNoteManager.save(carrepairNote2);
     			} else {
     				return JsonWrapper.failureWrapper("流程ID为空！");
     			}

     			return JsonWrapper.successWrapper(id);
     		}
  	}
  	  
}