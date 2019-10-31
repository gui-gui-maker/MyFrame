package com.lsts.finance.web;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.springResolver.RequestBean;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.finance.bean.Clfbxd;
import com.lsts.finance.bean.CwBorrowMoney;
import com.lsts.finance.bean.CwDrawmoney;
import com.lsts.finance.bean.Fybxd;
import com.lsts.finance.bean.Pxfbxd;
import com.lsts.finance.service.ClfbxdManager;
import com.lsts.finance.service.CwBorrowMoneyManager;
import com.lsts.finance.service.CwDrawmoneyManager;
import com.lsts.finance.service.FybxdService;
import com.lsts.finance.service.PxfbxdManager;


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
@RequestMapping("finance/pxfbxd")
public class PxfbxdAction<Ayou> extends SpringSupportAction<Pxfbxd, PxfbxdManager> {

	// 必须提供Demo实体的manager对象，使用注解@Autowired标识为自动装配
	@Autowired
	private PxfbxdManager pxfbxdManager;
	
	@Autowired
	private FlowExtManager flowExtManager;
	
	@Autowired
	private ActivityManager activityManager;
	
	@Autowired
	private FybxdService fybxdService;
	
	/*  public HashMap<String, Object> save(HttpServletRequest request,Clfbxd clfbxd) throws Exception{
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			
			System.out.println("差旅费填报-登记人为"+clfbxdManager.CW_FY_REGISTER);
			clfbxd.setStatue(FybxdService.CW_FY_REGISTER);
	    	return super.save(request,clfbxd);
	    	
	    }*/
	@RequestMapping(value = "save1")
	@ResponseBody
	public synchronized HashMap<String, Object> save1(HttpServletRequest request,@RequestBody Pxfbxd pxfbxd) throws Exception{
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			HashMap<String, Object> map = new HashMap<String, Object>();
			try {
				
				if( fybxdService.CW_CSTG.equals(pxfbxd.getStatue())){
					
					map.put("ts", "此条数据已通过不可修改");
					map.put("success", false);
				}else{
					pxfbxdManager.saveTask1(pxfbxd,user);
					map.put("success", true);
				}
			} catch (Exception e) {
				e.printStackTrace();
				map.put("success", false);
				map.put("msg", e.getMessage());
			}
			return map;
	}
	
	@Override
	public HashMap<String, Object> delete(String ids) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		Pxfbxd cwMoney=pxfbxdManager.get(ids);
  		if(cwMoney.getStatue().equals(CwBorrowMoneyManager.CW_CSTG)){
			map.put("msg", "此条数据已通过不可删除！");
			map.put("success", false);
  		}else{
  			pxfbxdManager.delete(ids);
			map.put("msg", "数据删除成功！");
			map.put("success", true);
		}
		return map;
		//return JsonWrapper.successWrapper();
	}
	
	@RequestMapping(value = "save2")
	@ResponseBody
	 public synchronized HashMap<String, Object> save2(HttpServletRequest request,@RequestBody Pxfbxd pxfbxd) throws Exception{
			HashMap<String, Object> map = new HashMap<String, Object>();
				CurrentSessionUser user = SecurityUtil.getSecurityUser();
				
//				String userid = fybxd.getUserId();
				
					
				pxfbxd.setStatue(CwBorrowMoneyManager.CW_CSTG);
				pxfbxd.setHandleId(user.getId());
				pxfbxd.setHandleName(user.getName());
				pxfbxd.setHandleTime(new Date());
			    /*if(userid.equals(user.getId()) ){
			    	
			    	String mid = fybxdService.getmid(userid);
			    	fybxd.setUserId(mid);
			    	
			    }	*/
				pxfbxdManager.save(pxfbxd);
			    map.put("success", true);
				return map;
		    }
	  
	 
		/**提交单据
		 * @param id
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "submit")
		@ResponseBody
		public HashMap<String, Object> submit(String id) throws Exception {
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (id.isEmpty()){
				map.put("success", false);
				map.put("msg", "所选业务ID为空！");
			} else {
				Pxfbxd pxfbxd = pxfbxdManager.get(id);
				if(pxfbxd==null){
					map.put("success", false);
					map.put("msg", "数据获取失败！");
				} else {
					//clfbxd.setStatue(ClfbxdManager.CW_SUBMIT);//已提交
					//pxfbxdManager.save(clfbxd);
					try{
						
						pxfbxdManager.submit(pxfbxd);
						map.put("success", true);
					}catch(Exception e){
						map.put("success", false);
						map.put("msg", e.getMessage());
					}
				}
			}
			return map;
		}
		//作废单据
		@RequestMapping(value = "invalidate")
		@ResponseBody
		public HashMap<String, Object> invalidate(String id) throws Exception {
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			try {
				pxfbxdManager.invalidate(id);
				map.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("success", false);
				map.put("msg", e.getMessage());
			}
					
			return map;
		}
		//作废单据
		@RequestMapping(value = "submitzf")
		@ResponseBody
		public HashMap<String, Object> submitzf(String id) throws Exception {
			HashMap<String, Object> map = new HashMap<String, Object>();
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			
			if (id.isEmpty()){
				map.put("success", false);
				map.put("msg", "所选业务ID为空！");
			} else {
				Pxfbxd clfbxd = pxfbxdManager.get(id);
				if(clfbxd==null){
					map.put("success", false);
					map.put("msg", "数据获取失败！");
				} else {
					clfbxd.setStatue(ClfbxdManager.CW_DJZF);//已提交
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
					pxfbxdManager.save(clfbxd);
					
					map.put("success", true);
				}
			}
			return map;
		}
		
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "getBeanMap")
		public String getBeanMap(String id,HttpServletRequest request) throws Exception {
			Map<String, String> map = new HashMap<String, String>();
			Pxfbxd bean = pxfbxdManager.get(id);
			map = bean.beanToMap();
			request.setAttribute("infoMap",map);
			request.setAttribute("templetName","CLVYBXD");
			return "app/finance/cw_borrow_print";
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
				Pxfbxd clfbxd = pxfbxdManager.get(id);
				if(clfbxd==null){
					map.put("success", false);
					map.put("msg", "数据获取失败！");
				} else {
					clfbxd.setStatue(FybxdService.CW_CSTG);//已初审
					clfbxd.setHandleId(user.getId());//处理意见人id
					clfbxd.setHandleName(user.getName());//处理意见人
					clfbxd.setHandleOPinion(yijian);//处理意见
					clfbxd.setHandleTime(new Date());//处理时间
					pxfbxdManager.save(clfbxd);
					
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
						Pxfbxd clfbxd = pxfbxdManager.get(id);
						if(clfbxd==null){
							map.put("success", false);
							map.put("msg", "数据获取失败！");
						} else {
							clfbxd.setStatue(FybxdService.CW_TH);//已退回
							clfbxd.setHandleId(user.getId());//处理意见人id
							clfbxd.setHandleName(user.getName());//处理意见人
							clfbxd.setHandleOPinion(yijian);//处理意见
							clfbxd.setHandleTime(new Date());
							pxfbxdManager.save(clfbxd);
							
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
						Pxfbxd pxfbxd = pxfbxdManager.get(id);
						if(pxfbxd==null){
							map.put("success", false);
							map.put("msg", "数据获取失败！");
						} else {
							CurrentSessionUser user = SecurityUtil.getSecurityUser();
							//取消确认				
							if(check.equals("0")){
								pxfbxd.setBxQrId(null);//确认处理人ID
								pxfbxd.setBxQrName(null);//确认处理人姓名
								pxfbxd.setBxQrTime(null);//确认处理时间
								pxfbxd.setStatue(CwDrawmoneyManager.CW_CSTG);//反写回审批通过状态			
							}else if(check.equals("1")){
								pxfbxd.setBxQrId(user.getId());//确认处理人ID
								pxfbxd.setBxQrName(user.getName());//确认处理人姓名
								pxfbxd.setBxQrTime(new Date());//确认处理时间
								pxfbxd.setStatue(PxfbxdManager.CW_FY_BXYES);//已报销	
							}
							pxfbxdManager.save(pxfbxd);
							
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
						Pxfbxd pxfbxd = pxfbxdManager.get(id);
						pxfbxd.setBxQrId(user.getId());//确认处理人ID
						pxfbxd.setBxQrName(user.getName());//确认处理人姓名
						pxfbxd.setBxQrTime(new Date());//确认处理时间
						pxfbxd.setStatue(PxfbxdManager.CW_FY_BXYES);//已报销	
						pxfbxdManager.save(pxfbxd);
					}
					return JsonWrapper.successWrapper(ids);
				}
}
