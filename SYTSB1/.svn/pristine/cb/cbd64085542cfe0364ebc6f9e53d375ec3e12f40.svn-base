package com.lsts.finance.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.core.service.ProcessManager;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.lsts.finance.bean.CwBorrowMoney;
import com.lsts.finance.dao.CwBorrowMoneyDao;
import com.lsts.finance.web.MoneyConvertAction;


@Service("cwBorrowMoneyManager")
public class CwBorrowMoneyManager extends EntityManageImpl<CwBorrowMoney,CwBorrowMoneyDao>{
    @Autowired
    CwBorrowMoneyDao cwBorrowMoneyDao;
	@Autowired
	private ProcessManager processManager;
	@Autowired
	private ActivityManager activityManager;
	@Autowired
    FlowExtManager flowExtManager;
	@Autowired
    MoneyConvertAction moneyConvertAction;
  
	/**---状态常量---*/
	public final static String CW_JK_REGISTER = "REGISTER";// 登记申请
//	public final static String CW_JK_SUBMIT = "SUBMIT";// 提交申请
	public final static String CW_JK_NO_PASS = "NO_PASS"; // 审核不通过
	public final static String CW_SUBMIT = "SUBMIT"; // 
	public final static String CW_PRINT  = "PRINT"; // 已打印
	public final static String CW_CSTG  = "CSTG"; // 审核通过
	public final static String CW_DJZF  =  "DJZF"; //作废

	
	public final static String CW_HK_YES = "HKYES"; // 已还款
	public final static String CW_HK_NO = "HKNO"; // 未还款
	public final static String CW_HK_BACK = "BACK"; // 审核退回
	

	
	
	/**
	 * 删除
	 * */
  	public void delete(String ids)throws Exception{
      	for(String id:ids.split(",")){
      		CwBorrowMoney mtMP = cwBorrowMoneyDao.get(id);
      		cwBorrowMoneyDao.getHibernateTemplate().delete(mtMP);
      	}
      }
  	/**
  	 * 提交
  	 * */
  	
  	public void doStartPress(Map<String, Object> map)throws Exception{
  		flowExtManager.startFlowProcess(map);
      }
  	
  	
  	/**
	 * 生成修改单编号
 * @throws ParseException 
	 * */
	public synchronized void saveTask1(CwBorrowMoney cwMoney,CurrentSessionUser user) throws ParseException{
		//新增保存时，生成新编号，修改功能不需要重新生成编号
		if(null==cwMoney.getId() || "".equals(cwMoney.getId())){
			String docNum = "";
			Calendar a=Calendar.getInstance();
 		int nowYear = a.get(Calendar.YEAR);
 		List<CwBorrowMoney> cwBorrowMoneylist = cwBorrowMoneyDao.getTaskAllot();
 		if(cwBorrowMoneylist==null || cwBorrowMoneylist.size()==0) {
 			docNum = nowYear+"-"+"0001";
 		} else {
 			int[] docNumArray = new int[cwBorrowMoneylist.size()];
 			for (int i=0;i<cwBorrowMoneylist.size();i++) {
 				//将编号去掉“-”，转换成int型存入数组
 				if(cwBorrowMoneylist.get(i).getIdentifier()!=null && !"".equals(cwBorrowMoneylist.get(i).getIdentifier())){
 					docNumArray[i] = Integer.parseInt(cwBorrowMoneylist.get(i).getIdentifier().replaceAll("-", ""));
 				}
 			}
 			int max = docNumArray[0];
 			//获取数组中最大的值
 			for (int i : docNumArray) {
 				max = max>i?max:i;
 			}
 			String docNum1 = String.valueOf(max+1);
 			if(nowYear>Integer.parseInt(docNum1.substring(0, 4))) {
 	 			docNum = nowYear+"-"+"0001";
 	 		}else{
	 			//编号加1后重新组
	 			docNum = docNum1.substring(0, 4)+"-"+docNum1.substring(4, 8);
 	 		}
 		}
 		cwMoney.setIdentifier(docNum);
 		//获取当前登录人姓名
 		cwMoney.setRegistrant(user.getName());
 		cwMoney.setRegistrantId(user.getId());
		}
		//空值为零
		int b=0;
		if(cwMoney.getCash()==null || cwMoney.getCash().equals("")){
			cwMoney.setCash(new BigDecimal(b));
		}
		if(cwMoney.getMoney()==null || cwMoney.getMoney().equals("")){
			cwMoney.setMoney(new BigDecimal(b));
		}
		//改变状态
		if(cwMoney.getStatus()==null || cwMoney.getStatus().equals("")){
			cwMoney.setStatus(CwBorrowMoneyManager.CW_JK_REGISTER);
		}
		if(cwMoney.getRepayment_status()!=null ){
		if(cwMoney.getRepayment_status().equals(CwBorrowMoneyManager.CW_JK_REGISTER)){
			cwMoney.setRepayment_status(CwBorrowMoneyManager.CW_JK_REGISTER);
		}
		if(cwMoney.getRepayment_status().equals(CwBorrowMoneyManager.CW_SUBMIT)){
			cwMoney.setRepayment_status(CwBorrowMoneyManager.CW_SUBMIT);
		}
		if(cwMoney.getRepayment_status().equals(CwBorrowMoneyManager.CW_CSTG)){
			cwMoney.setRepayment_status(CwBorrowMoneyManager.CW_CSTG);
		}
		if(cwMoney.getRepayment_status().equals(CwBorrowMoneyManager.CW_JK_NO_PASS)){
			cwMoney.setRepayment_status(CwBorrowMoneyManager.CW_JK_NO_PASS);
		}}
		//验证保存时的金额大写是否正确
		  BigDecimal import_money = cwMoney.getImport_money();
		  String rmb = cwMoney.getRmb();
		  String sumAmountSup = moneyConvertAction.number2CNMontrayUnit(import_money);
		  if(!rmb.equals(sumAmountSup)){
			  cwMoney.setRmb(sumAmountSup);
		  }
		cwBorrowMoneyDao.save(cwMoney);
	}
}
