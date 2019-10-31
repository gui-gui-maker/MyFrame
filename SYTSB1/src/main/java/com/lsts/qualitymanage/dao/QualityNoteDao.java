package com.lsts.qualitymanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.qualitymanage.bean.QualityNote;
import com.lsts.qualitymanage.bean.QualityZssq;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName QualityNoteDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("QualityNoteDao")
public class QualityNoteDao extends EntityDaoImpl<QualityNote> {
	@SuppressWarnings("unchecked")
	public List<QualityNote> getAllIndentifier() {
		String hql = "from QualityNote where identifier is not null";
		List<QualityNote> list = createQuery(hql).list();
		return list;
	}
}