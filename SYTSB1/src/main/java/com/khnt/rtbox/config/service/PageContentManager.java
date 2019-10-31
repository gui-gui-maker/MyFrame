package com.khnt.rtbox.config.service;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rtbox.config.bean.PageContent;
import com.khnt.rtbox.config.dao.PageContentDao;
import com.khnt.rtbox.template.constant.RtSign;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName PageContentManager
 * @JDK 1.7
 * @author zmh
 * @date 2019-01-14 19:22:34
 */
@Service("pageContentManager")
public class PageContentManager extends EntityManageImpl<PageContent, PageContentDao> {
	@Autowired
	PageContentDao pageContentDao;
	
	@Override
	public void save(PageContent entity) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		if(StringUtil.isEmpty(entity.getId())) {
			entity.setCreatedBy(user.getName());// 操作人名称
			entity.setCreatedById(user.getId());// 操作人ID
			entity.setCreatedDate(new Date());// 操作时间
		}
		entity.setLastUpdBy(user.getName());// 最后更新人名称
		entity.setLastUpdById(user.getId());// 最后更新人ID
		entity.setLastUpdDate(new Date());// 最后更新时间
		entity.setStatus(RtSign.STATUS_ENABLE);
		super.save(entity);
	}
	
	@Override
	public void deleteBusiness(Serializable id) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		PageContent entity = pageContentDao.get(id);
		entity.setLastUpdBy(user.getName());// 最后更新人名称
		entity.setLastUpdById(user.getId());// 最后更新人ID
		entity.setLastUpdDate(new Date());// 最后更新时间
		entity.setStatus(RtSign.STATUS_DEL);// 彻底删除
	}
	
	@Override
	public void deleteBusiness(String[] ids) throws Exception {
		for(String id:ids){
			deleteBusiness(id);
		}
	}
	
	/**
	 * 根据报表ID和报告模版类别
	 * @param RtCode
	 * @param pageVersionId
	 * @return
	 */
	public PageContent findByRtCode(String rtPageId, String pageType) {
		String hql = "from PageContent where rtPage.id=? and pageType=?";
		List<PageContent> list = pageContentDao.createQuery(hql, rtPageId,pageType).list();
		return list.size()>0 ? list.get(0) : null;
	}
}
