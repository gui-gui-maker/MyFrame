package com.lsts.relevant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.relevant.bean.RelevantPeople;
import com.lsts.relevant.dao.RelevantPeopleDao;

/**
 * 特种作业人员基本信息业务逻辑对象
 * @ClassName RelevantPeopleService
 * @JDK 1.6
 * @author GaoYa
 * @date 2014-02-13 下午05:10:00
 */
@Service("relevantPeopleService")
public class RelevantPeopleService extends EntityManageImpl<RelevantPeople, RelevantPeopleDao> {

	@Autowired
	private RelevantPeopleDao relevantPeopleDao;

	@Override
	public void save(RelevantPeople entity) throws Exception {
		super.save(entity);
	}
	
	// 删除特种作业人员基本信息
    public void delete(String ids) {
    	relevantPeopleDao.delete(ids);
    }
}
