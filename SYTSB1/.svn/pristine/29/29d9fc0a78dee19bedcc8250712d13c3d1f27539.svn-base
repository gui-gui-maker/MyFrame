package demo.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;

import demo.crud.bean.OneToOneP;
import demo.crud.dao.OneToOnePDao;


@Service("oneToOnePManager")
public class OneToOnePManager extends EntityManageImpl<OneToOneP,OneToOnePDao>{
    @Autowired
    OneToOnePDao oneToOnePDao;
    
    public void del(String ids){
    	oneToOnePDao.del(ids);
    }
}
