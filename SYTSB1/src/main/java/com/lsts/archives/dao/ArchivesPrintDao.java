package com.lsts.archives.dao;

import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.archives.bean.ArchivesBorrow;
import com.lsts.archives.bean.ArchivesPrint;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName ArchivesPrintDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("archivesPrintDao")
public class ArchivesPrintDao extends EntityDaoImpl<ArchivesPrint> {

	
	/**获取任务
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ArchivesPrint> getTaskAllot() {
		String hql = "from ArchivesPrint";
		List<ArchivesPrint> list = createQuery(hql).list();
		return list;
	}
	

	/**
	 * 获取流程id
	 * */
	@SuppressWarnings("unchecked")
	public List<ArchivesPrint> getLcid(String id) {
		String sql = "select DISTINCT t.id from TJY2_ARCHIVES_PRINT b, v_pub_worktask t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_FN_DYSQ1%' and t.STATUS='0' and b.id=?";
		List<ArchivesPrint> list = this.createSQLQuery(sql,id).list();
		return list;
	}
	/**
	 * 获取process_id
	 * */
	@SuppressWarnings("unchecked")
	public List<ArchivesPrint> getprocess_id(String id) {
		String sql = "select DISTINCT t.process_id from TJY2_ARCHIVES_PRINT b, v_pub_worktask t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_FN_DYSQ1%' and t.STATUS='3' and b.id=?";
		List<ArchivesPrint> list = this.createSQLQuery(sql,id).list();
		return list;
	}
}