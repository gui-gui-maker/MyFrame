package com.lsts.archives.dao;


import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.archives.bean.ArchivesBorrow;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.qualitymanage.bean.QualitySgcjjybg;
import com.lsts.qualitymanage.bean.QualityUpdateFile;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName ArchivesBorrowDao
 * @JDK 1.5
 * @author Jonny
 * @date  2015年11月24日 10:30:38
 */
@Repository("archivesBorrowDao")
public class ArchivesBorrowDao extends EntityDaoImpl<ArchivesBorrow> {

	
	/**获取任务
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ArchivesBorrow> getTaskAllot() {
		String hql = "from ArchivesBorrow";
		List<ArchivesBorrow> list = createQuery(hql).list();
		return list;
	}
	/**
	 * 获取申请人的id
	 * */
	@SuppressWarnings("rawtypes")
	public List getname(String name) {
		String sql = "select id from EMPLOYEE where name=?";
		List list = this.createSQLQuery(sql,name).list();
		return list;
	}
	/**
	 * 验证报告编号
	 * */
	@SuppressWarnings("unchecked")
	public List<InspectionInfo> getBgbh(String reportNumber) {
		String hql = "from InspectionInfo where report_sn=?";
		List<InspectionInfo> list = createQuery(hql,reportNumber).list();
		return list;
	}
	
	/**
	 * 获取流程id
	 * */
	@SuppressWarnings("unchecked")
	public List<ArchivesBorrow> getLcid(String id,String userId) {
		String sql = "select DISTINCT t.id from TJY2_ARCHIVES_BORROW b, v_pub_worktask t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_FN_JYSQ1%' and t.STATUS='0' and b.id=? and t.HANDLER_ID=?";
		List<ArchivesBorrow> list = this.createSQLQuery(sql,id,userId).list();
		if(list==null || list.size()==0) {
			if(this.get(id).getApplyUnitId().equals("100030")) {
				sql = "select DISTINCT t.id from TJY2_ARCHIVES_BORROW b, v_pub_worktask_add_position t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_FN_JYSQ1%' and t.STATUS='0' and b.id=? and t.HANDLER_ID=?";
				list = this.createSQLQuery(sql,id,userId).list();
			}
			if(this.get(id).getApplyUnitId().equals("100025")) {
				sql = "select DISTINCT t.id from TJY2_ARCHIVES_BORROW b, v_pub_worktask_add_position t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_FN_JYSQ1%' and t.STATUS='0' and b.id=? and t.HANDLER_ID=?";
				list = this.createSQLQuery(sql,id,userId).list();
			}
			if(this.get(id).getApplyUnitId().equals("100044")) {
				sql = "select DISTINCT t.id from TJY2_ARCHIVES_BORROW b, v_pub_worktask_add_position t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_FN_JYSQ1%' and t.STATUS='0' and b.id=? and t.HANDLER_ID=?";
				list = this.createSQLQuery(sql,id,userId).list();
			}
		}
		return list;
	}
	/**
	 * 获取process_id
	 * */
	@SuppressWarnings("unchecked")
	public List<ArchivesBorrow> getprocess_id(String id) {
		String sql = "select DISTINCT t.process_id from TJY2_ARCHIVES_BORROW b, v_pub_worktask t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_FN_JYSQ1%' and t.STATUS='3' and b.id=?";
		List<ArchivesBorrow> list = this.createSQLQuery(sql,id).list();
		return list;
	}
	
}