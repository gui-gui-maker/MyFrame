package com.lsts.humanresources.dao;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.humanresources.bean.ContractRemind;
import com.lsts.humanresources.bean.PositionTitleRemind;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("positionTitleRemindDao")
public class PositionTitleRemindDao extends EntityDaoImpl<PositionTitleRemind> {
	/**
	 * 获取消息提醒设置
	 * @return
	 */
	public PositionTitleRemind getSetting(){
  		List<PositionTitleRemind> list;
  		PositionTitleRemind positionTitleRemind = new PositionTitleRemind();
  		try {
			String hql = "from PositionTitleRemind";
			list = this.createQuery(hql).list();
			if(list.size()==0){
				positionTitleRemind=null;
			}else{
				positionTitleRemind = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return positionTitleRemind;
    }
	/**
	 * 根据消息提醒设置获取接收人（除当事人）的手机号
	 * @return
	 */
	public List<?> getPhoneBySetting(){
		List<?> list = new ArrayList<>();
		try {
			String sql="select r.id emp_id, r.emp_phone" + 
					"  from TJY2_RL_EMPLOYEE_BASE r" + 
					" where r.id in" + 
					"       (SELECT REGEXP_SUBSTR(t.remind_id, '[^,]+', 1, rownum)" + 
					"          FROM TJY2_RL_POSITION_T_REMIND t" +
					"        CONNECT BY ROWNUM <= LENGTH(t.remind_id) -" + 
					"                   LENGTH(REPLACE(t.remind_id, ',', '')) + 1)";
			String sql1 = "select R.ID EMP_ID, R.EMP_PHONE\n" +
					"  from TJY2_RL_EMPLOYEE_BASE R\n" +
					" where R.ID in\n" +
					"       (select REGEXP_SUBSTR(T.REMIND_ID, '[^,]+', 1, ROWNUM)\n" +
					"          from TJY2_RL_POSITION_T_REMIND T\n" +
					"       connect by ROWNUM <= LENGTH(regexp_replace(T.REMIND_ID, '[^,]', null)))";
			list = this.createSQLQuery(sql1).list();
  		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
    }

	/**
	 * 获取60天内聘用到期的人员信息
	 * @return
	 */
	public List<?> getRemindInfo() {
		List<?> list = new ArrayList<Object>();
		try {
			String sql="select t.id, t.fk_employee_id, t.signed_man, t.position_title_stop_date, r.emp_phone" +
					"  from TJY2_RL_POSITION_TITLE t, TJY2_RL_EMPLOYEE_BASE r" +
					" where t.fk_employee_id(+) = r.id" + 
					"   and t.termination = '0'" + 
					"   and (t.position_title_stop_date - trunc(sysdate ,'dd')) = 60" +
					"   and r.emp_status in ('3', '4')";
			list = this.createSQLQuery(sql).list();
  		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}