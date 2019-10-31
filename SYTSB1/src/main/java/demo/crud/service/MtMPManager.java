package demo.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;

import demo.crud.bean.MtMP;
import demo.crud.dao.MtMPDao;


@Service("mtMPManager")
public class MtMPManager extends EntityManageImpl<MtMP,MtMPDao>{
    @Autowired
    MtMPDao mtMPDao;
    
    public void delete(String ids)throws Exception{
    	for(String id:ids.split(",")){
    		MtMP mtMP = mtMPDao.get(id);
    		mtMPDao.getHibernateTemplate().delete(mtMP);
    	}
    }
}
