package com.lsts.equipment2.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.rbac.impl.bean.Org;
import com.lsts.equipment2.bean.EquipmentLoan;
import com.lsts.finance.bean.Clfbxd;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName EquipmentLoanDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("equipmentLoanDao")
public class EquipmentLoanDao extends EntityDaoImpl<EquipmentLoan> {

	
	/**
	 * 根据领用/借用人id、领用/借用部门id获取领用/借用记录
	 * @param loanId -- 领用/借用人id
	 * @param depId -- 领用/借用部门id
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2015-11-21 12:35:00
	 */
	@SuppressWarnings("unchecked")
	public List<EquipmentLoan> getInfos(String loanId, String depId) {
		List<EquipmentLoan> list = new ArrayList<EquipmentLoan>();
		String hql = "from EquipmentLoan t where t.loanId=? and t.depId=?";
		list = this.createQuery(hql, loanId, depId).list();
		return list;
	}
	/**
	 * 根据部门ID获取部门Code
	 * @param depId 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Org> getOrgCode(String depId) {
		List<Org> list = new ArrayList<Org>();
		String hql = "from Org t where t.id = ?";
		list = this.createQuery(hql,depId).list();
		return list;
	}
	/**
	 * 查询末尾数字最大的编号
	 * @param depId 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EquipmentLoan> getLoanTotal(String nowYear) {
		String hql = "from EquipmentLoan where loanNo like '%-"+nowYear+"%-'";
		List<EquipmentLoan> list = createQuery(hql).list();
		return list;
	}
}