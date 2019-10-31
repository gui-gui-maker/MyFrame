package com.scts.discipline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.scts.discipline.bean.DisciplineUser;
import com.scts.discipline.dao.DisciplineUserDao;

@Service("disciplineUserService")
public class DisciplineUserService extends EntityManageImpl<DisciplineUser, DisciplineUserDao> {

	@Autowired
	private DisciplineUserDao disciplineUserDao;

	public void del(String ids) {
		
		disciplineUserDao.del(ids);
		
	}
	
}
