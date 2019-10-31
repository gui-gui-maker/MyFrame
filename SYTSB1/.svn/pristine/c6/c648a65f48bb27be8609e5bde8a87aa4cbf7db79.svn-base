package com.lsts.finance.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.archives.bean.ArchivesFile;
import com.lsts.finance.bean.Fybxd;

/**
 * 实体DAO，继承自泛型类EntityDaoImpl，同时声明泛型的运行时类为Demo。<br/>
 * 通过这样的继承方式，自动获得了对Demo实体的crud操作
 * 
 * 注解@Repository标识该类为持久化dao组件。
 */
@Repository("fybxdDao")
public class FybxdDao extends EntityDaoImpl<Fybxd> {
	
	@SuppressWarnings("rawtypes")
	public List getmid(String ids) {
		String sql = "select * from SYS_USER where id=?";
		List list = this.createSQLQuery(sql,ids).list();
		return list;
	}

		@SuppressWarnings("unchecked")
	public List<Fybxd> getTaskAllot() {
		String hql = "from Fybxd where identifier is not null";
		List<Fybxd> list = createQuery(hql).list();
		return list;
	}


}
