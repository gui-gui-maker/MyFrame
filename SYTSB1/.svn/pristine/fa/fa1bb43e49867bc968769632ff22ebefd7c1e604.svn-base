package com.scts.payment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.scts.payment.bean.PayInfoUnit;
@Repository("payInfoUnitDao")
public class PayInfoUnitDao extends EntityDaoImpl<PayInfoUnit> {

	public List<PayInfoUnit> getListByPayId(String id){
		
		
		String hql = "from PayInfoUnit t where t.fk_pay_info_id = ?";
		
		List<PayInfoUnit> list = this.createQuery(hql, id).list();
		
		return list;
	}
	
	
	public void deleteByPayId(String id){
		
		
		String hql = "delete from  PayInfoUnit t where t.fk_pay_info_id = ?";
		
		this.createQuery(hql, id).executeUpdate();
		
	}
}
