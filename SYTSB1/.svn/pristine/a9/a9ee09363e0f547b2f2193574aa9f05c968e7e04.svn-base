package com.lsts.qualitymanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.qualitymanage.bean.QualitySgcjjybg;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2QualitySgcjjybgDao
 * @JDK 1.5
 * @author Jonny
 * @date  2016年1月19日 09:37:07
 */
@Repository("QualitySgcjjybgDao")
public class QualitySgcjjybgDao extends EntityDaoImpl<QualitySgcjjybg> {

	
	@SuppressWarnings("unchecked")
	public List<QualitySgcjjybg> getTaskAllot() {
		String hql = "from QualitySgcjjybg where identifier is not null and status !='CANCEL'";
		List<QualitySgcjjybg> list = createQuery(hql).list();
		return list;
	}
	/**
	 * 获取流程id
	 * */
	@SuppressWarnings("unchecked")
	public List<QualitySgcjjybg> getLcid(String id) {
		String sql = "select DISTINCT t.id from TJY2_QUALITY_SGCJJYBG b, v_pub_worktask t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_ZL_SGCJJYBG%' and t.STATUS='0' and b.id=?";
		List<QualitySgcjjybg> list = this.createSQLQuery(sql,id).list();
		return list;
	}
	/**
	 * 获取process_id
	 * */
	@SuppressWarnings("unchecked")
	public List<QualitySgcjjybg> getprocess_id(String id) {
		String sql = "select DISTINCT t.process_id from TJY2_QUALITY_SGCJJYBG b, v_pub_worktask t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_ZL_SGCJJYBG%' and t.STATUS='3' and b.id=?";
		List<QualitySgcjjybg> list = this.createSQLQuery(sql,id).list();
		return list;
	}
}