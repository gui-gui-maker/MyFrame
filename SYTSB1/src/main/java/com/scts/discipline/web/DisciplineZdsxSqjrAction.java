package com.scts.discipline.web;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fwxm.recipients.bean.Tjy2ChLq;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.finance.bean.Fybxd;
import com.scts.discipline.bean.DisciplineZdjr;
import com.scts.discipline.bean.DisciplineZdsxSqjr;
import com.scts.discipline.service.DisciplineZdsxSqjrService;

@Controller
@RequestMapping("/com/zdsx/sqjr")
public class DisciplineZdsxSqjrAction extends SpringSupportAction<DisciplineZdsxSqjr, DisciplineZdsxSqjrService>{

	@Autowired
	DisciplineZdsxSqjrService disciplineZdsxSqjrService;
	

	@RequestMapping("savebasic")
	@ResponseBody
	public HashMap<String, Object> savebasic(HttpServletRequest request,DisciplineZdsxSqjr bean){
		HashMap<String, Object> map=new HashMap<String, Object>();
		try {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			disciplineZdsxSqjrService.savebasic(bean,user);
			map.put("success", true);
		} catch (Exception e) {
			System.err.println(e);
			map.put("success", false);
		}
		return map;
	}
	/**
	 * 启动流程
	 * @param request
	 * @return
	 */
	@RequestMapping("subflow")
	@ResponseBody
	public HashMap<String, Object> subflow(HttpServletRequest request){
		HashMap<String, Object> map=new HashMap<String, Object>();
		try {
			String id=request.getParameter("id");
			String flowId=request.getParameter("flowId");
			if(StringUtil.isNotEmpty(id)){
				DisciplineZdsxSqjr bean=disciplineZdsxSqjrService.get(id);
				return disciplineZdsxSqjrService.subFlow(bean,flowId);
			}else{
				System.err.println("申请介入，提交失败！-----------------id is null");
				 return JsonWrapper.failureWrapperMsg("参数错误！");
			}
			
			
//			map.put("success", true);
		} catch (Exception e) {
			System.err.println(e);
			map.put("success", false);
		}
		return map;
	}
	

	// 查询流程步骤信息
	@RequestMapping(value = "getFlowStep")
	@ResponseBody
	public ModelAndView getFlowStep(HttpServletRequest request)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map = disciplineZdsxSqjrService.getFlowStep(request.getParameter("id"));
		map.put("sn", request.getParameter("sn"));
		ModelAndView mav = new ModelAndView("app/flow/flow_card", map);
		return mav;
	}


    @RequestMapping({"sqjrTg"})
    @ResponseBody
    public HashMap<String, Object> tgSave(HttpServletRequest request) throws Exception {
    		HashMap<String, Object> map=new HashMap<String, Object>();
    		try {
    			String entity=request.getParameter("entity");
    			JSONObject json= JSON.parseObject(entity);
            	DisciplineZdsxSqjr bean=JSON.parseObject(entity, DisciplineZdsxSqjr.class);
            	String activity_id=json.getString("activity_id");
    			disciplineZdsxSqjrService.sqjrTg(bean,activity_id);
    			map.put("success", true);
    		} catch (Exception e) {
    			System.err.println(e);
    			map.put("success", false);
    		}
    		return map;
    }
    /**
	 * 流程结束
	 * @param request
	 * @return
	 */
	@RequestMapping("sqjrFlowEnd")
	@ResponseBody
	public HashMap<String, Object> sqjrFlowEnd(HttpServletRequest request){
		try {
			String entity=request.getParameter("entity");
			JSONObject json= JSON.parseObject(entity);
			DisciplineZdsxSqjr bean=JSON.parseObject(entity, DisciplineZdsxSqjr.class);
			String type=json.getString("type");
			String process_id=json.getString("process_id");
			disciplineZdsxSqjrService.sqjrFlowEnd(bean,type,process_id);
			return JsonWrapper.successWrapper("操作成功！");
		} catch (Exception e) {
			System.out.println(e);
			return JsonWrapper.failureWrapper("操作失败！");
		}
	}
	/**
	 * 分配人员处理
	 * @param request
	 * @return
	 */
	@RequestMapping("sqjrSzCzr")
	@ResponseBody
	public HashMap<String, Object> zdjrSzCzr(HttpServletRequest request){
		try {
			String id=request.getParameter("id");
			String ids=request.getParameter("ids");
			String names=request.getParameter("names");
			String fzrxf=URLDecoder.decode(request.getParameter("fzrxf"), "UTF-8");
			if(StringUtil.isNotEmpty(id)){
				DisciplineZdsxSqjr entity=disciplineZdsxSqjrService.get(id);
				entity.setCz_user_ids(ids);
				entity.setCz_user_names(names);
				entity.setFzrxf(fzrxf);
				disciplineZdsxSqjrService.save(entity);
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
	@RequestMapping("sqjrWj")
	@ResponseBody
	public HashMap<String, Object> zdjrWj(HttpServletRequest request){
		try {
			String id=request.getParameter("id");
			if(StringUtil.isNotEmpty(id)){
				DisciplineZdsxSqjr entity=disciplineZdsxSqjrService.get(id);
				entity.setState("6");
				entity.setBljg_time(new Date());
				disciplineZdsxSqjrService.save(entity);
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
			disciplineZdsxSqjrService.del(idArr[i]);
		}
		return JsonWrapper.successWrapper(ids);
	}
}
