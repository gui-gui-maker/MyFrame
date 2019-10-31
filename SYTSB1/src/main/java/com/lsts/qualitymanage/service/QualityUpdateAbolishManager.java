package com.lsts.qualitymanage.service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.qualitymanage.bean.QualityUpdateAbolish;
import com.lsts.qualitymanage.dao.QualityUpdateAbolishDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("qualityUpdateAbolish")
public class QualityUpdateAbolishManager extends EntityManageImpl<QualityUpdateAbolish,QualityUpdateAbolishDao>{
    @Autowired
    QualityUpdateAbolishDao qualityUpdateAbolishDao;
    
    
	public final static String ZL_XGSQ_FC = "8";//已废除

}
