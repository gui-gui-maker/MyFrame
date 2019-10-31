package com.lsts.archives.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.archives.bean.ArchivesFile;
import com.lsts.archives.bean.Uploads;
import com.lsts.humanresources.bean.Upload;
import com.lsts.qualitymanage.bean.QualityUpdateFile;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName ArchivesFileDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("archivesFileDao")
public class ArchivesFileDao extends EntityDaoImpl<ArchivesFile> {

	/**获取任务
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ArchivesFile> getTaskAllot() {
		String hql = "from ArchivesFile";
		List<ArchivesFile> list = createQuery(hql).list();
		return list;
	}
	/**获取附件
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Uploads> getFj(String id) {
		String hql = "from Uploads where id=?";
		List<Uploads> list =createQuery(hql,id).list();
		return list;
	}
	
	/**获取附件
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Uploads> getIds(String sn) {
		String sql = "select id from TJY2_ARCHIVES_UPLOAD where FILE_ID=?";
		List<Uploads> list =createSQLQuery(sql,sn).list();
		return list;
	}
}