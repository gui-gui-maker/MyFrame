package com.lsts.mobileapp.input.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.bean.ReportItemRecord;
@Repository("recordDao")
public class RecordDao extends EntityDaoImpl<ReportItemRecord>{
	/**
	 * 查询流程启动信息
	 * @param info
	 * @return
	 * @throws Exception
	 */
   	public String getProcess(InspectionInfo info) throws Exception{
   		
   		String sql = "select t.ID from FLOW_PROCESS t where t.business_id= ? ";
   		@SuppressWarnings("unchecked")
		List<Object> list = this.createSQLQuery(sql,info.getId()).list();
   		if(list.size()>0){
   			return list.get(0).toString();
   		}
   		return null;
   	}
	
	/**
	 * 查询配置的流程
	 * @param info
	 * @param isMobile
	 * @return
	 * @throws Exception
	 */
	public String getFlowId(InspectionInfo info) throws Exception{
		String sql="select flow_id from BASE_UNIT_FLOW t1 ,flow_service_config t2 where t1.fk_flow_id=t2.id and t1.check_type = ? and t1.fk_report_id= ? ";
		@SuppressWarnings("unchecked")
		List<Object> list = this.createSQLQuery(sql,info.getCheck_category_code(),info.getReport_type()).list();
		if(list != null){
			return list.get(0).toString();
		}
		return null;
	}

}
