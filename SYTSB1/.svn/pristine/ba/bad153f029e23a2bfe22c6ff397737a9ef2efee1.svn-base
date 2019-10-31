package com.lsts.equipment2.web;

import com.alibaba.fastjson.JSON;
import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.archives.bean.ArchivesBorrow;
import com.lsts.archives.service.ArchivesBorrowManager;
import com.lsts.equipment2.bean.Equipment;
import com.lsts.equipment2.bean.EquipmentBuy;
import com.lsts.equipment2.bean.EquipMaintain;
import com.lsts.equipment2.service.EquipmentManager;
import com.lsts.equipment2.service.EquipMaintainManager;
import com.lsts.equipment2.service.EquipmentBuyService;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.inspection.bean.FlowInfoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("equipMaintainAction")
public class EquipMaintainAction extends SpringSupportAction<EquipMaintain, EquipMaintainManager> {

    @Autowired
    private EquipMaintainManager  equipMaintainManager;
    @Autowired
    private EquipmentManager equipment2Service;
    /**
   	 * 提交审核
   	 * 
   	 * */
       @RequestMapping(value = "subFolws")
      	@ResponseBody
      	public HashMap<String, Object> subFolws(ServletRequest request,String id, String flowId,
      			String typeCode, String status,String activityId) throws Exception {
      		Map<String, Object> map = new HashMap<String, Object>();
      		//流程传参
      		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
      		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
      		if(status.equals("0")){
      			map.put(FlowExtWorktaskParam.SERVICE_TITLE, "设备维修申请");
      		}else if(status.equals("1")){
      			map.put(FlowExtWorktaskParam.SERVICE_TITLE, "设备验收申请");
      		}
      		map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
      		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
      		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
      		
      		
      		if (StringUtil.isEmpty(id)) {
      			return JsonWrapper.failureWrapper("参数错误！");
      		} else {
      			// 启动流程
      			if (StringUtil.isNotEmpty(flowId)) {
      				equipMaintainManager.doStartPress(map);
      				//改变状态
      				EquipMaintain equipMaintain=equipMaintainManager.get(id);
      				equipMaintain.setFlowStatus(ArchivesBorrowManager.DA_JYSQ_YTJ);
      				Equipment equipment2=equipment2Service.get(equipMaintain.getFkDeviceId());
      				if(status.equals("0")){
      					equipMaintain.setProcessSteps("3");
      				equipment2.setEq_status("05");
      				equipment2.setEq_use_status("02");
      	      		}else if(status.equals("1")){
      	      		  equipMaintain.setProcessSteps("2");
      	      		equipment2.setEq_status("06");
      				equipment2.setEq_use_status("02");
      	      		}
      				equipMaintainManager.save(equipMaintain);
      				equipment2Service.save(equipment2);
      			} else {
      				return JsonWrapper.failureWrapper("流程ID为空！");
      			}

      			return JsonWrapper.successWrapper(id);
      		}

      	}
       /**
      	 * 审核通过
      	 * 
      	 * */
          @RequestMapping(value = "subPass")
         	@ResponseBody
         	public HashMap<String, Object> subPass(ServletRequest request,String id, String areaFlag,
         			String typeCode, String status,String activityId) throws Exception {
         		Map<String, Object> map = new HashMap<String, Object>();
         		String maintain=request.getParameter("maintain").toString();
         		EquipMaintain entity=JSON.parseObject(maintain, EquipMaintain.class);
         		//流程传参
         		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
         		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
         		if(status.equals("0")){
          			map.put(FlowExtWorktaskParam.SERVICE_TITLE, "设备维修申请");
          		}else if(status.equals("1")){
          			map.put(FlowExtWorktaskParam.SERVICE_TITLE, "设备验收申请");
          		}
         		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
         		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
         		if (StringUtil.isEmpty(id)) {
         			return JsonWrapper.failureWrapper("参数错误！");
         		} else {
         			// 启动流程
         			if (StringUtil.isNotEmpty(activityId)) {
         				//改变状态
         				equipMaintainManager.doProcess(map);
         				entity.setFlowStatus(ArchivesBorrowManager.DA_JYSQ_SHZ);
         				Equipment equipment2=equipment2Service.get(entity.getFkDeviceId());
         				if(status.equals("0")){
         					if(areaFlag.equals("2")){
             					entity.setProcessSteps("4");
             				}else if(areaFlag.equals("3")){
             					entity.setProcessSteps("5");
             				}else if(areaFlag.equals("4")){
             					entity.setProcessSteps("6");
             					equipment2.setEq_status("01");
             					equipment2.setEq_use_status("03");
             					entity.setFlowStatus(ArchivesBorrowManager.DA_JYSQ_PASS);
             				}
          	      		}else if(status.equals("1")){
          	      		     entity.setProcessSteps("6");
          	      			equipment2.setEq_status("01");
          	      		    equipment2.setEq_use_status("03");
          	      		     entity.setFlowStatus(ArchivesBorrowManager.DA_JYSQ_PASS);
          	      		}
         				
         				equipMaintainManager.save(entity);
         				equipment2Service.save(equipment2);
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
      			String activityId,String typeCode,String status,String processId)throws Exception {
      		    Map<String, Object> map = new HashMap<String, Object>();
      			Map<String, Object> map1 = new HashMap<String, Object>();
      			String maintain=request.getParameter("maintain").toString();
         		EquipMaintain entity=JSON.parseObject(maintain, EquipMaintain.class);
         		//流程传参
         		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
         		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
         		if(status.equals("0")){
          			map.put(FlowExtWorktaskParam.SERVICE_TITLE, "设备维修申请");
          		}else if(status.equals("1")){
          			map.put(FlowExtWorktaskParam.SERVICE_TITLE, "设备验收申请");
          		}
         		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
         		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
         		
         		map1.put(FlowExtWorktaskParam.PROCESS_ID, processId);
      			map1.put(FlowExtWorktaskParam.FINISH_TYPE,FinishFlowInf.FINISH_TYPE_TERMINATE);
         		if (StringUtil.isEmpty(id)) {
         			return JsonWrapper.failureWrapper("参数错误！");
         		} else {
         			// 结束流程
         			if (StringUtil.isNotEmpty(activityId)) {
         				//改变状态
         				equipMaintainManager.stop(map1);
         				entity.setFlowStatus(ArchivesBorrowManager.DA_JYSQ_NO_PASS);
         				if(status.equals("0")){
         					if(areaFlag.equals("2")){
             					entity.setProcessSteps("4");
             				}else if(areaFlag.equals("3")){
             					entity.setProcessSteps("5");
             				}else if(areaFlag.equals("4")){
             					entity.setProcessSteps("6");
             				}
          	      		}else if(status.equals("1")){
          	      		     entity.setProcessSteps("6");
          	      		}
         				equipMaintainManager.save(entity);
         			} else {
         				return JsonWrapper.failureWrapper("流程ID为空！");
         			}

         			return JsonWrapper.successWrapper(id);
         		}
      	}
      	/**
  		 * 通过业务ID查询流程信息
  		 * 
  		 * @param request
  		 * @param id
  		 * @return
  		 */
  		@RequestMapping(value = "queryMainId")
  		@ResponseBody
  		public HashMap<String, Object> queryMainId(HttpServletRequest request, String id) throws Exception {
  			HashMap<String, Object> wrapper = new HashMap<String, Object>();
  			List<FlowInfoDTO> list = equipMaintainManager.queryMainId(request, id);
  			if(list!=null&&list.size()>0){
  				wrapper.put("success", true);
  	  			wrapper.put("list", list);
  			}else{
  				wrapper.put("success", false);
  			}
  			return wrapper;

  		}
}