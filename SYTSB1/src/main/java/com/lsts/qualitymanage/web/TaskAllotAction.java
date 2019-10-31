package com.lsts.qualitymanage.web;

import java.util.HashMap;
import java.util.Map;

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
import com.lsts.qualitymanage.bean.TaskAllot;
import com.lsts.qualitymanage.dao.TaskAllotDao;
import com.lsts.qualitymanage.service.TaskAllotManager;


@Controller
@RequestMapping("taskAllot/allot")
public class TaskAllotAction extends SpringSupportAction<TaskAllot, TaskAllotManager> {

    @Autowired
    private TaskAllotManager  taskAllotManager;
	@Autowired
    private TaskAllotDao taskAllotDao;
    
	
	
	
	
	/**
	 * 工作任务通过
	 * @param id
	 * @param c
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "audit")
	@ResponseBody
	public HashMap<String, Object> audit(HttpServletRequest request,String id,String verify) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (id.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			TaskAllot taskAllot = taskAllotManager.get(id);
			if(taskAllot==null){
				map.put("success", false);
				map.put("msg", "数据获取失败！");
			} else {
				if(verify.equals("1")){
					taskAllot.setStatus(TaskAllotManager.ZLGL_RWXF_WKS);//未开始
					taskAllotManager.save(taskAllot);
		   			map.put("success", true);
		   			taskAllotManager.TaskPush(request,taskAllot.getId());//审核通过后发送短信和微信
		   		}else{
		   			taskAllot.setStatus(TaskAllotManager.ZLGL_RWXF_BTG);//审核不通过
		   			taskAllotManager.save(taskAllot);
		   			map.put("success", true);
		   		}
				map.put("success", true);
			}
		}
		return map;
	}
	
	
	
	
	
    /** 
     * 提交
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "submit")
	@ResponseBody
	public HashMap<String, Object> submit(HttpServletRequest request,String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (id.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			TaskAllot taskAllot = taskAllotManager.get(id);
			if(taskAllot!=null){
				taskAllotManager.doLctj(request,taskAllot);
				map.put("success", true);
			} else {
				map.put("success", false);
				map.put("msg", "数据获取失败！");
			}
		}
		return map;
	}
	
	
	/**
	 * 已接收任务并更新状态
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getTask")
	@ResponseBody
	public Map<String, Object> getTask(String id) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (id.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			TaskAllot taskAllot = taskAllotManager.get(id);
			if(taskAllot!=null){
				taskAllotManager.getTask(taskAllot);
				map.put("success", true);
				
			} else {
				map.put("success", false);
				map.put("msg", "数据获取失败！");
			}
		}
		return map;
		
	}
	
	/**
	 * 完成任务并更新状态
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getFinish")
	@ResponseBody
	public Map<String, Object> getFinish(String ids,TaskAllot taskAllot) throws Exception{
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		taskAllotManager.getFinish(ids,taskAllot, user);
		taskAllot = taskAllotDao.get(ids);
		taskAllot.setStatus(TaskAllotManager.ZLGL_RWXF_YWC);
		taskAllotManager.save(taskAllot);
		return JsonWrapper.successWrapper();
		
	}
	
	
	/**
	 * 保存
	 * @param request
	 * @param taskAllot
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveTask")
	@ResponseBody
	public HashMap<String, Object> saveTask(HttpServletRequest request, @RequestBody TaskAllot taskAllot) throws Exception{
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		taskAllotManager.saveTask(taskAllot, user);
		String SaveAndSub = request.getParameter("SaveAndSub");
		if(SaveAndSub!=null||!"".equals(SaveAndSub)){
			if(SaveAndSub.equals("2")){
				String id =	taskAllot.getId();
					submit(request,id);
			}
		}
		return JsonWrapper.successWrapper();
		
	}
	
	/**
	 * 审核时保存
	 * @param request
	 * @param taskAllot
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "save1")
	@ResponseBody
	public HashMap<String, Object> save1(HttpServletRequest request, @RequestBody TaskAllot taskAllot) throws Exception{
		taskAllotManager.save(taskAllot);
		return JsonWrapper.successWrapper();
		
	}
	
	/**
	 * 删除
	 * */
	@Override
	public HashMap<String, Object> delete(String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		TaskAllot taskAllot = taskAllotManager.get(ids);
			if(taskAllot.getStatus().equals(TaskAllotManager.ZLGL_RWXF_WKS)){
				map.put("msg", "此条数据已改变不可删除！");
				map.put("success", false);
			}else{
				taskAllotManager.delete(ids);
				map.put("msg", "数据删除成功！");
				map.put("success", true);
			}
		
		return map;
	}
	
}