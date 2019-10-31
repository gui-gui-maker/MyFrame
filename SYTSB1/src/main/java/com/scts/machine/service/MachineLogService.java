package com.scts.machine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.scts.machine.bean.MachineLog;
import com.scts.machine.dao.MachineLogDao;


@Service("machineLogService")
public class MachineLogService extends EntityManageImpl<MachineLog, MachineLogDao> {
	@Autowired
	private MachineLogDao machineLogDao;

}
