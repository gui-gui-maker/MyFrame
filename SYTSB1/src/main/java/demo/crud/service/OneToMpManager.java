package demo.crud.service;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;

import demo.crud.bean.OneToMP;
import demo.crud.dao.OneToMPDao;

@Service("oneToMpManager")
public class OneToMpManager extends EntityManageImpl<OneToMP, OneToMPDao> {
	@Autowired
	OneToMPDao oneToMPDao;

	/**
	 * 级联删除写法
	 * @param ids
	 */
	public void delete(String ids) {
		Session session = oneToMPDao.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		for(String id:ids.split(",")){
			OneToMP oneToMP = oneToMPDao.get(id);
			session.delete(oneToMP);
		}
	}
}
