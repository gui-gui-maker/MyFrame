package com.lsts.qualitymanage.service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.qualitymanage.bean.QualityUpdateDestroy;
import com.lsts.qualitymanage.dao.QualityUpdateDestroyDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("qualityUpdateDestroy")
public class QualityUpdateDestroyManager extends EntityManageImpl<QualityUpdateDestroy,QualityUpdateDestroyDao>{
    @Autowired
    QualityUpdateDestroyDao qualityUpdateDestroyDao;
    
    
    public final static String ZL_XHSQ_DWFZR = "DWFZR";// 已提交到单位负责人2
    public final static String ZL_XHSQ_FGYLD = "FGYLD";// 已提交到分管院领导1
    public final static String ZL_XHSQ_WTJ = "WTJ";//未提交
	public final static String ZL_XHSQ_PASS = "PASS"; // 审核通过
	public final static String ZL_XHSQ_NO_PASS = "NO_PASS"; // 审核不通过
}
