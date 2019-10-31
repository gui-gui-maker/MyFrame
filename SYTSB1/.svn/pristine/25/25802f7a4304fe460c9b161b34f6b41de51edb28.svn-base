package com.khnt.rtbox.config.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.rtbox.config.bean.PageContentRecord;
import com.khnt.utils.StringUtil;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName PageContentRecordDao
 * @JDK 1.7
 * @author zmh
 * @date 2019-01-14 19:22:34
 */
@Repository("pageContentRecordDao")
public class PageContentRecordDao extends EntityDaoImpl<PageContentRecord> {

	public String getMaxVersion(String pageContentId, String pageCode, Integer bigVersion) {
		List<Object> list = null;
		String hql = "select max(r.version) from PageContentRecord r where r.pageContent.id = ?";
		if(pageContentId==null||StringUtil.isEmpty(pageContentId)) {
			hql = "select max(r.version) from PageContentRecord r where r.pageType = ? and  r.bigVersion = ?";
			list = this.createQuery(hql, pageCode,bigVersion).list();
		}else {
			list = this.createQuery(hql, pageContentId).list();
		}
		if(list.size()>0&&list.get(0)!=null) {
			return list.get(0).toString();
		}
		return "0";
	}

	/**
	 * 查询指定页面指定版本的模本内容
	 * author pingZhou
	 * @param id
	 * @param pageCode 页面code
	 * @param version 版本
	 * @return 历史版本内容
	 */
	public PageContentRecord getByRtContentAndCodeAndVersion(String id, String pageCode, Integer version) {
		String hql = "from PageContentRecord where pageContent.id = ? and pageType = ? and version = ?";
		@SuppressWarnings("unchecked")
		List<PageContentRecord> list = this.createQuery(hql, id,pageCode,version).list();
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据大版本号查询指定页面指定版本的模本内容
	 * author pingZhou
	 * @param RtCode 
	 * @param bigVersion
	 * @param pageCode
	 * @param version
	 * @return
	 */
	public PageContentRecord getByBigVersionAndCodeAndVersion(String rtCode, Integer bigVersion, String pageCode, Integer version) {
		String hql = "from PageContentRecord where rtCode=? and  bigVersion = ? and pageType = ? and version = ?";
		@SuppressWarnings("unchecked")
		List<PageContentRecord> list = this.createQuery(hql,rtCode, bigVersion,pageCode,version).list();
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	public List<Object> getByMaxVersion(String pageCode, Integer version) {
		String hql = "select max(t.version),t.pageType  from PageContentRecord t where t.rtCode = ? and t.bigVersion = ? group by t.pageType";
		@SuppressWarnings("unchecked")
		List<Object> list = this.createQuery(hql, pageCode,version).list();
		return list;
	}
}
