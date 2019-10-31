package com.khnt.rtbox.config.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.rtbox.config.bean.PageDirVersion;
import com.khnt.rtbox.config.bean.RtPage;
import com.khnt.utils.StringUtil;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName PageDirVersionDao
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2019-01-17 10:14:06
 */
@Repository("pageDirVersionDao")
public class PageDirVersionDao extends EntityDaoImpl<PageDirVersion> {
	
	
	/**
	 * 获取最大可用版本号
	 * @param entity
	 * @return
	 */
	public Integer getMaxVersion(PageDirVersion entity) {
		Integer version = entity.getVersion();
		if(StringUtil.isEmpty(entity.getId()) || entity.getVersion() == null) {
			version = (Integer) createQuery("select max(version) from PageDirVersion where rtPage.id=?",entity.getRtPage().getId()).uniqueResult();
			if(version == null || "0".equals(version)) {
				version = 1;
			}else {
				version = version + 1;
			}
		}
		return version;
	}
}
