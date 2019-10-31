package com.lsts.qualitymanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.qualitymanage.bean.TxwjspSealRegSug;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2SealRegSugDao
 * @JDK 1.6
 * @author 
 * @date  
 */
@Repository("TxwjspSealRegSugDao")
public class TxwjspSealRegSugDao extends EntityDaoImpl<TxwjspSealRegSug> {

	public List<TxwjspSealRegSug> getAllByBusId(String id) {
		String hql = "from TxwjspSealRegSug t where t.businessId=?";
		List<TxwjspSealRegSug> list = createQuery(hql,id).list();
		return list;
	}
}