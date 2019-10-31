package com.lsts.relevant.web;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.lsts.relevant.bean.RelevantPeople;
import com.lsts.relevant.service.RelevantPeopleService;

/**
 * 特种作业人员基本信息控制器
 * @ClassName RelevantPeopleAction
 * @JDK 1.6
 * @author GaoYa
 * @date 2014-02-13 下午05:15:00
 */
@Controller
@RequestMapping("relevant/basic")
public class RelevantPeopleAction extends SpringSupportAction<RelevantPeople, RelevantPeopleService> {

	@Autowired
	private RelevantPeopleService relevantPeopleService;

	/**
	 * 保存
	 * 
	 * @param request
	 * @param relevantPeople
	 * @throws Exception
	 */
	@RequestMapping(value = "saveBasic")
	@ResponseBody
	public HashMap<String, Object> saveBasic(HttpServletRequest request, RelevantPeople relevantPeople) throws Exception {
		String status = request.getParameter("status");
		try {
			CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
			if ("add".equals(status)) {
				relevantPeople.setCreated_by(curUser.getName());
				relevantPeople.setCreated_date(new Date());
			}else if ("modify".equals(status)) {
				relevantPeople.setLast_upd_by(curUser.getName());
				relevantPeople.setLast_upd_date(new Date());
			}
			relevantPeopleService.save(relevantPeople);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存特种作业人员基本信息失败，请重试！");
		}
		return JsonWrapper.successWrapper(relevantPeople);
	}
	
	/**
	 * 详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "detail")
	@ResponseBody
	public HashMap<String, Object> detail(HttpServletRequest request, String id) throws Exception {
		return super.detail(request, id);
	}
	
	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public HashMap<String, Object> delete(String ids) throws Exception {
		relevantPeopleService.delete(ids);
		return JsonWrapper.successWrapper(ids);
	}
}
