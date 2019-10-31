package com.scts.car.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rbac.impl.manager.OrgManagerImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.khnt.weixin.enums.EnumMethod;
import com.khnt.weixin.interceptor.OAuthRequired;
import com.khnt.weixin.util.HttpRequestUtil;
import com.khnt.weixin.util.Result;
import com.khnt.weixin.util.WxUtil;
import com.lsts.employee.service.EmployeesService;
import com.scts.car.bean.CarApply;
import com.scts.car.bean.CarApplyDTO;
import com.scts.car.service.CarApplyService;
import com.scts.weixin.app.service.WeixinAppInfoManager;

import net.sf.json.JSONObject;
import util.TS_Util;

/**
 * 公务用车申请管理控制器
 * 
 * @ClassName CarApplyAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2018-06-27 下午02:16:00
 */
@Controller
@RequestMapping("car/apply")
public class CarApplyAction extends
		SpringSupportAction<CarApply, CarApplyService> {
	@Autowired
	private CarApplyService carApplyService;
	@Autowired
    private EmployeesService  employeesService;
	@Autowired
	private OrgManagerImpl orgManager;
	@Autowired
    private WeixinAppInfoManager  weixinAppInfoManager;
	

	// 保存
	@RequestMapping(value = "saveBasic")
	@ResponseBody
	public HashMap<String, Object> saveBasic(HttpServletRequest request, CarApply carApply) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		Org org = TS_Util.getCurOrg(curUser);
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			// 1a、验证用车部门当前用车数量（每个检验部门最多派三辆车）
			if (StringUtil.isNotEmpty(carApply.getUse_dep_id())) {
				Org use_org = orgManager.get(carApply.getUse_dep_id());
				if (use_org != null) {
					if ((use_org.getOrgCode().contains("jd") || use_org.getOrgCode().contains("cy"))
							&& !use_org.getOrgCode().contains("center")) {
						List<CarApply> list = carApplyService.getCarApplyByUseDepId(carApply.getUse_dep_id(), carApply.getId());
						if (list != null) {
							if (list.size() >= 3) {
								wrapper.put("success", false);
								wrapper.put("msg", "亲，每个检验部门最多申请三辆车哦，您部门有车辆还未归还。");
								return wrapper;
							}
						}
					}
				}
			}
			// 1b、验证申请部门当前用车数量（每个申请部门最多派三辆车）2018年7月30日贺飞峙要求
			if (StringUtil.isNotEmpty(org.getId())) {
				Org use_org = orgManager.get(org.getId());
				if (use_org != null) {
					if ((use_org.getOrgCode().contains("jd") || use_org.getOrgCode().contains("cy"))
							&& !use_org.getOrgCode().contains("center")) {
						List<CarApply> list = carApplyService.getCarApplyByApplyDepId(org.getId(), carApply.getId());
						if (list != null) {
							if (list.size() >= 3) {
								wrapper.put("success", false);
								wrapper.put("msg", "亲，每个申请部门最多申请三辆车哦，您部门有车辆还未归还。");
								return wrapper;
							}
						}
					}
				}
			}
			// 2、验证日期有效性
			boolean doSave = false;
			if(carApply.getUse_start_date() != null && carApply.getUse_end_date() != null){
//				Date curTime = DateUtil.convertStringToDate(Constant.ymdhmsDatePattern, DateUtil.getCurrentDateTime());
//				if(carApply.getUse_end_date().before(curTime)) {
//					wrapper.put("success", false);
//					wrapper.put("msg", "亲，您所选的用车时间错误，“用车止”不能早于当前时间哦！");
//					return wrapper;
//				}
				if(carApply.getUse_start_date().after(carApply.getUse_end_date())) {
					wrapper.put("success", false);
					wrapper.put("msg", "亲，您所选的用车时间错误，“申请用车起”不能晚于“用车止”哦！");
					return wrapper;
				}else {
					doSave = true;
				}
			}

			if(doSave) {
				// 3、保存
				CarApply info = carApplyService.saveBasic(carApply, request);
				wrapper.put("success", true);
				wrapper.put("obj", info);
			}else {
				wrapper.put("success", false);
				wrapper.put("msg", "亲，您所选的用车时间错误，“申请用车起”不能等于“用车止”哦！");
				return wrapper;
			}
		} catch (Exception e) {
			log.debug(e.toString());
			wrapper.put("success", false);
			wrapper.put("msg", "保存失败！");
			e.printStackTrace();
		}
		return wrapper;
	}
	

	// 获取公务用车申请单信息
	@RequestMapping(value = "getDetail")
	@ResponseBody
	public HashMap<String, Object> getDetail(HttpServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		try {
			return carApplyService.getDetail(id);
		} catch (Exception e) {
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("读取用车申请信息失败！");
		}
	}
	

	// 删除
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(HttpServletRequest request, String ids)
			throws Exception {
		String[] idArr = ids.split(",");
		for (int i = 0; i < idArr.length; i++) {
			carApplyService.del(request, idArr[i]);
		}
		return JsonWrapper.successWrapper(ids);
	}
	
	// 查询流程步骤信息
	@RequestMapping(value = "getFlowStep")
	@ResponseBody
	public ModelAndView getFlowStep(HttpServletRequest request) throws Exception {
		Map<String, Object> map = carApplyService.getFlowStep(request.getParameter("id"));
		ModelAndView mav = new ModelAndView("app/car/flow_card", map);
		return mav;
	}
	
	// 用车申请单审核
    @RequestMapping(value = "checks")
    @ResponseBody
    public HashMap<String, Object> checks(@RequestBody CarApplyDTO entity, HttpServletRequest request) throws Exception {	
        return carApplyService.checks(request, entity);
    }
    
    // 用车申请单审核（微信）
    @RequestMapping(value = "wxChecks")
    @ResponseBody
    public HashMap<String, Object> wxChecks(HttpServletRequest request, CarApply carApply) throws Exception {	
        return carApplyService.wxChecks(request,carApply);
    }
    
    // 派车
    @RequestMapping(value = "assigns")
    @ResponseBody
    public HashMap<String, Object> assigns(@RequestBody CarApplyDTO entity, HttpServletRequest request) throws Exception {	
        return carApplyService.assigns(request, entity);
    }
    
    // 派车(微信)
    @RequestMapping(value = "wxAssigns")
    @ResponseBody
    public HashMap<String, Object> wxAssigns(HttpServletRequest request, CarApply carApply) throws Exception {	
        return carApplyService.wxAssigns(request, carApply);
    }
    
    // 收车
    @RequestMapping(value = "receives")
    @ResponseBody
    public HashMap<String, Object> receives(@RequestBody CarApplyDTO entity, HttpServletRequest request) throws Exception {	
        return carApplyService.receives(request, entity);
    }
    // 收车(微信)
    @RequestMapping(value = "wxReceives")
    @ResponseBody
    public HashMap<String, Object> wxReceives(HttpServletRequest request, CarApply carApply) throws Exception {	
        return carApplyService.wxReceives(request, carApply);
    }
    
    // 查询提取上一次该车收车时的公里止数
 	@RequestMapping(value = "queryCarKm")
 	@ResponseBody
 	public HashMap<String, Object> queryCarKm(HttpServletRequest request) throws Exception {
 		String fk_car_id = request.getParameter("fk_car_id");
 		String apply_id = request.getParameter("apply_id");
 		try {
 			String end_km = carApplyService.getEndKmByCarId(fk_car_id, apply_id);
 			return JsonWrapper.successWrapper(end_km);
 		} catch (Exception e) {
 			e.printStackTrace();
 			log.debug(e.toString());
 			return JsonWrapper.failureWrapperMsg("读取公里止数失败！");
 		}
 	}
 	
 	// 查询联系人电话
  	@RequestMapping(value = "queryMobileTel")
  	@ResponseBody
  	public HashMap<String, Object> queryMobileTel(HttpServletRequest request, String user_id) throws Exception {
  		HashMap<String, Object>  map = new HashMap<String, Object>();
  		try {
  			String mobile_tel = employeesService.getMobileByUserId(user_id);
  			map.put("success", true);
			map.put("data", mobile_tel);
			
  		} catch (Exception e) {
  			e.printStackTrace();
  			log.debug(e.toString());
  			map.put("success", false);
			map.put("msg", "查询联系人电话失败！系统繁忙，请稍后再试！");
  		}
  		return map;
  	}
 	
  	/**
	 * 加载个人信息，此处添加了@OAuthRequired注解
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "weixinUserInfo")
	@OAuthRequired
	public String weixinUserInfo(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        log.debug(this.getClass().getName() + " method weixinUserInfo Load a User start");
        if(session.getAttribute("Userid")==null || ((String)session.getAttribute("Userid")).equals("noUserId")) {
            //AccessToken accessToken = WxUtil.getAccessToken(Constants.CORPID, Constants.SECRET);
            String token = WxUtil.getAccessTokenString();
            log.debug(this.getClass().getName() + " method weixinUserInfo 获取到code："+request.getParameter("code"));
            log.debug(this.getClass().getName() + " method weixinUserInfo 获取到token："+token);
            if (StringUtil.isNotEmpty(token) && request.getParameter("code")!=null) {
                Result<String> result = WxUtil.oAuth2GetUserByCode(token, request.getParameter("code"), 7);
                String menuUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token="+token+"&userid="+result.getObj() ;
                log.debug(this.getClass().getName() + " method weixinUserInfo menuUrl："+menuUrl);
                JSONObject jo = HttpRequestUtil.httpRequest(menuUrl, EnumMethod.GET.name(), null);
                if(jo!=null){
                	if(jo.has("mobile")) {
						User user;
						try {
							user = weixinAppInfoManager.getUser(jo.getString("mobile"));
							if(user!=null) {
								session.setAttribute("Name", user.getName());
								session.setAttribute("Phone", jo.getString("mobile"));
								session.setAttribute("Account",user.getAccount());
								log.debug(this.getClass().getName() + " method weixinUserInfo 获取到用户信息，用户姓名："+user.getName()+"，用户手机号：" + jo.getString("mobile")+"，用户Account:"+user.getAccount());
							}else {
								session.setAttribute("error_msg", "亲，检验平台中未找到与该手机号匹配的用户信息");
								log.debug("亲，检验平台中未找到与该手机号匹配的用户信息");
								return "app/weixininfo/app_info/weixin_error_page";
							}
						} catch (Exception e) {
							log.debug(e.getMessage());
							return "app/weixininfo/app_info/weixin_error_page";
						}
					}else {
						session.setAttribute("error_msg", "亲，微信接口服务出现异常，未找到手机号");
						log.debug("亲，微信接口服务出现异常，未找到手机号");
						return "app/weixininfo/app_info/weixin_error_page";
					}
                }else {
                	session.setAttribute("error_msg", "亲，微信接口服务出现异常，未获取到微信企业用户信息");
					log.debug("亲，微信接口服务出现异常，未获取到微信企业用户信息");
					return "app/weixininfo/app_info/weixin_error_page";
				}
                session.setAttribute("Userid", result.getObj());
                //session.setAttribute("AccessToken", accessToken.getToken());
                
            } else {
            	session.setAttribute("Userid", "noUserId");
            	session.setAttribute("Name", "noUserName");
            	session.setAttribute("Phone", "noUserPhone");
            	//session.setAttribute("Account", wxLeaveManager.getAccount("18980021021"));//明子涵，本地测试用
            	//session.setAttribute("Account", wxLeaveManager.getAccount("13980717755"));//张展彬，本地测试用
            	//session.setAttribute("Account", wxLeaveManager.getAccount("18190738531"));//纪刚，本地测试用
            	//session.setAttribute("Account", wxLeaveManager.getAccount("13060002978"));//阳晓薇，本地测试用
            	//session.setAttribute("Account", wxLeaveManager.getAccount("13308036073"));//贺飞峙，本地测试用
            }
        }
        log.debug(this.getClass().getName() + " method weixinUserInfo Load a User end");
        session.setAttribute("businessId", request.getParameter("businessId"));
        session.setAttribute("businessStatus", request.getParameter("businessStatus"));
		model.addAttribute("Userid", session.getAttribute("Userid"));
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!"+session.getAttribute("Userid"));
		return "app/car/weixin/transfer_page";
	}
	
	/**
	 * 微信端获取待办事项信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
 	@RequestMapping(value = "queryCheckList")
 	@ResponseBody
 	public HashMap<String, Object> queryCheckList(HttpServletRequest request) throws Exception {
 		CurrentSessionUser cur_user = SecurityUtil.getSecurityUser();
 		String userId = cur_user.getId();
 		String orgId = cur_user.getDepartment().getId();
 		
 		Map<String, String> roles = cur_user.getRoles();
 		String car_apply_check_status = "";
 		for(String roleid : roles.keySet()){
 			Object obj = roles.get(roleid);
 			if("部门负责人".equals(obj)){
 				car_apply_check_status = "0";
 			}
 			if("公务用车申请-办公室负责人审核".equals(obj)){
 				car_apply_check_status = "1";
 				break;
 			}
 			if("公务用车申请-分管院领导审核".equals(obj)){
 				car_apply_check_status = "2";
 				break;
 			}
 			if("公务用车申请-车队派车/收车".equals(obj)){
 				car_apply_check_status = "4";
 			}
 			if("公务用车申请-车队负责人审核".equals(obj)){
 				car_apply_check_status = "3";
 				break;
 			}
 		}
 		try {
 			List<CarApply> carApplyList = carApplyService.queryCheckList(car_apply_check_status,userId,orgId);
 			return JsonWrapper.successWrapper(carApplyList);
 		} catch (Exception e) {
 			e.printStackTrace();
 			log.debug(e.toString());
 			return JsonWrapper.failureWrapperMsg("获取待办事项失败！");
 		}
 	}
 	/**
	 * 微信端获取我的申请事项信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
 	@RequestMapping(value = "queryWdsqList")
 	@ResponseBody
 	public HashMap<String, Object> queryWdsqList(HttpServletRequest request) throws Exception {
 		CurrentSessionUser cur_user = SecurityUtil.getSecurityUser();
 		String userId = cur_user.getId();
 		String orgId = cur_user.getDepartment().getId();
 		String car_apply_check_status = "";
 		try {
 			List<CarApply> carApplyList = carApplyService.queryWdsqList(car_apply_check_status,userId,orgId);
 			return JsonWrapper.successWrapper(carApplyList);
 		} catch (Exception e) {
 			e.printStackTrace();
 			log.debug(e.toString());
 			return JsonWrapper.failureWrapperMsg("获取我的申请事项失败！");
 		}
 	}
 	/**
	 * 微信端获取已办理事项信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
 	@RequestMapping(value = "queryYblList")
 	@ResponseBody
 	public HashMap<String, Object> queryYblList(HttpServletRequest request) throws Exception {
 		CurrentSessionUser cur_user = SecurityUtil.getSecurityUser();
 		String userId = cur_user.getId();
 		String orgId = cur_user.getDepartment().getId();
 		String car_apply_check_status = "";
 		try {
 			List<CarApply> carApplyList = carApplyService.queryYblList(car_apply_check_status,userId,orgId);
 			return JsonWrapper.successWrapper(carApplyList);
 		} catch (Exception e) {
 			e.printStackTrace();
 			log.debug(e.toString());
 			return JsonWrapper.failureWrapperMsg("获取已办理事项失败！");
 		}
 	}
 	/**
 	 * 获取部门信息
 	 * @param request
 	 * @return
 	 * @throws Exception
 	 */
 	@RequestMapping(value = "getOrgList")
	@ResponseBody
	public HashMap<String, Object> getOrgList(HttpServletRequest request)
			throws Exception {
		try {
			return carApplyService.getOrgList();
		} catch (Exception e) {
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("读取部门信息失败！");
		}
	}
 	/**
 	 * 获取人员信息
 	 * @param request
 	 * @return
 	 * @throws Exception
 	 */
 	@RequestMapping(value = "getUserList")
	@ResponseBody
	public HashMap<String, Object> getUserList(HttpServletRequest request)
			throws Exception {
		try {
			String org_id = request.getParameter("org_id");
			return carApplyService.getUserList(org_id);
		} catch (Exception e) {
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("读取人员信息失败！");
		}
	}
 	/**
 	 * 获取车辆信息
 	 * @param request
 	 * @return
 	 * @throws Exception
 	 */
 	@RequestMapping(value = "getCarList")
	@ResponseBody
	public HashMap<String, Object> getCarList(HttpServletRequest request)
			throws Exception {
		try {
			return carApplyService.getCarList();
		} catch (Exception e) {
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("读取车辆信息失败！");
		}
	}
 	

 	/**
 	 * 作废
 	 * @param request
 	 * @param carApply
 	 * @return
 	 */
 	@RequestMapping(value = "zuoFei")
	@ResponseBody
 	public HashMap<String, Object> zuoFei(HttpServletRequest request, String id){
 		try {
			return carApplyService.zuofei(request,id);
		} catch (Exception e) {
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("数据作废失败！！");
		}
 	}
}
