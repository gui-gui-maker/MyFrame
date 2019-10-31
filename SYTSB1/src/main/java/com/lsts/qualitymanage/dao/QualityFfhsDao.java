package com.lsts.qualitymanage.dao;


import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.qualitymanage.bean.QualityFfhs;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName QualityFfhsDao
 * @JDK 1.5
 * @author Jonny
 * @date  2016年4月7日 10:45:27
 */
@Repository("QualityFfhsDao")
public class QualityFfhsDao extends EntityDaoImpl<QualityFfhs> {

	
	@SuppressWarnings("unchecked")
	public List<QualityFfhs> getTaskAllot() {
		String hql = "from QualityFfhs where identifier is not null";
		List<QualityFfhs> list = createQuery(hql).list();
		return list;
	}
}