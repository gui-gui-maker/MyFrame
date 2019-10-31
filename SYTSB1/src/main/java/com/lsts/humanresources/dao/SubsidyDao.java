package com.lsts.humanresources.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.humanresources.bean.Subsidy;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2RlSubsidyDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("subsidyDao")
public class SubsidyDao extends EntityDaoImpl<Subsidy> {

	//根据加班申请外键获取加班补助通知单
	public List<Subsidy> getDetail(String fkOvertimeId){
		List<Subsidy> list = new ArrayList<Subsidy>();
		String hql="from Subsidy";
		list = this.createQuery(hql).list();
    	return list;
    }
}	