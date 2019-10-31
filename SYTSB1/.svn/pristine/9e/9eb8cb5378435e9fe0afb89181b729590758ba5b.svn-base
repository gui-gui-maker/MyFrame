package com.lsts.mobileapp.confirm.dao;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.inspection.bean.InspectionInfo;

@Repository
public class ConfirmDao extends EntityDaoImpl<InspectionInfo>{
	
	
   	/**
   	 * 获取当前流程
   	 * @param info
   	 * @return
   	 * @throws Exception
   	 */
	public Object[] getNextFlowProcess(InspectionInfo info) throws Exception {
   		String sql = "select t.id,v.ACTIVITY_ID,t.flow_note_id"
   				+ " from tzsb_inspection_info t, V_PUB_WORKTASK v "
   				+ " where t.id = v.SERVICE_ID and v.STATUS = '0' and t.data_status <> '99'"
   				+" and t.id= ? and v.ACTIVITY_NAME = ? ";
   		List<Object[]> list = this.createSQLQuery(sql,info.getId(),info.getFlow_note_name()).list();
   		if(list.size()>0) {
   			return list.get(0);
   		}else {
   			throw new Exception("为获取到下一步流程！");
   		}
   	}
	
}
