package demo.crud.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;

import demo.crud.bean.Demo;

/**
 * 实体DAO，继承自泛型类EntityDaoImpl，同时声明泛型的运行时类为Demo。<br/>
 * 通过这样的继承方式，自动获得了对Demo实体的crud操作
 * 
 * 注解@Repository标识该类为持久化dao组件。
 */
@Repository("demoDao")
public class DemoDao extends EntityDaoImpl<Demo> {

	@SuppressWarnings("unchecked")
	public List<Demo> query(String s) {
		// super中预定义了很多有用的方法，可供使用
		// return super.findBy("strVal", s);
		// return super.listQuery("from Demo where strVal=? order by intVal desc", s);
		// return super.createCriteria(Restrictions.eq("strVal",s)).addOrder(Order.desc("intVal")).list();
		return super.createQuery("from Demo where strVal=? order by intVal desc", s).list();
	}
}
