package com.lsts.expert.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.lsts.expert.bean.ExpertPre;
import com.lsts.expert.dao.ExpertPreDao;

@Service("expertPreService")
public class ExpertPreService extends EntityManageImpl<ExpertPre, ExpertPreDao> {

	@Autowired
	private ExpertPreDao expertPreDao;

	
	
	
	@Override
	public void save(ExpertPre entity) throws Exception {
		
		if(expertPreDao.hasSet(entity.getPerson_type(),entity.getPerson_id())){
			throw new KhntException("保存失败，已经设置！");
		}
		super.save(entity);
	}




	public void del(String ids) {
		String[] idss = ids.split(",");
		for (int i = 0; i < idss.length; i++) {
			ExpertPre expertPre = expertPreDao.get(idss[i]);
			expertPre.setData_status("99");
			expertPre.setDel_date(new Date());
			expertPreDao.save(expertPre);
		}
		
	}
	
}
