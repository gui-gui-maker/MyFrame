package com.khnt.bpm.tag;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.khnt.bpm.core.service.ActivityManager;

/*******************************************************************************
 * 
 * <p>
 * 活动环节功能权限标签
 * </p>
 * 
 * @JDK 1.5
 * @author
 * @date 2012-4-5 下午02:15:52
 */
public class FunctionTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private String activityId;// 活动环节

	private String function;// 功能

	private boolean pre; // 当前活动环节是否有此功能权限

	private boolean contain = true;// 包含或者不包含

	public FunctionTag() {
		super();
		init();
	}

	@Override
	public void release() {
		super.release();
		init();
	}

	@Override
	public int doStartTag() throws JspException {
		setPre();
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			// 条件为包含并且结果确实包含 或者条件为不包含并且结果确实不包含
			if ((pre && contain) || (!contain && !pre))
				pageContext.getOut().write(bodyContent.getString().trim());
		} catch (IOException e) {
			throw new JspException("IOError while writing the body: " + e.getMessage(), e);
		}

		init();
		return super.doEndTag();
	}

	/***************************************************************************
	 * 判断当前环节是否有此工能权限
	 * 
	 * @return
	 */
	public void setPre() {
		ServletContext application = pageContext.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
		ActivityManager activityManager = (ActivityManager) ctx.getBean("activityManager");
		this.pre = activityManager.isActivityPre(activityId, function);
	}

	private void init() {
		pre = false;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public boolean isContain() {
		return contain;
	}

	public void setContain(boolean contain) {
		this.contain = contain;
	}
}
