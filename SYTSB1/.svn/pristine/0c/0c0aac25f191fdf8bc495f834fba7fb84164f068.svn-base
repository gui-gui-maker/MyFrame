package com.lsts.finance.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.finance.bean.CwInvoiceF;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName cwInvoiceDao
 * @JDK 1.6
 * @author HQ
 * @date  
 */
@Repository("cwInvoiceFDao")
public class CwInvoiceFDao extends EntityDaoImpl<CwInvoiceF> {
	
	/**
	 * 删除重复的发票
	 * */
	@SuppressWarnings("unchecked")
	public List<CwInvoiceF> getinvoiceCode(String s) {
		List<CwInvoiceF> list = new ArrayList<CwInvoiceF>();
		try {
			String hql = "from CwInvoiceF t where t.invoiceCode=? ";	
			list = this.createQuery(hql, s).list();
			if (list.size()>0) {
				String sql = "DELETE FROM TJY2_CW_INVOICE_F where INVOICE_CODE='"+s+"'";
				this.createSQLQuery(sql).executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据发票状态获取发票信息
	 * @param status -- 发票状态（WSY：未使用 SY：使用 ZF：作废 LY：领用）
	 * @return
	 * @author GaoYa
	 * @date 2015-11-16 上午09:30:00
	 */
	@SuppressWarnings("unchecked")
	public List<CwInvoiceF> queryCwInvoiceFs(String status) {
		List<CwInvoiceF> list = new ArrayList<CwInvoiceF>();
		try {
			if (StringUtil.isNotEmpty(status)) {
				String hql = "from CwInvoiceF t where t.status=? ";	
				list = this.createQuery(hql, status).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查询收费取消、外借取消的发票信息
	 * 发票状态（WSY：未使用 SY：使用 ZF：作废 LY：领用 SFQX：收费取消 WJQX：外借取消）
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2016-09-02 下午03:53:00
	 */
	@SuppressWarnings("unchecked")
	public List<CwInvoiceF> queryCwInvoiceQx() {
		List<CwInvoiceF> list = new ArrayList<CwInvoiceF>();
		try {
			String hql = "from CwInvoiceF t where t.status='SFQX' or t.status='WJQX'";	
			list = this.createQuery(hql).list();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据发票号查询发票信息
	 * @param invoice_no -- 发票号
	 * @return 
	 * @author GaoYa
	 * @date 2015-11-16 下午01:53:00
	 */
	@SuppressWarnings("unchecked")
    public CwInvoiceF queryByInvoice_no(String invoice_no) {
		CwInvoiceF cwInvoiceF = null;
    	try {
    		if (StringUtil.isNotEmpty(invoice_no)) {
    			String hql = "from CwInvoiceF where invoiceCode = ?";
    			cwInvoiceF = (CwInvoiceF)this.createQuery(hql, invoice_no).uniqueResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return cwInvoiceF;
    }
	
	/**
	 * 根据发票号查询发票信息
	 * @param invoice_no -- 发票号
	 * @return 
	 * @author GaoYa
	 * @date 2015-11-16 下午01:53:00
	 */
	@SuppressWarnings("unchecked")
    public List<CwInvoiceF> querysByInvoice_no(String invoice_no) {
		List<CwInvoiceF> list = new ArrayList<CwInvoiceF>();
    	try {
    		if (StringUtil.isNotEmpty(invoice_no)) {
    			String hql = "from CwInvoiceF where invoiceCode = ?";
    			list = (List<CwInvoiceF>)this.createQuery(hql, invoice_no).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
    }

	public Object querysByInvoiceNoForPay(String invoice_no) {
		if (StringUtil.isNotEmpty(invoice_no)) {
			String hql = "select invoiceDate,invoiceUnit,status from CwInvoiceF where invoiceCode = ?";
			List<Object> list = this.createQuery(hql, invoice_no).list();
			if(list.size()>0) {
				return list.get(0);
			}
		}
		return null;
	}
}
