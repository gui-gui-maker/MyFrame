package com.lsts.advicenote.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.task.bean.ScheduleJob;
import com.khnt.task.service.ScheduleJobManager;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.bean.MessageContentCon;
import com.lsts.advicenote.dao.MessageContentConDao;

@Service("messageContentConService")
public class MessageContentConService extends EntityManageImpl<MessageContentCon, MessageContentConDao> {

	@Autowired
	private MessageContentConDao contentConDao;
	@Autowired
	private ScheduleJobManager scheduleJobManager;
	
	
	
	@Override
	public void save(MessageContentCon entity) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		
		if(entity.getId()==null||StringUtil.isEmpty(entity.getId())) {
			entity.setCreate_op_id(user.getId());
			entity.setCreate_op(user.getName());
			entity.setCreate_time(new Date());
			
			entity.setLast_update_op_id(user.getId());
			entity.setLast_update_op(user.getName());
			entity.setLast_update_time(new Date());
		}else {
			entity.setLast_update_op_id(user.getId());
			entity.setLast_update_op(user.getName());
			entity.setLast_update_time(new Date());
			
		}
		String date="";
		/*if(entity.getSend_time().equals("3")){
			 date=analysisDate(entity.getPreview_time(),"1","1");
			
		}
		if(entity.getSend_time().equals("2")){
			 date=analysisDate(entity.getLate_time(),"1","1");
		}
		//保存定时任务
		ScheduleJob job=new ScheduleJob();
		job.setJobStatus("1");
		job.setJobName("测试");//任务名称
		job.setJobGroup("message");//任务分组
		job.setBeanClass("测试");//任务对应类
		job.setSpringId("测试");//对应SpringBeanId
		job.setMethodName("测试");//调用方法
		job.setCronExpression(date);
		scheduleJobManager.save(job);*/
		super.save(entity);
	}




	public void del(String ids) {
		String idss = ids.replace(",", "','");
		String hql = "delete MessageContentCon c where c.id in ('"+idss+"')";
		
		contentConDao.createQuery(hql).executeUpdate();
	}
	//定时、延迟任务
	public static String analysisDate(Date dates,String type,String types){
		String time="";
		 SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间
		try {
			String date= sDateFormat.format(dates);
			String ss=date.substring(17, 19);
			String mm=date.substring(14, 16);
			String hh=date.substring(11, 13);
			String dd=date.substring(8, 10);
			String yf=date.substring(5, 7);
			
			if(!ss.equals("00")){//秒
				time=time+ss+" ";
			}else{
				time=time+"0 ";
			}
			if(!mm.equals("00")){//分
				time=time+mm+" ";
			}else{
				time=time+"0 ";
			}
			if(!hh.equals("00")){//时
				time=time+hh+" ";
			}else{
				time=time+"0 ";
			}

			
			if(type.equals("1")){//定时任务
				if(types.equals("1")){//每天执行
					time=time+"* * ?";
				}
			}else if(type.equals("2")){//延迟任务
				if(!dd.equals("00")){//天
					time=time+dd+" ";
				}else{
					time=time+"0 ";
				}
				if(!yf.equals("00")){//月份
					time=time+yf+" ";
				}else{
					time=time+"0 ";
				}
				time=time+"?";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}
	
	
	
	
}
