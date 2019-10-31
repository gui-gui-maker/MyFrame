package com.lsts.qualitymanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.qualitymanage.bean.QualityStaffTrain;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName QualityStaffTrainDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("QualityStaffTrainDao")
public class QualityStaffTrainDao extends EntityDaoImpl<QualityStaffTrain> {

	/**获取任务
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<QualityStaffTrain> getQualityStaffTrain() {
		String hql = "from QualityStaffTrain";
		List<QualityStaffTrain> list = createQuery(hql).list();
		return list;
	}
	
	/**
	 * 获取流程id
	 * */
	@SuppressWarnings("unchecked")
	public List<QualityStaffTrain> getLcid(String id) {
		String sql = "select DISTINCT t.id from TJY2_QUALITY_STAFF_TRAIN b, v_pub_worktask t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_ZL_ZGPX%' and t.STATUS='0' and b.id=?";
		List<QualityStaffTrain> list = this.createSQLQuery(sql,id).list();
		return list;
	}
	/**
	 * 获取process_id
	 * */
	@SuppressWarnings("unchecked")
	public List<QualityStaffTrain> getprocess_id(String id) {
		String sql = "select DISTINCT t.process_id from TJY2_QUALITY_STAFF_TRAIN b, v_pub_worktask t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_ZL_ZGPX%' and t.STATUS='3' and b.id=?";
		List<QualityStaffTrain> list = this.createSQLQuery(sql,id).list();
		return list;
	}
	
}