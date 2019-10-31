package com.lsts.office.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.lsts.office.bean.TaskFeedback;
import com.lsts.office.bean.WeightyTask;
import com.lsts.office.bean.Ywhbsgz;
import com.lsts.office.bean.YwhbsgzFk;
import com.lsts.office.dao.TaskFeedbackDao;
import com.lsts.office.dao.WeightyTaskDao;



@Service("taskFeedback")
public class TaskFeedbackManager extends EntityManageImpl<TaskFeedback,TaskFeedbackDao>{
    @Autowired
    TaskFeedbackDao taskFeedbackDao;
    @Autowired
    private WeightyTaskDao weightyTaskDao;
    
    public final static String BG_FK_WWC="WWC"; //未完成
    public final static String BG_FK_JXZ="JXZ"; //进行中
    public final static String BG_FK_YWC="YWC"; //已完成
    
    /**
     * 保存反馈信息
     * @param weightyDep
     * @param ids
     */
    public void saveWei(TaskFeedback taskFeedback){
    	WeightyTask weightyTask = weightyTaskDao.get(taskFeedback.getWeightyTask().getId());
    	taskFeedback.setWeightyTask(weightyTask);
    	
    	//保存反馈信息
    	taskFeedbackDao.save(taskFeedback);
    	
    	if(taskFeedback.getStatus().equals(BG_FK_WWC)){
    		weightyTask.setStatus(WeightyTaskManager.BG_RWZT_WWC);
    	}
    	if(taskFeedback.getStatus().equals(BG_FK_YWC)){
    		weightyTask.setStatus(WeightyTaskManager.BG_RWZT_YWC);
    	}
    	weightyTask.setPerformance( taskFeedback.getFeedbackRemark());
    	weightyTask.setUnfinishedReason(taskFeedback.getUnfinishedTask());
    	//回写任务状态及任务完成情况
    	weightyTaskDao.save(weightyTask);
    }
    
    
    public List<TaskFeedback> getList(String onetompid) throws Exception{
    	return taskFeedbackDao.findBy("weightyTask.id", onetompid);
    }
    
    
    /**
     * 级联删除**删除主表内容时子表内容一同删除
     * @param ids
     */
    public void delete(String ids ){
    	for(String id: ids.split(",")){
    		TaskFeedback taskFeedback = taskFeedbackDao.get(id);
    		taskFeedbackDao.getHibernateTemplate().delete(taskFeedback);
    		
    	}
    }
    
  //微信反馈
    public void saveWXFK(WeightyTask weightyTask){
    	TaskFeedback taskFeedback = new TaskFeedback();
    	taskFeedback.setWeightyTask(weightyTask);
    	taskFeedback.setStatus(weightyTask.getStatus());
    	taskFeedback.setFeedbackRemark(weightyTask.getPerformance());
    	taskFeedback.setUnfinishedTask(weightyTask.getUnfinishedReason());
    	//保存任务最新信息
    	weightyTaskDao.save(weightyTask);
    	//保存任务反馈信息
    	taskFeedbackDao.save(taskFeedback);
    }
}
