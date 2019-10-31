package com.scts.cspace.file.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.scts.cspace.file.bean.TjyptFileInfo;


/**
 * 物理资源属性数据处理对象
 * @ClassName TjyptFileInfoDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-05-04 下午04:24:00
 */
@Repository("tjyptFileInfoDao")
public class TjyptFileInfoDao extends EntityDaoImpl<TjyptFileInfo> {

	public TjyptFileInfo getBeanByArrtId(String fk_attachment_id) {
		String hql = "from TjyptFileInfo f where f.fk_attachment_id = ?";
		List<TjyptFileInfo> list = this.createQuery(hql, fk_attachment_id).list();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}


}
