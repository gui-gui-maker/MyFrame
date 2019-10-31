package com.lsts.finance.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.security.CurrentSessionUser;
import com.lsts.finance.bean.CwInvoiceF;
import com.lsts.finance.bean.CwInvoiceLead;
import com.lsts.finance.bean.CwInvoiceP;
import com.lsts.finance.dao.CwInvoiceFDao;
import com.lsts.finance.dao.CwInvoiceLeadDao;
import com.lsts.log.bean.SysLog;
import com.lsts.log.service.SysLogService;

@Service("cwInvoiceLeadManager")
public class CwInvoiceLeadManager extends EntityManageImpl<CwInvoiceLead, CwInvoiceLeadDao> {
	
	@Autowired
	CwInvoiceLeadDao cwInvoiceLeadDao;
	@Autowired
	CwInvoiceFDao cwInvoiceFDao;
	@Autowired
	private SysLogService logService;
	/**保存领取发票记录并回写发票信息
	 * @param cwInvoiceLead
	 * @param ids
	 * @param user
	 */
	public void saveLy(CwInvoiceLead cwInvoiceLead,String[] ids,CurrentSessionUser user,HttpServletRequest request){
		cwInvoiceLead.setRegistrantId(user.getId());
		cwInvoiceLead.setRegistrant_name(user.getName());//登记人姓名
		cwInvoiceLead.setRegistrant_date(new Date()); //登记时间
		cwInvoiceLead.setLead_date(new Date());	//获取当前领用时间
		cwInvoiceLeadDao.save(cwInvoiceLead);
		//获取发票id
    	if(ids.length>0){
    		//遍历发票id，回写领用信息
    		for(int i=0;i<ids.length;i++){
    			CwInvoiceF cwInvoiceF = cwInvoiceFDao.get(ids[i]);
    			cwInvoiceF.setStatus(CwInvoiceFManager.CW_FPGL_LY);//已领用
    			cwInvoiceF.setPk_lead_id(cwInvoiceLead.getId());
    			cwInvoiceF.setLead_id(cwInvoiceLead.getLead_id());
    			cwInvoiceF.setLead_name(cwInvoiceLead.getLead_name());
    			cwInvoiceF.setLead_date(cwInvoiceLead.getLead_date());
    			cwInvoiceFDao.save(cwInvoiceF);
    			// 写入系统日志
    			try {
    				logService.setLogs(cwInvoiceF.getId(), "发票领用", "发票领用（发票号："+cwInvoiceF.getInvoiceCode()+")", user.getId(), user.getName(),
    						new Date(),request.getRemoteAddr());
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}

    }
	
	/**
	 * 查询发票号码是否为'已领用',如有领用择自动退回
	 * @param cwInvoiceLead
	 * @throws KhntException
	 */
	public void refund (CwInvoiceLead cwInvoiceLead) throws KhntException{
		//获取领用主表信息
		cwInvoiceLead = cwInvoiceLeadDao.get(cwInvoiceLead.getId());
		//获取主表对应的所有发票号
		String[] code = cwInvoiceLead.getLead_code().split(",");
		//获取主表对应的领用的数量
		int num = cwInvoiceLead.getLead_num();
		//通过那个所有发票号，去查询哪些发票依然是领用状态，返回所有在领用状态的发票对象list
		List<CwInvoiceF> list = cwInvoiceLeadDao.refund(code);
			//编列发票对象的list，对每张发票进行退回处理
			for(CwInvoiceF cwInvoiceF : list){
				cwInvoiceF.setLead_id(null); //清空领用人ID
				cwInvoiceF.setLead_date(null);//清空领用时间
				cwInvoiceF.setLead_name(null);//清空领用人
				cwInvoiceF.setPk_lead_id(null);//清空领用外键ID
				cwInvoiceF.setStatus(CwInvoiceFManager.CW_FPGL_WSY); //回写状态至'未使用'
				cwInvoiceFDao.save(cwInvoiceF);
			}
		/*
		
		StringBuffer lead_code = new StringBuffer() ;
  		int count = 0 ;
  		for (int i=0;i<=num;i++){
  			lead_code.append(code[0] + count).append(",");
  			count++;
  		}
  		List refund = cwInvoiceLeadDao.getStatus(code[0], num);
  		BigDecimal scount =  (BigDecimal) refund.get(0);
  		if(scount.intValue() > 0){
			for(int i=0; i<=num;i++){
				CwInvoiceF cwInvoiceF = new CwInvoiceF();
				cwInvoiceF.setInvoiceCode(code[i]);
				cwInvoiceF.setLead_id(null);
				cwInvoiceF.setLead_date(null);
				cwInvoiceF.setLead_name(null);
				cwInvoiceF.setStatus(CwInvoiceFManager.CW_FPGL_WSY);
				cwInvoiceFDao.save(cwInvoiceF);
			}
  		}else{
  			throw new KhntException("退票失败,已有使用过的发票");
  		}*/
	}
	/**
	 * 查询历史记录
	 * @param cId
	 * @return
	 */
	public List<SysLog> queryRecord(String cId) {
		List<SysLog> list = new ArrayList<SysLog>();
		list=cwInvoiceLeadDao.queryRecord(cId);
		return list;
	}
}
