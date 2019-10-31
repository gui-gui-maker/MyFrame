package com.lsts.employee.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.employee.bean.Allowancefo;
import com.lsts.employee.dao.AllowancefoDao;




@Service("allowancefoService")
public class AllowancefoService extends EntityManageImpl<Allowancefo,AllowancefoDao> {
	@Autowired
	AllowancefoDao allowancefoDao;

	public Allowancefo getDetailByOp(HttpServletRequest request, String id, String opId) {
		Allowancefo allowancefo = allowancefoDao.getDetailByOp(id,opId);
		return allowancefo;
	}

}
