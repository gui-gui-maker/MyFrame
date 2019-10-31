package com.lsts.qualitymanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.qualitymanage.bean.Tyfabh;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2QualityTyfabhDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("TyfabhDao")
public class TyfabhDao extends EntityDaoImpl<Tyfabh> {
	
	@SuppressWarnings("unchecked")
	public List<Tyfabh> getTaskAllot() {
		String hql = "from Tyfabh where identifier is not null";
		List<Tyfabh> list = createQuery(hql).list();
		return list;
	}

}