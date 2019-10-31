package com.fwxm.scientific.dao;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.fwxm.scientific.bean.Instruction;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName InstructionDao
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-01-10 13:42:45
 */
@Repository("instructionDao")
public class InstructionDao extends EntityDaoImpl<Instruction> {
	
	public SQLQuery getquery(String sql){
		SQLQuery query = this.getSession().createSQLQuery(sql);
		return query;
	}
}
