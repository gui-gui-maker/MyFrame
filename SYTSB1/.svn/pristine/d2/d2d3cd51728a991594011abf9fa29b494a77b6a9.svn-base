package demo.bpm.dao;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;

import demo.bpm.bean.BpmServiceBean;

@Repository("testServiceDao")
public class BpmServiceDao extends EntityDaoImpl<BpmServiceBean> {

	public String findUserOrgId(String userId) {
		Object r = super.createSQLQuery("select org_id from sys_user where id=?", userId).uniqueResult();
		return r == null ? null : r.toString();
	}
}
