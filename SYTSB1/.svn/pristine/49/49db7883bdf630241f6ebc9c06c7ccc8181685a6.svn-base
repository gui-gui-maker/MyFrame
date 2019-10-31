package com.lsts.hall.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.hall.bean.ReportHallInfo;



/**
 * 移动检验业务任务接收明细业务处理对象
 * @ClassName ReportHallInfoDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2016-04-18 下午03:49:00
 */
@Repository("reportHallInfoDao")
public class ReportHallInfoDao extends EntityDaoImpl<ReportHallInfo> {

	@SuppressWarnings("unchecked")
	public List<ReportHallInfo> getInfos(String device_id) {
		List<ReportHallInfo> list = new ArrayList<ReportHallInfo>();
		String hql = "from ReportHallInfo t where t.fk_device_id=? and t.data_status!='99' order by t.op_time desc";
		list = this.createQuery(hql, device_id).list();
		return list;
	}
	
}
