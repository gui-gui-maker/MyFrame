package com.lsts.finance.dao;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;







import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.finance.bean.Clfbxd;
import com.lsts.finance.bean.Fybxd;
import com.lsts.office.bean.MeetingReq;

/**
 * 实体DAO，继承自泛型类EntityDaoImpl，同时声明泛型的运行时类为Demo。<br/>
 * 通过这样的继承方式，自动获得了对Demo实体的crud操作
 * 
 * 注解@Repository标识该类为持久化dao组件。
 */
@Repository("clfbxdDao")
public class ClfbxdDao extends EntityDaoImpl<Clfbxd> {
	
	@SuppressWarnings("unchecked")
	public List<Clfbxd> getTaskAllot() {
		String hql = "from Clfbxd where identifier is not null";
		List<Clfbxd> list = null;
		try {
			list = createQuery(hql).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}


	//通过起点时间与终点时间统计和人员id查询是否有人填写过申请单
	@SuppressWarnings("unchecked")
	public  List<Object> GetTime(String bxdId,String ids, String clQdR1, String clZdR1, String clQdR1Year, String clZdR1Year) throws Exception {
		String sql ="select * from ("+ 
				"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R1=? and CL_ZD_R1=? and CL_QD_R1_YEAR=? and CL_ZD_R1_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') union "+
				"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R2=? and CL_ZD_R2=? and CL_QD_R2_YEAR=? and CL_ZD_R2_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') union "+
				"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R3=? and CL_ZD_R3=? and CL_QD_R3_YEAR=? and CL_ZD_R3_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') union "+
				"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R4=? and CL_ZD_R4=? and CL_QD_R4_YEAR=? and CL_ZD_R4_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') "+
				") order by IDENTIFIER desc Nulls last";
		if(bxdId!= null && bxdId.length() != 0){
			sql ="select * from ("+ 
					"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R1=? and CL_ZD_R1=? and CL_QD_R1_YEAR=? and CL_ZD_R1_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') and id!= '"+bxdId+"' union "+
					"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R2=? and CL_ZD_R2=? and CL_QD_R2_YEAR=? and CL_ZD_R2_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') and id!= '"+bxdId+"' union "+
					"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R3=? and CL_ZD_R3=? and CL_QD_R3_YEAR=? and CL_ZD_R3_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') and id!= '"+bxdId+"' union "+
					"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R4=? and CL_ZD_R4=? and CL_QD_R4_YEAR=? and CL_ZD_R4_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') and id!= '"+bxdId+"' "+
					") order by IDENTIFIER desc Nulls last";
		}
		
		List<Object> list = this.createSQLQuery(sql,clQdR1,clZdR1,clQdR1Year,clZdR1Year,clQdR1,clZdR1,clQdR1Year,clZdR1Year,clQdR1,clZdR1,clQdR1Year,clZdR1Year,clQdR1,clZdR1,clQdR1Year,clZdR1Year).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public  List<Object>  GetTime2(String bxdId,String ids, String clQdR2, String clZdR2, String clQdR2Year, String clZdR2Year) throws Exception {
		String sql ="select * from ("+
				"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R2=? and CL_ZD_R2=? and CL_QD_R2_YEAR=? and CL_ZD_R2_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') union "+
				"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R1=? and CL_ZD_R1=? and CL_QD_R1_YEAR=? and CL_ZD_R1_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') union "+
				"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R3=? and CL_ZD_R3=? and CL_QD_R3_YEAR=? and CL_ZD_R3_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') union "+
				"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R4=? and CL_ZD_R4=? and CL_QD_R4_YEAR=? and CL_ZD_R4_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') "+
				") order by IDENTIFIER desc Nulls last";
		if(bxdId!= null && bxdId.length() != 0){
			sql ="select * from ("+
					"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R2=? and CL_ZD_R2=? and CL_QD_R2_YEAR=? and CL_ZD_R2_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') and id!= '"+bxdId+"' union "+
					"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R1=? and CL_ZD_R1=? and CL_QD_R1_YEAR=? and CL_ZD_R1_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') and id!= '"+bxdId+"' union "+
					"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R3=? and CL_ZD_R3=? and CL_QD_R3_YEAR=? and CL_ZD_R3_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') and id!= '"+bxdId+"' union "+
					"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R4=? and CL_ZD_R4=? and CL_QD_R4_YEAR=? and CL_ZD_R4_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') and id!= '"+bxdId+"' "+
					") order by IDENTIFIER desc Nulls last";
		}
		List<Object>  list = this.createSQLQuery(sql,clQdR2,clZdR2,clQdR2Year,clZdR2Year,clQdR2,clZdR2,clQdR2Year,clZdR2Year,clQdR2,clZdR2,clQdR2Year,clZdR2Year,clQdR2,clZdR2,clQdR2Year,clZdR2Year).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public  List<Object>  GetTime3(String bxdId,String ids, String clQdR3, String clZdR3, String clQdR3Year, String clZdR3Year) throws Exception {
		String sql ="select * from ("+
				"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R3=? and CL_ZD_R3=? and CL_QD_R3_YEAR=? and CL_ZD_R3_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') union "+
				"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R2=? and CL_ZD_R2=? and CL_QD_R2_YEAR=? and CL_ZD_R2_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') union "+
				"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R1=? and CL_ZD_R1=? and CL_QD_R1_YEAR=? and CL_ZD_R1_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') union "+
				"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R4=? and CL_ZD_R4=? and CL_QD_R4_YEAR=? and CL_ZD_R4_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') "+
				") order by IDENTIFIER desc Nulls last";
		if(bxdId!= null && bxdId.length() != 0){
			sql ="select * from ("+
					"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R3=? and CL_ZD_R3=? and CL_QD_R3_YEAR=? and CL_ZD_R3_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') and id!= '"+bxdId+"' union "+
					"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R2=? and CL_ZD_R2=? and CL_QD_R2_YEAR=? and CL_ZD_R2_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') and id!= '"+bxdId+"' union "+
					"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R1=? and CL_ZD_R1=? and CL_QD_R1_YEAR=? and CL_ZD_R1_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') and id!= '"+bxdId+"' union "+
					"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R4=? and CL_ZD_R4=? and CL_QD_R4_YEAR=? and CL_ZD_R4_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') and id!= '"+bxdId+"' "+
					") order by IDENTIFIER desc Nulls last";
		}
		List<Object>  list = this.createSQLQuery(sql,clQdR3,clZdR3,clQdR3Year,clZdR3Year,clQdR3,clZdR3,clQdR3Year,clZdR3Year,clQdR3,clZdR3,clQdR3Year,clZdR3Year,clQdR3,clZdR3,clQdR3Year,clZdR3Year).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public  List<Object>  GetTime4(String bxdId,String ids, String clQdR4, String clZdR4, String clQdR4Year, String clZdR4Year) throws Exception {
		String sql ="select * from ("+
				"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R4=? and CL_ZD_R4=? and CL_QD_R4_YEAR=? and CL_ZD_R4_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') union "+
				"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R2=? and CL_ZD_R2=? and CL_QD_R2_YEAR=? and CL_ZD_R2_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') union "+
				"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R3=? and CL_ZD_R3=? and CL_QD_R3_YEAR=? and CL_ZD_R3_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') union "+
				"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R1=? and CL_ZD_R1=? and CL_QD_R1_YEAR=? and CL_ZD_R1_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') "+
				") order by IDENTIFIER desc Nulls last";
		if(bxdId!= null && bxdId.length() != 0){
			sql ="select * from ("+
					"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R4=? and CL_ZD_R4=? and CL_QD_R4_YEAR=? and CL_ZD_R4_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') and id!= '"+bxdId+"' union "+
					"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R2=? and CL_ZD_R2=? and CL_QD_R2_YEAR=? and CL_ZD_R2_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') and id!= '"+bxdId+"' union "+
					"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R3=? and CL_ZD_R3=? and CL_QD_R3_YEAR=? and CL_ZD_R3_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') and id!= '"+bxdId+"' union "+
					"select IDENTIFIER from TJY2_CLFBXD where CL_CCID like '%"+ids+"%' and CL_QD_R1=? and CL_ZD_R1=? and CL_QD_R1_YEAR=? and CL_ZD_R1_YEAR=? and IDENTIFIER is not null and STATUE not in('CSTG','NO_PASS','DJZF') and id!= '"+bxdId+"' "+
					") order by IDENTIFIER desc Nulls last";
		}
		List<Object>  list = this.createSQLQuery(sql,clQdR4,clZdR4,clQdR4Year,clZdR4Year,clQdR4,clZdR4,clQdR4Year,clZdR4Year,clQdR4,clZdR4,clQdR4Year,clZdR4Year,clQdR4,clZdR4,clQdR4Year,clZdR4Year).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public  List<Clfbxd>  GetIds(String IDENTIFIER) throws Exception {
		String sql ="select id from TJY2_CLFBXD where IDENTIFIER=?";
		List<Clfbxd>  list = this.createSQLQuery(sql,IDENTIFIER).list();
		return list;
	}
	
}
