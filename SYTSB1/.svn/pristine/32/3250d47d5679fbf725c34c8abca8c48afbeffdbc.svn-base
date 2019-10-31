package com.fwxm.scientific.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository; 

import com.fwxm.scientific.bean.Tjy2ScientificCost;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.employee.bean.EmployeeCert;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2ScientificCostDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("tjy2ScientificCostDao")
public class Tjy2ScientificCostDao extends EntityDaoImpl<Tjy2ScientificCost> {
	
	
	
	
	
	
	/**
	 * 根据基本信息ID查询费用情况
	 * @param id
	 * @return 
	 * @author GaoYa
	 * @date 2014-03-03 下午05:00:00
	 */
	@SuppressWarnings("unchecked")
    public List<Tjy2ScientificCost> queryTjy2ScientificCostByBasicId(String id) {
    	List<Tjy2ScientificCost> list = new ArrayList<Tjy2ScientificCost>();
    	try {
    		if (StringUtil.isNotEmpty(id)) {
    			String hql = "from Tjy2ScientificCost  where fkTjy2ScientificId=?";
    			list = this.createQuery(hql, id).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
    }
}