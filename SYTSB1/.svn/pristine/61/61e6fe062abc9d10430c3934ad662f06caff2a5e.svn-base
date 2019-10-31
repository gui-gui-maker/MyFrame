package com.lsts.finance.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.finance.bean.CwDrawmoney;
import com.lsts.finance.bean.Fybxd;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName CwDrawmoneyDao
 * @JDK 1.5
 * @author HQ
 * @date  
 */
@Repository("cwDrawmoneyDao")
public class CwDrawmoneyDao extends EntityDaoImpl<CwDrawmoney> {

	/**
	 * 删除
	 * @param id
	 */
	public void delete (String id){
		String sql = "delete TJY2_CW_DRAWMONEY t where t.id=?";
		this.createSQLQuery(sql, id).executeUpdate();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CwDrawmoney> getNumber() {
		String hql = "from CwDrawmoney where number_tab is not null";
		List<CwDrawmoney> list = createQuery(hql).list();
		return list;
	}
	
	
}