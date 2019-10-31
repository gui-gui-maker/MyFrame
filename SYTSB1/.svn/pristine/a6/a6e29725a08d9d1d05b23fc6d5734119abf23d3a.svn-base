package demo.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.khnt.core.crud.manager.impl.EntityManageImpl;

import demo.crud.bean.Demo;
import demo.crud.dao.DemoDao;

/**
 * 实体Manager，继承自泛型类EntityManageImpl，同时声明泛型的运行时bean和dao为Demo,DemoDao。<br/>
 * 通过这样的继承方式，自动获得了对Demo实体的crud操作<br/>
 * 注解@Service声明了该类为一个spring对象
 */
@Service("demoManager")
public class DemoManager extends EntityManageImpl<Demo, DemoDao> {

	// 必须提供Demo实体的dao对象，使用注解@Autowired标识为自动装配
	@Autowired
	DemoDao demoDao;
	
	/**
	 * 自定义事务样例
	 */
	@Transactional(readOnly=true,propagation=Propagation.REQUIRES_NEW)
	public void exampleTransactional(){
		
	}
}
