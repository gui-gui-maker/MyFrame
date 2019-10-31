package com.lsts.equipment2.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.equipment2.bean.EquipmentApplyList;
import com.lsts.equipment2.bean.EquipmentBuy;
import com.lsts.equipment2.dao.EquipmentBuyDao;
import com.lsts.equipment2.service.EquipmentApplyListManager;
import com.lsts.equipment2.service.EquipmentBuyService;

/**
 * 设备购买申请控制器
 * 
 * @ClassName EquipmentBuyAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-02-18 下午03:24:00
 */
@Controller
@RequestMapping("equipmentBuyAction")
public class EquipmentBuyAction extends
		SpringSupportAction<EquipmentBuy, EquipmentBuyService> {
	@Autowired
	private EquipmentBuyService baseEquipmentApplyService;
	@Autowired
	private EquipmentBuyDao equipmentBuyDao;
	@Autowired
	EquipmentApplyListManager equipmentApplyListManager;
	@Autowired
	private FlowExtManager flowExtManager;
	/**
	 * 设备购买申请保存
	 * 
	 * @param request
	 * @param EquipmentBuy
	 * @throws Exception
	 */
	@RequestMapping(value = "applySave")
	@ResponseBody
	public HashMap<String, Object> applySave(HttpServletRequest request,@RequestBody EquipmentBuy baseEquipmentApply) throws Exception {
		String status = request.getParameter("pageStatus");
		try {
			CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
				baseEquipmentApply.setCreateBy(curUser.getName());
				baseEquipmentApply.setCreateDate(new Date());
				baseEquipmentApply.setApplyStatus("WTJ"); // 申请状态，码表：TJY2_EQ_APPLY_STATUS（WTJ：未提交）
			baseEquipmentApply.setLastModifyBy(curUser.getName());
			baseEquipmentApply.setLastModifyDate(new Date());
			//将申请基本信息保存到申请表里面
			baseEquipmentApplyService.save(baseEquipmentApply);
			//获取申请记录id
			String applyListId=baseEquipmentApply.getId();
			//取出设备到list中
			List<EquipmentApplyList> equipmentApplyLists = baseEquipmentApply.getEquipmentApplyList();
			if(equipmentApplyLists!=null && equipmentApplyLists.size()>0) {
				if (applyListId != null) {
					for (int i = 0; i < equipmentApplyLists.size(); i++) {
						//保存申请表里面的设备信息
						EquipmentApplyList equipmentApplyList = equipmentApplyLists.get(i);
						equipmentApplyList.setApplyListId(applyListId);
						equipmentApplyList.setCreateBy(curUser.getName());
						equipmentApplyList.setCreateDate(new Date());
						equipmentApplyListManager.save(equipmentApplyList);
					}
				} else {
					return JsonWrapper.failureWrapperMsg("保存有误！");
				}
			} else {
				return JsonWrapper.failureWrapperMsg("没有设备信息！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存设备申请失败，请重试！");
		}
		return JsonWrapper.successWrapper(baseEquipmentApply);
	}
	/**
	 * 修改申请内容以及申请设备
	 */
	@RequestMapping(value = "applyUpdate")
	@ResponseBody
	public HashMap<String, Object> applyUpdate(HttpServletRequest request,@RequestBody EquipmentBuy baseEquipmentApply) throws Exception {
		String status = request.getParameter("pageStatus");
		try {
			//将修改信息保存到申请表里面
			CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
			EquipmentBuy baseEquipment2Apply = baseEquipmentApplyService.queryBaseEquipment2Apply(baseEquipmentApply.getId());
			baseEquipment2Apply.setLastModifyBy(curUser.getName());
			baseEquipment2Apply.setLastModifyDate(new Date());
			baseEquipment2Apply.setApplyId(baseEquipmentApply.getApplyId());
			baseEquipment2Apply.setApplyName(baseEquipmentApply.getApplyName());
			baseEquipment2Apply.setApplyUnitId(baseEquipmentApply.getApplyUnitId());
			baseEquipment2Apply.setApplyUnitName(baseEquipmentApply.getApplyUnitName());
			baseEquipment2Apply.setApplyDate(baseEquipmentApply.getApplyDate());
			baseEquipment2Apply.setApplyStatus("WTJ");
			baseEquipment2Apply.setSqbmyj(null);
			baseEquipment2Apply.setZnbmyj(null);
			baseEquipmentApplyService.save(baseEquipment2Apply);
			
			//获取申请记录id
			String applyListId=baseEquipmentApply.getId();
			//从页面取出设备到list中
			List<EquipmentApplyList> equipmentApplyLists = baseEquipmentApply.getEquipmentApplyList();
			//若页面list不为空，保存修改的信息
			if(equipmentApplyLists!=null && equipmentApplyLists.size()>0){
				//根据记录ID删除数据库中相应的设备
				/*List<EquipmentApplyList> equipmentApplyLists1 = equipmentApplyListManager.getEquipmentApplyList(applyListId);*/
				equipmentApplyListManager.delete(applyListId);
				//将页面设备保存到数据库中
				for(int k=0;k<equipmentApplyLists.size();k++){
					EquipmentApplyList equipmentApplyList = equipmentApplyLists.get(k);
					if(equipmentApplyList.getId() == null){
						equipmentApplyList.setApplyListId(applyListId);
						equipmentApplyList.setCreateBy(curUser.getName());
						equipmentApplyList.setCreateDate(new Date());
						equipmentApplyListManager.save(equipmentApplyList);
					}else{
						equipmentApplyList.setId("");
						equipmentApplyList.setCreateBy(curUser.getName());
						equipmentApplyList.setCreateDate(new Date());
						equipmentApplyListManager.save(equipmentApplyList);
					}
				}
			}else {
				return JsonWrapper.failureWrapperMsg("没有设备信息！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败，请重试！");
		}
		return JsonWrapper.successWrapper(baseEquipmentApply);
	}
	
	/**
	 * 详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "detail1")
	@ResponseBody
	public HashMap<String, Object> detail1(HttpServletRequest request, String id)
			throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		List<EquipmentApplyList> equipmentApplyLists = new ArrayList<EquipmentApplyList>();
		EquipmentBuy baseEquipment2Apply = new EquipmentBuy();
		//获取申请信息
		baseEquipment2Apply = baseEquipmentApplyService.queryBaseEquipment2Apply(id);
		try {
			//获取申请设备信息
			equipmentApplyLists = equipmentApplyListManager.getEquipmentApplyList(id);
			wrapper.put("success", true);
			wrapper.put("equipmentApplyLists", equipmentApplyLists);
			wrapper.put("baseEquipment2Apply", baseEquipment2Apply);
			
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("error", true);
		}
		return wrapper;
	}

	/**提交流程并改变状态
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "subFolw")
	@ResponseBody
	public HashMap<String, Object> subFolw(ServletRequest request, String id, String flowId,
		String typeCode, String status, String activityId) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		Map<String, Object> map = new HashMap<String, Object>();
		//流程传参
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE,"设备购买申请-"+user.getName());
		map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			if (StringUtil.isNotEmpty(flowId)) {
				//改变状态
				EquipmentBuy baseEquipment2Apply =  baseEquipmentApplyService.get(id);
				baseEquipment2Apply.setApplyStatus(EquipmentBuyService.BUY_FLOW_DSH);
				baseEquipmentApplyService.save(baseEquipment2Apply);
				// 启动流程
				baseEquipmentApplyService.doStartPress(map);
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}
			return JsonWrapper.successWrapper(id);
		}
		/*//查询当前申请的activityId
		String a=equipmentBuyDao.getFlowId(id).toString();
		String activityId1 = Pattern.compile("\\b([\\w\\W])\\b").matcher(a.toString()
    			.substring(1,a.toString().length()-1)).replaceAll("'$1'"); 
		if(StringUtil.isNotEmpty(activityId1)){
			//流程传参
			map.put(FlowExtWorktaskParam.SERVICE_ID, id);
			map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
			map.put(FlowExtWorktaskParam.SERVICE_TITLE,"设备购买申请-"+user.getName());
			map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
			map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
			map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
			
			if (StringUtil.isEmpty(id)) {
				return JsonWrapper.failureWrapper("参数错误！");
			} else {
				if (StringUtil.isNotEmpty(flowId)) {
					//改变状态
					EquipmentBuy baseEquipment2Apply =  baseEquipmentApplyService.get(id);
					baseEquipment2Apply.setApplyStatus(EquipmentBuyService.BUY_FLOW_DSH);
					baseEquipmentApplyService.save(baseEquipment2Apply);
					// 退回流程
					baseEquipmentApplyService.doreturn(map);
				} else {
					return JsonWrapper.failureWrapper("流程ID为空！");
				}
				return JsonWrapper.successWrapper(id);
			}
		}else{
			//流程传参
			map.put(FlowExtWorktaskParam.SERVICE_ID, id);
			map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
			map.put(FlowExtWorktaskParam.SERVICE_TITLE,"设备购买申请-"+user.getName());
			map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
			map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
			map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
			
			if (StringUtil.isEmpty(id)) {
				return JsonWrapper.failureWrapper("参数错误！");
			} else {
				if (StringUtil.isNotEmpty(flowId)) {
					//改变状态
					EquipmentBuy baseEquipment2Apply =  baseEquipmentApplyService.get(id);
					baseEquipment2Apply.setApplyStatus(EquipmentBuyService.BUY_FLOW_DSH);
					baseEquipmentApplyService.save(baseEquipment2Apply);
					// 启动流程
					baseEquipmentApplyService.doStartPress(map);
				} else {
					return JsonWrapper.failureWrapper("流程ID为空！");
				}
				return JsonWrapper.successWrapper(id);
			}
		}*/
	}
	/**
  	 * 审核通过
  	 * 
  	 * */
    @RequestMapping(value = "subPass")
 	@ResponseBody
 	public HashMap<String, Object> subPass(ServletRequest request,String areaFlag,String id, String flowId,
			String activityId,String typeCode) throws Exception {
 		Map<String, Object> map = new HashMap<String, Object>();
 		String equipmentBuyApply=request.getParameter("equipmentBuyApply").toString();
 		EquipmentBuy equipmentBuyApply1=JSON.parseObject(equipmentBuyApply, EquipmentBuy.class);
 		EquipmentBuy equipmentBuy = baseEquipmentApplyService.get(id);
 		//流程传参
 		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
 		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
 		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "设备购买申请");
 		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
 		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
 		
 		CurrentSessionUser user = SecurityUtil.getSecurityUser();
 		if (StringUtil.isEmpty(id)) {
 			return JsonWrapper.failureWrapper("参数错误！");
 		} else {
 			// 启动流程
 			if (StringUtil.isNotEmpty(activityId)) {
 				//改变状态并保存审核意见
 				if(areaFlag.equals("2")){
 					equipmentBuy.setJhlzId(user.getUserId());
 					equipmentBuy.setJhlzName(user.getUserName());
 					equipmentBuy.setJhlzDate(new Date());
 					equipmentBuy.setApplyStatus(EquipmentBuyService.BUY_FLOW_SHZ);
 				}else if(areaFlag.equals("3")){
 					equipmentBuy.setSqbmshId(user.getUserId());
 					equipmentBuy.setSqbmshName(user.getUserName());
 					equipmentBuy.setSqbmshDate(equipmentBuyApply1.getSqbmshDate());
 					equipmentBuy.setSqbmyj(equipmentBuyApply1.getSqbmyj());
 					equipmentBuy.setApplyStatus(EquipmentBuyService.BUY_FLOW_SHZ);
 				}else if(areaFlag.equals("4")){
 					equipmentBuy.setZnbmshId(user.getUserId());
 					equipmentBuy.setZnbmshName(user.getUserName());
 					equipmentBuy.setZnbmshDate(equipmentBuyApply1.getZnbmshDate());
 					equipmentBuy.setZnbmyj(equipmentBuyApply1.getZnbmyj());
 					equipmentBuy.setApplyStatus(EquipmentBuyService.BUY_FLOW_SHTG);
 				}
 				baseEquipmentApplyService.save(equipmentBuy);
 				baseEquipmentApplyService.doProcess(map);
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
		    Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> map1 = new HashMap<String, Object>();
			String equipmentBuyApply=request.getParameter("equipmentBuyApply").toString();
	 		EquipmentBuy equipmentBuyApply1=JSON.parseObject(equipmentBuyApply, EquipmentBuy.class);
	    	EquipmentBuy equipmentBuy = baseEquipmentApplyService.get(id);
	    	
	    	//流程传参
	  		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
	  		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
	  		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "设备购买申请");
	  		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
	  		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
	  		
	  		map1.put(FlowExtWorktaskParam.PROCESS_ID, processId);
			map1.put(FlowExtWorktaskParam.FINISH_TYPE,FinishFlowInf.FINISH_TYPE_TERMINATE);
			
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
	  		if (StringUtil.isEmpty(id)) {
	  			return JsonWrapper.failureWrapper("参数错误！");
	  		} else {
	  			// 结束流程
	  			if (StringUtil.isNotEmpty(activityId)) {
	  			//改变状态并保存审核意见
	 				if(areaFlag.equals("2")){
	 					equipmentBuy.setJhlzId(user.getUserId());
	 					equipmentBuy.setJhlzName(user.getUserName());
	 					equipmentBuy.setJhlzDate(new Date());
	 					equipmentBuy.setApplyStatus(EquipmentBuyService.BUY_FLOW_SHBTG);
	 				}else if(areaFlag.equals("3")){
	 					equipmentBuy.setSqbmshId(user.getUserId());
	 					equipmentBuy.setSqbmshName(user.getUserName());
	 					equipmentBuy.setSqbmshDate(equipmentBuyApply1.getSqbmshDate());
	 					equipmentBuy.setSqbmyj(equipmentBuyApply1.getSqbmyj());
	 					equipmentBuy.setApplyStatus(EquipmentBuyService.BUY_FLOW_SHBTG);
	 				}else if(areaFlag.equals("4")){
	 					equipmentBuy.setZnbmshId(user.getUserId());
	 					equipmentBuy.setZnbmshName(user.getUserName());
	 					equipmentBuy.setZnbmshDate(equipmentBuyApply1.getZnbmshDate());
	 					equipmentBuy.setZnbmyj(equipmentBuyApply1.getZnbmyj());
	 					equipmentBuy.setApplyStatus(EquipmentBuyService.BUY_FLOW_SHBTG);
	 				}
	 				baseEquipmentApplyService.save(equipmentBuy);
	 				baseEquipmentApplyService.stop(map1);
	  			} else {
	  				return JsonWrapper.failureWrapper("流程ID为空！");
	  			}
	  			return JsonWrapper.successWrapper(id);
	  		}
	
	}
	//删除申请及申请相关的设备
	@RequestMapping(value = "delete")
	@ResponseBody
	public HashMap<String, Object> delete(String ids) throws Exception {
		String arr[] = ids.split(",");
		if(arr.length > 0){
			for(int i = 0;i<arr.length;i++){
				baseEquipmentApplyService.delete(arr[i]);
				equipmentApplyListManager.delete(arr[i]);
			}
		}
		return JsonWrapper.successWrapper(ids);
	}
	/**采购确认
	 * @param id
	 * @return
	 * @throws Exception
	 **/
	@RequestMapping(value = "confirmed")
	@ResponseBody
	public HashMap<String, Object> confirmed(String id) throws Exception {
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			EquipmentBuy equipmentBuy = baseEquipmentApplyService.get(id);
			equipmentBuy.setApplyStatus(EquipmentBuyService.BUY_FLOW_YCG);//改变状态为已采购
			equipmentBuy.setLastModifyBy(user.getName());
			equipmentBuy.setLastModifyDate(new Date());
			baseEquipmentApplyService.save(equipmentBuy);
			return JsonWrapper.successWrapper(id);
		}
	}
	/**验货确认
	 * @param id
	 * @return
	 * @throws Exception
	 **/
	@RequestMapping(value = "examine")
	@ResponseBody
	public HashMap<String, Object> examine(String id) throws Exception {
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			EquipmentBuy equipmentBuy = baseEquipmentApplyService.get(id);
			equipmentBuy.setApplyStatus(EquipmentBuyService.BUY_FLOW_YYH);//改变状态为已验货
			equipmentBuy.setLastModifyBy(user.getName());
			equipmentBuy.setLastModifyDate(new Date());
			baseEquipmentApplyService.save(equipmentBuy);
			return JsonWrapper.successWrapper(id);
		}
	}
	/**入库确认
	 * @param id
	 * @return
	 * @throws Exception
	 **/
	@RequestMapping(value = "instockOk")
	@ResponseBody
	public HashMap<String, Object> instockOk(String id) throws Exception {
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			EquipmentBuy equipmentBuy = baseEquipmentApplyService.get(id);
			equipmentBuy.setApplyStatus(EquipmentBuyService.BUY_FLOW_YWC);//改变状态为已完成
			equipmentBuy.setLastModifyBy(user.getName());
			equipmentBuy.setLastModifyDate(new Date());
			baseEquipmentApplyService.save(equipmentBuy);
			return JsonWrapper.successWrapper(id);
		}
	}
}
