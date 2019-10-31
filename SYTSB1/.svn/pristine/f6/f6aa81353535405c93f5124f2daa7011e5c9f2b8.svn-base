package com.fwxm.scientific.service;

import java.util.List;
import com.fwxm.scientific.bean.Tjy2ScientificRemark;
import com.fwxm.scientific.dao.Tjy2ScientificRemarkDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("tjy2ScientificRemarkManager")
public class Tjy2ScientificRemarkManager extends EntityManageImpl<Tjy2ScientificRemark,Tjy2ScientificRemarkDao>{
    @Autowired
    Tjy2ScientificRemarkDao tjy2ScientificRemarkDao;
 // 根据基本信息ID查询持证情况
    public List<Tjy2ScientificRemark> queryTjy2ScientificRemarkByBasicId(String id){
    	return tjy2ScientificRemarkDao.createQuery("from Tjy2ScientificRemark where fk_scientific_id=? ORDER BY CREATE_DATE DESC", id).list();
    }
}
