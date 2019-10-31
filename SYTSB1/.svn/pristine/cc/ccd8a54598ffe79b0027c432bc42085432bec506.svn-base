package com.lsts.finance.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.Org;
import com.lsts.finance.bean.CwBankFefund;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName CwBankFefundDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("CwBankFefundDao")
public class CwBankFefundDao extends EntityDaoImpl<CwBankFefund> {

	@SuppressWarnings("unchecked")
	public List<CwBankFefund> queryBankFefund(HttpServletRequest request,String fkBankDetailId) {
		String hql = "from CwBankFefund where fkBankDetailId = ? and dataStatus is not null and dataStatus <> '99' and dataStatus <> '2' and dataStatus <> '3' and dataStatus <> '98'";
        List<CwBankFefund> list = new ArrayList<CwBankFefund>();
		try {
			list = createQuery(hql,fkBankDetailId).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return list;
    }
}