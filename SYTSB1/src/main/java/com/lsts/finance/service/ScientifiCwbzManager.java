package com.lsts.finance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.contract.bean.ContractCustom;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.finance.bean.Clfbxd;
import com.lsts.finance.bean.ScientificCwbz;
import com.lsts.finance.dao.ScientificCwbzDao;


/**
 * 实体Manager，继承自泛型类EntityManageImpl，同时声明泛型的运行时bean和dao为Demo,DemoDao。<br/>
 * 通过这样的继承方式，自动获得了对Demo实体的crud操作<br/>
 * 注解@Service声明了该类为一个spring对象
 */
@Service("scientifiCwbzManager")
public class ScientifiCwbzManager extends EntityManageImpl<ScientificCwbz, ScientificCwbzDao> {
	 @Autowired
	    private ScientificCwbzDao  scientificCwbzDao;
		
	
	 public   void changeStatus(String id) throws Exception {
		 //改变科研报销表状态
		 String hql =" from ScientificCwbz where fk_clfbxd_id=?";
		 List<ScientificCwbz> list = scientificCwbzDao.createQuery(hql,id).list();
		 ScientificCwbz  csbz = list.get(0);
		 csbz.setStatus("2");//已报销
		 
		 
		 scientificCwbzDao.save(csbz);
		 
		 
	 }
	
}
