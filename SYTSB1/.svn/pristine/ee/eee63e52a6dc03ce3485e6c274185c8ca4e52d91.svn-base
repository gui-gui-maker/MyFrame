package com.scts.payment.web;

import com.khnt.base.Factory;
import com.khnt.base.SysParaInf;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.fileupload.service.IAttachmentManager;
import com.khnt.rbac.bean.Org;
import com.khnt.rbac.impl.manager.OrgManagerImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.BeanUtils;
import com.khnt.utils.DateUtil;
import com.khnt.utils.FileUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.device.bean.DeviceDocument;
import com.lsts.device.service.DeviceService;
import com.lsts.employee.service.EmployeesService;
import com.lsts.finance.bean.CwBank2Pay;
import com.lsts.finance.bean.CwBankDTO;
import com.lsts.finance.bean.CwBankDetail;
import com.lsts.finance.bean.CwInvoiceF;
import com.lsts.finance.service.CwBank2PayManager;
import com.lsts.finance.service.CwBankDetailManager;
import com.lsts.finance.service.CwInvoiceFManager;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.bean.InspectionZZJDInfo;
import com.lsts.inspection.bean.ReportDrawSign;
import com.lsts.inspection.dao.ReportDrawSignMiddleDao;
import com.lsts.inspection.service.InspectionInfoService;
import com.lsts.inspection.service.InspectionZZJDInfoService;
import com.lsts.inspection.service.ReportDrawSignService;
import com.lsts.log.service.SysLogService;
import com.lsts.org.bean.EnterInfo;
import com.lsts.org.service.EnterService;
import com.scts.payment.bean.*;
import com.scts.payment.dao.InspectionPayInfoDao;
import com.scts.payment.order.service.LockUserCidService;
import com.scts.payment.service.InspectionPayDetailService;
import com.scts.payment.service.InspectionPayInfoService;
import com.scts.payment.service.InspectionPaySignService;
import com.scts.payment.service.ReportBorrowService;
import com.scts.push.service.PushManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import util.ReportNum;
import util.TS_Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;

/**
 * 报检业务收费信息控制器
 *
 * @author GaoYa
 * @ClassName InspectionPayBackAction
 * @JDK 1.7
 * @date 2014-05-04 下午04:47:00
 */
@Controller
@RequestMapping("payment/payInfo")
public class InspectionPayInfoAction extends
        SpringSupportAction<InspectionPayInfo, InspectionPayInfoService> {
    private Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private InspectionPayInfoService inspectionPayInfoService;
    @Autowired
    private InspectionPayDetailService inspectionPayDetailService;
    @Autowired
    private InspectionInfoService inspectionInfoService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private OrgManagerImpl orgManager;
    @Autowired
    private InspectionZZJDInfoService inspectionZZJDInfoService;
    @Autowired
    private ReportBorrowService reportBorrowService;
    @Autowired
    private SysLogService logService;
    @Autowired
    private InspectionPayInfoDao inspectionPayInfoDao;
    @Autowired
    private CwBankDetailManager cwBankDetailManager;
    @Autowired
    private CwInvoiceFManager cwInvoiceFManager;
    @Autowired
    private CwBank2PayManager cwBank2PayManager;
    @Autowired
    private MessageService messageService;
    @Autowired
    private EmployeesService employeesService;
    @Autowired
    private EnterService enterService;
    @Autowired
    private InspectionPaySignService inspectionPaySignService;
    @Autowired
    private ReportDrawSignMiddleDao reportDrawSignDao;
    @Autowired
    ReportDrawSignService reportDrawSignService;

    @Autowired
    private PushManager pushManager;
    @Autowired
	private IAttachmentManager attachmentManager;
    @Autowired
	private LockUserCidService lockUserCidService;

    /**
     * 是否涉及转账业务
     *
     * @param type
     * @return
     */
    private boolean isZhuanZhang(String type) {
        return "2".equals(type) || "4".equals(type) || "7".equals(type) || "14".equals(type) || "15".equals(type);
    }

    // 机电类设备报告收费保存
    @RequestMapping(value = "savePayInfo")
    @ResponseBody
    public HashMap<String, Object> savePayInfo(HttpServletRequest request,
                                               @RequestBody InspectionPayInfo inspectionPayInfo) {
        try {
        	  long et = System.currentTimeMillis();
            CurrentSessionUser user = super.getCurrentUser();
            long[] t = new long[14]; 
            t[0] = System.currentTimeMillis();
            // 1、验证发票号是否存在
            String invoice_no = inspectionPayInfo.getInvoice_no();
            // 1.2、原发票号
            String old_invoice_no = "";

            boolean isAdd = true;
            String status = request.getParameter("status");
            if (!"add".equals(status)) {
                isAdd = false;
            }

            t[1] = System.currentTimeMillis();
            double old_pay_money = 0;
            if (StringUtil.isNotEmpty(inspectionPayInfo.getId())) {
                old_invoice_no = inspectionPayInfoService.queryInvoice_no(inspectionPayInfo.getId());
                old_pay_money = inspectionPayInfoService.queryMoney(inspectionPayInfo.getId());
            }

            t[2] = System.currentTimeMillis();
            if (StringUtil.isNotEmpty(invoice_no)) {
            	//##############InvoiceDate  InvoiceUnit  Status
            	Object object = cwInvoiceFManager.querysByInvoiceNoForPay(invoice_no);
            	if (object==null) {
                    return JsonWrapper.failureWrapperMsg("亲，系统未能识别发票号（" + invoice_no + "），请联系财务人员导入！");
                } else {
                	Object [] objs =  (Object[]) object;
                	
                    if ("SY".equals(objs[2]==null?"":objs[2].toString())) {
                        String pay_date = DateUtil.format(inspectionPayInfo.getPay_date(), Constant.defaultDatePattern);
                        String invoice_date = objs[0]==null?"":objs[2].toString().substring(0, 10);

                        if (pay_date.equals(invoice_date)
                                && inspectionPayInfo.getCompany_name().equals(objs[1])) {
                            // 相同开票单位和相同开票日期允许重复登记收费，反之
                        } else {
                            if (isAdd) {
                                return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经使用，请核实！");
                            } else {
                                if (StringUtil.isNotEmpty(old_invoice_no)) {
                                    if (!invoice_no.equals(old_invoice_no)) {
                                        return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经使用，请核实！");
                                    }
                                }
                            }
                        }
                    }
                    if ("ZF".equals(objs[2]==null?"":objs[2].toString())) {
                        return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经作废，不能使用，请核实！");
                    }
                }
                	/*List<CwInvoiceF> list = cwInvoiceFManager.querysByInvoice_no(invoice_no);
            	
                if (list.isEmpty()) {
                    return JsonWrapper.failureWrapperMsg("亲，系统未能识别发票号（" + invoice_no + "），请联系财务人员导入！");
                } else {
                    CwInvoiceF cwInvoiceF = list.get(0);
                    if ("SY".equals(cwInvoiceF.getStatus())) {
                        String pay_date = DateUtil.format(inspectionPayInfo.getPay_date(), Constant.defaultDatePattern);
                        String invoice_date = DateUtil.format(cwInvoiceF.getInvoiceDate(), Constant.defaultDatePattern);

                        if (pay_date.equals(invoice_date)
                                && inspectionPayInfo.getCompany_name().equals(cwInvoiceF.getInvoiceUnit())) {
                            // 相同开票单位和相同开票日期允许重复登记收费，反之
                        } else {
                            if (isAdd) {
                                return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经使用，请核实！");
                            } else {
                                if (StringUtil.isNotEmpty(old_invoice_no)) {
                                    if (!invoice_no.equals(old_invoice_no)) {
                                        return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经使用，请核实！");
                                    }
                                }
                            }
                        }
                    }
                    if ("ZF".equals(cwInvoiceF.getStatus())) {
                        return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经作废，不能使用，请核实！");
                    }
                }
                */
            } else {
                if (!"3".equals(inspectionPayInfo.getPay_type())) {
                    return JsonWrapper.failureWrapperMsg("亲，发票号是必填项，请填写发票号！");
                }
            }

            t[3] = System.currentTimeMillis();
            // 2、当收费方式为“银行转账、现金及转账、转账及pos机刷卡”时，验证转账剩余金额是否充足
            double total_fee = Double.parseDouble(inspectionPayInfo.getRemark()); // 本次转账总金额
            if (isZhuanZhang(inspectionPayInfo.getPay_type())) {
                if (inspectionPayInfo.getCwBankDTOList() != null) {
                    List<CwBankDTO> cwBankList = inspectionPayInfo.getCwBankDTOList();
                    if (!cwBankList.isEmpty()) {
                        // 4.1、验证银行转账余额是否不足
                        String bank_ids = "";
                        for (CwBankDTO cwBankDTO : cwBankList) {
                            if (bank_ids.length() > 0) {
                                bank_ids += ",";
                            }
                            bank_ids += cwBankDTO.getId();
                        }
                        if (StringUtil.isNotEmpty(bank_ids)) {
                            // 获取银行转账剩余金额
                            double money = cwBankDetailManager.queryBankMoney(bank_ids);
                            // 如果剩余金额不足，提示用户
                            if (money < total_fee) {
                                return JsonWrapper.failureWrapperMsg("亲，您所选的银行转账余额不足，请重新选择！");
                            }
                        }
                    }
                }
            }
            t[4] = System.currentTimeMillis();
            // 3、保存业务信息（更新收费状态、实收金额等）
            int count = inspectionPayInfo.getInspectionInfoDTOList().size();
            String ids = "";
            String report_com_name = ""; // 报告书使用单位（受检单位）
            String report_sn = ""; // 报告书编号（用于西藏报告提醒消息推送）
            String check_unit_id = "";
            String check_department = ""; // 检验部门
            for (InspectionInfoDTO inspectionInfoDTO : inspectionPayInfo.getInspectionInfoDTOList()) {
            	String infoId = inspectionInfoDTO.getId();
            	Object obj = inspectionInfoService.queryInfoForPay(infoId);
            	Object[] infoObj = null; 
            	if(obj!=null) {
            		infoObj = (Object[]) obj;
            	}
            	//报告编号
            	if(infoObj!=null) {
            		
            		report_sn = infoObj[0]==null?"":infoObj[0].toString();
            		check_unit_id = infoObj[2]==null?"":infoObj[2].toString();
            		check_department = infoObj[3]==null?"":infoObj[3].toString();

            		
            		
            		 if ("3".equals(inspectionPayInfo.getPay_type())) {
            			 
            			 inspectionInfoService.updatePayInfo(infoId,inspectionPayInfo.getPay_type(),inspectionPayInfo.getInvoice_no(),"2",inspectionPayInfo.getPay_date());
            			 
            		 }else  if (!"10".equals(inspectionPayInfo.getPay_type())
                             && !"11".equals(inspectionPayInfo.getPay_type())) {
            		 
            			 inspectionInfoService.updatePayInfo(infoId,inspectionPayInfo.getPay_type(),inspectionPayInfo.getInvoice_no(),null,inspectionPayInfo.getPay_date());
            		 }
            		 
            		//修改收费金额
             		inspectionInfoService.updateReceivable(infoId);
            	}
              /*  InspectionInfo inspectionInfo = inspectionInfoService.get(inspectionInfoDTO.getId());
                if (StringUtil.isEmpty(report_sn)) {
                    report_sn = inspectionInfo.getReport_sn();
                }
                // 实收金额
                if (inspectionInfo.getReceivable() == null || inspectionInfo.getReceivable() == 0) {
                    inspectionInfo.setReceivable(inspectionInfo.getAdvance_fees()); // 实收金额
                }
                // 缴费方式 1 现金缴费 2 转账 3 免收费 4 现金及转账 5 pos 6 现金及pos 7 转账及pos
                inspectionInfo.setFee_type(inspectionPayInfo.getPay_type());
                inspectionInfo.setInvoice_no(inspectionPayInfo.getInvoice_no()); // 发票号
                if ("3".equals(inspectionPayInfo.getPay_type())) {
                    inspectionInfo.setAdvance_type("2"); // 收费类型 0 正常收费 1 协议收费 2
                } else {
                    // 10：支付宝 11：微信
                    if (!"10".equals(inspectionPayInfo.getPay_type())
                            && !"11".equals(inspectionPayInfo.getPay_type())) {
                        inspectionInfo.setAdvance_type("0");
                    }
                }
                // 收费状态（0 默认 1 待收费 2 已收费 3 借报告 4 借发票 5 借报告和发票）
                inspectionInfo.setFee_status("2");
                inspectionInfo.setInvoice_date(inspectionPayInfo.getPay_date()); // 缴费日期

                if (StringUtil.isEmpty(check_unit_id)) {
                    check_unit_id = inspectionInfo.getCheck_unit_id();
                }
                if (StringUtil.isEmpty(check_department)) {
                    if (StringUtil.isNotEmpty(inspectionInfo.getCheck_unit_id())) {
                        Org org = orgManager.get(inspectionInfo.getCheck_unit_id());
                        check_department = StringUtil.isNotEmpty(org.getOrgName()) ? org.getOrgName() : ""; // 检验部门
                    }
                }

                inspectionInfoService.save(inspectionInfo);*/
                // 4、查询是否有外借情况
                ReportBorrow reportBorrow = reportBorrowService.queryByInspectionInfoID(infoId);
                if (reportBorrow != null) {
                    // 如果存在外借情况，重新收费后，重新更新外借业务和外借欠款金额
                    String fk_inspection_info_id = reportBorrow.getFk_inspection_info_id();
                    fk_inspection_info_id = fk_inspection_info_id.replace(infoId, "");
                    double unpay = Double.parseDouble(reportBorrow.getUnpay_amount());
                    unpay = unpay - (infoObj[1]==null?0:Double.parseDouble(infoObj[1].toString()));
                    reportBorrow.setFk_inspection_info_id(fk_inspection_info_id); // 更新外借业务id
                    reportBorrow.setUnpay_amount(unpay + ""); // 更新外借欠款金额
                    boolean isCandel = true; // 是否是全部取消外借重新收费
                    String[] idArr = fk_inspection_info_id.split(",");
                    for (int i = 0; i < idArr.length; i++) {
                        if (StringUtil.isNotEmpty(idArr[i])) {
                            isCandel = false;
                        }
                    }
                    if (isCandel) {
                        reportBorrow.setBorrow_status("3"); // 3：已取消外借
                    }
                    if (unpay == 0) {
                        reportBorrow.setBorrow_status("3"); // 3：已取消外借
                    }
                    reportBorrowService.save(reportBorrow);
                    logService.setLogs(infoId, "【外借业务】报告收费", "【外借业务】报告收费", user.getId(), user.getName(),
                            new Date(), request.getRemoteAddr());
                }

                if (ids.length() > 0) {
                    ids += ",";
                }
                ids += infoId;
                if (StringUtil.isEmpty(report_com_name)) {
                    report_com_name = infoObj[4]==null?"":infoObj[4].toString();
                }
            }
            t[5] = System.currentTimeMillis();
            // 5、保存缴费信息
            if (StringUtil.isEmpty(inspectionPayInfo.getPay_no())) {
                String curYearMonth = TS_Util.getCurYearMonth(); // 获取当前年月
                String no = inspectionPayInfoService.queryNextPay_no(curYearMonth); // 获取编号后四位序号
                // 编号 = 当前年月 + 4位序号
                inspectionPayInfo.setPay_no(curYearMonth + no);
            }
            inspectionPayInfo.setPayment_status("2"); // 缴费状态 0 默认 1 待收费 2 已收费
            inspectionPayInfo.setCreated_date(new Date()); // 操作日期
            inspectionPayInfo.setCreated_by(user.getName()); // 操作人
            if (StringUtil.isEmpty(inspectionPayInfo.getReceive_man_name())) {
                inspectionPayInfo.setReceive_man_name(user.getName());
            }
            inspectionPayInfoService.save(inspectionPayInfo);
            t[6] = System.currentTimeMillis();
            // 6、保存缴费详细信息
            InspectionPayDetail inspectionPayDetail = new InspectionPayDetail();
            BeanUtils.copyProperties(inspectionPayDetail, inspectionPayInfo);
            inspectionPayDetail.setId(null);
            inspectionPayDetail.setFk_pay_info_id(inspectionPayInfo.getId());
            inspectionPayDetail.setReceive_date(inspectionPayInfo.getPay_date());
            inspectionPayDetailService.save(inspectionPayDetail);
            t[7] = System.currentTimeMillis();
            // 7、当收费方式为“银行转账、现金及转账、转账及pos机刷卡”时，返写银行转账记录信息表
            String cw_bank_ids = "";
            if (isZhuanZhang(inspectionPayInfo.getPay_type())) {
                if (inspectionPayInfo.getCwBankDTOList() != null) {
                    List<CwBankDTO> cwBankList = inspectionPayInfo.getCwBankDTOList();
                    if (!cwBankList.isEmpty()) {
                        for (CwBankDTO cwBankDTO : cwBankList) {
                            // 4.2、保存收费业务与银行转账记录关联信息表
                            inspectionPayInfoDao.insertCwBank2Pay(StringUtil.getUUID(), cwBankDTO.getId(),
                                    inspectionPayInfo.getId(), cwBankDTO.getUsedMoney(), cwBankDTO.getPay_remark());

                            // 4.3、返写银行转账记录信息表
                            CwBankDetail cwBankDetail = cwBankDetailManager.get(cwBankDTO.getId());
                            // 剩余金额
                            cwBankDetail.setRestMoney(TS_Util.ratioTransform((cwBankDetail.getRestMoney().doubleValue()
                                    - cwBankDTO.getUsedMoney().doubleValue())));
                            // 本次已收金额
                            cwBankDetail.setUsedMoney(TS_Util.ratioTransform((cwBankDetail.getUsedMoney().doubleValue()
                                    + cwBankDTO.getUsedMoney().doubleValue())));
                            cwBankDetailManager.save(cwBankDetail);
                            if (cw_bank_ids.length() > 0) {
                                cw_bank_ids += ",";
                            }
                            cw_bank_ids += cwBankDetail.getId();

                            logService.setLogs(cwBankDTO.getId(), "报告收费，使用银行转账",
                                    "本次剩余金额：" + TS_Util.ratioTransform(cwBankDetail.getRestMoney().doubleValue()) + "，"
                                            + "本次收费金额："
                                            + TS_Util.ratioTransform(cwBankDTO.getUsedMoney().doubleValue()),
                                    user.getId(), user.getName(), new Date(), request.getRemoteAddr());
                        }
                    }

                }
            } else {
                // 10：支付宝 11：微信
                if ("10".equals(inspectionPayInfo.getPay_type()) || "11".equals(inspectionPayInfo.getPay_type())) {
                    if (StringUtil.isEmpty(inspectionPayInfo.getFk_alipay_trade_id())
                            || StringUtil.isEmpty(inspectionPayInfo.getFk_weixin_trade_id())) {
                        List<MachinePayTrade> tradeList = inspectionPayInfo.getMachinePayTradeList();
                        if (!tradeList.isEmpty()) {
                            for (MachinePayTrade trade : tradeList) {
                                if (StringUtil.isEmpty(inspectionPayInfo.getFk_alipay_trade_id())) {
                                    inspectionPayInfo.setFk_alipay_trade_id(trade.getId());
                                    break;
                                }
                                if (StringUtil.isEmpty(inspectionPayInfo.getFk_weixin_trade_id())) {
                                    inspectionPayInfo.setFk_weixin_trade_id(trade.getId());
                                    break;
                                }
                            }
                        }
                    }

                }
            }
            inspectionPayInfo.setFk_cw_bank_id(cw_bank_ids);
            inspectionPayInfoService.save(inspectionPayInfo);
            t[8] = System.currentTimeMillis();
            // 8、返写发票信息
            if (StringUtil.isNotEmpty(invoice_no) && !"3".equals(inspectionPayInfo.getPay_type())) {
                if (StringUtil.isNotEmpty(report_com_name)) {
                    inspectionPayInfo.setReport_com_name(report_com_name);
                }
                if (StringUtil.isEmpty(old_invoice_no)) {
                    // 5.1 返写更新本次使用的新发票号信息
                    updateCwInvoiceF(request, inspectionPayInfo, check_department, isAdd, false);
                } else {
                    if (!invoice_no.equals(old_invoice_no)) {
                        // 5.2 返写更新原发票号信息
                        // 原票号是否作废（0：否 1：是）
                        String if_del_invoice = request.getParameter("if_del_invoice");
                        if ("1".equals(if_del_invoice)) {
                            zfOldCwInvoiceF(request, old_invoice_no, old_pay_money, check_department);
                        } else {
                            updateOldCwInvoiceF(request, old_invoice_no, old_pay_money, check_department);
                        }
                    }
                    updateCwInvoiceF(request, inspectionPayInfo, check_department, isAdd, false);
                }
            }
            t[9] = System.currentTimeMillis();
            // 9、记录日志
            String[] idArr = ids.split(",");
            for (int i = 0; i < idArr.length; i++) {
                logService.setLogs(idArr[i], "报告收费", "报告收费", user.getId(), user.getName(), new Date(),
                        request.getRemoteAddr());
            }
            t[10] = System.currentTimeMillis();
            // 10、西藏和新疆报告提醒消息推送
			/*
			 * if (StringUtil.isNotEmpty(check_unit_id)) { if
			 * (Constant.CHECK_UNIT_100069.equals(check_unit_id)) { // 获取发送内容 String content
			 * = Constant.getXzReportPayNoticeContent(inspectionPayInfo.getCompany_name(),
			 * count, report_sn); // 获取发送目标号码 String mobile =
			 * employeesService.getMobile(Constant.XZ_REPORT_NOTIC_EMPID); // 发送微信
			 * messageService.sendWxMsg(request, inspectionPayInfo.getId(),
			 * Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, content, mobile); //
			 * 发送短信 // messageService.sendMoMsg(request, // inspectionPayInfo.getId(),
			 * content, mobile); } // 2017-12-12应明子涵要求，增加新疆报告已收费提醒消息推送 if
			 * (Constant.CHECK_UNIT_100090.equals(check_unit_id)) { // 获取发送内容 String content
			 * = Constant.getXzReportPayNoticeContent(inspectionPayInfo.getCompany_name(),
			 * count, report_sn); // 获取发送目标号码 String emp_id =
			 * Factory.getSysPara().getProperty("XJ_REPORT_NOTIC_EMPID"); if
			 * (StringUtil.isEmpty(emp_id)) { emp_id = Constant.XJ_REPORT_NOTIC_EMPID; }
			 * String mobile = employeesService.getMobile(emp_id); // 发送微信
			 * messageService.sendWxMsg(request, inspectionPayInfo.getId(),
			 * Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, content, mobile); //
			 * 发送短信 // messageService.sendMoMsg(request, // inspectionPayInfo.getId(),
			 * content, mobile); } // 2018-03-29应明子涵要求，增加九寨报告已收费提醒消息推送 if
			 * (Constant.CHECK_UNIT_100091.equals(check_unit_id)) { // 获取发送内容 String content
			 * = Constant.getJzReportPayNoticeContent(inspectionPayInfo.getCompany_name(),
			 * count, report_sn); // 获取发送目标号码 String emp_id =
			 * Factory.getSysPara().getProperty("JZ_REPORT_NOTIC_EMPID"); if
			 * (StringUtil.isEmpty(emp_id)) { emp_id = Constant.JZ_REPORT_NOTIC_EMPID; }
			 * String mobile = employeesService.getMobile(emp_id); // 发送微信
			 * messageService.sendWxMsg(request, inspectionPayInfo.getId(),
			 * Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, content, mobile); //
			 * 发送短信 // messageService.sendMoMsg(request, // inspectionPayInfo.getId(),
			 * content, mobile); } }
			 */
            t[11] = System.currentTimeMillis();
            for(int i=0;i<14;i++) {
            	System.out.println("times:"+t[i]);
            }
            
            long et1 = System.currentTimeMillis();
  	        System.out.println("保存收费信息----------------------------all thread complete"+ (et1-et));
            return JsonWrapper.successWrapper(inspectionPayInfo);
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
            return JsonWrapper.failureWrapperMsg("保存失败！");
        }
    }

    // 更新发票为已使用状态
    private void updateCwInvoiceF(HttpServletRequest request, InspectionPayInfo inspectionPayInfo, String check_dep_name, boolean isAdd, boolean isBorrow) {
        try {
            CurrentSessionUser user = super.getCurrentUser();

            // 1.1 获取发票基本信息
            CwInvoiceF cwInvoiceF = cwInvoiceFManager.queryByInvoice_no(inspectionPayInfo.getInvoice_no());
            if(isAdd) {
            	// 1.2.1更新发票金额
                cwInvoiceF.setInvoiceMoney(TS_Util.ratioTransform((cwInvoiceF.getInvoiceMoney() != null ? cwInvoiceF.getInvoiceMoney().doubleValue() : 0.00)
                											+ (inspectionPayInfo.getPay_received() != null ? Double.parseDouble(inspectionPayInfo.getPay_received()) : 0.00)));
            }else {
            	// 1.2.2更新发票金额
                cwInvoiceF.setInvoiceMoney(TS_Util.ratioTransform(inspectionPayInfoService.sumMoneyByInvoiceNo(inspectionPayInfo.getInvoice_no())));
            }
            // 1.3 更新发票交易类型
            cwInvoiceF.setMoneyType(inspectionPayInfo.getPay_type());
            if (StringUtil.isEmpty(inspectionPayInfo.getCompany_name())) {
                EnterInfo enterInfo = enterService.get(inspectionPayInfo.getFk_company_id());
                cwInvoiceF.setInvoiceUnit(enterInfo.getCom_name());
            } else {
                cwInvoiceF.setInvoiceUnit(inspectionPayInfo.getCompany_name());
            }
            cwInvoiceF.setCheckoutUnit(inspectionPayInfo.getReport_com_name());    // 受检单位
            cwInvoiceF.setCheckoutDep(check_dep_name);
            cwInvoiceF.setStatus("SY");                // 发票状态（WSY：未使用 SY：使用 ZF：作废 LY：领用）
            cwInvoiceF.setInvoice_id(user.getId());    // 开票人id
            cwInvoiceF.setInvoice_name(user.getName()); // 开票人name
            cwInvoiceF.setInvoiceDate(inspectionPayInfo.getPay_date());        // 开票日期
            cwInvoiceF.setInvoiceDate1(inspectionPayInfo.getPay_date1());        // 销票日期
            cwInvoiceFManager.save(cwInvoiceF);

            String op_name = "";
            if (isBorrow) {
                op_name = isAdd ? "借票收费" : "修改借票收费";
            } else {
                op_name = isAdd ? "报告收费" : "修改收费";
            }

            logService.setLogs(cwInvoiceF.getId(), op_name,
                    op_name + "（发票号：" + inspectionPayInfo.getInvoice_no() + "）", user.getId(), user.getName(),
                    new Date(), request.getRemoteAddr());
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
        }
    }

    // 更新发票为外借取消状态
    private void updateOldCwInvoiceF(HttpServletRequest request, String old_invoice_no, double old_money,
                                     String check_dep_name) {
        try {
            CurrentSessionUser user = super.getCurrentUser();

            CwInvoiceF old_cwInvoiceF = cwInvoiceFManager.queryByInvoice_no(old_invoice_no);
            if (old_cwInvoiceF != null) {
                // 发票金额
                old_cwInvoiceF.setInvoiceMoney(
                        TS_Util.ratioTransform(old_cwInvoiceF.getInvoiceMoney().doubleValue() - old_money));
                // 发票状态（WSY：未使用 SY：使用 ZF：作废 LY：领用 SFQX：收费取消）
                // 若发票金额更新后为0则发票状态更新为未使用，若不为0则保持除此次收费修改的信息
                if(old_cwInvoiceF.getInvoiceMoney().compareTo(new BigDecimal("0"))==0) {
                	old_cwInvoiceF.setMoneyType("");	// 交易类型
                	old_cwInvoiceF.setInvoiceUnit("");	// 开票单位
                	old_cwInvoiceF.setCheckoutUnit("");	// 受检单位
                	old_cwInvoiceF.setCheckoutDep("");	// 检验部门
                	old_cwInvoiceF.setStatus("WSY");	// 发票状态（WSY：未使用 SY：使用 ZF：作废 LY：领用 SFQX：收费取消）
                	old_cwInvoiceF.setInvoice_id("");	// 开票人id
                	old_cwInvoiceF.setInvoice_name("");	// 开票人name
                	old_cwInvoiceF.setInvoiceDate(null);	// 开票日期
                }else {
                	old_cwInvoiceF.setStatus("SY");
                }
                cwInvoiceFManager.save(old_cwInvoiceF);

                logService.setLogs(old_cwInvoiceF.getId(), "修改收费", "取消使用发票号：" + old_invoice_no, user.getId(),
                        user.getName(), new Date(), request.getRemoteAddr());
            }
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
        }
    }

    // 更新发票为作废状态
    private void zfOldCwInvoiceF(HttpServletRequest request, String old_invoice_no, double old_money,
                                 String check_dep_name) {
        try {
            CurrentSessionUser user = super.getCurrentUser();

            CwInvoiceF old_cwInvoiceF = cwInvoiceFManager.queryByInvoice_no(old_invoice_no);
            if (old_cwInvoiceF != null) {
                // 发票状态（WSY：未使用 SY：使用 ZF：作废 LY：领用 SFQX：收费取消）
                old_cwInvoiceF.setStatus("ZF");
                cwInvoiceFManager.save(old_cwInvoiceF);

                logService.setLogs(old_cwInvoiceF.getId(), "修改收费，作废发票", "修改收费，作废发票：" + old_invoice_no, user.getId(),
                        user.getName(), new Date(), request.getRemoteAddr());
            }
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
        }
    }

    // 修改收费
    @RequestMapping(value = "savePayInfo2")
    @ResponseBody
    public HashMap<String, Object> savePayInfo2(HttpServletRequest request,
                                                @RequestBody InspectionPayInfo inspectionPayInfo) {
        try {
            CurrentSessionUser user = super.getCurrentUser();
            double old_pay_total = 0;
            double pay_total = Double.parseDouble(inspectionPayInfo.getPay_received()); // 本次实收总金额
            double remark = Double.parseDouble(inspectionPayInfo.getRemark()); // 本次转账总金额
            // double cur_pay = pay_total; // 当前需收金额

            // 1、验证发票号是否存在
            String invoice_no = inspectionPayInfo.getInvoice_no();
            // 1.2、原发票号
            String old_invoice_no = "";

            boolean isAdd = true;
            String status = request.getParameter("status");
            if (!"add".equals(status)) {
                isAdd = false;
            }

            double old_pay_money = 0;
            if (StringUtil.isNotEmpty(inspectionPayInfo.getId())) {
                old_invoice_no = inspectionPayInfoService.queryInvoice_no(inspectionPayInfo.getId());
                old_pay_money = inspectionPayInfoService.queryMoney(inspectionPayInfo.getId());
            }

            if (StringUtil.isNotEmpty(invoice_no)) {
                List<CwInvoiceF> list = cwInvoiceFManager.querysByInvoice_no(invoice_no);
                if (list.isEmpty()) {
                    return JsonWrapper.failureWrapperMsg("亲，系统未能识别发票号（" + invoice_no + "），请联系财务人员导入！");
                } else {
                    CwInvoiceF cwInvoiceF = list.get(0);
                    if ("SY".equals(cwInvoiceF.getStatus())) {
                    	String pay_date = DateUtil.format(inspectionPayInfo.getPay_date(), Constant.defaultDatePattern);
                        String invoice_date = DateUtil.format(cwInvoiceF.getInvoiceDate(), Constant.defaultDatePattern);
                        if (pay_date.equals(invoice_date)
                                && inspectionPayInfo.getCompany_name().equals(cwInvoiceF.getInvoiceUnit())) {
                            // 相同开票单位和相同开票日期允许重复登记收费，反之
                        } else {
                            if (isAdd) {
                                return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经使用，请核实！");
                            } else {
                                if (StringUtil.isNotEmpty(old_invoice_no)) {
                                    if (!invoice_no.equals(old_invoice_no)) {
                                        return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经使用，请核实！");
                                    }
                                }
                            }
                        }
                    }
                    if ("ZF".equals(cwInvoiceF.getStatus())) {
                        return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经作废，不能使用，请核实！");
                    }
                }
            } else {
                if (!"3".equals(inspectionPayInfo.getPay_type())) {
                    return JsonWrapper.failureWrapperMsg("亲，发票号是必填项，请填写发票号！");
                }
            }

            // 2、更新转账记录
            if (StringUtil.isNotEmpty(inspectionPayInfo.getId())) {
                old_pay_total = inspectionPayInfoService.queryMoney(inspectionPayInfo.getId());
                // 是否重新冲账（0：否 1：是）
                String bank_change = request.getParameter("bank_change");

                if ("1".equals(bank_change)) {
                    // 2、重新冲账时，删除原收费方式为“银行转账、现金及转账、转账及pos机刷卡”的银行转账记录信息表
                    List<CwBank2Pay> list = cwBank2PayManager.queryCwBank2Pays(inspectionPayInfo.getId());
                    if (!list.isEmpty()) {
                        for (CwBank2Pay cwBank2Pay : list) {
                            // 2.1、返写银行转账记录信息表
                            CwBankDetail cwBankDetail = cwBankDetailManager.get(cwBank2Pay.getFk_cw_bank_id());
                            if (cwBank2Pay != null) {
                                // 剩余金额
                                cwBankDetail
                                        .setRestMoney(TS_Util.ratioTransform((cwBankDetail.getRestMoney().doubleValue()
                                                + cwBank2Pay.getCur_used_money().doubleValue())));
                                // 已收金额
                                cwBankDetail
                                        .setUsedMoney(TS_Util.ratioTransform(cwBankDetail.getUsedMoney().doubleValue()
                                                - cwBank2Pay.getCur_used_money().doubleValue()));
                            }
                            cwBankDetailManager.save(cwBankDetail);

                            // 1.2、重新冲账时，删除收费业务与银行转账记录关联信息表
                            cwBank2PayManager.deleteBusiness(cwBank2Pay.getId());
                        }
                    } else {
                        String fk_cw_bank_ids = inspectionPayInfo.getFk_cw_bank_id();
                        if (StringUtil.isNotEmpty(fk_cw_bank_ids)) {
                            String[] bank_ids = fk_cw_bank_ids.split(",");
                            for (int j = 0; j < bank_ids.length; j++) {
                                // 1.1 获取银行转账历史记录
                                CwBank2Pay cwBank2Pay = cwBank2PayManager.queryCwBank2Pay(bank_ids[j],
                                        inspectionPayInfo.getId());

                                // 1.2、返写银行转账记录信息表
                                CwBankDetail cwBankDetail = cwBankDetailManager.get(bank_ids[j]);
                                if (cwBank2Pay != null) {
                                    // 剩余金额
                                    cwBankDetail.setRestMoney(
                                            TS_Util.ratioTransform((cwBankDetail.getRestMoney().doubleValue()
                                                    + cwBank2Pay.getCur_used_money().doubleValue())));
                                    // 已收金额
                                    cwBankDetail.setUsedMoney(
                                            TS_Util.ratioTransform(cwBankDetail.getUsedMoney().doubleValue()
                                                    - cwBank2Pay.getCur_used_money().doubleValue()));
                                }
                                cwBankDetailManager.save(cwBankDetail);

                                // 1.3、重新冲账时，删除收费业务与银行转账记录关联信息表
                                cwBank2PayManager.deleteBusiness(cwBank2Pay.getId());
                            }
                        }
                    }

                    // 2、重新冲账时，返写收费方式为“银行转账、现金及转账、转账及pos机刷卡”的银行转账记录信息表
                    String cw_bank_ids = "";
                    if (inspectionPayInfo.getCwBankDTOList() != null) {
                        List<CwBankDTO> cwBankList = inspectionPayInfo.getCwBankDTOList();
                        if (!cwBankList.isEmpty()) {
                            // 2.1、验证银行转账余额是否不足
                            String bank_ids = "";
                            for (CwBankDTO cwBankDTO : cwBankList) {
                                if (bank_ids.length() > 0) {
                                    bank_ids += ",";
                                }
                                bank_ids += cwBankDTO.getId();
                            }
                            if (StringUtil.isNotEmpty(bank_ids)) {
                                // 获取银行转账剩余金额
                                double money = cwBankDetailManager.queryBankMoney(bank_ids);
                                // 如果剩余金额不足，提示用户
                                if (money < remark) {
                                    return JsonWrapper.failureWrapperMsg("亲，您所选的银行转账余额不足，请重新选择！");
                                }
                            }

                            for (CwBankDTO cwBankDTO : cwBankList) {
                                // 2.2、保存收费业务与银行转账记录关联信息表
                                inspectionPayInfoDao.insertCwBank2Pay(StringUtil.getUUID(), cwBankDTO.getId(),
                                        inspectionPayInfo.getId(), cwBankDTO.getUsedMoney(), cwBankDTO.getPay_remark());

                                // 2.3、返写银行转账记录信息表
                                CwBankDetail cwBankDetail = cwBankDetailManager.get(cwBankDTO.getId());
                                // 剩余金额
                                cwBankDetail
                                        .setRestMoney(TS_Util.ratioTransform((cwBankDetail.getRestMoney().doubleValue()
                                                - cwBankDTO.getUsedMoney().doubleValue())));
                                // 本次已收金额
                                cwBankDetail
                                        .setUsedMoney(TS_Util.ratioTransform((cwBankDetail.getUsedMoney().doubleValue()
                                                + cwBankDTO.getUsedMoney().doubleValue())));
                                cwBankDetailManager.save(cwBankDetail);
                                if (cw_bank_ids.length() > 0) {
                                    cw_bank_ids += ",";
                                }
                                cw_bank_ids += cwBankDetail.getId();

                                logService.setLogs(cwBankDTO.getId(), "修改收费，修改银行转账",
                                        "本次剩余金额：" + TS_Util.ratioTransform(cwBankDetail.getRestMoney().doubleValue())
                                                + "，" + "本次收费金额："
                                                + TS_Util.ratioTransform(cwBankDetail.getUsedMoney().doubleValue()),
                                        user.getId(), user.getName(), new Date(), request.getRemoteAddr());
                            }
                        }
                    }
                    inspectionPayInfo.setFk_cw_bank_id(cw_bank_ids);
                    // inspectionPayInfoService.save(inspectionPayInfo);
                }
            }

            // double receivable = 0;
            // int count = inspectionPayInfo.getInspectionInfoDTOList().size();
            // receivable =
            // Double.parseDouble(inspectionPayInfo.getPay_received()) / count;
            String ids = "";
            String report_com_name = ""; // 报告书使用单位（受检单位）
            String check_department = ""; // 检验部门
            for (InspectionInfoDTO inspectionInfoDTO : inspectionPayInfo.getInspectionInfoDTOList()) {
                // 3、保存业务信息（更新收费状态、实收金额等）
                InspectionInfo inspectionInfo = inspectionInfoService.get(inspectionInfoDTO.getId());
                // 实收金额
                if (inspectionInfo.getReceivable() == null || inspectionInfo.getReceivable() == 0) {
                    inspectionInfo.setReceivable(inspectionInfo.getAdvance_fees()); // 实收金额
                }
                // 缴费方式 1 现金缴费 2 转账 3 免收费 4 现金及转账 5 pos 6 现金及pos 7 转账及pos
                inspectionInfo.setFee_type(inspectionPayInfo.getPay_type());
                inspectionInfo.setInvoice_no(inspectionPayInfo.getInvoice_no()); // 发票号
                if ("3".equals(inspectionPayInfo.getPay_type())) {
                    inspectionInfo.setAdvance_type("2"); // 收费类型 0 正常收费 1 协议收费 2
                    // 免收费
                } else {
                    inspectionInfo.setAdvance_type("0");
                }
                // 收费状态（0 默认 1 待收费 2 已收费 3 借报告 4 借发票 5 借报告和发票）
                inspectionInfo.setFee_status("2");
                inspectionInfo.setInvoice_date(inspectionPayInfo.getPay_date()); // 缴费日期

                if (StringUtil.isEmpty(check_department)) {
                    if (StringUtil.isNotEmpty(inspectionInfo.getCheck_unit_id())) {
                        Org org = orgManager.get(inspectionInfo.getCheck_unit_id());
                        check_department = StringUtil.isNotEmpty(org.getOrgName()) ? org.getOrgName() : ""; // 检验部门
                    }
                }
                inspectionInfoService.save(inspectionInfo);

                // 4、查询是否有外借情况
                ReportBorrow reportBorrow = reportBorrowService.queryByInspectionInfoID(inspectionInfo.getId());
                if (reportBorrow != null) {
                    // 如果存在外借情况，重新收费后，重新更新外借业务和外借欠款金额
                    String fk_inspection_info_id = reportBorrow.getFk_inspection_info_id();
                    fk_inspection_info_id = fk_inspection_info_id.replace(inspectionInfo.getId(), "");
                    double unpay = Double.parseDouble(reportBorrow.getUnpay_amount());
                    unpay = unpay - inspectionInfo.getAdvance_fees();
                    reportBorrow.setFk_inspection_info_id(fk_inspection_info_id); // 更新外借业务id
                    reportBorrow.setUnpay_amount(unpay + ""); // 更新外借欠款金额
                    boolean isCandel = true; // 是否是全部取消外借重新收费
                    String[] idArr = fk_inspection_info_id.split(",");
                    for (int i = 0; i < idArr.length; i++) {
                        if (StringUtil.isNotEmpty(idArr[i])) {
                            isCandel = false;
                        }
                    }
                    if (isCandel) {
                        reportBorrow.setBorrow_status("3"); // 3：已取消外借
                    }
                    if (unpay == 0) {
                        reportBorrow.setBorrow_status("3"); // 3：已取消外借
                    }
                    reportBorrowService.save(reportBorrow);
                    logService.setLogs(inspectionInfo.getId(), "【外借业务】修改收费", "【外借业务】修改收费", user.getId(), user.getName(),
                            new Date(), request.getRemoteAddr());
                }

                if (ids.length() > 0) {
                    ids += ",";
                }
                ids += inspectionInfo.getId();
                if (StringUtil.isEmpty(report_com_name)) {
                    report_com_name = inspectionInfo.getReport_com_name();
                }
                /*
                 * logService.setLogs(inspectionInfo.getId(), "报告收费", "报告收费",
                 * user.getId(), user.getName(), new Date(),
                 * request.getRemoteAddr());
                 */
            }
            // 5、保存缴费信息
            if (StringUtil.isEmpty(inspectionPayInfo.getPay_no())) {
                String curYearMonth = TS_Util.getCurYearMonth(); // 获取当前年月
                String no = inspectionPayInfoService.queryNextPay_no(curYearMonth); // 获取编号后四位序号
                // 编号 = 当前年月 + 4位序号
                inspectionPayInfo.setPay_no(curYearMonth + no);
            }
            inspectionPayInfo.setPayment_status("2"); // 缴费状态 0 默认 1 待收费 2 已收费
            inspectionPayInfo.setCreated_date(new Date()); // 操作日期
            inspectionPayInfo.setCreated_by(user.getName()); // 操作人
            if (StringUtil.isEmpty(inspectionPayInfo.getReceive_man_name())) {
                inspectionPayInfo.setReceive_man_name(user.getName());
            }
            inspectionPayInfoService.save(inspectionPayInfo);

            // 6、保存缴费详细信息
            InspectionPayDetail inspectionPayDetail = new InspectionPayDetail();
            BeanUtils.copyProperties(inspectionPayDetail, inspectionPayInfo);
            inspectionPayDetail.setId(null);
            inspectionPayDetail.setFk_pay_info_id(inspectionPayInfo.getId());
            inspectionPayDetail.setReceive_date(inspectionPayInfo.getPay_date());
            inspectionPayDetailService.save(inspectionPayDetail);

            // 7、写日志
            logService.setLogs(inspectionPayInfo.getId(), "修改收费", "修改收费，使用发票（发票号：" + invoice_no + "）", user.getId(),
                    user.getName(), new Date(), request.getRemoteAddr());

            // 8、返写发票信息
            if (StringUtil.isNotEmpty(invoice_no)) {
                if (StringUtil.isNotEmpty(report_com_name)) {
                    inspectionPayInfo.setReport_com_name(report_com_name);
                }
                if (StringUtil.isEmpty(old_invoice_no)) {
                    // 5.1 返写更新本次使用的新发票号信息
                    updateCwInvoiceF(request, inspectionPayInfo, check_department, isAdd, false);
                } else {
                    if (!invoice_no.equals(old_invoice_no)) {
                        // 5.2 返写更新原发票号信息
                        // 原票号是否作废（0：否 1：是）
                        String if_del_invoice = request.getParameter("if_del_invoice");
                        if ("1".equals(if_del_invoice)) {
                            zfOldCwInvoiceF(request, old_invoice_no, old_pay_money, check_department);
                        } else {
                            updateOldCwInvoiceF(request, old_invoice_no, old_pay_money, check_department);
                        }
                    }
                    updateCwInvoiceF(request, inspectionPayInfo, check_department, isAdd, false);
                }
            }

            // 9、记录日志
            String[] idArr = ids.split(",");
            for (int i = 0; i < idArr.length; i++) {
                logService.setLogs(idArr[i], "修改收费", "修改收费", user.getId(), user.getName(), new Date(),
                        request.getRemoteAddr());
            }
            return JsonWrapper.successWrapper(inspectionPayInfo);
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
            return JsonWrapper.failureWrapperMsg("保存失败！");
        }
    }

    // 机电类外借收费（外借发票、外借报告和发票）保存
    @RequestMapping(value = "savePayBorrow")
    @ResponseBody
    public HashMap<String, Object> savePayBorrow(HttpServletRequest request,
                                                 @RequestBody InspectionPayInfo inspectionPayInfo) {
        try {
            CurrentSessionUser user = super.getCurrentUser();

            String borrow_id = request.getParameter("borrow_id");
            ReportBorrow reportBorrow = reportBorrowService.get(borrow_id);


            // 1、验证发票号是否存在
            String invoice_no = inspectionPayInfo.getInvoice_no();
            // 1.2、原发票号
            String old_invoice_no = "";

            boolean isAdd = true;
            String status = request.getParameter("status");
            if (!"add".equals(status)) {
                isAdd = false;
            }

            double old_pay_money = 0;
            if (StringUtil.isNotEmpty(inspectionPayInfo.getId())) {
                old_invoice_no = inspectionPayInfoService.queryInvoice_no(inspectionPayInfo.getId());
                old_pay_money = inspectionPayInfoService.queryMoney(inspectionPayInfo.getId());
            }

            if (StringUtil.isNotEmpty(invoice_no)) {
                List<CwInvoiceF> list = cwInvoiceFManager.querysByInvoice_no(invoice_no);
                if (list.isEmpty()) {
                    return JsonWrapper.failureWrapperMsg("亲，系统未能识别发票号（" + invoice_no + "），请联系财务人员导入！");
                } else {
                    CwInvoiceF cwInvoiceF = list.get(0);
                    if ("SY".equals(cwInvoiceF.getStatus())) {
                        // 外借类型，数据字典：TZSB_BORROW_TYPE（0：外借报告，1：外借发票，2：外借报告和发票）
                        if ("0".equals(reportBorrow.getBorrow_type())) {
                            String pay_date = DateUtil.format(inspectionPayInfo.getPay_date(), Constant.defaultDatePattern);
                            String invoice_date = DateUtil.format(cwInvoiceF.getInvoiceDate(), Constant.defaultDatePattern);

                            if (pay_date.equals(invoice_date)
                                    && inspectionPayInfo.getCompany_name().equals(cwInvoiceF.getInvoiceUnit())) {
                                // 相同开票单位和相同开票日期允许重复登记收费，反之
                            } else {
                                if (isAdd) {
                                    return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经使用，请核实！");
                                } else {
                                    if (StringUtil.isNotEmpty(old_invoice_no)) {
                                        if (!invoice_no.equals(old_invoice_no)) {
                                            return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经使用，请核实！");
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if ("ZF".equals(cwInvoiceF.getStatus())) {
                        return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经作废，不能使用，请核实！");
                    }
                }
            } else {
                if (!"3".equals(inspectionPayInfo.getPay_type())) {
                    return JsonWrapper.failureWrapperMsg("亲，发票号是必填项，请填写发票号！");
                }
            }

            // 2、当收费方式为“银行转账、现金及转账、转账及pos机刷卡”时，验证转账剩余金额是否充足
            double remark = Double.parseDouble(inspectionPayInfo.getRemark()); // 本次转账总金额
            if (isZhuanZhang(inspectionPayInfo.getPay_type())) {
                if (inspectionPayInfo.getCwBankDTOList() != null) {
                    List<CwBankDTO> cwBankList = inspectionPayInfo.getCwBankDTOList();
                    if (!cwBankList.isEmpty()) {
                        // 4.1、验证银行转账余额是否不足
                        String bank_ids = "";
                        for (CwBankDTO cwBankDTO : cwBankList) {
                            if (bank_ids.length() > 0) {
                                bank_ids += ",";
                            }
                            bank_ids += cwBankDTO.getId();
                        }
                        if (StringUtil.isNotEmpty(bank_ids)) {
                            // 获取银行转账剩余金额
                            double money = cwBankDetailManager.queryBankMoney(bank_ids);
                            // 如果剩余金额不足，提示用户
                            if (money < remark) {
                                return JsonWrapper.failureWrapperMsg("亲，您所选的银行转账余额不足，请重新选择！");
                            }
                        }
                    }
                }
            }

            // 3、保存业务信息（更新收费状态、实收金额等）
            int count = inspectionPayInfo.getInspectionInfoDTOList().size();
            String ids = "";
            String report_com_name = ""; // 报告书使用单位（受检单位）
            String report_sn = ""; // 报告书编号（用于西藏报告提醒消息推送）
            String check_unit_id = "";
            String check_department = ""; // 检验部门
            for (InspectionInfoDTO inspectionInfoDTO : inspectionPayInfo.getInspectionInfoDTOList()) {
                InspectionInfo inspectionInfo = inspectionInfoService.get(inspectionInfoDTO.getId());
                if (StringUtil.isEmpty(report_sn)) {
                    report_sn = inspectionInfo.getReport_sn();
                }
                // 实收金额
                if (inspectionInfo.getReceivable() == null || inspectionInfo.getReceivable() == 0) {
                    inspectionInfo.setReceivable(inspectionInfo.getAdvance_fees()); // 实收金额
                }
                // 缴费方式 1 现金缴费 2 转账 3 免收费 4 现金及转账 5 pos 6 现金及pos 7 转账及pos
                inspectionInfo.setFee_type(inspectionPayInfo.getPay_type());
                inspectionInfo.setInvoice_no(inspectionPayInfo.getInvoice_no()); // 发票号
                if ("3".equals(inspectionPayInfo.getPay_type())) {
                    inspectionInfo.setAdvance_type("2"); // 收费类型 0 正常收费 1 协议收费 2
                    // 免收费
                } else {
                    inspectionInfo.setAdvance_type("0");
                }
                // 收费状态（0 默认 1 待收费 2 已收费 3 借报告 4 借发票 5 借报告和发票）
                inspectionInfo.setFee_status("2");
                inspectionInfo.setInvoice_date(inspectionPayInfo.getPay_date()); // 缴费日期

                if (StringUtil.isEmpty(check_unit_id)) {
                    check_unit_id = inspectionInfo.getCheck_unit_id();
                }
                if (StringUtil.isEmpty(check_department)) {
                    if (StringUtil.isNotEmpty(inspectionInfo.getCheck_unit_id())) {
                        Org org = orgManager.get(inspectionInfo.getCheck_unit_id());
                        check_department = StringUtil.isNotEmpty(org.getOrgName()) ? org.getOrgName() : ""; // 检验部门
                    }
                }

                inspectionInfoService.save(inspectionInfo);

                // 4、查询是否有外借情况
                if (reportBorrow != null) {
                    // 如果存在外借情况，重新收费后，重新更新外借业务和外借欠款金额
                    String fk_inspection_info_id = reportBorrow.getFk_inspection_info_id();
                    fk_inspection_info_id = fk_inspection_info_id.replace(inspectionInfo.getId(), "");
                    double unpay = Double.parseDouble(reportBorrow.getUnpay_amount());
                    unpay = unpay - inspectionInfo.getAdvance_fees();
                    reportBorrow.setFk_inspection_info_id(fk_inspection_info_id); // 更新外借业务id
                    reportBorrow.setUnpay_amount(unpay + ""); // 更新外借欠款金额
                    boolean isCandel = true; // 是否是全部取消外借重新收费
                    String[] idArr = fk_inspection_info_id.split(",");
                    for (int i = 0; i < idArr.length; i++) {
                        if (StringUtil.isNotEmpty(idArr[i])) {
                            isCandel = false;
                        }
                    }
                    if (isCandel) {
                        reportBorrow.setBorrow_status("3"); // 3：已取消外借
                    }
                    if (unpay == 0) {
                        reportBorrow.setBorrow_status("3"); // 3：已取消外借
                    }
                    reportBorrowService.save(reportBorrow);
                    logService.setLogs(inspectionInfo.getId(), "【外借业务】报告收费", "【外借业务】报告收费", user.getId(), user.getName(),
                            new Date(), request.getRemoteAddr());
                }

                if (ids.length() > 0) {
                    ids += ",";
                }
                ids += inspectionInfo.getId();
                if (StringUtil.isEmpty(report_com_name)) {
                    report_com_name = inspectionInfo.getReport_com_name();
                }
            }

            // 5、保存缴费信息
            if (StringUtil.isEmpty(inspectionPayInfo.getPay_no())) {
                String curYearMonth = TS_Util.getCurYearMonth(); // 获取当前年月
                String no = inspectionPayInfoService.queryNextPay_no(curYearMonth); // 获取编号后四位序号
                // 编号 = 当前年月 + 4位序号
                inspectionPayInfo.setPay_no(curYearMonth + no);
            }
            inspectionPayInfo.setPayment_status("2"); // 缴费状态 0 默认 1 待收费 2 已收费
            inspectionPayInfo.setCreated_date(new Date()); // 操作日期
            inspectionPayInfo.setCreated_by(user.getName()); // 操作人
            if (StringUtil.isEmpty(inspectionPayInfo.getReceive_man_name())) {
                inspectionPayInfo.setReceive_man_name(user.getName());
            }
            inspectionPayInfoService.save(inspectionPayInfo);

            // 6、保存缴费详细信息
            InspectionPayDetail inspectionPayDetail = new InspectionPayDetail();
            BeanUtils.copyProperties(inspectionPayDetail, inspectionPayInfo);
            inspectionPayDetail.setId(null);
            inspectionPayDetail.setFk_pay_info_id(inspectionPayInfo.getId());
            inspectionPayDetail.setReceive_date(inspectionPayInfo.getPay_date());
            inspectionPayDetailService.save(inspectionPayDetail);

            // 7、当收费方式为“银行转账、现金及转账、转账及pos机刷卡”时，返写银行转账记录信息表
            String cw_bank_ids = "";
            if (isZhuanZhang(inspectionPayInfo.getPay_type())) {
                if (inspectionPayInfo.getCwBankDTOList() != null) {
                    List<CwBankDTO> cwBankList = inspectionPayInfo.getCwBankDTOList();
                    if (!cwBankList.isEmpty()) {
                        for (CwBankDTO cwBankDTO : cwBankList) {
                            // 4.2、保存收费业务与银行转账记录关联信息表
                            inspectionPayInfoDao.insertCwBank2Pay(StringUtil.getUUID(), cwBankDTO.getId(),
                                    inspectionPayInfo.getId(), cwBankDTO.getUsedMoney(), cwBankDTO.getPay_remark());

                            // 4.3、返写银行转账记录信息表
                            CwBankDetail cwBankDetail = cwBankDetailManager.get(cwBankDTO.getId());
                            // 剩余金额
                            cwBankDetail.setRestMoney(TS_Util.ratioTransform((cwBankDetail.getRestMoney().doubleValue()
                                    - cwBankDTO.getUsedMoney().doubleValue())));
                            // 本次已收金额
                            cwBankDetail.setUsedMoney(TS_Util.ratioTransform((cwBankDetail.getUsedMoney().doubleValue()
                                    + cwBankDTO.getUsedMoney().doubleValue())));
                            cwBankDetailManager.save(cwBankDetail);
                            if (cw_bank_ids.length() > 0) {
                                cw_bank_ids += ",";
                            }
                            cw_bank_ids += cwBankDetail.getId();

                            logService.setLogs(cwBankDTO.getId(), "报告收费，使用银行转账",
                                    "本次剩余金额：" + TS_Util.ratioTransform(cwBankDetail.getRestMoney().doubleValue()) + "，"
                                            + "本次收费金额："
                                            + TS_Util.ratioTransform(cwBankDTO.getUsedMoney().doubleValue()),
                                    user.getId(), user.getName(), new Date(), request.getRemoteAddr());
                        }
                    }

                }
            }
            inspectionPayInfo.setFk_cw_bank_id(cw_bank_ids);
            inspectionPayInfoService.save(inspectionPayInfo);

            // 8、返写发票信息
            // 外借类型，数据字典：TZSB_BORROW_TYPE（0：外借报告，1：外借发票，2：外借报告和发票）
            // 涉及到外借过的发票，进行收费时，因发票已开，不再更新发票信息
            if ("0".equals(reportBorrow.getBorrow_type())) {
                if (StringUtil.isNotEmpty(invoice_no) && !"3".equals(inspectionPayInfo.getPay_type())) {
                    if (StringUtil.isNotEmpty(report_com_name)) {
                        inspectionPayInfo.setReport_com_name(report_com_name);
                    }
                    if (StringUtil.isEmpty(old_invoice_no)) {
                        // 5.1 返写更新本次使用的新发票号信息
                        updateCwInvoiceF(request, inspectionPayInfo, check_department, isAdd, true);
                    } else {
                        if (!invoice_no.equals(old_invoice_no)) {
                            // 5.2 返写更新原发票号信息
                            // 原票号是否作废（0：否 1：是）
                            String if_del_invoice = request.getParameter("if_del_invoice");
                            if ("1".equals(if_del_invoice)) {
                                zfOldCwInvoiceF(request, old_invoice_no, old_pay_money, check_department);
                            } else {
                                updateOldCwInvoiceF(request, old_invoice_no, old_pay_money, check_department);
                            }
                        }
                        updateCwInvoiceF(request, inspectionPayInfo, check_department, isAdd, true);
                    }
                }
            }


            // 9、记录日志
            String[] idArr = ids.split(",");
            for (int i = 0; i < idArr.length; i++) {
                logService.setLogs(idArr[i], "报告收费", "报告收费", user.getId(), user.getName(), new Date(),
                        request.getRemoteAddr());
            }

            // 10、西藏和新疆报告提醒消息推送
            if (StringUtil.isNotEmpty(check_unit_id)) {
                if (Constant.CHECK_UNIT_100069.equals(check_unit_id)) {
                    // 获取发送内容
                    String content = Constant.getXzReportPayNoticeContent(inspectionPayInfo.getCompany_name(), count,
                            report_sn);
                    // 获取发送目标号码
                    String mobile = employeesService.getMobile(Constant.XZ_REPORT_NOTIC_EMPID);
                    // 发送微信
                    messageService.sendWxMsg(request, inspectionPayInfo.getId(), Constant.INSPECTION_CORPID,
                            Constant.INSPECTION_PWD, content, mobile);
                    // 发送短信
                    // messageService.sendMoMsg(request,
                    // inspectionPayInfo.getId(), content, mobile);
                }
                // 2017-12-12应明子涵要求，增加新疆报告已收费提醒消息推送
                if (Constant.CHECK_UNIT_100090.equals(check_unit_id)) {
                    // 获取发送内容
                    String content = Constant.getXjReportPayNoticeContent(inspectionPayInfo.getCompany_name(), count,
                            report_sn);
                    // 获取发送目标号码
                    String emp_id = Factory.getSysPara().getProperty("XJ_REPORT_NOTIC_EMPID");
                    if (StringUtil.isEmpty(emp_id)) {
                        emp_id = Constant.XJ_REPORT_NOTIC_EMPID;
                    }
                    String mobile = employeesService.getMobile(emp_id);
                    // 发送微信
                    messageService.sendWxMsg(request, inspectionPayInfo.getId(), Constant.INSPECTION_CORPID,
                            Constant.INSPECTION_PWD, content, mobile);
                    // 发送短信
                    // messageService.sendMoMsg(request,
                    // inspectionPayInfo.getId(), content, mobile);
                }
                // 2018-03-29应明子涵要求，增加九寨报告已收费提醒消息推送
                if (Constant.CHECK_UNIT_100091.equals(check_unit_id)) {
                    // 获取发送内容
                    String content = Constant.getJzReportPayNoticeContent(inspectionPayInfo.getCompany_name(), count,
                            report_sn);
                    // 获取发送目标号码
                    String emp_id = Factory.getSysPara().getProperty("JZ_REPORT_NOTIC_EMPID");
                    if (StringUtil.isEmpty(emp_id)) {
                        emp_id = Constant.JZ_REPORT_NOTIC_EMPID;
                    }
                    String mobile = employeesService.getMobile(emp_id);
                    // 发送微信
                    messageService.sendWxMsg(request, inspectionPayInfo.getId(), Constant.INSPECTION_CORPID,
                            Constant.INSPECTION_PWD, content, mobile);
                    // 发送短信
                    // messageService.sendMoMsg(request,
                    // inspectionPayInfo.getId(), content, mobile);
                }
            }
            return JsonWrapper.successWrapper(inspectionPayInfo);
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
            return JsonWrapper.failureWrapperMsg("保存失败！");
        }
    }

    // 机电类外借收费（外借报告）保存
    @RequestMapping(value = "savePayBorrow2")
    @ResponseBody
    public HashMap<String, Object> savePayBorrow2(HttpServletRequest request,
                                                  @RequestBody InspectionPayInfo inspectionPayInfo) {
        try {
            CurrentSessionUser user = super.getCurrentUser();

            // 1、验证发票号是否存在
            String invoice_no = inspectionPayInfo.getInvoice_no();
            // 1.2、原发票号
            String old_invoice_no = "";

            boolean isAdd = true;
            String status = request.getParameter("status");
            if (!"add".equals(status)) {
                isAdd = false;
            }

            double old_pay_money = 0;
            if (StringUtil.isNotEmpty(inspectionPayInfo.getId())) {
                old_invoice_no = inspectionPayInfoService.queryInvoice_no(inspectionPayInfo.getId());
                old_pay_money = inspectionPayInfoService.queryMoney(inspectionPayInfo.getId());
            }

            if (StringUtil.isNotEmpty(invoice_no)) {
                List<CwInvoiceF> list = cwInvoiceFManager.querysByInvoice_no(invoice_no);
                if (list.isEmpty()) {
                    return JsonWrapper.failureWrapperMsg("亲，系统未能识别发票号（" + invoice_no + "），请联系财务人员导入！");
                } else {
                    CwInvoiceF cwInvoiceF = list.get(0);
                    if ("SY".equals(cwInvoiceF.getStatus())) {
                        // 此处为外借报告
                        String pay_date = DateUtil.format(inspectionPayInfo.getPay_date(),
                                Constant.defaultDatePattern);
                        String invoice_date = DateUtil.format(cwInvoiceF.getInvoiceDate(),
                                Constant.defaultDatePattern);

                        if (pay_date.equals(invoice_date)
                                && inspectionPayInfo.getCompany_name().equals(cwInvoiceF.getInvoiceUnit())) {
                            // 相同开票单位和相同开票日期允许重复登记收费，反之
                        } else {
                            if (isAdd) {
                                return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经使用，请核实！");
                            } else {
                                if (StringUtil.isNotEmpty(old_invoice_no)) {
                                    if (!invoice_no.equals(old_invoice_no)) {
                                        return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经使用，请核实！");
                                    }
                                }
                            }
                        }
                    }
                    if ("ZF".equals(cwInvoiceF.getStatus())) {
                        return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经作废，不能使用，请核实！");
                    }
                }
            } else {
                if (!"3".equals(inspectionPayInfo.getPay_type())) {
                    return JsonWrapper.failureWrapperMsg("亲，发票号是必填项，请填写发票号！");
                }
            }

            // 2、当收费方式为“银行转账、现金及转账、转账及pos机刷卡”时，验证转账剩余金额是否充足
            double remark = Double.parseDouble(inspectionPayInfo.getRemark()); // 本次转账总金额
            if (isZhuanZhang(inspectionPayInfo.getPay_type())) {
                if (inspectionPayInfo.getCwBankDTOList() != null) {
                    List<CwBankDTO> cwBankList = inspectionPayInfo.getCwBankDTOList();
                    if (!cwBankList.isEmpty()) {
                        // 4.1、验证银行转账余额是否不足
                        String bank_ids = "";
                        for (CwBankDTO cwBankDTO : cwBankList) {
                            if (bank_ids.length() > 0) {
                                bank_ids += ",";
                            }
                            bank_ids += cwBankDTO.getId();
                        }
                        if (StringUtil.isNotEmpty(bank_ids)) {
                            // 获取银行转账剩余金额
                            double money = cwBankDetailManager.queryBankMoney(bank_ids);
                            // 如果剩余金额不足，提示用户
                            if (money < remark) {
                                return JsonWrapper.failureWrapperMsg("亲，您所选的银行转账余额不足，请重新选择！");
                            }
                        }
                    }
                }
            }

            // 3、保存业务信息（更新收费状态、实收金额等）
            int count = inspectionPayInfo.getInspectionInfoDTOList().size();
            String ids = "";
            String report_com_name = ""; // 报告书使用单位（受检单位）
            String report_sn = ""; // 报告书编号（用于西藏报告提醒消息推送）
            String check_unit_id = "";
            String check_department = ""; // 检验部门
            for (InspectionInfoDTO inspectionInfoDTO : inspectionPayInfo.getInspectionInfoDTOList()) {
                InspectionInfo inspectionInfo = inspectionInfoService.get(inspectionInfoDTO.getId());
                if (StringUtil.isEmpty(report_sn)) {
                    report_sn = inspectionInfo.getReport_sn();
                }
                // 实收金额
                if (inspectionInfo.getReceivable() == null || inspectionInfo.getReceivable() == 0) {
                    inspectionInfo.setReceivable(inspectionInfo.getAdvance_fees()); // 实收金额
                }
                // 缴费方式 1 现金缴费 2 转账 3 免收费 4 现金及转账 5 pos 6 现金及pos 7 转账及pos
                inspectionInfo.setFee_type(inspectionPayInfo.getPay_type());
                inspectionInfo.setInvoice_no(inspectionPayInfo.getInvoice_no()); // 发票号
                if ("3".equals(inspectionPayInfo.getPay_type())) {
                    inspectionInfo.setAdvance_type("2"); // 收费类型 0 正常收费 1 协议收费 2
                    // 免收费
                } else {
                    inspectionInfo.setAdvance_type("0");
                }
                // 收费状态（0 默认 1 待收费 2 已收费 3 借报告 4 借发票 5 借报告和发票）
                inspectionInfo.setFee_status("2");
                inspectionInfo.setInvoice_date(inspectionPayInfo.getPay_date()); // 缴费日期

                if (StringUtil.isEmpty(check_unit_id)) {
                    check_unit_id = inspectionInfo.getCheck_unit_id();
                }
                if (StringUtil.isEmpty(check_department)) {
                    if (StringUtil.isNotEmpty(inspectionInfo.getCheck_unit_id())) {
                        Org org = orgManager.get(inspectionInfo.getCheck_unit_id());
                        check_department = StringUtil.isNotEmpty(org.getOrgName()) ? org.getOrgName() : ""; // 检验部门
                    }
                }

                inspectionInfoService.save(inspectionInfo);


                // 4、查询是否有外借情况
                String borrow_id = request.getParameter("borrow_id");
                String[] borrow_ids = borrow_id.split(",");
                for (int j = 0; j < borrow_ids.length; j++) {
                    ReportBorrow reportBorrow = reportBorrowService.get(borrow_ids[j]);
                    if (reportBorrow != null) {
                        // 如果存在外借情况，重新收费后，重新更新外借业务和外借欠款金额
                        // 2017-12-05修改外借报告收费不更新原始外借金额和报告业务信息
                        //String fk_inspection_info_id = reportBorrow.getFk_inspection_info_id();
                        /*fk_inspection_info_id = fk_inspection_info_id.replace(inspectionInfo.getId(), "");
                        double unpay = Double.parseDouble(reportBorrow.getUnpay_amount());
						unpay = unpay - inspectionInfo.getAdvance_fees();
						reportBorrow.setFk_inspection_info_id(fk_inspection_info_id); // 更新外借业务id
						reportBorrow.setUnpay_amount(unpay + ""); // 更新外借欠款金额
						boolean isCandel = true; // 是否是全部取消外借重新收费
						String[] idArr = fk_inspection_info_id.split(",");
						for (int i = 0; i < idArr.length; i++) {
							if (StringUtil.isNotEmpty(idArr[i])) {
								isCandel = false;
							}
						}
						if (isCandel) {
							reportBorrow.setBorrow_status("3"); // 3：已取消外借
						}
						if (unpay == 0) {
							reportBorrow.setBorrow_status("3"); // 3：已取消外借
						}*/
                        String op_desc = "【外借业务】报告收费";
                        if (StringUtil.isNotEmpty(inspectionPayInfo.getPay_desc())) {
                            reportBorrow.setRemark(inspectionPayInfo.getPay_desc());
                            op_desc += "，" + inspectionPayInfo.getPay_desc();
                        }
                        reportBorrowService.save(reportBorrow);
                        logService.setLogs(reportBorrow.getId(), "【外借业务】报告收费", op_desc, user.getId(), user.getName(),
                                new Date(), request.getRemoteAddr());
                    }
                }

                if (ids.length() > 0) {
                    ids += ",";
                }
                ids += inspectionInfo.getId();
                if (StringUtil.isEmpty(report_com_name)) {
                    report_com_name = inspectionInfo.getReport_com_name();
                }
            }

            // 5、保存缴费信息
            if (StringUtil.isEmpty(inspectionPayInfo.getPay_no())) {
                String curYearMonth = TS_Util.getCurYearMonth(); // 获取当前年月
                String no = inspectionPayInfoService.queryNextPay_no(curYearMonth); // 获取编号后四位序号
                // 编号 = 当前年月 + 4位序号
                inspectionPayInfo.setPay_no(curYearMonth + no);
            }
            inspectionPayInfo.setPayment_status("2"); // 缴费状态 0 默认 1 待收费 2 已收费
            inspectionPayInfo.setCreated_date(new Date()); // 操作日期
            inspectionPayInfo.setCreated_by(user.getName()); // 操作人
            if (StringUtil.isEmpty(inspectionPayInfo.getReceive_man_name())) {
                inspectionPayInfo.setReceive_man_name(user.getName());
            }
            inspectionPayInfoService.save(inspectionPayInfo);

            // 6、保存缴费详细信息
            InspectionPayDetail inspectionPayDetail = new InspectionPayDetail();
            BeanUtils.copyProperties(inspectionPayDetail, inspectionPayInfo);
            inspectionPayDetail.setId(null);
            inspectionPayDetail.setFk_pay_info_id(inspectionPayInfo.getId());
            inspectionPayDetail.setReceive_date(inspectionPayInfo.getPay_date());
            inspectionPayDetailService.save(inspectionPayDetail);

            // 7、当收费方式为“银行转账、现金及转账、转账及pos机刷卡”时，返写银行转账记录信息表
            String cw_bank_ids = "";
            if (isZhuanZhang(inspectionPayInfo.getPay_type())) {
                if (inspectionPayInfo.getCwBankDTOList() != null) {
                    List<CwBankDTO> cwBankList = inspectionPayInfo.getCwBankDTOList();
                    if (!cwBankList.isEmpty()) {
                        for (CwBankDTO cwBankDTO : cwBankList) {
                            // 4.2、保存收费业务与银行转账记录关联信息表
                            inspectionPayInfoDao.insertCwBank2Pay(StringUtil.getUUID(), cwBankDTO.getId(),
                                    inspectionPayInfo.getId(), cwBankDTO.getUsedMoney(), cwBankDTO.getPay_remark());

                            // 4.3、返写银行转账记录信息表
                            CwBankDetail cwBankDetail = cwBankDetailManager.get(cwBankDTO.getId());
                            // 剩余金额
                            cwBankDetail.setRestMoney(TS_Util.ratioTransform((cwBankDetail.getRestMoney().doubleValue()
                                    - cwBankDTO.getUsedMoney().doubleValue())));
                            // 本次已收金额
                            cwBankDetail.setUsedMoney(TS_Util.ratioTransform((cwBankDetail.getUsedMoney().doubleValue()
                                    + cwBankDTO.getUsedMoney().doubleValue())));
                            cwBankDetailManager.save(cwBankDetail);
                            if (cw_bank_ids.length() > 0) {
                                cw_bank_ids += ",";
                            }
                            cw_bank_ids += cwBankDetail.getId();

                            logService.setLogs(cwBankDTO.getId(), "报告收费，使用银行转账",
                                    "本次剩余金额：" + TS_Util.ratioTransform(cwBankDetail.getRestMoney().doubleValue()) + "，"
                                            + "本次收费金额："
                                            + TS_Util.ratioTransform(cwBankDTO.getUsedMoney().doubleValue()),
                                    user.getId(), user.getName(), new Date(), request.getRemoteAddr());
                        }
                    }

                }
            }
            inspectionPayInfo.setFk_cw_bank_id(cw_bank_ids);
            inspectionPayInfoService.save(inspectionPayInfo);

            // 8、返写发票信息
            // 外借类型，数据字典：TZSB_BORROW_TYPE（0：外借报告，1：外借发票，2：外借报告和发票）
            // 涉及到外借过的发票，进行收费时，因发票已开，不再更新发票信息
            // 此处为外借报告
            if (StringUtil.isNotEmpty(invoice_no) && !"3".equals(inspectionPayInfo.getPay_type())) {
                if (StringUtil.isNotEmpty(report_com_name)) {
                    inspectionPayInfo.setReport_com_name(report_com_name);
                }
                if (StringUtil.isEmpty(old_invoice_no)) {
                    // 5.1 返写更新本次使用的新发票号信息
                    updateCwInvoiceF(request, inspectionPayInfo, check_department, isAdd, true);
                } else {
                    if (!invoice_no.equals(old_invoice_no)) {
                        // 5.2 返写更新原发票号信息
                        // 原票号是否作废（0：否 1：是）
                        String if_del_invoice = request.getParameter("if_del_invoice");
                        if ("1".equals(if_del_invoice)) {
                            zfOldCwInvoiceF(request, old_invoice_no, old_pay_money, check_department);
                        } else {
                            updateOldCwInvoiceF(request, old_invoice_no, old_pay_money, check_department);
                        }
                    }
                    updateCwInvoiceF(request, inspectionPayInfo, check_department, isAdd, true);
                }
            }

            // 9、记录日志
            String[] idArr = ids.split(",");
            for (int i = 0; i < idArr.length; i++) {
                String op_desc = "【外借业务】报告收费";
                if (StringUtil.isNotEmpty(inspectionPayInfo.getPay_desc())) {
                    op_desc += "，" + inspectionPayInfo.getPay_desc();
                }
                logService.setLogs(idArr[i], "报告收费", op_desc, user.getId(), user.getName(), new Date(),
                        request.getRemoteAddr());
            }

            // 10、西藏和新疆报告提醒消息推送
            if (StringUtil.isNotEmpty(check_unit_id)) {
                if (Constant.CHECK_UNIT_100069.equals(check_unit_id)) {
                    // 获取发送内容
                    String content = Constant.getXzReportPayNoticeContent(inspectionPayInfo.getCompany_name(), count,
                            report_sn);
                    // 获取发送目标号码
                    String mobile = employeesService.getMobile(Constant.XZ_REPORT_NOTIC_EMPID);
                    // 发送微信
                    messageService.sendWxMsg(request, inspectionPayInfo.getId(), Constant.INSPECTION_CORPID,
                            Constant.INSPECTION_PWD, content, mobile);
                    // 发送短信
                    // messageService.sendMoMsg(request,
                    // inspectionPayInfo.getId(), content, mobile);
                }
                // 2017-12-12应明子涵要求，增加新疆报告已收费提醒消息推送
                if (Constant.CHECK_UNIT_100090.equals(check_unit_id)) {
                    // 获取发送内容
                    String content = Constant.getXjReportPayNoticeContent(inspectionPayInfo.getCompany_name(), count,
                            report_sn);
                    // 获取发送目标号码
                    String emp_id = Factory.getSysPara().getProperty("XJ_REPORT_NOTIC_EMPID");
                    if (StringUtil.isEmpty(emp_id)) {
                        emp_id = Constant.XJ_REPORT_NOTIC_EMPID;
                    }
                    String mobile = employeesService.getMobile(emp_id);
                    // 发送微信
                    messageService.sendWxMsg(request, inspectionPayInfo.getId(), Constant.INSPECTION_CORPID,
                            Constant.INSPECTION_PWD, content, mobile);
                    // 发送短信
                    // messageService.sendMoMsg(request,
                    // inspectionPayInfo.getId(), content, mobile);
                }
                // 2018-03-29应明子涵要求，增加九寨报告已收费提醒消息推送
                if (Constant.CHECK_UNIT_100091.equals(check_unit_id)) {
                    // 获取发送内容
                    String content = Constant.getJzReportPayNoticeContent(inspectionPayInfo.getCompany_name(), count,
                            report_sn);
                    // 获取发送目标号码
                    String emp_id = Factory.getSysPara().getProperty("JZ_REPORT_NOTIC_EMPID");
                    if (StringUtil.isEmpty(emp_id)) {
                        emp_id = Constant.JZ_REPORT_NOTIC_EMPID;
                    }
                    String mobile = employeesService.getMobile(emp_id);
                    // 发送微信
                    messageService.sendWxMsg(request, inspectionPayInfo.getId(), Constant.INSPECTION_CORPID,
                            Constant.INSPECTION_PWD, content, mobile);
                    // 发送短信
                    // messageService.sendMoMsg(request,
                    // inspectionPayInfo.getId(), content, mobile);
                }
            }
            return JsonWrapper.successWrapper(inspectionPayInfo);
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
            return JsonWrapper.failureWrapperMsg("保存失败！");
        }
    }

    // 承压类设备报告收费保存
    @RequestMapping(value = "savePayCY")
    @ResponseBody
    public HashMap<String, Object> savePayCY(HttpServletRequest request,
                                             @RequestBody InspectionPayInfo inspectionPayInfo) {
        try {
            CurrentSessionUser user = SecurityUtil.getSecurityUser();

            double pay_total = Double.parseDouble(inspectionPayInfo
                    .getPay_received()); // 本次收费总金额
            double remark = Double.parseDouble(inspectionPayInfo.getRemark()); // 本次转账总金额

            // 1、验证发票号是否存在
            String invoice_no = inspectionPayInfo.getInvoice_no();
            // 1.2、原发票号
            String old_invoice_no = "";

            boolean isAdd = true;
            String status = request.getParameter("status");
            if (!"add".equals(status)) {
                isAdd = false;
            }

            double old_pay_money = 0;
            if (StringUtil.isNotEmpty(inspectionPayInfo.getId())) {
                old_invoice_no = inspectionPayInfoService.queryInvoice_no(inspectionPayInfo.getId());
                old_pay_money = inspectionPayInfoService.queryMoney(inspectionPayInfo.getId());
            }

            if (StringUtil.isNotEmpty(invoice_no)) {
                List<CwInvoiceF> list = cwInvoiceFManager.querysByInvoice_no(invoice_no);
                if (list.isEmpty()) {
                    return JsonWrapper.failureWrapperMsg("亲，系统未能识别发票号（" + invoice_no + "），请联系财务人员导入！");
                } else {
                    CwInvoiceF cwInvoiceF = list.get(0);
                    if ("SY".equals(cwInvoiceF.getStatus())) {
                    	String pay_date = DateUtil.format(inspectionPayInfo.getPay_date(), Constant.defaultDatePattern);
                        String invoice_date = DateUtil.format(cwInvoiceF.getInvoiceDate(), Constant.defaultDatePattern);
                        if (pay_date.equals(invoice_date)
                                && inspectionPayInfo.getCompany_name().equals(cwInvoiceF.getInvoiceUnit())) {
                            // 相同开票单位和相同开票日期允许重复登记收费，反之
                        } else {
                            if (isAdd) {
                                return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经使用，请核实！");
                            } else {
                                if (StringUtil.isNotEmpty(old_invoice_no)) {
                                    if (!invoice_no.equals(old_invoice_no)) {
                                        return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经使用，请核实！");
                                    }
                                }
                            }
                        }
                    }
                    if ("ZF".equals(cwInvoiceF.getStatus())) {
                        return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经作废，不能使用，请核实！");
                    }
                }
            } else {
                if (!"3".equals(inspectionPayInfo.getPay_type())) {
                    return JsonWrapper.failureWrapperMsg("亲，发票号是必填项，请填写发票号！");
                }
            }

            // 2、当收费方式为“银行转账、现金及转账、转账及pos机刷卡”时，验证转账剩余金额是否充足
            if (isZhuanZhang(inspectionPayInfo.getPay_type())) {
                if (inspectionPayInfo.getCwBankDTOList() != null) {
                    List<CwBankDTO> cwBankList = inspectionPayInfo
                            .getCwBankDTOList();
                    if (!cwBankList.isEmpty()) {
                        // 2.1、验证银行转账余额是否不足
                        String bank_ids = "";
                        for (CwBankDTO cwBankDTO : cwBankList) {
                            if (bank_ids.length() > 0) {
                                bank_ids += ",";
                            }
                            bank_ids += cwBankDTO.getId();
                        }
                        if (StringUtil.isNotEmpty(bank_ids)) {
                            // 获取银行转账剩余金额
                            double money = cwBankDetailManager
                                    .queryBankMoney(bank_ids);
                            // 如果剩余金额不足，提示用户
                            if (money < remark) {
                                return JsonWrapper
                                        .failureWrapperMsg("亲，您所选的银行转账余额不足，请重新选择！");
                            }
                        }
                    }
                }
            }

            // 3、保存缴费信息
            inspectionPayInfo.setPayment_status("2"); // 缴费状态 0 默认 1 待收费 2 已收费 3
            // 取消收费
            inspectionPayInfo.setCreated_date(new Date()); // 操作日期
            inspectionPayInfo.setCreated_by(user.getName()); // 操作人
            // 收费人姓名
            if (StringUtil.isEmpty(inspectionPayInfo.getReceive_man_name())) {
                inspectionPayInfo.setReceive_man_name(user.getName());
            }
            // 检验部门
            String check_dep_name = "";
            if (StringUtil.isEmpty(inspectionPayInfo.getCheck_dep_name())) {
                if (StringUtil.isNotEmpty(inspectionPayInfo.getCheck_dep_id())) {
                    Org org = orgManager.get(inspectionPayInfo
                            .getCheck_dep_id());
                    check_dep_name = StringUtil.isNotEmpty(org.getOrgName()) ? org
                            .getOrgName() : "";
                    inspectionPayInfo.setCheck_dep_name(check_dep_name);
                }
            }
            if (StringUtil.isEmpty(inspectionPayInfo.getDevice_type())) {
                inspectionPayInfo.setDevice_type("CY"); // 承压开票
            }
            inspectionPayInfoService.save(inspectionPayInfo);

            // 4、当收费方式为“银行转账、现金及转账、转账及pos机刷卡”时，返写银行转账记录信息表
            String cw_bank_ids = "";
            if (isZhuanZhang(inspectionPayInfo.getPay_type())) {
                if (inspectionPayInfo.getCwBankDTOList() != null) {
                    List<CwBankDTO> cwBankList = inspectionPayInfo
                            .getCwBankDTOList();
                    if (!cwBankList.isEmpty()) {
                        for (CwBankDTO cwBankDTO : cwBankList) {
                            // 4.2、保存收费业务与银行转账记录关联信息表
                            inspectionPayInfoDao.insertCwBank2Pay(
                                    StringUtil.getUUID(), cwBankDTO.getId(),
                                    inspectionPayInfo.getId(),
                                    cwBankDTO.getUsedMoney(),
                                    cwBankDTO.getPay_remark());

                            // 4.3、返写银行转账记录信息表
                            CwBankDetail cwBankDetail = cwBankDetailManager
                                    .get(cwBankDTO.getId());
                            // 剩余金额
                            cwBankDetail
                                    .setRestMoney(TS_Util
                                            .ratioTransform((cwBankDetail
                                                    .getRestMoney()
                                                    .doubleValue() - cwBankDTO
                                                    .getUsedMoney()
                                                    .doubleValue())));
                            // 本次已收金额
                            cwBankDetail
                                    .setUsedMoney(TS_Util
                                            .ratioTransform((cwBankDetail
                                                    .getUsedMoney()
                                                    .doubleValue() + cwBankDTO
                                                    .getUsedMoney()
                                                    .doubleValue())));
                            cwBankDetailManager.save(cwBankDetail);
                            if (cw_bank_ids.length() > 0) {
                                cw_bank_ids += ",";
                            }
                            cw_bank_ids += cwBankDetail.getId();

                            logService
                                    .setLogs(
                                            cwBankDTO.getId(),
                                            "报告开票，使用银行转账",
                                            "本次剩余金额："
                                                    + TS_Util
                                                    .ratioTransform(cwBankDetail
                                                            .getRestMoney()
                                                            .doubleValue())
                                                    + "，"
                                                    + "本次收费金额："
                                                    + TS_Util
                                                    .ratioTransform(cwBankDetail
                                                            .getUsedMoney()
                                                            .doubleValue()),
                                            user.getId(), user.getName(),
                                            new Date(), request.getRemoteAddr());
                        }
                    }

                }
            }
            inspectionPayInfo.setFk_cw_bank_id(cw_bank_ids);
            inspectionPayInfoService.save(inspectionPayInfo);

            // 5、写日志
            logService.setLogs(inspectionPayInfo.getId(), "报告开票",
                    "报告开票，使用发票（发票号：" + invoice_no + "）", user.getId(),
                    user.getName(), new Date(), request.getRemoteAddr());

            // 6、返写发票信息
            if (StringUtil.isNotEmpty(invoice_no)) {
                if (StringUtil.isEmpty(old_invoice_no)) {
                    // 5.1 返写更新本次使用的新发票号信息
                    updateCwInvoiceF(request, inspectionPayInfo, check_dep_name, isAdd, false);
                } else {
                    if (!invoice_no.equals(old_invoice_no)) {
                        // 5.2 返写更新原发票号信息
                        // 原票号是否作废（0：否 1：是）
                        String if_del_invoice = request.getParameter("if_del_invoice");
                        if ("1".equals(if_del_invoice)) {
                            zfOldCwInvoiceF(request, old_invoice_no, old_pay_money, check_dep_name);
                        } else {
                            updateOldCwInvoiceF(request, old_invoice_no, old_pay_money, check_dep_name);
                        }
                    }
                    updateCwInvoiceF(request, inspectionPayInfo, check_dep_name, isAdd, false);
                }
            }
            return JsonWrapper.successWrapper(inspectionPayInfo.getId());
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
            return JsonWrapper.failureWrapperMsg("保存失败！");
        }
    }

    // 借票收费保存
    @RequestMapping(value = "savePayCY1")
    @ResponseBody
    public HashMap<String, Object> savePayCY1(HttpServletRequest request,
                                              @RequestBody InspectionPayInfo inspectionPayInfo) {
        try {
            CurrentSessionUser user = SecurityUtil.getSecurityUser();

            double pay_total = Double.parseDouble(inspectionPayInfo
                    .getPay_received()); // 本次收费总金额
            double remark = Double.parseDouble(inspectionPayInfo.getRemark()); // 本次转账总金额

            boolean isAdd = true;
            String status = request.getParameter("status");
            if (!"add".equals(status)) {
                isAdd = false;
            }

            // 1、验证发票号是否存在
            String invoice_no = inspectionPayInfo.getInvoice_no();
            // 1.2、原发票号
            String old_invoice_no = "";

            double old_pay_money = 0;
            if (StringUtil.isNotEmpty(inspectionPayInfo.getId())) {
                old_invoice_no = inspectionPayInfoService.queryInvoice_no(inspectionPayInfo.getId());
                old_pay_money = inspectionPayInfoService.queryMoney(inspectionPayInfo.getId());
            }

            String type = request.getParameter("type");
            if (!"borrowPay".equals(type)) {
                if (StringUtil.isNotEmpty(invoice_no)) {
                    List<CwInvoiceF> list = cwInvoiceFManager.querysByInvoice_no(invoice_no);
                    if (list.isEmpty()) {
                        return JsonWrapper.failureWrapperMsg("亲，系统未能识别发票号（" + invoice_no + "），请联系财务人员导入！");
                    } else {
                        CwInvoiceF cwInvoiceF = list.get(0);
                        if ("SY".equals(cwInvoiceF.getStatus())) {
                            if (inspectionPayInfo.getPay_date().equals(cwInvoiceF.getInvoiceDate())
                                    && inspectionPayInfo.getCompany_name().equals(cwInvoiceF.getInvoiceUnit())) {
                                // 相同开票单位和相同开票日期允许重复登记收费，反之
                            	// 经确认通用借票不存在同一票号重复登记的情况（2019/01/18）
                            } else {
                                if (isAdd) {
                                    return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经使用，请核实！");
                                } else {
                                    if (StringUtil.isNotEmpty(old_invoice_no)) {
                                        if (!invoice_no.equals(old_invoice_no)) {
                                            return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经使用，请核实！");
                                        }
                                    }
                                }
                            }
                        }
                        if ("ZF".equals(cwInvoiceF.getStatus())) {
                            return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经作废，不能使用，请核实！");
                        }
                    }
                } else {
                    if (!"3".equals(inspectionPayInfo.getPay_type())) {
                        return JsonWrapper.failureWrapperMsg("亲，发票号是必填项，请填写发票号！");
                    }
                }
            }

            // 2、当收费方式为“银行转账、现金及转账、转账及pos机刷卡”时，验证转账剩余金额是否充足
            if (isZhuanZhang(inspectionPayInfo.getPay_type())) {
                if (inspectionPayInfo.getCwBankDTOList() != null) {
                    List<CwBankDTO> cwBankList = inspectionPayInfo
                            .getCwBankDTOList();
                    if (!cwBankList.isEmpty()) {
                        // 2.1、验证银行转账余额是否不足
                        String bank_ids = "";
                        for (CwBankDTO cwBankDTO : cwBankList) {
                            if (bank_ids.length() > 0) {
                                bank_ids += ",";
                            }
                            bank_ids += cwBankDTO.getId();
                        }
                        if (StringUtil.isNotEmpty(bank_ids)) {
                            // 获取银行转账剩余金额
                            double money = cwBankDetailManager
                                    .queryBankMoney(bank_ids);
                            // 如果剩余金额不足，提示用户
                            if (money < remark) {
                                return JsonWrapper
                                        .failureWrapperMsg("亲，您所选的银行转账余额不足，请重新选择！");
                            }
                        }
                    }
                }
            }

            // 3、将原开票记录标记取消表示已开票
            String borrow_id = request.getParameter("borrow_id");
            if (StringUtil.isNotEmpty(borrow_id)) {
                // 3.1、保存外借记录
                ReportBorrow reportBorrow = reportBorrowService.get(borrow_id);
                reportBorrow.setBorrow_status("99"); // 3:取消外借 99：标记已取消外借且已收费
                reportBorrow.setOperator_user_id(user.getId());
                reportBorrow.setOperator_name(user.getName()); // 操作人
                reportBorrow.setOperator_date(new Date()); // 操作时间
                reportBorrowService.save(reportBorrow);

                // 3.2、写日志
                logService.setLogs(reportBorrow.getId(), "借票收费", "借票收费，取消借票",
                        user.getId(), user.getName(), new Date(),
                        request.getRemoteAddr());
                // 3.3、取消外借（1：外借发票）时，修改发票使用状态
                // 经确认外借发票收费取消外借后，发票状态为已使用（2019/01/18）
                if ("1".equals(reportBorrow.getBorrow_type())) {
                    String invoice_no1 = reportBorrow.getInvoice_no();
                    if (StringUtil.isNotEmpty(invoice_no1)) {
                        CwInvoiceF cwInvoiceF = cwInvoiceFManager
                                .queryByInvoice_no(invoice_no1);
                        // 发票金额
                        cwInvoiceF.setInvoiceMoney(TS_Util
                                .ratioTransform(cwInvoiceF.getInvoiceMoney()
                                        .doubleValue()
                                        - Double.parseDouble(reportBorrow
                                        .getUnpay_amount())));
                        cwInvoiceF.setMoneyType("");// 交易类型
                        cwInvoiceF.setInvoiceUnit(""); // 开票单位
                        cwInvoiceF.setCheckoutUnit(""); // 受检单位
                        cwInvoiceF.setCheckoutDep(""); // 检验部门
                        cwInvoiceF.setStatus("WJQX"); // 发票状态（WSY：未使用 SY：使用 ZF：作废 LY：领用 SFQX：收费取消  WJQX：外借取消）
                        cwInvoiceF.setInvoiceDate(null);// 开票日期
                        cwInvoiceFManager.save(cwInvoiceF);
                        logService.setLogs(cwInvoiceF.getId(), "取消借票",
                                "取消借票，修改发票（发票号：" + invoice_no1 + "）",
                                user.getId(), user.getName(), new Date(),
                                request.getRemoteAddr());
                    }
                }
            }

            // 4、保存缴费信息
            inspectionPayInfo.setPayment_status("2"); // 缴费状态 0 默认 1 待收费 2 已收费 3
            // 取消收费
            inspectionPayInfo.setCreated_date(new Date()); // 操作日期
            inspectionPayInfo.setCreated_by(user.getName()); // 操作人
            // 收费人姓名
            if (StringUtil.isEmpty(inspectionPayInfo.getReceive_man_name())) {
                inspectionPayInfo.setReceive_man_name(user.getName());
            }
            // 检验部门
            String check_dep_name = "";
            if (StringUtil.isEmpty(inspectionPayInfo.getCheck_dep_name())) {
                if (StringUtil.isNotEmpty(inspectionPayInfo.getCheck_dep_id())) {
                    Org org = orgManager.get(inspectionPayInfo
                            .getCheck_dep_id());
                    check_dep_name = StringUtil.isNotEmpty(org.getOrgName()) ? org
                            .getOrgName() : "";
                    inspectionPayInfo.setCheck_dep_name(check_dep_name);
                }
            }
            if (StringUtil.isEmpty(inspectionPayInfo.getDevice_type())) {
                inspectionPayInfo.setDevice_type("CY"); // 承压开票
            }
            inspectionPayInfoService.save(inspectionPayInfo);

            // 5、当收费方式为“银行转账、现金及转账、转账及pos机刷卡”时，返写银行转账记录信息表
            String cw_bank_ids = "";
            if (isZhuanZhang(inspectionPayInfo.getPay_type())) {
                if (inspectionPayInfo.getCwBankDTOList() != null) {
                    List<CwBankDTO> cwBankList = inspectionPayInfo
                            .getCwBankDTOList();
                    if (!cwBankList.isEmpty()) {
                        for (CwBankDTO cwBankDTO : cwBankList) {
                            // 4.2、保存收费业务与银行转账记录关联信息表
                            inspectionPayInfoDao.insertCwBank2Pay(
                                    StringUtil.getUUID(), cwBankDTO.getId(),
                                    inspectionPayInfo.getId(),
                                    cwBankDTO.getUsedMoney(),
                                    cwBankDTO.getPay_remark());

                            // 4.3、返写银行转账记录信息表
                            CwBankDetail cwBankDetail = cwBankDetailManager
                                    .get(cwBankDTO.getId());
                            // 剩余金额
                            cwBankDetail
                                    .setRestMoney(TS_Util
                                            .ratioTransform((cwBankDetail
                                                    .getRestMoney()
                                                    .doubleValue() - cwBankDTO
                                                    .getUsedMoney()
                                                    .doubleValue())));
                            // 本次已收金额
                            cwBankDetail
                                    .setUsedMoney(TS_Util
                                            .ratioTransform((cwBankDetail
                                                    .getUsedMoney()
                                                    .doubleValue() + cwBankDTO
                                                    .getUsedMoney()
                                                    .doubleValue())));
                            cwBankDetailManager.save(cwBankDetail);
                            if (cw_bank_ids.length() > 0) {
                                cw_bank_ids += ",";
                            }
                            cw_bank_ids += cwBankDetail.getId();

                            logService
                                    .setLogs(
                                            cwBankDTO.getId(),
                                            "借票收费，使用银行转账",
                                            "本次剩余金额："
                                                    + TS_Util
                                                    .ratioTransform(cwBankDetail
                                                            .getRestMoney()
                                                            .doubleValue())
                                                    + "，"
                                                    + "本次收费金额："
                                                    + TS_Util
                                                    .ratioTransform(cwBankDetail
                                                            .getUsedMoney()
                                                            .doubleValue()),
                                            user.getId(), user.getName(),
                                            new Date(), request.getRemoteAddr());
                        }
                    }

                }
            }
            inspectionPayInfo.setFk_cw_bank_id(cw_bank_ids);
            inspectionPayInfoService.save(inspectionPayInfo);

            // 6、写日志
            logService.setLogs(inspectionPayInfo.getId(), "借票收费",
                    "借票收费，使用发票（发票号：" + invoice_no + "）", user.getId(),
                    user.getName(), new Date(), request.getRemoteAddr());

            // 7、返写发票信息
            if (StringUtil.isNotEmpty(invoice_no)) {
                if (StringUtil.isEmpty(old_invoice_no)) {
                    // 7.1 返写更新本次使用的新发票号信息
                    updateCwInvoiceF(request, inspectionPayInfo, check_dep_name, isAdd, true);
                } else {
                    if (!invoice_no.equals(old_invoice_no)) {
                        // 7.2 返写更新原发票号信息
                        // 原票号是否作废（0：否 1：是）
                        String if_del_invoice = request.getParameter("if_del_invoice");
                        if ("1".equals(if_del_invoice)) {
                            zfOldCwInvoiceF(request, old_invoice_no, old_pay_money, check_dep_name);
                        } else {
                            updateOldCwInvoiceF(request, old_invoice_no, old_pay_money, check_dep_name);
                        }
                    }
                    updateCwInvoiceF(request, inspectionPayInfo, check_dep_name, isAdd, true);
                }
            }
            return JsonWrapper.successWrapper(inspectionPayInfo.getId());
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
            return JsonWrapper.failureWrapperMsg("保存失败！");
        }
    }

    // 修改通用开票
    @RequestMapping(value = "savePayCY2")
    @ResponseBody
    public HashMap<String, Object> savePayCY2(HttpServletRequest request,
                                              @RequestBody InspectionPayInfo inspectionPayInfo) {
        try {
            CurrentSessionUser user = SecurityUtil.getSecurityUser();
            double old_pay_total = 0;
            double pay_total = Double.parseDouble(inspectionPayInfo
                    .getPay_received()); // 本次收费总金额
            double remark = Double.parseDouble(inspectionPayInfo.getRemark()); // 本次转账总金额

            // 1、验证发票号是否存在
            String invoice_no = inspectionPayInfo.getInvoice_no();
            // 1.2、原发票号
            String old_invoice_no = "";

            boolean isAdd = true;
            String status = request.getParameter("status");
            if (!"add".equals(status)) {
                isAdd = false;
            }

            double old_pay_money = 0;
            if (StringUtil.isNotEmpty(inspectionPayInfo.getId())) {
                old_invoice_no = inspectionPayInfoService.queryInvoice_no(inspectionPayInfo.getId());
                old_pay_money = inspectionPayInfoService.queryMoney(inspectionPayInfo.getId());
            }

            if (StringUtil.isNotEmpty(invoice_no)) {
                List<CwInvoiceF> list = cwInvoiceFManager.querysByInvoice_no(invoice_no);
                if (list.isEmpty()) {
                    return JsonWrapper.failureWrapperMsg("亲，系统未能识别发票号（" + invoice_no + "），请联系财务人员导入！");
                } else {
                    CwInvoiceF cwInvoiceF = list.get(0);
                    if ("SY".equals(cwInvoiceF.getStatus())) {
                    	String pay_date = DateUtil.format(inspectionPayInfo.getPay_date(),Constant.defaultDatePattern);
                        String invoice_date = DateUtil.format(cwInvoiceF.getInvoiceDate(),Constant.defaultDatePattern);
                        if (pay_date.equals(invoice_date)
                                && inspectionPayInfo.getCompany_name().equals(cwInvoiceF.getInvoiceUnit())) {
                            // 相同开票单位和相同开票日期允许重复登记收费，反之
                        } else {
                            if (isAdd) {
                                return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经使用，请核实！");
                            } else {
                                if (StringUtil.isNotEmpty(old_invoice_no)) {
                                    if (!invoice_no.equals(old_invoice_no)) {
                                        return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经使用，请核实！");
                                    }
                                }
                            }
                        }
                    }
                    if ("ZF".equals(cwInvoiceF.getStatus())) {
                        return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经作废，不能使用，请核实！");
                    }
                }
            } else {
                if (!"3".equals(inspectionPayInfo.getPay_type())) {
                    return JsonWrapper.failureWrapperMsg("亲，发票号是必填项，请填写发票号！");
                }
            }

            // 2、更新转账记录
            if (StringUtil.isNotEmpty(inspectionPayInfo.getId())) {
                old_pay_total = inspectionPayInfoService.queryMoney(inspectionPayInfo.getId());
                // 是否重新冲账（0：否 1：是）
                String bank_change = request.getParameter("bank_change");
                if ("1".equals(bank_change)) {
                    // 2、重新冲账时，删除原收费方式为“银行转账、现金及转账、转账及pos机刷卡”的银行转账记录信息表
                    List<CwBank2Pay> list = cwBank2PayManager
                            .queryCwBank2Pays(inspectionPayInfo.getId());
                    if (!list.isEmpty()) {
                        for (CwBank2Pay cwBank2Pay : list) {
                            // 1.1、返写银行转账记录信息表
                            CwBankDetail cwBankDetail = cwBankDetailManager
                                    .get(cwBank2Pay.getFk_cw_bank_id());
                            if (cwBank2Pay != null) {
                                // 剩余金额
                                cwBankDetail
                                        .setRestMoney(TS_Util
                                                .ratioTransform((cwBankDetail
                                                        .getRestMoney()
                                                        .doubleValue() + cwBank2Pay
                                                        .getCur_used_money()
                                                        .doubleValue())));
                                // 已收金额
                                cwBankDetail.setUsedMoney(TS_Util
                                        .ratioTransform(cwBankDetail
                                                .getUsedMoney().doubleValue()
                                                - cwBank2Pay
                                                .getCur_used_money()
                                                .doubleValue()));
                            }
                            cwBankDetailManager.save(cwBankDetail);

                            // 2.2、重新冲账时，删除收费业务与银行转账记录关联信息表
                            cwBank2PayManager
                                    .deleteBusiness(cwBank2Pay.getId());
                        }
                    } else {
                        String fk_cw_bank_ids = inspectionPayInfo
                                .getFk_cw_bank_id();
                        if (StringUtil.isNotEmpty(fk_cw_bank_ids)) {
                            String[] bank_ids = fk_cw_bank_ids.split(",");
                            for (int j = 0; j < bank_ids.length; j++) {
                                // 2.1 获取银行转账历史记录
                                CwBank2Pay cwBank2Pay = cwBank2PayManager
                                        .queryCwBank2Pay(bank_ids[j],
                                                inspectionPayInfo.getId());

                                // 2.2、返写银行转账记录信息表
                                CwBankDetail cwBankDetail = cwBankDetailManager
                                        .get(bank_ids[j]);
                                if (cwBank2Pay != null) {
                                    // 剩余金额
                                    cwBankDetail.setRestMoney(TS_Util
                                            .ratioTransform((cwBankDetail
                                                    .getRestMoney()
                                                    .doubleValue() + cwBank2Pay
                                                    .getCur_used_money()
                                                    .doubleValue())));
                                    // 已收金额
                                    cwBankDetail
                                            .setUsedMoney(TS_Util
                                                    .ratioTransform(cwBankDetail
                                                            .getUsedMoney()
                                                            .doubleValue()
                                                            - cwBank2Pay
                                                            .getCur_used_money()
                                                            .doubleValue()));
                                }
                                cwBankDetailManager.save(cwBankDetail);

                                // 2.3、重新冲账时，删除收费业务与银行转账记录关联信息表
                                cwBank2PayManager.deleteBusiness(cwBank2Pay
                                        .getId());
                            }
                        }
                    }

                    // 2.4、重新冲账时，返写收费方式为“银行转账、现金及转账、转账及pos机刷卡”的银行转账记录信息表
                    String cw_bank_ids = "";
                    if (inspectionPayInfo.getCwBankDTOList() != null) {
                        List<CwBankDTO> cwBankList = inspectionPayInfo
                                .getCwBankDTOList();
                        if (!cwBankList.isEmpty()) {
                            // 2.5、验证银行转账余额是否不足
                            String bank_ids = "";
                            for (CwBankDTO cwBankDTO : cwBankList) {
                                if (bank_ids.length() > 0) {
                                    bank_ids += ",";
                                }
                                bank_ids += cwBankDTO.getId();
                            }
                            if (StringUtil.isNotEmpty(bank_ids)) {
                                // 获取银行转账剩余金额
                                double money = cwBankDetailManager
                                        .queryBankMoney(bank_ids);
                                // 如果剩余金额不足，提示用户
                                if (money < remark) {
                                    return JsonWrapper
                                            .failureWrapperMsg("亲，您所选的银行转账余额不足，请重新选择！");
                                }
                            }

                            for (CwBankDTO cwBankDTO : cwBankList) {
                                // 2.6、保存收费业务与银行转账记录关联信息表
                                inspectionPayInfoDao.insertCwBank2Pay(
                                        StringUtil.getUUID(),
                                        cwBankDTO.getId(),
                                        inspectionPayInfo.getId(),
                                        cwBankDTO.getUsedMoney(),
                                        cwBankDTO.getPay_remark());

                                // 2.7、返写银行转账记录信息表
                                CwBankDetail cwBankDetail = cwBankDetailManager
                                        .get(cwBankDTO.getId());
                                // 剩余金额
                                cwBankDetail
                                        .setRestMoney(TS_Util
                                                .ratioTransform((cwBankDetail
                                                        .getRestMoney()
                                                        .doubleValue() - cwBankDTO
                                                        .getUsedMoney()
                                                        .doubleValue())));
                                // 本次已收金额
                                cwBankDetail
                                        .setUsedMoney(TS_Util
                                                .ratioTransform((cwBankDetail
                                                        .getUsedMoney()
                                                        .doubleValue() + cwBankDTO
                                                        .getUsedMoney()
                                                        .doubleValue())));
                                cwBankDetailManager.save(cwBankDetail);
                                if (cw_bank_ids.length() > 0) {
                                    cw_bank_ids += ",";
                                }
                                cw_bank_ids += cwBankDetail.getId();

                                logService
                                        .setLogs(
                                                cwBankDTO.getId(),
                                                "修改开票，修改银行转账",
                                                "本次剩余金额："
                                                        + TS_Util
                                                        .ratioTransform(cwBankDetail
                                                                .getRestMoney()
                                                                .doubleValue())
                                                        + "，"
                                                        + "本次收费金额："
                                                        + TS_Util
                                                        .ratioTransform(cwBankDetail
                                                                .getUsedMoney()
                                                                .doubleValue()),
                                                user.getId(), user.getName(),
                                                new Date(), request
                                                        .getRemoteAddr());
                            }
                        }
                    }
                    inspectionPayInfo.setFk_cw_bank_id(cw_bank_ids);
                    // inspectionPayInfoService.save(inspectionPayInfo);
                }

                // 4、保存缴费信息
                inspectionPayInfo.setPayment_status("2"); // 缴费状态 0 默认 1 待收费 2
                // 已收费 3 取消收费
                inspectionPayInfo.setCreated_date(new Date()); // 操作日期
                inspectionPayInfo.setCreated_by(user.getName()); // 操作人
                // 收费人姓名
                if (StringUtil.isEmpty(inspectionPayInfo.getReceive_man_name())) {
                    inspectionPayInfo.setReceive_man_name(user.getName());
                }
                // 检验部门
                String check_dep_name = "";
                if (StringUtil.isEmpty(inspectionPayInfo.getCheck_dep_name())) {
                    if (StringUtil.isNotEmpty(inspectionPayInfo
                            .getCheck_dep_id())) {
                        Org org = orgManager.get(inspectionPayInfo
                                .getCheck_dep_id());
                        check_dep_name = StringUtil
                                .isNotEmpty(org.getOrgName()) ? org
                                .getOrgName() : "";
                        inspectionPayInfo.setCheck_dep_name(check_dep_name);
                    }
                }
                if (StringUtil.isEmpty(inspectionPayInfo.getDevice_type())) {
                    inspectionPayInfo.setDevice_type("CY"); // 承压开票
                }
                inspectionPayInfoService.save(inspectionPayInfo);

                // 6、写日志
                logService.setLogs(inspectionPayInfo.getId(), "修改开票",
                        "修改开票，修改发票（发票号：" + invoice_no + "）", user.getId(),
                        user.getName(), new Date(), request.getRemoteAddr());

                // 7、返写发票信息
                if (StringUtil.isNotEmpty(invoice_no)) {
                    if (StringUtil.isEmpty(old_invoice_no)) {
                        // 7.1 返写更新本次使用的新发票号信息
                        updateCwInvoiceF(request, inspectionPayInfo, check_dep_name, isAdd, false);
                    } else {
                        if (!invoice_no.equals(old_invoice_no)) {
                            // 7.2 返写更新原发票号信息
                            // 原票号是否作废（0：否 1：是）
                            String if_del_invoice = request.getParameter("if_del_invoice");
                            if ("1".equals(if_del_invoice)) {
                                zfOldCwInvoiceF(request, old_invoice_no, old_pay_money, check_dep_name);
                            } else {
                                updateOldCwInvoiceF(request, old_invoice_no, old_pay_money, check_dep_name);
                            }
                        }
                        updateCwInvoiceF(request, inspectionPayInfo, check_dep_name, isAdd, false);
                    }
                }
            }
            return JsonWrapper.successWrapper(inspectionPayInfo.getId());
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
            return JsonWrapper.failureWrapperMsg("保存失败！");
        }
    }

    // 制造监督检验报告收费保存
    @RequestMapping(value = "saveZZJDPayInfo")
    @ResponseBody
    public HashMap<String, Object> saveZZJDPayInfo(
            @RequestBody InspectionPayInfo inspectionPayInfo,
            HttpServletRequest request) {
        try {
            CurrentSessionUser user = super.getCurrentUser();
            double pay_total = Double.parseDouble(inspectionPayInfo
                    .getPay_received()); // 本次实收总金额
            // double cur_pay = pay_total; // 当前需收金额

            // 1、保存业务信息（更新收费状态、实收金额等）
            double receivable = 0;
            int count = inspectionPayInfo.getInspectionZZJDPayInfoDTOList()
                    .size();
            receivable = Double
                    .parseDouble(inspectionPayInfo.getPay_received()) / count;
            String ids = "";
            String report_com_name = ""; // 报告书使用单位（受检单位）
            for (InspectionZZJDPayInfoDTO inspectionZZJDPayInfoDTO : inspectionPayInfo
                    .getInspectionZZJDPayInfoDTOList()) {
                InspectionInfo inspectionInfo = inspectionInfoService
                        .get(inspectionZZJDPayInfoDTO.getId());
                inspectionInfo.setReceivable(receivable); // 实收金额
                // 缴费方式 1 现金缴费 2 转账 3 免收费 4 现金及转账 5 pos 6 现金及pos 7 转账及pos
                inspectionInfo.setFee_type(inspectionPayInfo.getPay_type());
                inspectionInfo.setInvoice_no(inspectionPayInfo.getInvoice_no()); // 发票号
                if ("3".equals(inspectionPayInfo.getPay_type())) {
                    // 收费类型 0 正常收费 1 协议收费 2 免收费
                    inspectionInfo.setAdvance_type("2");
                } else {
                    inspectionInfo.setAdvance_type("0");
                }
                // 收费状态（0 默认 1 待收费 2 已收费 3 借报告 4 借发票 5 借报告和发票）
                inspectionInfo.setFee_status("2");
                inspectionInfo.setInvoice_date(inspectionPayInfo.getPay_date()); // 缴费日期
                inspectionInfoService.save(inspectionInfo); // 1、保存业务信息（更新收费状态、实收金额等）
                if (ids.length() > 0) {
                    ids += ",";
                }
                ids += inspectionInfo.getId();
                if (StringUtil.isEmpty(report_com_name)) {
                    report_com_name = inspectionInfo.getReport_com_name();
                }
            }

            // 2、保存缴费信息
            if (StringUtil.isEmpty(inspectionPayInfo.getPay_no())) {
                String curYearMonth = TS_Util.getCurYearMonth(); // 获取当前年月
                String no = inspectionPayInfoService
                        .queryNextPay_no(curYearMonth); // 获取编号后四位序号
                // 编号 = 当前年月 + 4位序号
                inspectionPayInfo.setPay_no(curYearMonth + no);
            }
            inspectionPayInfo.setPayment_status("2"); // 缴费状态 0 默认 1 待收费 2 已收费
            inspectionPayInfo.setCreated_date(new Date()); // 操作日期
            inspectionPayInfo.setCreated_by(user.getName()); // 操作人
            inspectionPayInfoService.save(inspectionPayInfo); // 2、保存缴费信息

            // 3、保存缴费详细信息
            InspectionPayDetail inspectionPayDetail = new InspectionPayDetail();
            BeanUtils.copyProperties(inspectionPayDetail, inspectionPayInfo);
            inspectionPayDetail.setId(null);
            inspectionPayDetail.setFk_pay_info_id(inspectionPayInfo.getId());
            inspectionPayDetail
                    .setReceive_date(inspectionPayInfo.getPay_date());
            inspectionPayDetailService.save(inspectionPayDetail); // 3、保存缴费详细信息

            // 4、当收费方式为“银行转账、现金及转账、转账及pos机刷卡”时，返写银行转账记录信息表
            String cw_bank_ids = "";
            if (isZhuanZhang(inspectionPayInfo.getPay_type())) {
                for (CwBankDTO cwBankDTO : inspectionPayInfo.getCwBankDTOList()) {
                    // 4.1、保存收费业务与银行转账记录关联信息表
                    inspectionPayInfoDao.insertCwBank2Pay(StringUtil.getUUID(), cwBankDTO.getId(),
                            inspectionPayInfo.getId(), cwBankDTO.getUsedMoney(), cwBankDTO.getPay_remark());

                    // 4.2、返写银行转账记录信息表
                    CwBankDetail cwBankDetail = cwBankDetailManager.get(cwBankDTO.getId());
                    // 剩余金额
                    cwBankDetail.setRestMoney(TS_Util.ratioTransform(
                            (cwBankDTO.getRestMoney().doubleValue() - cwBankDTO.getUsedMoney().doubleValue())));
                    // 本次已收金额
                    cwBankDetail.setUsedMoney(TS_Util.ratioTransform(
                            (cwBankDetail.getUsedMoney().doubleValue() + cwBankDTO.getUsedMoney().doubleValue())));
                    cwBankDetailManager.save(cwBankDetail);
                    if (cw_bank_ids.length() > 0) {
                        cw_bank_ids += ",";
                    }
                    cw_bank_ids += cwBankDetail.getId();

                    logService.setLogs(cwBankDTO.getId(), "报告收费，使用银行转账",
                            "本次剩余金额：" + TS_Util.ratioTransform(cwBankDetail.getRestMoney().doubleValue()) + "，"
                                    + "本次收费金额：" + TS_Util.ratioTransform(cwBankDTO.getUsedMoney().doubleValue()),
                            user.getId(), user.getName(), new Date(), request.getRemoteAddr());
                }
            }
            inspectionPayInfo.setFk_cw_bank_id(cw_bank_ids);
            inspectionPayInfoService.save(inspectionPayInfo);

            // 5、返写发票信息
            String invoice_no = inspectionPayInfo.getInvoice_no();
            if (StringUtil.isNotEmpty(invoice_no)) {
                CwInvoiceF cwInvoiceF = cwInvoiceFManager
                        .queryByInvoice_no(invoice_no);
                // 发票金额
                cwInvoiceF.setInvoiceMoney(BigDecimal.valueOf(pay_total));
                // 交易类型（1 现金缴费 2 转账 3 免收费/判废免收 4 现金及转账 5 pos 6 现金及pos 7 转账及pos）
                cwInvoiceF.setMoneyType(inspectionPayInfo.getPay_type());
                cwInvoiceF.setInvoiceUnit(inspectionPayInfo.getCompany_name()); // 开票单位
                cwInvoiceF.setCheckoutUnit(report_com_name); // 受检单位
                cwInvoiceF.setStatus("SY"); // 发票状态（WSY：未使用 SY：使用 ZF：作废 LY：领用）
                cwInvoiceF.setInvoice_id(user.getId());        // 开票人id
                cwInvoiceF.setInvoice_name(user.getName());    // 开票人name
                cwInvoiceFManager.save(cwInvoiceF);
                logService.setLogs(cwInvoiceF.getId(), "报告收费，使用发票",
                        "报告收费，使用发票（发票号：" + invoice_no + "）", user.getId(),
                        user.getName(), new Date(), request.getRemoteAddr());
            }

            // 6、记录日志
            String[] idArr = ids.split(",");
            for (int i = 0; i < idArr.length; i++) {
                logService.setLogs(idArr[i], "报告收费", "报告收费", user.getId(),
                        user.getName(), new Date(), request.getRemoteAddr());
            }
            return JsonWrapper.successWrapper(inspectionPayInfo);
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
            return JsonWrapper.failureWrapperMsg("保存失败！");
        }
    }

    // 取消收费（机电类）
    @RequestMapping(value = "cancelPayment")
    @ResponseBody
    public HashMap<String, Object> cancelPayment(HttpServletRequest request,
                                                 String ids) {
        CurrentSessionUser user = super.getCurrentUser();
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        try {
            String[] idArr = ids.split(",");
            for (int i = 0; i < idArr.length; i++) {
                InspectionPayInfo inspectionPayInfo = inspectionPayInfoService
                        .queryByInspectionInfoID(idArr[i]); // 报检业务收费信息
                if (inspectionPayInfo != null) {
                    // 1、当收费方式为“银行转账、现金及转账、转账及pos机刷卡”时，取消收费更新银行转账记录信息表
                    if (isZhuanZhang(inspectionPayInfo.getPay_type())) {
                        List<CwBank2Pay> list = cwBank2PayManager.queryCwBank2Pays(inspectionPayInfo.getId());
                        if (!list.isEmpty()) {
                            for (CwBank2Pay cwBank2Pay : list) {
                                // 4.1、返写银行转账记录信息表
                                CwBankDetail cwBankDetail = cwBankDetailManager
                                        .get(cwBank2Pay.getFk_cw_bank_id());
                                if (cwBank2Pay != null) {
                                    // 剩余金额
                                    cwBankDetail
                                            .setRestMoney(TS_Util
                                                    .ratioTransform((cwBankDetail
                                                            .getRestMoney()
                                                            .doubleValue() + (cwBank2Pay
                                                            .getCur_used_money() != null ? cwBank2Pay
                                                            .getCur_used_money()
                                                            .doubleValue()
                                                            : 0.00))));
                                    // 已收金额
                                    cwBankDetail
                                            .setUsedMoney(TS_Util.ratioTransform(cwBankDetail
                                                    .getUsedMoney()
                                                    .doubleValue()
                                                    - (cwBank2Pay
                                                    .getCur_used_money() != null ? cwBank2Pay
                                                    .getCur_used_money()
                                                    .doubleValue()
                                                    : 0.00)));

                                    // 4.2、取消收费时，同时删除收费业务与银行转账记录关联信息表
                                    cwBank2PayManager
                                            .deleteBusiness(cwBank2Pay.getId());
                                }
                                cwBankDetailManager.save(cwBankDetail);
                                // 4.3、取消收费时，记录取消转账日志
                                logService
                                        .setLogs(
                                                cwBankDetail.getId(),
                                                "取消收费，取消银行转账",
                                                "本次取消金额："
                                                        + (cwBank2Pay
                                                        .getCur_used_money() != null ? cwBank2Pay
                                                        .getCur_used_money()
                                                        .doubleValue()
                                                        : 0.00), user
                                                        .getId(), user
                                                        .getName(), new Date(),
                                                request.getRemoteAddr());
                            }
                        } else {
                            String fk_cw_bank_ids = inspectionPayInfo
                                    .getFk_cw_bank_id();
                            if (StringUtil.isNotEmpty(fk_cw_bank_ids)) {
                                String[] bank_ids = fk_cw_bank_ids.split(",");
                                for (int j = 0; j < bank_ids.length; j++) {
                                    // 4.1 获取银行转账历史记录
                                    CwBank2Pay cwBank2Pay = cwBank2PayManager
                                            .queryCwBank2Pay(bank_ids[j],
                                                    inspectionPayInfo.getId());

                                    // 4.2、返写银行转账记录信息表
                                    CwBankDetail cwBankDetail = cwBankDetailManager
                                            .get(bank_ids[j]);
                                    if (cwBank2Pay != null) {
                                        // 剩余金额
                                        cwBankDetail
                                                .setRestMoney(TS_Util
                                                        .ratioTransform((cwBankDetail
                                                                .getRestMoney()
                                                                .doubleValue() + (cwBank2Pay
                                                                .getCur_used_money() != null ? cwBank2Pay
                                                                .getCur_used_money()
                                                                .doubleValue()
                                                                : 0.00))));
                                        // 已收金额
                                        cwBankDetail
                                                .setUsedMoney(TS_Util.ratioTransform(cwBankDetail
                                                        .getUsedMoney()
                                                        .doubleValue()
                                                        - (cwBank2Pay
                                                        .getCur_used_money() != null ? cwBank2Pay
                                                        .getCur_used_money()
                                                        .doubleValue()
                                                        : 0.00)));
                                        cwBankDetailManager.save(cwBankDetail);

                                        // 4.3、取消收费时，同时删除收费业务与银行转账记录关联信息表
                                        cwBank2PayManager.deleteBusiness(cwBank2Pay
                                                .getId());
                                        // 4.3、取消收费时，记录取消转账日志
                                        logService
                                                .setLogs(
                                                        cwBankDetail.getId(),
                                                        "取消收费，取消银行转账",
                                                        "本次取消金额："
                                                                + (cwBank2Pay
                                                                .getCur_used_money() != null ? cwBank2Pay
                                                                .getCur_used_money()
                                                                .doubleValue()
                                                                : 0.00),
                                                        user.getId(), user
                                                                .getName(),
                                                        new Date(),
                                                        request.getRemoteAddr());
                                    }
                                }
                            }
                        }
                    }

                    // 2、取消收费，修改发票使用状态
                    String invoice_no = inspectionPayInfo.getInvoice_no();
                    if (StringUtil.isNotEmpty(invoice_no)) {
                        CwInvoiceF cwInvoiceF = cwInvoiceFManager
                                .queryByInvoice_no(invoice_no);
                        if (cwInvoiceF != null) {
                            // 发票金额
                            cwInvoiceF
                                    .setInvoiceMoney(TS_Util.ratioTransform((cwInvoiceF
                                            .getInvoiceMoney() != null ? cwInvoiceF
                                            .getInvoiceMoney().doubleValue() : 0.00)
                                            - (inspectionPayInfo.getPay_received() != null ? Double
                                            .parseDouble(inspectionPayInfo
                                                    .getPay_received()) : 0.00)));
                            // 若取消收费后金额为0则发票状态更新为收费取消，若不为0则保持除此次收费修改的信息
                            if(cwInvoiceF.getInvoiceMoney().compareTo(new BigDecimal("0"))==0) {
                            	cwInvoiceF.setMoneyType("");	// 交易类型
                            	cwInvoiceF.setInvoiceUnit("");	// 开票单位
                            	cwInvoiceF.setCheckoutUnit("");	// 受检单位
                            	cwInvoiceF.setCheckoutDep("");	// 检验部门
                            	cwInvoiceF.setStatus("SFQX");	// 发票状态（WSY：未使用 SY：使用 ZF：作废 LY：领用 SFQX：收费取消）
                            	cwInvoiceF.setInvoice_id("");	// 开票人id
                            	cwInvoiceF.setInvoice_name("");	// 开票人name
                            	cwInvoiceF.setInvoiceDate(null);	// 开票日期
                            }else {
                            	cwInvoiceF.setStatus("SY");
                            }
                            cwInvoiceFManager.save(cwInvoiceF);
                            logService.setLogs(cwInvoiceF.getId(), "取消收费时，更新发票状态",
                                    "取消收费时，更新发票状态（发票号：" + invoice_no + "）",
                                    user.getId(), user.getName(), new Date(),
                                    request.getRemoteAddr());
                        }
                    }

                    // 3、取消收费，更新业务信息表和收费业务表
                    inspectionPayInfo.setCreated_date(new Date()); // 操作日期
                    inspectionPayInfo.setCreated_by(user.getName()); // 操作人
                    // 取消收费时，更新报检业务信息收费状态为“待收费”等
                    inspectionInfoService.updateInspectionInfo(idArr[i]);

                    // 4、记录日志
                    String type = request.getParameter("info_type");
                    String op_desc = "";
                    if (StringUtil.isNotEmpty(type) && "2".equals(type)) {
                        op_desc = "【已收费】业务，取消收费（发票金额：" + inspectionPayInfo.getPay_received() + "）";
                    } else {
                        op_desc = "【判废免收】业务，取消收费（发票金额：" + inspectionPayInfo.getPay_received() + "）";
                    }
                    logService
                            .setLogs(idArr[i], "取消收费", op_desc, user.getId(),
                                    user.getName(), new Date(),
                                    request.getRemoteAddr());

                    inspectionPayInfoService.delete(inspectionPayInfo.getId()); // 删除收费记录

                } else {
                    // 取消收费时，更新报检业务信息收费状态为“待收费”等
                    inspectionInfoService.updateInspectionInfo(idArr[i]);
                }
            }
            wrapper.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
            wrapper.put("error", true);
            return JsonWrapper.failureWrapper("取消收费失败！");
        }
        return wrapper;
    }

    // 取消收费（承压类设备）
    @RequestMapping(value = "cancelPayCY")
    @ResponseBody
    public HashMap<String, Object> cancelPayCY(HttpServletRequest request,
                                               String ids) {
        CurrentSessionUser user = super.getCurrentUser();
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        try {
            String[] idArr = ids.split(",");
            for (int i = 0; i < idArr.length; i++) {
                InspectionPayInfo inspectionPayInfo = inspectionPayInfoService
                        .get(idArr[i]);
                if (inspectionPayInfo != null) {
                    // 1、当收费方式为“银行转账、现金及转账、转账及pos机刷卡”时，取消收费更新银行转账记录信息表
                    if (isZhuanZhang(inspectionPayInfo.getPay_type())) {
                        List<CwBank2Pay> list = cwBank2PayManager
                                .queryCwBank2Pays(inspectionPayInfo.getId());
                        if (!list.isEmpty()) {
                            for (CwBank2Pay cwBank2Pay : list) {
                                // 4.1、返写银行转账记录信息表
                                CwBankDetail cwBankDetail = cwBankDetailManager
                                        .get(cwBank2Pay.getFk_cw_bank_id());
                                if (cwBank2Pay != null) {
                                    // 剩余金额
                                    cwBankDetail.setRestMoney(TS_Util
                                            .ratioTransform((cwBankDetail
                                                    .getRestMoney()
                                                    .doubleValue() + cwBank2Pay
                                                    .getCur_used_money()
                                                    .doubleValue())));
                                    // 已收金额
                                    cwBankDetail
                                            .setUsedMoney(TS_Util
                                                    .ratioTransform(cwBankDetail
                                                            .getUsedMoney()
                                                            .doubleValue()
                                                            - cwBank2Pay
                                                            .getCur_used_money()
                                                            .doubleValue()));
                                }
                                cwBankDetailManager.save(cwBankDetail);

                                // 4.2、取消收费时，同时删除收费业务与银行转账记录关联信息表
                                cwBank2PayManager.deleteBusiness(cwBank2Pay
                                        .getId());
                            }
                        } else {
                            String fk_cw_bank_ids = inspectionPayInfo
                                    .getFk_cw_bank_id();
                            if (StringUtil.isNotEmpty(fk_cw_bank_ids)) {
                                String[] bank_ids = fk_cw_bank_ids.split(",");
                                for (int j = 0; j < bank_ids.length; j++) {
                                    // 4.1 获取银行转账历史记录
                                    CwBank2Pay cwBank2Pay = cwBank2PayManager
                                            .queryCwBank2Pay(bank_ids[j],
                                                    inspectionPayInfo.getId());

                                    // 4.2、返写银行转账记录信息表
                                    CwBankDetail cwBankDetail = cwBankDetailManager
                                            .get(bank_ids[j]);
                                    if (cwBank2Pay != null) {
                                        // 剩余金额
                                        cwBankDetail
                                                .setRestMoney(TS_Util
                                                        .ratioTransform((cwBankDetail
                                                                .getRestMoney()
                                                                .doubleValue() + cwBank2Pay
                                                                .getCur_used_money()
                                                                .doubleValue())));
                                        // 已收金额
                                        cwBankDetail
                                                .setUsedMoney(TS_Util
                                                        .ratioTransform(cwBankDetail
                                                                .getUsedMoney()
                                                                .doubleValue()
                                                                - cwBank2Pay
                                                                .getCur_used_money()
                                                                .doubleValue()));
                                    }
                                    cwBankDetailManager.save(cwBankDetail);

                                    // 4.3、取消收费时，同时删除收费业务与银行转账记录关联信息表
                                    cwBank2PayManager.deleteBusiness(cwBank2Pay
                                            .getId());
                                }
                            }
                        }
                    }

                    // 2、取消收费，修改发票使用状态
                    String invoice_no = inspectionPayInfo.getInvoice_no();
                    if (StringUtil.isNotEmpty(invoice_no)) {
                        CwInvoiceF cwInvoiceF = cwInvoiceFManager
                                .queryByInvoice_no(invoice_no);
                        // 发票金额
                        cwInvoiceF.setInvoiceMoney(TS_Util
                                .ratioTransform(cwInvoiceF.getInvoiceMoney()
                                        .doubleValue()
                                        - Double.parseDouble(inspectionPayInfo
                                        .getPay_received())));
                        // 若取消收费后金额为0则发票状态更新为收费取消，若不为0则保持除此次收费修改的信息
                        if(cwInvoiceF.getInvoiceMoney().compareTo(new BigDecimal("0"))==0) {
                        	cwInvoiceF.setMoneyType("");	// 交易类型
                        	cwInvoiceF.setInvoiceUnit("");	// 开票单位
                        	cwInvoiceF.setCheckoutUnit("");	// 受检单位
                        	cwInvoiceF.setCheckoutDep("");	// 检验部门
                        	cwInvoiceF.setStatus("SFQX");	// 发票状态（WSY：未使用 SY：使用 ZF：作废 LY：领用 SFQX：收费取消）
                        	cwInvoiceF.setInvoice_id("");	// 开票人id
                        	cwInvoiceF.setInvoice_name("");	// 开票人name
                        	cwInvoiceF.setInvoiceDate(null);	// 开票日期
                        }else {
                        	cwInvoiceF.setStatus("SY");
                        }
                        cwInvoiceFManager.save(cwInvoiceF);
                        logService.setLogs(cwInvoiceF.getId(), "取消收费时，更新发票状态",
                                "取消收费时，更新发票状态（发票号：" + invoice_no + "）",
                                user.getId(), user.getName(), new Date(),
                                request.getRemoteAddr());
                    }

                    // 3、取消收费，更新业务信息表和收费业务表
                    inspectionPayInfo.setPayment_status("99"); // 缴费状态 0 默认 1
                    // 待收费 2 已收费 99
                    // 取消收费
                    inspectionPayInfo.setCreated_date(new Date()); // 操作日期
                    inspectionPayInfo.setCreated_by(user.getName());// 操作人
                    inspectionPayInfoService.save(inspectionPayInfo);

                    // 4、写日志
                    logService
                            .setLogs(inspectionPayInfo.getId(), "取消开票",
                                    "取消开票，还原发票（发票号：" + invoice_no + "）",
                                    user.getId(), user.getName(), new Date(),
                                    request.getRemoteAddr());
                }
            }
            wrapper.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
            wrapper.put("error", true);
            return JsonWrapper.failureWrapper("取消收费失败！");
        }
        return wrapper;
    }

    // 详情
    @RequestMapping(value = "getDetail")
    @ResponseBody
    public HashMap<String, Object> getDetail(HttpServletRequest request,
                                             String id, String status) {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        try {
            // 1、收费业务信息
            InspectionPayInfo inspectionPayInfo = inspectionPayInfoService.doPrint(id);
            CurrentSessionUser user = SecurityUtil.getSecurityUser();
            List<Map<String, Object>> list=lockUserCidService.queryLockCid(user.getId());
    		if(list.size()>0){
    			String cid=list.get(0).get("CID").toString();
    			wrapper.put("cid", cid);
    		}else{
    			wrapper.put("cid", "");
    		}
            /*		inspectionPayInfoService.queryByInspectionInfoID(id); // 报检业务收费信息
            if (inspectionPayInfo == null) {
                inspectionPayInfo = new InspectionPayInfo();
            }
            // 2、检验业务信息
            List<InspectionInfoDTO> inspectionInfoDTOList = new ArrayList<InspectionInfoDTO>();
            String[] ids = id.split(",");
            if (StringUtil.isNotEmpty(inspectionPayInfo.getFk_inspection_info_id())) {
                ids = inspectionPayInfo.getFk_inspection_info_id().split(",");
            }
            // 应收金额
            double advance_fees = 0.00;
            // 实收金额
            double receivable = 0.00;
            for (int i = 0; i < ids.length; i++) { // 报检业务信息
                InspectionInfo inspectionInfo = inspectionInfoService.get(ids[i]);
                if (inspectionInfo != null) {
                    InspectionInfoDTO inspectionInfoDTO = new InspectionInfoDTO();
                    inspectionInfoDTO.setId(inspectionInfo.getId());
                    inspectionInfoDTO
                            .setSn(StringUtil.isNotEmpty(inspectionInfo.getSn()) ? inspectionInfo.getSn() : ""); // 业务流水号
                    inspectionInfoDTO.setCom_id(inspectionInfo.getInspection().getFk_unit_id()); // 报检单位ID
                    inspectionInfoDTO.setCom_name(inspectionInfo.getInspection().getCom_name()); // 报检单位名称
                    inspectionInfoDTO.setCheck_type(inspectionInfo.getInspection().getCheck_type()); // 检验类别
                    if (StringUtil.isNotEmpty(inspectionInfo.getCheck_unit_id())) {
                        Org org = orgManager.get(inspectionInfo.getCheck_unit_id());
                        inspectionInfoDTO
                                .setCheck_department(StringUtil.isNotEmpty(org.getOrgName()) ? org.getOrgName() : ""); // 检验部门
                    } else {
                        inspectionInfoDTO.setCheck_department("");
                    }
                    DeviceDocument deviceDocument = deviceService.get(inspectionInfo.getFk_tsjc_device_document_id());
                    inspectionInfoDTO.setDevice_sort_code(deviceDocument.getDevice_sort_code()); // 设备类别
                    inspectionInfoDTO.setDevice_name(deviceDocument.getDevice_name()); // 设备名称
                    inspectionInfoDTO.setAdvance_fees(
                            inspectionInfo.getAdvance_fees() != null && inspectionInfo.getAdvance_fees() != 0
                                    ? inspectionInfo.getAdvance_fees() : 0); // 应收金额
                    inspectionInfoDTO
                            .setReceivable(inspectionInfo.getReceivable() != null && inspectionInfo.getReceivable() != 0
                                    ? inspectionInfo.getReceivable() : 0); // 实收金额
                    advance_fees += inspectionInfoDTO.getAdvance_fees();
                    //advance_fees = addAmount(advance_fees, inspectionInfoDTO.getAdvance_fees());

                    // 如果存在实收金额，说明报告金额有申请修改过，收费金额以新金额（修改过后的金额，即实收金额）为准
                    if (inspectionInfoDTO.getReceivable() != 0) {
                        receivable += inspectionInfoDTO.getReceivable();
                        //receivable = addAmount(receivable, inspectionInfoDTO.getReceivable());
                    } else {
                        receivable += inspectionInfoDTO.getAdvance_fees();
                        //receivable = addAmount(receivable, inspectionInfoDTO.getAdvance_fees());
                    }

                    inspectionInfoDTO.setReport_sn(
                            StringUtil.isNotEmpty(inspectionInfo.getReport_sn()) ? inspectionInfo.getReport_sn() : ""); // 报告书编号
                    inspectionInfoDTO.setReport_com_name(StringUtil.isNotEmpty(inspectionInfo.getReport_com_name())
                            ? inspectionInfo.getReport_com_name() : ""); // 报告书使用单位
                    inspectionInfoDTO.setAdvance_remark(StringUtil.isNotEmpty(inspectionInfo.getAdvance_remark())
                            ? inspectionInfo.getAdvance_remark() : ""); // 预收金额备注
                    inspectionInfoDTOList.add(inspectionInfoDTO);
                }
            }

            //String ysze = String.valueOf(new BigDecimal(advance_fees).setScale(0, BigDecimal.ROUND_HALF_UP));
            //String ssze = String.valueOf(new BigDecimal(receivable).setScale(0, BigDecimal.ROUND_HALF_UP));
            inspectionPayInfo.setPay_receive(String.valueOf(advance_fees)); // 应收总金额
            inspectionPayInfo.setPay_received(String.valueOf(receivable));  // 实收总金额
            inspectionPayInfo.setInspectionInfoDTOList(inspectionInfoDTOList);

            // 3、银行转账情况
            List<CwBankDTO> cwBankDTOList = new ArrayList<CwBankDTO>();
            if (StringUtil.isNotEmpty(inspectionPayInfo.getFk_cw_bank_id())) {
                String[] bank_ids = inspectionPayInfo.getFk_cw_bank_id().split(",");
                for (int i = 0; i < bank_ids.length; i++) {
                    CwBankDetail cwBankDetail = cwBankDetailManager.get(bank_ids[i]);
                    if (cwBankDetail != null) {
                        CwBankDTO cwBankDTO = new CwBankDTO();
                        BeanUtils.copyProperties(cwBankDTO, cwBankDetail);
                        CwBank2Pay cwBank2Pay = cwBank2PayManager.queryCwBank2Pay(bank_ids[i], id);
                        if (cwBank2Pay != null) {
                            cwBankDTO
                                    .setUsedMoney(TS_Util.ratioTransform(cwBank2Pay.getCur_used_money().doubleValue()));
                            cwBankDTO.setPay_remark(cwBank2Pay.getPay_remark());
                        } else {
                            cwBankDTO.setUsedMoney(
                                    TS_Util.ratioTransform(Double.parseDouble(inspectionPayInfo.getPay_receive())));
                        }
                        cwBankDTOList.add(cwBankDTO);
                    }
                }
            } else {
                List<CwBank2Pay> list = cwBank2PayManager.queryCwBank2Pays(inspectionPayInfo.getId());
                if (!list.isEmpty()) {
                    for (CwBank2Pay cwBank2Pay : list) {
                        if (cwBank2Pay != null) {
                            CwBankDetail cwBankDetail = cwBankDetailManager.get(cwBank2Pay.getFk_cw_bank_id());
                            if (cwBankDetail != null) {
                                CwBankDTO cwBankDTO = new CwBankDTO();
                                BeanUtils.copyProperties(cwBankDTO, cwBankDetail);
                                cwBankDTO.setUsedMoney(
                                        TS_Util.ratioTransform(cwBank2Pay.getCur_used_money().doubleValue()));
                                cwBankDTO.setPay_remark(cwBank2Pay.getPay_remark());
                                cwBankDTOList.add(cwBankDTO);
                            }
                        }
                    }
                }
            }
            inspectionPayInfo.setCwBankDTOList(cwBankDTOList);
*/
            wrapper.put("success", true);
            wrapper.put("data", inspectionPayInfo);
            return wrapper;
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
            return JsonWrapper.failureWrapper("报检业务收费信息查询失败！");
        }
    }


    // 外借收费（外借发票、外借报告和发票）时，获取外借业务详情
    @RequestMapping(value = "getDetail2")
    @ResponseBody
    public HashMap<String, Object> getDetail2(HttpServletRequest request, String id, String borrow_id, String status) {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        try {
            // 1、收费业务信息
            InspectionPayInfo inspectionPayInfo = inspectionPayInfoService.queryByInspectionInfoID(id); // 报检业务收费信息
            if (inspectionPayInfo == null) {
                inspectionPayInfo = new InspectionPayInfo();
            }
            // 2、外借业务信息
            ReportBorrow reportBorrow = reportBorrowService.get(borrow_id);

            // 3、检验业务信息
            List<InspectionInfoDTO> inspectionInfoDTOList = new ArrayList<InspectionInfoDTO>();
            String[] ids = id.split(",");
            if (StringUtil.isNotEmpty(reportBorrow.getFk_inspection_info_id())) {
                ids = reportBorrow.getFk_inspection_info_id().split(",");
            }

            // 应收金额
            float advance_fees = 0.00f;
            // 实收金额
            float receivable = 0.00f;
            for (int i = 0; i < ids.length; i++) { // 报检业务信息
                InspectionInfo inspectionInfo = inspectionInfoService.get(ids[i]);
                if (inspectionInfo != null) {
                    InspectionInfoDTO inspectionInfoDTO = new InspectionInfoDTO();
                    inspectionInfoDTO.setId(inspectionInfo.getId());
                    inspectionInfoDTO
                            .setSn(StringUtil.isNotEmpty(inspectionInfo.getSn()) ? inspectionInfo.getSn() : ""); // 业务流水号
                    inspectionInfoDTO.setCom_id(inspectionInfo.getInspection().getFk_unit_id()); // 报检单位ID
                    inspectionInfoDTO.setCom_name(inspectionInfo.getInspection().getCom_name()); // 报检单位名称
                    inspectionInfoDTO.setCheck_type(inspectionInfo.getInspection().getCheck_type()); // 检验类别
                    if (StringUtil.isNotEmpty(inspectionInfo.getCheck_unit_id())) {
                        Org org = orgManager.get(inspectionInfo.getCheck_unit_id());
                        inspectionInfoDTO
                                .setCheck_department(StringUtil.isNotEmpty(org.getOrgName()) ? org.getOrgName() : ""); // 检验部门
                    } else {
                        inspectionInfoDTO.setCheck_department("");
                    }
                    DeviceDocument deviceDocument = deviceService.get(inspectionInfo.getFk_tsjc_device_document_id());
                    inspectionInfoDTO.setDevice_sort_code(deviceDocument.getDevice_sort_code()); // 设备类别
                    inspectionInfoDTO.setDevice_name(deviceDocument.getDevice_name()); // 设备名称
                    inspectionInfoDTO.setAdvance_fees(
                            inspectionInfo.getAdvance_fees() != null && inspectionInfo.getAdvance_fees() != 0
                                    ? inspectionInfo.getAdvance_fees() : 0); // 应收金额
                    inspectionInfoDTO
                            .setReceivable(inspectionInfo.getReceivable() != null && inspectionInfo.getReceivable() != 0
                                    ? inspectionInfo.getReceivable() : 0); // 实收金额
                    advance_fees += inspectionInfoDTO.getAdvance_fees();

                    // 如果存在实收金额，说明报告金额有申请修改过，收费金额以新金额（修改过后的金额，即实收金额）为准
                    if (inspectionInfoDTO.getReceivable() != 0) {
                        receivable += inspectionInfoDTO.getReceivable();
                    } else {
                        receivable += inspectionInfoDTO.getAdvance_fees();
                    }

                    inspectionInfoDTO.setReport_sn(
                            StringUtil.isNotEmpty(inspectionInfo.getReport_sn()) ? inspectionInfo.getReport_sn() : ""); // 报告书编号
                    inspectionInfoDTO.setReport_com_name(StringUtil.isNotEmpty(inspectionInfo.getReport_com_name())
                            ? inspectionInfo.getReport_com_name() : ""); // 报告书使用单位
                    inspectionInfoDTO.setAdvance_remark(StringUtil.isNotEmpty(inspectionInfo.getAdvance_remark())
                            ? inspectionInfo.getAdvance_remark() : ""); // 预收金额备注
                    inspectionInfoDTOList.add(inspectionInfoDTO);
                }
            }
            inspectionPayInfo.setPay_receive(String.valueOf(advance_fees)); // 应收总金额
            inspectionPayInfo.setPay_received(String.valueOf(receivable)); // 实收总金额
            inspectionPayInfo.setInspectionInfoDTOList(inspectionInfoDTOList);

            // 4、银行转账情况
            List<CwBankDTO> cwBankDTOList = new ArrayList<CwBankDTO>();
            if (StringUtil.isNotEmpty(inspectionPayInfo.getFk_cw_bank_id())) {
                String[] bank_ids = inspectionPayInfo.getFk_cw_bank_id().split(",");
                for (int i = 0; i < bank_ids.length; i++) {
                    CwBankDetail cwBankDetail = cwBankDetailManager.get(bank_ids[i]);
                    if (cwBankDetail != null) {
                        CwBankDTO cwBankDTO = new CwBankDTO();
                        BeanUtils.copyProperties(cwBankDTO, cwBankDetail);
                        CwBank2Pay cwBank2Pay = cwBank2PayManager.queryCwBank2Pay(bank_ids[i], id);
                        if (cwBank2Pay != null) {
                            cwBankDTO
                                    .setUsedMoney(TS_Util.ratioTransform(cwBank2Pay.getCur_used_money().doubleValue()));
                            cwBankDTO.setPay_remark(cwBank2Pay.getPay_remark());
                        } else {
                            cwBankDTO.setUsedMoney(
                                    TS_Util.ratioTransform(Double.parseDouble(inspectionPayInfo.getPay_receive())));
                        }
                        cwBankDTOList.add(cwBankDTO);
                    }
                }
            } else {
                List<CwBank2Pay> list = cwBank2PayManager.queryCwBank2Pays(inspectionPayInfo.getId());
                if (!list.isEmpty()) {
                    for (CwBank2Pay cwBank2Pay : list) {
                        if (cwBank2Pay != null) {
                            CwBankDetail cwBankDetail = cwBankDetailManager.get(cwBank2Pay.getFk_cw_bank_id());
                            if (cwBankDetail != null) {
                                CwBankDTO cwBankDTO = new CwBankDTO();
                                BeanUtils.copyProperties(cwBankDTO, cwBankDetail);
                                cwBankDTO.setUsedMoney(
                                        TS_Util.ratioTransform(cwBank2Pay.getCur_used_money().doubleValue()));
                                cwBankDTO.setPay_remark(cwBank2Pay.getPay_remark());
                                cwBankDTOList.add(cwBankDTO);
                            }
                        }
                    }
                }
            }
            inspectionPayInfo.setCwBankDTOList(cwBankDTOList);

            wrapper.put("success", true);
            wrapper.put("data", inspectionPayInfo);
            wrapper.put("invoice_no", reportBorrow.getInvoice_no());
            wrapper.put("info_id", reportBorrow.getFk_inspection_info_id());
            return wrapper;
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
            return JsonWrapper.failureWrapper("报检业务收费信息查询失败！");
        }
    }

    // 外借收费（外借报告）时，获取外借业务详情
    @RequestMapping(value = "getDetail3")
    @ResponseBody
    public HashMap<String, Object> getDetail3(HttpServletRequest request, String id, String status) {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        try {
            // 1、收费业务信息
            InspectionPayInfo inspectionPayInfo = inspectionPayInfoService.queryByInspectionInfoID(id); // 报检业务收费信息
            if (inspectionPayInfo == null) {
                inspectionPayInfo = new InspectionPayInfo();
            }

            // 2、检验业务信息
            List<InspectionInfoDTO> inspectionInfoDTOList = new ArrayList<InspectionInfoDTO>();
            String[] ids = id.split(",");

            // 应收金额
            float advance_fees = 0.00f;
            // 实收金额
            float receivable = 0.00f;
            for (int i = 0; i < ids.length; i++) { // 报检业务信息
                InspectionInfo inspectionInfo = inspectionInfoService.get(ids[i]);
                if (inspectionInfo != null) {
                    InspectionInfoDTO inspectionInfoDTO = new InspectionInfoDTO();
                    inspectionInfoDTO.setId(inspectionInfo.getId());
                    inspectionInfoDTO
                            .setSn(StringUtil.isNotEmpty(inspectionInfo.getSn()) ? inspectionInfo.getSn() : ""); // 业务流水号
                    inspectionInfoDTO.setCom_id(inspectionInfo.getInspection().getFk_unit_id()); // 报检单位ID
                    inspectionInfoDTO.setCom_name(inspectionInfo.getInspection().getCom_name()); // 报检单位名称
                    inspectionInfoDTO.setCheck_type(inspectionInfo.getInspection().getCheck_type()); // 检验类别
                    if (StringUtil.isNotEmpty(inspectionInfo.getCheck_unit_id())) {
                        Org org = orgManager.get(inspectionInfo.getCheck_unit_id());
                        inspectionInfoDTO
                                .setCheck_department(StringUtil.isNotEmpty(org.getOrgName()) ? org.getOrgName() : ""); // 检验部门
                    } else {
                        inspectionInfoDTO.setCheck_department("");
                    }
                    DeviceDocument deviceDocument = deviceService.get(inspectionInfo.getFk_tsjc_device_document_id());
                    inspectionInfoDTO.setDevice_sort_code(deviceDocument.getDevice_sort_code()); // 设备类别
                    inspectionInfoDTO.setDevice_name(deviceDocument.getDevice_name()); // 设备名称
                    inspectionInfoDTO.setAdvance_fees(
                            inspectionInfo.getAdvance_fees() != null && inspectionInfo.getAdvance_fees() != 0
                                    ? inspectionInfo.getAdvance_fees() : 0); // 应收金额
                    inspectionInfoDTO
                            .setReceivable(inspectionInfo.getReceivable() != null && inspectionInfo.getReceivable() != 0
                                    ? inspectionInfo.getReceivable() : 0); // 实收金额
                    advance_fees += inspectionInfoDTO.getAdvance_fees();

                    // 如果存在实收金额，说明报告金额有申请修改过，收费金额以新金额（修改过后的金额，即实收金额）为准
                    if (inspectionInfoDTO.getReceivable() != 0) {
                        receivable += inspectionInfoDTO.getReceivable();
                    } else {
                        receivable += inspectionInfoDTO.getAdvance_fees();
                    }

                    inspectionInfoDTO.setReport_sn(
                            StringUtil.isNotEmpty(inspectionInfo.getReport_sn()) ? inspectionInfo.getReport_sn() : ""); // 报告书编号
                    inspectionInfoDTO.setReport_com_name(StringUtil.isNotEmpty(inspectionInfo.getReport_com_name())
                            ? inspectionInfo.getReport_com_name() : ""); // 报告书使用单位
                    inspectionInfoDTO.setAdvance_remark(StringUtil.isNotEmpty(inspectionInfo.getAdvance_remark())
                            ? inspectionInfo.getAdvance_remark() : ""); // 预收金额备注
                    inspectionInfoDTOList.add(inspectionInfoDTO);
                }
            }
            inspectionPayInfo.setPay_receive(String.valueOf(advance_fees)); // 应收总金额
            inspectionPayInfo.setPay_received(String.valueOf(receivable)); // 实收总金额
            inspectionPayInfo.setInspectionInfoDTOList(inspectionInfoDTOList);

            // 3、银行转账情况
            List<CwBankDTO> cwBankDTOList = new ArrayList<CwBankDTO>();
            if (StringUtil.isNotEmpty(inspectionPayInfo.getFk_cw_bank_id())) {
                String[] bank_ids = inspectionPayInfo.getFk_cw_bank_id().split(",");
                for (int i = 0; i < bank_ids.length; i++) {
                    CwBankDetail cwBankDetail = cwBankDetailManager.get(bank_ids[i]);
                    if (cwBankDetail != null) {
                        CwBankDTO cwBankDTO = new CwBankDTO();
                        BeanUtils.copyProperties(cwBankDTO, cwBankDetail);
                        CwBank2Pay cwBank2Pay = cwBank2PayManager.queryCwBank2Pay(bank_ids[i], id);
                        if (cwBank2Pay != null) {
                            cwBankDTO
                                    .setUsedMoney(TS_Util.ratioTransform(cwBank2Pay.getCur_used_money().doubleValue()));
                            cwBankDTO.setPay_remark(cwBank2Pay.getPay_remark());
                        } else {
                            cwBankDTO.setUsedMoney(
                                    TS_Util.ratioTransform(Double.parseDouble(inspectionPayInfo.getPay_receive())));
                        }
                        cwBankDTOList.add(cwBankDTO);
                    }
                }
            } else {
                List<CwBank2Pay> list = cwBank2PayManager.queryCwBank2Pays(inspectionPayInfo.getId());
                if (!list.isEmpty()) {
                    for (CwBank2Pay cwBank2Pay : list) {
                        if (cwBank2Pay != null) {
                            CwBankDetail cwBankDetail = cwBankDetailManager.get(cwBank2Pay.getFk_cw_bank_id());
                            if (cwBankDetail != null) {
                                CwBankDTO cwBankDTO = new CwBankDTO();
                                BeanUtils.copyProperties(cwBankDTO, cwBankDetail);
                                cwBankDTO.setUsedMoney(
                                        TS_Util.ratioTransform(cwBank2Pay.getCur_used_money().doubleValue()));
                                cwBankDTO.setPay_remark(cwBank2Pay.getPay_remark());
                                cwBankDTOList.add(cwBankDTO);
                            }
                        }
                    }
                }
            }
            inspectionPayInfo.setCwBankDTOList(cwBankDTOList);

            wrapper.put("success", true);
            wrapper.put("data", inspectionPayInfo);
            wrapper.put("info_id", id);
            return wrapper;
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
            return JsonWrapper.failureWrapper("报检业务收费信息查询失败！");
        }
    }

    // 详情
    @RequestMapping(value = "getCYDetail")
    @ResponseBody
    public HashMap<String, Object> getCYDetail(HttpServletRequest request,
                                               String id, String status) {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        try {
            // 1、收费业务信息
            InspectionPayInfo inspectionPayInfo = inspectionPayInfoService
                    .get(id); // 收费信息
            if (inspectionPayInfo == null) {
                inspectionPayInfo = new InspectionPayInfo();
            }

            // 2、银行转账情况
            List<CwBankDTO> cwBankDTOList = new ArrayList<CwBankDTO>();
            if (StringUtil.isNotEmpty(inspectionPayInfo.getFk_cw_bank_id())) {
                String[] bank_ids = inspectionPayInfo.getFk_cw_bank_id().split(
                        ",");
                for (int i = 0; i < bank_ids.length; i++) {
                    CwBankDetail cwBankDetail = cwBankDetailManager
                            .get(bank_ids[i]);
                    if (cwBankDetail != null) {
                        CwBankDTO cwBankDTO = new CwBankDTO();
                        BeanUtils.copyProperties(cwBankDTO, cwBankDetail);
                        CwBank2Pay cwBank2Pay = cwBank2PayManager
                                .queryCwBank2Pay(bank_ids[i], id);
                        if (cwBank2Pay != null) {
                            cwBankDTO
                                    .setUsedMoney(TS_Util
                                            .ratioTransform(cwBank2Pay
                                                    .getCur_used_money()
                                                    .doubleValue()));
                            cwBankDTO.setPay_remark(cwBank2Pay.getPay_remark());
                        }
                        cwBankDTOList.add(cwBankDTO);
                    }
                }
            } else {
                List<CwBank2Pay> list = cwBank2PayManager
                        .queryCwBank2Pays(inspectionPayInfo.getId());
                if (!list.isEmpty()) {
                    for (CwBank2Pay cwBank2Pay : list) {
                        if (cwBank2Pay != null) {
                            CwBankDetail cwBankDetail = cwBankDetailManager
                                    .get(cwBank2Pay.getFk_cw_bank_id());
                            if (cwBankDetail != null) {
                                CwBankDTO cwBankDTO = new CwBankDTO();
                                BeanUtils.copyProperties(cwBankDTO,
                                        cwBankDetail);
                                cwBankDTO.setUsedMoney(TS_Util
                                        .ratioTransform(cwBank2Pay
                                                .getCur_used_money()
                                                .doubleValue()));
                                cwBankDTO.setPay_remark(cwBank2Pay
                                        .getPay_remark());
                                cwBankDTOList.add(cwBankDTO);
                            }
                        }
                    }
                }
            }
            inspectionPayInfo.setCwBankDTOList(cwBankDTOList);

            wrapper.put("success", true);
            wrapper.put("data", inspectionPayInfo);
            return wrapper;
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
            return JsonWrapper.failureWrapper("承压收费信息查询失败！");
        }
    }

    // 批量导入初始化
    @RequestMapping(value = "initBatch")
    @ResponseBody
    public HashMap<String, Object> initBatch(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        try {
            HashMap<String, Object> map = new HashMap<String, Object>();
            try {
                InspectionPayInfoDTO inspectionPayInfoDTO = new InspectionPayInfoDTO();
                // 查询制造监督检验明细表
                List<InspectionPayInfo> list = new ArrayList<InspectionPayInfo>();
                inspectionPayInfoDTO.setInspectionPayInfo(list);
                map.put("data", inspectionPayInfoDTO);
                map.put("inspectionPayInfo", list);
                map.put("success", true);
            } catch (Exception e) {
                e.printStackTrace();
                log.debug(e.toString());
            }
            return map;
        } catch (Exception e) {
            log.debug(e.toString());
            return JsonWrapper.failureWrapperMsg("读取报告录入信息失败！");
        }
    }

    // 根据收费业务ID查询使用银行转账记录
    @RequestMapping(value = "getBankInfos")
    @ResponseBody
    public HashMap<String, Object> getBankInfos(HttpServletRequest request,
                                                String id) {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        try {
            // 1、收费业务信息
            InspectionPayInfo inspectionPayInfo = inspectionPayInfoService
                    .get(id); // 收费信息
            // 2、银行转账情况
            List<CwBankDTO> cwBankDTOList = new ArrayList<CwBankDTO>();
            if (StringUtil.isNotEmpty(inspectionPayInfo.getFk_cw_bank_id())) {
                String[] bank_ids = inspectionPayInfo.getFk_cw_bank_id().split(
                        ",");
                for (int i = 0; i < bank_ids.length; i++) {
                    CwBankDetail cwBankDetail = cwBankDetailManager
                            .get(bank_ids[i]);
                    if (cwBankDetail != null) {
                        CwBankDTO cwBankDTO = new CwBankDTO();
                        BeanUtils.copyProperties(cwBankDTO, cwBankDetail);
                        CwBank2Pay cwBank2Pay = cwBank2PayManager
                                .queryCwBank2Pay(bank_ids[i], id);
                        if (cwBank2Pay != null) {
                            if (cwBank2Pay
                                    .getCur_used_money() != null) {
                                cwBankDTO
                                        .setUsedMoney(TS_Util
                                                .ratioTransform(cwBank2Pay
                                                        .getCur_used_money()
                                                        .doubleValue()));
                            } else {
                                cwBankDTO
                                        .setUsedMoney(TS_Util
                                                .ratioTransform(0));
                            }
                            cwBankDTO.setPay_remark(cwBank2Pay.getPay_remark());
                        }
                        cwBankDTOList.add(cwBankDTO);
                    }
                }
            } else {
                List<CwBank2Pay> list = cwBank2PayManager
                        .queryCwBank2Pays(inspectionPayInfo.getId());
                if (!list.isEmpty()) {
                    for (CwBank2Pay cwBank2Pay : list) {
                        if (cwBank2Pay != null) {
                            CwBankDetail cwBankDetail = cwBankDetailManager
                                    .get(cwBank2Pay.getFk_cw_bank_id());
                            if (cwBankDetail != null) {
                                CwBankDTO cwBankDTO = new CwBankDTO();
                                BeanUtils.copyProperties(cwBankDTO,
                                        cwBankDetail);
                                if (cwBank2Pay
                                        .getCur_used_money() != null) {
                                    cwBankDTO.setUsedMoney(TS_Util
                                            .ratioTransform(cwBank2Pay
                                                    .getCur_used_money()
                                                    .doubleValue()));
                                } else {
                                    cwBankDTO.setUsedMoney(TS_Util
                                            .ratioTransform(0));
                                }
                                cwBankDTO.setPay_remark(cwBank2Pay
                                        .getPay_remark());
                                cwBankDTOList.add(cwBankDTO);
                            }
                        }
                    }
                }
            }
            wrapper.put("success", true);
            wrapper.put("data", cwBankDTOList);
            return wrapper;
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
            return JsonWrapper.failureWrapper("银行转账信息查询失败！");
        }
    }

    // 根据收费业务ID查询使用银行转账记录
    @RequestMapping(value = "getBankInfos2")
    @ResponseBody
    public HashMap<String, Object> getBankInfos2(HttpServletRequest request,
                                                 String id) {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        try {
            // 1、收费业务信息
            //InspectionPayInfo inspectionPayInfo = inspectionPayInfoService.get(id); // 收费信息
            InspectionPayInfo inspectionPayInfo = inspectionPayInfoService
                    .queryByInspectionInfoID(id); // 报检业务收费信息

            // 2、银行转账情况
            List<CwBankDTO> cwBankDTOList = new ArrayList<CwBankDTO>();
            if (inspectionPayInfo != null) {
                if (StringUtil.isNotEmpty(inspectionPayInfo.getFk_cw_bank_id()) && StringUtil.isNotEmpty(inspectionPayInfo.getId())) {
                    String[] bank_ids = inspectionPayInfo.getFk_cw_bank_id().split(
                            ",");
                    for (int i = 0; i < bank_ids.length; i++) {
                        CwBankDetail cwBankDetail = cwBankDetailManager
                                .get(bank_ids[i]);
                        if (cwBankDetail != null) {
                            CwBankDTO cwBankDTO = new CwBankDTO();
                            BeanUtils.copyProperties(cwBankDTO, cwBankDetail);
                            CwBank2Pay cwBank2Pay = cwBank2PayManager
                                    .queryCwBank2Pay(bank_ids[i], inspectionPayInfo.getId());
                            if (cwBank2Pay != null) {
                                if (cwBank2Pay
                                        .getCur_used_money() != null) {
                                    cwBankDTO
                                            .setUsedMoney(TS_Util
                                                    .ratioTransform(cwBank2Pay
                                                            .getCur_used_money()
                                                            .doubleValue()));
                                } else {
                                    cwBankDTO
                                            .setUsedMoney(TS_Util
                                                    .ratioTransform(0));
                                }
                                cwBankDTO.setPay_remark(cwBank2Pay.getPay_remark());
                            }
                            cwBankDTOList.add(cwBankDTO);
                        }
                    }
                } else {
                    if (StringUtil.isNotEmpty(inspectionPayInfo.getId())) {
                        List<CwBank2Pay> list = cwBank2PayManager
                                .queryCwBank2Pays(inspectionPayInfo.getId());
                        if (!list.isEmpty()) {
                            for (CwBank2Pay cwBank2Pay : list) {
                                if (cwBank2Pay != null) {
                                    CwBankDetail cwBankDetail = cwBankDetailManager
                                            .get(cwBank2Pay.getFk_cw_bank_id());
                                    if (cwBankDetail != null) {
                                        CwBankDTO cwBankDTO = new CwBankDTO();
                                        BeanUtils.copyProperties(cwBankDTO,
                                                cwBankDetail);
                                        if (cwBank2Pay
                                                .getCur_used_money() != null) {
                                            cwBankDTO.setUsedMoney(TS_Util
                                                    .ratioTransform(cwBank2Pay
                                                            .getCur_used_money()
                                                            .doubleValue()));
                                        } else {
                                            cwBankDTO.setUsedMoney(TS_Util
                                                    .ratioTransform(0));
                                        }
                                        cwBankDTO.setPay_remark(cwBank2Pay
                                                .getPay_remark());
                                        cwBankDTOList.add(cwBankDTO);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            wrapper.put("success", true);
            wrapper.put("data", cwBankDTOList);
            return wrapper;
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
            return JsonWrapper.failureWrapper("银行转账信息查询失败！");
        }
    }

    // 缴费结算单打印
    @RequestMapping(value = "doPrint")
    public ModelAndView doPrint(HttpServletRequest request, String id) {
        ModelAndView mav = new ModelAndView("app/payment/payment_print");
        try {
          InspectionPayInfo inspectionPayInfo = inspectionPayInfoService.doPrint(id);
            /*      queryByInspectionInfoID(id); // 报检业务收费信息
            if (inspectionPayInfo == null) {
                inspectionPayInfo = new InspectionPayInfo();
            }
            List<InspectionInfoDTO> inspectionInfoDTOList = new ArrayList<InspectionInfoDTO>(); // 业务收费信息列表
            String[] ids = id.split(",");
            if (StringUtil.isNotEmpty(inspectionPayInfo
                    .getFk_inspection_info_id())) {
                ids = inspectionPayInfo.getFk_inspection_info_id().split(",");
                String idss = inspectionPayInfo.getFk_inspection_info_id().replace(",","','");
                
                String hql = "select i.id,id.sn,i.inspection.fk_unit_id,i.i.inspection.com_name,i.i.inspection.check_type,o.orgName "
                		+" d.device_sort_code,d.device_registration_code,d.device_name,i.advance_fees,i.receivable "
                		+" i.report_sn ,i.report_com_name,i.advance_remark "
                		+ " from InspectionInfo i,DeviceDocument d,Org o where d.id = i.fk_tsjc_device_document_id  and o.id(+) = i.inspection.fk_unit_id "
                		+ " and i.id in ('"+idss+"')";
                
                
                List<Object> list = inspectionPayInfoService.
            }
            float advance_fees = 0.00f;
            float receivable = 0.00f;
            for (int i = 0; i < ids.length; i++) { // 报检业务信息
                InspectionInfo inspectionInfo = inspectionInfoService
                        .get(ids[i]);
                if (inspectionInfo != null) {
                    InspectionInfoDTO inspectionInfoDTO = new InspectionInfoDTO();
                    inspectionInfoDTO.setId(inspectionInfo.getId());
                    inspectionInfoDTO
                            .setSn(StringUtil.isNotEmpty(inspectionInfo.getSn()) ? inspectionInfo
                                    .getSn() : ""); // 业务流水号
                    inspectionInfoDTO.setCom_id(inspectionInfo.getInspection()
                            .getFk_unit_id()); // 报检单位ID
                    inspectionInfoDTO.setCom_name(inspectionInfo
                            .getInspection().getCom_name()); // 报检单位名称
                    inspectionInfoDTO.setCheck_type(inspectionInfo
                            .getInspection().getCheck_type()); // 检验类别
                    if (StringUtil
                            .isNotEmpty(inspectionInfo.getCheck_unit_id())) {
                        Org org = orgManager.get(inspectionInfo
                                .getCheck_unit_id());
                        inspectionInfoDTO.setCheck_department(StringUtil
                                .isNotEmpty(org.getOrgName()) ? org
                                .getOrgName() : ""); // 检验部门
                    } else {
                        inspectionInfoDTO.setCheck_department("");
                    }

                    // 获取设备信息
                    DeviceDocument deviceDocument = deviceService
                            .get(inspectionInfo.getFk_tsjc_device_document_id());
                    if (deviceDocument != null) {
                        inspectionInfoDTO.setDevice_sort_code(deviceDocument
                                .getDevice_sort_code()); // 设备类别
                        inspectionInfoDTO.setDevice_registration_code(deviceDocument.getDevice_registration_code());
                        inspectionInfoDTO.setDevice_name(deviceDocument
                                .getDevice_name()); // 设备名称
                    }

                    inspectionInfoDTO
                            .setAdvance_fees(inspectionInfo.getAdvance_fees() != null
                                    && inspectionInfo.getAdvance_fees() != 0 ? inspectionInfo
                                    .getAdvance_fees() : 0); // 应收金额
                    inspectionInfoDTO
                            .setReceivable(inspectionInfo.getReceivable() != null
                                    && inspectionInfo.getReceivable() != 0 ? inspectionInfo
                                    .getReceivable() : 0); // 实收金额
                    advance_fees += inspectionInfoDTO.getAdvance_fees();
                    receivable += inspectionInfoDTO.getReceivable();
                    inspectionInfoDTO
                            .setReport_sn(StringUtil.isNotEmpty(inspectionInfo
                                    .getReport_sn()) ? inspectionInfo
                                    .getReport_sn() : ""); // 报告书编号
                    inspectionInfoDTO
                            .setReport_com_name(StringUtil
                                    .isNotEmpty(inspectionInfo
                                            .getReport_com_name()) ? inspectionInfo
                                    .getReport_com_name() : ""); // 报告书使用单位
                    inspectionInfoDTO
                            .setAdvance_remark(StringUtil
                                    .isNotEmpty(inspectionInfo
                                            .getAdvance_remark()) ? inspectionInfo
                                    .getAdvance_remark() : ""); // 预收金额备注
                    inspectionInfoDTOList.add(inspectionInfoDTO);
                }
            }
            inspectionPayInfo.setPay_receive(String.valueOf(advance_fees)); // 应收总金额
            inspectionPayInfo.setInspectionInfoDTOList(inspectionInfoDTOList);

            // 银行转账
            List<CwBankDTO> cwBankDTOList = new ArrayList<CwBankDTO>();
            if (StringUtil.isNotEmpty(inspectionPayInfo.getFk_cw_bank_id())) {
                String[] bank_ids = inspectionPayInfo.getFk_cw_bank_id().split(
                        ",");
                for (int i = 0; i < bank_ids.length; i++) {
                    CwBankDetail cwBankDetail = cwBankDetailManager
                            .get(bank_ids[i]);
                    if (cwBankDetail != null) {
                        CwBankDTO cwBankDTO = new CwBankDTO();
                        BeanUtils.copyProperties(cwBankDTO, cwBankDetail);
                        CwBank2Pay cwBank2Pay = cwBank2PayManager
                                .queryCwBank2Pay(bank_ids[i], id);
                        if (cwBank2Pay != null) {
                            cwBankDTO
                                    .setUsedMoney(TS_Util
                                            .ratioTransform(cwBank2Pay
                                                    .getCur_used_money()
                                                    .doubleValue()));
                            cwBankDTO.setPay_remark(cwBank2Pay.getPay_remark());
                        }
                        cwBankDTOList.add(cwBankDTO);
                    }
                }
            } else {
                List<CwBank2Pay> list = cwBank2PayManager
                        .queryCwBank2Pays(inspectionPayInfo.getId());
                if (!list.isEmpty()) {
                    for (CwBank2Pay cwBank2Pay : list) {
                        if (cwBank2Pay != null) {
                            CwBankDetail cwBankDetail = cwBankDetailManager
                                    .get(cwBank2Pay.getFk_cw_bank_id());
                            if (cwBankDetail != null) {
                                CwBankDTO cwBankDTO = new CwBankDTO();
                                BeanUtils.copyProperties(cwBankDTO,
                                        cwBankDetail);
                                cwBankDTO.setUsedMoney(TS_Util
                                        .ratioTransform(cwBank2Pay
                                                .getCur_used_money()
                                                .doubleValue()));
                                cwBankDTO.setPay_remark(cwBank2Pay
                                        .getPay_remark());
                                cwBankDTOList.add(cwBankDTO);
                            }
                        }
                    }
                }
            }
            inspectionPayInfo.setCwBankDTOList(cwBankDTOList);

            String pay_received = inspectionPayInfo.getPay_received();
            if (StringUtil.isNotEmpty(pay_received)) {
                Double pay = Double.parseDouble(pay_received);
                inspectionPayInfo.setPay_received(String.valueOf(pay));
            }
            String pos = inspectionPayInfo.getPos();
            if (StringUtil.isNotEmpty(pos)) {
                Double pos_money = Double.parseDouble(pos);
                inspectionPayInfo.setPos(String.valueOf(pos_money));
            }
            String cash_pay = inspectionPayInfo.getCash_pay();
            if (StringUtil.isNotEmpty(cash_pay)) {
                Double cash = Double.parseDouble(cash_pay);
                inspectionPayInfo.setCash_pay(String.valueOf(cash));
            }*/
            //String zz = inspectionPayInfo.getRemark();
            //if(StringUtil.isNotEmpty(zz)){
            //Double trans = Double.parseDouble(zz);
            //inspectionPayInfo.setRemark(String.valueOf(trans));
            //}
            request.getSession().setAttribute("inspectionPayInfo",
                    inspectionPayInfo);
            ModelMap modelMap = new ModelMap();
            modelMap.put("inspectionPayInfo", inspectionPayInfo);
            mav.getModelMap().addAllAttributes(modelMap);
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
        }

        return mav;
    }

    // 缴费结算单打印
    @RequestMapping(value = "getPaymentSheet")
    @ResponseBody
    public HashMap<String, Object> getPaymentSheet(HttpServletRequest request, @RequestBody Map<String, Object> params) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            String id = (String) params.get("id");
            InspectionPayInfo inspectionPayInfo = inspectionPayInfoService
                    .queryByInspectionInfoID(id); // 报检业务收费信息
            if (inspectionPayInfo == null) {
                inspectionPayInfo = new InspectionPayInfo();
            }
            List<InspectionInfoDTO> inspectionInfoDTOList = new ArrayList<InspectionInfoDTO>(); // 业务收费信息列表
            String[] ids = id.split(",");
            if (StringUtil.isNotEmpty(inspectionPayInfo
                    .getFk_inspection_info_id())) {
                ids = inspectionPayInfo.getFk_inspection_info_id().split(",");
            }
            float advance_fees = 0.00f;
            float receivable = 0.00f;
            for (int i = 0; i < ids.length; i++) { // 报检业务信息
                InspectionInfo inspectionInfo = inspectionInfoService
                        .get(ids[i]);
                if (inspectionInfo != null) {
                    InspectionInfoDTO inspectionInfoDTO = new InspectionInfoDTO();
                    inspectionInfoDTO.setId(inspectionInfo.getId());
                    inspectionInfoDTO
                            .setSn(StringUtil.isNotEmpty(inspectionInfo.getSn()) ? inspectionInfo
                                    .getSn() : ""); // 业务流水号
                    inspectionInfoDTO.setCom_id(inspectionInfo.getInspection()
                            .getFk_unit_id()); // 报检单位ID
                    inspectionInfoDTO.setCom_name(inspectionInfo
                            .getInspection().getCom_name()); // 报检单位名称
                    inspectionInfoDTO.setCheck_type(inspectionInfo
                            .getInspection().getCheck_type()); // 检验类别
                    if (StringUtil
                            .isNotEmpty(inspectionInfo.getCheck_unit_id())) {
                        Org org = orgManager.get(inspectionInfo
                                .getCheck_unit_id());
                        inspectionInfoDTO.setCheck_department(StringUtil
                                .isNotEmpty(org.getOrgName()) ? org
                                .getOrgName() : ""); // 检验部门
                    } else {
                        inspectionInfoDTO.setCheck_department("");
                    }

                    // 获取设备信息
                    DeviceDocument deviceDocument = deviceService
                            .get(inspectionInfo.getFk_tsjc_device_document_id());
                    if (deviceDocument != null) {
                        inspectionInfoDTO.setDevice_sort_code(deviceDocument
                                .getDevice_sort_code()); // 设备类别
                        inspectionInfoDTO.setDevice_registration_code(deviceDocument.getDevice_registration_code());
                        inspectionInfoDTO.setDevice_name(deviceDocument
                                .getDevice_name()); // 设备名称
                    }

                    inspectionInfoDTO
                            .setAdvance_fees(inspectionInfo.getAdvance_fees() != null
                                    && inspectionInfo.getAdvance_fees() != 0 ? inspectionInfo
                                    .getAdvance_fees() : 0); // 应收金额
                    inspectionInfoDTO
                            .setReceivable(inspectionInfo.getReceivable() != null
                                    && inspectionInfo.getReceivable() != 0 ? inspectionInfo
                                    .getReceivable() : 0); // 实收金额
                    advance_fees += inspectionInfoDTO.getAdvance_fees();
                    receivable += inspectionInfoDTO.getReceivable();
                    inspectionInfoDTO
                            .setReport_sn(StringUtil.isNotEmpty(inspectionInfo
                                    .getReport_sn()) ? inspectionInfo
                                    .getReport_sn() : ""); // 报告书编号
                    inspectionInfoDTO
                            .setReport_com_name(StringUtil
                                    .isNotEmpty(inspectionInfo
                                            .getReport_com_name()) ? inspectionInfo
                                    .getReport_com_name() : ""); // 报告书使用单位
                    inspectionInfoDTO
                            .setAdvance_remark(StringUtil
                                    .isNotEmpty(inspectionInfo
                                            .getAdvance_remark()) ? inspectionInfo
                                    .getAdvance_remark() : ""); // 预收金额备注
                    inspectionInfoDTOList.add(inspectionInfoDTO);
                }
            }
            inspectionPayInfo.setPay_receive(String.valueOf(advance_fees)); // 应收总金额
            inspectionPayInfo.setInspectionInfoDTOList(inspectionInfoDTOList);

            // 银行转账
            List<CwBankDTO> cwBankDTOList = new ArrayList<CwBankDTO>();
            if (StringUtil.isNotEmpty(inspectionPayInfo.getFk_cw_bank_id())) {
                String[] bank_ids = inspectionPayInfo.getFk_cw_bank_id().split(
                        ",");
                for (int i = 0; i < bank_ids.length; i++) {
                    CwBankDetail cwBankDetail = cwBankDetailManager
                            .get(bank_ids[i]);
                    if (cwBankDetail != null) {
                        CwBankDTO cwBankDTO = new CwBankDTO();
                        BeanUtils.copyProperties(cwBankDTO, cwBankDetail);
                        CwBank2Pay cwBank2Pay = cwBank2PayManager
                                .queryCwBank2Pay(bank_ids[i], id);
                        if (cwBank2Pay != null) {
                            cwBankDTO
                                    .setUsedMoney(TS_Util
                                            .ratioTransform(cwBank2Pay
                                                    .getCur_used_money()
                                                    .doubleValue()));
                            cwBankDTO.setPay_remark(cwBank2Pay.getPay_remark());
                        }
                        cwBankDTOList.add(cwBankDTO);
                    }
                }
            } else {
                List<CwBank2Pay> list = cwBank2PayManager
                        .queryCwBank2Pays(inspectionPayInfo.getId());
                if (!list.isEmpty()) {
                    for (CwBank2Pay cwBank2Pay : list) {
                        if (cwBank2Pay != null) {
                            CwBankDetail cwBankDetail = cwBankDetailManager
                                    .get(cwBank2Pay.getFk_cw_bank_id());
                            if (cwBankDetail != null) {
                                CwBankDTO cwBankDTO = new CwBankDTO();
                                BeanUtils.copyProperties(cwBankDTO,
                                        cwBankDetail);
                                cwBankDTO.setUsedMoney(TS_Util
                                        .ratioTransform(cwBank2Pay
                                                .getCur_used_money()
                                                .doubleValue()));
                                cwBankDTO.setPay_remark(cwBank2Pay
                                        .getPay_remark());
                                cwBankDTOList.add(cwBankDTO);
                            }
                        }
                    }
                }
            }
            inspectionPayInfo.setCwBankDTOList(cwBankDTOList);

            String pay_received = inspectionPayInfo.getPay_received();
            if (StringUtil.isNotEmpty(pay_received)) {
                Double pay = Double.parseDouble(pay_received);
                inspectionPayInfo.setPay_received(String.valueOf(pay));
            }
            String pos = inspectionPayInfo.getPos();
            if (StringUtil.isNotEmpty(pos)) {
                Double pos_money = Double.parseDouble(pos);
                inspectionPayInfo.setPos(String.valueOf(pos_money));
            }
            String cash_pay = inspectionPayInfo.getCash_pay();
            if (StringUtil.isNotEmpty(cash_pay)) {
                Double cash = Double.parseDouble(cash_pay);
                inspectionPayInfo.setCash_pay(String.valueOf(cash));
            }
            //获取签名信息
            InspectionPaySign inspectionPaySign = inspectionPaySignService.getSignByPayInfo(inspectionPayInfo.getId());
            map.put("data", inspectionPayInfo);
            map.put("signInfo", inspectionPaySign);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("data", e.getMessage());
            map.put("success", true);
        }
        return map;
    }

    // 缴费结算单打印
    @RequestMapping(value = "doPrintCY")
    public ModelAndView doPrintCY(HttpServletRequest request, String id) {
        try {
            String type_1 = request.getParameter("type_1");

            InspectionPayInfo inspectionPayInfo = inspectionPayInfoService
                    .get(id); // 收费信息
            List<CwBankDTO> cwBankDTOList = new ArrayList<CwBankDTO>();
            if (StringUtil.isNotEmpty(inspectionPayInfo.getFk_cw_bank_id())) {
                String[] bank_ids = inspectionPayInfo.getFk_cw_bank_id().split(
                        ",");
                for (int i = 0; i < bank_ids.length; i++) {
                    CwBankDetail cwBankDetail = cwBankDetailManager
                            .get(bank_ids[i]);
                    if (cwBankDetail != null) {
                        CwBankDTO cwBankDTO = new CwBankDTO();
                        BeanUtils.copyProperties(cwBankDTO, cwBankDetail);
                        CwBank2Pay cwBank2Pay = cwBank2PayManager
                                .queryCwBank2Pay(bank_ids[i], id);
                        if (cwBank2Pay != null) {
                            cwBankDTO
                                    .setUsedMoney(TS_Util
                                            .ratioTransform(cwBank2Pay
                                                    .getCur_used_money()
                                                    .doubleValue()));
                            cwBankDTO.setPay_remark(cwBank2Pay.getPay_remark());
                        }
                        cwBankDTOList.add(cwBankDTO);
                    }
                }
            } else {
                List<CwBank2Pay> list = cwBank2PayManager
                        .queryCwBank2Pays(inspectionPayInfo.getId());
                if (!list.isEmpty()) {
                    for (CwBank2Pay cwBank2Pay : list) {
                        if (cwBank2Pay != null) {
                            CwBankDetail cwBankDetail = cwBankDetailManager
                                    .get(cwBank2Pay.getFk_cw_bank_id());
                            if (cwBankDetail != null) {
                                CwBankDTO cwBankDTO = new CwBankDTO();
                                BeanUtils.copyProperties(cwBankDTO,
                                        cwBankDetail);
                                cwBankDTO.setUsedMoney(TS_Util
                                        .ratioTransform(cwBank2Pay
                                                .getCur_used_money()
                                                .doubleValue()));
                                cwBankDTO.setPay_remark(cwBank2Pay
                                        .getPay_remark());
                                cwBankDTOList.add(cwBankDTO);
                            }
                        }
                    }
                }
            }
            inspectionPayInfo.setCwBankDTOList(cwBankDTOList);
            request.setAttribute("inspectionPayInfo",
                    inspectionPayInfo);
            request.setAttribute("type_1",
                    type_1);
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
        }
        ModelAndView mav = new ModelAndView("app/payment/payment_print_cy");
        return mav;
    }

    // 缴费结算单打印
    @RequestMapping(value = "doBatchPrintCY")
    public ModelAndView doBatchPrintCY(HttpServletRequest request, String id) {
        try {
            String type_1 = request.getParameter("type_1");
            InspectionPayInfo inspectionPayInfo = inspectionPayInfoService.getBatchPay(id);
            
            inspectionPayInfo.getInvoice_no();
            ReportBorrow entity=reportBorrowService.getReportBorrowByInvoice_no(inspectionPayInfo.getInvoice_no());
            
            request.setAttribute("reportBorrow", entity);
            request.setAttribute("inspectionPayInfo", inspectionPayInfo);
            request.setAttribute("type_1", type_1);
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
        }
        ModelAndView mav = new ModelAndView("app/payment/payment_print_cy");
        return mav;
    }

    // 详情
    @RequestMapping(value = "getZZJDDetail")
    @ResponseBody
    public HashMap<String, Object> getZZJDDetail(HttpServletRequest request,
                                                 String id, String status) {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        try {
            // 1、收费业务信息
            InspectionPayInfo inspectionPayInfo = inspectionPayInfoService
                    .queryByInspectionInfoID(id);
            if (inspectionPayInfo == null) {
                inspectionPayInfo = new InspectionPayInfo();
            }

            // 2、检验业务信息
            List<InspectionZZJDPayInfoDTO> list = new ArrayList<InspectionZZJDPayInfoDTO>(); // 业务收费信息列表
            String[] ids = id.split(",");
            if (StringUtil.isNotEmpty(inspectionPayInfo
                    .getFk_inspection_info_id())) {
                ids = inspectionPayInfo.getFk_inspection_info_id().split(",");
            }
            float advance_fees = 0.00f;
            float receivable = 0.00f;
            String com_name = "";
            for (int i = 0; i < ids.length; i++) { // 报检业务信息
                InspectionInfo inspectionInfo = inspectionInfoService
                        .get(ids[i]);
                InspectionZZJDInfo inspectionZZJDInfo = inspectionZZJDInfoService
                        .getByInfoId(ids[i]);
                if (inspectionInfo != null) {
                    if (StringUtil.isEmpty(com_name)) {
                        com_name = inspectionInfo.getReport_com_name();
                    }
                    InspectionZZJDPayInfoDTO inspectionZZJDPayInfoDTO = new InspectionZZJDPayInfoDTO();
                    BeanUtils.copyProperties(inspectionZZJDPayInfoDTO,
                            inspectionInfo);
                    BeanUtils.copyProperties(inspectionZZJDPayInfoDTO,
                            inspectionZZJDInfo);
                    inspectionZZJDPayInfoDTO.setId(inspectionInfo.getId());
                    inspectionZZJDPayInfoDTO
                            .setSn(StringUtil.isNotEmpty(inspectionInfo.getSn()) ? inspectionInfo
                                    .getSn() : ""); // 业务流水号
                    inspectionZZJDPayInfoDTO
                            .setAdvance_fees(inspectionInfo.getAdvance_fees() != null
                                    && inspectionInfo.getAdvance_fees() != 0 ? inspectionInfo
                                    .getAdvance_fees() : 0); // 应收金额
                    inspectionZZJDPayInfoDTO
                            .setReceivable(inspectionInfo.getReceivable() != null
                                    && inspectionInfo.getReceivable() != 0 ? inspectionInfo
                                    .getReceivable() : 0); // 实收金额
                    advance_fees += inspectionZZJDPayInfoDTO.getAdvance_fees();
                    receivable += inspectionZZJDPayInfoDTO.getReceivable();
                    inspectionZZJDPayInfoDTO
                            .setReport_sn(StringUtil.isNotEmpty(inspectionInfo
                                    .getReport_sn()) ? inspectionInfo
                                    .getReport_sn() : ""); // 报告书编号
                    list.add(inspectionZZJDPayInfoDTO);
                }
            }
            inspectionPayInfo.setPay_receive(String.valueOf(advance_fees)); // 应收总金额
            inspectionPayInfo.setInspectionZZJDPayInfoDTOList(list);

            // 3、银行转账情况
            List<CwBankDTO> cwBankDTOList = new ArrayList<CwBankDTO>();
            if (StringUtil.isNotEmpty(inspectionPayInfo.getFk_cw_bank_id())) {
                String[] bank_ids = inspectionPayInfo.getFk_cw_bank_id().split(
                        ",");
                for (int i = 0; i < bank_ids.length; i++) {
                    CwBankDetail cwBankDetail = cwBankDetailManager
                            .get(bank_ids[i]);
                    if (cwBankDetail != null) {
                        CwBankDTO cwBankDTO = new CwBankDTO();
                        BeanUtils.copyProperties(cwBankDTO, cwBankDetail);
                        CwBank2Pay cwBank2Pay = cwBank2PayManager
                                .queryCwBank2Pay(bank_ids[i], id);
                        if (cwBank2Pay != null) {
                            cwBankDTO
                                    .setUsedMoney(TS_Util
                                            .ratioTransform(cwBank2Pay
                                                    .getCur_used_money()
                                                    .doubleValue()));
                        }
                        cwBankDTOList.add(cwBankDTO);
                    }
                }
            }
            inspectionPayInfo.setCwBankDTOList(cwBankDTOList);

            wrapper.put("success", true);
            wrapper.put("data", inspectionPayInfo);
            wrapper.put("com_name", com_name);
            return wrapper;
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
            return JsonWrapper.failureWrapper("制造监督检验业务收费信息查询失败！");
        }
    }

    // 查询报检业务信息
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getInspectionInfoList")
    @ResponseBody
    public HashMap<String, Object> getInspectionInfoList(
            HttpServletRequest request, String id) {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        List<InspectionInfoDTO> inspectionInfoDTOList = new ArrayList<InspectionInfoDTO>(); // 报检业务信息列表
        try {
            if (StringUtil.isNotEmpty(id)) {
                String[] ids = id.split(",");
                InspectionPayInfo inspectionPayInfo = inspectionPayInfoService
                        .queryByInspectionInfoID(id); // 报检业务收费信息
                if (StringUtil.isNotEmpty(inspectionPayInfo
                        .getFk_inspection_info_id())) {
                    ids = inspectionPayInfo.getFk_inspection_info_id().split(
                            ",");
                }
                for (int i = 0; i < ids.length; i++) {
                    InspectionInfo inspectionInfo = inspectionInfoService
                            .get(ids[i]);
                    if (inspectionInfo != null) {
                        InspectionInfoDTO inspectionInfoDTO = new InspectionInfoDTO();
                        BeanUtils.copyProperties(inspectionInfoDTO,
                                inspectionInfo);
                        inspectionInfoDTO.setCom_name(inspectionInfo
                                .getInspection().getCom_name());
                        inspectionInfoDTO.setCheck_type(inspectionInfo
                                .getInspection().getCheck_type()); // 检验类别
                        DeviceDocument deviceDocument = deviceService
                                .get(inspectionInfo
                                        .getFk_tsjc_device_document_id());
                        inspectionInfoDTO.setDevice_sort_code(deviceDocument
                                .getDevice_sort_code()); // 设备类别
                        inspectionInfoDTO.setDevice_name(deviceDocument
                                .getDevice_name()); // 设备名称
                        inspectionInfoDTOList.add(inspectionInfoDTO);
                    }
                }
                wrapper.put("success", true);
                wrapper.put("datalist", inspectionInfoDTOList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            wrapper.put("error", true);
        }
        return wrapper;
    }

    // 查询报检业务信息
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getInspectionZZJDInfoList")
    @ResponseBody
    public HashMap<String, Object> getInspectionZZJDInfoList(
            HttpServletRequest request, String id) {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        List<InspectionZZJDPayInfoDTO> list = new ArrayList<InspectionZZJDPayInfoDTO>(); // 业务收费信息列表
        try {
            if (StringUtil.isNotEmpty(id)) {
                String[] ids = id.split(",");
                InspectionPayInfo inspectionPayInfo = inspectionPayInfoService
                        .queryByInspectionInfoID(id); // 报检业务收费信息
                if (StringUtil.isNotEmpty(inspectionPayInfo
                        .getFk_inspection_info_id())) {
                    ids = inspectionPayInfo.getFk_inspection_info_id().split(
                            ",");
                }
                for (int i = 0; i < ids.length; i++) {
                    InspectionInfo inspectionInfo = inspectionInfoService
                            .get(ids[i]);
                    InspectionZZJDInfo inspectionZZJDInfo = inspectionZZJDInfoService
                            .getByInfoId(ids[i]);

                    if (inspectionInfo != null) {
                        InspectionZZJDPayInfoDTO inspectionZZJDPayInfoDTO = new InspectionZZJDPayInfoDTO();
                        BeanUtils.copyProperties(inspectionZZJDPayInfoDTO,
                                inspectionInfo);
                        BeanUtils.copyProperties(inspectionZZJDPayInfoDTO,
                                inspectionZZJDInfo);
                        inspectionZZJDPayInfoDTO.setId(inspectionInfo.getId());
                        inspectionZZJDPayInfoDTO
                                .setSn(StringUtil.isNotEmpty(inspectionInfo
                                        .getSn()) ? inspectionInfo.getSn() : ""); // 业务流水号
                        inspectionZZJDPayInfoDTO
                                .setAdvance_fees(inspectionInfo
                                        .getAdvance_fees() != null
                                        && inspectionInfo.getAdvance_fees() != 0 ? inspectionInfo
                                        .getAdvance_fees() : 0); // 应收金额
                        inspectionZZJDPayInfoDTO
                                .setReceivable(inspectionInfo.getReceivable() != null
                                        && inspectionInfo.getReceivable() != 0 ? inspectionInfo
                                        .getReceivable() : 0); // 实收金额
                        inspectionZZJDPayInfoDTO
                                .setReport_sn(StringUtil
                                        .isNotEmpty(inspectionInfo
                                                .getReport_sn()) ? inspectionInfo
                                        .getReport_sn() : ""); // 报告书编号
                        list.add(inspectionZZJDPayInfoDTO);
                    }
                }
                wrapper.put("success", true);
                wrapper.put("datalist", list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            wrapper.put("error", true);
        }
        return wrapper;
    }

    /**
     * 更新业务收费信息中的业务ID、预收总金额、实收总金额等
     *
     * @param inspectionPayInfo -- 业务收费信息
     * @param ids               -- 报检业务ID
     * @return
     */
    private void updateInspectionPayInfo(InspectionPayInfo inspectionPayInfo,
                                         String ids) throws Exception {
        String[] idArr = ids.split(",");
        float pay_receive = 0.0f;
        String inspection_info_ids = inspectionPayInfo
                .getFk_inspection_info_id();
        for (int i = 0; i < idArr.length; i++) {
            if (StringUtil.isNotEmpty(idArr[i])) {
                InspectionInfo inspectionInfo = inspectionInfoService
                        .get(idArr[i]);
                if (inspectionInfo != null) {
                    pay_receive += inspectionInfo.getAdvance_fees() != null ? inspectionInfo
                            .getAdvance_fees() : 0;
                    inspection_info_ids = inspection_info_ids.replace(idArr[i],
                            "");
                }
            }
        }
        inspectionPayInfo.setPay_receive(String.valueOf(Float
                .parseFloat(inspectionPayInfo.getPay_receive()) - pay_receive)); // 应收总金额
        inspectionPayInfo
                .setPay_received(String.valueOf(Float
                        .parseFloat(inspectionPayInfo.getPay_received())
                        - pay_receive)); // 实收总金额
        if (inspection_info_ids.contains(",")) {
            String inspection_info_id = "";
            String[] insp_ids = inspection_info_ids.split(",");
            for (int i = 0; i < insp_ids.length; i++) {
                if (StringUtil.isNotEmpty(insp_ids[i])) {
                    if (inspection_info_id.length() > 0) {
                        inspection_info_id += ",";
                    }
                    inspection_info_id += insp_ids[i];
                }
            }
            inspectionPayInfo.setFk_inspection_info_id(inspection_info_id);
        } else {
            inspectionPayInfo.setFk_inspection_info_id(inspection_info_ids);
        }
        inspectionPayInfoService.save(inspectionPayInfo);
    }

    /**
     * 更新业务收费信息中的业务ID、预收总金额、实收总金额等
     *
     * @param ids -- 报检业务ID
     * @return
     */
    private void updateInspectionPayInfo(String ids) throws Exception {
        String[] idArr = ids.split(",");
        for (int i = 0; i < idArr.length; i++) {
            InspectionPayInfo payInfo = inspectionPayInfoService
                    .queryByInspectionInfoID(idArr[i]); // 报检业务收费信息
            if (payInfo != null) {
                String inspection_info_ids = payInfo.getFk_inspection_info_id();
                InspectionInfo inspectionInfo = inspectionInfoService
                        .get(idArr[i]);
                double pay_receive = 0.0f;
                if (inspectionInfo != null) {
                    pay_receive = inspectionInfo.getAdvance_fees() != null ? inspectionInfo
                            .getAdvance_fees() : 0;
                    inspection_info_ids = inspection_info_ids.replace(idArr[i],
                            "");
                }
                payInfo.setPay_receive(String.valueOf(Float.parseFloat(payInfo
                        .getPay_receive()) - pay_receive)); // 应收总金额
                payInfo.setPay_received(String.valueOf(Float.parseFloat(payInfo
                        .getPay_received()) - pay_receive)); // 实收总金额
                if (inspection_info_ids.contains(",")) {
                    String inspection_info_id = "";
                    String[] insp_ids = inspection_info_ids.split(",");
                    for (int j = 0; j < insp_ids.length; j++) {
                        if (StringUtil.isNotEmpty(insp_ids[j])) {
                            if (inspection_info_id.length() > 0) {
                                inspection_info_id += ",";
                            }
                            inspection_info_id += insp_ids[j];
                        }
                    }
                    payInfo.setFk_inspection_info_id(inspection_info_id);
                } else {
                    payInfo.setFk_inspection_info_id(inspection_info_ids);
                }
                inspectionPayInfoService.save(payInfo);
            }
        }
    }

    /**
     * 导出交账明细
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "export")
    public String export(HttpServletRequest request) {
        try {
            String org_id = request.getParameter("org_id");
            /*
             * if (StringUtil.isNotEmpty(str)) { check_dep_name = new
             * String(str.getBytes("ISO8859_1"), "UTF-8"); }
             */
            String invoice_start_date = request
                    .getParameter("invoice_start_date");
            String invoice_end_date = request.getParameter("invoice_end_date");
            float cashTotal = 0;
            float transferTotal = 0;
            float posTotal = 0;
            float hand_inTotal = 0;
            float draftTotal = 0;
            List<InspectionPayInfoDTO> list = inspectionPayInfoService.export(
                    org_id, invoice_start_date, invoice_end_date);
            if (!list.isEmpty()) {
                for (InspectionPayInfoDTO inspectionPayInfoDTO : list) {
                    if ("1".equals(inspectionPayInfoDTO.getPay_type())) { // 现金
                        cashTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("2".equals(inspectionPayInfoDTO.getPay_type())) { // 银行转账
                        transferTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("4".equals(inspectionPayInfoDTO.getPay_type())) { // 现金及转账
                        cashTotal += StringUtil.isNotEmpty(inspectionPayInfoDTO
                                .getCash_pay())
                                && !"null".equals(inspectionPayInfoDTO
                                .getCash_pay()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getCash_pay())
                                : 0;

                        transferTotal += StringUtil
                                .isNotEmpty(inspectionPayInfoDTO.getRemark())
                                && !"null".equals(inspectionPayInfoDTO
                                .getRemark()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getRemark())
                                : 0;
                    } else if ("5".equals(inspectionPayInfoDTO.getPay_type())) { // POS机刷卡
                        posTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("6".equals(inspectionPayInfoDTO.getPay_type())) { // 现金及POS机刷卡
                        cashTotal += StringUtil.isNotEmpty(inspectionPayInfoDTO
                                .getCash_pay())
                                && !"null".equals(inspectionPayInfoDTO
                                .getCash_pay()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getCash_pay())
                                : 0;

                        posTotal += StringUtil.isNotEmpty(inspectionPayInfoDTO
                                .getPos())
                                && !"null"
                                .equals(inspectionPayInfoDTO.getPos()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getPos()) : 0;
                    } else if ("7".equals(inspectionPayInfoDTO.getPay_type())) { // 转账及POS机刷卡
                        transferTotal += StringUtil
                                .isNotEmpty(inspectionPayInfoDTO.getRemark())
                                && !"null".equals(inspectionPayInfoDTO
                                .getRemark()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getRemark())
                                : 0;
                        posTotal += StringUtil.isNotEmpty(inspectionPayInfoDTO
                                .getPos())
                                && !"null"
                                .equals(inspectionPayInfoDTO.getPos()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getPos()) : 0;
                    } else if ("8".equals(inspectionPayInfoDTO.getPay_type())) { // 上缴财政
                        hand_inTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("9".equals(inspectionPayInfoDTO.getPay_type())) { // 承兑汇票
                        draftTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    }
                }
            }
            request.getSession().setAttribute("data", list); // 已收费记录
            request.getSession().setAttribute("cashTotal", cashTotal);            // 现金合计
            request.getSession().setAttribute("transferTotal", transferTotal);    // 银行转账合计
            request.getSession().setAttribute("posTotal", posTotal);            // POS机刷卡合计
            request.getSession().setAttribute("hand_inTotal", hand_inTotal);    // 上缴财政合计
            request.getSession().setAttribute("draftTotal", draftTotal);        // 承兑汇票合计
        } catch (Exception e) {
            e.printStackTrace();
            log.error("已收费业务导出失败！");
        }
        return "app/payment/export_pay_info";
    }

    /**
     * 导出交账明细（承压）
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "exportCY")
    public String exportCY(HttpServletRequest request) {
        try {
            String org_id = request.getParameter("org_id");
            /*
             * if (StringUtil.isNotEmpty(str)) { check_dep_name = new
             * String(str.getBytes("ISO8859_1"), "UTF-8"); }
             */
            String pay_start_date = request.getParameter("pay_start_date");
            String pay_end_date = request.getParameter("pay_end_date");
            float cashTotal = 0;
            float transferTotal = 0;
            float posTotal = 0;
            float hand_inTotal = 0;
            float draftTotal = 0;
            List<InspectionPayInfoDTO> list = inspectionPayInfoService
                    .exportCY(org_id, pay_start_date, pay_end_date);
            if (!list.isEmpty()) {
                for (InspectionPayInfoDTO inspectionPayInfoDTO : list) {
                    if ("1".equals(inspectionPayInfoDTO.getPay_type())) { // 现金
                        cashTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("2".equals(inspectionPayInfoDTO.getPay_type())) { // 银行转账
                        transferTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("4".equals(inspectionPayInfoDTO.getPay_type())) { // 现金及转账
                        cashTotal += StringUtil.isNotEmpty(inspectionPayInfoDTO
                                .getCash_pay())
                                && !"null".equals(inspectionPayInfoDTO
                                .getCash_pay()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getCash_pay())
                                : 0;

                        transferTotal += StringUtil
                                .isNotEmpty(inspectionPayInfoDTO.getRemark())
                                && !"null".equals(inspectionPayInfoDTO
                                .getRemark()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getRemark())
                                : 0;
                    } else if ("5".equals(inspectionPayInfoDTO.getPay_type())) { // POS机刷卡
                        posTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("6".equals(inspectionPayInfoDTO.getPay_type())) { // 现金及POS机刷卡
                        cashTotal += StringUtil.isNotEmpty(inspectionPayInfoDTO
                                .getCash_pay())
                                && !"null".equals(inspectionPayInfoDTO
                                .getCash_pay()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getCash_pay())
                                : 0;

                        posTotal += StringUtil.isNotEmpty(inspectionPayInfoDTO
                                .getPos())
                                && !"null"
                                .equals(inspectionPayInfoDTO.getPos()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getPos()) : 0;
                    } else if ("7".equals(inspectionPayInfoDTO.getPay_type())) { // 转账及POS机刷卡
                        transferTotal += StringUtil
                                .isNotEmpty(inspectionPayInfoDTO.getRemark())
                                && !"null".equals(inspectionPayInfoDTO
                                .getRemark()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getRemark())
                                : 0;
                        posTotal += StringUtil.isNotEmpty(inspectionPayInfoDTO
                                .getPos())
                                && !"null"
                                .equals(inspectionPayInfoDTO.getPos()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getPos()) : 0;
                    } else if ("8".equals(inspectionPayInfoDTO.getPay_type())) { // 上缴财政
                        hand_inTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("9".equals(inspectionPayInfoDTO.getPay_type())) { // 承兑汇票
                        draftTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    }
                }
            }
            request.getSession().setAttribute("data", list); // 已收费记录
            request.getSession().setAttribute("cashTotal", cashTotal);            // 现金合计
            request.getSession().setAttribute("transferTotal", transferTotal);    // 银行转账合计
            request.getSession().setAttribute("posTotal", posTotal);            // POS机刷卡合计
            request.getSession().setAttribute("hand_inTotal", hand_inTotal);    // 上缴财政合计
            request.getSession().setAttribute("draftTotal", draftTotal);        // 承兑汇票合计
        } catch (Exception e) {
            e.printStackTrace();
            log.error("承压交账明细导出失败！");
        }
        return "app/payment/export_pay_cy";
    }

    /**
     * 导出交账明细（所有，包含机电和承压）
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "exportAll")
    public String exportAll(HttpServletRequest request) {
        try {
            String org_id = request.getParameter("org_id");
            /*
             * if (StringUtil.isNotEmpty(str)) { check_dep_name = new
             * String(str.getBytes("ISO8859_1"), "UTF-8"); }
             */
            String pay_start_date = request.getParameter("pay_start_date");
            String pay_end_date = request.getParameter("pay_end_date");
            String invoice_no_start = request.getParameter("invoice_no_start");
            String invoice_no_end = request.getParameter("invoice_no_end");
            String invoice_category = request.getParameter("invoice_category");
            String payment_category = request.getParameter("payment_category");
            String op_user_name = request.getParameter("op_user_name");

            float cashTotal = 0;
            float transferTotal = 0;
            float posTotal = 0;
            float hand_inTotal = 0;
            float draftTotal = 0;
            List<InspectionPayInfoDTO> list = inspectionPayInfoService.exportAll(org_id, pay_start_date, pay_end_date,
                    invoice_no_start, invoice_no_end, invoice_category, payment_category, op_user_name);
            if (!list.isEmpty()) {
                for (InspectionPayInfoDTO inspectionPayInfoDTO : list) {
                    if ("1".equals(inspectionPayInfoDTO.getPay_type())) { // 现金
                        cashTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("2".equals(inspectionPayInfoDTO.getPay_type())) { // 银行转账
                        transferTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("4".equals(inspectionPayInfoDTO.getPay_type())) { // 现金及转账
                        cashTotal += StringUtil.isNotEmpty(inspectionPayInfoDTO
                                .getCash_pay())
                                && !"null".equals(inspectionPayInfoDTO
                                .getCash_pay()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getCash_pay())
                                : 0;

                        transferTotal += StringUtil
                                .isNotEmpty(inspectionPayInfoDTO.getRemark())
                                && !"null".equals(inspectionPayInfoDTO
                                .getRemark()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getRemark())
                                : 0;
                    } else if ("5".equals(inspectionPayInfoDTO.getPay_type())) { // POS机刷卡
                        posTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("6".equals(inspectionPayInfoDTO.getPay_type())) { // 现金及POS机刷卡
                        cashTotal += StringUtil.isNotEmpty(inspectionPayInfoDTO
                                .getCash_pay())
                                && !"null".equals(inspectionPayInfoDTO
                                .getCash_pay()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getCash_pay())
                                : 0;

                        posTotal += StringUtil.isNotEmpty(inspectionPayInfoDTO
                                .getPos())
                                && !"null"
                                .equals(inspectionPayInfoDTO.getPos()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getPos()) : 0;
                    } else if ("7".equals(inspectionPayInfoDTO.getPay_type())) { // 转账及POS机刷卡
                        transferTotal += StringUtil
                                .isNotEmpty(inspectionPayInfoDTO.getRemark())
                                && !"null".equals(inspectionPayInfoDTO
                                .getRemark()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getRemark())
                                : 0;
                        posTotal += StringUtil.isNotEmpty(inspectionPayInfoDTO
                                .getPos())
                                && !"null"
                                .equals(inspectionPayInfoDTO.getPos()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getPos()) : 0;
                    } else if ("8".equals(inspectionPayInfoDTO.getPay_type())) { // 上缴财政
                        hand_inTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("9".equals(inspectionPayInfoDTO.getPay_type())) { // 承兑汇票
                        draftTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    }
                }
            }
            request.getSession().setAttribute("data", list); // 已收费记录
            request.getSession().setAttribute("cashTotal", cashTotal);            // 现金合计
            request.getSession().setAttribute("transferTotal", transferTotal);    // 银行转账合计
            request.getSession().setAttribute("posTotal", posTotal);            // POS机刷卡合计
            request.getSession().setAttribute("hand_inTotal", hand_inTotal);    // 上缴财政合计
            request.getSession().setAttribute("draftTotal", draftTotal);        // 承兑汇票合计
        } catch (Exception e) {
            e.printStackTrace();
            log.error("交账明细导出失败！");
        }
        return "app/payment/export_pay_all";
    }

    /**
     * 导出收入明细
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "export1")
    public String export1(HttpServletRequest request) {
        try {
            String check_dep_name = "";
            String check_unit_id = request.getParameter("org_id");
            /*
             * String str = request.getParameter("check_dep_name"); if
             * (StringUtil.isNotEmpty(str)) { check_dep_name = new
             * String(str.getBytes("ISO8859_1"), "UTF-8"); }
             */
            String invoice_start_date = request
                    .getParameter("invoice_start_date");
            String invoice_end_date = request.getParameter("invoice_end_date");
            float cashTotal = 0;
            float transferTotal = 0;
            float posTotal = 0;
            float hand_inTotal = 0;
            float draftTotal = 0;
            List<InspectionPayInfoDTO> list = inspectionPayInfoService.export(
                    check_unit_id, invoice_start_date, invoice_end_date);
            if (!list.isEmpty()) {
                for (InspectionPayInfoDTO inspectionPayInfoDTO : list) {
                    if ("1".equals(inspectionPayInfoDTO.getPay_type())) { // 现金
                        cashTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("2".equals(inspectionPayInfoDTO.getPay_type())) { // 银行转账
                        transferTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("4".equals(inspectionPayInfoDTO.getPay_type())) { // 现金及转账
                        cashTotal += StringUtil.isNotEmpty(inspectionPayInfoDTO
                                .getCash_pay())
                                && !"null".equals(inspectionPayInfoDTO
                                .getCash_pay()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getCash_pay())
                                : 0;

                        transferTotal += StringUtil
                                .isNotEmpty(inspectionPayInfoDTO.getRemark())
                                && !"null".equals(inspectionPayInfoDTO
                                .getRemark()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getRemark())
                                : 0;
                    } else if ("5".equals(inspectionPayInfoDTO.getPay_type())) { // POS机刷卡
                        posTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("6".equals(inspectionPayInfoDTO.getPay_type())) { // 现金及POS机刷卡
                        cashTotal += StringUtil.isNotEmpty(inspectionPayInfoDTO
                                .getCash_pay())
                                && !"null".equals(inspectionPayInfoDTO
                                .getCash_pay()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getCash_pay())
                                : 0;

                        posTotal += StringUtil.isNotEmpty(inspectionPayInfoDTO
                                .getPos())
                                && !"null"
                                .equals(inspectionPayInfoDTO.getPos()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getPos()) : 0;
                    } else if ("7".equals(inspectionPayInfoDTO.getPay_type())) { // 转账及POS机刷卡
                        transferTotal += StringUtil
                                .isNotEmpty(inspectionPayInfoDTO.getRemark())
                                && !"null".equals(inspectionPayInfoDTO
                                .getRemark()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getRemark())
                                : 0;
                        posTotal += StringUtil.isNotEmpty(inspectionPayInfoDTO
                                .getPos())
                                && !"null"
                                .equals(inspectionPayInfoDTO.getPos()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getPos()) : 0;
                    } else if ("8".equals(inspectionPayInfoDTO.getPay_type())) { // 上缴财政
                        hand_inTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("9".equals(inspectionPayInfoDTO.getPay_type())) { // 承兑汇票
                        draftTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    }

                }
            }

            if (StringUtil.isNotEmpty(check_unit_id)) {
                Org org = orgManager.get(check_unit_id);
                check_dep_name = StringUtil.isNotEmpty(org.getOrgName()) ? org
                        .getOrgName() : ""; // 检验部门
            }
            request.getSession().setAttribute("check_dep_name", check_dep_name); // 检验部门
            request.getSession().setAttribute("data", list); // 收入记录
            request.getSession().setAttribute("cashTotal", cashTotal);            // 现金合计
            request.getSession().setAttribute("transferTotal", transferTotal);    // 银行转账合计
            request.getSession().setAttribute("posTotal", posTotal);            // POS机刷卡合计
            request.getSession().setAttribute("hand_inTotal", hand_inTotal);    // 上缴财政合计
            request.getSession().setAttribute("draftTotal", draftTotal);        // 承兑汇票合计
        } catch (Exception e) {
            e.printStackTrace();
            log.error("已收费业务导出失败！");
        }
        return "app/payment/export_pay_info1";
    }

    /**
     * 导出借报告
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "export2")
    public String export2(HttpServletRequest request) {
        try {
            String check_dep_name = "";
            // 检验部门ID
            String check_unit_id = request.getParameter("org_id");
            // 签字主任ID
            String sign_leader_id = request.getParameter("sign_leader_id");
            // 外借开始日期
            String borrow_start_date = request.getParameter("borrow_start_date");
            // 外借结束日期
            String borrow_end_date = request.getParameter("borrow_end_date");

            float total = 0;
            List<ReportBorrowDTO> list = inspectionPayInfoService.exportBorrow(check_unit_id, sign_leader_id,
                    borrow_start_date, borrow_end_date, "0");
            if (!list.isEmpty()) {
                for (ReportBorrowDTO reportBorrowDTO : list) {
                    total += Float.parseFloat(reportBorrowDTO.getUnpay_amount());
                    if (StringUtil.isEmpty(check_dep_name)) {
                        check_dep_name = reportBorrowDTO.getCheck_department();
                    }
                }
            }
            request.getSession().setAttribute("check_dep_name", check_dep_name); // 检验部门
            request.getSession().setAttribute("data", list); // 收入记录
            request.getSession().setAttribute("total", total); // 金额合计
        } catch (Exception e) {
            e.printStackTrace();
            log.error("借报告导出失败！");
        }
        return "app/payment/export_pay_info2";
    }

    /**
     * 导出借发票
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "export3")
    public String export3(HttpServletRequest request) {
        try {
            String check_dep_name = "";
            // 检验部门ID
            String check_unit_id = request.getParameter("org_id");
            // 签字主任ID
            String sign_leader_id = request.getParameter("sign_leader_id");
            // 外借开始日期
            String borrow_start_date = request.getParameter("borrow_start_date");
            // 外借结束日期
            String borrow_end_date = request.getParameter("borrow_end_date");

            float total = 0;
            List<ReportBorrowDTO> list = inspectionPayInfoService
                    .exportBorrow2(check_unit_id, sign_leader_id, borrow_start_date,
                            borrow_end_date, "1");
            if (!list.isEmpty()) {
                for (ReportBorrowDTO reportBorrowDTO : list) {
                    total += Float
                            .parseFloat(reportBorrowDTO.getUnpay_amount());
                    if (StringUtil.isEmpty(check_dep_name)) {
                        check_dep_name = reportBorrowDTO.getCheck_department();
                    }
                }
            }
            request.getSession().setAttribute("borrow_type", "1"); // 外借类型（0：外借报告，1：外借发票，2：外借报告和发票）
            request.getSession().setAttribute("check_dep_name", check_dep_name); // 检验部门
            request.getSession().setAttribute("data", list); // 收入记录
            request.getSession().setAttribute("total", total); // 金额合计
        } catch (Exception e) {
            e.printStackTrace();
            log.error("借发票导出失败！");
        }
        return "app/payment/export_pay_info3";
    }

    /**
     * 导出借报告和发票
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "export4")
    public String export4(HttpServletRequest request) {
        try {
            String check_dep_name = "";
            // 检验部门ID
            String check_unit_id = request.getParameter("org_id");
            // 签字主任ID
            String sign_leader_id = request.getParameter("sign_leader_id");
            // 外借开始日期
            String borrow_start_date = request.getParameter("borrow_start_date");
            // 外借结束日期
            String borrow_end_date = request.getParameter("borrow_end_date");

            float total = 0;
            List<ReportBorrowDTO> list = inspectionPayInfoService.exportBorrow3(check_unit_id, sign_leader_id,
                    borrow_start_date, borrow_end_date, "2");
            if (!list.isEmpty()) {
                for (ReportBorrowDTO reportBorrowDTO : list) {
                    total += Float.parseFloat(reportBorrowDTO.getUnpay_amount());
                    if (StringUtil.isEmpty(check_dep_name)) {
                        check_dep_name = reportBorrowDTO.getCheck_department();
                    }
                }
            }
            request.getSession().setAttribute("borrow_type", "2"); // 外借类型（0：外借报告，1：外借发票，2：外借报告和发票）
            request.getSession().setAttribute("check_dep_name", check_dep_name); // 检验部门
            request.getSession().setAttribute("data", list); // 收入记录
            request.getSession().setAttribute("total", total); // 金额合计
        } catch (Exception e) {
            e.printStackTrace();
            log.error("借报告导出失败！");
        }
        return "app/payment/export_pay_info4";
    }

    /**
     * 导出交账明细
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "exportZZJD")
    public String exportZZJD(HttpServletRequest request) {
        try {
            String check_dep_name = request.getParameter("check_dep_name");
            /*
             * if (StringUtil.isNotEmpty(str)) { check_dep_name = new
             * String(str.getBytes("ISO8859_1"), "UTF-8"); }
             */
            String invoice_start_date = request
                    .getParameter("invoice_start_date");
            String invoice_end_date = request.getParameter("invoice_end_date");
            float cashTotal = 0;
            float transferTotal = 0;
            float posTotal = 0;
            List<InspectionPayInfoDTO> list = inspectionPayInfoService
                    .exportZZJD(check_dep_name, invoice_start_date,
                            invoice_end_date);
            if (!list.isEmpty()) {
                for (InspectionPayInfoDTO inspectionPayInfoDTO : list) {
                    if ("1".equals(inspectionPayInfoDTO.getPay_type())) { // 现金
                        cashTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("2".equals(inspectionPayInfoDTO.getPay_type())) { // 银行转账
                        transferTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("4".equals(inspectionPayInfoDTO.getPay_type())) { // 现金及转账
                        cashTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getCash_pay());
                        transferTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getRemark());
                    } else if ("5".equals(inspectionPayInfoDTO.getPay_type())) { // POS机刷卡
                        posTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("6".equals(inspectionPayInfoDTO.getPay_type())) { // 现金及POS机刷卡
                        cashTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getCash_pay());
                        posTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPos());
                    } else if ("7".equals(inspectionPayInfoDTO.getPay_type())) { // 转账及POS机刷卡
                        transferTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getRemark());
                        posTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPos());
                    }
                }
            }
            request.getSession().setAttribute("data", list); // 已收费记录
            request.getSession().setAttribute("cashTotal", cashTotal); // 现金合计
            request.getSession().setAttribute("transferTotal", transferTotal); // 银行转账合计
            request.getSession().setAttribute("posTotal", posTotal); // POS机刷卡合计
        } catch (Exception e) {
            e.printStackTrace();
            log.error("已收费业务导出失败！");
        }
        return "app/payment/export_pay_info";
    }

    /**
     * 导出收入明细
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "exportZZJD1")
    public String exportZZJD1(HttpServletRequest request) {
        try {
            String check_dep_name = request.getParameter("check_dep_name");
            /*
             * String str = request.getParameter("check_dep_name"); if
             * (StringUtil.isNotEmpty(str)) { check_dep_name = new
             * String(str.getBytes("ISO8859_1"), "UTF-8"); }
             */
            String invoice_start_date = request
                    .getParameter("invoice_start_date");
            String invoice_end_date = request.getParameter("invoice_end_date");
            float cashTotal = 0;
            float transferTotal = 0;
            float posTotal = 0;
            List<InspectionPayInfoDTO> list = inspectionPayInfoService
                    .exportZZJD(check_dep_name, invoice_start_date,
                            invoice_end_date);
            if (!list.isEmpty()) {
                for (InspectionPayInfoDTO inspectionPayInfoDTO : list) {
                    if ("1".equals(inspectionPayInfoDTO.getPay_type())) { // 现金
                        cashTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("2".equals(inspectionPayInfoDTO.getPay_type())) { // 银行转账
                        transferTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("4".equals(inspectionPayInfoDTO.getPay_type())) { // 现金及转账
                        cashTotal += StringUtil.isNotEmpty(inspectionPayInfoDTO
                                .getCash_pay())
                                && !"null".equals(inspectionPayInfoDTO
                                .getCash_pay()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getCash_pay())
                                : 0;

                        transferTotal += StringUtil
                                .isNotEmpty(inspectionPayInfoDTO.getRemark())
                                && !"null".equals(inspectionPayInfoDTO
                                .getRemark()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getRemark())
                                : 0;
                    } else if ("5".equals(inspectionPayInfoDTO.getPay_type())) { // POS机刷卡
                        posTotal += Float.parseFloat(inspectionPayInfoDTO
                                .getPay_received());
                    } else if ("6".equals(inspectionPayInfoDTO.getPay_type())) { // 现金及POS机刷卡
                        cashTotal += StringUtil.isNotEmpty(inspectionPayInfoDTO
                                .getCash_pay())
                                && !"null".equals(inspectionPayInfoDTO
                                .getCash_pay()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getCash_pay())
                                : 0;

                        posTotal += StringUtil.isNotEmpty(inspectionPayInfoDTO
                                .getRemark())
                                && !"null".equals(inspectionPayInfoDTO
                                .getRemark()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getRemark())
                                : 0;
                    } else if ("7".equals(inspectionPayInfoDTO.getPay_type())) { // 转账及POS机刷卡
                        transferTotal += StringUtil
                                .isNotEmpty(inspectionPayInfoDTO.getRemark())
                                && !"null".equals(inspectionPayInfoDTO
                                .getRemark()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getRemark())
                                : 0;
                        posTotal += StringUtil.isNotEmpty(inspectionPayInfoDTO
                                .getPos())
                                && !"null"
                                .equals(inspectionPayInfoDTO.getPos()) ? Float
                                .parseFloat(inspectionPayInfoDTO.getPos()) : 0;
                    }
                }
            }
            request.getSession().setAttribute("check_dep_name", check_dep_name); // 检验部门
            request.getSession().setAttribute("data", list); // 收入记录
            request.getSession().setAttribute("cashTotal", cashTotal); // 现金合计
            request.getSession().setAttribute("transferTotal", transferTotal); // 银行转账合计
            request.getSession().setAttribute("posTotal", posTotal); // POS机刷卡合计
        } catch (Exception e) {
            e.printStackTrace();
            log.error("已收费业务导出失败！");
        }
        return "app/payment/export_pay_info1";
    }

    /**
     * 导出借报告
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "exportZZJD2")
    public String exportZZJD2(HttpServletRequest request) {
        try {
            String check_dep_name = request.getParameter("check_dep_name");
            /*
             * String check_dep_name = ""; String str =
             * request.getParameter("check_dep_name"); if
             * (StringUtil.isNotEmpty(str)) { check_dep_name = new
             * String(str.getBytes("ISO8859_1"), "UTF-8"); }
             */
            String borrow_start_date = request
                    .getParameter("borrow_start_date");
            String borrow_end_date = request.getParameter("borrow_end_date");
            float total = 0;
            List<ReportBorrowDTO> list = inspectionPayInfoService
                    .exportZZJDBorrow(check_dep_name, borrow_start_date,
                            borrow_end_date, "0"); // 外借类型（0：外借报告，1：外借发票，2：外借报告和发票）
            if (!list.isEmpty()) {
                for (ReportBorrowDTO reportBorrowDTO : list) {
                    total += Float
                            .parseFloat(reportBorrowDTO.getUnpay_amount());
                }
            }
            request.getSession().setAttribute("check_dep_name", check_dep_name); // 检验部门
            request.getSession().setAttribute("data", list); // 收入记录
            request.getSession().setAttribute("total", total); // 金额合计
        } catch (Exception e) {
            e.printStackTrace();
            log.error("借报告导出失败！");
        }
        return "app/payment/export_pay_info2";
    }

    /**
     * 导出借发票
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "exportZZJD3")
    public String exportZZJD3(HttpServletRequest request) {
        try {
            String check_dep_name = request.getParameter("check_dep_name");
            /*
             * String check_dep_name = ""; String str =
             * request.getParameter("check_dep_name"); if
             * (StringUtil.isNotEmpty(str)) { check_dep_name = new
             * String(str.getBytes("ISO8859_1"), "UTF-8"); }
             */
            String borrow_start_date = request
                    .getParameter("borrow_start_date");
            String borrow_end_date = request.getParameter("borrow_end_date");
            float total = 0;
            List<ReportBorrowDTO> list = inspectionPayInfoService
                    .exportZZJDBorrow(check_dep_name, borrow_start_date,
                            borrow_end_date, "1"); // 外借类型（0：外借报告，1：外借发票，2：外借报告和发票）
            if (!list.isEmpty()) {
                for (ReportBorrowDTO reportBorrowDTO : list) {
                    total += Float
                            .parseFloat(reportBorrowDTO.getUnpay_amount());
                }
            }
            request.getSession().setAttribute("borrow_type", "1"); // 外借类型（0：外借报告，1：外借发票，2：外借报告和发票）
            request.getSession().setAttribute("check_dep_name", check_dep_name); // 检验部门
            request.getSession().setAttribute("data", list); // 收入记录
            request.getSession().setAttribute("total", total); // 金额合计
        } catch (Exception e) {
            e.printStackTrace();
            log.error("借发票导出失败！");
        }
        return "app/payment/export_pay_info3";
    }

    /**
     * 导出借报告和发票
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "exportZZJD4")
    public String exportZZJD4(HttpServletRequest request) {
        try {
            String check_dep_name = request.getParameter("check_dep_name");
            /*
             * String check_dep_name = ""; String str =
             * request.getParameter("check_dep_name"); if
             * (StringUtil.isNotEmpty(str)) { check_dep_name = new
             * String(str.getBytes("ISO8859_1"), "UTF-8"); }
             */
            String borrow_start_date = request
                    .getParameter("borrow_start_date");
            String borrow_end_date = request.getParameter("borrow_end_date");
            float total = 0;
            List<ReportBorrowDTO> list = inspectionPayInfoService
                    .exportZZJDBorrow(check_dep_name, borrow_start_date,
                            borrow_end_date, "2"); // 外借类型（0：外借报告，1：外借发票，2：外借报告和发票）
            if (!list.isEmpty()) {
                for (ReportBorrowDTO reportBorrowDTO : list) {
                    total += Float
                            .parseFloat(reportBorrowDTO.getUnpay_amount());
                }
            }
            request.getSession().setAttribute("borrow_type", "2"); // 外借类型（0：外借报告，1：外借发票，2：外借报告和发票）
            request.getSession().setAttribute("check_dep_name", check_dep_name); // 检验部门
            request.getSession().setAttribute("data", list); // 收入记录
            request.getSession().setAttribute("total", total); // 金额合计
        } catch (Exception e) {
            e.printStackTrace();
            log.error("借发票导出失败！");
        }
        return "app/payment/export_pay_info3";
    }

    // 查询开票日志信息
    @RequestMapping(value = "getFlowStep")
    @ResponseBody
    public ModelAndView getFlowStep(HttpServletRequest request)
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map = inspectionPayInfoService.getFlowStep(request.getParameter("id"));
        ModelAndView mav = new ModelAndView("app/payment/flow_card", map);
        return mav;
    }

    /**
     * 验证发票号是否已存在（使用）
     *
     * @param invoice_no -- 发票号
     * @return 存在发票号返回true，不存在返回false
     */
    @RequestMapping(value = "validateInvoice")
    @ResponseBody
    public HashMap<String, Object> validateInvoice(String invoice_no)
            throws Exception {
        return inspectionPayInfoService.validateInvoice(invoice_no);
    }

    /**
     * 验证发票号是否存在
     *
     * @param invoice_no -- 发票号
     * @return 存在发票号返回true，不存在返回false
     */
    @RequestMapping(value = "validateInvoices")
    @ResponseBody
    public HashMap<String, Object> validateInvoices(String invoice_no)
            throws Exception {
        return inspectionPayInfoService.validateInvoices(invoice_no);
    }

    /**
     * 验证发票号是否可用
     *
     * @param invoice_no -- 发票号
     * @return 可用发票号返回true，不可用返回false
     */
    @RequestMapping(value = "validateInvoice2")
    @ResponseBody
    public HashMap<String, Object> validateInvoice2(String invoice_no) throws Exception {
        return inspectionPayInfoService.validateInvoice2(invoice_no);
    }

    // 保存批量导入数据
    @RequestMapping(value = "batchSave")
    @ResponseBody
    public HashMap<String, Object> batchSave(HttpServletRequest request,
                                             @RequestBody InspectionPayInfoDTO inspectionPayInfoDTO) throws Exception {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        try {
            inspectionPayInfoService.batchSave(inspectionPayInfoDTO, request);
            wrapper.put("success", true);
        } catch (Exception e) {
            log.debug(e.toString());
            wrapper.put("success", false);
            wrapper.put("msg", "保存失败！");
            e.printStackTrace();
        }
        return wrapper;
    }

    // 删除
    public void del(HttpServletRequest request, String id) {
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        try {
            InspectionPayInfo inspectionPayInfo = inspectionPayInfoDao.get(id);
            if (inspectionPayInfo != null) {
                // 1、删除（TZSB_INSPECTION_PAY_INFO）
                inspectionPayInfoDao
                        .createSQLQuery("update TZSB_INSPECTION_PAY_INFO set payment_status='99' where id = ? ", id)
                        .executeUpdate();
            }

            // 2、写入日志
            logService.setLogs(id, "批量导入时，删除开票", "批量导入时，删除开票", user.getId(),
                    user.getName(), new Date(), request.getRemoteAddr());
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.toString());
        }

    }

    /**
     * 上传Excel并解析数据（暂只支持2003版本的excel）
     *
     * @param filename -- 文件名
     * @return JSON
     * @author GaoYa
     * @date 2017-01-16 下午04:07:00
     */
    @RequestMapping(value = "importData")
    @ResponseBody
    public HashMap<String, Object> parse(String filename, String report_type) {
        HashMap<String, Object> map = new HashMap<String, Object>();
		/*
		 * // 获取系统参数 SysParaInf sp = Factory.getSysPara(); // 获取文件上传路径 String importpath
		 * = sp.getProperty("UPLOAD_PATH"); if (StringUtil.isEmpty(importpath)) { return
		 * JsonWrapper.failureWrapper("错误：系统未配置文件上传路径！"); } File importdir = new
		 * File(importpath); if (!importdir.exists()) importdir.mkdirs();
		 * 
		 * File file = new File(importdir, filename);
		 */
        File file = new File(attachmentManager.download(filename).getFilePath());
        try {
            if (file.canRead()) {
                List<InspectionPayInfo> infoList = new ArrayList<InspectionPayInfo>();
                List<String> errors = new ArrayList<String>(); // 错误信息

                // 解析Excel文件
                parseExcelDatas(file, infoList,
                        errors);
                if (errors.isEmpty()) {
                    // 解析完Excel后将文件删除
                    // file.delete();
                    // 返回解析数据到页面
                    map.put("infoList", infoList);
                    map.put("success", true);
                    return map;
                } else {
                    //file.delete();
                    return JsonWrapper.failureWrapper("导入失败：" + errors);
                }
            } else {
                //file.delete();
                return JsonWrapper.failureWrapper("导入文件不可读！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //file.delete();
            log.debug(e.toString());
            return JsonWrapper.failureWrapper("导入失败：" + e.getMessage());
        }
    }

    /**
     * 解析Excel
     *
     * @param xlsfile  --
     *                 Excel文件
     * @param infoList --
     *                 通用开票明细数据集合
     * @param errors   --
     *                 错误信息
     * @return
     * @author GaoYa
     * @date 2017-01-16 下午04:08:00
     */
    public void parseExcelDatas(File xlsfile,
                                List<InspectionPayInfo> infoList, List<String> errors)
            throws Exception {
        Workbook excelfile = null;
        try {
            // 创建工作簿
            excelfile = createWorkbook(xlsfile);
        } catch (Exception e) {
            e.printStackTrace();
            errors.add("读excel文件失败！");
            return;
        }

        // 获取Excel文件中的第一个工作表对象
        Sheet sheet = excelfile.getSheetAt(0);

        // 读取工作表数据（压力容器制造监督检验）
        readPaymentExcelSheet(sheet, infoList, errors);

        // 遍历工作簿中的工作表
        /*
         * for (int numSheets = 0; numSheets <
         * excelfile.getNumberOfSheets();numSheets++){ if
         * (excelfile.getSheetAt(numSheets) != null){ Sheet sheet =
         * excelfile.getSheetAt(numSheets); // 根据序号获取工作表
         * if(sheet.getSheetName().contains("Sheet1")) { // 解析Sheet1工作表
         * readExcelSheet1(sheet, infoList, errors); } } }
         */
    }

    /**
     * 解析工作表（压力容器制造监督检验、特种设备制造监督检验证书（储气井））
     *
     * @param sheet    --
     *                 工作表
     * @param infoList --
     *                 监检报告信息集合
     * @param errors   --
     *                 错误信息
     * @return
     * @author GaoYa
     * @date 2017-01-16 下午04:08:00
     */
    @SuppressWarnings("unchecked")
    private void readPaymentExcelSheet(Sheet sheet,
                                       List<InspectionPayInfo> infoList, List<String> errors) {
        CurrentSessionUser user = super.getCurrentUser();

        int line = 0;
        // 迭代工作表行
        for (Iterator it = sheet.rowIterator(); it.hasNext(); ) {
            if (errors.size() > 1000)
                return;

            Row row = (Row) it.next();
            line++;
            // 验证首行标题行数据格式
            if (line == 1) {
                if (row.getPhysicalNumberOfCells() < Constant.PAYMENT_IMPORT_INFO_TITLES.length) {
                    errors.add(
                            "导入文件中【Sheet1】格式错误，必须含有 " + Constant.PAYMENT_IMPORT_INFO_TITLES.length + " 列！请核实后再导入数据！");
                    return;
                } else {
                    for (int i = 0; i < Constant.PAYMENT_IMPORT_INFO_TITLES.length; i++) {
                        String columnName = readCellData(row, i);
                        if (!Constant.PAYMENT_IMPORT_INFO_TITLES[i].equalsIgnoreCase(columnName)) {
                            errors.add("导入文件中【Sheet1】格式错误，列 " + columnName + " 必须改为 "
                                    + Constant.PAYMENT_IMPORT_INFO_TITLES[i]);
                            return;
                        }
                    }
                }
                continue;
            }

            // 验证是否是空行，如果是空行就跳过，反之读取数据
            boolean nullRow = validateNullRow(row, Constant.PAYMENT_IMPORT_INFO_TITLES.length);
            if (nullRow) {
                continue;
            }

            InspectionPayInfo inspectionPayInfo = new InspectionPayInfo();
            // 受检单位
            String report_com_name = readCellData(row, 0); // 从0开始，表示第一列
            if (StringUtil.isEmpty(report_com_name.trim())) {
                errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 受检单位不能为空！");
                return;
            } else {
                List<EnterInfo> enterList = enterService.queryInfos(report_com_name.trim());
                if (!enterList.isEmpty()) {
                    if (enterList.size() == 1) {
                        EnterInfo enterInfo = enterList.get(0);
                        if (enterInfo != null) {
                            inspectionPayInfo.setReport_com_id(enterInfo.getId());
                        }
                    }
                }
                inspectionPayInfo.setReport_com_name(report_com_name);
            }

            // 开票单位
            String company_name = readCellData(row, 1);
            if (StringUtil.isNotEmpty(company_name.trim())) {
                List<EnterInfo> enterList = enterService.queryInfos(company_name.trim());
                if (!enterList.isEmpty()) {
                    if (enterList.size() == 1) {
                        EnterInfo enterInfo = enterList.get(0);
                        if (enterInfo != null) {
                            inspectionPayInfo.setFk_company_id(enterInfo.getId());
                        }
                    }
                }
                inspectionPayInfo.setCompany_name(company_name.trim());
            } else {
                errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 开票单位不能为空！");
                return;
            }

            // 报告编号
            String report_sn = readCellData(row, 2);
            if (StringUtil.isNotEmpty(report_sn.trim())) {
                try {
                    InspectionInfo info = inspectionInfoService.getInfoByReportSn(report_sn.trim());
                    if (info != null) {
                        inspectionPayInfo.setFk_inspection_info_id(info.getId());
                        inspectionPayInfo.setReport_sn(report_sn.trim());
                        inspectionPayInfo.setCheck_dep_id(info.getCheck_unit_id());
                        if (StringUtil.isEmpty(inspectionPayInfo.getCheck_dep_name())) {
                            Org org = orgManager.getOrg(info.getCheck_unit_id());
                            if (org != null) {
                                inspectionPayInfo.setCheck_dep_name(org.getOrgName());
                            }
                        }
                    } else {
                        errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 系统未找到该报告编号，请检查是否填写正确！");
                        return;
                    }
                } catch (Exception e) {
                    errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 系统未找到该报告编号，请检查是否填写完整！");
                    return;
                }

            } else {
                errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 报告编号不能为空！");
                return;
            }

            // 发票号
            String invoice_no = readCellData(row, 3);
            if (StringUtil.isNotEmpty(invoice_no.trim())) {
                CwInvoiceF cwInvoiceF = cwInvoiceFManager.queryByInvoice_no(invoice_no.trim());
                if (cwInvoiceF == null) {
                    errors.add("亲，系统未能识别发票号（" + invoice_no + "），请联系财务人员导入！");
                    return;
                } else {
                    if ("SY".equals(cwInvoiceF.getStatus())
                            && !cwInvoiceF.getInvoiceUnit().equals(inspectionPayInfo.getCompany_name())) {
                        errors.add("亲，该发票号（" + invoice_no + "）已经使用，请核实！");
                    }
                    if ("ZF".equals(cwInvoiceF.getStatus())) {
                        errors.add("亲，该发票号（" + invoice_no + "）已经作废，不能使用，请核实！");
                        return;
                    }
                }
                inspectionPayInfo.setInvoice_no(invoice_no.trim());
            } else {
                errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 发票号不能为空！");
                return;
            }

            // 合同号
            String pay_no = readCellData(row, 4);
            if (StringUtil.isNotEmpty(pay_no.trim())) {
                inspectionPayInfo.setPay_no(pay_no.trim());
            }/*else{
				String curYearMonth = TS_Util.getCurYearMonth(); // 获取当前年月
				String no = inspectionPayInfoService
						.queryNextPay_no(curYearMonth); // 获取编号后四位序号
				// 编号 = 当前年月 + 4位序号
				inspectionPayInfo.setPay_no(curYearMonth + no);
			}*/

            // 检验部门
            String check_dep_name = readCellData(row, 5);
            if (StringUtil.isNotEmpty(check_dep_name.trim())) {
                inspectionPayInfo.setCheck_dep_name(check_dep_name.trim());
            } else {
                errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 检验部门不能为空！");
                return;
            }

            // 收费方式
            String pay_type = readCellData(row, 6);
            if (StringUtil.isNotEmpty(pay_type.trim())) {
                if ("POS".equals(pay_type.trim())) {
                    inspectionPayInfo.setPay_type("5"); // 收费方式（5：POS）
                } else if ("现金".equals(pay_type.trim())) {
                    inspectionPayInfo.setPay_type("1"); // 收费方式（1：现金）
                } else {
                    errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 收费方式只能为POS！");
                    return;
                }
            } else {
                errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 收费方式不能为空！");
                return;
            }

            // 金额
            String pay_received = readCellData(row, 7);
            if (StringUtil.isNotEmpty(pay_received.trim())) {
                inspectionPayInfo.setPay_receive(pay_received.trim());
                inspectionPayInfo.setPay_received(pay_received.trim());
                if ("POS".equals(pay_type.trim())) {
                    inspectionPayInfo.setPos(pay_received.trim());
                    inspectionPayInfo.setCash_pay("0");
                    inspectionPayInfo.setRemark("0");
                    inspectionPayInfo.setHand_in("0");
                } else if ("现金".equals(pay_type.trim())) {
                    inspectionPayInfo.setCash_pay(pay_received.trim());
                    inspectionPayInfo.setRemark("0");
                    inspectionPayInfo.setPos("0");
                    inspectionPayInfo.setHand_in("0");
                }
            } else {
                errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 金额不能为空！");
                return;
            }

            // 数量
            String report_count = readCellData(row, 8);
            if (StringUtil.isNotEmpty(report_count.trim())) {
                inspectionPayInfo.setReport_count(Integer.parseInt(report_count.trim()));
            } else {
                inspectionPayInfo.setReport_count(1); // 数量默认1
            }

            // 开票日期（未填时，默认为当天）
            String pay_date = readCellData(row, 9);
            if (StringUtil.isNotEmpty(pay_date.trim())) {
                if (pay_date.trim().length() == 10) {
                    if (pay_date.trim().contains("/") || pay_date.trim().contains(".")) {
                        pay_date = pay_date.trim().replaceAll("[./—]", "-");
                    }
                    try {
                        inspectionPayInfo.setPay_date(
                                DateUtil.convertStringToDate(Constant.defaultDatePattern, pay_date.trim()));
                    } catch (ParseException pe) {
                        errors.add("导入文件中【Sheet1】数据有误，第" + line + "行 开票日期为“" + pay_date.trim()
                                + "”格式错误，格式必须为：“2017-01-01”！");
                        return;
                    }
                } else {
                    errors.add(
                            "导入文件中【Sheet1】数据有误，第" + line + "行 开票日期为“" + pay_date.trim() + "”格式错误，格式必须为：“2017-01-01”！");
                    return;
                }
            } else {
                try {
                    inspectionPayInfo.setPay_date(
                            DateUtil.convertStringToDate(Constant.defaultDatePattern, DateUtil.getCurrentDateTime()));
                } catch (ParseException pe) {
                    errors.add(
                            "导入文件中【Sheet1】数据有误，第" + line + "行 开票日期为“" + pay_date.trim() + "”格式错误，格式必须为：“2017-01-01”！");
                    return;
                }
            }

            if (errors.isEmpty()) {
                infoList.add(inspectionPayInfo);
            }
        }
    }

    /**
     * 创建工作簿（暂只支持2003版本的excel）
     *
     * @param importfile --
     *                   Excel文件
     * @return Workbook -- 工作簿
     * @author GaoYa
     * @date 2017-01-16 下午04:08:00
     */
    public Workbook createWorkbook(File importfile) throws Exception {
        // 创建对Excel工作簿文件的引用
        Workbook wb = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(importfile);
            if (importfile.getName().toLowerCase().endsWith("xlsx")) {
                wb = new XSSFWorkbook(fis);
            }
            if (importfile.getName().toLowerCase().endsWith("xls")) {
                wb = new HSSFWorkbook(fis);
            }
            fis.close();
        } catch (Exception e) {
            logger.info("Reading excel file error that " + e);
            log.debug(e.toString());
            throw new Exception("读excel文件失败！");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    log.debug(e.toString());
                    e.printStackTrace();
                }
            }
        }
        return wb;
    }

    /**
     * 读取单元格内容
     *
     * @param row --
     *            行
     * @param col --
     *            列
     * @return String -- 单元格内容
     * @author GaoYa
     * @date 2017-01-16 下午04:08:00
     */
    public String readCellData(Row row, int col) {
        Cell cell = row.getCell((short) col);
        String value = "";
        if (cell != null) {
            int cellType = cell.getCellType();

            switch (cellType) {
                case Cell.CELL_TYPE_FORMULA:
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    value = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    /*
                     * if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                     * value =
                     * Constant.defaultDecimalFormat.format(cell.getNumericCellValue()); }
                     * else if ("General".equals(cell.getCellStyle()
                     * .getDataFormatString())) { value =
                     * Constant.defaultDecimalFormat.format(cell.getNumericCellValue()); }
                     * else { value =
                     * DateUtil.getDateTime(Constant.defaultDatePattern,HSSFDateUtil.getJavaDate(cell
                     * .getNumericCellValue())); }
                     */
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        double d = cell.getNumericCellValue();
                        Date date = HSSFDateUtil.getJavaDate(d);
                        value = DateUtil.getDateTime(Constant.defaultDatePattern,
                                date);
                    } else {
                        DecimalFormat decimalFormat = new DecimalFormat();
                        decimalFormat.setDecimalSeparatorAlwaysShown(false);
                        decimalFormat.setGroupingUsed(false);
                        value = decimalFormat.format(cell.getNumericCellValue());
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    if (cell.getBooleanCellValue()) {
                        value = "true";
                    } else {
                        value = "false";
                    }
                    break;
            }
        } else {
            value = "";
        }
        return value.trim();
    }

    private boolean validateNullRow(Row row, int cell_length) {
        boolean nullRow = true;
        for (int i = 0; i < cell_length; i++) {
            String columnName = readCellData(row, i);
            if (StringUtil.isNotEmpty(columnName)) {
                nullRow = false;
            }
        }
        return nullRow;
    }


    @RequestMapping(value = "showTwoDim")
    public void showTwoDim(HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        try {
            HashMap<String, Object> map = new HashMap<String, Object>();
            String filePath = null;
            String id = request.getParameter("id");
            filePath = inspectionPayInfoService.showTwoDim(id, request);
            System.out.println("-----------------" + filePath);
            FileUtil.download(response, filePath, "", "application/octet-stream");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getMobileInfo")
    @ResponseBody
    public HashMap<String, Object> getMobileInfo(String id,
                                                 HttpServletRequest request) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        /*InspectionPayInfo ins=inspectionPayInfoService.getByinspectionInfoId(id);*/
        InspectionPayInfo ins = inspectionPayInfoService.get(id);
        List<InspectionInfo> infos = inspectionInfoService.getInfoListByIds(ins.getFk_inspection_info_id());
        map.put("payId", ins.getId());
        String pp = ins.getFk_inspection_info_id().trim();
        map.put("infoIds", pp);
        map.put("infos", infos);
        map.put("success", true);
        return map;
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "mobelTwodraw")
    @ResponseBody
    public HashMap<String, Object> mobelTwodraw(HttpServletRequest request, String infoIds) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String filePath = inspectionPayInfoService.showTwoDim2(request, infoIds);
        map.put("filePath", filePath);
        map.put("success", true);
        return map;
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "mobelTwodraw3")
    @ResponseBody
    public HashMap<String, Object> mobelTwodraw3(HttpServletRequest request, String path) throws Exception {
        path = "20170105/CO-TD201701522/CO-TD201701522.pdf";
        String[] xx = path.split("/");
        String xxx = "";
        for (int i = 0; i < xx.length; i++) {
            if (i == xx.length - 1) {
                xxx = xxx + xx[i];
            } else {
                xxx = xxx + xx[i] + File.separator;
            }
        }
        String inputPath = "D:" + File.separator + "gongCheng" + File.separator
                + "shengyuan" + File.separator + "SYTS" + File.separator + "src" + File.separator
                + "main" + File.separator + "webapp" + File.separator + "upload" + File.separator + xxx;
        String outputPath = "E:" + File.separator + "hiberder" + File.separator
                + "pdf" + File.separator + "upload" + File.separator + xxx;

        File output = new File(outputPath);
        if (!output.getParentFile().exists()) {
            output.getParentFile().mkdirs();
        }
        if (!output.exists()) {
            output.createNewFile();
        } else {
            output.delete();
            output.createNewFile();
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        File input = new File(inputPath);       //括号里参数为文件图片路径
        if (input.exists()) {
            InputStream in = new FileInputStream(input);   //用该文件创建一个输入流
            OutputStream os = new FileOutputStream(outputPath);
            byte[] b = new byte[1024];
            while (in.read(b) != -1) {
                os.write(b);
            }
            in.close();
            os.flush();
            os.close();
        }
        return map;
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "infoidds")
    @ResponseBody
    public HashMap<String, Object> infoidds(HttpServletRequest request, String id) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String infoidds = inspectionPayInfoService.infoidds(request, id);
        map.put("infoidds", infoidds);
        map.put("success", true);
        return map;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getRefRep")
    @ResponseBody
    public HashMap<String, Object> getRefRep(HttpServletRequest request, String id) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        List ids = inspectionPayInfoService.getRefRep(id);
        map.put("data", ids);
        map.put("success", true);
        return map;
    }


    // 缴费结算单打印
    @RequestMapping(value = "doPrint2")
    public ModelAndView doPrint2(HttpServletRequest request, String id) {
        try {
            InspectionPayInfo inspectionPayInfo = inspectionPayInfoService
                    .queryByInspectionInfoID(id); // 报检业务收费信息
            if (inspectionPayInfo == null) {
                inspectionPayInfo = new InspectionPayInfo();
            }
            List<InspectionInfoDTO> inspectionInfoDTOList = new ArrayList<InspectionInfoDTO>(); // 业务收费信息列表
            String[] ids = id.split(",");
            if (StringUtil.isNotEmpty(inspectionPayInfo
                    .getFk_inspection_info_id())) {
                ids = inspectionPayInfo.getFk_inspection_info_id().split(",");
            }
            float advance_fees = 0.00f;
            float receivable = 0.00f;
            for (int i = 0; i < ids.length; i++) { // 报检业务信息
                InspectionInfo inspectionInfo = inspectionInfoService
                        .get(ids[i]);
                if (inspectionInfo != null) {
                    InspectionInfoDTO inspectionInfoDTO = new InspectionInfoDTO();
                    inspectionInfoDTO.setId(inspectionInfo.getId());
                    inspectionInfoDTO
                            .setSn(StringUtil.isNotEmpty(inspectionInfo.getSn()) ? inspectionInfo
                                    .getSn() : ""); // 业务流水号
                    inspectionInfoDTO.setCom_id(inspectionInfo.getInspection()
                            .getFk_unit_id()); // 报检单位ID
                    inspectionInfoDTO.setCom_name(inspectionInfo
                            .getInspection().getCom_name()); // 报检单位名称
                    inspectionInfoDTO.setCheck_type(inspectionInfo
                            .getInspection().getCheck_type()); // 检验类别
                    if (StringUtil
                            .isNotEmpty(inspectionInfo.getCheck_unit_id())) {
                        Org org = orgManager.get(inspectionInfo
                                .getCheck_unit_id());
                        inspectionInfoDTO.setCheck_department(StringUtil
                                .isNotEmpty(org.getOrgName()) ? org
                                .getOrgName() : ""); // 检验部门
                    } else {
                        inspectionInfoDTO.setCheck_department("");
                    }

                    // 获取设备信息
                    DeviceDocument deviceDocument = deviceService
                            .get(inspectionInfo.getFk_tsjc_device_document_id());
                    if (deviceDocument != null) {
                        inspectionInfoDTO.setDevice_sort_code(deviceDocument
                                .getDevice_sort_code()); // 设备类别
                        inspectionInfoDTO.setDevice_registration_code(deviceDocument.getDevice_registration_code());
                        inspectionInfoDTO.setDevice_name(deviceDocument
                                .getDevice_name()); // 设备名称
                    }

                    inspectionInfoDTO
                            .setAdvance_fees(inspectionInfo.getAdvance_fees() != null
                                    && inspectionInfo.getAdvance_fees() != 0 ? inspectionInfo
                                    .getAdvance_fees() : 0); // 应收金额
                    inspectionInfoDTO
                            .setReceivable(inspectionInfo.getReceivable() != null
                                    && inspectionInfo.getReceivable() != 0 ? inspectionInfo
                                    .getReceivable() : 0); // 实收金额
                    advance_fees += inspectionInfoDTO.getAdvance_fees();
                    receivable += inspectionInfoDTO.getReceivable();
                    inspectionInfoDTO
                            .setReport_sn(StringUtil.isNotEmpty(inspectionInfo
                                    .getReport_sn()) ? inspectionInfo
                                    .getReport_sn() : ""); // 报告书编号
                    inspectionInfoDTO
                            .setReport_com_name(StringUtil
                                    .isNotEmpty(inspectionInfo
                                            .getReport_com_name()) ? inspectionInfo
                                    .getReport_com_name() : ""); // 报告书使用单位
                    inspectionInfoDTO
                            .setAdvance_remark(StringUtil
                                    .isNotEmpty(inspectionInfo
                                            .getAdvance_remark()) ? inspectionInfo
                                    .getAdvance_remark() : ""); // 预收金额备注
                    inspectionInfoDTOList.add(inspectionInfoDTO);
                }
            }
            inspectionPayInfo.setPay_receive(String.valueOf(advance_fees)); // 应收总金额
            inspectionPayInfo.setInspectionInfoDTOList(inspectionInfoDTOList);

            // 银行转账
            List<CwBankDTO> cwBankDTOList = new ArrayList<CwBankDTO>();
            if (StringUtil.isNotEmpty(inspectionPayInfo.getFk_cw_bank_id())) {
                String[] bank_ids = inspectionPayInfo.getFk_cw_bank_id().split(
                        ",");
                for (int i = 0; i < bank_ids.length; i++) {
                    CwBankDetail cwBankDetail = cwBankDetailManager
                            .get(bank_ids[i]);
                    if (cwBankDetail != null) {
                        CwBankDTO cwBankDTO = new CwBankDTO();
                        BeanUtils.copyProperties(cwBankDTO, cwBankDetail);
                        CwBank2Pay cwBank2Pay = cwBank2PayManager
                                .queryCwBank2Pay(bank_ids[i], id);
                        if (cwBank2Pay != null) {
                            cwBankDTO
                                    .setUsedMoney(TS_Util
                                            .ratioTransform(cwBank2Pay
                                                    .getCur_used_money()
                                                    .doubleValue()));
                            cwBankDTO.setPay_remark(cwBank2Pay.getPay_remark());
                        }
                        cwBankDTOList.add(cwBankDTO);
                    }
                }
            } else {
                List<CwBank2Pay> list = cwBank2PayManager
                        .queryCwBank2Pays(inspectionPayInfo.getId());
                if (!list.isEmpty()) {
                    for (CwBank2Pay cwBank2Pay : list) {
                        if (cwBank2Pay != null) {
                            CwBankDetail cwBankDetail = cwBankDetailManager
                                    .get(cwBank2Pay.getFk_cw_bank_id());
                            if (cwBankDetail != null) {
                                CwBankDTO cwBankDTO = new CwBankDTO();
                                BeanUtils.copyProperties(cwBankDTO,
                                        cwBankDetail);
                                cwBankDTO.setUsedMoney(TS_Util
                                        .ratioTransform(cwBank2Pay
                                                .getCur_used_money()
                                                .doubleValue()));
                                cwBankDTO.setPay_remark(cwBank2Pay
                                        .getPay_remark());
                                cwBankDTOList.add(cwBankDTO);
                            }
                        }
                    }
                }
            }
            inspectionPayInfo.setCwBankDTOList(cwBankDTOList);

            String pay_received = inspectionPayInfo.getPay_received();
            if (StringUtil.isNotEmpty(pay_received)) {
                Double pay = Double.parseDouble(pay_received);
                inspectionPayInfo.setPay_received(String.valueOf(pay));
            }
            String pos = inspectionPayInfo.getPos();
            if (StringUtil.isNotEmpty(pos)) {
                Double pos_money = Double.parseDouble(pos);
                inspectionPayInfo.setPos(String.valueOf(pos_money));
            }
            String cash_pay = inspectionPayInfo.getCash_pay();
            if (StringUtil.isNotEmpty(cash_pay)) {
                Double cash = Double.parseDouble(cash_pay);
                inspectionPayInfo.setCash_pay(String.valueOf(cash));
            }
            request.getSession().setAttribute("inspectionPayInfo",
                    inspectionPayInfo);
            HashMap<String, Object> map1 = this.getPayInfoSign(id);
            request.setAttribute("image", map1);
        } catch (Exception e) {
            e.printStackTrace();
            KhntException kh = new KhntException(e);
            log.error(kh.getMessage());
        }
        ModelAndView mav = new ModelAndView("app/payment/payPrinting");
        return mav;
    }

    /**
     * 获取收费信息，缴费人签字，电话等
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("getPayInfoSign")
    @ResponseBody
    public HashMap<String, Object> getPayInfoSign(String id) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            map = inspectionPayInfoService.getPayInfoSign(id);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", true);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @RequestMapping("queryAppSign")
    @ResponseBody
    public HashMap<String, Object> queryAppSign(String id, String cid, String payType) throws Exception {
        String fkinsptionInfoId = id;
        List<InspectionPayInfo> payInfo = inspectionPayInfoService.queryInspectionPayInfo1(fkinsptionInfoId);
        if (payInfo.size() > 0) {
            String fkInspectionInfoId = payInfo.get(0).getFk_inspection_info_id();
            String payNo = payInfo.get(0).getPay_no();//编号
            String companyName = payInfo.get(0).getCompany_name();//开票单位
            String invoiceNo = payInfo.get(0).getInvoice_no();//发票编号
            if (StringUtil.isNotEmpty(fkInspectionInfoId)) {
                String str = "(";
                for (int i = 0; i < fkInspectionInfoId.split(",").length; i++) {
                    String fkInfoId = fkInspectionInfoId.split(",")[i].toString();
                    if ((fkInspectionInfoId.split(",").length - 1) == i) {
                        str = str + "'" + fkInfoId + "')";
                    } else {
                        str = str + "'" + fkInfoId + "',";
                    }
                }
                List<Map<String, Object>> list = inspectionPayInfoService.queryAppSign(str);
                List<Map<String, Object>> listPay = new ArrayList<Map<String, Object>>();
                if (list.size() > 0) {
                    String reportComName = "";
                    String orgName = "";
                    for (int i = 0; i < list.size(); i++) {
                        String bid = list.get(i).get("ID").toString();//报检业务数据传输对象
                        if (i == 0) {
                            reportComName = list.get(0).get("REPORT_COM_NAME") == null ? "" : list.get(0).get("REPORT_COM_NAME").toString();//受检单位
                            orgName = list.get(0).get("ORG_NAME") == null ? "" : list.get(0).get("ORG_NAME").toString();//检验部门
                        }
                    }
                    Map<String, Object> map1 = new HashMap<String, Object>();
                    map1.put("payInfoId", payInfo.get(0).getId());

                    map1.put("reportComName", reportComName);
                    map1.put("payNo", payNo);
                    map1.put("companyName", companyName);
                    map1.put("orgName", orgName);
                    map1.put("invoiceNo", invoiceNo);
                    map1.put("needPay", "12".equals(payType) || "13".equals(payType) || "14".equals(payType) || "15".equals(payType));
                    if ("12".equals(payType) || "14".equals(payType)) {
                        map1.put("needPayType", "alipay");
                        map1.put("needPA", payInfo.get(0).getAlipay_mobile());
                    } else if ("13".equals(payType) || "15".equals(payType)) {
                        map1.put("needPayType", "wechat");
                        map1.put("needPA", payInfo.get(0).getWechat_mobile());
                    }

                    JSONObject obj = new JSONObject();
                    obj.put("title", "推送测试");
                    obj.put("userName", "");
                    obj.put("state", 0);
                    obj.put("pageUrl", "../../app/report/printing.html");
                    obj.put("pageId", "../../app/report/printing.html");
                    obj.put("toastMsg", "state 2 需要弹出的提示消息");
                    obj.put("isDialog", false);
                    obj.put("pageFunction", "recivemsg");
                    obj.put("params", map1);
                    String title = "四川省特种设备检验研究院检验收费结算单";
                    String content = "受检单位为：" + reportComName + "的结算单";

                    List<String> cids = new ArrayList<String>();
                    cids.add(cid);
                    pushManager.pusMsgToTargets(obj, title, content, "财务待收费", cids);
                }
            }
            return JsonWrapper.successWrapper();
        } else {
            return JsonWrapper.failureWrapperMsg("该报告未保存领取信息，请先保存再打印！");
        }
    }
    /**
     * 获取财务收费时签名和电话
     * @param request
     * @param fkInspectionInfoId
     * @return
     * @throws Exception
     */
    @RequestMapping("querySignImgAndTel")
    @ResponseBody
    public HashMap<String, Object> querySignImgAndTel(HttpServletRequest request,String fkInspectionInfoId,String invoice_no) throws Exception{
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	try{
    		HashMap<String, Object> signInfoMap = inspectionPayInfoService.getPayInfoSignAndTel(request,fkInspectionInfoId,invoice_no);
    		map.put("success", true);
    		map.put("data", signInfoMap);
    	}catch(Exception e){
    		e.printStackTrace();
    		map.put("success", false);
    		map.put("msg", e.getMessage());
    	}
    	return map;
    }
    

    /**
     * 验证收费单信息是否保存
     *
     * @param fkInspectionInfoId
     * @return
     */
    @RequestMapping("isNotSave")
    @ResponseBody
    public HashMap<String, Object> isNotSave(String fkInspectionInfoId) {
//		List<InspectionPayInfo> list= inspectionPayInfoService.queryInspectionPayInfo(fkInspectionInfoId.split(",")[0].toString());
        List<InspectionPayInfo> list = inspectionPayInfoService.queryInspectionPayInfo1(fkInspectionInfoId);
        if (list.size() > 0) {
            String id = list.get(0).getId().toString();
            List<Map<String, Object>> listSign = inspectionPayInfoService.queryInspectionPayInfoSign(id);
            if (listSign.size() > 0) {
                return JsonWrapper.failureWrapperMsg("已保存收费信息并签字，请勿反复保存！");
            } else {
                return JsonWrapper.failureWrapperMsg("已保存收费信息，请耐心等待签字确认！");
            }
        } else {
            return JsonWrapper.successWrapper();
        }
    }

    /**
     * 打印时验证收费信息是否保存
     *
     * @param fkInspectionInfoId
     * @return
     */
    @RequestMapping("queryPrintingPaySign")
    @ResponseBody
    public HashMap<String, Object> queryPrintingPaySign(HttpServletRequest request,String fkInspectionInfoId) {
    	InspectionPayInfo payInfo = inspectionPayInfoService.queryPayInfoCache(request,fkInspectionInfoId);
        if (null != payInfo) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("fkPayInfoId", payInfo.getId());
            return JsonWrapper.successWrapper(map);
        } else {
            return JsonWrapper.failureWrapperMsg("亲，该收费单未保存，请先保存，耐心等待平板签名！");
        }
    }

    /**
     * 打印时验证收费信息是否保存
     *
     * @param fkInspectionInfoId
     * @return
     */
    @RequestMapping("queryPrintingPaySign1")
    @ResponseBody
    public HashMap<String, Object> queryPrintingPaySign1(String fkInspectionInfoId) {
//		List<InspectionPayInfo> list= inspectionPayInfoService.queryInspectionPayInfo(fkInspectionInfoId.split(",")[0].toString());
        List<InspectionPayInfo> list = inspectionPayInfoService.queryInspectionPayInfo1(fkInspectionInfoId);
        if (list.size() > 0) {
            Map<String, Object> map = new HashMap<String, Object>();
            String fkPayInfoId = list.get(0).getId().toString();
            map.put("fkPayInfoId", fkPayInfoId);
            return JsonWrapper.successWrapper(map);
        } else {
            return JsonWrapper.failureWrapperMsg("亲，该收费单未保存，请先保存，耐心等待平板签名！");
        }
    }

    /**
     * 验证该收费结算单是否签字
     *
     * @param fkPayInfoId
     * @return
     */
    @RequestMapping("isNotPrintingPaySign")
    @ResponseBody
    public HashMap<String, Object> isNotPrintingPaySign(String fkPayInfoId) {
        List<Map<String, Object>> list = inspectionPayInfoService.queryInspectionPayInfoSign(fkPayInfoId);
        String pathFile = "";
        if (list.size() > 0) {
            String file = list.get(0).get("BASE64_SIGN_FILE") == null ? "" : list.get(0).get("BASE64_SIGN_FILE").toString();
            if (StringUtil.isNotEmpty(file)) {
                pathFile = "../../" + file.replaceAll("\\\\", "\\/");
            } else {
                pathFile = "";
            }
            return JsonWrapper.successWrapper(pathFile);
        } else {
            return JsonWrapper.successWrapper("该收费结算单未签字确认");
        }
    }


    public HashMap<String, Object> payPrinting(String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            map = inspectionPayInfoService.getPayInfoSign(id);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String image = map.get("image").toString();
        return map;
    }

    /**
     * 收费结算单，查询设备注册代码、报告书编号、检验费信息
     *
     * @param fkInspectionInfoId
     * @return
     */
    @RequestMapping("queryInspectionInfo")
    @ResponseBody
    public HashMap<String, Object> queryInspectionInfo(String fkInspectionInfoId) {
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
//			List<InspectionPayInfo> payInfo=inspectionPayInfoService.queryInspectionPayInfo(fkInspectionInfoId.split(",")[0].toString());
//			List<InspectionPayInfo> payInfo=inspectionPayInfoService.queryInspectionPayInfo1(fkInspectionInfoId);
        List<InspectionPayInfo> payInfo = inspectionPayInfoService.queryInspectionPayInfo2(fkInspectionInfoId);
        if (payInfo.size() > 0) {
            String fkInspeInfoId = payInfo.get(0).getFk_inspection_info_id();
            String payNo = payInfo.get(0).getPay_no();//编号
            String companyName = payInfo.get(0).getCompany_name();//开票单位
            String invoiceNo = payInfo.get(0).getInvoice_no();//发票编号
            if (StringUtil.isNotEmpty(fkInspeInfoId)) {
                String str = "(";
                for (int i = 0; i < fkInspeInfoId.split(",").length; i++) {
                    String fkInfoId = fkInspeInfoId.split(",")[i].toString();
                    if ((fkInspeInfoId.split(",").length - 1) == i) {
                        str = str + "'" + fkInfoId + "')";
                    } else {
                        str = str + "'" + fkInfoId + "',";
                    }
                }
                List<Map<String, Object>> list = inspectionPayInfoService.queryAppSign(str);
                if (list.size() > 0) {
                    String reportComName = "";
                    String orgName = "";
                    for (int i = 0; i < list.size(); i++) {
                        String bid = list.get(i).get("ID").toString();//报检业务数据传输对象
                        String reportSn = list.get(i).get("REPORT_SN") == null ? "" : list.get(i).get("REPORT_SN").toString();//报告/证书编号
//							String sbzcdm=list.get(i).get("DEVICE_REGISTRATION_CODE")==null?"":list.get(i).get("DEVICE_REGISTRATION_CODE").toString();//设备注册代码
                        String sbzcdm = list.get(i).get("SBZCDM") == null ? "" : list.get(i).get("SBZCDM").toString();//设备注册代码
                        String advanceFees = list.get(i).get("ADVANCE_FEES") == null ? "" : list.get(i).get("ADVANCE_FEES").toString();//检验费
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("reportSn", reportSn);
                        map.put("sbzcdm", sbzcdm);
                        map.put("advanceFees", advanceFees);
                        listMap.add(map);
                    }
                }
            }
        }
        return JsonWrapper.successWrapper(listMap);
    }

    /**
     * 获取收费结算单签名图片
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("getPayInfoSignPath")
    @ResponseBody
    public HashMap<String, Object> getPayInfoSignPath(String id) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            map = inspectionPayInfoService.getPayInfoSign(id);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", true);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    /**
     * 验证收费单签名推送
     *
     * @param fkInspectionInfoId
     * @return
     */
    @RequestMapping("signPush")
    @ResponseBody
    public HashMap<String, Object> signPush(String fkInspectionInfoId) {
        List<InspectionPayInfo> list = inspectionPayInfoService.queryInspectionPayInfo(fkInspectionInfoId.split(",")[0].toString());
        if (list.size() > 0) {
            String id = list.get(0).getId().toString();
            List<Map<String, Object>> listSign = inspectionPayInfoService.queryInspectionPayInfoSign(id);
            if (listSign.size() > 0) {
                return JsonWrapper.failureWrapperMsg("已保存签名图片，请勿反复推送！");
            } else {
                return JsonWrapper.successWrapper();
            }
        } else {
            return JsonWrapper.successWrapper();
        }
    }

    /**
     * 添加设备cid
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("addPanelComputer")
    @ResponseBody
    public HashMap<String, Object> addPanelComputer() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pulldown_op", "pulldown_op");
        map.put("pulldown_op1", "pulldown_op");
        map.put("linkmode", "linkmode");
        map.put("linkmode1", "linkmode1");
        map.put("linkmode", "linkmode");
        map.put("report_sn1", "report_sn1");
        map.put("fkInspectionInfoId", "fkInspectionInfoId");
        map.put("fkInspectionInfoId1", "fkInspectionInfoId1");
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list.add(map);
        String jsonStr = JSONArray.fromObject(list).toString();
        JSONArray ja = JSONArray.fromObject(jsonStr);
        JSONObject obj = new JSONObject();
        obj.put("title", "个推测试");
        obj.put("userName", "");
        obj.put("state", 0);
        obj.put("pageUrl", "../../app/report/test.html");
        obj.put("pageId", "../../app/report/test.html");
        obj.put("toastMsg", "state 2 需要弹出的提示消息");
        obj.put("isDialog", false);
        obj.put("pageFunction", "recivemsg");
        obj.put("params", ja.toString());
        String title = "添加平板cid";
        String content = "消息推送，添加平板";
        pushManager.pusMsgToAll(obj, title, content, "添加设备cid");
        return JsonWrapper.successWrapper();
    }

    @RequestMapping(value="selectReportSn")
    @ResponseBody
    public HashMap<String, Object> selectReportSn(String reportSn) throws Exception{
    	try{
	        String reportSnFour = "CO-QA,CO-PA,CO-RW,CO-YW,CO-NW,CO-SD,CO-PJ,CO-YJ,CO-YD,CO-GA,CO-FD,CO-QD,CO-RD,CO-ND,CO-GD,CO-BJ,CO-HD,CO-ZW,CO-TD,CO-HJ,CO-NA,CO-GJ,CO-FJ,CO-DD,CO-TP,CO-TA,CO-FW,CO-YA,CO-RK,CO-DA,CO-TW,CO-BW,CO-RA,CO-RJ,CO-DJ,CO-QW,CO-WW";
	        String reportSnFour1 = "CO-QA(,CO-PA(,CO-RW(,CO-YW(,CO-NW(,CO-SD(,CO-PJ(,CO-YJ(,CO-YD(,CO-GA(,CO-FD(,CO-QD(,CO-RD(,CO-ND(,CO-GD(,CO-BJ(,CO-HD(,CO-ZW(,CO-TD(,CO-HJ(,CO-NA(,CO-GJ(,CO-FJ(,CO-DD(,CO-TP(,CO-TA(,CO-FW(,CO-YA(,CO-RK(,CO-DA(,CO-TW(,CO-BW(,CO-RA(,CO-RJ(,CO-DJ(,CO-QW(,CO-WW(";
	        String[] arrs = reportSnFour.split(","); // 用,分割,转数组
	        String[] arr = reportSn.split(","); // 用,分割,转数组
	//		String[] arrFour1=reportSnFour1.split(",");
	        String[] arrFour1 = reportSnFour.split(",");
	        Arrays.sort(arrFour1);
	        Arrays.sort(arr);
	        Arrays.sort(arrs);
	        System.out.println(arrs.length);
	        String str2 = ArrayUtils.toString(arrs, ",");
	        System.out.println(str2);
	        String report_sn = "";
	
	        /*start*/
	        //封装数组（截取字符串）
	        String reportSnNum = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
	        Float[] repSnNum = new Float[reportSnNum.length()];
	
	        //封装数组（字符串个数）
	        String rsn = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
	        Integer[] rsNumber = new Integer[rsn.length()];
	        String yearR = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
	        Float[] yearRNum = new Float[yearR.length()];
	        /*end*/
	
	        /*start,r开头*/
	        String rSNFour = "RDJ-,RGJ-,RJJ-,RWJ-";
	        String[] rSNFourS = rSNFour.split(",");
	        String rSNFour1 = "RDJ-(,RGJ-(,RJJ-(,RWJ-(";
	        String[] rSNFour1S = rSNFour1.split(",");
	        //封装数组（截取字符串）
	        String rkt = "0,0,0,0";
	        Float[] rktNum = new Float[rkt.length()];
	        //封装数组（字符串个数）
	        String rktNum1 = "0,0,0,0";
	        Integer[] rktNumber = new Integer[rktNum1.length()];
	        //封装数组（年份）
	        String year = "0,0,0,0";
	        Float[] yearNum = new Float[year.length()];
	        /*end*/
	
	        String wcygcd = "";//w开头
	        /*start，TSWJGLSC123开头*/
	        String tswjglsc = "TSWJGLSC123-(";
	        String[] tswaArrs = tswjglsc.split(","); // 用,分割,转数组
	        /*start，TSWJGLSC123开头*/
	        //封装数组（截取字符串）
	        String wNum = "0";
	        Integer[] wrepSnNum = new Integer[wNum.length()];
	        //封装数组（字符串个数）
	        String wrsn = "0";
	        Integer[] wrsNumber = new Integer[wrsn.length()];
	        /*end*/
	
	        /*start ,W开头*/
	        String wYear = "W";
	        String[] wYearArrs = wYear.split(","); // 用,分割,转数组
	        //封装数组（截取字符串）
	        String wyearNum = "0";
	        Float[] wyearNumSnNum = new Float[wyearNum.length()];
	        //封装数组（字符串个数）
	        String wyearrsn = "0";
	        Integer[] wYearrsNumber = new Integer[wyearrsn.length()];
	        //封装数组（年份）
	        String yearW = "0";
	        Float[] yearWNum = new Float[yearW.length()];
	        /*end*/
	
	        for (int i = 0; i < arr.length; i++) {
	            String repSn = arr[i];
	            String repSnOne = repSn.substring(0, 1);//截取前1位字符串
	            String repSnFour = repSn.substring(0, 4);//截取前4位字符串
	            String repSnFive = repSn.substring(0, 5);//截取前5位字符串
	            String repSnTwenty = repSn.substring(0, 12);//截取前5位字符串
	
	            if (repSnOne.equals("W")) {
	//				wcygcd=wcygcd+repSn+",";
	                String between = repSn.substring(6, 11);
	                String lastEleven = repSn.substring(11, repSn.length());//截取后5位
	                float ElevenNum = Float.parseFloat(lastEleven);
	                int wyears = Integer.parseInt(repSn.substring(1, 5));
	                ReportNum.wOne(yearWNum, wyearNumSnNum, ElevenNum, wYearrsNumber, wYearArrs, lastEleven, 0, wyears, between);
	
	            } else {
	                if (repSnFour.equals("RDJ-")) {
	//					rdj=rdj+repSn.substring(4, repSn.length())+",";
	                    String lastFour = repSn.substring(9, repSn.length());//截取第4位后的字符串
	                    float fourNum = Float.parseFloat(lastFour);
	                    int years = Integer.parseInt(repSn.substring(4, 8));
	                    ReportNum.threeNum(yearNum, rktNum, fourNum, rktNumber, rSNFourS, lastFour, 0, years);
	                } else if (repSnFour.equals("RGJ-")) {
	//					rgj=rgj+repSn.substring(4, repSn.length())+",";
	                    String lastFour = repSn.substring(9, repSn.length());//截取第4位后的字符串
	                    float fourNum = Float.parseFloat(lastFour);
	                    int years = Integer.parseInt(repSn.substring(4, 8));
	                    ReportNum.threeNum(yearNum, rktNum, fourNum, rktNumber, rSNFourS, lastFour, 0, years);
	                } else if (repSnFour.equals("RJJ-")) {
	//					rjj=rjj+repSn.substring(4, repSn.length())+",";
	                    String lastFour = repSn.substring(9, repSn.length());//截取第4位后的字符串
	                    float fourNum = Float.parseFloat(lastFour);
	                    int years = Integer.parseInt(repSn.substring(4, 8));
	                    ReportNum.threeNum(yearNum, rktNum, fourNum, rktNumber, rSNFourS, lastFour, 0, years);
	                } else if (repSnFour.equals("RWJ-")) {
	//					rwj=rwj+repSn.substring(4, repSn.length())+",";
	                    String lastFour = repSn.substring(9, repSn.length());//截取第4位后的字符串
	                    float fourNum = Float.parseFloat(lastFour);
	                    int years = Integer.parseInt(repSn.substring(4, 8));
	                    ReportNum.threeNum(yearNum, rktNum, fourNum, rktNumber, rSNFourS, lastFour, 0, years);
	                } else {
	                    if (repSnTwenty.equals("TSWJGLSC123-")) {
	//						tswjglsc=tswjglsc+repSn.substring(12, repSn.length())+",";
	                        String lastTwenty = repSn.substring(12, repSn.length());//截取后5位
	                        int twentyNum = Integer.parseInt(lastTwenty);
	                        ReportNum.tswjglscNum(wrepSnNum, twentyNum, wrsNumber, tswaArrs, lastTwenty, 0);
	                    } else {
	                        /*start*/
	                        String lastFive = repSn.substring(9, repSn.length());//截取第9位后的字符串
	                        float fiveNum = Float.parseFloat(lastFive);
	                        int yearRs = Integer.parseInt(repSn.substring(5, 9));
	                        /*end*/
	                        if (repSnFive.equals(arrs[0])) {
	//							arrFour1[0]=arrFour1[0]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 0, yearRs);
	                        } else if (repSnFive.equals(arrs[1])) {
	//							arrFour1[1]=arrFour1[1]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 1, yearRs);
	                        } else if (repSnFive.equals(arrs[2])) {
	//							arrFour1[2]=arrFour1[5]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 2, yearRs);
	                        } else if (repSnFive.equals(arrs[3])) {
	//							arrFour1[3]=arrFour1[3]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 3, yearRs);
	                        } else if (repSnFive.equals(arrs[4])) {
	//							arrFour1[4]=arrFour1[4]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 4, yearRs);
	                        } else if (repSnFive.equals(arrs[5])) {
	//							arrFour1[5]=arrFour1[5]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 5, yearRs);
	                        } else if (repSnFive.equals(arrs[6])) {
	//							arrFour1[6]=arrFour1[6]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 6, yearRs);
	                        } else if (repSnFive.equals(arrs[7])) {
	//							arrFour1[7]=arrFour1[7]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 7, yearRs);
	                        } else if (repSnFive.equals(arrs[8])) {
	//							arrFour1[8]=arrFour1[8]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 8, yearRs);
	                        } else if (repSnFive.equals(arrs[9])) {
	//							arrFour1[9]=arrFour1[9]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 9, yearRs);
	                        } else if (repSnFive.equals(arrs[10])) {
	//							arrFour1[10]=arrFour1[10]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 10, yearRs);
	                        } else if (repSnFive.equals(arrs[11])) {
	//							arrFour1[11]=arrFour1[11]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 11, yearRs);
	                        } else if (repSnFive.equals(arrs[12])) {
	//							arrFour1[12]=arrFour1[12]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 12, yearRs);
	                        } else if (repSnFive.equals(arrs[13])) {
	//							arrFour1[13]=arrFour1[13]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 13, yearRs);
	                        } else if (repSnFive.equals(arrs[14])) {
	//							arrFour1[14]=arrFour1[14]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 14, yearRs);
	                        } else if (repSnFive.equals(arrs[15])) {
	//							arrFour1[15]=arrFour1[15]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 15, yearRs);
	                        } else if (repSnFive.equals(arrs[16])) {
	//							arrFour1[16]=arrFour1[16]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 16, yearRs);
	                        } else if (repSnFive.equals(arrs[17])) {
	//							arrFour1[17]=arrFour1[17]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 17, yearRs);
	                        } else if (repSnFive.equals(arrs[18])) {
	//							arrFour1[18]=arrFour1[18]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 18, yearRs);
	                        } else if (repSnFive.equals(arrs[19])) {
	//							arrFour1[19]=arrFour1[19]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 19, yearRs);
	                        } else if (repSnFive.equals(arrs[20])) {
	//							arrFour1[20]=arrFour1[20]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 20, yearRs);
	                        } else if (repSnFive.equals(arrs[21])) {
	//							arrFour1[21]=arrFour1[21]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 21, yearRs);
	                        } else if (repSnFive.equals(arrs[22])) {
	//							arrFour1[22]=arrFour1[22]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 22, yearRs);
	                        } else if (repSnFive.equals(arrs[23])) {
	//							arrFour1[23]=arrFour1[23]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 23, yearRs);
	                        } else if (repSnFive.equals(arrs[24])) {
	//							arrFour1[24]=arrFour1[24]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 24, yearRs);
	                        } else if (repSnFive.equals(arrs[25])) {
	//							arrFour1[25]=arrFour1[25]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 25, yearRs);
	                        } else if (repSnFive.equals(arrs[26])) {
	//							arrFour1[26]=arrFour1[26]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 26, yearRs);
	                        } else if (repSnFive.equals(arrs[27])) {
	//							arrFour1[27]=arrFour1[27]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 27, yearRs);
	                        } else if (repSnFive.equals(arrs[28])) {
	//							arrFour1[28]=arrFour1[28]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 28, yearRs);
	                        } else if (repSnFive.equals(arrs[29])) {
	//							arrFour1[29]=arrFour1[29]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 29, yearRs);
	                        } else if (repSnFive.equals(arrs[30])) {
	//							arrFour1[30]=arrFour1[30]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 30, yearRs);
	                        } else if (repSnFive.equals(arrs[31])) {
	//							arrFour1[31]=arrFour1[31]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 31, yearRs);
	                        } else if (repSnFive.equals(arrs[32])) {
	//							arrFour1[32]=arrFour1[32]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 32, yearRs);
	                        } else if (repSnFive.equals(arrs[33])) {
	//							arrFour1[33]=arrFour1[33]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 33, yearRs);
	                        } else if (repSnFive.equals(arrs[34])) {
	//							arrFour1[34]=arrFour1[34]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 34, yearRs);
	                        } else if (repSnFive.equals(arrs[35])) {
	//							arrFour1[35]=arrFour1[35]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 35, yearRs);
	                        } else if (repSnFive.equals(arrs[36])) {
	//							arrFour1[36]=arrFour1[36]+repSn.substring(5,repSn.length())+",";
	                            ReportNum.fourNum(yearRNum, repSnNum, fiveNum, rsNumber, arrFour1, lastFive, 36, yearRs);
	                        }
	                    }
	                }
	            }
	        }
	
	        String wYearArrsSn = "";
	        for (int i = 0; i < wYearArrs.length; i++) {
	            if (wYearArrs[i].length() > 13) {
	                if (wYearArrs[i].substring(wYearArrs[i].length() - 1, wYearArrs[i].length()).equals(",")) {
	                    String fourSn = wYearArrs[i].substring(0, wYearArrs[i].length() - 1) + ")";
	                    wYearArrsSn = wYearArrsSn + fourSn + "\n";
	                } else {
	                    wYearArrsSn = wYearArrsSn + wYearArrs[i] + ")" + "\n";
	                }
	            }
	        }
	        if (wYearArrsSn.length() > 0) {
	            wYearArrsSn = wYearArrsSn.substring(0, wYearArrsSn.length() - 1);
	            report_sn = report_sn + wYearArrsSn;
	        }
	
	
	        String arrtswArSn = "";
	        for (int i = 0; i < tswaArrs.length; i++) {
	            if (tswaArrs[i].length() > 13) {
	                if (tswaArrs[i].substring(tswaArrs[i].length() - 1, tswaArrs[i].length()).equals(",")) {
	                    String fourSn = tswaArrs[i].substring(0, tswaArrs[i].length() - 1) + ")";
	                    arrtswArSn = arrtswArSn + fourSn + "\n";
	                } else {
	                    arrtswArSn = arrtswArSn + tswaArrs[i] + ")" + "\n";
	                }
	            }
	        }
	        if (arrtswArSn.length() > 0) {
	            arrtswArSn = arrtswArSn.substring(0, arrtswArSn.length() - 1);
	            report_sn = report_sn + arrtswArSn;
	        }
	
	
	        String arrFourSn = "";
	        for (int i = 0; i < arrFour1.length; i++) {
	            if (arrFour1[i].length() > 6) {
	                if (arrFour1[i].substring(arrFour1[i].length() - 1, arrFour1[i].length()).equals(",")) {
	                    String fourSn = arrFour1[i].substring(0, arrFour1[i].length() - 1) + ")";
	                    arrFourSn = arrFourSn + fourSn + "\n";
	                } else {
	                    arrFourSn = arrFourSn + arrFour1[i] + ")" + "\n";
	                }
	
	            }
	        }
	        if (arrFourSn.length() > 0) {
	            arrFourSn = arrFourSn.substring(0, arrFourSn.length() - 1);
	            report_sn = report_sn + arrFourSn;
	        }
	
	        String rSNfour = "";
	        for (int i = 0; i < rSNFourS.length; i++) {
	            if (rSNFourS[i].length() > 5) {
	                if (rSNFourS[i].substring(rSNFourS[i].length() - 1, rSNFourS[i].length()).equals(",")) {
	                    String fourSn = rSNFourS[i].substring(0, rSNFourS[i].length() - 1) + ")";
	                    rSNfour = rSNfour + fourSn + "\n";
	                } else {
	                    rSNfour = rSNfour + rSNFourS[i] + ")" + "\n";
	                }
	
	            }
	        }
	        if (rSNfour.length() > 0) {
	            rSNfour = rSNfour.substring(0, rSNfour.length() - 1);
	            report_sn = report_sn + rSNfour;
	        }
	        System.out.println("=========report_sn=========" + report_sn);
	        return JsonWrapper.successWrapper(report_sn);
    	}catch(Exception e){
    		e.printStackTrace();
    		return JsonWrapper.failureWrapperMsg(e.getMessage());
    		
    	}
    }

    /**
     * 查询报告领取签名中间表是否保存图片
     *
     * @param fkInspectionInfoId
     * @return
     */
    @RequestMapping("selectReportSignFile")
    @ResponseBody
    public HashMap<String, Object> selectReportSignFile(String reportDrawSignId,String fkInspectionInfoId) throws Exception{
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	boolean isSign = false;//标记签名是否结束
    	try{
	        //查询是否已经签名推送
	    	ReportDrawSign rds = reportDrawSignService.get(reportDrawSignId);
	        if (rds != null && StringUtil.isNotEmpty(rds.getSignRelativeFile())) {
            	isSign = true;
                map.put("imagePath", rds.getSignRelativeFile().replaceAll("//", "\\"));
	        }
	        map.put("isSign", isSign);
	        map.put("data", rds);
	        map.put("success", true);
    	}catch(Exception e){
    		e.printStackTrace();
    		map.put("success", false);
    		map.put("msg", e.getMessage());
    	}
        return map;
    }

    /**
     * 根据查询条件查询收费统计
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getPaymentListAll")
    @ResponseBody
    public List<Map<String, String>> getPaymentListAll(HttpServletRequest request) throws Exception {
        return inspectionPayInfoService.getPaymentList(request);
    }
}
