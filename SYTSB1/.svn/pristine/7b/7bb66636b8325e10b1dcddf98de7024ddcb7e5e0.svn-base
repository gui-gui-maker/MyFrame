package com.lsts.gis.device.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rbac.impl.bean.User;
import com.khnt.utils.StringUtil;
import com.lsts.gis.device.bean.InspectionQueryHistory;
import com.lsts.gis.device.dao.InspectionQueryHistoryDao;
@Service
public class InspectionQueryHistoryService extends 
EntityManageImpl<InspectionQueryHistory, InspectionQueryHistoryDao>{

	@Autowired
	InspectionQueryHistoryDao inspectionQueryHistoryDao;

	public InspectionQueryHistory log(User user, String code, String device_qr_code, int size) throws Exception{
		InspectionQueryHistory iqh = new InspectionQueryHistory();
		iqh.setQuery_op(user.getName());
		iqh.setQuery_op_id(user.getId());
		iqh.setQuery_time(new Date());
		
		if(StringUtil.isNotEmpty(code)){
			iqh.setQuery_type("1");//设备注册代码查询
			iqh.setQuery_content(code);
		}else if(StringUtil.isNotEmpty(device_qr_code)){
			iqh.setQuery_type("2");//二维码查询
			iqh.setQuery_content(device_qr_code);
		};
		
		iqh.setQuery_result(size);
		inspectionQueryHistoryDao.save(iqh);
		return iqh;
	}

	public InspectionQueryHistory log(User user, String company, int size) throws Exception{
		InspectionQueryHistory iqh = new InspectionQueryHistory();
		iqh.setQuery_op(user.getName());
		iqh.setQuery_op_id(user.getId());
		iqh.setQuery_time(new Date());
		
		if(StringUtil.isNotEmpty(company)){
			iqh.setQuery_type("3");//使用单位查询
			iqh.setQuery_content(company);
		}
		iqh.setQuery_result(size);
		inspectionQueryHistoryDao.save(iqh);
		return iqh;
	}

	@SuppressWarnings("unchecked")
	public List<InspectionQueryHistory> getHistories(int size) throws Exception{
		Query query = inspectionQueryHistoryDao.createQuery("from InspectionQueryHistory order by query_time desc");
		query.setMaxResults(size);
		
		return query.list();
	}

	public InspectionQueryHistory log(String code, String device_qr_code, int size) {
		InspectionQueryHistory iqh = new InspectionQueryHistory();
		iqh.setQuery_op("匿名");
		iqh.setQuery_op_id("nologin");
		iqh.setQuery_time(new Date());
		
		if(StringUtil.isNotEmpty(code)){
			iqh.setQuery_type("1");//设备注册代码查询
			iqh.setQuery_content(code);
		}else if(StringUtil.isNotEmpty(device_qr_code)){
			iqh.setQuery_type("2");//二维码查询
			iqh.setQuery_content(device_qr_code);
		};
		
		iqh.setQuery_result(size);
		inspectionQueryHistoryDao.save(iqh);
		return iqh;
	}

	public List<Object[]> hotSearch(Integer numb) {
		if(numb==null||numb <= 0){
			numb =25;
		}
		/*String sql = "select * from ( select t.*, row_number() over(ORDER BY t.cnt desc) my_index "
				+ "from (select query_content, count(query_content) cnt from TZSB_INSPECTION_QUERY where query_type=3 "
				+ "group by query_content ) t ) where my_index <= "+numb;*/
		String sql = "select * "
					+ "  from (select t.*, row_number() over(ORDER BY t.cnt desc) my_index "
					+ "          from (select querycontent, count(querycontent) cnt "
					+ "                  from (select querycontent "
					+ "                          from QUERYMESSAGEINFO "
					+ "                         where regexp_like(querycontent, '[^[:digit:]]') "
					+ "                         union all "
					+ "                        select query_content as querycontent "
					+ "                          from TZSB_INSPECTION_QUERY "
					+ "                         where query_type = 3) "
					+ "                 group by querycontent) t) "
					+ " where my_index < "+numb;
		return inspectionQueryHistoryDao.createSQLQuery(sql).list();
	}
	
	
}
