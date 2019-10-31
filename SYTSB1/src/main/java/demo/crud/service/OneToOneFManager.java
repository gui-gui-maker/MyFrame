package demo.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;

import demo.crud.bean.OneToOneF;
import demo.crud.dao.OneToOneFDao;


@Service("oneToOneFManager")
public class OneToOneFManager extends EntityManageImpl<OneToOneF,OneToOneFDao>{
    @Autowired
    OneToOneFDao oneToOneFDao;
}
