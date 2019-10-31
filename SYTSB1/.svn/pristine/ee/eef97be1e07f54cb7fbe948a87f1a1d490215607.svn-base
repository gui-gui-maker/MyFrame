package com.lsts.finance.service;

import java.text.ParseException;
import java.util.List;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.finance.bean.CwYijian;
import com.lsts.finance.dao.CwYijianDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("Tjy2CwYijianManager")
public class CwYijianManager extends EntityManageImpl<CwYijian,CwYijianDao>{
    @Autowired
    CwYijianDao cwYijianDao;
    
    
    public List<CwYijian> getyijian(String id){
    	return cwYijianDao.getyijian(id);
    }
    
public String getmid(String id) throws ParseException {
		
		List getmids =	cwYijianDao.getmid(id);
		
		Object user1[] = null;
		String mids=null;
		if(getmids.size()==0){
			mids=null;
		}else{
		user1 = (Object[]) getmids.get(0);
		
		 mids =  user1[0].toString();
}
		return mids;
	}
}
