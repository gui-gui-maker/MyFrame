package com.lsts.log.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.lsts.log.bean.SysLog;
import com.lsts.log.dao.SysLogDao;

@Service("sysLogService")
public class SysLogService extends EntityManageImpl<SysLog, SysLogDao> {
	@Autowired
	private SysLogDao sysLogDao;

	public void insertSysLog(SysLog sysLog) {
		System.out.println("操作人ID：" + sysLog.getOp_user_id() + "，操作人姓名：" + sysLog.getOp_user_name());
	}

	public SysLogDao getSysLogDao() {
		return sysLogDao;
	}

	public void setSysLogDao(SysLogDao sysLogDao) {
		this.sysLogDao = sysLogDao;
	}

	public void setLogs(String fk_inspection_info_id, String op_name, 
			String op_conclusion, String enter_op_id,
			String enter_op, Date op_time, String remoteAddr) throws Exception {
		SysLog sysLog = new SysLog();
		sysLog.setBusiness_id(fk_inspection_info_id);
		sysLog.setOp_action(op_name);
		sysLog.setOp_remark(op_conclusion);
		sysLog.setOp_user_id(enter_op_id);
		sysLog.setOp_user_name(enter_op);
		sysLog.setOp_time(op_time);
		sysLog.setOp_ip(remoteAddr);
		sysLogDao.save(sysLog);
	}
	public  void setLogs(String fk_inspection_info_id, String op_name, 
			String op_conclusion, String enter_op_id, String enter_op, 
			Date op_time, String remoteAddr,String op_status) throws KhntException {
    	
    	SysLog  sysLog = new SysLog();
		
		sysLog.setBusiness_id(fk_inspection_info_id);
		sysLog.setOp_action(op_name);
		sysLog.setOp_remark(op_conclusion);
		sysLog.setOp_user_id(enter_op_id);
		sysLog.setOp_user_name(enter_op);
		sysLog.setOp_time(op_time);
		sysLog.setOp_ip(remoteAddr);
		sysLog.setOp_status(op_status);
		sysLogDao.save(sysLog);
	    	
    }
	
	public List<SysLog> getReportLogs(String info_id, String op_action) {
		return sysLogDao.getReportLogs(info_id, op_action);
	}
}
