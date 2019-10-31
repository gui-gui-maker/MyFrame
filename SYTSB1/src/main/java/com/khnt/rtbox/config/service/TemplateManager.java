package com.khnt.rtbox.config.service;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.rtbox.config.bean.RtPage;
import com.khnt.rtbox.config.bean.Template;
import com.khnt.rtbox.config.bean.Template;
import com.khnt.rtbox.config.dao.RtPageDao;
import com.khnt.rtbox.config.dao.TemplateDao;
import com.khnt.rtbox.template.constant.RtPath;
import com.khnt.rtbox.template.constant.RtSign;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName TemplateManager
 * @JDK 1.7
 * @author ZMH
 * @date 2019-01-15 10:45:04
 */
@Service("templateManager")
public class TemplateManager extends EntityManageImpl<Template, TemplateDao> {
	@Autowired
	TemplateDao templateDao;
	@Autowired
	RtPageManager rtPageManager;
	
	@Override
	public void save(Template entity) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		if(StringUtil.isEmpty(entity.getId())) {
			entity.setCreatedBy(user.getName());// 操作人名称
			entity.setCreatedById(user.getId());// 操作人ID
			entity.setCreatedDate(new Date());// 操作时间
		}
		entity.setLastUpdBy(user.getName());// 最后更新人名称
		entity.setLastUpdById(user.getId());// 最后更新人ID
		entity.setLastUpdDate(new Date());// 最后更新时间
		entity.setStatus(RtSign.STATUS_ENABLE);//默认启用，后续扩展
		super.save(entity);
	}
	
	@Override
	public void deleteBusiness(Serializable id) throws Exception {
		Template entity = templateDao.get(id);
		entity.setStatus(RtSign.STATUS_DEL);// 彻底删除
		templateDao.save(entity);
		/**根据自己业务状态判断是否可删除数据，不能删除抛出异常*/
		/**
		if(!RSConstant.ATTEND_STATUS_NOSUBMIT.equals(entity.getStatus())) {
			throw new KhntException("您提交的【" + CodeTableUtil.getNameByValue(RSConstant.ATTEND_APPLY_TYPE, entity.getApplyType()) + 
					"】申请正在审核中,不可删除！");
		}
		*/
	}
	
	@Override
	public void deleteBusiness(String[] ids) throws Exception {
		for(String id:ids){
			deleteBusiness(id);
		}
	}
	
	/**
	 * 检验报表名称是否存在
	 * 
	 * @param rtCode
	 */
	public void validateNotExsit(Template Template) throws KhntException {
		String rtCode = Template.getRtCode();
		if (StringUtil.isEmpty(Template.getId())) {
			// 新增
			List<Template> list = this.templateDao.listQuery("from Template where rtCode=? and status=?", rtCode, Template.getStatus());
			if (list != null && !list.isEmpty()) {
				throw new KhntException("报表代码已存在");
			}
		} else {
			// 修改
			List<Template> list = this.templateDao.listQuery("from Template where rtCode=? and status=? and id <> ?", rtCode, Template.getStatus(), Template.getId());
			if (list != null && !list.isEmpty()) {
				throw new KhntException("报表代码已存在");
			}
		}
	}

	/**
	 * 根据模板查询模板内容，没有传入版本号时默认最新
	 * author pingZhou
	 * @param request
	 * @param templateId
	 * @param pageCode
	 * @param bigVersion
	 * @param version
	 * @return
	 * @throws Exception 
	 */
	public String getPageContentByVersion(HttpServletRequest request, String templateId, String pageCode, Integer bigVersion,
			Integer version) throws Exception {
		if(bigVersion==null) {
			Template template = templateDao.get(templateId);
			bigVersion = template.getVersion();
		}
		RtPage rtPage = rtPageManager.getByTemplateAndVersion(templateId,bigVersion);
		if(rtPage==null) {
			log.debug("没有找到对应模板-----"+templateId+"---版本号："+bigVersion);
			throw new KhntException("没有找到对应模板！");
		}
		return rtPageManager.getPageContentByVersion(request, rtPage.getId(), pageCode,version);
	}
	
	public String getVersionJson(String templateId) throws Exception {
		
		Template template = templateDao.get(templateId);
		Integer bigVersion = template.getVersion();
		RtPage rtPage = rtPageManager.getByTemplateAndVersion(templateId,bigVersion);
		if(rtPage==null) {
			log.debug("没有找到对应模板-----"+templateId+"---版本号："+bigVersion);
			throw new KhntException("没有找到对应模板！");
		}
		return rtPageManager.getVersionJson(rtPage.getId());
	}

}
