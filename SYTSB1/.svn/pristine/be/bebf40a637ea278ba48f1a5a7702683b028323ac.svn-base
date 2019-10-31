package com.lsts.office.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.office.bean.WeightyDep;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName weightyDepDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("weightyDepDao")
public class WeightyDepDao extends EntityDaoImpl<WeightyDep> {
	@Autowired
	WeightyTaskDao weightyTaskDao;
	
	/**
	 * 通过ID删除配合部门信息
	 * @param id
	 */
	public void getDelete(String id){
		String sql = "delete TJY2_BG_DEP t where t.id=?";
		this.createSQLQuery(sql,id).executeUpdate();
	}
	
	/**
	 * 查询配合部门名称是否重复
	 * @param id
	 * @param depName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<WeightyDep> ExamineDep(String id,String depName){
		List<WeightyDep> list = new ArrayList<WeightyDep>();
			//通过主表ID查询子表配合部门名称是否存在,如果存在则返回1,如不存在则返回0
			String sql = "select count(1) from TJY2_BG_DEP t,TJY2_BG_WEIGHTYTASK a "+
						 " where a.id = '"+id+
						 "' and t.pk_dep_id = a.id"+
						 "  and t.ph_dep_name= '"+depName+"'"; 
			list = this.createSQLQuery(sql).list();
		return list;
		
	}
}