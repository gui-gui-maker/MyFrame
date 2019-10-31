package com.lsts.log.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.log.bean.SysDzyzLog;

/**
 * 系统电子印章日志数据处理对象
 * @ClassName SysDzyzLogDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2018-03-26 下午02:27:00
 */
@Repository("sysDzyzLogDao")
public class SysDzyzLogDao extends EntityDaoImpl<SysDzyzLog> {

	// 根据业务ID获取日志
	@SuppressWarnings("unchecked")
	public SysDzyzLog getBJLog(String info_id) {
		String hql = "from SysDzyzLog i where i.business_id=? order by i.op_time asc";
		List<SysDzyzLog> list = this.createQuery(hql, info_id).list();
		if (!list.isEmpty()) {
			return (SysDzyzLog)list.get(0);
		}
		return null;
	}	
}
