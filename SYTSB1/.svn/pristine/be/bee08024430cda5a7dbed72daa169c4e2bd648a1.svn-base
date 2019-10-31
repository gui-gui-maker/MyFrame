package demo.orm.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import demo.orm.bean.OTMMany;
import demo.orm.bean.OTMOne;

/**
 * 测试集合类型子对象条件过滤
 */
public class TestCollectionFieldFilter {

	SessionFactory sessionFactory;
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
		OTMOne one1 = new OTMOne();
		one1.setRemark("孩子爸-张老大");

		OTMOne one2 = new OTMOne();
		one2.setRemark("孩子爸-张老三");

		OTMMany c1 = new OTMMany();
		c1.setRemark("张老大儿子-张三");
		c1.setOne(one1);// 他爹是张老大
		OTMMany c2 = new OTMMany();
		c2.setRemark("张老三儿子-张五");
		c2.setOne(one2);// 他爹是张老三

		Set<OTMMany> cs1 = new HashSet<OTMMany>();
		cs1.add(c1);
		Set<OTMMany> cs2 = new HashSet<OTMMany>();
		cs2.add(c2);

		// 张老大的儿子
		one1.setMany(cs1);
		// 张老三的儿子
		one2.setMany(cs2);

		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 直接将孩子爸送进数据库，两个儿子也自动级联送入
		session.save(one1);
		session.save(one2);
		tr.commit();
	}

	public void testCollectionFilter() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		// 构造带有集合过滤子查询的HQL
		String hql = "from OTMOne o where 0<(select count(m) from o.many m where m.remark=?)";
		Query query1 = session.createQuery(hql);
		pringtMsg("张三", query1);
		pringtMsg("张五", query1);
		tr.commit();
	}

	@SuppressWarnings("unchecked")
	private void pringtMsg(String name, Query q) {
		q.setString(0, name);
		List<OTMOne> ones = q.list();
		log.debug("儿子名称为【" + name + "】的老爹有" + ones.size() + "个，分别是: ");
		StringBuilder ps = new StringBuilder();
		for (OTMOne o : ones)
			ps.append(",").append(o.getRemark());
		log.debug(ps.deleteCharAt(0));
	}

	@Test
	public void doTest() {
		// log.debug("测试：构造孩子爸和二个儿子，并设置父子关系");
		// this.testCascadeSave();

		// log.debug("测试：测试子对象集合过滤");
		// this.testCollectionFilter();
	}
}
