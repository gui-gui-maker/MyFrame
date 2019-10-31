package com.lsts.mobileapp.input.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.lsts.mobileapp.input.bean.TzsbRecordLog;
import com.lsts.mobileapp.input.dao.TzsbRecordLogDao;

@Service("tzsbRecordLogService")
public class TzsbRecordLogService extends EntityManageImpl<TzsbRecordLog, TzsbRecordLogDao> {

	@Autowired
	private TzsbRecordLogDao recordLogDao;
	
	/**
	 * 设值
	 * @param fk_inspection_info_id
	 * @param op_name
	 * @param op_conclusion
	 * @param enter_op_id
	 * @param enter_op
	 * @param op_time
	 * @param remoteAddr
	 * @param op_status
	 * @throws RuntimeException
	 */
	public void setLogs(String fk_inspection_info_id, String op_name,
			String op_remark, String enter_op_id, 
			String enter_op, Date op_time, String remoteAddr,
			String op_status, String nextId, String nextName) throws KhntException {
    	
		TzsbRecordLog sysLog = new TzsbRecordLog();
		
		sysLog.setBusiness_id(fk_inspection_info_id);
		sysLog.setOp_action(op_name);
		sysLog.setOp_remark(op_remark);
		sysLog.setOp_user_id(enter_op_id);
		sysLog.setOp_user_name(enter_op);
		sysLog.setOp_time(op_time);
		sysLog.setOp_ip(remoteAddr);
		sysLog.setOp_status(op_status);//
		sysLog.setNext_id(nextId);
		sysLog.setNext_name(nextName);
		
		recordLogDao.save(sysLog);
	    	
	    }
	
}
