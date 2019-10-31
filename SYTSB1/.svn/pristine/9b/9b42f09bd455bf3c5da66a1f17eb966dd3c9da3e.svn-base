package com.lsts.qualitymanage.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.qualitymanage.bean.TaskAllotFk;
import com.lsts.qualitymanage.dao.TaskAllotFkDao;
import com.lsts.qualitymanage.service.TaskAllotFkManager;




@Controller
@RequestMapping("task/Fk")
public class TaskAllotFkAction extends SpringSupportAction<TaskAllotFk, TaskAllotFkManager> {

    @Autowired
    private TaskAllotFkManager  taskAllotFkManager;
    @Autowired
    private TaskAllotFkDao taskAllotFkDao;
   
   /**
    * 保存反馈信息
    * @param request
    * @param taskAllotFk
    * @return
    */
    @RequestMapping(value = "saveFK")
   	@ResponseBody
       public HashMap<String,Object> saveFK(HttpServletRequest request, @RequestBody TaskAllotFk taskAllotFk){
       	HashMap<String, Object> map = new HashMap<String, Object>();
       		CurrentSessionUser user = SecurityUtil.getSecurityUser();
       		String ids = request.getParameter("ids");
       		Date date = taskAllotFk.getDelay(); //获取延期时间
        	if(date!=null &&!"".equals(date)){ 
        		List list = taskAllotFkDao.Detail(date,ids);
        		if(list.size()> 0){
        			map.put("msg", "只允许延期一次,请重新选择！");
        			map.put("success", false);
        		}else{
        			//保存反馈信息
        			taskAllotFkManager.saveFK(taskAllotFk, user,ids);
        	    	map.put("msg", "保存成功！");
        			map.put("success", true);
        		}
        	}else{
    			//保存反馈信息
    			taskAllotFkManager.saveFK(taskAllotFk, user,ids);
    	    	map.put("msg", "保存成功！");
    			map.put("success", true);
    	}
       	
			return map;
    }
    
    @RequestMapping(value="getDetail")
    @ResponseBody
    public HashMap<String, Object> getDetail(String id) throws Exception{ 
    	List<TaskAllotFk> list = taskAllotFkManager.getList(id);
    	return JsonWrapper.successWrapper("Rows",list);
    }
    
    
    
}