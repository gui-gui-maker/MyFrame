package com.lsts.equipment2.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.equipment2.bean.Equipment;
import com.lsts.equipment2.bean.EquipmentBox;
import com.lsts.equipment2.bean.EquipmentCentre;
import com.lsts.equipment2.bean.EquipmentLoan;
import com.lsts.equipment2.service.EquipmentBoxManager;
import com.lsts.equipment2.service.EquipmentCentreManager;
import com.lsts.equipment2.service.EquipmentLoanManager;
import com.lsts.equipment2.service.EquipmentManager;


@Controller
@RequestMapping("equipment/Loan")
public class EquipmentLoanAction extends SpringSupportAction<EquipmentLoan, EquipmentLoanManager> {

    @Autowired
    private EquipmentLoanManager  equipmentLoanManager;
    
    @Autowired
	private FlowExtManager flowExtManager;
    
    @Autowired
    private EquipmentCentreManager equipmentCentreManager;
    
    @Autowired
    private EquipmentManager baseEquipment2Service;
    
    @Autowired
    private EquipmentBoxManager equipmentBoxManager;
    
    
    /**
     * 保存信息
     * @param equipmentBox
     * @param request
     * @return
     */
    @RequestMapping(value="saveEqui")
    @ResponseBody
    public HashMap<String, Object> saveEqui(@RequestBody 
    		EquipmentLoan equipmentLoan,HttpServletRequest request,String boxIds){
    	CurrentSessionUser user = SecurityUtil.getSecurityUser();
    	equipmentLoanManager.saveEqui(equipmentLoan,request,user, boxIds);
    	return JsonWrapper.successWrapper();
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
	public HashMap<String, Object> subFolw(String id, String flowId,
			String activityId,String typeCode, String status) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE,"设备借用/领用申请");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		
		EquipmentLoan equipmentLoan = equipmentLoanManager.get(id);
		equipmentLoan.setStatus(EquipmentLoanManager.EQ_SHSQ_YTJ);
		equipmentLoan.setAuditStatus(EquipmentLoanManager.EQ_SHSQ_YTJ);
		equipmentLoanManager.save(equipmentLoan);

		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			// 启动流程
			if (StringUtil.isNotEmpty(flowId)) {
				equipmentLoanManager.doStartPress(map);

			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}

			return JsonWrapper.successWrapper(id);
		}

	}
    
	
	/**
	 * 审批流程并改变状态
	 * @param id
	 * @param flowId
	 * @param activityId
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "equiLc")
	@ResponseBody
	public HashMap<String, Object> equiLc(String areaFlag,String id, String flowId,
			String activityId,String typeCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE,"设备借用/领用申请");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);

		EquipmentLoan equipmentLoan = equipmentLoanManager.get(id);
		equipmentLoan.setAuditStatus(EquipmentLoanManager.EQ_SHSQ_TG);
		equipmentLoanManager.save(equipmentLoan);
		
		if(StringUtil.isEmpty(id)){
			return JsonWrapper.failureWrapper("参数错误！");
		}else{
			// 审批流程
			if (StringUtil.isNotEmpty(activityId)) {
				
					EquipmentLoan equipmentLoan2=equipmentLoanManager.get(id);
					equipmentLoan.setAuditStatus(EquipmentLoanManager.EQ_SHSQ_TG);
					equipmentLoanManager.save(equipmentLoan2);
					flowExtManager.submitActivity(map);
				
				
			} else {
					return JsonWrapper.failureWrapper("流程ID为空！");
				}

				return JsonWrapper.successWrapper(id);
			}

		}
    
	/**审批流程不通过并改变状态
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @param status
	 * @return
	 * @throws Exception
	 * 已提交，审核中，审核通过，审核不通过
	 */
	@RequestMapping(value = "equibtg")
	@ResponseBody
	public HashMap<String, Object> equibtg(String areaFlag,String id,
			String typeCode, String status,String activityId,String processId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "设备借用/领用申请");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		
		map1.put(FlowExtWorktaskParam.PROCESS_ID, processId);
		map1.put(FlowExtWorktaskParam.FINISH_TYPE,FinishFlowInf.FINISH_TYPE_TERMINATE);

		EquipmentLoan equipmentLoan = equipmentLoanManager.get(id);
		equipmentLoan.setAuditStatus(EquipmentLoanManager.EQ_SHSQ_BTG);
		equipmentLoanManager.save(equipmentLoan);
		
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			// 退回流程
			if (StringUtil.isNotEmpty(activityId)) {
				equipmentLoanManager.stop(map1);
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}
			return JsonWrapper.successWrapper(id);
		}

	}
	
	
	/**
	 * 详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "detail")
	@ResponseBody
	public HashMap<String, Object> detail(HttpServletRequest request, String id) throws Exception {
		return super.detail(request, id);
	}
	
	/**
	 * 申请详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "detail1")
	@ResponseBody
	public HashMap<String, Object> detail1(HttpServletRequest request, String id) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		//根据ID获取申请记录
		EquipmentLoan equipmentLoan = equipmentLoanManager.get(id);
		//根据申请记录ID获取中间表相关信息
		List<EquipmentCentre> equipmentCentres= new ArrayList<EquipmentCentre>();//中间表list
		List<Equipment> baseEquipment2s = new ArrayList<Equipment>();//设备list
		List<EquipmentBox> equipmentBoxs = new ArrayList<EquipmentBox>();//设备箱list
		
		equipmentCentres= equipmentCentreManager.queryEquipmentCentre(id);
		if(!"".equals(equipmentCentres)&&equipmentCentres!=null){
			for(int i = 0;i<equipmentCentres.size();i++){
				EquipmentCentre equipmentCentre = equipmentCentres.get(i);
				
				String eq_id = equipmentCentre.getFk_equipment_id();
				String box_id = equipmentCentre.getFk_box_id();
				if(eq_id!=null){
					Equipment baseEquipment2 = baseEquipment2Service.get(eq_id);
					baseEquipment2s.add(baseEquipment2);
				}else if(box_id!=null){
					EquipmentBox equipmentBox = equipmentBoxManager.get(box_id);
					equipmentBoxs.add(equipmentBox);
				}
			}
		}
		wrapper.put("success", true);
		wrapper.put("equipmentLoan", equipmentLoan);
		wrapper.put("baseEquipment2s", baseEquipment2s);
		wrapper.put("equipmentBoxs", equipmentBoxs);
		return wrapper;
	}
	/**
	 * 删除
	 */
	@Override
	public HashMap<String, Object> delete(String ids) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(ids!=null || !"".equals(ids)){
			EquipmentLoan equipmentLoan = equipmentLoanManager.get(ids);
			if(equipmentLoan.getAuditStatus()=="3"||equipmentLoan.equals("3")){
				map.put("success", false);
				map.put("msg", "此条数据已提交不可删除！");
			}else{
				map.put("success", true);
				map.put("msg", "数据删除成功！");
				equipmentLoanManager.delete(ids);
			}
		}
		return map;
	}
}