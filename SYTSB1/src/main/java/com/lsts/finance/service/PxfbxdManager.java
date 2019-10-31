package com.lsts.finance.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.core.service.ProcessManager;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.finance.bean.Clfbxd;
import com.lsts.finance.bean.CwBorrowMoney;
import com.lsts.finance.bean.Fybxd;
import com.lsts.finance.bean.Pxfbxd;
import com.lsts.finance.dao.ClfbxdDao;
import com.lsts.finance.dao.FybxdDao;
import com.lsts.finance.dao.PxfbxdDao;
import com.lsts.finance.web.MoneyConvertAction;
import com.scts.car.bean.CarApply;
import com.scts.car.dao.CarApplyDao;


/**
 * 实体Manager，继承自泛型类EntityManageImpl，同时声明泛型的运行时bean和dao为Demo,DemoDao。<br/>
 * 通过这样的继承方式，自动获得了对Demo实体的crud操作<br/>
 * 注解@Service声明了该类为一个spring对象
 */
@Service("pxfbxdManager")
public class PxfbxdManager extends EntityManageImpl<Pxfbxd, PxfbxdDao> {

	// 必须提供Demo实体的dao对象，使用注解@Autowired标识为自动装配
	@Autowired
	PxfbxdDao pxfbxdDao;
	@Autowired
	FybxdDao fybxdDao;
	@Autowired
	private ProcessManager processManager;
	@Autowired
	private ActivityManager activityManager;
	@Autowired
	private CarApplyDao carApplyDao;
	@Autowired
    FlowExtManager flowExtManager;
    @Autowired
    MoneyConvertAction moneyConvertAction;
	
	
	/*设置状态常量*/
	public final static String CW_FY_REGISTER = "REGISTER";
	public final static String CW_FY_SUBMIT = "SUBMIT";
	public final static String CW_FY_AUDIT = "AUDIT";
	public final static String CW_FY_PASS = "PASS";
	public final static String CW_FY_NO_PASS = "NO_PASS";
	public final static String CW_SUBMIT = "SUBMIT"; // 已提交
	public final static String CW_FY_BXNO = "BXNO";//未报销
	public final static String CW_FY_BXYES = "BXYES";//已报销
	
	
public String getmid(String id) throws ParseException {
		
		List getmids =	fybxdDao.getmid(id);
		
		Object user1[] = null;
		user1 = (Object[]) getmids.get(0);
		
		System.out.println(user1[7]);
		String mids =  user1[7].toString();
		return mids;
	}
	
	/**
	 * 生成修改单编号
	 * @throws Exception 
	 * */
	public synchronized void saveTask1(Pxfbxd pxfbxd,CurrentSessionUser user) throws Exception{
		//先验证派车单是否被报销
		/*String carApplyIds = pxfbxd.getCarApplyIds();
		List<CarApply> list = carApplyDao.createQuery("from CarApply where id in ('"+carApplyIds.replaceAll(",", "','")+"') and expenses = '1'").list();
		if(list.size()>0){
			String carApSns = "";
			for (CarApply carApply : list) {
				carApSns += carApply.getApply_sn();
			}
			throw new Exception("派车单："+carApSns+"已报销！");
		}*/
		//新增保存时，生成新编号，修改功能不需要重新生成编号
		if(null==pxfbxd.getId() || "".equals(pxfbxd.getId())){
			String docNum = "";
			Calendar a=Calendar.getInstance();
 		int nowYear = a.get(Calendar.YEAR);
 		List<Pxfbxd> fybxdlist = pxfbxdDao.getTaskAllot();
 		if(fybxdlist==null || fybxdlist.size()==0) {
 			docNum = nowYear+"-"+"0001";
 		} else {
 			int[] docNumArray = new int[fybxdlist.size()];
 			for (int i=0;i<fybxdlist.size();i++) {
 				//将编号去掉“-”，转换成int型存入数组
 				if(fybxdlist.get(i).getIdentifier()!=null && !"".equals(fybxdlist.get(i).getIdentifier())){
 					docNumArray[i] = Integer.parseInt(fybxdlist.get(i).getIdentifier().replaceAll("-", ""));
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
 		pxfbxd.setIdentifier(docNum);
		}
		String userid = pxfbxd.getUserId();
		  if(userid.equals(user.getId()) ){
		    	
		    	String mid = getmid(userid);
		    	pxfbxd.setUserId(mid);
		    	
		    }
		  if(pxfbxd.getStatue()==null || pxfbxd.getStatue().equals("")){
			  pxfbxd.setStatue(CW_FY_REGISTER);
		  }
		  if(pxfbxd.getStatue()==null || pxfbxd.getStatue().equals("")){
			  pxfbxd.setStatue(CW_FY_REGISTER);
		  }
		  //验证保存时的金额大写是否正确
		  BigDecimal clHjJexj = pxfbxd.getClHjJexj();
		  String clHjRmbdx = pxfbxd.getClHjRmbdx();
		  String sumAmountSup = moneyConvertAction.number2CNMontrayUnit(clHjJexj);
		  if(!clHjRmbdx.equals(sumAmountSup)){
			  pxfbxd.setClHjRmbdx(sumAmountSup);
		  }
		/*  if(pxfbxd.getClJtfJe1()==null || pxfbxd.getClJtfJe1().equals("") ){
			 pxfbxd.setClJexj1(new BigDecimal(0));
		  }
		  if(pxfbxd.getClJtfJe2()==null || pxfbxd.getClJtfJe2().equals("") ){
				 pxfbxd.setClJexj2(new BigDecimal(0));
			  }
		  if(pxfbxd.getClJtfJe3()==null || pxfbxd.getClJtfJe3().equals("") ){
				 pxfbxd.setClJexj3(new BigDecimal(0));
			  }
		  if(pxfbxd.getClJtfJe4()==null || pxfbxd.getClJtfJe4().equals("") ){
				 pxfbxd.setClJexj4(new BigDecimal(0));
			  }
		  //住宿费金额
		  System.out.println(pxfbxd.getClZsfJe1());
		  if(pxfbxd.getClZsfJe1()==null || pxfbxd.getClZsfJe1().equals("")){
			  System.out.println(123);
			  pxfbxd.setClZsfJe1(new BigDecimal(0));
		  }
		  if(pxfbxd.getClZsfJe2()==null || pxfbxd.getClZsfJe2().equals("")){
			  pxfbxd.setClZsfJe2(new BigDecimal(0));
		  }
		  if(pxfbxd.getClZsfJe3()==null || pxfbxd.getClZsfJe3().equals("")){
			  pxfbxd.setClZsfJe3(new BigDecimal(0));
		  }
		  if(pxfbxd.getClZsfJe4()==null || pxfbxd.getClZsfJe4().equals("")){
			  pxfbxd.setClZsfJe4(new BigDecimal(0));
		  }
		  //伙食补助
		  if(pxfbxd.getClHsbzJe1()==null || pxfbxd.getClHsbzJe1().equals("")){
			  pxfbxd.setClHsbzJe1(new BigDecimal(0));
		  }
		  if(pxfbxd.getClHsbzJe2()==null || pxfbxd.getClHsbzJe2().equals("")){
			  pxfbxd.setClHsbzJe2(new BigDecimal(0));
		  }
		  if(pxfbxd.getClHsbzJe3()==null || pxfbxd.getClHsbzJe3().equals("")){
			  pxfbxd.setClHsbzJe3(new BigDecimal(0));
		  }
		  if(pxfbxd.getClHsbzJe4()==null || pxfbxd.getClHsbzJe4().equals("")){
			  pxfbxd.setClHsbzJe4(new BigDecimal(0));
		  }
		  //公杂费金额
		  if(pxfbxd.getClGzfJe1()==null || pxfbxd.getClGzfJe1().equals("")){
			  pxfbxd.setClGzfJe1(new BigDecimal(0));
		  }
		  if(pxfbxd.getClGzfJe2()==null || pxfbxd.getClGzfJe2().equals("")){
			  pxfbxd.setClGzfJe2(new BigDecimal(0));
		  }
		  if(pxfbxd.getClGzfJe3()==null || pxfbxd.getClGzfJe3().equals("")){
			  pxfbxd.setClGzfJe3(new BigDecimal(0));
		  }
		  if(pxfbxd.getClGzfJe4()==null || pxfbxd.getClGzfJe4().equals("")){
			  pxfbxd.setClGzfJe4(new BigDecimal(0));
		  }
		  //其他金额
		  if(pxfbxd.getClQtJe1()==null || pxfbxd.getClQtJe1().equals("")){
			  pxfbxd.setClQtJe1(new BigDecimal(0));
		  }
		  if(pxfbxd.getClQtJe2()==null || pxfbxd.getClQtJe2().equals("")){
			  pxfbxd.setClQtJe2(new BigDecimal(0));
		  }
		  if(pxfbxd.getClQtJe3()==null || pxfbxd.getClQtJe3().equals("")){
			  pxfbxd.setClQtJe3(new BigDecimal(0));
		  }
		  if(pxfbxd.getClQtJe4()==null || pxfbxd.getClQtJe4().equals("")){
			  pxfbxd.setClQtJe4(new BigDecimal(0));
		  }
		  //金额合计
		  if(pxfbxd.getClHjGzfJe()==null || pxfbxd.getClHjGzfJe().equals("")){
			  pxfbxd.setClHjGzfJe(new BigDecimal(0));
		  }
		  if(pxfbxd.getClJexj1()==null || pxfbxd.getClJexj1().equals("")){
			  pxfbxd.setClJexj1(new BigDecimal(0));
		  }
		  if(pxfbxd.getClJexj2()==null || pxfbxd.getClJexj2().equals("")){
			  pxfbxd.setClJexj2(new BigDecimal(0));
		  }
		  if(pxfbxd.getClJexj3()==null || pxfbxd.getClJexj3().equals("")){
			  pxfbxd.setClJexj3(new BigDecimal(0));
		  }
		  if(pxfbxd.getClJexj4()==null || pxfbxd.getClJexj4().equals("")){
			  pxfbxd.setClJexj4(new BigDecimal(0));
		  }
		  
		  if(pxfbxd.getClHjJtfJe()==null || pxfbxd.getClHjJtfJe().equals("")){
			  pxfbxd.setClHjJtfJe(new BigDecimal(0));
		  }
		  if(pxfbxd.getClHjZsfJe()==null || pxfbxd.getClHjZsfJe().equals("")){
			  pxfbxd.setClHjZsfJe(new BigDecimal(0));
		  }
		  if(pxfbxd.getClHjHsbzJe()==null || pxfbxd.getClHjHsbzJe().equals("")){
			  pxfbxd.setClHjHsbzJe(new BigDecimal(0));
		  }
		  if(pxfbxd.getClHjGzfJe()==null || pxfbxd.getClHjGzfJe().equals("")){
			  pxfbxd.setClHjGzfJe(new BigDecimal(0));
		  }
		  if(pxfbxd.getClHjGzfJe()==null || pxfbxd.getClHjGzfJe().equals("")){
			  pxfbxd.setClHjGzfJe(new BigDecimal(0));
		  }
		  if(pxfbxd.getClHjQtJe()==null || pxfbxd.getClHjQtJe().equals("")){
			  pxfbxd.setClHjQtJe(new BigDecimal(0));
		  }*/
		  
		
		  pxfbxdDao.save(pxfbxd);
	}

	public void delete(String ids)throws Exception{
      	for(String id:ids.split(",")){
      		Pxfbxd mtMP = pxfbxdDao.get(id);
      		//更新派车单
  			String carApplyIds = mtMP.getCarApplyIds();
  			if(StringUtil.isNotEmpty(carApplyIds)){
  				pxfbxdDao.createSQLQuery("update TZSB_CAR_APPLY set expenses = '0' where id in ('"+carApplyIds.replaceAll(",", "','")+"')").executeUpdate();
  			}
      		//删除自身
  			pxfbxdDao.getHibernateTemplate().delete(mtMP);
      	}
      }
	public void invalidate(String id) throws Exception{
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		Pxfbxd pxfbxd = this.get(id);
		pxfbxd.setStatue(ClfbxdManager.CW_DJZF);//已提交
		pxfbxd.setAbolishTime(new Date());
		pxfbxd.setAbolishName(user.getName());
		if(pxfbxd.getHandleTime()!=null){
		boolean bj=isSameDate(new Date(),pxfbxd.getHandleTime());
		System.out.println(bj);
		//1报销时间和审核通过相同2为不相同
		if(bj){
			pxfbxd.setAbolish("1");
		}else{
			pxfbxd.setAbolish("2");
		}
		}
		pxfbxdDao.save(pxfbxd);
		String carApplyIds = pxfbxd.getCarApplyIds();
		if(StringUtil.isNotEmpty(carApplyIds)){
			pxfbxdDao.createSQLQuery("update TZSB_CAR_APPLY set expenses = '0' where id in ('"+carApplyIds.replaceAll(",", "','")+"')").executeUpdate();
		}
	}
	private static boolean isSameDate(Date date1, Date date2) {
	       Calendar cal1 = Calendar.getInstance();
	       cal1.setTime(date1);

	       Calendar cal2 = Calendar.getInstance();
	       cal2.setTime(date2);

	       boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
	               .get(Calendar.YEAR);
	       boolean isSameMonth = isSameYear
	               && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
	       boolean isSameDate = isSameMonth
	               && cal1.get(Calendar.DAY_OF_MONTH) == cal2
	                       .get(Calendar.DAY_OF_MONTH);

	       return isSameDate;
	   }
	public synchronized void submit(Pxfbxd pxfbxd) throws Exception {
		//先验证派车单是否被报销
		String carApplyIds = pxfbxd.getCarApplyIds();
		if(!StringUtil.isEmpty(carApplyIds)&& carApplyIds.length()>6){
			List<CarApply> list = carApplyDao.createQuery("from CarApply where id in ('"+carApplyIds.replaceAll(",", "','")+"') and expenses = '1'").list();
			if(list.size()>0){
				String carApSns = "";
				for (CarApply carApply : list) {
					carApSns += carApply.getApply_sn()+",";
				}
				throw new Exception("派车单："+carApSns.substring(0,carApSns.length()-1)+" 已报销！");
			}
			//没有则改变派车单状态
			carApplyDao.createSQLQuery("update TZSB_CAR_APPLY set expenses = '1' where id in ('"+carApplyIds.replaceAll(",", "','")+"')").executeUpdate();
		}
		//
		pxfbxd.setStatue(ClfbxdManager.CW_SUBMIT);//已提交
		this.save(pxfbxd);
	}

}
