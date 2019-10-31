package com.lsts.finance.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.StringUtil;
import com.lsts.finance.bean.CwBankDetail;
import com.lsts.finance.bean.CwBankFefund;
import com.lsts.finance.dao.CwBankDetailDao;
import com.lsts.finance.dao.CwBankFefundDao;
import com.lsts.humanresources.bean.BgLeave;
import com.lsts.log.bean.SysLog;
import com.lsts.log.dao.SysLogDao;
import com.lsts.log.service.SysLogService;


@Service("cwBankFefundManager")
public class CwBankFefundManager extends EntityManageImpl<CwBankFefund,CwBankFefundDao>{
    @Autowired
    CwBankFefundDao cwBankFefundDao;
    @Autowired
    CwBankDetailDao cwBankDetailDao;
    @Autowired
	private SysLogService logService;
    @Autowired
    SysLogDao sysLogDao;
    
   public void saveBank(HttpServletRequest request, CwBankFefund cwBankFefund , CurrentSessionUser user,String id){
	   
	   cwBankFefund.setOperatorId(user.getId());
	   cwBankFefund.setOperatorName(user.getName());
	   cwBankFefund.setOperatorTime(new Date());
	   cwBankFefund.setFkBankDetailId(id);
	   cwBankFefund.setDataStatus("0");//待审核
	   /*if(cwBankFefund.getRearAmount()!=null||!"".equals(cwBankFefund.getRearAmount())){
		   CwBankDetail cwBankDetail = cwBankDetailDao.get(id);
		   cwBankDetail.setRestMoney(cwBankFefund.getRearAmount());
		   cwBankDetailDao.save(cwBankDetail);
		   cwBankFefund.setRearAmount(cwBankDetail.getRestMoney());
	   }
	   cwBankFefundDao.save(cwBankFefund);*/
	   try {
		   cwBankFefundDao.save(cwBankFefund);
		   //记录撤销银行转账退款申请日志
		   if("add".equals(request.getParameter("pageStatus"))) {
			   logService.setLogs(cwBankFefund.getId(), "提交银行转账退款申请", "填写银行转账退款申请，自动提交。", user.getId(), user.getName(),
						new Date(), request.getRemoteAddr());
		   }else if("modify".equals(request.getParameter("pageStatus"))) {
			   logService.setLogs(cwBankFefund.getId(), "修改银行转账退款申请", "修改银行转账退款申请，自动提交", user.getId(), user.getName(),
						new Date(), request.getRemoteAddr());
		   }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
   /**
    * 查询未确认、未撤销、未删除的退款申请
    * @param request
    * @return
    * @throws Exception
    */
   public List<CwBankFefund> queryBankFefund(HttpServletRequest request,String fkBankDetailId) throws Exception {
	   List<CwBankFefund> list = new ArrayList<CwBankFefund>();
	   try {
		   list = cwBankFefundDao.queryBankFefund(request, fkBankDetailId);
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
   	return list;
	}
   /**
    * 获取流转过程
    * @param id
    * @return
    * @throws Exception
    */
   public HashMap<String, Object>  getFlowStep(String id) throws Exception{	
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<SysLog> list = sysLogDao.getBJLogs(id);
		CwBankFefund cwBankFefund = cwBankFefundDao.get(id);
		map.put("flowStep", list);
		map.put("size", list.size());
		map.put("identifier", cwBankFefund.getUnitName()+"</br>（退款金额："+cwBankFefund.getFefundMoney()+",退款人："+cwBankFefund.getFefundName()+"）");
		map.put("success", true);
		
		return map;
   }
}
