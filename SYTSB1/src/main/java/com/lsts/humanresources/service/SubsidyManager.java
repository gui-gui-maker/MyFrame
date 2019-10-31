package com.lsts.humanresources.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.humanresources.bean.Subsidy;
import com.lsts.humanresources.dao.SubsidyDao;


@Service("subsidyManager")
public class SubsidyManager extends EntityManageImpl<Subsidy,SubsidyDao>{
    @Autowired
    SubsidyDao subsidyDao;
    @Autowired
    FlowExtManager flowExtManager;
    /**状态常量*/
    public final static String OVERTIME_FLOW_WTJ = "WJY"; //未提交
    public final static String OVERTIME_FLOW_BMFZR = "BMFZRDSH"; //部门负责人待审核
    public final static String OVERTIME_FLOW_RLZY = "RLZYDSH"; //人力资源待审核
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
  	//根据加班申请ID获取加班补助通知
    public Subsidy getDetail(String fkOvertimeId){
    	List<Subsidy> list = subsidyDao.getDetail(fkOvertimeId);
    	if(list!=null&&list.size()>0){
    		return list.get(0);
    	}else{
    		return new Subsidy();
    	}
    }
}
