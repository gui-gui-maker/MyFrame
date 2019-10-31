package com.lsts.device.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.DateUtil;
import com.lsts.device.bean.DeviceCategories;
import com.lsts.device.bean.TzsbApplication;

/**
 * 
 * @author liming 
 *
 */
@Repository("tzsbAppcationDao")
public class TzsbApplicationDao extends EntityDaoImpl<TzsbApplication>{

	public String getApplicationCode(){
		String num = DateUtil.getDateTime("yyyyMM", new Date());
		// String num = Util.getCurrentDateString("yyyyMM");
	     String num_sql = "select max(application_code)+1 as count_num from tzsb_application where application_code like '%"+num+"%'";
	    SQLQuery query= getSession().createSQLQuery(num_sql);
	    Object obj=query.uniqueResult();
	    String code="";
	    if(obj==null){
			//String num = DateUtil.getDateTime("yyyyMM", new Date());
			code = DateUtil.getDateTime("yyyyMM", new Date()) + "01";
			//code=Util.getCurrentDateString("yyyyMM")+"01";
		}else{
	    BigDecimal bd=(BigDecimal) obj;
	    code=bd.toString();
		}
	    return code;
	}
	public DeviceCategories getDeviceSort(String id){
	     String hql = "from DeviceCategories where id=?";
	     Query query=  createQuery(hql, id);
	    DeviceCategories bd=(DeviceCategories) query.uniqueResult();
	    return bd;
	}
}
