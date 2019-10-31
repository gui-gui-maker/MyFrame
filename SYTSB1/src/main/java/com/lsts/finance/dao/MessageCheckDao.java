package com.lsts.finance.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.finance.bean.MessageCheck2;
import com.lsts.humanresources.bean.EmployeeBase;
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
@Repository("messageCheckDao")
public class MessageCheckDao extends EntityDaoImpl<MessageCheck2> {
	/**
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<MessageCheck2> MessageCheck2(String dhhm,String yzm ) {
		String hql = "from MessageCheck2 t where t.account=? and t.fkMsg=?";
		return createQuery(hql,dhhm,yzm).list();
		}
	@SuppressWarnings("unchecked")
	public List<MessageCheck2> MessageCheck212(String yzm ) {
		String hql = "from MessageCheck2 t where t.fkMsg=?";
		List<MessageCheck2> list=createQuery(hql,yzm).list();
		return list;
		}
	@SuppressWarnings("rawtypes")
	public List getmid(String ids) {
		String sql = "select * from SYS_USER where id=?";
		List list = this.createSQLQuery(sql,ids).list();
		return list;
	}
	
	/**查找申请人手机号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getsjh(String userId) {
		String sql = "select MOBILE_TEL from EMPLOYEE where Id=?";
		List<String> list = createSQLQuery(sql,userId).list();
		return list;

	}
	
	

}