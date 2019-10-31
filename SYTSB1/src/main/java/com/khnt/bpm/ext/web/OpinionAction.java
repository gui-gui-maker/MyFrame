package com.khnt.bpm.ext.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.ext.bean.Opinion;
import com.khnt.bpm.ext.service.OpinionManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.StringUtil;

/**
 * 工作流处理签署意见web controller
 * 
 */
@Controller
@RequestMapping("/bpm/opinion/")
public class OpinionAction extends SpringSupportAction<Opinion, OpinionManager> {

	@Autowired
	private OpinionManager opinionManager;

	@Autowired
	private ActivityManager activityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.khnt.core.crud.web.SpringSupportAction#save(javax.servlet.http.
	 * HttpServletRequest, com.khnt.core.crud.bean.BaseEntity)
	 */
	public HashMap<String, Object> save(HttpServletRequest request, Opinion opinion) throws Exception {
		String activityId = request.getParameter("activityId");
		if (StringUtil.isNotEmpty(activityId)) {
			Activity activity = activityManager.get(activityId);
			if (activity != null && "500".equals(activity.getState())) {
				throw new KhntException("该环节已经处理过了，不能重复提交");
			}
		}
		CurrentSessionUser cuser = super.getCurrentUser();
		if (StringUtil.isEmpty(opinion.getSignerId()))
			opinion.setSignerId(cuser.getId());
		if (StringUtil.isEmpty(opinion.getSignerName()))
			opinion.setSignerName(cuser.getName());
		if (opinion.getSignDate() == null)
			opinion.setSignDate(new Date());
		if (StringUtil.isEmpty(opinion.getLock()))
			opinion.setLock("0");// 未锁定状态，可修改
		String att = request.getParameter("attachment");
		opinionManager.save(opinion, att);
		return JsonWrapper.successWrapper(opinion);
	}

	/**
	 * 获取业务意见列表
	 * 
	 * @param serviceId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("serviceOpinion")
	@ResponseBody
	public String getServiceOpinion(String serviceId) throws Exception {
		JSONArray oar = this.opinionManager.queryServiceOpinion(serviceId);
		JSONObject rm = new JSONObject();
		rm.put("success", true);
		rm.put("data", oar);
		return rm.toString();
	}

	/**
	 * 获取某个业务的某个环节或者某个workitem的意见，用于重复签署意见和修改意见
	 * 
	 * @param aid
	 * @param sid
	 * @param item
	 * @return
	 */
	@RequestMapping("signOpinion")
	@ResponseBody
	public Map<String, Object> signOpinion(String aid, String sid, String item) {
		CurrentSessionUser user = getCurrentUser();
		Opinion o = this.opinionManager.signOpinion(null, sid, aid, item, user.getId());
		Map<String, Object> activity = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(aid)) {
			Activity at = activityManager.get(aid);
			if (at != null) {
				activity.put("name", at.getName());
				activity.put("function", at.getFunction());
			}
		}
		Map<String, Object> result = JsonWrapper.successWrapper(o);
		result.put("activity", activity);
		return result;
	}

	/**
	 * 检查指定业务在某个环节是否签署了意见
	 * 
	 * @param aid
	 * @param sid
	 * @param item
	 * @return
	 */
	@RequestMapping("checkSignOpinion")
	@ResponseBody
	public String checkSignOpinion(String aid, String sid, String item) {
		Opinion o = this.opinionManager.signOpinion(null, sid, aid, item, null);
		return "{\"success\":true,\"result\":" + (o.getId() == null ? false : true) + "}";
	}
}
