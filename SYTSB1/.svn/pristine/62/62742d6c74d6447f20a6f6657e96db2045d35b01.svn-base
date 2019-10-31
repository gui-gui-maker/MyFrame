package com.lsts.finance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.finance.bean.CwBillPara;
import com.lsts.finance.bean.CwInvoiceF;
import com.lsts.finance.dao.CwBillParaDao;
import com.lsts.finance.dao.CwInvoiceFDao;
import com.lsts.finance.dao.CwInvoiceLeadDao;

@Service("cwBillParaManager")
public class CwBillParaManager extends EntityManageImpl<CwBillPara, CwBillParaDao>{
	
	@Autowired
	private CwBillParaDao cwBillParaDao;
	@Autowired
	private CwInvoiceLeadManager cwInvoiceLeadManager;
	@Autowired
	private CwInvoiceFDao cwInvoiceFDao;

	public void del(String id) throws Exception {
		CwBillPara cbp = cwBillParaDao.get(id);
		cwInvoiceLeadManager.deleteBusiness(cbp.getCw_invoice_lead_id());
		CwInvoiceF cf = cwInvoiceFDao.get(cbp.getInvoice_id());
		cf.setStatus("WSY");
		cf.setLead_id("");
		cf.setLead_name("");
		cf.setLead_date(null);
		cwInvoiceFDao.save(cf);
		this.deleteBusiness(id);
	}

	public void delReturn(String id) {
		CwBillPara cbp = cwBillParaDao.get(id);
		cbp.setData_type("0");
		cwBillParaDao.save(cbp);;
		
	}

}
