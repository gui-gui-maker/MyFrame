package com.fwxm.contract.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fwxm.contract.bean.ContractInfo;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;

@Repository("contractInfoDao")
public class ContractInfoDao extends EntityDaoImpl<ContractInfo> {

	public List<Object> getAllByYear() {
		String sql = "select count(t.id) counts,sum(t.amount) amount,"
				+ "to_char(t.sign_time,'yyyy') years "
				+ "from CONTRACT_INFO t group by to_char(t.sign_time,'yyyy')";
		
		List<Object> list = this.createSQLQuery(sql).list();
		
		return list;
	}

	public List<Object> getAllByMonth(String year) {
		String sql = "select count(t.id) counts,sum(t.amount) amount,"
				+ "to_char(t.sign_time,'MM') mm from CONTRACT_INFO t "
				+ " where to_char(t.sign_time,'yyyy')=?"
				+ "  group by to_char(t.sign_time,'MM')";
		
		List<Object> list = this.createSQLQuery(sql,year).list();
		
		return list;
	}

	public List<Object> getProperByMonth(String year, String sumAmou) {
		String sql = "select count(t.id) counts,to_char(round(sum(t.amount)/to_number(?)*100,2)) bl,"
				+ "to_char(t.sign_time,'MM') mm from CONTRACT_INFO t  where to_char(t.sign_time,'yyyy')=?"
				+ "  group by to_char(t.sign_time,'MM')";
		
		List<Object> list = this.createSQLQuery(sql,sumAmou,year).list();
		
		return list;
	}
	
	/**
	 * 根据项目名称或者单位名称查询合同信息
	 * author pingZhou
	 * @param name
	 * @return
	 */
	public List<ContractInfo> getDataByName(String name) {
		String hql = "from ContractInfo c where (c.project_name like '%"+name
				+"%' or c.custom_com_name like '%"+name+"%') and c.data_status<>'99'";
		@SuppressWarnings("unchecked")
		List<ContractInfo> list = this.createQuery(hql).list();
		
		return list;
	}

	/**
	 * 根据签订日期查询合同信息
	 * author pingZhou
	 * @param date
	 * @return
	 */
	public List<ContractInfo> getDataByDate(String date) {
		String hql = "from ContractInfo c where c.sign_time = to_date( '"+date+"','yyyy-MM-dd') and c.data_status<>'99'";
		@SuppressWarnings("unchecked")
		List<ContractInfo> list = this.createQuery(hql).list();
		
		return list;
	}

	/**
	 * 根据合同编号查询合同
	 * author pingZhou
	 * @param bh
	 * @return
	 */
	public List<ContractInfo> getDataByBh(String bh) {
		String hql = "from ContractInfo c where c.contract_no = '"+bh+"' and c.data_status<>'99'";
		@SuppressWarnings("unchecked")
		List<ContractInfo> list = this.createQuery(hql).list();
		
		return list;
	}

	
	
}
