package com.khnt.bpm.tag;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.hibernate.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.khnt.bpm.ext.bean.Opinion;
import com.khnt.bpm.ext.dao.OpinionDao;
import com.khnt.utils.StringUtil;

public class OpinionTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private String name, objectId, node, workitem, remark;

	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			ServletContext application = pageContext.getServletContext();
			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
			OpinionDao opinionDao = (OpinionDao) ctx.getBean("opinionDao");
			String where = "";
			List<Object> qValue = new ArrayList<Object>();
			if (!StringUtil.isEmpty(name)) {
				where += "and name=? ";
				qValue.add(getParam(name));
			}
			if (!StringUtil.isEmpty(objectId)) {
				where += "and objectId=? ";
				qValue.add(getParam(objectId));
			}
			if (!StringUtil.isEmpty(node)) {
				where += "and node=? ";
				qValue.add(getParam(node));
			}
			if (!StringUtil.isEmpty(workitem)) {
				where += "and workitem=? ";
				qValue.add(getParam(workitem));
			}
			if (!StringUtil.isEmpty(remark)) {
				where += "and remark=? ";
				qValue.add(getParam(remark));
			}
			if (where.length() < 1) {
				throw new JspTagException("参数设置错误");
			} else {
				where = where.substring(3, where.length());
				String hql = "from Opinion where " + where;
				Query q = opinionDao.createQuery(hql, qValue.toArray());
				List<Opinion> list = q.list();
				for (Opinion obj : list) {
					out.write("[" + obj.getSignerName() + " " + obj.getSignDate() + "]");
					out.write(obj.getOpinion() + "<br>");
				}
			}
		} catch (Exception e) {
			throw new JspTagException(e.getMessage());
		}
		return SKIP_BODY;
	}

	public String getParam(String name) throws Exception {
		if (name.indexOf("#") == 0) {
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			String value = request.getParameter(name.substring(1, name.length()));
			byte[] temp;
			String att = "";
			temp = value.getBytes("ISO8859-1");
			att = new String(temp, "utf-8");
			return att;
		} else {
			return name;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWorkitem() {
		return workitem;
	}

	public void setWorkitem(String workitem) {
		this.workitem = workitem;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
