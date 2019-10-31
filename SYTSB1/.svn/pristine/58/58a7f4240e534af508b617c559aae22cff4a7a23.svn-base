package com.khnt.oa.car.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.oa.car.bean.CarrepairNote;
import com.khnt.oa.car.dao.CarrepairNoteDao;


@Service("CarrepairNoteManager")
public class CarrepairNoteManager extends EntityManageImpl<CarrepairNote,CarrepairNoteDao>{
    @Autowired
    CarrepairNoteDao carrepairNoteDao;
    @Autowired
    FlowExtManager flowExtManager;
    /**状态常量*/
    public final static String CARREPAIE_FLOW_WTJ = "WJY"; //未提交
    public final static String CARREPAIE_FLOW_BMFZRDSH = "BMFZRDSH"; //部门负责人待审核
    public final static String CARREPAIE_FLOW_CDFZRDSH="CDFZRDSH"; //车队负责人待审核
    public final static String CARREPAIE_FLOW_GLBMDSH="GLBMDSH"; //管理部门待审核
    public final static String CARREPAIE_FLOW_SHTG ="SHTG"; //审核通过
	public final static String CARREPAIE_FLOW_SHBTG ="SHBTG"; //审核不通过
	
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
