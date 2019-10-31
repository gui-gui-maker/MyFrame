package com.scts.payment.order.service;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.scts.payment.order.bean.LockUserCid;
import com.scts.payment.order.dao.LockUserCidDao;

@Service("lockUserCidService")
public class LockUserCidService extends EntityManageImpl<LockUserCid,LockUserCidDao>{
	@Autowired
	private LockUserCidDao lockUserCidDao;
	public List<LockUserCid> authQuery(String cid) throws Exception{
		return lockUserCidDao.authQuery(cid);
	}
	
	public void deleteLockUser(String cid){
		String sql="delete from LOCK_USER_CID where CID=?";
		lockUserCidDao.createSQLQuery(sql, cid).executeUpdate();
	}
	
	/**
	 * 根据用户id查询用户姓名
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> queryUser(String userId){
		String sql="select NAME from SYS_USER where id=? ";
		Query query=lockUserCidDao.createSQLQuery(sql, userId).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list=query.list();
		return list;
	}
	
	/**
	 * 查询绑定用户的设备id
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> queryLockCid(String userId){
		String sql="select CID from LOCK_USER_CID where FK_USER_ID=? ";
		Query query=lockUserCidDao.createSQLQuery(sql, userId).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list=query.list();
		return list;
	}
	
	
	/**
	 * 查询绑定用户的设备id
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> queryLockUserCid(String userId){
		String sql="select ID,CID from LOCK_USER_CID where FK_USER_ID=? ";
		Query query=lockUserCidDao.createSQLQuery(sql, userId).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list=query.list();
		return list;
	}
	
	/**
	 * 删除码表签名数据
	 * @param id
	 */
	public void deleteTableValue(String id){
		String sql="delete from PUB_CODE_TABLE_VALUES where id=?";
		lockUserCidDao.createSQLQuery(sql, id).executeUpdate();
	}
}
