package com.scts.discipline.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.scts.discipline.bean.DisciplineZdjr;
/**
 * 主动介入
 * @author Administrator
 *
 */
@Repository("disciplineZdjrDao")
public class DisciplineZdjrDao extends EntityDaoImpl<DisciplineZdjr>{
	/**
	 * 查询编号最大的一位
	 * @param year
	 * @return
	 */
	public Map<String, String> getBeanByMaxSn(String year){
		String sql="SELECT * FROM ( SELECT substr(sn,15) as sn  FROM TJY2_DISCIPLINE_ZDSX_ZDJR WHERE sn LIKE  '"+year+"%'   ) order by sn desc";
		List<Map<String, String>> list=this.createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		if(list!=null && list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
	}
	/**
	 * 待办理
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getDsh(String userId){
		String sql="select vpw.STATUS,zz.*,vpw.HANDLER_ID,vpw.HANDLER_NAME,vpw.activity_id,vpw.process_id from TJY2_DISCIPLINE_ZDSX_ZDJR zz"
				+ " LEFT JOIN (SELECT * FROM V_PUB_WORKTASK_ADD_POSITION WHERE STATUS='0' AND WORK_TYPE='ZDJC_ZDJR_FLOW') vpw ON vpw.SERVICE_ID=zz.id  "
				+ " WHERE zz.STATE <> '99' AND vpw.HANDLER_ID=?"
				+ "  ORDER BY zz.CREATE_TIME DESC";
		List<Map<String, Object>> list=this.createSQLQuery(sql, userId).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}
	 public List<Map<String, Object>> getFlowByserviceIdAndHandlerId(String serviceId,String userId,String work_type){
	    	String sql="select * from V_PUB_WORKTASK_ADD_POSITION WHERE WORK_TYPE=? AND SERVICE_ID=? AND STATUS='0'";
	    	if(userId!=null){
	    		sql+=" AND HANDLER_ID='"+userId+"'";
	    	}
	    	List<Map<String, Object>> list=this.createSQLQuery(sql,work_type,serviceId).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
	    	return list;
	    }
	 /**
	  * 已办理
	  * @param UserId
	  * @return
	  */
	 public List<Map<String, Object>> getYbl(String UserId){
		 String sql="select  distinct zz.* from TJY2_DISCIPLINE_ZDSX_ZDJR zz"
		 		+ " LEFT JOIN (SELECT * FROM V_PUB_WORKTASK_ADD_POSITION WHERE STATUS='3' AND WORK_TYPE='ZDJC_ZDJR_FLOW') vpw ON vpw.SERVICE_ID=zz.id  "
		 		+ " WHERE zz.STATE <> '99' AND vpw.HANDLER_ID=?  AND vpw.activity_name <> '提交'"
		 		+ " ORDER BY zz.CREATE_TIME DESC";
		 List<Map<String, Object>> list=this.createSQLQuery(sql, UserId).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		 return list;
	 }
	 public List<DisciplineZdjr> getDisciplineZdjrsByCreateUserId(String userId){
		 String hql=" from DisciplineZdjr where create_user_id=? and state <> '99'  order by create_time desc";
		 List<DisciplineZdjr> list= this.createQuery(hql, userId).list();
		 return list;
	 }
	 public List<Map<String, Object>>  getUserById(String id){
		 String sql="select e.MOBILE_TEL from employee e,sys_user u "
 				+ " where u.employee_id=e.id  and u.id=?";
		 List<Map<String, Object>> list= this.createSQLQuery(sql, id).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		 return list;
	 }
}
