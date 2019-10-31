package com.scts.discipline.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.scts.discipline.bean.DisciplineUser;

@Repository("disciplineUserDao")
public class DisciplineUserDao extends EntityDaoImpl<DisciplineUser> {

	public void del(String ids) {
		
		String idss = ids.replace(",", "','");
		String hql = "update DisciplineUser u set u.data_status='99' where u.id in ('"+idss+"')";
		this.createQuery(hql).executeUpdate();
		
	}

	public String getPhoneByUser(String userId) {

		String hql = "from DisciplineUser u where u.user_id = ? and u.data_status<>'99'";
		List<DisciplineUser> list = this.createQuery(hql, userId).list();
		if(list.size()>0&&list.get(0)!=null) {
			return list.get(0).getPhone();
		}
		
		return null;
	}
	
}
