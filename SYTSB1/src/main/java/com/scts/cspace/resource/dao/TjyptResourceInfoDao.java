package com.scts.cspace.resource.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.scts.cspace.resource.bean.TjyptResourceInfo;


/**
 * 资源属性数据处理对象
 * @ClassName TjyptResourceInfoDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-05-04 下午04:24:00
 */
@Repository("tjyptResourceInfoDao")
public class TjyptResourceInfoDao extends EntityDaoImpl<TjyptResourceInfo> {

	public TjyptResourceInfo getByBusinessId(String business_id) {
		String hql = "from TjyptResourceInfo t where t.business_id = ? "
				+ "and resource_is_recyclebin='0' and if_down='1' and resource_status='1' and resource_share_status is null ";
		List<TjyptResourceInfo> list = this.createQuery(hql, business_id).list();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}


}
