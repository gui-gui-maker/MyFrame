package com.lsts.advicenote.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.advicenote.bean.Affiche;
import com.lsts.advicenote.dao.AfficheDao;

/**
 * 通知书业务逻辑对象
 * @ClassName AdviceNoteService
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-03-24 下午03:43:00
 */
@Service("afficheService")
public class AfficheService extends
		EntityManageImpl<Affiche, AfficheDao> {
	@Autowired
	private AfficheDao afficheDao;
	
	
	
	public HashMap<String, Object> getAffiche()
			throws Exception {
		
		 HashMap<String, Object>  map = new HashMap();
		 
		List list =   afficheDao.createQuery(" from Affiche t where t.userful_time > sysdate and t.flag = '1' and t.affiche_status='1' order by t.created_date desc").list();
		 
		 String returnStr = "" ;
		 
		 if(list.size()>0){

			 for(int i=0;i<list.size();i++){
				 
				 Affiche affiche = (Affiche)list.get(i);
				 
				 returnStr+=affiche.getAffiche_title()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+affiche.getAffiche_content()+"      <br>";
						 
			 }
			 
			 
		 }
		
		 map.put("content", returnStr);
		 map.put("success", true);
		return map;
	
	}
	
	

}
