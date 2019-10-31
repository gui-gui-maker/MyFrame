package com.fwxm.supplies.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.supplies.bean.Zdbq;
import com.fwxm.supplies.dao.ZdbqDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;

@Service("zdbqManager")
public class ZdbqManager extends EntityManageImpl<Zdbq, ZdbqDao>{
	@Autowired
	ZdbqDao zdbqDao; 
	 public void delText(String text,String org,String type,CurrentSessionUser curUser) throws Exception{
		List<Zdbq> list= zdbqDao.getDelText(text, org, type);
		for (Zdbq zdbq : list) {
			this.deleteBusiness(zdbq.getId());
		}
		//添加
		Zdbq z=new Zdbq();
		z.setText(text);
		z.setCreateOrgId(curUser.getDepartment().getId());
		z.setCreateOrgName(curUser.getDepartment().getOrgName());
		z.setCreateTime(new Date());
		z.setCreateunitId(curUser.getUnit().getId());
		z.setCreateunitName(curUser.getUnit().getOrgName());
		z.setCreateUserId(curUser.getId());
		z.setCreateUserName(curUser.getUserName());
		z.setType(type);
		this.save(z);
		
	 }
	 

		public List<Zdbq> getZdbqList(String org,String type){
			 return zdbqDao.getZdbqList(org, type);
		}
}
