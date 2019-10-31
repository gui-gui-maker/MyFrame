package com.lsts.office.dao;

import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.office.bean.Ywhbsgz;
import com.lsts.office.bean.YwhbsgzFk;


/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName ywhbsgzDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("ywhbsgz_fkDao")
public class Ywhbsgz_fkDao extends EntityDaoImpl<YwhbsgzFk> {
	@SuppressWarnings("unchecked")
	public List<YwhbsgzFk> Ywhbsgz(String ids) {
		String hql = "from YwhbsgzFk t where t.id=? ";
		return createQuery(hql,ids).list();
		}
	/**
	 * @return
	 */

	
	
	

}