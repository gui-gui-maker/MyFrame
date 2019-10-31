package com.lsts.humanresources.service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.humanresources.bean.Remove;
import com.lsts.humanresources.dao.RemoveDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("remove")
public class RemoveManager extends EntityManageImpl<Remove,RemoveDao>{
    @Autowired
    RemoveDao removeDao;
}
