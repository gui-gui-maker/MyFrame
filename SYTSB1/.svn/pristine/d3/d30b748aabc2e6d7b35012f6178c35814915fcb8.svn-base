package com.scts.discipline.web;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.scts.discipline.bean.DisciplineZdjr;
import com.scts.discipline.service.DisciplineZdjrService;

@Controller
@RequestMapping("disciplineZdjrAction")
public class DisciplineZdjrAction extends SpringSupportAction<DisciplineZdjr, DisciplineZdjrService>{

	@Autowired
	DisciplineZdjrService disciplineZdjrService;

	@RequestMapping("saveZdjr")
	@ResponseBody
	public HashMap<String, Object> saveZdjr(HttpServletRequest request,DisciplineZdjr entity){
		try {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			disciplineZdjrService.saveZdjr(user,entity);
		} catch (Exception e) {
			return JsonWrapper.failureWrapperMsg("操作失败，请重试！");
		}
		return JsonWrapper.successWrapper();
	}
	/**
	 * 启动流程
	 * @param request
	 * @return
	 */
	@RequestMapping("subflow")
	@ResponseBody
	public HashMap<String, Object> subflow(HttpServletRequest request){
		try {
			String id=request.getParameter("id");
			String flowId=request.getParameter("flowId");
			if(StringUtil.isNotEmpty(id)){
				DisciplineZdjr bean=disciplineZdjrService.get(id);
				disciplineZdjrService.subFlow(bean,flowId);
				return JsonWrapper.successWrapper(); 
			}else{
				System.err.println("主动介入，提交失败！-----------------id is null");
				 return JsonWrapper.failureWrapperMsg("参数错误！");
			}
		} catch (Exception e) {
			System.out.println(e);
			return JsonWrapper.failureWrapperMsg("流程启动失败!");
		}
	}
	/**
	 * 主动介入-通过
	 * @param request
	 * @return
	 */
	@RequestMapping("zdjrTg")
	@ResponseBody
	public HashMap<String, Object> zdjrTg(HttpServletRequest request){
		try {
			String entity=request.getParameter("entity");
			JSONObject json= JSON.parseObject(entity);
			DisciplineZdjr bean=JSON.parseObject(entity, DisciplineZdjr.class);
        	String activity_id=json.getString("activity_id");
        	String jjfgy=json.getString("fgy");
			disciplineZdjrService.zdjrTg(bean,activity_id,jjfgy);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			System.out.println(e);
			return JsonWrapper.failureWrapper("审核失败！");
		}
	}
	/**
	 * 流程结束
	 * @param request
	 * @return
	 */
	@RequestMapping("zdjrFlowEnd")
	@ResponseBody
	public HashMap<String, Object> zdjrFlowEnd(HttpServletRequest request){
		try {
			String entity=request.getParameter("entity");
			JSONObject json= JSON.parseObject(entity);
			DisciplineZdjr bean=JSON.parseObject(entity, DisciplineZdjr.class);
			String type=json.getString("type");
			String process_id=json.getString("process_id");
			disciplineZdjrService.zdjrFlowEnd(bean,type,process_id);
			return JsonWrapper.successWrapper("操作成功！");
		} catch (Exception e) {
			System.out.println(e);
			return JsonWrapper.failureWrapper("操作失败！");
		}
	}

	@RequestMapping("print")
	@ResponseBody
	public HashMap<String, Object> print(HttpServletRequest request,String id){
		try {
			DisciplineZdjr entity=disciplineZdjrService.getBeanById(id);
			return JsonWrapper.successWrapper(entity);
		} catch (Exception e) {
			System.err.println("重大监督事项打印出错！！！！！！");
			e.printStackTrace();
			return JsonWrapper.failureWrapper("打印失败！");
		}
	}
	/**
	 * 分配人员处理
	 * @param request
	 * @return
	 */
	@RequestMapping("zdjrSzCzr")
	@ResponseBody
	public HashMap<String, Object> zdjrSzCzr(HttpServletRequest request){
		try {
			String id=request.getParameter("id");
			String ids=request.getParameter("ids");
			String names=request.getParameter("names");
			String fzrxf=URLDecoder.decode(request.getParameter("fzrxf"), "UTF-8");
			if(StringUtil.isNotEmpty(id)){
				DisciplineZdjr entity=disciplineZdjrService.get(id);
				entity.setCz_user_ids(ids);
				entity.setCz_user_names(names);
				entity.setFzrxf(fzrxf);
				disciplineZdjrService.save(entity);
			}
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper("设置失败");
		}
	}
	
	/**
	 * 完结
	 * @param request
	 * @return
	 */
	@RequestMapping("zdjrWj")
	@ResponseBody
	public HashMap<String, Object> zdjrWj(HttpServletRequest request){
		try {
			String id=request.getParameter("id");
			if(StringUtil.isNotEmpty(id)){
				DisciplineZdjr entity=disciplineZdjrService.get(id);
				entity.setState("6");
				entity.setBljg_time(new Date());
				disciplineZdjrService.save(entity);
			}
			
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper();
		}
	}

	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(HttpServletRequest request, String ids){
		String[] idArr = ids.split(",");
		for (int i = 0; i < idArr.length; i++) {
			disciplineZdjrService.del(idArr[i]);
		}
		return JsonWrapper.successWrapper(ids);
	}
	
}
