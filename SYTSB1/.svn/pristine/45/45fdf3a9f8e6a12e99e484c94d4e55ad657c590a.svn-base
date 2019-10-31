package com.lsts.org.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.org.bean.EnterInfo;
import com.lsts.statistics.bean.DeviceCountDTO;

@Repository("enterSearchDao")
public class EnterSearchDao extends EntityDaoImpl<EnterInfo> {
	
	/**
	 * 查询使用单位信息
	 * author pingZhou
	 * @param com_name
	 * @return
	 */
	public List<Object> getUseComByName(String com_name) {
		String sql = "  select t.id,t.com_name,count(d.id) device_count"+
						 " from BASE_COMPANY_INFO t, base_device_document d"+
						" where t.com_status <> '99'"+
						 "  and t.com_name like '%"+com_name+"%'"+
						  " and d.fk_company_info_use_id = t.id and d.device_status<> '99'"+
						" group by t.id,t.com_name ";
		List<Object> list = this.createSQLQuery(sql).list();
		
		return list;
	}
	
	/**
	 * 查询维保单位信息
	 * author pingZhou
	 * @param com_name
	 * @return
	 */
	public List<Object> getMaintainComByName(String com_name) {
		String sql = "  select t.id,t.com_name,count(d.id) device_count"+
						 " from BASE_COMPANY_INFO t, base_device_document d"+
						" where t.com_status <> '99'"+
						 "  and t.com_name like '%"+com_name+"%'"+
						  " and d.FK_MAINTAIN_UNIT_ID = t.id and d.device_status<> '99'"+
						" group by t.id,t.com_name ";
		List<Object> list = this.createSQLQuery(sql).list();
		
		return list;
	}


	/**
	 *  查询 所有的检验相关数据
	 * 
	 * @param startDate
	 *            统计开始日期
	 * @param endDate
	 *            统计结束日期
	 * @return
	 * @author pingZhou
	 * @date 2017-01-12 下午14:00:00
	 */
	public List<DeviceCountDTO> inspectCountByDate() {
		List<DeviceCountDTO> list = new ArrayList<DeviceCountDTO>();
		try {
			String sql = "select t.op_action,t.counts from REPORT_LOG_ALL_S t";
			
			List<Object> list1= this.createSQLQuery(sql).list();
			DeviceCountDTO deviceCount = new DeviceCountDTO();
			for (int i = 0; i < list1.size(); i++) {
				Object [] obj = (Object[]) list1.get(i);
				String op_action = obj[0].toString();
				int counts = Integer.parseInt(obj[1].toString());
				if("报告录入".equals(op_action)){
					deviceCount.setLr_count(counts);
				}else if("报告审核".equals(op_action)){
					deviceCount.setSh_count(counts);
				}else if("报告签发".equals(op_action)){
					deviceCount.setQf_count(counts);
				}else if("打印报告".equals(op_action)){
					deviceCount.setDy_count(counts);
				}else if("报告领取".equals(op_action)){
					deviceCount.setLq_count(counts);
				}else if("报告归档".equals(op_action)){
					deviceCount.setGd_count(counts);
				}else if("打印合格证".equals(op_action)){
					deviceCount.setDyhgz_count(counts);
				}
			}
			list.add(deviceCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getCodeName(String code,String table_code){
		String name="";
		String sql = "select tv.name from pub_code_table pt,pub_code_table_values tv where pt.id = tv.code_table_id"
				+ " and tv.value=? and pt.code=?";
		
		List<Object> list = this.createSQLQuery(sql,code,table_code).list();
		if(list.size()>0&&list.get(0)!=null){
			name = list.get(0).toString();
		}
		
		return name;
	}
	
	
}
