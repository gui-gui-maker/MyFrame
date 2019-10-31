package com.scts.cspace.log.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.scts.cspace.log.bean.TjyptLog;
import com.scts.cspace.log.dao.TjyptLogDao;

@Service("tjyptLogService")
public class TjyptLogService extends EntityManageImpl<TjyptLog, TjyptLogDao> {

	@Autowired
	private TjyptLogDao tjyptLogDao;
	
	public void saveLog(String target_type,String op_id,String op_name,
			Date op_time,String target_id,String event,String ip,String op_space,String target_space){
			TjyptLog log =  new TjyptLog();
			log.setEvent(event);
			log.setOp_id(op_id);
			log.setOp_name(op_name);
			log.setOp_time(op_time);
			log.setTarget_id(target_id);
			log.setTarget_type(target_type);
			log.setIp(ip);
			log.setOp_space(op_space);
			log.setTarget_space(target_space);
			//1 上传文件 2 下载文件 3 预览文件 4 分享文件 5 删除文件 6 恢复文件
			if(event.startsWith("上传文件")){
				log.setOp_type("1");
			}else if(event.startsWith("下载文件")){
				log.setOp_type("2");
			}else if(event.startsWith("预览文件")){
				log.setOp_type("3");
			}else if(event.startsWith("分享文件")){
				log.setOp_type("4");
			}else if(event.startsWith("删除文件")){
				log.setOp_type("5");
			}else if(event.startsWith("恢复文件")){
				log.setOp_type("6");
			}
			tjyptLogDao.save(log);
		
	}
	
	
}
