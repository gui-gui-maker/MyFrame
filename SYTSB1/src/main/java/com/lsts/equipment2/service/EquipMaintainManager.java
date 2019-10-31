package com.lsts.equipment2.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.equipment2.bean.EquipMaintain;
import com.lsts.equipment2.dao.EquipMaintainDao;
import com.lsts.inspection.bean.FlowInfoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("equipMaintain")
public class EquipMaintainManager extends EntityManageImpl<EquipMaintain,EquipMaintainDao>{
    @Autowired
    EquipMaintainDao equipMaintainDao;
    
    
    @Autowired
    FlowExtManager flowExtManager;
    
    /**状态常量*/
    public final static String DA_JYSQ_WTJ = "0";// 未提交
    public final static String DA_JYSQ_YTJ = "1"; // 已提交
	public final static String DA_JYSQ_SHZ = "2";// 审核中
	public final static String DA_JYSQ_PASS = "3"; // 审核通过
	public final static String DA_JYSQ_NO_PASS = "4"; // 审核不通过


	
    /**	
  	 * 提交
  	 * */
  	
  	public void doStartPress(Map<String, Object> map)throws Exception{
  		flowExtManager.startFlowProcess(map);
      }
  	

    /**
  	 * 审核
  	 * */
  	
  	public void doProcess(Map<String, Object> map)throws Exception{
  		flowExtManager.submitActivity(map);
      }
  	
  	/**
  	 * 退回
  	 * */
  	
  	public void doreturn(Map<String, Object> map)throws Exception{
  		flowExtManager.returnedActivity(map);
      }
  	
  	/**
  	 * 流程结束
  	 * */
  	public void stop(Map<String, Object> map)throws Exception{
  		flowExtManager.finishProcess(map);
      }
  	/**
	 * 通过业务ID查询流程信息
	 *  
	 */
	public  List<FlowInfoDTO> queryMainId(HttpServletRequest request, String id) throws Exception {
		List<FlowInfoDTO> list = equipMaintainDao.queryMainId(request, id);
		return list;
	}
}
