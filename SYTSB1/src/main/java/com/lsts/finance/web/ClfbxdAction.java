package com.lsts.finance.web;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.manager.EmployeeManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.employee.service.EmployeesService;
import com.lsts.finance.bean.Clfbxd;
import com.lsts.finance.bean.CwBorrowMoney;
import com.lsts.finance.bean.Fybxd;
import com.lsts.finance.bean.Pxfbxd;
import com.lsts.finance.dao.ClfbxdDao;
import com.lsts.finance.service.ClfbxdManager;
import com.lsts.finance.service.CwBorrowMoneyManager;
import com.lsts.finance.service.CwDrawmoneyManager;
import com.lsts.finance.service.FybxdService;
import com.lsts.finance.service.PxfbxdManager;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.humanresources.service.EmployeeBaseManager;
import com.scts.car.bean.CarApply;


/**
 * <p>
 * web控制器组件。该组件继承自泛型类SpringSupportAction，并提供了运行时的bean和manager对象。
 * 由此获得了对bean的crund操作能力，SpringSupportAction类中已经定义了对bean的 save,detail,delete方法。
 * 
 * </p>
 * <p>
 * 注解@Controller标识该类为web 控制器；
 * </p>
 * <p>
 * 注解@RequestMapping定义该控制器的web访问路径
 * </p>
 * @param <Ayou>
 */
@Controller
@RequestMapping("finance/clfbxd")
public class ClfbxdAction<Ayou> extends SpringSupportAction<Clfbxd, ClfbxdManager> {

	// 必须提供Demo实体的manager对象，使用注解@Autowired标识为自动装配
	@Autowired
	private ClfbxdManager clfbxdManager;
	
	@Autowired
	private FlowExtManager flowExtManager;
	
	@Autowired
	private ActivityManager activityManager;
	
	@Autowired
	private FybxdService fybxdService;
	@Autowired
	ClfbxdDao clfbxdDao;
	@Autowired
	private EmployeeBaseManager employeeBaseManager;
	@Autowired
	private EmployeeManager employeeManager;
	/**
	 * 通过起点时间与终点时间统计和人员id查询是否有人填写过申请单
	 * */
	@SuppressWarnings("unused")
	@RequestMapping(value = "saveTime")
	@ResponseBody
	 public HashMap<String, Object> saveTime(HttpServletRequest request,@RequestBody Clfbxd clfbxd) throws Exception{
			HashMap<String, Object> map = new HashMap<String, Object>();
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			
			String clQdR1=clfbxd.getClQdR1();//起点 日
			String clQdR2=clfbxd.getClQdR2();
			String clQdR3=clfbxd.getClQdR3();
			String clQdR4=clfbxd.getClQdR4();
			
			String clQdR1Year=clfbxd.getClQdR1Year();//起点 年份
			String clQdR2Year=clfbxd.getClQdR2Year();
			String clQdR3Year=clfbxd.getClQdR3Year();
			String clQdR4Year=clfbxd.getClQdR4Year();
			
			String clZdR1=clfbxd.getClZdR1();//终点 日
			String clZdR2=clfbxd.getClZdR2();
			String clZdR3=clfbxd.getClZdR3();
			String clZdR4=clfbxd.getClZdR4();
			
			String clZdR1Year=clfbxd.getClZdR1Year();//终点 年份
			String clZdR2Year=clfbxd.getClZdR2Year();
			String clZdR3Year=clfbxd.getClZdR3Year();
			String clZdR4Year=clfbxd.getClZdR4Year();
			
			String ClCcid=clfbxd.getClCcid();//报销人id
			String[] ClCcids = ClCcid.split(",");
			String bxdId = clfbxd.getId();//报销单id
			System.out.println(bxdId);
			int a=0;
			map.put("success", true);
			if(clQdR1==null || clQdR1.equals("") && clZdR1==null || clZdR1.equals("")){
				a=123;
			}else{
				String names = "";
				for(String ids : ClCcids){
					Employee employee = employeeManager.get(ids);
					List<Object> list=clfbxdManager.GetTime(bxdId,ids, clQdR1, clZdR1, clQdR1Year, clZdR1Year);
					System.out.println(ids);
					if(!list.isEmpty() && list.size()>0){
						names=employee.getName()+","+names;
						String IDENTIFIER = null;
						try {
							IDENTIFIER=list.get(0).toString();
						} catch (Exception e) {
							e.printStackTrace();
						}
						//获取id
						List<Clfbxd> listid= clfbxdDao.GetIds(IDENTIFIER);
						String id = Pattern.compile("\\b([\\w\\W])\\b").matcher(listid.toString()
								.substring(1,listid.toString().length()-1)).replaceAll("'$1'");
						Clfbxd clfbxding=clfbxdManager.get(id);
						map.put("success", false);
						a=11;
						if(clfbxding==null){
							map.put("mt", clQdR1+"至"+clZdR1+"日"+"已有其他人为您填写过报销单！");
						}else{
							map.put("mt", "“"+clfbxding.getUser()+"”"+"已为“"+names.substring(0, names.length()-1)+"”填写过"+clQdR1+"至"+clZdR1+"日的报销单，请核实！");
						}
					}else{
						a=1;
						
					}
				}
			}
			if(clQdR2==null || clQdR2.equals("") && clZdR2==null || clZdR2.equals("")){
				a=123;
			}else{
				String names = "";
				for(String ids : ClCcids){
					Employee employee = employeeManager.get(ids);
					List<Object> list=clfbxdManager.GetTime2(bxdId,ids, clQdR2, clZdR2, clQdR2Year, clZdR2Year);
					System.out.println(ids);
					if(!list.isEmpty() && list.size()>0){
						names=employee.getName()+","+names;
						String IDENTIFIER = null;
						try {
							IDENTIFIER=list.get(0).toString();
						} catch (Exception e) {
							e.printStackTrace();
						}
						//获取id
						List<Clfbxd> listid= clfbxdDao.GetIds(IDENTIFIER);
						String id = Pattern.compile("\\b([\\w\\W])\\b").matcher(listid.toString()
								.substring(1,listid.toString().length()-1)).replaceAll("'$1'");
						Clfbxd clfbxding=clfbxdManager.get(id);
						map.put("success", false);
						a=11;
						if(clfbxding==null){
							map.put("mt", clQdR2+"至"+clZdR2+"日"+"已有其他人为您填写过报销单！");
						}else{//
							map.put("mt", "“"+clfbxding.getUser()+"”"+"已为“"+names.substring(0, names.length()-1)+"”填写过"+clQdR2+"至"+clZdR2+"日的报销单，请核实！");
						}
					}else{
						a=2;
						//map.put("success", true);
					}
				}
			}
			if(clQdR3==null || clQdR3.equals("") && clZdR3==null || clZdR3.equals("")){
				a=123;
			}else{
				String names = "";
				for(String ids : ClCcids){
					Employee employee = employeeManager.get(ids);
					List<Object> list=clfbxdManager.GetTime3(bxdId,ids, clQdR3, clZdR3, clQdR3Year, clZdR3Year);
					System.out.println(ids);
					if(!list.isEmpty() && list.size()>0){
						names=employee.getName()+","+names;
						String IDENTIFIER = null;
						try {
							IDENTIFIER=list.get(0).toString();
						} catch (Exception e) {
							e.printStackTrace();
						}
						//获取id
						List<Clfbxd> listid= clfbxdDao.GetIds(IDENTIFIER);
						String id = Pattern.compile("\\b([\\w\\W])\\b").matcher(listid.toString()
								.substring(1,listid.toString().length()-1)).replaceAll("'$1'");
						Clfbxd clfbxding=clfbxdManager.get(id);
						map.put("success", false);
						a=11;
						if(clfbxding==null){
							map.put("mt", clQdR3+"至"+clZdR3+"日"+"已有其他人为您填写过报销单！");
						}else{
							map.put("mt", "“"+clfbxding.getUser()+"”"+"已为“"+names.substring(0, names.length()-1)+"”填写过"+clQdR3+"至"+clZdR3+"日的报销单，请核实！");
						}
					}else{
						a=3;
						//map.put("success", true);
					}
				}
			}
			if(clQdR4==null || clQdR4.equals("") && clZdR4==null || clZdR4.equals("")){
				a=123;
			}else{
				String names = "";
				for(String ids : ClCcids){
					Employee employee = employeeManager.get(ids);
					List<Object> list=clfbxdManager.GetTime4(bxdId,ids, clQdR4, clZdR4, clQdR4Year, clZdR4Year);
					System.out.println(ids);
					if(!list.isEmpty() && list.size()>0){
						names=employee.getName()+","+names;
						String IDENTIFIER = null;
						try {
							IDENTIFIER=list.get(0).toString();
						} catch (Exception e) {
							e.printStackTrace();
						}
						//获取id
						List<Clfbxd> listid= clfbxdDao.GetIds(IDENTIFIER);
						String id = Pattern.compile("\\b([\\w\\W])\\b").matcher(listid.toString()
								.substring(1,listid.toString().length()-1)).replaceAll("'$1'");
						Clfbxd clfbxding=clfbxdManager.get(id);
						map.put("success", false);
						a=11;
						if(clfbxding==null){
							map.put("mt", clQdR4+"至"+clZdR4+"日"+"已有其他人为您填写过报销单！");
						}else{
							map.put("mt", "“"+clfbxding.getUser()+"”"+"已为“"+names.substring(0, names.length()-1)+"”填写过"+clQdR4+"至"+clZdR4+"日的报销单，请核实！");
						}
					}else{
						a=4;
						//map.put("success", true);
					}
				}
			}
//			if(a==1 || a==2 || a==3 || a==4 || a==123){
//				map.put("success", true);
//			}else{
//				if(a==11){
//					map.put("mt", clQdR1+"至"+clZdR1+"已有其他人为您填写过报销单");
//				}else if(a==22){
//					map.put("mt", clQdR2+"至"+clZdR2+"已有其他人为您填写过报销单");
//				}else if(a==33){
//					map.put("mt", clQdR3+"至"+clZdR3+"已有其他人为您填写过报销单");
//				}else if(a==44){
//					map.put("mt", clQdR4+"至"+clZdR4+"已有其他人为您填写过报销单");
//				}
//			}
			return map;
	    }
	@Override
	  public synchronized HashMap<String, Object> save(HttpServletRequest request,@RequestBody Clfbxd clfbxd) throws Exception{
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			HashMap<String, Object> map = new HashMap<String, Object>();
			try{
				if( clfbxd.getStatue().equals(fybxdService.CW_CSTG)){
					map.put("ts", "此条数据已通过不可修改");
					map.put("success", false);
				}else{
					clfbxdManager.saveTask1(clfbxd,user);
					map.put("success", true); 
				}
			}catch(Exception e){
				e.printStackTrace();
				map.put("success", false);
				map.put("msg", e.getMessage());
			}
			return map;
	    	
	    }
	@RequestMapping(value="hasExpenses")
	@ResponseBody
	public HashMap<String, Object> hasExpenses(HttpServletRequest request,String carApplyId) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<CarApply> expensed = clfbxdManager.hasExpenses(carApplyId);
		map.put("success", true);
		map.put("data", expensed);
		return map;
    }
	@RequestMapping(value = "save1")
	@ResponseBody
	 public synchronized HashMap<String, Object> save1(HttpServletRequest request,@RequestBody Clfbxd clfbxd) throws Exception{
			HashMap<String, Object> map = new HashMap<String, Object>();
				CurrentSessionUser user = SecurityUtil.getSecurityUser();
				
//				String userid = fybxd.getUserId();
				
					
				clfbxd.setStatue(CwBorrowMoneyManager.CW_CSTG);
				clfbxd.setHandleId(user.getId());
				clfbxd.setHandleName(user.getName());
				clfbxd.setHandleTime(new Date());
			    /*if(userid.equals(user.getId()) ){
			    	
			    	String mid = fybxdService.getmid(userid);
			    	fybxd.setUserId(mid);
			    	
			    }	*/
				clfbxdManager.save(clfbxd);
			    map.put("success", true);
				return map;
		    }
	  
	 
		/**提交单据
		 * @param id
		 * @return
		 * @throws Exception
		 */
		/*@RequestMapping(value = "submit")
		@ResponseBody
		public HashMap<String, Object> submit(String id) throws Exception {
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (id.isEmpty()){
				map.put("success", false);
				map.put("msg", "所选业务ID为空！");
			} else {
				Clfbxd clfbxd = clfbxdManager.get(id);
				if(clfbxd==null){
					map.put("success", false);
					map.put("msg", "数据获取失败！");
				} else {
					clfbxd.setStatue(ClfbxdManager.CW_SUBMIT);//已提交
					clfbxdManager.save(clfbxd);
					
					map.put("success", true);
				}
			}
			return map;
		}*/
		@RequestMapping(value = "submit")
		@ResponseBody
		public HashMap<String, Object> submit(String id) throws Exception {
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			try {
				clfbxdManager.submit(id);
				map.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("success", false);
				map.put("msg", e.getMessage());
			}
			return map;
		}
		//单据作废
		
		/*@RequestMapping(value = "submitzf")
		@ResponseBody
		public HashMap<String, Object> submitzf(String id) throws Exception {
			HashMap<String, Object> map = new HashMap<String, Object>();
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			if (id.isEmpty()){
				map.put("success", false);
				map.put("msg", "所选业务ID为空！");
			} else {
				Clfbxd clfbxd = clfbxdManager.get(id);
				if(clfbxd==null){
					map.put("success", false);
					map.put("msg", "数据获取失败！");
				} else {
					clfbxd.setStatue(ClfbxdManager.CW_DJZF);//作废
					clfbxd.setAbolishTime(new Date());
					clfbxd.setAbolishName(user.getName());
					if(clfbxd.getHandleTime()!=null){
					boolean bj=isSameDate(new Date(),clfbxd.getHandleTime());
					System.out.println(bj);
					//1报销时间和审核通过相同2为不相同
					if(bj){
						clfbxd.setAbolish("1");
					}else{
						clfbxd.setAbolish("2");
					}
					}
					clfbxdManager.save(clfbxd);
					if("1".equals(clfbxd.getClType())){
						clfbxdDao.createSQLQuery("update TZSB_CAR_APPLY set expenses = '0' where id = ?", clfbxd.getItemId()).executeUpdate();
					}
					
					map.put("success", true);
				}
			}
			return map;
		}*/
		@RequestMapping(value = "invalidate")
		@ResponseBody
		public HashMap<String, Object> invalidate(String id) throws Exception {
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			try {
				clfbxdManager.invalidate(id);
				map.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("success", false);
				map.put("msg", e.getMessage());
			}
					
			return map;
		}
		
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "getBeanMap")
		public String getBeanMap(String id,HttpServletRequest request) throws Exception {
			Map<String, String> map = new HashMap<String, String>();
			Clfbxd bean = clfbxdManager.get(id);
			map = bean.beanToMap();
			request.setAttribute("infoMap",map);
			request.setAttribute("templetName","CLVYBXD");
			return "app/finance/cw_borrow_print";
		}
		
		@Override
		public HashMap<String, Object> delete(String ids) throws Exception {
			// TODO Auto-generated method stub
			HashMap<String, Object> map = new HashMap<String, Object>();
			Clfbxd cwMoney=clfbxdManager.get(ids);
	  		if(cwMoney.getStatue().equals(CwBorrowMoneyManager.CW_CSTG)){
				map.put("msg", "此条数据已通过不可删除！");
				map.put("success", false);
	  		}else{
	  			clfbxdManager.delete(ids);
				map.put("msg", "数据删除成功！");
				map.put("success", true);
			}
			return map;
			//return JsonWrapper.successWrapper();
		}
	//	初审通过
		@RequestMapping(value = "submitcs")
		@ResponseBody
		public HashMap<String, Object> submitcs(String id,String yijian) throws Exception {
			HashMap<String, Object> map = new HashMap<String, Object>();
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			if (id.isEmpty()){
				map.put("success", false);
				map.put("msg", "所选业务ID为空！");
			} else {
				Clfbxd clfbxd = clfbxdManager.get(id);
				if(clfbxd==null){
					map.put("success", false);
					map.put("msg", "数据获取失败！");
				} else {
					clfbxd.setStatue(FybxdService.CW_CSTG);//已初审
					clfbxd.setHandleId(user.getId());//处理意见人id
					clfbxd.setHandleName(user.getName());//处理意见人
					clfbxd.setHandleOPinion(yijian);//处理意见
					clfbxd.setHandleTime(new Date());//处理时间
					clfbxdManager.save(clfbxd);
					
					map.put("success", true);
				}
			}
			return map;
		}
		
		
		
		//初审不通过
				@RequestMapping(value = "submitcsno")
				@ResponseBody
				public HashMap<String, Object> submitcsno(String id,String yijian) throws Exception {
					HashMap<String, Object> map = new HashMap<String, Object>();
					CurrentSessionUser user = SecurityUtil.getSecurityUser();
					if (id.isEmpty()){
						map.put("success", false);
						map.put("msg", "所选业务ID为空！");
					} else {
						Clfbxd clfbxd = clfbxdManager.get(id);
						if(clfbxd==null){
							map.put("success", false);
							map.put("msg", "数据获取失败！");
						} else {
							clfbxd.setStatue(FybxdService.CW_TH);//已退回
							clfbxd.setHandleId(user.getId());//处理意见人id
							clfbxd.setHandleName(user.getName());//处理意见人
							clfbxd.setHandleOPinion(yijian);//处理意见
							clfbxd.setHandleTime(new Date());
							clfbxdManager.save(clfbxd);
							
							map.put("success", true);
						}
					}
					return map;
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
				/**
				 * 报销确认
				 * @param request
				 * @param repayment_man
				 * @return
				 * @throws Exception
				 */
				@RequestMapping(value = "bxsubmit")
				@ResponseBody
				public HashMap<String, Object> bxsubmit(HttpServletRequest request,String repayment_man) throws Exception {
					HashMap<String, Object> map = new HashMap<String, Object>();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//					request.setCharacterEncoding("UTF-8");
					String id=request.getParameter("id").toString();
					String check=request.getParameter("check").toString();
//					String repayment_man_id=request.getParameter("borrowerId").toString();
//					String repayment_man=request.getParameter("repayment_man");
//					String repayment_time=request.getParameter("repayment_time").toString();
					if (id.isEmpty()){
						map.put("success", false);
						map.put("msg", "所选业务ID为空！");
					} else {
						Clfbxd clfbxd = clfbxdManager.get(id);
						if(clfbxd==null){
							map.put("success", false);
							map.put("msg", "数据获取失败！");
						} else {
							CurrentSessionUser user = SecurityUtil.getSecurityUser();
							//取消确认				
							if(check.equals("0")){
								clfbxd.setBxQrId(null);//确认处理人ID
								clfbxd.setBxQrName(null);//确认处理人姓名
								clfbxd.setBxQrTime(null);//确认处理时间
								clfbxd.setStatue(CwDrawmoneyManager.CW_CSTG);//反写回审批通过状态			
							}else if(check.equals("1")){
								clfbxd.setBxQrId(user.getId());//确认处理人ID
								clfbxd.setBxQrName(user.getName());//确认处理人姓名
								clfbxd.setBxQrTime(new Date());//确认处理时间
								clfbxd.setStatue(PxfbxdManager.CW_FY_BXYES);//已报销	
							}
							clfbxdManager.save(clfbxd);
							
							map.put("success", true);
						}
					}
					return map;
				}
				/**
				 * 批量报销确认
				 * @param request
				 * @param repayment_man
				 * @return
				 * @throws Exception
				 */
				@RequestMapping(value = "bxsubmits")
				@ResponseBody
				public HashMap<String, Object> bxsubmits(HttpServletRequest request,String repayment_man) throws Exception {
					CurrentSessionUser user = SecurityUtil.getSecurityUser();
					String ids=request.getParameter("ids").toString();
					for(String id:ids.split(",")){
						Clfbxd clfbxd = clfbxdManager.get(id);
						clfbxd.setBxQrId(user.getId());//确认处理人ID
						clfbxd.setBxQrName(user.getName());//确认处理人姓名
						clfbxd.setBxQrTime(new Date());//确认处理时间
						clfbxd.setStatue(PxfbxdManager.CW_FY_BXYES);//已报销	
						clfbxdManager.save(clfbxd);
					}
					return JsonWrapper.successWrapper(ids);
				}
}
