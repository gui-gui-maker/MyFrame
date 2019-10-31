package com.lsts.humanresources.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository; 
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.humanresources.bean.ContractRemind;
import com.lsts.humanresources.bean.RemindMessage;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName ContractRemindDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("contractRemindDao")
public class ContractRemindDao extends EntityDaoImpl<ContractRemind> {
	/**
	 * 获取消息提醒设置
	 * @return
	 */
	public ContractRemind getSetting(){
  		List<ContractRemind> list = new ArrayList<ContractRemind>();
  		ContractRemind contractRemind = new ContractRemind();
  		try {
			String hql = "from ContractRemind";
			list = this.createQuery(hql).list();
			if(list.size()==0){
				contractRemind=null;
			}else{
				contractRemind = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contractRemind;
    }
	/**
	 * 根据消息提醒设置获取接收人（除当事人）的手机号
	 * @return
	 */
	public List<?> getPhoneBySetting(){
		List<?> list = new ArrayList<Object>();
		try {
			String sql="select r.id emp_id, r.emp_phone" + 
					"  from TJY2_RL_EMPLOYEE_BASE r" + 
					" where r.id in" + 
					"       (SELECT REGEXP_SUBSTR(t.remind_id, '[^,]+', 1, rownum)" + 
					"          FROM TJY2_RL_CONTRACT_REMIND t" + 
					"        CONNECT BY ROWNUM <= LENGTH(t.remind_id) -" + 
					"                   LENGTH(REPLACE(t.remind_id, ',', '')) + 1)";
			list = this.createSQLQuery(sql).list();
  		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
    }

	/**
	 * 获取60天内合同到期的人员信息
	 * @return
	 */
	public List<?> getRemindInfo() {
		List<?> list = new ArrayList<Object>();
		try {
			String sql="select t.id, t.fk_employee_id, t.signed_man, t.contract_stop_date, r.emp_phone" + 
					"  from TJY2_RL_CONTRACT t, TJY2_RL_EMPLOYEE_BASE r" + 
					" where t.fk_employee_id(+) = r.id" + 
					"   and t.termination = '0'" + 
					"   and (t.contract_stop_date - trunc(sysdate ,'dd')) = 60" +
					"   and r.emp_status in ('3', '4')";
			list = this.createSQLQuery(sql).list();
  		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}