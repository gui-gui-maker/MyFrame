package com.khnt.rtbox.config.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.rtbox.config.bean.PageContent;
import com.khnt.rtbox.template.constant.RtSign;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName PageContentDao
 * @JDK 1.7
 * @author zmh
 * @date 2019-01-14 19:22:34
 */
@Repository("pageContentDao")
public class PageContentDao extends EntityDaoImpl<PageContent> {

	public PageContent getByRtPageAndCode(String rtboxId, String pageCode) {
		String hql = "from PageContent c where c.rtPage.id = ? and c.pageType = ? ";
		List<PageContent> list = this.createQuery(hql, rtboxId,RtSign.REPORT_TEMPLATE_INDEX + pageCode).list();
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}
}
