package com.lsts.equipment2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;








import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.equipment2.bean.DocimasyFk;
import com.lsts.equipment2.bean.DocimasyFks;
import com.lsts.equipment2.dao.DocimasyFksDao;



@Service("docimasyFksManager")
public class DocimasyFksManager extends EntityManageImpl<DocimasyFks,DocimasyFksDao>{
    @Autowired
    DocimasyFksDao docimasyFksDao;
    
    public List<DocimasyFks> getList(String onetompid) throws Exception{
    	return docimasyFksDao.findBy("docimasyFk.id", onetompid);
    }

	
}



