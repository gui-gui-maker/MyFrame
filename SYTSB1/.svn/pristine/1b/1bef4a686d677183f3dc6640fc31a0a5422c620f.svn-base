package com.khnt.rtbox.config.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.rtbox.config.bean.RtPage;
import com.khnt.utils.StringUtil;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName RtPageDao
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2016-03-22 09:51:04
 */
@Repository("rtPageDao")
public class RtPageDao extends EntityDaoImpl<RtPage> {
	
	public RtPage getByCode(String rtCode) {
		@SuppressWarnings("unchecked")
		List<RtPage> list = this.createQuery("from RtPage where rtCode=?", rtCode).list();
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 获取最大版本号
	 * @param entity
	 * @return
	 */
	public Integer getMaxVersion(RtPage entity) {
		Integer version = entity.getVersion();
		if(StringUtil.isEmpty(entity.getId()) || entity.getVersion() == null) {
			version = (Integer) createQuery("select max(version) from RtPage where template.id=?",entity.getTemplate().getId()).uniqueResult();
			if(version == null || "0".equals(version)) {
				version = 1;
			}else {
				version = version + 1;
			}
		}
		return version;
	}

	/**
	 * 根据模板和版本查询报告
	 * author pingZhou
	 * @param templateId 模板id
	 * @param bigVersion 版本id
	 * @return 模板对象
	 */
	@SuppressWarnings("null")
	public RtPage getByTemplateAndVersion(String templateId, Integer bigVersion) {
		
		String hql = " from RtPage where template.id=? and version = ?";
		List<RtPage> list = null;
		if(bigVersion==null) {
			hql = " from RtPage where template.id=? order by version desc";
			list = this.createQuery(hql, templateId).list();
		}else {
			list = this.createQuery(hql, templateId,bigVersion).list();
		}
	
		
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	public RtPage getNewByCode(String rtCode) {
		@SuppressWarnings("unchecked")
		List<RtPage> list = this.createQuery("from RtPage where rtCode=? order by version desc", rtCode).list();
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
}
