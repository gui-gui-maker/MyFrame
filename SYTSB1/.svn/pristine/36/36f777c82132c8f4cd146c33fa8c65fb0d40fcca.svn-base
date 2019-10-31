package com.scts.discipline.web;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.base.Factory;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.scts.cspace.log.service.TjyptLogService;
import com.scts.discipline.bean.DisciplineCall;
import com.scts.discipline.service.DisciplineCallService;

import util.FileUtil;
import util.TS_Util;

@Controller
@RequestMapping("disciplineCallAction")
public class DisciplineCallAction extends SpringSupportAction<DisciplineCall, DisciplineCallService>{

	@Autowired
	private DisciplineCallService disciplineCallService;
	@Autowired
	private TjyptLogService tjyptLogService;
	

	@RequestMapping("saveCall")
	@ResponseBody
	public HashMap<String, Object> saveCall(HttpServletRequest request, @RequestBody DisciplineCall entity) throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			HashMap<String, Object> data = disciplineCallService.saveCall(entity);
			
			map.put("data", data);
			map.put("success", true);
			
		} catch (Exception e) {
			if("分机占线中，请先挂机！".equals(e.getMessage())) {
				map.put("msg", "分机占线中，请先挂机！");
			}else if("您没有分配分机号，请联系系统管理员！".equals(e.getMessage())){
				map.put("msg", "您没有分配分机号，请联系系统管理员！");
			}else {
				map.put("msg", "呼叫失败！");
			}
			e.printStackTrace();
			map.put("success", false);
			
		}

		return map;
		
	}
	
	@RequestMapping("shutDownCall")
	@ResponseBody
	public HashMap<String, Object> shutDownCall(HttpServletRequest request, String id) throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			HashMap<String, Object> data = disciplineCallService.shutDownCall(id);
			
			map.put("data", data);
			map.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "挂断失败！");
		}

		return map;
		
	}
	
	@RequestMapping("getJudgeGrade")
	@ResponseBody
	public HashMap<String, Object> getJudgeGrade(HttpServletRequest request, String id) throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			HashMap<String, Object> data = disciplineCallService.getJudgeGrade(id);
			
			map.put("data", data);
			map.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "挂断失败！");
		}

		return map;
		
	}
	
	
	@RequestMapping("saveCallOther")
	@ResponseBody
	public HashMap<String, Object> saveCallOther(HttpServletRequest request, @RequestBody DisciplineCall entity) throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			HashMap<String, Object> data = disciplineCallService.saveCallOther(entity);
			
			map.put("data", data);
			map.put("success", true);
			
		} catch (Exception e) {
			if("其他分机占线中，不能完成转呼！".equals(e.getMessage())) {
				map.put("msg", "其他分机占线中，不能完成转呼！");
			}else {
				map.put("msg", "转呼失败！");
			}
			e.printStackTrace();
			map.put("success", false);
			
		}

		return map;
		
	}
	
	@RequestMapping("downloadById")
	public void downloadByPath(HttpServletRequest request,
			HttpServletResponse response,String name,String id)
			throws Exception {
			try {
					CurrentSessionUser user = SecurityUtil.getSecurityUser();
					
					DisciplineCall call = disciplineCallService.get(id);
					if(call.getPath()!=null) {
						String path = Factory.getWebRoot()+"/"+call.getPath();
						File root = new File(path);
				        if (!root.exists()) {
				            log.info(path + " not exist!");
				        }
				        tjyptLogService.saveLog("呼叫录音", user.getId(), user.getName(), new Date(),
								 id, "下载文件："+path, TS_Util.getIpAddress(request),"","");
						FileUtil.download(response, path,
							name, "");
					}else {
						log.info(id + " : path is null!");
					}

				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
		
	}

	@RequestMapping("queryComCall")
	@ResponseBody
	public HashMap<String, Object> queryComCall(HttpServletRequest request, String mkid, String mtid) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String,Object[]> coms = disciplineCallService.queryComCall(mkid,mtid);
			map.put("data", coms);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	@RequestMapping("saveCallin")
	@ResponseBody
	public HashMap<String, Object> saveCallin(HttpServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			disciplineCallService.saveCallIn();
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
}
