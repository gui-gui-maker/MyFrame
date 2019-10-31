package com.lsts.relevant.dao;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.relevant.bean.RelevantPeople;

/**
 * 特种作业人员基本信息数据处理对象
 * @ClassName RelevantPeopleDao
 * @JDK 1.6
 * @author GaoYa
 * @date 2014-02-13 下午04:45:00
 */
@Repository("relevantPeopleDao")
public class RelevantPeopleDao extends EntityDaoImpl<RelevantPeople> {

	/**
	 * 删除特种作业人员基本信息
	 * @param ids
	 * @return 
	 * @author GaoYa
	 * @date 2014-02-14 下午01:45:00
	 */
    public void delete(String ids) {
        String arr[] = ids.split(",");
        for (int i = 0; i < arr.length; i++) {
        	deleteCert(arr[i]);	//删除持证情况
            super.removeById(arr[i]);	//删除特种作业人员基本信息
        }
    }
    
    /**
	 * 删除持证情况
	 * @param ids
	 * @return 
	 * @author GaoYa
	 * @date 2014-02-14 下午01:47:00
	 */
    public void deleteCert(String id) {
    	this.createQuery("delete from RelevantPeopleCert c where c.relevantPeople.id=?", id).executeUpdate();   
    }
}
