package demo.orm.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import demo.orm.bean.OTOP;
import demo.orm.bean.OTOS;

/**
 * 测试one-to-one
 */
public class TestOTO {
	SessionFactory sessionFactory;
	String pk;
	String ck;
	Log log = LogFactory.getLog(getClass());

	@Before
	public void init() {
		Configuration config = new Configuration();
		config.addAnnotatedClass(OTOP.class);
		config.addAnnotatedClass(OTOS.class);
		config.configure();
		sessionFactory = config.buildSessionFactory();
	}

	public void testCascadeSave() {
		// 男人
		OTOP p = new OTOP();
		p.setRemark("男人（丈夫）");
		// 女人
		OTOS s = new OTOS();
		s.setRemark("女人（妻子）");

		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 男人妻子是女人
		p.setOtos(s);
		// 女人的丈夫是男人
		s.setOtop(p);
		// 直接将男人送进数据库，男人的妻子也自动级联送入
		session.save(p);
		pk = p.getId();
		ck = s.getId();

		log.debug("男人（丈夫）：" + pk);
		log.debug("女人（妻子）：" + ck);
		tr.commit();
	}

	public void testCascadeRemove() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 获得男人（丈夫）信息
		OTOP p = (OTOP) session.get(OTOP.class, pk);
		// 将男人的妻子（女人）删除
		log.debug("删除妻子：" + ck);
		p.setOtos(null);

		// 提交档案，男人没有老婆了
		session.save(p);
		tr.commit();
	}

	public void testCascadeUpdate() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 获得男人（丈夫）信息
		OTOP p = (OTOP) session.get(OTOP.class, pk);

		OTOS s = new OTOS();
		s.setRemark("女人（妻子）");
		s.setOtop(p);

		// 给男人娶个妻子（女人）
		p.setOtos(s);

		// 提交档案，男人有老婆了
		session.save(p);

		tr.commit();
	}

	public void testRemove() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 找出男人（丈夫）
		OTOP p = (OTOP) session.get(OTOP.class, pk);
		// 男人（丈夫）死亡，他的女人（妻子）一同殉葬
		log.debug("删除男人，同时女人（妻子）也自动删除：" + pk);
		session.delete(p);
		tr.commit();
	}

	@Test
	public void doTest() {
		// log.debug("测试一：添加男人女人，并建立夫妻关系");
		// this.testCascadeSave();
		// log.debug("测试二：女人（妻子）死亡，解除夫妻关系");
		// this.testCascadeRemove();
		// log.debug("测试三：给男人再娶个老婆");
		// this.testCascadeUpdate();
		// log.debug("测试四：男人（丈夫）死亡，妻子陪葬");
		// this.testRemove();
	}

	@After
	public void turndown() {
		// Session session = this.sessionFactory.getCurrentSession();
		// Transaction tr = session.beginTransaction();
		// session.createQuery("delete from OTOP").executeUpdate();
		// tr.commit();
	}
}
