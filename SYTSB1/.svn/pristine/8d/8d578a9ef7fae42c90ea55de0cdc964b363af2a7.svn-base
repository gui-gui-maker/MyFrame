package com.lsts.humanresources.Tjy2Ywfwbgzqrb.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.finance.bean.CwBorrowMoney;
import com.lsts.humanresources.Tjy2Ywfwbgzqrb.bean.Tjy2Ywfwbgzqrb;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2YwfwbgzqrbDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("Tjy2YwfwbgzqrbDao")
public class Tjy2YwfwbgzqrbDao extends EntityDaoImpl<Tjy2Ywfwbgzqrb> {

	public List<Tjy2Ywfwbgzqrb> GetIDs(String ids) {
			String hql = "from Tjy2Ywfwbgzqrb t where  t.name=?";
			List<Tjy2Ywfwbgzqrb> list = createQuery(hql,ids).list();
			return list;
		
	}
	
	public List<Tjy2Ywfwbgzqrb> getIdByNameId(String nameId) {
		String sql = "select t.id from TJY2_YWFWBGZQRB t where t.NAME_ID=?";
		List list = this.createSQLQuery(sql,nameId).list();
		return list;
	}
	
	//查询人员信息是否在工资表中有信息
	public Boolean gzqr(String id) {
		boolean isexist= false;
		String sql = "select * from TJY2_YWFWBGZQRB where NAME_ID=?";
		List<?>  list = this.createSQLQuery(sql,id).list();
		if(list!=null && !list.isEmpty()){
			isexist = true;
		}
		return isexist;
	}
	public void deleteByEmployeeId(String id){
		String sql = "update TJY2_YWFWBGZQRB set DATA_STATUS = '99' where NAME_ID=?";
		this.createSQLQuery(sql,id).executeUpdate();
	}
	
	//查询人员信息是否在工资表中有信息
		public Tjy2Ywfwbgzqrb getGzByEmp(String id) {

			String sql = "from Tjy2Ywfwbgzqrb where nameId=?";
			List<Tjy2Ywfwbgzqrb>  list = this.createQuery(sql,id).list();
			if(list!=null && list.size()>0){
				return list.get(0);
			}
			return null;
		}
}