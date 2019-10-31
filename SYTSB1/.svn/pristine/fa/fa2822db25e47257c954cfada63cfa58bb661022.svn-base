package com.fwxm.dining.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.dining.bean.DiningWindow;
import com.fwxm.dining.dao.DiningWindowDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rbac.impl.bean.Employee;
@Service("diningWindowService")
public class DiningWindowService extends EntityManageImpl<DiningWindow, DiningWindowDao>{
	@Autowired
	private DiningWindowDao diningWindowDao;
	/**
	 * 食堂窗口开关
	 * @param windowStatus 开关
	 * @param id userId
	 * @return
	 */
	public DiningWindow updatewindowStatus(int windowStatus, String id) {
		DiningWindow diningWindow = (DiningWindow) diningWindowDao.createQuery("from DiningWindow where employeeId=?",id).uniqueResult();
		diningWindow.setWindowStatus(windowStatus);
		
		return diningWindow;//diningWindowDao.createQuery("update DiningWindow set windowStatus=? where employeeId=?", windowStatus,id).executeUpdate();
	}
	public DiningWindow getDiningWindowByEmployee(Employee e) {
		return (DiningWindow) diningWindowDao.createQuery("from DiningWindow where employeeId=?", e.getId()).uniqueResult();
	}

}
