package com.lsts.archives.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.archives.bean.ArchivesBox;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName ArchivesBoxDao
 * @JDK 1.5
 * @author Jonny
 * @date  2015年12月21日 11:18:39
 */
@Repository("ArchivesBoxDao")
public class ArchivesBoxDao extends EntityDaoImpl<ArchivesBox> {

	
	/**获取任务
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ArchivesBox> getTaskAllot() {
		String hql = "from ArchivesBox";
		List<ArchivesBox> list = createQuery(hql).list();
		return list;
	}
	
	/**获取盒子中是否有报告
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ArchivesBox> getReportNumber(String info_id) {
		String sql = "select * from TJY2_ARCHIVES_BOX where report_number_id like"+"'%"+info_id+"%'";
		List<ArchivesBox> list = createSQLQuery(sql).list();
		return list;

	}
}