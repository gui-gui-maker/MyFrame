package demo.orm.test;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import demo.orm.bean.OTMMany;
import demo.orm.bean.OTMOne;

/**
 * 测试one-to-many
 */
public class TestOTM {

	SessionFactory sessionFactory;
	String pk;
	String ck;
	Log log = LogFactory.getLog(getClass());

	@Before
	public void init() {
		Configuration config = new Configuration();
		config.addAnnotatedClass(OTMOne.class);
		config.addAnnotatedClass(OTMMany.class);
		config.configure();
		sessionFactory = config.buildSessionFactory();
	}

	public void testCascadeSave() {
		OTMOne one = new OTMOne();
		one.setRemark("孩子爸-张老大");

		OTMMany c1 = new OTMMany();
		c1.setRemark("大儿子-张三");
		c1.setOne(one);// 他爹是张老大
		OTMMany c2 = new OTMMany();
		c2.setRemark("小儿子-张五");
		c2.setOne(one);// 他爹还是张老大

		Set<OTMMany> cs = new HashSet<OTMMany>();
		cs.add(c1);
		cs.add(c2);

		// 孩子爸的两个儿子 c1和c2
		one.setMany(cs);
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 直接将孩子爸送进数据库，两个儿子也自动级联送入
		session.save(one);
		pk = one.getId();
		ck = c2.getId();
		log.debug("孩子爸：" + pk);
		log.debug("小儿子：" + ck);
		tr.commit();
	}

	public void testCascadeRemove() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		OTMMany c = new OTMMany();
		// 设置小儿子的id
		c.setId(ck);

		// 获取孩子爸
		OTMOne p = (OTMOne) session.get(OTMOne.class, pk);
		// 将孩子爸的小儿子信息删除
		log.debug("只删除小儿子：" + ck);
		p.getMany().remove(c);

		// 提交档案，孩子爸只有一个儿子了
		tr.commit();
	}

	public void testRemove() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 找出孩子爸
		OTMOne p = (OTMOne) session.get(OTMOne.class, pk);
		// 删除孩子爸信息，同时所有儿子信息也将被清除
		log.debug("删除孩子爸，同时所有儿子（共" + p.getMany().size() + "个儿子）也自动清除：" + pk);
		session.delete(p);
		tr.commit();
	}

	@Test
	public void doTest() {
		// log.debug("测试一：构造孩子爸和二个儿子，并设置父子关系");
		// this.testCascadeSave();
		// log.debug("测试二：小儿子死亡，解除父子关系");
		// this.testCascadeRemove();
		// log.debug("测试三：孩子爸死亡，所有儿子陪葬");
		// this.testRemove();
	}

	 @After
	public void turndown() {
		// Session session = this.sessionFactory.getCurrentSession();
		// Transaction tr = session.beginTransaction();
		// session.createQuery("delete from OTMOne").executeUpdate();
		// tr.commit();
	}
}
