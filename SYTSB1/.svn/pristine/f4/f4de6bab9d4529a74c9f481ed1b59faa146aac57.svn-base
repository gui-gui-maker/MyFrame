package com.lsts.humanresources.service;

import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.humanresources.bean.Overtime;
import com.lsts.humanresources.dao.OvertimeDao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("overtimeManager")
public class OvertimeManager extends EntityManageImpl<Overtime,OvertimeDao>{
    @Autowired
    OvertimeDao overtimeDao;
    @Autowired
    FlowExtManager flowExtManager;
    /**状态常量*/
    public final static String OVERTIME_FLOW_WTJ = "WJY"; //未提交
    public final static String OVERTIME_FLOW_BMFZR = "BMFZRDSH"; //部门负责人待审核
    public final static String OVERTIME_FLOW_FGLD="FGLDDSH"; //分管院领导待审核
    public final static String OVERTIME_FLOW_SHTG="SHTG"; //审核通过
	public final static String OVERTIME_FLOW_SHBTG ="SHBTG"; //审核不通过
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
}
