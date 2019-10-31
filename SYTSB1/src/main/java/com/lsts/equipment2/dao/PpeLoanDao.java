package com.lsts.equipment2.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.equipment2.bean.PpeLoan;
import com.lsts.qualitymanage.bean.QualityZssq;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName PpeLoanDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("PpeLoanDao")
public class PpeLoanDao extends EntityDaoImpl<PpeLoan> {
	/**
	 * 获取所有编号不为空的登记表信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PpeLoan> getTaskAllot() {
		String hql = "from PpeLoan where identifier is not null";
		List<PpeLoan> list = createQuery(hql).list();
		return list;
	}

}