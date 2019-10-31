package com.lsts.humanresources.service;

import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.humanresources.bean.InspectionDeclare;
import com.lsts.humanresources.dao.InspectionDeclareDao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("inspectionDeclareManager")
public class InspectionDeclareManager extends EntityManageImpl<InspectionDeclare,InspectionDeclareDao>{
    @Autowired
    InspectionDeclareDao inspectionDeclareDao;
    @Autowired
    FlowExtManager flowExtManager;
    /**状态常量*/
    public final static String DECLARE_FLOW_WTJ = "WJY"; //未提交
    public final static String DECLARE_FLOW_YTJ = "YTJ"; //已提交
    public final static String DECLARE_FLOW_SHZ="SHZ"; //审批中
    public final static String DECLARE_FLOW_SHTG="SHTG"; //审批通过
	public final static String DECLARE_FLOW_SHBTG ="SHBTG"; //审批不通过
	
	/**
     * 启动流程
     * @param map
     * @throws Exception
     */
  	
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
  	 * 流程结束
  	 * */
  	public void stop(Map<String, Object> map)throws Exception{
  		flowExtManager.finishProcess(map);
    }
  	/**
  	 * 退回流程
  	 * @param map
  	 * @throws Exception
  	 */
  	public void returnedActivity(Map<String, Object> map)throws Exception{
  		flowExtManager.returnedActivity(map);
    }
}
