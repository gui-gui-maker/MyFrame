package demo.crud.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;

import demo.crud.bean.OneToOneP;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName TestOnetoonepDemoDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("oneToOnePDao")
public class OneToOnePDao extends EntityDaoImpl<OneToOneP> {
	
	public void del(String ids){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		for (int i = 0; i < ids.split(",").length; i++) {
			OneToOneP oneP = (OneToOneP)session.get(OneToOneP.class,ids.split(",")[i]);
			session.delete(oneP);
		}
	}

}