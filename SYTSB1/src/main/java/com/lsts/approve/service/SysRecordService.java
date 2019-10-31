package com.lsts.approve.service;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.utils.StringUtil;
import com.lsts.approve.bean.SysRecord;
import com.lsts.approve.bean.SysRecordSearch;
import com.lsts.approve.dao.SysRecordDao;

@Service("sysRecordService")
public class SysRecordService  extends EntityManageImpl<SysRecord,SysRecordDao>{
	@Autowired
	private SysRecordDao sysRecordDao;

	@SuppressWarnings("unchecked")
	public HashMap<String, Object> getRecords(int page, int pageSize) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		Query query = sysRecordDao.createQuery("from SysRecord order by op_time desc");
		query.setFirstResult((page-1)*pageSize);
		query.setMaxResults(page*pageSize);
		List<SysRecord> list = query.list();
		map.put("Rows", list);
		Object obj = sysRecordDao.createQuery("select count(*) from SysRecord").uniqueResult();
		map.put("Total", Integer.parseInt(obj.toString()));
		return map;
	}

	public HashMap<String, Object> searchRecords(SysRecordSearch filter) throws ParseException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String account = filter.getAccount();
		String action = filter.getAction();
		String bean_name = filter.getBean_name();
		String op_time1 = filter.getOp_time1();
		String op_time2 = filter.getOp_time2();
		DateFormat ds = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		Criteria c = sysRecordDao.getSessionFactory().getCurrentSession().createCriteria(SysRecord.class);
		if(!StringUtil.isEmpty(account)){
			c.add(Restrictions.ilike("account", "%"+account+"%")); 
		}
		if(!StringUtil.isEmpty(action)){
			c.add(Restrictions.eq("action", action)); 
		}
		if(!StringUtil.isEmpty(bean_name)){
			c.add(Restrictions.eq("bean_name", bean_name)); 
		}
		if(!StringUtil.isEmpty(op_time1)){
			Date start = ds.parse(op_time1);
			c.add(Restrictions.ge("op_time", start)); 
		}
		if(!StringUtil.isEmpty(op_time2)){
			Date end = ds.parse(op_time2);
			c.add(Restrictions.le("op_time", end)); 
		}
		c.addOrder(Order.desc("op_time"));
		List<SysRecord> list = c.list();
		map.put("success", true);
		map.put("data", list);
		return map;
	}
	
}
