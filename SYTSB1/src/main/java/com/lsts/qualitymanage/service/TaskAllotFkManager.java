package com.lsts.qualitymanage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.lsts.qualitymanage.bean.TaskAllot;
import com.lsts.qualitymanage.bean.TaskAllotFk;
import com.lsts.qualitymanage.dao.TaskAllotDao;
import com.lsts.qualitymanage.dao.TaskAllotFkDao;



@Service("taskAllotFkManager")
public class TaskAllotFkManager extends EntityManageImpl<TaskAllotFk,TaskAllotFkDao>{
    @Autowired
    TaskAllotFkDao taskAllotFkDao;
    
    @Autowired
   private TaskAllotDao taskAllotDao;
    
    
    public final static String ZLGL_RWXF_WWC="WWC"; //未完成
    public final static String ZLGL_RWXF_YQ="YQ"; //延期
    public final static String ZLGL_RWXF_YWC="YWC"; //已完成
    
    /**
     * 保存反馈信息
     * @param TaskAllotFk
     * @param ids
     */
    public HashMap<String, Object> saveFK(TaskAllotFk taskAllotFk,CurrentSessionUser user,String ids){
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	TaskAllot taskAllot = taskAllotDao.get(ids);
    	taskAllotFk.setTaskAllot(taskAllot);
    	taskAllotFkDao.save(taskAllotFk);
		
    	if(taskAllotFk.getStatus().equals(ZLGL_RWXF_WWC)){
    		taskAllot.setStatus(TaskAllotManager.ZLGL_RWXF_WWC);
    	}
    	if(taskAllotFk.getStatus().equals(ZLGL_RWXF_YWC)){
    		taskAllot.setStatus(TaskAllotManager.ZLGL_RWXF_YWC);
    		taskAllot.setItemTime(new Date());
    	}
    	//回写任务状态
    	if(taskAllotFk.getDelay() !=null || !"".equals(taskAllotFk.getDelay())){
    		taskAllot.setDelay(taskAllotFk.getDelay());
    	}
    	taskAllotDao.save(taskAllot);
		return map;
    }
    
    public List<TaskAllotFk> getList(String onetompid) throws Exception{
    	return taskAllotFkDao.findBy("taskAllot.id", onetompid);
    }
}
