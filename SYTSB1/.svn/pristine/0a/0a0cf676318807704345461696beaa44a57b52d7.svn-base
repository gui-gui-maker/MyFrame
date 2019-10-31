package com.lsts.log.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.log.bean.SysDzyzLog;
import com.lsts.log.dao.SysDzyzLogDao;

/**
 * 系统电子印章日志业务逻辑对象
 * @ClassName SysDzyzLogService
 * @JDK 1.7
 * @author GaoYa
 * @date 2018-03-26 下午03:06:00
 */
@Service("sysDzyzLogService")
public class SysDzyzLogService extends EntityManageImpl<SysDzyzLog, SysDzyzLogDao> {
	@Autowired
	private SysDzyzLogDao sysDzyzLogDao;

	public void insertSysDzyzLog(SysDzyzLog sysDzyzLog) {
		System.out.println("操作人ID：" + sysDzyzLog.getOp_user_id() + "，操作人姓名：" + sysDzyzLog.getOp_user_name());
	}

	public SysDzyzLogDao getSysDzyzLogDao() {
		return sysDzyzLogDao;
	}

	public void setSysDzyzLogDao(SysDzyzLogDao sysDzyzLogDao) {
		this.sysDzyzLogDao = sysDzyzLogDao;
	}

	public void setDzyzLogs(String fk_inspection_info_id, String op_name, String op_conclusion, String enter_op_id,
			String enter_op, Date op_time, String remoteAddr) throws Exception {
		SysDzyzLog sysDzyzLog = new SysDzyzLog();
		sysDzyzLog.setBusiness_id(fk_inspection_info_id);
		sysDzyzLog.setOp_action(op_name);
		sysDzyzLog.setOp_remark(op_conclusion);
		sysDzyzLog.setOp_user_id(enter_op_id);
		sysDzyzLog.setOp_user_name(enter_op);
		sysDzyzLog.setOp_time(op_time);
		sysDzyzLog.setOp_ip(remoteAddr);
		sysDzyzLogDao.save(sysDzyzLog);
	}
}
