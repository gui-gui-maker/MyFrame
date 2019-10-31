package com.lsts.qualitymanage.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository; 
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.qualitymanage.bean.QualityZssqSub;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName QualityZssqSubDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("QualityZssqSubDao")
public class QualityZssqSubDao extends EntityDaoImpl<QualityZssqSub> {

	//删除原有数据
	public void deleteSub(String qualityZssFk) throws Exception {
   		String sql="delete from TJY2_QUALITY_ZSSQ_SUB t where t.QUALITY_ZSSQ_FK=?";
   		this.createSQLQuery(sql, qualityZssFk).executeUpdate();
   	}
	@SuppressWarnings("unchecked")
	public List<QualityZssqSub> getQualityZssqSubs(
			String qualityZssFk) {
		List<QualityZssqSub> list = new ArrayList<QualityZssqSub>();
		String hql = "from QualityZssqSub t where t.qualityZssFk=?";
		list = this.createQuery(hql, qualityZssFk).list();
		return list;
	}
}