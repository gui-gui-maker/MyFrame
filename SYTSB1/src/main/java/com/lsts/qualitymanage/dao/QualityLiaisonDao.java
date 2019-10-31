package com.lsts.qualitymanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.qualitymanage.bean.QualityLiaison;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName QualityLiaisonDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("QualityLiaisonDao")
public class QualityLiaisonDao extends EntityDaoImpl<QualityLiaison> {
	@SuppressWarnings("unchecked")
	public List<QualityLiaison> getAllIndentifier() {
		String hql = "from QualityLiaison where identifier is not null";
		List<QualityLiaison> list = createQuery(hql).list();
		return list;
	}
}