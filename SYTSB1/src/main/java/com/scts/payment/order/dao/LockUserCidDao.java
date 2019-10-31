package com.scts.payment.order.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.scts.payment.order.bean.LockUserCid;

@Repository("lockUserCidDao")
public class LockUserCidDao extends EntityDaoImpl<LockUserCid>{
	
	/**
	 * 根据设备cid查询绑定用户
	 * @param fk_per_user_id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LockUserCid> authQuery(String cid)throws Exception{
		Session session = this.getSession();
		String sql="select * from LOCK_USER_CID where CID=?";
		SQLQuery query = session.createSQLQuery(sql).addEntity(LockUserCid.class);
        query.setParameter(0, cid);
        List<LockUserCid> list = (List<LockUserCid>) query.list();       
		return list;
	}
}
