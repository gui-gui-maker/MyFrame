package com.lsts.humanresources.web;

import com.alibaba.fastjson.JSON;
import com.khnt.base.Factory;
import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.BeanUtils;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.equipment2.bean.EquipmentBuy;
import com.lsts.equipment2.service.EquipmentBuyService;
import com.lsts.humanresources.bean.BgLeave;
import com.lsts.humanresources.service.BgLeaveManager;
import com.lsts.humanresources.service.EmployeeBaseManager;
import com.lsts.humanresources.service.OrgidLeaderidManager;
import com.lsts.log.service.SysLogService;
import com.lsts.qualitymanage.bean.QualityUpdateFile;
import net.sf.json.JSONObject;

import org.hibernate.id.IdentityGenerator.GetGeneratedKeysDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("BgLeaveAction")
public class BgLeaveAction extends SpringSupportAction<BgLeave, BgLeaveManager> {
    private Logger logger = LoggerFactory.getLogger(BgLeaveAction.class);
    @Autowired
    private BgLeaveManager bgLeaveManager;
    @Autowired
    private MessageService messageService;
    @Autowired
    private EmployeeBaseManager employeeBaseManager;
    @Autowired
    private OrgidLeaderidManager orgidLeaderidManager;
    @Autowired
    private SysLogService logService;

    /**
     * 休假请假保存
     *
     * @throws Exception
     **/
    @RequestMapping(value = "save")
    @ResponseBody
    public HashMap<String, Object> save(HttpServletRequest request, @RequestBody BgLeave bgLeave) {
        try {
            String pageStatus = request.getParameter("pageStatus");//获取页面状态
            String modifyType = request.getParameter("modifyType");//获取修改类型
            String uploadFiles = request.getParameter("uploadFiles");//获取附件
            CurrentSessionUser user = SecurityUtil.getSecurityUser();//获取当前登陆人
            if (pageStatus.equals("add")) {
                bgLeave.setApplyStatus("WTJ");//初始化申请状态为未提交
                bgLeave.setCreateName(user.getName());//创建人
                bgLeave.setCreateDate(new Date());//创建时间
                bgLeaveManager.saveEntyandFiles(bgLeave, uploadFiles);
                return JsonWrapper.successWrapper("操作成功！");
            } else if (pageStatus.equals("modify")) {
                BgLeave bgLeave1 = bgLeaveManager.get(bgLeave.getId());
                if (modifyType.equals("back")) {
                    bgLeave1.setXjrqDate(bgLeave.getXjrqDate());
                    bgLeave1.setLeaveCount2(bgLeave.getLeaveCount2());
                    bgLeave1.setXjPeopleSign(bgLeave.getXjPeopleSign());
                    bgLeave1.setXjHrSign(bgLeave.getXjHrSign());
                    bgLeave1.setApplyStatus("YXJ");
                    //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                    logService.setLogs(bgLeave.getId(),
                            "销假",
                            "销假处理，操作结果：已销假",
                            user != null ? user.getId() : "未获取到操作用户编号",
                            user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                            request != null ? request.getRemoteAddr() : "");
                } else {
                    bgLeave1.setPeopleId(bgLeave.getPeopleId());
                    bgLeave1.setPeopleName(bgLeave.getPeopleName());
                    bgLeave1.setDepId(bgLeave.getDepId());
                    bgLeave1.setDepName(bgLeave.getDepName());
                    bgLeave1.setLeaveType(bgLeave.getLeaveType());
                    bgLeave1.setStartDate(bgLeave.getStartDate());
                    bgLeave1.setEndDate(bgLeave.getEndDate());
                    bgLeave1.setLeaveCount1(bgLeave.getLeaveCount1());
                    bgLeave1.setLeaveCount2(bgLeave.getLeaveCount2());
                    bgLeave1.setTotal(bgLeave.getTotal());
                    bgLeave1.setLeaveReason(bgLeave.getLeaveReason());
                    bgLeave1.setPeopleSign(bgLeave.getPeopleSign());
                    bgLeave1.setPeopleSignDate(bgLeave.getPeopleSignDate());
                }
                bgLeave1.setLastModifyName(user.getName());//最近修改人
                bgLeave1.setLastModifyDate(new Date());//最近修改时间
                bgLeaveManager.saveEntyandFiles(bgLeave1, uploadFiles);
                return JsonWrapper.successWrapper("操作成功！");
            } else if (pageStatus.equals("add_record")) {
                bgLeave.setApplyStatus("SPTG");//申请状态为审批通过
                bgLeave.setCreateName(user.getName());//创建人
                bgLeave.setCreateDate(new Date());//创建时间
                bgLeaveManager.saveEntyandFiles(bgLeave, uploadFiles);
                return JsonWrapper.successWrapper("操作成功！");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return JsonWrapper.failureWrapper("操作失败！");
        }
        return JsonWrapper.successWrapper(bgLeave);
    }

    /**
     * 已请假种类及天数
     *
     * @param peopleId
     * @throws Exception
     */
    @RequestMapping(value = "queryLeave")
    @ResponseBody
    public HashMap<String, Object> queryLeave(HttpServletRequest request, String peopleId, String startDate) throws Exception {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        String peopleId1 = request.getParameter("peopleId");
        String leaveInfo = bgLeaveManager.queryLeave(request, peopleId1, startDate);
        System.out.println(leaveInfo);
        if (leaveInfo == null) {
            wrapper.put("success", false);
            wrapper.put("leaveInfo", "请向人事部确认你的可休年假天数！");
        } else if (leaveInfo == "1") {
            wrapper.put("success", false);
            wrapper.put("leaveInfo", "请向人事部确认你的基本信息是否正确！");
        } else {
            wrapper.put("success", true);
            wrapper.put("leaveInfo", leaveInfo);
        }
        return wrapper;
    }

    /**
     * 撤销请休假申请
     *
     * @param Id
     * @throws Exception
     */
    @RequestMapping(value = "revokeLeave")
    @ResponseBody
    public HashMap<String, Object> revokeLeave(HttpServletRequest request, String id) throws Exception {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        BgLeave bgLeave = bgLeaveManager.get(id);
        bgLeave.setApplyStatus("YCX");
        try {
            bgLeaveManager.save(bgLeave);
            System.out.println("@@@@@@@@@@@@@@@@@@撤销成功@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            wrapper.put("success", true);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            wrapper.put("success", false);
        }
        return wrapper;
    }

    /**
     * 初始化流程参数
     *
     * @param serviceId         业务id
     * @param activityId        流程环节id
     * @param serviceTitle      业务标题
     * @param flowDefinitionId  流程定义id
     * @param serviceType       业务类型
     * @param isCurrentUserTask 是否当前用户任务 true/false
     * @return map
     */
    private Map<String, Object> initFlowParam(String serviceId, String activityId, String serviceTitle,
                                              String flowDefinitionId, String serviceType, boolean isCurrentUserTask) {
        Map<String, Object> map = new HashMap<>();
        map.put(FlowExtWorktaskParam.SERVICE_ID, serviceId);
        map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
        map.put(FlowExtWorktaskParam.SERVICE_TITLE, serviceTitle);
        map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowDefinitionId);
        map.put(FlowExtWorktaskParam.SERVICE_TYPE, serviceType);
        map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, isCurrentUserTask);
        return map;
    }

    /**
     * 开始请假撤销流程
     *
     * @param request      request
     * @param id           TJY2_RL_LEAVE.id
     * @param people_id    TJY2_RL_LEAVE.people_id
     * @param leave_count1 TJY2_RL_LEAVE.leave_count1
     * @param flowId       FLOW_DEFINITION.id
     * @param typeCode     业务类型
     * @param status       业务状态
     * @param activityId   流程环节id
     * @return map
     */
    @RequestMapping(value = "subCancelFolw")
    @ResponseBody
    public HashMap<String, Object> subCancelFolw(HttpServletRequest request, String id, String people_id,
                                                 String leave_count1, String flowId,
                                                 String typeCode, String status, String activityId) {
        this.logger.info("开始提交请假撤销流程('{}','{}')", typeCode, flowId);
        try {
            CurrentSessionUser user = SecurityUtil.getSecurityUser();
            // 获取申请人的权限
            List<?> list = bgLeaveManager.getUserPower(people_id);
            // 获取需要撤销的业务数据
            BgLeave bgLeaveOld = bgLeaveManager.get(id);
            // 判断是否有对应的撤销流程
            // 新的业务数据
            BgLeave bgLeave = new BgLeave();
            BeanUtils.copyProperties(bgLeave, bgLeaveOld);
            bgLeave.setId(null);
            bgLeave.setRevokeBusId(bgLeaveOld.getId());
            bgLeave.setLeaveReason(bgLeaveOld.getLeaveReason()+"----请假撤销");
            bgLeaveManager.save(bgLeave);

            this.logger.debug("请假撤销申请人('name={}','people_id={}')的权限角色{}", new Object[]{user.getName(), people_id,
                    list});
            String sign = "0";

            // 定义流程参数
            Map<String, Object> map = initFlowParam(bgLeave.getId(), activityId, "请假撤销申请-" + user.getName(),
                    flowId, typeCode, true);

            if (StringUtil.isEmpty(id)) {
                this.logger.error("请假撤销流程参数错误=====================================business id is null");
                return JsonWrapper.failureWrapper("参数错误！");
            } else {
                if (StringUtil.isNotEmpty(flowId)) {
                    // 已定义流程
                    JSONObject dataBus = new JSONObject();
                    if (!list.contains("部门负责人") && !list.contains("院领导") && !list.contains("院长")) {
                        dataBus.put("org", "1");
                        this.logger.debug("请假撤销流程参数org=====================================1");
                    } else if (list.contains("部门负责人") || list.contains("院领导")) {
                        dataBus.put("org", "2");
                        this.logger.debug("请假撤销流程参数org=====================================2");
                    }
                    map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
                    //改变状态
                    bgLeaveOld.setApplyStatus(BgLeaveManager.LEAVE_FLOW_CXZ);
                    bgLeave.setApplyStatus(BgLeaveManager.LEAVE_FLOW_YTJ);
                    // 启动流程
                    bgLeaveManager.doStartPress(map);
                    bgLeaveManager.save(bgLeave);
                    this.logger.debug("请假撤销流程提交成功=====================================");

                    //获取下一步审核人姓名
                    String checkName = bgLeaveManager.getCheckName(bgLeave.getId(), bgLeave.getDepId());
                    checkName = StringUtil.isNotEmpty(checkName) ? checkName : "";
                    request.setAttribute("sign", sign);//提交时设置sign参数值为0，表示未到分管领导审核
                    request.setAttribute("checkName", checkName);
                    //发送提醒消息
                    sendMsg(request, bgLeave);
                    this.logger.info("记录日志=====================================");
                    //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                    logService.setLogs(bgLeave.getId(),
                            "提交请假撤销申请",
                            "提交请假撤销申请至" + checkName + "，操作结果：已提交",
                            user.getId(), user.getName(), new Date(), request.getRemoteAddr());
                } else {
                    this.logger.error("流程ID为空，请假撤销流程提交失败=====================================");
                    return JsonWrapper.failureWrapper("流程ID为空！");
                }
                this.logger.info("提交请假撤销流程('{}','{}')完成", typeCode, flowId);
                return JsonWrapper.successWrapper(id);
            }
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
            return JsonWrapper.failureWrapperMsg("提交请假撤销流程失败");
        }
    }

    /**
     * 请假撤销审核通过
     */
    @RequestMapping(value = "subPassCancel")
    @ResponseBody
    public HashMap<String, Object> subPassCancel(HttpServletRequest request, String areaFlag, String id, String flowId,
                                                 String activityId, String typeCode) throws Exception {
        String sign = "0";
        String checkName = "";
        String bgLeave = request.getParameter("bgLeave").toString();
        BgLeave bgLeave1 = JSON.parseObject(bgLeave, BgLeave.class);
        BgLeave bgLeave2 = bgLeaveManager.get(id);//获取请假撤销申请信息

        // 获取该撤销申请的关联请假信息
        BgLeave bgLeaveOld = bgLeaveManager.get(bgLeave2.getRevokeBusId());

        String leaveCount1 = bgLeave2.getLeaveCount1();//获取请假人的请假天数
        List<?> list = bgLeaveManager.getUserPower(bgLeave2.getPeopleId());//获取申请人的权限
        String fgldName = orgidLeaderidManager.getInfoByOrgid(bgLeave2.getDepId()).getEmpName();//获取分管领导姓名
        fgldName = StringUtil.isNotEmpty(fgldName) ? fgldName : "";
        //流程传参
        Map<String, Object> map = initFlowParam(id, activityId, "请假撤销申请-" + bgLeave2.getPeopleName(), typeCode,
                flowId, true);

        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        if (StringUtil.isEmpty(id)) {
            return JsonWrapper.failureWrapper("参数错误！");
        } else {
            // 启动流程
            if (StringUtil.isNotEmpty(activityId)) {
                //改变状态并保存审核意见
                bgLeave2.setApplyStatus(bgLeaveManager.LEAVE_FLOW_SHZ);
                if (areaFlag.equals("2")) {
                    bgLeaveManager.doProcess(map);
                    bgLeave2.setKsfzryj(bgLeave1.getKsfzryj());
                    bgLeave2.setKsfzryjSing(bgLeave1.getKsfzryjSing());
                    bgLeave2.setKsfzryjDate(bgLeave1.getKsfzryjDate());
                    checkName = bgLeaveManager.getCheckName(bgLeave2.getId(), bgLeave2.getDepId());
                    checkName = StringUtil.isNotEmpty(checkName) ? checkName : "";
                    request.setAttribute("checkName", checkName);//审核流程未结束时将当下一步审核人的姓名作为参数
                    request.setAttribute("sign", sign);//审核流程未结束时传递参数sign

                    //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                    logService.setLogs(bgLeave2.getId(),
                            "审批请假撤销申请",
                            "部门负责人审批通过，并提交至" + checkName + "审批，操作结果：审批中",
                            user != null ? user.getId() : "未获取到操作用户编号",
                            user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                            request.getRemoteAddr());
                } else if (areaFlag.equals("3")) {
                    //选择分支,当休假请假天数小于等于一天
                    JSONObject dataBus = new JSONObject();
                    if (!list.contains("部门负责人") && !list.contains("院领导") && !list.contains("院长")) {
                        if (leaveCount1.equals("0.5") || leaveCount1.equals("1")) {
                            dataBus.put("org", "12");
                            map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
                            bgLeaveManager.doProcess(map);
                            // 撤销申请通过后，将本申请信息改为已撤销
                            bgLeave2.setApplyStatus(BgLeaveManager.LEAVE_FLOW_YCX);

                            // 撤销申请通过后，删除原请假信息
                            bgLeaveManager.deleteBusiness(bgLeave2.getRevokeBusId());

                            request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数

                            //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                            logService.setLogs(bgLeave2.getId(),
                                    "审批请假撤销申请",
                                    "人事审批通过，操作结果：审批通过",
                                    user.getId(),
                                    user.getName(), new Date(),
                                    request.getRemoteAddr());
                        } else if (!leaveCount1.equals("0") && !leaveCount1.equals("0.5") && !leaveCount1.equals("1")) {
                            sign = "1";
                            dataBus.put("org", "11");
                            map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
                            bgLeaveManager.doProcess(map);
                            request.setAttribute("checkName", fgldName);//审核流程未结束时将当下一步审核人的姓名作为参数
                            request.setAttribute("sign", sign);//审核流程未结束时传递参数sign

                            //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                            logService.setLogs(bgLeave2.getId(),
                                    "审批请假撤销申请",
                                    "人事审批通过并提交至" + fgldName + "审批，操作结果：审批中",
                                    user != null ? user.getId() : "未获取到操作用户编号",
                                    user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                                    request != null ? request.getRemoteAddr() : "");
                        }
                    } else if (list.contains("部门负责人") && !list.contains("院领导")) {
                        sign = "1";
                        dataBus.put("org", "22");
                        map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
                        bgLeaveManager.doProcess(map);
                        //审核流程未结束时将当下一步审核人的姓名作为参数
                        request.setAttribute("checkName", fgldName);//审核流程未结束时将当下一步审核人的姓名作为参数
                        request.setAttribute("sign", sign);//审核流程未结束时传递参数sign

                        //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                        logService.setLogs(bgLeave2.getId(),
                                "审批请假撤销申请",
                                "人事审批通过并提交至" + fgldName + "审批，操作结果：审批中",
                                user != null ? user.getId() : "未获取到操作用户编号",
                                user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                                request != null ? request.getRemoteAddr() : "");
                    } else if (list.contains("院领导")) {
                        dataBus.put("org", "21");
                        map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
                        bgLeaveManager.doProcess(map);
                        request.setAttribute("checkName", "蒋青");//审核流程未结束时将当下一步审核人的姓名作为参数
                        request.setAttribute("sign", sign);//审核流程未结束时传递参数sign

                        //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                        logService.setLogs(bgLeave2.getId(),
                                "审批请假撤销申请",
                                "人事审批通过并提交至蒋青审批，操作结果：审批中",
                                user != null ? user.getId() : "未获取到操作用户编号",
                                user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                                request != null ? request.getRemoteAddr() : "");
                    }
                    bgLeave2.setRsyj(bgLeave1.getRsyj());
                    bgLeave2.setRsyjSign(bgLeave1.getRsyjSign());
                    bgLeave2.setRsyjDate(bgLeave1.getRsyjDate());
                } else if (areaFlag.equals("4")) {
                    //选择分支,当休假请假天数小于等于一天
                    JSONObject dataBus = new JSONObject();
                    if (!list.contains("部门负责人") && !list.contains("院领导") && !list.contains("院长")) {
                        bgLeaveManager.doProcess(map);
                        // 撤销申请通过后，将本申请信息改为已撤销
                        bgLeave2.setApplyStatus(BgLeaveManager.LEAVE_FLOW_YCX);

                        // 撤销申请通过后，删除原请假信息
                        bgLeaveManager.deleteBusiness(bgLeave2.getRevokeBusId());

                        request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数

                        //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                        logService.setLogs(bgLeave2.getId(),
                                "审批请假撤销申请",
                                "分管领导审批通过，操作结果：审批通过",
                                user != null ? user.getId() : "未获取到操作用户编号",
                                user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                                request != null ? request.getRemoteAddr() : "");
                    } else if (list.contains("部门负责人") && !list.contains("院领导")) {
                        if (leaveCount1.equals("0.5") || leaveCount1.equals("1")) {
                            dataBus.put("org", "221");
                            map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
                            bgLeaveManager.doProcess(map);
                            // 撤销申请通过后，将本申请信息改为已撤销
                            bgLeave2.setApplyStatus(BgLeaveManager.LEAVE_FLOW_YCX);

                            // 撤销申请通过后，删除原请假信息
                            bgLeaveManager.deleteBusiness(bgLeave2.getRevokeBusId());

                            request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数

                            //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                            logService.setLogs(bgLeave2.getId(),
                                    "审批请假撤销申请",
                                    "分管领导审批通过，操作结果：审批通过",
                                    user != null ? user.getId() : "未获取到操作用户编号",
                                    user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                                    request != null ? request.getRemoteAddr() : "");
                        } else if (!leaveCount1.equals("0") && !leaveCount1.equals("0.5") && !leaveCount1.equals("1")) {
                            dataBus.put("org", "222");
                            map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
                            bgLeaveManager.doProcess(map);
                            request.setAttribute("checkName", "蒋青");//审核流程未结束时将当下一步审核人的姓名作为参数
                            request.setAttribute("sign", sign);//审核流程未结束时传递参数sign

                            //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                            logService.setLogs(bgLeave2.getId(),
                                    "审批请假撤销申请",
                                    "分管领导审批通过并提交至蒋青审批，操作结果：审批中",
                                    user != null ? user.getId() : "未获取到操作用户编号",
                                    user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                                    request != null ? request.getRemoteAddr() : "");
                        }
                    }
                    bgLeave2.setFgyldyj(bgLeave1.getFgyldyj());
                    bgLeave2.setFgyldyjSign(bgLeave1.getFgyldyjSign());
                    bgLeave2.setFgyldyjDate(bgLeave1.getFgyldyjDate());
                } else if (areaFlag.equals("5")) {
                    bgLeaveManager.doProcess(map);
                    bgLeave2.setYzyj(bgLeave1.getYzyj());
                    bgLeave2.setYzyjSign(bgLeave1.getYzyjSign());
                    bgLeave2.setYzyjDate(bgLeave1.getYzyjDate());
                    // 撤销申请通过后，将本申请信息改为已撤销
                    bgLeave2.setApplyStatus(BgLeaveManager.LEAVE_FLOW_YCX);

                    // 撤销申请通过后，删除原始请假信息
                    bgLeaveManager.deleteBusiness(bgLeave2.getRevokeBusId());

                    request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数

                    //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                    logService.setLogs(bgLeave2.getId(),
                            "审批请假撤销申请",
                            "院长审批通过，操作结果：审批通过",
                            user != null ? user.getId() : "未获取到操作用户编号",
                            user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                            request != null ? request.getRemoteAddr() : "");
                }
                bgLeaveManager.save(bgLeave2);

                //发送提醒消息
                try {
                    sendMsg(request, bgLeave2);
                } catch (Exception e) {
                    e.printStackTrace();
                    KhntException kh = new KhntException(e);
                    log.error(kh.getMessage());
                }
            } else {
                return JsonWrapper.failureWrapper("流程ID为空！");
            }
            return JsonWrapper.successWrapper(id);
        }
    }

    /**
     * 请假撤销审核不通过
     **/
    @RequestMapping(value = "shbtgCancel")
    @ResponseBody
    public HashMap<String, Object> shbtgCancel(HttpServletRequest request, String areaFlag, String id, String flowId,
                                         String activityId, String typeCode, String processId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        String bgLeave = request.getParameter("bgLeave").toString();
        BgLeave bgLeave1 = JSON.parseObject(bgLeave, BgLeave.class);
        BgLeave bgLeave2 = bgLeaveManager.get(id);
        BgLeave bgLeaveOld = bgLeaveManager.get(bgLeave2.getRevokeBusId());
        //流程传参
        map.put(FlowExtWorktaskParam.SERVICE_ID, id);
        map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
        map.put(FlowExtWorktaskParam.SERVICE_TITLE, "请假撤销申请-" + bgLeave2.getPeopleName());
        map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
        map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);

        map1.put(FlowExtWorktaskParam.PROCESS_ID, processId);
        map1.put(FlowExtWorktaskParam.FINISH_TYPE, FinishFlowInf.FINISH_TYPE_TERMINATE);

        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        if (StringUtil.isEmpty(id)) {
            return JsonWrapper.failureWrapper("参数错误！");
        } else {
            // 结束流程
            if (StringUtil.isNotEmpty(activityId)) {
                //改变状态并保存审核意见
                bgLeaveManager.stop(map1);
                bgLeave2.setApplyStatus(bgLeaveManager.LEAVE_FLOW_SHBTG);
                // 撤销申请不通过，则将原始请假申请数据状态改为审核通过
                bgLeaveOld.setApplyStatus(BgLeaveManager.LEAVE_FLOW_SHTG);

                if (areaFlag.equals("2")) {
                    bgLeave2.setKsfzryj(bgLeave1.getKsfzryj());
                    bgLeave2.setKsfzryjSing(bgLeave1.getKsfzryjSing());
                    bgLeave2.setKsfzryjDate(bgLeave1.getKsfzryjDate());
                    //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                    logService.setLogs(bgLeave2.getId(),
                            "审批请假撤销申请",
                            "部门主任审批不通过，操作结果：审批不通过",
                            user != null ? user.getId() : "未获取到操作用户编号",
                            user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                            request != null ? request.getRemoteAddr() : "");
                } else if (areaFlag.equals("3")) {
                    bgLeave2.setRsyj(bgLeave1.getRsyj());
                    bgLeave2.setRsyjSign(bgLeave1.getRsyjSign());
                    bgLeave2.setRsyjDate(bgLeave1.getRsyjDate());
                    //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                    logService.setLogs(bgLeave2.getId(),
                            "审批请假撤销申请",
                            "人事审批不通过，操作结果：审批不通过",
                            user != null ? user.getId() : "未获取到操作用户编号",
                            user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                            request != null ? request.getRemoteAddr() : "");
                } else if (areaFlag.equals("4")) {
                    bgLeave2.setFgyldyj(bgLeave1.getFgyldyj());
                    ;
                    bgLeave2.setFgyldyjSign(bgLeave1.getFgyldyjSign());
                    bgLeave2.setFgyldyjDate(bgLeave1.getFgyldyjDate());
                    //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                    logService.setLogs(bgLeave2.getId(),
                            "审批请假撤销申请",
                            "分管领导审批不通过，操作结果：审批不通过",
                            user != null ? user.getId() : "未获取到操作用户编号",
                            user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                            request != null ? request.getRemoteAddr() : "");
                } else if (areaFlag.equals("5")) {
                    bgLeave2.setYzyj(bgLeave1.getYzyj());
                    bgLeave2.setYzyjSign(bgLeave1.getYzyjSign());
                    bgLeave2.setYzyjDate(bgLeave1.getYzyjDate());
                    //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                    logService.setLogs(bgLeave2.getId(),
                            "审批请假撤销申请",
                            "院长审批不通过，操作结果：审批不通过",
                            user != null ? user.getId() : "未获取到操作用户编号",
                            user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                            request != null ? request.getRemoteAddr() : "");
                }
                bgLeaveManager.save(bgLeave2);
                //发送提醒消息
                try {
                    request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数
                    sendMsg(request, bgLeave2);
                } catch (Exception e) {
                    e.printStackTrace();
                    KhntException kh = new KhntException(e);
                    log.error(kh.getMessage());
                }
            } else {
                return JsonWrapper.failureWrapper("流程ID为空！");
            }
            return JsonWrapper.successWrapper(id);
        }
    }
    /**
     * 提交流程并改变状态
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
    public HashMap<String, Object> subFolw(HttpServletRequest request, String id, String people_id,
                                           String leave_count1, String flowId,
                                           String typeCode, String status, String activityId) throws Exception {
        System.out.println("开始提交请休假流程(" + typeCode + flowId + ")=====================================");
        System.out.println("获取请休假申请用户信息=====================================");
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        System.out.println("得到请休假申请用户信息(" + user.getName() + ")=====================================");
        List<?> list = bgLeaveManager.getUserPower(people_id);//获取申请人的权限
        System.out.println("请休假申请人(" + user.getName() + people_id + ")的权限角色=====================================" + list);
        String sign = "0";
        Map<String, Object> map = new HashMap<String, Object>();
        //流程传参
        map.put(FlowExtWorktaskParam.SERVICE_ID, id);
        map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
        map.put(FlowExtWorktaskParam.SERVICE_TITLE, "休假请假申请-" + user.getName());
        map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
        map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
        map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);

        if (StringUtil.isEmpty(id)) {
            System.out.println("请休假流程参数错误=====================================business id is null");
            return JsonWrapper.failureWrapper("参数错误！");
        } else {
            if (StringUtil.isNotEmpty(flowId)) {
                //选择分支
                JSONObject dataBus = new JSONObject();
                if (!list.contains("部门负责人") && !list.contains("院领导") && !list.contains("院长")) {
                    dataBus.put("org", "1");
                    System.out.println("请休假流程参数org=====================================1");
                } else if (list.contains("部门负责人") || list.contains("院领导")) {
                    dataBus.put("org", "2");
                    System.out.println("请休假流程参数org=====================================2");
                }
                map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
                //改变状态
                BgLeave bgLeave = bgLeaveManager.get(id);
                bgLeave.setApplyStatus(BgLeaveManager.LEAVE_FLOW_YTJ);
                // 启动流程
                bgLeaveManager.doStartPress(map);
                bgLeaveManager.save(bgLeave);
                System.out.println("请休假流程提交成功=====================================");
                //获取下一步审核人姓名
                String checkName = bgLeaveManager.getCheckName(bgLeave.getId(), bgLeave.getDepId());
                checkName = StringUtil.isNotEmpty(checkName) ? checkName : "";
                request.setAttribute("sign", sign);//提交时设置sign参数值为0，表示未到分管领导审核
                request.setAttribute("checkName", checkName);
                //发送提醒消息
                try {
                    sendMsg(request, bgLeave);
                } catch (Exception e) {
                    e.printStackTrace();
                    KhntException kh = new KhntException(e);
                    log.error(kh.getMessage());
                }
                System.out.println("记录日志=====================================");
                //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                logService.setLogs(bgLeave.getId(),
                        "提交休假请假申请",
                        "提交休假请假申请至" + checkName + "，操作结果：已提交",
                        user != null ? user.getId() : "未获取到操作用户编号",
                        user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                        request != null ? request.getRemoteAddr() : "");
            } else {
                System.out.println("流程ID为空，请休假流程提交失败=====================================");
                return JsonWrapper.failureWrapper("流程ID为空！");
            }
            System.out.println("提交请休假流程(" + typeCode + flowId + ")完成=====================================");
            return JsonWrapper.successWrapper(id);
        }
    }

    /**
     * 审核通过
     */
    @RequestMapping(value = "subPass")
    @ResponseBody
    public HashMap<String, Object> subPass(HttpServletRequest request, String areaFlag, String id, String flowId,
                                           String activityId, String typeCode) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String sign = "0";
        String checkName = "";
        String bgLeave = request.getParameter("bgLeave").toString();
        BgLeave bgLeave1 = JSON.parseObject(bgLeave, BgLeave.class);
        BgLeave bgLeave2 = bgLeaveManager.get(id);//获取休假请假申请信息
        String leaveCount1 = bgLeave2.getLeaveCount1();//获取请假人的请假天数
        List<?> list = bgLeaveManager.getUserPower(bgLeave2.getPeopleId());//获取申请人的权限
        String fgldName = orgidLeaderidManager.getInfoByOrgid(bgLeave2.getDepId()).getEmpName();//获取分管领导姓名
        fgldName = StringUtil.isNotEmpty(fgldName) ? fgldName : "";
        //流程传参
        map.put(FlowExtWorktaskParam.SERVICE_ID, id);
        map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
        map.put(FlowExtWorktaskParam.SERVICE_TITLE, "休假请假申请-" + bgLeave2.getPeopleName());
        map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
        map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);

        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        if (StringUtil.isEmpty(id)) {
            return JsonWrapper.failureWrapper("参数错误！");
        } else {
            // 启动流程
            if (StringUtil.isNotEmpty(activityId)) {
                //改变状态并保存审核意见
                bgLeave2.setApplyStatus(bgLeaveManager.LEAVE_FLOW_SHZ);
                if (areaFlag.equals("2")) {
                    bgLeaveManager.doProcess(map);
                    bgLeave2.setKsfzryj(bgLeave1.getKsfzryj());
                    bgLeave2.setKsfzryjSing(bgLeave1.getKsfzryjSing());
                    bgLeave2.setKsfzryjDate(bgLeave1.getKsfzryjDate());
                    checkName = bgLeaveManager.getCheckName(bgLeave2.getId(), bgLeave2.getDepId());
                    checkName = StringUtil.isNotEmpty(checkName) ? checkName : "";
                    request.setAttribute("checkName", checkName);//审核流程未结束时将当下一步审核人的姓名作为参数
                    request.setAttribute("sign", sign);//审核流程未结束时传递参数sign

                    //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                    logService.setLogs(bgLeave2.getId(),
                            "审批休假请假申请",
                            "部门负责人审批通过，并提交至" + checkName + "审批，操作结果：审批中",
                            user != null ? user.getId() : "未获取到操作用户编号",
                            user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                            request != null ? request.getRemoteAddr() : "");
                } else if (areaFlag.equals("3")) {
                    //选择分支,当休假请假天数小于等于一天
                    JSONObject dataBus = new JSONObject();
                    if (!list.contains("部门负责人") && !list.contains("院领导") && !list.contains("院长")) {
                        if (leaveCount1.equals("0.5") || leaveCount1.equals("1")) {
                            dataBus.put("org", "12");
                            map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
                            bgLeaveManager.doProcess(map);
                            bgLeave2.setApplyStatus(bgLeaveManager.LEAVE_FLOW_SHTG);
                            request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数

                            //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                            logService.setLogs(bgLeave2.getId(),
                                    "审批休假请假申请",
                                    "人事审批通过，操作结果：审批通过",
                                    user != null ? user.getId() : "未获取到操作用户编号",
                                    user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                                    request != null ? request.getRemoteAddr() : "");
                        } else if (!leaveCount1.equals("0") && !leaveCount1.equals("0.5") && !leaveCount1.equals("1")) {
                            sign = "1";
                            dataBus.put("org", "11");
                            map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
                            bgLeaveManager.doProcess(map);
                            request.setAttribute("checkName", fgldName);//审核流程未结束时将当下一步审核人的姓名作为参数
                            request.setAttribute("sign", sign);//审核流程未结束时传递参数sign

                            //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                            logService.setLogs(bgLeave2.getId(),
                                    "审批休假请假申请",
                                    "人事审批通过并提交至" + fgldName + "审批，操作结果：审批中",
                                    user != null ? user.getId() : "未获取到操作用户编号",
                                    user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                                    request != null ? request.getRemoteAddr() : "");
                        }
                    } else if (list.contains("部门负责人") && !list.contains("院领导")) {
                        sign = "1";
                        dataBus.put("org", "22");
                        map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
                        bgLeaveManager.doProcess(map);
                        //审核流程未结束时将当下一步审核人的姓名作为参数
                        request.setAttribute("checkName", fgldName);//审核流程未结束时将当下一步审核人的姓名作为参数
                        request.setAttribute("sign", sign);//审核流程未结束时传递参数sign

                        //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                        logService.setLogs(bgLeave2.getId(),
                                "审批休假请假申请",
                                "人事审批通过并提交至" + fgldName + "审批，操作结果：审批中",
                                user != null ? user.getId() : "未获取到操作用户编号",
                                user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                                request != null ? request.getRemoteAddr() : "");
                    } else if (list.contains("院领导")) {
                        dataBus.put("org", "21");
                        map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
                        bgLeaveManager.doProcess(map);
                        request.setAttribute("checkName", "蒋青");//审核流程未结束时将当下一步审核人的姓名作为参数
                        request.setAttribute("sign", sign);//审核流程未结束时传递参数sign

                        //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                        logService.setLogs(bgLeave2.getId(),
                                "审批休假请假申请",
                                "人事审批通过并提交至蒋青审批，操作结果：审批中",
                                user != null ? user.getId() : "未获取到操作用户编号",
                                user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                                request != null ? request.getRemoteAddr() : "");
                    }
                    bgLeave2.setRsyj(bgLeave1.getRsyj());
                    bgLeave2.setRsyjSign(bgLeave1.getRsyjSign());
                    bgLeave2.setRsyjDate(bgLeave1.getRsyjDate());
                } else if (areaFlag.equals("4")) {
                    //选择分支,当休假请假天数小于等于一天
                    JSONObject dataBus = new JSONObject();
                    if (!list.contains("部门负责人") && !list.contains("院领导") && !list.contains("院长")) {
                        bgLeaveManager.doProcess(map);
                        bgLeave2.setApplyStatus(bgLeaveManager.LEAVE_FLOW_SHTG);
                        request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数

                        //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                        logService.setLogs(bgLeave2.getId(),
                                "审批休假请假申请",
                                "分管领导审批通过，操作结果：审批通过",
                                user != null ? user.getId() : "未获取到操作用户编号",
                                user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                                request != null ? request.getRemoteAddr() : "");
                    } else if (list.contains("部门负责人") && !list.contains("院领导")) {
                        if (leaveCount1.equals("0.5") || leaveCount1.equals("1")) {
                            dataBus.put("org", "221");
                            map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
                            bgLeaveManager.doProcess(map);
                            bgLeave2.setApplyStatus(bgLeaveManager.LEAVE_FLOW_SHTG);
                            request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数

                            //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                            logService.setLogs(bgLeave2.getId(),
                                    "审批休假请假申请",
                                    "分管领导审批通过，操作结果：审批通过",
                                    user != null ? user.getId() : "未获取到操作用户编号",
                                    user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                                    request != null ? request.getRemoteAddr() : "");
                        } else if (!leaveCount1.equals("0") && !leaveCount1.equals("0.5") && !leaveCount1.equals("1")) {
                            dataBus.put("org", "222");
                            map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
                            bgLeaveManager.doProcess(map);
                            request.setAttribute("checkName", "蒋青");//审核流程未结束时将当下一步审核人的姓名作为参数
                            request.setAttribute("sign", sign);//审核流程未结束时传递参数sign

                            //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                            logService.setLogs(bgLeave2.getId(),
                                    "审批休假请假申请",
                                    "分管领导审批通过并提交至蒋青审批，操作结果：审批中",
                                    user != null ? user.getId() : "未获取到操作用户编号",
                                    user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                                    request != null ? request.getRemoteAddr() : "");
                        }
                    }
                    bgLeave2.setFgyldyj(bgLeave1.getFgyldyj());
                    ;
                    bgLeave2.setFgyldyjSign(bgLeave1.getFgyldyjSign());
                    bgLeave2.setFgyldyjDate(bgLeave1.getFgyldyjDate());
                } else if (areaFlag.equals("5")) {
                    bgLeaveManager.doProcess(map);
                    bgLeave2.setYzyj(bgLeave1.getYzyj());
                    bgLeave2.setYzyjSign(bgLeave1.getYzyjSign());
                    bgLeave2.setYzyjDate(bgLeave1.getYzyjDate());
                    bgLeave2.setApplyStatus(bgLeaveManager.LEAVE_FLOW_SHTG);
                    request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数

                    //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                    logService.setLogs(bgLeave2.getId(),
                            "审批休假请假申请",
                            "院长审批通过，操作结果：审批通过",
                            user != null ? user.getId() : "未获取到操作用户编号",
                            user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                            request != null ? request.getRemoteAddr() : "");
                }
                bgLeaveManager.save(bgLeave2);

                //发送提醒消息
                try {
                    sendMsg(request, bgLeave2);
                } catch (Exception e) {
                    e.printStackTrace();
                    KhntException kh = new KhntException(e);
                    log.error(kh.getMessage());
                }
            } else {
                return JsonWrapper.failureWrapper("流程ID为空！");
            }
            return JsonWrapper.successWrapper(id);
        }
    }

    /**
     * 审核不通过
     **/
    @RequestMapping(value = "shbtg")
    @ResponseBody
    public HashMap<String, Object> shbtg(HttpServletRequest request, String areaFlag, String id, String flowId,
                                         String activityId, String typeCode, String processId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        String bgLeave = request.getParameter("bgLeave").toString();
        BgLeave bgLeave1 = JSON.parseObject(bgLeave, BgLeave.class);
        BgLeave bgLeave2 = bgLeaveManager.get(id);
        //流程传参
        map.put(FlowExtWorktaskParam.SERVICE_ID, id);
        map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
        map.put(FlowExtWorktaskParam.SERVICE_TITLE, "休假请假申请-" + bgLeave2.getPeopleName());
        map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
        map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);

        map1.put(FlowExtWorktaskParam.PROCESS_ID, processId);
        map1.put(FlowExtWorktaskParam.FINISH_TYPE, FinishFlowInf.FINISH_TYPE_TERMINATE);

        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        if (StringUtil.isEmpty(id)) {
            return JsonWrapper.failureWrapper("参数错误！");
        } else {
            // 结束流程
            if (StringUtil.isNotEmpty(activityId)) {
                //改变状态并保存审核意见
                bgLeaveManager.stop(map1);
                bgLeave2.setApplyStatus(bgLeaveManager.LEAVE_FLOW_SHBTG);
                if (areaFlag.equals("2")) {
                    bgLeave2.setKsfzryj(bgLeave1.getKsfzryj());
                    bgLeave2.setKsfzryjSing(bgLeave1.getKsfzryjSing());
                    bgLeave2.setKsfzryjDate(bgLeave1.getKsfzryjDate());
                    //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                    logService.setLogs(bgLeave2.getId(),
                            "审批休假请假申请",
                            "部门主任审批不通过，操作结果：审批不通过",
                            user != null ? user.getId() : "未获取到操作用户编号",
                            user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                            request != null ? request.getRemoteAddr() : "");
                } else if (areaFlag.equals("3")) {
                    bgLeave2.setRsyj(bgLeave1.getRsyj());
                    bgLeave2.setRsyjSign(bgLeave1.getRsyjSign());
                    bgLeave2.setRsyjDate(bgLeave1.getRsyjDate());
                    //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                    logService.setLogs(bgLeave2.getId(),
                            "审批休假请假申请",
                            "人事审批不通过，操作结果：审批不通过",
                            user != null ? user.getId() : "未获取到操作用户编号",
                            user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                            request != null ? request.getRemoteAddr() : "");
                } else if (areaFlag.equals("4")) {
                    bgLeave2.setFgyldyj(bgLeave1.getFgyldyj());
                    ;
                    bgLeave2.setFgyldyjSign(bgLeave1.getFgyldyjSign());
                    bgLeave2.setFgyldyjDate(bgLeave1.getFgyldyjDate());
                    //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                    logService.setLogs(bgLeave2.getId(),
                            "审批休假请假申请",
                            "分管领导审批不通过，操作结果：审批不通过",
                            user != null ? user.getId() : "未获取到操作用户编号",
                            user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                            request != null ? request.getRemoteAddr() : "");
                } else if (areaFlag.equals("5")) {
                    bgLeave2.setYzyj(bgLeave1.getYzyj());
                    bgLeave2.setYzyjSign(bgLeave1.getYzyjSign());
                    bgLeave2.setYzyjDate(bgLeave1.getYzyjDate());
                    //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                    logService.setLogs(bgLeave2.getId(),
                            "审批休假请假申请",
                            "院长审批不通过，操作结果：审批不通过",
                            user != null ? user.getId() : "未获取到操作用户编号",
                            user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                            request != null ? request.getRemoteAddr() : "");
                }
                bgLeaveManager.save(bgLeave2);
                //发送提醒消息
                try {
                    request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数
                    sendMsg(request, bgLeave2);
                } catch (Exception e) {
                    e.printStackTrace();
                    KhntException kh = new KhntException(e);
                    log.error(kh.getMessage());
                }
            } else {
                return JsonWrapper.failureWrapper("流程ID为空！");
            }
            return JsonWrapper.successWrapper(id);
        }
    }

    /**
     * 获取附件
     *
     * @param request
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryFiles")
    @ResponseBody
    public HashMap<String, Object> queryFiles(HttpServletRequest request, String id) throws Exception {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        List<Attachment> list = bgLeaveManager.queryFiles(request, id);
        System.out.println(list.get(0).getFilePath());
        wrapper.put("success", true);
        wrapper.put("list", list);
        return wrapper;

    }

    /**
     * 通过请休假ID查询流程主键ID
     *
     * @param request
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryMainId")
    @ResponseBody
    public HashMap<String, Object> queryMainId(HttpServletRequest request, String id) throws Exception {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        String mainId = bgLeaveManager.queryMainId(request, id);
        System.out.println(mainId);
        wrapper.put("success", true);
        wrapper.put("mainId", mainId);
        return wrapper;

    }

    /**
     * 通过请休假ID查询流程PROCESS_ID
     *
     * @param request
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryProcessId")
    @ResponseBody
    public HashMap<String, Object> queryProcessId(HttpServletRequest request, String id) throws Exception {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        String processId = bgLeaveManager.queryProcessId(request, id);
        System.out.println(processId);
        wrapper.put("success", true);
        wrapper.put("processId", processId);
        return wrapper;

    }

    /**
     * 通过ID查询图片
     *
     * @param request
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "yzImage")
    @ResponseBody
    public void yzImage(HttpServletRequest request, String id) throws Exception {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        bgLeaveManager.yzImage(request, id);
    }

    /**
     * 计算段时间不含周末及法定节假日的天数
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "countDays")
    @ResponseBody
    public HashMap<String, Object> countDays(HttpServletRequest request, String peopleId, Date startDate,
                                             Date endDate, String leaveType) throws Exception {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        if (startDate.getTime() - endDate.getTime() > 0) {
            wrapper.put("success", false);
            wrapper.put("notice", "开始时间不能大于结束时间！");
        } else {
            try {
                //判断此段时间是否已有存在的假条
                Boolean isexist = bgLeaveManager.getLeave(request, peopleId, startDate, endDate);
                if (isexist) {
                    wrapper.put("success", false);
                    wrapper.put("notice", "已有假条与此时间重叠，请检查！");
                } else {
                    int days = this.differentDays(startDate, endDate);
                    if (leaveType.equals("NJ") || leaveType.equals("SHIJ") || leaveType.equals("GWWC")) {
                        days = bgLeaveManager.countDays(request, startDate, endDate);
                    }
                    wrapper.put("success", true);
                    wrapper.put("days", days);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                wrapper.put("success", false);
                wrapper.put("notice", "计算假期天数失败！");
            }
        }
        return wrapper;
    }

    /**
     * 剩余年假天数
     *
     * @param peopleId
     * @throws Exception
     */
    @RequestMapping(value = "queryYearDays")
    @ResponseBody
    public HashMap<String, Object> queryYearDays(HttpServletRequest request, String peopleId) throws Exception {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        String yearDays = bgLeaveManager.queryYearDays(request, peopleId);
        System.out.println("剩余年假天数：" + yearDays);
        wrapper.put("success", true);
        wrapper.put("yearDays", yearDays);
        return wrapper;
    }

    /**
     * 请休假信息统计
     *
     * @param request
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getLeaveInfoCount")
    @ResponseBody
    public HashMap<String, Object> getLeaveInfoCount(HttpServletRequest request) throws Exception {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        try {
            String startDate = request.getParameter("startDate");
            String lastDate = request.getParameter("lastDate");
            String org_id = request.getParameter("org_id");
            String userName = request.getParameter("userName");
            if (StringUtil.isEmpty(startDate)) {
                startDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
            }
            if (StringUtil.isEmpty(lastDate)) {
                lastDate = DateUtil.getLastDateStringOfYear("yyyy-MM-dd");
            }
            wrapper.put("data", bgLeaveManager.getLeaveInfoCount(org_id, userName, startDate, lastDate));
            wrapper.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonWrapper.failureWrapperMsg("统计失败，请重试！");
        }
        return wrapper;
    }

    /**
     * 导出请休假统计
     *
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getLeaveInfoCountExport")
    public String getLeaveInfoCountExport(HttpServletRequest request) throws Exception {
        try {
            String startDate = request.getParameter("startDate");
            String lastDate = request.getParameter("lastDate");
            String org_id = request.getParameter("org_id_val");
            String userName = request.getParameter("userName");
            if (StringUtil.isEmpty(startDate)) {
                startDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
            }
            if (StringUtil.isEmpty(lastDate)) {
                lastDate = DateUtil.getLastDateStringOfYear("yyyy-MM-dd");
            }
            String userName_trans = new String(request.getParameter("userName").getBytes("ISO-8859-1"), "utf-8");
            String org_name_trans = new String(request.getParameter("org_id").getBytes("ISO-8859-1"), "utf-8");
            request.getSession().setAttribute("org_name", org_name_trans);
            request.getSession().setAttribute("userName", userName_trans);
            request.getSession().setAttribute("startDate", startDate);
            request.getSession().setAttribute("lastDate", lastDate);
            request.getSession().setAttribute("data", bgLeaveManager.getLeaveInfoCount(org_id, userName, startDate,
                    lastDate));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("请休假统计表导出失败！");
        }
        return "app/humanresources/leave/leave_statistical_export";
    }

    /**
     * 提交公务外出流程并改变状态
     *
     * @param id
     * @param flowId
     * @param typeCode
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "subFolwGwwc")
    @ResponseBody
    public HashMap<String, Object> subFolwGwwc(HttpServletRequest request, String id, String people_id,
                                               String leave_count1, String flowId,
                                               String typeCode, String status, String activityId) throws Exception {
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        String sign = "0";
        String workTitle = employeeBaseManager.getWorkTitle(people_id);//获取申请人职务
        Map<String, Object> map = new HashMap<String, Object>();
        //流程传参
        map.put(FlowExtWorktaskParam.SERVICE_ID, id);
        map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
        map.put(FlowExtWorktaskParam.SERVICE_TITLE, "休假请假申请-" + user.getName());
        map.put(FlowExtWorktaskParam.FLOW_DEFINITION_ID, flowId);
        map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
        map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);

        if (StringUtil.isEmpty(id)) {
            return JsonWrapper.failureWrapper("参数错误！");
        } else {
            if (StringUtil.isNotEmpty(flowId)) {
                //选择分支
                JSONObject dataBus = new JSONObject();
                BgLeave bgLeave = bgLeaveManager.get(id);
                //若是院党政领导则直接有院长审批，否则按照职称选择流程分支
                if (bgLeave.getDepId().equals("100029")) {
                    dataBus.put("org", "3");
                } else {
                    if (workTitle.indexOf("副主任") != -1 || workTitle.indexOf("副部长") != -1 || workTitle.indexOf("助理") != -1) {
                        if (workTitle.indexOf("主持") == -1 && workTitle.indexOf("主持工作") == -1) {
                            dataBus.put("org", "1");
                        } else if (workTitle.indexOf("主持") != -1 || workTitle.indexOf("主持工作") != -1) {
                            sign = "1";
                            dataBus.put("org", "2");
                        }
                    } else if (workTitle.indexOf("主任") != -1 || workTitle.indexOf("部长") != -1) {
                        if (workTitle.indexOf("副主任") == -1 && workTitle.indexOf("副部长") == -1) {
                            sign = "1";
                            dataBus.put("org", "2");
                        }
                    } else if (workTitle.indexOf("院长") != -1 || workTitle.indexOf("副院长") != -1 || workTitle.indexOf(
                            "副总工") != -1) {
                        dataBus.put("org", "3");
                    }
                }
                map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
                //改变状态
                bgLeave.setApplyStatus(BgLeaveManager.LEAVE_FLOW_YTJ);
                // 启动流程
                bgLeaveManager.doStartPress(map);
                bgLeaveManager.save(bgLeave);
                System.out.println("请休假【公务外出】流程提交成功=====================================");
                //获取下一步审核人姓名
                String checkName = bgLeaveManager.getCheckName(bgLeave.getId(), bgLeave.getDepId());
                checkName = StringUtil.isNotEmpty(checkName) ? checkName : "";
                request.setAttribute("sign", sign);//公务外出提交时设置sign参数值为0
                request.setAttribute("checkName", checkName);
                //发送提醒消息
                try {
                    sendMsg(request, bgLeave);
                } catch (Exception e) {
                    e.printStackTrace();
                    KhntException kh = new KhntException(e);
                    log.error(kh.getMessage());
                }
                //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                logService.setLogs(bgLeave.getId(),
                        "提交休假请假申请",
                        "提交休假请假申请至" + checkName + "，操作结果：已提交",
                        user != null ? user.getId() : "未获取到操作用户编号",
                        user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                        request != null ? request.getRemoteAddr() : "");
            } else {
                return JsonWrapper.failureWrapper("流程ID为空！");
            }
            return JsonWrapper.successWrapper(id);
        }
    }

    /**
     * 审核通过
     */
    @RequestMapping(value = "subPassGwwc")
    @ResponseBody
    public HashMap<String, Object> subPassGwwc(HttpServletRequest request, String areaFlag, String id, String flowId,
                                               String activityId, String typeCode) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String sign = "0";
        String checkName = "";
        String bgLeave = request.getParameter("bgLeave").toString();
        BgLeave bgLeave1 = JSON.parseObject(bgLeave, BgLeave.class);
        BgLeave bgLeave2 = bgLeaveManager.get(id);//获取休假请假申请信息
        //流程传参
        map.put(FlowExtWorktaskParam.SERVICE_ID, id);
        map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
        map.put(FlowExtWorktaskParam.SERVICE_TITLE, "休假请假申请-" + bgLeave2.getPeopleName());
        map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
        map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);

        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        if (StringUtil.isEmpty(id)) {
            return JsonWrapper.failureWrapper("参数错误！");
        } else {
            // 启动流程
            if (StringUtil.isNotEmpty(activityId)) {
                //改变状态并保存审核意见
                bgLeave2.setApplyStatus(bgLeaveManager.LEAVE_FLOW_SHZ);
                if (areaFlag.equals("2")) {
                    sign = "1";
                    bgLeaveManager.doProcess(map);
                    bgLeave2.setKsfzryj(bgLeave1.getKsfzryj());
                    bgLeave2.setKsfzryjSing(bgLeave1.getKsfzryjSing());
                    bgLeave2.setKsfzryjDate(bgLeave1.getKsfzryjDate());
                    checkName = bgLeaveManager.getCheckName(bgLeave2.getId(), bgLeave2.getDepId());
                    checkName = StringUtil.isNotEmpty(checkName) ? checkName : "";
                    request.setAttribute("checkName", checkName);//审核流程未结束时将当下一步审核人的姓名作为参数
                    request.setAttribute("sign", sign);//审核流程未结束时传递参数sign

                    //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                    logService.setLogs(bgLeave2.getId(),
                            "审批休假请假申请",
                            "部门负责人审批通过，并提交至" + checkName + "审批，操作结果：审批中",
                            user != null ? user.getId() : "未获取到操作用户编号",
                            user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                            request != null ? request.getRemoteAddr() : "");
                } else if (areaFlag.equals("4")) {
                    bgLeaveManager.doProcess(map);
                    bgLeave2.setFgyldyj(bgLeave1.getFgyldyj());
                    ;
                    bgLeave2.setFgyldyjSign(bgLeave1.getFgyldyjSign());
                    bgLeave2.setFgyldyjDate(bgLeave1.getFgyldyjDate());
                    checkName = bgLeaveManager.getCheckName(bgLeave2.getId(), bgLeave2.getDepId());
                    checkName = StringUtil.isNotEmpty(checkName) ? checkName : "";
                    request.setAttribute("checkName", checkName);//审核流程未结束时将当下一步审核人的姓名作为参数
                    request.setAttribute("sign", sign);//审核流程未结束时传递参数sign

                    //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                    logService.setLogs(bgLeave2.getId(),
                            "审批休假请假申请",
                            "分管领导审批通过，并提交至" + checkName + "审批，操作结果：审批中",
                            user != null ? user.getId() : "未获取到操作用户编号",
                            user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                            request != null ? request.getRemoteAddr() : "");
                } else if (areaFlag.equals("5")) {
                    bgLeaveManager.doProcess(map);
                    bgLeave2.setYzyj(bgLeave1.getYzyj());
                    bgLeave2.setYzyjSign(bgLeave1.getYzyjSign());
                    bgLeave2.setYzyjDate(bgLeave1.getYzyjDate());
                    bgLeave2.setApplyStatus(bgLeaveManager.LEAVE_FLOW_SHTG);
                    request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数

                    //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                    logService.setLogs(bgLeave2.getId(),
                            "审批休假请假申请",
                            "院长审批通过，操作结果：审批通过",
                            user != null ? user.getId() : "未获取到操作用户编号",
                            user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                            request != null ? request.getRemoteAddr() : "");
                }
                bgLeaveManager.save(bgLeave2);
                //发送提醒消息
                try {
                    sendMsg(request, bgLeave2);
                } catch (Exception e) {
                    e.printStackTrace();
                    KhntException kh = new KhntException(e);
                    log.error(kh.getMessage());
                }
            } else {
                return JsonWrapper.failureWrapper("流程ID为空！");
            }
            return JsonWrapper.successWrapper(id);
        }
    }

    /**
     * 审核不通过
     **/
    @RequestMapping(value = "shbtgGwwc")
    @ResponseBody
    public HashMap<String, Object> shbtgGwwc(HttpServletRequest request, String areaFlag, String id, String flowId,
                                             String activityId, String typeCode, String processId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        String bgLeave = request.getParameter("bgLeave").toString();
        BgLeave bgLeave1 = JSON.parseObject(bgLeave, BgLeave.class);
        BgLeave bgLeave2 = bgLeaveManager.get(id);
        //流程传参
        map.put(FlowExtWorktaskParam.SERVICE_ID, id);
        map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
        map.put(FlowExtWorktaskParam.SERVICE_TITLE, "休假请假申请-" + bgLeave2.getPeopleName());
        map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
        map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);

        map1.put(FlowExtWorktaskParam.PROCESS_ID, processId);
        map1.put(FlowExtWorktaskParam.FINISH_TYPE, FinishFlowInf.FINISH_TYPE_TERMINATE);

        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        if (StringUtil.isEmpty(id)) {
            return JsonWrapper.failureWrapper("参数错误！");
        } else {
            // 结束流程
            if (StringUtil.isNotEmpty(activityId)) {
                //改变状态并保存审核意见
                bgLeaveManager.stop(map1);
                bgLeave2.setApplyStatus(bgLeaveManager.LEAVE_FLOW_SHBTG);
                if (areaFlag.equals("2")) {
                    bgLeave2.setKsfzryj(bgLeave1.getKsfzryj());
                    bgLeave2.setKsfzryjSing(bgLeave1.getKsfzryjSing());
                    bgLeave2.setKsfzryjDate(bgLeave1.getKsfzryjDate());
                    //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                    logService.setLogs(bgLeave2.getId(),
                            "审批休假请假申请",
                            "部门主任审批不通过，操作结果：审批不通过",
                            user != null ? user.getId() : "未获取到操作用户编号",
                            user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                            request != null ? request.getRemoteAddr() : "");
                } else if (areaFlag.equals("4")) {
                    bgLeave2.setFgyldyj(bgLeave1.getFgyldyj());
                    ;
                    bgLeave2.setFgyldyjSign(bgLeave1.getFgyldyjSign());
                    bgLeave2.setFgyldyjDate(bgLeave1.getFgyldyjDate());
                    //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                    logService.setLogs(bgLeave2.getId(),
                            "审批休假请假申请",
                            "分管领导审批不通过，操作结果：审批不通过",
                            user != null ? user.getId() : "未获取到操作用户编号",
                            user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                            request != null ? request.getRemoteAddr() : "");
                } else if (areaFlag.equals("5")) {
                    bgLeave2.setYzyj(bgLeave1.getYzyj());
                    bgLeave2.setYzyjSign(bgLeave1.getYzyjSign());
                    bgLeave2.setYzyjDate(bgLeave1.getYzyjDate());
                    //业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
                    logService.setLogs(bgLeave2.getId(),
                            "审批休假请假申请",
                            "院长审批不通过，操作结果：审批不通过",
                            user != null ? user.getId() : "未获取到操作用户编号",
                            user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
                            request != null ? request.getRemoteAddr() : "");
                }
                bgLeaveManager.save(bgLeave2);
                //发送提醒消息
                try {
                    request.setAttribute("checkName", user.getName());//审核流程结束时将最后审核人的姓名作为参数
                    sendMsg(request, bgLeave2);
                } catch (Exception e) {
                    e.printStackTrace();
                    KhntException kh = new KhntException(e);
                    log.error(kh.getMessage());
                }
            } else {
                return JsonWrapper.failureWrapper("流程ID为空！");
            }
            return JsonWrapper.successWrapper(id);
        }
    }

    /**
     * 公务外出信息统计
     *
     * @param request
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getGwwcInfoCount")
    @ResponseBody
    public HashMap<String, Object> getGwwcInfoCount(HttpServletRequest request) throws Exception {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        try {
            String startDate = request.getParameter("startDate");
            String lastDate = request.getParameter("lastDate");
            String org_id = request.getParameter("org_id");
            String userName = request.getParameter("userName");
            if (StringUtil.isEmpty(startDate)) {
                startDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
            }
            if (StringUtil.isEmpty(lastDate)) {
                lastDate = DateUtil.getLastDateStringOfYear("yyyy-MM-dd");
            }
            wrapper.put("data", bgLeaveManager.getGwwcInfoCount(org_id, userName, startDate, lastDate));
            wrapper.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonWrapper.failureWrapperMsg("统计失败，请重试！");
        }
        return wrapper;
    }

    /**
     * 导出公务外出统计
     *
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getGwwcInfoCountExport")
    public String getGwwcInfoCountExport(HttpServletRequest request) throws Exception {
        try {
            String startDate = request.getParameter("startDate");
            String lastDate = request.getParameter("lastDate");
            String org_id = request.getParameter("org_id_val");
            String userName = request.getParameter("userName");
            if (StringUtil.isEmpty(startDate)) {
                startDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
            }
            if (StringUtil.isEmpty(lastDate)) {
                lastDate = DateUtil.getLastDateStringOfYear("yyyy-MM-dd");
            }
            String userName_trans = new String(request.getParameter("userName").getBytes("ISO-8859-1"), "utf-8");
            String org_name_trans = new String(request.getParameter("org_id").getBytes("ISO-8859-1"), "utf-8");
            request.getSession().setAttribute("org_name", org_name_trans);
            request.getSession().setAttribute("userName", userName_trans);
            request.getSession().setAttribute("data", bgLeaveManager.getGwwcInfoCount(org_id, userName, startDate,
                    lastDate));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("请休假统计表导出失败！");
        }
        return "app/humanresources/leave/official_business_export";
    }

    // 查询流程步骤信息
    @RequestMapping(value = "getFlowStep")
    @ResponseBody
    public ModelAndView getFlowStep(HttpServletRequest request)
            throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        map = bgLeaveManager.getFlowStep(request.getParameter("id"));

        ModelAndView mav = new ModelAndView("app/humanresources/leave/flow_card", map);

        return mav;

    }

    /**
     * 计算date1到date2的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) //闰年
                {
                    timeDistance += 366;
                } else //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1 + 1);
        } else //不同年
        {
            System.out.println("计算出的天数为: " + (day2 - day1 + 1));
            return day2 - day1 + 1;
        }
    }

    /**
     * 请休假申请消息提醒
     *
     * @param request -- 请求对象
     * @param bgLeave -- 请休假申请对象
     * @return
     * @throws Exception
     */
    public void sendMsg(HttpServletRequest request, BgLeave bgLeave) throws Exception {
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        String sign = (String) request.getAttribute("sign");
        String wx_check_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb0f376eb09e64dd3" +
                "&redirect_uri=http://kh.scsei.org.cn/WxLeaveAction/weixinUserInfo.do?businessId=" + bgLeave.getId()
                + "&response_type=code&scope=snsapi_base&state=STATE";
        String leaveType = "";
        if (bgLeave.getLeaveType().equals("GWWC")) {
            leaveType = "公务外出";
        } else if (bgLeave.getLeaveType().equals("NJ")) {
            leaveType = "年假";
        } else if (bgLeave.getLeaveType().equals("SHIJ")) {
            leaveType = "事假";
        } else if (bgLeave.getLeaveType().equals("HJ")) {
            leaveType = "婚假";
        } else if (bgLeave.getLeaveType().equals("CJ")) {
            leaveType = "产假";
        } else if (bgLeave.getLeaveType().equals("TQJ")) {
            leaveType = "探亲假";
        } else if (bgLeave.getLeaveType().equals("BJ")) {
            leaveType = "病假";
        } else if (bgLeave.getLeaveType().equals("SANGJ")) {
            leaveType = "丧假";
        } else if (bgLeave.getLeaveType().equals("PCJ")) {
            leaveType = "陪产假";
        } else if (bgLeave.getLeaveType().equals("OTHER")) {
            leaveType = "其他";
        }

        map1.put("leaveType", leaveType);
        map1.put("startDate", new SimpleDateFormat("yyyy-MM-dd").format(bgLeave.getStartDate()));
        map1.put("endDate", new SimpleDateFormat("yyyy-MM-dd").format(bgLeave.getEndDate()));
        try {
            System.out.println("发送消息开始=====================================");
            if ("YTJ".equals(bgLeave.getApplyStatus()) || "SPZ".equals(bgLeave.getApplyStatus())) {
                //获取下一步审核人电话
                String phoneTemp = bgLeaveManager.getAuditor(bgLeave.getId(), sign);
                //获取下一步审核人姓名
                String checkName = (String) request.getAttribute("checkName");
                map1.put("peopleName", bgLeave.getPeopleName());
                map1.put("depName", bgLeave.getDepName());
                map1.put("leaveCount1", bgLeave.getLeaveCount1());
                map1.put("checkName", checkName);
                map2.put("url", wx_check_url);
                //发送消息给下一步审核人
                messageService.sendMassageByConfig(request, bgLeave.getId(), phoneTemp, null, "leave_apply_auditor",
                        null, map1, map2, null, Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD);
                System.out.println("this auditor's phone is :" + phoneTemp);
                //发送消息给请假人
                messageService.sendMassageByConfig(request, bgLeave.getId(),
                        employeeBaseManager.get(bgLeave.getPeopleId()).getEmpPhone(), null, "leave_apply_user",
                        null, map1, null, null, Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD);
            } else if ("SPTG".equals(bgLeave.getApplyStatus())) {
                //最后审核人
                String checkName = (String) request.getAttribute("checkName");
                map1.put("checkName", checkName);
                //发送消息给请假人
                messageService.sendMassageByConfig(request, bgLeave.getId(),
                        employeeBaseManager.get(bgLeave.getPeopleId()).getEmpPhone(), null, "leave_apply_pass",
                        null, map1, null, null, Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD);
            } else if ("SPBTG".equals(bgLeave.getApplyStatus())) {
                //最后审核人
                String checkName = (String) request.getAttribute("checkName");
                map1.put("checkName", checkName);
                //发送消息给请假人
                messageService.sendMassageByConfig(request, bgLeave.getId(),
                        employeeBaseManager.get(bgLeave.getPeopleId()).getEmpPhone(), null, "leave_apply_fail",
                        null, map1, null, null, Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD);
            } else if ("YCX".equals(bgLeave.getApplyStatus())) {

            } else if ("YXJ".equals(bgLeave.getApplyStatus())) {

            }
            System.out.println("发送消息结束=====================================");
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.toString());
        }
    }
}