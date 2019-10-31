package com.lsts.qualitymanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.qualitymanage.bean.QualitySgcjjybg;
import com.lsts.qualitymanage.bean.TxwjspSealreg;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2QualityTxwjspSealregDao
 * @JDK 1.7
 * @author 
 * @date  
 */
@Repository("txwjspSealregDao")
public class TxwjspSealregDao extends EntityDaoImpl<TxwjspSealreg> {
	
	
	/**
	 * 获取流程id
	 * */
	@SuppressWarnings("unchecked")
	public List<TxwjspSealreg> getLcid(String id) {
		String sql = "select DISTINCT t.id from TJY2_QUALITY_TXWJSP_SEALREG b, v_pub_worktask t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_ZL_GZDJ%' and t.STATUS='0' and b.id=?";
		List<TxwjspSealreg> list = this.createSQLQuery(sql,id).list();
		return list;
	}
	/**
	 * 获取process_id
	 * */
	@SuppressWarnings("unchecked")
	public List<TxwjspSealreg> getprocess_id(String id) {
		String sql = "select DISTINCT t.process_id from TJY2_QUALITY_TXWJSP_SEALREG b, v_pub_worktask t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_ZL_GZDJ%' and t.STATUS='3' and b.id=?";
		List<TxwjspSealreg> list = this.createSQLQuery(sql,id).list();
		return list;
	}
	/**
	 * 通过申请表id获取流程表表id
	 * @param Id
	 * @return
	 */
public List getFlowId(String id){
		String sql = "select distinct(t.activity_id) from TJY2_QUALITY_TXWJSP_SEALREG b, v_pub_worktask t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_ZL_GZDJ%' and t.STATUS='0' and b.id=?";
		List list = this.createSQLQuery(sql,id).list();
		return list;
	}
/**
 * 通过流程id设置处理状态
 * @param activityId
 * @return
 */

public List getProcessId(String id){
	String sql = "select t.process_id from TJY2_QUALITY_TXWJSP_SEALREG b, v_pub_worktask t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_ZL_GZDJ%' and t.STATUS='0' and b.id=?";
	List list = this.createSQLQuery(sql,id).list();
	return list;
}
@SuppressWarnings("unchecked")
public List<TxwjspSealreg> getTaskAllot() {
	String hql = "from TxwjspSealreg where identifier is not null";
	List<TxwjspSealreg> list = createQuery(hql).list();
	return list;
   }
/**
 * 通过部门ID获取当前部门的负责人
 * @param orgID
 * @return
 */
/*public List getUser(String orgId){
	String sql = "select e.id,e.name,e.mobile_tel from SYS_ROLE t ,sys_user_role r,sys_user u,employee e where t.name='部门负责人' and r.role_id=t.id and u.id=r.user_id and e.id=u.employee_id and u.org_id=?";
	List list = this.createSQLQuery(sql,orgId).list();
	return list;
}*/
}