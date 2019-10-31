package com.fwxm.recipients.service;

import com.fwxm.material.bean.GoodsAndOrder;
import com.fwxm.material.dao.GoodsAndOrderDao;
import com.fwxm.outstorage.bean.OutStorageParam;
import com.fwxm.outstorage.inf.OutStorageExtends;
import com.fwxm.recipients.bean.Tjy2ChLq;
import com.fwxm.recipients.bean.Tjy2ChLqStatus;
import com.fwxm.recipients.dao.Tjy2ChLqDao;
import com.khnt.base.Factory;
import com.khnt.bpm.communal.BpmOrg;
import com.khnt.bpm.communal.BpmOrgImpl;
import com.khnt.bpm.communal.BpmUser;
import com.khnt.bpm.communal.BpmUserImpl;
import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.bean.Process;
import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.core.service.ProcessManager;
import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.ext.bean.FlowServiceConfig;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.bpm.ext.service.FlowServiceConfigManager;
import com.khnt.bpm.ext.support.FlowExtParam;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rbac.impl.dao.OrgDao;
import com.khnt.rbac.impl.dao.UserDao;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.humanresources.bean.OrgidLeaderid;
import com.lsts.humanresources.service.OrgidLeaderidManager;
import com.lsts.log.bean.SysLog;
import com.lsts.log.service.SysLogService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TryCatchFinally;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class Tjy2ChLqManager extends OutStorageExtends<Tjy2ChLq, Tjy2ChLqDao> {
    @Autowired
    Tjy2ChLqDao tjy2ChLqDao;

    @Autowired
    GoodsAndOrderDao goodsAndOrderDao;

    @Autowired
    FlowExtManager flowExtManager;

    @Autowired
    ActivityManager activityManager;

    @Autowired
    ProcessManager processManager;
    @Autowired
    SysLogService logService;
    @Autowired
    OrgDao orgDao;
    @Autowired
    UserDao userDao;
    @Autowired
    OrgidLeaderidManager orgidLeaderidManager;
    @Autowired
    FlowServiceConfigManager flowServiceConfigManager;

    @Autowired
    MessageService messageService;

    public static String URL = "";
    public static boolean IS_DEBUG = false;
    public static String DEBUG_PHONE;

    static {
        URL = Factory.getSysPara().getProperty("CHLQ_WX_SEND_URL");
        String isDebug = Factory.getSysPara().getProperty("CHLQ_WX_SEND_IS_DEBUG");
        if (StringUtil.isNotEmpty(isDebug)) {
            try {
                IS_DEBUG = Boolean.parseBoolean(isDebug);
            } catch (Exception e) {
                IS_DEBUG = false;
            }
        }
        DEBUG_PHONE = Factory.getSysPara().getProperty("CHLQ_WX_SEND_DEBUG_PHONE");
    }

    public final static String MSG_CODE = "ch_lq_apply";

    /**
     * 存货领取，原始版本一对多，保存领取物品，由保障部工作人员对物品进行修改
     * 从20181024开始废弃不使用
     *
     * @param entity
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> savechlq(@RequestBody Tjy2ChLq entity) throws Exception {
        List<GoodsAndOrder> goodsAndOrderDaoList = entity.getGoods();
        if (StringUtil.isNotEmpty(entity.getId())) {
            //先删除原来的，再新增。
            Tjy2ChLq source = tjy2ChLqDao.get(entity.getId());
            if (Tjy2ChLqStatus.YCK.equals(source.getStatus())) {
                throw new KhntException("不能修改已出库的领取单");
            }
            for (GoodsAndOrder gad : source.getGoods()) {
                goodsAndOrderDao.remove(gad);
            }
            tjy2ChLqDao.remove(source);
            entity.setId(null);
            entity.setLqBh(source.getLqBh());
        } else {
            entity.setLqBh(getLQBH(entity.getCreateTime()));
        }
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        entity.setGoods(null);
        entity.setCreateId(user.getId());
        entity.setCreateName(user.getName());
        entity.setCreateOrgId(user.getDepartment().getId());
        entity.setCreateOrgName(user.getDepartment().getOrgName());
        entity.setCreateUnitId(user.getUnit().getId());
        entity.setCreateUnitName(user.getUnit().getOrgName());
        Date now = new Date();
        entity.setCreateTime(now);
        entity.setStatus(Tjy2ChLqStatus.WTJ); //未领取
        tjy2ChLqDao.save(entity);
        for (GoodsAndOrder gad : goodsAndOrderDaoList) {
            gad.setType("LQ");
            gad.setCreate_time(now);
            gad.setStatus("1");
            gad.setFk_order_id(entity.getId());
            goodsAndOrderDao.save(gad);
        }
        return JsonWrapper.successWrapper(entity);

    }

    /**
     * 常规保存，将领用物品写在textarea中，由保障部人员自行添加。
     *
     * @param entity
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> usualSave(@RequestBody Tjy2ChLq entity, String status) throws Exception {
        if (StringUtil.isNotEmpty(entity.getId())) {
            //先删除原来的，再新增。
            Tjy2ChLq source = tjy2ChLqDao.get(entity.getId());
            if (Tjy2ChLqStatus.YCK.equals(source.getStatus())) {
                throw new KhntException("不能修改已出库的领取单");
            }
            tjy2ChLqDao.remove(source);
            entity.setId(null);
            entity.setLqBh(source.getLqBh());
        } else {
            entity.setLqBh(getLQBH(entity.getCreateTime()));
        }
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        entity.setGoods(null);
        entity.setCreateId(user.getId());
        entity.setCreateName(user.getName());
        entity.setCreateOrgId(user.getDepartment().getId());
        entity.setCreateOrgName(user.getDepartment().getOrgName());
        entity.setCreateUnitId(user.getUnit().getId());
        entity.setCreateUnitName(user.getUnit().getOrgName());
        entity.setStatus(status);
        tjy2ChLqDao.save(entity);
        if("3".equals(status)){
        	logService.setLogs(entity.getId(), "直接出库", "直接出库", user.getId(), user.getName(), new Date(),
                   null);
        }
        return JsonWrapper.successWrapper(entity);
    }

    /**
     * 后台提交流程
     *
     * @param request
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> subFlow(HttpServletRequest request) throws Exception {
        //流程传参
        String serviceId = request.getParameter("serviceId");
        String flowId = request.getParameter("flowId");
        String typeCode = request.getParameter("typeCode");
        String isYgje = request.getParameter("isYgje");
        return _startFlow(request, serviceId, flowId, typeCode, isYgje);
    }

    private HashMap<String, Object> _startFlow(HttpServletRequest request, String serviceId, String flowId, String typeCode, String isYgje) throws Exception {
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(FlowExtWorktaskParam.SERVICE_ID, serviceId);
        map.put(FlowExtWorktaskParam.SERVICE_TITLE, "存货领取申请-" + user.getName());
        map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
        map.put(FlowExtWorktaskParam.SERVICE_TYPE, "tjy2_ch_lq");
        map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
        if (StringUtil.isEmpty(serviceId)) {
            System.out.println("领取单流程参数错误=====================================business id is null");
            return JsonWrapper.failureWrapperMsg("参数错误！");
        } else {
            if (StringUtil.isNotEmpty(flowId)) {
                JSONObject dataBus = new JSONObject();
                dataBus.put("isYgje", isYgje);

                //改变状态
                Tjy2ChLq tjy2ChLq = tjy2ChLqDao.get(serviceId);
                tjy2ChLq.setStatus(Tjy2ChLqStatus.SHZ);
                // 启动流程
                boolean isFgld = user.getPermissions().values().contains("TJY2_RL_FGLDSH");
                if (isFgld) {
                    dataBus.put("fgldtj", "true");
                    map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
                    Map<String, Object> flowMap = flowExtManager.startFlowProcess(map);
                    tjy2ChLq.setStatus(Tjy2ChLqStatus.WCK);
                    map.put(FlowExtWorktaskParam.PROCESS_ID, flowMap.get(FlowExtParam.PROCESS_ID));
                    map.put(FlowExtWorktaskParam.FINISH_TYPE, FinishFlowInf.FINISH_TYPE_COMMON);
                    flowExtManager.finishProcess(map);
                    logService.setLogs(tjy2ChLq.getId(), "提交申请", "提交成功！", user.getId(), user.getName(), new Date(),
                            request.getRemoteAddr());
                } else {
                    dataBus.put("fgldtj", "false");
                    map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
                    Map<String, Object> flowMap = flowExtManager.startFlowProcess(map);
                    tjy2ChLqDao.save(tjy2ChLq);
                    System.out.println("processId:============================" + flowMap.get(FlowExtWorktaskParam.PROCESS_ID));
                    List<Activity> activitys = activityManager.getCurrentServiceActivity(tjy2ChLq.getId());
                    Set<BpmUser> bpmUserSet = new HashSet<BpmUser>();
                    String nextUserName="";
                    for (Activity activity : activitys) {
                        //去重要发微信或短信的用户。
                        //获取下一步处理人信息
                        List<BpmUser> bpmUsers = activityManager.getBpmUserPaticipator(activity.getId());
                        for (BpmUser bpmUser : bpmUsers) {
                            bpmUserSet.add(bpmUser);
                            nextUserName=bpmUser.getName();
                        }
                        if (bpmUsers == null || bpmUsers.size() == 0) {
                            String createOrgId = tjy2ChLq.getCreateOrgId();
                            User nextUser = null;
                            if ("100025".equals(createOrgId) || "100042".equals(createOrgId)) {
                                //部门100025(财务管理部),100042(四川省特种设备检验检测协会)的部门负责人是孙宇艺
                                nextUser = userDao.findLoginUser("孙宇艺", "1");

                            } else if ("100030".equals(createOrgId)) {
                                //部门100030（科研技术管理部）的部门负责人是韩绍义
                                nextUser = userDao.findLoginUser("韩绍义", "1");
                            } else if ("100044".equals(createOrgId)) {
                                //100044(司法鉴定中心)的部门负责人是李山桥
                                nextUser = userDao.findLoginUser("李山桥", "1");
                            }
                            Map<String, String> role = userDao.findUserRoles(nextUser.getId());
                            BpmOrg bpmOrg = new BpmOrgImpl(nextUser.getOrg());
                            BpmUser bpm = new BpmUserImpl(nextUser.getId(), nextUser.getName(), bpmOrg, bpmOrg, role);
                            bpmUserSet.add(bpm);
                            nextUserName=bpm.getName();
                        }

                    }
                    for (Activity activity : activitys) {
                        sendMessage(request, tjy2ChLq, bpmUserSet, activity.getId(), flowMap.get(FlowExtWorktaskParam.PROCESS_ID).toString());
                    }
                    logService.setLogs(tjy2ChLq.getId(), "提交申请", "提交申请到下一步处理人"+nextUserName, user.getId(), user.getName(), new Date(),
                            request.getRemoteAddr());
                }

                System.out.println("领取单流程提交成功=====================================");
            } else {
                System.out.println("流程ID为空，领取流程提交失败=====================================");
                return JsonWrapper.failureWrapperMsg("流程ID为空！");
            }
            System.out.println("提交领取流程(" + typeCode + ")(" + flowId + ")完成=====================================");
            return JsonWrapper.successWrapper(serviceId);
        }
    }

    private void sendMessage(HttpServletRequest request, Tjy2ChLq tjy2ChLq, JSONArray pts, String activityId, String processId) throws Exception {
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("lqName", tjy2ChLq.getLqName());
        map1.put("lqOrgName", tjy2ChLq.getLqOrgName());
        map1.put("lqBh", tjy2ChLq.getLqBh());

        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("url", URL + "?businessId=" + tjy2ChLq.getId() + "%26activityId=" + activityId + "%26processId=" + processId + "&response_type=code&scope=snsapi_base&state=STATE");

        if (pts.length() > 0) {
            for (int i = 0; i < pts.length(); i++) {
                JSONObject object = pts.getJSONObject(i);
                User uu = userDao.get(object.getString("id"));
                if (uu.getEmployee() != null && StringUtil.isNotEmpty(uu.getEmployee().getMobileTel())) {
                    String mobile = uu.getEmployee().getMobileTel();
                    if (IS_DEBUG) {
                        mobile = DEBUG_PHONE;
                    }
                    messageService.sendMassageByConfig(request, tjy2ChLq.getId(), mobile, null, MSG_CODE,
                            null, map1, map2, null, Constant.CHLQ_CORPID, Constant.CHLQ_PWD);
/*                    messageService.sendMassageByConfig(request, tjy2ChLq.getId(), mobile, null, MSG_CODE,
                            null, map1, map2, null, null, null);*/
                }
            }
        }
    }

    private void sendMessage(HttpServletRequest request, Tjy2ChLq tjy2ChLq, Set<BpmUser> bpmUserSet, String activityId, String processId) throws Exception {
        JSONArray pts = new JSONArray();
        Iterator<BpmUser> bpmUserIterator = bpmUserSet.iterator();
        while (bpmUserIterator.hasNext()) {
            JSONObject obj = new JSONObject();
            BpmUser user = bpmUserIterator.next();
            obj.put("id", user.getId());
            obj.put("name", user.getName());
            pts.put(obj);
        }
        sendMessage(request, tjy2ChLq, pts, activityId, processId);
    }

    /**
     * 审核不通过
     *
     * @param request
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> shbtg(HttpServletRequest request) throws Exception {
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        String serviceId = request.getParameter("serviceId");
        String activityId = request.getParameter("activityId");
        String lqzje = request.getParameter("lqzje");
        String processId = request.getParameter("processId");
        Tjy2ChLq tjy2ChLq = this.get(serviceId);
        if (StringUtils.isNotEmpty(lqzje)) {
            tjy2ChLq.setLqzje(Double.parseDouble(lqzje));
        }
        Map<String, Object> map = new HashMap<String, Object>();
        //流程传参
        map.put(FlowExtWorktaskParam.PROCESS_ID, processId);
        map.put(FlowExtWorktaskParam.FINISH_TYPE, FinishFlowInf.FINISH_TYPE_TERMINATE);

        if (StringUtil.isEmpty(serviceId)) {
            return JsonWrapper.failureWrapper("参数错误！");
        } else {
            // 结束流程
            if (StringUtil.isNotEmpty(activityId)) {
                flowExtManager.finishProcess(map);
                Activity activity = activityManager.get(activityId);
                tjy2ChLq.setStatus(Tjy2ChLqStatus.WTG);
                tjy2ChLqDao.save(tjy2ChLq);
                logService.setLogs(tjy2ChLq.getId(), activity.getName(), activity.getName()+"操作结果：不同意。", user.getId(), user.getName(), new Date(),
                        request.getRemoteAddr());
            } else {
                return JsonWrapper.failureWrapper("流程ID为空！");
            }
            return JsonWrapper.successWrapper(serviceId);
        }
    }

    /**
     * 审核通过
     *
     * @param request
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> shtg(HttpServletRequest request) throws Exception {
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        Map<String, Object> map = new HashMap<String, Object>();
        String serviceId = request.getParameter("serviceId");
        String activityId = request.getParameter("activityId");
        String processId = request.getParameter("processId");
        String typeCode = request.getParameter("typeCode");
        String ygzje = request.getParameter("ygzje");
        Tjy2ChLq tjy2ChLq = tjy2ChLqDao.get(serviceId);
        if (StringUtil.isNotEmpty(ygzje)) {
            tjy2ChLq.setLqzje(Double.parseDouble(ygzje));
        }
        //流程传参
        map.put(FlowExtWorktaskParam.SERVICE_ID, serviceId);
        map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
        map.put(FlowExtWorktaskParam.SERVICE_TITLE, "存货领取申请-" + user.getName());
        map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
        map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
        if (StringUtil.isEmpty(serviceId)) {
            return JsonWrapper.failureWrapper("参数错误！");
        } else {
            Activity activity = activityManager.get(activityId);
            Process process = processManager.getServiceProcess(serviceId);
            process.getActivitys();
            String function = activity.getFunction();
            JSONObject dataBus = new JSONObject();
            JSONArray pts = new JSONArray();
            if (function.contains("CH_LQ_YGJE")) {
                //当前环节是保障部核定金额，下一步处理人会是流程启动者发起所在部门的部门负责人。
                String createOrgId = tjy2ChLq.getCreateOrgId();
                if ("100025".equals(createOrgId) || "100042".equals(createOrgId)) {
                    //部门100025(财务管理部),100042(四川省特种设备检验检测协会)的部门负责人是孙宇艺
                    //"孙宇艺"
                    User nextUser = userDao.findLoginUser("孙宇艺", "1");
                    JSONObject pt = new JSONObject();
                    pt.put("id", nextUser.getId());
                    pt.put("name", nextUser.getName());
                    pts.put(pt);
                    dataBus.put("paticipator", pts);
                } else if ("100030".equals(createOrgId)) {
                    //部门100030（科研技术管理部）的部门负责人是韩绍义
                    User nextUser = userDao.findLoginUser("韩绍义", "1");
                    JSONObject pt = new JSONObject();
                    pt.put("id", nextUser.getId());
                    pt.put("name", nextUser.getName());
                    pts.put(pt);
                    dataBus.put("paticipator", pts);
                } else if ("100044".equals(createOrgId)) {
                    //100044(司法鉴定中心)的部门负责人是李山桥
                    User nextUser = userDao.findLoginUser("李山桥", "1");
                    JSONObject pt = new JSONObject();
                    pt.put("id", nextUser.getId());
                    pt.put("name", nextUser.getName());
                    pts.put(pt);
                    dataBus.put("paticipator", pts);
                } else {
                    //其他部门的部门负责人在他们各自的部门里按权限查找。
                    List<com.khnt.rbac.bean.User> users = this.findPermissioneUser("TJY2_BMFZR", createOrgId);
                    for (int i = 0; i < users.size(); i++) {
                        com.khnt.rbac.bean.User nextUser = users.get(i);
                        JSONObject pt = new JSONObject();
                        pt.put("id", nextUser.getId());
                        pt.put("name", nextUser.getName());
                        pts.put(pt);
                    }
                }
                dataBus.put("paticipator", pts);
                map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
            }
            map.put(FlowExtWorktaskParam.DATA_BUS, dataBus.put("isFgld", "false"));
            if (function.contains("CH_LQ_BMFZR")) {
                //如果是部门负责人审核环节，要默认选中处理人为
                Double lqzjeD = tjy2ChLq.getLqzje();
                if (lqzjeD >= 1000d) {
                    dataBus.remove("isFgld");
                    map.put(FlowExtWorktaskParam.DATA_BUS, dataBus.put("isFgld", "true"));
                    JSONObject pt = new JSONObject();
                    OrgidLeaderid leader = orgidLeaderidManager.getInfoByOrgid(tjy2ChLq.getCreateOrgId());
                    User learderUser = (User) userDao.createQuery("from User where employee.id=?", leader.getFkRlEmplpyeeId()).uniqueResult();
                    pt.put("id", learderUser.getId());
                    pt.put("name", learderUser.getName());
                    pts.put(pt);
                    dataBus.put("paticipator", pts);
                }
            }
            if (StringUtil.isNotEmpty(activityId)) {
                Map<String, Object> result = flowExtManager.submitActivity(map);
                if ("4".equals(result.get(FlowExtWorktaskParam.RESULT_TYPE).toString())) {
                    tjy2ChLq.setStatus(Tjy2ChLqStatus.WCK);
                    map.put(FlowExtWorktaskParam.PROCESS_ID, processId);
                    map.put(FlowExtWorktaskParam.FINISH_TYPE, FinishFlowInf.FINISH_TYPE_COMMON);
                    flowExtManager.finishProcess(map);
                    
                    logService.setLogs(tjy2ChLq.getId(), activity.getName(), activity.getName()+"审批通过。", user.getId(), user.getName(), new Date(),
                            request.getRemoteAddr());
                } else {
                    List<Activity> objects = (List<Activity>) result.get(FlowExtWorktaskParam.RESULT_ACTIVITY_LIST);
                    String nextName="";
                    for (Activity activity1 : objects) {
                        sendMessage(request, tjy2ChLq, pts, activity1.getId(), processId);
                        JSONObject object = pts.getJSONObject(0);
                        nextName=object.getString("name");
                    }
                    logService.setLogs(tjy2ChLq.getId(), activity.getName(), activity.getName()+"处理结果：同意。提交给下一步处理人（"+nextName+"）。", user.getId(), user.getName(), new Date(),
                            request.getRemoteAddr());
                }
            } else {
                return JsonWrapper.failureWrapper("流程ID为空！");
            }
            tjy2ChLqDao.save(tjy2ChLq);
            return JsonWrapper.successWrapper(serviceId);
        }
    }


    private String getLQBH(Date createTime) {
    	 String newbh="";
    	try {
    		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	        String bh= "LQ"+sdf.format(createTime);
    	        Map<String,Object> mapBean=tjy2ChLqDao.getBeanByYear(bh);
    	        if(mapBean!=null){
    	        	Object object = mapBean.get("LQ_BH");
        	        int no= Integer.parseInt(String.valueOf(object ))+1;
    			    newbh=bh+String.format("%03d", no);
    		     }else{
    		    	 newbh=bh+"001";
    		     }
		} catch (Exception e) {
			System.err.println(e);
		}
       
        return newbh;
    	
    }

    public void deleteChlq(String[] ids) {
        for (String id : ids) {
            Tjy2ChLq entity = this.get(id);
            if (!Tjy2ChLqStatus.WTJ.equals(entity.getStatus())) {
                throw new KhntException("只能删除未提交的领取单");
            }
            entity.setStatus(Tjy2ChLqStatus.REMOVE);
            for (GoodsAndOrder gad : entity.getGoods()) {
                gad.setStatus(Tjy2ChLqStatus.REMOVE);
                goodsAndOrderDao.save(gad);
            }
            tjy2ChLqDao.save(entity);
        }
    }

    public HashMap<String, Object> getFlowStep(String id) throws Exception {
        List<SysLog> list = tjy2ChLqDao.createQuery("  from SysLog where business_id ='" + id + "' order by op_time,id asc").list();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("flowStep", list);
        map.put("size", list.size());
        map.put("sn", tjy2ChLqDao.get(id).getLqBh());
        map.put("success", true);
        return map;
    }

    @Override
    public void startOutStorage(String id) {

    }

    @Override
    public void finishOutStorage(String id, List<GoodsAndOrder> goods, JSONObject object) {
        Tjy2ChLq entity = tjy2ChLqDao.get(id);
        for (GoodsAndOrder gad : entity.getGoods()) {
            goodsAndOrderDao.remove(gad);
        }
        Date now = new Date();
        List<GoodsAndOrder> sourceGoods = entity.getGoods();
        sourceGoods.removeAll(sourceGoods);
        for (GoodsAndOrder gad : goods) {
            gad.setType("LQ");
            gad.setCreate_time(now);
            gad.setFk_order_id(entity.getId());
            gad.setStatus("1");
            goodsAndOrderDao.save(gad);
        }

        sourceGoods.addAll(goods);
        entity.setGoods(sourceGoods);
        entity.setStatus(Tjy2ChLqStatus.YCK);
        Date lqTime = (Date) JSONObject.toBean(JSONObject.fromString(object.get(OutStorageParam.LQ_TIME).toString()), Date.class);
        entity.setLqTime(lqTime);
        Date sqTime = (Date) JSONObject.toBean(JSONObject.fromString(object.get(OutStorageParam.SQ_TIME).toString()), Date.class);
        entity.setCreateTime(sqTime);
        entity.setCkdId(object.get(OutStorageParam.ID).toString());
        entity.setCkdBH(object.get(OutStorageParam.CKDBH).toString());
    }

    public List<Map<String, Object>> getFlowByserviceIdAndHandlerId(String serviceId,String userId){
    	String sql="select * from V_PUB_WORKTASK_ADD_POSITION WHERE WORK_TYPE='tjy2_ch_lq' AND SERVICE_ID=? AND STATUS='0'  AND HANDLER_ID=?";
    	List<Map<String, Object>> list=tjy2ChLqDao.createSQLQuery(sql,serviceId,userId).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
    	return list;
    }
    public List<com.khnt.rbac.bean.User> findPermissioneUser(String pcode, String orgId) {
        SQLQuery query = (SQLQuery) tjy2ChLqDao.createSQLQuery("select u.id,u.name from sys_org o join sys_user u on u.org_id=o.id join sys_user_role ur on ur.user_id = u.id join sys_role_permission rp on rp.role_id = ur.role_id join sys_permission p on p.id = rp.permission_id where p.p_code=:pcode" + (orgId != null ? " and (o.id=:oid or o.parent_id=:oid)" : ""), new Object[0]);
        query.setString("pcode", pcode);
        if (orgId != null) {
            query.setString("oid", orgId);
        }

        List<Object[]> ql = query.list();
        List<com.khnt.rbac.bean.User> rus = new ArrayList();
        Iterator var7 = ql.iterator();

        while (var7.hasNext()) {
            Object[] o = (Object[]) var7.next();
            User u = new User();
            u.setId(String.valueOf(o[0]));
            u.setName(String.valueOf(o[1]));
            rus.add(u);
        }

        return rus;
    }

    @Override
    public List<GoodsAndOrder> getGoodsAndOrder(String id) {
        Tjy2ChLq tjy2ChLq = this.tjy2ChLqDao.get(id);
        return tjy2ChLq.getGoods();
    }

    @Override
    public String getOrderNumber(String id) {
        Tjy2ChLq tjy2ChLq = this.tjy2ChLqDao.get(id);
        return tjy2ChLq.getLqBh();
    }

    @Override
    public void deleteOutStorage(String id) {
        Tjy2ChLq tjy2ChLq = this.tjy2ChLqDao.get(id);
        if (!Tjy2ChLqStatus.WCK.equals(tjy2ChLq.getStatus())) {
            throw new KhntException("只能删除审核通过待出库的领取单。");
        }
        tjy2ChLq.setStatus(Tjy2ChLqStatus.KGSC);
        tjy2ChLqDao.save(tjy2ChLq);
    }

    @Override
    public boolean canOutStorage(String id) {
        Tjy2ChLq tjy2ChLq = tjy2ChLqDao.get(id);
        if (Tjy2ChLqStatus.WCK.equals(tjy2ChLq.getStatus())) {
            return true;
        } else {
            return false;
        }
    }

    public HashMap<String, Object> queryCheck(HttpServletRequest request) throws Exception {
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        String userId = user.getId();
        String departmentId = user.getDepartment().getId();
        String userDep = "";
        Employee e = userDao.get(userId).getEmployee();
        String employeeId = e.getId();
        String sql = "select * from ( select * from (select b.*,t.handler_id,t.ACTIVITY_ID,t.PROCESS_ID from TJY2_CH_RECIPIENTS b,v_pub_worktask t where " +
                "b.id = t.SERVICE_ID and t.WORK_TYPE like 'tjy2_ch_lq' and t.status = '0' and b.status != 99 ) s where s.handler_id = '" + userId + "' order by lq_bh desc";
        Map<String, String> permissions = user.getPermissions();
        boolean isBmfzr = permissions.values().contains("TJY2_BMFZR");
        boolean isFgld = permissions.values().contains("TJY2_RL_FGLDSH");
        boolean isYGJE = permissions.values().contains("CH_LQ_JEYG");//预估金额
        if (isBmfzr) {
            sql = "select * from ( select * from (select b.*,t.handler_id,t.ACTIVITY_ID,t.PROCESS_ID from TJY2_CH_RECIPIENTS b,v_pub_worktask t where " +
                    "b.id = t.SERVICE_ID and t.WORK_TYPE like 'tjy2_ch_lq' and t.status = '0' and b.status != 99 ) s where s.handler_id = '" + userId + "' and s.create_org_id = '" + departmentId + "' ) order by lq_bh desc";
            if (user.getName().equals("孙宇艺")) {
                userDep = "100025";
                if (user.getName().equals("韩绍义")) {
                    userDep = "100030";
                }
                sql = "select * from (select * from (select b.*,t.handler_id,t.ACTIVITY_ID,t.PROCESS_ID,t.org_id from TJY2_CH_RECIPIENTS b,v_pub_worktask_add_position t " +
                        "where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like   'tjy2_ch_lq' " +
                        "and t.status='0' and b.status != 99 ) s where s.handler_id = '" + userId + "' and s.org_id = '" + departmentId + "' and s.create_org_id = '" + userDep + "' ) order by lq_bh desc";
            }
        }
        if (isFgld) {
            sql = "select * from (select s.* from ( " +
                    "select b.*,t.handler_id,t.ACTIVITY_ID,t.PROCESS_ID from TJY2_CH_RECIPIENTS b, v_pub_worktask t " +
                    "where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like   'tjy2_ch_lq' and t.STATUS='0' and b.status != 99 ) s " +
                    "where s.handler_id = '" + userId + "' and instr((select d.fk_sys_org_id from TJY2_RL_ORGID_LEADERID d " +
                    "where d.fk_rl_emplpyee_id='" + employeeId + "'), s.create_org_id) > 0 ) order by lq_bh desc";
        }
        if (isBmfzr && isFgld) {
            sql = "select * from (select s.* from ( " +
                    "select b.*,t.handler_id,t.ACTIVITY_ID,t.PROCESS_ID from TJY2_CH_RECIPIENTS b, v_pub_worktask t " +
                    "where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like 'tjy2_ch_lq' and t.STATUS='0' and b.status != 99 ) s " +
                    "where s.handler_id = '" + userId + "' and instr((select d.fk_sys_org_id from TJY2_RL_ORGID_LEADERID d " +
                    "where d.fk_rl_emplpyee_id='" + employeeId + "'), s.create_org_id) > 0 ) order by lq_bh desc";
            if (e.getName().equals("孙宇艺")) {
                sql = "select * from (select * from (select b.*,t.handler_id,t.ACTIVITY_ID,t.PROCESS_ID, t.org_id from TJY2_CH_RECIPIENTS b, v_pub_worktask_add_position t " +
                        "where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like   'tjy2_ch_lq' " +
                        "and t.STATUS='0' and b.status != 99 ) s where s.handler_id = '" + userId + "' and s.create_org_id in('100025','100048') union " +
                        "select s.* from ( " +
                        "select b.*,t.handler_id,t.ACTIVITY_ID,t.PROCESS_ID,b.CREATE_ORG_ID org_id from TJY2_CH_RECIPIENTS b, v_pub_worktask t " +
                        "where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like   'tjy2_ch_lq' and t.STATUS='0'  and b.status != 99) s " +
                        "where s.handler_id = '" + userId + "' and instr((select d.fk_sys_org_id from TJY2_RL_ORGID_LEADERID d " +
                        "where d.fk_rl_emplpyee_id='" + employeeId + "'), s.create_org_id) > 0 ) order by lq_bh desc";
            }
            if (e.getName().equals("韩绍义")) {
                sql = "select * from (select * from (select b.*,t.handler_id,t.ACTIVITY_ID,t.PROCESS_ID, t.org_id from TJY2_CH_RECIPIENTS b, v_pub_worktask_add_position t " +
                        "where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like   'tjy2_ch_lq' " +
                        "and t.STATUS='0' and b.status != 99 ) s where s.handler_id = '" + userId + "' and s.create_org_id in('100030') union " +
                        "select s.* from ( " +
                        "select b.*,t.handler_id,t.ACTIVITY_ID,t.PROCESS_ID,b.CREATE_ORG_ID org_id from TJY2_CH_RECIPIENTS b, v_pub_worktask t " +
                        "where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like   'tjy2_ch_lq' and t.STATUS='0' and b.status != 99) s " +
                        "where s.handler_id = '" + userId + "' and instr((select d.fk_sys_org_id from TJY2_RL_ORGID_LEADERID d " +
                        "where d.fk_rl_emplpyee_id='" + employeeId + "'), s.create_org_id) > 0 ) order by lq_bh desc";
            }
        }
        if (isYGJE) {
            sql = "select * from ( select * from (select b.*,t.handler_id,t.ACTIVITY_ID,t.PROCESS_ID from TJY2_CH_RECIPIENTS b,v_pub_worktask t where " +
                    "b.id = t.SERVICE_ID and t.WORK_TYPE like 'tjy2_ch_lq' and t.status = '0' and b.status != 99 ) s where s.handler_id = '" + userId + "') order by lq_bh desc";
        }
        List<Object[]> objects = tjy2ChLqDao.createSQLQuery(sql).list();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (Object[] object : objects) {
            Map map = constructMap(object);
            map.put("activityId", object[25]);
            map.put("processId", object[26]);
            mapList.add(map);
        }
        return JsonWrapper.successWrapper(mapList);
    }

    private Map<String, Object> constructMap(Object[] object) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", object[0]);
        map.put("createId", object[1]);
        map.put("createName", object[2]);
        map.put("createOrgId", object[3]);
        map.put("createOrgName", object[4]);
        map.put("createUnitId", object[5]);
        map.put("createUnitName", object[6]);
        map.put("createTime", object[7]);
        map.put("lqId", object[8]);
        map.put("lqName", object[9]);
        map.put("lqOrgId", object[10]);
        map.put("lqOrgName", object[11]);
        map.put("lqTime", object[12]);
        map.put("lqBh", object[13]);
        map.put("status", object[14]);
        map.put("blqBm", object[15]);
        map.put("blqBmId", object[16]);
        map.put("bmfzr", object[17]);
        map.put("lqRemark", object[18]);
        map.put("lqwp", object[19]);
        map.put("lqzje", object[20]);
        map.put("ckdId", object[21]);
        map.put("ckdBh", object[22]);
        map.put("dataFrom", object[23]);
        map.put("handlerId", object[24]);
        return map;
    }

    public HashMap<String, Object> queryChecked(HttpServletRequest request) throws Exception {
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        String userId = user.getId();
        String departmentId = user.getDepartment().getId();
        String userDep = "";
        Employee e = userDao.get(userId).getEmployee();
        String employeeId = e.getId();
        String sql = "";
        Map<String, String> permissions = user.getPermissions();
        boolean isBmfzr = permissions.values().contains("TJY2_BMFZR");
        boolean isFgld = permissions.values().contains("TJY2_RL_FGLDSH");
        boolean isYGJE = permissions.values().contains("CH_LQ_JEYG");//预估金额
        if (isBmfzr) {
            sql = "select * from ( select * from (select b.*,t.handler_id from TJY2_CH_RECIPIENTS b,v_pub_worktask t where " +
                    "b.id = t.SERVICE_ID and t.WORK_TYPE like 'tjy2_ch_lq' and t.status != '0' and t.ACTIVITY_NAME !='提交' and b.status != 99 ) s where s.handler_id = '" + userId + "' and s.create_org_id = '" + departmentId + "') order by lq_bh desc";
            if (user.getName().equals("孙宇艺")) {
                userDep = "100025";
                if (user.getName().equals("韩绍义")) {
                    userDep = "100030";
                }
                sql = "select * from (select * from (select b.*,t.handler_id,t.org_id from TJY2_CH_RECIPIENTS b,v_pub_worktask_add_position t " +
                        "where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like   'tjy2_ch_lq' " +
                        "and t.status!='0' and t.ACTIVITY_NAME !='提交' and b.status != 99 ) s where s.handler_id = '" + userId + "' and s.org_id = '" + departmentId + "' and s.create_org_id = '" + userDep + "') order by lq_bh desc";
            }
        }
        if (isFgld) {
            sql = "select * from (select s.* from ( " +
                    "select b.*,t.handler_id from TJY2_CH_RECIPIENTS b, v_pub_worktask t " +
                    "where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like   'tjy2_ch_lq' and t.STATUS!='0' and t.ACTIVITY_NAME !='提交' and b.status != 99 ) s " +
                    "where s.handler_id = '" + userId + "' and instr((select d.fk_sys_org_id from TJY2_RL_ORGID_LEADERID d " +
                    "where d.fk_rl_emplpyee_id='" + employeeId + "'), s.create_org_id) > 0 ) order by lq_bh desc";
        }
        if (isBmfzr && isFgld) {
            sql = "select * from (select s.* from ( " +
                    "select b.*,t.handler_id from TJY2_CH_RECIPIENTS b, v_pub_worktask t " +
                    "where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like 'tjy2_ch_lq' and t.STATUS!='0' and t.ACTIVITY_NAME !='提交' and b.status != 99 ) s " +
                    "where s.handler_id = '" + userId + "' and instr((select d.fk_sys_org_id from TJY2_RL_ORGID_LEADERID d " +
                    "where d.fk_rl_emplpyee_id='" + employeeId + "'), s.create_org_id) > 0 ) order by lq_bh desc";
            if (e.getName().equals("孙宇艺")) {
                sql = "select * from (select * from (select b.*,t.handler_id, t.org_id from TJY2_CH_RECIPIENTS b, v_pub_worktask_add_position t " +
                        "where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like   'tjy2_ch_lq' " +
                        "and t.STATUS!='0' and t.ACTIVITY_NAME !='提交' and b.status != 99 ) s where s.handler_id = '" + userId + "' and s.create_org_id in('100025','100048') union " +
                        "select s.* from ( " +
                        "select b.*,t.handler_id,b.CREATE_ORG_ID org_id from TJY2_CH_RECIPIENTS b, v_pub_worktask t " +
                        "where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like   'tjy2_ch_lq' and t.STATUS!='0' t.ACTIVITY_NAME !='提交'  and b.status != 99) s " +
                        "where s.handler_id = '" + userId + "' and instr((select d.fk_sys_org_id from TJY2_RL_ORGID_LEADERID d " +
                        "where d.fk_rl_emplpyee_id='" + employeeId + "'), s.create_org_id) > 0 ) order by lq_bh desc";
            }
            if (e.getName().equals("韩绍义")) {
                sql = "select * from (select * from (select b.*,t.handler_id, t.org_id from TJY2_CH_RECIPIENTS b, v_pub_worktask_add_position t " +
                        "where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like   'tjy2_ch_lq' " +
                        "and t.STATUS=!'0' t.ACTIVITY_NAME !='提交' and b.status != 99 ) s where s.handler_id = '" + userId + "' and s.create_org_id in('100030') union " +
                        "select s.* from ( " +
                        "select b.*,t.handler_id,b.CREATE_ORG_ID org_id from TJY2_CH_RECIPIENTS b, v_pub_worktask t " +
                        "where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like   'tjy2_ch_lq' and t.STATUS=!'0' t.ACTIVITY_NAME !='提交' and b.status != 99 ) s " +
                        "where s.handler_id = '" + userId + "' and instr((select d.fk_sys_org_id from TJY2_RL_ORGID_LEADERID d " +
                        "where d.fk_rl_emplpyee_id='" + employeeId + "'), s.create_org_id) > 0 ) order by lq_bh desc";
            }
        }
        if (isYGJE) {
            sql = "select * from ( select * from (select b.*,t.handler_id from TJY2_CH_RECIPIENTS b,v_pub_worktask t where " +
                    "b.id = t.SERVICE_ID and t.WORK_TYPE like 'tjy2_ch_lq' and t.status != '0' and t.ACTIVITY_NAME !='提交' and b.status != 99 ) s where s.handler_id = '" + userId + "' ) order by lq_bh desc";
        }
        List<Object[]> objects = tjy2ChLqDao.createSQLQuery(sql).list();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (Object[] object : objects) {
            mapList.add(constructMap(object));
        }
        return JsonWrapper.successWrapper(mapList);
    }

    public HashMap<String, Object> queryCheckMy(HttpServletRequest request) throws Exception {
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        String userId = user.getId();
        String sql = "select t.*,t.status as showStatus from TJY2_CH_RECIPIENTS t where status != 99 and create_id = '" + userId + "' order by lq_bh desc";
        List<Object[]> objects = tjy2ChLqDao.createSQLQuery(sql).list();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (Object[] object : objects) {
            mapList.add(constructMap(object));
        }
        return JsonWrapper.successWrapper(mapList);
    }

    /**
     * 保存并提交领取申请
     *
     * @param request
     * @param entity
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> saveAndSubmit(HttpServletRequest request, Tjy2ChLq entity) throws Exception {
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        String typeCode = "tjy2_ch_lq";
        String isYgje = request.getParameter("isYgje");
        List<FlowServiceConfig> list = flowServiceConfigManager.queryServiceConfig(typeCode, user.getUnit().getId());
        if (CollectionUtils.isEmpty(list)) {
            throw new KhntException("未找到可用的流程定义，请联系管理员处理");
        } else if (list.size() > 1) {
            throw new KhntException("找到多个可用的流程定义，请联系管理员处理");
        }
        if (StringUtil.isEmpty(isYgje)) {
            throw new KhntException("请选择是否提交到保障部预估金额");
        }
        FlowServiceConfig config = list.get(0);
        this.usualSave(entity, Tjy2ChLqStatus.SHZ);
        return _startFlow(request, entity.getId(), config.getFlowId(), typeCode, isYgje);
    }
}
