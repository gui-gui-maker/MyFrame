package com.khnt.rtbox.config.service;


import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rtbox.config.bean.PageDirVersion;
import com.khnt.rtbox.config.dao.PageDirVersionDao;
import com.khnt.rtbox.template.constant.RtSign;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName PageDirVersionManager
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2019-01-17 10:14:06
 */
@Service("pageDirVersionManager")
public class PageDirVersionManager extends EntityManageImpl<PageDirVersion, PageDirVersionDao> {
	@Autowired
	PageDirVersionDao pageDirVersionDao;
	
	@Override
	public void save(PageDirVersion entity) throws Exception {
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
		PageDirVersion entity = pageDirVersionDao.get(id);
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
	 * 获取最大可用版本号
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public Integer getMaxVersion(PageDirVersion entity) throws Exception {
		return pageDirVersionDao.getMaxVersion(entity);
	}
}
