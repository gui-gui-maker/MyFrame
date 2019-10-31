package demo.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.khnt.core.crud.manager.impl.EntityManageImpl;

import demo.crud.bean.OneToMF;
import demo.crud.dao.OneToMFDao;


@Service("oneToMFManager")
public class OneToMFManager extends EntityManageImpl<OneToMF,OneToMFDao>{
    @Autowired
    OneToMFDao oneToMFDao;
    
    public List<OneToMF> getList(String onetompid) throws Exception{
    	return oneToMFDao.findBy("oneToMP.id", onetompid);
    }
}
