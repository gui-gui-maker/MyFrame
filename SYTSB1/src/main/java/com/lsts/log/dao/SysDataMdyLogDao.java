package com.lsts.log.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.log.bean.SysDataMdyLog;

/**
 * 系统数据修改对比日志数据处理对象
 * @ClassName SysDataMdyLogDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-10-26 17:52:00
 */
@Repository("sysDataMdyLogDao")
public class SysDataMdyLogDao extends EntityDaoImpl<SysDataMdyLog> {
	
	/**
	 * 根据业务ID获取对比数据
	 * @param business_id -- 业务ID
	 * @return 
	 * @author GaoYa
	 * @date 2017-04-29 下午18:17:00
	 */
	@SuppressWarnings("unchecked")
	public List<SysDataMdyLog> queryByBusiness_id(String business_id){
		List<SysDataMdyLog> list = new ArrayList<SysDataMdyLog>();
    	try {
    		if (StringUtil.isNotEmpty(business_id)) {
    			String hql = "from SysDataMdyLog r where r.business_id=?";
    			list = this.createQuery(hql, business_id).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
}
