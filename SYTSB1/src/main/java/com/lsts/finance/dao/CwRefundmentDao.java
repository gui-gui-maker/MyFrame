package com.lsts.finance.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.finance.bean.CwDrawmoney;
import com.lsts.finance.bean.CwRefundment;
import com.lsts.finance.bean.Fybxd;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName CwRefundmentDao
 * @JDK 1.5
 * @author HQ
 * @date  
 */
@Repository("cwRefundmentDao")
public class CwRefundmentDao extends EntityDaoImpl<CwRefundment> {

	/**
	 * 删除
	 * @param id
	 */
	public void delete (String id){
		String sql = "delete TJY2_CW_REFUNDMENT t where t.id=?";
		this.createSQLQuery(sql, id).executeUpdate();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CwRefundment> getNumber() {
		String hql = "from CwRefundment where number_tab is not null";
		List<CwRefundment> list = createQuery(hql).list();
		return list;
	}
	
	
}