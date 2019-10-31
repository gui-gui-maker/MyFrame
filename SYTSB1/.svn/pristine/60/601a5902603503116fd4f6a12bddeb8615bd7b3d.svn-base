package com.lsts.advicenote.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.bean.MessageContentMod;

@Repository("messageContentModDao")
public class MessageContentModDao extends EntityDaoImpl<MessageContentMod> {

	public Boolean checkCode(String moduleCode, String id) {
		String hql = "from MessageContentMod m where m.data_status<>'99' and m.module_code=?";
		if(id!=null&&StringUtil.isNotEmpty(id)) {
			hql = "from MessageContentMod m where m.data_status<>'99' and m.module_code=? and m.id <> '"+id+"'";
		}
		
		@SuppressWarnings("unchecked")
		List<Object> list = this.createQuery(hql, moduleCode).list();
		if(list.size()>0) {
			return true;
		}
		return false;
	}
	
	public void del(String ids) {
		String idss = ids.replace(",", "','");
		String hql = "delete MessageContentMod m set m.data_status<>'99' where m.id in ('"+idss+"')";

		this.createQuery(hql).executeUpdate();
		 
	}

	public MessageContentMod getByCode(String moduleCode) {
		String hql = "from MessageContentMod m where m.data_status='0' and m.module_code=?";
		
		@SuppressWarnings("unchecked")
		List<MessageContentMod> list = this.createQuery(hql, moduleCode).list();
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}

}
