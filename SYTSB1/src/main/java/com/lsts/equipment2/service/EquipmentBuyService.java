package com.lsts.equipment2.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.StringUtil;
import com.lsts.equipment2.bean.Equipment;
import com.lsts.equipment2.bean.EquipmentBuy;
import com.lsts.equipment2.bean.EquipmentBuyRelation;
import com.lsts.equipment2.dao.EquipmentBuyDao;
import com.lsts.equipment2.dao.EquipmentDao;

/**
 * 设备申请业务逻辑对象
 * 
 * @ClassName EquipmentBuyService
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-02-18 下午03:22:00
 */
@Service("equipmentBuyService")
public class EquipmentBuyService extends
		EntityManageImpl<EquipmentBuy, EquipmentBuyDao> {

	@Autowired
	private EquipmentBuyDao baseEquipmentApplyDao;
	@Autowired
	private EquipmentDao baseEquipmentDao;
	@Autowired
    FlowExtManager flowExtManager;
	/**状态常量*/
    public final static String BUY_FLOW_WTJ = "WTJ"; //未提交
    public final static String BUY_FLOW_DSH = "DSH"; //待审核
    public final static String BUY_FLOW_SHZ="SHZ"; //审核中
    public final static String BUY_FLOW_SHTG="SHTG"; //审核通过
	public final static String BUY_FLOW_SHBTG ="SHBTG"; //审核不通过
	public final static String BUY_FLOW_YQHT ="YQHT"; //已签合同
	public final static String BUY_FLOW_YCG ="YCG"; //已采购
	public final static String BUY_FLOW_YYH ="YYH"; //已验货
	public final static String BUY_FLOW_YWC ="YWC"; //已完成


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
  	 * 删除申请
  	 */
  	public void delete(String id) {
  		baseEquipmentApplyDao.delete(id);
    }
  	/**
  	 * 根据申请ID查找申请信息
  	 */
  	public EquipmentBuy queryBaseEquipment2Apply(String id){
    	return baseEquipmentApplyDao.queryBaseEquipment2Apply(id);
    }
}
