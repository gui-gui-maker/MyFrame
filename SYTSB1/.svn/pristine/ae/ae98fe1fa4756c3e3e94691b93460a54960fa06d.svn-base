package com.lsts.finance.dao;

import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.finance.bean.CwBorrowMoney;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName CwMoneyDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("cwBorrowMoneyDao")
public class CwBorrowMoneyDao extends EntityDaoImpl<CwBorrowMoney> {

	
	@SuppressWarnings("unchecked")
	public List<CwBorrowMoney> getTaskAllot() {
		String hql = "from CwBorrowMoney where identifier is not null";
		List<CwBorrowMoney> list = createQuery(hql).list();
		return list;
	}
}