package com.lsts.advicenote.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.advicenote.bean.MessageContentCon;
import com.lsts.advicenote.bean.MessageContentMod;

@Repository("messageContentConDao")
public class MessageContentConDao extends EntityDaoImpl<MessageContentCon> {

	public MessageContentCon getByModuleId(String moduleId) {
		String hql = "from MessageContentCon m where m.data_status<>'99' and m.fk_module_id=?";
		
		@SuppressWarnings("unchecked")
		List<MessageContentCon> list = this.createQuery(hql, moduleId).list();
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}

}
