package com.scts.discipline.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.advicenote.service.MessageService;
import com.scts.discipline.bean.DisciplineMsg;
import com.scts.discipline.dao.DisciplineMsgDao;

@Service("disciplineMsgService")
public class DisciplineMsgService extends EntityManageImpl<DisciplineMsg, DisciplineMsgDao> {

	@Autowired
	private DisciplineMsgDao disciplineMsgDao;
	@Autowired
	private MessageService messageService;


	public void save(HttpServletRequest request, DisciplineMsg entity) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		
		entity.setCreate_date(new Date());
		entity.setCreate_op(user.getName());
		entity.setCreate_op_id(user.getId());
		disciplineMsgDao.save(entity);
		
		if(entity.getContent()!=null&&entity.getPhone_number()!=null) {
			messageService.sendMoMsg(request, entity.getId(), entity.getContent(), entity.getPhone_number());
		}
		
	}
	
	
	
}
