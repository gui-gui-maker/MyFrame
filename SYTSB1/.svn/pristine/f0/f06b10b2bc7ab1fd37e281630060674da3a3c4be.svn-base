package com.lsts.qualitymanage.dao;


import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.finance.bean.Clfbxd;
import com.lsts.qualitymanage.bean.QualityZssq;
import com.lsts.qualitymanage.bean.TxwjspSealreg;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2QualityZssqDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("QualityZssqDao")
public class QualityZssqDao extends EntityDaoImpl<QualityZssq> {

	@SuppressWarnings("unchecked")
	public List<QualityZssq> getTaskAllot() {
		String hql = "from QualityZssq where fileNumber is not null";
		List<QualityZssq> list = createQuery(hql).list();
		return list;
	}
	
	/**
	 * 获取流程id
	 * */
	@SuppressWarnings("unchecked")
	public List<QualityZssq> getLcid(String id) {
		String sql = "select DISTINCT t.id from TJY2_QUALITY_ZSSQ b, v_pub_worktask t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_ZL_ZSSQ1%' and t.STATUS='0' and b.id=?";
		List<QualityZssq> list = this.createSQLQuery(sql,id).list();
		return list;
	}
	/**
	 * 获取process_id
	 * */
	@SuppressWarnings("unchecked")
	public List<QualityZssq> getprocess_id(String id) {
		String sql = "select DISTINCT t.process_id from TJY2_QUALITY_ZSSQ b, v_pub_worktask t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_ZL_ZSSQ1%' and t.STATUS='3' and b.id=?";
		List<QualityZssq> list = this.createSQLQuery(sql,id).list();
		return list;
	}

}