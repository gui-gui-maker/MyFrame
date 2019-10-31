package com.lsts.office.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.office.bean.TaskFeedback;
import com.lsts.office.bean.WeightyTask;
import com.lsts.office.bean.Ywhbsgz;
import com.lsts.office.service.TaskFeedbackManager;
import com.lsts.office.service.WeightyTaskManager;


@Controller
@RequestMapping("task/Feedback")
public class TaskFeedbackAction extends SpringSupportAction<TaskFeedback, TaskFeedbackManager> {

    @Autowired
    private TaskFeedbackManager  taskFeedbackManager;
    @Autowired
    private WeightyTaskManager  weightyTaskManager;

    
    /**
     * 保存反馈信息
     * @param request
     * @param weightyDep
     * @return
     */
    @RequestMapping(value = "saveWei")
   	@ResponseBody
       public HashMap<String,Object> saveWei(HttpServletRequest request, TaskFeedback taskFeedback){
       	HashMap<String, Object> map = new HashMap<String, Object>();
       	try {
       		/*CurrentSessionUser user = SecurityUtil.getSecurityUser();*/
       		taskFeedbackManager.saveWei(taskFeedback);
       	} catch (Exception e) {
   			e.printStackTrace();
   		}
       	
       	map.put("success", true);
   		return map;
       }
    
    
    
    
    @RequestMapping(value="fdetail")
    @ResponseBody
    public HashMap<String, Object> getDetail(String id) throws Exception{ 
    	List<TaskFeedback> list = taskFeedbackManager.getList(id);
    	return JsonWrapper.successWrapper("Rows",list);
    }
    
    @RequestMapping(value = "getDelete")
    @ResponseBody
	public HashMap<String, Object> delete(String ids) throws Exception {
		taskFeedbackManager.delete(ids);
		return JsonWrapper.successWrapper();
    }
    
    /**
     * 微信保存反馈信息
     * @param request
     * @param weightyDep
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value = "saveWXFK")
   	@ResponseBody
       public HashMap<String,Object> saveWXFK(HttpServletRequest request) throws UnsupportedEncodingException{
       	HashMap<String, Object> map = new HashMap<String, Object>();
       	String id = request.getParameter("id");
       	String status = request.getParameter("status");
       	String unfinishedTask1 = request.getParameter("unfinishedTask");
       	String unfinishedTask = URLDecoder.decode(unfinishedTask1,"utf-8");
       	String feedbackRemark1 = request.getParameter("feedbackRemark");
       	String feedbackRemark = URLDecoder.decode(feedbackRemark1,"utf-8");
       	//get the object of WeightyTask
       	WeightyTask weightyTask = weightyTaskManager.get(id);
       	weightyTask.setStatus(status);
       	if(status.equals("WWC")){
       		weightyTask.setUnfinishedReason(unfinishedTask);
       	}else{
       		weightyTask.setPerformance(feedbackRemark);
       	}
       	try {
       		taskFeedbackManager.saveWXFK(weightyTask);
       	} catch (Exception e) {
   			e.printStackTrace();
   		}
       	map.put("success", true);
   		return map;
       }
}