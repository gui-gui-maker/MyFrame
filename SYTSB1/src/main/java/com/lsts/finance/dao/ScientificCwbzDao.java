package com.lsts.finance.dao;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.finance.bean.ScientificCwbz;

/**
 * 实体DAO，继承自泛型类EntityDaoImpl，同时声明泛型的运行时类为Demo。<br/>
 * 通过这样的继承方式，自动获得了对Demo实体的crud操作
 * 
 * 注解@Repository标识该类为持久化dao组件。
 */
@Repository("scientificCwbzDao")
public class ScientificCwbzDao extends EntityDaoImpl<ScientificCwbz> {
	

	
}
