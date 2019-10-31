package com.lsts.humanresources.web;

import com.alibaba.fastjson.JSONObject;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.humanresources.bean.WorkExperience;
import com.lsts.humanresources.service.WorkExperienceManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("workExperienceAction")
public class WorkExperienceAction extends SpringSupportAction<WorkExperience, WorkExperienceManager> {

    @Autowired
    private WorkExperienceManager  workExperienceManager;
    //查询员工工作经历
  	@RequestMapping(value = "detailWork")
  	@ResponseBody
  	public HashMap<String, Object> detailWork(String id,HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try{
  			List<WorkExperience> list= workExperienceManager.getByEmpId(id);
  			
  			wrapper.put("Rows", list);
  			wrapper.put("success", true);
  		}catch(Exception e){
  			log.error("保存单位信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "保存单位信息出错！");
  			e.printStackTrace();	
  		}
  		return wrapper;
  	}
}