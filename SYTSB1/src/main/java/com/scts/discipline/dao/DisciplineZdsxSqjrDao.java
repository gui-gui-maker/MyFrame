package com.scts.discipline.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.rbac.impl.bean.User;
import com.lsts.log.bean.SysLog;
import com.scts.discipline.bean.DisciplineZdsxSqjr;

/**
 * 重大事项---申请介入
 * @author Administrator
 *
 */
@Repository("disciplineZdsxSqjrDao")
public class DisciplineZdsxSqjrDao extends EntityDaoImpl<DisciplineZdsxSqjr> {
		
		/**
		 * 查询部门负责人
		 * @param orgid
		 * @return
		 */
		public List<User> getBmfzr(String orgid){
			String sql="SELECT su.* FROM SYS_USER su "
					+ " INNER JOIN SYS_USER_ROLE sur ON su.id=sur.user_id"
					+ " INNER JOIN SYS_ROLE sr ON sr.id=sur.role_id"
					+ " WHERE su.org_id=? AND sr.name='部门负责人' AND STATUS='1'";
			SQLQuery query=((SQLQuery)this.createSQLQuery(sql, orgid)).addEntity(User.class);
			return query.list();
		}
		/**
		 * 获取流程信息
		 * @param business_id
		 * @return
		 * @throws Exception
		 */
		public HashMap<String, Object>  getFlowStep(String business_id) throws Exception
	    {	
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			List<SysLog> list = this.createQuery("  from SysLog where business_id ='"+business_id+"' order by op_time,id asc").list();
			
			list.size();
		
			map.put("flowStep", list);
			map.put("size", list.size());
			map.put("success", true);
			
			return map;
	    }
		
		/**
		 * 查询编号最大的一位
		 * @param year
		 * @return
		 */
		public Map<String, String> getBeanByMaxSn(String year){
			String sql="SELECT * FROM ( SELECT substr(sn,15) as sn  FROM TJY2_DISCIPLINE_ZDSX_SQJR WHERE sn LIKE  '"+year+"%'   ) order by sn desc";
			List<Map<String, String>> list=this.createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
			if(list!=null && list.size()>0){
				return list.get(0);
			}else {
				return null;
			}
		}
		
		/**
		 * 已办理
		 * @param userId
		 * @return
		 */
		public List<Map<String, Object>> querychecked(String userId){
			 String sql="select  distinct zz.* from TJY2_DISCIPLINE_ZDSX_SQJR zz"
				 		+ " LEFT JOIN (SELECT * FROM V_PUB_WORKTASK_ADD_POSITION WHERE STATUS='3' AND WORK_TYPE='ZDJC_SQJR_FLOW') vpw ON vpw.SERVICE_ID=zz.id  "
				 		+ " WHERE zz.STATE <> '99' AND vpw.HANDLER_ID=? AND vpw.activity_name <> '提交'"
				 		+ " ORDER BY zz.CREATE_TIME DESC";
				 List<Map<String, Object>> list=this.createSQLQuery(sql, userId).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
				 return list;
		}
		/**
		 * 我的申请
		 * @param usertId
		 * @return
		 */
		public List<DisciplineZdsxSqjr> querymy(String usertId){
			 String hql=" from DisciplineZdsxSqjr where create_user_id=? and state <> '99'  order by create_time desc";
			 List<DisciplineZdsxSqjr> list= this.createQuery(hql, usertId).list();
			 return list;
		}
		
		/**
		 * 待办理
		 * @param userId
		 * @return
		 */
		public List<Map<String, Object>> querycheck(String userId){
			String sql="select vpw.STATUS,zz.*,vpw.HANDLER_ID,vpw.HANDLER_NAME,vpw.activity_id,vpw.process_id from TJY2_DISCIPLINE_ZDSX_SQJR zz"
					+ " LEFT JOIN (SELECT * FROM V_PUB_WORKTASK_ADD_POSITION WHERE STATUS='0' AND WORK_TYPE='ZDJC_SQJR_FLOW') vpw ON vpw.SERVICE_ID=zz.id  "
					+ " WHERE zz.STATE <> '99' AND vpw.HANDLER_ID=?"
					+ "  ORDER BY zz.CREATE_TIME DESC";
			List<Map<String, Object>> list=this.createSQLQuery(sql, userId).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
			return list;
		}
		
		 public List<Map<String, Object>>  getUserById(String id){
			 String sql="select e.MOBILE_TEL from employee e,sys_user u "
	 				+ " where u.employee_id=e.id  and u.id=?";
			 List<Map<String, Object>> list= this.createSQLQuery(sql, id).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
			 return list;
		 }
}
