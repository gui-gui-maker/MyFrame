package com.scts.cspace.file.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.scts.cspace.file.bean.FileCache;

@Repository("fileCacheDao")
public class FileCacheDao extends EntityDaoImpl<FileCache> {


	public List<FileCache> getInfos(
			String file_name) {
		List<FileCache> list = new ArrayList<FileCache>();
		String hql = "from FileCache i where i.infoName like '%"+file_name+"%'";
		list = this.createQuery(hql).list();
		return list;
	}
	
	
	public HashMap<String, Object> queryById(String parent_id)
			throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		List<FileCache> list = new ArrayList<FileCache>();
		String hql = "from FileCache i where i.parent_id=? ";
		list = this.createQuery(hql, parent_id).list();
		
		wrapper.put("cacheLst", list);
		
		return wrapper;
	}
	
	/**
	 * 只查磁盘库的文件，不查文件夹
	 * author pingZhou
	 * @param file_name
	 * @return
	 */
	public List<FileCache> getInfosOnly(
			String file_name) {
		List<FileCache> list = new ArrayList<FileCache>();
		String hql = "from FileCache i where (i.infoName like '%"+file_name+"%' or i.id='"+file_name+"') and i.infoType='3'";
		list = this.createQuery(hql).list();
		return list;
	}
	
	
	
}
