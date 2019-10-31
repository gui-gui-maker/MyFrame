package com.lsts.office.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;














import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.lsts.office.bean.TaskFeedback;
import com.lsts.office.bean.WeightyTask;
import com.lsts.office.bean.Ywhbsgz;
import com.lsts.office.bean.YwhbsgzFk;
import com.lsts.office.dao.YwhbsgzDao;
import com.lsts.office.dao.Ywhbsgz_fkDao;




@Service("ywhbsgz_fkManager")
public class Ywhbsgz_fkManager extends EntityManageImpl<YwhbsgzFk,Ywhbsgz_fkDao>{
    @Autowired
    Ywhbsgz_fkDao ywhbsgz_fkDao;
    @Autowired
    YwhbsgzDao ywhbsgzDao;
    
    public final static String BG_RWZT_WKS="WKS"; //未开始
    public final static String BG_RWZT_YJS="JXZ"; //进行中
    public final static String BG_RWZT_YWC="YWC"; //已完成
    public final static String BG_RWZT_WWC="WWC"; //未完成
    
    public final static String BG_FK_WWC="WWC"; //未完成
    public final static String BG_FK_JXZ="JXZ"; //进行中
    public final static String BG_FK_YWC="YWC"; //已完成
    
    public List<YwhbsgzFk> getList(String onetompid) throws Exception{
    	return ywhbsgz_fkDao.findBy("ywhbsgz.id", onetompid);
    }
    
   
  
    public void saveWei(YwhbsgzFk ywhbsgzFk){
    	Ywhbsgz ywhbsgz = ywhbsgzDao.get(ywhbsgzFk.getYwhbsgz().getId());
    	ywhbsgzFk.setYwhbsgz(ywhbsgz);
    	//保存反馈信息
    	ywhbsgz_fkDao.save(ywhbsgzFk);
    	
    	if(ywhbsgzFk.getStatus1().equals(BG_FK_WWC)){
    		ywhbsgz.setStatus(WeightyTaskManager.BG_RWZT_WWC);
    	}
    	if(ywhbsgzFk.getStatus1().equals(BG_FK_YWC)){
    		ywhbsgz.setStatus(WeightyTaskManager.BG_RWZT_YWC);
    	}
    	ywhbsgz.setPerformance( ywhbsgzFk.getFeedbackRemark());
    	ywhbsgz.setUnfinishedReason(ywhbsgzFk.getNotCompleteReason());
    	
    	//回写任务状态
    	ywhbsgzDao.save(ywhbsgz);
    }
    //微信反馈
    public void saveWXFK(Ywhbsgz ywhbsgz){
    	YwhbsgzFk ywhbsgzFk = new YwhbsgzFk();
    	ywhbsgzFk.setYwhbsgz(ywhbsgz);
    	ywhbsgzFk.setStatus1(ywhbsgz.getStatus());
    	ywhbsgzFk.setFeedbackRemark(ywhbsgz.getPerformance());
    	ywhbsgzFk.setNotCompleteReason(ywhbsgz.getUnfinishedReason());
    	//保存任务最新信息
    	ywhbsgzDao.save(ywhbsgz);
    	//保存任务反馈信息
    	ywhbsgz_fkDao.save(ywhbsgzFk);
    }
    
}
