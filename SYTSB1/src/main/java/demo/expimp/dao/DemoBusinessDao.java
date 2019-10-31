package demo.expimp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;

import demo.expimp.bean.DemoBusinessBean;

@Repository("demoBusinessDao")
public class DemoBusinessDao extends EntityDaoImpl<DemoBusinessBean> {

	public void saveBatch(List<DemoBusinessBean> datas) {
		Session session = this.getSession();

		for (DemoBusinessBean b : datas) {
			session.save(b);
		}

		// 关键之处，每处理完一批，刷出session中的数据
		session.flush();
		session.clear();
	}

	@SuppressWarnings("unchecked")
	public List<Object> getData(String str, int total) {
		return createCriteria(Restrictions.like("str", str)).setMaxResults(total).list();
	}
}
