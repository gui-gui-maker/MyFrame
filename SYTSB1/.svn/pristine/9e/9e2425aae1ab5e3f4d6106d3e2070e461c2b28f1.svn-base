package com.scts.machine.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.scts.machine.bean.MachineLog;

/**
 * 系统日志数据处理对象
 * @ClassName SysLogDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2018-05-18 上午09:52:00
 */
@Repository("machineLogDao")
public class MachineLogDao extends EntityDaoImpl<MachineLog> {
	
	// 根据业务环节名称获取智能一体机上业务流程待提交的系统操作日志
	@SuppressWarnings("unchecked")
	public List<MachineLog> getLogsByAction(String op_action) {
		List<MachineLog> list = new ArrayList<MachineLog>();
		String hql = "from MachineLog t where t.op_action = ? and (t.flow_status='0' or t.flow_status is null)";
		list = this.createQuery(hql, op_action).list();
		return list;
	}
	
	// 根据业务环节名称获取智能一体机上业务流程待提交的系统操作日志
	@SuppressWarnings("unchecked")
	public List<MachineLog> getLogsByInfoId(String business_id, String op_action) {
		List<MachineLog> list = new ArrayList<MachineLog>();
		String hql = "from MachineLog t where t.business_id = ? and t.op_action = ? and t.flow_status='1'";
		list = this.createQuery(hql, business_id, op_action).list();
		return list;
	}
}
