package com.lsts.finance.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.finance.bean.CwYijian;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName CwYijianDao
 * @JDK 1.5
 * @author Jonny
 * @date  2015年11月30日 15:06:09
 */
@Repository("Tjy2CwYijianDao")
public class CwYijianDao extends EntityDaoImpl<CwYijian> {

	
	@SuppressWarnings("unchecked")
    public List<CwYijian> getyijian(String id) {
    	List<CwYijian> list = new ArrayList<CwYijian>();
    	try {
    		if (StringUtil.isNotEmpty(id)) {
    			String hql = "from CwYijian r where r.fileId=?";
    			list = this.createQuery(hql, id).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
    }
	
	@SuppressWarnings("rawtypes")
	public List getmid(String ids) {
		String sql = "select * from SYS_USER where EMPLOYEE_ID=?";
		List list = this.createSQLQuery(sql,ids).list();
		return list;
	}
}