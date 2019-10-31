package com.lsts.equipment2.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.equipment2.bean.BuyYijian;
import com.lsts.equipment2.bean.EquipmentBuy;
import com.lsts.equipment2.service.BuyYijianManager;
import com.lsts.equipment2.service.EquipmentBuyService;
import com.lsts.office.bean.MeetUpdateYijina;
import com.lsts.office.bean.MeetingReq;
import com.lsts.office.service.MeetUpdateYijinaManager;


@Controller
@RequestMapping("buyYijianAction")
public class BuyYijianAction extends SpringSupportAction<BuyYijian, BuyYijianManager> {

    @Autowired
    private BuyYijianManager  BuyYijianManager;
    @Autowired
	private EquipmentBuyService equipmentBuyService;
    /**
   	 * 添加审核意见
   	 * @param response
     * @throws Exception 
   	 */
    @RequestMapping(value = "saveShyj")
   	@ResponseBody
   	@SuppressWarnings("unused")
    public HashMap<String, Object> saveShyj(HttpServletRequest request,BuyYijian buyYijian) throws Exception {
    	HashMap<String, Object> map = new HashMap<String, Object>();
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		buyYijian.setAuditManId(user.getId());
		buyYijian.setAuditManName(user.getName());
		buyYijian.setAuditTime(new Date());
		buyYijian.setAuditStep(user.getName()+buyYijian.getAuditResult());
		BuyYijianManager.save(buyYijian);
    	map.put("success", true);
		return map;
   	}
    /**审批流程并改变状态
   	 * @param id
   	 * @param flowId
   	 * @param typeCode
   	 * @param status
   	 * @return
   	 * @throws Exception
   	 */
   	@RequestMapping(value = "zltj")
   	@ResponseBody
   	public HashMap<String, Object> zltj(String areaFlag,String id,
   			String typeCode, String status,String activityId,HttpServletRequest request) throws Exception {
   		Map<String, Object> map = new HashMap<String, Object>();
   		CurrentSessionUser user = SecurityUtil.getSecurityUser();
   		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
   		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
   		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "采购申请"+user.getName());
   		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
   		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK,true);
   		
   		if (StringUtil.isEmpty(id)) {
   			return JsonWrapper.failureWrapper("参数错误！");
   		} else {
   			// 审批流程
   			String opinion=request.getParameter("opinion");
   			//URLEncoder.encode(opinion,"utf-8"); 
   			if (StringUtil.isNotEmpty(activityId)) {
				if(areaFlag.equals("2")){
					BuyYijianManager.doProcess(map);
					//保存业务表数据
					EquipmentBuy equipmentBuy=equipmentBuyService.get(id);
					equipmentBuy.setApplyStatus(BuyYijianManager.BUY_FLOW_SHZ);
					equipmentBuy.setSqbmyj(opinion);
					equipmentBuyService.save(equipmentBuy);
					
				}else if(areaFlag.equals("3")){
					BuyYijianManager.doProcess(map);
					//保存业务表数据
					EquipmentBuy equipmentBuy=equipmentBuyService.get(id);
					equipmentBuy.setApplyStatus(BuyYijianManager.BUY_FLOW_SHZ);
					equipmentBuy.setSqbmyj(opinion);
					equipmentBuyService.save(equipmentBuy);
					
				}
				else if(areaFlag.equals("4")){
					BuyYijianManager.doProcess(map);
					//保存业务表数据
					EquipmentBuy equipmentBuy=equipmentBuyService.get(id);
					equipmentBuy.setApplyStatus(BuyYijianManager.BUY_FLOW_SHTG);
					equipmentBuy.setSqbmyj(opinion);
					equipmentBuyService.save(equipmentBuy);
					/*String employee=meetingReqDao.getTel(meetingReq.getDjPeopleId()).toString();
					String mobileTel = Pattern.compile("\\b([\\w\\W])\\b").matcher(employee.toString()
			    			.substring(1,employee.toString().length()-1)).replaceAll("'$1'"); 
					 String msg = "您好！您的会议室申请("+meetingReq.getName()+")已通过！";
					 messageXinxiService.setSaveMessageXinxi(id, meetingReq.getSqDepartment(), mobileTel, new Date(), msg, "会议室申请", "短信，微信", "实时发送");
					 messageservice.sendMoMsg(request, id, msg, mobileTel);
					 //微信发送
						String msg1 ="您好！您的会议室申请("+meetingReq.getName()+")已通过！";
						messageXinxiService.setSaveMessageXinxi(id, meetingReq.getSqDepartment(), mobileTel, new Date(), msg1, "会议室申请", "短信，微信", "实时发送");
						messageservice.sendWxMsg(request, id, msg1, mobileTel);*/
					 
					
	  	    			
				}else{
					EquipmentBuy equipmentBuy=equipmentBuyService.get(id);
					equipmentBuy.setApplyStatus(BuyYijianManager.BUY_FLOW_SHBTG);
					equipmentBuyService.save(equipmentBuy);
					/*String employee=meetingReqDao.getTel(meetingReq.getDjPeopleId()).toString();
					String mobileTel = Pattern.compile("\\b([\\w\\W])\\b").matcher(employee.toString()
			    			.substring(1,employee.toString().length()-1)).replaceAll("'$1'"); 
					 String msg = "您好！您的会议室申请("+meetingReq.getName()+")失败！";
					 messageXinxiService.setSaveMessageXinxi(id, meetingReq.getSqDepartment(), mobileTel, new Date(), msg, "会议室申请", "短信，微信", "实时发送");
					 //messageXinxiService.setSendMessage(mobileTel, msg);
					 messageservice.sendMoMsg(request, id, msg, mobileTel);
					 //微信发送
						String msg1 = "您好！您的会议室申请("+meetingReq.getName()+")失败！";
						messageXinxiService.setSaveMessageXinxi(id, meetingReq.getSqDepartment(), mobileTel, new Date(), msg1, "会议室申请", "短信，微信", "实时发送");
						 messageservice.sendWxMsg(request, id, msg1, mobileTel);*/
					
					
				}
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}

   			return JsonWrapper.successWrapper(id);
   		}

   	}

   	/**退回审批流程并改变状态
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @param status
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping(value = "xgth")
	@ResponseBody
	public HashMap<String, Object> xgth(MeetUpdateYijina meetUpdateYijina,String areaFlag,String id,
			String typeCode, String status,String activityId,String processId,HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "修改流程");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		map.put(FlowExtWorktaskParam.PROCESS_ID, processId);
		MeetingReq meetingReq=meetingReqManager.get(id);
		meetingReq.setStatus(MeetUpdateYijinaManager.MEET_FLOW_SHBTG);
		
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			// 退回流程
			request.setCharacterEncoding("UTF-8");
   			String opinion=request.getParameter("opinion");
			if (StringUtil.isNotEmpty(activityId)) {
				if(areaFlag.equals("2")){
					meetingReq.setSzrYj(opinion);
					meetingReqManager.save(meetingReq);
					meetUpdateYijinaManager.stop(map);
				}else if(areaFlag.equals("3")){
					meetingReq.setOfficeYj(opinion);
					meetingReqManager.save(meetingReq);
				   meetUpdateYijinaManager.doreturn(map);
				}else{
					meetingReq.setFgyzYj(opinion);
					meetingReqManager.save(meetingReq);
				   meetUpdateYijinaManager.doreturn(map);
				}
				String employee=meetingReqDao.getTel(meetingReq.getDjPeopleId()).toString();
				String mobileTel = Pattern.compile("\\b([\\w\\W])\\b").matcher(employee.toString()
		    			.substring(1,employee.toString().length()-1)).replaceAll("'$1'"); 
				 String msg = "您好！您的会议室申请("+meetingReq.getName()+")失败！";
				 messageXinxiService.setSaveMessageXinxi(id, meetingReq.getSqDepartment(), mobileTel, new Date(), msg, "会议室申请", "短信，微信", "实时发送");
				 messageservice.sendMoMsg(request, id, msg, mobileTel);
				 
				 //微信发送
					String msg1 = "您好！您的会议室申请("+meetingReq.getName()+")失败！";
					messageXinxiService.setSaveMessageXinxi(id, meetingReq.getSqDepartment(), mobileTel, new Date(), msg1, "会议室申请", "短信，微信", "实时发送");
					 messageservice.sendWxMsg(request, id, msg1, mobileTel);
				
				
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}
			return JsonWrapper.successWrapper(id);
		}
    
	}*/
	
	/**撤销审批流程并改变状态
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @param status
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping(value = "ret")
	@ResponseBody
	public HashMap<String, Object> ret(HttpServletRequest request,String id,String cxyy,String typeCode) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		meetUpdateYijinaManager.saveRet(request,id, cxyy, getCurrentUser(),typeCode);
		map.put("success", true);
		return map; 
	}*/
}