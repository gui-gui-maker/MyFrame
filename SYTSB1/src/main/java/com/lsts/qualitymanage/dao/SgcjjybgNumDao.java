package com.lsts.qualitymanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.qualitymanage.bean.QualitySgcjjybg;
import com.lsts.qualitymanage.bean.SgcjjybgNum;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2QualitySgcjjybgNumDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("SgcjjybgNumDao")
public class SgcjjybgNumDao extends EntityDaoImpl<SgcjjybgNum> {
	
	@SuppressWarnings("unchecked")
	public List<SgcjjybgNum> getTaskAllot() {
		String hql = "from SgcjjybgNum where identifierType ='1' and identifierType is not null";
		List<SgcjjybgNum> list = createQuery(hql).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SgcjjybgNum> getFwbjhbg_num(String test_coding,String code_year) {
		String hql = "from SgcjjybgNum where identifierType ='1' and status='1' and testCoding='"+test_coding
				+"' and fwbjhbgNum is not null and fwbjhbgNum like '"+code_year+"%'";
		List<SgcjjybgNum> list = createQuery(hql).list();
		return list;
	}
}