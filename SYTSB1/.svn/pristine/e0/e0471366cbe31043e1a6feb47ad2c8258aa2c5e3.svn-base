package com.lsts.equipment2.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.advicenote.service.MessageService;
import com.lsts.common.service.MessageXinxiService;
import com.lsts.employee.dao.EmployeesDao;
import com.lsts.equipment2.bean.BuyYijian;
import com.lsts.equipment2.dao.BuyYijianDao;
import com.lsts.office.dao.MeetUpdateYijinaDao;
import com.lsts.office.dao.MeetingReqDao;


@Service("buyYijianManager")
public class BuyYijianManager extends EntityManageImpl<BuyYijian,BuyYijianDao>{
    @Autowired
    BuyYijianDao BuyYijianDao;
    @Autowired
    private FlowExtManager flowExtManager;
    /**状态常量*/
    public final static String BUY_FLOW_WTJ = "1"; //未提交
    public final static String BUY_FLOW_DSH = "2"; //待审核
    public final static String BUY_FLOW_SHZ="3"; //审核中
    public final static String BUY_FLOW_SHTG="4"; //审核通过
	public final static String BUY_FLOW_SHBTG ="5"; //审核不通过
	public final static String BUY_FLOW_YQHT ="6"; //已签合同
	public final static String BUY_FLOW_BUY ="7"; //已采购
	public final static String BUY_FLOW_YH ="8"; //已验货
	
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
}
