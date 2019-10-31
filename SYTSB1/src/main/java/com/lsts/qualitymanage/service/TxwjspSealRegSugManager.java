package com.lsts.qualitymanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.qualitymanage.bean.TxwjspSealRegSug;
import com.lsts.qualitymanage.dao.TxwjspSealRegSugDao;


@Service("TxwjspSealRegSugManager")
public class TxwjspSealRegSugManager extends EntityManageImpl<TxwjspSealRegSug,TxwjspSealRegSugDao>{
    @Autowired
    TxwjspSealRegSugDao txwjspSealRegSugDao;
    public void saves(TxwjspSealRegSug txwjspSealRegSug){
    	txwjspSealRegSugDao.save(txwjspSealRegSug);
    	
    }
    
    public Object getAllByBusId(String id) {
		List<TxwjspSealRegSug> list = txwjspSealRegSugDao.getAllByBusId(id);
		return list;
	}
}
