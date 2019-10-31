package demo.orm.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import demo.orm.bean.MTMC;
import demo.orm.bean.MTMP;

/**
 * 测试one-to-many
 */
public class TestMTM {

	SessionFactory sessionFactory;
	Log log = LogFactory.getLog(getClass());

	String p1id, p2id;

	@Before
	public void init() {
		Configuration config = new Configuration();
		config.addAnnotatedClass(MTMP.class);
		config.addAnnotatedClass(MTMC.class);
		config.configure();
		sessionFactory = config.buildSessionFactory();
	}

	public void testCascadeSave() {
		MTMP p1 = new MTMP();
		p1.setRemark("many to many : p1");
		MTMP p2 = new MTMP();
		p2.setRemark("many to many : p2");

		MTMC c1 = new MTMC();
		c1.setRemark("many to many : c1");
		MTMC c2 = new MTMC();
		c2.setRemark("many to many : c2");

		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		List<MTMP> ps = new ArrayList<MTMP>();
		ps.add(p1);
		ps.add(p2);

		List<MTMC> cs = new ArrayList<MTMC>();
		cs.add(c1);
		cs.add(c2);

		session.save(p1);
		session.save(p2);
		session.save(c1);
		session.save(c2);

		p1.setMtmcs(cs);
		p2.setMtmcs(cs);

		p1id = p1.getId();
		p2id = p2.getId();

		tr.commit();
	}

	public void testCascadeUpdate() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		MTMP p = (MTMP) session.get(MTMP.class, p1id);

		MTMC c1 = new MTMC();
		c1.setRemark("many to many : c1");
		MTMC c2 = new MTMC();
		c2.setRemark("many to many : c2");

		session.save(c1);
		session.save(c2);

		p.getMtmcs().add(c1);
		p.getMtmcs().add(c2);

		tr.commit();
	}

	@Test
	public void doTest() {
		// 取消注释即可执行测试
		// this.testCascadeSave();
		// this.testCascadeUpdate();
	}

	/**
	 * 清除测试数据
	 * 
	 * 取消@After注释执行测试。
	 */
	// @After
	@SuppressWarnings("unchecked")
	public void clear() {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		List<MTMP> ps = session.createCriteria(MTMP.class).list();

		// 依次清除关联对象MTMC
		for (MTMP p : ps) {
			p.setMtmcs(new ArrayList<MTMC>());
		}
		session.flush();
		// 清除MTMP
		session.createQuery("delete from MTMP").executeUpdate();
		session.createQuery("delete from MTMC").executeUpdate();
		tr.commit();
	}
}
