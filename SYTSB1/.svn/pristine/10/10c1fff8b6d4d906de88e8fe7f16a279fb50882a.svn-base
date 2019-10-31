package com.lsts.finance.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.bean.BaseEntity;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rbac.impl.dao.UserDao;
import com.khnt.utils.StringUtil;
import com.lsts.IdFormat;
import com.lsts.finance.bean.CwBill;
import com.lsts.finance.bean.CwBillPara;
import com.lsts.finance.bean.CwInvoiceF;
import com.lsts.finance.bean.CwInvoiceLead;
import com.lsts.finance.dao.CwBillDao;
import com.lsts.finance.dao.CwInvoiceFDao;
import com.lsts.finance.dao.CwInvoiceLeadDao;

@Service("cwBillManager")
public class CwBillManager extends EntityManageImpl<CwBill, CwBillDao>{
	
	@Autowired
	private CwBillDao cwBillDao;
	@Autowired
	private CwInvoiceLeadDao cwInvoiceLeadDao;
	@Autowired
	private CwInvoiceFDao cwInvoiceFDao;
	@Autowired
	private CwInvoiceLeadManager cwInvoiceLeadManager;
	@Autowired
	private UserDao userDao;

	public void saveBasic(CwBill cwBill) {
		if(cwBill.getBillPara()!=null){
			for(CwBillPara cwBillPara:cwBill.getBillPara()){
				cwBillPara.setCwBill(cwBill);
				if(StringUtil.isEmpty(cwBillPara.getCw_invoice_lead_id())){
					//CwInvoiceLead lead = cwInvoiceLeadDao.get(cwBillPara.getInvoice_id());
					CwInvoiceLead lead = new CwInvoiceLead();
					lead.setLead_code(cwBillPara.getLead_code());
					lead.setInvoiceType(cwBillPara.getInvoice_type_code());
					lead.setLead_date(cwBillPara.getLead_date());
					lead.setLead_dep(cwBillPara.getLead_dep());
					lead.setLead_dep_id(cwBillPara.getLead_dep_id());
					User user = userDao.get(cwBillPara.getLead_id());
					lead.setLead_id(user.getEmployee().getId());
					lead.setLead_name(cwBillPara.getLead_name());
					lead.setLead_num(cwBillPara.getLead_num());
					lead.setLead_use(cwBillPara.getLead_use());
					lead.setRegistrant_date(cwBillPara.getRegistrant_date());
					lead.setRegistrant_name(cwBillPara.getRegistrant_name());
					lead.setRegistrantId(cwBillPara.getRegistrant_id());
					cwInvoiceLeadDao.save(lead);
					cwBillPara.setCw_invoice_lead_id(lead.getId());
					cwBillPara.setData_type("0");
					CwInvoiceF cf = cwInvoiceFDao.get(cwBillPara.getInvoice_id());
					cf.setStatus("LY");
					cf.setLead_id(cwBillPara.getLead_id());
					cf.setLead_name(cwBillPara.getLead_name());
					cf.setLead_date(cwBillPara.getLead_date());
					cwInvoiceFDao.save(cf);
				}
			}
		}		
		cwBillDao.save(cwBill);		
	}

	public void del(String ids) {
		String sql="update TJY2_CW_BILL t set t.data_status='99' where t.id in("+IdFormat.formatIdStr(ids)+")";
		cwBillDao.createSQLQuery(sql).executeUpdate();
	}

	public void subAudit(String ids, String op_id, String op) {
		String sql="update TJY2_CW_BILL t set t.audit_op_id='"+op_id+"',t.audit_op='"+op+"',t.data_status='1'"
				+ " where t.id in("+IdFormat.formatIdStr(ids)+")";
		cwBillDao.createSQLQuery(sql).executeUpdate();	
	}

	public HashMap<String, Object> getReturnDetail(String id,String step) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		CwBill cwBill = this.get(id);
		Collection<CwBillPara> bp = cwBill.getBillPara();
		Collection<CwBillPara> newbp =new ArrayList<CwBillPara>();
		if("apply".equals(step)){
			for (Iterator iterator = bp.iterator(); iterator.hasNext();) {
				CwBillPara cwBillPara = (CwBillPara) iterator.next();
				CwInvoiceF cf = cwInvoiceFDao.get(cwBillPara.getInvoice_id());
				if("LY".equals(cf.getStatus())){
					//bp.remove(cwBillPara);
					newbp.add(cwBillPara);
				}
			}	
		}else{
			for (Iterator iterator = bp.iterator(); iterator.hasNext();) {
				CwBillPara cwBillPara = (CwBillPara) iterator.next();
				if("1".equals(cwBillPara.getData_type())){
					//bp.remove(cwBillPara);
					newbp.add(cwBillPara);
				}
			}	
		}
		
		/*for(CwBillPara cwBillPara:cwBill.getBillPara()){
			CwInvoiceF cf = cwInvoiceFDao.get(cwBillPara.getInvoice_id());
			if(!"LY".equals(cf.getStatus())){
				//newbp.add(cwBillPara);
				bp.remove(cwBillPara);
			}
		}*/
		map.put("data", cwBill);
		map.put("billList", newbp);
		return map;		
	}

	public void saveReturnDetail(CwBill cwBill) {
		if(cwBill.getBillPara()!=null){
			for(CwBillPara cwBillPara:cwBill.getBillPara()){
				cwBillPara.setCwBill(cwBill);
				cwBillPara.setData_type("1");
			}
		}
		cwBill.setData_status("5");
		cwBillDao.save(cwBill);				
	}

	public void subRerurnApply(String ids) {
		String[] id=ids.split(",");
		for (int i = 0; i < id.length; i++) {
			CwBill cwBill = cwBillDao.get(id[i]);
			cwBill.setData_status("6");
			cwBillDao.save(cwBill);	
		}
		
	}

	public void saveReceivedDetail(CwBill cwBill) throws Exception {
		if(cwBill.getBillPara()!=null){
			for(CwBillPara cwBillPara:cwBill.getBillPara()){
				cwInvoiceLeadManager.deleteBusiness(cwBillPara.getCw_invoice_lead_id());
				CwInvoiceF cf = cwInvoiceFDao.get(cwBillPara.getInvoice_id());
				cf.setStatus("WSY");
				cf.setLead_id("");
				cf.setLead_name("");
				cf.setLead_date(null);
				cwInvoiceFDao.save(cf);
				cwBillPara.setCwBill(cwBill);
			}
		}
		cwBillDao.save(cwBill);			
	}
}
