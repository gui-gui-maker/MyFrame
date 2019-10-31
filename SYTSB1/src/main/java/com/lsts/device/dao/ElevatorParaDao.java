package com.lsts.device.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.device.bean.ElevatorPara;



/**
 * 特种设备信息管理   dao
 * 
 * @author 肖慈边 2014-1-8
 */

@Repository("elevatorParaDao")
public class ElevatorParaDao extends EntityDaoImpl<ElevatorPara> {

	/**
	 * 根据设备ID查询设备参数信息
	 * @param id
	 * @return 
	 * @author GaoYa
	 * @date 2015-12-22 上午09:18:00
	 */
	@SuppressWarnings("unchecked")
    public List<ElevatorPara> queryParas(String device_id) {
    	List<ElevatorPara> list = new ArrayList<ElevatorPara>();
    	try {
    		if (StringUtil.isNotEmpty(device_id)) {
    			String hql = "from ElevatorPara d where d.fk_tsjc_device_document_id=?";
    			list = this.createQuery(hql, device_id).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
    }

	

}
