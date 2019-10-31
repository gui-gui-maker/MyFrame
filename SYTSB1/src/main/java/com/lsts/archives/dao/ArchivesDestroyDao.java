package com.lsts.archives.dao;


import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.archives.bean.ArchivesBorrow;
import com.lsts.archives.bean.ArchivesDestroy;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName ArchivesDestroyDao
 * @JDK 1.5
 * @author Jonny
 * @date  2015年11月24日 13:44:42
 */
@Repository("archivesDestroyDao")
public class ArchivesDestroyDao extends EntityDaoImpl<ArchivesDestroy> {

	
	/**获取任务
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ArchivesDestroy> getTaskAllot() {
		String hql = "from ArchivesDestroy";
		List<ArchivesDestroy> list = createQuery(hql).list();
		return list;
	}
}