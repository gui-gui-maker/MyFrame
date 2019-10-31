package com.khnt.rtbox.config.web;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.rtbox.config.bean.Template;
import com.khnt.rtbox.config.service.TemplateManager;
import com.khnt.utils.StringUtil;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;

/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName Template
 * @JDK 1.7
 * @author ZMH
 * @date 2019-01-15 10:45:04
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("com/rt/template")
public class TemplateAction extends
		SpringSupportAction<Template, TemplateManager> {

	@Autowired
	private TemplateManager templateManager;

	@Override
	public HashMap<String, Object> save(HttpServletRequest request, Template entity) throws Exception {
		if (StringUtil.isEmpty(entity.getId())) {
			this.templateManager.validateNotExsit(entity);
		}
		return super.save(request, entity);
	}

	/**
	 * 根据模板查询模板内容，没有传入版本号时默认最新
	 * author pingZhou
	 * @param request
	 * @param templateId 模板id
	 * @param pageCode 页面code
	 * @param bigVersion 大版本号
	 * @param version 页面版本号
	 * @return 模板内容
	 * @throws Exception
	 */
	@RequestMapping("getPageContentByVersion")
	@ResponseBody
	public HashMap<String, Object> getPageContentByVersion(HttpServletRequest request,String templateId,String pageCode,Integer bigVersion,Integer version) throws Exception {
		try {
			
			String content  = templateManager.getPageContentByVersion(request,templateId,pageCode,bigVersion,version);
			return JsonWrapper.successWrapper(content);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	

}
