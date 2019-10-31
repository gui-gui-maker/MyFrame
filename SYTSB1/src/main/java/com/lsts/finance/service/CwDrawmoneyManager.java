package com.lsts.finance.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.core.service.ProcessManager;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.security.CurrentSessionUser;
import com.lsts.finance.bean.CwDrawmoney;
import com.lsts.finance.bean.Fybxd;
import com.lsts.finance.dao.CwDrawmoneyDao;
import com.lsts.finance.web.MoneyConvertAction;



@Service("cwDrawmoneyManager")
public class CwDrawmoneyManager extends EntityManageImpl<CwDrawmoney,CwDrawmoneyDao>{
    @Autowired
    CwDrawmoneyDao	cwDrawmoneyDao; 
	@Autowired
	private ProcessManager processManager;
	@Autowired
	private ActivityManager activityManager;
	@Autowired
    FlowExtManager flowExtManager;
	@Autowired
    MoneyConvertAction moneyConvertAction;
	
	/**---状态常量---*/
	public final static String CW_LK_REGISTER = "REGISTER";// 登记申请
	public final static String CW_LK_AUDIT = "AUDIT";// 各部门审核
	public final static String CW_LK_PASS = "PASS"; // 审核通过
	public final static String CW_LK_NO_PASS = "NO_PASS"; // 审核不通过
	public final static String CW_SUBMIT = "SUBMIT"; // 已提交
	public final static String CW_CSTG = "CSTG";//审批通过;
	public final static String CW_DJZF  =  "DJZF"; //作废
	public final static String CW_YLK_YES  =  "LKYES"; //已领款
	public final static String CW_YLK_NO  =  "LKNO"; //未领款
	
	
	public synchronized void savelk(CwDrawmoney cwDrawmoney, CurrentSessionUser user){
		//新增保存时，生成新编号，修改功能不需要重新生成编号
				if(null==cwDrawmoney.getId() || "".equals(cwDrawmoney.getId())){
					String docNum = "";
					Calendar newYear=Calendar.getInstance();
		 		int nowYear = newYear.get(Calendar.YEAR);
		 		List<CwDrawmoney> cwDrawmoneyList = cwDrawmoneyDao.getNumber();
		 		if(cwDrawmoneyList==null || cwDrawmoneyList.size()==0) {
		 			docNum = nowYear+"-"+"0001";
		 		} else {
		 			int[] docNumArray = new int[cwDrawmoneyList.size()];
		 			for (int i=0;i<cwDrawmoneyList.size();i++) {
		 				//将编号去掉“-”，转换成int型存入数组
		 				if(cwDrawmoneyList.get(i).getNumber_tab()!=null && !"".equals(cwDrawmoneyList.get(i).getNumber_tab())){
		 					docNumArray[i] = Integer.parseInt(cwDrawmoneyList.get(i).getNumber_tab().replaceAll("-", ""));
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
		 			cwDrawmoney.setNumber_tab(docNum);
				}
				//获取当前登录人姓名
				cwDrawmoney.setRegistrant(user.getName());
				//保存登记人ID
				cwDrawmoney.setRegistrantId(user.getId());
				//当前申请日期
				cwDrawmoney.setRegistrantDate(new Date());
				
				int money=0;
				if(cwDrawmoney.getMoney()== null || cwDrawmoney.getMoney().equals("")){
					cwDrawmoney.setMoney(new BigDecimal(money));
				}
				if(cwDrawmoney.getStatus()== null || cwDrawmoney.getStatus().equals("")){
					cwDrawmoney.setStatus(CW_LK_REGISTER);
				}
				//验证保存时的金额大写是否正确
				  BigDecimal money1 = cwDrawmoney.getMoney();
				  String amountinwords = cwDrawmoney.getAmountinwords();
				  String sumAmountSup = moneyConvertAction.number2CNMontrayUnit(money1);
				  if(!amountinwords.equals(sumAmountSup)){
					  cwDrawmoney.setAmountinwords(sumAmountSup);
				  }
				cwDrawmoneyDao.save(cwDrawmoney);
	}
	
  	/**删除,如果状态为审批通过,则删除失败
  	 * @param ids
  	 * @throws Exception
  	 */
  	public void delete(String id,CwDrawmoney cwDrawmoney)throws Exception{
  		cwDrawmoney = cwDrawmoneyDao.get(id);   //获取领款单ID
  		//List<CwDrawmoney> list = cwDrawmoneyDao.dateail(ids);
  		if(!cwDrawmoney.getStatus().equals("CSTG")){ //判断领款单状态不是 '审批通过'
  			cwDrawmoneyDao.delete(id);		//删除相应数据
  		}else{
  			throw new KhntException("审批已通过,不能删除");  //状态通过时,回退,并返回页面信息
  		}
      }
  	
  	/**启动流程
  	 * @param map
  	 * @throws Exception
  	 */
  	public void doStartProess(Map<String, Object> map)throws Exception{
  		flowExtManager.startFlowProcess(map);
      }
  	
  	/**
  	 * 流程提交
  	 * @param map
  	 * @throws Exception
  	 */
  	public void doLctj(Map<String, Object> map) throws Exception{
  		flowExtManager.submitActivity(map);
  	}
  	
  	
}
