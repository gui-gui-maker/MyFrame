package com.lsts.finance.web;

import com.fwxm.outstorage.bean.Tjy2ChCkStatus;
import com.fwxm.outstorage.service.Tjy2ChCkManager;
import com.fwxm.supplies.bean.Warehousing;
import com.fwxm.supplies.service.WarehousingManager;
import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.finance.bean.Fybxd;
import com.lsts.finance.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.*;

//import com.lsts.finance.service.Tjy2FcpManager;

/**
 * 流程配置控制器
 *
 * @author GaoYa
 * @ClassName BaseFlowConfigAction
 * @JDK 1.7
 * @date 2014-03-11 下午02:35:00
 */
@Controller
@RequestMapping("lsts/finance")
public class FybxdAction extends
        SpringSupportAction<Fybxd, FybxdService> {

    @Autowired
    private FybxdService fybxdService;
	
	/*@Autowired
	private Tjy2FcpManager tjy2FcpManager;*/

    @Autowired
    private FlowExtManager flowExtManager;

    @Autowired
    private ActivityManager activityManager;

    @Autowired
    private WarehousingManager warehousingManager;

    @Autowired
    private Tjy2ChCkManager tjy2ChCkManager;


    /*  public HashMap<String, Object> save(HttpServletRequest request,Fybxd fybxd) throws Exception{
            CurrentSessionUser user = SecurityUtil.getSecurityUser();
            fybxd.setUser(user.getName());
            fybxd.setUserId(user.getId());
            System.out.println("费用填报-登记人为"+FybxdService.CW_FY_REGISTER);
            fybxd.setStatus(FybxdService.CW_FY_REGISTER);
            return super.save(request,fybxd);

        }*/
    @Override
    public synchronized HashMap<String, Object> save(HttpServletRequest request, @RequestBody Fybxd fybxd) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        System.out.println(fybxd.getStatus());
        if (fybxd.getStatus().equals(fybxdService.CW_CSTG)) {

            map.put("ts", "此条数据已通过不可修改");
            map.put("success", false);


        } else {
//			String userid = fybxd.getUserId();
            //		System.out.println(fybxd.getId());
            String rkdh = request.getParameter("rkdh");
            String ckids = request.getParameter("ckdids");
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("rkdh", rkdh);
            paramMap.put("ckdids", ckids);
            fybxdService.saveTask1(fybxd, user, paramMap);
            //tjy2FcpManager.savefybxd(fybxd,fybxd.getId(),user.getId(),user.getName(), fybxd.getTotal());
			
			
		    /*if(userid.equals(user.getId()) ){
		    	String mid = fybxdService.getmid(userid);
		    	fybxd.setUserId(mid);
		    	
		    }	*/


            map.put("success", true);
        }
        return map;

    }

    @Override
    public HashMap<String, Object> delete(String ids) throws Exception {
        // TODO Auto-generated method stub
        HashMap<String, Object> map = new HashMap<String, Object>();
        Fybxd cwMoney = fybxdService.get(ids);
        if (cwMoney.getStatus().equals(CwBorrowMoneyManager.CW_CSTG)) {
            map.put("msg", "此条数据已通过不可删除！");
            map.put("success", false);
        } else {
            fybxdService.delete(ids);
            map.put("msg", "数据删除成功！");
            map.put("success", true);
        }
        return map;
        //return JsonWrapper.successWrapper();
    }

    @RequestMapping(value = "save1")
    @ResponseBody
    public synchronized HashMap<String, Object> save1(HttpServletRequest request, @RequestBody Fybxd fybxd) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        CurrentSessionUser user = SecurityUtil.getSecurityUser();

//				String userid = fybxd.getUserId();


        fybxd.setStatus(CwBorrowMoneyManager.CW_CSTG);
        fybxd.setHandleId(user.getId());
        fybxd.setHandleName(user.getName());
        fybxd.setHandleTime(new Date());
			    /*if(userid.equals(user.getId()) ){
			    	
			    	String mid = fybxdService.getmid(userid);
			    	fybxd.setUserId(mid);
			    	
			    }	*/
        fybxdService.save(fybxd);

        map.put("success", true);
        return map;

    }


    /**
     * 提交单据
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "submit")
    @ResponseBody
    public HashMap<String, Object> submit(String id) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (id.isEmpty()) {
            map.put("success", false);
            map.put("msg", "所选业务ID为空！");
        } else {
            Fybxd fybxd = fybxdService.get(id);
            if (fybxd == null) {
                map.put("success", false);
                map.put("msg", "数据获取失败！");
            } else {

                fybxd.setStatus(FybxdService.CW_SUBMIT);//已提交

                fybxdService.save(fybxd);

                map.put("success", true);
            }
        }
        return map;
    }

    //作废单据
    @RequestMapping(value = "submitzf")
    @ResponseBody
    public HashMap<String, Object> submitzf(String id) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        if (id.isEmpty()) {
            map.put("success", false);
            map.put("msg", "所选业务ID为空！");
        } else {
            Fybxd fybxd = fybxdService.get(id);
            if (fybxd == null) {
                map.put("success", false);
                map.put("msg", "数据获取失败！");
            } else {

                fybxd.setStatus(ClfbxdManager.CW_DJZF);//已提交
                fybxd.setAbolishTime(new Date());
                fybxd.setAbolishName(user.getName());
                System.out.println(fybxd.getHandleTime() == null);
                System.out.println(fybxd.getHandleTime());
                if (fybxd.getHandleTime() != null) {
                    boolean bj = isSameDate(new Date(), fybxd.getHandleTime());
                    System.out.println(bj);
                    //1报销时间和审核通过相同2为不相同
                    if (bj) {
                        fybxd.setAbolish("1");
                    } else {
                        fybxd.setAbolish("2");
                    }
                }
                fybxdService.save(fybxd);
                //入库单标记报销作废
                List<Warehousing> list = warehousingManager.getWarehousingByFybxId(fybxd.getId());
                for (Warehousing entity : list) {
                	entity.setBz_zt("2");//标记作废
                	warehousingManager.save(entity);
				}
                //标记出库单报销作废
                tjy2ChCkManager.updateBxStatus(fybxd.getId(), Tjy2ChCkStatus.BX_BKBX);
                map.put("success", true);
            }
        }
        return map;
    }

    //初审通过
    @RequestMapping(value = "submitcs")
    @ResponseBody
    public HashMap<String, Object> submitcs(String id, String yijian) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        if (id.isEmpty()) {
            map.put("success", false);
            map.put("msg", "所选业务ID为空！");
        } else {
            Fybxd fybxd = fybxdService.get(id);
            if (fybxd == null) {
                map.put("success", false);
                map.put("msg", "数据获取失败！");
            } else {
                fybxd.setStatus(FybxdService.CW_CSTG);//已初审
                fybxd.setHandleId(user.getId());//处理意见人id
                fybxd.setHandleName(user.getName());//处理意见人
                fybxd.setHandleOPinion(yijian);//处理意见
                fybxd.setHandleTime(new Date());//处理时间
                fybxdService.save(fybxd);

                map.put("success", true);
            }
        }
        return map;
    }


    //初审不通过
    @RequestMapping(value = "submitcsno")
    @ResponseBody
    public HashMap<String, Object> submitcsno(String id, String yijian) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        if (id.isEmpty()) {
            map.put("success", false);
            map.put("msg", "所选业务ID为空！");
        } else {
            Fybxd fybxd = fybxdService.get(id);
            if (fybxd == null) {
                map.put("success", false);
                map.put("msg", "数据获取失败！");
            } else {
                fybxd.setStatus(FybxdService.CW_TH);//已退回
                fybxd.setHandleId(user.getId());//处理意见人id
                fybxd.setHandleName(user.getName());//处理意见人
                fybxd.setHandleOPinion(yijian);//处理意见
                fybxd.setHandleTime(new Date());
                fybxdService.save(fybxd);

                map.put("success", true);
            }
        }
        return map;
    }


    /**
     * 提交流程并改变状态（保留）
     *
     * @param id
     * @param flowId
     * @param typeCode
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "subFolw")
    @ResponseBody
    public HashMap<String, Object> subFolw(String id, String flowId,
                                           String typeCode, String status, String activityId) throws Exception {
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(FlowExtWorktaskParam.SERVICE_ID, id);
        map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
        map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
        map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
        map.put(FlowExtWorktaskParam.SERVICE_TITLE, "费用填报- " + user.getName());
        Fybxd fybxd = fybxdService.get(id);
        fybxd.setStatus(FybxdService.CW_FY_AUDIT);
        fybxdService.save(fybxd);

        if (StringUtil.isEmpty(id)) {
            return JsonWrapper.failureWrapper("参数错误！");
        } else {
            // 启动流程
            if (StringUtil.isNotEmpty(flowId)) {

                fybxdService.doStartProess(map);
            } else {
                return JsonWrapper.failureWrapper("流程ID为空！");
            }

            return JsonWrapper.successWrapper(id);
        }

    }


    @RequestMapping(value = "submitfybxd")
    @ResponseBody
    public HashMap<String, Object> submitfybxd(String areaFlag, String id,
                                               String typeCode, String activityId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(FlowExtWorktaskParam.SERVICE_ID, id);
        map.put(FlowExtWorktaskParam.SERVICE_TITLE, "费用报销填报");
        map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
        map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
        map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);

        Fybxd fybxd = fybxdService.get(id);
        fybxd.setStatus(FybxdService.CW_FY_AUDIT);
        fybxdService.save(fybxd);
        if (StringUtil.isEmpty(id)) {

            return JsonWrapper.failureWrapper("参数错误！");
        } else {
            // 启动流程
            if (StringUtil.isNotEmpty(activityId)) {
                if (areaFlag.equals("1") || areaFlag.equals("2") || areaFlag.equals("3")) {
                    flowExtManager.submitActivity(map);
                } else if (areaFlag.equals("4")) {
                    Fybxd fybxd1 = fybxdService.get(id);
                    fybxd1.setStatus(FybxdService.CW_FY_PASS);
                    fybxdService.save(fybxd1);
                    flowExtManager.submitActivity(map);
                } else {
                    Fybxd fybxd1 = fybxdService.get(id);
                    fybxd1.setStatus(FybxdService.CW_FY_NO_PASS);
                    fybxdService.save(fybxd1);
                }
            } else {
                return JsonWrapper.failureWrapper("流程ID为空！");
            }
            return JsonWrapper.successWrapper(id);
        }

    }

    /**
     * 退回审批流程并改变状态
     *
     * @param id
     * @param flowId
     * @param typeCode
     * @param status
     * @return
     * @throws Exception 已登记，已提交，审核中，审核通过，审核不通过
     */
    @RequestMapping(value = "fyth")
    @ResponseBody
    public HashMap<String, Object> cwth(String areaFlag, String id,
                                        String typeCode, String status, String activityId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put(FlowExtWorktaskParam.SERVICE_ID, id);
        map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
        map.put(FlowExtWorktaskParam.SERVICE_TITLE, "费用报销");
        map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
        map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);

        Fybxd fybxd = fybxdService.get(id);
        fybxd.setStatus(FybxdService.CW_FY_NO_PASS);
        fybxdService.save(fybxd);

        if (StringUtil.isEmpty(id)) {
            return JsonWrapper.failureWrapper("参数错误！");
        } else {
            // 退回流程
            if (StringUtil.isNotEmpty(activityId)) {
                flowExtManager.returnedActivity(map);
            } else {
                return JsonWrapper.failureWrapper("流程ID为空！");
            }
            return JsonWrapper.successWrapper(id);
        }

    }
    //获取打印数据


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "doPrintDetail")
    public ModelAndView doPrintDetail(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        String viewURL = request.getParameter("viewURL");
        String id = request.getParameter("id");//返回页面地址
        map = getPrintTagsData(request, id);
        return new ModelAndView(viewURL, map);
    }

    /**
     * 获取标签打印数据
     *
     * @param request
     * @param id      --
     *                设备ID
     * @return
     * @throws Exception
     */
    private Map<String, Object> getPrintTagsData(HttpServletRequest request, String id
    ) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();


        Map mapPara = new HashMap();

        Fybxd fybxd = fybxdService.get(id);

        //mapPara =TS_Util.convertBeanToMap(fybxd);


        System.out.println(fybxd.getBsDate().toString());
        System.out.println(fybxd.getBsDate().toString().split(" ")[0]);

        mapPara.put("BSDATE", fybxd.getBsDate() != null ? fybxd.getBsDate().toString().split(" ")[0] : "");
        mapPara.put("IDERTIFIER", fybxd.getIdentifier() != null ? fybxd.getIdentifier().toString() : "");
        mapPara.put("UNIT", fybxd.getUnit() != null ? fybxd.getUnit().toString() : "");

        mapPara.put("COSTITEM1", fybxd.getCostItem1() != null ? fybxd.getCostItem1().toString() : "");
        mapPara.put("COSTITEM2", fybxd.getCostItem2() != null ? fybxd.getCostItem2().toString() : "");
        mapPara.put("COSTITEM3", fybxd.getCostItem3() != null ? fybxd.getCostItem3().toString() : "");
        mapPara.put("COSTITEM4", fybxd.getCostItem4() != null ? fybxd.getCostItem4().toString() : "");
        mapPara.put("CLASS1", fybxd.getClass1() != null ? fybxd.getClass1().toString() : "");
        mapPara.put("CLASS2", fybxd.getClass2() != null ? fybxd.getClass2().toString() : "");
        mapPara.put("CLASS3", fybxd.getClass3() != null ? fybxd.getClass3().toString() : "");
        mapPara.put("CLASS4", fybxd.getClass4() != null ? fybxd.getClass4().toString() : "");
        mapPara.put("MONEY1", fybxd.getMoney1() != null ? fybxd.getMoney1().toString() : "");
        mapPara.put("MONEY2", fybxd.getMoney2() != null ? fybxd.getMoney2().toString() : "");
        mapPara.put("MONEY3", fybxd.getMoney3() != null ? fybxd.getMoney3().toString() : "");
        mapPara.put("MONEY4", fybxd.getMoney4() != null ? fybxd.getMoney4().toString() : "");
        mapPara.put("TOTAL", fybxd.getTotal() != null ? fybxd.getTotal().toString() : "");
        mapPara.put("PEOPLECONCERNED", fybxd.getPeopleConcerned() != null ? fybxd.getPeopleConcerned().toString() : "");
        mapPara.put("WORDFIGURE", fybxd.getWordFigure() != null ? fybxd.getWordFigure().toString() : "");
//		
        map.put("fybxd", mapPara);
        // 报检信息

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
     *
     * @param request
     * @param repayment_man
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "bxsubmit")
    @ResponseBody
    public HashMap<String, Object> bxsubmit(HttpServletRequest request, String repayment_man) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id").toString();
        String check = request.getParameter("check").toString();
//		String repayment_man_id=request.getParameter("borrowerId").toString();
//		String repayment_man=request.getParameter("repayment_man");
//		String repayment_time=request.getParameter("repayment_time").toString();
        if (id.isEmpty()) {
            map.put("success", false);
            map.put("msg", "所选业务ID为空！");
        } else {
            Fybxd fybxd = fybxdService.get(id);
            if (fybxd == null) {
                map.put("success", false);
                map.put("msg", "数据获取失败！");
            } else {
                CurrentSessionUser user = SecurityUtil.getSecurityUser();
                //取消确认
                if (check.equals("0")) {
                    fybxd.setBxQrId(null);//确认处理人ID
                    fybxd.setBxQrName(null);//确认处理人姓名
                    fybxd.setBxQrTime(null);//确认处理时间
                    fybxd.setStatus(CwDrawmoneyManager.CW_CSTG);//反写回审批通过状态
                    tjy2ChCkManager.updateBxStatus(fybxd.getId(), Tjy2ChCkStatus.BX_BXZ);
                    //审核通过后修改入库单，标记已报销
               		List<Warehousing> list=warehousingManager.getWarehousingByFybxId(id);
               		for (Warehousing warehousing : list) {
            			warehousing.setBz_zt(null);
            			warehousingManager.save(warehousing);
            		}
                    
                } else if (check.equals("1")) {
                    fybxd.setBxQrId(user.getId());//确认处理人ID
                    fybxd.setBxQrName(user.getName());//确认处理人姓名
                    fybxd.setBxQrTime(new Date());//确认处理时间
                    fybxd.setStatus(PxfbxdManager.CW_FY_BXYES);//已报销
                    tjy2ChCkManager.updateBxStatus(fybxd.getId(), Tjy2ChCkStatus.BX_YBX);
                    //审核通过后修改入库单，标记已报销
               		List<Warehousing> list=warehousingManager.getWarehousingByFybxId(id);
               		for (Warehousing warehousing : list) {
            			warehousing.setBz_zt("1");
            			warehousingManager.save(warehousing);
            		}
                }
                fybxdService.save(fybxd);
                map.put("success", true);
            }
        }
        return map;
    }

    /**
     * 批量报销确认
     *
     * @param request
     * @param repayment_man
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "bxsubmits")
    @ResponseBody
    public HashMap<String, Object> bxsubmits(HttpServletRequest request, String repayment_man) throws Exception {
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        String ids = request.getParameter("ids").toString();
        for (String id : ids.split(",")) {
            Fybxd fybxd = fybxdService.get(id);
            fybxd.setBxQrId(user.getId());//确认处理人ID
            fybxd.setBxQrName(user.getName());//确认处理人姓名
            fybxd.setBxQrTime(new Date());//确认处理时间
            fybxd.setStatus(PxfbxdManager.CW_FY_BXYES);//已报销
            tjy2ChCkManager.updateBxStatus(fybxd.getId(), Tjy2ChCkStatus.BX_YBX);
            fybxdService.save(fybxd);
        }
        return JsonWrapper.successWrapper(ids);
    }

    @RequestMapping(value = "getWarehousingByFybxId")
    @ResponseBody
    public HashMap<String, Object> getWarehousingBh(HttpServletRequest request, String fybxId) {
        try {
            List<Warehousing> list = warehousingManager.getWarehousingByFybxId(fybxId);
            return JsonWrapper.successWrapper(list);
        } catch (Exception e) {
            System.err.println(e);
            return JsonWrapper.failureWrapperMsg("查询入库单号失败！");
        }

    }
}
