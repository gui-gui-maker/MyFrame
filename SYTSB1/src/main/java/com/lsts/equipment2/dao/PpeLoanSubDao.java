package com.lsts.equipment2.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.equipment2.bean.PpeLoanSub;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName PpeLoanSubDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("PpeLoanSubDao")
public class PpeLoanSubDao extends EntityDaoImpl<PpeLoanSub> {

	/**
     * 根据登记表ID获取资产信息
     * @param loanFk
     * @return
     * @throws Exception
     */
   	public List<?> getPpeLoanSubList(String loanFk) throws Exception {
   		String sql="select * from TJY2_PPE_LOAN_SUB t where t.loan_fk=? order by t.self_no";
   		List<?> list=this.createSQLQuery(sql, loanFk).list();
   		return list;
   	}
   	/**
     * 删除与登记表相关的资产信息
     * @param loanFk
     * @return
     * @throws Exception
     */
   	public void deletePpeLoanSub(String loanFk) throws Exception {
   		String sql="delete from TJY2_PPE_LOAN_SUB t where t.loan_fk=?";
   		this.createSQLQuery(sql, loanFk).executeUpdate();
   	}
   	/**
   	 * 遍历登记表相关的所有资产状态
   	 * @param loanFk
   	 * @return
   	 */
   	public List<?>  checkBackAll(String loanFk){
   		String sql="select t.status from TJY2_PPE_LOAN_SUB t where t.loan_fk=?";
   		List<?> list=this.createSQLQuery(sql, loanFk).list();
		return list;
   	}
   	/**
   	 * 恢复资产表的借用状态为未借用
   	 * @param loanFk
   	 * @throws Exception
   	 */
   	public void recoverPpeInfo(String loanFk) throws Exception {
   		String sql="update TJY2_EQUIP_PPE p " + 
   				"   set p.loan_status = '0' " + 
   				" where p.id in " + 
   				"       (select t.ppe_fk from TJY2_PPE_LOAN_SUB t where t.loan_fk = ?)";
   		this.createSQLQuery(sql, loanFk).executeUpdate();
   	}
}