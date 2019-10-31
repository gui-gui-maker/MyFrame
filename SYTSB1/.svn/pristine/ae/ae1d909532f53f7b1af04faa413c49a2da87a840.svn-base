package com.lsts.finance.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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
import com.lsts.finance.dao.ClfbxdDao;
import com.lsts.finance.dao.FybxdDao;
import com.lsts.finance.web.MoneyConvertAction;
import com.lsts.office.bean.MeetingReq;
import com.scts.car.bean.CarApply;
import com.scts.car.dao.CarApplyDao;


/**
 * 实体Manager，继承自泛型类EntityManageImpl，同时声明泛型的运行时bean和dao为Demo,DemoDao。<br/>
 * 通过这样的继承方式，自动获得了对Demo实体的crud操作<br/>
 * 注解@Service声明了该类为一个spring对象
 */
@Service("clfbxdManager")
public class ClfbxdManager extends EntityManageImpl<Clfbxd, ClfbxdDao> {

	// 必须提供Demo实体的dao对象，使用注解@Autowired标识为自动装配
	@Autowired
	ClfbxdDao clfbxdDao;
	@Autowired
	FybxdDao fybxdDao;
	@Autowired
	private ProcessManager processManager;
	@Autowired
	private ActivityManager activityManager;
	@Autowired
    FlowExtManager flowExtManager;
	@Autowired
    MoneyConvertAction moneyConvertAction;
	@Autowired
	CarApplyDao carApplyDao;
	
	
	
	/*设置状态常量*/
	public final static String CW_FY_REGISTER = "REGISTER";
	public final static String CW_FY_SUBMIT = "SUBMIT";
	public final static String CW_FY_AUDIT = "AUDIT";
	public final static String CW_FY_PASS = "PASS";
	public final static String CW_FY_NO_PASS = "NO_PASS";
	public final static String CW_SUBMIT = "SUBMIT"; // 已提交
	public final static String CW_DJZF  =  "DJZF";
	
	
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
 * @throws ParseException 
	 * */
	public synchronized void saveTask1(Clfbxd clfbxd,CurrentSessionUser user) throws Exception{
		//新增保存时，生成新编号，修改功能不需要重新生成编号
		if(null==clfbxd.getId() || "".equals(clfbxd.getId())){
			String docNum = "";    
			Calendar a=Calendar.getInstance();
			int nowYear = a.get(Calendar.YEAR);
			List<Clfbxd> fybxdlist = clfbxdDao.getTaskAllot();
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
			clfbxd.setIdentifier(docNum);
		}/*else{
			//修改时判断是否关联派车单，派车单如果没有被其他已提交的报销单关联，则恢复成未报销状态
			if("1".equals(clfbxd.getClType())){
				if(!ClfbxdManager.CW_FY_REGISTER.equals(clfbxd.getStatue())){
					String[] itemIds = clfbxd.getItemId().split(",");
					String[] oldItemIds = clfbxdDao.get(clfbxd.getId()).getItemId().split(",");
					//找到修改后原派车单被改掉的
					Set<String> newItems = new HashSet<String>();
					Set<String> modifyItems = new HashSet<String>();
					for (int i = 0; i < itemIds.length; i++) {
						newItems.add(itemIds[i]);
					}
					for (int j = 0; j < oldItemIds.length; j++) {
						if(newItems.contains(oldItemIds[j])){
							continue;
						}else{
							modifyItems.add(oldItemIds[j]);
						}
					}
					for (String item : modifyItems) {
						String sql = "select t.* from TJY2_CLFBXD t where t.id <> ? and t.statue <> ? and t.item_id like '%"+item+"%'";
						List<Object[]> lst = clfbxdDao.createSQLQuery(sql, clfbxd.getId(),ClfbxdManager.CW_FY_REGISTER).list();
						if(lst.size()==0){
							clfbxdDao.createSQLQuery("update TZSB_CAR_APPLY set expenses = '0' where id = ?",item).executeUpdate();
						}
					}
				}
			}
		}*/
		String userid = clfbxd.getUserId();
		if(userid.equals(user.getId()) ){
	    	String mid = getmid(userid);
	    	clfbxd.setUserId(mid);
		}
		if(clfbxd.getStatue()==null || clfbxd.getStatue().equals("")){
			clfbxd.setStatue(CW_FY_REGISTER);
		}
		  //验证保存时的金额大写是否正确
		  BigDecimal clHjJexj = clfbxd.getClHjJexj();
		  String clHjRmbdx = clfbxd.getClHjRmbdx();
		  String sumAmountSup = moneyConvertAction.number2CNMontrayUnit(clHjJexj);
		  if(!clHjRmbdx.equals(sumAmountSup)){
			  clfbxd.setClHjRmbdx(sumAmountSup);
		  }
		 /* 
		  * 使金额 如果未填 默认值为空
		  * */
		  //交通费金额
		 /* if(clfbxd.getClJtfJe1()==null || clfbxd.getClJtfJe1().equals("") ){
			 clfbxd.setClJexj1(new BigDecimal(0));
		  }
		  if(clfbxd.getClJtfJe2()==null || clfbxd.getClJtfJe2().equals("") ){
				 clfbxd.setClJexj2(new BigDecimal(0));
			  }
		  if(clfbxd.getClJtfJe3()==null || clfbxd.getClJtfJe3().equals("") ){
				 clfbxd.setClJexj3(new BigDecimal(0));
			  }
		  if(clfbxd.getClJtfJe4()==null || clfbxd.getClJtfJe4().equals("") ){
				 clfbxd.setClJexj4(new BigDecimal(0));
			  }
		  //住宿费金额
		  if(clfbxd.getClZsfJe1()==null || clfbxd.getClZsfJe1().equals("")){
			  clfbxd.setClZsfJe1(new BigDecimal(0));
		  }
		  if(clfbxd.getClZsfJe2()==null || clfbxd.getClZsfJe2().equals("")){
			  clfbxd.setClZsfJe2(new BigDecimal(0));
		  }
		  if(clfbxd.getClZsfJe3()==null || clfbxd.getClZsfJe3().equals("")){
			  clfbxd.setClZsfJe3(new BigDecimal(0));
		  }
		  if(clfbxd.getClZsfJe4()==null || clfbxd.getClZsfJe4().equals("")){
			  clfbxd.setClZsfJe4(new BigDecimal(0));
		  }
		  //伙食补助
		  if(clfbxd.getClHsbzJe1()==null || clfbxd.getClHsbzJe1().equals("")){
			  clfbxd.setClHsbzJe1(new BigDecimal(0));
		  }
		  if(clfbxd.getClHsbzJe2()==null || clfbxd.getClHsbzJe2().equals("")){
			  clfbxd.setClHsbzJe2(new BigDecimal(0));
		  }
		  if(clfbxd.getClHsbzJe3()==null || clfbxd.getClHsbzJe3().equals("")){
			  clfbxd.setClHsbzJe3(new BigDecimal(0));
		  }
		  if(clfbxd.getClHsbzJe4()==null || clfbxd.getClHsbzJe4().equals("")){
			  clfbxd.setClHsbzJe4(new BigDecimal(0));
		  }
		  //公杂费金额
		  if(clfbxd.getClGzfJe1()==null || clfbxd.getClGzfJe1().equals("")){
			  clfbxd.setClGzfJe1(new BigDecimal(0));
		  }
		  if(clfbxd.getClGzfJe2()==null || clfbxd.getClGzfJe2().equals("")){
			  clfbxd.setClGzfJe2(new BigDecimal(0));
		  }
		  if(clfbxd.getClGzfJe3()==null || clfbxd.getClGzfJe3().equals("")){
			  clfbxd.setClGzfJe3(new BigDecimal(0));
		  }
		  if(clfbxd.getClGzfJe4()==null || clfbxd.getClGzfJe4().equals("")){
			  clfbxd.setClGzfJe4(new BigDecimal(0));
		  }
		  //其他金额
		  if(clfbxd.getClQtJe1()==null || clfbxd.getClQtJe1().equals("")){
			  clfbxd.setClQtJe1(new BigDecimal(0));
		  }
		  if(clfbxd.getClQtJe2()==null || clfbxd.getClQtJe2().equals("")){
			  clfbxd.setClQtJe2(new BigDecimal(0));
		  }
		  if(clfbxd.getClQtJe3()==null || clfbxd.getClQtJe3().equals("")){
			  clfbxd.setClQtJe3(new BigDecimal(0));
		  }
		  if(clfbxd.getClQtJe4()==null || clfbxd.getClQtJe4().equals("")){
			  clfbxd.setClQtJe4(new BigDecimal(0));
		  }
		  //金额合计
		  if(clfbxd.getClHjGzfJe()==null || clfbxd.getClHjGzfJe().equals("")){
			  clfbxd.setClHjGzfJe(new BigDecimal(0));
		  }
		  if(clfbxd.getClJexj1()==null || clfbxd.getClJexj1().equals("")){
			  clfbxd.setClJexj1(new BigDecimal(0));
		  }
		  if(clfbxd.getClJexj2()==null || clfbxd.getClJexj2().equals("")){
			  clfbxd.setClJexj2(new BigDecimal(0));
		  }
		  if(clfbxd.getClJexj3()==null || clfbxd.getClJexj3().equals("")){
			  clfbxd.setClJexj3(new BigDecimal(0));
		  }
		  if(clfbxd.getClJexj4()==null || clfbxd.getClJexj4().equals("")){
			  clfbxd.setClJexj4(new BigDecimal(0));
		  }
		  
		  if(clfbxd.getClHjJtfJe()==null || clfbxd.getClHjJtfJe().equals("")){
			  clfbxd.setClHjJtfJe(new BigDecimal(0));
		  }
		  if(clfbxd.getClHjZsfJe()==null || clfbxd.getClHjZsfJe().equals("")){
			  clfbxd.setClHjZsfJe(new BigDecimal(0));
		  }
		  if(clfbxd.getClHjHsbzJe()==null || clfbxd.getClHjHsbzJe().equals("")){
			  clfbxd.setClHjHsbzJe(new BigDecimal(0));
		  }
		  if(clfbxd.getClHjGzfJe()==null || clfbxd.getClHjGzfJe().equals("")){
			  clfbxd.setClHjGzfJe(new BigDecimal(0));
		  }
		  if(clfbxd.getClHjGzfJe()==null || clfbxd.getClHjGzfJe().equals("")){
			  clfbxd.setClHjGzfJe(new BigDecimal(0));
		  }
		  if(clfbxd.getClHjQtJe()==null || clfbxd.getClHjQtJe().equals("")){
			  clfbxd.setClHjQtJe(new BigDecimal(0));
		  }*/
		  
		  
		  clfbxdDao.save(clfbxd);
	}

	public void delete(String ids)throws Exception{
      	for(String id:ids.split(",")){
      		Clfbxd mtMP = clfbxdDao.get(id);
      		if("1".equals(mtMP.getClType())){
      			String itemIds = mtMP.getItemId();
      			if(StringUtil.isNotEmpty(itemIds)){
      				clfbxdDao.createSQLQuery("update TZSB_CAR_APPLY set expenses = '0' where id in ('"+itemIds.replaceAll(",", "','")+"')").executeUpdate();
      			}
      		}
      		clfbxdDao.getHibernateTemplate().delete(mtMP);
      	}
      }

	
	/**
	 * 通过起点时间与终点时间统计和人员id查询是否有人填写过申请单
	 * 
	 * @param request
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
 	public List<Object> GetTime(String bxdId,String ids, String clQdR1, String clZdR1, String clQdR1Year, String clZdR1Year) throws Exception {
 		List<Object> list= clfbxdDao.GetTime(bxdId,ids,clQdR1,clZdR1,clQdR1Year,clZdR1Year);
 		return list;
 	}
 	public List<Object> GetTime2(String bxdId,String ids, String clQdR2, String clZdR2, String clQdR2Year, String clZdR2Year) throws Exception {
 		List<Object> list= clfbxdDao.GetTime2(bxdId,ids,clQdR2,clZdR2,clQdR2Year,clZdR2Year);
 		return list;
 	}
 	public List<Object> GetTime3(String bxdId,String ids, String clQdR3, String clZdR3, String clQdR3Year, String clZdR3Year) throws Exception {
 		List<Object> list= clfbxdDao.GetTime3(bxdId,ids,clQdR3,clZdR3,clQdR3Year,clZdR3Year);
 		return list;
 	}
 	public List<Object> GetTime4(String bxdId,String ids, String clQdR4, String clZdR4, String clQdR4Year, String clZdR4Year) throws Exception {
 		List<Object> list= clfbxdDao.GetTime4(bxdId,ids,clQdR4,clZdR4,clQdR4Year,clZdR4Year);
 		return list;
 	}

	public List<CarApply> hasExpenses(String carApplyId) throws Exception{
		List<CarApply> list = carApplyDao.createQuery("from CarApply where id in ('"+carApplyId.replaceAll(",", "','")+"') and expenses = '1'").list();
		//List<Object> exp = clfbxdDao.createSQLQuery("select expenses from TZSB_CAR_APPLY where id = ?", carApplyId).list();
		//String hasExpenses = exp.get(0).toString();
		
		return list;
	}

	public void submit(String id) throws Exception{
		Clfbxd clfbxd = clfbxdDao.get(id);
		//先验证派车单是否被报销
		String carApplyIds = clfbxd.getItemId();
		if(!StringUtil.isEmpty(carApplyIds)&& carApplyIds.length()>6){
			List<CarApply> list = carApplyDao.createQuery("from CarApply where id in ('"+carApplyIds.replaceAll(",", "','")+"') and expenses = '1'").list();
			if(list.size()>0){
				String carApSns = "";
				for (CarApply carApply : list) {
					carApSns += carApply.getApply_sn()+",";
				}
				throw new Exception("派车单："+carApSns.substring(0,carApSns.length()-1)+" 已报销！");
			}
		}
		//提交
		clfbxd.setStatue(ClfbxdManager.CW_SUBMIT);//已提交
		clfbxdDao.save(clfbxd);
		if("1".equals(clfbxd.getClType())){
			String itemIds = clfbxd.getItemId();
			if(!StringUtil.isEmpty(itemIds)){
				clfbxdDao.createSQLQuery("update TZSB_CAR_APPLY set expenses = '1' where id in ('"+itemIds.replaceAll(",", "','")+"')").executeUpdate();
			}
			//clfbxdDao.createSQLQuery("update TZSB_CAR_APPLY set expenses = '1' where id = ?", clfbxd.getItemId()).executeUpdate();
		}
	}

	public void invalidate(String id) throws Exception{
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		Clfbxd clfbxd = clfbxdDao.get(id);
		clfbxd.setStatue(ClfbxdManager.CW_DJZF);//作废
		clfbxd.setAbolishTime(new Date());
		clfbxd.setAbolishName(user.getName());
		if(clfbxd.getHandleTime()!=null){
			boolean bj=isSameDate(new Date(),clfbxd.getHandleTime());
			//1报销时间和审核通过相同2为不相同
			if(bj){
				clfbxd.setAbolish("1");
			}else{
				clfbxd.setAbolish("2");
			}
		}
		clfbxdDao.save(clfbxd);
		if("1".equals(clfbxd.getClType())){
			String itemIds = clfbxd.getItemId();
			if(StringUtil.isNotEmpty(itemIds)){
				clfbxdDao.createSQLQuery("update TZSB_CAR_APPLY set expenses = '0' where id in ('"+itemIds.replaceAll(",", "','")+"')").executeUpdate();
			}
			//clfbxdDao.createSQLQuery("update TZSB_CAR_APPLY set expenses = '0' where id = ?", clfbxd.getItemId()).executeUpdate();
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
	
}
