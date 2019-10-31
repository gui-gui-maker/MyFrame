package com.lsts.relevant.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.StringUtil;
import com.lsts.relevant.bean.RelevantPeopleCert;
import com.lsts.relevant.service.RelevantPeopleCertService;

/**
 * 特种作业人员持证情况控制器
 * @ClassName RelevantPeopleCertAction
 * @JDK 1.6
 * @author GaoYa
 * @date 2014-02-13 下午05:00:00
 */
@Controller
@RequestMapping("relevant/cert")
public class RelevantPeopleCertAction extends SpringSupportAction<RelevantPeopleCert, RelevantPeopleCertService> {

	@Autowired
	private RelevantPeopleCertService relevantPeopleCertService;
	
	/**
	 * 保存
	 * 
	 * @param request
	 * @param relevantPeopleCert
	 * @throws Exception
	 */
	@RequestMapping(value = "saveCert")
	@ResponseBody
	public HashMap<String, Object> saveCert(HttpServletRequest request, RelevantPeopleCert relevantPeopleCert) throws Exception {
		try {
			CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
			if(StringUtil.isEmpty(relevantPeopleCert.getId())){
				relevantPeopleCert.setCreated_by(curUser.getName());
				relevantPeopleCert.setCreated_date(new Date());
			}else{
				relevantPeopleCert.setLast_upd_by(curUser.getName());
				relevantPeopleCert.setLast_upd_date(new Date());
			}
			relevantPeopleCertService.save(relevantPeopleCert); // 保存持证情况
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存持证情况失败，请重试！");
		}
		return JsonWrapper.successWrapper(relevantPeopleCert);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getList")
	@ResponseBody
	public HashMap<String, Object> getList(HttpServletRequest request,String id) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		List<Map> datalist = new ArrayList<Map>();
		try {
			if (StringUtil.isNotEmpty(id)) {
				List<RelevantPeopleCert> list = relevantPeopleCertService.queryRelevantPeopleCertByBasicId(id);
				if(!list.isEmpty()){
					for (RelevantPeopleCert relevantPeopleCert : list) {
						Map map = relevantPeopleCert.to_Map();
						datalist.add(map);
					}
				}
				wrapper.put("success", true);
				wrapper.put("datalist", datalist);
			}
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("error", true);
		}
		return wrapper;
	}
	
	/**
	 * 删除持证情况
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteCerts")
	@ResponseBody
	public HashMap<String, Object> deleteCerts(String ids) throws Exception {
		relevantPeopleCertService.delete(ids);
		return JsonWrapper.successWrapper(ids);
	}
}
