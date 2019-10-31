package com.lsts.hall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.hall.bean.ReportHallInfo;
import com.lsts.hall.dao.ReportHallInfoDao;

/**
 * 移动检验业务任务接收明细业务逻辑对象
 * 
 * @ClassName reportHallInfoService
 * @JDK 1.6
 * @author GaoYa
 * @date 2016-04-18 下午03:51:00
 */
@Service("reportHallInfoService")
public class ReportHallInfoService extends
		EntityManageImpl<ReportHallInfo, ReportHallInfoDao> {
	@Autowired
	private ReportHallInfoDao reportHallInfoDao;

	
}