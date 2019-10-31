package com.lsts.qualitymanage.dao;


import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.qualitymanage.bean.QualityUpdateYijina;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2QualityUpdateYijinaDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("qualityUpdateYijinaDao")
public class QualityUpdateYijinaDao extends EntityDaoImpl<QualityUpdateYijina> {

	/**获取单条
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<QualityUpdateYijina> getOne(String fileId) {
		String hql = "from QualityUpdateYijina where fileId=?";
		List<QualityUpdateYijina> list = createQuery(hql,fileId).list();
		return list;

	}
}