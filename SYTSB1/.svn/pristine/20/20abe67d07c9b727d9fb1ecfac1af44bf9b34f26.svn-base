package com.lsts.finance.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.finance.bean.CwInvoiceF;
import com.lsts.finance.bean.CwInvoiceLead;
import com.lsts.log.bean.SysLog;



@Repository("cwInvoiceLeadDao")
public class CwInvoiceLeadDao extends EntityDaoImpl<CwInvoiceLead> {

	
	
//	@SuppressWarnings("unchecked")
//	public List getStatus(int code){
//		List list = new ArrayList();
//		String sql = " select count(1) from TJY2_CW_INVOICE_F t "
//				+ " where t.invoice_code in (:code)  and t.status = 'LY' ";
//		 list = this.createSQLQuery(sql).list();
//		 
//		 return list;
//	}
//	
	
	/**
	 * 已领用的发票才允许退票
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CwInvoiceF> refund(String[] code){
		List<CwInvoiceF> list = new ArrayList<CwInvoiceF>();
		String hql = " from CwInvoiceF t where invoiceCode in (:code) and t.status = 'LY' ";
				/*"exists ( select * from (select "
				+ code+ "  + (rownum-1) invoice_code "
				+ "from dual connect by rownum <=  "+num+") a "
				+ "where t.invoice_code  = a.invoice_code) "
				+ "and t.status = 'LY'";*/
		list = this.createQuery(hql).setParameterList("code", code).list();
		return list;
	}
	/**
	 * 查询历史记录
	 * @param cId
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public  List<SysLog> queryRecord(String cId){
		List<SysLog> list = new ArrayList<SysLog>();
		String hql = "from SysLog where business_id = ? order by op_time desc";
		try {
			list = this.createQuery(hql,cId).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
