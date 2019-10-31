package com.lsts.finance.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.finance.bean.CwInvoiceF;
import com.lsts.finance.bean.CwInvoiceP;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName cwInvoiceDao
 * @JDK 1.5
 * @author HQ
 * @date  
 */
@Repository("cwInvoicePDao")
public class CwInvoicePDao extends EntityDaoImpl<CwInvoiceP> {

	
	/**
	 * 查询发票连号是否重复
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getCode(String code,int num){
		List<CwInvoiceF> list = new ArrayList<CwInvoiceF>();
		String sql = " select count(1) from TJY2_CW_INVOICE_F t where "+
				"exists ( select * from (select "
				+ code+ "  + (rownum-1) invoice_code "
				+ "from dual connect by rownum <=  "+num+") a "
				+ "where t.invoice_code  = a.invoice_code )";
		list = this.createSQLQuery(sql).list();
		return list;
	}
	
	/**
	 * 查询发票连号状态是否已使用,领用,作废等.如已使用责不能删除,并删除连续号
	 * @param code
	 * @param status
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void getDelete(String code ,int num){
		String sql = " delete TJY2_CW_INVOICE_F t where "+
				"exists ( select * from (select "
				+ code+ "  + (rownum - 1) invoice_code "
				+ "from dual connect by rownum <=  "+num+") a "
				+ "where t.invoice_code  = a.invoice_code ) ";
		 this.createSQLQuery(sql).executeUpdate();
	}
	
	public void getDel(String ids,int start,int end){
		String sql = "delete TJY2_CW_INVOICE_P t where t.id=? and t.invoice_start = "+start+
				" and t.invoice_end = "+end;
		this.createSQLQuery(sql, ids).executeUpdate();
	}
	
	/**
	 * 查询发票连号状态是否已使用,领用,作废等
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getDatail(String code,int num){
		List<CwInvoiceF> list = new ArrayList<CwInvoiceF>();
		String sql = " select count(1) from TJY2_CW_INVOICE_F t where "+
				"exists ( select * from (select "
				+ code+ "  + (rownum-1) invoice_code "
				+ "from dual connect by rownum <=  "+num+") a "
				+ "where t.invoice_code  = a.invoice_code ) "
				+ "and t.status != 'WSY'";
		list = this.createSQLQuery(sql).list();
		return list;
	}
	
	
}
