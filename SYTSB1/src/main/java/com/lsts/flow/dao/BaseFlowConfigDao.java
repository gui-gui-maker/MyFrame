package com.lsts.flow.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.flow.bean.BaseFlowConfig;

/**
 * 流程配置数据处理对象
 * @ClassName BaseFlowConfigDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-03-11 下午02:28:00
 */
@Repository("baseFlowConfigDao")
public class BaseFlowConfigDao extends EntityDaoImpl<BaseFlowConfig>{

	/**
	 * 获取流程配置
	 * @param device_type -- 设备类别
	 * @param check_type -- 检验类别
	 * @return 
	 * @author GaoYa
	 * @date 2014-03-14 下午03:26:00
	 */
	@SuppressWarnings("unchecked")
	public String getFlowConfig(String device_type, String check_type){
		String id = "";
		String hql = "select f.id from base_unit_flow f where f.device_type=? and f.check_type=?";
        List list = this.createSQLQuery(hql, device_type, check_type).list(); 
		if(list.size()>0){
			id = String.valueOf(list.get(0));
		}
		return id;
	}
	
	/**
	 * 获取流程编号
	 * @param device_type -- 设备类别
	 * @param check_type -- 检验类别
	 * @return 
	 * @author GaoYa
	 * @date 2014-03-14 下午03:08:00
	 */
	@SuppressWarnings("unchecked")
	public String getFlowCode(String device_type, String check_type){
		String flow_code = "";
		String hql = "select t.SERVICE_CODE from FLOW_SERVICE_CONFIG t,BASE_UNIT_FLOW t1 where t.id=t1.FK_FLOW_ID and t1.DEVICE_TYPE=? and t1.CHECK_TYPE=?";
		List list = this.createSQLQuery(hql, device_type, check_type).list(); 
		if(list.size()>0){
			flow_code = String.valueOf(list.get(0));
		}
		return flow_code;
	}
	
	/**
	 * 获取流程ID
	 * @param device_type -- 设备类别
	 * @param check_type -- 检验类别
	 * @return 
	 * @author GaoYa
	 * @date 2014-03-14 下午03:08:00
	 */
	@SuppressWarnings("unchecked")
	public String getFlowCY(){
		String flow_id = "";
		String hql = "select t.flow_id from FLOW_SERVICE_CONFIG t where t.service_code='CYGC'";
		List list = this.createSQLQuery(hql).list(); 
		if(list.size()>0){
			flow_id = String.valueOf(list.get(0));
		}
		return flow_id;
	}
	
	/**
	 * 删除流程配置信息
	 * @param ids
	 * @return 
	 * @author GaoYa
	 * @date 2014-03-11 下午02:32:00
	 */
    public void delete(String ids) {
        String arr[] = ids.split(",");
        for (int i = 0; i < arr.length; i++) {
            super.removeById(arr[i]);	//删除流程配置
        }
    }
}
