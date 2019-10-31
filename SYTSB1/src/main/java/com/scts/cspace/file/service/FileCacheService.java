package com.scts.cspace.file.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.scts.cspace.file.bean.FileCache;
import com.scts.cspace.file.dao.FileCacheDao;
import com.scts.cspace.file.dao.TjyptFileInfoDao;

/**
 * 物理资源属性业务逻辑对象
 * @ClassName TjyptFileInfoService
 * @JDK 1.7
 * @author xcb
 * @date 2016-10-24 下午04:40:00
 */
@Service("fileCacheService")
public class FileCacheService extends
		EntityManageImpl<FileCache, FileCacheDao> {
	
	@Autowired
	private FileCacheDao fileCacheDao;
	

	public HashMap<String, Object> queryById(String parent_id)
			throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		
		return fileCacheDao.queryById(parent_id);
	}
		
	
	
}
