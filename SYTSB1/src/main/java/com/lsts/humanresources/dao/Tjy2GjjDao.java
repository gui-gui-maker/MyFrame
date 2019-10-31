package com.lsts.humanresources.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.humanresources.bean.RemindMessage;
import com.lsts.humanresources.bean.Tjy2Gjj;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2GjjDao
 * @JDK 1.5
 * @author Jonny
 * @date  2016年6月3日 11:27:33
 */
@Repository("Tjy2GjjDao")
public class Tjy2GjjDao extends EntityDaoImpl<Tjy2Gjj> {
	
public List getId(String id){
	String sql = "select t.id from TJY2_GJJ t where t.NAME_ID="+id;
	List list = this.createSQLQuery(sql,id).list();
	return list;
}

@SuppressWarnings("rawtypes")
public List getid(String name){
	String sql = "select t.id from TJY2_GJJ t where t.NAME=?";
	List list = this.createSQLQuery(sql,name).list();
	return list;
}
@SuppressWarnings("rawtypes")
public List getIdByNameId(String nameId){
	String sql = "select t.id from TJY2_GJJ t where t.NAME_ID=?";
	List list = this.createSQLQuery(sql,nameId).list();
	return list;
}
@SuppressWarnings("unchecked")
public List<Tjy2Gjj> getGjjs(){
	String hql = "from Tjy2Gjj";
	List<Tjy2Gjj> list = new ArrayList<Tjy2Gjj>();
	try {
		list = this.createQuery(hql).list();
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
}
//查询人员信息是否在工资表中有信息
public Boolean gJJ(String id) {
	boolean isexist= false;
	String sql = "select * from TJY2_GJJ where NAME_ID=?";
	List<?>  list = this.createSQLQuery(sql,id).list();
	if(list!=null && !list.isEmpty()){
		isexist = true;
	}
	return isexist;
}
public void deleteByEmployeeId(String id){
	String sql = "update TJY2_GJJ set DATA_STATUS = '99' where NAME_ID=?";
	this.createSQLQuery(sql,id).executeUpdate();
} 

//查询人员信息是否在工资表中有信息
public Tjy2Gjj getGjjByEmp(String id) {

	String sql = " from Tjy2Gjj where nameId=?";
	List<Tjy2Gjj>  list = this.createQuery(sql,id).list();
	if(list!=null && list.size()>0){
		return list.get(0);
	}
	return null;
}


}